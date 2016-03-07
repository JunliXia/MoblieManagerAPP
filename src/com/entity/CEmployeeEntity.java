package com.entity;

import java.io.Serializable;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.JSONObject;

import com.bll.LoginBll;
import com.tool.MyOpcode;

public class CEmployeeEntity implements Serializable{


	private int EmployeeId;
	private String EmployeeAccount;
	private String EmployeePassword;
	private String EmployeeName;
	private String EmployeePhone;
	private String EmployeeSex;
	private String EmployeeDepartment;
	private String EmployeeJob;
	private int EmployeeType;
	
	
	public CEmployeeEntity(){}
	
	public CEmployeeEntity(int EmployeeId, String EmployeeAccount,String EmployeePassword,String EmployeeName,String EmployeePhone,String EmployeeSex,String EmployeeDepartment,String EmployeeJob,int EmployeeType){
		this.EmployeeId=EmployeeId;
		this.EmployeeAccount=EmployeeAccount;
		this.EmployeePassword=EmployeePassword;
		this.EmployeeName=EmployeeName;
		this.EmployeePhone=EmployeePhone;
		this.EmployeeSex=EmployeeSex;
		this.EmployeeDepartment=EmployeeDepartment;
		this.EmployeeJob=EmployeeJob;
		this.EmployeeType=EmployeeType;
	}
	
	public CEmployeeEntity(int EmployeeId,String EmployeePassword){
		this.EmployeeId=EmployeeId;
		this.EmployeePassword=EmployeePassword;
	}
	
	//µÇÂ½
	public JSONObject toJSon(String EmployeeAccount,String EmployeePassword,int operation){
		JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Operation.OPERATION, operation);
			js.put(MyOpcode.Employee.EmployeeAccount,EmployeeAccount);
			js.put(MyOpcode.Employee.EmployeePassword,EmployeePassword);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	
	//½øÈë
	public JSONObject toJSon(int operation){
		JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Operation.OPERATION, operation);
			js.put(MyOpcode.Employee.EmployeeId, LoginBll.getCuruser().getEmployeeId());
			js.put(MyOpcode.Employee.EmployeePassword, LoginBll.getCuruser().getEmployeePassword());
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}


	public String getEmployeeAccount() {
		return EmployeeAccount;
	}

	public int getEmployeeId() {
		return EmployeeId;
	}
	public String getEmployeePassword() {
		return EmployeePassword;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public String getEmployeePhone() {
		return EmployeePhone;
	}
	public String getEmployeeSex() {
		return EmployeeSex;
	}
	public String getEmployeeDepartment() {
		return EmployeeDepartment;
	}
	public String getEmployeeJob() {
		return EmployeeJob;
	}
	public int getEmployeeType() {
		return EmployeeType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}
