package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CMissionAccessoryEntityList implements Serializable{
	private List<CMissionAccessoryEntity> cMissionAccessoryEntityList=new ArrayList<CMissionAccessoryEntity>();
	
	public CMissionAccessoryEntityList(List<CMissionAccessoryEntity> cMissionAccessoryEntityList){
		this.cMissionAccessoryEntityList=cMissionAccessoryEntityList;
	}
	
	public int getSize(){
		return cMissionAccessoryEntityList.size();
	}
	
	public CMissionAccessoryEntity getItem(int pos){
		return cMissionAccessoryEntityList.get(pos);
	}
}
