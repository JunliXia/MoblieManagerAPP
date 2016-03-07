package com.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.tool.MyOpcode;

public class CAddressEntity implements Serializable{
	private int AddressId;
	private int EmployeeId;
	private String AddressLat;
	private String AddressLong;
	private String AddressName;
	
	public JSONObject toJson(int EmployeeId,String AddressLat,String AddressLong,String AddressName){
		JSONObject js=new JSONObject();
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.SENDADDRESS);
			js.put(MyOpcode.Bussiness.EmployeeId, EmployeeId);
			js.put("AddressLat", AddressLat);
			js.put("AddressLong", AddressLong);
			if(AddressName.equals("")){
				js.put("AddressName", "ÎÞ·¨½âÎö");
			}else{
				js.put("AddressName", AddressName);
			}
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	public int getAddressId() {
		return AddressId;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	public String getAddressLat() {
		return AddressLat;
	}
	public String getAddressLong() {
		return AddressLong;
	}
	public String getAddressName() {
		return AddressName;
	}
	
	
}
