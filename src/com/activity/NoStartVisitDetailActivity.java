package com.activity;

import java.util.HashMap;

import org.json.JSONObject;

import com.adapter.NostartVisitAdapter;
import com.bll.VisitBll;
import com.entity.CClientEntity;
import com.entity.CClientEntityList;
import com.entity.CEmployeeEntity;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;
import com.tool.ToastUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NoStartVisitDetailActivity extends Activity {
//	private ImageView imgrewrite;
	
	private ImageView imageclientaddress;
	private ImageView imageviewBack;
	private Button butclientviste;
	
	private TextView textclientname,textclientcompany,
					 textclientphone,textclientarea,
					 textclientaddress;
	
	
	private CVisitEntity cVisitEntity;

	int clienttype=0;
	
	private VisitBll visitBll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientinfomation);
		cVisitEntity=(CVisitEntity)this.getIntent().getExtras().getSerializable("CVisitEntity");
		visitBll=VisitBll.getVisitBll(this);
		init();
		setListener();
		getKey();
		setText();
	}
	private void getKey(){
		  Bundle bundle=getIntent().getExtras();  
		  clienttype=bundle.getInt("clienttype");
//		  if(clienttype==MyConstant.CLIENT_COMPANY){
//			  imgrewrite.setVisibility(View.GONE);
//		  }else if (clienttype==MyConstant.CLIENT_PERSON) {
//			  imgrewrite.setVisibility(View.VISIBLE);
//		}
	}
	private void setText(){
		textclientname.setText(cVisitEntity.getClientName());
		textclientcompany.setText(cVisitEntity.getClientCompany());
		textclientphone.setText(cVisitEntity.getClientPhone());
		textclientarea.setText(cVisitEntity.getClientArea());
		textclientaddress.setText(cVisitEntity.getClientAddress());
		butclientviste.setText("°Ý·ÃÉÐÎ´¿ªÊ¼");
		
	}
	private void init(){
		imageclientaddress=(ImageView)findViewById(R.id.imageclientaddress);
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
//		imgrewrite=(ImageView)findViewById(R.id.imgrewrite);
		butclientviste=(Button)findViewById(R.id.butclientviste);
	
		textclientname=(TextView)findViewById(R.id.textclientname);
		textclientcompany=(TextView)findViewById(R.id.textclientcompany);
		textclientphone=(TextView)findViewById(R.id.textclientphone);
		textclientarea=(TextView)findViewById(R.id.textclientarea);
		textclientaddress=(TextView)findViewById(R.id.textclientaddress);
	}
	private void setListener(){
		imageclientaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(NoStartVisitDetailActivity.this,ClientAddressActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("textclientarea", textclientarea.getText().toString());
				bundle.putString("textclientaddress", textclientaddress.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CVisitEntity cVisitEntity=new CVisitEntity();
				JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETNOSTARTVISITPLAN);
				visitBll.EnterVisit(js, GetNoStartVisitHandler, MyURL.GETNOSTARTVISITPLAN);
			}
		});
		
		
		butclientviste.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ToastUtil.show(NoStartVisitDetailActivity.this, "°Ý·ÃÉÐÎ´¿ªÊ¼");
			}
		});
		
//		imgrewrite.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intent =new Intent();
//				intent.setClass(ClientInfomationActivity.this, ClientUpdateActivity.class);
//				intent.putExtra("CClientEntity", cClientEntity);
//				startActivity(intent);
//				finish();
//			}
//		});
	}
	
	public Handler GetNoStartVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent=new Intent();
			intent.setClass(NoStartVisitDetailActivity.this, VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_NOTSTART);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
	};
	


}
