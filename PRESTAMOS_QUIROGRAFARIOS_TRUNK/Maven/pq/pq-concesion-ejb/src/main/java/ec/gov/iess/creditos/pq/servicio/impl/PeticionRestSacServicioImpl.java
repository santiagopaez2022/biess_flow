/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;
import ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;

/**
 * @author roberto.guizado
 *
 */
@Stateless
public class PeticionRestSacServicioImpl implements PeticionRestSacServicio {

	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	private static final LoggerBiess LOG = LoggerBiess.getLogger(PeticionRestSacServicioImpl.class);
	/**
	 * Timeout
	 */
	private int timeOut;
	/**
	 * Codigo de respuesta servicio OK 200 HTTP
	 */
	private static final int HTT_200_OK = 200;
	
	@PostConstruct
	public void init() {
		try {
			this.timeOut = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, ConstantesSAC.TIME_OUT_REST_SAC)
					.getValorNumerico().intValue();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro "+ ConstantesSAC.TIME_OUT_REST_SAC, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PeticionRestSacServicio#obtenerRespuestaSac(
	 * java.lang.Object, java.lang.String)
	 */
	@Override
	public String obtenerRespuestaSac(Object request, String urlServicioRest) throws PeticionSacException {
		String guid = java.util.UUID.randomUUID().toString().replace("-", "");
		StringBuilder logeoError = new StringBuilder();
		try {
			URL myUrl = new URL(urlServicioRest);
			HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("guid", guid);
			connection.setRequestProperty("user", "usrpqweb");
			connection.setConnectTimeout(timeOut);
			connection.setReadTimeout(timeOut);

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

			final Gson gson = new Gson();

			final String json = gson.toJson(request);
			writer.write(json);
			writer.close();
			logeoError.append("Peticion json:").append(json);
			if (connection.getResponseCode() != HTT_200_OK) {
				StringBuilder mensajeLog = new StringBuilder("Fallo : HTTP CodigoError : ")
						.append(connection.getResponseCode()).append(" peticionjson=").append(json).append(" guid=")
						.append(guid).append("url=").append(urlServicioRest);
				LOG.error(mensajeLog.toString());
				throw new PeticionSacException(ConstantesSAC.MENSAJE_PERDIDA_RESPUESTA+" id:"+guid,
						ConstantesSAC.COD_PERDIDA_RESPUESTA);
			}
			final InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			final BufferedReader bufferedReader = new BufferedReader(reader);
			String respuestaJson = "";
			String respuestString = "";
			while ((respuestString = bufferedReader.readLine()) != null) {
				respuestaJson += respuestString;
			}

			reader.close();
			connection.getResponseCode();
			return respuestaJson;
		} catch (SocketTimeoutException e) {
			StringBuilder mensajeLog = new StringBuilder("Timeout al consumir servicio: ").append(urlServicioRest)
					.append(logeoError);
			LOG.error(mensajeLog, e);
			throw new PeticionSacException(ConstantesSAC.MENSAJE_PERDIDA_RESPUESTA+" id:"+guid,
					ConstantesSAC.COD_PERDIDA_RESPUESTA);
		} catch (IOException e) {
			StringBuilder mensajeLog = new StringBuilder("Error al leer respuesta del servicio: ")
					.append(urlServicioRest).append(logeoError);
			LOG.error(mensajeLog, e);
			throw new PeticionSacException(ConstantesSAC.MENSAJE_VALOR_NULO + " id:" + guid,
					ConstantesSAC.COD_VALOR_NULO);
		}
	}

}
