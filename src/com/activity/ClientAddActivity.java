package com.activity;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bll.ClientBll;
import com.entity.CClientEntity;
import com.entity.CClientEntityList;
import com.entity.CEmployeeEntity;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;

public class ClientAddActivity extends Activity {
	private String[] array = null;
	private TextView textclientzone;
	private ImageView imageclientaddress,imageviewBack;
	private Button butUploadClient;
	private String address,name,company,phone,zone;
	
	private EditText  textclientname,textclientcompany,textclientphone,textclientaddress;
	
	
	private ClientBll clientBll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientadd);
		clientBll=ClientBll.getClienetBll(this);
		init();
		setListener();
		getKey();
	}

	private void getKey(){
		Bundle bundle=getIntent().getExtras();
		address=bundle.getString("address");
		name=bundle.getString("name");
		company=bundle.getString("company");
		phone=bundle.getString("phone");
		zone=bundle.getString("zone");
		
		System.out.println(name+"   "+company+"  "+phone+"   "+zone);
	    textclientname.setText(name);
		textclientcompany.setText(company);
		textclientphone.setText(phone);
		textclientaddress.setText(address);
		textclientzone.setText(zone);
		
	}
	private void init() {
		textclientzone = (TextView) findViewById(R.id.textclientzone);
		imageclientaddress=(ImageView)findViewById(R.id.imageclientaddress);
		
		array = new String[] { "北京", "重庆", "安徽", "福建", "甘肃", "广东", "广西", "贵州",
				"海南", "河北", "黑龙江", "河南", "湖北", "湖南", "内蒙古", "江苏", "江西", "吉林",
				"辽宁", "宁夏", "青海", "山西", "山东", "上海", "四川", "天津", "西藏", "新疆",
				"云南", "浙江", "陕西", "台湾", "香港", "澳门", "海外", "其他" };
		
		butUploadClient=(Button)findViewById(R.id.butUploadClient);
		textclientname=(EditText)findViewById(R.id.textclientname);
		textclientcompany=(EditText)findViewById(R.id.textclientcompany);
		textclientphone=(EditText)findViewById(R.id.textclientphone);
		textclientaddress=(EditText)findViewById(R.id.textclientaddress);
		
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
	}

	private void setListener() {
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CEmployeeEntity emtity=new CEmployeeEntity();
				JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENT_MANAGER);
				clientBll.EnterClientManager(enterClientJS, EnterClientManagerHandler);
			}
		});
		textclientzone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog5();
			}
		});
		
		imageclientaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ClientAddActivity.this,ClientUpdataAddressActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("address",  textclientaddress.getText().toString());
				bundle.putString("name", textclientname.getText().toString());
				bundle.putString("company", textclientcompany.getText().toString());
				bundle.putString("phone", textclientphone.getText().toString());
				bundle.putString("zone",textclientzone.getText().toString());
				bundle.putInt("id", 0);
				bundle.putString("WhatActivity", "ClientAddActivity");
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		butUploadClient.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CClientEntity Entity=new CClientEntity();
				JSONObject js=Entity.toJson(textclientname.getText().toString(), textclientcompany.getText().toString(), textclientphone.getText().toString(), textclientzone.getText().toString(), textclientaddress.getText().toString(),MyOpcode.Operation.ADD_CLIENT);
				clientBll.AddClient(js, AddClientHandler);
			
				
			}
		});
	}

	private Handler AddClientHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			CEmployeeEntity emtity=new CEmployeeEntity();
			JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENT_MANAGER);
			clientBll.EnterClientManager(enterClientJS, EnterClientManagerHandler);
		}
		
		
	};
	
	
	//进入客户管理
		public Handler EnterClientManagerHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
				CClientEntityList cClientEntityList=(CClientEntityList)map.get("CClientEntityList");
				Intent intent=new Intent();
				Bundle bundle=new Bundle();
				bundle.putInt("ClientType", MyConstant.CLIENT_PERSON);
				intent.putExtra("CClientEntityList", cClientEntityList);
				intent.putExtras(bundle);
				intent.setClass(ClientAddActivity.this, ClientActivity.class);
				startActivity(intent);
				finish();
			}
			
			
		};
	
	public void showDialog5() {
		final String SingleChoiceItem[] = array;
		final StringBuilder sb = new StringBuilder();
		new AlertDialog.Builder(this)
				.setTitle("选择所在地")
				.setSingleChoiceItems(SingleChoiceItem, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								sb.append(SingleChoiceItem[which]);
								textclientzone.setText(sb);
								dialog.dismiss();

							}

						}).setNegativeButton("取消", null).show();
	}
}
