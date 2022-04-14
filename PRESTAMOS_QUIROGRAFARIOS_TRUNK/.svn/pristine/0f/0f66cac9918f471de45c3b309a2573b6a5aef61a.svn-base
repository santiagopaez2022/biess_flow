package ec.gov.iess.creditos.pq.servicio.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.dto.DividendoDto;
import ec.gov.iess.creditos.pq.dto.InformacionTablaAmortizacionResponseDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.servicio.TablaAmortizacionOperacionSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.TablaAmortizacionOperacionSacServicioRemote;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Servicio para obtener informacion de la tabla de amortizacion desde core
 * negocio,
 * 
 * @author hugo.mora
 * @date 05/09/2018
 *
 */
@Stateless
public class TablaAmortizacionOperacionSacServicioImpl
		implements TablaAmortizacionOperacionSacServicioLocal, TablaAmortizacionOperacionSacServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(TablaAmortizacionOperacionSacServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private PeticionRestSacServicio peticionRestSacServicio;

	private String urlTablaAmortizacion;
	private static final String NOMBRE_PARAMETRO_URL = "URL_TABLA_AMORTIZACION_OPERACION_SAC";

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlTablaAmortizacion = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de tabla de amortizacion de SAC", e);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private InformacionTablaAmortizacionResponseDto consultarServicioTablaAmortizacionSac(OperacionSacRequest request)
			throws TablaAmortizacionSacException {
		InformacionTablaAmortizacionResponseDto response = null;
		try {
			String respuestaJson = peticionRestSacServicio.obtenerRespuestaSac(request, this.urlTablaAmortizacion);
			final Gson gson = new Gson();
			final Type tipo = new TypeToken<InformacionTablaAmortizacionResponseDto>() {
			}.getType();
			response = gson.fromJson(respuestaJson, tipo);
			if (!ConstantesSAC.RESPUESTA_EXITOSA.equals(response.getCodigoRespuesta())) {
				grabarLogPeticion(urlTablaAmortizacion, response.getCodigoRespuesta(), response.getMensajeRespuesta());
				throw new TablaAmortizacionSacException(FuncionesUtilesSac.asignarMensaje(response.getCodigoRespuesta(),
						response.getMensajeRespuesta()), response.getCodigoRespuesta());
			}

		} catch (PeticionSacException e) {
			throw new TablaAmortizacionSacException(e.getMensaje(), e.getCodigo());
		}
		return response;
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
	 * @see ec.gov.iess.creditos.pq.servicio.TablaAmortizacionSacServicioLocal#
	 * obtenerTablaAmortizacionSac(ec.gov.iess.
	 * creditos.pq.dto.TablaAmortizacionSacRequestDto)
	 */
	@Override
	public List<DividendoDto> obtenerTablaAmortizacionOperacionSac(OperacionSacRequest request)
			throws TablaAmortizacionSacException {
		InformacionTablaAmortizacionResponseDto response = this.consultarServicioTablaAmortizacionSac(request);
		if (response != null && response.getTablaAmortizacionSac() != null
				&& !response.getTablaAmortizacionSac().getListaDividendos().isEmpty()) {
			return response.getTablaAmortizacionSac().getListaDividendos();
		} else {
			return new ArrayList<DividendoDto>();
		}
	}

	@Override
	public TablaAmortizacionSac obtenerTablaAmortizacionSac(OperacionSacRequest request)
			throws TablaAmortizacionSacException {
		return this.consultarServicioTablaAmortizacionSac(request).getTablaAmortizacionSac();
	}

}
