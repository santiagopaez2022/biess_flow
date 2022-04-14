/*
 * Copyright 2013 BIESS - ECUADOR
 *
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.pq.concesion.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.security.auth.spi.IessPrincipal;

import ec.gov.iess.pq.concesion.web.enumeration.ParametrosGeneralesEnum;
import ec.gov.iess.pq.concesion.web.enumeration.UnidadFechaEnum;

/**
 * Clase que ofrece funciones utiles.
 *
 * @author Omar Villanueva
 * @version 1.0
 *
 */
public class Utilities {

	/**
	 * MÃ©todo que retorna la fecha actual del sistema en el formato especificado.
	 *
	 * @param format
	 * @return String
	 */
	public static String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(cal.getTime());
	}

	/**
	 * MÃ©todo que retorna la hora actual del sistema en el formato especificado.
	 *
	 * @param format
	 * @return String
	 */
	public static String getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		return sdf.format(cal.getTime());
	}

	/**
	 * MÃ©todo que retorna la fecha actual del sistema en el formato especificado.
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

	/**
	 * MÃ©todo que retorna la clase principal que contiene informaciÃ³n del
	 * afiliado/asegurado
	 *
	 * @return IessPrincipal
	 */
	private static IessPrincipal getIessPrincipal() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		return (IessPrincipal) request.getUserPrincipal();
	}

	/**
	 * MÃ©todo que retorna la ip remota.
	 *
	 * @return String
	 */
	public static String getRemoteIp() {
		return getIessPrincipal().getIPAddress();
	}

	/**
	 * MÃ©todo que retorna la CI del usuario.
	 *
	 * @return String
	 */
	public static String getRemoteCI() {
		// return
		// FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		return getIessPrincipal().getName();
	}

	/**
	 * Metodo que elimina hh:mm:ss:ms de una fecha
	 *
	 * @param fecha
	 * @return Date
	 */
	public static Date getZeroTimeDate(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * @param fechauno
	 * @param fechados
	 * @return Un valor de 0 si fechauno es igual a fechados Un valor menor que 0 si
	 *         fechauno es menor que fechados Un valor mayor que 0 si fechauno es
	 *         mayor que fechados.
	 */
	public static int compareDatesWithoutTime(Date fechauno, Date fechados) {
		return getZeroTimeDate(fechauno).compareTo(getZeroTimeDate(fechados));
	}

	/**
	 * Calcula la diferecnia de fechas
	 *
	 * @param fechaInicio
	 * @param fechaFin
	 * @param unidad
	 * @return
	 */
	public static long calcularDiferenciaFechas(Date fechaInicio, Date fechaFin, UnidadFechaEnum unidad) {

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
	 * Reemplaza el contenido de una cadena por otro texto hasta que encuentre el
	 * texto especificado en cadenaHasta
	 *
	 * @param cadena
	 *            Es la cadena a reemplazar el texto
	 * @param cadenaHasta
	 *            Cuando encuentra esa cadena lo reemplaza
	 * @param cadenaReemplaza
	 *            Texto por el que se reemplaza
	 * @return
	 */
	public static String reemplazarStringHastaCaracter(String cadena, String cadenaHasta, String cadenaReemplaza) {
		return cadena.replaceAll(".*" + cadenaHasta, cadenaReemplaza);
	}

	/**
	 * Agrega ceros antes de la cadena
	 *
	 * @param cadena
	 * @param numeroCeros
	 * @return
	 */
	public static String agregarCerosInicio(String cadena, int numeroCeros) {
		String ceros = "%0" + numeroCeros + "d";
		return String.format(ceros, Integer.parseInt(cadena));
	}

	/**
	 * Elimina caracteres especiales de la cadena
	 *
	 * @param input
	 * @return
	 */
	public static String removeSpecialCharacters(String input) {
		// Cadena de caracteres original a sustituir.
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		// Cadena de caracteres ASCII que reemplazarÃ¡n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		} // for i
		return output;
	}

	public static boolean esNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Reemplaza el valor de un Parametro por un texto
	 *
	 * @param texto
	 *            Es la cadena a reemplazar el texto
	 *
	 * @param parametroReemplazo
	 *            Texto por el que se reemplaza
	 * @return
	 */
	public static String reemplazarParametroEnTexto(String texto, ParametrosGeneralesEnum parametro,
													Map<String, String> mapaParametros) {

		String nuevoValor = parametro.getValorReemplazoTexto();

		if (mapaParametros != null && mapaParametros.get(parametro.getCodigo()) != null) {
			nuevoValor = mapaParametros.get(parametro.getCodigo());
		}

		return texto.replace(parametro.getValorReemplazoTexto(), nuevoValor);
	}
	
	public static String obtenerNumeroOrdinal(int numero) {		
		String ordinal;
		
		String Unidad[]={"", "primero", "segundo", "tercero",
		        "cuarto", "quinto", "sexto", "septimo", "octavo", "noveno"};
		
		String Decena[]={"", "décimo", "vigésimo", "trigésimo",
			      "cuadragesimo","quincuagesimo", "sexagesimo", "septuagesimo",
			      "octogesimo", "nonagesimo"};
				
		int u=numero%10;
	    int d=(numero/10)%10;
		
		if(numero > 9 ) {
			ordinal= Decena[d]+" "+Unidad[u];
		}else {
			ordinal= Unidad[numero];
		}		
	return ordinal;
	}
	
	/**
	 * Permite obtener los rangos solo dias
	 * @param rangos
	 * @return
	 */
	public static String obtenerRangosDias(String rangoCompleto) {
		int primerCont=0;
		int cont=0;
		String valor="";
		String[] primerArreglo = rangoCompleto.split("\\|");
		for(String params: primerArreglo){
			String[] partes= params.split("-");
			primerCont++;
			cont=0;
			for(String tramas: partes) {
				String dias[]=tramas.split(",");
				cont++;
				if(cont==1){
					valor=valor+dias[0]+"-";
				}else valor=valor+dias[0];
			
			}
			if(primerCont>0){
				valor=valor+":";
			}
		}
		return valor;
	}
}
