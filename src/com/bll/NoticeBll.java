package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.NoticeDao;
import com.entity.CNoticeEntity;
import com.entity.CNoticeEntityList;
import com.tool.MyOpcode;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class NoticeBll {
	private static NoticeBll noticeBll=null;
	private Context context;
	
	public NoticeBll(Context context){
		this.context=context;
	}
	
	public static NoticeBll getNoticeBll(Context context){
		if(noticeBll==null){
			noticeBll=new NoticeBll(context);
		}
		return noticeBll;
		
	}
	
	//进入通知公告
	public void EnterNotice(JSONObject js,final Handler EnterNoticeHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterNotice接受");
				JSONObject json=(JSONObject)msg.obj;
				List<CNoticeEntity> noticeEntities =new ArrayList<CNoticeEntity>();
				
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray(MyOpcode.Notice.NoticeList);
						for(int i=0;i<arrays.length();i++){
							JSONObject noticeJS=arrays.getJSONObject(i);
							CNoticeEntity cNoticeEntity=new CNoticeEntity(
									noticeJS.getInt("NoticeId"), 
									noticeJS.getString("NoticeTime"),
									noticeJS.getString("NoticeTitle"),
									noticeJS.getString("NoticeContent"));
						noticeEntities.add(cNoticeEntity);
						
						}
					}
					CNoticeEntityList cNoticeEntityList=new CNoticeEntityList(noticeEntities);
					if(EnterNoticeHandler!=null){
						HashMap<String, Object> map=new HashMap<String, Object>();
						map.put("CNoticeEntityList", cNoticeEntityList);
						Message mg=new Message();
						mg.obj=map;
						EnterNoticeHandler.sendMessage(mg);
					}
					
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterNotice--e:" + e);
					e.printStackTrace();
				}
				
			}
			
		};
		
		new NoticeDao(context).sendtoEnterNotice(handler, js);
	}
	
}
