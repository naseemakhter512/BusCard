<%@page import="in.co.buscard.util.DataUtility"%>
<%@page import="in.co.buscard.common.model.UserModel"%>
<%@page import="in.co.buscard.common.controller.BaseCtl"%>
<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="in.co.buscard.common.controller.UserListCtl"%>
<%@page import="in.co.buscard.common.controller.UserCtl"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.buscard.util.AccessUtility"%>

<jsp:useBean id="typeMap" class="java.util.LinkedHashMap"
	scope="request" />

<form action="<%=ORSView.USER_LIST_CTL%>" >

<%
	UserModel userModel = ServletUtility.getUserModel(request);
%>
	<center>
		<br>
		<p>
			<font class="textPMWithOutBorderCommonCenterRedBoldUnderline">User
				List</font>
		</p>
		<table cellspacing="0" style="border: 0px solid gray;" width="90%">
			<tr>
			<td class="label" width="9%" height="22"
					style="border: 0px solid gray;"></td>
					<% if(userModel.getRoleId()==9) {%>
				<td height="22" width="20%" style="border: 0px solid gray;"><%=HTMLUtility.getList("roleId",
					ServletUtility.getParameter("roleId", request), typeMap)%></td>
				<%} %>
				<td class="label" width="9%" height="22"
					style="border: 0px solid gray;">First Name</td>
				<td height="22" width="20%" style="border: 0px solid gray;"><input
					type="text" name="firstName"
					value="<%=ServletUtility.getParameter("firstName", request)%>">
				</td>
				<td class="label" width="7%" height="22"
					style="border: 0px solid gray;">Login Id</td>
				<td class="label" width="20%" height="22"
					style="border: 0px solid gray;"><input type="text"
					name="login"
					value="<%=ServletUtility.getParameter("login", request)%>">
				</td>
				<td class="label" height="22" style="border: 0px solid gray;">
					<input type="submit" class="button" name="operation"
					value="<%=UserListCtl.OP_SEARCH%>">
						
				</td>
				
			</tr>
		</table>
		<br>
		<table border="1" cellspacing="0"
			style="border-collapse: collapse; border: 1px solid gray;"
			width="90%">
			<tr>
				<td class="subheader" height="22" style="border: 1px solid gray;">Select</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">FirstName</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">LastName</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">LoginId</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">Gender</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">DOB</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">Edit</td>
			</tr>
			<tr>
				<td colspan="4"><%=HTMLUtility.getErrorMessage(request)%></td>
			</tr>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<UserModel> it = list.iterator();

				while (it.hasNext()) {

					UserModel model = it.next();
			%>
			<tr>
				<td class="label" height="22" style="border: 1px solid gray;"><input
					type="checkbox" name="ids" value="<%=model.getId()%>"></td>
				<td class="label" height="22" style="border: 1px solid gray;"><%=model.getFirstName()%></td>
				<td class="label" height="22" style="border: 1px solid gray;"><%=model.getLastName()%></td>
				<td class="label" height="22" style="border: 1px solid gray;"><%=model.getLogin()%></td>
				<td class="label" height="22" style="border: 1px solid gray;"><%=model.getGender()%></td>
			
				<td class="label" height="22" style="border: 1px solid gray;">
					<%
						String label = (AccessUtility.canWrite(request)) ? "Edit"
									: "View";
					%> <a href="<%=ORSView.USER_CTL%>?id=<%=model.getId()%>"><%=label%></a>
			</tr>
			<%
				}
			%>
		</table>
		<table cellspacing="0" style="border: 0px solid gray;" width="90%">
			<tr>
				<td class="label" height="22" style="border: 0px solid gray;"><input
					type="submit" class="button" name="operation"
					value="<%=UserListCtl.OP_PREVIOUS%>"></td>

				<td colspan="3" class="label" height="22"
					style="border: 0px solid gray;" align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>

				<td class="label" height="22" style="border: 0px solid gray;"
					align="right"><input type="submit" name="operation"
					class="button" value="<%=UserCtl.OP_NEXT%>"></td>
			</tr>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</center>
</form>



