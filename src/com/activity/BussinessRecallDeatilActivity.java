package com.activity;

import java.util.HashMap;

import org.json.JSONObject;

import com.bll.BussinessBll;
import com.entity.CBussinessEntity;
import com.entity.CBussinessEntityList;
import com.entity.CEmployeeEntity;
import com.entity.CMissionEntityList;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyOpcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BussinessRecallDeatilActivity extends Activity{
	private TextView textBussinessPlace;
	private TextView textBussinessContent;
	private ImageView imageBack;
	
	private TextView textRegirestTime;
	
	private TextView textInDestinationPlace;
	private TextView textInDestinationTime;
	
	private TextView textComingtime;
	
	private TextView texOuttDestinationTime;
	private TextView textOutDestinationPlace;
	
	private CBussinessEntity cBussinessEntity;
	private Button butbussinessactivity;
	private BussinessBll bussinessBll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bussinessrecalldetail);
		cBussinessEntity=(CBussinessEntity)this.getIntent().getExtras().getSerializable("CBussinessEntity");
		bussinessBll=BussinessBll.getBussinessBll(this);
		init();
		setText();
		setListener();
	
	}
	private void init(){
		textBussinessPlace=(TextView)findViewById(R.id.textBussinessPlace);
		textBussinessPlace.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
		textBussinessContent=(TextView)findViewById(R.id.textBussinessContent);
		imageBack=(ImageView)findViewById(R.id.imageviewBack);
		textRegirestTime=(TextView)findViewById(R.id.textRegirestTime);
		textComingtime=(TextView)findViewById(R.id.textComingtime);
		textInDestinationPlace=(TextView)findViewById(R.id.textInDestinationPlace);
		textInDestinationTime=(TextView)findViewById(R.id.textInDestinationTime);
		texOuttDestinationTime=(TextView)findViewById(R.id.texOuttDestinationTime);
		textOutDestinationPlace=(TextView)findViewById(R.id.textOutDestinationPlace);
		butbussinessactivity=(Button)findViewById(R.id.butbussinessactivity);
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
	
	
	private void setListener(){
		imageBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
			intent.setClass(BussinessRecallDeatilActivity.this, BussinessActivityActivity.class);
			startActivity(intent);
//			finish();
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
			intent.setClass(BussinessRecallDeatilActivity.this, BussinessRecallActivity.class);
			startActivity(intent);
			finish();
		}
		
		
	};
}
