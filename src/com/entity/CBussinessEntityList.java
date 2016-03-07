package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CBussinessEntityList implements Serializable {

	private List<CBussinessEntity> cBussinessEntityList=new ArrayList<CBussinessEntity>();
	
	public CBussinessEntityList(List<CBussinessEntity> cBussinessEntityList){
		this.cBussinessEntityList=cBussinessEntityList;
	}
	public int getSize(){
		return cBussinessEntityList.size();
	}
	
	public CBussinessEntity getItem(int pos){
		return cBussinessEntityList.get(pos);
		
	}
	
}
