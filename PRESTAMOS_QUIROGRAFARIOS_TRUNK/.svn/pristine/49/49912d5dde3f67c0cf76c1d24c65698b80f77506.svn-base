package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ec.fin.biess.creditos.pq.enumeracion.CodigosRespuestaPaqueteTuristicoEnum;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.pq.excepcion.CodigoReservaPaqueteTuristicoException;
import ec.gov.iess.creditos.pq.excepcion.PrestamosTuristicosException;
import ec.gov.iess.creditos.pq.helper.reglas.Messages;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoLocalService;
import ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoRemoteService;
import ec.gov.iess.creditos.turismo.dto.AnulaPaqueteTuristicoResponseDto;
import ec.gov.iess.creditos.turismo.dto.ConsultaPaqueteTuristicoResponseDto;
import ec.gov.iess.creditos.turismo.dto.PaqueteTurismoInfoDto;
import ec.gov.iess.creditos.turismo.dto.PaqueteTuristicoRequestDto;

/**
 * Servicio para manejo de logica de negocio de prestamos turisticos
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class PrestamoTuristicoServiceImpl implements PrestamoTuristicoLocalService, PrestamoTuristicoRemoteService {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(PrestamoTuristicoServiceImpl.class);

	@EJB
	private ParametroBiessDao parametroBiessDao;

	@EJB
	private PrestamoServicio prestamoServicio;

	private String urlConsultarReserva;

	private String urlAnularReserva;

	@PostConstruct
	private void init() {
		try {
			this.urlConsultarReserva = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.CONSULTAR_RESERVA.getIdParametro(),
					ParametrosBiessEnum.CONSULTAR_RESERVA.getNombreParametro()).getValorCaracter();

			this.urlAnularReserva = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.ANULAR_RESERVA.getIdParametro(),
					ParametrosBiessEnum.ANULAR_RESERVA.getNombreParametro()).getValorCaracter();
		} catch (ParametroBiessException e) {
			LOG.info("1. Error al obtener URL para consulta y anulacion de paquetes turisticos", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoLocalService#consultarInformacionPaqueteTuristico(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public PaqueteTurismoInfoDto consultarInformacionPaqueteTuristico(String cedula, String codigoReserva)
			throws PrestamosTuristicosException, CodigoReservaPaqueteTuristicoException {
		PaqueteTurismoInfoDto paqueteTuristico = null;
		try {
			final String uri = this.urlConsultarReserva;
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
			PaqueteTuristicoRequestDto paqueteRequest = new PaqueteTuristicoRequestDto();
			paqueteRequest.setCedula(cedula);
			paqueteRequest.setCodigoReserva(codigoReserva);

			final String json = gson.toJson(paqueteRequest);
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
			final Type tipo = new TypeToken<ConsultaPaqueteTuristicoResponseDto>() {
			}.getType();
			final ConsultaPaqueteTuristicoResponseDto respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (CodigosRespuestaPaqueteTuristicoEnum.EXISTE_RESERVA.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					paqueteTuristico = new PaqueteTurismoInfoDto();
					paqueteTuristico.setDescripcionPaquete(respuesta.getDescripcionReserva());
					paqueteTuristico.setMontoPaquete(respuesta.getValorTotalReserva());
				} else if (CodigosRespuestaPaqueteTuristicoEnum.NO_EXISTE_RESERVA.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					throw new PrestamosTuristicosException(Messages.getString("mensaje.turismo.no.existe.reserva"));
				} else if (CodigosRespuestaPaqueteTuristicoEnum.CODIGO_INVALIDO.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					throw new CodigoReservaPaqueteTuristicoException(Messages.getString("mensaje.turismo.codigo.reserva.invalido"));
				} else if (CodigosRespuestaPaqueteTuristicoEnum.DATO_INGRESO_NULO.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					throw new PrestamosTuristicosException(respuesta.getDescripcionRespuesta());
				} else if (CodigosRespuestaPaqueteTuristicoEnum.ERROR_CONSULTAR.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					throw new PrestamosTuristicosException(Messages.getString("mensaje.turismo.error.no.controlado.consulta.reserva"));
				}
			}

		} catch (MalformedURLException e) {
			LOG.error("2. Error al consultar informacion de paquetes turisticos", e);
			throw new PrestamosTuristicosException(
					"Se present\u00F3 un problema al consultar informaci\u00F3n del paquete tur\u00EDstico. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al consultar informacion de paquetes turisticos", e);
			throw new PrestamosTuristicosException(
					"Se present\u00F3 un problema al consultar informaci\u00F3n del paquete tur\u00EDstico. Por favor intente m\u00E1s tarde.");
		}

		return paqueteTuristico;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoLocalService#rechazaCreditoTuristico(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean rechazaCreditoTuristico(String cedula, String codigoReserva) throws PrestamosTuristicosException {
		boolean resp = false;
		try {
			final String uri = this.urlAnularReserva;
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
			PaqueteTuristicoRequestDto paqueteRequest = new PaqueteTuristicoRequestDto();
			paqueteRequest.setCedula(cedula);
			paqueteRequest.setCodigoReserva(codigoReserva);

			final String json = gson.toJson(paqueteRequest);
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

			final Type tipo = new TypeToken<AnulaPaqueteTuristicoResponseDto>() {
			}.getType();
			final AnulaPaqueteTuristicoResponseDto respuesta = gson.fromJson(respuestaJson, tipo);

			if (respuesta != null) {
				if (CodigosRespuestaPaqueteTuristicoEnum.EXITO_PROCESO.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					resp = true;
				} else if (CodigosRespuestaPaqueteTuristicoEnum.ERROR_ANULAR.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					throw new PrestamosTuristicosException(Messages.getString("mensaje.turismo.error.anular.reserva"));
				} else if (CodigosRespuestaPaqueteTuristicoEnum.DATO_INGRESO_NULO.getCodigo().equals(respuesta.getCodigoRespuesta())) {
					throw new PrestamosTuristicosException(respuesta.getDescripcionRespuesta());
				} else {
					throw new PrestamosTuristicosException(Messages.getString("mensaje.turismo.error.no.controlado.anula.reserva"));
				}
			}
		} catch (MalformedURLException e) {
			LOG.error("2. Error al anular paquetes turisticos", e);
			throw new PrestamosTuristicosException(
					"2. Se present\u00F3 un problema al anular el pr\u00E9stamo tur\u00EDstico. Por favor intente m\u00E1s tarde.");
		} catch (IOException e) {
			LOG.error("3. Error al anular paquetes turisticos", e);
			throw new PrestamosTuristicosException(
					"3. Se present\u00F3 un problema al anular el pr\u00E9stamo tur\u00EDstico. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("4. Error al anular paquetes turisticos", e);
			throw new PrestamosTuristicosException(e);
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoLocalService#rechazarCreditoTuristicoConProcedimiento(java.util
	 * .Map, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void rechazarCreditoTuristicoConProcedimiento(Map<String, Long> parametrosCredito, String estadoActualCredito, String cedula,
			String codigoReserva) throws PrestamosTuristicosException, PrestamoPqCoreException {
		boolean anulaProveedorExterno = this.rechazaCreditoTuristico(cedula, codigoReserva);
		if (anulaProveedorExterno) {
			this.prestamoServicio.anularCreditoPQConProcedimiento(parametrosCredito, estadoActualCredito, "REC");
		}
	}

}
