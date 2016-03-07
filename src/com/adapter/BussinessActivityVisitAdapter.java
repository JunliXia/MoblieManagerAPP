package com.adapter;

import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BussinessActivityVisitAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private Context context;
	private CVisitEntityList cVisitEntityList;
	
	
	public BussinessActivityVisitAdapter(Context context,
			CVisitEntityList cVisitEntityList) {
		this.inflater = LayoutInflater.from(context);
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
		view=inflater.inflate(R.layout.bussinessactivity_visitplan_item, null); 
		holder.textclientname=(TextView)view.findViewById(R.id.textclientname);
		holder.textclientcompany=(TextView)view.findViewById(R.id.textclientcompany);
		holder.textState=(TextView)view.findViewById(R.id.textState);
		holder.imgperson=(ImageView)view.findViewById(R.id.imgperson);
		view.setTag(holder);  
		}else {
			holder=(ViewHolder)view.getTag();
		}
		setText(holder,arg0);
		return view;
	}
	
	public void setText(ViewHolder holder,int arg0){
		String strcheck = null;
		if(getItem(arg0).getVisitPlanState()==0){
			strcheck="未开始";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.CYAN);
			holder.imgperson.setImageResource(R.drawable.imgvt1);
		}else if(getItem(arg0).getVisitPlanState()==1){
			strcheck="执行中";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.GRAY);
			holder.imgperson.setImageResource(R.drawable.imgvt2);
		}else if(getItem(arg0).getVisitPlanState()==2){
			strcheck="未审核";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.MAGENTA);
			holder.imgperson.setImageResource(R.drawable.imgvt4);
		}else if(getItem(arg0).getVisitPlanState()==3){
			strcheck="已审核";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.GREEN);
			holder.imgperson.setImageResource(R.drawable.imgvt6);
		}else if(getItem(arg0).getVisitPlanState()==4){
			strcheck="已撤销";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.BLUE);
			holder.imgperson.setImageResource(R.drawable.imgvt7);
		}else if(getItem(arg0).getVisitPlanState()==5){
			strcheck="已过期";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.YELLOW);
			holder.imgperson.setImageResource(R.drawable.imgvt3);
		}else if(getItem(arg0).getVisitPlanState()==6){
			strcheck="已失败";
			holder.textState.setText(strcheck);
			holder.textState.setTextColor(Color.RED);
			holder.imgperson.setImageResource(R.drawable.imgvt5);
		}
		
		holder.textclientname.setText(getItem(arg0).getClientName());
		holder.textclientcompany.setText(getItem(arg0).getClientCompany());
		
	}
	
	private class ViewHolder{
		private TextView textclientname;
		private TextView textclientcompany;
		private TextView textState;
		private ImageView imgperson;
	}

}
