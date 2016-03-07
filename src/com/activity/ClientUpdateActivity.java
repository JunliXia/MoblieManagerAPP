package com.activity;

import java.util.HashMap;

import org.json.JSONObject;

import com.bll.ClientBll;
import com.entity.CClientEntity;
import com.entity.CClientEntityList;
import com.entity.CEmployeeEntity;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ClientUpdateActivity extends Activity {
	private String[] array = null;
	private CClientEntity cClientEntity;

	private ImageView imageviewBack, imageclientaddress;
	private EditText textclientname, textclientcompany, textclientphone, textclientaddress;
	private Button butUploadClient;
	private TextView textclientzone;
	private ClientBll clientBll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientadd);
		cClientEntity = (CClientEntity) this.getIntent().getExtras()
				.getSerializable("CClientEntity");
		clientBll = ClientBll.getClienetBll(this);
		init();
		setText();
		setListener();

	}

	private void setText() {
		textclientname.setText(cClientEntity.getClientName());
		textclientcompany.setText(cClientEntity.getClientCompany());
		textclientphone.setText(cClientEntity.getClientPhone());
		textclientzone.setText(cClientEntity.getClientArea());
		textclientaddress.setText(cClientEntity.getClientAddress());

	}

	private void init() {
		imageviewBack = (ImageView) findViewById(R.id.imageviewBack);
		imageclientaddress = (ImageView) findViewById(R.id.imageclientaddress);

		textclientname = (EditText) findViewById(R.id.textclientname);
		textclientcompany = (EditText) findViewById(R.id.textclientcompany);
		textclientphone = (EditText) findViewById(R.id.textclientphone);
		textclientzone = (TextView) findViewById(R.id.textclientzone);
		textclientaddress = (EditText) findViewById(R.id.textclientaddress);
		array = new String[] { "北京", "重庆", "安徽", "福建", "甘肃", "广东", "广西", "贵州",
				"海南", "河北", "黑龙江", "河南", "湖北", "湖南", "内蒙古", "江苏", "江西", "吉林",
				"辽宁", "宁夏", "青海", "山西", "山东", "上海", "四川", "天津", "西藏", "新疆",
				"云南", "浙江", "陕西", "台湾", "香港", "澳门", "海外", "其他" };
		
		butUploadClient=(Button)findViewById(R.id.butUploadClient);

	}

	/**
	 * 
	 */
	private void setListener() {
		imageviewBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
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
				Intent intent = new Intent(ClientUpdateActivity.this,
						ClientUpdataAddressActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("address", textclientaddress.getText()
						.toString());
				bundle.putString("name", textclientname.getText().toString());
				bundle.putString("company", textclientcompany.getText()
						.toString());
				bundle.putString("phone", textclientphone.getText().toString());
				bundle.putString("zone", textclientzone.getText().toString());
				bundle.putString("WhatActivity", "ClientUpdateActivity");
				bundle.putInt("id", cClientEntity.getClientId());
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
				JSONObject js=Entity.toJson(cClientEntity.getClientId(),textclientname.getText().toString(), textclientcompany.getText().toString(), textclientphone.getText().toString(), textclientzone.getText().toString(), textclientaddress.getText().toString(),MyOpcode.Operation.UPDATA_CLIENT);
				clientBll.UpdateClient(js, UpdateClientHandler);
			}
		});

	}

	public Handler UpdateClientHandler=new Handler(){

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
				intent.setClass(ClientUpdateActivity.this, ClientActivity.class);
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
