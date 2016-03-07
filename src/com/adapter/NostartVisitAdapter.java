package com.adapter;

import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NostartVisitAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private CVisitEntityList cVisitEntityList;
	
	
	public NostartVisitAdapter(Context context,
			CVisitEntityList cVisitEntityList) {
		this.inflater=LayoutInflater.from(context);
		this.context = context;
		this.cVisitEntityList = cVisitEntityList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cVisitEntityList.getSize();
	}

	@Override
	public CVisitEntity getItem(int arg0) {
		// TODO Auto-generated method stub
		return cVisitEntityList.getItem(arg0);
	
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
		view=inflater.inflate(R.layout.nostartvisit_item, null); 
		holder.textclientname=(TextView)view.findViewById(R.id.textclientname);
		holder.textclientcompany=(TextView)view.findViewById(R.id.textclientcompany);
		holder.textVisitTime=(TextView)view.findViewById(R.id.textVisitTime);
		view.setTag(holder);  
		}else {
			holder=(ViewHolder)view.getTag();
		}
		setText(holder,arg0);
		return view;
	}
	public void setText(ViewHolder holder,int arg0){
		holder.textclientname.setText(getItem(arg0).getClientName());
		holder.textclientcompany.setText(getItem(arg0).getClientCompany());
		holder.textVisitTime.setText(getItem(arg0).getVisitPlanStartTime());
		
	}
	
	private class ViewHolder{
		private TextView textclientname;
		private TextView textclientcompany;
		private TextView textVisitTime;
	}
	
}
