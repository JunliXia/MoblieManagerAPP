package com.http;

import java.io.File;

import com.tool.UploadUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 
 * 
 */
public class UploadFileTask extends AsyncTask<String, Void, String>{
	public static final String requestURL="http://10.20.4.96:8080/MobileManagerServer/FileUploadServlet";
   /**
    *  �ɱ䳤�������������AsyncTask.exucute()��Ӧ
    */
   private  ProgressDialog pdialog;
   private  Activity context=null;
    public UploadFileTask(Activity ctx){
    	this.context=ctx;
    	pdialog=ProgressDialog.show(context, "���ڼ���...", "ϵͳ���ڴ�����������");  
    }
    @Override
    protected void onPostExecute(String result) {
        // ����HTMLҳ�������
        pdialog.dismiss(); 
        if(UploadUtils.SUCCESS.equalsIgnoreCase(result)){
        	Toast.makeText(context, "�ϴ��ɹ�!",Toast.LENGTH_LONG ).show();
        }else{
        	Toast.makeText(context, "�ϴ�ʧ��!",Toast.LENGTH_LONG ).show();
        }
    }

	  @Override
	  protected void onPreExecute() {
	  }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

		@Override
		protected String doInBackground(String... params) {
			File file=new File(params[0]);
			return UploadUtils.uploadFile( file, requestURL);
		}
	       @Override
	       protected void onProgressUpdate(Void... values) {
	       }

	
}