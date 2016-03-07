package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.adapter.VisitAdapter;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;

public class VisitActivity extends Activity {
	
	
	private ListView listVisit;
	private VisitAdapter visitAdapter;
	private CVisitEntityList cVisitEntityList;
	private ImageView imageviewBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.visit);
		cVisitEntityList=(CVisitEntityList)this.getIntent().getExtras().getSerializable("CVisitEntityList");
		init();
		setListener();
	}
	
	private void init(){
		listVisit=(ListView)findViewById(R.id.listVisit);
		visitAdapter=new VisitAdapter(VisitActivity.this, cVisitEntityList);
		listVisit.setAdapter(visitAdapter);
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
	}
	
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		listVisit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(VisitActivity.this, VisitDetailActivity.class);
				intent.putExtra("CVisitEntity", cVisitEntityList.getItem(arg2));
				startActivity(intent);
				
			}
		});
	}
	

	
	
	
	
}
