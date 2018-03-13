package org.primejava.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primejava.basic.model.DateConstant;

/**
 * @author xs
 */
public final class CommonDateUtils {

    private static Logger LOGGER = Logger.getLogger(CommonDateUtils.class);
    
    public static final Long SECOND=1000L;
    public static final Long MINUTE=60L;
    public static final Long HOUR=24L;

    /**
     * 当前时间
     * @return 当前日期
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 当前时间的字符串，以yyyyMMddHHmmss格式化的字符串
     * @return 当前时间的字符串
     */
    public static String nowToString() {
        return dateToString(now());
    }

    /**
     * 将日期时间转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
     * @param dateTime
     *            日期
     * @return 日期字符串
     */
    public static String dateToString(Date dateTime, String dateFormat) {
        if (dateTime == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(dateTime);
    }

    /**
     * 将日期时间转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
     * @param datetime
     *            日期
     * @return 日期字符串
     */
    public static String dateToString(Date datetime) {
        return dateToString(datetime, DateConstant.YMDHMS);
    }

    public static Date stringToDate(String dateTime, String dateFormat) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            return formatter.parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            LOGGER.error("convert date error!", e);
        }
        return null;
    }

    public static Date stringToDate(String dateTime) {
        return stringToDate(dateTime, DateConstant.YMDHMS);
    }

    /**
     * 当前年. <br/>
     * @author xs
     * @return
     */
    public static int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 当前月. <br/>
     * @author xs
     * @return
     */
    public static int currentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 一年开始的时间. <br/>
     * @author xs
     * @param year 年份
     * @return 开始时间
     */
    public static Date yearStartTime(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 一年结束的时间. <br/>
     * @author xs
     * @param year 年份
     * @return 结束时间
     */
    public static Date yearEndTime(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year + 1);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    public static Boolean isActiveTime(Date startTime, Date endTime){
        return isActiveTime(startTime, endTime, null);
    }

    public static Boolean isActiveTime(Date startTime, Date endTime, Date currentTime) {
        if (null == currentTime) {
            currentTime = now();
        }
        if (startTime.before(currentTime) && endTime.after(currentTime)) {
            return true;
        }
        return false;
    }

    /**
     *取一个月后的今天. <br/>
     *
     * @author xs
     * @return
     */
    public static Date nextMonthNow() {
        // TODO Auto-generated method stub
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     *取XX周后的今天. <br/>
     *
     * @author xs
     * @return
     */
    public static Date nextWeekNow(int week) {
        // TODO Auto-generated method stub
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, week);
        return calendar.getTime();
    }

    /**
     * 当前时间往前追溯一个月. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     *
     * @author xs
     * @return
     */
    public static Date lateMonth(){
        return lateMonth(now());
    }
    
    /**
     * 按照date时间的最近一个月. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     *
     * @author xs
     * @param date
     * @return  date之前的一个月  date 为空时返回当前时间
     */
    public static Date lateMonth(Date date){
        if(null==date){
            return now();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
        return calendar.getTime();
    }
    
    /**
     * 当前时间往前追溯一年. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     *
     * @author xs
     * @return
     */
    public static Date lateYear(){
        return lateYear(now());
    }
    /**
     * 这里用一句话描述这个方法的作用. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     *
     * @author xs
     * @param now
     * @return
     */
    private static Date lateYear(Date date) {
        // TODO Auto-generated method stub
        if(null==date){
            return now();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-1);
        return calendar.getTime();
    }
    
    public static String calculatorRemainTime(Date endTime){
        return calculatorRemainTime(now(),endTime);
    }
    
    public static String calculatorRemainTime(Date startTime, Date endTime){
        if(null==startTime){
            return "";
        }
        if(null==endTime){
            return "";
        }
        long remainTime = endTime.getTime() - startTime.getTime();
        if(remainTime==0||remainTime<0){
            return "0分";
        }
        StringBuilder calculator=new StringBuilder();
        long days = remainTime / (SECOND * MINUTE * MINUTE * HOUR);
        if(days>0){
            calculator.append(days).append("天");
        }
        long hours = (remainTime % (SECOND * MINUTE * MINUTE * HOUR)) / (SECOND * MINUTE * MINUTE);
        if(hours>0){
            calculator.append(hours).append("时");
        }
        long minutes = (remainTime % (SECOND * MINUTE * MINUTE)) / (SECOND * MINUTE);
        if(minutes>0){
            calculator.append(minutes).append("分");
        }
        return StringUtils.isBlank(calculator.toString())?"0分":calculator.toString();
    }
}
