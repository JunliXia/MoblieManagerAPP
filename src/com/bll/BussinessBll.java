package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.BussinessDao;
import com.entity.CBussinessEntity;
import com.entity.CBussinessEntityList;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class BussinessBll {

	private static BussinessBll bussinessBll=null;
	
	private Context context;
	public BussinessBll(Context context){
		this.context=context;
	}
	
	public static BussinessBll getBussinessBll(Context context){
		if(bussinessBll==null){
			bussinessBll=new BussinessBll(context);
		}
		return bussinessBll;
	}
	
	
	//进入出差
	public void EnterBussiness(JSONObject js,final Handler EnterBussinessHandler,final ProgressDialog pdialog){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterBussiness接受");
				JSONObject json=(JSONObject)msg.obj;
				CBussinessEntity cBussinessEntity=new CBussinessEntity();
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
							 cBussinessEntity=new CBussinessEntity(
									json.getInt("BussinessId"), 
									json.getString("BussinessSideAddress"),
									json.getString("BussinessContent"), 
									json.getString("BussinessRegisterTime"), 
									json.getString("BussinessInAddress"),
									json.getString("BussinessInTime"),
									json.getString("BussinessOutAddress"),
									json.getString("BussinessOutTime"), 
									json.getString("BussinessReturnTime"),
									json.getInt("BussinessState"));
							
						
					}
					if(EnterBussinessHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CBussinessEntity", cBussinessEntity);
						Message mg=new Message();
						mg.obj = map;
						EnterBussinessHandler.sendMessage(mg);
						pdialog.dismiss();
					}
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterBussiness--e:" + e);
					e.printStackTrace();
				}
			}
			
		};
		new BussinessDao(context).sendtoEnterBussiness(handler, js);
	}
	
	//进入出差记录
	public void EnterBussinessRecalss(JSONObject js,final Handler EnterBussinessRecall){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterBussinessRecalss接收");
				JSONObject json=(JSONObject)msg.obj;
				List<CBussinessEntity> cBussinessEntities=new ArrayList<CBussinessEntity>();
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray("BussinessList");
						for(int i=0;i<arrays.length();i++){
							JSONObject busjson =arrays.getJSONObject(i);
						CBussinessEntity cBussinessEntity=new CBussinessEntity(
								busjson.getInt("BussinessId"), 
								busjson.getString("BussinessSideAddress"),
								busjson.getString("BussinessContent"), 
								busjson.getString("BussinessRegisterTime"), 
								busjson.getString("BussinessInAddress"),
								busjson.getString("BussinessInTime"),
								busjson.getString("BussinessOutAddress"),
								busjson.getString("BussinessOutTime"), 
								busjson.getString("BussinessReturnTime"),
								busjson.getInt("BussinessState"));
							cBussinessEntities.add(cBussinessEntity);
						}
						
					}
					CBussinessEntityList cBussinessEntityLIST=new CBussinessEntityList(cBussinessEntities);
					if(EnterBussinessRecall!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CBussinessEntityList", cBussinessEntityLIST);
						Message mg=new Message();
						mg.obj = map;
						EnterBussinessRecall.sendMessage(mg);
					}
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterBussiness--e:" + e);
					e.printStackTrace();
				}
				
			}
			
		};
		new BussinessDao(context).senttoEnterBussinessRecall(handler, js);
		
		
	}
	
	//获取出差活动
	public void GetBussinessActivity(JSONObject js,final Handler GetBussinessHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("GetBussinessActivity接收");
				JSONObject json=(JSONObject)msg.obj;
				List<CMissionEntity> cMissionEntities=new ArrayList<CMissionEntity>();
				List<CVisitEntity> cVisitEntities=new ArrayList<CVisitEntity>();
				try {
					System.out.println("sign"+json.getBoolean(MyOpcode.Operation.SIGN));
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray missionarrays=json.getJSONArray("MissionList");
						for(int i=0;i<missionarrays.length();i++){
							JSONObject missjson=missionarrays.getJSONObject(i);
							CMissionEntity cMissionEntity=new CMissionEntity(
									missjson.getInt("MissionId"), 
									missjson.getString("MissionPubdate"),
									missjson.getString("MissionContent"), 
									missjson.getString("MissionDeadline"),
									missjson.getInt("MissionState"),
									missjson.getInt("MissionDelayState"),
									missjson.getInt("MissionBussinessBandState"));
							cMissionEntities.add(cMissionEntity);
						}
						
						JSONArray visitarrays=json.getJSONArray("VisitPlanList");
						for(int i=0;i<visitarrays.length();i++){
							JSONObject visitJS=visitarrays.getJSONObject(i);
							CVisitEntity cVisitEntity=new CVisitEntity(
									visitJS.getInt("VisitPlanId"), 
									visitJS.getString("VisitPlanPubdate"), 
									visitJS.getString("VisitPlanStartTime"), 
									visitJS.getString("VisitPlanEndTime"),
									visitJS.getInt("VisitPlanState"),
									visitJS.getInt("ClientId"), 
									visitJS.getString("ClientName"),
									visitJS.getString("ClientCompany"),
									visitJS.getString("ClientPhone"), 
									visitJS.getString("ClientArea"),
									visitJS.getString("ClientAddress"),
									visitJS.getInt("ClientState"));
							
							
							cVisitEntities.add(cVisitEntity);
						}
					}
					CMissionEntityList cMissionEntityList=new CMissionEntityList(cMissionEntities);
					CVisitEntityList cVisitEntityList=new CVisitEntityList(cVisitEntities);
					if(GetBussinessHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CMissionEntityList", cMissionEntityList);
						map.put("CVisitEntityList", cVisitEntityList);
						Message mg=new Message();
						mg.obj = map;
						GetBussinessHandler.sendMessage(mg);
					}
					
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("EnterBussiness--e:" + e);
					e.printStackTrace();
				}
			}
			
		};
		
		new BussinessDao(context).sentoGetBussinessActivity(handler, js);
	}
	
	
	
	//提交出差
	public void SendBussiness(JSONObject js,final Handler SendBussinessHandler,String url){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("SendBussiness接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						ToastUtil.show(context, "提交成功");
						
					}
					
					if(SendBussinessHandler!=null){
						Message mg=new Message();
						SendBussinessHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("SendBussiness--e:" + e);
					e.printStackTrace();
				}
				
				
			}
			
		};
		new BussinessDao(context).senttoSendBussiness(handler, js,url);
	}
	
}
