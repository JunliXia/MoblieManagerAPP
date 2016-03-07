package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CMissionEntityList implements Serializable{
	private List<CMissionEntity> cMissionEntityList=new ArrayList<CMissionEntity>();
	
	public CMissionEntityList(List<CMissionEntity> cMissionEntityList){
		this.cMissionEntityList=cMissionEntityList;
		
	}
	
	public int getSize(){
		return cMissionEntityList.size();
	}
	
	public CMissionEntity getItem(int pos){
		return cMissionEntityList.get(pos);
	}
}
