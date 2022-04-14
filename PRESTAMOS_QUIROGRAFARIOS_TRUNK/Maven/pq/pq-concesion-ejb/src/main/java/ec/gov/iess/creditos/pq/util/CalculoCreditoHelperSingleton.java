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
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import ec.fin.biess.creditos.pq.dao.ParametroPQDao;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.modelo.persistencia.ParametroPQ;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoPeriodoGracia;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.enumeracion.VariedadCreditoEnum;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * Clase singleton de apoyo para los procesos de calculo del credito
 * quirografario
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
public class CalculoCreditoHelperSingleton {

	LoggerBiess log = LoggerBiess.getLogger(CalculoCreditoHelperSingleton.class);

	private volatile static CalculoCreditoHelperSingleton instanciaUnica;

	private CalculoCreditoHelperSingleton() {
	}

	public static CalculoCreditoHelperSingleton getInstancia() {

		if (instanciaUnica == null) {
			synchronized(CalculoCreditoHelperSingleton.class) {
				if (instanciaUnica == null) {
			instanciaUnica = new CalculoCreditoHelperSingleton();
		}
			}			
		}

		return instanciaUnica;

	}

	public BigDecimal calculoCouta() {
		return null;
	}

	public BigDecimal calculoMonto() {

		return null;
	}

	/**
	 * Devuelve la edad en anios y meses del solicitante con respecto a la fecha
	 * de calculo del prestamo
	 * 
	 * @param fechaNacimiento
	 *            Fecha de nacimiento del solicitante
	 * @param fechaCalculo
	 *            Fecha de calculo del prestamo
	 * @return la edad en anios y meses del solicitante
	 * @author psoria
	 */
	public BigDecimal determinarEdad(Date fechaNacimiento, Date fechaCalculo) {

		Calendar clFechaNacimiento = new GregorianCalendar();
		Calendar clFechaCalculo = new GregorianCalendar();

		clFechaNacimiento.setTime(fechaNacimiento);
		clFechaCalculo.setTime(fechaCalculo);

		log.debug(" fechaNacimiento:" + clFechaNacimiento.getTime());
		log.debug(" fechaCalculo:" + clFechaCalculo.getTime());

		BigDecimal edad = null;

		float fechaToNumber1 = (float) (clFechaNacimiento.get(Calendar.YEAR)
				+ ((clFechaNacimiento.get(Calendar.MONTH) + 1) / 12.00) + (clFechaNacimiento.get(Calendar.DATE) / 360.00));

		float fechaToNumber2 = (float) (clFechaCalculo.get(Calendar.YEAR)
				+ ((clFechaCalculo.get(Calendar.MONTH) + 1) / 12.00) + (clFechaCalculo.get(Calendar.DATE) / 360.00));

		edad = new BigDecimal(fechaToNumber2 - fechaToNumber1);

		edad = edad.setScale(4, BigDecimal.ROUND_HALF_UP);

		log.debug(" Determina Edad : " + edad);

		return edad;

	}

