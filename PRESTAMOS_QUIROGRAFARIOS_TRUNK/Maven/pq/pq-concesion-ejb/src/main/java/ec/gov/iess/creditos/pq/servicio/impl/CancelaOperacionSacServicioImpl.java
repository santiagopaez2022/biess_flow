package ec.gov.iess.creditos.pq.servicio.impl;

import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.OperacionSacResponse;
import ec.gov.iess.creditos.pq.excepcion.CancelacionOperacionSacException;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.CancelaOperacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.CancelaOperacionSacServicioRemoto;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Servicio para cancelar la liquidacion de un credito del core negocio
 * 
 * @author roberto.guizado
 * @date 2018/10/12
 *
 */
@Stateless
public class CancelaOperacionSacServicioImpl implements CancelaOperacionSacServicio, CancelaOperacionSacServicioRemoto {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(CancelaOperacionSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlCancelacionOperacion;
	private static final String NOMBRE_PARAMETRO_URL = "URL_CANCELAOPERACION_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlCancelacionOperacion = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de Liquidacion de SAC", e);
		}
	}

	/**
	 * Devuelve un objeto de tipo InformacionPQExigibleResponseDto con informacion
	 * de PQ Exigibles de core negocio.
	 * 
	 * @param request
	 * @return
	 */
	private OperacionSacResponse obtenerLiquidacionCredito(OperacionSacRequest request)
			throws CancelacionOperacionSacException {
		OperacionSacResponse respuesta = null;
		try {
			String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(request, this.urlCancelacionOperacion);
			final Gson gson = new Gson();
			final Type tipo = new TypeToken<OperacionSacResponse>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
				grabarLogPeticion(urlCancelacionOperacion, respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta());
				throw new CancelacionOperacionSacException(FuncionesUtilesSac
						.asignarMensaje(respuesta.getCodigoRespuesta(), respuesta.getMensajeRespuesta()),
						respuesta.getCodigoRespuesta());
			}
		} catch (PeticionSacException e) {
			throw new CancelacionOperacionSacException(e.getMensaje(), e.getCodigo());
		}

		return respuesta;
	}

	/**
	 * Permite logear cuando sucede algun error en la peticion de los servicios rest
	 * 
	 * @param urlServicio
	 * @param codigo
	 * @param mensaje
	 */
	private void grabarLogPeticion(String urlServicio, String codigo, String mensaje) {
		StringBuilder mensajeLog = new StringBuilder("Error al consumir servicio: ").append(urlServicio)
				.append(", codigo: ").append(codigo).append(", mensaje: ").append(mensaje);
		LOG.error(mensajeLog);
	}

	@Override
	public CreditoExigibleDto cancelarCredito(OperacionSacRequest operacionSacRequest)
			throws CancelacionOperacionSacException {
		return obtenerLiquidacionCredito(operacionSacRequest).getOperacionResponse();
	}

}
