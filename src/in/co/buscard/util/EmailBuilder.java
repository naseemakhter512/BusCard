package in.co.buscard.util;

import java.util.HashMap;

/**
 * Class that build Application Email messages
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 * 
 */

 public class EmailBuilder {
	 /**
		 * Returns Successful User Registration Message
		 * 
		 * @param map
		 *            : Message parameters
		 * @return
		 */
	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();


		msg.append("<HTML><BODY>");
		msg.append("Registration is successful at  Bus Card");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
    
	/**
	 * Returns Email message of Forget Password
	 * 
	 * @param map
	 *            : params
	 * @return
	 */
	
	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your password is recovered !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
	
	/**
	 * Returns Email message of Change Password
	 * 
	 * @param map
	 * @return
	 */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Password has been changed Successfully !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

}
