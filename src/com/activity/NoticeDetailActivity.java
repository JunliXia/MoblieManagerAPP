package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.CNoticeEntity;
import com.example.mobliemanager.R;

public class NoticeDetailActivity extends Activity {

	private ImageView imageviewBack;
	
	private TextView textnoticetitle;
	private TextView textnoticetime;
	private TextView textnoticecontent;

	
	private CNoticeEntity cNoticeEntity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.noticedetail);
		cNoticeEntity=(CNoticeEntity)this.getIntent().getExtras().getSerializable("CNoticeEntity");
		init();
		setText();
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
	}
	
	
	private void setText(){
		textnoticecontent.setText(cNoticeEntity.getNoticeContent());
		textnoticetime.setText(cNoticeEntity.getNoticeTime());
		textnoticetitle.setText(cNoticeEntity.getNoticeTitle());
	}
	
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		textnoticetitle=(TextView)findViewById(R.id.textnoticetitle);
		textnoticetime=(TextView)findViewById(R.id.textnoticetime);
		textnoticecontent=(TextView)findViewById(R.id.textnoticecontent);
	}
}
