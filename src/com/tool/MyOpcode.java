package com.tool;

public class MyOpcode {
	public static final class Operation {
		public static final String OPERATION = "operation";
		public static final String SIGN = "check";
		public static final String TRUE = "true";
		public static final String FALSE = "false";
		public static final String LIST = "list";
		public static final String TYPE = "type";
		public static final String CHECK="check";	
		
		// ��¼
		public static final int LOGIN = 1;

		// ���ڹ���
		public static final int ENTER_ATTENDANCE = 2; // ���뿼�ڹ���
		public static final int SEND_REGISTERATTENDANCE = 3; // ǩ��
		public static final int SEND_SIGNOUTATTENDANCE = 4; // ǩ��
		
		
		
		// ����
		public static final int GET_WAITTAKE_MISSION = 5; // �������������
		public static final int GET_UNDERWAY_MISSION = 6; // �������������
		public static final int GET_COMPLETE_MISSION = 7; // �������������
		public static final int TAKE_MISSION=8;	//��������
		public static final int SENT_MISSION = 9; // �ύ����
		public static final int GET_MISSIONCONCLUSION=10;//��������ܽ�

		// ����
		public static final int ENTER_BUSSINESS = 11; // �������

		public static final int SEND_BUSSINESS_REGISTER = 12; // �ύ����Ǽ�
		public static final int SEND_BUSSINESS_IN = 13; // �ύ����ִ�
		public static final int SEND_BUSSINESS_OUT = 14; // �ύ�����뿪
		public static final int SEND_BUSSINESS_RETURN = 15; // �ύ�������
//		public static final int SEND_BUSSINESS=12;	//�ύ����
		// �ͻ�����
		public static final int GETNOSTARTVISITPLAN=16;	//����δ��ʼ�ݷüƻ�
		public static final int GETUNDERWAYVISITPLAN=17;//����ִ���аݷüƻ�
		public static final int GETCOMPLETEVISITPLAN=18;//�������̬�ݷüƻ�
		public static final int ENTER_COMMINITION = 19; // ����ͨѶ¼
		public static final int SUBMITVISITCONCLUSION=20;//�ύ�ͻ��ݷ�
//		public static final int ENTER_CLIENT_VISIT = 21; // �ύ�ͻ��ݷ�
		public static final int ENTER_NOTICE = 21;// ����֪ͨͨ��
		public static final int UPLOADIMG=22;//ͼƬ�ϴ�
		public static final int SENDADDRESS=23;//�������λ����Ϣ
		public static final int SEND_SUGGEST=24;//�ύͶ�߽���
		public static final int ENTER_BUSSINESSRECALL=25;//��ó����¼
		public static final int GETBUSSINESSACTIVITY=26;//��ó���
		
		public static final int ENTER_CLIENT_MANAGER = 27; // ����ͻ�����
		public static final int ENTER_CLIENTSUBMIT=28;//����ͻ��ύ��¼
		
		
		public static final int ADD_CLIENT = 29; // ���ӿͻ�
		
		
		public static final int UPDATA_CLIENT = 30; // �޸Ŀͻ�
		public static final int ENTER_VISIT = 31; // ����ݷü�¼

		
	
		
	
	}

	

	// Ա����
	public static final class Employee {
		public static final String EmployeeList="EmployeeList";
		public static final String EmployeeId = "EmployeeId";
		public static final String EmployeeAccount="EmployeeAccount"; 
		public static final String EmployeePassword = "EmployeePassword";
		public static final String EmployeeName = "EmployeeName";
		public static final String EmployeePhone = "EmployeePhone";
		public static final String EmployeeSex = "EmployeeSex";
		public static final String EmployeeDepartment = "EmployeeDepartment";
		public static final String EmployeeJob = "EmployeeJob";
		public static final String EmployeeType = "EmployeeType";
	}

