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

<form action="<%=ORSView.USER_LIST_CTL%>">
	<center>
		<br>
		<p>
			<font class="textPMWithOutBorderCommonCenterRedBoldUnderline">User
				List</font>
		</p>

		<table border="1" cellspacing="0"
			style="border-collapse: collapse; border: 1px solid gray;"
			width="90%">
			<tr>
				<td class="subheader" height="22" style="border: 1px solid gray;">Select</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">FirstName</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">LastName</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">Training
					Center</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">LoginId</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">Gender</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">DOB</td>
			<tr>
				<td colspan="4"><%=HTMLUtility.getErrorMessage(request)%></td>
			</tr>
			<%
			response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename="
                            + "UserList.xls");
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
		
			</tr>
			<%
				}
			%>
		</table>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</center>
</form>

