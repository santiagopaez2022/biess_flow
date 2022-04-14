package ec.gov.iess.planillaspq.web.util;

/**
 * Clase utilitaria para obtener direccion de correo de la persona.
 * 
 * @author joffre.delacruz
 */

public class Util {

	public static String devolverCorreo(String stringCorreoPersona, String identificacion) {
		String[] listadoCorreoPersona = stringCorreoPersona.split(";");
		String correo = null;
		
		for (int i = 0; i < listadoCorreoPersona.length; i++) {
			String correoPersona = listadoCorreoPersona[i].trim();
			String[] objetoCorreoPersona = correoPersona.split(",");
			String identificacionPersona = objetoCorreoPersona[0].trim();
			if (identificacion.equals(identificacionPersona)) {
				correo = objetoCorreoPersona[1].trim();
				break;
			}
		}	
		return  correo;
	}
}
