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
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.mysqlmapper.SysUserMapper;
import com.xqsight.sys.service.SysUserService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午2:11:03
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	private static Logger logger = LogManager.getLogger(SysUserServiceImpl.class); 
	
	@Autowired
	private SysUserMapper sysLoginMapper;
	
	/**
	 * <p>Title: saveSysLogin</p>
	 * <p>Description: </p>
	 * @param sysLogin
	 * @see com.xqsight.sys.service.SysUserService#saveSysLogin(com.xqsight.sys.model.SysLogin)
	 */
	@Override
	public void saveSysLogin(SysLogin sysLogin) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysLogin));
		sysLoginMapper.saveSysLogin(sysLogin);
	}

	
	/**
	 * <p>Title: updateSysLogin</p>
	 * <p>Description: </p>
	 * @param sysLogin
	 * @see com.xqsight.sys.service.SysUserService#updateSysLogin(com.xqsight.sys.model.SysLogin)
	 */
	@Override
	public void updateSysLoginPwd(SysLogin sysLogin) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysLogin));
		sysLoginMapper.updateSysLoginPwd(sysLogin);
	}
	
	/**
	 * <p>Title: updUserName</p>
	 * <p>Description: </p>
	 * @param sysLogin
	 * @see com.saicfc.pmpf.sys.service.SysUserService#updUserName(com.saicfc.pmpf.sys.model.SysLogin)
	 */
	@Override
	public void updUserName(SysLogin sysLogin) {
		sysLoginMapper.updUserName(sysLogin);
	}
	

	/**
	 * <p>Title: deleteSysLogin</p>
	 * <p>Description: </p>
	 * @param id
	 * @see com.xqsight.sys.service.SysUserService#deleteSysLogin(int)
	 */
	@Override
	public void deleteSysLogin(int id) {
		logger.debug("出入参数:{}", id);
		sysLoginMapper.deleteSysLogin(id);
		sysLoginMapper.deleteUserRole(id);
	}

	/**
	 * <p>Title: querySysLogin</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.sys.service.SysUserService#querySysLogin()
	 */
	@Override
	public List<SysLogin> querySysLogin(String loginId) {
		logger.debug("出入参数:{}", loginId);
		if(StringUtils.isBlank(loginId)){
			loginId = "%%";
		}else {
			loginId = "%" + loginId + "%";
		}
		return sysLoginMapper.querySysLogin(loginId);
	}


	/**
	 * <p>Title: querySysLoginById</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see com.xqsight.sys.service.SysUserService#querySysLoginById(int)
	 */
	@Override
	public SysLogin querySysLoginById(long id) {
		return sysLoginMapper.querySysLoginById(id);
	}


	/**
	 * <p>Title: querySysLoginByLoginId</p>
	 * <p>Description: </p>
	 * @param loginId
	 * @return
	 * @see com.xqsight.sys.service.SysUserService#querySysLoginByLoginId(java.lang.String)
	 */
	@Override
	public List<SysLogin> querySysLoginByLoginId(String loginId) {
		return sysLoginMapper.querySysLoginByLoginId(loginId);
	}
	
	
}
