package com.tool;

public class MyURL {
	private static final String HIP="http://10.20.4.96:8080/MobileManagerServer";
	
	
	
	public static final String LOGINURI=HIP+"/LoginServlet";						//��¼
	
	public static final String ENTERATTENDANCE=HIP+"/EnterAttendanceServlet";		//���뿼�ڹ���
	public static final String SENDREGISTERATTENDANCE=HIP+"/RegisterAttendanceServlet";			//ǩ��
	public static final String SENDSIGNOUTATTENDANCE=HIP+"/SignoutAttendanceServlet";//ǩ��
	
	public static final String GETWAITTAKEMISSION=HIP+"/GetWaitTakeMissionServlet";		//��������.���δ��������
	public static final String GETUNDERWAYMISSION=HIP+"/GetUnderWayMissionServlet";		//���ִ��̬����
	public static final String GETCOMPLETEMISSION=HIP+"/GetCompleteMissionServlet";		//�����ֹ̬����
	public static final String TAKEMISSION=HIP+"/TakeMissionServlet";	//��������
	
	public static final String SENDMISSION=HIP+"/SubmitMissionConclusionServlet";		//�ύ����
	public static final String GETMISSIONCONCLUSION=HIP+"/GetMissionConclusionServlet";	//��������ܽ�
	
	public static final String ENTERBUSSINESS=HIP+"/EnterBussinessServlet";			//�������
//	public static final String SENDBUSSINESS=HIP+"/SendBussinessServlete";			//�ύ����
	public static final String REGISTERBUSSINESS=HIP+"/RegisterBussinessServlet";	//����Ǽ�
	public static final String INADDRESSBUSSINESS=HIP+"/InAddressBussinessServlet";	//����ִ�Ǽ�
	public static final String OUTADDRESSBUSSINESS=HIP+"/OutAddressBussissServlet";//�����뿪�Ǽ�
	public static final String RETURNBUSSINESS=HIP+"/ReturnBussinessServlet";		//��������Ǽ�
	
	public static final String GETNOSTARTVISITPLAN=HIP+"/GetNoStartVisitPlanServlet";//����δ��ʼ�ݷüƻ�
	public static final String GETUNDERWAYVISITPLAN=HIP+"/GetUnderwayVisitPlanServlet";//����ִ���аݷüƻ�
	public static final String GETCOMPLETEVISITPLAN=HIP+"/GetCompleteVisitPlanServlet";	//�������̬�ݷüƻ�
	public static final String SUBMITVISITCONCLUSION=HIP+"/SubmitVisitConclusionServlet";//�ύ�ͻ��ݷ�
	
	public static final String ENTERBUSSINESSRECALEE=HIP+"/EnterBussinessRecallServlet";//��������¼
	public static final String GETBUSSINESSACTIVITY=HIP+"/GetBussinessActivityServlet";//��ó���
	public static final String GETCLIENTSUBMITSERVLET=HIP+"/GetClientSubmitServlet";	//����ͻ��ύ��¼
	
	
	public static final String ENTERCLIENTMANAGER=HIP+"/GetEmployeeClientServlet";	//����ͻ�����
	public static final String ENTERCLIENTVISIT=HIP+"/EnterClientVisitServlet";		//�ύ�ͻ��ݷ�
	public static final String ADDCLIENT=HIP+"/SubmitClientServlet";					//���ӿͻ�
	
	public static final String UPDATECLIENT=HIP+"/UpdateClientServlet";				//�޸Ŀͻ�
	
	public static final String ENTERVISIT=HIP+"/EnterVisitServlet";					//����ݷ�
	
	public static final String ENTERCOMMUNITION=HIP+"/EnterCommunitionServlet";		//����ͨѶ¼
	
	public static final String ENTERNOTICE=HIP+"/EnterNoticeServlet";				//����֪ͨͨ��
	
	public static final String SENTSUGGEST=HIP+"/SubmitSuggestServlet";				//�ύͶ�߽���
	
	public static final String UPLOADIMG=HIP+"/UploadImgServlet";
	
	public static final String SENDADDRESS=HIP+"/UpdateAddressServlet";
}
