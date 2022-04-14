package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.dto.AportacionesBiessRequestDto;
import ec.gov.iess.creditos.pq.dto.InformacionAportacionResponseDto;
import ec.gov.iess.creditos.pq.excepcion.AportacionesException;
import ec.gov.iess.creditos.pq.servicio.AportacionesServicioLocal;
import ec.gov.iess.creditos.pq.servicio.AportacionesServicioRemote;

/**
 * Servicio para verificacion de mora patronal del empleador
 * 
 * @author hugo.mora
 * @date 29 de mar. de 2016
 *
 */
@Stateless
public class AportacionesServicioImpl implements AportacionesServicioLocal, AportacionesServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger("AportacionesServicioImpl");
	
	private String urlBiessAportaciones = "";
	
	private Integer timeoutMoraPatronal;

	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	@PostConstruct
	public void init() {
		try {
			this.urlBiessAportaciones = this.parametroBiessDao.consultarPorIdNombreParametro("BIESS_INTEGRACION",
					"BIESS_APORTACIONES_URL").getValorCaracter();
			
			this.timeoutMoraPatronal = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.MORA_PATRONAL.getIdParametro(),
					ParametrosBiessEnum.MORA_PATRONAL.getNombreParametro()).getValorNumerico().intValue();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL aportaciones", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.AportacionesServicioLocal# obtenerInformacionAportaciones(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public InformacionAportacionResponseDto obtenerInformacionAportaciones(String cedula, String tipoCalculo, int numeroUltimasAportaciones)
			throws AportacionesException {
		InformacionAportacionResponseDto informacionAportacion = null;
		try {
			URL myUrl = new URL(this.urlBiessAportaciones);
			HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setConnectTimeout(this.timeoutMoraPatronal);
			connection.setReadTimeout(this.timeoutMoraPatronal);

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			final AportacionesBiessRequestDto aportacionesBiessRequest = new AportacionesBiessRequestDto();
			aportacionesBiessRequest.setCedula(cedula);
			aportacionesBiessRequest.setNumeroUltimasAportaciones(numeroUltimasAportaciones);
			aportacionesBiessRequest.setTipoCalculo(tipoCalculo);

			final String json = gson.toJson(aportacionesBiessRequest);
			writer.write(json);
			writer.close();
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}
			reader.close();
			connection.getResponseCode();

			final Type tipo = new TypeToken<InformacionAportacionResponseDto>() {
			}.getType();
			informacionAportacion = gson.fromJson(respuestaJson, tipo);

			if (informacionAportacion == null || (informacionAportacion != null && "1".equals(informacionAportacion.getRespuestaConsumo()))) {
				LOG.error("Error al consumir servicio de aportaciones: " + informacionAportacion == null ? "null"
						: informacionAportacion.getMensajeConsumo());
				throw new AportacionesException(informacionAportacion.getMensajeConsumo());
			}
		} catch (SocketTimeoutException e) {
			LOG.error("AportesIessTimeout-"+cedula);
			LOG.error("Timeout al verificar el numero de aportaciones consecutivas y acumuladas", e);
			throw new AportacionesException(e);
		} catch (IOException e) {
			LOG.error("AportesIessIoException-"+cedula);
			LOG.error("Error al consumir el servicio de aportaciones consecutivas y acumuladas", e);
			throw new AportacionesException(e);
		} catch (Exception e) {
			LOG.error("AportesIessGenerica-"+cedula);
			LOG.error("Error al verificar las aportaciones consecutivas y acumuladas", e);
			throw new AportacionesException(e);
		}
	    LOG.info("AportesIessExitoso-"+cedula);
		return informacionAportacion;
	}

}
