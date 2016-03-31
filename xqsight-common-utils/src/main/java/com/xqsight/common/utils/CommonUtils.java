package com.xqsight.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常用工具类
 * 
 * @author lizhiyong
 * 
 */
public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 取得一个非空的String
     * @param str
     * @return
     */
    public static String getStringNotNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param object
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return ((String) object).trim().length() == 0;
        } else if (object instanceof Collection) {
            return ((Collection<?>) object).size() == 0;
        } else if (object instanceof Map) {
            return ((Map<?, ?>) object).size() == 0;
        } else if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param object
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map<String, Object> convertBeanToMap(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class<?> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * @param map
     * @param clazz
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException 
     * @throws IllegalArgumentException 
     */
    public static <T> T convertMapToBean(Map<String, Object> map, Class<T> clazz) throws IntrospectionException, IllegalAccessException,
                                                                                 InvocationTargetException, IllegalArgumentException, ParseException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                if (map.get(propertyName) != null) {
                    Method setMethod = descriptor.getWriteMethod();
                    Object object = map.get(propertyName);
                    if (setMethod.getParameterTypes()[0].getName().equals("java.math.BigDecimal")) {
                        if (object instanceof String) {
                            setMethod.invoke(t, new BigDecimal((String) object));
                        } else if (object instanceof BigDecimal) {
                            setMethod.invoke(t, object);
                        } else if (object instanceof Integer) {
                            setMethod.invoke(t, BigDecimal.valueOf((Integer) object));
                        } else if (object instanceof Long) {
                            setMethod.invoke(t, BigDecimal.valueOf((Long) object));
                        }
                    } else if (setMethod.getParameterTypes()[0].getName().equals("java.util.Date")) {
                        if (object instanceof String && object != null) {
                            String str = (String) object;
                            SimpleDateFormat sdf = null;
                            if (str.length() == 14) {
                                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                            } else if (str.length() == 8) {
                                sdf = new SimpleDateFormat("yyyyMMdd");
                            } else if (str.length() == 6) {
                                sdf = new SimpleDateFormat("HHmmss");
                            }
                            setMethod.invoke(t, sdf.parse(str));
                        } else if (object instanceof Date) {
                            setMethod.invoke(t, object);
                        }
                    } else {
                        setMethod.invoke(t, object);
                    }

                }
            }
        }
        return t;
    }

    public static <T> T convertStringMapToBean(Map<String, String> map, Class<T> clazz) throws IntrospectionException, IllegalAccessException,
                                                                                       InvocationTargetException, IllegalArgumentException, ParseException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                if (StringUtils.isNotBlank(map.get(propertyName))) {
                    descriptor.getWriteMethod().invoke(t, map.get(propertyName));
                }
            }
        }
        return t;
    }

    /**
     * 厘转分
     * @param val
     * @return
     */
    public static String MilliToCent(BigDecimal val) {
        return val.divide(BigDecimal.TEN, 0, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 分转厘
     * @param val
     * @return
     */
    public static BigDecimal CentToMilli(BigDecimal val) {
        return val.multiply(BigDecimal.TEN);
    }

    /**
     * 分转厘
     * @param val
     * @return
     */
    public static BigDecimal CentToMilli(String val) {
        return new BigDecimal(val).multiply(BigDecimal.TEN);
    }

    public static ByteArrayOutputStream readFile(String filename) {
        try {
            FileInputStream fileInStream = new FileInputStream(filename);
            ByteArrayOutputStream fileByteStream = new ByteArrayOutputStream();
            int i = 0;
            while ((i = fileInStream.read()) != -1) {
                fileByteStream.write(i);
            }
            fileInStream.close();
            return fileByteStream;
        } catch (Exception e) {
            logger.error("读取文件发生异常", e);
        }

        return null;
    }

    /**
     * 用map中的value替换表达式中以${key}形式存在的绑定变量
     * 例如：expr="${A} love ${B}"; values=["A","I"]["B","Motor"]
     * @param expr
     * @param values
     * @return 
     * @author shenbing
     */
    public static String replaceBindExpr(String expr, Map<String, ?> values) {
        String patternString = "\\$\\{(" + StringUtils.join(values.keySet(), "|") + ")\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(expr);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, values.get(matcher.group(1)).toString());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * 把map中的key-value对转换为"key1=value1&key2=value2"的字符串形式
     * @param map
     * @return 
     * @author shenbing
     */
    @SuppressWarnings("rawtypes")
    public static String covertMapToUrl(Map<String, ?> map) {
        StringBuffer contentBuf = new StringBuffer();
        for (Iterator<?> i = map.entrySet().iterator(); i.hasNext();) {
            Map.Entry element = (Map.Entry) i.next();
            Object key = element.getKey();
            Object value = element.getValue();
            contentBuf.append(key.toString() + "=" + String.valueOf(value) + "&");
        }

        return contentBuf.substring(0, contentBuf.length() - 1);
    }

}
