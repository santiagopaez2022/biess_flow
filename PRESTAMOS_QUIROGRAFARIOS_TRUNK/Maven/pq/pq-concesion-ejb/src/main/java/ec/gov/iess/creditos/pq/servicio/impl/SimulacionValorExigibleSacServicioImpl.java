package ec.gov.iess.creditos.pq.servicio.impl;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.DatoDeudaDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.OperacionSacResponse;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.excepcion.SimulacionValorExigibleException;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionValorExigibleSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.SimulacionValorExigibleSacServicioRemoto;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

@Stateless
public class SimulacionValorExigibleSacServicioImpl
		implements SimulacionValorExigibleSacServicioLocal, SimulacionValorExigibleSacServicioRemoto {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(SimulacionValorExigibleSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlSimularValorExigible;
	private static final String NOMBRE_PARAMETRO_URL = "URL_SIMULARVALOREXIGLE_SAC";

	@PostConstruct
	public void init() {
		try {
			this.urlSimularValorExigible = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de prestamos cancelados de SAC", e);
		}
	}

	private OperacionSacResponse obtieneSimulacionValorExigible(OperacionSacRequest request)
			throws SimulacionValorExigibleException {

		OperacionSacResponse respuesta = null;
		try {
			String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(request, this.urlSimularValorExigible);
			final Gson gson = new Gson();
			final Type tipo = new TypeToken<OperacionSacResponse>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
				grabarLogPeticion(urlSimularValorExigible, respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta());
				throw new SimulacionValorExigibleException(FuncionesUtilesSac
						.asignarMensaje(respuesta.getCodigoRespuesta(), respuesta.getMensajeRespuesta()),
						respuesta.getCodigoRespuesta());
			}
		} catch (PeticionSacException e) {
			throw new SimulacionValorExigibleException(e.getMensaje(), e.getCodigo());
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
	public List<DatoDeudaDto> obtenerSimulacionValorExigible(OperacionSacRequest operacion)
			throws SimulacionValorExigibleException {
		OperacionSacResponse response = obtieneSimulacionValorExigible(operacion);
		return response.getOperacionResponse().getDatosDeuda();
	}

	@Override
	public CreditoExigibleDto obtenerSimulacionExigibles(OperacionSacRequest operacion)
			throws SimulacionValorExigibleException {
		OperacionSacResponse response = obtieneSimulacionValorExigible(operacion);
		return response.getOperacionResponse();
	}

}
