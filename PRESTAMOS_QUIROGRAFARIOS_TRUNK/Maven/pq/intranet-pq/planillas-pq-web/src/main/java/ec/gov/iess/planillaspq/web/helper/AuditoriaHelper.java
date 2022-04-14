package ec.gov.iess.planillaspq.web.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ec.fin.biess.auditoria.util.ParametroEvento;

/**
 * Metodos usados para manejo de parametros para la auditoria
 * 
 * @author hugo.mora
 * @date 2017/04/12
 *
 */
public class AuditoriaHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado para el proceso de consulta de prestamos
	 * en estado PDA y PDC
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConsultaPrestamoPDA(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("estado", parametros[1]);
		parametrosAuditoria.put("perDesde", parametros[2]);
		parametrosAuditoria.put("perHasta", parametros[3]);
		parametrosAuditoria.put("tipoProd", parametros[4]);
		parametrosAuditoria.put("numDoc", parametros[5]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de aprobación, rechazo y
	 * ver detalle de prestamos en estado PDA y PDC.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosAprobarRechazarDetallePrestamoPDA(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("estado", parametros[1]);
		parametrosAuditoria.put("perDesde", parametros[2]);
		parametrosAuditoria.put("perHasta", parametros[3]);
		parametrosAuditoria.put("tipoProd", parametros[4]);
		parametrosAuditoria.put("numDoc", parametros[5]);
		parametrosAuditoria.put("codprecla", parametros[6]);
		parametrosAuditoria.put("codpretip", parametros[7]);
		parametrosAuditoria.put("numpreafi", parametros[8]);
		parametrosAuditoria.put("ordpreafi", parametros[9]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de consultar cuentas
	 * bloqueadas (ERC, ECC) y al exportarlas a Excel.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConsultarExcelDesbloqueo(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("estado", parametros[1]);
		parametrosAuditoria.put("perDesde", parametros[2]);
		parametrosAuditoria.put("perHasta", parametros[3]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de desbloqueo de prestamos
	 * ECC y ERC.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosDesbloqueoPrestamo(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("estado", parametros[1]);
		parametrosAuditoria.put("perDesde", parametros[2]);
		parametrosAuditoria.put("perHasta", parametros[3]);
		parametrosAuditoria.put("codprecla", parametros[4]);
		parametrosAuditoria.put("codpretip", parametros[5]);
		parametrosAuditoria.put("numpreafi", parametros[6]);
		parametrosAuditoria.put("ordpreafi", parametros[7]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de aprobación, rechazo y
	 * ver detalle de prestamos en estado PDV.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosAprobarRechazarDetallePrestamoPDV(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("codprecla", parametros[1]);
		parametrosAuditoria.put("codpretip", parametros[2]);
		parametrosAuditoria.put("numpreafi", parametros[3]);
		parametrosAuditoria.put("ordpreafi", parametros[4]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de consulta de prestamos en
	 * estado PDV.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConsultarPrestamoPDV(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("perDesde", parametros[1]);
		parametrosAuditoria.put("perHasta", parametros[2]);
		parametrosAuditoria.put("tipoProd", parametros[3]);
		parametrosAuditoria.put("numDoc", parametros[4]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de consulta de planillas,
	 * tanto para ajuste como anulacion.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConsultaAjusteAnulacionPlanilla(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("ruc", parametros[0]);
		parametrosAuditoria.put("sucursal", parametros[1]);
		parametrosAuditoria.put("anio", String.valueOf(parametros[2]));
		parametrosAuditoria.put("mes", String.valueOf(parametros[3]));
		parametrosAuditoria.put("codtippla", parametros[4]);
		parametrosAuditoria.put("esttippla", parametros[5]);
		parametrosAuditoria.put("secpla", parametros[6]);
		parametrosAuditoria.put("tipcomp", parametros[7]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en el proceso de anulacion de
	 * comprobante, tanto para ajuste como anulacion de planillas.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosAnulaComprobante(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("ruc", parametros[0]);
		parametrosAuditoria.put("sucursal", parametros[1]);
		parametrosAuditoria.put("anio", parametros[2]);
		parametrosAuditoria.put("mes", parametros[3]);
		parametrosAuditoria.put("codtippla", parametros[4]);
		parametrosAuditoria.put("esttippla", parametros[5]);
		parametrosAuditoria.put("tipcomp", parametros[6]);
		parametrosAuditoria.put("secpla", parametros[7]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento. Se usa en el proceso de ajustar planilla.
	 * 
	 * @return
	 */
	public static ParametroEvento ajustarPlanilla() {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");
		parametroEvento.setNombreDetalle("detallePlanilla");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se usa en el proceso de confirmar ajuste de
	 * planillas, y al momento de anular la planilla.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConfirmaAjusteAnulacionPlanilla(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("ruc", parametros[0]);
		parametrosAuditoria.put("sucursal", parametros[1]);
		parametrosAuditoria.put("anio", parametros[2]);
		parametrosAuditoria.put("mes", parametros[3]);
		parametrosAuditoria.put("codtippla", parametros[4]);
		parametrosAuditoria.put("esttippla", parametros[5]);
		parametrosAuditoria.put("secpla", parametros[6]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de una cedula. Es usado en procesos que solo tienen como parametro la cedula
	 * como: - Consultar por cedula cuenta bloqueada por intentos fallidos
	 * 
	 * @param cedula
	 * @return
	 */
	public static ParametroEvento obtenerParametrosCedula(String cedula) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", cedula);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se usa en el proceso de desbloqueo de cuenta por
	 * intentos fallidos.
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosDesbloquearCuenta(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("observ", parametros[1]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se usa en el proceso de confirmacion de datos con
	 * el registro civil
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConfirmacionDatosRC(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("coddact", parametros[1]);
		parametrosAuditoria.put("anio", parametros[2]);
		parametrosAuditoria.put("mes", parametros[3]);
		parametrosAuditoria.put("dia", parametros[4]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se usa en el proceso de confirmacion de datos con
	 * el registro civil
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosComprobante(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("usuario", parametros[0]);
		parametrosAuditoria.put("cedula", parametros[1]);
		parametrosAuditoria.put("clasePrestamo", parametros[2]);
		parametrosAuditoria.put("tipoPrestamo", parametros[3]);
		parametrosAuditoria.put("numPrestamo", parametros[4]);
		parametrosAuditoria.put("ordPrestamo", parametros[4]);
		parametrosAuditoria.put("tipoComprobante", parametros[4]);
		parametrosAuditoria.put("numComprobante", parametros[4]);
		parametrosAuditoria.put("valComprobante", parametros[4]);
		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	

	/**
	 * Devuelve un ParametroEvento, sin recibir parametros adicionales, pero deja instanciado el mapa de parametros
	 * 
	 * @return
	 */
	public static ParametroEvento obtenerParametrosVacio() {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado para el proceso de consulta de prestamos
	 * en estado PDA y PDC o PDV masivos
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosConsultaPrestamosMasivos(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedulas", parametros[0]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se usa en el proceso de auditor�a de comprobantes
	 * 
	 * @param parametros
	 * @return ParametroEvento
	 */
	public static ParametroEvento obtenerParametrosAuditoriaComprobante(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("usuario", parametros[0]);
		parametrosAuditoria.put("asegurado", parametros[1]);
		parametrosAuditoria.put("codprecla", parametros[2]);
		parametrosAuditoria.put("codpretip", parametros[3]);
		parametrosAuditoria.put("numpreafi", parametros[4]);
		parametrosAuditoria.put("codpreord", parametros[5]);
		if (parametros.length > 6) {
			parametrosAuditoria.put("tipcompag", parametros[6]);
			parametrosAuditoria.put("numcompag", parametros[7]);
		}
		if (parametros.length > 8) {
			parametrosAuditoria.put("valtotcomp", parametros[8]);
		}
		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Se usa en el proceso de auditor�a de liquidaci�n
	 * 
	 * @param parametros
	 * @return ParametroEvento
	 */
	public static ParametroEvento obtenerParametrosAuditoriaLiquidacion(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("usuario", parametros[0]);
		parametrosAuditoria.put("asegurado", parametros[1]);
		parametrosAuditoria.put("codprecla", parametros[2]);
		parametrosAuditoria.put("codpretip", parametros[3]);
		parametrosAuditoria.put("numpreafi", parametros[4]);
		parametrosAuditoria.put("codpreord", parametros[5]);
		parametrosAuditoria.put("tipoliq", parametros[6]);
		parametrosAuditoria.put("numliq", parametros[7]);
		if (parametros.length > 8) {
			parametrosAuditoria.put("valliq", parametros[8]);
			parametrosAuditoria.put("tipcompag", parametros[9]);
			parametrosAuditoria.put("numcompag", parametros[10]);
			parametrosAuditoria.put("valtotcomp", parametros[11]);
		}
		
		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

}
