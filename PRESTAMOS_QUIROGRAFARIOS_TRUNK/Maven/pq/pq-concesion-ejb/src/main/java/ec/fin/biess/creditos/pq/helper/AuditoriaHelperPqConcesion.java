package ec.fin.biess.creditos.pq.helper;

import java.util.HashMap;
import java.util.Map;

import ec.fin.biess.auditoria.util.ParametroEvento;

/**
 * Helper para el manejo de auditoria en el componente de pq-concesion-ejb
 * 
 * @author hugo.mora
 *
 */
public class AuditoriaHelperPqConcesion {

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se utiliza en el proceso de consumo de servicios
	 * web como discapacitados, prestaciones, etc
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConsumeServicioWeb(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("exito", parametros[1]);
		parametrosAuditoria.put("observ", parametros[2]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se utiliza en el proceso de actualizacion de datos
	 * de correo y celular
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosActualizaDatos(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("exito", parametros[1]);
		parametrosAuditoria.put("observ", parametros[2]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. 
	 * Es usado en el proceso de aprobacion masiva de prestamos PDA, PDC y PDV.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosAprobarPrestamoMasivo(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");
		
		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("estado", parametros[1]);
		parametrosAuditoria.put("tipoProd", parametros[2]);
		parametrosAuditoria.put("numpreafi", parametros[3]);
		parametrosAuditoria.put("codprecla", parametros[4]);
		parametrosAuditoria.put("codpretip", parametros[5]);
		parametrosAuditoria.put("ordpreafi", parametros[6]);
		parametrosAuditoria.put("fechaapr", parametros[7]);
		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

}
