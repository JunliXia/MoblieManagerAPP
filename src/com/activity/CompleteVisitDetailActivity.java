package com.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.adapter.VisitConclusionAdatpter;
import com.entity.CMissionEntity;
import com.entity.CVisitConclusionEntity;
import com.entity.CVisitConclusionEntityList;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class CompleteVisitDetailActivity extends Activity{
	private ImageView imageviewBack;
	private ListView listvisitconclusion;
//	private CVisitConclusionEntityList conclusionEntityList;
	private CVisitEntity cVisitEntity;
	private VisitConclusionAdatpter visitConclusionAdatpter;
	private CVisitConclusionEntityList conclusionEntityList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.completevisit_detail);
		cVisitEntity=(CVisitEntity)this.getIntent().getExtras().getSerializable("CVisitEntity");
		conclusionEntityList=new CVisitConclusionEntityList(cVisitEntity.getConclusionEntities());
		init();
		setListener();
	}
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				GetCompleteVisitHandler.sendEmptyMessage(0);
			}
		});
		
		listvisitconclusion.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CompleteVisitDetailActivity.this,VisitDetailActivity.class);
//				CMissionEntity cMissionEntity=cUnderWayList.getItem(arg2);
				CVisitConclusionEntity conclusionEntity=conclusionEntityList.getItem(arg2);
				intent.putExtra("CVisitConclusionEntity", conclusionEntity);
				intent.putExtra("ClientName",cVisitEntity.getClientName());
				intent.putExtra("ClientCompany",cVisitEntity.getClientCompany());
				startActivity(intent);
//				finish();
			}
		});
	}
	
	
	public Handler GetCompleteVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			List<CVisitEntity> cVisitEntities=new ArrayList<CVisitEntity>();
			CVisitEntityList cVisitEntityList=new CVisitEntityList(cVisitEntities);
			Intent intent = new Intent();
			intent.setClass(CompleteVisitDetailActivity.this,
					VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_WAITCHECK);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		listvisitconclusion=(ListView)findViewById(R.id.listvisitconclusion);
	
		visitConclusionAdatpter=new VisitConclusionAdatpter(CompleteVisitDetailActivity.this, conclusionEntityList);
		listvisitconclusion.setAdapter(visitConclusionAdatpter);
		
	}
	
}
