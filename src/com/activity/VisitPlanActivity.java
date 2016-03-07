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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.adapter.CompleteVisitAdapter;
import com.adapter.NostartVisitAdapter;
import com.adapter.RunVisitAdapter;
import com.bll.ClientBll;
import com.bll.VisitBll;
import com.entity.CClientEntityList;
import com.entity.CEmployeeEntity;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;
import com.tool.ToastUtil;


public class VisitPlanActivity extends Activity{

	private ViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private ImageView textView1, textView2, textView3;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int bmpW;// 动画图片宽度
	private View view1, view2, view3;// 各个页卡
	
	private ImageView imgviewBack;
	
	private ListView listnostartvist;
	private NostartVisitAdapter nostartVisitAdapter;
	
	private ListView listrunvisit;
	private RunVisitAdapter runVisitAdapter;
	
	private ListView listcompletevisit;
	private CompleteVisitAdapter completeVisitAdapter;
	
	private CVisitEntityList cNoStartList;
	private CVisitEntityList cRunList;
	private CVisitEntityList cCompleteList;
	private VisitBll visitBll;
	private int item;
	
	private ImageView imgclient;
	
	private ClientBll clientBll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.visitplan);
		cNoStartList=(CVisitEntityList)this.getIntent().getExtras().getSerializable("CVisitEntityList");
		visitBll=VisitBll.getVisitBll(this);
		clientBll=ClientBll.getClienetBll(this);
		InitTextView();
		InitViewPager();
		getkey();
		
		setListener();
	}
	
	private void getkey(){
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		item=bundle.getInt("item");
		viewPager.setCurrentItem(item);
	}
	private int getState(){
		int State=0;
		if(cNoStartList!=null&&cNoStartList.getSize()!=0){
			State=cNoStartList.getItem(0).getVisitPlanState();
		}
		
		return State;
	}
	
	public void InitTextView(){
		textView1=(ImageView)findViewById(R.id.imgnostartvisit);
		textView1.setImageResource(R.drawable.imgnostartvisittwo);
		textView2=(ImageView)findViewById(R.id.imgrunvisit);
		textView3=(ImageView)findViewById(R.id.imgcomvisit);
		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
		imgviewBack=(ImageView)findViewById(R.id.imageviewBack);
		imgclient=(ImageView)findViewById(R.id.imgclient);
		if(getState()==MyConstant.VISITPLAN_NOTSTART){
			nostartVisitAdapter=new NostartVisitAdapter(this,cNoStartList);
		}
	}
	private void setListener(){
		imgviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		listnostartvist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(VisitPlanActivity.this,NoStartVisitDetailActivity.class);
			
				CVisitEntity cVisitEntity=cNoStartList.getItem(arg2);
				Bundle bundle=new Bundle();
				bundle.putInt("clienttype", MyConstant.CLIENT_COMPANY);
				intent.putExtra("CVisitEntity", cVisitEntity);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
		
		listrunvisit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CVisitEntity cVisitEntity=cRunList.getItem(arg2);
				if(cVisitEntity.getVisitPlanState()==1){
					Intent intent=new Intent(VisitPlanActivity.this,ClientVisitActivity.class);
					intent.putExtra("CVisitEntity", cVisitEntity);
					startActivity(intent);
					finish();
				}else if(cVisitEntity.getVisitPlanState()==5){
					ToastUtil.show(VisitPlanActivity.this, "拜访已过期，请等待处理");
				}
				
			}
		});
		
		listcompletevisit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				CVisitEntity cVisitEntity=cCompleteList.getItem(arg2);
