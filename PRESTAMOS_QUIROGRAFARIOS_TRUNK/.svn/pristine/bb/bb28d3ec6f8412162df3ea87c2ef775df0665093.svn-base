package ec.gov.iess.pq.concesion.web.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.iess.creditos.dinamico.dto.DataNotificaProvAprRequestDto;

/**
 * Metodos usados para manejo de parametros para la auditoria
 * 
 * @author hugo.mora
 * @date 2017/04/25
 *
 */
public class AuditoriaPqWebHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Devuelve un ParametroEvento a partir de una cedula. Es usado en procesos que solo tienen como parametro la cedula
	 * como: - Login - Logout
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
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en procesos que auditan la cedula del
	 * asegurado y el numero de prestamo
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosCedulaCredito(String... parametros) {
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
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en procesos que auditan la cedula del
	 * asegurado, indicador de proceso exitoso y una observacion
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosCedulaExitoObservacion(String... parametros) {
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
	 * Devuelve un ParametroEvento a partir de un varargs de String. Se usa en el proceso de auditoria de los requisitos
	 * de precalificacion para concesion y novacion
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosRequisitosPrecalificacion(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");
		parametroEvento.setNombreDetalle("requisitos");
		
		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("exito", parametros[1]);
		
		parametroEvento.setParametros(parametrosAuditoria);
		
		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de la cedula, el codigo de reserva y el numero de intento. Se usa en la
	 * verificacion del codigo de reserva para creditos Ecuador tu lugar en el mundo
	 * 
	 * @param cedula
	 * @param codigoReserva
	 * @param numeroIntento
	 * @return
	 */
	public static ParametroEvento obtenerParametrosReservaEcuadorTurismo(String cedula, String codigoReserva, int numeroIntento,
			String descripcionReserva, BigDecimal montoReserva) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", cedula);
		parametrosAuditoria.put("codreserv", codigoReserva);
		parametrosAuditoria.put("numintent", String.valueOf(numeroIntento));
		parametrosAuditoria.put("desresev", descripcionReserva == null ? "null" : descripcionReserva);
		parametrosAuditoria.put("montores", montoReserva == null ? "null" : montoReserva.toString());

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de la cedula, el plazo, tipo de tabla de amortizacion y monto. Se usa en el
	 * calculo de cuota mensual
	 * 
	 * @param cedula
	 * @param plazo
	 * @param tipoTablaAmortizacion
	 * @param monto
	 * @return
	 */
	public static ParametroEvento obtenerParametrosAceptaCondicionesEcuadorTurismo(String cedula, Integer plazo, String tipoTablaAmortizacion,
			BigDecimal monto, String numeroReserva) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", cedula);
		parametrosAuditoria.put("plazo", plazo == null ? "null" : String.valueOf(plazo));
		parametrosAuditoria.put("tiptabamo", tipoTablaAmortizacion);
		parametrosAuditoria.put("monto", monto == null ? "null" : monto.setScale(2, RoundingMode.HALF_UP).toString());
		parametrosAuditoria.put("numres", numeroReserva);
		parametrosAuditoria.put("exito", "true");

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	/**
	 * Devuelve un ParametroEvento a partir de un varargs de Strings. Es usado en procesos que auditan el proceso de
	 * rechazo de un credito de Ecuador tu lugar en el mundo y cuando se reimprime el formulario de autorizacion de
	 * transferencia de fondos
	 * 
	 * @param parametros
	 * @return
	 */
	public static ParametroEvento obtenerParametrosRechazaReimprimePQEcuadorTurismo(String... parametros) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("cedula", parametros[0]);
		parametrosAuditoria.put("codprecla", parametros[1]);
		parametrosAuditoria.put("codpretip", parametros[2]);
		parametrosAuditoria.put("numpreafi", parametros[3]);
		parametrosAuditoria.put("ordpreafi", parametros[4]);
		parametrosAuditoria.put("numres", parametros[5]);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

	/**
	 * Metodo que obtiene parametros para guardar la auditoria
	 * 
	 * @param dataNotificaProv
	 * @param descError
	 * @return
	 */
	public static ParametroEvento obtenerParametrosEnvioNotiProveedor(DataNotificaProvAprRequestDto dataNotificaProv,
			String descError) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("codContrat", dataNotificaProv.getCodigoContrato());
		parametrosAuditoria.put("rucEmpDest", dataNotificaProv.getRucEmpresa());
		parametrosAuditoria.put("crespecial", dataNotificaProv.getCodigoEspecial().toString());
		parametrosAuditoria.put("codprod", dataNotificaProv.getCodigoProducto().toString());
		parametrosAuditoria.put("fechaApr", dataNotificaProv.getFechaAprobacion().toString());
		parametrosAuditoria.put("numPrestam", dataNotificaProv.getNumeroPrestamo().toString());
		parametrosAuditoria.put("valorProduct", dataNotificaProv.getValorPaquete().toString());
		parametrosAuditoria.put("descError", descError);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}
	
	public static ParametroEvento obtenerParamIntentos(DataNotificaProvAprRequestDto dataNotificaProv,
			String descError) {
		ParametroEvento parametroEvento = new ParametroEvento();
		parametroEvento.setNombre("parametros");

		Map<String, String> parametrosAuditoria = new HashMap<String, String>();
		parametrosAuditoria.put("codContrat", dataNotificaProv.getCodigoContrato());
		parametrosAuditoria.put("rucEmpDest", dataNotificaProv.getRucEmpresa());
		parametrosAuditoria.put("crespecial", dataNotificaProv.getCodigoEspecial().toString());
		parametrosAuditoria.put("codprod", dataNotificaProv.getCodigoProducto().toString());
		parametrosAuditoria.put("fechaApr", dataNotificaProv.getFechaAprobacion().toString());
		parametrosAuditoria.put("numPrestam", dataNotificaProv.getNumeroPrestamo().toString());
		parametrosAuditoria.put("valorProduct", dataNotificaProv.getValorPaquete().toString());
		parametrosAuditoria.put("descError", descError);

		parametroEvento.setParametros(parametrosAuditoria);

		return parametroEvento;
	}

}
