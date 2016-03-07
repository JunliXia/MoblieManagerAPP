package com.bll;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.AccessoryDao;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.test.TouchUtils;

public class AccessoryBll {

	private static AccessoryBll accessoryBll=null;
	private Context context;
	public AccessoryBll(Context context){
		this.context=context;
		
	}
	
	public static AccessoryBll getAccessoryBll(Context context){
		if(accessoryBll==null){
			accessoryBll=new AccessoryBll(context);
		}
		return accessoryBll;
	}
	
	//上传图片
	public void uploadImg(JSONObject js){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("图片Handler接收");
				JSONObject js = (JSONObject) msg.obj;
				try {
					if(js.getBoolean(MyOpcode.Operation.SIGN)){
						ToastUtil.show(context, "图片成功");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		new AccessoryDao(context).uploadImg(handler, js);
	}
	
//	// 上传图片
//	public void uploadImg(byte[] data) {
//		Handler uploadImgHanlder = new Handler() {
//
//			@Override
//			public void handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				super.handleMessage(msg);
//				System.out.println("图片Handler接收");
//				JSONObject js = (JSONObject) msg.obj;
//				try {
//					String address = js.getString(MyOpcode.MissionAccessory.MissionAccessoryPath);
//					System.out.println("address:" + address);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		};
//
//		try {
//			new AccessoryDao(context).uploadImg(data, uploadImgHanlder);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
