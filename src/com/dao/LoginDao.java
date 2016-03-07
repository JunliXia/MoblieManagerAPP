package com.dao;

import org.json.JSONObject;






import com.http.HTTPLink;
import com.tool.MyURL;

import android.content.Context;
import android.os.Handler;

public class LoginDao {
	private Context context;
	public LoginDao(Context context) {
		this.context = context;
		// hl = HTTPLink.getHttpLink(context);
	}

	public void sendtoLogin(Handler handler,JSONObject js){
		System.out.println("logindao---sendtologin");
		HTTPLink hl = new HTTPLink();
		hl.setLoginHandler(handler,MyURL.LOGINURI);
		hl.execute(js);
	}
}
