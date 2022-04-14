/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.dao.ResiduosCesantiaDao;
import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionCesantia;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.fin.biess.creditos.pq.modelo.dto.PrestacionPensionado;
import ec.fin.biess.creditos.pq.servicio.ConsultaPrestacionPensionadosServicioLocal;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.GarantiaCesantiaDao;
import ec.gov.iess.creditos.dao.GarantiaPrestamoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoRol;
import ec.gov.iess.creditos.enumeracion.VariedadCreditoEnum;
import ec.gov.iess.creditos.excepcion.CesantiaExcepcion;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.DetalleCalculoEgresos;
import ec.gov.iess.creditos.modelo.dto.DetalleCalculoIngresos;
import ec.gov.iess.creditos.modelo.dto.DetalleGarantia;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.PrestacionConcesion;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.GarantiaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.servicio.ConsultaCesantiaServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicio;
import ec.gov.iess.creditos.pq.servicio.DetalleCatalogoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicioRemote;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.OrigenJubiladoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.hl.modelo.Imposicion;
import ec.gov.iess.hl.servicio.ImposicionServicio;
import ec.gov.iess.servicio.cesantia.excepcion.ActualizacionCesantiaExcetion;
import ec.gov.iess.servicio.fondoreserva.modelo.CuentaFondoReserva;
import ec.gov.iess.servicio.fondoreserva.servicio.FondoReservaServicio;

/**
 * 
 * <b> Permite realizar cÃ¡lculo con respecto a las GarantÃ­as de afiliados o
 * Jubilados. </b>
 * 
 * @author cbastidas , pjarrin
 * @version $Revision: 1.6.2.1 $
 *          <p>
 *          [$Author: cbastidas $, $Date: 2011/05/24 13:54:56 $]
 *          </p>
 */
