/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.ScheduleJob;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface ScheduleJobService {

	/**
	 * 新增角色
	 *
	 * @Title: savescheduleJob
	 * @Description: TODO
	 * @param @param sysJob    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveScheduleJob(ScheduleJob scheduleJob);
	
	/**
	 * 
	 * updatescheduleJob 修改
	 * @Title: updatescheduleJob
	 * @Description: TODO
	 * @param @param sysJob    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateScheduleJob(ScheduleJob scheduleJob);
	
	/**
	 * 
	 * deletescheduleJob 删除 
	 *
	 * @Title: deletescheduleJob
	 * @Description: TODO
	 * @param @param roleId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteScheduleJob(long jobId);
	
	/**
	 * 
	 * queryscheduleJobByJobName 查询
	 *
	 * @Title: queryscheduleJobByJobName
	 * @Description: TODO
	 * @param @param roleName
	 * @param @return    设定文件
	 * @return List<scheduleJob>    返回类型
	 * @throws
	 */
	List<ScheduleJob> queryScheduleJobByJobName(String jobName);
	
	/**
	 * 
	 * queryscheduleJobByJobId 查询
	 *
	 * @Title: queryscheduleJobByJobId
	 * @Description: TODO
	 * @param @param roleId
	 * @param @return    设定文件
	 * @return scheduleJob    返回类型
	 * @throws
	 */
	ScheduleJob queryScheduleJobByJobId(long jobId);

	/**
	 * queryscheduleJobAll查询所有
	 *
	 * @Title: queryscheduleJobAll
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return List<scheduleJob>    返回类型
	 * @throws
	 */
	List<ScheduleJob> queryScheduleJobAll();
	
	
	
}
