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
	
	//进入考勤管理
	public void sendtoEnterAttendance(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterAttendanceHander(handler, MyURL.ENTERATTENDANCE);;
		hl.execute(js);
	}
	
	//签到
	public void sendtoRegisterAttendance(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendAttendanceHandler(handler,MyURL.SENDREGISTERATTENDANCE);
		hl.execute(js);
	}
	//签退
	public void sendtoSignOutAttendance(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendAttendanceHandler(handler,MyURL.SENDSIGNOUTATTENDANCE);
		hl.execute(js);
	}
}
