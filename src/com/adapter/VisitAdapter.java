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

public class VisitAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context;
	private CVisitEntityList cVisitEntityList;
	public VisitAdapter(Context context,CVisitEntityList cVisitEntityList){
		this.inflater=LayoutInflater.from(context);
		this.context=context;
		this.cVisitEntityList=cVisitEntityList;
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
			view=inflater.inflate(R.layout.visit_item, null);
			holder.textclientcompany=(TextView)view.findViewById(R.id.textclientcompany);
			holder.textclientname=(TextView)view.findViewById(R.id.textclientname);
			holder.textVisitTime=(TextView)view.findViewById(R.id.textVisitTime);
			view.setTag(holder);
		}else {
		
			holder=(ViewHolder)view.getTag();
		
		}

		setText(holder, arg0);
		
		return view;
	}

	
	private void setText(ViewHolder holder,int arg0){
		holder.textclientcompany.setText(getItem(arg0).getClientCompany());
		holder.textclientname.setText(getItem(arg0).getClientName());
		holder.textVisitTime.setText("");
		
	}
	
	private class ViewHolder{
		private TextView textclientname;
		private TextView textclientcompany;
		private TextView textVisitTime;
		
	}
}
