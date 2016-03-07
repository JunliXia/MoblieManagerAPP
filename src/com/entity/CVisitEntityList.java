package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CVisitEntityList implements Serializable{
	private List<CVisitEntity> cVisitEntityList=new ArrayList<CVisitEntity>();
	
	public CVisitEntityList(List<CVisitEntity> cVisitEntityList){
		this.cVisitEntityList=cVisitEntityList;
		
	}
	
	public int getSize(){
		return cVisitEntityList.size();
		
	}
	
	public CVisitEntity getItem(int pos){
		return cVisitEntityList.get(pos);
	}
}
