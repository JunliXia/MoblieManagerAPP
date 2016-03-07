package com.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.bll.LoginBll;
import com.tool.MyOpcode;

public class CSuggestEntity implements Serializable{

	private int SuggestId;
	private String SuggestContent;
	private String SuggestTime;
	private int  EmployeeId;
	
	
	public CSuggestEntity(){
		
	}
	
	
	public JSONObject toJSon(String SuggestContent, String SuggestTime){
		JSONObject js=new JSONObject();
		try {
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.SEND_SUGGEST);
			js.put(MyOpcode.Suggest.SuggestContent, SuggestContent);
			js.put(MyOpcode.Suggest.SuggestTime, SuggestTime);
			js.put(MyOpcode.Employee.EmployeeId, LoginBll.getCuruser().getEmployeeId());
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return js;
	}
	
	public int getSuggestId() {
		return SuggestId;
	}
	public String getSuggestContent() {
		return SuggestContent;
	}
	public String getSuggestTime() {
		return SuggestTime;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	
	@Override
	public String toString() {
		return "SuggestEntity [SuggestId=" + SuggestId + ", SuggestContent="
				+ SuggestContent + ", SuggestTime=" + SuggestTime
				+ ", EmployeeId=" + EmployeeId + "]";
	}

	
}
