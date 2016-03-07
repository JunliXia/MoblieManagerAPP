package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.adapter.ClientAdapter;
import com.adapter.ClientSubmitAdapter;
import com.bll.ClientBll;
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
import com.tool.MyViewPager;

public class ClientActivity extends Activity implements View.OnClickListener{
	private MyViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片  
	private Button button1, button2;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 1;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private View view1, view2;// 各个页卡
	
	private ImageView imageviewBack;
	private ImageView imageAddClient;
	private ListView listclientcompany;
	private ClientAdapter clientAdapter;
	
	private ListView listclientperson;
	private ClientSubmitAdapter clientSubmitAdapter;
	
//	private CClientEntityList cClientEntityList;
	private CClientEntityList companyEntityList;
	private CClientEntityList personEntityList;
	
	private int clientType;
//	private Button butVisit;
	private VisitBll visitBll;
	private ClientBll clientBll;
	private int item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.client);
		companyEntityList=(CClientEntityList)this.getIntent().getExtras().getSerializable("CClientEntityList");
		visitBll=VisitBll.getVisitBll(this);
		clientBll=ClientBll.getClienetBll(this);

		InitTextView();
		InitViewPager();

		init();
		setListener();
		getKey();
	}
	
	private void getKey(){
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		item=bundle.getInt("item");
		viewPager.setCurrentItem(item);
	}
//	private int getState(){
//		int State=0;
//		if(companyEntityList!=null&&companyEntityList.getSize()!=0){
//			State=companyEntityList.getItem(0).getClientState();
//		}
//		
//		return State;
//	}
	
//	private void breakclient(){
//		List<CClientEntity> companys=new ArrayList<CClientEntity>();
//		List<CClientEntity> persons=new ArrayList<CClientEntity>();
//		for(int i=0;i<cClientEntityList.getSize();i++){
//
//			if(cClientEntityList.getItem(i).getClientType()==MyConstant.CLIENT_COMPANY){
//			
//				companys.add(cClientEntityList.getItem(i));
//		
//			}else if(cClientEntityList.getItem(i).getClientType()==MyConstant.CLIENT_PERSON){
//			
//				persons.add(cClientEntityList.getItem(i));
//			
//			}
//		
//		}
//		
//		companyEntityList=new CClientEntityList(companys);
//		personEntityList=new CClientEntityList(persons);
//		
//	}
	
	
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		imageAddClient=(ImageView)findViewById(R.id.imageAddClient);
//		butVisit=(Button)findViewById(R.id.butVisit);
	}
	
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		listclientcompany.setOnItemClickListener(new OnItemClickListener(
				) {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(ClientActivity.this,ClientInfomationActivity.class);
						Bundle bundle=new Bundle();
						bundle.putInt("clienttype", MyConstant.CLIENT_COMPANY);
						intent.putExtra("CClientEntity", companyEntityList.getItem(arg2));
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
						
					}
		});
		listclientperson.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ClientActivity.this,ClientInfomationActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("clienttype", MyConstant.CLIENT_PERSON);
				intent.putExtra("CClientEntity", personEntityList.getItem(arg2));
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		imageAddClient.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ClientActivity.this,ClientAddActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("address", "");
				bundle.putString("name", "");
				bundle.putString("company","");
				bundle.putString("phone", "");
				bundle.putString("zone","");
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		
//		
//		butVisit.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				CEmployeeEntity cEmployeeEntity=new CEmployeeEntity();
//				JSONObject js=cEmployeeEntity.toJSon(MyOpcode.Operation.ENTER_VISIT);
//				visitBll.EnterVisit(js, EnterVisitHandler,MyURL.ENTERVISIT);
//			}
//		});
	}
	
	
	private Handler EnterVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent=new Intent();
			intent.setClass(ClientActivity.this, VisitActivity.class);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
		}
		
	};
	
	
	
	private void InitTextView() {
		button1 = (Button) findViewById(R.id.butcompany);
		button1.setBackgroundColor(Color.rgb(88,213,243));
		button2 = (Button) findViewById(R.id.butperson);
		button2.setBackgroundColor(Color.rgb(255, 255, 255));
		button1.setOnClickListener(new MyOnClickListener(0));
		button2.setOnClickListener(new MyOnClickListener(1));
		
		clientAdapter=new ClientAdapter(ClientActivity.this, 0, companyEntityList);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}

	}
	
	private void InitViewPager() {
		viewPager = (MyViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.client_company, null);
		listclientcompany=(ListView)view1.findViewById(R.id.listclientcompany);
//		clientAdapter=new ClientAdapter(this,MyConstant.CLIENT_COMPANY,companyEntityList);
		listclientcompany.setAdapter(clientAdapter);
	
		
		views.add(view1);
		
		
		
		
		view2 = inflater.inflate(R.layout.client_person, null);
		listclientperson=(ListView)view2.findViewById(R.id.listclientperson);
//		clientAdapter=new ClientAdapter(this, MyConstant.CLIENT_PERSON,personEntityList);
		listclientperson.setAdapter(clientSubmitAdapter);
		views.add(view2);
	

		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
//			Animation animation = new TranslateAnimation(one * currIndex, one
//					* arg0, 0, 0);// 显然这个比较简洁，只有一行代码。
//			currIndex = arg0;
//			animation.setFillAfter(true);// True:图片停在动画结束位置
//			animation.setDuration(300);
//			imageView.startAnimation(animation);
			// Toast.makeText(MainActivity.this, "您选择了"+
			// viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
			if (viewPager.getCurrentItem() == 0) {
				button1.setBackgroundColor(Color.rgb(88,213,243));
				button2.setBackgroundColor(Color.rgb(255, 255, 255));
				imageAddClient.setVisibility(View.GONE);
				CEmployeeEntity emtity=new CEmployeeEntity();
				JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENT_MANAGER);
				clientBll.EnterClientManager(enterClientJS, EnterClientManagerHandler);
			}else if (viewPager.getCurrentItem()==1) {
				button2.setBackgroundColor(Color.rgb(88,213,243));
				button1.setBackgroundColor(Color.rgb(255, 255, 255));
				imageAddClient.setVisibility(View.VISIBLE);
				CEmployeeEntity emtity=new CEmployeeEntity();
				JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENTSUBMIT);
				clientBll.EnterClientSubmit(enterClientJS, EnterClientSubmitHandler);
			}
		}

	}

	
	//进入客户管理
		public Handler EnterClientManagerHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
				CClientEntityList cClientEntityList=(CClientEntityList)map.get("CClientEntityList");
				companyEntityList=cClientEntityList;
				clientAdapter=new ClientAdapter(ClientActivity.this, 0, cClientEntityList);
				listclientcompany.setAdapter(clientAdapter);
			
			}
		};
		
		public Handler EnterClientSubmitHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
				CClientEntityList cClientEntityList=(CClientEntityList)map.get("CClientEntityList");
				personEntityList=cClientEntityList;
				clientSubmitAdapter=new ClientSubmitAdapter(ClientActivity.this, cClientEntityList);
				listclientperson.setAdapter(clientSubmitAdapter);
			
			}
		};
	
}
