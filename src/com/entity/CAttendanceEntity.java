package com.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.tool.MyOpcode;

public class CAttendanceEntity implements Serializable{
	
	private int AttendanceId;
	private String AttendanceRegisterTime;
	private String AttendanceSignoutTime;
	private int AttendanceType;
	
	
	//ǩ��
	public JSONObject toRegisterJSon(int EmployeeId,int operation,String AttendanceTime){
		JSONObject js=new JSONObject();
		try {
		js.put(MyOpcode.Operation.OPERATION, operation);
		js.put(MyOpcode.Employee.EmployeeId, EmployeeId);
		js.put(MyOpcode.Attendance.AttendanceRegisterTime, AttendanceTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
	}
	//ǩ��
	public JSONObject toSignoutJSon(int EmployeeId,int operation,String AttendanceTime){
		JSONObject js=new JSONObject();
		try {
		js.put(MyOpcode.Operation.OPERATION, operation);
		js.put(MyOpcode.Employee.EmployeeId, EmployeeId);
		js.put(MyOpcode.Attendance.AttendanceSignoutTime, AttendanceTime);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
	}
	public CAttendanceEntity(){}
	
	public CAttendanceEntity(int AttendanceId,String AttendanceRegisterTime,String AttendanceSignoutTime,int AttendanceType){
		this.AttendanceId=AttendanceId;
		this.AttendanceRegisterTime=AttendanceRegisterTime;
		this.AttendanceSignoutTime=AttendanceSignoutTime;
		this.AttendanceType=AttendanceType;
	}
	
	//���뿼�ڵõ���ֵ
	public CAttendanceEntity(String AttendanceRegisterTime,String AttendanceSignoutTime){
		this.AttendanceRegisterTime=AttendanceRegisterTime;
		this.AttendanceSignoutTime=AttendanceSignoutTime;
	}
	
//	//���뿼����Ҫ��json
//	public JSONObject toJson(String EmployeeId)

	
	public int getAttendanceId() {
		return AttendanceId;
	}
	public String getAttendanceRegisterTime() {
		return AttendanceRegisterTime;
	}
	public String getAttendanceSignoutTime() {
		return AttendanceSignoutTime;
	}
	public int getAttendanceType() {
		return AttendanceType;
	}
	
	@Override
	public String toString() {
		return "CAttendanceEntity [AttendanceId=" + AttendanceId
				+ ", AttendanceRegisterTime=" + AttendanceRegisterTime
				+ ", AttendanceSignoutTime=" + AttendanceSignoutTime
				+ ", AttendanceType=" + AttendanceType + "]";
	}
}
