package com.activity;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adapter.AttendanceAdapter;
import com.entity.CAttendanceEntity;
import com.example.mobliemanager.R;

public class AttendanceActivity extends Activity{
  
	private ListView listAttance;
	private AttendanceAdapter attendanceAdapter;
	private ImageView imageviewBack;
	private CAttendanceEntity cAttendanceEntity;
	
	private TextView vada;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.attendance);
		cAttendanceEntity=(CAttendanceEntity)this.getIntent().getExtras().getSerializable("CAttendanceEntity");
		System.out.println(cAttendanceEntity.toString());
		init();
		setListener();
		setText();
	}
	
	
	private void setText(){
		
	}
	
	
	private void init(){
		listAttance=(ListView)findViewById(R.id.listAttendance);
		attendanceAdapter=new AttendanceAdapter(this,cAttendanceEntity);
		listAttance.setAdapter(attendanceAdapter);
		
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		
		vada=(TextView)findViewById(R.id.kaoqingshijian);
		vada.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
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
