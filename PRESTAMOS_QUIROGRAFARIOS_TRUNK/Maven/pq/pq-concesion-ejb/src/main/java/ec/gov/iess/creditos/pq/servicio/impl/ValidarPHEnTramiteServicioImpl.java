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

import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.dto.CalificacionRequisitosResponseDto;
import ec.gov.iess.creditos.pq.excepcion.PHEnTramiteException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.servicio.ValidarPHEnTramiteServicioLocal;
import ec.gov.iess.creditos.pq.servicio.ValidarPHEnTramiteServicioRemote;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;

@Stateless
public class ValidarPHEnTramiteServicioImpl implements ValidarPHEnTramiteServicioLocal, ValidarPHEnTramiteServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(ValidarPHEnTramiteServicioImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;

	private String urlPhEnTramite;
	private static final String ID_PARAMETRO = "CENTRALIZACION_CARTERA";
	private static final String NOMBRE_PARAMETRO_URL = "URL_VALIDAR_PH_TRAMITE_SAC";
	private static final String RESPUESTA_EXITOSA = "0";
	private static final String MENSAJE_ERROR = "Error al consumir el servicio de:" + ID_PARAMETRO + ":" + NOMBRE_PARAMETRO_URL;

	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	public void init() {
		try {
			this.urlPhEnTramite = this.parametroBiessDao.consultarPorIdNombreParametro(ID_PARAMETRO, NOMBRE_PARAMETRO_URL).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de URL de garantias comprometidas de SAC", e);
		}
	}

	@Override
	public boolean tienePhEnTramite(String cedula) throws PHEnTramiteException {
		
		CalificacionRequisitosResponseDto respuesta = new CalificacionRequisitosResponseDto();
		
		respuesta = this.obtenerValidacionPhEnTramite(cedula);

		return respuesta.isEstaEnTramite();
	}

	private CalificacionRequisitosResponseDto obtenerValidacionPhEnTramite(String cedula) throws PHEnTramiteException {
		CalificacionRequisitosResponseDto respuesta = null;

		try {

			URL myUrl = new URL(this.urlPhEnTramite);
			HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			final Gson gson = new Gson();
			writer.write(cedula);
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

			final Type tipo = new TypeToken<CalificacionRequisitosResponseDto>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta == null ) {
				LOG.error("Respuesta de consumo de servicio de core negocio de PH en tramite es nulo");
				throw new PQExigibleException(ConstantesSAC.MENSAJE_VALOR_NULO, ConstantesSAC.COD_VALOR_NULO);
			} else if (!RESPUESTA_EXITOSA.equals(respuesta.getCodigoError())) {
				LOG.error("Respuesta de consumo de servicio devolvio codigo no exitoso. Codigo: " + respuesta.getCodigoError() + " Mensaje: "
						+ respuesta.getMensajeError());
				throw new PHEnTramiteException(MENSAJE_ERROR);
			}

		} catch (SocketTimeoutException e) {
			LOG.error("Timeout al consumir servicio de validar PH en tramite desde SAC", e);
			throw new PHEnTramiteException(e);
		} catch (IOException e) {
			LOG.error("Error al consumir el servicio de validar PH en tramite desde SAC", e);
			throw new PHEnTramiteException(e);
		} catch (PQExigibleException e) {
			throw new PHEnTramiteException(e);
		} 

		return respuesta;

	}

}
