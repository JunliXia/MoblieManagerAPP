package com.adapter;

import com.entity.CVisitConclusionEntity;
import com.entity.CVisitConclusionEntityList;
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

public class VisitConclusionAdatpter extends BaseAdapter{

	private LayoutInflater inflater;
	private Context context;
	private CVisitConclusionEntityList conclusionEntityList;
	
	public VisitConclusionAdatpter(Context context,CVisitConclusionEntityList conclusionEntityList){
		this.inflater=LayoutInflater.from(context);
		this.context=context;
		this.conclusionEntityList=conclusionEntityList;
		System.out.println(conclusionEntityList.getSize()+"---------------");
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return conclusionEntityList.getSize();
	}

	@Override
	public CVisitConclusionEntity getItem(int arg0) {
		// TODO Auto-generated method stub
		return conclusionEntityList.getItem(arg0);
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
		view=inflater.inflate(R.layout.visitconclusion_item, null); 
		holder.visitconclusioncheck=(TextView)view.findViewById(R.id.visitconclusioncheck);
		holder.textVisitTime=(TextView)view.findViewById(R.id.textVisitTime);
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
		if(getItem(arg0).getVisitCheck()==0){
			strcheck="未审核";
			holder.visitconclusioncheck.setTextColor(Color.GRAY);
			holder.imgperson.setImageResource(R.drawable.imgmt4);
		}else if(getItem(arg0).getVisitCheck()==1){
			strcheck="已通过";
			holder.visitconclusioncheck.setTextColor(Color.GREEN);
			holder.imgperson.setImageResource(R.drawable.imgmt6);
		}else if(getItem(arg0).getVisitCheck()==2){
			strcheck="未通过";
			holder.visitconclusioncheck.setTextColor(Color.RED);
			holder.imgperson.setImageResource(R.drawable.imgmt5);
		}
		holder.visitconclusioncheck.setText(strcheck);
		holder.textVisitTime.setText(getItem(arg0).getVisitSubmitTime());
		
	}
	private class ViewHolder{
		private TextView textVisitTime;
		private TextView visitconclusioncheck;
		private ImageView imgperson;
		
	}

}
