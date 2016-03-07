package com.activity;

import org.json.JSONObject;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bll.AddressBll;
import com.bll.LoginBll;
import com.entity.CAddressEntity;
import com.tool.ToastUtil;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;

public class LocationService extends Service {
	private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 1800000;
	private static int LOCATION_COUTNS = 0;
	private AddressBll addressBll;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		addressBll=AddressBll.getAddressBll(this);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
		.build());
	}



	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		locationClient = new LocationClient(this);
		// ���ö�λ����
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // �Ƿ��GPS
		option.setCoorType("bd09ll"); // ���÷���ֵ���������͡�
		option.setPriority(LocationClientOption.NetWorkFirst); // ���ö�λ���ȼ�
		option.setProdName("LocationDemo"); // ���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����
		option.setScanSpan(UPDATE_TIME); // ���ö�ʱ��λ��ʱ��������λ����
		locationClient.setLocOption(option);

		// ע��λ�ü�����
		locationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				if (location == null) {
					return;
				}
				StringBuffer sb = new StringBuffer(256);
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
//				locationInfoTextView.setText(sb.toString());
				CAddressEntity cAddressEntity=new CAddressEntity();
				String strlat=String.valueOf(location.getLatitude());
				String strlong=String.valueOf(location.getLongitude());
				String straddress=String.valueOf(location.getAddrStr());
				System.out.println(straddress);
				JSONObject js=cAddressEntity.toJson(LoginBll.getCuruser().getEmployeeId(),strlat ,strlong , straddress);
				addressBll.SendAddress(js, SendAddressHandler);
				System.out.println(sb);
			}

			@Override
			public void onReceivePoi(BDLocation location) {
			}

		});
		
		if (locationClient == null) {
			return;
		}
		if (locationClient.isStarted()) {
//			startButton.setText("Start");
			locationClient.stop();
		} else {
//			startButton.setText("Stop");
			locationClient.start();
			/*
			 * �����������ֵ���ڵ���1000��ms��ʱ����λSDK�ڲ�ʹ�ö�ʱ��λģʽ������requestLocation
			 * ( )��ÿ���趨��ʱ�䣬��λSDK�ͻ����һ�ζ�λ��
			 * �����λSDK���ݶ�λ���ݷ���λ��û�з����仯���Ͳ��ᷢ����������
			 * ������һ�ζ�λ�Ľ�����������λ�øı䣬�ͽ�������������ж�λ���õ��µĶ�λ�����
			 * ��ʱ��λʱ������һ��requestLocation���ᶨʱ��������λ�����
			 */
			locationClient.requestLocation();
		
		}
//		ToastUtil.show(LocationDemoActivity.this, "���ָ���һ��");


	}
	private Handler SendAddressHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
		
		
	};


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}



	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return (IBinder) arg0;
	}

	
	
	//����������̳�Binder
    public class LocalBinder extends Binder{
        //���ر��ط���
    	LocationService getService(){
            return LocationService.this;
        }
    }

}
