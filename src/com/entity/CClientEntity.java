package com.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.bll.LoginBll;
import com.tool.MyOpcode;

public class CClientEntity implements Serializable{
	private int ClientId;
	private String ClientName;
	private String ClientCompany;
	private String ClientPhone;
	private String ClientArea;
	private String ClientAddress;
	private int ClientState; 
	
	private int ClientSubmitId;
	private int ClientSubmitState;
	private String ClientSubmitTime;
	
	
	


	public CClientEntity(int clientId, String clientName, String clientCompany,
			String clientPhone, String clientArea, String clientAddress,
			int clientState, int clientSubmitId, int clientSubmitState,
			String clientSubmitTime) {
		ClientId = clientId;
		ClientName = clientName;
		ClientCompany = clientCompany;
		ClientPhone = clientPhone;
		ClientArea = clientArea;
		ClientAddress = clientAddress;
		ClientState = clientState;
		ClientSubmitId = clientSubmitId;
		ClientSubmitState = clientSubmitState;
		ClientSubmitTime = clientSubmitTime;
	}

	public CClientEntity(){}
	
	public CClientEntity(String ClientName,String ClientCompany,String ClientPhone,String ClientArea,String ClientAddress,int ClientId){
		this.ClientName=ClientName;
		this.ClientCompany=ClientCompany;
		this.ClientPhone=ClientPhone;
		this.ClientArea=ClientArea;
		this.ClientAddress=ClientAddress;
		this.ClientId=ClientId;
	}
	
	public JSONObject toJson(int ClientId,String ClientName, String ClientCompany, String ClientPhone,String ClientArea,String ClientAddress,int operation){
		JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Client.ClientId, ClientId);
			js.put(MyOpcode.Client.ClientName, ClientName);
			js.put(MyOpcode.Client.ClientCompany, ClientCompany);
			js.put(MyOpcode.Client.ClientPhone, ClientPhone);
			js.put(MyOpcode.Client.ClientArea, ClientArea);
			js.put(MyOpcode.Client.ClientAddress, ClientAddress);
			js.put(MyOpcode.Operation.OPERATION, operation);
		}catch(JSONException e){
			e.printStackTrace();
			
		}
		return js;
		
	}
	public JSONObject toJson(String ClientName, String ClientCompany, String ClientPhone,String ClientArea,String ClientAddress,int operation){
		JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Employee.EmployeeId, LoginBll.getCuruser().getEmployeeId());
			js.put(MyOpcode.Client.ClientName, ClientName);
			js.put(MyOpcode.Client.ClientCompany, ClientCompany);
			js.put(MyOpcode.Client.ClientPhone, ClientPhone);
			js.put(MyOpcode.Client.ClientArea, ClientArea);
			js.put(MyOpcode.Client.ClientAddress, ClientAddress);
			js.put(MyOpcode.Operation.OPERATION, operation);
		}catch(JSONException e){
			e.printStackTrace();
			
		}
		return js; 
		
	}
	public CClientEntity(int ClientId, String ClientName,String ClientCompany,String ClientPhone,String ClientArea, String ClientAddress,int ClientState){
		this.ClientId=ClientId;
		this.ClientName=ClientName;
		this.ClientCompany=ClientCompany;
		this.ClientPhone=ClientPhone;
		this.ClientArea=ClientArea;
		this.ClientAddress=ClientAddress;
		this.ClientState=ClientState;
	}
	
	public int getClientId() {
		return ClientId;
	}
	public String getClientName() {
		return ClientName;
	}
	public String getClientCompany() {
		return ClientCompany;
	}
	public String getClientPhone() {
		return ClientPhone;
	}
	public String getClientArea() {
		return ClientArea;
	}
	public String getClientAddress() {
		return ClientAddress;
	}
	public int getClientState() {
		return ClientState;
	}
	
	public int getClientSubmitId() {
		return ClientSubmitId;
	}

	public int getClientSubmitState() {
		return ClientSubmitState;
	}

	public String getClientSubmitTime() {
		return ClientSubmitTime;
	}
	@Override
	public String toString() {
		return "CClientEntity [ClientId=" + ClientId + ", ClientName="
				+ ClientName + ", ClientCompany=" + ClientCompany
				+ ", ClientPhone=" + ClientPhone + ", ClientArea=" + ClientArea
				+ ", ClientAddress=" + ClientAddress + ", ClientType="
				+ ClientState + "]";
	}
	
	
	
}
