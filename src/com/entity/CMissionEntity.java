package com.entity;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.bll.LoginBll;
import com.tool.MyOpcode;

public class CMissionEntity implements Serializable{
	private int MissionId;
	private String MissionPubdate;
	private String MissionContent;
	private String MissionDeadLine;
	private int MissionState;
	private int MissionDelayState;
	private int MissionBussinessBandState;
	
	
	
	
	public CMissionEntity(){}

	
	public CMissionEntity(int missionId, String missionPubdate,
			String missionContent, String missionDeadLine, int missionState,
			int missionDelayState, int missionBussinessBandState) {
		MissionId = missionId;
		MissionPubdate = missionPubdate;
		MissionContent = missionContent;
		MissionDeadLine = missionDeadLine;
		MissionState = missionState;
		MissionDelayState = missionDelayState;
		MissionBussinessBandState = missionBussinessBandState;
	}


	//进入任务
	public JSONObject toJson(int operation){
		JSONObject js=new JSONObject();
		try{
			js.put(MyOpcode.Operation.OPERATION,operation);
			js.put(MyOpcode.Employee.EmployeeId, LoginBll.getCuruser().getEmployeeId());
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
	}
	
	//接受任务
	public JSONObject getMissionJson(int MissionId){
		JSONObject js=new JSONObject();
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.TAKE_MISSION);
			js.put(MyOpcode.Mission.MissionId, MissionId);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
	}
	//获得任务总结
	public JSONObject getMissionConclusionJson(int MissionId){
		JSONObject js=new JSONObject();
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.GET_MISSIONCONCLUSION);
			js.put(MyOpcode.Mission.MissionId, MissionId);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
	}
	
	
	//提交任务
	public JSONObject sendMissionJS(int MissionId,String MissionSummary,String MissionSubmitTime,String MissionAccessoryPath){
		JSONObject js=new JSONObject();
		try{
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.SENT_MISSION);
			js.put(MyOpcode.Mission.MissionId, MissionId);
			js.put(MyOpcode.Mission.MissionSummary, MissionSummary);
			js.put(MyOpcode.Mission.MissionSubmitTime, MissionSubmitTime);
			js.put(MyOpcode.Mission.MissionAccessoryPath,MissionAccessoryPath);
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js;
		
	}
	
	public int getMissionId() {
		return MissionId;
	}
	public String getMissionPubdate() {
		return MissionPubdate;
	}
	public String getMissionContent() {
		return MissionContent;
	}
	public String getMissionDeadLine() {
		return MissionDeadLine;
	}
	public int getMissionState() {
		return MissionState;
	}

	public int getMissionDelayState() {
		return MissionDelayState;
	}
	public int getMissionBussinessBandState() {
		return MissionBussinessBandState;
	}


	@Override
	public String toString() {
		return "CMissionEntity [MissionId=" + MissionId + ", MissionPubdate="
				+ MissionPubdate + ", MissionContent=" + MissionContent
				+ ", MissionDeadLine=" + MissionDeadLine + ", MissionState="
				+ MissionState + ", MissionDelayState=" + MissionDelayState
				+ ", MissionBussinessBandState=" + MissionBussinessBandState
				+ "]";
	}

	
}
