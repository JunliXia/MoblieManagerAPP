package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.mobliemanager.R;
import com.tool.ToastUtil;

public class LocationDemoActivity extends Activity {
//	private TextView locationInfoTextView = null;
//	private Button startButton = null;
//	private LocationClient locationClient = null;
//	private static final int UPDATE_TIME = 5000;
//	private static int LOCATION_COUTNS = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locationxml);

//		locationInfoTextView = (TextView) this.findViewById(R.id.tv_loc_info);
//		startButton = (Button) this.findViewById(R.id.btn_start);
//
//		locationClient = new LocationClient(this);
//		// ���ö�λ����
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true); // �Ƿ��GPS
//		option.setCoorType("bd09ll"); // ���÷���ֵ���������͡�
//		option.setPriority(LocationClientOption.NetWorkFirst); // ���ö�λ���ȼ�
//		option.setProdName("LocationDemo"); // ���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����
//		option.setScanSpan(UPDATE_TIME); // ���ö�ʱ��λ��ʱ��������λ����
//		locationClient.setLocOption(option);
//
//		// ע��λ�ü�����
//		locationClient.registerLocationListener(new BDLocationListener() {
//
//			@Override
//			public void onReceiveLocation(BDLocation location) {
//				// TODO Auto-generated method stub
//				if (location == null) {
//					return;
//				}
//				StringBuffer sb = new StringBuffer(256);
//				sb.append("Time : ");
//				sb.append(location.getTime());
//				sb.append("\nError code : ");
//				sb.append(location.getLocType());
//				sb.append("\nLatitude : ");
//				sb.append(location.getLatitude());
//				sb.append("\nLontitude : ");
//				sb.append(location.getLongitude());
//				sb.append("\nRadius : ");
//				sb.append(location.getRadius());
//				if (location.getLocType() == BDLocation.TypeGpsLocation) {
//					sb.append("\nSpeed : ");
//					sb.append(location.getSpeed());
//					sb.append("\nSatellite : ");
//					sb.append(location.getSatelliteNumber());
//				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//					sb.append("\nAddress : ");
//					sb.append(location.getAddrStr());
//				}
//				LOCATION_COUTNS++;
//				sb.append("\n���λ�ø��´�����");
//				sb.append(String.valueOf(LOCATION_COUTNS));
//				locationInfoTextView.setText(sb.toString());
//			}
//
//			@Override
//			public void onReceivePoi(BDLocation location) {
//			}
//
//		});
//		
//		if (locationClient == null) {
//			return;
//		}
//		if (locationClient.isStarted()) {
//			startButton.setText("Start");
//			locationClient.stop();
//		} else {
//			startButton.setText("Stop");
//			locationClient.start();
//			/*
//			 * �����������ֵ���ڵ���1000��ms��ʱ����λSDK�ڲ�ʹ�ö�ʱ��λģʽ������requestLocation
//			 * ( )��ÿ���趨��ʱ�䣬��λSDK�ͻ����һ�ζ�λ��
//			 * �����λSDK���ݶ�λ���ݷ���λ��û�з����仯���Ͳ��ᷢ����������
//			 * ������һ�ζ�λ�Ľ�����������λ�øı䣬�ͽ�������������ж�λ���õ��µĶ�λ�����
//			 * ��ʱ��λʱ������һ��requestLocation���ᶨʱ��������λ�����
//			 */
//			locationClient.requestLocation();
//		}
//		ToastUtil.show(LocationDemoActivity.this, "���ָ���һ��");


	}

//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		if (locationClient != null && locationClient.isStarted()) {
//			locationClient.stop();
//			locationClient = null;
//		}
//	}

}