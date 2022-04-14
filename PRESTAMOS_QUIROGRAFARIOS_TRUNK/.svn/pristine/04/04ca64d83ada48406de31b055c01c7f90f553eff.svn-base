/*
 * Copyright 2014 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR Todos los derechos reservados
 */

package ec.gov.iess.planillaspq.web.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase para transformaciones de fechas
 * 
 * @author edwin.maza
 * @date 17/01/2014
 */
public class UtilDate {
	/**
	 * Funcion que retorna una fecha Date parseada a String
	 * 
	 * @author edwin.maza
	 * @date 17/01/2014
	 * @param fecha
	 * @param formato
	 * @return fecha parseada a String
	 */
	public static String dateToString(Date fecha, String formato) {
		Format formatter = new SimpleDateFormat(formato);
		return formatter.format(fecha);
	}

	/**
	 * <Funcion que devuelve la fecha dado su dia,mes,año>
	 * 
	 * @author edwin.maza
	 * @date 29/01/2014
	 * @param dia
	 * @param mes
	 * @param anio
	 * @return fecha
	 */
	public static Date getDate(int dia, int mes, int anio) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, anio);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}
