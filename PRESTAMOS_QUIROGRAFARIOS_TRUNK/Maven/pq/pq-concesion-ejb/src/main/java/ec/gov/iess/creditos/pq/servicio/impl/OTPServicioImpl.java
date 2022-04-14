package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.otp.dto.NotificacionOTP;
import ec.gov.iess.creditos.otp.dto.OTPGenerarRequestDto;
import ec.gov.iess.creditos.otp.dto.OTPResponseDto;
import ec.gov.iess.creditos.otp.dto.OTPValidarRequestDto;
import ec.gov.iess.creditos.otp.dto.ObtenerCodigoRequestDto;
import ec.gov.iess.creditos.pq.excepcion.OTPException;
import ec.gov.iess.creditos.pq.servicio.OTPServicioLocal;
import ec.gov.iess.creditos.pq.servicio.OTPServicioRemote;

/**
 * Servicio para generacion y validacion de codigo OTP
 * 
 * @author hugo.mora
 * @date 2016/11/14
 *
 */
@Stateless
public class OTPServicioImpl implements OTPServicioLocal, OTPServicioRemote {

	private static final LoggerBiess LOG = LoggerBiess.getLogger("OTPServicioImpl");

	@EJB
	private ParametroBiessDao parametroBiessDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.OTPServicioLocal#generaOTP(java.lang.String, java.lang.String,
	 * ec.gov.iess.creditos.otp.dto.NotificacionOTP)
	 */
	@Override
	public void generaOTP(String idTransaccion, String referencia, NotificacionOTP notificacionOTP, String idNegocio) throws OTPException {
		OTPResponseDto respuesta = null;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.GENERAR_OTP.getIdParametro(),
					ParametrosBiessEnum.GENERAR_OTP.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			OTPGenerarRequestDto generarRequest = new OTPGenerarRequestDto();
			generarRequest.setIdTransaccion(idTransaccion);
			generarRequest.setReferencia(referencia);
			generarRequest.setNotificacion(notificacionOTP);
			generarRequest.setIdNegocio(idNegocio);
			
			final String json = gson.toJson(generarRequest);
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

			final Type tipo = new TypeToken<OTPResponseDto>() {
			}.getType();
			respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if ("1".equals(respuesta.getCodigoRespuesta())) {
					throw new OTPException(
							"Se present\u00F3 un error en la generaci\u00F3n del c\u00F3digo temporal. Por favor intentar nuevamente.");
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al generar codigo OTP", e);
			throw new OTPException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al generar codigo OTP", e);
			throw new OTPException(e);
		} catch (IOException e) {
			LOG.error("3. Error al generar codigo OTP", e);
			throw new OTPException(e);
		} catch (Exception e) {
			LOG.error("4. Error al generar codigo OTP", e);
			throw new OTPException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.OTPServicioLocal#validaOTP(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validaOTP(String idTransaccion, String codigoIngresado) throws OTPException {
		boolean respuesta = false;
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.VALIDAR_OTP.getIdParametro(),
					ParametrosBiessEnum.VALIDAR_OTP.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

			final Gson gson = new Gson();
			OTPValidarRequestDto validarRequest = new OTPValidarRequestDto();
			validarRequest.setIdTransaccion(idTransaccion);
			validarRequest.setCodigoIngresado(codigoIngresado);

			final String json = gson.toJson(validarRequest);
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

			final Type tipo = new TypeToken<OTPResponseDto>() {
			}.getType();
			OTPResponseDto otpResponse = gson.fromJson(respuestaJson, tipo);

			if (otpResponse != null) {
				if ("0".equals(otpResponse.getCodigoRespuesta())) {
					respuesta = true;
				} else if ("2".equals(otpResponse.getCodigoRespuesta())) {
					throw new OTPException(
							"Se present\u00F3 un error en la validaci\u00F3n del c\u00F3digo temporal. Por favor intentar nuevamente.");
				} else if ("3".equals(otpResponse.getCodigoRespuesta())) {
					respuesta = false;
				} else if ("4".equals(otpResponse.getCodigoRespuesta())) {
					throw new OTPException("El tiempo de vigencia del c\u00F3digo temporal ha expirado.");
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al validar codigo OTP", e);
			throw new OTPException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al validar codigo OTP", e);
			throw new OTPException(e);
		} catch (IOException e) {
			LOG.error("3. Error al validar codigo OTP", e);
			throw new OTPException(e);
		} catch (Exception e) {
			LOG.error("4. Error al validar codigo OTP", e);
			throw new OTPException(e);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.OTPServicioLocal#obtenerCodigoActivacion(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String obtenerCodigoActivacion(String idTransaccion, String referencia) throws OTPException {
		String codigoActivacion = "";
		try {
			final String uri = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.CODIGO_ACTIVACION_FOCALIZADOS.getIdParametro(),
					ParametrosBiessEnum.CODIGO_ACTIVACION_FOCALIZADOS.getNombreParametro()).getValorCaracter();
			final URL myUrl = new URL(uri);
			final HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

			// indico que recibo informacion
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Le voy enviar un contet type y le voy enviar un json
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			
			final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			
			final Gson gson = new Gson();
			ObtenerCodigoRequestDto obtenerRequest = new ObtenerCodigoRequestDto();
			obtenerRequest.setIdTransaccion(idTransaccion);
			obtenerRequest.setReferencia(referencia);

			final String json = gson.toJson(obtenerRequest);
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

			final Type tipo = new TypeToken<OTPResponseDto>() {
			}.getType();
			OTPResponseDto respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if ("0".equals(respuesta.getCodigoRespuesta())) {
					codigoActivacion = respuesta.getCodigoGenerado();
				} else if ("5".equals(respuesta.getCodigoRespuesta())) {
					throw new OTPException(
							"Se present\u00F3 un error al generar el c\u00F3digo de activaci\u00F3n. Favor intentar nuevamente.");
				}
			}
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al obtener codigo de activacion para pq focalizado", e);
			throw new OTPException(e);
		} catch (MalformedURLException e) {
			LOG.error("2. Error al obtener codigo de activacion para pq focalizado", e);
			throw new OTPException(e);
		} catch (IOException e) {
			LOG.error("3. Error al obtener codigo de activacion para pq focalizado", e);
			throw new OTPException(e);
		} catch (Exception e) {
			LOG.error("4. Error al obtener codigo de activacion para pq focalizado", e);
			throw new OTPException(e);
		}
		return codigoActivacion;
	}

}
