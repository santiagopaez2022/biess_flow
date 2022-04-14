package ec.fin.biess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.ConfiguracionPQEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.creditos.pq.servicio.PersonalizarCreditoService;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.pq.dto.migracion.cartera.DataPersonalizacionDto;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;
import ec.fin.biess.webservice.MontoMaximoPqWebService;
import ec.fin.biess.webservice.ObtenerMontoMaximoPq;
import ec.fin.biess.webservice.SimulacionException_Exception;

/**
 * Implementacion de la interfaz de PersonalizarCreditoService
 * 
 * @author diana.suasnavas
 *
 */
@Stateless
public class PersonalizarCreditoServiceImpl implements PersonalizarCreditoService {

	@EJB
	private transient ParametroBiessDao parametroBiessDao;
	@EJB
	private transient ParametroCreditoServicio parametroCreditoServicio;
	@EJB
	private transient ParametrizacionPQServicioLocal parametrizacionPQServicio;

	/**
	 * Monto maximo se guarda en cache
	 */
	private static  BigDecimal MONTO_MAX_EMERGENTE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.PersonalizarCreditoService#obtenerMontoMaximoPorTipoAmortizacion(java.math.
	 * BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.lang.String, boolean,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista, int)
	 */
	@Override
	public BigDecimal obtenerMontoMaximoPorTipoAmortizacion(final BigDecimal cuotaMaximaEndeudamiento, final BigDecimal tasaInteres, final BigDecimal numeroMeses,
			final BigDecimal garantiaTotal, final String tipo, final boolean esEmergente, final TipoPrestamista tipoPrestamista, final int edad) throws MontosMaximosException {
		BigDecimal montoMaximo = null;
		final TipoTablaEnum tipoTabla = TipoTablaEnum.valueOf(tipo);
		switch (tipoTabla) {
		case ALEMANA:
			montoMaximo = montoMaximoAlemana(cuotaMaximaEndeudamiento, tasaInteres, numeroMeses, garantiaTotal, esEmergente, tipoPrestamista, edad);
			break;
		case FRANCESA:
			montoMaximo = montoMaximoFrancesa(cuotaMaximaEndeudamiento, tasaInteres, numeroMeses, garantiaTotal, esEmergente, tipoPrestamista, edad);
			break;
		default:
			throw new MontosMaximosException("El tipo de tabla deber ser ALEMANA o FRANCESA");
		}

		return montoMaximo;
	}

	/**
	 * Obtiene el monto maximo para tabla alemana
	 * 
	 * @param cuotaMaximaEndeudamiento
	 * @param tasaInteres
	 * @param numeroMeses
	 * @param garantiaTotal
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 * @throws MontosMaximosException
	 */
	private BigDecimal montoMaximoAlemana(final BigDecimal cuotaMaximaEndeudamiento, final BigDecimal tasaInteres, final BigDecimal numeroMeses,
			final BigDecimal garantiaTotal, final boolean esEmergente, final TipoPrestamista tipoPrestamista, final int edad) throws MontosMaximosException {
		BigDecimal montoMaximo = null;
		try {
			final MontoMaximoPqWebService montoMaximoPqWebService = this.getMontoMaximoPqWebService();
			final BigDecimal porcentajeMontoMaximoGarantias = this.obtenerPorcentajeGarantia(esEmergente, tipoPrestamista, edad, numeroMeses);
			final BigDecimal valorMaximoSBU = this.obtenerValorMaximoSBU(tipoPrestamista, edad, numeroMeses);

			montoMaximo = montoMaximoPqWebService.obtenerMontoMaximoAlemana(cuotaMaximaEndeudamiento, tasaInteres, numeroMeses, garantiaTotal,
					porcentajeMontoMaximoGarantias, valorMaximoSBU);
			if(esEmergente && montoMaximo.compareTo(obtenerMontoMaxSimulacionEmer())>=0) {
				montoMaximo=obtenerMontoMaxSimulacionEmer();
			}
			
		} catch (final SimulacionException_Exception e) {
			throw new MontosMaximosException("El servicio web de montos maximos alemana no esta disponible", e);
		}

		return montoMaximo;
	}

	/**
	 * Obtiene el monto maximo para tabla francesa
	 * 
	 * @param cuotaMaximaEndeudamiento
	 * @param tasaInteres
	 * @param numeroMeses
	 * @param garantiaTotal
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 * @throws MontosMaximosException
	 */
	private BigDecimal montoMaximoFrancesa(final BigDecimal cuotaMaximaEndeudamiento, final BigDecimal tasaInteres, final BigDecimal numeroMeses,
			final BigDecimal garantiaTotal, final boolean esEmergente, final TipoPrestamista tipoPrestamista, final int edad) throws MontosMaximosException {

		BigDecimal montoMaximo = null;
		final BigDecimal porcentajeMontoMaximoGarantias = obtenerPorcentajeGarantia(esEmergente, tipoPrestamista, edad, numeroMeses);
		final MontoMaximoPqWebService montoMaximoPqWebService = getMontoMaximoPqWebService();
		final BigDecimal valorMaximoSBU = this.obtenerValorMaximoSBU(tipoPrestamista, edad, numeroMeses);

		try {
			montoMaximo = montoMaximoPqWebService.obtenerMontoMaximoFrancesa(cuotaMaximaEndeudamiento, tasaInteres, numeroMeses, garantiaTotal,
					porcentajeMontoMaximoGarantias, valorMaximoSBU);
			
			if(esEmergente && montoMaximo.compareTo(obtenerMontoMaxSimulacionEmer())>=0) {
				montoMaximo=obtenerMontoMaxSimulacionEmer();
			}
		} catch (final SimulacionException_Exception e) {
			throw new MontosMaximosException("El servicio web de montos maximos francesa no esta disponible", e);
		}

		return montoMaximo;
	}
	
	/**
	 * Obtiene el porcentaje de garantia
	 * 
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @param plazo
	 * @return
	 * @throws MontosMaximosException
	 */
	@Override
	public BigDecimal obtenerPorcentajeGarantia(final boolean esEmergente, final TipoPrestamista tipoPrestamista, int edad, BigDecimal plazo)
			throws MontosMaximosException {
		BigDecimal resp = BigDecimal.ZERO;
	
			ParametrizacionPQ parametrizacionPQ = null;
			try {
				if (TipoPrestamista.JUBILADO == tipoPrestamista) {
					parametrizacionPQ = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A",
							TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(edad));
				} else {
					parametrizacionPQ = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A",
							TipoProductoEnum.NORMAL.getDescripcion());
				}
				resp = parametrizacionPQ.getMontoMaximoGarantia();
			} catch (final ParametrizacionPQException e) {
				throw new MontosMaximosException("Error al obtener parametro de porcentaje de garantias", e);
			}
		
		return resp;
	}
	
	/**
	 * Obtiene el valor por el que se multiplicara el SBU para obtener el monto maximo del prestamo
	 * 
	 * @param tipoPrestamista
	 * @param edad
	 * @param plazo
	 * @return
	 * @throws MontosMaximosException
	 */
	private BigDecimal obtenerValorMaximoSBU(final TipoPrestamista tipoPrestamista, final int edad, final BigDecimal plazo) throws MontosMaximosException {
		BigDecimal valorMaxSBU = BigDecimal.ZERO;
		ParametrizacionPQ parametrizacionPQ = null;
		
		try {
			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
				parametrizacionPQ = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A",
						TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(edad));
			} else {
				parametrizacionPQ = parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A",
						TipoProductoEnum.NORMAL.getDescripcion());
			}
			valorMaxSBU = parametrizacionPQ.getMontoMaximoSBU();
		} catch (final ParametrizacionPQException e) {
			throw new MontosMaximosException("Error al obtener parametro de valor maximo SBU", e);
		}
		
		return valorMaxSBU;
	}
	
	/**
	 * Conexion al servicio web de montos maximos
	 * 
	 * @return
	 * @throws MontosMaximosException
	 */
	private MontoMaximoPqWebService getMontoMaximoPqWebService() throws MontosMaximosException {
		String url;
		try {
			url = parametroBiessDao.consultarPorIdNombreParametro(ConfiguracionPQEnum.URL_MONTO_MAXIMO.getIdParametro(),
					ConfiguracionPQEnum.URL_MONTO_MAXIMO.getNombreParametro()).getValorCaracter();
		} catch (final ParametroBiessException e) {
			throw new MontosMaximosException("Error al obtener parametros de URL de montos maximos de biess emergente", e);
		}
		try {
			final QName qname = new QName("http://webservice.biess.fin.ec/", "ObtenerMontoMaximoPq");
			final ObtenerMontoMaximoPq obtenerMontoMaximoPq = new ObtenerMontoMaximoPq(new URL(url), qname);
			return obtenerMontoMaximoPq.getMontoMaximoPqWebServicePort();
		} catch (final Exception ex) {
			throw new MontosMaximosException("La URL de montos maximos no esta disponible", ex);
		}
	}
	
	private synchronized BigDecimal obtenerMontoMaxSimulacionEmer() throws MontosMaximosException {
	 
		try {
			if(MONTO_MAX_EMERGENTE==null) {
			MONTO_MAX_EMERGENTE= parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MONTO_MAX_CREDITO_EMERGENTE.getIdParametro(),
					BiessEmergenteEnum.MONTO_MAX_CREDITO_EMERGENTE.getNombreParametro()).getValorNumerico();
			}
			return MONTO_MAX_EMERGENTE;
		} catch (ParametroBiessException e) {
			throw new MontosMaximosException("Error al obtener parametros de valor monto maximo de biess emergente", e);
		}
		
	}

	@Override
	public BigDecimal obtenerMontoMaximoPorTipoAmortizacionFran(final DataPersonalizacionDto dataPerDto)
			throws MontosMaximosException, MontoMinimoException {
		final BigDecimal plazo = BigDecimal.valueOf(dataPerDto.getPlazoMaximo());
		// comienza validaciones para mostrar el monto maximo
		ParametrizacionPQ parametrizacionPQ = null;
		BigDecimal valorSBU = null;
		BigDecimal valorMinimoSBU = null;
		BigDecimal valorSBUMitad = null;
		try {
		if (TipoPrestamista.JUBILADO == dataPerDto.getTipoPrestamista()) {
			parametrizacionPQ = this.parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A",
					TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(dataPerDto.getEdad()));
		} else {
			parametrizacionPQ = this.parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A",
					TipoProductoEnum.NORMAL.getDescripcion());
		}
		} catch (final ParametrizacionPQException e) {
			throw new MontosMaximosException("Error al obtener parametro de valor minimo SBU", e);
		}
		
		valorMinimoSBU = parametrizacionPQ.getMontoMinimoSBU();
		if (valorMinimoSBU == null) {
			throw new MontoMinimoException("No existe parametrizado el valor de monto m\u00EDnimo del cr\u00E9dito.");
		}
	    try {
			valorSBU = parametroCreditoServicio.obtenerValorSBU();
		} catch (final ParametroCreditoException e) {
			throw new MontosMaximosException("Error al obtener parametro valor del SBU", e);
		}
	
		valorSBUMitad = valorSBU.multiply(valorMinimoSBU);
		final BigDecimal mntMaximoGarantia =dataPerDto.getGarantiaTotal();
		final BigDecimal porcMmtMaxGarantia = obtenerPorcentajeGarantia(dataPerDto.isEmergente(), dataPerDto.getTipoPrestamista(),
				dataPerDto.getEdad(), plazo);
		final BigDecimal montoMaximoPorcentaje = mntMaximoGarantia.multiply(porcMmtMaxGarantia).divide(new BigDecimal(100));
		final BigDecimal cupoMaxCred = dataPerDto.getCupoMaxCredito();
		final BigDecimal salDispConcesion = cupoMaxCred.subtract(dataPerDto.getTotalSaldosCred());
		final BigDecimal montoMaxTbl= TipoTablaEnum.FRANCESA.name().equals(dataPerDto.getTipo())?dataPerDto.getSimulacionSac().getMontoPrestamo():obtenerMontoMaximoPorTipoAmortizacionAle(dataPerDto);
		
		return  devolverMontoMaximo(montoMaxTbl, valorSBUMitad, montoMaximoPorcentaje, salDispConcesion);
		
	}
	
	private BigDecimal devolverMontoMaximo(final BigDecimal montoMaxPrestamo, final BigDecimal valorSBUMitad,
			final BigDecimal montoMaximoPorcentaje, final BigDecimal salDispConcesion) throws MontosMaximosException {
		BigDecimal montoMaximoCredito;
		if (salDispConcesion.compareTo(montoMaxPrestamo) > 0) {
			montoMaximoCredito = montoMaxPrestamo;
		} else {
			montoMaximoCredito = salDispConcesion;
		}

		if (montoMaximoCredito.compareTo(montoMaximoPorcentaje) > 0) {
			montoMaximoCredito = montoMaximoPorcentaje;

		} else {
			// Dejo el mismo monto maximo
		}

		if (montoMaximoCredito.compareTo(valorSBUMitad) > 0) {
			// Aqui debo dejar el valor del monto minimo
		} else {
			throw new MontosMaximosException("El monto m\u00EDnimo de concesi\u00F3n corresponde a "+valorSBUMitad+"USD"+". No puede acceder a pr\u00E9stamo quirografario en este momento.");
		}
		return montoMaximoCredito;
	}



	@Override
	public BigDecimal obtenerMontoMaximoPorTipoAmortizacionAle(final DataPersonalizacionDto dataPerDto)
			throws MontosMaximosException, MontoMinimoException {
		final BigDecimal valorDivision = BigDecimal.ONE
				.divide(BigDecimal.valueOf(dataPerDto.getSimulacionSac().getPlazo()), 10, BigDecimal.ROUND_HALF_UP);
		final BigDecimal tasaIntereseNominal = dataPerDto.getSimulacionSac().getInteres().divide(BigDecimal.valueOf(100), 10,
						BigDecimal.ROUND_HALF_UP);
		final BigDecimal valorDivision2 = tasaIntereseNominal.divide(BigDecimal.valueOf(12), 10,
				BigDecimal.ROUND_HALF_UP);
		final BigDecimal valorDivisionFinal = valorDivision.add(valorDivision2);
		return dataPerDto.getSimulacionSac().getValorCuotaPagar().divide(valorDivisionFinal, RoundingMode.HALF_EVEN);
	
	
	}
	
}
