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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CompleteMissionAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private CMissionEntityList cMissionEntityList;
	public CompleteMissionAdapter(Context context,CMissionEntityList cMissionEntityList) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
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
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.completemission_item, null);
			holder.textState = (TextView) view
					.findViewById(R.id.textState);
			holder.textContent = (TextView) view.findViewById(R.id.textContent);
//			holder.textTimeData = (TextView) view
//					.findViewById(R.id.textTimeData);
			holder.imgmissionitem=(ImageView)view.findViewById(R.id.imgmissionitem);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		setText(holder, arg0); 
		return view;
	}

	public void setText(ViewHolder holder, int arg0) {
		holder.textContent.setText(getItem(arg0).getMissionContent());
//		holder.textTimeData.setText("先空着");
		if(getItem(arg0).getMissionState()==MyConstant.MISSION_NOCHECK){
			holder.imgmissionitem.setImageResource(R.drawable.imgmt4);
			holder.textState.setText("未审核");
			holder.textState.setTextColor(Color.MAGENTA);
		}else if (getItem(arg0).getMissionState()==MyConstant.MISSION_CHECK) {
			holder.imgmissionitem.setImageResource(R.drawable.imgmt6);
			holder.textState.setText("已审核");
			holder.textState.setTextColor(Color.GREEN);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_UNDO){
			holder.imgmissionitem.setImageResource(R.drawable.imgmt7);
			holder.textState.setText("已撤销");
			holder.textState.setTextColor(Color.BLUE);
		}else if(getItem(arg0).getMissionState()==MyConstant.MISSION_FAILURE){
			holder.imgmissionitem.setImageResource(R.drawable.imgmt5);
			holder.textState.setText("已失败");
			holder.textState.setTextColor(Color.RED);
		}
	}

	private class ViewHolder {
		private TextView textState;
		private TextView textContent;
//		private TextView textTimeData;
		private ImageView imgmissionitem;
	}
}
