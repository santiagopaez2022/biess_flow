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
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.OperacionSacResponse;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.DatoOperacionSacLocal;
import ec.gov.iess.creditos.pq.servicio.DatoOperacionSacRemote;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Servicio para consultar la liquidacion de un credito del core negocio
 * 
 * @author roberto.guizado
 * @date 2018/10/12
 *
 */
@Stateless
public class DatoOperacionSacServicioImpl implements DatoOperacionSacLocal, DatoOperacionSacRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(DatoOperacionSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlConsultaDatoOperacion;
	private static final String NOMBRE_PARAMETRO_URL = "URL_CONSULTAR_DATA_OPERACION_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlConsultaDatoOperacion = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de Liquidacion de SAC", e);
		}
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

	/**
	 * Permite obtener los datos de la operacion
	 * 
	 * @param operacion
	 * @return
	 * @throws PeticionSacException
	 */
	private OperacionSacResponse devolverDatoOperacion(OperacionSacRequest operacion) throws PeticionSacException {
		OperacionSacResponse respuesta = null;

		String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(operacion, this.urlConsultaDatoOperacion);
		final Gson gson = new Gson();
		final Type tipo = new TypeToken<OperacionSacResponse>() {
		}.getType();
		respuesta = gson.fromJson(respuestaJson, tipo);
		if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
			grabarLogPeticion(urlConsultaDatoOperacion, respuesta.getCodigoRespuesta(),
					respuesta.getMensajeRespuesta());
			throw new PeticionSacException(
					FuncionesUtilesSac.asignarMensaje(respuesta.getCodigoRespuesta(), respuesta.getMensajeRespuesta()),
					respuesta.getCodigoRespuesta());
		}
		return respuesta;
	}

	@Override
	public OperacionSacResponse obtenerDatosOperacion(OperacionSacRequest operacion) throws PeticionSacException {
		return devolverDatoOperacion(operacion);
	}

	@Override
	public String obtenerEstadoOperacion(OperacionSacRequest operacion) throws PeticionSacException {
		return devolverDatoOperacion(operacion).getOperacionResponse().getEstado();
	}

}
