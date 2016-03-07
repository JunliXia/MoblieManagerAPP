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
	
	//��������
	public void sendtoGetWaittakeMission(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setGetWaittakeMissionHandler(handler, MyURL.TAKEMISSION);
		hl.execute(js);
	}
	
	//�ύ����
	public void sendtoSendMission(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendMissionHandler(handler, MyURL.SENDMISSION);
		hl.execute(js);
	}
	
	//��������ܽ�
	public void sendtoMissionConclusion(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setGetMissionConclusionHandler(handler, MyURL.GETMISSIONCONCLUSION);
		hl.execute(js);
	}
}
