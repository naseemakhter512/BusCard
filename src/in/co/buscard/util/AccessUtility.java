package in.co.buscard.util;



import javax.servlet.http.HttpServletRequest;

import in.co.buscard.wsx.model.AppRole;

/**
 * Set and Get User access permissions.
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */
public class AccessUtility {

	public static void setAddAccess(String permission,
			HttpServletRequest request) {
		request.setAttribute("addPermission", permission);
	}

	public static void setWriteAccess(String permission,
			HttpServletRequest request) {
		request.setAttribute("writePermission", permission);
	}

	public static void setDeleteAccess(String permission,
			HttpServletRequest request) {
		request.setAttribute("deletePermission", permission);
	}

	public static void setReadAccess(String permission,
			HttpServletRequest request) {
		request.setAttribute("readPermission", permission);
	}

	public static boolean canRead(HttpServletRequest request) {
		String permissions = (String) request.getAttribute("readPermission");
		long roleId = ServletUtility.getRole(request);

		if (permissions == null) {
			return false;
		} else if (permissions.indexOf("" + roleId) > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean canWrite(HttpServletRequest request) {
		String permissions = (String) request.getAttribute("writePermission");
		long roleId = ServletUtility.getRole(request);

		if (permissions == null) {
			return false;
		} else if (permissions.indexOf("" + roleId) > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean canDelete(HttpServletRequest request) {
		String permissions = (String) request.getAttribute("deletePermission");
		long roleId = ServletUtility.getRole(request);

		if (permissions == null) {
			return false;
		} else if (permissions.indexOf("" + roleId) > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean canAdd(HttpServletRequest request) {
		String permissions = (String) request.getAttribute("addPermission");
		long roleId = ServletUtility.getRole(request);

		if (permissions == null) {
			return false;
		} else if (permissions.indexOf("" + roleId) > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		String roles = AppRole.ADMIN +"";

		

	}

}
