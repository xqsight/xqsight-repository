/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.ScheduleJob;
import com.xqsight.sys.mysqlmapper.ScheduleJobMapper;
import com.xqsight.sys.service.ScheduleJobService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:48:31
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	
	private static Logger logger = LogManager.getLogger(ScheduleJobServiceImpl.class); 
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	/**
	 * <p>Title: savescheduleJob</p>
	 * <p>Description: </p>
	 * @param sysJob
	 * @see com.saicfc.pmpf.sys.service.scheduleJobService#savescheduleJob(com.saicfc.pmpf.sys.model.scheduleJob)
	 */
	@Override
	public void saveScheduleJob(ScheduleJob scheduleJob) {
		logger.debug("出入参数:{}", JSON.toJSONString(scheduleJob));
		scheduleJobMapper.saveScheduleJob(scheduleJob);
	}

	/**
	 * <p>Title: updatescheduleJob</p>
	 * <p>Description: </p>
	 * @param sysJob
	 * @see com.saicfc.pmpf.sys.service.scheduleJobService#updatescheduleJob(com.saicfc.pmpf.sys.model.scheduleJob)
	 */
	@Override
	public void updateScheduleJob(ScheduleJob sysJob) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysJob));
		scheduleJobMapper.updateScheduleJob(sysJob);
	}

	/**
	 * <p>Title: deletescheduleJob</p>
	 * <p>Description: </p>
	 * @param jobId
	 * @see com.saicfc.pmpf.sys.service.scheduleJobService#deletescheduleJob(int)
	 */
	@Override
	public void deleteScheduleJob(long jobId) {
		logger.debug("出入参数:{}", jobId);
		scheduleJobMapper.deleteScheduleJob(jobId);
	}

	/**
	 * <p>Title: queryscheduleJobByJobName</p>
	 * <p>Description: </p>
	 * @param jobName
	 * @return
	 * @see com.saicfc.pmpf.sys.service.scheduleJobService#queryscheduleJobByJobName(java.lang.String)
	 */
	@Override
	public List<ScheduleJob> queryScheduleJobByJobName(String jobName) {
		logger.debug("出入参数:{}", jobName);
		if(StringUtils.isBlank(jobName)){
			jobName = "%%";
		}else {
			jobName = "%" + jobName + "%";
		}
			
		return scheduleJobMapper.queryScheduleJobByJobName(jobName);
	}

	/**
	 * <p>Title: queryscheduleJobByJobId</p>
	 * <p>Description: </p>
	 * @param jobId
	 * @return
	 * @see com.saicfc.pmpf.sys.service.scheduleJobService#queryscheduleJobByJobId(int)
	 */
	@Override
	public ScheduleJob queryScheduleJobByJobId(long jobId) {
		return scheduleJobMapper.queryScheduleJobByJobId(jobId);
	}

	/**
	 * <p>Title: queryscheduleJobAll</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.saicfc.pmpf.sys.service.scheduleJobService#queryscheduleJobAll()
	 */
	@Override
	public List<ScheduleJob> queryScheduleJobAll() {
		return scheduleJobMapper.queryScheduleJobAll();
	}

	
	
	
}
