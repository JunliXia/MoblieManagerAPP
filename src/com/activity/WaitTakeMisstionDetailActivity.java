package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bll.MissionBll;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;

public class WaitTakeMisstionDetailActivity extends Activity{

	private ImageView imageviewBack;
	
	private CMissionEntity cMissionEntity;
	
	private TextView textTimeData,textContent;
	private Button butmission;
	private MissionBll missionBll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.waittakemission_detail);
		cMissionEntity=(CMissionEntity)this.getIntent().getExtras().getSerializable("CMissionEntity");
		missionBll=MissionBll.getMissionBll(this);
		init();
		setListener();
		setText();
	}

	private void setText(){
		textTimeData.setText(cMissionEntity.getMissionDeadLine());
		textContent.setText(cMissionEntity.getMissionContent());
	}
	
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		textTimeData=(TextView)findViewById(R.id.textTimeData);
		textContent=(TextView)findViewById(R.id.textContent);
		butmission=(Button)findViewById(R.id.butmission);
	}
	
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CMissionEntity entity=new CMissionEntity();
				JSONObject js=entity.toJson(MyOpcode.Operation.GET_WAITTAKE_MISSION);
				missionBll.EnterMission(js, EnterMissionHandler,MyURL.GETWAITTAKEMISSION);
			
			}
		});
		
		butmission.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CMissionEntity Entity=new CMissionEntity();
				JSONObject js=Entity.getMissionJson(cMissionEntity.getMissionId());
				missionBll.GetWaittakeMission(js, GetWaittakeMissionHandler);
			}
		});
	}
	
	
	private Handler GetWaittakeMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			List<CMissionEntity> cMissionEntities=new ArrayList<CMissionEntity>();
			CMissionEntityList cMissionEntityList=new CMissionEntityList(cMissionEntities);
			Intent intent=new Intent();
			intent.setClass(WaitTakeMisstionDetailActivity.this, MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_UNDERWAY);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};

	//进入任务(待接受)
	public Handler EnterMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CMissionEntityList cMissionEntityList=(CMissionEntityList)map.get("CMissionEntityList");
			Intent intent=new Intent();
			intent.setClass(WaitTakeMisstionDetailActivity.this, MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_WAITTAKE);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};
}
