<%@page import="in.co.buscard.wsx.model.AppRole"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.buscard.common.controller.LoginCtl"%>
<%@page import="in.co.buscard.common.controller.BaseCtl"%>
<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="in.co.buscard.common.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="in.co.buscard.util.DataUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>
<%@page import="in.co.buscard.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.buscard.common.model.UserModel"
	scope="request"></jsp:useBean>
	
<jsp:useBean id="collegeList" class="java.util.ArrayList"
	scope="request" />

	
<%
	HashMap<String, String> gMap = new HashMap<String, String>();
	gMap.put("M", "Male");
	gMap.put("F", "Female");

	LinkedHashMap<String, String> collegeCodeMap = new LinkedHashMap<String, String>();
	collegeCodeMap.put("IT", "IT");
	collegeCodeMap.put("CS", "CS");
	collegeCodeMap.put("EC", "EC");
	collegeCodeMap.put("CE", "CE");
	collegeCodeMap.put("ME", "ME");

	LinkedHashMap<String, String> yearMap = new LinkedHashMap<String, String>();
	yearMap.put("16", "16");
	yearMap.put("15", "15");
	yearMap.put("14", "14");
	yearMap.put("13", "13");
	yearMap.put("12", "12");
	yearMap.put("11", "11");
%>
<%
	String userRole = "Student";
	int roleId = DataUtility.getInt(request.getParameter("roleId"));


%>

<p class="st-title"><%=userRole%>
	Registration
</p>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="POST">
<input type="hidden" name="createdBy" value="<%=model.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy"
		value="<%=model.getModifiedBy()%>"> <input type="hidden"
		name="createdDatetime"
		value="<%=DataUtility.getTimestamp(model.getCreatedDatetime())%>">
	<input type="hidden" name="modifiedDatetime"
		value="<%=DataUtility.getTimestamp(model.getModifiedDatetime())%>">
	
	<input type="hidden" name="roleId" value="<%=roleId%>">
	
	<table>
		<tr>
			<th>First Name*</th>
			<td><input type="text" name="firstName"
				value="<%=DataUtility.getStringData(model.getFirstName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
			<th>Last Name*</th>
			<td><input type="text" name="lastName"
				value="<%=DataUtility.getStringData(model.getLastName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
		</tr>

	
		<tr>
			<th>Login ID*</th>
			<td><input type="text" name="login"
				value="<%=DataUtility.getStringData(model.getLogin())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
		</tr>
		<tr>
			<th>Password*</th>
			<td><input type="password" name="password"
				value="<%=DataUtility.getStringData(model.getPassword())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
			<th>Confirm Password*</th>
			<td><input type="password" name="confirmPassword"
				value="<%=DataUtility.getStringData(model.getConfirmPassword())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
		</tr>
		<tr>
			<th>Gender*</th>
			<td><%=HTMLUtility.getList("gender", model.getGender(), gMap)%></td>

		</tr>
		<tr>
			<th>Mobile No*</th>
			<td><input type="text" name="mobileNo"
				value="<%=DataUtility.getStringData(model.getMobileNo())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>

		</tr>
		<tr>
			<%
				if (model.getId() > 0) {
			%><td align="center" colspan="4"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%><input
				type="submit" name="operation" value="<%=BaseCtl.OP_DELETE%>"><input
				type="submit" name="operation" value="<%=BaseCtl.OP_CANCEL%>"></td>
			<%
				} else {
			%><td align="center" colspan="4"><input type="submit"
				name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
			</td>
			<%
				}
			%>
		</tr>
	</table>
</form>