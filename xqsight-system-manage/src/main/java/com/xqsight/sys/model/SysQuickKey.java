package com.xqsight.sys.model;/******************************************************************************* * javaBeans * SYS_QUICK_KEY --> SysQuickKey  * <table explanation> * @author 2016-03-23 17:22:39 *  */	public class SysQuickKey implements java.io.Serializable {	/**	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）	 */	private static final long serialVersionUID = -8234220315018606711L;		//field	/** 登陆内码 **/	private long id;	/** 功能序号 **/	private int funOrder;	/** 图标 **/	private String keyIcon;	/** 标题 **/	private String keyTitle;	/** 连接值 **/	private String keyValue;	/** 是否有效
            0:有效
            -1:无效
             **/	private int active;	/** 创建时间 **/	private Object createTime;	/** 创建人ID **/	private String createOprId;	/** 修改时间 **/	private Object updateTime;	/** 修改人ID **/	private String updOprId;	/** 备注 **/	private String remark;	//method	public long getId() {		return id;	}	public void setId(long id) {		this.id = id;	}	public int getFunOrder() {		return funOrder;	}	public void setFunOrder(int funOrder) {		this.funOrder = funOrder;	}	public String getKeyIcon() {		return keyIcon;	}	public void setKeyIcon(String keyIcon) {		this.keyIcon = keyIcon;	}	public String getKeyTitle() {		return keyTitle;	}	public void setKeyTitle(String keyTitle) {		this.keyTitle = keyTitle;	}	public String getKeyValue() {		return keyValue;	}	public void setKeyValue(String keyValue) {		this.keyValue = keyValue;	}	public int getActive() {		return active;	}	public void setActive(int active) {		this.active = active;	}	public Object getCreateTime() {		return createTime;	}	public void setCreateTime(Object createTime) {		this.createTime = createTime;	}	public String getCreateOprId() {		return createOprId;	}	public void setCreateOprId(String createOprId) {		this.createOprId = createOprId;	}	public Object getUpdateTime() {		return updateTime;	}	public void setUpdateTime(Object updateTime) {		this.updateTime = updateTime;	}	public String getUpdOprId() {		return updOprId;	}	public void setUpdOprId(String updOprId) {		this.updOprId = updOprId;	}	public String getRemark() {		return remark;	}	public void setRemark(String remark) {		this.remark = remark;	}}