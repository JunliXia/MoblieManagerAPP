package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.adapter.ExpandAdapter;
import com.adapter.NostartVisitAdapter;
import com.adapter.NoticeAdapter;
import com.baidu.android.bbalbs.common.a.c;
import com.bll.AttendanceBll;
import com.bll.BussinessBll;
import com.bll.ClientBll;
import com.bll.MissionBll;
import com.bll.NoticeBll;
import com.bll.VisitBll;
import com.entity.CAttendanceEntity;
import com.entity.CBussinessEntity;
import com.entity.CClientEntityList;
import com.entity.CEmployeeEntity;
import com.entity.CEmployeeEntityList;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.entity.CNoticeEntityList;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;
import com.tool.MyViewPager;
import com.tool.ToastUtil;

public class MainActivity extends MenuActivity implements View.OnClickListener,
		OnChildClickListener {
	private MyViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private ImageView textView1, textView2, textView3;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 1;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private View view1, view2, view3;// 各个页卡

	// Work工作
	private ImageButton imgbutAttendance, imgbutBussiness, imgbutMission,
			imgbutClient;
	private AttendanceBll attendanceBll;
	private MissionBll missionBll;
	private BussinessBll bussinessBll;
	private ClientBll clientBll;
	private VisitBll visitBll;
	
	private  ProgressDialog pdialog;
	// 通讯录

	private ExpandableListView mListView = null;
	private ExpandAdapter mAdapter = null;
	
	private List<CEmployeeEntityList> employeeListss=new ArrayList<CEmployeeEntityList>();
	
	private CEmployeeEntityList cEmployeeEntityList;
	//通知
	
	private ListView listnotice;
	private NoticeAdapter noticeAdapter;
	private NoticeBll noticeBll;
	private CNoticeEntityList cNoticeEntityList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		/************* 侧滑动画效果调用方法 **********************/
		initAnimation();
		initSlidingMenu();
		/***************************************************/
		menuView();
		menuListener(this);
		cEmployeeEntityList=(CEmployeeEntityList)this.getIntent().getExtras().getSerializable("CEmployeeEntityList");
		attendanceBll=AttendanceBll.getAttendanceBll(this);
		missionBll=MissionBll.getMissionBll(this);
		bussinessBll=BussinessBll.getBussinessBll(this);
		clientBll=ClientBll.getClienetBll(this);
		noticeBll=NoticeBll.getNoticeBll(this);
		visitBll=VisitBll.getVisitBll(this);
		breakEmployeeList();
		InitTextView();
//		initData();
		InitViewPager();
		setListener();

	}
	
	private void breakEmployeeList(){
		CEmployeeEntityList xiaoshouEntityList,peisongEntityList,shouhouEntityList,duchaEntityList,xingzhengEntityList;
		List<CEmployeeEntity> xiaoshouList=new ArrayList<CEmployeeEntity>();
		List<CEmployeeEntity> peisongList=new ArrayList<CEmployeeEntity>();
		List<CEmployeeEntity> shouhouList=new ArrayList<CEmployeeEntity>();
		List<CEmployeeEntity> duchaList=new ArrayList<CEmployeeEntity>();
		List<CEmployeeEntity> xingzhengList=new ArrayList<CEmployeeEntity>();
		for(int i=0;i<cEmployeeEntityList.getSize();i++){
			if(cEmployeeEntityList.getItem(i).getEmployeeType()==MyConstant.EMPLOYEE_XIAOSHOU){
			
				xiaoshouList.add(cEmployeeEntityList.getItem(i));
			
			}else if(cEmployeeEntityList.getItem(i).getEmployeeType()==MyConstant.EMPLOYEE_SHOUHOU){
				
				shouhouList.add(cEmployeeEntityList.getItem(i));
				
			}else if (cEmployeeEntityList.getItem(i).getEmployeeType()==MyConstant.EMPLOYEE_PEISONG) {
				
				peisongList.add(cEmployeeEntityList.getItem(i));
				
			}else if (cEmployeeEntityList.getItem(i).getEmployeeType()==MyConstant.EMPLOYEE_DUCHA) {
				
				duchaList.add(cEmployeeEntityList.getItem(i));
				
			}else if(cEmployeeEntityList.getItem(i).getEmployeeType()==MyConstant.EMPLOYEE_XINGZHENG){
				
				xingzhengList.add(cEmployeeEntityList.getItem(i));
				
			}
			
		}
		xiaoshouEntityList=new CEmployeeEntityList(xiaoshouList);
		peisongEntityList=new CEmployeeEntityList(peisongList);
		shouhouEntityList=new CEmployeeEntityList(shouhouList);
		duchaEntityList=new CEmployeeEntityList(duchaList);
		xingzhengEntityList=new CEmployeeEntityList(xingzhengList);
		employeeListss.add(xingzhengEntityList);
		employeeListss.add(xiaoshouEntityList);
		employeeListss.add(shouhouEntityList);
		employeeListss.add(peisongEntityList);
		employeeListss.add(duchaEntityList);
		
	}
	
	private void setListener(){
		listnotice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,NoticeDetailActivity.class);
				intent.putExtra("CNoticeEntity", cNoticeEntityList.getItem(arg2));
				
				startActivity(intent);
				
			}
		});
		
		
	}

