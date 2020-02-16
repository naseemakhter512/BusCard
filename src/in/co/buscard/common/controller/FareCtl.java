package in.co.buscard.common.controller;

import in.co.buscard.common.model.FareModel;
import in.co.buscard.common.model.RouteModel;
import in.co.buscard.util.DataUtility;
import in.co.buscard.util.ServletUtility;
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

public class FareCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(FareCtl.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("RouteCtl Method dopost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
	
		// get model
		FareModel model = new FareModel();
		model.setId(DataUtility.getLong(request.getParameter("id")));
		model.setMinKm(DataUtility.getString(request.getParameter("minkm")));
		model.setMaxKm(DataUtility.getString(request.getParameter("maxkm")));
		model.setGeneralFare(DataUtility.getInt(request.getParameter("generalfare")));
		model.setSeniorCitizenFare(DataUtility.getInt(request.getParameter("srcitizenfare")));
		model.setFemaleFare(DataUtility.getInt(request.getParameter("femalefare")));
		model.setJuStudentFare(DataUtility.getInt(request.getParameter("justudentfare")));
		model.setSrStudentFare(DataUtility.getInt(request.getParameter("srstudentfare")));
		long id = model.getId();

		if ("Save".equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.updateFare();
				} else {
					long pk = model.add();
					model.setId(pk);
					
				}
				//ServletUtility.setModel(model, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);
				ServletUtility.redirect(ORSView.FARE_LIST_CTL, request,
						response);
				return ;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setErrorMessage("ID already exists",
						request);
				RequestDispatcher rd = request
						.getRequestDispatcher(ORSView.FARE_VIEW);
				rd.forward(request, response);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		}
		RequestDispatcher rd = request.getRequestDispatcher(ORSView.FARE_VIEW);
		rd.forward(request, response);
		log.debug("UserCtl Method doPost Ended");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = DataUtility.getLong(request.getParameter("id"));

		FareModel model = new FareModel();
		
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

		RequestDispatcher rd = request.getRequestDispatcher(ORSView.FARE_VIEW);
		rd.forward(request, response);


		// ServletUtility.forwardView("/uploadImage.jsp", request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FARE_VIEW;
	}

}