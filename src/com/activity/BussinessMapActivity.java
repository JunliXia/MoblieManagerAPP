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
		option.setScanSpan(10000);
		mLocClient.setLocOption(option);
		mLocClient.start();



		// ��ʼ������ģ�飬ע���¼�����
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
	        	Date curDate = new  Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
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
			pdialog=ProgressDialog.show(BussinessMapActivity.this, "���ڼ���...", "����ִ����");  
			CEmployeeEntity ementity=new CEmployeeEntity();
			JSONObject enterBussinessJS=ementity.toJSon(MyOpcode.Operation.ENTER_BUSSINESS);
			bussinessBll.EnterBussiness(enterBussinessJS, EnterBussinessHandler,pdialog);

			
		}
		
	};
	
	//�������
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
			f_Latitude=(float) location.getLatitude();
			f_Longitude=(float)location.getLongitude();
			System.out.println(f_Latitude+"     +"+f_Longitude);
			LatLng ptCenter = new LatLng((Float.valueOf(f_Latitude)), (Float.valueOf(f_Longitude)));
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
			Toast.makeText(BussinessMapActivity.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
					.show();
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
		Toast.makeText(BussinessMapActivity.this, strInfo, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(BussinessMapActivity.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
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
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}



}
