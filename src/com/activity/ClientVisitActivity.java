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
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.CompleteVisitAdapter;
import com.adapter.RunVisitAdapter;
import com.bll.VisitBll;
import com.entity.CEmployeeEntity;
import com.entity.CVisitEntity;
import com.entity.CVisitEntityList;
import com.example.mobliemanager.R;
import com.http.UploadFileTask;
import com.tool.ImageHelper;
import com.tool.MyConstant;
import com.tool.MyOpcode;
import com.tool.MyURL;

public class ClientVisitActivity extends Activity{

	private ImageView imageviewBack;
	private ImageView butPhoto;
	private ImageView imagePhoto;
	private Bitmap myBitmap;
	private byte[] mContent;

	private File sdcardTempFile;
	private String IMAGE_FILE_PATH = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/MobileSale";
	private String takePicturePath;// 调用相机拍摄照片的名字
	
	
	
	private TextView textclientname;
	private TextView textclientcompany;
	private EditText textRemand;
	private EditText textContentSum;
	private Button butUploadVisit;
//	private CClientEntity cClientEntity;
	
	private VisitBll visitBll;
	private String picPath = null;
	private String strTime=null;
	
	private CVisitEntity cVisitEntity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.clientvisit);
		cVisitEntity=(CVisitEntity)this.getIntent().getExtras().getSerializable("CVisitEntity");
		visitBll=VisitBll.getVisitBll(this);
		init();
		setText();
		setListener();
		touploadimg();
		File file = new File(IMAGE_FILE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	private void init(){
		imageviewBack=(ImageView)findViewById(R.id.imageviewBack);
		butPhoto=(ImageView)findViewById(R.id.butPhoto);
		imagePhoto=(ImageView)findViewById(R.id.imagePhoto);
		textclientname=(TextView)findViewById(R.id.textclientname);
		textclientcompany=(TextView)findViewById(R.id.textclientcompany);
		textRemand=(EditText)findViewById(R.id.textRemand);
		textContentSum=(EditText)findViewById(R.id.textContentSum);
		butUploadVisit=(Button)findViewById(R.id.butUploadVisit);
	}
	
	
	private void setText(){
		textclientname.setText(cVisitEntity.getClientName());
		textclientcompany.setText(cVisitEntity.getClientCompany());
		
	}
	private void setListener(){
		imageviewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				CVisitEntity cVisitEntity=new CVisitEntity();
//				JSONObject js=cVisitEntity.toVisitJSon(MyOpcode.Operation.GETUNDERWAYVISITPLAN);
//				visitBll.EnterVisit(js, GetRunVisitHandler, MyURL.GETUNDERWAYVISITPLAN)
				GetRunVisitHandler.sendEmptyMessage(0);
			}
		});
		
		
		
		butUploadVisit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if(picPath!=null&&picPath.length()>0)
				{
					System.out.println("picpath-----"+picPath);
					UploadFileTask uploadFileTask=new UploadFileTask(ClientVisitActivity.this);
					uploadFileTask.execute(picPath);
				}
				
				
//				CVisitEntity cVisitEntity=new CVisitEntity();
				 SimpleDateFormat formatter =new SimpleDateFormat ("yyyy/MM/dd");       
	        	 Date curDate = new  Date(System.currentTimeMillis());//获取当前时间       
	        	 String strTime  =  formatter.format(curDate);
	        	 String timetrg=timeSplit(strTime);
	        	 String path="F:/ImagesUploaded/"+timetrg+"ClientId"+cVisitEntity.getClientId()+".jpg";
				
				JSONObject js=cVisitEntity.toVisitConclusionJSON(strTime, textContentSum.getText().toString(), textRemand.getText().toString(), path, cVisitEntity.getVisitPlanId());
				visitBll.EnterClientVisit(js, GetCompleteVisitHandler);
			
			}
		});
		
		
		
	}
	
	
	public Handler GetCompleteVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			List<CVisitEntity> cVisitEntities=new ArrayList<CVisitEntity>();
			CVisitEntityList cVisitEntityList=new CVisitEntityList(cVisitEntities);
			Intent intent = new Intent();
			intent.setClass(ClientVisitActivity.this,
					VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_WAITCHECK);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};
	public Handler GetRunVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			List<CVisitEntity> cVisitEntities=new ArrayList<CVisitEntity>();
			CVisitEntityList cVisitEntityList=new CVisitEntityList(cVisitEntities);
			Intent intent = new Intent();
			intent.setClass(ClientVisitActivity.this,
					VisitPlanActivity.class);
			intent.putExtra("item", MyConstant.VISITPLAN_UNDERWAY);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
			startActivity(intent);
			finish();
		}
		
		
	};
	
	
	private Handler EnterClientVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			CEmployeeEntity cEmployeeEntity=new CEmployeeEntity();
			JSONObject js=cEmployeeEntity.toJSon(MyOpcode.Operation.ENTER_VISIT);
			visitBll.EnterVisit(js, EnterVisitHandler,MyURL.ENTERVISIT);
		
		}
		
		
	};
	private Handler EnterVisitHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;	
			CVisitEntityList cVisitEntityList=(CVisitEntityList)map.get("CVisitEntityList");
			Intent intent=new Intent();
			intent.setClass(ClientVisitActivity.this, VisitActivity.class);
			intent.putExtra("CVisitEntityList", cVisitEntityList);
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
						+timetrg+"ClientId"+cVisitEntity.getClientId()+".jpg";
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
