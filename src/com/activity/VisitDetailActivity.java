package com.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.CVisitConclusionEntity;
import com.entity.CVisitEntity;
import com.example.mobliemanager.R;
import com.tool.MyURL;

public class VisitDetailActivity extends Activity {

	private ImageView imageviewBack;
	private TextView textclientname, textclientcompany, textRemand,
			textContentSum;
//	private CVisitEntity cVisitEntity;
	private CVisitConclusionEntity conclusionEntity;
	public static String path = MyURL.HIP+"/DownloadServlet";
	private String result = "";
	private ImageView image;

	private String clientname;
	private String clientcompany;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.visitdetail);
		image = (ImageView) findViewById(R.id.imagePhoto);
		conclusionEntity = (CVisitConclusionEntity) this.getIntent().getExtras()
				.getSerializable("CVisitConclusionEntity");
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		showImage();
		init();
		setText();
		setListener();

	}

	private String stringpath(String strtime,int ClientId){
		String time1 = strtime.substring(0, 4);
		String time2 = strtime.substring(5, 7);
		String time3 = strtime.substring(8, 10);
		String time4 = strtime.substring(11, 13);
		String result="F:/ImagesUploaded/"+time1+time2+time3+time4+"ClientId"+ClientId+".jpg";
		System.out.println(result);
		return result;
	}
	
	public void showImage() {
		String parm=conclusionEntity.getVisitAccessoryPath();
		BasicHttpParams httpParams = new BasicHttpParams();// 设置超时
		HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);
		HttpPost post = new HttpPost(path+"?AccessoryPath="+parm);
		try {
			// 使用response接收servlet返回的数据
			HttpResponse response = (HttpResponse) new DefaultHttpClient()
					.execute(post);
			// 判断servlet是否返回成功
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						entity.getContent(), "UTF-8"));
				String line = "";
				while ((line = br.readLine()) != null) {
					result = result + line;
				}
				if (!result.equals("")) {
					Bitmap bitmap = DownloadActivity.getBitmapFromByte(result);
					
					image.setImageBitmap(bitmap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = "";
	}

	public static Bitmap getBitmapFromByte(String str) {
		if (str == null) {
			return null;
		}
		Bitmap bitmap = null;
		byte[] bytes = Base64.decode(str, Base64.DEFAULT);
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return bitmap;
	}
	private void setText() {
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		clientname=bundle.getString("ClientName");
		clientcompany=bundle.getString("ClientCompany");
		textclientname.setText(clientname);
		textclientcompany.setText(clientcompany);
		textRemand.setText(conclusionEntity.getVisitCommand());
		textContentSum.setText(conclusionEntity.getVisitSummary());
	}

	private void init() {
		imageviewBack = (ImageView) findViewById(R.id.imageviewBack);
		textclientname = (TextView) findViewById(R.id.textclientname);
		textclientcompany = (TextView) findViewById(R.id.textclientcompany);
		textRemand = (TextView) findViewById(R.id.textRemand);
		textContentSum = (TextView) findViewById(R.id.textContentSum);
	}

	private void setListener() {
		imageviewBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
