package com.entity;

import java.io.Serializable;

public class CNoticeEntity implements Serializable {
	private int NoticeId;
	private String NoticeTime;
	private String NoticeTitle;
	private String NoticeContent;
	
	
	public CNoticeEntity(){
		
	}
	
	public CNoticeEntity(int NoticeId,String NoticeTime,String NoticeTitle,String NoticeContent){
		this.NoticeId=NoticeId;
		this.NoticeTime=NoticeTime;
		this.NoticeTitle=NoticeTitle;
		this.NoticeContent=NoticeContent;
	}
	
	public int getNoticeId() {
		return NoticeId;
	}
	public void setNoticeId(int noticeId) {
		NoticeId = noticeId;
	}
	public String getNoticeTime() {
		return NoticeTime;
	}
	public void setNoticeTime(String noticeTime) {
		NoticeTime = noticeTime;
	}
	public String getNoticeTitle() {
		return NoticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		NoticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return NoticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		NoticeContent = noticeContent;
	}
	
	

	@Override
	public String toString() {
		return "CNoticeEntity [NoticeId=" + NoticeId + ", NoticeTime="
				+ NoticeTime + ", NoticeTitle=" + NoticeTitle
				+ ", NoticeContent=" + NoticeContent + "]";
	}
}
