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
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionGarantiasResponseDto;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioRemoto;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Servicio para obtener informacion de garantias comprometidas del SAC
 * 
 * @author hugo.mora
 * @date 2018/08/31
 *
 */
@Stateless
public class GarantiasSacServicioImpl implements GarantiasSacServicioLocal, GarantiasSacServicioRemoto {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(GarantiasSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlGarantiasSac;
	private static final String NOMBRE_PARAMETRO_URL = "URL_GARANTIAS_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlGarantiasSac = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de garantias comprometidas de SAC", e);
		}
	}

	/**
	 * Devuelve un objeto de tipo InformacionGarantiasResponseDto con informacion de
	 * las garantias comprometidas de fondos de reserva y fondos de cesantia desde
	 * core negocio, informacion que es proporcionada por el SAC
	 * 
	 * @param tipoIdentificacionSac
	 * @param numeroIdentificacion
	 * @param request
	 * @return
	 * @throws GarantiasSacException
	 */
	private InformacionGarantiasResponseDto obtenerGarantiasComprometidasSac(ClienteRequestDto request)
			throws GarantiasSacException {
		InformacionGarantiasResponseDto respuesta = null;
		try {
			String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(request, this.urlGarantiasSac);
			final Gson gson = new Gson();
			final Type tipo = new TypeToken<InformacionGarantiasResponseDto>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
				grabarLogPeticion(urlGarantiasSac, respuesta.getCodigoRespuesta(), respuesta.getMensajeRespuesta());
				throw new GarantiasSacException(FuncionesUtilesSac.asignarMensaje(respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta()), respuesta.getCodigoRespuesta());
			}
		} catch (PeticionSacException e) {
			throw new GarantiasSacException(e.getMensaje(), e.getCodigo());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal#obtenerGarantias(
	 * java.lang.String)
	 */
	public InformacionGarantias obtenerGarantias(String numeroIdentificacion) throws GarantiasSacException {
		ClienteRequestDto request = new ClienteRequestDto();
		request.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(numeroIdentificacion));
		request.setNumeroDocumento(numeroIdentificacion);
		return obtenerGarantiasComprometidasSac(request).getInformacionGarantiasDto();
	}

}
