package com.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.example.mobliemanager.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UnderwayMissionAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private CMissionEntityList cMissionEntityList;

	public UnderwayMissionAdapter(Context context,
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
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.underwaymission_item, null);
			// holder.textRemainTime = (TextView) view
			// .findViewById(R.id.textRemainTime);
			holder.textContent = (TextView) view.findViewById(R.id.textContent);
			holder.textTimeData = (TextView) view
					.findViewById(R.id.textTimeData);
			holder.fabushijian = (TextView) view.findViewById(R.id.fabushijian);
			holder.imgmissionitem = (ImageView) view
					.findViewById(R.id.imgmissionitem);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		setText(holder, arg0);
		return view;
	}

	public void setText(ViewHolder holder, int arg0) {
		// long remaintime=getRemaintime(getItem(arg0).getMissionDeadLine());
		// String strmain=String.valueOf(remaintime);
		// holder.textRemainTime.setText(strmain);
		holder.textContent.setText(getItem(arg0).getMissionContent());
		if (getItem(arg0).getMissionState() == 1) {
			
			holder.textTimeData.setText(getItem(arg0).getMissionDeadLine());
			holder.fabushijian.setText("任务期限");
			holder.fabushijian.setTextColor(Color.BLACK);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt2);
			holder.textTimeData.setTextColor(Color.BLUE);
		} else if (getItem(arg0).getMissionState() == 5) {
			// 过期
			holder.textTimeData.setText(getItem(arg0).getMissionDeadLine());
			holder.fabushijian.setText("已过期");
			holder.fabushijian.setTextColor(Color.RED);
			holder.imgmissionitem.setImageResource(R.drawable.imgmt3);
			holder.textTimeData.setTextColor(Color.RED);
		}

	}

	private class ViewHolder {
		// private TextView textRemainTime;
		private TextView textContent;
		private TextView textTimeData;
		private TextView fabushijian;
		private ImageView imgmissionitem;
	}

	private long getRemaintime(String time) {
		// String time="2015/4/10--2015/4/16";
		// String newtime=time.substring(11,time.length());
		// System.out.println(newtime);
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date sdate = new Date();
		Date edate = new Date();
		try {
			sdate = format.parse(time);
			System.out.println(edate);
			System.out.println(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long days = 0;
		if (sdate.getTime() - edate.getTime() < 1) {
			System.out.println("equal");
		} else {
			days = (sdate.getTime() - edate.getTime()) / (24 * 60 * 60 * 1000)
					+ 1;
		}
		System.out.println("我是days--------------" + days);

		return days;
	}
}