	/**
	 * Determina el plazo máximo de endeudamiento en base a la edad del
	 * solicitante y la esperanza de vida.
	 * 
	 * Si es menor que cero ==> 1 anio
	 * 
	 * Si es mayor o igual que el plazo maximo ==> plazo maximo
	 * 
	 * Si es menor que el plazo máximo ==> la diferencia + 1
	 * 
	 * 
	 * @param edadAniosMeses
	 *            Edad del solicitante en anios y meses
	 * @param esperanzaVida
	 *            Edad de esperanza de vida
	 * @return Número de meses máximo a concederse el prestamo
	 * 
	 * @author psoria
	 */
	public BigDecimal determinarMesesEndeudamiento(BigDecimal edadAniosMeses, BigDecimal esperanzaVida,
			BigDecimal plazoMaximo) {

		BigDecimal diffAniosEsperanza = null;
		BigDecimal plazoMaximoEndeudamiento = null;

		diffAniosEsperanza = esperanzaVida.add(edadAniosMeses.negate());

		log.debug(" Diferencia anios : " + diffAniosEsperanza);

		if (diffAniosEsperanza.floatValue() < 0) {
			log.debug(" Plazo solo a 12 meses");
			plazoMaximoEndeudamiento = new BigDecimal(1);

		} else if (diffAniosEsperanza.floatValue() >= plazoMaximo.floatValue()) {

			log.debug(" Mayor al plazo maximo");

			plazoMaximoEndeudamiento = new BigDecimal(plazoMaximo.floatValue());

		} else if (diffAniosEsperanza.floatValue() < plazoMaximo.floatValue()) {

			log.debug(" Plazo intermedio");

			plazoMaximoEndeudamiento = new BigDecimal(diffAniosEsperanza.floatValue() + 1);

			if (plazoMaximoEndeudamiento.floatValue() > plazoMaximo.floatValue()) {
				plazoMaximoEndeudamiento = new BigDecimal(plazoMaximo.floatValue());

			}
		}

		log.debug(" plazo endeudamiento Anios: " + plazoMaximoEndeudamiento);
		plazoMaximoEndeudamiento = plazoMaximoEndeudamiento.multiply(new BigDecimal(12));

		plazoMaximoEndeudamiento = plazoMaximoEndeudamiento.setScale(0, BigDecimal.ROUND_HALF_UP);
		log.debug(" plazo endeudamiento Meses: " + plazoMaximoEndeudamiento);

		return plazoMaximoEndeudamiento;

	}

	/**
	 * Calcula el monto máximo del prestamo dependiendo de la capacidad de
	 * endeudamiento del solicitante
	 * 
	 * @param cuotaMaximaEndeudamiento
	 *            Monto de la cuota maxima de endeudamiento dependiendo de la
	 *            capacidad de pago
	 * 
	 * @param tasaInteres
	 *            Porcentaje de interes a aplicarse
	 * @param numeroMeses
	 *            Número de anios maximo a realizarse el prestamo *
	 * @return Monto máximo de endeudamiento
	 * 
	 * @author psoria
	 */
	public BigDecimal determinarMontoMaximo(BigDecimal cuotaMaximaEndeudamiento, BigDecimal tasaInteres,
			BigDecimal numeroMeses, BigDecimal garantiaTotal) {

		int meses = 12;

		tasaInteres = tasaInteres.divide(new BigDecimal(100), 10, BigDecimal.ROUND_HALF_UP);
		log.debug(" Tasa / 100 : " + tasaInteres);

		/*
		 * Se divide la tasa de interes para el nuemro de meses
		 */
		BigDecimal jDivM = tasaInteres.divide(new BigDecimal(meses), 10, BigDecimal.ROUND_HALF_UP);
		log.debug(" jDivM : " + jDivM);

		/*
		 * Calculo de 1/(1+j/m)^nm
		 */

		BigDecimal coeficiente = new BigDecimal(1.00 / Math.pow(1.00 + jDivM.floatValue(), numeroMeses.doubleValue()));
		coeficiente = coeficiente.setScale(10, BigDecimal.ROUND_HALF_UP);
		log.debug(" Calculo de 1/(1+j/m)^nm  : " + coeficiente);

		BigDecimal montoMaximo = new BigDecimal(cuotaMaximaEndeudamiento.doubleValue() * (1 - coeficiente.floatValue())
				/ jDivM.doubleValue());

		if (montoMaximo.floatValue() > garantiaTotal.floatValue()) {
			montoMaximo = garantiaTotal;
		}
		montoMaximo = montoMaximo.setScale(10, BigDecimal.ROUND_HALF_UP);

		log.debug(" montoMaximo :" + montoMaximo);
		return montoMaximo;

	}
	
