<%@page import="in.co.buscard.wsx.controller.ORSView"%>
<%@page import="in.co.buscard.common.controller.RoleCtl"%>
<%@page import="in.co.buscard.common.controller.BaseCtl"%>
<%@page import="in.co.buscard.util.DataUtility"%>
<%@page import="in.co.buscard.util.HTMLUtility"%>
<%@page import="in.co.buscard.util.ServletUtility"%>
<%@page import="in.co.buscard.util.AccessUtility"%>

<jsp:useBean id="model" class="in.co.buscard.common.model.RoleModel"
	scope="request"></jsp:useBean>

<%=HTMLUtility.getSuccessMessage(request)%>
<%=HTMLUtility.getErrorMessage(request)%>

<form action="<%=ORSView.ROLE_CTL%>" method="post">
<center>
<br>
			<p>
<font class="textPMWithOutBorderCommonCenterRedBoldUnderline">Role Form</font>
</p>
	<%=HTMLUtility.getCommonFields(request) %>

<table border="1" cellspacing="0" style="border-collapse: collapse;border: 1px solid gray;" width="90%">
		<tr>
				<td  class="label" width="27%" height="22" style="border: 1px solid gray;">Name*</td>
			<td   height="22" style="border: 1px solid gray;"><input type="text" name="name" size="20"
				value="<%=DataUtility.getStringData(model.getName())%>"></input><font
				color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
		</tr>
		<tr>
			<td  class="label" width="27%" height="22" style="border: 1px solid gray;">Description *</td>
			<td   height="22" style="border: 1px solid gray;"><textarea name="description" cols="22"><%=DataUtility.getStringData(model.getDescription())%></textarea>
			<font
				color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
			</td>
			</tr>

	</table>
		<p align="center">
		<%=HTMLUtility.getSubmitButton(BaseCtl.OP_SAVE,
					AccessUtility.canAdd(request), request)%>
		</p>
	</center>
</form>