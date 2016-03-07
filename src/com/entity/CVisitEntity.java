package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.bll.LoginBll;
import com.tool.MyOpcode;

public class CVisitEntity implements Serializable{

	private int VisitPlanId;
	private String VisitPlanPubdate;
	private String VisitPlanStartTime;
	private String VisitPlanEndTime;
	private int VisitPlanState;
	
	private String VisitSummary;
	private String VisitCommand;
	private String VisitSubmitTime;
	
	
	private int ClientId;
	private String ClientName;
	private String ClientCompany;
	private String ClientPhone; // 客户号码
	private String ClientArea; // 客户区域
	private String ClientAddress;// 客户地址
	private int ClientState; // 客户状态(0未分配，1已分配，2未审核，3已删除(指撤销，不在数据库中删除记录))


	private List<CVisitConclusionEntity> conclusionEntities=new ArrayList<CVisitConclusionEntity>();
	

	public List<CVisitConclusionEntity> getConclusionEntities() {
		return conclusionEntities;
	}


	public void setConclusionEntities(
			List<CVisitConclusionEntity> conclusionEntities) {
		this.conclusionEntities = conclusionEntities;
	}


	//无summary版
	public CVisitEntity(int visitPlanId, String visitPlanPubdate,
			String visitPlanStartTime, String visitPlanEndTime,
			int visitPlanState, int clientId, String clientName,
			String clientCompany, String clientPhone, String clientArea,
			String clientAddress, int clientState) {
		VisitPlanId = visitPlanId;
		VisitPlanPubdate = visitPlanPubdate;
		VisitPlanStartTime = visitPlanStartTime;
		VisitPlanEndTime = visitPlanEndTime;
		VisitPlanState = visitPlanState;

		
		ClientId = clientId;
		ClientName = clientName;
		ClientCompany = clientCompany;
		ClientPhone = clientPhone;
		ClientArea = clientArea;
		ClientAddress = clientAddress;
		ClientState = clientState;
	}


	//提交拜访
	public JSONObject toVisitConclusionJSON(String VisitSubmitTime,String VisitSummary, String VisitCommand,String VisitAccessoryPath,int VisitPlanId){
		JSONObject js=new JSONObject();
		try {
			js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.SUBMITVISITCONCLUSION);
			js.put(MyOpcode.Employee.EmployeeId, LoginBll.getCuruser().getEmployeeId());
			js.put("VisitSubmitTime",VisitSubmitTime);
			js.put(MyOpcode.Visit.VisitCommand, VisitCommand);
			js.put(MyOpcode.Visit.VisitSummary, VisitSummary);
			js.put("VisitAccessoryPath", VisitAccessoryPath);
			js.put("VisitPlanId", VisitPlanId);
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	
	public JSONObject toVisitJSon(int operation){
		JSONObject js=new JSONObject();
		try {
			js.put(MyOpcode.Operation.OPERATION, operation);
			js.put(MyOpcode.Employee.EmployeeId, LoginBll.getCuruser().getEmployeeId());
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return js;
	}
	
	public CVisitEntity(){}


	public int getVisitPlanId() {
		return VisitPlanId;
	}


	public String getVisitPlanPubdate() {
		return VisitPlanPubdate;
	}


	public String getVisitPlanStartTime() {
		return VisitPlanStartTime;
	}


	public String getVisitPlanEndTime() {
		return VisitPlanEndTime;
	}


	public int getVisitPlanState() {
		return VisitPlanState;
	}


	public String getVisitSummary() {
		return VisitSummary;
	}


	public String getVisitCommand() {
		return VisitCommand;
	}


	public String getVisitSubmitTime() {
		return VisitSubmitTime;
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


	@Override
	public String toString() {
		return "CVisitEntity [VisitPlanId=" + VisitPlanId
				+ ", VisitPlanPubdate=" + VisitPlanPubdate
				+ ", VisitPlanStartTime=" + VisitPlanStartTime
				+ ", VisitPlanEndTime=" + VisitPlanEndTime
				+ ", VisitPlanState=" + VisitPlanState + ", VisitSummary="
				+ VisitSummary + ", VisitCommand=" + VisitCommand
				+ ", VisitSubmitTime=" + VisitSubmitTime + ", ClientId="
				+ ClientId + ", ClientName=" + ClientName + ", ClientCompany="
				+ ClientCompany + ", ClientPhone=" + ClientPhone
				+ ", ClientArea=" + ClientArea + ", ClientAddress="
				+ ClientAddress + ", ClientState=" + ClientState
				+ ", conclusionEntities=" + conclusionEntities + "]";
	}

	
	
}
