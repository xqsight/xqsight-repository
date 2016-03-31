/**
 * 
 */
package com.xqsight.common.utils;

import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xqsight.common.utils.CommonUtils;
import com.xqsight.common.utils.model.BeanForTest;

/**
 * @author xqsight-jerry
 *
 */
public class CommonUtilsTest {

	@Test
	public void testConvertMapToBean() throws IntrospectionException, IllegalAccessException, InvocationTargetException, IllegalArgumentException, ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		String str = "sss";
		Date date = new Date();
		map.put("strName", str);
		map.put("date", date);
		map.put("bigDecimal", BigDecimal.TEN);
		BeanForTest bean = CommonUtils.convertMapToBean(map, BeanForTest.class);
		assertNotNull(bean);
		assertNotNull(bean.getBigDecimal());
		assertNotNull(bean.getDate());
		assertTrue(str.equals(bean.getStrName()));
		assertEquals(0, BigDecimal.TEN.compareTo(bean.getBigDecimal()));
		assertEquals(0, date.compareTo(bean.getDate()));
	}

}
