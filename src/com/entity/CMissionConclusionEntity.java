package com.entity;

import java.io.Serializable;

public class CMissionConclusionEntity implements Serializable{

	private int m_iMissionConclusionId; // 任务附件号,主键，自增
	private int m_iMissionCheck; // 任务审核结果(0为默认，1为审核通过，2为审核不通过)
	private String m_sMissionSummary; // 任务总结
	private String m_sMissionSubmitTime; // 任务提交时间
	private String m_sMissionAccessoryPath; // 任务附件路径
	public CMissionConclusionEntity(int m_iMissionConclusionId,
			int m_iMissionCheck, String m_sMissionSummary,
			String m_sMissionSubmitTime, String m_sMissionAccessoryPath) {
		this.m_iMissionConclusionId = m_iMissionConclusionId;
		this.m_iMissionCheck = m_iMissionCheck;
		this.m_sMissionSummary = m_sMissionSummary;
		this.m_sMissionSubmitTime = m_sMissionSubmitTime;
		this.m_sMissionAccessoryPath = m_sMissionAccessoryPath;
	}
	
	
	public CMissionConclusionEntity() {
	}


	public int getM_iMissionConclusionId() {
		return m_iMissionConclusionId;
	}
	public int getM_iMissionCheck() {
		return m_iMissionCheck;
	}
	public String getM_sMissionSummary() {
		return m_sMissionSummary;
	}
	public String getM_sMissionSubmitTime() {
		return m_sMissionSubmitTime;
	}
	public String getM_sMissionAccessoryPath() {
		return m_sMissionAccessoryPath;
	}


}
