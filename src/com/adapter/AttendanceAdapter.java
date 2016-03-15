package com.adapter;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bll.AttendanceBll;
import com.bll.LoginBll;
import com.entity.CAttendanceEntity;
import com.entity.CEmployeeEntity;
import com.example.mobliemanager.R;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

public class AttendanceAdapter extends BaseAdapter {
	private static String str="";
	private static int colortag=0;
	private LayoutInflater inflater;  
	private Context context;
	private CAttendanceEntity cAttendanceEntity;
	private AttendanceBll attendanceBll;
	
	public AttendanceAdapter(Context context,CAttendanceEntity cAttendanceEntity){
		
		this.inflater=LayoutInflater.from(context);
		this.cAttendanceEntity=cAttendanceEntity;
		this.context=context;
		attendanceBll=AttendanceBll.getAttendanceBll(context);
		
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(view==null){
		holder=new ViewHolder();
		view=inflater.inflate(R.layout.attendance_item, null); 
		holder.imageClicre=(ImageView)view.findViewById(R.id.imgviewStartWork);
		holder.textState=(TextView)view.findViewById(R.id.textState);
		holder.textTime=(TextView)view.findViewById(R.id.textTime);
		view.setTag(holder);  
		}else {
			holder=(ViewHolder)view.getTag();
		}
			
		
		holder.imageClicre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(holder.textTime.getText().toString().equals("")) {
					ToastUtil.show(context, holder.textTime.getText().toString());
				new AlertDialog.Builder(context).setTitle("提示")//设置对话框标题  
				   
				     .setMessage("确认提交考勤数据吗?")//设置显示的内容  
				  
				     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
				  
				             // TODO Auto-generated method stub  
				        	 SimpleDateFormat formatter =new SimpleDateFormat ("HH:mm");       
				        	 Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
				        	 String strTime  =  formatter.format(curDate);
				        	 System.out.println("-------------------"+strTime);
				        	 CAttendanceEntity cAttendanceEntity=new CAttendanceEntity();
				        	
				        	
				        	 
				        	 if(position==0){
				     			holder.textState.setText("签到");
				     			holder.imageClicre.setImageResource(R.drawable.choiceclice);
				     		}else if(position==1){
				     			holder.textState.setText("签退");
				     			holder.imageClicre.setImageResource(R.drawable.choiceclice);
				     			
				     		}
				        	 JSONObject js = null;
				        	 if(position==0){
				        		 if(compareRegisterTime(strTime)){
				        			 js=cAttendanceEntity.toRegisterJSon(LoginBll.getCuruser().getEmployeeId(), MyOpcode.Operation.SEND_REGISTERATTENDANCE, strTime);
					        		 attendanceBll.SendRegisterAttendance(js, SendAttendanceHnadler);
					        		 holder.textTime.setText(strTime);
					        		 if(colortag==1){
					        			 holder.textTime.setTextColor(Color.GREEN);
					        		 }else if(colortag==2){
					        			 holder.textTime.setTextColor(Color.RED);
					        		 }
				        		 }else{
				        			 ToastUtil.show(context, str);
				        		 }
				        		
				        	 }else if(position==1){
				        		if(compareSignouttime(strTime)){
				        			 js=cAttendanceEntity.toSignoutJSon(LoginBll.getCuruser().getEmployeeId(), MyOpcode.Operation.SEND_SIGNOUTATTENDANCE, strTime);
					        		 attendanceBll.SendSignOutAttendance(js, SendAttendanceHnadler);
					        		 holder.textTime.setText(strTime);
					        		 if(colortag==1){
					        			 holder.textTime.setTextColor(Color.GREEN);
					        		 }else if (colortag==2) {
					        			 holder.textTime.setTextColor(Color.RED);
									}
				        		}else{
				        			 ToastUtil.show(context, str);
				        		}
				        		
				        	 }
				        
				        
				        	 
				         }  
				  
				     }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮  
				  
				          
				  
				         @Override  
				  
