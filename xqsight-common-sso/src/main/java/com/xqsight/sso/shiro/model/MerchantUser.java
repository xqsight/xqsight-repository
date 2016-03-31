/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.model;

/**
 * 
 * @author linhaoran
 * @version MerchantUser.java, v 0.1 2015年8月5日 上午10:38:10 linhaoran
 */
public class MerchantUser extends AbstractUser {

    private String merId; //所属公司

    /**
     * Getter method for property <tt>merId</tt>.
     * 
     * @return property value of merId
     */
    public String getMerId() {
        return merId;
    }


    /**
     * Setter method for property <tt>merId</tt>.
     * 
     * @param merId value to be assigned to property merId
     */
    public void setMerId(String merId) {
        this.merId = merId;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", merId=" + merId +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", salt='" + getSalt() + '\'' +
                ", roleIds=" + getRoleIds() +
                ", locked=" + getLocked() +
                '}';
    }
}
