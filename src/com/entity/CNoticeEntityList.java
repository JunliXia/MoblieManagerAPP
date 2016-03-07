package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CNoticeEntityList implements Serializable{
	
	private List<CNoticeEntity> cNoticeEntityList=new ArrayList<CNoticeEntity>();
	
	public CNoticeEntityList(List<CNoticeEntity>cNoticeEntityList){
		this.cNoticeEntityList=cNoticeEntityList;
	}
	public int getSize(){
		return cNoticeEntityList.size();
	}
	public CNoticeEntity getItem(int pos){
		return cNoticeEntityList.get(pos);
	}

		
}