@Stateless
public class GarantiaCreditoServicioImpl implements GarantiaCreditoServicio,
		GarantiaCreditoServicioRemote {

	private static LoggerBiess log = LoggerBiess
			.getLogger(GarantiaCreditoServicioImpl.class);

	@EJB
	ImposicionServicio imposicionServicio;

	@EJB(beanName = "FondoReserva2ServicioImpl")
	FondoReservaServicio fondoReservaServicio;

	@EJB
	ConsultaFondosReservaServicio consultaFondosReservaServicio;

	@EJB
	OrigenJubiladoServicio origenJubiladoServicio;

	@EJB
	ConsultaCesantiaServicio consultaCesantiaServicio;

	@EJB
	PrestamoDao prestamoDao;

	@EJB
	GarantiaPrestamoDao garantiaPrestamoDao;

	@EJB
	GarantiaCesantiaDao cesantiaDao;

	@EJB
	SolicitudCreditoServicio solicitudCreditoServicio;

	@EJB
	DetalleCatalogoServicio detalleCatalogoServicio;
	
	@EJB
	ResiduosCesantiaDao residuosCesantiaDao; 
	
	// INC-2135 Pensiones Jubilados/Pensionistas
	@EJB
	ConsultaPrestacionPensionadosServicioLocal consultaPrestacionPensionadosServicioLocal;
	
	@EJB
	private ParametroCreditoServicio parametroCreditoServicio;
	
	@EJB
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;
	
	
	@EJB
	private GarantiasSacServicioLocal garantiasSAC;
	
	private Map<Integer, TipoRol> mapVariedadCredito;
	
	private BigDecimal capacidadPago;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	private CalculoCreditoHelperSingleton calculoCreditoHelper;
	
	//MIGRACION CARTERA 
	/**
	 * Informacion con los creditos exigibles del asegurado obtenido en la precalif
	 */
	private InformacionPQExigible infoPqExigile;
	
	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	private void init() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton.getInstancia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio#getGarantia(ec.gov.iess.creditos.modelo.dto.
	 * DatosGarantia, ec.gov.iess.creditos.enumeracion.TipoPrestamista,
	 * ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado, java.math.BigDecimal)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Garantia getGarantia(final DatosGarantia datGarantia, final TipoPrestamista rolPrestamista,
			final InformacionIessServicioDto informacionIessServicioDto, final BigDecimal sueldoPromedio,InformacionGarantias infoGarantiaComp) // DatosGarantia
																										// garantia
			throws GarantiaException {
		try {
			setMapVariedadCredito();
			if (TipoPrestamista.AFILIADO.equals(datGarantia
					.getTipoPrestamista())) {
				return getGarantiaAfiliado(datGarantia, rolPrestamista, sueldoPromedio, informacionIessServicioDto.getInformacionCesantia(),infoGarantiaComp);

			} else if (TipoPrestamista.JUBILADO.equals(datGarantia
					.getTipoPrestamista())) {
				// INC-2135 Pensiones Jubilados/Pensionistas: se pase como parametro el valor a comprometer.
				return getGarantiaJubilado(datGarantia, rolPrestamista, informacionIessServicioDto.getInformacionPrestacionPensionado());
			} else if (TipoPrestamista.AFILIADO_JUBILADO.equals(datGarantia
					.getTipoPrestamista())) {
				final Garantia gAfiliado = getGarantiaAfiliado(datGarantia, rolPrestamista, sueldoPromedio, informacionIessServicioDto.getInformacionCesantia(),infoGarantiaComp);
				// INC-2135 Pensiones Jubilados/Pensionistas: se pase como parametro el valor a comprometer.
				final Garantia gJubilado = getGarantiaJubilado(datGarantia, rolPrestamista, informacionIessServicioDto.getInformacionPrestacionPensionado());

				final Garantia unaGarantia = new Garantia();

				final List<DetalleGarantia> listaGarantia = new ArrayList<DetalleGarantia>();
				listaGarantia.addAll(gAfiliado.getDetalleGarantiaList());
				listaGarantia.addAll(gJubilado.getDetalleGarantiaList());

				unaGarantia.setDetalleGarantiaList(listaGarantia);
				return unaGarantia;

			} else if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datGarantia
					.getTipoPrestamista())) {
				return getGarantiaZafrero(datGarantia, rolPrestamista, informacionIessServicioDto.getInformacionCesantia(),infoGarantiaComp);
			} else {
				throw new GarantiaException(
						"Tipo de prestamista no soportado o no definido");
			}
		} catch (final GarantiaException e) {
			throw new GarantiaException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio#getGarantia(ec.gov.iess.creditos.modelo.dto.
	 * DatosGarantia, ec.gov.iess.creditos.enumeracion.TipoPrestamista,
	 * ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado, java.math.BigDecimal)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Garantia getGarantia(final DatosGarantia datGarantia, final TipoPrestamista rolPrestamista,
			final InformacionIessServicioDto informacionIessServicioDto, final BigDecimal sueldoPromedio,final InformacionPQExigible infoPqExigile,InformacionGarantias infoGarantiaComp) // DatosGarantia
																										// garantia 
	throws GarantiaException {
		try {
			//Asigo los 
			this.infoPqExigile=infoPqExigile;
			setMapVariedadCredito();
			if (TipoPrestamista.AFILIADO.equals(datGarantia
					.getTipoPrestamista())) {
				return getGarantiaAfiliado(datGarantia, rolPrestamista, sueldoPromedio, informacionIessServicioDto.getInformacionCesantia(),infoGarantiaComp);

			} else if (TipoPrestamista.JUBILADO.equals(datGarantia
					.getTipoPrestamista())) {
				// INC-2135 Pensiones Jubilados/Pensionistas: se pase como parametro el valor a comprometer.
				return getGarantiaJubilado(datGarantia, rolPrestamista, informacionIessServicioDto.getInformacionPrestacionPensionado());
			} else if (TipoPrestamista.AFILIADO_JUBILADO.equals(datGarantia
					.getTipoPrestamista())) {
				final Garantia gAfiliado = getGarantiaAfiliado(datGarantia, rolPrestamista, sueldoPromedio, informacionIessServicioDto.getInformacionCesantia(),infoGarantiaComp);
				// INC-2135 Pensiones Jubilados/Pensionistas: se pase como parametro el valor a comprometer.
				final Garantia gJubilado = getGarantiaJubilado(datGarantia, rolPrestamista, informacionIessServicioDto.getInformacionPrestacionPensionado());

				final Garantia unaGarantia = new Garantia();

				final List<DetalleGarantia> listaGarantia = new ArrayList<DetalleGarantia>();
				listaGarantia.addAll(gAfiliado.getDetalleGarantiaList());
				listaGarantia.addAll(gJubilado.getDetalleGarantiaList());

				unaGarantia.setDetalleGarantiaList(listaGarantia);
				return unaGarantia;

			} else if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datGarantia
					.getTipoPrestamista())) {
				return getGarantiaZafrero(datGarantia, rolPrestamista, informacionIessServicioDto.getInformacionCesantia(),infoGarantiaComp);
			} else {
				throw new GarantiaException(
						"Tipo de prestamista no soportado o no definido");
			}
		} catch (final GarantiaException e) {
			throw new GarantiaException(e);
		}
	}

	/**
	 * 
	 * <b> Calcula las GarantÃ­as de Zafrero. </b>
	 * <p>
	 * [Author: cbastidas, Date: 15/03/2011]
	 * </p>
	 * 
	 * @param unaGarantia
	 * @return
	 * @throws GarantiaException
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private Garantia getGarantiaZafrero(final DatosGarantia datGarantia, final TipoPrestamista rolPrestamista, final InformacionCesantia informacionCesantia,InformacionGarantias infoGarantiaComp)
			throws GarantiaException {

		// Hasta AquÃ­ todo igual que el afiliado
		final Garantia garantia = getGarantiaAfiliado(datGarantia, rolPrestamista, BigDecimal.ZERO, informacionCesantia,infoGarantiaComp);
		return garantia;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio#obtenerPorcentajeComprometimientoPQ(boolean,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista, int, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal obtenerPorcentajeComprometimientoPQ(final boolean creditoEmergente, final TipoPrestamista tipoPrestamista, final int edad, final BigDecimal plazo)
			throws ParametrizacionPQException, ParametroBiessException {
		
			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
				if (plazo == null) {
					capacidadPago = parametrizacionPQServicio.obtenerCapacidadPagoMaximoProductoEdad("A", TipoProductoEnum.JUB_PEN.getDescripcion(),
							new BigDecimal(edad));
				} else {
					capacidadPago = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A", TipoProductoEnum.JUB_PEN.getDescripcion(),
							new BigDecimal(edad)).getCapacidadPago();
				}
			} else {
				if (plazo == null) {
					capacidadPago = parametrizacionPQServicio.obtenerCapacidadPagoMaximoProducto("A", TipoProductoEnum.NORMAL.getDescripcion());
				} else {
					capacidadPago = parametrizacionPQServicio
							.obtenerListaParametrosRangoEstadoProducto(plazo, "A", TipoProductoEnum.NORMAL.getDescripcion()).getCapacidadPago();
				}
			}
		
		return capacidadPago;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private Garantia getGarantiaAfiliado(final DatosGarantia datGarantia, final TipoPrestamista rolPrestamista, final BigDecimal sueldoPromedio, final InformacionCesantia infoCesantia,InformacionGarantias infoGarantiaComp)
			throws GarantiaException {
		final Garantia garantia = new Garantia();

		// Toma el monto mÃ¡ximo del credito solo de los parÃ¡metros del crÃ©dito
		// ya no realiza el cruce con la tasa de interÃ©s.
		final BigDecimal montoMaximoCredito = datGarantia.getParametroMontoMaximo();

		// Inicializo los valores comprometidos de fondos y cesantias
		datGarantia.setComprometidoCesantias(new BigDecimal(0));
		datGarantia.setComprometidoFondos(new BigDecimal(0));

		if (montoMaximoCredito == null) {
			throw new GarantiaException(
					"NO SE PUDO DETERMINAR EL MONTO M\u00C1XIMO DE CR\u00C9DITO QUE SE PUEDE CONCEDER: montoMaximoCredito = "
							+ montoMaximoCredito);
		}

		// Cupo Maximo
		garantia.setCupoMaximoCredito(montoMaximoCredito);

		// Sueldo promedio

		// Para Zafreros debe considerar el sueldo promedio diferente

		if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datGarantia.getTipoPrestamista())) {
			// Pero el promedio de sueldos del zafrero se hace con los
			// sueldos del
			// Ãºltimo semestre del aÃ±o anterior
			// Primero Obtenemos la lista de imposisiones que nos servirÃ¡n
			// para el
			// calculo del promedio
			// Para los zafreros hay que usar las imposiciones del Ãºltimo
			// semestre
			// del aÃ±o anterior
			List<Imposicion> imposionesUltimoSemestreAnioAnterior = new ArrayList<Imposicion>();
			final Calendar hoy = new GregorianCalendar();
			final Long anioAnterior = (long) (hoy.get(Calendar.YEAR) - 1);
			final List<Long> mesList = new ArrayList<Long>();
			mesList.add(7L);
			mesList.add(8L);
			mesList.add(9L);
			mesList.add(10L);
			mesList.add(11L);
			mesList.add(12L);

			imposionesUltimoSemestreAnioAnterior = imposicionServicio.consultarPorCedulaAnioListaMes(datGarantia.getCedula(), anioAnterior, mesList);

//			if (imposionesUltimoSemestreAnioAnterior.size() < 6) {
//				throw new GarantiaException(
//						"EL AFILIADO (ZAFRERO TEMPORAL) NO TIENE TODOS LOS APORTES DEL \u00DALTIMO SEMESTRE DEL A\u00D1O ANTERIOR, DETECTADO EN CONSULTA DE GARANT\u00CDAS.  C\u00C9DULA: "
//								+ datGarantia.getCedula() + ".  N\u00DAMERO DE APORTES: " + imposionesUltimoSemestreAnioAnterior.size());
//			}

			final BigDecimal promedioSueldoUltimoSemestreAnioAnterior = imposicionServicio.calcularPromedio(imposionesUltimoSemestreAnioAnterior);
			garantia.setSueloPromedio(promedioSueldoUltimoSemestreAnioAnterior);
		} else {
			garantia.setSueloPromedio(sueldoPromedio);
		}
		if (garantia.getSueloPromedio().doubleValue() < 0) {
			garantia.setSueloPromedio(new BigDecimal(0));
		}

		// Capacidad de Pago
		final int edad = calculoCreditoHelper.determinarEdad(datGarantia.getFechaNacimiento(), new Date()).intValue();
		BigDecimal porcentajeComprometimiento = BigDecimal.ZERO;
		try {
			porcentajeComprometimiento = obtenerPorcentajeComprometimientoPQ(datGarantia.isIndicaCreditoEmergente(), datGarantia.getTipoPrestamista(), edad, null);
		} catch (final ParametrizacionPQException e1) {
			log.error("1. Se presento un error al obtener el porcentaje de comprometimiento", e1);
		} catch (final ParametroBiessException e1) {
			log.error("2. Se presento un error al obtener el porcentaje de comprometimiento", e1);
		}
		porcentajeComprometimiento = porcentajeComprometimiento
				.divide(new BigDecimal(100));
		garantia.setCapacidadPago(garantia.getSueloPromedio().multiply(
				porcentajeComprometimiento));

		// Detalle garantias
		// Se determina indepensientemente el valor de fondos de reserva y de
		// cesantÃ­as para armar una
		// colecciÃ³n de datalles

		// Se consulta la cesantÃ­a
		BigDecimal valorCesantia;
		BigDecimal valorComprometidoFR;
		GarantiaCesantia cuentaCesantia = null;
		CuentaFondoReserva cuentaFondoReserva = null;
		DetalleCalculoIngresos fondosReserva = null;
		DetalleCalculoIngresos cesantias = null;
		DetalleGarantia fr = null;
		DetalleGarantia ces = null;

		garantia.setDetalleCalculoIngresos(new ArrayList<DetalleCalculoIngresos>());
		
		/* INC-27737 - Consultar residuos de adicionales de cesantias que no deben ser considerados como garantias */
		BigDecimal residuosCesantia = BigDecimal.ZERO;
		boolean residuosFlag = false;
		try {
			final BigDecimal residuosTmp = residuosCesantiaDao.consultarResiduos(datGarantia.getCedula());
			if (null != residuosTmp) {
				residuosCesantia = residuosTmp.setScale(2, BigDecimal.ROUND_HALF_UP);
				residuosFlag = true;
			}
		} catch (final Exception e) {
			log.error("Error al consultar residuos de los adicionales de cesantias CI: " + datGarantia.getCedula(), e);
			throw new GarantiaException("Error al consultar residuos de los adicionales de cesantias", e);
		}
		if (residuosCesantia.compareTo(BigDecimal.ZERO) < 0) {
			log.error("El valor de residuos de los adicionales de cesantias es negativo CI: " + datGarantia.getCedula());
			throw new GarantiaException("El valor de residuos de los adicionales de cesantias es negativo");				
		}		
		/* INC-27737 */
		
		try {
			// Cambios acantos 25/08/2011
			// consultar de la nueva tabla de cesantia, actualizo la cesantia
			// (ksafitcesantias)
			
			BigDecimal valordisponibleconext = infoCesantia.getValorCesantia();

			if (valordisponibleconext == null)
				valordisponibleconext = new BigDecimal(0.0);

			consultaCesantiaServicio.actualizarvalorcesantia(
					datGarantia.getCedula(), valordisponibleconext);

			//Consulto las garantias comprometidas registradas en el SAC 
			 datGarantia.setCompromCesSac(infoGarantiaComp.getTotalGarantiasFondosCesantia());
			 datGarantia.setCompromFonrSac(infoGarantiaComp.getTotalGarantiasFondoReserva());

			valorCesantia = consultaCesantiaServicio.consultarCesantia(datGarantia);
			garantia.setValorComprometidoCesantia(valorCesantia);
			garantia.setDatGarantia(datGarantia);

			cuentaCesantia = cesantiaDao.load(datGarantia.getCedula());

		} catch (final CesantiaExcepcion e) {
			valorCesantia = BigDecimal.ZERO;
			cuentaCesantia = new GarantiaCesantia();
			cuentaCesantia.setValorHost(BigDecimal.ZERO);
			cuentaCesantia.setValorHistoriaLaboral(BigDecimal.ZERO);
			cuentaCesantia.setValorComprometidoHost(BigDecimal.ZERO);
			cuentaCesantia.setValorComprometidoHl(BigDecimal.ZERO);
			residuosCesantia = BigDecimal.ZERO;
			throw new GarantiaException(e);		
		} catch (final ActualizacionCesantiaExcetion e) {
			valorCesantia = BigDecimal.ZERO;
			cuentaCesantia = new GarantiaCesantia();
			cuentaCesantia.setValorHost(new BigDecimal(0));
			cuentaCesantia.setValorHistoriaLaboral(BigDecimal.ZERO);
			cuentaCesantia.setValorComprometidoHost(BigDecimal.ZERO);
			cuentaCesantia.setValorComprometidoHl(BigDecimal.ZERO);
			residuosCesantia = BigDecimal.ZERO;
		}

		// Controlando que el valor de la cesantia no sea menor que cero.
		if (valorCesantia.doubleValue() < 0) {
			valorCesantia = BigDecimal.ZERO;
			cuentaCesantia.setValorHost(BigDecimal.ZERO);
			cuentaCesantia.setValorHistoriaLaboral(BigDecimal.ZERO);
			cuentaCesantia.setValorComprometidoHost(BigDecimal.ZERO);
			cuentaCesantia.setValorComprometidoHl(BigDecimal.ZERO);
			residuosCesantia = BigDecimal.ZERO;
		}

		cesantias = new DetalleCalculoIngresos();
		cesantias.setNombre("Total:");
		/* INC-27737 - Restar del valor cesantia el valor de los residuos de los adicionales */
		cesantias.setValor(cuentaCesantia.getValorHost().add(
				cuentaCesantia.getValorHistoriaLaboral()));
		cesantias.setObservacion("Cesantia");
		garantia.getDetalleCalculoIngresos().add(cesantias);

		if (datGarantia.getValReqFondos().isNovacion()) {
			cesantias = new DetalleCalculoIngresos();
			cesantias.setNombre("Comprometidas:");
			cesantias.setValor(datGarantia.getCompromCesSac().subtract(datGarantia.getComprometidoCesantias())
							.multiply(BigDecimal.valueOf(-1)));
			cesantias.setObservacion("Cesantia");
			garantia.getDetalleCalculoIngresos().add(cesantias);
		} else {
			cesantias = new DetalleCalculoIngresos();
			cesantias.setNombre("Comprometidas:");
			cesantias.setValor(cuentaCesantia.getValorComprometidoHost()
					//.add(cuentaCesantia.getValorComprometidoHl())
					//se quita las compremetidas de HL
					.add(datGarantia.getCompromCesSac())
					.multiply(BigDecimal.valueOf(-1)));
			cesantias.setObservacion("Cesantia");
			garantia.getDetalleCalculoIngresos().add(cesantias);
		}

		cesantias = new DetalleCalculoIngresos();
		cesantias.setNombre("Disponible: <strong>(E)</strong>");
		cesantias.setObservacion("Cesantia");
		cesantias.setValor(valorCesantia);
		garantia.getDetalleCalculoIngresos().add(cesantias);

		garantia.setDetalleGarantiaList(new ArrayList<DetalleGarantia>());
		ces = new DetalleGarantia();
		ces.setNombreGarantia("CESANTIA");
		ces.setValorGarantia(valorCesantia);
		/* INC-27737 - Nota indicando que no se incluye el residuo de los adicionales */
		if (residuosFlag) {
			ces.setObservacion("ESTA SUMA INCLUYE \u00daNICAMENTE VALORES VALIDADOS DE SU CUENTA INDIVIDUAL DE CESANT\u00cdA");			
		}

		cuentaFondoReserva = consultaFondosReservaServicio
				.consultarFondoReserva(datGarantia);

		//tomo los valores comprometidos de fondos de reserva del SAC
		valorComprometidoFR=datGarantia.getCompromFonrSac();

		if (valorComprometidoFR == null) {
			valorComprometidoFR = BigDecimal.ZERO;
		}

		if (datGarantia.getValReqFondos().isNovacion()) {

			fondosReserva = new DetalleCalculoIngresos();
			fondosReserva.setNombre("Total:");
			fondosReserva.setValor(cuentaFondoReserva.getSaldoDisponible().add(valorComprometidoFR));
			fondosReserva.setObservacion("Fondos");
			garantia.getDetalleCalculoIngresos().add(fondosReserva);

			fondosReserva = new DetalleCalculoIngresos();
			fondosReserva.setNombre("Comprometidas:");
			fondosReserva.setValor(valorComprometidoFR.subtract(
					datGarantia.getComprometidoFondos()).multiply(
					BigDecimal.valueOf(-1)));
			fondosReserva.setObservacion("Fondos");
			garantia.getDetalleCalculoIngresos().add(fondosReserva);

			cuentaFondoReserva.setSaldoDisponible(cuentaFondoReserva.getSaldoDisponible().add(
					datGarantia.getComprometidoFondos()));

		} else {

			fondosReserva = new DetalleCalculoIngresos();
			fondosReserva.setNombre("Total:");
			fondosReserva.setValor(cuentaFondoReserva.getSaldoDisponible().add(valorComprometidoFR));
			fondosReserva.setObservacion("Fondos");
			garantia.getDetalleCalculoIngresos().add(fondosReserva);

			fondosReserva = new DetalleCalculoIngresos();
			fondosReserva.setNombre("Comprometidas:");
			fondosReserva.setValor(valorComprometidoFR.multiply(BigDecimal
					.valueOf(-1)));
			fondosReserva.setObservacion("Fondos");
			garantia.getDetalleCalculoIngresos().add(fondosReserva);
			cuentaFondoReserva.setSaldoDisponible(cuentaFondoReserva.getSaldoDisponible());

		}

		fondosReserva = new DetalleCalculoIngresos();
		fondosReserva.setNombre("Disponible: <strong>(F)</strong>");
		fondosReserva.setValor(cuentaFondoReserva.getSaldoDisponible());
		fondosReserva.setObservacion("Fondos");
		garantia.getDetalleCalculoIngresos().add(fondosReserva);

		fr = new DetalleGarantia();
		fr.setNombreGarantia("FONDOS DE RESERVA");
		fr.setValorGarantia(cuentaFondoReserva.getSaldoDisponible());
		fr.setObservacion(cuentaFondoReserva.getObservacion());

		garantia.getDetalleGarantiaList().add(ces);
		garantia.getDetalleGarantiaList().add(fr);

		// Total garantias

		final BigDecimal garantiaReal = ces.getValorGarantia().add(
				fr.getValorGarantia());

		// Este es el valor total de la suma de fondos de reserva mas cesantias
		garantia.setTotalGarantia(garantiaReal);

		if (garantiaReal.doubleValue() > montoMaximoCredito.doubleValue()) {
			// Si las garantÃ­as del afiliado superan el mÃ¡ximo de credito
			// permitido, entonces se ajusta ese valor al mÃ¡ximo
			garantia.setTotalGarantiaAjustada(montoMaximoCredito);
		} else {
			// Si no supera el mÃ¡ximo la garantÃ­a ajustada es igual a la
			// garantÃ­a real
			garantia.setTotalGarantiaAjustada(garantiaReal);
		}

		// Total Egresos para ConcesiÃ³n
		// Se agrega el listado de las cuotas de prÃ©stamos vigentes para se
		// restadas del sueldo promedio
		DetalleCalculoEgresos detPq = null;
		DetalleCalculoEgresos detPh = null;
		BigDecimal sumatoriaDivPQ = BigDecimal.ZERO;
		BigDecimal sumatoriaMntPQ = BigDecimal.ZERO;

		garantia.setNumTotPQVig(this.infoPqExigile.getListaCreditosExigible().size());
		BigDecimal valorDividendosTotalPqs = new BigDecimal(0);
		garantia.setDetalleCalculoEgresos(new ArrayList<DetalleCalculoEgresos>());
		for (final CreditoExigibleDto credito : this.infoPqExigile.getListaCreditosExigible()) {

			if (null != rolPrestamista && rolPrestamista.compareTo(TipoPrestamista.AFILIADO_JUBILADO) == 0) {

				final Prestamo prestamoBd = prestamoDao.buscarPorNumOperacionSAC(credito.getOperacionSAC());
				if (prestamoBd != null && mapVariedadCredito.get(prestamoBd.getCreditoPk().getCodprecla().intValue())
						.compareTo(TipoRol.AF) != 0) {
				continue;
			}
				}

			if (datGarantia.getValReqFondos().isNovacion()
					&& credito.getOperacionSAC().equals(datGarantia.getPrestamoVigNovacion().getNumOperacionSAC())) {
				continue;
			} else {
				detPq = new DetalleCalculoEgresos();
				detPq.setNombre("Cuota Pr\u00E9stamo Quirografario por Monto: " + credito.getMontoConcedido());
				detPq.setValor(credito.getCuota());
				detPq.setValorMtn(credito.getMontoConcedido());
				valorDividendosTotalPqs = valorDividendosTotalPqs.add(detPq.getValor());
				detPq.setValor(credito.getCuota().multiply(new BigDecimal(-1)));
				garantia.getDetalleCalculoEgresos().add(detPq);
				// sumatoria montos de los pq vigentes
				sumatoriaMntPQ = sumatoriaMntPQ.add(credito.getMontoConcedido());
				// sumatoria de todos los dividendos de los pq vigentes
				sumatoriaDivPQ = sumatoriaDivPQ.add(credito.getCuota());
			}
		}

		garantia.setSumDivPq(sumatoriaDivPQ);
		garantia.setSumMntPq(sumatoriaMntPQ);

		// Cuota de Hipotecario
		final BigDecimal cuotaHipotecario = prestamoDao
				.cuotaPrestamoHipotecario(datGarantia.getCedula());

		// numero total de prestamos vig ph
		garantia.setNumTotPHVig(prestamoDao.numeroPrestamoPHVIG(
				datGarantia.getCedula()).intValue());

		// sumatoria div de pretamos vig ph
		garantia.setSumDivPh(cuotaHipotecario);

		// sumatoria monto de prestamos vig ph
		garantia.setSumMntPh(prestamoDao.sumatoriaMntPHVIG(datGarantia
				.getCedula()));

		garantia.setCuotaHipotecarios(cuotaHipotecario);
		if (cuotaHipotecario.doubleValue() > 0) {
			detPh = new DetalleCalculoEgresos();
			detPh.setNombre("Cuota Pr\u00E9stamo Hipotecario por Total de Dividendos: ");
			detPh.setValor(cuotaHipotecario.multiply(new BigDecimal(-1)));
		}

		garantia.getDetalleCalculoEgresos().add(detPh);
		if (detPq == null && detPh == null) {
			detPq = new DetalleCalculoEgresos();
			detPq.setNombre(" ");
			detPq.setValor(BigDecimal.ZERO);
			detPq.setValorMtn(BigDecimal.ZERO);
			// garantia.setDetalleCalculoEgresos(null);
			garantia.getDetalleCalculoEgresos().add(detPq);
		}

		// Resto los egresos del sueldo promedio
		// El sueldo promedio para Novacion no debe restar los Pqs Vigentes
		BigDecimal capacidadEndeudamiento = BigDecimal.ZERO;
		capacidadEndeudamiento = garantia.getCapacidadPago()
				.subtract(valorDividendosTotalPqs).subtract(cuotaHipotecario);
		/*
		 * if (unaGarantia.getValReqFondos().isNovacion()) {
		 * capacidadEndeudamiento =
		 * garantia.getCapacidadPago().subtract(cuotaHipotecario); } else {
		 * capacidadEndeudamiento = garantia.getCapacidadPago()
		 * .subtract(valorDividendosTotalPqs).subtract(cuotaHipotecario); }
		 */
		garantia.setCapacidadEndeudamiento(capacidadEndeudamiento);

		// El sueldo promedio debe ser igual a la capacidad de endeudamiento
		// para mantener
		// los cÃ¡lculos anteriores
		garantia.setSueldoPromedioOriginal(garantia.getSueloPromedio());
		garantia.setSueloPromedio(garantia.getCapacidadEndeudamiento());

		// garantia.setListaPrestamosPHVig(obtenerPrestamosPHVIG(datGarantia.getCedula()));

		return garantia;
	}


	/**
	 * 
	 * <b> Calcula las GarantÃ­as de Jubilados </b>
	 * <p>
	 * [Author: cbastidas, Date: 15/03/2011]
	 * </p>
	 * 
	 * @param unaGarantia
	 * @return
	 * @throws GarantiaException
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	private Garantia getGarantiaJubilado(final DatosGarantia datGarantia, final TipoPrestamista rolPrestamista, 
			final InformacionPrestacionPensionado informacionPrestacionPensionado) throws GarantiaException {

		// Toma el monto mÃ¡ximo del credito solo de los parÃ¡metros del crÃ©dito
		// ya no realiza el cruce con la tasa de interÃ©s.
		final BigDecimal montoMaximoCredito = datGarantia.getParametroMontoMaximo();
		if (montoMaximoCredito == null) {
			throw new GarantiaException(
					"NO SE PUDO DETERMINAR EL MONTO M\u00C1XIMO DE CREDITO QUE SE PUEDE CONCEDER: montoMaximoCredito = "
							+ montoMaximoCredito);
		}

		final Garantia garantia = new Garantia();
		// INC-2135 Pensiones Jubilados/Pensionistas: se pasa como parametro el valor a comprometer.
		if (informacionPrestacionPensionado != null && informacionPrestacionPensionado.getListaPrestaciones() != null
				&& !informacionPrestacionPensionado.getListaPrestaciones().isEmpty()) {
			garantia.setListPrestamoConcesion(procesarPrestamoConcesion(informacionPrestacionPensionado
					.getListaPrestaciones()));
			garantia.setSueldoPromedioOriginal(informacionPrestacionPensionado.getValorAComprometer());
		} else {
			garantia.setListPrestamoConcesion(new ArrayList<PrestacionConcesion>());
			garantia.setSueldoPromedioOriginal(BigDecimal.ZERO);
		}
		
		// Cupo Maximo
		garantia.setCupoMaximoCredito(montoMaximoCredito);

		// Total de la garantia es total de renta menos retenciones
		// garantia.setTotalGarantia(sumaRenta.subtract(sumaRetenciones));
		garantia.setTotalGarantia(montoMaximoCredito);

		garantia.setTotalGarantiaAjustada(montoMaximoCredito);

		// Validacion del buro de credito
		if (datGarantia.getCuotaMensualBuro() == null) {
			datGarantia.setCuotaMensualBuro(BigDecimal.ZERO);
		}
		
		// Capacidad de Pago
		final int edad = calculoCreditoHelper.determinarEdad(datGarantia.getFechaNacimiento(), new Date()).intValue();
		BigDecimal porcentajeComprometimiento = BigDecimal.ZERO;
		try {
			porcentajeComprometimiento = obtenerPorcentajeComprometimientoPQ(datGarantia.isIndicaCreditoEmergente(), datGarantia.getTipoPrestamista(), edad, null);
		} catch (final ParametrizacionPQException e1) {
			log.error("1. Se presento un error al obtener el porcentaje de comprometimiento", e1);
		} catch (final ParametroBiessException e1) {
			log.error("2. Se presento un error al obtener el porcentaje de comprometimiento", e1);
		}
		porcentajeComprometimiento = porcentajeComprometimiento
				.divide(new BigDecimal(100));
		garantia.setCapacidadPago(garantia.getSueldoPromedioOriginal()
				.multiply(porcentajeComprometimiento));

		// Se agrega el listado de las cuotas de prÃ©stamos vigentes para se
		// restadas del sueldo promedio
		DetalleCalculoEgresos detPq = null;
		DetalleCalculoEgresos detPh = null;

		BigDecimal sumatoriaDivPQ = BigDecimal.ZERO;
		BigDecimal sumatoriaMntPQ = BigDecimal.ZERO;

		garantia.setNumTotPQVig(this.infoPqExigile.getListaCreditosExigible().size());
		BigDecimal valorDividendosTotalPqs = BigDecimal.ZERO;
		garantia.setDetalleCalculoEgresos(new ArrayList<DetalleCalculoEgresos>());
		
		for (final CreditoExigibleDto credito : this.infoPqExigile.getListaCreditosExigible()) {

			if (null != rolPrestamista && rolPrestamista.compareTo(TipoPrestamista.AFILIADO_JUBILADO) == 0) {

				final Prestamo prestamoBd = prestamoDao.buscarPorNumOperacionSAC(credito.getOperacionSAC());
				if (prestamoBd != null && mapVariedadCredito.get(prestamoBd.getCreditoPk().getCodprecla().intValue())
						.compareTo(TipoRol.JU) != 0) {
				continue;
			}			
				}

			if (datGarantia.getValReqFondos().isNovacion()
					&& credito.getOperacionSAC().equals(datGarantia.getPrestamoVigNovacion().getNumOperacionSAC())) {
				continue;
			} else {
				detPq = new DetalleCalculoEgresos();
				detPq.setNombre("Cuota Pr\u00E9stamo Quirografario por Monto: " + credito.getMontoConcedido());
				detPq.setValor(credito.getCuota());
				detPq.setValorMtn(credito.getMontoConcedido());
				valorDividendosTotalPqs = valorDividendosTotalPqs.add(detPq.getValor());
				detPq.setValor(credito.getCuota().multiply(new BigDecimal(-1)));
				garantia.getDetalleCalculoEgresos().add(detPq);
				// sumatoria montos de los pq vigentes
				sumatoriaMntPQ = sumatoriaMntPQ.add(credito.getMontoConcedido());
				// sumatoria de todos los dividendos de los pq vigentes
				sumatoriaDivPQ = sumatoriaDivPQ.add(credito.getCuota());
			}
		}

		garantia.setSumDivPq(sumatoriaDivPQ);
		garantia.setSumMntPq(sumatoriaMntPQ);

		// Cuota de Hipotecario
		final BigDecimal cuotaHipotecario = prestamoDao
				.cuotaPrestamoHipotecario(datGarantia.getCedula());
		// numero total de prestamos vig ph
		garantia.setNumTotPHVig(prestamoDao.numeroPrestamoPHVIG(
				datGarantia.getCedula()).intValue());

		// sumatoria div de pretamos vig ph
		garantia.setSumDivPh(cuotaHipotecario);

		// sumatoria monto de prestamos vig ph
		garantia.setSumMntPh(prestamoDao.sumatoriaMntPHVIG(datGarantia
				.getCedula()));

		garantia.setCuotaHipotecarios(cuotaHipotecario);
		if (cuotaHipotecario.doubleValue() > 0) {
			detPh = new DetalleCalculoEgresos();
			detPh.setNombre("Cuota Pr\u00E9stamo Hipotecario por Total de Dividendos: ");
			detPh.setValor(cuotaHipotecario.multiply(new BigDecimal(-1)));
			detPh.setValorMtn(BigDecimal.ZERO);
		}
		garantia.getDetalleCalculoEgresos().add(detPh);
		if (detPq == null && detPh == null) {
			detPq = new DetalleCalculoEgresos();
			detPq.setNombre(" ");
			detPq.setValor(BigDecimal.ZERO);
			detPq.setValorMtn(BigDecimal.ZERO);
			// garantia.setDetalleCalculoEgresos(null);
			garantia.getDetalleCalculoEgresos().add(detPq);
		}

		BigDecimal capacidadEndeudamiento = BigDecimal.ZERO;
		capacidadEndeudamiento = garantia.getCapacidadPago()
				.subtract(valorDividendosTotalPqs).subtract(cuotaHipotecario);
		/*
		 * if
		 * (valReqPrecalificacion.getGarantia().getValReqFondos().isNovacion())
		 * { capacidadEndeudamiento = garantia.getCapacidadPago().subtract(
		 * cuotaHipotecario); } else { capacidadEndeudamiento =
		 * garantia.getCapacidadPago() .subtract(valorDividendosTotalPqs)
		 * .subtract(cuotaHipotecario); }
		 */
		garantia.setCapacidadEndeudamiento(capacidadEndeudamiento);

		final BigDecimal promedioSueldos = capacidadEndeudamiento;
		garantia.setSueloPromedio(promedioSueldos);

		// Se crea un detalle para la cuota del Buro
		// CB: Se elimina de las garantÃ­as el detalle del buro de credito
		/*
		 * DetalleGarantia cuotaBuro = new DetalleGarantia();
		 * cuotaBuro.setNombreGarantia("Deudas de BurÃ³");
		 * cuotaBuro.setValorGarantia
		 * (unaGarantia.getCuotaMensualBuro().multiply(
		 * BigDecimal.valueOf(-1))); detalles.add(cuotaBuro);
		 */

		// garantia.setListaPrestamosPHVig(obtenerPrestamosPHVIG(datGarantia.getCedula()));

		return garantia;
	}


	/**
	 * 
	 * <b> Almacena las prestaciones de jubilados. </b>
	 * <p>
	 * [Author: rtituana, Date: 16/06/2011]
	 * </p>
	 * 
	 * @param prestacionesjubilado
	 * @return
	 */
	private List<PrestacionConcesion> procesarPrestamoConcesion(final List<PrestacionPensionado> listaPrestaciones) {
		
		// INC-2135 Pensiones Jubilados/Pensionistas
		final List<PrestacionConcesion> listPrestamoConcesion = new ArrayList<PrestacionConcesion>();
		if (listaPrestaciones == null || listaPrestaciones.isEmpty()) {
			return listPrestamoConcesion;
		}

		BigDecimal diferencia = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		BigDecimal sumatoriaDif = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		final Date hoy = new Date(System.currentTimeMillis());
		BigDecimal porcentaje = BigDecimal.ZERO;

		for (final PrestacionPensionado prestacion : listaPrestaciones) {
			final PrestacionConcesion prestamoConcesion = new PrestacionConcesion();
			prestamoConcesion.setPpCodigoPrestacion(Long.valueOf(prestacion.getSecuencialprestacion()));
			diferencia = new BigDecimal(prestacion.getIngresos()).subtract(new BigDecimal(prestacion.getEgresos()));
			sumatoriaDif = sumatoriaDif.add(diferencia.setScale(2, RoundingMode.HALF_UP));
			prestamoConcesion.setPpValorPrestacionpq(diferencia.setScale(2, RoundingMode.HALF_UP));
			prestamoConcesion.setPpFechaCreacion(hoy);
			final BigDecimal valorPrestacionPq = prestamoConcesion.getPpValorPrestacionpq();
			porcentaje = valorPrestacionPq.divide(sumatoriaDif, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
			prestamoConcesion.setPpPorcentajeParticipacion(porcentaje);
			
			listPrestamoConcesion.add(prestamoConcesion);
		}
				
		return listPrestamoConcesion;
	}
	
	private void setMapVariedadCredito() {
		mapVariedadCredito = new HashMap<Integer, TipoRol>();		
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_AFILIADO.getIdVariedad(), TipoRol.AF);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_ZAFRERO_TEMPORAL.getIdVariedad(), TipoRol.AF);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_JUBILADO_HL.getIdVariedad(), TipoRol.JU);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_JUBILADO_HOST_GYE.getIdVariedad(), TipoRol.JU);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_JUBILADO_HOST_UIO.getIdVariedad(), TipoRol.JU);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio#obtenerMontoMaximo(java.math.BigDecimal,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista, int)
	 */
	@Override
	public BigDecimal obtenerMontoMaximo(final BigDecimal plazo, final TipoPrestamista tipoPrestamista, final int edad)
			throws ParametrizacionPQException, ParametroCreditoException {
		ParametrizacionPQ parametro = null;
		if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			parametro = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A", TipoProductoEnum.JUB_PEN.getDescripcion(),
					new BigDecimal(edad));
		} else {
			parametro = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A", TipoProductoEnum.NORMAL.getDescripcion());
		}
		final BigDecimal numeroMaximoSBU = parametro.getMontoMaximoSBU();
		
		final BigDecimal valorSBU = parametroCreditoServicio.obtenerValorSBU();
		
		return numeroMaximoSBU.multiply(valorSBU);
	}

	public InformacionPQExigible getInfoPqExigile() {
		return infoPqExigile;
	}

	public void setInfoPqExigile(final InformacionPQExigible infoPqExigile) {
		this.infoPqExigile = infoPqExigile;
	}

}
