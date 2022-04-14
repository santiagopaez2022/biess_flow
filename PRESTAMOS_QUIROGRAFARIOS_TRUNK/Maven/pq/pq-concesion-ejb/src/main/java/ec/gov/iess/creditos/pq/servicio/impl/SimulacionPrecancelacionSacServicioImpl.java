package ec.gov.iess.creditos.pq.servicio.impl;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigibleResponseDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionPrecancelacionSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.SimulacionPrecancelacionSacServicioRemoto;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Servicio para consumo de creditos pq exigibles desde core negocio
 * 
 * @author roberto.guizado
 * @date 2018/11/19
 *
 */
@Stateless
public class SimulacionPrecancelacionSacServicioImpl
		implements SimulacionPrecancelacionSacServicioLocal, SimulacionPrecancelacionSacServicioRemoto {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(SimulacionPrecancelacionSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlPQExigible;
	private static final String NOMBRE_PARAMETRO_URL = "URL_SIMULACION_PRECANCELACION_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlPQExigible = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de garantias comprometidas de SAC", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.SimulacionPrecancelacionSacServicioLocal#
	 * obtenerListaExigiblePrecancelacion(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CreditoExigibleDto> obtenerListaExigiblePrecancelacion(String numeroIdentificacion)
			throws PQExigibleException {
		OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
		ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(numeroIdentificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(numeroIdentificacion));
		OperacionRequestDto operacion = new OperacionRequestDto();
		try {
			operacion.setFechaSimulacion(FuncionesUtilesSac.obtenerFechaSac(new Date()));
		} catch (ParseException e) {
			LOG.error("Error al parsear la fecha simulacion precancelacion envio SAC", e);
		}
		operacionSacRequest.setCliente(afiliado);
		operacionSacRequest.setOperacion(operacion);
		InformacionPQExigibleResponseDto response = obtenerInformacionPrecancelacionExigible(operacionSacRequest);
		return response.getInformacionPQExigible().getListaCreditosExigible();
	}

	/**
	 * Devuelve un objeto de tipo InformacionPQExigibleResponseDto con informacion
	 * de PQ Exigibles de core negocio.
	 * 
	 * @param request
	 * @return
	 */
	private InformacionPQExigibleResponseDto obtenerInformacionPrecancelacionExigible(OperacionSacRequest request)
			throws PQExigibleException {
		InformacionPQExigibleResponseDto respuesta = null;
		try {
			String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(request, this.urlPQExigible);
			final Gson gson = new Gson();
			final Type tipo = new TypeToken<InformacionPQExigibleResponseDto>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
				grabarLogPeticion(urlPQExigible, respuesta.getCodigoRespuesta(), respuesta.getMensajeRespuesta());
				throw new PQExigibleException(FuncionesUtilesSac.asignarMensaje(respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta()), respuesta.getCodigoRespuesta());
			}
		} catch (PeticionSacException e) {
			throw new PQExigibleException(e.getCodigo(), e.getMensaje());
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

}