				         public void onClick(DialogInterface dialog, int which) {//响应事件  
				  
				             // TODO Auto-generated method stub  
				  
				  
				         }  
				  
				     }).show();//在按键响应事件中显示此对话框  
				  
			}else {
				ToastUtil.show(context, "今日已完成");
			}
			}
		});
		setText(holder,position);
		return view;
	}
	

	
	private Handler SendAttendanceHnadler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
		}
		
	};
	
	public void setText(ViewHolder holder,int arg0){
		 SimpleDateFormat formatter =new SimpleDateFormat ("HH:mm");       
    	 Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
    	 String strTime  =  formatter.format(curDate);
		if(arg0==0){
			
			holder.textState.setText("签到");
			if(!strTime.equals(cAttendanceEntity.getAttendanceRegisterTime())){
				holder.textTime.setText(cAttendanceEntity.getAttendanceRegisterTime());
				holder.imageClicre.setImageResource(R.drawable.choiceclice);
				 if(compareRegisterTime(strTime)){
	        		 if(colortag==1){
	        			 holder.textTime.setTextColor(Color.GREEN);
	        		 }else if(colortag==2){
	        			 holder.textTime.setTextColor(Color.RED);
	        		 }
				 }
			}else{
				holder.textTime.setText(cAttendanceEntity.getAttendanceRegisterTime());
				if(compareRegisterTime(strTime)){
	        		 if(colortag==1){
	        			 holder.textTime.setTextColor(Color.GREEN);
	        		 }else if(colortag==2){
	        			 holder.textTime.setTextColor(Color.RED);
	        		 }
				 }
			}
			
			
		}else if(arg0==1){
			holder.textState.setText("签退");
			if(!strTime.equals(cAttendanceEntity.getAttendanceSignoutTime())){
				holder.textTime.setText(cAttendanceEntity.getAttendanceSignoutTime());
				holder.imageClicre.setImageResource(R.drawable.choiceclice);
				if(compareSignouttime(strTime)){
	        		 if(colortag==1){
	        			 holder.textTime.setTextColor(Color.GREEN);
	        		 }else if (colortag==2) {
	        			 holder.textTime.setTextColor(Color.RED);
					}
				}
			}else {
				holder.textTime.setText(cAttendanceEntity.getAttendanceRegisterTime());
				if(compareRegisterTime(strTime)){
	        		 if(colortag==1){
	        			 holder.textTime.setTextColor(Color.GREEN);
	        		 }else if(colortag==2){
	        			 holder.textTime.setTextColor(Color.RED);
	        		 }
				 }
			}
			
		}
	}
	
	private class ViewHolder{
		private ImageView imageClicre;
		private TextView textState;
		private TextView textTime;
	}
	public static boolean compareRegisterTime(String time){
		Calendar ctime=Calendar.getInstance();
		String s1 = "08:30";
		String s2 = "09:00";
		SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(sFormat.parse(s1));
			c2.setTime(sFormat.parse(s2));
			ctime.setTime(sFormat.parse(time));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result1 = c1.compareTo(ctime);
		int result2 = c2.compareTo(ctime);
		
		
		if(result1>0){
			str="还未到考勤时间";
			return false;
		}else if(result1<0&&result2>0){
			colortag=1;
			return true;
		}else if(result2<0){
			colortag=2;
			return true;
		}
		
		return false;
	}
	
	
	private static boolean compareSignouttime(String time){
		Calendar ctime=Calendar.getInstance();
		String s3 = "16:30";
		String s4 = "17:00";
		SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm");
		Calendar c3 = Calendar.getInstance();
		Calendar c4 = Calendar.getInstance();
		
		try {
			c3.setTime(sFormat.parse(s3));
			c4.setTime(sFormat.parse(s4));
			ctime.setTime(sFormat.parse(time));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int result1 = c3.compareTo(ctime);
		int result2 = c4.compareTo(ctime);
		
		if(result1>0){
			str="还未到签退时间";
			return false;
		}else if(result1<0&&result2>0){
			colortag=1;
			return true;
		}else if(result2<0){
			colortag=2;
			return true;
		}
		return false;
	}
	
}
