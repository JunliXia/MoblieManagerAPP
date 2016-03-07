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
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.CMissionConclusionEntity;
import com.entity.CMissionEntity;
import com.example.mobliemanager.R;
import com.tool.MyConstant;

public class CompleteMissionDetailActivity extends Activity {

	private ImageView imageBack;
//	private CMissionEntity cMissionEntity;
	private CMissionConclusionEntity cMissionConclusionEntity;
	private TextView textMissionState, textTimeData, textContent,
			textContentSum;

	public static String path = "http://10.20.4.96:8080/MobileManagerServer/DownloadServlet";
	private String result = "";
	private ImageView image;
	
	private String missioncontent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.completemission_detail);
//		cMissionEntity = (CMissionEntity) this.getIntent().getExtras()
//				.getSerializable("CMissionEntity");
		cMissionConclusionEntity=(CMissionConclusionEntity)this.getIntent().getExtras().getSerializable("CMissionConclusionEntity");
		
		image = (ImageView) findViewById(R.id.imagePhoto);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
		showImage();
		init();
		getkey();
		setText();
		setListener();

	}
	
	
	private void getkey(){
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		missioncontent=bundle.getString("missioncontent");
//		viewPager.setCurrentItem(item);
}
	private String stringpath(String strtime,int Missionid){
		String time1 = strtime.substring(0, 4);
		String time2 = strtime.substring(5, 7);
		String time3 = strtime.substring(8, 10);
		String time4 = strtime.substring(11, 13);
		String result="F:/ImagesUploaded/"+time1+time2+time3+time4+"MissionId"+Missionid+".jpg";
		
		System.out.println(result);
		return result;
	}
	
	public void showImage() {
		String parm=cMissionConclusionEntity.getM_sMissionAccessoryPath();
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
	private void init() {
		imageBack = (ImageView) findViewById(R.id.imageviewBack);
		textMissionState = (TextView) findViewById(R.id.textMissionState);
		textTimeData = (TextView) findViewById(R.id.textTimeData);
		textContent = (TextView) findViewById(R.id.textContent);
		textContentSum = (TextView) findViewById(R.id.textContentSum);
	}

	private void setText() {
		if (cMissionConclusionEntity.getM_iMissionCheck() == MyConstant.MISSIONCONCLUSION_WAITCHECK) {
			textMissionState.setText("未审核");
			textMissionState.setTextColor(Color.MAGENTA);
		} else if (cMissionConclusionEntity.getM_iMissionCheck() == MyConstant.MISSIONCONCLUSION_PASS) {
			textMissionState.setText("已通过");
			textMissionState.setTextColor(Color.GREEN);
		}else if (cMissionConclusionEntity.getM_iMissionCheck() == MyConstant.MISSIONCONCLUSION_NOPASS) {
			textMissionState.setText("未通过");
			textMissionState.setTextColor(Color.RED);
		}

		textTimeData.setText(cMissionConclusionEntity.getM_sMissionSubmitTime());
		textContent.setText(missioncontent);
		textContentSum.setText(cMissionConclusionEntity.getM_sMissionSummary());

	}

	private void setListener() {
		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
