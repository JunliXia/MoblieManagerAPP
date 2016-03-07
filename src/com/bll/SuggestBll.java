package com.bll;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.SuggestDao;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class SuggestBll {
	
	private static SuggestBll suggestBll=null;
	
	private Context context;
	
	public SuggestBll(Context context){
		this.context=context;
	}
	
	public static SuggestBll getSuggestBll(Context context){
		if(suggestBll==null){
			suggestBll=new SuggestBll(context);
		}
		return suggestBll;
	}
	
	//提交投诉建议
	public void SendSuggest(JSONObject js,final Handler SendSuggestHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("SendSuggest接受");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if (json.getBoolean(MyOpcode.Operation.SIGN)) {
						ToastUtil.show(context, "提交成功");
					}
					
					if(SendSuggestHandler!=null){
						Message mg=new Message();
						SendSuggestHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("SendSuggest--e:" + e);
					e.printStackTrace();
				}
				
			}
			
			
		};
		
		new SuggestDao(context).senttoSentSuggent(handler, js);
	}

}
