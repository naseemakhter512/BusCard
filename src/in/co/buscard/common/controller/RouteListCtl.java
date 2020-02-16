package in.co.buscard.common.controller;

import in.co.buscard.common.model.BaseModel;
import in.co.buscard.common.model.RouteModel;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Route List functionality Controller. Performs operation for list, search
 * operations of Role
 * 
 */

public class RouteListCtl extends BaseCtl {

	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(RouteListCtl.class);

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {
		RouteModel model = new RouteModel();
		model.setRouteName(DataUtility.getString(request.getParameter("routeName")));
		model.setRouteId(DataUtility.getString(request.getParameter("routeId")));
		model.setKm(DataUtility.getString(request.getParameter("km")));
		return model;
	}

	/**
	 * Handles GET request.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("RoleListCtl doGet Start");

		List list = null;


		RouteModel model = (RouteModel) populateModel(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		Long id = DataUtility.getLong(request.getParameter("id"));
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
					|| "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
		
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
			
				} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.ROUTE_LIST_CTL, request, response);
					return;
				}
				
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				
				if (id != null ) {
					RouteModel deletebean = new RouteModel();
					
						deletebean.setId(id);
						deletebean.delete();
					
				} else {
					ServletUtility.setErrorMessage(
							"Select at least one record", request);
				}
			}else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.ROUTE_CTL, request, response);
				return;
			} 
			list = model.search(0, 0);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			//ServletUtility.forwardView(ORSView.ROUTE_LIST_VIEW, request,
			//		response);
			RequestDispatcher rd = request
			.getRequestDispatcher(ORSView.ROUTE_LIST_VIEW);
	rd.forward(request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("RoleListCtl doGet End");
	}
	@Override
	protected void setAccess(HttpServletRequest request) {
		super.setAccess(request);
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
		
		AccessUtility.setWriteAccess("" + AppRole.ADMIN, request);
	
	}
	@Override
	protected String getView() {
		return ORSView.ROUTE_LIST_VIEW;
	}

}