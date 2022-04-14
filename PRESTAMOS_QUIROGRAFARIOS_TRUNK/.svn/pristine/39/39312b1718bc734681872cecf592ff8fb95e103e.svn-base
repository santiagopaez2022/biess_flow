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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.enumeracion.TipoPeriodoGracia;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Clase de realiza los calculos del credito.
 * 
 * @version 1.o
 * @author cvillarreal
 * 
 */
public class CalculoValoresCredito {

	LoggerBiess log = LoggerBiess.getLogger(CalculoValoresCredito.class);

	private CalculoCreditoHelperSingleton calculoCreditoHelper;

	/**
	 * 
	 */
	public CalculoValoresCredito() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();
	}

	/**
	 * Llena todos los datos del prestamo y los dividendos de la tabla de
	 * amortizacion
	 * 
	 * @param tasaInteres
	 *            Tasa de interes a aplicarse
	 * @param monto
	 *            Monto total del prestamo
	 * @param plazo
	 *            Plazo en meses
	 * @param tasaSeguroDesgravamen
	 *            tasa del seguro de desgravamen
	 * @param fechaConcesion
	 *            fecha de la concesion
	 * @return prestamoCalculo {@link}prestamoCalculo
	 * 
	 * @author psoria
	 */
	public PrestamoCalculo poblarPrestamoCalculo(DatosCredito datosCredito) {

		log.debug("CARGA PRESTAMO");
		// String cedula = datosCredito.getCedulaAfiliado();
		// BigDecimal tasaInteres =
		// UtilAjusteCalculo.ajusteNumber(datosCredito.getTasaInteres(), 2);
		// BigDecimal monto =
		// UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2);
		// int plazo = datosCredito.getPlazo();
		// Date fechaConcesion = datosCredito.getFechaSolicitud();

		// Date inicioPeriodoGracia = prestamoCalculo.
		PrestamoCalculo prestamoCalculo = new PrestamoCalculo();
		CalculoPeriodoGracia calculoPeriodoGracia = new CalculoPeriodoGracia();
		List<DividendoCalculo> dividendoCalculolist = new ArrayList<DividendoCalculo>();

		log.debug("Calculo de inicio de periodo de gracia");

		// determina periodo de gracia
		prestamoCalculo.setPeriodoGracia(calculoPeriodoGracia
				.determinaPeriodoGracia(datosCredito.getFechaSolicitud(),
						UtilAjusteCalculo.ajusteNumber(datosCredito
								.getTasaInteres(), 2), UtilAjusteCalculo
								.ajusteNumber(datosCredito.getMonto(), 2)));

		log.debug("PeriodoGracia: "
				+ prestamoCalculo.getPeriodoGracia().getValor());

		prestamoCalculo.setPlazoMeses(datosCredito.getPlazo());
		log.debug("plazo: " + datosCredito.getPlazo());

		prestamoCalculo.setValorCredito(UtilAjusteCalculo.ajusteNumber(
				datosCredito.getMonto(), 2));
		log.debug("monto: " + prestamoCalculo.getValorCredito());

		BigDecimal valorDividendo = this.calculoCreditoHelper
				.determinarCuotaMaxima(UtilAjusteCalculo.ajusteNumber(
						datosCredito.getMonto(), 2), UtilAjusteCalculo
						.ajusteNumber(datosCredito.getTasaInteres(), 2),
						datosCredito.getPlazo());

		prestamoCalculo.setValorTotalDividendoMensual(valorDividendo);
		log.debug("valordividento: "
				+ prestamoCalculo.getValorTotalDividendoMensual());

		prestamoCalculo.setCedula(datosCredito.getCedulaAfiliado());
		log.debug("cedula: " + datosCredito.getCedulaAfiliado());

		prestamoCalculo.setMontoPrestamo(UtilAjusteCalculo.ajusteNumber(
				datosCredito.getMonto(), 2));
		log.debug("montoPrestamo: "
				+ UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2));

		// TODO: Calcular la fecha de tranferencia
		// prestamoCalculo.setFechaTrasferencia(fechaInicioPeriodoGracia);

		/*
		 * prestamoCalculo.setDividendoCalculoList(
		 * poblarDividendoCalculo(tasaInteres, monto, plazo, prestamoCalculo
		 * .getPeriodoGracia().getFechaInicio(),
		 * prestamoCalculo.getPeriodoGracia().getFechaFin(), periodoGracia));
		 */
		datosCredito.setPrestamoCalculo(prestamoCalculo);
		prestamoCalculo
				.setDividendoCalculoList(poblarDividendoCalculo(datosCredito));

		if (prestamoCalculo.getDividendoCalculoList().size() > 1) {
			Date fechaIni = ((DividendoCalculo) prestamoCalculo
					.getDividendoCalculoList().get(1)).getFechapagoDividendo();
			prestamoCalculo.setFechaInicioPrestamo(fechaIni);
		} else {
			prestamoCalculo.setFechaInicioPrestamo(datosCredito
					.getFechaSolicitud());
		}

		log.debug("fechaInicioPrestamo: "
				+ (new SimpleDateFormat("dd/MM/yyyy")).format(prestamoCalculo
						.getFechaInicioPrestamo()));

		dividendoCalculolist = prestamoCalculo.getDividendoCalculoList();
		// Aqui tienes el ultimo dividendo calculo como un objeto
		DividendoCalculo dividendoCalculoUltimo = dividendoCalculolist
				.get(dividendoCalculolist.size() - 1);

		Date fechaFinPrestamo = dividendoCalculoUltimo.getFechapagoDividendo();
		prestamoCalculo.setFechaFinPrestamo(fechaFinPrestamo);
		log.debug("fechaFinPrestamo: " + fechaFinPrestamo);
		return prestamoCalculo;
	}

	/**
	 * Actualiza datos de los dividendos de un credito
	 * 
	 * @param interes
	 *            tasa de interes a aplicaarse
	 * @param monto
	 *            monto total del prestamo
	 * @param plazo
	 *            plazo en meses del prestama
	 * @param tasaSeguroDesgravamen
	 *            tasa de seguroo de saldos
	 * @param fechaConcesion
	 *            fecha de concesion del credito
	 * @param fechaPagoCredito
	 *            fecha del pago del credito
	 * @return listado de dividendos
	 * 
	 * @author psoria
	 */

	/*
	 * private int varidadCredito; private int tipoCredito; private String
	 * tipoPeriodo = "M"; private int anioDividendo; private int mesDividendo;
	 */

	protected List<DividendoCalculo> poblarDividendoCalculo(
			DatosCredito datosCredito) {

		BigDecimal interes = UtilAjusteCalculo.ajusteNumber(datosCredito
				.getTasaInteres(), 2);
		BigDecimal monto = UtilAjusteCalculo.ajusteNumber(datosCredito
				.getMonto(), 2);
		int plazo = datosCredito.getPlazo();
		Date fechaPagoCredito = datosCredito.getPrestamoCalculo()
				.getPeriodoGracia().getFechaFin();
		BigDecimal periodoGracia = datosCredito.getPrestamoCalculo()
				.getPeriodoGracia().getValor();

		int numeroDecimales = 2;

		List<DividendoCalculo> dividendoCalculoList = new ArrayList<DividendoCalculo>();

		BigDecimal saldoCapitalAnterior = new BigDecimal(0);

		Calendar clfechaPagoDividendo = new GregorianCalendar();
		clfechaPagoDividendo.setTime(fechaPagoCredito);
		Date fechaPagoDividendo = new Date();

		BigDecimal valorInteres = new BigDecimal(0);
		BigDecimal abonoCapital = new BigDecimal(0);
		BigDecimal valorCuota = UtilAjusteCalculo.ajusteNumber(
				calculoCreditoHelper.determinarCuotaMaxima(monto, interes,
						plazo), numeroDecimales);
		int numeroDividendo = 0;

		CalculoPeriodoGracia calculoPeriodoGraciaZafra = new CalculoPeriodoGracia();
		BigDecimal periodoGraciaInterZafra = new BigDecimal(0);
		BigDecimal periodoGraciaPorrateado = new BigDecimal(0);
		BigDecimal diferenciaPorrateo = new BigDecimal(0);
		int nuevoPlazo = 0;
		datosCredito.getPrestamoCalculo().setPeriodoGraciaPorrateadoInterZafra(
				new BigDecimal(0));
		datosCredito.getPrestamoCalculo().setPeriodoGraciaInterZafra(null);

		// primer registro --> todos cero y carga solo saldo capital
		DividendoCalculo dividendoCalculo = new DividendoCalculo();
		dividendoCalculo.setvalorSaldoCapital(monto);
		dividendoCalculo.setNumeroDividendo(numeroDividendo);
		dividendoCalculo.setValorInteres(new BigDecimal(0));
		dividendoCalculo.setValorCuota(new BigDecimal(0));
		dividendoCalculo.setValorAbonoCapital(new BigDecimal(0));
		dividendoCalculo.setValorDividendo(new BigDecimal(0));
		dividendoCalculo.setValorSegurSaldo(new BigDecimal(0));
		dividendoCalculo.setTipoPeriodo("M");
		dividendoCalculo.setAnioDividendo(0);
		dividendoCalculo.setMesDividendo(0);
		dividendoCalculoList.add(dividendoCalculo);

		log
				.debug("capital inicial: "
						+ dividendoCalculo.getvalorSaldoCapital());

		saldoCapitalAnterior = monto;

		log.debug("saldoCapitalAnterior: " + saldoCapitalAnterior);

		for (int i = 0; i < plazo; i++) {

			DividendoCalculo dividendoCalculoFor = new DividendoCalculo();

			// numero de dividendo
			numeroDividendo = numeroDividendo + 1;
			dividendoCalculoFor.setNumeroDividendo(numeroDividendo);

			// Para calcular la fecha final en caso de zafreros
			// fecha de pago
			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datosCredito
					.getTipoPrestamista())) {
				if (i == (ConstantesCreditos.PLAZO_INTERZAFRA)) {
					fechaPagoDividendo = this.calculoCreditoHelper
							.determinaFechaFin(fechaPagoDividendo,
									TipoPeriodoGracia.PERIODO_INTERZAFRA);
				} else {
					fechaPagoDividendo = this.calculoCreditoHelper
							.determinaFechaFin(fechaPagoDividendo,
									TipoPeriodoGracia.MES_SIGUIENTE);
				}
				if (i == (ConstantesCreditos.PLAZO_INTERZAFRA)) {
					datosCredito
							.getPrestamoCalculo()
							.setPeriodoGraciaInterZafra(
									calculoPeriodoGraciaZafra
											.determinaPeriodoGraciaInterZafra(
													datosCredito
															.getFechaSolicitud(),
													interes,
													UtilAjusteCalculo
															.ajusteNumber(
																	saldoCapitalAnterior,
																	2), plazo));

					periodoGraciaInterZafra = UtilAjusteCalculo
					.ajusteNumber(datosCredito.getPrestamoCalculo()
							.getPeriodoGraciaInterZafra().getValor(),2);
					nuevoPlazo = plazo - ConstantesCreditos.PLAZO_INTERZAFRA;
					periodoGraciaPorrateado =  UtilAjusteCalculo.ajusteNumber(BigDecimal.valueOf(periodoGraciaInterZafra.doubleValue() / nuevoPlazo),2);
					datosCredito.getPrestamoCalculo()
							.setPeriodoGraciaPorrateadoInterZafra(
									periodoGraciaPorrateado);
				}
				if (i >= ConstantesCreditos.PLAZO_INTERZAFRA) {
					dividendoCalculoFor.setValorDividendo(valorCuota
							.add(datosCredito.getPrestamoCalculo()
									.getPeriodoGraciaPorrateadoInterZafra()));
				} else {
					dividendoCalculoFor.setValorDividendo(valorCuota);
				}
			} else {
				fechaPagoDividendo = this.calculoCreditoHelper
						.determinaFechaFin(fechaPagoDividendo,
								TipoPeriodoGracia.MES_SIGUIENTE);
				dividendoCalculoFor.setValorDividendo(valorCuota);
			}

			clfechaPagoDividendo.setTime(fechaPagoDividendo);
			dividendoCalculoFor.setFechapagoDividendo(clfechaPagoDividendo
					.getTime());

			// valor interes
			valorInteres = this.calculoCreditoHelper.determinaInteresMensual(
					interes, saldoCapitalAnterior);
			valorInteres = UtilAjusteCalculo.ajusteNumber(valorInteres,
					numeroDecimales);

			// cuota = dividendo, seguro saldos = 0
			dividendoCalculoFor.setValorInteres(valorInteres);

			// dividendoCalculoFor.setValorCuota(valorCuota);
			dividendoCalculoFor.setValorSegurSaldo(new BigDecimal(0));
			// solo para primer dividendo, se incluye el periodo de gracia
			if (i == 0) {
				dividendoCalculoFor.setValorDividendo(valorCuota
						.add(periodoGracia));
			}

			dividendoCalculoFor.setTipoPeriodo("M");
			dividendoCalculoFor.setAnioDividendo(clfechaPagoDividendo
					.get(Calendar.YEAR));
			dividendoCalculoFor.setMesDividendo(clfechaPagoDividendo
					.get(Calendar.MONTH) + 1);

			// abono capital
			abonoCapital = valorCuota.subtract(valorInteres);

			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datosCredito
					.getTipoPrestamista())) {
				// En la ultima cuota el abono capital debe ser el saldo
				if (plazo == i + 1) {
					diferenciaPorrateo = BigDecimal.valueOf(periodoGraciaInterZafra.doubleValue() - (periodoGraciaPorrateado.doubleValue() * nuevoPlazo));
					if (diferenciaPorrateo.doubleValue() != 0)
					{
						periodoGraciaPorrateado = BigDecimal.valueOf(periodoGraciaPorrateado.doubleValue() + diferenciaPorrateo.doubleValue());
						dividendoCalculoFor
						.setValorAbonoCapital(saldoCapitalAnterior);
				        dividendoCalculoFor.setValorDividendo(saldoCapitalAnterior
						.add(valorInteres.add(periodoGraciaPorrateado)));
				        dividendoCalculoFor.setValorCuota(saldoCapitalAnterior
						.add(valorInteres.add(periodoGraciaPorrateado)));
				       saldoCapitalAnterior = new BigDecimal(0);
					}
					else
					{
						dividendoCalculoFor
						.setValorAbonoCapital(saldoCapitalAnterior);
				        dividendoCalculoFor.setValorDividendo(saldoCapitalAnterior
						.add(valorInteres.add(datosCredito
								.getPrestamoCalculo()
								.getPeriodoGraciaPorrateadoInterZafra())));
				        dividendoCalculoFor.setValorCuota(saldoCapitalAnterior
						.add(valorInteres.add(datosCredito
								.getPrestamoCalculo()
								.getPeriodoGraciaPorrateadoInterZafra())));
				       saldoCapitalAnterior = new BigDecimal(0);
					}
					
				} else {
					dividendoCalculoFor.setValorAbonoCapital(abonoCapital);
					saldoCapitalAnterior = saldoCapitalAnterior
							.subtract(abonoCapital);
				}
			} else {
				// En la ultima cuota el abono capital debe ser el saldo
				if (plazo == i + 1) {
					dividendoCalculoFor
							.setValorAbonoCapital(saldoCapitalAnterior);
					dividendoCalculoFor.setValorDividendo(saldoCapitalAnterior
							.add(valorInteres));
					dividendoCalculoFor.setValorCuota(saldoCapitalAnterior
							.add(valorInteres));
					saldoCapitalAnterior = new BigDecimal(0);
				} else {
					dividendoCalculoFor.setValorAbonoCapital(abonoCapital);
					saldoCapitalAnterior = saldoCapitalAnterior
							.subtract(abonoCapital);
				}
			}

			dividendoCalculoFor.setvalorSaldoCapital(saldoCapitalAnterior);

			dividendoCalculoFor.setValorPeriodoGraciaInterzafra(periodoGraciaPorrateado);
			
			dividendoCalculoList.add(dividendoCalculoFor);

		}

		log.debug(dividendoCalculoList.size());
		log.debug("periodo de gracia: " + periodoGracia.floatValue());
		log
				.debug("Numero      interes      abonocapital     dividendo      saldocapital     ");

		BigDecimal totalInteresPagado = new BigDecimal(0);
		BigDecimal totalCapitalPagado = new BigDecimal(0);

		for (DividendoCalculo dividendo : dividendoCalculoList) {

			totalInteresPagado = totalInteresPagado.add(dividendo
					.getValorInteres());
			totalCapitalPagado = totalCapitalPagado.add(dividendo
					.getValorAbonoCapital());

			try {
				log.debug(dividendo.getNumeroDividendo()
						+ "     "
						+ dividendo.getValorInteres()
						+ "      "
						+ dividendo.getValorAbonoCapital()
						+ "           "
						+ dividendo.getValorDividendo()
						+ "         "
						+ dividendo.getvalorSaldoCapital()
						+ "      "
						+ (new SimpleDateFormat("MM/yyyy")).format(dividendo
								.getFechapagoDividendo()));
			} catch (NullPointerException e) {
				log.debug(" ERROR : " + dividendo.getAnioDividendo() + "  "
						+ dividendo.getMesDividendo());
			}
		}
		log
				.debug("Numero      interes      abonocapital     dividendo      saldocapital     ");

		log.debug("Totales");
		log.debug("Interes:" + totalInteresPagado);
		log.debug("Capital:" + totalCapitalPagado);

		return dividendoCalculoList;
	}

}
