/**
 * 
 */
package com.xqsight.common.utils;

import java.util.Date;

import org.junit.Test;

import com.xqsight.common.utils.DateFormatUtils;

/**
 * @author xqsight-jerry
 *
 */
public class DateFormatUtilsTest {

	@Test
	public void test() {
		Date date = new Date();
		
		System.out.println(DateFormatUtils.HH_mm_ss.format(date));
		System.out.println(DateFormatUtils.HHmmss.format(date));
		System.out.println(DateFormatUtils.yyMMdd.format(date));
		System.out.println(DateFormatUtils.yyyy_MM_dd.format(date));
		System.out.println(DateFormatUtils.yyyy_MM_dd_SPACE_HH_mm_ss.format(date));
		System.out.println(DateFormatUtils.yyyyMMdd.format(date));
		System.out.println(DateFormatUtils.yyyyMMddHHmmss.format(date));
		System.out.println(DateFormatUtils.yyyyMMddHHmmssSSS.format(date));
		
		
	}

}
