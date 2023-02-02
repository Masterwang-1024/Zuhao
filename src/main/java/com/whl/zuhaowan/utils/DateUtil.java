package com.whl.zuhaowan.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author liuxl
 *
 */
public class DateUtil {
	
	/**
	 * 将日期转换为字符串
	 * @return
	 */
	public static String dateFormat(){
		return dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	public static String dateFormat(String pattern){
		return dateFormat(new Date(), pattern);
	}
	public static String dateFormat(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转换为日期
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String source){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
     * 增加月
     * @param date
     * @param i
     * @return
     */
    public static Date addMonths(Date date, int i) {
        return org.apache.commons.lang.time.DateUtils.addMonths(date, i);
    }
    
    /**
     * 增加小时
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(Date date,int amount){
    	return org.apache.commons.lang.time.DateUtils.addHours(date, amount);
    }

	/**
	 * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间）
	 * @param date
	 * @return
	 */
	public static String getISO8601Timestamp(Date date){
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(tz);
		String nowAsISO = df.format(date);
		return nowAsISO;
	}

	/**
	 * 传入string类型的ISO8601日期格式，转换日期格式
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static String strISO8601ToDate(String source){
		DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		DateTime dt= dtf1.parseDateTime(source);
		DateTimeFormatter dtf2= DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		return dt.toString(dtf2);
	}
	public static void main(String[] args) throws ParseException {

		System.out.println(DateUtil.strToDate(DateUtil.strISO8601ToDate("2021-06-14T09:10:50.000Z")));
	}
}


