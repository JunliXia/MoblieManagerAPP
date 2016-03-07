package com.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bll.BussinessBll;
import com.entity.CBussinessEntity;
import com.entity.CEmployeeEntity;
import com.example.mobliemanager.R;
import com.tool.MyOpcode;
import com.tool.MyURL;

public class BussinessMapActivity extends Activity implements
		OnGetGeoCoderResultListener {
	GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用

	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	BitmapDescriptor mCurrentMarker;

	//
	private static int LOCATION_COUTNS = 0;

	MapView mMapView;
	BaiduMap mBaiduMap;
	StringBuffer sb = new StringBuffer(256);
	String address = "";

	private ImageView imageUpload;
	float f_Latitude;
	float f_Longitude;
	
	
	private BussinessBll bussinessBll;
	private CBussinessEntity cBussinessEntity;
	private int myoperation=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bussinessmap);
		init();
		setListener();
		getKey();
		bussinessBll=BussinessBll.getBussinessBll(this);
		cBussinessEntity=(CBussinessEntity)this.getIntent().getExtras().getSerializable("CBussinessEntity");
		
		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		option.setScanSpan(10000);
		mLocClient.setLocOption(option);
		mLocClient.start();



		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		
	}

	private void getKey(){
		  Bundle bundle=getIntent().getExtras();  
		  myoperation=bundle.getInt("Myoperation");
		 
	}
	private void init() {
		imageUpload = (ImageView) findViewById(R.id.imageUpload);

	}

	private void setListener() {
		imageUpload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				CBussinessEntity Entity=new CBussinessEntity();
				SimpleDateFormat formatter =new SimpleDateFormat ("yyyy/MM/dd");       
	        	Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
	        	String strTime  =  formatter.format(curDate);
				JSONObject js=Entity.toSendJson(cBussinessEntity.getBussinessId(), address,strTime, myoperation);
				String url = null;
				if(myoperation==MyOpcode.Operation.SEND_BUSSINESS_IN){
					url=MyURL.INADDRESSBUSSINESS;
				}else if(myoperation==MyOpcode.Operation.SEND_BUSSINESS_OUT){
					url=MyURL.OUTADDRESSBUSSINESS;
				}
				bussinessBll.SendBussiness(js, sendInHandler,url);
				
				
			}
		});
	}
	private  ProgressDialog pdialog;
	
	private Handler sendInHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			pdialog=ProgressDialog.show(BussinessMapActivity.this, "正在加载...", "正在执行中");  
			CEmployeeEntity ementity=new CEmployeeEntity();
			JSONObject enterBussinessJS=ementity.toJSon(MyOpcode.Operation.ENTER_BUSSINESS);
			bussinessBll.EnterBussiness(enterBussinessJS, EnterBussinessHandler,pdialog);

			
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
			intent.setClass(BussinessMapActivity.this, BussinessActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("address", "address");
			intent.putExtra("CBussinessEntity", cBussinessEntity);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
		}
		
		
	};
	
	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			LatLng ll = new LatLng(location.getLatitude(),
					location.getLongitude());
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
			mBaiduMap.animateMapStatus(u);
		
			sb.append("Time : ");
			sb.append(location.getTime());
			sb.append("\nError code : ");
			sb.append(location.getLocType());
			sb.append("\nLatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nLontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nRadius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nSpeed : ");
				sb.append(location.getSpeed());
				sb.append("\nSatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\nAddress : ");
				sb.append(location.getAddrStr());

			}
			LOCATION_COUTNS++;
			sb.append("\n检查位置更新次数：");
			sb.append(String.valueOf(LOCATION_COUTNS));
			address = location.getAddrStr();
			f_Latitude=(float) location.getLatitude();
			f_Longitude=(float)location.getLongitude();
			System.out.println(f_Latitude+"     +"+f_Longitude);
			LatLng ptCenter = new LatLng((Float.valueOf(f_Latitude)), (Float.valueOf(f_Longitude)));
			// 反Geo搜索
			mSearch.reverseGeoCode(new ReverseGeoCodeOption()
					.location(ptCenter));
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}


	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}


	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(BussinessMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		String strInfo = String.format("纬度：%f 经度：%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(BussinessMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(BussinessMapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
//		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
//				.icon(BitmapDescriptorFactory
//						.fromResource(R.drawable.ic_launcher)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		Toast.makeText(BussinessMapActivity.this, result.getAddress(),
				Toast.LENGTH_LONG).show();
		address=result.getAddress();
	}


	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}



}
