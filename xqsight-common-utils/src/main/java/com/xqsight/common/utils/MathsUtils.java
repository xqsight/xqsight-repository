package com.xqsight.common.utils;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * 数值工具类
 * @author lizhiyong
 *
 */
public class MathsUtils {

	/**
	 * 把以厘为单位的金额转化成以元为单位的金额,并保留两位小数，且四舍五入
	 * @param strAmount
	 * @return
	 */
	public static final String amountFormt(String strAmount){
		String m = null;
		try {
			Double amount = Double.valueOf(strAmount.replaceAll(",", "").trim());
			DecimalFormat dFormat = new DecimalFormat("#.00");
			m = dFormat.format(amount/1000);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return m;
	}
	
	
	/**
	 * 生成随机数
	 * @return
	 */
	public static final String getRandom(){
	    return DateUtils.convertDateToString("yyyyMMddHHmmss", new Date()) + Long.valueOf(System.currentTimeMillis()).toString();
	} 
	
	
	public static void main(String[] a){
	    System.out.println(getRandom());
	}
	
}
