/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.sys.model.ScheduleJob;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface ScheduleJobMapper {

	@Insert("INSERT INTO SYS_JOB (JOB_NAME,JOB_GROUP,JOB_STATUS,BEAN_CLASS,METHOD_NAME,SPRING_ID,CRON_EXPRESSION, ACTIVE, CREATE_TIME, CREATE_OPR_ID, UPDATE_TIME, UPD_OPR_ID, REMARK) VALUES(" 
	+ "#{jobName, jdbcType=VARCHAR},#{jobGroup, jdbcType=VARCHAR},#{jobStatus, jdbcType=NUMERIC},#{beanClass, jdbcType=VARCHAR},#{methodName, jdbcType=VARCHAR},#{springId, jdbcType=VARCHAR},#{cronExpression, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveScheduleJob(ScheduleJob scheduleJob);

	@Update("UPDATE  SYS_JOB SET JOB_NAME=#{jobName, jdbcType=VARCHAR},JOB_GROUP=#{jobGroup, jdbcType=VARCHAR}, JOB_STATUS=#{jobStatus, jdbcType=NUMERIC},BEAN_CLASS=#{beanClass, jdbcType=VARCHAR},METHOD_NAME=#{methodName, jdbcType=VARCHAR},SPRING_ID=#{springId, jdbcType=VARCHAR},CRON_EXPRESSION=#{cronExpression, jdbcType=VARCHAR},active=#{active, jdbcType=NUMERIC}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE JOB_ID=#{jobId, jdbcType=NUMERIC}")
	void updateScheduleJob(ScheduleJob scheduleJob);
	
	@Delete("DELETE FROM SYS_JOB WHERE  JOB_ID=#{jobId, jdbcType=NUMERIC}")
	void deleteScheduleJob(@Param("jobId") long jobId);

	@Select("SELECT * FROM SYS_JOB WHERE JOB_NAME LIKE #{jobName, jdbcType=VARCHAR} ORDER BY JOB_NAME DESC")
	List<ScheduleJob> queryScheduleJobByJobName(@Param("jobName") String jobName);
	
	@Select("SELECT * FROM SYS_JOB WHERE JOB_ID=#{jobId, jdbcType=NUMERIC}")
	ScheduleJob queryScheduleJobByJobId(@Param("jobId") long jobId);

	@Select("SELECT * FROM SYS_JOB ORDER BY JOB_NAME ASC")
	List<ScheduleJob> queryScheduleJobAll();
	
}
