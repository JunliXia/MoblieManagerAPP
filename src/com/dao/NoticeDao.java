package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class NoticeDao {
	private Context context;
	
	public NoticeDao(Context context){
		this.context =context;
	}
	
	public void sendtoEnterNotice(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterNoticeHandler(handler, MyURL.ENTERNOTICE);
		hl.execute(js);
	}
}
