package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class VisitDao {
	private Context context;
	
	public VisitDao(Context context){
		this.context=context;
	}
	
	//进入拜访记录
	public void sendtoEnterVisit(Handler handler,JSONObject js,String url){
		HTTPLink hl=new HTTPLink();
//		hl.setEnterVisitHandler(handler, MyURL.ENTERVISIT);
		hl.setEnterVisitHandler(handler,url);
		hl.execute(js);
	}
	
	//提交拜访
	public void senttoEnterClientVisit(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setEnterClientVisitHandler(handler, MyURL.SUBMITVISITCONCLUSION);
		hl.execute(js);
	}
}
