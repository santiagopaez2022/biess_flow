/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.iess.creditos.pq.dto.migracion.cartera.FechaEfectivaDto;
import ec.gov.iess.creditos.pq.servicio.ConsultaFechaEfectivaGafLocal;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;

/**
 * Consulta fecha efectiva GAF
 * @author PAUL
 *
 */
@Stateless
public class ConsultaFechaEfectivaGafImpl implements
ConsultaFechaEfectivaGafLocal  {




	/**
	 * parametro
	 */
	@EJB
	ParametroBiessDao parametroBiessDao;
	

	/**
	 * LOG
	 */
	private transient static final Logger LOG = Logger.getLogger(ConsultaFechaEfectivaGafImpl.class);
	/**
	 * Url de fecha efectiva
	 */
	private String urlFechaEfectiva;
	/**
	 * Codigo de respuesta exitosa
	 */
	private static final String RESPUESTA_EXITOSA = "0";
	/**
	 * ID PARAMETRO
	 */
	private static final String ID_PARAMETRO = "OBTENER_FECHA_EFECTIVA";
	/**
	 * Nombre de parametro
	 */
	private static final String NOMBRE_PARAMETRO = "ADMINISTRADOR_CREDITO";
	/**
	 * Timeout
	 */
	private int timeOut;

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlFechaEfectiva = this.parametroBiessDao
					.consultarPorIdNombreParametro(NOMBRE_PARAMETRO, ID_PARAMETRO).getValorCaracter();
			this.timeOut = this.parametroBiessDao
					.consultarPorIdNombreParametro(ConstantesSAC.ID_PARAMETRO, ConstantesSAC.TIME_OUT_REST_SAC)
					.getValorNumerico().intValue();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de Fecha efectiva", e);
		}
	}
	
	@Override
	public String consultarFechaEfectiva() throws ConsultaParametroException {
		String guid = java.util.UUID.randomUUID().toString().replace("-", "");
		try {
		
			final URL myUrl = new URL(urlFechaEfectiva);
			final HttpURLConnection connection = (HttpURLConnection) myUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("guid", guid);
			connection.setRequestProperty("user", "usrpqweb");
			connection.setConnectTimeout(timeOut);
			connection.setReadTimeout(timeOut);

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
			final Type tipo = new TypeToken<FechaEfectivaDto>() {
			}.getType();
			final FechaEfectivaDto respuesta = gson.fromJson(respuestaJson,
					tipo);

			if (respuesta != null && RESPUESTA_EXITOSA.equals( respuesta.getCodigoRespuesta())) {
			   
				return respuesta.getFechaEfectiva();
			}else if(respuesta != null && !RESPUESTA_EXITOSA.equals( respuesta.getCodigoRespuesta()) ) {
				grabarLogPeticion(this.urlFechaEfectiva, respuesta.getCodigoRespuesta(),
						respuesta.getMensajeRespuesta());
				throw new ConsultaParametroException(respuesta.getCodigoRespuesta()+":"+respuesta.getMensajeRespuesta());
			}

		
		} catch (MalformedURLException e) {
			LOG.error(e);
			throw new ConsultaParametroException("MFE:Estimado asegurado el sistema no pudo completar la acccion");
		} catch (IOException e) {
			LOG.error(e);
			throw new ConsultaParametroException("IOE:Estimado asegurado el sistema no pudo completar la acccion");
		}

		throw new ConsultaParametroException("DES:Estimado asegurado el sistema no pudo completar la acccion");
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
}