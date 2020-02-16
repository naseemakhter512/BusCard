<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="in.co.buscard.common.model.RoleModel"%>
<%@page import="in.co.buscard.common.controller.RoleListCtl"%>
<%@page import="in.co.buscard.common.controller.BaseCtl"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.buscard.util.AccessUtility"%>

<form action="<%=ORSView.ROLE_LIST_CTL%>">
	<center>
		<br>
		<p>
			<font class="textPMWithOutBorderCommonCenterRedBoldUnderline">Role
				List</font>
		</p>
		<table cellspacing="0" style="border: 0px solid gray;" width="90%">
			<tr>
				<td class="label" width="9%" height="22"
					style="border: 0px solid gray;">Role Name</td>
				<td height="22" width="20%" style="border: 0px solid gray;"><input
					type="text" name="name"
					value="<%=ServletUtility.getParameter("name", request)%>">
				</td>
				<td class="label" height="22" style="border: 0px solid gray;">
					<input type="submit" name="operation" class="button"
					value="<%=BaseCtl.OP_SEARCH%>">
				</td>
			</tr>
		</table>
		<br>
		<table border="1" cellspacing="0"
			style="border-collapse: collapse; border: 1px solid gray;"
			width="90%">
			<tr>
				<td class="subheader" height="22" style="border: 1px solid gray;">Select</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">Name
				</th>
				<td class="subheader" height="22" style="border: 1px solid gray;">Description</td>
				<td class="subheader" height="22" style="border: 1px solid gray;">Edit</td>
			</tr>
			<%
			if (HTMLUtility.getErrorMessage(request).length() > 0) {
		%>
			<tr>
				<td colspan="5"><%=HTMLUtility.getErrorMessage(request)%></td>
			</tr>
			<%
			}
		%>
			<%
			int i = 1;
			List list = ServletUtility.getList(request);
			Iterator<RoleModel> it = list.iterator();
			while (it.hasNext()) {
				RoleModel model = it.next();
		%>
			<tr>
				<td class="label" height="22" style="border: 1px solid gray;"><input
					type="checkbox" name="ids" value="<%=model.getId()%>"></td>
				<td class="label" height="22" style="border: 1px solid gray;"><%=model.getName()%></td>
				<td class="label" height="22" style="border: 1px solid gray;"><%=model.getDescription()%></td>
				<td class="label" height="22" style="border: 1px solid gray;">
					<%
					String label = (AccessUtility.canWrite(request)) ? "Edit"
								: "View";
			
			%> <a href="<%=ORSView.ROLE_CTL%>?id=<%=model.getId()%>"><%=label%></a>
			</tr>
			<%
			}
		%>
		</table>
		<table cellspacing="0" style="border: 0px solid gray;" width="90%">
			<tr>
				<td class="label" height="22" style="border: 0px solid gray;"><input
					type="submit" name="operation" class="button"
					value="<%=BaseCtl.OP_PREVIOUS%>"></td>

				<td class="label" height="22" style="border: 0px solid gray;"
					align="center"><%=HTMLUtility.getSubmitButton(BaseCtl.OP_NEW,
					AccessUtility.canAdd(request), request)%><%=HTMLUtility.getSubmitButton(BaseCtl.OP_DELETE,
					AccessUtility.canDelete(request), request)%></td>

				<td class="label" height="22" style="border: 0px solid gray;"
					align="right"><input type="submit" name="operation"
					class="button" value="<%=BaseCtl.OP_NEXT%>"></td>
			</tr>
		</table>
		<%
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		int index = ((pageNo - 1) * pageSize) + 1;
	%>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</center>

</form>