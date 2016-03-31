/**
 * 新启信息技术有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author xqsight-jerry
 * @version BeanMessage.java, v 0.1 2015年7月25日 下午3:31:10 xqsight-jerry
 */
public class BeanMessage {

    public static String message(Object bean) {
        if (bean == null)
            return "Object can not be null ";
        StringBuffer sbf = new StringBuffer();
        Class<?> clazz = bean.getClass();
        sbf.append("\n" + clazz.getName() + " check begin:\n");
        try {
            sbf.append(" check public fields:\n");
            Field[] fs = clazz.getFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                sbf.append("    " + clazz.getName() + "." + f.getName() + ": " + f.get(bean) + "\n");
            }
            sbf.append("   check public methods:\n");
            Method[] ms = clazz.getMethods();
            for (int i = 0; i < ms.length; i++) {
                Method m = ms[i];
                if ((!m.getReturnType().getName().equals("void") && m.getParameterTypes().length == 0)
                    && (m.getName().startsWith("get") || m.getName().startsWith("is"))) {
                    sbf.append("    " + clazz.getName() + "." + m.getName() + "(): " + m.invoke(bean) + "\n");
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        sbf.append(clazz.getName() + " check done!");
        return sbf.toString();
    }

}
