package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CSuggestEntityList implements Serializable{

	private List<CSuggestEntity> cSuggestEntityList=new ArrayList<CSuggestEntity>();
	
	public CSuggestEntityList(List<CSuggestEntity> cSuggestEntityList){
		this.cSuggestEntityList=cSuggestEntityList;
		
	}
	
	public int getSize(){
		return cSuggestEntityList.size();
	}
	
	public CSuggestEntity getItem(int pos){
		
		return cSuggestEntityList.get(pos);
	}
	
	
}
