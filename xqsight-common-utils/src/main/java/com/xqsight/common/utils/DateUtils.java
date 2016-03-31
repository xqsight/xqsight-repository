package com.xqsight.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Assert;

/**
 * 日期操作辅助类
 * 
 * @author lizhiyong
 * 
 */
public class DateUtils {

    /**
     * 根据传入的日期格式自动转换参数日期
     * @param format
     *          日期格式
     * @param date
     *          日期
     * @return
     * @throws ParseException
     */
    public static final String convertDateToString(String format, Date date) {
        SimpleDateFormat fm = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = fm.format(date);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return strDate;
    }

    /**
     * format 'yyyy/MM/dd'
     * @param date
     * @return strDate
     */
    public static final String convertDateToStringFormat(Date date) {
        String strDate = null;
        try {
            strDate = convertDateToString("yyyy/MM/dd", date);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return strDate;
    }

    /**
     * format 'yyyy-MM-dd'
     * @param date
     * @return strDate
     */
    public static final String convertDateToStringFromPage(Date date) {
        String strDate = null;
        try {
            strDate = convertDateToString("yyyy-MM-dd", date);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return strDate;
    }

    /**
     * format 'HH:mm:ss'
     * @param date
     * @return strDate
     */
    public static final String convertDateToHourStringFormat(Date date) {
        String strDate = null;
        try {
            strDate = convertDateToString("HH:mm:ss", date);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return strDate;
    }

    /**
     * format 'yyyy-MM-dd HH:mm:ss'
     * @param date
     * @return strDate
     */
    public static final String convertDateToFullFormat(Date date) {
        String strDate = null;
        try {
            strDate = convertDateToString("yyyy/MM/dd HH:mm:ss", date);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return strDate;
    }

    /**
    * 把String类型转化成Date类型的数据
    * @param strDate
    *               要转化的日期字符串
    * @param format
    *              转化的格式
    * @return date
    *        转化后的Date日期
    */
    public static final Date stringToDateFormat(String strDate, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date date;
        try {
            date = fmt.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 求两个日期之间相差的天数
     * @param startTime
     *               第一个日期
     * @param endTime
     *               第二个日期
     * @return date1 与 date2 之间相差的天数
     */
    public static final int dateDiff(Date startTime, Date endTime) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(startTime);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(endTime);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }
    
    /**
     * 通过指定今天的Date，得到昨天的Date
     * @param now
     * @return
     */
    public static Date getYesterday(Date today){
        Assert.notNull(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
