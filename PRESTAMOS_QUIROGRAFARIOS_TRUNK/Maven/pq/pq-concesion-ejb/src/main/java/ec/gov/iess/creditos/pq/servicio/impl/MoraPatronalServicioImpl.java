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
import ec.gov.iess.creditos.pq.dto.MoraPatronalBiessRequestDto;
import ec.gov.iess.creditos.pq.dto.MoraPatronalBiessResponseDto;
import ec.gov.iess.creditos.pq.excepcion.ResumenConsolidadoException;
import ec.gov.iess.creditos.pq.servicio.MoraPatronalServicioLocal;
import ec.gov.iess.creditos.pq.servicio.MoraPatronalServicioRemote;

/**
 * Servicio para consumir el servicio rest BIESS para verificar si el empleador tiene mora patronal
 * 
 * @author hugo.mora
 * @date 29 de mar. de 2016
 *
 */
@Stateless
public class MoraPatronalServicioImpl implements MoraPatronalServicioLocal, MoraPatronalServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger("MoraPatronalServicioImpl");
	
	private String urlBiessMora = "";
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	private Integer timeoutMoraPatronal;
	
	@PostConstruct
	public void init() {
		try {
			this.urlBiessMora = this.parametroBiessDao.consultarPorIdNombreParametro("MORA_PATRONAL_BIESS",
					"URL_MORA_BIESS").getValorCaracter();
			
			this.timeoutMoraPatronal = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.MORA_PATRONAL.getIdParametro(),
					ParametrosBiessEnum.MORA_PATRONAL.getNombreParametro()).getValorNumerico().intValue();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL mora patronal", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.MoraPatronalServicioLocal#tieneMoraPatronal(java.lang.String,
	 * java.lang.String)
	 */
	public boolean tieneMoraPatronal(String rucEmpleador, String codigoSucursal) throws ResumenConsolidadoException {
		boolean tieneMora = false;
		try {
			URL myUrl = new URL(this.urlBiessMora);
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
			final MoraPatronalBiessRequestDto requestIESS = new MoraPatronalBiessRequestDto();

			requestIESS.setRucEmpleador(rucEmpleador);
			requestIESS.setCodigoSucursal(codigoSucursal);

			final String json = gson.toJson(requestIESS);
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
			final Type tipo = new TypeToken<MoraPatronalBiessResponseDto>() {
			}.getType();
			final MoraPatronalBiessResponseDto respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if(respuesta.isTieneMoraPatronal()){
					tieneMora = true;
				}

				// Verificamos si tiene un mensaje de error
				if (respuesta.getMensajeError() != null) {
					throw new ResumenConsolidadoException(respuesta.getMensajeError());
				}
			}
		} catch (SocketTimeoutException e) {
			LOG.error("Timeout al consumir el servicio web de mora patronal", e);
			throw new ResumenConsolidadoException(e);
		} catch (IOException e) {
			LOG.error("Error al consumir el servicio web de mora patronal", e);
			throw new ResumenConsolidadoException(e);
		} catch (Exception e) {
			LOG.error("Error al verificar si empleador tiene mora patronal", e);
			throw new ResumenConsolidadoException(e);
		}

		return tieneMora;
	}

}
