package com.entity;

import java.io.Serializable;

public class CVisitConclusionEntity implements Serializable{
	private int VisitConclusionId; // �ݷ��ܽ�ã�����������
	private int VisitCheck; // �ݷ���˽��(0ΪĬ�ϣ�1Ϊ���ͨ����2Ϊ��˲�ͨ��)
	private String VisitSubmitTime; // �ݷ��ύʱ��
	private String VisitSummary; // �ݷ��ܽ�
	private String VisitCommand; // �ͻ�����
	private String VisitAccessoryPath; // �ͻ�����·��
	
	
	
	
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
