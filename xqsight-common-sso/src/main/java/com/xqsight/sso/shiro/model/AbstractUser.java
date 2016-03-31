/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.model;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 
 * @author linhaoran
 * @version AbstractUser.java, v 0.1 2015年8月3日 下午5:41:34 linhaoran
 */
public abstract class AbstractUser {

    private Long       id;                    //编号
    private String     username;              //用户名
    private String     password;              //密码
    private String     salt;                  //加密密码的盐
    private List<Long> roleIds;               //拥有的角色列表
    private Boolean    locked = Boolean.FALSE;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>username</tt>.
     * 
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property <tt>username</tt>.
     * 
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>salt</tt>.
     * 
     * @return property value of salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Setter method for property <tt>salt</tt>.
     * 
     * @param salt value to be assigned to property salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Getter method for property <tt>roleIds</tt>.
     * 
     * @return property value of roleIds
     */
    public List<Long> getRoleIds() {
        return roleIds;
    }

    /**
     * Setter method for property <tt>roleIds</tt>.
     * 
     * @param roleIds value to be assigned to property roleIds
     */
    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    /**
     * Getter method for property <tt>locked</tt>.
     * 
     * @return property value of locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * Setter method for property <tt>locked</tt>.
     * 
     * @param locked value to be assigned to property locked
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AbstractUser user = (AbstractUser) o;

        if (id != null ? !id.equals(user.id) : user.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getRoleIdsStr() {
        if (CollectionUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (Long roleId : roleIds) {
            s.append(roleId);
            s.append(",");
        }
        return s.toString();
    }

    public void setRoleIdsStr(String roleIdsStr) {
        if (StringUtils.isEmpty(roleIdsStr)) {
            return;
        }
        String[] roleIdStrs = roleIdsStr.split(",");
        for (String roleIdStr : roleIdStrs) {
            if (StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            getRoleIds().add(Long.valueOf(roleIdStr));
        }
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", salt='" + salt + '\'' + ", roleIds="
               + roleIds + ", locked=" + locked + '}';
    }

    /**
     * 
     * @return
     */
    public String getCredentialsSalt() {
        return username + salt;
    }
}
