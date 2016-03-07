package com.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.tool.MyJsonChange;
import com.tool.MyOpcode;
import com.tool.ToastUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class HTTPLink extends AsyncTask<JSONObject, Integer, String> {

	private Context context;

	
	private String uri;
	
	public HTTPLink() {
		// TODO Auto-generated constructor stub
	}

	// ��¼
	private Handler LoginHandler;
	//���뿼�ڹ���
	private Handler EnterAttendanceHander;
	//ǩ��/ǩ��
	private Handler SendAttendanceHandler;
	//��������
	private Handler EnterMissionHandler;
	//��������
	private Handler GetWaittakeMissionHandler;
	//�ύ����
	private Handler SendMissionHandler;
	//�������
	private Handler EnterBussinessHandler;
	//�ύ����
	private Handler SendBussinessHandler;
	//����ͻ�����
	private Handler EnterClientManagerHandler;
	//�ύ�ͻ��ݷ�
	private Handler EnterClientVisitHandler;
	//���ӿͻ�
	private Handler AddClientHandler;
	//�޸Ŀͻ�
	private Handler UpdateClientHandler;
	//����ݷ�
	private Handler EnterVisitHandler;
	//����ͨѶ¼
	private Handler EnterCommunitionHandler;
	//����֪ͨͨ��
	private Handler EnterNoticeHandler;
	//�ύͶ�߽���
	private Handler SendSuggestHandler;
	//�ϴ�ͼƬ 
	private Handler UploadImgHandler;
	//�ϴ�����λ��
	private Handler SendAddressHandler;
	//��ȡ�����ܽ�
	private Handler GetMissionConclusionHandler;
	//��������¼
	private Handler EnterBussinessRecallHandler;
	//��ó���
	private Handler GetBussinessActivityHandler;
	//����ͻ��ύ��¼
	private Handler GetClientSubmitHandler;
	
	
	public void setGetClientSubmitHandler(Handler getClientSubmitHandler,String url) {
		GetClientSubmitHandler = getClientSubmitHandler;
		this.uri=url;
	}


	public void setGetBussinessActivityHandler(Handler getBussinessActivityHandler,String url) {
		GetBussinessActivityHandler = getBussinessActivityHandler;
		this.uri=url;
	}


	public void setEnterBussinessRecallHandler(Handler enterBussinessRecallHandler,String url) {
		EnterBussinessRecallHandler = enterBussinessRecallHandler;
		this.uri=url;
	}


	public void setGetMissionConclusionHandler(Handler getMissionConclusionHandler,String url) {
		GetMissionConclusionHandler = getMissionConclusionHandler;
		this.uri=url;
	}


	public void setSendAddressHandler(Handler sendAddressHandler,String uri) {
		SendAddressHandler = sendAddressHandler;
		this.uri=uri;
	}


	public void setUploadImgHandler(Handler uploadImgHandler,String uri) {
		UploadImgHandler = uploadImgHandler;
		this.uri=uri;
	}


	public void setSendSuggestHandler(Handler sendSuggestHandler,String uri) {
		SendSuggestHandler = sendSuggestHandler;
		this.uri=uri;
	}


	public void setEnterMissionHandler(Handler enterMissionHandler,String uri) {
		EnterMissionHandler = enterMissionHandler;
		this.uri=uri;
	}


	public void setGetWaittakeMissionHandler(Handler getWaittakeMissionHandler,String uri) {
		GetWaittakeMissionHandler = getWaittakeMissionHandler;
		this.uri=uri;
	}


	public void setSendMissionHandler(Handler sendMissionHandler,String uri) {
		SendMissionHandler = sendMissionHandler;
		this.uri=uri;
	}


	public void setEnterBussinessHandler(Handler enterBussinessHandler,String uri) {
		EnterBussinessHandler = enterBussinessHandler;
		this.uri=uri;
	}


	public void setSendBussinessHandler(Handler sendBussinessHandler,String uri) {
		SendBussinessHandler = sendBussinessHandler;
		this.uri=uri;
	}


	public void setEnterClientManagerHandler(Handler enterClientManagerHandler,String uri) {
		EnterClientManagerHandler = enterClientManagerHandler;
		this.uri=uri;
	}


	public void setEnterClientVisitHandler(Handler enterClientVisitHandler,String uri) {
		EnterClientVisitHandler = enterClientVisitHandler;
		this.uri=uri;
	}


	public void setAddClientHandler(Handler addClientHandler,String uri) {
		AddClientHandler = addClientHandler;
		this.uri=uri;
	}


	public void setUpdateClientHandler(Handler updateClientHandler,String uri) {
		UpdateClientHandler = updateClientHandler;
		this.uri=uri;
	}


	public void setEnterVisitHandler(Handler enterVisitHandler,String uri) {
		EnterVisitHandler = enterVisitHandler;
		this.uri=uri;
	}


	public void setEnterCommunitionHandler(Handler enterCommunitionHandler,String uri) {
		EnterCommunitionHandler = enterCommunitionHandler;
		this.uri=uri;
	}


	public void setEnterNoticeHandler(Handler enterNoticeHandler,String uri) {
		EnterNoticeHandler = enterNoticeHandler;
		this.uri=uri;
	}

	public void setSendAttendanceHandler(Handler sendAttendanceHandler,String uri) {
		SendAttendanceHandler = sendAttendanceHandler;
		this.uri=uri;
	}


	public void setLoginHandler(Handler LoginHandler ,String uri) {
		this.LoginHandler = LoginHandler;
		this.uri=uri;
	}

	
	public void setEnterAttendanceHander(Handler enterAttendanceHander,String uri) {
		this.EnterAttendanceHander = enterAttendanceHander;
		this.uri=uri;
	}


	/************************************/
	/** ������ͨ�� 
	 * @throws UnsupportedEncodingException **/
	public String sendRequest(JSONObject js) throws UnsupportedEncodingException {
		System.out.println("sendrequest--js" + js);
		BasicHttpParams httpParams = new BasicHttpParams();// ���ó�ʱ
		HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 5 * 1000);
		System.out.println("uri--------"+uri);
//		HttpPost post = setPost(js, uri);
		String strjs=MyJsonChange.getChange(js);
	
		HttpPost post=new HttpPost(	uri+"?"+strjs);
		HttpResponse response = null;
		String buffer = null;
		try {
			response = (HttpResponse) new DefaultHttpClient(httpParams)
					.execute(post);
		
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				buffer = getResponse(response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		System.out.println("----buffer:" + buffer);
		return buffer;
	}

	
//	
//	/** ����post **/
//	private HttpPost setPost(JSONObject js, String uri) {
//		HttpPost post = new HttpPost(uri);
//		try {
//			StringEntity strEntity = new StringEntity(js.toString(), "utf-8");
//			post.setEntity(strEntity);
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.e("Post", "setPost E");
//		}
//		post.getParams().setBooleanParameter(
//				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
//		return post;
//	}

	/** ���շ��� **/
	private String getResponse(HttpResponse response) {
		StringBuffer buffer = null;
		try {
			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "utf-8"));
			buffer = new StringBuffer();
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				buffer.append(buf);
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("----getResponse" + buffer);
		return buffer.toString();
	}

	@Override
	protected String doInBackground(JSONObject... js) {
		// TODO Auto-generated method stub
		/*
		 * Timer timer = new Timer(); timer.schedule(new TimerTask() { public
		 * void run() { if(HTTPLink.this.getStatus() ==
		 * AsyncTask.Status.RUNNING) { HTTPLink.this.cancel(true); } } }, 5000);
		 */System.out.println("doinback---js:" + js);
		String msg="";
		try {
			msg = sendRequest(js[0]);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("doinback--msg:" + msg);
		return msg;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	// ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�������ͨ���÷������ݵ�UI
	// thread.
	protected void onPostExecute(String message) {
		// TODO Auto-generated method stub
		try {

			JSONObject js = new JSONObject(message);
			int operation = js.getInt(MyOpcode.Operation.OPERATION);// �жϲ�����
			Message msg = new Message();
			msg.obj = js;
			
			switch (operation) {
			case MyOpcode.Operation.LOGIN:
				if (LoginHandler != null) {
					LoginHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.ENTER_ATTENDANCE:
				if(EnterAttendanceHander!=null){
					EnterAttendanceHander.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SEND_REGISTERATTENDANCE:
				if(SendAttendanceHandler!=null){
					SendAttendanceHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SEND_SIGNOUTATTENDANCE:
				if(SendAttendanceHandler!=null){
					SendAttendanceHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.GET_UNDERWAY_MISSION:
				if(EnterMissionHandler!=null){
					EnterMissionHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.GET_WAITTAKE_MISSION:
				if(EnterMissionHandler!=null){
					EnterMissionHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.GET_COMPLETE_MISSION:
				if(EnterMissionHandler!=null){
					EnterMissionHandler.sendMessage(msg);
				}
				break;
				
			case MyOpcode.Operation.TAKE_MISSION:
				if(GetWaittakeMissionHandler!=null){
					GetWaittakeMissionHandler.sendMessage(msg);
				}
			case MyOpcode.Operation.SENT_MISSION:
				if(SendMissionHandler!=null){
					SendMissionHandler.sendMessage(msg);
				}
		
				break;
			case MyOpcode.Operation.GET_MISSIONCONCLUSION:
				if(GetMissionConclusionHandler!=null){
					GetMissionConclusionHandler.sendMessage(msg);
				}
				
			case MyOpcode.Operation.ENTER_BUSSINESS:
				if(EnterBussinessHandler!=null){
					EnterBussinessHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SEND_BUSSINESS_REGISTER:
				if(SendBussinessHandler!=null){
					SendBussinessHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SEND_BUSSINESS_IN:
				if(SendBussinessHandler!=null){
					SendBussinessHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SEND_BUSSINESS_OUT:
				if(SendBussinessHandler!=null){
					SendBussinessHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SEND_BUSSINESS_RETURN:
				if(SendBussinessHandler!=null){
					SendBussinessHandler.sendMessage(msg);
				}
				break;
				
				
			case MyOpcode.Operation.GETNOSTARTVISITPLAN:
				if(EnterVisitHandler!=null){
					EnterVisitHandler.sendMessage(msg);
				}
				break;
				
			case MyOpcode.Operation.GETUNDERWAYVISITPLAN:
				if(EnterVisitHandler!=null){
					EnterVisitHandler.sendMessage(msg);
				}
				break;
				
			case MyOpcode.Operation.GETCOMPLETEVISITPLAN:
				if(EnterVisitHandler!=null){
					EnterVisitHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.ENTER_COMMINITION:
				if(EnterCommunitionHandler!=null){
					EnterCommunitionHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SUBMITVISITCONCLUSION:
				if(EnterClientVisitHandler!=null){
					EnterClientVisitHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.ENTER_NOTICE:
				if(EnterNoticeHandler!=null){
					EnterNoticeHandler.sendMessage(msg);
				}
				break;
				
			case MyOpcode.Operation.SEND_SUGGEST:
				if(SendSuggestHandler!=null){
					SendSuggestHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.UPLOADIMG:
				if(UploadImgHandler!=null){
					UploadImgHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.SENDADDRESS:
				if(SendAddressHandler!=null){
					SendAddressHandler.sendMessage(msg);
				}
				break;
				
			case MyOpcode.Operation.ENTER_BUSSINESSRECALL:
				if(EnterBussinessRecallHandler!=null){
					EnterBussinessRecallHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.GETBUSSINESSACTIVITY:
				if(GetBussinessActivityHandler!=null){
					GetBussinessActivityHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.ENTER_CLIENTSUBMIT:
				if(GetClientSubmitHandler!=null){
					GetClientSubmitHandler.sendMessage(msg);
				}
				break;
			case MyOpcode.Operation.ENTER_CLIENT_MANAGER:
				if(EnterClientManagerHandler!=null){
					EnterClientManagerHandler.sendMessage(msg);
				}
				break;
//			case MyOpcode.Operation.ENTER_CLIENT_VISIT:
//				if(EnterClientVisitHandler!=null){
//					EnterClientVisitHandler.sendMessage(msg);
//				}
//				break;
			case MyOpcode.Operation.ADD_CLIENT:
				if(AddClientHandler!=null){
					AddClientHandler.sendMessage(msg);
				}
				break;
//			case MyOpcode.Operation.UPDATA_CLIENT:
//				if(UpdateClientHandler!=null){
//					UpdateClientHandler.sendMessage(msg);
//				}
//				break;
//			case MyOpcode.Operation.ENTER_VISIT:
//				if(EnterVisitHandler!=null){
//					EnterVisitHandler.sendMessage(msg);
//				}
//				break;
		

	
			default:
				break;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SocketConect--messageReceived--e:" + e);
		}
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
//		Log.d("cancel", "in");
		 ToastUtil.show(context, "���糬ʱ���Ժ�����...");
	}
}