//				CVisitConclusionEntityList conclusionEntityList=new CVisitConclusionEntityList(cVisitEntity.getConclusionEntities());
				
				Intent intent=new Intent(VisitPlanActivity.this,CompleteVisitDetailActivity.class);
				intent.putExtra("CVisitEntity", cVisitEntity);
				startActivity(intent);
				finish();
			}
		});
		
		
		imgclient.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CEmployeeEntity emtity=new CEmployeeEntity();
				JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENT_MANAGER);
				clientBll.EnterClientManager(enterClientJS, EnterClientManagerHandler);
			}
		});
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
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();


		
		view1 = inflater.inflate(R.layout.nostartvisit, null);
		listnostartvist=(ListView)view1.findViewById(R.id.listnostartvist);
		listnostartvist.setAdapter(nostartVisitAdapter);
		views.add(view1);
		
		
		
		
		view2 = inflater.inflate(R.layout.runvisit, null);
		listrunvisit=(ListView)view2.findViewById(R.id.listrunvisit);
		listrunvisit.setAdapter(runVisitAdapter);
		
		views.add(view2);
		
		
		view3 = inflater.inflate(R.layout.completevisit, null);
		listcompletevisit=(ListView)view3.findViewById(R.id.listcompletevisit);
		listcompletevisit.setAdapter(completeVisitAdapter);
		views.add(view3);

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
			if (viewPager.getCurrentItem() == 0) {
				CVisitEntity cVisitEntity=new CVisitEntity();
				JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETNOSTARTVISITPLAN);
				visitBll.EnterVisit(js, GetNoStartVisitHandler, MyURL.GETNOSTARTVISITPLAN);
				textView1.setImageResource(R.drawable.imgnostartvisittwo);
				textView2.setImageResource(R.drawable.imgrunvisitone);
				textView3.setImageResource(R.drawable.imgcomvisitone);
				
			} else if (viewPager.getCurrentItem() == 1) {
				CVisitEntity cVisitEntity=new CVisitEntity();
				JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETUNDERWAYVISITPLAN);
				visitBll.EnterVisit(js, GetRunVisitHandler, MyURL.GETUNDERWAYVISITPLAN);
				textView1.setImageResource(R.drawable.imgnostartvisitone);
				textView2.setImageResource(R.drawable.imgrunvisittwo);
				textView3.setImageResource(R.drawable.imgcomvisitone);
			} else if (viewPager.getCurrentItem() == 2) {
				CVisitEntity cVisitEntity=new CVisitEntity();
				JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETCOMPLETEVISITPLAN);
				visitBll.EnterCompleteVisit(js, GetCompleteVisitHandler, MyURL.GETCOMPLETEVISITPLAN);
				textView1.setImageResource(R.drawable.imgnostartvisitone);
				textView2.setImageResource(R.drawable.imgrunvisitone);
				textView3.setImageResource(R.drawable.imgcomvisittwo);
			}
		}

	}
	
	public Handler GetNoStartVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			cNoStartList=cVisitEntityList;
			nostartVisitAdapter=new NostartVisitAdapter(VisitPlanActivity.this, cVisitEntityList);
			listnostartvist.setAdapter(nostartVisitAdapter);
		}
	};
	
	
	public Handler GetRunVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			cRunList=cVisitEntityList;
			runVisitAdapter=new RunVisitAdapter(VisitPlanActivity.this, cVisitEntityList);
			listrunvisit.setAdapter(runVisitAdapter);
		}
		
		
	};
	
	public Handler GetCompleteVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			cCompleteList=cVisitEntityList;
			completeVisitAdapter=new CompleteVisitAdapter(VisitPlanActivity.this, cVisitEntityList);
			listcompletevisit.setAdapter(completeVisitAdapter);
		}
		
		
	};
	
	//进入客户管理
	public Handler EnterClientManagerHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CClientEntityList cClientEntityList=(CClientEntityList)map.get("CClientEntityList");
			Intent intent=new Intent();
			intent.setClass(VisitPlanActivity.this, ClientActivity.class);
			intent.putExtra("CClientEntityList", cClientEntityList);
			intent.putExtra("item",0);//我的客户
			startActivity(intent);
		}
	};
}
