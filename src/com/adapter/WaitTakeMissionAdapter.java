package com.adapter;

import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.example.mobliemanager.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WaitTakeMissionAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context;
	private CMissionEntityList cMissionEntityList;
	
	public WaitTakeMissionAdapter(Context context,CMissionEntityList cMissionEntityList){
		this.inflater=LayoutInflater.from(context);
		this.context=context;
		this.cMissionEntityList=cMissionEntityList;
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
		view=inflater.inflate(R.layout.waittakemission_item, null); 
		holder.textPushTime=(TextView)view.findViewById(R.id.textPushTime);
		holder.textContent=(TextView)view.findViewById(R.id.textContent);
//		holder.textTimeData=(TextView)view.findViewById(R.id.textTimeData);
		view.setTag(holder);  
		}else {
			holder=(ViewHolder)view.getTag();
		}
		setText(holder,arg0);
		return view;
	}
	public void setText(ViewHolder holder,int arg0){
		holder.textPushTime.setText(getItem(arg0).getMissionPubdate());
		holder.textContent.setText(getItem(arg0).getMissionContent());
//		holder.textTimeData.setText(getItem(arg0).getMissionDeadLine());
		
	}
	
	private class ViewHolder{
		private TextView textPushTime;
		private TextView textContent;
//		private TextView textTimeData;
	}
	
	
}
