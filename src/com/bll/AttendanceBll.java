package com.bll;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.AttendanceDao;
import com.entity.CAttendanceEntity;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class AttendanceBll {

	private static AttendanceBll attendanceBll = null;
	private Context context;

	public AttendanceBll(Context context) {
		this.context = context;
	}

	public static AttendanceBll getAttendanceBll(Context context) {
		if (attendanceBll == null) {
			attendanceBll = new AttendanceBll(context);
		}
		return attendanceBll;
	}
	
	
	
	//进入考勤管理
	public void EnterAttendance(JSONObject js,final Handler EnterAttencehandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterAttendance接受");
				JSONObject json=(JSONObject)msg.obj;
				CAttendanceEntity cAttendanceEntity=new CAttendanceEntity();
				
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						cAttendanceEntity=new CAttendanceEntity(
								json.getString("AttendanceRegisterTime"), 
								json.getString("AttendanceSignoutTime"));
					}
					
					if(EnterAttencehandler!=null){
						Message mg = new Message();
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CAttendanceEntity", cAttendanceEntity);
						mg.obj = map;
						EnterAttencehandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterAttendance--e:" + e);
					e.printStackTrace();
				}
			}
			
		};
		new AttendanceDao(context).sendtoEnterAttendance(handler, js);
	}
	
	//签到
	
	public void SendRegisterAttendance(JSONObject js,final Handler SendAttendanceHnadler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("SendAttendance接受");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						ToastUtil.show(context, "提交成功");
					}
					if(SendAttendanceHnadler!=null){
						Message mg = new Message();
						SendAttendanceHnadler.sendMessage(mg);
										
					}
				
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("SendAttendance--e:" + e);
					e.printStackTrace();
				}
			
			}
		};
		
		new AttendanceDao(context).sendtoRegisterAttendance(handler, js);
		
	}
	
	//签退
	public void SendSignOutAttendance(JSONObject js,final Handler SendAttendanceHnadler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("SendAttendance接受");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						ToastUtil.show(context, "提交成功");
					}
					if(SendAttendanceHnadler!=null){
						Message mg = new Message();
						SendAttendanceHnadler.sendMessage(mg);
										
					}
				
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("SendAttendance--e:" + e);
					e.printStackTrace();
				}
			
			}
		};
		
		new AttendanceDao(context).sendtoSignOutAttendance(handler, js);
		
		
		
	}

}
