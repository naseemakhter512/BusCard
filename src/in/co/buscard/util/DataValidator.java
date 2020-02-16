package in.co.buscard.util;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * This class validates input data
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class DataValidator {

	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNumber(String val) {
		 Pattern pattern = Pattern.compile(".*[^0-9].*");
		boolean flag= (!pattern.matcher(val).matches());
		if(flag==true){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public static boolean isNumberLenth(String val) {
		 Pattern digitPattern = Pattern.compile("\\d{10}");
		boolean flag= (digitPattern.matcher(val).matches());
		if(flag==true){
			return true;
		}else{
			return false;
		}
		
		
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	public static boolean isFloat(String val) {
		if (isNotNull(val)) {
			try {
				float i = Float.parseFloat(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	public static boolean istime(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getTimestamp(val);
		}
		return d != null;
	}
	
	public static boolean isDouble(String val) {
		if (isNotNull(val)) {
			try {
				double i = Double.parseDouble(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * Test above methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
	}

}
