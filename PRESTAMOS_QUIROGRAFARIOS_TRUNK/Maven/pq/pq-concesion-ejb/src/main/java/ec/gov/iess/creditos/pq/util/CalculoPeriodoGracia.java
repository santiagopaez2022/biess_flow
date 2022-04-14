/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.pq.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.enumeracion.TipoPeriodoGracia;
import ec.gov.iess.creditos.modelo.dto.PeriodoGracia;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;
import ec.gov.iess.util.DateUtil;

/**
 * 
 * Clase que realiza los calculos del periodo de gracia
 * 
 * @author cvillarreal
 * 
 */
public class CalculoPeriodoGracia {

	LoggerBiess log = LoggerBiess.getLogger(CalculoPeriodoGracia.class);

	private CalculoCreditoHelperSingleton calculoCreditoHelper;

	public CalculoPeriodoGracia() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();
	}

	/**
	 * Determina la fecha de iniciio del periodo de gracia en base al decimo
	 * digito de la cédula y a la fecha de la solicitud
	 * 
	 * Metodo creado para obtener la fecha de transferencia final
	 * 
	 * @param cedula
	 *            identificador del solicitante
	 * @param fechaSolicitud
	 *            Fecha de solicitud del prestamo
	 * @return fecha de inicio del periodo de gracia
	 * 
	 * @author psoria
	 */

	public Date determinarFechaInicioPeriodoGracia(String cedula,
			Date fechaSolicitud) {

		Date fechaTransfer = determinarFechaInicioPeriodoGracia1(cedula,
				fechaSolicitud);

		return this.determinarDiasNoLaborables(fechaTransfer, fechaSolicitud);
	}

	/**
	 * Determina si la fecha es dia no laborable y la ajusta a un dia laborable
	 * (lunes - viernes)
	 * 
	 * @param Transferencia
	 *            Fecha de transferencia sin tomar en cuenta (viernes / sabado /
	 *            domingo)
	 * @return fecha ajustada, exluyendo viernes, sabados y domingos
	 * 
	 * @author psoria
	 */
	public Date determinarDiasNoLaborables(Date transferencia, Date solicitud) {

		Calendar clFechaTransferencia = new GregorianCalendar();
		clFechaTransferencia.setTime(transferencia);

		long diff = diasEntreDosFechas(solicitud, transferencia); 

		if (diff <= 1) {
			if (clFechaTransferencia.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
				clFechaTransferencia.add(Calendar.DATE, 3);

		}
		if (clFechaTransferencia.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			clFechaTransferencia.add(Calendar.DATE, 2);
		if (clFechaTransferencia.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			clFechaTransferencia.add(Calendar.DATE, 1);
		log.debug("FECHA AJUSTADA FERIADO: " + clFechaTransferencia.getTime());
		log.debug("--------------------------");

		return clFechaTransferencia.getTime();
	}

	/**
	 * Determina la fecha de transferencia en base al decimo digito y a la fecha
	 * de solicitud, sin tomar en cuenta dias no laborables.
	 * 
	 * @param cedula
	 *            Identificacion del solicitante
	 * @param fechaSolicitud
	 *            Fecha de solicitud del prestamo
	 * @return Fecha de transferencia
	 * 
	 * @author psoria
	 */
	public Date determinarFechaInicioPeriodoGracia1(String cedula,
			Date fechaSolicitud) {
		Calendar clFechaOriginal = new GregorianCalendar();
		clFechaOriginal.setTime(fechaSolicitud);

		Calendar clFechaSolicitud = new GregorianCalendar();
		clFechaSolicitud.setTime(fechaSolicitud);

		Integer diaPrestamo = clFechaSolicitud.get(Calendar.DATE);
		Integer decimoDigito = new Integer(cedula.substring(9));
		Boolean valormenor10 = new Boolean(false);
		Boolean valormayor20 = new Boolean(false);

		log.debug("diaPrestamo: " + diaPrestamo + "     decimoDigito: "
				+ decimoDigito);
		log.debug("cedula: " + cedula);
		log.debug("Solicitud:  " + clFechaSolicitud.getTime());

		if (diaPrestamo.intValue() <= 10) {
			valormenor10 = new Boolean(true);
		} else if (diaPrestamo.intValue() < 20) {
			valormenor10 = new Boolean(false);
			valormayor20 = new Boolean(false);
			diaPrestamo = diaPrestamo - 10;
		} else if (diaPrestamo.intValue() > 20) {
			valormenor10 = new Boolean(false);
			valormayor20 = new Boolean(true);
			diaPrestamo = diaPrestamo - 20;
		}

		if (diaPrestamo >= 10)
			diaPrestamo = diaPrestamo - 10;

		// dia menor o igual a 10
		if (valormenor10) {
			log.debug("primeros 10 dias");
			if (diaPrestamo.intValue() == decimoDigito.intValue()) {
				log.debug("digito = que dia");
				clFechaSolicitud.add(Calendar.DATE, 1);
			} else if (diaPrestamo.intValue() > decimoDigito.intValue()) {
				log.debug("dia mayor que digito");
				if (decimoDigito == 0) {
					log.debug("digito 0");
					clFechaSolicitud.set(Calendar.DATE, 10);
				} else {
					clFechaSolicitud.set(Calendar.DATE, decimoDigito + 20);
				}
			} else if (diaPrestamo.intValue() < decimoDigito.intValue()) {
				log.debug("dia menor que digito");
				if (decimoDigito == 0) {
					log.debug("digito 0");
					clFechaSolicitud.set(Calendar.DATE, 10);
				} else
					clFechaSolicitud.add(Calendar.DATE, 1); // ojo
			}
			log.debug("FECHA SIN AJUSTAR: " + clFechaSolicitud.getTime());

			return clFechaSolicitud.getTime();
		}

		// dia entre 10 y 20
		if (!valormayor20) {
			log.debug("dias 10 - 20");
			if (decimoDigito == 0) {
				log.debug("digito 0");
				clFechaSolicitud.set(Calendar.DATE, 30);
			} else {
				clFechaSolicitud.set(Calendar.DATE, decimoDigito + 20);
			}
			log.debug("FECHA SIN AJUSTAR: " + clFechaSolicitud.getTime());

			return clFechaSolicitud.getTime();

		} else { // dia mayor que 20
			log.debug("ultimos 10 dias");
			if (diaPrestamo.intValue() == decimoDigito.intValue()) {
				log.debug("digito = que dia");
				if (decimoDigito != 0)
					clFechaSolicitud.add(Calendar.DATE, 1);
				else {
					clFechaSolicitud.set(Calendar.DATE, 10);
					clFechaSolicitud.add(Calendar.MONTH, 1);

				}

			} else if (diaPrestamo.intValue() > decimoDigito.intValue()) {
				log.debug("dia mayor que digito");
				if (decimoDigito == 0) {
					clFechaSolicitud.set(Calendar.DATE, 30);
				} else {
					clFechaSolicitud.set(Calendar.DATE, decimoDigito + 1); // ojo
					clFechaSolicitud.add(Calendar.MONTH, 1);
				}
			} else if (diaPrestamo.intValue() < decimoDigito.intValue()) {
				log.debug("dia menor que digito");
				if (diaPrestamo.intValue() == 0) {
					clFechaSolicitud.set(Calendar.DATE, decimoDigito + 1);
					clFechaSolicitud.add(Calendar.MONTH, 1);

				} else
					clFechaSolicitud.set(Calendar.DATE, decimoDigito + 20);
			}
			log.debug("FECHA SIN AJUSTAR: " + clFechaSolicitud.getTime());

			return clFechaSolicitud.getTime();
		}
	}

	/**
	 * Determina el interes periodo de gracia en base a los datos de la
	 * solicitud del prestamo *
	 * 
	 * @param fechaConcesion
	 *            Fecha de consecion de prestamo
	 * @param tasaInteres
	 *            tasa de interes a aplicarse a la concesion del crédito
	 * @param montoPrestamo
	 *            monto del prestamo
	 * @return Objeto periodo de gracia con datos
	 */
	public PeriodoGracia determinaPeriodoGracia(Date fechaConcesion,
			BigDecimal tasaInteres, BigDecimal montoPrestamo) {

		tasaInteres = UtilAjusteCalculo.ajusteNumberBaseDatos(tasaInteres);
		montoPrestamo = UtilAjusteCalculo.ajusteNumberBaseDatos(montoPrestamo);
		
		
		log.debug(" Parametros----");
		log.debug(" tasaInteres : " + tasaInteres);
		log.debug(" fechaConcesion : " + fechaConcesion);
		log.debug(" montoPrestamo : " + montoPrestamo);
		
		PeriodoGracia periodoGracia = new PeriodoGracia();
		Date fechaFin = this.calculoCreditoHelper.determinaFechaFin(
					fechaConcesion, TipoPeriodoGracia.FIN_DE_MES);
		int	numeroDias = DateUtil.diferenciaEnDias(fechaConcesion, fechaFin);
		BigDecimal montoInteres = this.calculoCreditoHelper
				.determinaInteresDiario(tasaInteres, montoPrestamo, numeroDias);
		montoInteres = UtilAjusteCalculo.ajusteNumberBaseDatos(montoInteres);		

		periodoGracia.setFechaInicio(fechaConcesion);
		periodoGracia.setFechaFin(fechaFin);
		periodoGracia.setTasaInteres(tasaInteres);
		periodoGracia.setValor(montoInteres);
		periodoGracia.setNumeroDias(numeroDias);
		log.debug(" Periodo gracia : " + periodoGracia.getValor());
		return periodoGracia;
	}
	
	
	public PeriodoGracia determinaPeriodoGraciaInterZafra(Date fechaConcesion,
			BigDecimal tasaInteres, BigDecimal montoPrestamo,int plazoMeses) {

		tasaInteres = UtilAjusteCalculo.ajusteNumberBaseDatos(tasaInteres);
		montoPrestamo = UtilAjusteCalculo.ajusteNumberBaseDatos(montoPrestamo);
		
		
		log.debug(" Parametros----");
		log.debug(" tasaInteres : " + tasaInteres);
		log.debug(" fechaConcesion : " + fechaConcesion);
		log.debug(" montoPrestamo : " + montoPrestamo);
		
		int numeroDias = 0;
		PeriodoGracia periodoGracia = new PeriodoGracia();
		Date fechaFin = null;
		
		numeroDias = ConstantesCreditos.PLAZO_INTERZAFRA * 30;

		BigDecimal montoInteres = this.calculoCreditoHelper
				.determinaInteresDiario(tasaInteres, montoPrestamo, numeroDias);
		montoInteres = UtilAjusteCalculo.ajusteNumberBaseDatos(montoInteres);		

		periodoGracia.setFechaInicio(fechaConcesion);
		periodoGracia.setFechaFin(fechaFin);
		periodoGracia.setTasaInteres(tasaInteres);
		periodoGracia.setValor(montoInteres);
		periodoGracia.setNumeroDias(numeroDias);
		log.debug(" Periodo gracia : " + periodoGracia.getValor());
		return periodoGracia;
	}
	

	public static int diasEntreDosFechas(Date dBeginDate, Date dEndDate) {
		int intMonths = 0;
		Calendar cl_dEndDate = new GregorianCalendar();
		Calendar cl_dBeginDate = new GregorianCalendar();
		cl_dEndDate.setTime(dEndDate);
		cl_dBeginDate.setTime(dBeginDate);
		intMonths = ((cl_dEndDate.get(Calendar.YEAR) - cl_dBeginDate
				.get(Calendar.YEAR)) * 360)
				+ (cl_dEndDate.get(Calendar.MONTH) - cl_dBeginDate
						.get(Calendar.MONTH))
				* 30
				+ (cl_dEndDate.get(Calendar.DATE) - cl_dBeginDate
						.get(Calendar.DATE));
		return intMonths;

	}

}
