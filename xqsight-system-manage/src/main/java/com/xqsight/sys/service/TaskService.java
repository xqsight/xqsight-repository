package com.xqsight.sys.service;
/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2016 All Rights Reserved.
 *//*
package com.saicfc.pmpf.sys.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.saicfc.pmpf.sys.model.ScheduleJob;

*//**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月25日 下午2:20:38
 *
 *//*
public interface TaskService {

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 添加任务
	 *
	 * @Title: addJob
	 * @param @param job    设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void addJob(ScheduleJob job) throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 初始化调用
	 *
	 * @Title: init
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void init() throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 获取所有计划中的任务列表
	 *
	 * @Title: getAllJob
	 * @param @return    设定文件
	 * @return List<ScheduleJob>    返回类型
	 * @throws
	 *//*
	List<ScheduleJob> getAllJob() throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 所有正在运行的job
	 *
	 * @Title: getRunningJob
	 * @param @return    设定文件
	 * @return List<ScheduleJob>    返回类型
	 * @throws
	 *//*
	List<ScheduleJob> getRunningJob() throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 暂停一个job
	 *
	 * @Title: pauseJob
	 * @param @param scheduleJob    设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void pauseJob(ScheduleJob scheduleJob) throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 恢复一个job
	 *
	 * @Title: resumeJob
	 * @param @param scheduleJob    设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void resumeJob(ScheduleJob scheduleJob) throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 删除一个job
	 *
	 * @Title: deleteJob
	 * @param @param scheduleJob    设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void deleteJob(ScheduleJob scheduleJob) throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 立即执行job
	 *
	 * @Title: runAJobNow
	 * @param @param scheduleJob    设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;

	*//**
	 * @throws SchedulerException 
	 * 
	 * @Description: 更新job时间表达式
	 *
	 * @Title: updateJobCron
	 * @param @param scheduleJob    设定文件
	 * @return void    返回类型
	 * @throws
	 *//*
	void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;

}
*/