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

import com.xqsight.sys.model.SysLogin;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysUserMapper {

	@Insert("INSERT INTO SYS_LOGIN (LOGIN_ID,USER_NAME, PASSWORD, LOGIN_TYPE, KEY_CODE, IMG_URL, SALT, LOCKED, ACTIVE, CREATE_TIME, CREATE_OPR_ID, UPDATE_TIME, UPD_OPR_ID, REMARK) VALUES(" 
	+ "#{loginId, jdbcType=VARCHAR},#{userName, jdbcType=VARCHAR},#{password, jdbcType=VARCHAR},#{loginType, jdbcType=NUMERIC},#{keyCode, jdbcType=VARCHAR},#{imgUrl, jdbcType=VARCHAR},#{salt, jdbcType=VARCHAR},#{locked, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysLogin(SysLogin sysLogin);
	
	@Update("UPDATE  SYS_LOGIN SET PASSWORD=#{password, jdbcType=VARCHAR} , SALT = #{salt, jdbcType=VARCHAR}  WHERE  ID=#{id, jdbcType=NUMERIC}")
	void updateSysLoginPwd(SysLogin sysLogin);
	
	@Update("UPDATE  SYS_LOGIN SET USER_NAME=#{userName, jdbcType=VARCHAR} WHERE  ID=#{id, jdbcType=NUMERIC}")
	void updUserName(SysLogin sysLogin);
	
	@Delete("DELETE  FROM SYS_LOGIN WHERE  ID=#{id, jdbcType=NUMERIC}")
	void deleteSysLogin(@Param("id") int id);
	
	@Delete("DELETE  FROM SYS_USER_ROLE WHERE  ID=#{id, jdbcType=NUMERIC}")
	void deleteUserRole(@Param("id") int id);
	
	@Select("SELECT * FROM SYS_LOGIN WHERE LOGIN_ID LIKE #{loginId, jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<SysLogin> querySysLogin(@Param("loginId")String loginId);
	
	@Select("SELECT SL.*,SL.LOGIN_ID USER_ID FROM SYS_LOGIN SL WHERE ID=#{id, jdbcType=NUMERIC}")
	SysLogin querySysLoginById(@Param("id") long id);
	
	
	@Select("SELECT * FROM SYS_LOGIN where LOGIN_ID = #{loginId, jdbcType=VARCHAR}  ORDER BY CREATE_TIME DESC")
	List<SysLogin> querySysLoginByLoginId(@Param("loginId")String loginId);
}
