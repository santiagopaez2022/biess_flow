
<% 
	try {
		if (session.getAttribute("dinoisses").equals(session.getId()) 
				&& null != session.getAttribute("dekcolblaitrap") && null != session.getAttribute("noitacifitnedi")) {		
%>
			<jsp:useBean id="emailUnlockBean" class="ec.gov.iess.pq.concesion.web.backing.EmailDesbloqueoBean" scope="request" />
<% 
			emailUnlockBean.sentUnlockEmail();
		} else {			
			session.removeAttribute("dekcolblaitrap");
			session.removeAttribute("noitacifitnedi");
			session.removeAttribute("dinoisses");
			session.setAttribute("egassemrorrenigol", "Error al enviar enlace de desbloqueo.");			
		}
		response.sendRedirect(request.getContextPath() + "/pages/concesion/roles.jsf");
	} catch (Exception e) {
		response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
	}	
%>

