package in.co.buscard.wsx.controller;

/**
 * Contains ORS View and Controller URI
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public interface ORSView {

	public String APP_CONTEXT = "/BusCard";

	public String PAGE_FOLDER = "/jsp";
	public String COMMON_FOLDER = "/common";
	public String USER_FOLDER = "/user";
	
	public String IMG_FOLDER = APP_CONTEXT + "/images";
	public String CSS_FOLDER = APP_CONTEXT + "/css";
	public String JS_FOLDER = APP_CONTEXT + "/js";
	
	// generic Views
	public String ERROR_VIEW = COMMON_FOLDER + "/Error.jsp";
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";
	public String COMMON_ATTRIBUTES = COMMON_FOLDER + "/CommonAttributes.jsp";

	// User Folder
	public String USER_VIEW = USER_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = USER_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = USER_FOLDER
			+ "/UserRegistrationView.jsp";
	public String MY_PROFILE_VIEW = USER_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = USER_FOLDER
			+ "/ForgetPasswordView.jsp";
	public String LOGIN_VIEW = USER_FOLDER + "/LoginView.jsp";
	
	
	//Common View
	public String WELCOME_VIEW = COMMON_FOLDER + "/Welcome.jsp";
	
	// Role View
	public String ROLE_VIEW = USER_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = USER_FOLDER + "/RoleListView.jsp";

	//Route Folder
		public String ROUTE_VIEW = USER_FOLDER + "/Route.jsp";
		public String ROUTE_LIST_VIEW = USER_FOLDER + "/RouteListView.jsp";
		public String ROUTE_CTL = APP_CONTEXT + "/RouteCtl";
		public String ROUTE_LIST_CTL = APP_CONTEXT + "/RouteListCtl";
		
		//Fare Folder
				public String FARE_VIEW = USER_FOLDER + "/Fare.jsp";
				public String FARE_LIST_VIEW = USER_FOLDER + "/FareListView.jsp";
				public String FARE_CTL = APP_CONTEXT + "/FareCtl";
				public String FARE_LIST_CTL = APP_CONTEXT + "/FareListCtl";


	public String ERRORMESSAGE_LIST_VIEW = PAGE_FOLDER
			+ "/ErrorMessageListView.jsp";
	
	// generic Controller
	public String ERROR_CTL = "/ErrorCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/LoginCtl";

	// User Controller

	// Without Login
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	// After Login
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	// Role Controller
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	public String ERRORMESSAGE_LIST_CTL = APP_CONTEXT
			+ "/ctl/ErrorMessageListCtl";
	public String Download_CTL = APP_CONTEXT + "/ctl/DownloadCtl";

	
}