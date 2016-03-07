package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class BussinessDao {
	private Context context;
	
	public BussinessDao(Context context){
		this.context=context;
	}
	
	//进入出差
	public void sendtoEnterBussiness(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterBussinessHandler(handler, MyURL.ENTERBUSSINESS);
		hl.execute(js);
	}
	
	//提交出差
	public void senttoSendBussiness(Handler handler,JSONObject js,String url){
		HTTPLink hl=new HTTPLink();
		hl.setSendBussinessHandler(handler,url);
		hl.execute(js);
	}
	
	//进入出差记录
	public void senttoEnterBussinessRecall(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterBussinessRecallHandler(handler, MyURL.ENTERBUSSINESSRECALEE);
		hl.execute(js);
	}
	
	//获取出差活动
	public void sentoGetBussinessActivity(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setGetBussinessActivityHandler(handler, MyURL.GETBUSSINESSACTIVITY);
		hl.execute(js);
	}
	
	
}
