/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.service.SysDictService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/dict/")
public class SysDictController extends BaseController {

	@Autowired
	private SysDictService sysDictService;

	@RequestMapping("save")
	public Object saveDict(HttpServletRequest request, SysDict sysDict) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysDict.setCreateOprId("" + getCurrentUserId());
		sysDict.setUpdOprId("" + getCurrentUserId());
		sysDict.setCreateTime(new Date());
		sysDict.setUpdateTime(new Date());
		List<SysDict> sysDicts = sysDictService.querySysDictByDictCode(sysDict.getDictCode());
		if (sysDicts != null && sysDicts.size() > 0) {
			map.put(Constants.KEY_STATUS, Constants.FAILURE);
			map.put(Constants.KEY_MESSAGE, "字典编号[" + sysDict.getDictCode() + "]已经存在，请修改后保存！");
		} else {
			sysDictService.saveSysDict(sysDict);
			map.put(Constants.KEY_STATUS, Constants.SUCCESS);
			map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		}
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("savedetail")
	public Object saveDictDetail(HttpServletRequest request, SysDictDetail sysDictDetail) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysDictDetail.setCreateOprId("" + getCurrentUserId());
		sysDictDetail.setUpdOprId("" + getCurrentUserId());
		sysDictDetail.setCreateTime(new Date());
		sysDictDetail.setUpdateTime(new Date());
		sysDictService.saveSysDictDetail(sysDictDetail);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("update")
	public Object updateDict(HttpServletRequest request, SysDict sysDict) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysDict.setUpdOprId("" + getCurrentUserId());
		sysDict.setUpdateTime(new Date());
		List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(sysDict.getDictId());
		boolean booUpd = true;
		if (sysDictDetails != null && sysDictDetails.size() > 0) {
			for (SysDictDetail sysDictDetail : sysDictDetails) {
				if (sysDictDetail.getEditable() == -1) {
					booUpd = false;
					break;
				}
			}
		}
		if (booUpd) {
			sysDictService.updateSysDict(sysDict);
			map.put(Constants.KEY_STATUS, Constants.SUCCESS);
			map.put(Constants.KEY_MESSAGE, Constants.UPD_OK_MSG);
		} else {
			map.put(Constants.KEY_STATUS, Constants.FAILURE);
			map.put(Constants.KEY_MESSAGE, "该条字典是系统内置数据不可修改的，请修改其他记录！");
		}
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("updatedetail")
	public Object updateDictDetail(HttpServletRequest request, SysDictDetail sysDictDetail) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysDictDetail.setUpdOprId("" + getCurrentUserId());
		sysDictDetail.setUpdateTime(new Date());
		SysDictDetail sysDictDetails = sysDictService.querySysDictDetailByDictDetailId(sysDictDetail.getDictDetailId());
		if (sysDictDetails.getEditable() == -1) {
			map.put(Constants.KEY_STATUS, Constants.FAILURE);
			map.put(Constants.KEY_MESSAGE, "该条明细是系统内置数据不可修改的，请修改其他记录！");
		} else {
			sysDictService.updateSysDictDetail(sysDictDetail);
			map.put(Constants.KEY_STATUS, Constants.SUCCESS);
			map.put(Constants.KEY_MESSAGE, Constants.UPD_OK_MSG);
		}
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("delete")
	public Object deleteDict(HttpServletRequest request, int dictId) {
		logger.debug("删除DictId={}", dictId);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(dictId);
		boolean booDel = true;
		if (sysDictDetails != null && sysDictDetails.size() > 0) {
			for (SysDictDetail sysDictDetail : sysDictDetails) {
				if (sysDictDetail.getEditable() == -1) {
					booDel = false;
					break;
				}
			}
		}
		if (booDel) {
			sysDictService.deleteSysDict(dictId);
			map.put(Constants.KEY_STATUS, Constants.SUCCESS);
			map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		} else {
			map.put(Constants.KEY_STATUS, Constants.FAILURE);
			map.put(Constants.KEY_MESSAGE, "该条字典是系统内置数据不可删除的，请查看其他记录！");
		}
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("deletedetail")
	public Object deleteDictDetail(HttpServletRequest request, int dictDetailId) {
		logger.debug("删除dictDetailId={}", dictDetailId);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysDictDetail sysDictDetail = sysDictService.querySysDictDetailByDictDetailId(dictDetailId);
		if (sysDictDetail.getEditable() == -1) {
			map.put(Constants.KEY_STATUS, Constants.FAILURE);
			map.put(Constants.KEY_MESSAGE, "该条明细是系统内置数据不可删除的，请查看其他记录！");
		} else {
			sysDictService.deleteSysDictDetail(dictDetailId);
			map.put(Constants.KEY_STATUS, Constants.SUCCESS);
			map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		}
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("query")
	public Object queryDictByName(HttpServletRequest request, SysDict sysDict) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysDict> sysDicts = sysDictService.querySysDictByDictName(sysDict.getDictName());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysDicts);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querydetail")
	public Object queryDictDetailByDictId(HttpServletRequest request, int dictId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(dictId);
		// 分页处理
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put("sEcho", 0);
		map.put("iTotalRecords", sysDictDetails.size());
		map.put("iTotalDisplayRecords", sysDictDetails.size());
		map.put("aaData", sysDictDetails);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querybyid")
	public Object queryDictById(HttpServletRequest request, int dictId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysDict sysDicts = sysDictService.querySysDictByDictId(dictId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysDicts);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querydetailbyid")
	public Object queryDictDetailById(HttpServletRequest request, int dictDetailId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysDictDetail sysDictDetails = sysDictService.querySysDictDetailByDictDetailId(dictDetailId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysDictDetails);
		return WebUtils.getResponseBody(request, map);
	}

}
