/**
 * DireccionIPUtil.java
 * 
 * Modulo Conozca a su Cliente.
 * 
 * Copyright 2014 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 *  
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase utilitaria para obtener direcciones ip del cliente..
 * 
 * @author diego.iza
 */
public class DireccionIPUtil {

	/**
	 * Obtiene la direccion IP del cliente.
	 * 
	 * @param request
	 *            - request de sesion.
	 * 
	 * @return direccion ip del cliente.
	 */
	public static String obtenerDireccionIPCliente(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String ipAddress;
		if ((ipAddress = request.getHeader("X-FORWARDED-FOR")) != null) {
			ip = ipAddress;
			int idx = ip.indexOf(',');
			if (idx > -1) {
				ip = ip.substring(0, idx);
			}
		}
		return ip;
	}
}
