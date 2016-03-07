package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class MissionDao {
	private Context context;
	
	public MissionDao(Context context){
		this.context=context;
	}
	
	public void sendtoEnterMission(Handler handler,JSONObject js,String URL){
		HTTPLink hl=new HTTPLink();
		hl.setEnterMissionHandler(handler,URL);
		hl.execute(js);
	}
	
	//接受任务
	public void sendtoGetWaittakeMission(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setGetWaittakeMissionHandler(handler, MyURL.TAKEMISSION);
		hl.execute(js);
	}
	
	//提交任务
	public void sendtoSendMission(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendMissionHandler(handler, MyURL.SENDMISSION);
		hl.execute(js);
	}
	
	//获得任务总结
	public void sendtoMissionConclusion(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setGetMissionConclusionHandler(handler, MyURL.GETMISSIONCONCLUSION);
		hl.execute(js);
	}
}
