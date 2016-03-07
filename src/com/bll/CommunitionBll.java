package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.CommunitionDao;
import com.entity.CEmployeeEntity;
import com.entity.CEmployeeEntityList;
import com.tool.MyOpcode;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class CommunitionBll {
	private static CommunitionBll communitionBll=null;
	
	private Context context;
	public CommunitionBll(Context context){
		this.context=context;
	}
	
	public static CommunitionBll getCommunitionBll(Context context){
		if(communitionBll==null){
			communitionBll=new CommunitionBll(context);
		}
		return communitionBll;
	}
	
	//进入通讯里
	public void EnterCommunition(JSONObject js,final Handler EnterCommunitionHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterCommunition接受");
				JSONObject json=(JSONObject)msg.obj;
				List<CEmployeeEntity> cEmployeeEntities=new ArrayList<CEmployeeEntity>();
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray(MyOpcode.Employee.EmployeeList);
						for(int i=0;i<arrays.length();i++){
							JSONObject communictionJS=arrays.getJSONObject(i);
							CEmployeeEntity cEmployeeEntity=new CEmployeeEntity(
									communictionJS.getInt("EmployeeId"), 
									communictionJS.getString("EmployeeAccount"),
									communictionJS.getString("EmployeePassword"),
									communictionJS.getString("EmployeeName"),
									communictionJS.getString("EmployeePhone"),
									communictionJS.getString("EmployeeSex"), 
									communictionJS.getString("EmployeeDepartment"), 
									communictionJS.getString("EmployeeJob"),
									communictionJS.getInt("EmployeeType"));
							cEmployeeEntities.add(cEmployeeEntity);
						}
					}
					
					CEmployeeEntityList cEmployeeEntityList=new CEmployeeEntityList(cEmployeeEntities);
					if(EnterCommunitionHandler!=null){
						HashMap<String, Object> map=new HashMap<String, Object>();
						map.put("CEmployeeEntityList", cEmployeeEntityList);
						Message mg=new Message();
						mg.obj = map;
						EnterCommunitionHandler.sendMessage(mg);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterMission--e:" + e);
					e.printStackTrace();
				}
				
			}
			
			
		};
		
		new CommunitionDao(context).senttoEnterCommunition(handler, js);
		
	}
	
}
