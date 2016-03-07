package com.adapter;

import com.entity.CBussinessEntity;
import com.entity.CBussinessEntityList;
import com.example.mobliemanager.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BussinrssRecallAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private CBussinessEntityList cBussinessEntityList;
	
	
	public BussinrssRecallAdapter(Context context,
			CBussinessEntityList cBussinessEntityList) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		this.cBussinessEntityList = cBussinessEntityList;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cBussinessEntityList.getSize();
	}

	@Override
	public CBussinessEntity getItem(int arg0) {
		// TODO Auto-generated method stub
		return cBussinessEntityList.getItem(arg0);
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
		view=inflater.inflate(R.layout.bussiness_item, null); 
		holder.textbussinessside=(TextView)view.findViewById(R.id.textbussinessside);
		holder.textbussinesstime=(TextView)view.findViewById(R.id.textbussinesstime);
		holder.textbussinesscheck=(TextView)view.findViewById(R.id.textbussinesscheck);
		holder.imgperson=(ImageView)view.findViewById(R.id.imgperson);
		view.setTag(holder);  
		}else {
			holder=(ViewHolder)view.getTag();
		}
		setText(holder,arg0);
		return view;
	}
	public void setText(ViewHolder holder,int arg0){
		holder.textbussinessside.setText(getItem(arg0).getBussinessSideAddress());
		holder.textbussinesstime.setText(getItem(arg0).getBussinessReturnTime());
		if(getItem(arg0).getBussinessReturnTime().equals("")){
//			holder.textbussinesstime.setText("");
		}
		
		String str=null;
		if(getItem(arg0).getBussinessState()==0){
			str="未登记";
			holder.imgperson.setImageResource(R.drawable.imgmt1);
			holder.textbussinesscheck.setText(str);
			holder.textbussinesscheck.setTextColor(Color.CYAN);
		}else if(getItem(arg0).getBussinessState()==1){
			str="执行中";
			holder.imgperson.setImageResource(R.drawable.imgmt2);
			holder.textbussinesscheck.setText(str);
			holder.textbussinesscheck.setTextColor(Color.GRAY);
		}else if(getItem(arg0).getBussinessState()==2){
			str="未审核";
			holder.imgperson.setImageResource(R.drawable.imgmt4);
			holder.textbussinesscheck.setText(str);
			holder.textbussinesscheck.setTextColor(Color.MAGENTA);
		}else if(getItem(arg0).getBussinessState()==3){
			str="未通过";
			holder.imgperson.setImageResource(R.drawable.imgmt5);
			holder.textbussinesscheck.setText(str);
			holder.textbussinesscheck.setTextColor(Color.RED);
		}else if(getItem(arg0).getBussinessState()==4){
			str="已通过";
			holder.imgperson.setImageResource(R.drawable.imgmt6);
			holder.textbussinesscheck.setText(str);
			holder.textbussinesscheck.setTextColor(Color.GREEN);
		}else if(getItem(arg0).getBussinessState()==5){
			str="已撤销";
			holder.imgperson.setImageResource(R.drawable.imgmt7);
			holder.textbussinesscheck.setText(str);
			holder.textbussinesscheck.setTextColor(Color.BLUE);
		}
		
		
		
	}
	
	private class ViewHolder{
		private TextView textbussinessside;
		private TextView textbussinesstime;
		private TextView textbussinesscheck;
		private ImageView imgperson;
	}
	
	

}
