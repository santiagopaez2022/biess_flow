package ec.gov.iess.creditos.pq.servicio.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
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
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigibleResponseDto;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioRemoto;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;



/**
 * The Class CreditoPQExigibleSacServicioImpl.
 *
 * @author paul.sampedro
 */
@Stateless
public class CreditoPQExigibleSacServicioImpl
		implements CreditoPQExigibleSacServicioLocal, CreditoPQExigibleSacServicioRemoto {

	/** The Constant LOG. */
	private static final LoggerBiess LOG = LoggerBiess.getLogger(CreditoPQExigibleSacServicioImpl.class);

	/** The parametro biess dao. */
	@EJB
	private transient ParametroBiessDao parametroBiessDao;

	/** The peticion rest sac servicio. */
	@EJB
	private transient PeticionRestSacServicio peticionRestSacServicio;

	/** The url PQ exigible. */
	private String urlPQExigible;
	
	/** The Constant NOMBRE_PARAMETRO_URL. */
	private static final String NOMBRE_PARAMETRO_URL = "URL_PQEXIGIBLES_SAC";

	/**
	 * Inicializacion de variables.
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlPQExigible = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (final ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de garantias comprometidas de SAC", e);
		}
	}

	/**
	 * Devuelve un objeto de tipo InformacionPQExigibleResponseDto con informacion
	 * de PQ Exigibles de core negocio.
	 *
	 * @param request the request
	 * @return the informacion PQ exigible response dto
	 * @throws PQExigibleException the PQ exigible exception
	 */
	private InformacionPQExigibleResponseDto obtenerInformacionPQExigible(final ClienteRequestDto request)
			throws PQExigibleException {
		InformacionPQExigibleResponseDto respuesta = null;
		try {
			final String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(request, this.urlPQExigible);
			final Gson gson = new Gson();
			final Type tipo = new TypeToken<InformacionPQExigibleResponseDto>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
				grabarLogPeticion(urlPQExigible, respuesta.getCodigoRespuesta(), respuesta.getMensajeRespuesta());
				throw new PQExigibleException(FuncionesUtilesSac.asignarMensaje(respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta()), respuesta.getCodigoRespuesta());
			}
		} catch (final PeticionSacException e) {
			throw new PQExigibleException(e.getMensaje(), e.getCodigo());
		}
		return respuesta;
	}

	/**
	 * Permite logear cuando sucede algun error en la peticion de los servicios rest.
	 *
	 * @param urlServicio the url servicio
	 * @param codigo the codigo
	 * @param mensaje the mensaje
	 */
	private void grabarLogPeticion(final String urlServicio, final String codigo, final String mensaje) {
		final StringBuilder mensajeLog = new StringBuilder("Error al consumir servicio: ").append(urlServicio)
				.append(", codigo: ").append(codigo).append(", mensaje: ").append(mensaje);
		LOG.error(mensajeLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal#tieneMora(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean tieneMora(final String numeroIdentificacion) throws PQExigibleException {
		final ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(numeroIdentificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(numeroIdentificacion));
		final InformacionPQExigibleResponseDto response = obtenerInformacionPQExigible(afiliado);
		return response.getInformacionPQExigible().isTieneMora();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal#
	 * obtenerSaldoCreditos(java.lang.String, java.lang.String)
	 */
	@Override
	public BigDecimal obtenerSaldoCreditos(final String numeroIdentificacion) throws PQExigibleException {
		final ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(numeroIdentificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(numeroIdentificacion));
		final InformacionPQExigibleResponseDto response = obtenerInformacionPQExigible(afiliado);
		return response.getInformacionPQExigible().getValorSaldoCreditos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal#
	 * obtenerListaPQExigibles(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CreditoExigibleDto> obtenerListaPQExigibles(final String numeroIdentificacion) throws PQExigibleException {
		final ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(numeroIdentificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(numeroIdentificacion));
		final InformacionPQExigibleResponseDto response = obtenerInformacionPQExigible(afiliado);
		return response.getInformacionPQExigible().getListaCreditosExigible();
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal#obtenerInfoPqExigible(java.lang.String)
	 */
	@Override
	public InformacionPQExigible obtenerInfoPqExigible(final String numeroIdentificacion)
			throws PQExigibleException {
		final ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(numeroIdentificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(numeroIdentificacion));
		return obtenerInformacionPQExigible(afiliado).getInformacionPQExigible();
	}

}
