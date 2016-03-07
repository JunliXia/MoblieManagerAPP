package com.bll;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.AddressDao;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class AddressBll {
	private static AddressBll addressBll=null;
	
	private Context context;
	
	public AddressBll(Context context){
		this.context=context;
	}
	
	public static AddressBll getAddressBll(Context context){
		if(addressBll==null){
			addressBll=new AddressBll(context);
		}
		return addressBll;
	}
	
	//提交地理位置
	public void SendAddress(JSONObject js,final Handler SendAddressHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("SendAddress接受");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
//						ToastUtil.show(context, "提交成功");
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("SendAttendance--e:" + e);
					e.printStackTrace();
				}
			}
			
		};
		new AddressDao(context).sendAddress(handler, js);
	}
	
}
