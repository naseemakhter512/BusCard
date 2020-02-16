package in.co.buscard.common.controller;

import in.co.buscard.common.model.BaseModel;
import in.co.buscard.common.model.UserModel;
import in.co.buscard.util.DataUtility;
import in.co.buscard.util.DataValidator;
import in.co.buscard.util.PropertyReader;
import in.co.buscard.util.ServletUtility;
import in.co.buscard.wsx.controller.ORSView;
import in.co.buscard.wsx.exception.ApplicationException;
import in.co.buscard.wsx.exception.RecordNotFoundException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Forget Password functionality Controller. Performs operation for Forget
 * Password
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class ForgetPasswordCtl extends HttpServlet {
	/**
	 * Logger to log the messages.
	 */
	public static final String OP_GO = "Send";
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		log.debug("ForgetPasswordCtl Method validate Ended");

		return pass;
	}

	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl Method populatebean Started");

		UserModel model = new UserModel();

		model.setLogin(DataUtility.getString(request.getParameter("login")));

		log.debug("ForgetPasswordCtl Method populatebean Ended");

		return model;
	}

	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = new UserModel();

		if (id > 0) {
			try {
				model = model.findByPK(id);
				ServletUtility.setModel(model, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(ORSView.FORGET_PASSWORD_VIEW, request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("ForgetPasswordCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel populateModel = (UserModel) populateModel(request);

		// get model
		UserModel model = new UserModel();
		if (validate(request)) {
		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				model.forgetPassword(populateModel.getLogin());
				ServletUtility.setSuccessMessage(
						"Password has been sent to your email id.", request);
			} catch (RecordNotFoundException e) {
				ServletUtility.setErrorMessage(e.getMessage(), request);
				log.error(e);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
			ServletUtility.forward(ORSView.FORGET_PASSWORD_VIEW, request,
					response);
		}
		}else{
		ServletUtility.forward(ORSView.FORGET_PASSWORD_VIEW, request,
				response);

		log.debug("ForgetPasswordCtl Method doGet Ended");
		}
	}

	/*@Override
	protected String getView() {
		return ORSView.FORGET_PASSWORD_VIEW;
	}*/

}