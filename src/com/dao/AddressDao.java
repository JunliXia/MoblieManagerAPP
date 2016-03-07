package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class AddressDao {
	private Context context;
	public AddressDao(Context context){
		this.context=context;
	}
	
	public void sendAddress(Handler handler,JSONObject js){
		HTTPLink hl = new HTTPLink();
		hl.setSendAddressHandler(handler, MyURL.SENDADDRESS);
		hl.execute(js);
	}
}