	/**
	 * Calcula en monto maximo de prestamo segun tabla alemana, dependiendo de la capacidad de endeudamiento
	 * y sin ajuste de garantia
	 * 
	 * @param cuotaMaximaEndeudamiento  Monto de la cuota maxima de endeudamiento dependiendo de la
	 *            capacidad de pago
	 * @param tasaInteres porcentaje de intereres a aplicarse
	 * @param numeroMeses numero de meses maximo a realizarse el prestamo
	 * @param garantiaTotal valor total entre fondos de reserva y cesantias
	 * @return monto maximo de endeudamiento segun tabla alemana
	 * @author diana.suasnavas
	 */
	public BigDecimal determinarMontoMaximoAlemana(BigDecimal cuotaMaximaEndeudamiento, BigDecimal tasaInteres,
			BigDecimal numeroMeses) {		
		// Despejando utilizando las formulas Alemanas se obtiene la siguiente formula de montoMaximo
        // Formula: VNC = (1eraCuota * n) / (1 + (i/12) * n)
		// 1eraCuota = es la cuota Maxima de endeudamiento
		// i = tasa nominal anual
		// n = Plazo en el que se estructuro el prestamo.
		
		BigDecimal montoMaximo = BigDecimal.ZERO;
		
		//Se realiza el siguiente calculo (i/12) * n
		BigDecimal auxInteres = ((tasaInteres.divide(BigDecimal.valueOf(12), 15, BigDecimal.ROUND_HALF_UP)).divide(BigDecimal.valueOf(100))).multiply(numeroMeses);
		// Se realiza el siguiente calculo (1 + (i/12) * n)
		BigDecimal auxInteres2 = auxInteres.add(BigDecimal.ONE);
		// Se realiza el siguiente calculo (1eraCuota * n)
		BigDecimal auxCuota =cuotaMaximaEndeudamiento.multiply(numeroMeses);
        // Se calcula el monto (1eraCuota * n) / (1 + (i/12) * n)
		montoMaximo = auxCuota.divide(auxInteres2, 2, BigDecimal.ROUND_HALF_UP);
		
		log.debug(" montoMaximoALEMANA :" + montoMaximo);
		return montoMaximo;
	}

	/**
	 * Calcula el monto maximo del prestamo dependiendo de la capacidad de
	 * endeudamiento del solicitante Francesa
	 * 
	 * @param cuotaMaximaEndeudamiento
	 *            Monto de la cuota maxima de endeudamiento dependiendo de la
	 *            capacidad de pago
	 * 
	 * @param tasaInteres
	 *            Porcentaje de interes a aplicarse
	 * @param numeroMeses
	 *            Número de anios maximo a realizarse el prestamo *
	 * @return Monto máximo de endeudamiento
	 * 
	 * @author psoria
	 */
	public BigDecimal determinarMontoMaximoSinAjuste(BigDecimal cuotaMaximaEndeudamiento, BigDecimal tasaInteres,
			BigDecimal numeroMeses) {

		int meses = 12;

		tasaInteres = tasaInteres.divide(new BigDecimal(100), 10, BigDecimal.ROUND_HALF_UP);
		log.debug(" Tasa / 100 : " + tasaInteres);

		/* Se divide la tasa de interes para el nuemro de meses */
		BigDecimal jDivM = tasaInteres.divide(new BigDecimal(meses), 10, BigDecimal.ROUND_HALF_UP);
		log.debug(" jDivM : " + jDivM);

		/* Calculo de 1/(1+j/m)^nm */

		BigDecimal coeficiente = new BigDecimal(1.00 / Math.pow(1.00 + jDivM.floatValue(), numeroMeses.doubleValue()));
		coeficiente = coeficiente.setScale(10, BigDecimal.ROUND_HALF_UP);
		log.debug(" Calculo de 1/(1+j/m)^nm  : " + coeficiente);

		BigDecimal montoMaximo = new BigDecimal(cuotaMaximaEndeudamiento.doubleValue() * (1 - coeficiente.floatValue())
				/ jDivM.doubleValue());

		montoMaximo = montoMaximo.setScale(10, BigDecimal.ROUND_HALF_UP);

		log.debug(" montoMaximo :" + montoMaximo);
		return montoMaximo;

	}

