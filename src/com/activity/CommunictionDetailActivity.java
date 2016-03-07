package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.CEmployeeEntity;
import com.example.mobliemanager.R;

public class CommunictionDetailActivity extends Activity{

	private ImageView imageviewBack;
	
	private TextView textName,textPhone,textSex,textPartment,textJob;
	
	private CEmployeeEntity cEmployeeEntity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.communictiondetail);
		cEmployeeEntity=(CEmployeeEntity)this.getIntent().getExtras().getSerializable("CEmployeeEntity");
		init();
		setText();
		setListener();
	}

	
	private void setText(){
		textName.setText(cEmployeeEntity.getEmployeeName());
		textPhone.setText(cEmployeeEntity.getEmployeePhone());
		textSex.setText(cEmployeeEntity.getEmployeeSex());
		textPartment.setText(cEmployeeEntity.getEmployeeDepartment());
		textJob.setText(cEmployeeEntity.getEmployeeJob());
	}
	
	private void init(){
		
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		textName=(TextView)findViewById(R.id.textName);
		textPhone=(TextView)findViewById(R.id.textPhone);
		textSex=(TextView)findViewById(R.id.textSex);
		textPartment=(TextView)findViewById(R.id.textPartment);
		textJob=(TextView)findViewById(R.id.textJob);
	}
	
	
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
