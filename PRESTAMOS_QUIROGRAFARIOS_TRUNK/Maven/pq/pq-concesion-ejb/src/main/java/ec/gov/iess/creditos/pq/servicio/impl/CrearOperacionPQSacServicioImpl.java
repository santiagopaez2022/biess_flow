package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

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
import ec.gov.iess.creditos.pq.excepcion.CreacionOperacionSacException;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.CreditoPQCrearOperacionSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQCrearOperacionSacServicioRemoto;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Crear una operacion en el SAC.
 * 
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
@Stateless
public class CrearOperacionPQSacServicioImpl
		implements CreditoPQCrearOperacionSacServicioLocal, CreditoPQCrearOperacionSacServicioRemoto {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(CrearOperacionPQSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlCrearOperacion;
	private static final String NOMBRE_PARAMETRO_URL = "URL_CREACIONOPERACION_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlCrearOperacion = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de garantias comprometidas de SAC", e);
		}
	}

	/**
	 * Devuelve un objeto de tipo InformacionPQExigibleResponseDto con informacion
	 * de PQ Exigibles de core negocio.
	 * 
	 * @param request
	 * @return
	 */
	private OperacionSacResponse crearOperacionPQSac(OperacionSacRequest operacionSacRequest)
			throws CreacionOperacionSacException {
		OperacionSacResponse respuesta = null;
		try {

			respuesta = obtenerRespuestaCrearOp(operacionSacRequest);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
				grabarLogPeticion(this.urlCrearOperacion, respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta());
				throw new CreacionOperacionSacException(FuncionesUtilesSac.asignarMensaje(respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta()), respuesta.getCodigoRespuesta());
			}

		} catch (PeticionSacException e) {
			throw new CreacionOperacionSacException(e.getMensaje(), e.getCodigo());
		}

		return respuesta;
	}

	@Override
	public CreditoExigibleDto obtenerNumeroOperacion(OperacionSacRequest operacionSacRequest) throws CreacionOperacionSacException {
		return crearOperacionPQSac(operacionSacRequest).getOperacionResponse();
	}

	/**
	 * Obtiene la respuesta desde el servicio rest
	 * 
	 * @param operacionSacRequest
	 * @return
	 * @throws PeticionSacException
	 * @throws SocketTimeoutException
	 * @throws IOException
	 * @throws Exception
	 */
	private OperacionSacResponse obtenerRespuestaCrearOp(OperacionSacRequest operacionSacRequest)
			throws PeticionSacException {
		OperacionSacResponse respuesta = null;
		String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(operacionSacRequest, this.urlCrearOperacion);
		final Gson gson = new Gson();
		final Type tipo = new TypeToken<OperacionSacResponse>() {
		}.getType();
		respuesta = gson.fromJson(respuestaJson, tipo);
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

}
