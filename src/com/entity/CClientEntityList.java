package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CClientEntityList implements Serializable{
	private List<CClientEntity> cClientEntityList=new ArrayList<CClientEntity>();
	
	public CClientEntityList(List<CClientEntity> cClientEntityList){
		this.cClientEntityList=cClientEntityList;
		
	}
	
	public int getSize(){
		return cClientEntityList.size();
	}
	
	public CClientEntity getItem(int pos){
		return cClientEntityList.get(pos);
	}
}
