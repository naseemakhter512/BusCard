package in.co.buscard.common.controller;

import in.co.buscard.common.model.BaseModel;
import in.co.buscard.common.model.UserModel;
import in.co.buscard.util.DataUtility;
import in.co.buscard.util.DataValidator;
import in.co.buscard.util.PropertyReader;
import in.co.buscard.util.ServletUtility;
import in.co.buscard.util.UserFilterManager;
import in.co.buscard.wsx.controller.ORSView;
import in.co.buscard.wsx.exception.ApplicationException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class LoginCtl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "LogIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	/**
	 * Logger to log the messages.
	 */
	private static Logger log = Logger.getLogger(LoginCtl.class);

	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");

		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");
		UserModel model = new UserModel();
		model.setLogin(DataUtility.getString(request.getParameter("login")));
		model.setPassword(DataUtility.getString(request
				.getParameter("password")));
		log.debug("LoginCtl Method populatebean Ended");
		return model;
	}



	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		String operation = request.getParameter("op");
		if ("Home".equals(operation) && ServletUtility.isLoggedIn(request)) {
			//ServletUtility.forwardView(ORSView.WELCOME_VIEW, request, response);
			RequestDispatcher rd = request
					.getRequestDispatcher(ORSView.WELCOME_VIEW);
			rd.forward(request, response);
		} else {
			request.getSession().invalidate();
			//ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
			RequestDispatcher rd = request
					.getRequestDispatcher(ORSView.LOGIN_VIEW);
			rd.forward(request, response);
		}

		log.debug(" Method doGet End!");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		log.debug(" Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();
		if (validate(request)) {
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserModel populateModel = (UserModel) populateModel(request);

			try {

				populateModel = model.authenticate(populateModel.getLogin(),
						populateModel.getPassword());
System.out.println("populateModel"+populateModel);
				if (populateModel != null) {

					ServletUtility.setUserModel(populateModel, request);
					
					UserFilterManager.setRoleId(request,
							populateModel.getRoleId());
					
					//ServletUtility.forwardView(ORSView.WELCOME_VIEW, request,
					//		response);
					RequestDispatcher rd = request
							.getRequestDispatcher(ORSView.WELCOME_VIEW);
					rd.forward(request, response);
					return;

				} else {
				//	populateModel = (UserModel) populateModel(request);
				//	ServletUtility.setModel(populateModel, request);
					ServletUtility.setErrorMessage(
							"Invalid LoginId And Password", request);
					///ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
					RequestDispatcher rd = request
							.getRequestDispatcher(ORSView.LOGIN_VIEW);
					rd.forward(request, response);
					
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_LOG_OUT.equals(op)) {

			session = request.getSession();

			session.invalidate();

			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);

			return;

		} 
		}else{

		//ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		RequestDispatcher rd = request
				.getRequestDispatcher(ORSView.LOGIN_VIEW);
		rd.forward(request, response);
		log.debug("UserCtl Method doPost Ended");
		}
	}

	/*@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}*/

}