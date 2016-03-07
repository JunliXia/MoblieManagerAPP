package com.entity;

import java.io.Serializable;

public class CMissionConclusionEntity implements Serializable{

	private int m_iMissionConclusionId; // ���񸽼���,����������
	private int m_iMissionCheck; // ������˽��(0ΪĬ�ϣ�1Ϊ���ͨ����2Ϊ��˲�ͨ��)
	private String m_sMissionSummary; // �����ܽ�
	private String m_sMissionSubmitTime; // �����ύʱ��
	private String m_sMissionAccessoryPath; // ���񸽼�·��
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
