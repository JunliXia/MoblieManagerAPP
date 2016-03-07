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
		// 设置定位条件
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		option.setProdName("LocationDemo"); // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setScanSpan(UPDATE_TIME); // 设置定时定位的时间间隔。单位毫秒
		locationClient.setLocOption(option);

		// 注册位置监听器
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
				sb.append("\n检查位置更新次数：");
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
			 * 当所设的整数值大于等于1000（ms）时，定位SDK内部使用定时定位模式。调用requestLocation
			 * ( )后，每隔设定的时间，定位SDK就会进行一次定位。
			 * 如果定位SDK根据定位依据发现位置没有发生变化，就不会发起网络请求，
			 * 返回上一次定位的结果；如果发现位置改变，就进行网络请求进行定位，得到新的定位结果。
			 * 定时定位时，调用一次requestLocation，会定时监听到定位结果。
			 */
			locationClient.requestLocation();
		
		}
//		ToastUtil.show(LocationDemoActivity.this, "我又干了一次");


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

	
	
	//定义内容类继承Binder
    public class LocalBinder extends Binder{
        //返回本地服务
    	LocationService getService(){
            return LocationService.this;
        }
    }

}
