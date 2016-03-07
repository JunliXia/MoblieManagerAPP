package com.tool;

public class MyConstant {

	public static final int ATTENDANCE_REGISTER = 0; // 签到
	public static final int ATTENDANCE_SIGNOUT = 1; // 签退
	public static final int ATTENDANCE_NOSTART = 2; // 还未开始签到签退

	
	public static final int MISSION_WAITTAKE=0;		//任务未接受
	public static final int MISSION_UNDERWAY=1;		//任务执行中
	public static final int MISSION_NOCHECK=2;		//任务未审核
	public static final int MISSION_CHECK=3;		//任务已审核
	public static final int MISSION_UNDO=4;			//任务已撤销
	public static final int MISSION_OUTTIME=5;		//任务已过期
	public static final int MISSION_FAILURE=6;		//任务已失败
	
	
	public static final int MISSIONCONCLUSION_WAITCHECK=0;	//总结未审核
	public static final int MISSIONCONCLUSION_PASS=1;		//总结通过
	public static final int MISSIONCONCLUSION_NOPASS=2;		//总结未通过
	
	public static final int BUSSINESS_NOCOMPLETE = 0; // 出差还未完成
	public static final int BUSSINESS_COMPLETE = 1; // 出差完成
	public static final int BUSSINESS_SENDREGISTER = 2; // 提交出差登记
	public static final int BUSSINESS_SENDIN = 3; // 提交出差抵达
	public static final int BUSSINESS_SENDOUT = 4; // 提交出差离开
	public static final int BUSSINESS_RETURN = 5; // 提交出差归来

	public static final int CLIENT_VISIT = 0; // 提交拜访记录
	public static final int CLIENT_INFORMATION = 1; // 提交客户信息(做 修改时)
	public static final int CLIENT_COMPANY = 0; // 公司客户
	public static final int CLIENT_PERSON = 1; // 个人客户

	
	public static final int EMPLOYEE_XINGZHENG=4;
	public static final int EMPLOYEE_XIAOSHOU=0;
	public static final int EMPLOYEE_SHOUHOU=2;
	public static final int EMPLOYEE_PEISONG=1;
	public static final int EMPLOYEE_DUCHA=3;
	
	public static final int VISITPLAN_NOTSTART=0;	//拜访计划未开始
	public static final int VISITPLAN_UNDERWAY=1;	//拜访计划执行中
	public static final int VISITPLAN_WAITCHECK=2;	//拜访计划未审核
	public static final int VISITPLAN_CHECK=3;		//拜访计划已审核
	public static final int VISITPLAN_UNDO=4;		//拜访计划已撤销
	public static final int VISITPLAN_OUTTIME=5;	//拜访计划已过期
	public static final int VISITPLAN_FAILURE=6;	//拜访计划已失败
	
}
