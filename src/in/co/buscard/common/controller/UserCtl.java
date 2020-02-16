package in.co.buscard.common.controller;

import in.co.buscard.common.model.BaseModel;
import in.co.buscard.common.model.UserModel;
import in.co.buscard.util.AccessUtility;
import in.co.buscard.util.DataUtility;
import in.co.buscard.util.DataValidator;
import in.co.buscard.util.EmailBuilder;
import in.co.buscard.util.EmailMessage;
import in.co.buscard.util.EmailUtility;
import in.co.buscard.util.PropertyReader;
import in.co.buscard.util.ServletUtility;
import in.co.buscard.util.UserFilterManager;
import in.co.buscard.wsx.controller.ORSView;
import in.co.buscard.wsx.exception.ApplicationException;
import in.co.buscard.wsx.exception.DuplicateRecordException;
import in.co.buscard.wsx.model.AppRole;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class UserCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static final String List = null;

	/**
	 * Logger to log the messages.
	 */

	private static Logger log = Logger.getLogger(UserCtl.class);

	/**
	 * Loads pre-loaded data like Dropdown List.
	 */


	
	/**
	 * Validates Input data
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
	

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

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("roleId",
					PropertyReader.getValue("error.require", "Role"));
			pass = false;
		}
		if((request.getParameter("organizationId")).equalsIgnoreCase("9"))
		{
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId",
					PropertyReader.getValue("error.require", "Training Name"));
			pass = false;
		}
		
	}
		if (DataValidator.isNull(request.getParameter("organizationId"))) {
			request.setAttribute("organizationId",
					PropertyReader.getValue("error.require", "Organization Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId",
					PropertyReader.getValue("error.require", "Trainning Center"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}
		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage(
					"Password does not match with confirm password.", request);
			pass = false;
		}

		log.debug("UserCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseModel populateModel(HttpServletRequest request) {

		log.debug("UserCtl Method populateModel Started");

		UserModel model = new UserModel();

		model.setId(DataUtility.getLong(request.getParameter("id")));

		model.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		model.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));

		model.setLastName(DataUtility.getString(request
				.getParameter("lastName")));

		model.setLogin(DataUtility.getString(request.getParameter("login")));

		model.setPassword(DataUtility.getString(request
				.getParameter("password")));

		model.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));

		model.setGender(DataUtility.getString(request.getParameter("gender")));

		populateModel(model, request);

		log.debug("UserCtl Method populateModel Ended");

		return model;
	}

	/**
	 * Contains Display Logic
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = new UserModel();
		System.out.println("org"+UserFilterManager.getOrganizationId(request));
		System.out.println("clg"+UserFilterManager.getCollegeId(request));
		
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
		ServletUtility.forwardView(ORSView.USER_VIEW, request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("UserCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = (UserModel) populateModel(request);

		long id = model.getId();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update();
				} else {
					long pk = model.add();
					model.setId(pk);

					HashMap<String, String> map = new HashMap<String, String>();
					map.put("login", model.getLogin());
					map.put("password", model.getPassword());
					String message = EmailBuilder
							.getUserRegistrationMessage(map);
					EmailMessage msg = new EmailMessage();
					msg.setTo(model.getLogin());
					msg.setSubject("Registration is successful for SGX");
					msg.setMessage(message);
					msg.setMessageType(EmailMessage.HTML_MSG);
					EmailUtility.sendMail(msg);

				}
				ServletUtility.setModel(model, request);
				ServletUtility.setSuccessMessage("Data is successfully saved",
						request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setModel(model, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forwardView(ORSView.USER_VIEW,
						request, response);
			}
			
			

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			try {
				model.delete();
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_CTL, request,
					response);
			return;
		}
		
		else {
			if (id > 0 || op != null) {
				UserModel model1;
				try {
					model1 = model.findByPK(id);
					ServletUtility.setModel(model1, request);
				} catch (ApplicationException e) {

					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}
		ServletUtility.forwardView(ORSView.USER_VIEW, request, response);
		log.debug("UserCtl Method doPost Ended");
	}

	/**
	 * Returns View page of Controller.
	 */

	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

	@Override
	protected void setAccess(HttpServletRequest request) {
		super.setAccess(request);
		AccessUtility.setWriteAccess("" + AppRole.ADMIN , request);
				
        AccessUtility.setWriteAccess("" + AppRole.ADMIN + "", request);
				

	}
}