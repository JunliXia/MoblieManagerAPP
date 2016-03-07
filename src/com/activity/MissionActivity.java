package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.adapter.CompleteMissionAdapter;
import com.adapter.UnderwayMissionAdapter;
import com.adapter.WaitTakeMissionAdapter;
import com.bll.MissionBll;
import com.entity.CAttendanceEntity;
import com.entity.CMissionConclusionEntity;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;
import com.tool.ToastUtil;

public class MissionActivity extends Activity{
	private ViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private ImageView textView1, textView2, textView3;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int bmpW;// 动画图片宽度
	private View view1, view2, view3;// 各个页卡
	
	
	private ImageView imageviewBack;
	
	private ListView listWaittakeMission;
	private WaitTakeMissionAdapter waitTakeMissionAdapter;

	private ListView listUnderwayMission;
	private UnderwayMissionAdapter underwayMissionAdapter;
	
	private ListView listcompleteMission;
	private CompleteMissionAdapter completeMissionAdapter;
	
	
	private CMissionEntityList cWaitTakeList;
	private CMissionEntityList cUnderWayList;
	private CMissionEntityList cCompleteList;
	private MissionBll missionBll; 
	private int item;
	
	
	private String missioncontent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mission);
		cWaitTakeList=(CMissionEntityList)this.getIntent().getExtras().getSerializable("CMissionEntityList");
		missionBll=MissionBll.getMissionBll(this);
		
//		InitImageView();
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
		if(cWaitTakeList!=null&&cWaitTakeList.getSize()!=0){
			State=cWaitTakeList.getItem(0).getMissionState();
		}
		
		return State;
	}
	private void InitTextView() {
		textView1 = (ImageView) findViewById(R.id.imageWaittakeMission);
		textView1.setImageResource(R.drawable.imgtakemission);
		textView2 = (ImageView) findViewById(R.id.imageUnderwayMission);
		textView3 = (ImageView) findViewById(R.id.imageCompleteMission);
		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
	
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		
		if(getState()==MyConstant.MISSION_WAITTAKE){
			waitTakeMissionAdapter=new WaitTakeMissionAdapter(this,cWaitTakeList);
		}
		
//		underwayMissionAdapter=new UnderwayMissionAdapter(this);
//		completeMissionAdapter=new CompleteMissionAdapter(this);
	}
	
	
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		listWaittakeMission.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MissionActivity.this,WaitTakeMisstionDetailActivity.class);
				CMissionEntity cMissionEntity=cWaitTakeList.getItem(arg2);
				intent.putExtra("CMissionEntity", cMissionEntity);
				startActivity(intent);
				finish();
			}
		});
		
		listUnderwayMission.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CMissionEntity cMissionEntity=cUnderWayList.getItem(arg2);

				if(cMissionEntity.getMissionState()!=5){
					Intent intent=new Intent(MissionActivity.this,UnderwayMissionDetailActivity.class);
					intent.putExtra("CMissionEntity", cMissionEntity);
					startActivity(intent);
					finish();
				}else{
					ToastUtil.show(MissionActivity.this, "任务已过期，请等待处理");
				}
				
			}
		});
		
		listcompleteMission.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				
				
				CMissionEntity cMissionEntity=cCompleteList.getItem(arg2);
				missioncontent=cMissionEntity.getMissionContent();
				if(cMissionEntity.getMissionState()==4){
					ToastUtil.show(MissionActivity.this, "任务已撤销");
				}else if(cMissionEntity.getMissionState()==6){
					ToastUtil.show(MissionActivity.this, "任务已失败");
				}else{
					JSONObject js=cMissionEntity.getMissionConclusionJson(cMissionEntity.getMissionId());
					missionBll.GetMissionConclusion(js, GetMissionConclusionHandler);
				}
				
				
				
