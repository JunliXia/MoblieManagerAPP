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
	GeoCoder mSearch = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��

	// ��λ���
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
	 * ��ǰ�ص����
	 */
	private LatLng currentPt;
	private Marker mMarkerA;
	private InfoWindow mInfoWindow;

	// ��ʼ��ȫ�� bitmap ��Ϣ������ʱ��ʱ recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_gcoding);
	RoutePlanSearch mRouteSearch = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	RouteLine route = null;
	int nodeIndex = -1;// �ڵ�����,������ڵ�ʱʹ��
	OverlayManager routeOverlay = null;
	private TextView popupText = null;// ����view
	Button mBtnPre = null;// ��һ���ڵ�
	Button mBtnNext = null;// ��һ���ڵ�
	
	GeoCoder mSearchendpoint = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientupdataaddress);
		init();
		getKey();

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setPriority(LocationClientOption.NetWorkFirst); // ���ö�λ���ȼ�
		option.setScanSpan(300000);
		mLocClient.setLocOption(option);
		mLocClient.start();

		// ��ʼ������ģ�飬ע���¼�����
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		mSearchendpoint = GeoCoder.newInstance();
		mSearchendpoint.setOnGetGeoCodeResultListener(this);

		// ��ͼ����¼�����
		mBaiduMap.setOnMapClickListener(this);
		// ��ʼ������ģ�飬ע���¼�����
		mRouteSearch = RoutePlanSearch.newInstance();
		mRouteSearch.setOnGetRoutePlanResultListener(this);
		
		
		
		setListener();
		
		Toast.makeText(ClientUpdataAddressActivity.this, "������ȡ�ͻ�λ��", Toast.LENGTH_LONG)
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
			
				touchType = "����";
				currentPt = arg0;
				updateMapState();
			}
		});
	}
	/**
	 * ���µ�ͼ״̬��ʾ���
	 */
	private void updateMapState() {
		mBaiduMap.clear();
		String state = "";
		if (currentPt == null) {
			state = "�����������˫����ͼ�Ի�ȡ��γ�Ⱥ͵�ͼ״̬";
		} else {
			state = String.format(touchType + ",��ǰ���ȣ� %f ��ǰγ�ȣ�%f",
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

		// �������յ���Ϣ������tranist search ��˵��������������
		PlanNode stNode = PlanNode.withLocation(ptCenter);
		PlanNode enNode = PlanNode.withLocation(llA);
		// ʵ��ʹ�����������յ���н�����ȷ���趨

		mRouteSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode)
				.to(enNode));
		
		mSearchendpoint.reverseGeoCode(new ReverseGeoCodeOption()
		.location(llA));
	}
	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
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
			sb.append("\n���λ�ø��´�����");
			sb.append(String.valueOf(LOCATION_COUTNS));
			address = location.getAddrStr();
			f_Latitude = (float) location.getLatitude();
			f_Longitude = (float) location.getLongitude();
			ptCenter = new LatLng((Float.valueOf(f_Latitude)),
					(Float.valueOf(f_Longitude)));
			// ��Geo����
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
			Toast.makeText(ClientUpdataAddressActivity.this, "��Ǹ��δ���ҵ����",
					Toast.LENGTH_LONG).show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		String strInfo = String.format("γ�ȣ�%f ���ȣ�%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(ClientUpdataAddressActivity.this, strInfo, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ClientUpdataAddressActivity.this, "��Ǹ��δ���ҵ����",
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
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
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
			Toast.makeText(ClientUpdataAddressActivity.this, "��Ǹ��δ�ҵ����",
					Toast.LENGTH_SHORT).show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// ���յ��;�����ַ����壬ͨ�����½ӿڻ�ȡ�����ѯ��Ϣ
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
