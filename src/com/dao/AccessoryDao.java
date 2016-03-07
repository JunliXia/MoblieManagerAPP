package com.dao;

import org.json.JSONObject;

import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class AccessoryDao {

	private Context context;

	public AccessoryDao(Context context) {
		this.context = context;
	}

	public void uploadImg(Handler handler, JSONObject js) {
		HTTPLink hl = new HTTPLink();
		hl.setUploadImgHandler(handler, MyURL.UPLOADIMG);
		hl.execute(js);
	}

}
