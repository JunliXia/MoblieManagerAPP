package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CCientAccessoryEntityList implements Serializable{
	private List<CCientAccessoryEntity> cCientAccessoryEntityList=new ArrayList<CCientAccessoryEntity>();
	
	
	public CCientAccessoryEntityList(List<CCientAccessoryEntity> cCientAccessoryEntityList){
		this.cCientAccessoryEntityList=cCientAccessoryEntityList;
	}
	
	public int getSize(){
		return cCientAccessoryEntityList.size();
	}
	
	public CCientAccessoryEntity getItem(int pos){
		return cCientAccessoryEntityList.get(pos);
	}
}
