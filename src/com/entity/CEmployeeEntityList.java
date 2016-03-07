package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CEmployeeEntityList implements Serializable{
	private List<CEmployeeEntity> cEmployeeEntityList=new ArrayList<CEmployeeEntity>();
	
	public CEmployeeEntityList(List<CEmployeeEntity> cEmployeeEntityList) {
		this.cEmployeeEntityList=cEmployeeEntityList;
	}
	
	public int getSize(){
		return cEmployeeEntityList.size();
	}
	
	public CEmployeeEntity getItem(int pos){
		return cEmployeeEntityList.get(pos);
	}
}