//				finish();
			}
		});
	}
	
	public Handler GetMissionConclusionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CMissionConclusionEntity cMissionConclusionEntity=(CMissionConclusionEntity)map.get("CMissionConclusionEntity");
			Intent intent=new Intent(MissionActivity.this,CompleteMissionDetailActivity.class);
			intent.putExtra("CMissionConclusionEntity", cMissionConclusionEntity);
			intent.putExtra("missioncontent", missioncontent);
			startActivity(intent);
		}
		
		
	};
	
	
	
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


		
		view1 = inflater.inflate(R.layout.waittakemission, null);
		listWaittakeMission=(ListView)view1.findViewById(R.id.listWaintakeMission);
		listWaittakeMission.setAdapter(waitTakeMissionAdapter);
		views.add(view1);
		
		
		
		
		view2 = inflater.inflate(R.layout.underwaymission, null);
		listUnderwayMission=(ListView)view2.findViewById(R.id.listunderwaymission);
		listUnderwayMission.setAdapter(underwayMissionAdapter);
		
		views.add(view2);
		
		
		view3 = inflater.inflate(R.layout.completemission, null);
		listcompleteMission=(ListView)view3.findViewById(R.id.listcompleteMission);
		listcompleteMission.setAdapter(completeMissionAdapter);
		views.add(view3);

		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	

//	private void InitImageView() {
//		imageView = (ImageView) findViewById(R.id.cursor);
//		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.xiahua)
//				.getWidth();// 获取图片宽度
//		DisplayMetrics dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		int screenW = dm.widthPixels;// 获取分辨率宽度
//		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
//		Matrix matrix = new Matrix();
//		matrix.postTranslate(offset, 0);
//		imageView.setImageMatrix(matrix);// 设置动画初始位置
//	}

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
				CMissionEntity cMissionEntity=new CMissionEntity();
				JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_WAITTAKE_MISSION);
				missionBll.EnterMission(js, WaitTakeMissionHandler,MyURL.GETWAITTAKEMISSION);
				
				textView1.setImageResource(R.drawable.imgtakemission);
				textView2.setImageResource(R.drawable.imgbeforewaitmission);
				textView3.setImageResource(R.drawable.imgbeforecompletemission);
				
			} else if (viewPager.getCurrentItem() == 1) {
				CMissionEntity cMissionEntity=new CMissionEntity();
				JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_UNDERWAY_MISSION);
				missionBll.EnterMission(js, UnderWayMissionHandler,MyURL.GETUNDERWAYMISSION);
				textView1.setImageResource(R.drawable.imgbeforetakemission);
				textView2.setImageResource(R.drawable.imgwaitmisson);
				textView3.setImageResource(R.drawable.imgbeforecompletemission);
			} else if (viewPager.getCurrentItem() == 2) {
				CMissionEntity cMissionEntity=new CMissionEntity();
				JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_COMPLETE_MISSION);
				missionBll.EnterMission(js, CompleteMissionHandler,MyURL.GETCOMPLETEMISSION);
				textView1.setImageResource(R.drawable.imgbeforetakemission);
				textView2.setImageResource(R.drawable.imgbeforewaitmission);
				textView3.setImageResource(R.drawable.imgcompletemission);
			}
		}

	}

	
	public Handler WaitTakeMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CMissionEntityList cMissionEntityList=(CMissionEntityList)map.get("CMissionEntityList");
			cWaitTakeList=cMissionEntityList;
			waitTakeMissionAdapter=new WaitTakeMissionAdapter(MissionActivity.this,cMissionEntityList);
			listWaittakeMission.setAdapter(waitTakeMissionAdapter);
		}
	};
	
	
	public Handler UnderWayMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CMissionEntityList cMissionEntityList=(CMissionEntityList)map.get("CMissionEntityList");
			cUnderWayList=cMissionEntityList;
			underwayMissionAdapter=new UnderwayMissionAdapter(MissionActivity.this,cMissionEntityList);
			listUnderwayMission.setAdapter(underwayMissionAdapter);
		}
		
		
	};
	
	public Handler CompleteMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CMissionEntityList cMissionEntityList=(CMissionEntityList)map.get("CMissionEntityList");
			cCompleteList=cMissionEntityList;
			System.out.println("--------cCompleteList--------"+cCompleteList.getSize());
			completeMissionAdapter=new CompleteMissionAdapter(MissionActivity.this,cMissionEntityList);
			listcompleteMission.setAdapter(completeMissionAdapter);
		}
		
		
	};
	
}
