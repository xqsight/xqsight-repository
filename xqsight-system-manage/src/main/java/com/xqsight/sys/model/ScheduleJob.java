package com.xqsight.sys.model;

import java.util.Date;

import com.xqsight.common.model.BaseModel;

/**
 * 
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月25日 下午2:04:40
 *
 */
public class ScheduleJob extends BaseModel{

	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";

	private Long jobId;

	private Date createTime;

	private Date updateTime;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String jobGroup;
	/**
	 * 任务状态 是否启动任务
	 */
	private String jobStatus;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;
	/**
	 * 任务是否有状态
	 */
	private String isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * 任务调用的方法名
	 */
	private String methodName;
	/**
	 * 是否有效 0:有效 -1:无效
	 **/
	private int active;
	/** 创建人ID **/
	private String createOprId;
	/** 修改人ID **/
	private String updOprId;
	/** 备注 **/
	private String remark;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * getter method
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * setter method
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * getter method
	 * @return the createOprId
	 */
	public String getCreateOprId() {
		return createOprId;
	}

	/**
	 * setter method
	 * @param createOprId the createOprId to set
	 */
	public void setCreateOprId(String createOprId) {
		this.createOprId = createOprId;
	}

	/**
	 * getter method
	 * @return the updOprId
	 */
	public String getUpdOprId() {
		return updOprId;
	}

	/**
	 * setter method
	 * @param updOprId the updOprId to set
	 */
	public void setUpdOprId(String updOprId) {
		this.updOprId = updOprId;
	}

	/**
	 * getter method
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * setter method
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}