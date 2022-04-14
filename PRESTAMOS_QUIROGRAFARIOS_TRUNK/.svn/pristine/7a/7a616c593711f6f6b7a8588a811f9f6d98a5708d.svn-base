
<%
	try {
		if (null == session.getAttribute("egassemrorrenigol")) {
			session.setAttribute("egassemrorrenigol", "Error de autenticaci\u00F3n.");
		}
		response.sendRedirect(request.getContextPath() + "/pages/concesion/roles.jsf");
	} catch (Exception e) {
		response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
	}	
%>
