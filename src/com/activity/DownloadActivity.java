package com.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import com.example.mobliemanager.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.widget.ImageView;

public class DownloadActivity extends Activity {
	private ImageView image = null;
	public static String path = "http://10.20.4.96:8080/MobileManagerServer/DownloadServlet";
	private String result = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		image = (ImageView) findViewById(R.id.image);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		showImage();
	}

	public void showImage() {
		BasicHttpParams httpParams = new BasicHttpParams();// 设置超时
		HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);
		HttpPost post = new HttpPost(path);
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
				System.out.println(result);
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
}
