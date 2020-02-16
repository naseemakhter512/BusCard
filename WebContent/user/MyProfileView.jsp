<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="in.co.buscard.common.controller.MyProfileCtl"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.buscard.util.DataUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>

<jsp:useBean id="model" class="in.co.buscard.common.model.UserModel"
	scope="request"></jsp:useBean>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.MY_PROFILE_CTL%>" method="POST">
<center>
<br>
			<p>
<font class="textPMWithOutBorderCommonCenterRedBoldUnderline">My Profile</font>
</p>
	<input type="hidden" name="id" value="<%=model.getId()%>"> <input
		type="hidden" name="createdBy" value="<%=model.getCreatedBy()%>">
	<input type="hidden" name="modifiedBy"
		value="<%=model.getModifiedBy()%>"> <input type="hidden"
		name="createdDatetime"
		value="<%=DataUtility.getTimestamp(model.getCreatedDatetime())%>">
	<input type="hidden" name="modifiedDatetime"
		value="<%=DataUtility.getTimestamp(model.getModifiedDatetime())%>">

<table border="1" cellspacing="0" style="border-collapse: collapse;border: 1px solid gray;" width="90%">
		<tr>
			<td  class="label" width="27%" height="22" style="border: 1px solid gray;">LoginId*</td>
		<td   height="22" style="border: 1px solid gray;"><input type="text" name="login"
				value="<%=DataUtility.getStringData(model.getLogin())%>"
				readonly="readonly"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
		</tr>

		<tr>
			<td  class="label" width="27%" height="22" style="border: 1px solid gray;">First Name*</td>
			<td   height="22" style="border: 1px solid gray;"><input type="text" name="firstName"
				value="<%=DataUtility.getStringData(model.getFirstName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
		</tr>
		<tr>
			<td  class="label" width="27%" height="22" style="border: 1px solid gray;">Last Name*</td>
				<td   height="22" style="border: 1px solid gray;"><input type="text" name="lastName"
				value="<%=DataUtility.getStringData(model.getLastName())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
		</tr>
		<tr>
			<td  class="label" width="27%" height="22" style="border: 1px solid gray;">Gender</td>
				<td   height="22" style="border: 1px solid gray;">
				<%
					HashMap map = new HashMap();
				map.put("", "--Select--");	
					map.put("M", "Male");
					map.put("F", "Female");

					String htmlList = HTMLUtility.getList("gender", model.getGender(),
							map);
				%> <%=htmlList%>
			</td>
		</tr>
		<tr>
			<td  class="label" width="27%" height="22" style="border: 1px solid gray;">Mobile No*</td>
			<td   height="22" style="border: 1px solid gray;">
			<input type="text" name="mobileNo"
				value="<%=DataUtility.getStringData(model.getMobileNo())%>"><font
				color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
		</tr>

	

		<%=HTMLUtility.getErrorMessage(request)%>

	</table>
	<p align="center">
	<input type="submit" name="operation" class="button"
				value="<%=MyProfileCtl.OP_SAVE%>"> &nbsp;<input
				type="submit" name="operation" class="button"
				value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
	</p>
	</center>
</form>


