package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CAttendanceEntityList implements Serializable{
	
	private List<CAttendanceEntity> cAttendanceEntityList=new ArrayList<CAttendanceEntity>();
	
	public CAttendanceEntityList(List<CAttendanceEntity> cAttendanceEntityList){
		this.cAttendanceEntityList=cAttendanceEntityList;
	}
	
	public int getSize(){
		return cAttendanceEntityList.size();
		
	}
	public CAttendanceEntity getItem(int pos){
		return cAttendanceEntityList.get(pos);
	}
}
