package com.adapter;

import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
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

public class CompleteVisitAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context;
	private CVisitEntityList cVisitEntityList;
	
	
	public CompleteVisitAdapter(Context context,
			CVisitEntityList cVisitEntityList) {
		this.inflater=LayoutInflater.from(context);
		this.context = context;
		this.cVisitEntityList = cVisitEntityList;
		for(int i=0;i<cVisitEntityList.getSize();i++){
			System.out.println(cVisitEntityList.getItem(i).toString());
		}
		
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
		view=inflater.inflate(R.layout.completevisit_item, null); 
		holder.textclientname=(TextView)view.findViewById(R.id.textclientname);
		holder.textclientcompany=(TextView)view.findViewById(R.id.textclientcompany);
		holder.textstate=(TextView)view.findViewById(R.id.textstate);
		holder.imgperson=(ImageView)view.findViewById(R.id.imgperson);
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
//		holder.textstate.setText(getItem(arg0).getVisitPlanStartTime());
		if(getItem(arg0).getVisitPlanState()==2){
			holder.imgperson.setImageResource(R.drawable.imgvt4);
			holder.textstate.setText("Î´ÉóºË");
			holder.textstate.setTextColor(Color.MAGENTA);
		}else if (getItem(arg0).getVisitPlanState()==3) {
			holder.imgperson.setImageResource(R.drawable.imgvt6);
			holder.textstate.setText("ÒÑÉóºË");
			holder.textstate.setTextColor(Color.GREEN);
		}else if(getItem(arg0).getVisitPlanState()==4){
			holder.imgperson.setImageResource(R.drawable.imgvt7);
			holder.textstate.setText("ÒÑ³·Ïú");
			holder.textstate.setTextColor(Color.BLUE);
		}else if(getItem(arg0).getVisitPlanState()==6){
			holder.imgperson.setImageResource(R.drawable.imgvt5);
			holder.textstate.setText("ÒÑÊ§°Ü");
			holder.textstate.setTextColor(Color.RED);
		}
	}
	
	private class ViewHolder{
		private TextView textclientname;
		private TextView textclientcompany;
		private TextView textstate;
		private ImageView imgperson;
	}
	
}
