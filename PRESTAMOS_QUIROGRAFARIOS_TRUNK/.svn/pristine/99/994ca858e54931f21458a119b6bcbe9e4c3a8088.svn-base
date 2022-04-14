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
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigibleResponseDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Servicio para consumo de creditos pq exigibles desde core negocio
 * 
 * @author hugo.mora
 * @date 2018/09/03
 *
 */
@Stateless
public class CreditoPQOpEmplSacServicioImpl implements CreditoPQEmpSacServicioLocal {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(CreditoPQOpEmplSacServicioImpl.class);

	@EJB
	private transient ParametroBiessDao parametroBiessDao;

	@EJB
	private transient PeticionRestSacServicio peticionRestSacServicio;

	private String urlPQExigible;
	private static final String NOMBRE_PARAMETRO_URL = "URL_PQ_OPERACION_CLIENTES_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlPQExigible = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (final ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de operaciones empleador", e);
		}
	}

	/**
	 * Devuelve un objeto de tipo InformacionPQExigibleResponseDto con informacion
	 * de PQ Exigibles de core negocio.
	 * 
	 * @param request
	 * @return
	 */
	private InformacionPQExigibleResponseDto obtenerInformacionPQExigible(final OperacionSacRequest operacionSac)
			throws PQExigibleException {
		InformacionPQExigibleResponseDto respuesta = null;
		try {
			final String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(operacionSac, this.urlPQExigible);
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
	 * Permite logear cuando sucede algun error en la peticion de los servicios rest
	 * 
	 * @param urlServicio
	 * @param codigo
	 * @param mensaje
	 */
	private void grabarLogPeticion(final String urlServicio, final String codigo, final String mensaje) {
		final StringBuilder mensajeLog = new StringBuilder("Error al consumir servicio: ").append(urlServicio)
				.append(", codigo: ").append(codigo).append(", mensaje: ").append(mensaje);
		LOG.error(mensajeLog);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal#
	 * obtenerListaPQExigibles(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CreditoExigibleDto> obtenerListaPQOperEmp(final String identificacion) throws PQExigibleException {
		final ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(identificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(identificacion));
		final OperacionSacRequest operacionSac = new OperacionSacRequest();
		operacionSac.setCliente(afiliado);
		final OperacionRequestDto operacion = new OperacionRequestDto();
		operacion.setFechaSimulacion("");
		operacionSac.setOperacion(operacion);
		final InformacionPQExigibleResponseDto response = obtenerInformacionPQExigible(operacionSac);

		return response.getInformacionPQExigible().getListaCreditosExigible();
	}

	@Override
	public InformacionPQExigible obtenerInfoPqOperEmp(final String identificacion) throws PQExigibleException {
		final ClienteRequestDto afiliado = new ClienteRequestDto();
		afiliado.setNumeroDocumento(identificacion);
		afiliado.setTipoDocumento(FuncionesUtilesSac.obtenerTipoIdentificacionSac(identificacion));
		final OperacionSacRequest operacionSac = new OperacionSacRequest();
		operacionSac.setCliente(afiliado);

		return obtenerInformacionPQExigible(operacionSac).getInformacionPQExigible();
	}

}
