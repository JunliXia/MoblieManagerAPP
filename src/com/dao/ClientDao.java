package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class ClientDao {
	private Context context;
	
	public ClientDao(Context context){
		this.context=context;
	}
	
	//进入客户管理
	public void senttoEnterClientManager(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterClientManagerHandler(handler, MyURL.ENTERCLIENTMANAGER);
		hl.execute(js);
	}
	
	//增加客户
	public void senttoAddClient(Handler handler ,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setAddClientHandler(handler, MyURL.ADDCLIENT);
		hl.execute(js);
	}
	
	//修改客户
	public void senttoUpdateClient(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setUpdateClientHandler(handler, MyURL.UPDATECLIENT);
		hl.execute(js);
	}
	//进入客户提交记录
	public void sendtoClientSubmit(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setGetClientSubmitHandler(handler, MyURL.GETCLIENTSUBMITSERVLET);
		hl.execute(js);
	}
}
