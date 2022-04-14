package ec.gov.iess.pq.concesion.conozcacliente.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ec.gov.iess.pq.concesion.conozcacliente.constant.UnidadFecha;

/**
 * Clase para manejo de mensajes WEB
 * 
 * @author edison.cayambe
 *
 */
public class MensajeUtil {
	
	public static Properties propertiesDatos;

	public MensajeUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene el texto del mensaje de un properties
	 * 
	 * @param idMensaje
	 * @return
	 */
	public static String getMensajeWeb(String idMensaje) {
		String mensajeError = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages")
				.getString(idMensaje);

		return mensajeError;
	}

	/**
	 * Agrega un mensaje web
	 * 
	 * @param texto
	 */
	public static void addMensajeInfoWeb(String texto) {
		FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	/**
	 * Agrega un mensaje web de error
	 * 
	 * @param texto
	 */
	public static void addMensajeErrorWeb(String texto) {
		FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, null);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	/**
	 * Calcula la diferencia de fechas
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param unidad
	 * @return
	 */
	public static long calcularDiferenciaFechas(Date fechaInicio, Date fechaFin, UnidadFecha unidad) {

		if (fechaInicio == null || fechaFin == null) {
			return 0;
		}

		// fecha uno
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(fechaInicio);

		// fecha dos
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fechaFin);

		long fechaInicioms = calendarInicio.getTimeInMillis();
		long fechaFinms = calendarFin.getTimeInMillis();
		long diferencia = fechaFinms - fechaInicioms;

		// 1000 Milisegundo-1Segundo

		Double numeroDias = Math.floor(diferencia / unidad.getValor());// 3600*24*1000

		return numeroDias.intValue();

	}

	/**
	 * Metodo que retorna la fecha actual del sistema en el formato especificado.
	 * 
	 * @param format
	 * @return String
	 */
	public static String getFormatedDate(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String strDate = null;
		strDate = simpleDateFormat.format(date);

		return strDate;
	}
	
}