	/**
	 * Determina la cuota maxima a endeudarse del solicitante esto es para la francesa
	 * 
	 * @param montoMaximoEndeudamiento
	 *            Monto máximo de endeudamiento
	 * @param tasaInteres
	 *            Porcentaje de tasa de interes a aplicarse
	 * 
	 * @param numeroMeses
	 *            Número de anios máximo de endeudamiento
	 * @return Cuota máxima de endeudamiento
	 * 
	 * @author psoria
	 */
	public BigDecimal determinarCuotaMaxima(BigDecimal montoMaximoEndeudamiento, BigDecimal tasaInteres, int numeroMeses) {

		BigDecimal fraccionJM;

		BigDecimal mesesPeriodo = new BigDecimal(12);
		int mesesPorPeriodo = numeroMeses;

		BigDecimal cuotaMaxima = new BigDecimal(0);
		BigDecimal aux = null;

		/*
		 * log.debug("Parámetros"); log.debug("montoMaximoEndeudamiento: " +
		 * montoMaximoEndeudamiento); log.debug("numeroMeses: " + numeroMeses);
		 */

		tasaInteres = tasaInteres.divide(new BigDecimal(100));
		// log.debug("tasaInteres: " + tasaInteres);

		fraccionJM = tasaInteres.divide(mesesPeriodo, 32, RoundingMode.HALF_UP);
		// log.debug("fraccionJM: " + fraccionJM);

		BigDecimal uno = new BigDecimal(1);
		aux = uno.add(fraccionJM); //  new BigDecimal(1 + fraccionJM.floatValue());
		aux = uno.divide(aux.pow(mesesPorPeriodo), 32, RoundingMode.HALF_UP); // new BigDecimal(1 / aux.pow(mesesPorPeriodo).floatValue());
		// log.debug("aux: " + aux);

		cuotaMaxima = montoMaximoEndeudamiento.multiply(fraccionJM).divide(uno.subtract(aux), RoundingMode.HALF_UP);  //new BigDecimal(montoMaximoEndeudamiento.floatValue() * fraccionJM.floatValue() / (1 - aux.floatValue()));
		// log.debug("cuotaMaxima: " + cuotaMaxima);

		return cuotaMaxima;

	}
	
	/**
	 * Determina la cuota maxima de endeudamiento segun las formulas alemanas 
	 * 
	 * @param montoCredito monto del credito solicitado
	 * @param tasaInteres Porcentaje de tasa de interes a aplicarse
	 * @param numeroMeses Numero de anios maximo de endeudamiento
	 * @return cuota maxima
	 * @author diana.suasnavas
	 **/
	public BigDecimal determinarCuotaMaximaAlemana(BigDecimal montoCredito, BigDecimal tasaInteres, int numeroMeses){
		// Para obtener la cuota maxima se la realiza a partir del primer periodo 
		// Formula: Cuota1era = AC + Interes1era        
        // Donde:
		// AC = VNC / n
		// Interesk = [VNC-(k-1)AC](i/12) --> La formula reducida primer periodo es Interesk =  [VNC](i/12)
        // AC = Amortizacion del capital, en cualquier periodo (Constante).
        // VNC = Valor nominal del prestamo.
        // n = Plazo en el que se estructuro el prestamo.

        // Formula final ==> CoutaMax= (VNC/n) + [VNC(i/12)]
		
		// se obtiene el calculo (VNC/n)
		BigDecimal ac = montoCredito.divide(new BigDecimal(numeroMeses), 15 , BigDecimal.ROUND_HALF_UP);
		// se obtiene el calculo (i/12)
		BigDecimal interesNominal = (tasaInteres.divide(BigDecimal.valueOf(12), 15, BigDecimal.ROUND_HALF_UP)).divide(BigDecimal.valueOf(100));
		// se obtiene el calculo [VNC(i/12)]
		BigDecimal aux = montoCredito.multiply(interesNominal);
		//se obtiene el calculo (VNC/n) + [VNC(i/12)]
		BigDecimal coutaMaxima = ac.add(aux);		
		
		return coutaMaxima; 
		
	}

	/**
	 * Determina el número de meses dependiendo del número de anios
	 * (decimal) enviado
	 * 
	 * @param edadAniosMese
	 *            Edad en anio y meses (BigDecimal)
	 * @return número de meses del monto en anios enviado
	 * 
	 * @author psoria
	 */
	public int determinarNumeroMeses(BigDecimal edadAniosMese) {

		BigDecimal meses = new BigDecimal((edadAniosMese.floatValue() - edadAniosMese.intValue()));

		meses = meses.multiply(new BigDecimal(12));

		meses = meses.setScale(0, BigDecimal.ROUND_HALF_UP);

		return meses.intValue();
	}

