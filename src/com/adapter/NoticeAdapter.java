package com.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.entity.CNoticeEntity;
import com.entity.CNoticeEntityList;
import com.example.mobliemanager.R;

public class NoticeAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private CNoticeEntityList cNoticeEntityList;
	public NoticeAdapter(Context context,CNoticeEntityList cNoticeEntityList) {
		this.inflater = LayoutInflater.from(context);
		this.context = context;
		if(cNoticeEntityList!=null){
			this.cNoticeEntityList=cNoticeEntityList;
		}else {
			List<CNoticeEntity> cNoticeEntities=new ArrayList<CNoticeEntity>();
			this.cNoticeEntityList=new CNoticeEntityList(cNoticeEntities);
		}
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cNoticeEntityList.getSize();
	}

	@Override
	public CNoticeEntity getItem(int arg0) {
		// TODO Auto-generated method stub
		return cNoticeEntityList.getItem(arg0);
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
			view = inflater.inflate(R.layout.notice_item, null);
			holder.textTimeData = (TextView) view
					.findViewById(R.id.textTimeData);
			holder.texttitle = (TextView) view.findViewById(R.id.texttitle);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		setText(holder, arg0);
		return view;
	}

	public void setText(ViewHolder holder, int arg0) {
		holder.textTimeData.setText(getItem(arg0).getNoticeTime());
		holder.texttitle.setText(getItem(arg0).getNoticeTitle());

	}

	private class ViewHolder {
		private TextView textTimeData;
		private TextView texttitle;
	}
}