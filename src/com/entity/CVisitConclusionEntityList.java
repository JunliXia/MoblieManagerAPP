package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CVisitConclusionEntityList implements Serializable{
	private List<CVisitConclusionEntity> conclusionEntities=new ArrayList<CVisitConclusionEntity>();
	
	public CVisitConclusionEntityList(List<CVisitConclusionEntity> conclusionEntities){
		this.conclusionEntities=conclusionEntities;
	}
	
	public int getSize(){
		return conclusionEntities.size();
	}
	
	public CVisitConclusionEntity getItem(int index){
		return conclusionEntities.get(index);
	}
	
}
