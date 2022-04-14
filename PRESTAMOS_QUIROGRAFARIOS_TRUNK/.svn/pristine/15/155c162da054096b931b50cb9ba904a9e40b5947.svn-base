
<% 
	try {
		if (null != request.getParameter("code1") && null != request.getParameter("code2")) { %>
			<jsp:useBean id="onlineUnlockBean" class="ec.gov.iess.pq.concesion.web.backing.DesbloqueoOnlineBean" scope="request" />		
<% 
			onlineUnlockBean.unlockAccount();
		}
		response.sendRedirect(request.getContextPath() + "/pages/concesion/roles.jsf");
	} catch (Exception e) {
		response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
	}		
%>

