package com.xqsight.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class ClassUtil {
	
	/**
	 * 从request中获取值填充bean对象
	 * 
	 * @param objClass
	 *            bean的类
	 * @param request
	 *            请求对象
	 * @return
	 * @return object对象
	 */
	public static <T> T fillBean(Class<T> objClass, HttpServletRequest request) {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			Enumeration<?> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				params.put(name, java.net.URLDecoder.decode(request.getParameter(name), "utf-8"));
			}
			T bean = objClass.newInstance();
			BeanUtils.populate(bean, params);
			return bean;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
