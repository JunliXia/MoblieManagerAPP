package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.VisitDao;
import com.entity.CVisitConclusionEntity;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class VisitBll {
	private static VisitBll visitBll=null;
	private Context context;
	
	public VisitBll(Context context){
		this.context=context;
	}
	
	public static VisitBll getVisitBll(Context context){
		if(visitBll==null){
			visitBll=new VisitBll(context);
		}
		return visitBll;
	}
	
	//提交客户拜访
	public void EnterClientVisit(JSONObject js,final Handler EnterClientVisitHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterClientVisit接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						ToastUtil.show(context, "提交成功");
					}
					if(EnterClientVisitHandler!=null){
						Message mg=new Message();
						EnterClientVisitHandler.sendMessage(mg);
					}
					
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterClientVisit--e:" + e);
					e.printStackTrace();
				}
			}
			
		};
		
		new VisitDao(context).senttoEnterClientVisit(handler, js);
	}
	
	
	//进入拜访记录
	public void EnterVisit(JSONObject js,final Handler EnterVisitHandler,String url){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterVisit接收");
				JSONObject json=(JSONObject)msg.obj;
				List<CVisitEntity> visitEntities=new ArrayList<CVisitEntity>();
				
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray("VisitPlanList");
						for(int i=0;i<arrays.length();i++){
							JSONObject visitJS=arrays.getJSONObject(i);
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
							
							
							visitEntities.add(cVisitEntity);
						}
					}
					
					CVisitEntityList cVisitEntityList=new CVisitEntityList(visitEntities);
					if(EnterVisitHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CVisitEntityList", cVisitEntityList);
						Message mg=new Message();
						mg.obj=map;
						EnterVisitHandler.sendMessage(mg);
					}
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterVisit--e:" + e);
					e.printStackTrace();
				}
				
			}
			
		};
		
		new VisitDao(context).sendtoEnterVisit(handler, js,url);
	}
	
	
	public void EnterCompleteVisit(JSONObject js,final Handler EnterVisitHandler,String url){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterCompleteVisit接收");
				JSONObject json=(JSONObject)msg.obj;
				List<CVisitEntity> visitEntities=new ArrayList<CVisitEntity>();
				
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray("VisitPlanList");
						for(int i=0;i<arrays.length();i++){
							JSONObject visitJS=arrays.getJSONObject(i);
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
						    JSONArray concluarray=visitJS.getJSONArray("VisitConclusionList");
						    List<CVisitConclusionEntity> conclusionEntities=new ArrayList<CVisitConclusionEntity>();
						    if(concluarray.length()!=0){
						    	for(int j=0;j<concluarray.length();j++){
									JSONObject concluJS=concluarray.getJSONObject(j);
									CVisitConclusionEntity conclusionEntity=new CVisitConclusionEntity(
											concluJS.getInt("VisitConclusionId"), 
											concluJS.getInt("VisitCheck"),
											concluJS.getString("VisitSubmitTime"),
											concluJS.getString("VisitSummary"),
											concluJS.getString("VisitCommand"), 
											concluJS.getString("VisitAccessoryPath"));
								    conclusionEntities.add(conclusionEntity);
							    }
						    }
						    
						    cVisitEntity.setConclusionEntities(conclusionEntities);
							visitEntities.add(cVisitEntity);
						}
					}
					
					CVisitEntityList cVisitEntityList=new CVisitEntityList(visitEntities);
					if(EnterVisitHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CVisitEntityList", cVisitEntityList);
						Message mg=new Message();
						mg.obj=map;
						EnterVisitHandler.sendMessage(mg);
					}
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterVisit--e:" + e);
					e.printStackTrace();
				}
				
			}
			
		};
		
		new VisitDao(context).sendtoEnterVisit(handler, js,url);
	}
	
	
	
}