	/**
	 * Determina el Monto de seguro de desgravamen mensual
	 * 
	 * @param porcentajeSeguro
	 *            Porcentaje de Seguro de desgravamen
	 * @param montoPrestamo
	 *            Monto a comprometerse en el prestamo
	 * @return Monto de seguro de desgravamen mensual
	 * 
	 * @author psoria
	 */
	public BigDecimal determinaSeguroDesgravamenMensual(BigDecimal porcentajeSeguro, BigDecimal montoPrestamo) {

		BigDecimal seguroDesgravamenMensual;

		log.debug("Parametros");
		log.debug("porcentajeSeguro: " + porcentajeSeguro);
		log.debug("montoPrestamo: " + montoPrestamo);

		seguroDesgravamenMensual = new BigDecimal(
				(montoPrestamo.floatValue() * porcentajeSeguro.floatValue() / 100) / 12);

		seguroDesgravamenMensual = seguroDesgravamenMensual.setScale(2, BigDecimal.ROUND_HALF_UP);

		log.debug("seguroDesgravamenMensual: " + seguroDesgravamenMensual);
		return seguroDesgravamenMensual;

	}

	/**
	 * Calcula el interes dependiendo de un número de diás
	 * 
	 * @param porcentajeSeguro
	 *            tasa de interes a aplicarse si tasa = 8.9% => 8.9
	 * @param montoPrestamo
	 *            Monto total del prestamo a concederse
	 * @param numeroDias
	 *            Numero de dias a calcularse el interes
	 * @return Valor del interes correspondiente al número de dias enviado
	 */

	public BigDecimal determinaInteresDiario(BigDecimal porcentajeSeguro, BigDecimal montoPrestamo, int numeroDias) {

		BigDecimal interesNumeroDias = null;
		BigDecimal interesPorcentajeValor = null;

		// Saca valor de porcentaje a aplicarse 8.9% => 0.089
		interesPorcentajeValor = porcentajeSeguro.divide(new BigDecimal(100));
		interesPorcentajeValor = UtilAjusteCalculo.getEsacalaDigitosCalculo(interesPorcentajeValor);
		log.debug("interesPorcentajeValor: " + interesPorcentajeValor);

		// Saca valor de interes en número de dias

		interesNumeroDias = new BigDecimal(montoPrestamo.floatValue() * interesPorcentajeValor.floatValue()
				* numeroDias / 360);

		interesNumeroDias = interesNumeroDias.setScale(4, BigDecimal.ROUND_HALF_UP);

		log.debug("interesTotalDias: " + interesNumeroDias);

		return interesNumeroDias;
	}

	/**
	 * Calcula el interes mensual
	 * 
	 * @param porcentajeSeguro
	 *            tasa de interes a aplicarse si tasa = 8.9% => 8.9
	 * @param montoPrestamo
	 *            Monto total del prestamo a concederse *
	 * @return Valor del interes correspondiente al número de dias enviado
	 */

	public BigDecimal determinaInteresMensual(BigDecimal porcentajeSeguro, BigDecimal montoPrestamo) {

		BigDecimal interesMensual = null;
		BigDecimal interesPorcentajeValor = null;

		// Saca valor de porcentaje a aplicarse 8.9% => 0.089
		interesPorcentajeValor = porcentajeSeguro.divide(new BigDecimal(100));
		interesPorcentajeValor = UtilAjusteCalculo.getEsacalaDigitosCalculo(interesPorcentajeValor);
		// log.debug("interesPorcentajeValor: " + interesPorcentajeValor);

		// Saca valor de interes en número de dias

		log.debug(montoPrestamo);
		log.debug(interesPorcentajeValor);

		interesMensual = montoPrestamo.multiply(interesPorcentajeValor)
				.divide(new BigDecimal(12), RoundingMode.HALF_UP);

		// log.debug("interesMensual: " + interesMensual);

		return interesMensual;
	}

