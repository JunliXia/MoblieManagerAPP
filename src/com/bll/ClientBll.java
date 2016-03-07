package com.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.ClientDao;
import com.entity.CClientEntity;
import com.entity.CClientEntityList;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class ClientBll {
	private static ClientBll clientBll=null;
	
	private Context context;
	
	public ClientBll(Context context){
		this.context=context;
	}
	
	public static ClientBll getClienetBll(Context context){
		if (clientBll==null) {
			clientBll=new ClientBll(context);
		}
		return clientBll;
	}
	
	//进入客户管理
	public void EnterClientManager(JSONObject js,final Handler EnterClientManagerHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterClientManager接受");
				JSONObject json=(JSONObject)msg.obj;
				List<CClientEntity> cClientEntities=new ArrayList<CClientEntity>();
				
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray(MyOpcode.Client.ClientList);
						for(int i=0;i<arrays.length();i++){
							JSONObject clientJS=arrays.getJSONObject(i);
							CClientEntity cClientEntity=new CClientEntity(
									clientJS.getInt("ClientId"),
									clientJS.getString("ClientName"),
									clientJS.getString("ClientCompany"),
									clientJS.getString("ClientPhone"),
									clientJS.getString("ClientArea"), 
									clientJS.getString("ClientAddress"),
									clientJS.getInt("ClientState"));
							
							cClientEntities.add(cClientEntity);
						}
						
						CClientEntityList cClientEntityList=new CClientEntityList(cClientEntities);
						if(EnterClientManagerHandler!=null){
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("CClientEntityList", cClientEntityList);
							Message mg=new Message();
							mg.obj = map;
							EnterClientManagerHandler.sendMessage(mg);
						}
					}
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("EnterClientManager--e:" + e);
					e.printStackTrace();
				}
				
				
			}
			
		};
		new ClientDao(context).senttoEnterClientManager(handler, js);
	}
	
	
	//进入客户提交记录
	public void EnterClientSubmit(JSONObject js,final Handler EnterClientSubmitHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("EnterClientSubmit接受");
				JSONObject json=(JSONObject)msg.obj;
				List<CClientEntity> cClientEntities=new ArrayList<CClientEntity>();
				try {
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						JSONArray arrays=json.getJSONArray("ClientSubmitList");
						for(int i=0;i<arrays.length();i++){
							JSONObject clientJS=arrays.getJSONObject(i);
							CClientEntity cClientEntity=new CClientEntity(
									clientJS.getInt("ClientId"),
									clientJS.getString("ClientName"),
									clientJS.getString("ClientCompany"),
									clientJS.getString("ClientPhone"),
									clientJS.getString("ClientArea"), 
									clientJS.getString("ClientAddress"),
									clientJS.getInt("ClientState"),
									clientJS.getInt("ClientSubmitId"),
									clientJS.getInt("ClientSubmitState"),
									clientJS.getString("ClientSubmitTime"));
							
							cClientEntities.add(cClientEntity);
						}
					CClientEntityList cClientEntityList=new CClientEntityList(cClientEntities);
					if(EnterClientSubmitHandler!=null){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("CClientEntityList", cClientEntityList);
						Message mg=new Message();
						mg.obj = map;
						EnterClientSubmitHandler.sendMessage(mg);
						
					}
						
					}
					
				} catch (Exception e) {
					System.out.println("EnterClientSubmit--e:" + e);
					e.printStackTrace();
				}
			}
			
			
		};
		new ClientDao(context).sendtoClientSubmit(handler, js);
	}
	
	//增加客户
	public void AddClient(JSONObject js,final Handler AddClientHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("AddClient接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if (json.getBoolean(MyOpcode.Operation.SIGN)) {
						ToastUtil.show(context, "增加成功");
					}
					
					if(AddClientHandler!=null){
						Message mg=new Message();
						AddClientHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("AddClient--e:" + e);
					e.printStackTrace();
				}
			}
			
			
		};
		new ClientDao(context).senttoAddClient(handler, js);
	}
	
	
	//修改客户
	public void UpdateClient(JSONObject js,final Handler UpdateClientHandler){
		Handler handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("UpdateClient接收");
				JSONObject json=(JSONObject)msg.obj;
				try{
					if(json.getBoolean(MyOpcode.Operation.SIGN)){
						ToastUtil.show(context, "修改成功");
					}
					if(UpdateClientHandler!=null){
						Message mg=new Message();
						UpdateClientHandler.sendMessage(mg);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					System.out.println("UpdateClient--e:" + e);
					e.printStackTrace();
				}
				
			}
			
			
		};
		new ClientDao(context).senttoUpdateClient(handler, js);
	}
	
}
