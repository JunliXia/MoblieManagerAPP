package com.activity;


import java.util.ArrayList;











import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.entity.CClientEntity;
import com.example.mobliemanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ClientUpdataAddressActivity extends Activity implements
		OnGetGeoCoderResultListener ,BaiduMap.OnMapClickListener,
		OnGetRoutePlanResultListener{
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
    String address,name,company,phone,zone,WhatActivity;
    int clientid=0;
	private ImageView imageUpload;
	float f_Latitude;
	float f_Longitude;
	LatLng ptCenter ;
	
	private String touchType;
	/**
	 * 当前地点击点
	 */
	private LatLng currentPt;
	private Marker mMarkerA;
	private InfoWindow mInfoWindow;

	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_gcoding);
	RoutePlanSearch mRouteSearch = null; // 搜索模块，也可去掉地图模块独立使用
	RouteLine route = null;
	int nodeIndex = -1;// 节点索引,供浏览节点时使用
	OverlayManager routeOverlay = null;
	private TextView popupText = null;// 泡泡view
	Button mBtnPre = null;// 上一个节点
	Button mBtnNext = null;// 下一个节点
	
	GeoCoder mSearchendpoint = null; // 搜索模块，也可去掉地图模块独立使用
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientupdataaddress);
		init();
		getKey();

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
		option.setScanSpan(300000);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		mSearchendpoint = GeoCoder.newInstance();
		mSearchendpoint.setOnGetGeoCodeResultListener(this);

		// 地图点击事件处理
		mBaiduMap.setOnMapClickListener(this);
		// 初始化搜索模块，注册事件监听
		mRouteSearch = RoutePlanSearch.newInstance();
		mRouteSearch.setOnGetRoutePlanResultListener(this);
		
		
		
		setListener();
		
		Toast.makeText(ClientUpdataAddressActivity.this, "长按获取客户位置", Toast.LENGTH_LONG)
		.show();
	}
	private void getKey(){
		Bundle bundle=getIntent().getExtras();
		address=bundle.getString("address");
		name=bundle.getString("name");
		company=bundle.getString("company");
		phone=bundle.getString("phone");
		zone=bundle.getString("zone");
		WhatActivity=bundle.getString("WhatActivity");
		clientid=bundle.getInt("id");
	}
	
	private void init() {
		imageUpload = (ImageView) findViewById(R.id.imageUpload);

	}

	private void setListener() {
		imageUpload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				if (WhatActivity.endsWith("ClientUpdateActivity")) {
					intent.setClass(ClientUpdataAddressActivity.this,ClientUpdateActivity.class);
					CClientEntity cClientEntity=new CClientEntity(name, company, phone, zone, address,clientid);
					intent.putExtra("CClientEntity", cClientEntity);
				}else if (WhatActivity.endsWith("ClientAddActivity")) {
					intent.setClass(ClientUpdataAddressActivity.this,ClientAddActivity.class);
					bundle.putString("address", address);
					bundle.putString("name", name);
					bundle.putString("company",company);
					bundle.putString("phone", phone);
					bundle.putString("zone",zone);
					intent.putExtras(bundle);
				}
				
			
				startActivity(intent);
				finish();
			}
		});
		
		
		mBaiduMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			
			@Override
			public void onMapLongClick(LatLng arg0) {
				// TODO Auto-generated method stub
			
				touchType = "长按";
				currentPt = arg0;
				updateMapState();
			}
		});
	}
	/**
	 * 更新地图状态显示面板
	 */
	private void updateMapState() {
		mBaiduMap.clear();
		String state = "";
		if (currentPt == null) {
			state = "点击、长按、双击地图以获取经纬度和地图状态";
		} else {
			state = String.format(touchType + ",当前经度： %f 当前纬度：%f",
					currentPt.longitude, currentPt.latitude);
		}
		state += "\n";
		MapStatus ms = mBaiduMap.getMapStatus();
		state += String.format(
				"zoom=%.1f rotate=%d overlook=%d",
				ms.zoom, (int) ms.rotate, (int) ms.overlook);
	
	
		
		LatLng llA = new LatLng(currentPt.latitude,currentPt.longitude);

		OverlayOptions ooA = new MarkerOptions().position(llA).icon(bdA)
				.zIndex(9).draggable(true);
		
		mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
		ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
		giflist.add(bdA);

		// 设置起终点信息，对于tranist search 来说，城市名无意义
		PlanNode stNode = PlanNode.withLocation(ptCenter);
		PlanNode enNode = PlanNode.withLocation(llA);
		// 实际使用中请对起点终点城市进行正确的设定

		mRouteSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode)
				.to(enNode));
		
		mSearchendpoint.reverseGeoCode(new ReverseGeoCodeOption()
		.location(llA));
	}
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
			f_Latitude = (float) location.getLatitude();
			f_Longitude = (float) location.getLongitude();
			ptCenter = new LatLng((Float.valueOf(f_Latitude)),
					(Float.valueOf(f_Longitude)));
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
			Toast.makeText(ClientUpdataAddressActivity.this, "抱歉，未能找到结果",
					Toast.LENGTH_LONG).show();
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
		Toast.makeText(ClientUpdataAddressActivity.this, strInfo, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ClientUpdataAddressActivity.this, "抱歉，未能找到结果",
					Toast.LENGTH_LONG).show();
			return;
		}
		mBaiduMap.clear();
		// mBaiduMap.addOverlay(new
		// MarkerOptions().position(result.getLocation())
		// .icon(BitmapDescriptorFactory
		// .fromResource(R.drawable.ic_launcher)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		Toast.makeText(ClientUpdataAddressActivity.this, result.getAddress(),
				Toast.LENGTH_LONG).show();
		address = result.getAddress();
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

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
		// TODO Auto-generated method stub

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ClientUpdataAddressActivity.this, "抱歉，未找到结果",
					Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			route = result.getRouteLines().get(0);
			WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
			mBaiduMap.setOnMarkerClickListener(overlay);
			routeOverlay = overlay;
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}

	}
	private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

		public MyWalkingRouteOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public BitmapDescriptor getStartMarker() {
			return BitmapDescriptorFactory
					.fromResource(R.drawable.imgnull);
		}

		@Override
		public BitmapDescriptor getTerminalMarker() {
			return BitmapDescriptorFactory
					.fromResource(R.drawable.icon_gcoding);
		}
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		mBaiduMap.hideInfoWindow();
	}

	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
