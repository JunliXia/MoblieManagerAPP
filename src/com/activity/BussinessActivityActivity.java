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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.adapter.BussinessActivityMissionAdapter;
import com.adapter.BussinessActivityVisitAdapter;
import com.adapter.NostartVisitAdapter;
import com.bll.MissionBll;
import com.bll.VisitBll;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;

public class BussinessActivityActivity extends Activity{
	private ImageView imageviewBack;
	private CMissionEntityList cMissionEntityList;
	private CVisitEntityList cVisitEntityList;
	
	private ListView listmission;
	private BussinessActivityMissionAdapter bussinessActivityMissionAdapter;
	private ListView listvisit;
	private BussinessActivityVisitAdapter bussinessActivityVisitAdapter;
	private VisitBll visitBll;
	private MissionBll missionBll;
	
//	private Button butbussinessactivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bussinessactivity);
		visitBll=VisitBll.getVisitBll(this);
		missionBll=MissionBll.getMissionBll(this);
		cMissionEntityList=(CMissionEntityList)this.getIntent().getExtras().get("CMissionEntityList");
		cVisitEntityList=(CVisitEntityList)this.getIntent().getExtras().getSerializable("CVisitEntityList");
		init();
		setListener();
	}
	
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
//		butbussinessactivity=(Button)findViewById(R.id.butbussinessactivity);
		listmission=(ListView)findViewById(R.id.listmission);
		bussinessActivityMissionAdapter=new BussinessActivityMissionAdapter(BussinessActivityActivity.this, cMissionEntityList);
		listmission.setAdapter(bussinessActivityMissionAdapter);
		
		listvisit=(ListView)findViewById(R.id.listvisitplan);
		bussinessActivityVisitAdapter=new BussinessActivityVisitAdapter(BussinessActivityActivity.this, cVisitEntityList);
		listvisit.setAdapter(bussinessActivityVisitAdapter);
		
	}	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		listmission.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int state=cMissionEntityList.getItem(arg2).getMissionState();
				if(state==0){
					CMissionEntity cMissionEntity=new CMissionEntity();
					JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_WAITTAKE_MISSION);
					missionBll.EnterMission(js, GetWaittakeMissionHandler,MyURL.GETWAITTAKEMISSION);
				}else if(state==1||state==5){
					CMissionEntity cMissionEntity=new CMissionEntity();
					JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_UNDERWAY_MISSION);
					missionBll.EnterMission(js, GetUnderWayMissionHandler,MyURL.GETUNDERWAYMISSION);
				}else if(state==2||state==3||state==4||state==6){
					CMissionEntity cMissionEntity=new CMissionEntity();
					JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_COMPLETE_MISSION);
					missionBll.EnterMission(js, GetCompleteMissionHandler,MyURL.GETCOMPLETEMISSION);
				}
			
			}
		});
		
		listvisit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int state=cVisitEntityList.getItem(arg2).getVisitPlanState();
				if(state==0){
					CVisitEntity cVisitEntity=new CVisitEntity();
					JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETNOSTARTVISITPLAN);
					visitBll.EnterVisit(js, GetNoStartVisitHandler, MyURL.GETNOSTARTVISITPLAN);
				}else if(state==1||state==5){
					CVisitEntity cVisitEntity=new CVisitEntity();
					JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETUNDERWAYVISITPLAN);
					visitBll.EnterVisit(js, GetRunVisitHandler, MyURL.GETUNDERWAYVISITPLAN);
				}else if(state==2||state==3||state==4||state==6){
					CVisitEntity cVisitEntity=new CVisitEntity();
					JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETCOMPLETEVISITPLAN);
					visitBll.EnterCompleteVisit(js, GetCompleteVisitHandler, MyURL.GETCOMPLETEVISITPLAN);
				}
				
			}
		});
	
	}
	
	private Handler GetCompleteMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
			CMissionEntityList cMissionEntityList = (CMissionEntityList) map
					.get("CMissionEntityList");
			Intent intent=new Intent();
			intent.setClass(BussinessActivityActivity.this, MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_NOCHECK);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}
		
	};
	
	private Handler GetWaittakeMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
			CMissionEntityList cMissionEntityList = (CMissionEntityList) map
					.get("CMissionEntityList");
			Intent intent=new Intent();
			intent.setClass(BussinessActivityActivity.this, MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_WAITTAKE);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};
	
	public Handler GetUnderWayMissionHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
			CMissionEntityList cMissionEntityList = (CMissionEntityList) map
					.get("CMissionEntityList");
			Intent intent = new Intent();
			intent.setClass(BussinessActivityActivity.this,
					MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_UNDERWAY);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}

	};
	
	public Handler GetNoStartVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent = new Intent();
			intent.setClass(BussinessActivityActivity.this,
					VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_NOTSTART);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
	};
	public Handler GetCompleteVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent = new Intent();
			intent.setClass(BussinessActivityActivity.this,
					VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_WAITCHECK);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};
	public Handler GetRunVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent = new Intent();
			intent.setClass(BussinessActivityActivity.this,
					VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_UNDERWAY);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};

}
