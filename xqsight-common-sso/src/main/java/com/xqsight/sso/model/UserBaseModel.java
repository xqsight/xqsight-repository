package com.xqsight.sso.model;

import java.io.Serializable;
import java.util.Date;

public class UserBaseModel implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3140978491162709867L;
	

	private long id;
    
	private String userId;

	private String userName;

	private String password;

	private String cellPhone;

	private String email;
	
	private String imgUri;

	private Date createTime;

	private String salt;// 随机数
	
	/** 是否锁定 0-未锁定 -1-锁定*/
	private int locked;

	// 0:valid -1:no valid
	private int active;

	private String remark;
	
	public boolean isUserLocked(){
	    return this.locked == -1;
	}

	/**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Getter method for property <tt>imgUri</tt>.
     * 
     * @return property value of imgUri
     */
    public String getImgUri() {
        return imgUri;
    }

    /**
     * Setter method for property <tt>imgUri</tt>.
     * 
     * @param imgUri value to be assigned to property imgUri
     */
    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }


	/**
	 * getter method
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * setter method
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    /**
     * Getter method for property <tt>locked</tt>.
     * 
     * @return property value of locked
     */
    public int getLocked() {
        return locked;
    }

    /**
     * Setter method for property <tt>locked</tt>.
     * 
     * @param locked value to be assigned to property locked
     */
    public void setLocked(int locked) {
        this.locked = locked;
    }
    
    /**
     * 
     * @return
     */
    public String getCredentialsSalt() {
        return createTime.getTime() + salt;
    }

}
