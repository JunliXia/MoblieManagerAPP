package com.tool;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {    //Toast��������ʾ��Ϣ��һ�ֻ��ƣ���ʾ��ʱ�����ޣ���һ����ʱ��ͻ��Զ���ʧ
	public static void show(Context context, String str){
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
}
