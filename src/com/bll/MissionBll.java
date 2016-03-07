package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.MissionDao;
import com.entity.CMissionConclusionEntity;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class MissionBll {
	private static MissionBll missionBll=null;
	
	private Context context;
	
	public MissionBll(Context context){
		this.context=context;
	}
	
	public static MissionBll getMissionBll(Context context){
		if(missionBll==null){
			missionBll=new MissionBll(context);
		}
		return missionBll;
	}
	
	//进入任务
	public void EnterMission(JSONObject js,final Handler EnterMissionHandler,String URL){
		Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterMission接受");
				JSONObject json=(JSONObject)msg.obj;
				List<CMissionEntity> missionEntities=new ArrayList<CMissionEntity>();
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray(MyOpcode.Mission.MissionList);
						for(int i=0;i<arrays.length();i++){
							JSONObject missionJS=arrays.getJSONObject(i);
							CMissionEntity cMissionEntity=new CMissionEntity(
									missionJS.getInt("MissionId"),
									missionJS.getString("MissionPubdate") ,
									missionJS.getString("MissionContent"),
									missionJS.getString("MissionDeadline"), 
									missionJS.getInt("MissionState"), 
									missionJS.getInt("MissionDelayState"),
									missionJS.getInt("MissionBussinessBandState")
									);
							
							missionEntities.add(cMissionEntity);
						}
					}
					CMissionEntityList cMissionEntityList=new CMissionEntityList(missionEntities);
					if(EnterMissionHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CMissionEntityList", cMissionEntityList);
						Message mg=new Message();
						mg.obj = map;
						EnterMissionHandler.sendMessage(mg);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterMission--e:" + e);
					e.printStackTrace();
				}
			}
		};
		new MissionDao(context).sendtoEnterMission(handler, js,URL);
	}
	
	//获得任务总结
	public void GetMissionConclusion(JSONObject js,final Handler GetMissionConclusionHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("GetMissionConclusion接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					CMissionConclusionEntity cMissionConclusionEntity=new CMissionConclusionEntity();
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						cMissionConclusionEntity=new CMissionConclusionEntity(
								json.getInt("MissionConclusionId"), 
								json.getInt("MissionCheck"), 
								json.getString("MissionSummary"), 
								json.getString("MissionSubmitTime"),
								json.getString("MissionAccessoryPath"));
						
					}
					if(GetMissionConclusionHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CMissionConclusionEntity", cMissionConclusionEntity);
						Message mg=new Message();
						mg.obj = map;
						GetMissionConclusionHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("EnterMission--e:" + e);
						e.printStackTrace();
				}
			}
			
		};
		
		new MissionDao(context).sendtoMissionConclusion(handler, js);
	}
	
	
	//接受任务
	public void GetWaittakeMission(JSONObject js,final Handler GetWaittakeMissionHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("GetWaittakeMission接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if (json.getBoolean(MyOpcode.Operation.SIGN)) {
						ToastUtil.show(context, "接受成功");
						
					}
					if(GetWaittakeMissionHandler!=null){
						Message mg = new Message();
						GetWaittakeMissionHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("GetWaittakeMission--e:" + e);
					e.printStackTrace();
				}
				
			}
			
		};
		new MissionDao(context).sendtoGetWaittakeMission(handler, js);
	}
	
	
	//提交任务
	public void SentMission(JSONObject js,final Handler SentMissionHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("SentMission接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if (json.getBoolean(MyOpcode.Operation.SIGN)) {
						ToastUtil.show(context, "提交成功");
						
					}
					if(SentMissionHandler!=null){
						Message mg = new Message();
						SentMissionHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("SentMission--e:" + e);
					e.printStackTrace();
				}
				
			}
			
		};
		new MissionDao(context).sendtoSendMission(handler, js);
	}
}
