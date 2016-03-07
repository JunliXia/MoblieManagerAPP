package com.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.bbalbs.common.a.c;
import com.bll.AccessoryBll;
import com.bll.MissionBll;
import com.entity.CMissionEntity;
import com.entity.CMissionEntityList;
import com.example.mobliemanager.R;
import com.http.UploadFileTask;
import com.tool.ImageHelper;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;

public class UnderwayMissionDetailActivity extends Activity {
	private ImageView butPhoto;
	private ImageView imagePhoto;// 用于显示照片
	private Bitmap myBitmap;
	private byte[] mContent;

	private String IMAGE_FILE_PATH = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/MobileSale";
	private String takePicturePath;// 调用相机拍摄照片的名字

	private ImageView imageBack;
	private MissionBll missionBll;
	private CMissionEntity cMissionEntity;
	private TextView textTimeData, textContent;
	private EditText textContentSum;
	private Button butUploadMission;

	private AccessoryBll accessoryBll;
	private String picPath = null;
	private String strTime=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.underwaymission_detail);
		cMissionEntity = (CMissionEntity) this.getIntent().getExtras()
				.getSerializable("CMissionEntity");
		missionBll = MissionBll.getMissionBll(this);
		accessoryBll = AccessoryBll.getAccessoryBll(this);
		init();
		setListener();
		touploadimg();
		setText();
		File file = new File(IMAGE_FILE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private void setText() {
		textTimeData.setText(cMissionEntity.getMissionDeadLine());
		textContent.setText(cMissionEntity.getMissionContent());
		// textContentSum.setText("");
	}

	private void init() {
		butPhoto = (ImageView) findViewById(R.id.butPhoto);
		imagePhoto = (ImageView) findViewById(R.id.imagePhoto);
		imageBack = (ImageView) findViewById(R.id.imageviewBack);
		textTimeData = (TextView) findViewById(R.id.textTimeData);
		textContent = (TextView) findViewById(R.id.textContent);
		textContentSum = (EditText) findViewById(R.id.textContentSum);
		butUploadMission = (Button) findViewById(R.id.butUpload);
	}

	private void setListener() {
		imageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CMissionEntity entity = new CMissionEntity();
				JSONObject js=cMissionEntity.toJson(MyOpcode.Operation.GET_UNDERWAY_MISSION);
				missionBll.EnterMission(js, EnterMissionHandler,MyURL.GETUNDERWAYMISSION);
			}
		});

		butUploadMission.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				if(picPath!=null&&picPath.length()>0)
				{
					System.out.println("picpath-----"+picPath);
					UploadFileTask uploadFileTask=new UploadFileTask(UnderwayMissionDetailActivity.this);
					uploadFileTask.execute(picPath);
				}
				
				String timetrg=timeSplit(strTime);
				String missionPath="F:/ImagesUploaded/"+timetrg+"MissionId"+cMissionEntity.getMissionId()+".jpg";
				CMissionEntity Entity = new CMissionEntity();
				System.out.println("textContentSum.getText().toString()---"+textContentSum.getText().toString());
				JSONObject js = Entity.sendMissionJS(cMissionEntity
						.getMissionId(), textContentSum.getText().toString(),
						strTime,missionPath);
				missionBll.SentMission(js, SentMissionHandler);
			}
		});
	}

	private Handler SentMissionHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			List<CMissionEntity> cMissionEntities = new ArrayList<CMissionEntity>();
			CMissionEntityList cMissionEntityList = new CMissionEntityList(
					cMissionEntities);
			Intent intent = new Intent();
			intent.setClass(UnderwayMissionDetailActivity.this,
					MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_NOCHECK);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}

	};

	// 进入任务(待处理)
	public Handler EnterMissionHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
			CMissionEntityList cMissionEntityList = (CMissionEntityList) map
					.get("CMissionEntityList");
			Intent intent = new Intent();
			intent.setClass(UnderwayMissionDetailActivity.this,
					MissionActivity.class);
			intent.putExtra("item", MyConstant.MISSION_UNDERWAY);
			intent.putExtra("CMissionEntityList", cMissionEntityList);
			startActivity(intent);
			finish();
		}

	};

	private void touploadimg() {
		butPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy/MM/dd/HH:mm");
				Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
				strTime = formatter.format(curDate);
				String timetrg=timeSplit(strTime);
				
				Intent getImageByCamera = new Intent(
						"android.media.action.IMAGE_CAPTURE");
				takePicturePath = IMAGE_FILE_PATH + "/"
						+timetrg+"MissionId"+cMissionEntity.getMissionId()+".jpg";
				File image = new File(takePicturePath);
				getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(image));
				startActivityForResult(getImageByCamera, 1);

			}
			// showDialog5();

		});

	}
	private String timeSplit(String strtime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/HH");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		strtime = formatter.format(curDate);

		String time1 = strtime.substring(0, 4);
		String time2 = strtime.substring(5, 7);
		String time3 = strtime.substring(8, 10);
		String time4 = strtime.substring(11, 13);
		String result=time1+time2+time3+time4;
		
		return result;
		
		
	}
	

	/**
	 * 回调执行的方法
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==Activity.RESULT_OK)
		{
			/**
			 * 当选择的图片不为空的话，在获取到图片的途径  
			 */
