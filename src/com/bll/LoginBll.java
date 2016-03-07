package com.bll;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.LoginDao;
import com.entity.CEmployeeEntity;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class LoginBll {

	private static LoginBll loginBll = null;
	private Context context;

	public LoginBll(Context context) {
		this.context = context;
	}

	public static LoginBll getLoginBll(Context context) {
		if (loginBll == null) {
			loginBll = new LoginBll(context);
		}
		return loginBll;
	}

	private static CEmployeeEntity curuser = new CEmployeeEntity();

	public static CEmployeeEntity getCuruser() {
		return curuser;
	}

	public static void setCuruser(CEmployeeEntity curuser) {
		LoginBll.curuser = curuser;
	}

	// 登录
		public void login(JSONObject js, final Handler loginhandler,final ProgressDialog pdialog) {
			Handler handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					System.out.println("login接收");
					JSONObject json = (JSONObject) msg.obj;
					try {
						if (json.getBoolean(MyOpcode.Operation.SIGN)) {
							curuser = new CEmployeeEntity(
									json.getInt("EmployeeId"),
									json.getString("EmployeePassword"));
							if (loginhandler != null) {
								Message mg = new Message();
								loginhandler.sendMessage(mg);
							}
						}else{
							pdialog.dismiss();
							ToastUtil.show(context, "请输入正确的帐号或密码");
						}

						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.out.println("loginHandler--e:" + e);
						e.printStackTrace();
					}
				}

			};
			new LoginDao(context).sendtoLogin(handler, js);
		}
	
}
