package com.adapter;

import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.example.mobliemanager.R;
import com.tool.MyConstant;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BussinessActivityMissionAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private CMissionEntityList cMissionEntityList;
	
	public BussinessActivityMissionAdapter(Context context,
			CMissionEntityList cMissionEntityList) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		this.cMissionEntityList = cMissionEntityList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cMissionEntityList.getSize();
	}

	@Override
	public CMissionEntity getItem(int arg0) {
		// TODO Auto-generated method stub
		return cMissionEntityList.getItem(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(view==null){
		holder=new ViewHolder();
		view=inflater.inflate(R.layout.bussinessactivity_mission_item, null); 
//		holder.textPushTime=(TextView)view.findViewById(R.id.textPushTime);
		holder.textContent=(TextView)view.findViewById(R.id.textContent);
		holder.textState=(TextView)view.findViewById(R.id.textState);
		holder.imgmissionitem=(ImageView)view.findViewById(R.id.imgmissionitem);
		view.setTag(holder);  
		}else {
			holder=(ViewHolder)view.getTag();
		}
		setText(holder,arg0);
		return view;
	}
	
	public void setText(ViewHolder holder,int arg0){
//		holder.textPushTime.setText(getItem(arg0).getMissionPubdate());
		holder.textContent.setText(getItem(arg0).getMissionContent());
		if(getItem(arg0).getMissionState()==MyConstant.MISSION_NOCHECK){
			holder.textState.setText("未审核");
			holder.textState.setTextColor(Color.MAGENTA);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt4);
		}else if (getItem(arg0).getMissionState()==MyConstant.MISSION_CHECK) {
			holder.textState.setText("已审核");
			holder.textState.setTextColor(Color.GREEN);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt6);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_UNDO){
			holder.textState.setText("已撤销");
			holder.textState.setTextColor(Color.BLUE);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt7);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_FAILURE){
			holder.textState.setText("已失败");
			holder.textState.setTextColor(Color.RED);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt5);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_WAITTAKE){
			holder.textState.setText("未接受");
			holder.textState.setTextColor(Color.CYAN);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt1);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_UNDERWAY){
			holder.textState.setText("执行中");
			holder.textState.setTextColor(Color.GRAY);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt2);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_OUTTIME){
			holder.textState.setText("已过期");
			holder.textState.setTextColor(Color.YELLOW);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt3);
		}
		
	}
	private class ViewHolder{
//		private TextView textPushTime;
		private TextView textContent;
		private TextView textState;
		private ImageView imgmissionitem;
	}
	
}
