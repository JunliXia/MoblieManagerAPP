package com.activity;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bll.ClientBll;
import com.bll.VisitBll;
import com.entity.CClientEntity;
import com.entity.CClientEntityList;
import com.entity.CEmployeeEntity;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;


public class ClientInfomationActivity extends Activity{
//	private ImageView imgrewrite;
	
	private ImageView imageclientaddress;
	private ImageView imageviewBack;
	private Button butclientviste;
	
	private TextView textclientname,textclientcompany,
					 textclientphone,textclientarea,
					 textclientaddress;
	
	int clienttype=0;
	private CClientEntity cClientEntity;
	
	private  ClientBll clientBll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientinfomation);
		cClientEntity=(CClientEntity)this.getIntent().getExtras().getSerializable("CClientEntity");
		clientBll=ClientBll.getClienetBll(this);
		init();
		setListener();
		getKey();
		setText();
	}
	private void getKey(){
		  Bundle bundle=getIntent().getExtras();  
		  clienttype=bundle.getInt("clienttype");
//		  if(clienttype==MyConstant.CLIENT_COMPANY){
//			  imgrewrite.setVisibility(View.GONE);
//		  }else if (clienttype==MyConstant.CLIENT_PERSON) {
//			  imgrewrite.setVisibility(View.VISIBLE);
//		}
	}
	
	private void setText(){
		
		textclientname.setText(cClientEntity.getClientName());
		textclientcompany.setText(cClientEntity.getClientCompany());
		textclientphone.setText(cClientEntity.getClientPhone());
		textclientarea.setText(cClientEntity.getClientArea());
		textclientaddress.setText(cClientEntity.getClientAddress());
		
	}
	private void init(){
		imageclientaddress=(ImageView)findViewById(R.id.imageclientaddress);
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
//		imgrewrite=(ImageView)findViewById(R.id.imgrewrite);
		butclientviste=(Button)findViewById(R.id.butclientviste);
		butclientviste.setVisibility(View.GONE);
		textclientname=(TextView)findViewById(R.id.textclientname);
		textclientcompany=(TextView)findViewById(R.id.textclientcompany);
		textclientphone=(TextView)findViewById(R.id.textclientphone);
		textclientarea=(TextView)findViewById(R.id.textclientarea);
		textclientaddress=(TextView)findViewById(R.id.textclientaddress);
	}
	
	private void setListener(){
		imageclientaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(ClientInfomationActivity.this,ClientAddressActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("textclientarea", textclientarea.getText().toString());
				bundle.putString("textclientaddress", textclientaddress.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CEmployeeEntity emtity=new CEmployeeEntity();
				JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENT_MANAGER);
				clientBll.EnterClientManager(enterClientJS, EnterClientManagerHandler);
			}
		});
		
		
//		butclientviste.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(ClientInfomationActivity.this,ClientVisitActivity.class);
//				intent.putExtra("CClientEntity", cClientEntity);
//				startActivity(intent);
//			}
//		});
		
//		imgrewrite.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent =new Intent();
//				intent.setClass(ClientInfomationActivity.this, ClientUpdateActivity.class);
//				intent.putExtra("CClientEntity", cClientEntity);
//				startActivity(intent);
//				finish();
//			}
//		});
	}
	
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
					bundle.putInt("ClientType", clienttype);
					intent.putExtra("CClientEntityList", cClientEntityList);
					intent.putExtras(bundle);
					intent.setClass(ClientInfomationActivity.this, ClientActivity.class);
					startActivity(intent);
					finish();
				}
				
				
			};

}
