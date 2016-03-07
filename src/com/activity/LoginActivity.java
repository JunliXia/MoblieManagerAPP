package com.activity;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.bll.CommunitionBll;
import com.bll.LoginBll;
import com.entity.CEmployeeEntity;
import com.entity.CEmployeeEntityList;
import com.example.mobliemanager.R;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

public class LoginActivity extends Activity {

	private Button butlogin;
	private EditText account;
	private EditText password;

	private LoginBll loginBll;

	private CEmployeeEntity cEmployeeEntity;
	private JSONObject loginjs;

	private CommunitionBll communitionBll;
	
	
	private  ProgressDialog pdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		loginBll = LoginBll.getLoginBll(this);
		communitionBll=CommunitionBll.getCommunitionBll(this);
		cEmployeeEntity = new CEmployeeEntity();
		init();
		setListener();
	
		
	}

	private void init() {
		butlogin = (Button) findViewById(R.id.butlogin);
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
	}

	private void setListener() {
		butlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				butlogin.setTextColor(Color.WHITE);
				// int id=Integer.parseInt(account.getText().toString().trim());
				String strid = account.getText().toString();
				String pwd = password.getText().toString();
				System.out.println(account.getText().toString().trim());
				if(!(strid.equals("")||pwd.equals(""))){
					pdialog=ProgressDialog.show(LoginActivity.this, "正在加载...", "正在登陆中");  
					loginjs = cEmployeeEntity.toJSon(strid, pwd,
							MyOpcode.Operation.LOGIN);
					loginBll.login(loginjs, loginHandler,pdialog);
					
				}else{
					ToastUtil.show(LoginActivity.this, "请输入完整的帐号密码");
				}
				
			}
		});

	}

	
	public Handler loginHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			JSONObject js=new JSONObject();
			try {
				js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.ENTER_COMMINITION);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			communitionBll.EnterCommunition(js, EnterCommunitionHandler);
			
		
		}
		
	};
	
	private Handler EnterCommunitionHandler=new Handler(){

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CEmployeeEntityList cEmployeeEntityList=(CEmployeeEntityList)map.get("CEmployeeEntityList");
			
			ToastUtil.show(LoginActivity.this, "登录成功");
			Intent intent=new Intent(LoginActivity.this,MainActivity.class);
			intent.putExtra("CEmployeeEntityList", cEmployeeEntityList);
			startActivity(intent);
			finish();
			startService(new Intent(LoginActivity.this,LocationService.class));
			
		}
		
	};
	
}
