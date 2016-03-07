package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class AttendanceDao {

	private Context context;
	
	public AttendanceDao(Context context){
		this.context=context;
	}
	
	//���뿼�ڹ���
	public void sendtoEnterAttendance(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterAttendanceHander(handler, MyURL.ENTERATTENDANCE);;
		hl.execute(js);
	}
	
	//ǩ��
	public void sendtoRegisterAttendance(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendAttendanceHandler(handler,MyURL.SENDREGISTERATTENDANCE);
		hl.execute(js);
	}
	//ǩ��
	public void sendtoSignOutAttendance(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendAttendanceHandler(handler,MyURL.SENDSIGNOUTATTENDANCE);
		hl.execute(js);
	}
}