	/**
	 * Determina Fecha De inicio de prestamo en el caso de ser mes siguiente -
	 * fecha al 1 del siguiente mes en el caso de ser mes subsiguiente - fecha
	 * al 1 de 2 meses mas
	 * 
	 * @param fechainicial
	 *            fecha de concesion del crédito
	 * @param valTipoPeriodoGracia
	 *            Si es mes siguiente o subsiguiente
	 * @return
	 */
	public Date determinaFechaFin(Date fechainicial, TipoPeriodoGracia tipoPeriodoGracia) {

		Calendar clFechaInicial = new GregorianCalendar();
		Calendar clFechaFinal = new GregorianCalendar();

		clFechaInicial.setTime(fechainicial);
		clFechaInicial.set(Calendar.DATE, 1);

		if (TipoPeriodoGracia.MES_SIGUIENTE.equals(tipoPeriodoGracia)) {
			clFechaFinal.set(Calendar.YEAR, clFechaInicial.get(Calendar.YEAR));
			clFechaFinal.set(Calendar.DATE, 1);
			clFechaFinal.set(Calendar.MONTH, clFechaInicial.get(Calendar.MONTH) + 1);

		}
		if (TipoPeriodoGracia.MES_SUBSIGUIENTE.equals(tipoPeriodoGracia)) {
			clFechaFinal.set(Calendar.YEAR, clFechaInicial.get(Calendar.YEAR));
			clFechaFinal.set(Calendar.DATE, 1);
			clFechaFinal.set(Calendar.MONTH, clFechaInicial.get(Calendar.MONTH) + 2);
		}
		if (TipoPeriodoGracia.FIN_DE_MES.equals(tipoPeriodoGracia)) {
			// La fecha de fin del periodo de gracia es el ultimo dia del mes
			clFechaFinal.setTime(fechainicial);
			clFechaFinal.set(Calendar.DATE, clFechaInicial.getActualMaximum(Calendar.DATE));
		}
		
		if (TipoPeriodoGracia.PERIODO_INTERZAFRA.equals(tipoPeriodoGracia))
		{
			clFechaFinal.set(Calendar.YEAR, clFechaInicial.get(Calendar.YEAR));
			clFechaFinal.set(Calendar.DATE, 1);
			clFechaFinal.set(Calendar.MONTH, clFechaInicial.get(Calendar.MONTH) + (ConstantesCreditos.PLAZO_INTERZAFRA + 1));
		}
			
		return clFechaFinal.getTime();
	}
	
	
	public static int determinarIdVariedadCredito(TipoPrestamista tipoPrestamista, OrigenJubilado origenJubilado) throws CalculoCreditoException{
		int idVariedadCredito;
		if (tipoPrestamista.equals(TipoPrestamista.JUBILADO)) {
			if (origenJubilado.equals(OrigenJubilado.HL)) {
				idVariedadCredito = VariedadCreditoEnum.CREDITO_JUBILADO_HL.getIdVariedad();
			} else if (origenJubilado.equals(OrigenJubilado.HOUIO)) {
				idVariedadCredito = VariedadCreditoEnum.CREDITO_JUBILADO_HOST_UIO.getIdVariedad();
			} else { // HOGYE
				idVariedadCredito = VariedadCreditoEnum.CREDITO_JUBILADO_HOST_GYE.getIdVariedad();
			}
		} else if(TipoPrestamista.AFILIADO.equals(tipoPrestamista)){// AFILIADO
			idVariedadCredito = VariedadCreditoEnum.CREDITO_AFILIADO.getIdVariedad();
		}else if(TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista)){
			idVariedadCredito = VariedadCreditoEnum.CREDITO_ZAFRERO_TEMPORAL.getIdVariedad();
		}else{
			throw new CalculoCreditoException(
					"NO SE PUEDE DETERMINAR UNA VARIEDAD DE CREDITO PARA EL TIPO DE SOLICITANTE: "
							+ tipoPrestamista);
		}
		return idVariedadCredito;
	}
	
	/**
	 * Metodo que retorna el dia de la fecha actual.
	 * 
	 * @return int
	 */
	private int determinarDiaMes() {
		Calendar clFechaActual = Calendar.getInstance();
		clFechaActual.setTime(new Date());
		
		return clFechaActual.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Metodo que retorna el mes de la fecha actual.
	 * 
	 * @return
	 */
	private int determinarMes() {
		Calendar clFechaActual = Calendar.getInstance();
		clFechaActual.setTime(new Date());
		
		return clFechaActual.get(Calendar.MONTH) + 1;
	}
				
	/**
	 * Metodo que determina si el menu de novaciones esta habilitado o no dependiendo de la fecha.
	 * 
	 * @throws CalculoCreditoException 
	 * 
	 */
	public boolean habilitarNovacion() throws CalculoCreditoException {
		boolean habilitarNovacion = false;
		try {			
			int mes = determinarMes();
			int dia = determinarDiaMes();
			/* En los meses de enero a noviembre el menu de novaciones se deshabilita desde el 27 hasta el fin de mes */
			if (mes != 12 && dia >= 1 && dia < 27) {
				habilitarNovacion = true;
			}
			/* En el mes de diciembre el menu de novaciones se deshabilita desde el 25 hasta el fin de mes */
			if (mes == 12 && dia >= 1 && dia < 25) {
				habilitarNovacion = true;
			}			
		} catch (Exception e) {
			throw new CalculoCreditoException("Error al habilitar opcion de menu para novaciones.", e);
		}
		
		return habilitarNovacion;
	}

	/**
	 * Indica si el proceso de planillas se esta ejecutando
	 * 
	 * @param parametroPQDao dao para ejecutar consulta del parametro
	 * @return boolean indica si el proceso de planillas se esta ejecutando
	 */
	public boolean procesoPlanillaRunning(ParametroPQDao parametroPQDao) {
		try {
			/* Busca parametro que indica si se esta ejecutando el proceso de planillas */
			ParametroPQ parampq = parametroPQDao.consultarPorCodigo("BOPOLCORPQ", 1);
			if (null == parampq) {
				return true;
			}
			if (parampq.getValorCaracter().trim().compareTo("1") == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("Error al determinar si el proceso de planillas se esta ejecutando", e);
			return true;
		}
	}
	
	/**
	 * Indica si el reajuste de tasas de interes se esta ejecutando
	 * 
	 * @param parametroPQDao dao para ejecutar consulta del parametro
	 * @return boolean indica si el reajuste de tasas de interes se esta ejecutando
	 */
	public boolean reajusteTasasRunning(ParametroPQDao parametroPQDao) {
		try {
			/* Busca parametro que indica si se esta ejecutando el reajuste de tasas */
			ParametroPQ parampq = parametroPQDao.consultarPorCodigo("BLTASCORPQ", 1);
			if (null == parampq) {
				return true;
			}
			if (parampq.getValorCaracter().trim().compareTo("1") == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("Error al determinar si el reajuste de tasas de interes se esta ejecutando", e);
			return true;
		}
	}
	
	/**
	 * Obtiene el monto maximo de credito segun tipo de amortizacion deseada
	 * 
	 * @param cuotaMaximaEndeudamiento
	 * @param tasaInteres
	 * @param numeroMeses
	 * @param garantiaTotal
	 * @param tipo
	 *            que puede ser ALEMANA o FRANCESA
	 * @return MontoMaximo permitido
	 * @throws MontosMaximosException
	 * 
	 */
	public BigDecimal obtenerMontoMaximoPorTipoAmortizacionSinAjuste(
			BigDecimal cuotaMaximaEndeudamiento, BigDecimal tasaInteres,
			BigDecimal numeroMeses, String tipo) throws MontosMaximosException {

		BigDecimal montoMaximo = null;
		TipoTablaEnum tipoTabla = TipoTablaEnum.valueOf(tipo);
		switch (tipoTabla) {
		case ALEMANA:
			montoMaximo = determinarMontoMaximoAlemana(
					cuotaMaximaEndeudamiento, tasaInteres, numeroMeses);
			break;
		case FRANCESA:
			montoMaximo = determinarMontoMaximoSinAjuste(
					cuotaMaximaEndeudamiento, tasaInteres, numeroMeses);
			break;
		default:
			throw new MontosMaximosException(
					"EL tipo de Tabla deber ser ALEMANA o FRANCESA");
		}

		return montoMaximo;

	}

}
