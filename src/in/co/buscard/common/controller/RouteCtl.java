package in.co.buscard.common.controller;

import in.co.buscard.common.model.RouteModel;
import in.co.buscard.common.model.UserModel;
import in.co.buscard.util.DataUtility;
import in.co.buscard.util.ServletUtility;
import in.co.buscard.util.UserFilterManager;
import in.co.buscard.wsx.controller.ORSView;
import in.co.buscard.wsx.exception.ApplicationException;
import in.co.buscard.wsx.exception.DuplicateRecordException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * * Route functionality Controller. Performs operation for add, update and get
 * Route
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class RouteCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(RouteCtl.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("RouteCtl Method dopost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		RouteModel model = new RouteModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setRouteId(DataUtility.getString(request.getParameter("routeId")));
		model.setRouteName(DataUtility.getString(request.getParameter("routeName")));
		model.setKm(DataUtility.getString(request.getParameter("km")));
		long id = model.getId();

		if ("Save".equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.updateRoute();
				} else {
					long pk = model.add();
					model.setId(pk);
					
				}
				//ServletUtility.setModel(model, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);
				ServletUtility.redirect(ORSView.ROUTE_LIST_CTL, request,
						response);
				return ;
		
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setErrorMessage("Route already exists",
						request);
				RequestDispatcher rd = request
						.getRequestDispatcher(ORSView.ROUTE_VIEW);
				rd.forward(request, response);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		}
		RequestDispatcher rd = request.getRequestDispatcher(ORSView.ROUTE_VIEW);
		rd.forward(request, response);
		log.debug("UserCtl Method doPost Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		Long id = DataUtility.getLong(request.getParameter("id"));

		RouteModel model = new RouteModel();
		
		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.setModel(model, request);

		RequestDispatcher rd = request.getRequestDispatcher(ORSView.ROUTE_VIEW);
		rd.forward(request, response);

		// ServletUtility.forwardView("/uploadImage.jsp", request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROUTE_VIEW;
	}

}