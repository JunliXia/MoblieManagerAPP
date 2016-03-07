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
		
		// 登录
		public static final int LOGIN = 1;

		// 考勤管理
		public static final int ENTER_ATTENDANCE = 2; // 进入考勤管理
		public static final int SEND_REGISTERATTENDANCE = 3; // 签到
		public static final int SEND_SIGNOUTATTENDANCE = 4; // 签到
		
		
		
		// 任务
		public static final int GET_WAITTAKE_MISSION = 5; // 进入待接受任务
		public static final int GET_UNDERWAY_MISSION = 6; // 进入待处理任务
		public static final int GET_COMPLETE_MISSION = 7; // 进入已完成任务
		public static final int TAKE_MISSION=8;	//接受任务
		public static final int SENT_MISSION = 9; // 提交任务
		public static final int GET_MISSIONCONCLUSION=10;//获得任务总结

		// 出差
		public static final int ENTER_BUSSINESS = 11; // 进入出差

		public static final int SEND_BUSSINESS_REGISTER = 12; // 提交出差登记
		public static final int SEND_BUSSINESS_IN = 13; // 提交出差抵达
		public static final int SEND_BUSSINESS_OUT = 14; // 提交出差离开
		public static final int SEND_BUSSINESS_RETURN = 15; // 提交出差归来
//		public static final int SEND_BUSSINESS=12;	//提交出差
		// 客户管理
		public static final int GETNOSTARTVISITPLAN=16;	//进入未开始拜访计划
		public static final int GETUNDERWAYVISITPLAN=17;//进入执行中拜访计划
		public static final int GETCOMPLETEVISITPLAN=18;//进入完成态拜访计划
		public static final int ENTER_COMMINITION = 19; // 进入通讯录
		public static final int SUBMITVISITCONCLUSION=20;//提交客户拜访
//		public static final int ENTER_CLIENT_VISIT = 21; // 提交客户拜访
		public static final int ENTER_NOTICE = 21;// 进入通知通告
		public static final int UPLOADIMG=22;//图片上传
		public static final int SENDADDRESS=23;//传输地理位置信息
		public static final int SEND_SUGGEST=24;//提交投诉建议
		public static final int ENTER_BUSSINESSRECALL=25;//获得出差记录
		public static final int GETBUSSINESSACTIVITY=26;//获得出差活动
		
		public static final int ENTER_CLIENT_MANAGER = 27; // 进入客户管理
		public static final int ENTER_CLIENTSUBMIT=28;//进入客户提交记录
		
		
		public static final int ADD_CLIENT = 29; // 增加客户
		
		
		public static final int UPDATA_CLIENT = 30; // 修改客户
		public static final int ENTER_VISIT = 31; // 进入拜访记录

		
	
		
	
	}

	

	// 员工表
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

	// 考勤表
	public static final class Attendance {
		public static final String AttendanceId = "AttendanceId";
		public static final String EmployeeId = "EmployeeId";
		public static final String AttendanceRegisterTime = "AttendanceRegisterTime";
		public static final String AttendanceSignoutTime = "AttendanceSignoutTime";
		public static final String AttendanceType = "AttendanceType";
		public static final String AttendanceTime="AttendanceTime";
	}

	// 任务表
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

	// 任务附件表
	public static final class MissionAccessory {
		public static final String MissionAccessoryId = "MissionAccessoryId";
		public static final String MissionAccessoryPath = "MissionAccessoryPath";
		public static final String MissionAccessoryState = "MissionAccessoryState";
		public static final String MissionId = "MissionId";
	}

	// 出差
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

	// 客户表
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

	// 拜访表
	public static final class Visit {
		public static final String VisitList="VisitList";
		public static final String VisitId = "VisitId";
		public static final String VisitTime = "VisitTime";
		public static final String EmployeeId = "EmployeeId";
		public static final String ClientId = "ClientId";
		public static final String VisitSummary="VisitSummary";
		public static final String VisitCommand="VisitCommand";
	}

	// 任务附件表
	public static final class ClientAccessory {
		public static final String ClientAccessoryId = "ClientAccessoryId";
		public static final String ClientAccessoryPath = "ClientAccessoryPath";
		public static final String ClientAccessoryState = "ClientAccessoryState";
		public static final String ClientId = "ClientId";
	}

	// 通知表
	public static final class Notice {
		public static final String NoticeList="NoticeList";
		public static final String NoticeId = "NoticeId";
		public static final String NoticeTime = "NoticeTime";
		public static final String NoticeTitle = "NoticeTitle";
		public static final String NoticeContent = "NoticeContent";
	}
	
	//投诉建议
	public static final class Suggest{
		public static final String SuggestId="SuggestId";
		public static final String SuggestContent="SuggestContent";
		public static final String SuggestTime="SuggestTime";
	}
	
	//地理地址
	public static final class Address{
		public static final String AddressList="AddressList";
		public static final String AddressId="AddressId";
		public static final String AddressLat="AddressLat";
		public static final String AddressLong="AddressLong";
		public static final String AddressName="AddressName";
	}
}
