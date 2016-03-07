package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class SuggestDao {
	private Context context;
	
	public SuggestDao(Context context){
		this.context=context;
	}
	
	public void senttoSentSuggent(Handler handler,JSONObject js){
		HTTPLink hl=new HTTPLink();
		hl.setSendSuggestHandler(handler, MyURL.SENTSUGGEST);
		hl.execute(js);
	}
}
