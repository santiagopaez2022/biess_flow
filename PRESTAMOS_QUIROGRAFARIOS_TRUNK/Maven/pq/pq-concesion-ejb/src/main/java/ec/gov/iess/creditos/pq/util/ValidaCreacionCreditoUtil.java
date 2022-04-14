package ec.gov.iess.creditos.pq.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.helper.reglas.Messages;

/**
 * 
 * Metodos utilitarios durante la creacion del credito
 * 
 * @author hugo.mora
 *
 */
public class ValidaCreacionCreditoUtil {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(ValidaCreacionCreditoUtil.class);

	/**
	 * Verifica si es dia feriado en base a la informacion de un archivo properties
	 * 
	 * @return
	 */
	public static boolean esDiaFeriado() {
		boolean diaFeriadoResp = false;
		try {
			String diasFeriados = Messages.getString("diasferiados");
			String[] dias = diasFeriados.split(";");
			Calendar fechaActual = Calendar.getInstance();
			fechaActual.set(Calendar.HOUR_OF_DAY, 0);
			fechaActual.set(Calendar.MINUTE, 0);
			fechaActual.set(Calendar.SECOND, 0);
			fechaActual.set(Calendar.MILLISECOND, 0);

			for (String diaFeriado : dias) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaFeriado = sdf.parse(diaFeriado);
				Calendar calendarFeriado = Calendar.getInstance();
				calendarFeriado.setTime(fechaFeriado);
				calendarFeriado.set(Calendar.HOUR_OF_DAY, 0);
				calendarFeriado.set(Calendar.MINUTE, 0);
				calendarFeriado.set(Calendar.SECOND, 0);
				calendarFeriado.set(Calendar.MILLISECOND, 0);

				if (fechaActual.compareTo(calendarFeriado) == 0) {
					diaFeriadoResp = true;
				}
			}
		} catch (Exception e) {
			LOG.error("Error al determinar dia feriado", e);
		}
		return diaFeriadoResp;
	}

}