	// ���ڱ�
	public static final class Attendance {
		public static final String AttendanceId = "AttendanceId";
		public static final String EmployeeId = "EmployeeId";
		public static final String AttendanceRegisterTime = "AttendanceRegisterTime";
		public static final String AttendanceSignoutTime = "AttendanceSignoutTime";
		public static final String AttendanceType = "AttendanceType";
		public static final String AttendanceTime="AttendanceTime";
	}

	// �����
	public static final class Mission {
		public static final String MissionList="MissionList";
		public static final String MissionId = "MissionId";
		public static final String MissionPubdate = "MissionPubdate";
		public static final String MissionContent = "MissionContent";
		public static final String MissionDeadline = "MissionDeadline";
		public static final String MissionState = "MissionState";
		public static final String MissionSummary = "MissionSummary";
		public static final String MissionSubmitTime = "MissionSubmitTime";
		public static final String MissionAccessoryPath="MissionAccessoryPath";
	}

	// ���񸽼���
	public static final class MissionAccessory {
		public static final String MissionAccessoryId = "MissionAccessoryId";
		public static final String MissionAccessoryPath = "MissionAccessoryPath";
		public static final String MissionAccessoryState = "MissionAccessoryState";
		public static final String MissionId = "MissionId";
	}

	// ����
	public static final class Bussiness {
		public static final String BussinessList="BussinessList";
		public static final String BussinessId = "BussinessId";
		public static final String BussinessSideAddress = "BussinessSideAddress";
		public static final String BussinessContent = "BussinessContent";
		public static final String BussinessRegisterTime = "BussinessRegisterTime";
		public static final String BussinessInAddress = "BussinessInAddress";
		public static final String BussinessInTime = "BussinessInTime";
		public static final String BussinessOutAddress = "BussinessOutAddress";
		public static final String BussinessOutTime = "BussinessOutTime";
		public static final String BussinessReturnTime = "BussinessReturnTime";
		public static final String EmployeeId = "EmployeeId";
	}

	// �ͻ���
	public static final class Client {
		public static final String ClientList="ClientList";
		public static final String ClientId = "ClientId";
		public static final String ClientName = "ClientName";
		public static final String ClientCompany = "ClientCompany";
		public static final String ClientPhone = "ClientPhone";
		public static final String ClientArea = "ClientArea";
		public static final String ClientAddress = "ClientAddress";
		public static final String ClientType = "ClientType";
		public static final String ClientSummary = "ClientSummary";
		public static final String ClientCommand = "ClientCommand";
	}

	// �ݷñ�
	public static final class Visit {
		public static final String VisitList="VisitList";
		public static final String VisitId = "VisitId";
		public static final String VisitTime = "VisitTime";
		public static final String EmployeeId = "EmployeeId";
		public static final String ClientId = "ClientId";
		public static final String VisitSummary="VisitSummary";
		public static final String VisitCommand="VisitCommand";
	}

	// ���񸽼���
	public static final class ClientAccessory {
		public static final String ClientAccessoryId = "ClientAccessoryId";
		public static final String ClientAccessoryPath = "ClientAccessoryPath";
		public static final String ClientAccessoryState = "ClientAccessoryState";
		public static final String ClientId = "ClientId";
	}

	// ֪ͨ��
	public static final class Notice {
		public static final String NoticeList="NoticeList";
		public static final String NoticeId = "NoticeId";
		public static final String NoticeTime = "NoticeTime";
		public static final String NoticeTitle = "NoticeTitle";
		public static final String NoticeContent = "NoticeContent";
	}
	
	//Ͷ�߽���
	public static final class Suggest{
		public static final String SuggestId="SuggestId";
		public static final String SuggestContent="SuggestContent";
		public static final String SuggestTime="SuggestTime";
	}
	
	//�����ַ
	public static final class Address{
		public static final String AddressList="AddressList";
		public static final String AddressId="AddressId";
		public static final String AddressLat="AddressLat";
		public static final String AddressLong="AddressLong";
		public static final String AddressName="AddressName";
	}
}
