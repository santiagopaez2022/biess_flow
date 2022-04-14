/**
 * 
 */
package ec.gov.iess.creditos.pq.helper.dinamico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.excepcion.ConecSrvPqDinamicoException;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public final class PqDinamicoHelper {

	/**
	 * LOG para la clase
	 */
	private static final LoggerBiess LOG = LoggerBiess.getLogger(PqDinamicoHelper.class);

	/**
	 * Codigo de respuesta servicio OK 200 HTTP
	 */
	private static final int HTT_200_OK = 200;

	private PqDinamicoHelper() {
		// Constructor privado para que no pueda ser instanciada
	}

	/**
	 * Metodo que obtiene la trama de peticion en formato json
	 * 
	 * @param urlData
	 * @param tramaJsonPeticion
	 * @return
	 * @throws ConecSrvPqDinamicoException
	 */
	public static String obtenerTramaPeticionPOST(final String urlData, final String tramaJsonPeticion)
			throws ConecSrvPqDinamicoException {
		HttpURLConnection connection = null;
		try {
			connection = crearConexionPOST(urlData);
			writeStream(connection, tramaJsonPeticion);
			return devolverRespuestaJSON(connection);
		} catch (IOException e) {
			LOG.error("IOException", e);
			throw new ConecSrvPqDinamicoException(e);
		} finally {
			connection.disconnect();
		}

	}

	private static void writeStream(HttpURLConnection connection, String tramaPeticion)
			throws IOException, ConecSrvPqDinamicoException {
		final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write(tramaPeticion);
		writer.close();
		if (connection.getResponseCode() != HTT_200_OK) {
			LOG.error("Fallo : HTTP CodigoError : " + connection.getResponseCode());
			throw new ConecSrvPqDinamicoException("Fallo : HTTP CodigoError : " + connection.getResponseCode());
		}

	}

	private static String devolverRespuestaJSON(HttpURLConnection connection) throws IOException {

		final InputStreamReader reader = new InputStreamReader(connection.getInputStream(),"UTF-8");
		final BufferedReader bufferedReader = new BufferedReader(reader);
		String respuestString = "";
		final StringBuilder respuestaJson = new StringBuilder();
		while ((respuestString = bufferedReader.readLine()) != null) {
			respuestaJson.append(respuestString);
		}
		reader.close();
		connection.getResponseCode();
		return respuestaJson.toString();
	}

	private static HttpURLConnection crearConexionPOST(final String url)
			throws IOException, ConecSrvPqDinamicoException {
		try {
			final URL myUrl = new URL(url);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			return connection;
		} catch (MalformedURLException e) {
			LOG.error("MalformedURLException", e);
			throw new ConecSrvPqDinamicoException(e);
		} catch (ProtocolException e) {
			LOG.error("ProtocolException", e);
			throw new ConecSrvPqDinamicoException(e);

		}
	}

}
