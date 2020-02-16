package in.co.buscard.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import in.co.buscard.wsx.controller.ORSView;
import in.co.buscard.wsx.model.AppRole;

public class MenuBuilder {

	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final String separator = " | ";

	public static String getLink(String text, String url) {
		return "<a href='" + url + "'>" + text + "</a>";
	}

	public static String getHorizontalLink(HashMap<String, String> hmap) {
		StringBuffer sb = new StringBuffer(separator + "");
		Iterator<String> keys = hmap.keySet().iterator();

		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = hmap.get(key);
			sb.append(getLink(key, value) + separator);
		}
		return sb.toString();
	}

	public static String getVericalLink(HashMap<String, String> hmap) {
		/*
		 * <UL> <LI> </LI> </UL>
		 */
		StringBuffer sb = new StringBuffer("<UL>");

		Iterator<String> keys = hmap.keySet().iterator();
		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = hmap.get(key);
			sb.append("<LI>" + getLink(key, value) + "</LI>");
		}
		sb.append("</UL>");
		return sb.toString();
	}

	public static String getMenu(long roleId) {
		return getMenu(roleId, HORIZONTAL);
	}

	public static String getMenu(long roleId, int i) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Home", ORSView.WELCOME_CTL);

	 if (roleId == AppRole.ADMIN) {


			map.put("MyProfile", ORSView.MY_PROFILE_CTL);

		} 

		if (i == HORIZONTAL) {
			return getHorizontalLink(map);
		} else {
			return getVericalLink(map);
		}
	}

}