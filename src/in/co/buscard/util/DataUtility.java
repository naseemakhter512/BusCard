package in.co.buscard.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data Utility class to format data from one format to another
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class DataUtility {

	/**
	 * Application Date Format
	 */
	public static final String APP_DATE_FORMAT = PropertyReader
			.getValue("format.date");

	public static final String APP_TIME_FORMAT = PropertyReader
			.getValue("format.datetime");
	
			public static final String APP_TIMES_FORMAT = PropertyReader
			.getValue("format.datetimes");

	public static final String MYSQL_DATE_FORMAT = PropertyReader
			.getValue("yyyy-MM-dd");

	/**
	 * Date formatter yyyy-MM-dd
	 */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			APP_DATE_FORMAT);

	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(
			APP_TIME_FORMAT);
	
	private static final SimpleDateFormat timesFormatter = new SimpleDateFormat(
			APP_TIMES_FORMAT);

	private static final SimpleDateFormat mySQLFormatter = new SimpleDateFormat(
			MYSQL_DATE_FORMAT);

	/**
	 * Trims and trailing and leading spaces of a String
	 * 
	 * @param val
	 * @return
	 */
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	/**
	 * Converts and Object to String
	 * 
	 * @param val
	 * @return
	 */
	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	/**
	 * Converts String into Integer
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	public static Float getFloat(String val) {
		if (DataValidator.isFloat(val)) {
			return Float.parseFloat(val);
		} else {
			return null;
		}
	}

	/**
	 * Converts String into Long
	 * 
	 * @param val
	 * @return
	 */
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	/**
	 * Converts String into Date
	 * 
	 * @param val
	 * @return
	 */
	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		
		} catch (Exception e) {
		}
		
		return date;
	}
	
	
	
	public static Date getDate1(String val) {
		Date date = null;
		try {
		if(val.equalsIgnoreCase("")){
		String oldstring = "00/00/0000";
		 date = new SimpleDateFormat("dd/MM/yyyy").parse(oldstring);
			}else{
			date = formatter.parse(val);
			}
		} catch (Exception e) {
		}
		
		return date;
	}

	/**
	 * Converts Date into String
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {

		}
		return "";
	}
	
	/**
	 * Converts Date into String
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		try {
			return timesFormatter.format(date);
		} catch (Exception e) {

		}
		return "";
	}

	public static String getMySQLDateString(Date date) {
		try {
			return mySQLFormatter.format(date);
		} catch (Exception e) {

		}
		return "";
	}

	/**
	 * Gets date after n number of days
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDate(Date date, int day) {
		return null;
	}

	/**
	 * Converts String into Time
	 * 
	 * @param val
	 * @return
	 */
	public static Timestamp getTimestamp(String val) {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
	public static double getDouble(String val) {
		if (DataValidator.isDouble(val)) {
			return Double.parseDouble(val);
		} else {
			return 0;
		}
	}
	public static void main(String[] args) {
		System.out.println(getInt("124"));
	}

}
