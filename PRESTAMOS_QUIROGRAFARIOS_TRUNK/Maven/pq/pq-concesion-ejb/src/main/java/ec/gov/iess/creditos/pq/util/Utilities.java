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
package ec.gov.iess.creditos.pq.util;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que ofrece funciones utiles.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
public class Utilities {

	/**
	 * Método que retorna la fecha actual del sistema en el formato especificado.
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
	 * Método que retorna la hora actual del sistema en el formato especificado.
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
	 * Método que retorna la fecha actual del sistema en el formato especificado.
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
	 * Método que retorna la ip remota.
	 * 
	 * @return String
	 */
	public static String getRemoteIp() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			return ip.getHostAddress().toString();
		} catch (Exception e) {
			return "";
		}
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
	 * @return 	Un valor de 0 si fechauno es igual a fechados
	 * 			Un valor menor que 0 si fechauno es menor que fechados
	 * 			Un valor mayor que 0 si fechauno es mayor que fechados.
	 */
	public static int compareDatesWithoutTime(Date fechauno, Date fechados) {
		return getZeroTimeDate(fechauno).compareTo(getZeroTimeDate(fechados));
	}

	/**
	 * Obtiene el numero de dias existente entre dos fechas.
	 * 
	 * @param fechauno
	 *            - fecha uno.
	 * @param fechados
	 *            - fecha dos.
	 * 
	 * @return numero de dias.
	 * 
	 *         Si alguna fecha es nulo devuelve cero.
	 */
	public static int calcurarNuemroDias(Date fechauno, Date fechados) {

		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		if (fechauno == null || fechados == null) {
			return 0;
		}

		// fecha uno
		Calendar calendarUno = Calendar.getInstance();
		calendarUno.setTime(fechauno);

		// fecha dos
		Calendar calendarDos = Calendar.getInstance();
		calendarDos.setTime(fechados);

		long fechaunoms = calendarUno.getTimeInMillis();
		long fechadosms = calendarDos.getTimeInMillis();
		long diferencia = fechaunoms - fechadosms;

		Double numeroDias = Math.floor(diferencia / 86400000L);// 3600*24*1000

		return numeroDias.intValue();

	}
	
	/**
	 * Obtiene un string randomico del tamaño requerido
	 * 
	 * @param tamanio
	 * @return String
	 */
	public static String getRamdomString(int tamanio) {
		String chars = "abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		String pwd = "";
		int length = chars.length();
		for (int i = 0; i < tamanio; i++) {
			pwd += chars.split("")[(int) (Math.random() * (length - 1))];
		}

		return pwd;
	}

	/**
	 * Metodo que encripta una cadena en SHA-512.
	 * 
	 * @param cadena
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashString(String cadena) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(cadena.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return toHex(md.digest());
	}

	/**
	 * Metodo que transforma un byte[] a hexadecimal.
	 * 
	 * @param bytes
	 * @return String
	 */
	public static final String toHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xFF) < 16) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xFF, 16));
		}

		return buf.toString();
	}
	
	 /**
	 * @param cad String contiene el valor a validar
	 * @return Devuelve true si en una cadena que llega todos son numeros, false en caso contrario
	 */
	public static boolean esEntero(String cad)
	 {
		 for(int i = 0; i<cad.length(); i++) {
			 if( !Character.isDigit(cad.charAt(i)) ) {
				 return false;
			 }
		 }
		 return true;
	 }
	
	public static Calendar agregarDiasFecha(Calendar fecha, int valor) {
		Calendar fechaDia = fecha;
	      if (valor == 0 ) 
	    	  return fechaDia;
	      fechaDia.add(Calendar.DAY_OF_YEAR, valor); 
	      return fechaDia; 
	}
	
	public static int obtenerUltimoDiaMes(int anio, int mes) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(anio, mes-1, 1);
        return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

    }
   
    public static int obtenerDiasCalculo(Calendar fecha) {
        int diasCalculo = 30;
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int ultimoDiasMes = obtenerUltimoDiaMes(anio, mes);
       
        if (mes == 2 && dia==ultimoDiasMes)
            diasCalculo = 30;
        else if (dia == 31)
            diasCalculo = 30;
        else
            diasCalculo = dia;
       
        return diasCalculo;
    }
   
    public static Double obtenerInteresNormal(double valor, double tasaInteres, int dias) {
        return valor * tasaInteres / 36000 * dias;
    }
    
    //req 408
	public static String[] obtenerdiaHoraMinuto(String cadena){
		String diaHoraMinuto[]= new String[3];
		String paramsDia[]=cadena.split(",");
		String paramsHoraMinuto[]=paramsDia[1].split(":");
		
		diaHoraMinuto[0]=paramsDia[0];
		diaHoraMinuto[1]=paramsHoraMinuto[0];
		diaHoraMinuto[2]=paramsHoraMinuto[1];
		
	return diaHoraMinuto;
	}
	
	public static Calendar obtenerCalendario(int dia, int hora, int minuto) {
		if(dia>obtenertUltimoDiaMes()) {
			dia=obtenertUltimoDiaMes();
		}
		Calendar calendario = Calendar.getInstance();			
		calendario.set(Calendar.DAY_OF_MONTH, dia);
		calendario.set(Calendar.HOUR_OF_DAY, hora);
		calendario.set(Calendar.MINUTE,minuto);
		
		return calendario;
	}
	
	public static int obtenertUltimoDiaMes() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int year =  calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
