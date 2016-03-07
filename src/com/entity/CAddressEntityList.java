package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CAddressEntityList implements Serializable{
	private List<CAddressEntity> cAddressEntityList=new ArrayList<CAddressEntity>();
	public CAddressEntityList(List<CAddressEntity> cAddressEntityList){
		this.cAddressEntityList=cAddressEntityList;
	}
	
	public int getSize(){
		return cAddressEntityList.size();
	}
	
	public CAddressEntity getItem(int pos){
		return cAddressEntityList.get(pos);
	}
}
