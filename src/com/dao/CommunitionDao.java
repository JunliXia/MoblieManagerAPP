package com.dao;

import com.http.HTTPLink;
import com.tool.MyURL;

import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;

public class CommunitionDao {
	private Context context;
	
	public CommunitionDao(Context context){
		this.context=context;
	}
	
	//进入通讯录
	public void senttoEnterCommunition(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterCommunitionHandler(handler, MyURL.ENTERCOMMUNITION);
		hl.execute(js);
	}
	
	
}
