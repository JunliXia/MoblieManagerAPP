package com.tool;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {    //Toast是用来显示信息的一种机制，显示的时间有限，过一定的时间就会自动消失
	public static void show(Context context, String str){
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
}