//	private void initData() {
//		for (int i = 0; i < mGroupArrays.length; i++) {
//			List<Item> list = new ArrayList<Item>();
//			String[] childs = getStringArray(mGroupArrays[i]);
//			String[] details = getStringArray(mDetailIds[i]);
//			for (int j = 0; j < childs.length; j++) {
//				Item item = new Item(
//						childs[j], details[j]);
//				list.add(item);
//			}
//			mData.add(list);
//		}
//	}

	private String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	private void InitTextView() {
		textView1 = (ImageView) findViewById(R.id.textWork);
		textView1.setImageResource(R.drawable.worktwo);
		textView2 = (ImageView) findViewById(R.id.textCommunication);
		textView3 = (ImageView) findViewById(R.id.textNotice);
		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(3));
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
		view1 = inflater.inflate(R.layout.work, null);

		imgbutAttendance = (ImageButton) view1
				.findViewById(R.id.imgbutAttendance);
		imgbutAttendance.setOnClickListener(this);
		imgbutAttendance.setTag(R.id.imgbutAttendance);

		imgbutBussiness = (ImageButton) view1
				.findViewById(R.id.imgbutBussiness);
		imgbutBussiness.setOnClickListener(this);
		imgbutBussiness.setTag(R.id.imgbutBussiness);

		imgbutMission = (ImageButton) view1.findViewById(R.id.imgbutMission);
		imgbutMission.setOnClickListener(this);
		imgbutMission.setTag(R.id.imgbutMission);

		imgbutClient = (ImageButton) view1.findViewById(R.id.imgbutClient);
		imgbutClient.setOnClickListener(this);
		imgbutClient.setTag(R.id.imgbutClient);

		views.add(view1);

		view2 = inflater.inflate(R.layout.communiction, null);
		mListView = (ExpandableListView) view2.findViewById(R.id.mListView);
		mListView.setGroupIndicator(this.getResources().getDrawable(R.drawable.expander_floder));
		mAdapter = new ExpandAdapter(this, employeeListss);
		mListView.setAdapter(mAdapter);
		mListView
				.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS);
		mListView.setOnChildClickListener(this);
		views.add(view2);

		view3 = inflater.inflate(R.layout.notice, null);
		listnotice=(ListView)view3.findViewById(R.id.listnotice);
		noticeAdapter=new NoticeAdapter(this,cNoticeEntityList);
		listnotice.setAdapter(noticeAdapter);
		views.add(view3);

		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int tag = (Integer) arg0.getTag();

		switch (tag) {

		case R.id.imgbutAttendance:
			CEmployeeEntity cEmployeeEntity=new CEmployeeEntity();
			
			JSONObject enterAttendancejs=cEmployeeEntity.toJSon(MyOpcode.Operation.ENTER_ATTENDANCE);
			attendanceBll.EnterAttendance(enterAttendancejs, EnterAttencehandler);
			break;
		case R.id.imgbutMission:
			
			CMissionEntity cMissionEntity=new CMissionEntity();
			JSONObject enterMissionjs=cMissionEntity.toJson(MyOpcode.Operation.GET_UNDERWAY_MISSION);
			missionBll.EnterMission(enterMissionjs, EnterMissionHandler,MyURL.GETWAITTAKEMISSION);
			break;
			
		case R.id.imgbutBussiness:
			pdialog=ProgressDialog.show(MainActivity.this, "正在加载...", "正在执行中");  
			CEmployeeEntity ementity=new CEmployeeEntity();
			JSONObject enterBussinessJS=ementity.toJSon(MyOpcode.Operation.ENTER_BUSSINESS);
			bussinessBll.EnterBussiness(enterBussinessJS, EnterBussinessHandler,pdialog);

			break;

		case R.id.imgbutClient:
//			CEmployeeEntity emtity=new CEmployeeEntity();
//			JSONObject enterClientJS=emtity.toJSon(MyOpcode.Operation.ENTER_CLIENT_MANAGER);
//			clientBll.EnterClientManager(enterClientJS, EnterClientManagerHandler);

			CVisitEntity cVisitEntity=new CVisitEntity();
			JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETNOSTARTVISITPLAN);
			visitBll.EnterVisit(js, GetNoStartVisitHandler, MyURL.GETNOSTARTVISITPLAN);
			
		default:
			break;

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
			Intent intent=new Intent();
			Bundle bundle=new Bundle();
			bundle.putInt("ClientType", MyConstant.CLIENT_COMPANY);
			intent.putExtra("CClientEntityList", cClientEntityList);
			intent.putExtras(bundle);
			intent.setClass(MainActivity.this, ClientActivity.class);
			startActivity(intent);
		}
		
		
	};

	//进入出差
	public Handler EnterBussinessHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CBussinessEntity cBussinessEntity=(CBussinessEntity)map.get("CBussinessEntity");
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, BussinessActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("address", "address");
			intent.putExtra("CBussinessEntity", cBussinessEntity);
			intent.putExtras(bundle);
			startActivity(intent);
			
		}
		
		
	};
	
	//进入考勤
	public Handler EnterAttencehandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CAttendanceEntity cAttendanceEntity=(CAttendanceEntity)map.get("CAttendanceEntity");
			
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, AttendanceActivity.class);
			intent.putExtra("CAttendanceEntity", cAttendanceEntity);
			startActivity(intent);
		}
		
	};
	
	
	//进入任务(待接受)
	public Handler EnterMissionHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CMissionEntityList cMissionEntityList=(CMissionEntityList)map.get("CMissionEntityList");
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_WAITTAKE);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
		}
		
		
	};
	
	
	public Handler GetNoStartVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_NOTSTART);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
		}
	};
	
	
	
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
			// Animation animation = new TranslateAnimation(one * currIndex, one
			// * arg0, 0, 0);// 显然这个比较简洁，只有一行代码。
			// currIndex = arg0;
			// animation.setFillAfter(true);// True:图片停在动画结束位置
			// animation.setDuration(300);
			// imageView.startAnimation(animation);
			// Toast.makeText(MainActivity.this, "您选择了"+
			// viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
			if (viewPager.getCurrentItem() == 0) {
				textView1.setImageResource(R.drawable.worktwo);
				textView2.setImageResource(R.drawable.communtionone);
				textView3.setImageResource(R.drawable.noticeone);
			} else if (viewPager.getCurrentItem() == 1) {
				textView1.setImageResource(R.drawable.workone);
				textView2.setImageResource(R.drawable.communtiontwo);
				textView3.setImageResource(R.drawable.noticeone);
			} else if (viewPager.getCurrentItem() == 2) {
			
				JSONObject js=new JSONObject();
				try {
					js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.ENTER_NOTICE);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				noticeBll.EnterNotice(js, EnterNoticeHandler);
				textView1.setImageResource(R.drawable.workone);
				textView2.setImageResource(R.drawable.communtionone);
				textView3.setImageResource(R.drawable.noticetwo);
			}
		}

	}

	private Handler EnterNoticeHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CNoticeEntityList EntityList=(CNoticeEntityList)map.get("CNoticeEntityList");
			cNoticeEntityList=EntityList;
			noticeAdapter=new NoticeAdapter(MainActivity.this,cNoticeEntityList);
			listnotice.setAdapter(noticeAdapter);
			
		}
		
		
	};
	
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		CEmployeeEntity cEmployeeEntity = mAdapter.getChild(groupPosition, childPosition);
		Intent intent=new Intent(MainActivity.this,CommunictionDetailActivity.class);
		intent.putExtra("CEmployeeEntity", cEmployeeEntity);
		startActivity(intent);
		
		return true;
	}

}
