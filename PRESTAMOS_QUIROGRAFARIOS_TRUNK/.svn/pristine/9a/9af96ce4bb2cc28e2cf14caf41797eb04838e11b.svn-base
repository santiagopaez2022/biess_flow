/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.modelo.dto.ParametroResponseDto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioRemote;
import ec.gov.iess.creditos.pq.util.ParametroSingleton;

/**
 * Implementacion a los servicios locales y remotos para 
 * @author cristian.yaselga
 * 
 */
@Stateless
public class ConsultaParametroServicioImpl implements
		ConsultaParametroServicioLocal, ConsultaParametroServicioRemote {

	@EJB
	ParametroBiessDao parametroBiessDao;
	
	private static final String ID_PARAMETRO = "CONFIG-RS";
	private static final String NOMBRE_PARAMETRO_URL = "CONSULTAR_PARAMETRO";
	private static final String NOMBRE_PARAMETRO_TOKEN = "TOKEN_PQ-WEB";
	private static final String RESPUESTA_EXITOSA = "0";
	private transient static final Logger LOG = Logger.getLogger(ConsultaParametroServicioImpl.class);
	private static final String MENSAJE_ERROR = "Error al consumir el servicio de par\u00E1metros";
	
	private String uriBase;
	private String token;
	
	private ParametroSingleton singleton = ParametroSingleton.getInstancia();
	@Override
	public Integer consultarParametroEntero(String codigoParametro) throws ConsultaParametroException {
		try {
			return new BigDecimal(String.valueOf(consultarParametro(
					codigoParametro, "int"))).intValue();
		} catch (Exception e) {
			LOG.error(e);
			throw new ConsultaParametroException(MENSAJE_ERROR);
		}

	}
	
	@Override
	public BigDecimal consultarParametroDecimal(String codigoParametro) throws ConsultaParametroException {
		try {
			return new BigDecimal(String.valueOf(consultarParametro(
					codigoParametro, "double")));
		} catch (Exception e) {
			LOG.error(e);
			throw new ConsultaParametroException(MENSAJE_ERROR);
		}
	}
	
	@Override
	public String consultarParametroString(String codigoParametro) throws ConsultaParametroException {
		try {
			return String
					.valueOf(consultarParametro(codigoParametro, "string"));
		} catch (Exception e) {
			LOG.error(e);
			throw new ConsultaParametroException(MENSAJE_ERROR);
		}
	}
	
	@Override
	public Object consultarParametro(String codigoParametro, String tipoDato) throws ConsultaParametroException {
		Object valor = singleton.getParametrosMap().get(codigoParametro);
		if(valor != null){
			return valor;
		}
		try {
			if(uriBase == null){
				uriBase = parametroBiessDao
						.consultarPorIdNombreParametro(ID_PARAMETRO,
								NOMBRE_PARAMETRO_URL).getValorCaracter();
			}
			if(token == null){
			token = parametroBiessDao
					.consultarPorIdNombreParametro(ID_PARAMETRO,
							NOMBRE_PARAMETRO_TOKEN).getValorCaracter();
			}
			String urlServicio = uriBase + tipoDato + "/" + codigoParametro
					+ "/" + token;
			final URL myUrl = new URL(urlServicio);
			final HttpURLConnection connection = (HttpURLConnection) myUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final Gson gson = new Gson();

			final InputStreamReader reader = new InputStreamReader(
					connection.getInputStream());
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}

			reader.close();
			connection.getResponseCode();
			final Type tipo = new TypeToken<ParametroResponseDto>() {
			}.getType();
			final ParametroResponseDto respuesta = gson.fromJson(respuestaJson,
					tipo);

			if (respuesta != null) {
				if (RESPUESTA_EXITOSA.equals(respuesta.getCodigoRespuesta())) {
					singleton.getParametrosMap().put(codigoParametro, respuesta.getValor());
					return respuesta.getValor();
				}
			}

		} catch (ParametroBiessException e) {
			LOG.error(e);
			throw new ConsultaParametroException(MENSAJE_ERROR);
		} catch (MalformedURLException e) {
			LOG.error(e);
			throw new ConsultaParametroException(MENSAJE_ERROR);
		} catch (IOException e) {
			LOG.error(e);
			throw new ConsultaParametroException(MENSAJE_ERROR);
		}

		throw new ConsultaParametroException(MENSAJE_ERROR);
	}
}