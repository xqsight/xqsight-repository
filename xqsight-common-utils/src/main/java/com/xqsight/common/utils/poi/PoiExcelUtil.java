/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.common.utils.poi;

import java.util.ArrayList;
import java.util.List;

/**
 * 
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年1月8日 下午7:51:17
 */
public class PoiExcelUtil {

	// 获取Excel处理类
	private static PoiExcelHelper getPoiExcelHelper(String filePath) {
		PoiExcelHelper helper;
		if (filePath.indexOf(".xlsx") != -1) {
			helper = new PoiExcel2k7Helper();
		} else {
			helper = new PoiExcel2k3Helper();
		}
		return helper;
	}

	// 获取sheet列表
	public static List<String> GetSheetList(String filePath) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 获取Sheet列表
		ArrayList<String> sheets = helper.getSheetList(filePath);

		return sheets;
	}

	// 读取 Excel
	public static ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex);

		return dataList;
	}

	// 读取 Excel
	public static ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex, String rows) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, rows);

		return dataList;
	}

	// Excel读取
	public static ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex, String[] columns) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, columns);

		return dataList;
	}

	// Excel读取
	public static ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex, String rows, String[] columns) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);

		// 读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, rows, columns);

		return dataList;
	}

}
