package com.activity;

import com.adapter.BussinrssRecallAdapter;
import com.adapter.VisitConclusionAdatpter;
import com.entity.CBussinessEntity;
import com.entity.CBussinessEntityList;
import com.entity.CVisitConclusionEntity;
import com.example.mobliemanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BussinessRecallActivity extends Activity{

	private ImageView imageviewBack;
	private CBussinessEntityList cBussinessEntityList;
	
	private ListView listbussinessrecall;
	private BussinrssRecallAdapter bussinrssRecallAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bussinessreacll);
		cBussinessEntityList=(CBussinessEntityList)this.getIntent().getExtras().getSerializable("CBussinessEntityList");
		init();
		setListener();
		
	}
	
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		listbussinessrecall.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(BussinessRecallActivity.this,BussinessRecallDeatilActivity.class);
				CBussinessEntity cBussinessEntity=cBussinessEntityList.getItem(arg2);
				intent.putExtra("CBussinessEntity", cBussinessEntity);
				startActivity(intent);
				finish();
			}
		});
	}
	
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		listbussinessrecall=(ListView)findViewById(R.id.listbussinessrecall);
	
		bussinrssRecallAdapter=new BussinrssRecallAdapter(BussinessRecallActivity.this, cBussinessEntityList);
		listbussinessrecall.setAdapter(bussinrssRecallAdapter);
		
	}
}
