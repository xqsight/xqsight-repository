/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.common.model;


/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月31日 下午9:26:18
 */
public class BaseModel {
	
	private int sEcho=1;
	
	private int iDisplayStart=1;
	
	private int iDisplayLength=15;
	

	/** 是否有效
    0:有效
    -1:无效
     **/
	private int active;
	/** 创建时间 **/
	private Object createTime;
	/** 创建人ID **/
	private String createOprId;
	/** 修改时间 **/
	private Object updateTime;
	/** 修改人ID **/
	private String updOprId;
	/** 备注 **/
	private String remark;
	/**
	 * getter method
	 * @return the sEcho
	 */
	
	public int getsEcho() {
		return sEcho;
	}
	/**
	 * setter method
	 * @param sEcho the sEcho to set
	 */
	
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	/**
	 * getter method
	 * @return the iDisplayStart
	 */
	
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	/**
	 * setter method
	 * @param iDisplayStart the iDisplayStart to set
	 */
	
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	/**
	 * getter method
	 * @return the iDisplayLength
	 */
	
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	/**
	 * setter method
	 * @param iDisplayLength the iDisplayLength to set
	 */
	
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
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
	 * @return the createTime
	 */
	
	public Object getCreateTime() {
		return createTime;
	}
	/**
	 * setter method
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(Object createTime) {
		this.createTime = createTime;
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
	 * @return the updateTime
	 */
	
	public Object getUpdateTime() {
		return updateTime;
	}
	/**
	 * setter method
	 * @param updateTime the updateTime to set
	 */
	
	public void setUpdateTime(Object updateTime) {
		this.updateTime = updateTime;
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
