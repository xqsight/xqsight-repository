/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.model.Resource;
import com.xqsight.sso.shiro.model.Role;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午4:33:59
 */
public interface SysUserAuthcMapper {
	
	@Select("SELECT SL.*,SL.login_id userId FROM SYS_LOGIN SL WHERE SL.LOGIN_ID=#{loginId,jdbcType=VARCHAR}")
	UserBaseModel queryUserByLoginId(@Param("loginId")String loginId);
	
	@Select("SELECT SR.ROLE_ID ID, SR.ROLE_NAME description, SR.ROLE_CODE ROLE FROM SYS_USER_ROLE SUR LEFT JOIN SYS_ROLE SR ON SUR.ROLE_ID = SR.ROLE_ID WHERE SUR.ID=#{id,jdbcType=NUMERIC}")
	List<Role> querUserRoleById(@Param("id")long id);
	
	@Select("SELECT SM.MENU_ID ID,SM.MENU_NAME NAME ,SM.URL,SM.PERMISSION,SM.PARENT_ID,SM.TYPE FROM SYS_MENU_ROLE SMR LEFT JOIN SYS_MENU SM ON SMR.MENU_ID = SM.MENU_ID WHERE SMR.ROLE_ID=#{roleId,jdbcType=NUMERIC}")
	List<Resource> queryResourceByRoleId(@Param("roleId")long roleId);
}
