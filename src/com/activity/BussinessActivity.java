package com.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.bbalbs.common.a.c;
import com.bll.BussinessBll;
import com.entity.CBussinessEntity;
import com.entity.CBussinessEntityList;
import com.entity.CEmployeeEntity;
import com.entity.CMissionEntityList;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyOpcode;
import com.tool.MyURL;

public class BussinessActivity extends Activity{

	private TextView textBussinessPlace;
	private TextView textBussinessContent;
	private ImageView imageBack;
	
	private ImageView imageBussimessRegirest;
	private TextView textRegirestTime;
	
	private ImageView imageBussimessinDestination;
	private TextView textInDestinationPlace;
	private TextView textInDestinationTime;
	
	private ImageView imageBussimessoutComing;
	private TextView textComingtime;
	
	private ImageView imageBussimessoutDestination;
	private TextView texOuttDestinationTime;
	private TextView textOutDestinationPlace;
	
	private String address;
	private CBussinessEntity cBussinessEntity;
	private BussinessBll bussinessBll;
	
	private ImageView imgbussinessrecall;
	
	private Button butbussinessactivity;
	
	
	private  ProgressDialog pdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bussiness);
		cBussinessEntity=(CBussinessEntity)this.getIntent().getExtras().getSerializable("CBussinessEntity");
		bussinessBll=BussinessBll.getBussinessBll(this);
		init();
		setListener();
		getKey();
		setText();
	}
	
	private void getKey(){
		  Bundle bundle=getIntent().getExtras();  
		  address=bundle.getString("address");
		  textInDestinationPlace.setText(address);
	}
	
	private void setText(){
		textBussinessPlace.setText(cBussinessEntity.getBussinessSideAddress());
		textBussinessContent.setText(cBussinessEntity.getBussinessContent());
		textRegirestTime.setText(cBussinessEntity.getBussinessRegisterTime());
		textInDestinationPlace.setText(cBussinessEntity.getBussinessInAddress());
		textInDestinationTime.setText(cBussinessEntity.getBussinessInTime());
		texOuttDestinationTime.setText(cBussinessEntity.getBussinessOutTime());
		textOutDestinationPlace.setText(cBussinessEntity.getBussinessOutAddress());
		textComingtime.setText(cBussinessEntity.getBussinessReturnTime());
	}
	

	private void init(){
		textBussinessPlace=(TextView)findViewById(R.id.textBussinessPlace);
		textBussinessPlace.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
		textBussinessContent=(TextView)findViewById(R.id.textBussinessContent);
		imageBack=(ImageView)findViewById(R.id.imageviewBack);
		imageBussimessRegirest=(ImageView)findViewById(R.id.imageBussimessRegirest);
		textRegirestTime=(TextView)findViewById(R.id.textRegirestTime);
		imageBussimessoutComing=(ImageView)findViewById(R.id.imageBussimessoutComing);
		textComingtime=(TextView)findViewById(R.id.textComingtime);
		imageBussimessinDestination=(ImageView)findViewById(R.id.imageBussimessinDestination);
		textInDestinationPlace=(TextView)findViewById(R.id.textInDestinationPlace);
		imageBussimessoutDestination=(ImageView)findViewById(R.id.imageBussimessoutDestination);
		textInDestinationTime=(TextView)findViewById(R.id.textInDestinationTime);
		texOuttDestinationTime=(TextView)findViewById(R.id.texOuttDestinationTime);
		textOutDestinationPlace=(TextView)findViewById(R.id.textOutDestinationPlace);
		imgbussinessrecall=(ImageView)findViewById(R.id.imgbussinessrecall);
		
		butbussinessactivity=(Button)findViewById(R.id.butbussinessactivity);
	}
	
	private void setListener(){
		imageBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		imageBussimessRegirest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   new AlertDialog.Builder(BussinessActivity.this).setTitle("提示")//设置对话框标题  
				   
				     .setMessage("确认进行出差登记吗?")//设置显示的内容  
				  
				     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
				  
				          
				  
				         @Override  
				     
				         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
				  
				             // TODO Auto-generated method stub  
				        	 SimpleDateFormat formatter =new SimpleDateFormat ("yyyy/MM/dd");       
				        	 Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
				        	 String strTime  =  formatter.format(curDate);
				        	 System.out.println("-------------------"+strTime);
				        	 textRegirestTime.setText(strTime);
				        	 CBussinessEntity Entity=new CBussinessEntity();
				        	 JSONObject js=Entity.toRegisterJson(cBussinessEntity.getBussinessId(), strTime);
				        	 bussinessBll.SendBussiness(js, registerHandler,MyURL.REGISTERBUSSINESS);
				         }  
				  
				     }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//响应事件  
				  
				             // TODO Auto-generated method stub  
				  
				  
				         }  
				  
				     }).show();//在按键响应事件中显示此对话框  
				  
			}
		});
		
		
		imageBussimessoutComing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   new AlertDialog.Builder(BussinessActivity.this).setTitle("提示")//设置对话框标题  
				   
				     .setMessage("确认进行归来登记吗?")//设置显示的内容  
				  
				     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
				  
				             // TODO Auto-generated method stub  
				        	 SimpleDateFormat formatter =new SimpleDateFormat ("yyyy/MM/dd");       
				        	 Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
				        	 String strTime  =  formatter.format(curDate);
				        	 System.out.println("-------------------"+strTime);
				        	 textComingtime.setText(strTime);
				        	 CBussinessEntity Entity=new CBussinessEntity();
				        	 JSONObject js=Entity.toReturnJson(cBussinessEntity.getBussinessId(), strTime);
				        	 bussinessBll.SendBussiness(js, registerHandler,MyURL.RETURNBUSSINESS);
				        	 
				         }  
				  
				     }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//响应事件  
				  
				             // TODO Auto-generated method stub  
				  
				  
				         }  
				  
				     }).show();//在按键响应事件中显示此对话框  
				  
			}
		});
		
		
		
		imageBussimessinDestination.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(BussinessActivity.this,BussinessMapActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("Myoperation", MyOpcode.Operation.SEND_BUSSINESS_IN);
				intent.putExtras(bundle);
				intent.putExtra("CBussinessEntity", cBussinessEntity);
				startActivity(intent);
				finish();
			}
		});
		imageBussimessoutDestination.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(BussinessActivity.this,BussinessMapActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("Myoperation", MyOpcode.Operation.SEND_BUSSINESS_OUT);
				intent.putExtras(bundle);
				intent.putExtra("CBussinessEntity", cBussinessEntity);
				startActivity(intent);
				finish();
			}
		});
		
		imgbussinessrecall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub,
				CEmployeeEntity cEmployeeEntity=new CEmployeeEntity();
				JSONObject js=cEmployeeEntity.toJSon(MyOpcode.Operation.ENTER_BUSSINESSRECALL);
				bussinessBll.EnterBussinessRecalss(js, EnterBussinessRecallHandler);
			
			}
		});
		
		butbussinessactivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				JSONObject json=cBussinessEntity.tobussinessactivityJson(cBussinessEntity.getBussinessId());
				bussinessBll.GetBussinessActivity(json, EnterBussinessActivityHandler);
			}
		});
		
	}
	public Handler EnterBussinessActivityHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
			CMissionEntityList cMissionEntityList=(CMissionEntityList)map.get("CMissionEntityList");
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent=new Intent();
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			intent.setClass(BussinessActivity.this, BussinessActivityActivity.class);
			startActivity(intent);
		}

	};
	
	
	public Handler EnterBussinessRecallHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
			CBussinessEntityList cBussinessEntityList=(CBussinessEntityList)map.get("CBussinessEntityList");
			Intent intent=new Intent();
			intent.putExtra("CBussinessEntityList", cBussinessEntityList);
			intent.setClass(BussinessActivity.this, BussinessRecallActivity.class);
			startActivity(intent);
		}
		
		
	};
	
	
	
	private Handler registerHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
		
		
	};
	
}
