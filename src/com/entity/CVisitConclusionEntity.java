package com.entity;

import java.io.Serializable;

public class CVisitConclusionEntity implements Serializable{
	private int VisitConclusionId; // 拜访总结好，主键，自增
	private int VisitCheck; // 拜访审核结果(0为默认，1为审核通过，2为审核不通过)
	private String VisitSubmitTime; // 拜访提交时间
	private String VisitSummary; // 拜访总结
	private String VisitCommand; // 客户需求
	private String VisitAccessoryPath; // 客户附件路径
	
	
	
	
	public CVisitConclusionEntity() {
	}



	public CVisitConclusionEntity(int visitConclusionId, int visitCheck,
			String visitSubmitTime, String visitSummary, String visitCommand,
			String visitAccessoryPath) {
		VisitConclusionId = visitConclusionId;
		VisitCheck = visitCheck;
		VisitSubmitTime = visitSubmitTime;
		VisitSummary = visitSummary;
		VisitCommand = visitCommand;
		VisitAccessoryPath = visitAccessoryPath;
	}



	public int getVisitConclusionId() {
		return VisitConclusionId;
	}



	public int getVisitCheck() {
		return VisitCheck;
	}



	public String getVisitSubmitTime() {
		return VisitSubmitTime;
	}



	public String getVisitSummary() {
		return VisitSummary;
	}



	public String getVisitCommand() {
		return VisitCommand;
	}



	public String getVisitAccessoryPath() {
		return VisitAccessoryPath;
	}



	@Override
	public String toString() {
		return "CVisitConclusionEntity [VisitConclusionId=" + VisitConclusionId
				+ ", VisitCheck=" + VisitCheck + ", VisitSubmitTime="
				+ VisitSubmitTime + ", VisitSummary=" + VisitSummary
				+ ", VisitCommand=" + VisitCommand + ", VisitAccessoryPath="
				+ VisitAccessoryPath + "]";
	}
	
	
	
}