//			Uri uri = data.getData();
//			Log.e(TAG, "uri = "+ uri);
			try {
				String[] pojo = {MediaStore.Images.Media.DATA};
				
//				Cursor cursor = managedQuery(uri, pojo, null, null,null);
//				if(cursor!=null)
//				{
					Bitmap bitmap = ImageHelper.createImage(takePicturePath);
					// String filePath =
					// IMAGE_FILE_PATH+"/"+System.currentTimeMillis()+".jpg";

					ImageHelper.saveCompressBitmap(bitmap,
							new File(takePicturePath));

					bitmap.recycle();
				

					FileChannel fc = new FileInputStream(takePicturePath)
							.getChannel();
					ByteBuffer bb = ByteBuffer.allocate((int) fc.size());
					fc.read(bb);
					
//					byte[] bit1byte=Bitmap2Bytes(bitmap);
//					String strbyte=Base64.encodeToString(bit1byte, 0);
//					byte[] newbyte2=Base64.decode(strbyte, 0);
//					Bitmap bitmap2=Bytes2Bimap(newbyte2);
					
					System.out.println("takePicturePath------"+takePicturePath);
//					String strbyte1=strbyte.substring(0,strbyte.length()/2);
//					String strbyte2=strbyte.substring(strbyte1.length(),strbyte.length());
//					JSONObject js=new JSONObject();
//					js.put(MyOpcode.Operation.OPERATION, MyOpcode.Operation.UPLOADIMG);
//					js.put("img", strbyte1);
//					js.put("img2", strbyte2);
//					accessoryBll.uploadImg(js);

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					// myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
					mContent = baos.toByteArray();
					System.out.println("mcontent0" + mContent);
					System.out.println("takepicturepath:" + takePicturePath);
					// InputStream input=new FileInputStream(takePicturePath);
					// readStream(input);
//					imagePhoto.setImageBitmap(bitmap2);

					if(takePicturePath.endsWith("jpg")||takePicturePath.endsWith("png"))
					{
						picPath = takePicturePath;
						bitmap = BitmapFactory.decodeFile(takePicturePath,
								ImageHelper.getOperations(50, 50));
						imagePhoto.setImageBitmap(bitmap);
					}else{
						alert();
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 回调使用
		 */
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void alert()
    {
    	Dialog dialog = new AlertDialog.Builder(this)
		.setTitle("提示")
		.setMessage("您选择的不是有效的图片")
		.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						picPath = null;
					}
				})
		.create();
		dialog.show();
    }

	// 3、byte[] → Bitmap
	public Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	// 2、Bitmap → byte[]
	public byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public static Bitmap getPicFromBytes(byte[] bytes,
			BitmapFactory.Options opts) {
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
						opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}

	public byte[] readStream(InputStream in) throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		while ((len = in.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		// loginBll.uploadImg(data);
		outStream.close();
		in.close();

		return data;
	}

}
