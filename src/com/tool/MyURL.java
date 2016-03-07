package com.tool;

public class MyURL {
	private static final String HIP="http://10.20.4.96:8080/MobileManagerServer";
	
	
	
	public static final String LOGINURI=HIP+"/LoginServlet";						//登录
	
	public static final String ENTERATTENDANCE=HIP+"/EnterAttendanceServlet";		//进入考勤管理
	public static final String SENDREGISTERATTENDANCE=HIP+"/RegisterAttendanceServlet";			//签到
	public static final String SENDSIGNOUTATTENDANCE=HIP+"/SignoutAttendanceServlet";//签退
	
	public static final String GETWAITTAKEMISSION=HIP+"/GetWaitTakeMissionServlet";		//进入任务.获得未接受任务
	public static final String GETUNDERWAYMISSION=HIP+"/GetUnderWayMissionServlet";		//获得执行态任务
	public static final String GETCOMPLETEMISSION=HIP+"/GetCompleteMissionServlet";		//获得终止态任务
	public static final String TAKEMISSION=HIP+"/TakeMissionServlet";	//接受任务
	
	public static final String SENDMISSION=HIP+"/SubmitMissionConclusionServlet";		//提交任务
	public static final String GETMISSIONCONCLUSION=HIP+"/GetMissionConclusionServlet";	//获得任务总结
	
	public static final String ENTERBUSSINESS=HIP+"/EnterBussinessServlet";			//进入出差
//	public static final String SENDBUSSINESS=HIP+"/SendBussinessServlete";			//提交出差
	public static final String REGISTERBUSSINESS=HIP+"/RegisterBussinessServlet";	//出差登记
	public static final String INADDRESSBUSSINESS=HIP+"/InAddressBussinessServlet";	//出差抵达登记
	public static final String OUTADDRESSBUSSINESS=HIP+"/OutAddressBussissServlet";//出差离开登记
	public static final String RETURNBUSSINESS=HIP+"/ReturnBussinessServlet";		//出差归来登记
	
	public static final String GETNOSTARTVISITPLAN=HIP+"/GetNoStartVisitPlanServlet";//进入未开始拜访计划
	public static final String GETUNDERWAYVISITPLAN=HIP+"/GetUnderwayVisitPlanServlet";//进入执行中拜访计划
	public static final String GETCOMPLETEVISITPLAN=HIP+"/GetCompleteVisitPlanServlet";	//进入完成态拜访计划
	public static final String SUBMITVISITCONCLUSION=HIP+"/SubmitVisitConclusionServlet";//提交客户拜访
	
	public static final String ENTERBUSSINESSRECALEE=HIP+"/EnterBussinessRecallServlet";//进入出差记录
	public static final String GETBUSSINESSACTIVITY=HIP+"/GetBussinessActivityServlet";//获得出差活动
	public static final String GETCLIENTSUBMITSERVLET=HIP+"/GetClientSubmitServlet";	//进入客户提交记录
	
	
	public static final String ENTERCLIENTMANAGER=HIP+"/GetEmployeeClientServlet";	//进入客户管理
	public static final String ENTERCLIENTVISIT=HIP+"/EnterClientVisitServlet";		//提交客户拜访
	public static final String ADDCLIENT=HIP+"/SubmitClientServlet";					//增加客户
	
	public static final String UPDATECLIENT=HIP+"/UpdateClientServlet";				//修改客户
	
	public static final String ENTERVISIT=HIP+"/EnterVisitServlet";					//进入拜访
	
	public static final String ENTERCOMMUNITION=HIP+"/EnterCommunitionServlet";		//进入通讯录
	
	public static final String ENTERNOTICE=HIP+"/EnterNoticeServlet";				//进入通知通告
	
	public static final String SENTSUGGEST=HIP+"/SubmitSuggestServlet";				//提交投诉建议
	
	public static final String UPLOADIMG=HIP+"/UploadImgServlet";
	
	public static final String SENDADDRESS=HIP+"/UpdateAddressServlet";
}
