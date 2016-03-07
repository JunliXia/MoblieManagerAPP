package com.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bll.SuggestBll;
import com.entity.CSuggestEntity;
import com.example.mobliemanager.R;

public class SuggestActivity extends Activity {

	private ImageView imageviewBack;
	private EditText textContent;
	private Button butsuggest;

	private SuggestBll suggestBll;
	private  ProgressDialog pdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.suggest);
		suggestBll = SuggestBll.getSuggestBll(this);
		init();
		setListener();
	}

	private void init() {
		imageviewBack = (ImageView) findViewById(R.id.imageviewBack);
		textContent = (EditText) findViewById(R.id.textContent);
		butsuggest = (Button) findViewById(R.id.butsuggest);

	}

	private void setListener() {
		imageviewBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		butsuggest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pdialog=ProgressDialog.show(SuggestActivity.this, "正在加载...", "正在提交中");  
				CSuggestEntity cSuggestEntity = new CSuggestEntity();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
				String strTime = formatter.format(curDate);
				JSONObject js = cSuggestEntity.toJSon(textContent.getText().toString(), strTime);
				
				suggestBll.SendSuggest(js, SendSuggestHandler);

			}
		});
	}
	
	
	private Handler SendSuggestHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			pdialog.dismiss();
			finish();
			
		}
		
		
	};
}
