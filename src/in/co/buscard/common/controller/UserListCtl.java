package in.co.buscard.common.controller;

import in.co.buscard.common.model.BaseModel;
import in.co.buscard.common.model.UserModel;
import in.co.buscard.util.AccessUtility;
import in.co.buscard.util.DataUtility;
import in.co.buscard.util.PropertyReader;
import in.co.buscard.util.ServletUtility;
import in.co.buscard.wsx.controller.ORSView;
import in.co.buscard.wsx.exception.ApplicationException;
import in.co.buscard.wsx.model.AppRole;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class UserListCtl extends BaseCtl {
	

	

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(UserListCtl.class);

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		UserModel model = new UserModel();
		model.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		model.setLastName(DataUtility.getString(request
		.getParameter("lastName")));
		model.setRoleId(DataUtility.getInt(request.getParameter("roleId")));
		model.setLogin(DataUtility.getString(request.getParameter("login")));
		return model;
	}

	/**
	 * Handles GET request.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("UserListCtl doGet Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
				.getValue("page.size")) : pageSize;

		UserModel model = (UserModel) populateModel(request);
		
	
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					UserModel deletebean = new UserModel();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						deletebean.delete();
					}
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}
			list = model.search(pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forwardView(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doGet End");
	}

	/**
	 * Returns View page of Controller.
	 */
	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

	@Override
	protected void setAccess(HttpServletRequest request) {
		super.setAccess(request);
		AccessUtility.setWriteAccess("" 
				+ AppRole.ADMIN , request);

		AccessUtility.setDeleteAccess(""
				+ AppRole.ADMIN, request);

	}
}