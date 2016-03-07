package com.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.tool.MyOpcode;

public class CBussinessEntity implements Serializable{

	private int BussinessId;
	private String BussinessSideAddress;
	private String BussinessContent;
	private String BussinessRegisterTime;
	private String BussinessInAddress;
	private String BussinessInTime;
	private String BussinessOutAddress;
	private String BussinessOutTime;
	private String BussinessReturnTime;
	private int BussinessState;
	public CBussinessEntity(){}
	
	
	
	public int getBussinessState() {
		return BussinessState;
	}






	public JSONObject toJson(int BussinessId,String BussinessTime,String BussinessAddress,int MyOperation,int operation){
		JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Operation.OPERATION, operation);
			js.put(MyOpcode.Bussiness.BussinessId, BussinessId);
			js.put("BussinessTime", BussinessTime);
			js.put("BussinessAddress", BussinessAddress);
			js.put("MyOperation", MyOperation);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	public JSONObject tobussinessactivityJson(int BussinessId){
JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.GETBUSSINESSACTIVITY);
			js.put(MyOpcode.Bussiness.BussinessId, BussinessId);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	
	//出差登记
	public JSONObject toRegisterJson(int BussinessId,String BussinessTime){
	JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.SEND_BUSSINESS_REGISTER);
			js.put(MyOpcode.Bussiness.BussinessId, BussinessId);
			js.put("BussinessRegisterTime", BussinessTime);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	//抵达目的地登记 /离开目的地登记
	public JSONObject toSendJson(int BussinessId,String Address,String Time,int operation){
	JSONObject js=new JSONObject();
		try{
			if(operation==MyOpcode.Operation.SEND_BUSSINESS_IN){
				js.put(MyOpcode.Operation.OPERATION, operation);
				js.put(MyOpcode.Bussiness.BussinessId, BussinessId);
				js.put(MyOpcode.Bussiness.BussinessInAddress, Address);
				js.put(MyOpcode.Bussiness.BussinessInTime, Time);
			}else if(operation==MyOpcode.Operation.SEND_BUSSINESS_OUT){
				js.put(MyOpcode.Operation.OPERATION, operation);
				js.put(MyOpcode.Bussiness.BussinessId, BussinessId);
				js.put(MyOpcode.Bussiness.BussinessOutAddress, Address);
				js.put(MyOpcode.Bussiness.BussinessOutTime, Time);
			}
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	//出差归来登记
	public JSONObject toReturnJson(int BussinessId,String BussinessTime){
	JSONObject js=new JSONObject();
		
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.SEND_BUSSINESS_RETURN);
			js.put(MyOpcode.Bussiness.BussinessId, BussinessId);
			js.put("BussinessReturnTime", BussinessTime);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	public CBussinessEntity(int BussinessId,String BussinessSideAddress,String BussinessContent,String BussinessRegisterTime,String BussinessInAddress,String BussinessInTime,String BussinessOutAddress,String BussinessOutTime,String BussinessReturnTime,int BussinessState){
		this.BussinessId=BussinessId;
		this.BussinessSideAddress=BussinessSideAddress;
		this.BussinessContent=BussinessContent;
		this.BussinessRegisterTime=BussinessRegisterTime;
		this.BussinessInAddress=BussinessInAddress;
		this.BussinessInTime=BussinessInTime;
		this.BussinessOutAddress=BussinessOutAddress;
		this.BussinessOutTime=BussinessOutTime;
		this.BussinessReturnTime=BussinessReturnTime;
		this.BussinessState=BussinessState;
	}
	
	public int getBussinessId() {
		return BussinessId;
	}
	public String getBussinessSideAddress() {
		return BussinessSideAddress;
	}
	public String getBussinessContent() {
		return BussinessContent;
	}
	public String getBussinessRegisterTime() {
		return BussinessRegisterTime;
	}
	public String getBussinessInAddress() {
		return BussinessInAddress;
	}
	public String getBussinessInTime() {
		return BussinessInTime;
	}
	public String getBussinessOutAddress() {
		return BussinessOutAddress;
	}
	public String getBussinessOutTime() {
		return BussinessOutTime;
	}
	public String getBussinessReturnTime() {
		return BussinessReturnTime;
	}
	

	@Override
	public String toString() {
		return "CBussinessEntity [BussinessId=" + BussinessId
				+ ", BussinessSideAddress=" + BussinessSideAddress
				+ ", BussinessContent=" + BussinessContent
				+ ", BussinessRegisterTime=" + BussinessRegisterTime
				+ ", BussinessInAddress=" + BussinessInAddress
				+ ", BussinessInTime=" + BussinessInTime
				+ ", BussinessOutAddress=" + BussinessOutAddress
				+ ", BussinessOutTime=" + BussinessOutTime
				+ ", BussinessReturnTime=" + BussinessReturnTime + "]";
	}
}
