/*
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.AniosPlazoCapitalEndeudamientoDao;
import ec.gov.iess.creditos.dao.TasaInteresDao;
import ec.gov.iess.creditos.dao.TasaInteresDetalleDao;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.TasaInteresDaoException;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.dto.CondicionCalculo;
import ec.gov.iess.creditos.modelo.persistencia.TasaInteresDetalle;
import ec.gov.iess.creditos.pq.excepcion.CondicionCalculoException;
import ec.gov.iess.creditos.pq.excepcion.EsperanzaVidaException;
import ec.gov.iess.creditos.pq.excepcion.NoexisteTasaActuarialException;
import ec.gov.iess.creditos.pq.excepcion.NoexisteTasaSeguroDesgravamenException;
import ec.gov.iess.creditos.pq.excepcion.PlazoMaximoEndeudamientoException;
import ec.gov.iess.creditos.pq.excepcion.SemanaTasaInteresBancoCentralException;
import ec.gov.iess.creditos.pq.excepcion.TasaActuarialCeroException;
import ec.gov.iess.creditos.pq.excepcion.TasaActuarialException;
import ec.gov.iess.creditos.pq.excepcion.TasaActurialDuplicadaException;
import ec.gov.iess.creditos.pq.excepcion.TasaBancoCentralCeroException;
import ec.gov.iess.creditos.pq.excepcion.TasaDesgravamenCeroException;
import ec.gov.iess.creditos.pq.excepcion.TasaDesgravamenDuplicadaException;
import ec.gov.iess.creditos.pq.excepcion.TasaInteresBancoCentralException;
import ec.gov.iess.creditos.pq.excepcion.TasaSeguroDesgravamen;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicioRemoto;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.CondicionCalculoHelperSingleton;

/** 
 * <b>
 * Cálculo de datos para la cabecera del préstamo quirografario.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.4 $ <p>[$Author: dimbacuanl $, $Date: 2011/06/14 19:43:08 $]</p>
*/ 
@Stateless
public class CondicionCalculoServicioImpl implements CondicionCalculoServicio,
		CondicionCalculoServicioRemoto {

	LoggerBiess log = LoggerBiess.getLogger(CondicionCalculoServicioImpl.class);

	@EJB
	TasaInteresDao tasaInteresDao;
	@EJB
	TasaInteresDetalleDao tasaInteresDetalleDao;
	@EJB
	AniosPlazoCapitalEndeudamientoDao aniosPlazoCapitalEndeudamientoDao;
	
	@EJB
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;

	CondicionCalculoHelperSingleton condicionCalculoHelper;

	CalculoCreditoHelperSingleton calculoCreditoHelper;

	public CondicionCalculoServicioImpl() {
	}

	@PostConstruct
	public void init() {
		log.debug(" Inicializacion de variables");
		this.condicionCalculoHelper = CondicionCalculoHelperSingleton
				.getInstancia();
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();

	}

	public BigDecimal consultarEsperanzaVida(String genero,
			Date fechaNacimeinto, Date fechaSolicitud)
			throws EsperanzaVidaException {

		log.info(" Determina la esperannza de vida");
		log.debug(" fecha nacimiento : " + fechaNacimeinto);
		log.debug(" fecha solicitud : " + fechaSolicitud);

		if ("M".equals(genero)) {
			return new BigDecimal("200");
		} else {
			return new BigDecimal("200");
		}
	}

	public BigDecimal consultarTasaInteres(String idtasaBC, String idtasaACT,
			Date fechaSolicitud, int semanas) throws TasaInteresExcepcion {

		// Posicionar en semana anterior
		Date fechaCalculo = condicionCalculoHelper.determinarFechainicioTasaInteres(fechaSolicitud, 1);

		// Retroceder las semanas de calculo
		Date desdeAjustada = condicionCalculoHelper.determinarFechainicioTasaInteres(fechaSolicitud, semanas);
		desdeAjustada = retrocederAlLunes(desdeAjustada);
		
		// Avanzar al domingo 
		Date hastaAjustada = avanzarAlDomingo(fechaCalculo);

		log.info(" Tasa de interes");
		// int semanas = 9;
		Calendar clDesde = Calendar.getInstance();
		clDesde.setTime(fechaSolicitud);
		log.debug(" fecha desde : " + clDesde.getTime());


		log.debug(" fecha desde : " + desdeAjustada);
		log.debug(" fecha hasta : " + hastaAjustada);

		BigDecimal tasaBancoCentral = consultarTasaBancoCentral(idtasaBC,
				desdeAjustada, hastaAjustada, semanas);
		log.debug("tasa banco central:" + tasaBancoCentral);

		BigDecimal tasaActuarial = consultarTasaActuarial(idtasaACT, clDesde
				.getTime());
		log.debug("tasa actuarial:" + tasaActuarial);

		/*
		 * promedio de la tasa de interes del banco central y la de actuarial
		 */
		BigDecimal tasaTotal = tasaBancoCentral.add(tasaActuarial);

		tasaTotal = tasaTotal.divide(new BigDecimal(2), 4,
				BigDecimal.ROUND_HALF_UP);

		log.debug("Total TASA ->  BC : " + tasaBancoCentral + " + TAC : "
				+ tasaActuarial + " = suma /2 = " + tasaTotal);
		
		log.debug("tasa calculada:" + tasaTotal);
		
		return tasaTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal consultarTasaBancoCentral(String idTipoTasa,
			Date fechaDesde, Date fechaHasta, int semanas)
			throws TasaInteresBancoCentralException {

		log.info(" tasa banco central");
		log.debug(" Tipo tasa : " + idTipoTasa);
		log.debug(" fechaDesde : " + fechaDesde);
		log.debug(" fechaHasta : " + fechaHasta);

		BigDecimal tasaInteresDetalleCount = null;
		try {
			tasaInteresDetalleCount = tasaInteresDetalleDao
					.consultaRangoFechasInicialTipoTasaInteres(idTipoTasa,
							fechaDesde, fechaHasta);
			log.debug(" tasaInteresDetalleCount : "
					+ tasaInteresDetalleCount.intValue());
			if (tasaInteresDetalleCount.intValue() < semanas) {
				log
						.error(" Semanas menor a  : "
								+ tasaInteresDetalleCount.intValue() + " <= "
								+ semanas);
				throw new SemanaTasaInteresBancoCentralException();
			}

		} catch (TasaInteresDaoException e) {
			log.error(" Tasa interes consulta :", e);
			throw new TasaInteresBancoCentralException(e);
		}

		BigDecimal tasaPromedioBC = null;

		try {
			tasaPromedioBC = tasaInteresDetalleDao
					.consultaPromedioRangoFechasInicialTipoTasaInteres(
							idTipoTasa, fechaDesde, fechaHasta);

			if (tasaPromedioBC.equals(new BigDecimal("0"))) {
				log.error(" procedio = 0 ");
				throw new TasaBancoCentralCeroException(
						" Tasa Banco central es cero");
			}

		} catch (TasaInteresExcepcion e) {
			log.error(" Error consulta promedio : ", e);
			throw new TasaInteresBancoCentralException(e);
		}

		tasaPromedioBC = tasaPromedioBC.setScale(4, BigDecimal.ROUND_HALF_UP);
		log.debug(" promedio tasa de interes : " + tasaPromedioBC);
		return tasaPromedioBC;
	}

	public BigDecimal consultarTasaActuarial(String idTipoTasa, Date fecha)
			throws TasaActuarialException {

		log.debug(" consulta tasa actuarial");

		TasaInteresDetalle tasaActuarial = null;
		try {
			List<TasaInteresDetalle> tasaActuarialList = tasaInteresDetalleDao
					.consultaRangoFechasTipoTasaInteres(idTipoTasa, fecha);

			if (tasaActuarialList.size() == 0) {
				log.error(" No existe tasa de actuarial");
				throw new NoexisteTasaActuarialException(
						"No existe tasa actuarial");
			}
			if (tasaActuarialList.size() > 1) {
				log.error(" Mas de una tasa de actuarial para elmismo periodo");
				throw new TasaActurialDuplicadaException(
						"Mas de una tasa actuarial");
			}

			tasaActuarial = tasaActuarialList.get(0);

			if (tasaActuarial.equals(new BigDecimal("0"))) {
				throw new TasaActuarialCeroException(" Tasa Actuarial es cero");
			}

		} catch (TasaInteresExcepcion e) {
			log.error(" Consulta tasa de actuarial : ", e);
			throw new TasaActuarialException(e);
		}

		BigDecimal tasa = new BigDecimal(tasaActuarial.getPortasint());
		tasa = tasa.setScale(4, BigDecimal.ROUND_HALF_UP);
		log.debug(" valor tasa actuarial : " + tasa);
		return tasa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio#determinarPlazoMaximoEndeudamiento(java.util.Date,
	 * java.math.BigDecimal, ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public BigDecimal determinarPlazoMaximoEndeudamiento(Date fechaNacimiento, BigDecimal esperanzaVida, TipoPrestamista tipoPrestamista)
			throws PlazoMaximoEndeudamientoException {
		log.info(" determina plazo de endeudamiento");

		BigDecimal mesesMaximoEndeudamiento = BigDecimal.ZERO;
		BigDecimal edadMesesAnios = calculoCreditoHelper.determinarEdad(fechaNacimiento, new Date());
		
		if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			mesesMaximoEndeudamiento = parametrizacionPQServicio.obtenerPlazoMaximoProductoEdad("A", TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(edadMesesAnios.intValue()));
		} else {
			mesesMaximoEndeudamiento = parametrizacionPQServicio.obtenerPlazoMaximoProducto("A", TipoProductoEnum.NORMAL.getDescripcion());
		}
		if (mesesMaximoEndeudamiento == null) {
			throw new PlazoMaximoEndeudamientoException("No se encuentran parametrizados rangos de edades para jubilados.");
		}
		int numeroMesesMaximoEndeudamiento = mesesMaximoEndeudamiento.divide(new BigDecimal(12), RoundingMode.HALF_UP).intValue();

		log.debug(" Total de meses maximo de endeudamiento : " + numeroMesesMaximoEndeudamiento);

		return calculoCreditoHelper.determinarMesesEndeudamiento(edadMesesAnios, esperanzaVida, new BigDecimal(numeroMesesMaximoEndeudamiento));

	}

	public BigDecimal determinarSeguroDesgravamen(String idTipoSeguro,
			Date fechaSolicitud) throws TasaSeguroDesgravamen {

		log.info(" Determina seguro de desgravamen");

		/*
		 * La tasa de seguro de desgravamen es en base a la edad y el plazo
		 */

		TasaInteresDetalle tasaSeguroDesgravamen = null;
		try {
			List<TasaInteresDetalle> tasaSeguroDesgravamenList = tasaInteresDetalleDao
					.consultaRangoFechasTipoTasaInteres(idTipoSeguro,
							fechaSolicitud);

			if (tasaSeguroDesgravamenList.size() == 0) {
				log.error(" No existe seguro de desgravamen");
				throw new NoexisteTasaSeguroDesgravamenException(
						"No existe tasa seguro desgravamen");
			}

			if (tasaSeguroDesgravamenList.size() > 1) {
				log
						.error(" mas de un seguro de desgravamen para el mismo periodo");
				throw new TasaDesgravamenDuplicadaException(
						"Mas de una seguro desgravamen");
			}

			tasaSeguroDesgravamen = tasaSeguroDesgravamenList.get(0);

			if (tasaSeguroDesgravamen.equals(new BigDecimal("0"))) {
				log.error(" Seguro de desgravamen es cero");
				throw new TasaDesgravamenCeroException(
						" seguro desgravamen es cero");
			}

		} catch (TasaInteresExcepcion e) {
			log.error(" Consulta seguro de desgravamen", e);
			throw new TasaSeguroDesgravamen(e);
		}

		BigDecimal tasa = new BigDecimal(tasaSeguroDesgravamen.getPortasint());
		tasa = tasa.setScale(4, BigDecimal.ROUND_HALF_UP);
		log.debug(" Tasa seguro desgravamen : " + tasa);
		return tasa;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio#poblarCondicionCalculo(java.lang.String,
	 * java.util.Date, java.util.Date, int, ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public CondicionCalculo poblarCondicionCalculo(String genero,
			Date fechaNacimiento, 
			Date fechaSolicitud, int semanas, TipoPrestamista tipoPrestamista)
			throws CondicionCalculoException {

		log.info(" Pobla los datos de condicion de calculo resumen");

		CondicionCalculo condicionCalculo = new CondicionCalculo();
		BigDecimal esperanzaVida = null;

		log.debug(" espernza de vida");
		try {
			esperanzaVida = consultarEsperanzaVida(genero, fechaNacimiento,
					fechaSolicitud);
			condicionCalculo.setEsperanzaVida(esperanzaVida.intValue());
		} catch (EsperanzaVidaException e) {
			throw new CondicionCalculoException(e);
		}

		log.debug(" edad actual en anios y meses");

		BigDecimal edadMesesAnios = calculoCreditoHelper.determinarEdad(
				fechaNacimiento, fechaSolicitud);

		condicionCalculo.setEdadActualAnios(edadMesesAnios.intValue());

		condicionCalculo.setEdadMesesAnios(calculoCreditoHelper
				.determinarNumeroMeses(edadMesesAnios));

		log.debug(" plazo maximo");

		try {
			condicionCalculo.setPlazoMaximo(determinarPlazoMaximoEndeudamiento(
					fechaNacimiento, esperanzaVida, tipoPrestamista).intValue());
		} catch (PlazoMaximoEndeudamientoException e) {
			throw new CondicionCalculoException(e);
		}

		log.debug(" seguro de desgravamen");
		condicionCalculo.setPorcentajeSeguroDesgravamen(new BigDecimal(0));

		return condicionCalculo;
	}

	private Date retrocederAlLunes(Date fecha) {
		Calendar newDate = new GregorianCalendar();
		newDate.setTime(fecha);
		boolean salir = false;
		
		while (!salir) {
			if (newDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				break;
			}
			// Restar un dia
			newDate.setTimeInMillis(newDate.getTime().getTime() - 86400000);
		}
		return newDate.getTime();
	}

	private Date avanzarAlDomingo(Date fecha) {
		Calendar newDate = new GregorianCalendar();
		newDate.setTime(fecha);
		boolean salir = false;
		while (!salir) {
			if (newDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				break;
			}
			// Sumar un dia
			newDate.setTimeInMillis(newDate.getTime().getTime() + 86400000);
		}
		return newDate.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio#obtenerTasaInteresPorPlazo(java.math.BigDecimal,
	 * java.lang.String, int)
	 */
	@Override
	public BigDecimal obtenerTasaInteresPorPlazo(BigDecimal plazo, String tipoProducto, int edad) throws CondicionCalculoException {
		ParametrizacionPQ parametro = null;
		try {
			if (tipoProducto.equals(TipoProductoEnum.JUB_PEN.getDescripcion())) {
				parametro = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A", tipoProducto, new BigDecimal(edad));
			} else {
				parametro = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A", tipoProducto);
			}
			return parametro.getTasaInteres();
		} catch (ParametrizacionPQException e) {
			throw new CondicionCalculoException(e);
		}

	}
	
}
