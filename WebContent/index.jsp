<%@page import="java.util.Enumeration"%>
<%@page import="in.co.buscard.wsx.controller.ORSView"%>

<body>

	<H1>Request Info</H1>
	<%
		Enumeration e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Object value = request.getAttribute(key);
			out.println(key + " : " + value + "<BR>");
		}
	%>

	<H1>Session Info</H1>
	<%
		e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Object value = request.getAttribute(key);
			out.println(key + " : " + value + "<BR>");
		}
	%>

	<H1>Application Info</H1>

	<%
		e = application.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Object value = request.getAttribute(key);
			out.println(key + " : " + value + "<BR>");
		}
	%>


</body>
