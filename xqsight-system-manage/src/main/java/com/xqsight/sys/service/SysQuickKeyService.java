/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.SysQuickKey;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface SysQuickKeyService {

	/**
	 * 
	 * @Description: 新增
	 *
	 * @Title: saveSysQuickKey
	 * @param @param sysQuickKey    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysQuickKey(long id,List<SysQuickKey> sysQuickKeyList);
	
	
	
	/**
	 * 
	 * @Description: 查询
	 *
	 * @Title: querySysQuickKeyById
	 * @param @param id
	 * @param @return    设定文件
	 * @return List<SysQuickKey>    返回类型
	 * @throws
	 */
	List<SysQuickKey> querySysQuickKeyById(long id);
}
