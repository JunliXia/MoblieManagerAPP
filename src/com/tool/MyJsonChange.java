package com.tool;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;

public class MyJsonChange {
	public static String getChange(JSONObject js) {

		Iterator it = js.keys();
		
		String str_result="";
		try {
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = js.getString(key);
				System.out.println(key+"   "+value);
				str_result+=key+"="+value+"&";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return 	str_result.substring(0,str_result.length()-1);
	}

}
