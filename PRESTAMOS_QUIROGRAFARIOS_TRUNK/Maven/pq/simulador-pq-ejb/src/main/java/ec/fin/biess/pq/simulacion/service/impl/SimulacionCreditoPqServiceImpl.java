package ec.fin.biess.pq.simulacion.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoIdentificacionSacEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.creditos.pq.servicio.PersonalizarCreditoService;
import ec.fin.biess.creditos.pq.servicio.PrestamoCalculoService;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.pq.simulacion.dto.DatosSimulacionCuotaMontoDto;
import ec.fin.biess.pq.simulacion.dto.DatosSimulacionDto;
import ec.fin.biess.pq.simulacion.dto.DetalleEgresosDto;
import ec.fin.biess.pq.simulacion.dto.DetalleIngresosDto;
import ec.fin.biess.pq.simulacion.dto.ParametrosCalculoCreditoResponseDto;
import ec.fin.biess.pq.simulacion.exception.SimuladorPqException;
import ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqLocalService;
import ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqRemoteService;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.GarantiaCesantiaDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoRol;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.enumeracion.VariedadCreditoEnum;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.DatosOrdenCompra;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosFondos;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.PrestamoRequestDto;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.dto.migracion.cartera.DataPersonalizacionDto;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.pq.excepcion.CondicionCalculoException;
import ec.gov.iess.creditos.pq.excepcion.EsperanzaVidaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiaException;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.MontoMinimoException;
import ec.gov.iess.creditos.pq.excepcion.PlazoMaximoEndeudamientoException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicio;
import ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiasPorOperacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;
import ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.hl.exception.RegistroCivilException;
import ec.gov.iess.hl.modelo.Imposicion;
import ec.gov.iess.hl.modelo.RegistroCivil;
import ec.gov.iess.hl.servicio.ImposicionServicio;
import ec.gov.iess.hl.servicio.RegistroCivilServicio;
import ec.gov.iess.servicio.fondoreserva.modelo.CuentaFondoReserva;

/**
 * Servicio para la simulacion de prestamos quirografarios
 * 
 * @author hugo.mora
 * @date 2017/05/16
 *
 */
@Stateless
public class SimulacionCreditoPqServiceImpl implements SimulacionCreditoPqLocalService, SimulacionCreditoPqRemoteService {

	private static final String CUOTA_PREST_MNT = "Cuota Pr\u00E9stamo Quirografario por Monto: ";

	private static final LoggerBiess LOG = LoggerBiess.getLogger(SimulacionCreditoPqServiceImpl.class);

	@EJB
	private RegistroCivilServicio registroCivilServicio;

	@EJB
	private CondicionCalculoServicio condicionCalculoServicio;

	@EJB
	private ImposicionServicio imposicionServicio;

	@EJB
	private GarantiaCreditoServicio garantiaCreditoServicio;

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private GarantiaCesantiaDao garantiaCesantiaDao;

	@EJB
	private ConsultaFondosReservaServicio consultaFondosReservaServicio;

	@EJB
	private ParametroBiessDao parametroBiessDao;

	@EJB
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;

	@EJB
	private PersonalizarCreditoService personalizarCreditoService;

	@EJB
	private ParametroCreditoServicio parametroCreditoServicio;

	@EJB
	private SimularCreditoServicio simularCreditoServicio;

	@EJB
	private CalculoCreditoServicio calculoCreditoServicio;

	@EJB
	private PrecalificacionServicio precalificacionServicio;

	@EJB
	private PrestamoCalculoService prestamoCalculoService;
	
	@EJB
	private GarantiasSacServicioLocal garantiasSacServicio;
	
	@EJB
	private GarantiasPorOperacionSacServicio garantiaSacPorOp;

	private CalculoCreditoHelperSingleton calculoCreditoHelper;

	private BigDecimal valorMinimoSBU;

	private BigDecimal valorSBUMitad;

	private BigDecimal plazoMaximoEmergente;

	private Map<Integer, TipoRol> mapVariedadCredito;

	// Migracion cartera
	private BigDecimal montoMaxSimulacion;
	private DatosCredito datosCredito;

	/**
	 * Metodo que se ejecuta despues del constructor
	 */
	@PostConstruct
	private void init() {
		calculoCreditoHelper = CalculoCreditoHelperSingleton.getInstancia();

		try {
			this.plazoMaximoEmergente = this.parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.PLAZO_MAXIMO.getIdParametro(),
					BiessEmergenteEnum.PLAZO_MAXIMO.getNombreParametro()).getValorNumerico();
		} catch (final ParametroBiessException e) {
			LOG.error("Error al obtener parametro de base de datos", e);
		}
	}

	/**
	 * Enumeracion para obtener parametros del credito para la simulacion
	 * 
	 * @author hugo.mora
	 *
	 */
	private enum ParametrosCreditoEnum {
		DETALLE_EGRESOS, CAPACIDAD_ENDEUDAMIENTO, DETALLE_INGRESOS, TOTAL_GARANTIAS, TIENE_CAPACIDAD_ENDEUDAMIENTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#obtenerParametrosSimulacion(ec.fin.biess.pq.
	 * simulacion.dto.DatosSimulacionDto)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public ParametrosCalculoCreditoResponseDto obtenerParametrosSimulacion(final DatosSimulacionDto datosSimulacionDto) throws SimuladorPqException {
		final String cedula = datosSimulacionDto.getCedula();
		final TipoPrestamista tipoPrestamista = datosSimulacionDto.getTipoPrestamista();
		final TiposProductosPq tipoProducto = datosSimulacionDto.getTipoProducto();
		final boolean esNovacion = datosSimulacionDto.isEsNovacion();
		final Prestamo prestamoNovar = datosSimulacionDto.getPrestamoNovar();
		final BigDecimal sueldoPromedioAfiliado = datosSimulacionDto.getSueldoPromedioAfiliado();
		final BigDecimal valorCesantia = datosSimulacionDto.getValorCesantia();
		final InformacionPrestacionPensionado informacionPrestacion = datosSimulacionDto.getInformacionPrestacionPensionado();
		final TipoPrestamista rolPrestamista = datosSimulacionDto.getRolPrestamista();
		final List<CreditoExigibleDto> listaCreditos =datosSimulacionDto.getListaCreditos();

		boolean esEmergente = false;

		if (TiposProductosPq.EMERG == tipoProducto) {
			esEmergente = true;
		}

		final ParametrosCalculoCreditoResponseDto response = new ParametrosCalculoCreditoResponseDto();
		final RegistroCivil registroCivil = this.obtenerDatosRegistroCivil(cedula);
		final String genero = "1".equals(registroCivil.getGenper()) ? "M" : "F";
		final Date fechaNacimientoAsegurado = registroCivil.getFecnacper();
		response.setFechaNacimiento(fechaNacimientoAsegurado);

		// Setea variables de edad del asegurado
		final BigDecimal edadMesesAnios = this.calculoCreditoHelper.determinarEdad(fechaNacimientoAsegurado, new Date());
		response.setEdadActualAnios(edadMesesAnios.intValue());
		response.setEdadActualMeses(this.calculoCreditoHelper.determinarNumeroMeses(edadMesesAnios));

		// Setea variable de plazo maximo
		response.setPlazoMaximo(this.obtenerPlazoMaximo(genero, fechaNacimientoAsegurado, tipoPrestamista));

		// Setea variable de sueldo promedio
		response.setSueldoPromedio(this.obtenerIngresoPromedio(cedula, tipoPrestamista, sueldoPromedioAfiliado, informacionPrestacion));

		// Setea variable de capacidad de pago
		response.setCapacidadPago(
				this.obtenerCapacidadPago(response.getSueldoPromedio(), esEmergente, tipoPrestamista, response.getEdadActualAnios()));

		// Setea variable de porcentaje de capacidad de pago
		response.setPorcentajeCapacidadPago(this.obtienePorcentajeCapacidadPago(tipoPrestamista, fechaNacimientoAsegurado));

		Map<ParametrosCreditoEnum, Object> egresosCapacidad = new HashMap<SimulacionCreditoPqServiceImpl.ParametrosCreditoEnum, Object>();
		egresosCapacidad = this.obtenerDetalleEgresosCapacidadEndeudamiento(cedula, esNovacion, prestamoNovar, response.getCapacidadPago(),
				rolPrestamista, tipoPrestamista,listaCreditos);
		@SuppressWarnings("unchecked")
		final
		List<DetalleEgresosDto> listaEgresos = (List<DetalleEgresosDto>) egresosCapacidad.get(ParametrosCreditoEnum.DETALLE_EGRESOS);
		// Setea detalle de egresos
		response.setListaDetalleEgresos(listaEgresos);

		// Setea capacidad de endeudamiento
		response.setCapacidadEndeudamiento((BigDecimal) egresosCapacidad.get(ParametrosCreditoEnum.CAPACIDAD_ENDEUDAMIENTO));

		// Setea tiene capacidad de endeudamiento
		response.setTieneCapacidadEndeudamiento((Boolean) egresosCapacidad.get(ParametrosCreditoEnum.TIENE_CAPACIDAD_ENDEUDAMIENTO));

		// Setea informacion de prestaciones para jubilados y pensionistas
		response.setInformacionPrestacionPensionado(informacionPrestacion);

		Map<ParametrosCreditoEnum, Object> ingresosTotalGarantia = new HashMap<SimulacionCreditoPqServiceImpl.ParametrosCreditoEnum, Object>();
		if (TipoPrestamista.AFILIADO == tipoPrestamista || TipoPrestamista.ZAFRERO_TEMPORAL == tipoPrestamista) {
			ingresosTotalGarantia = this.obtenerDetalleIngresosTotalGarantiasAfiliado(cedula, esNovacion, valorCesantia,prestamoNovar,datosSimulacionDto);

			@SuppressWarnings("unchecked")
			final
			List<DetalleIngresosDto> listaIngresos = (List<DetalleIngresosDto>) ingresosTotalGarantia.get(ParametrosCreditoEnum.DETALLE_INGRESOS);
			// Setea detalle de ingresos
			response.setListaDetalleIngresos(listaIngresos);
		}

		// Setea el total de garantias disponibles
		if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			final ValidarRequisitosPrecalificacion validarRequisitos = new ValidarRequisitosPrecalificacion();
			final DatosGarantia datosGarantia = new DatosGarantia();
			validarRequisitos.setGarantia(datosGarantia);
			try {
				this.precalificacionServicio.consultarMontoMaximo(validarRequisitos);
			} catch (final GarantiaException e) {
				throw new SimuladorPqException(e);
			}
			response.setTotalGarantia(validarRequisitos.getGarantia().getParametroMontoMaximo());

		} else if (TipoPrestamista.AFILIADO == tipoPrestamista || TipoPrestamista.ZAFRERO_TEMPORAL == tipoPrestamista) {
			response.setTotalGarantia((BigDecimal) ingresosTotalGarantia.get(ParametrosCreditoEnum.TOTAL_GARANTIAS));
		}

		return response;
	}

	/**
	 * Obtiene el porcentaje de capacidad de pago dado un indicador de si el producto es emergente
	 * 
	 * @return
	 * @throws SimuladorPqException
	 */
	private BigDecimal obtienePorcentajeCapacidadPago(final TipoPrestamista tipoPrestamista, final Date fechaNacimiento)
			throws SimuladorPqException {

		BigDecimal porcentajeCapacidadPago = BigDecimal.ZERO;
		final int edadAsegurado = calculoCreditoHelper.determinarEdad(fechaNacimiento, new Date()).intValue();
		try {
			porcentajeCapacidadPago = this.garantiaCreditoServicio.obtenerPorcentajeComprometimientoPQ(false, tipoPrestamista, edadAsegurado, null);
		} catch (final ParametrizacionPQException e) {
			throw new SimuladorPqException(e);
		} catch (final ParametroBiessException e) {
			throw new SimuladorPqException(e);
		}

		return porcentajeCapacidadPago;
	}

	/**
	 * Obtiene la fecha de nacimiento del asegurado de la tabla del registro civil (KSPCOTREGCIV) dado el numero de
	 * cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws SimuladorPqException
	 */
	private RegistroCivil obtenerDatosRegistroCivil(final String cedula) throws SimuladorPqException {
		RegistroCivil registroCivil = null;
		try {
			registroCivil = registroCivilServicio.consultarRegistroCivil(cedula);
		} catch (final RegistroCivilException e) {
			throw new SimuladorPqException(e);
		}

		return registroCivil;
	}

	/**
	 * Obtiene el plazo maximo en meses para la concesion del credito
	 * 
	 * @param genero
	 * @param fechaNacimiento
	 * @param tipoPrestamista
	 * @return
	 * @throws SimuladorPqException
	 */
	private int obtenerPlazoMaximo(final String genero, final Date fechaNacimiento, final TipoPrestamista tipoPrestamista) throws SimuladorPqException {
		int plazoMaximo = 0;

		BigDecimal esperanzaVida = null;
		try {
			esperanzaVida = this.condicionCalculoServicio.consultarEsperanzaVida(genero, fechaNacimiento, new Date());
		} catch (final EsperanzaVidaException e) {
			throw new SimuladorPqException(e);
		}

		try {
			plazoMaximo = this.condicionCalculoServicio.determinarPlazoMaximoEndeudamiento(fechaNacimiento, esperanzaVida, tipoPrestamista)
					.intValue();
		} catch (final PlazoMaximoEndeudamientoException e) {
			throw new SimuladorPqException(e);
		}

		return plazoMaximo;
	}

	/**
	 * Obtiene el valor del ingreso promedio para la simulacion
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @param sueldoPromedioAfiliado
	 * @param informacionPrestacion
	 * @return
	 * @throws SimuladorPqException
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	private BigDecimal obtenerIngresoPromedio(final String cedula, final TipoPrestamista tipoPrestamista, final BigDecimal sueldoPromedioAfiliado,
			final InformacionPrestacionPensionado informacionPrestacion) throws SimuladorPqException {
		BigDecimal ingresoPromedio = BigDecimal.ZERO;

		if (TipoPrestamista.AFILIADO == tipoPrestamista) {
			ingresoPromedio = sueldoPromedioAfiliado;
		} else if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			if (informacionPrestacion != null && informacionPrestacion.getListaPrestaciones() != null
					&& !informacionPrestacion.getListaPrestaciones().isEmpty()) {
				ingresoPromedio = informacionPrestacion.getValorAComprometer();
			} else {
				ingresoPromedio = BigDecimal.ZERO;
			}
		} else if (TipoPrestamista.ZAFRERO_TEMPORAL == tipoPrestamista) {
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

			imposionesUltimoSemestreAnioAnterior = imposicionServicio.consultarPorCedulaAnioListaMes(cedula, anioAnterior, mesList);
			if (imposionesUltimoSemestreAnioAnterior.size() < 6) {
				throw new SimuladorPqException(
						"EL AFILIADO (ZAFRERO TEMPORAL) NO TIENE TODOS LOS APORTES DEL \u00DALTIMO SEMESTRE DEL A\u00D1O ANTERIOR, DETECTADO EN CONSULTA DE GARANT\u00CDAS.  C\u00C9DULA: "
								+ cedula + ".  N\u00DAMERO DE APORTES: " + imposionesUltimoSemestreAnioAnterior.size());
			}

			ingresoPromedio = imposicionServicio.calcularPromedio(imposionesUltimoSemestreAnioAnterior);
		}

		return ingresoPromedio;
	}

	/**
	 * @param ingresoPromedio
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 * @throws SimuladorPqException
	 */
	private BigDecimal obtenerCapacidadPago(final BigDecimal ingresoPromedio, final boolean esEmergente, final TipoPrestamista tipoPrestamista, final int edad)
			throws SimuladorPqException {
		BigDecimal capacidadPago = BigDecimal.ZERO;

		BigDecimal porcentajeComprometimiento = BigDecimal.ZERO;
		try {
			porcentajeComprometimiento = this.garantiaCreditoServicio.obtenerPorcentajeComprometimientoPQ(esEmergente, tipoPrestamista, edad, null);
		} catch (final ParametrizacionPQException e) {
			throw new SimuladorPqException(e);
		} catch (final ParametroBiessException e) {
			throw new SimuladorPqException(e);
		}
		porcentajeComprometimiento = porcentajeComprometimiento.divide(BigDecimal.valueOf(100));

		capacidadPago = ingresoPromedio.multiply(porcentajeComprometimiento);

		return capacidadPago;
	}

	/**
	 * Obtiene un detalle de los egresos del asegurado y la capacidad de endeudamiento
	 * 
	 * @param cedula
	 * @param esNovacion
	 * @param prestamoNovacion
	 * @param capacidadPago
	 * @param rolPrestamista
	 * @param tipoPrestamista
	 * @return
	 * @throws SimuladorPqException
	 */
	private Map<ParametrosCreditoEnum, Object> obtenerDetalleEgresosCapacidadEndeudamiento(final String cedula, final boolean esNovacion,
			final Prestamo  prestamoNovacion, final BigDecimal capacidadPago, final TipoPrestamista rolPrestamista, final TipoPrestamista tipoPrestamista,final List<CreditoExigibleDto> listaCreditos) {
		this.setMapVariedadCredito();

		final Map<ParametrosCreditoEnum, Object> parametrosCredito = new HashMap<SimulacionCreditoPqServiceImpl.ParametrosCreditoEnum, Object>();

		final List<DetalleEgresosDto> listaEgresos = new ArrayList<DetalleEgresosDto>();
		DetalleEgresosDto detPq = null;
		DetalleEgresosDto detPh = null;

		// Egreso prestamos quirografarios
		Double valorDividendo = new Double(0);
		BigDecimal valorDividendosTotalPqs = BigDecimal.ZERO;

	
		for (final CreditoExigibleDto credito : listaCreditos) {

			if (null != rolPrestamista && rolPrestamista.compareTo(TipoPrestamista.AFILIADO_JUBILADO) == 0) {

				final Prestamo prestamoBd = prestamoDao.buscarPorNumOperacionSAC(credito.getOperacionSAC());
				if (prestamoBd != null && mapVariedadCredito.get(prestamoBd.getCreditoPk().getCodprecla().intValue())
						.compareTo(TipoRol.AF) != 0 && TipoPrestamista.AFILIADO.compareTo(tipoPrestamista)==0) {
				continue;
			}
				}

			if (esNovacion
					&& credito.getOperacionSAC().equals(prestamoNovacion.getNumOperacionSAC())) {
				continue;
			} else {
				
				detPq = new DetalleEgresosDto();
				detPq.setNombre(CUOTA_PREST_MNT + credito.getMontoConcedido());
				valorDividendo = credito.getCuota().doubleValue();
				valorDividendosTotalPqs = valorDividendosTotalPqs.add(BigDecimal.valueOf(valorDividendo));
				detPq.setValor(BigDecimal.valueOf(valorDividendo).multiply(BigDecimal.valueOf(-1)));
				listaEgresos.add(detPq);
			}
		}

		// Egreso prestamos hipotecarios
		final BigDecimal cuotaHipotecario = prestamoDao.cuotaPrestamoHipotecario(cedula);

		if (cuotaHipotecario.doubleValue() > 0) {
			detPh = new DetalleEgresosDto();
			detPh.setNombre("Cuota Pr\u00E9stamo Hipotecario por Total de Dividendos: ");
			detPh.setValor(cuotaHipotecario.multiply(new BigDecimal(-1)));
		}

		listaEgresos.add(detPh);
		if (detPq == null && detPh == null) {
			detPq = new DetalleEgresosDto();
			detPq.setNombre(" ");
			detPq.setValor(BigDecimal.ZERO);
			listaEgresos.add(detPq);
		}

		parametrosCredito.put(ParametrosCreditoEnum.DETALLE_EGRESOS, listaEgresos);

		BigDecimal capacidadEndeudamiento = BigDecimal.ZERO;
		capacidadEndeudamiento = capacidadPago.subtract(valorDividendosTotalPqs).subtract(cuotaHipotecario);

		parametrosCredito.put(ParametrosCreditoEnum.CAPACIDAD_ENDEUDAMIENTO, capacidadEndeudamiento);

		boolean tieneCapacidadEndeudamiento = true;
		if (capacidadEndeudamiento.compareTo(BigDecimal.ZERO) <= 0) {
			tieneCapacidadEndeudamiento = false;
		}

		parametrosCredito.put(ParametrosCreditoEnum.TIENE_CAPACIDAD_ENDEUDAMIENTO, tieneCapacidadEndeudamiento);

		return parametrosCredito;
	}

	/**
	 * Obtiene el detalle de los ingresos de afiliado (cesantia y fondos de reserva)
	 * 
	 * @param cedula
	 * @param esNovacion
	 * @param prestamoNovacion
	 * @param valorCesantia
	 * @return
	 * @throws SimuladorPqException
	 */
	private Map<ParametrosCreditoEnum, Object> obtenerDetalleIngresosTotalGarantiasAfiliado(final String cedula, final boolean esNovacion,
			 final BigDecimal valorCesantia,final Prestamo prestamNovar,final DatosSimulacionDto datosSimulacionDto) throws SimuladorPqException {
		final Map<ParametrosCreditoEnum, Object> parametrosCredito = new HashMap<SimulacionCreditoPqServiceImpl.ParametrosCreditoEnum, Object>();
		final List<DetalleIngresosDto> listaIngresos = new ArrayList<DetalleIngresosDto>();

		final GarantiaCesantia cuentaCesantia = garantiaCesantiaDao.load(cedula);
		BigDecimal valorCesantiaTemp=cuentaCesantia.getValorHistoriaLaboral().add(cuentaCesantia.getValorHost());
		DetalleIngresosDto cesantias = new DetalleIngresosDto();
		cesantias.setNombre("Total:");
		cesantias.setValor(cuentaCesantia.getValorHost().add(cuentaCesantia.getValorHistoriaLaboral()));
		cesantias.setObservacion("Cesantia");
		listaIngresos.add(cesantias);
		// Se obtiene informacion de garantias comprometidas desde SAC
					BigDecimal garantiaComprometidaSacCE = null;			
					try {
						final InformacionGarantias informacionGarantias =(datosSimulacionDto.getInfoGarantia()==null? obtenerGarantiasPQSAC(cedula):datosSimulacionDto.getInfoGarantia());
						
						garantiaComprometidaSacCE = informacionGarantias.getTotalGarantiasFondosCesantia();
					} catch (final GarantiaException e) {
						LOG.error("Se presento un error al obtener informacion de garantias comprometidas de fondos de cesantia en simulador", e);
						throw new SimuladorPqException(e.getMessage());
					}


					final DatosGarantia datGarantia = new DatosGarantia();
					final ValidarRequisitosFondos validarRequisitosFondos = new ValidarRequisitosFondos();
					validarRequisitosFondos.setTiposCargos(this.obtenerTiposCargos());
					validarRequisitosFondos.setEstadoBloqueado("ACT");
					validarRequisitosFondos.setEstadosSolicitud(this.obtenerEstadosSolicitud());
					validarRequisitosFondos.setCedula(cedula);
					validarRequisitosFondos.setNovacion(esNovacion);


					datGarantia.setPrestamoVigNovacion(null);
		if (esNovacion) {

				final OperacionSacRequest operacionSacRequest=new OperacionSacRequest();
				final ClienteRequestDto cliente=new ClienteRequestDto();
				cliente.setTipoCliente(prestamNovar.getTipoafiliadoSac());
				operacionSacRequest.setCliente(cliente);
				final OperacionRequestDto operacion=new OperacionRequestDto();
				operacion.setNumero(prestamNovar.getNumOperacionSAC());
				operacion.setNut(prestamNovar.getNut());
				operacion.setTipoProducto(prestamNovar.getDestinoComercial().getCodProdPq());
				operacionSacRequest.setOperacion(operacion);
				try {
					if(datosSimulacionDto.getDatGarantia()==null || datosSimulacionDto.getDatGarantia().getComprometidoFondos()==null || datosSimulacionDto.getDatGarantia().getComprometidoCesantias()==null ) {
					final InformacionGarantias infoGaran=garantiaSacPorOp.obtenerGarantiasPorOperacion(operacionSacRequest);
					infoGaran.getTotalGarantiasFondosCesantia();
					datGarantia.setComprometidoFondos(infoGaran.getTotalGarantiasFondoReserva());
					datGarantia.setComprometidoCesantias(infoGaran.getTotalGarantiasFondosCesantia());
					}else {
						datGarantia.setComprometidoFondos(datosSimulacionDto.getDatGarantia().getComprometidoFondos());
						datGarantia.setComprometidoCesantias(datosSimulacionDto.getDatGarantia().getComprometidoCesantias());
					}
				
				} catch (final GarantiasSacException e1) {
				LOG.error("Error al consumir garantias SAC");
				throw new  SimuladorPqException("Error al consumir garantias comprometidas SAC");
				}
			
			cesantias = new DetalleIngresosDto();
			cesantias.setNombre("Comprometidas:");
			//consumo las cesantias comprometidas del SAC
			cesantias.setValor(cuentaCesantia.getValorComprometidoHost()
					.add(garantiaComprometidaSacCE.subtract(datGarantia.getComprometidoCesantias()).multiply(BigDecimal.valueOf(-1))));
			
			valorCesantiaTemp=valorCesantiaTemp.subtract(cuentaCesantia.getValorComprometidoHost()
					.add(garantiaComprometidaSacCE.subtract(datGarantia.getComprometidoCesantias())));
			cesantias.setObservacion("Cesantia");
			listaIngresos.add(cesantias);
		} else {
			cesantias = new DetalleIngresosDto();
			cesantias.setNombre("Comprometidas:");
	
			
			// Se reemplaza cuentaCesantia.getValorComprometidoHl() por garantia comprometida desde SAC

			cesantias.setValor(
					cuentaCesantia.getValorComprometidoHost().add(garantiaComprometidaSacCE).multiply(BigDecimal.valueOf(-1)));
			valorCesantiaTemp=valorCesantiaTemp.subtract(cuentaCesantia.getValorComprometidoHost().add(garantiaComprometidaSacCE));
			cesantias.setObservacion("Cesantia");
			listaIngresos.add(cesantias);
		}

		cesantias = new DetalleIngresosDto();
		cesantias.setNombre("Disponible: <strong>(E)</strong>");
		cesantias.setObservacion("Cesantia");
		cesantias.setValor(valorCesantiaTemp);
		listaIngresos.add(cesantias);


		datGarantia.setValReqFondos(validarRequisitosFondos);
		DetalleIngresosDto fondosReserva = null;
		final CuentaFondoReserva cuentaFondoReserva = this.consultaFondosReservaServicio.consultarFondoReserva(datGarantia);
	// Se obtiene el valor comprometido de fondos de reserva desde el SAC
		// Se obtiene informacion de garantias comprometidas desde SAC
		BigDecimal valorComprometidoFR = null;
		try {
			final InformacionGarantias informacionGarantias =(datosSimulacionDto.getInfoGarantia()==null? obtenerGarantiasPQSAC(cedula):datosSimulacionDto.getInfoGarantia());
			valorComprometidoFR = informacionGarantias.getTotalGarantiasFondoReserva();
		} catch (final GarantiaException e) {
			LOG.error("Se presento un error al obtener informacion de garantias comprometidas de fondos de reserva en simulador", e);
			throw new SimuladorPqException(e.getMessage());
		}
		if (valorComprometidoFR == null) {
			valorComprometidoFR = BigDecimal.ZERO;
		}

		if (esNovacion) {
			fondosReserva = new DetalleIngresosDto();
			fondosReserva.setNombre("Total");
			//aqui agregar el valor a restar del credito seleccionado.
			fondosReserva.setValor(cuentaFondoReserva.getSaldoDisponible().add(valorComprometidoFR));
			fondosReserva.setObservacion("Fondos");
			listaIngresos.add(fondosReserva);

			fondosReserva = new DetalleIngresosDto();
			fondosReserva.setNombre("Comprometidas");
			fondosReserva.setValor(valorComprometidoFR.subtract(datGarantia.getComprometidoFondos()).multiply(BigDecimal.valueOf(-1)));
			fondosReserva.setObservacion("Fondos");
			listaIngresos.add(fondosReserva);
			cuentaFondoReserva.setSaldoDisponible(cuentaFondoReserva.getSaldoDisponible().add(datGarantia.getComprometidoFondos()));
			
		} else {
			fondosReserva = new DetalleIngresosDto();
			fondosReserva.setNombre("Total");
			fondosReserva.setValor(cuentaFondoReserva.getSaldoDisponible().add(valorComprometidoFR));
			fondosReserva.setObservacion("Fondos");
			listaIngresos.add(fondosReserva);

			fondosReserva = new DetalleIngresosDto();
			fondosReserva.setNombre("Comprometidas");
			fondosReserva.setValor(valorComprometidoFR.multiply(BigDecimal.valueOf(-1)));
			fondosReserva.setObservacion("Fondos");
			listaIngresos.add(fondosReserva);
			
			cuentaFondoReserva.setSaldoDisponible(cuentaFondoReserva.getSaldoDisponible());
		}

		fondosReserva = new DetalleIngresosDto();
		fondosReserva.setNombre("Disponible: <strong>(F)</strong>");
		fondosReserva.setValor(cuentaFondoReserva.getSaldoDisponible());
		fondosReserva.setObservacion("Fondos");
		listaIngresos.add(fondosReserva);

		parametrosCredito.put(ParametrosCreditoEnum.DETALLE_INGRESOS, listaIngresos);

		BigDecimal garantiaReal = BigDecimal.ZERO;
		garantiaReal = valorCesantia.add(cuentaFondoReserva.getSaldoDisponible());
		parametrosCredito.put(ParametrosCreditoEnum.TOTAL_GARANTIAS, garantiaReal);

		return parametrosCredito;
	}



	/**
	 * Obtiene los tipos de cargos para la validacion de fondos de reserva
	 * 
	 * @return
	 */
	private List<String> obtenerTiposCargos() {
		final List<String> tipos = new ArrayList<String>();
		tipos.add("PRO");
		tipos.add("REG");
		return tipos;
	}

	/**
	 * Obtiene los estados de solicitud para validacion de fondos de reserva
	 * 
	 * @return
	 */
	private List<String> obtenerEstadosSolicitud() {
		final List<String> estados = new ArrayList<String>();
		estados.add("AFP");
		estados.add("AFT");
		estados.add("ANE");
		estados.add("GUI");
		estados.add("REG");
		estados.add("TRA");
		return estados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#obtenerMontoMaximo(ec.fin.biess.pq.simulacion
	 * .dto.ParametrosCalculoCreditoResponseDto, ec.gov.iess.creditos.modelo.dto.OpcionCredito, boolean,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public Simulacion obtenerMontoMaximo(final ParametrosCalculoCreditoResponseDto parametros, final OpcionCredito opcionSimCuota, final boolean esEmergente,
			final TipoPrestamista tipoPrestamista,final Solicitante solicitante,final Precalificacion precalificacion) throws SimuladorPqException {
		final Simulacion simulacion = new Simulacion();
		simulacion.setCalculoCredito(false);
		opcionSimCuota.setCuotaMensual(BigDecimal.ZERO);

		if (opcionSimCuota.getMeses() == null || opcionSimCuota.getMeses() == 0) {
			throw new SimuladorPqException("Debe ingresar un n\u00FAmero de plazo");
		} else if (opcionSimCuota.getMeses() > this.obtenerPlazoMaximo(parametros.getPlazoMaximo(), esEmergente)) {
			final String observacionMesesMaximo = "El plazo ingresado es mayor al permitido: %d meses";
			throw new SimuladorPqException(String.format(observacionMesesMaximo, this.obtenerPlazoMaximo(parametros.getPlazoMaximo(), esEmergente)));
		} else {
			final BigDecimal cuotaMaximaEndeudamiento = this.recalcularCapacidadEndeudamiento(parametros, esEmergente, tipoPrestamista,
					opcionSimCuota.getMeses());
			new BigDecimal(opcionSimCuota.getMeses());
			try {
				this.obtenerTasaInteres(new BigDecimal(opcionSimCuota.getMeses()), tipoPrestamista, parametros.getEdadActualAnios(),
						esEmergente);
			} catch (final CondicionCalculoException e1) {
				LOG.error("Error al obtener la tasa de interes en monto maximo en simulador: ", e1);
				final String mensajeError = e1.getMessage();
				throw new SimuladorPqException("Error al obtener la tasa de inter\u00E9s en monto m\u00E1ximo: "
						+ mensajeError.replace("ec.fin.biess.exception.ParametrizacionPQException:", ""), e1);
			}
			parametros.getTotalGarantia();
			final String tipoTablaAmortizacion = opcionSimCuota.getTipoTablaSeleccionado();
			try {
				if (tipoTablaAmortizacion != null && !tipoTablaAmortizacion.isEmpty()) {
					montoMaxSimulacion=obtenerMontoMaximoSimulacion(parametros, opcionSimCuota, esEmergente, tipoPrestamista, solicitante, precalificacion, cuotaMaximaEndeudamiento);
					simulacion.setMontoMaximoCredito(montoMaxSimulacion);
					
					simulacion.setPlazoMaximoCredito(this.obtenerPlazoMaximo(parametros.getPlazoMaximo(), esEmergente));
				}
			} catch (final MontosMaximosException e) {
				LOG.error("1. Error al obtener montos maximos en simulador: ", e);
				throw new SimuladorPqException("Error al obtener monto m\u00E1ximo: " + e.getMessage());
			} catch (final MontoMinimoException e) {
				LOG.error("1. Error al obtener montos maximos en simulador: ", e);
				throw new SimuladorPqException("Error al obtener monto mínimo: " + e.getMessage());
			} catch (final ParseException e) {
				LOG.error("1.Parse exception: ", e);
				throw new SimuladorPqException("Error al obtener monto m\u00E1ximo: " + e.getMessage());
			} catch (final TablaAmortizacionSacException e) {
				LOG.error("TablaAmortizacionSacException: ", e);
				throw new SimuladorPqException("Error al simular tabla amortizacion GAF: " + e.getMessage());
			}
		}

		return simulacion;
	}
	private  TablaAmortizacionSac  obtenerAmortizacionFrancesa(final DatosCredito credito,final Integer mesesCalculo,final BigDecimal cuotaMaximaEndeudamiento) throws ParseException, TablaAmortizacionSacException {
		return obtenerAmortizacionTipo(credito, mesesCalculo, TipoTablaEnum.FRANCESA,cuotaMaximaEndeudamiento);
	}
	
	
	private  TablaAmortizacionSac  obtenerAmortizacionTipo(final DatosCredito credito,final Integer mesesCalculo,final TipoTablaEnum tipo,final BigDecimal cuotaMaximaEndeudamiento) throws ParseException, TablaAmortizacionSacException {
		return obtenerAmortizacionTipo(credito, mesesCalculo, tipo, null,cuotaMaximaEndeudamiento);
	}
	private BigDecimal obtenerMontoMaximoSimulacion(final ParametrosCalculoCreditoResponseDto parametros, final OpcionCredito opcionSimCuota, final boolean esEmergente,
			final TipoPrestamista tipoPrestamista,final Solicitante solicitante,final Precalificacion precalificacion,final BigDecimal cuotaMaximaEndeudamiento) throws MontosMaximosException, MontoMinimoException, ParseException, TablaAmortizacionSacException {
		final DataPersonalizacionDto dataPerDto=new DataPersonalizacionDto();
		final DatosCredito datosCredito=new DatosCredito();
		datosCredito.setNombreAsegurado(solicitante.getDatosPersonales().getApellidosNombres());
		datosCredito.setFechaNacimiento(solicitante.getDatosPersonales().getFechaNacimiento());
		datosCredito.setCedulaAfiliado(solicitante.getDatosPersonales().getCedula());
		datosCredito.setTipoPrestamista(tipoPrestamista);
		this.datosCredito=datosCredito;
		final TablaAmortizacionSac tblAmorti = obtenerAmortizacionFrancesa(datosCredito,opcionSimCuota.getMeses(),cuotaMaximaEndeudamiento);
		dataPerDto.setSimulacionSac(tblAmorti);
		dataPerDto.setGarantiaTotal(parametros.getTotalGarantia());
		dataPerDto.setEdad(parametros.getEdadActualAnios());
		dataPerDto.setPlazoMaximo(opcionSimCuota.getMeses());
		dataPerDto.setTipoPrestamista(tipoPrestamista);
		dataPerDto.setTotalSaldosCred(precalificacion.getSumaSaldosCred());
		dataPerDto.setCupoMaxCredito(precalificacion.getGarantia().getCupoMaximoCredito());
		dataPerDto.setEmergente(esEmergente);
		dataPerDto.setTipo(opcionSimCuota.getTipoTablaSeleccionado());
		return personalizarCreditoService.obtenerMontoMaximoPorTipoAmortizacionFran(dataPerDto);
		
	}
	private  TablaAmortizacionSac  obtenerAmortizacionTipo(final DatosCredito credito,final Integer mesesCalculo,final TipoTablaEnum tipo,final BigDecimal monto,final BigDecimal cuotaMaximaEndeudamiento) throws ParseException, TablaAmortizacionSacException {
		final OperacionSacRequest request = new OperacionSacRequest();
		request.setCliente(FuncionesUtilesSac.fabricarCliente(credito));
		final PrestamoRequestDto prestamo = new PrestamoRequestDto();
		if(monto!=null && monto.signum()>0) {
			prestamo.setMonto(monto);
			prestamo.setMontoCuota(BigDecimal.ZERO);
		}else {
			prestamo.setMontoCuota(cuotaMaximaEndeudamiento);
			prestamo.setMonto(BigDecimal.ZERO);
		}
		prestamo.setPlazo(mesesCalculo);
		prestamo.setTipoTablaAmortizacion(FuncionesUtilesSac.obtenerTipoTablaSac(tipo.name()
				));
		final OperacionRequestDto operacion = new OperacionRequestDto();
		//Todos los productos se van ha simular como normal 
		operacion.setTipoProducto("NOR");
		request.setOperacion(operacion);
		request.setPrestamo(prestamo);
	
		return calculoCreditoServicio.obtenerInformacionTablaAmortizacionSAC(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#calcularSimulacionCuota(ec.gov.iess.creditos.
	 * modelo.dto.OpcionCredito, boolean, ec.gov.iess.creditos.enumeracion.TipoPrestamista,
	 * ec.fin.biess.pq.simulacion.dto.ParametrosCalculoCreditoResponseDto)
	 */
	@Override
	public Simulacion calcularSimulacionCuota(final OpcionCredito opcionSimCuota, final boolean esEmergente, final TipoPrestamista tipoPrestamista,
			final ParametrosCalculoCreditoResponseDto parametros, Long operacionSac) throws SimuladorPqException {
		Simulacion simulacion = new Simulacion();
		if (opcionSimCuota.getMeses() == null) {
			throw new SimuladorPqException("Debe ingresar un n\u00FAmero de plazo");
		} else {
			if (!esEmergente && this.validarValorSBU(opcionSimCuota.getValorTotalCredito(), new BigDecimal(opcionSimCuota.getMeses()),
					tipoPrestamista, parametros.getEdadActualAnios())) {
				throw new SimuladorPqException(
						"El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + this.valorMinimoSBU + " del SBU: $" + this.valorSBUMitad);
			}
		}

		// Obtiene la tasa de interes de acuerdo al plazo
		BigDecimal tasaInteres = null;
		try {
			tasaInteres = this.obtenerTasaInteres(new BigDecimal(opcionSimCuota.getMeses()), tipoPrestamista, parametros.getEdadActualAnios(),
					esEmergente);
		} catch (final CondicionCalculoException e) {
			throw new SimuladorPqException(e);
		}

		final String tipoTablaAmortizacion = opcionSimCuota.getTipoTablaSeleccionado();

		final BigDecimal capacidadEndeudamiento = this.recalcularCapacidadEndeudamiento(parametros, esEmergente, tipoPrestamista,
				opcionSimCuota.getMeses());

		opcionSimCuota.setCapacidadEndeudamiento(capacidadEndeudamiento);
		opcionSimCuota.setTasaInteres(tasaInteres);
		opcionSimCuota.setTotalGarantias(parametros.getTotalGarantia());
		opcionSimCuota.setPlazoMaximoCredito(parametros.getPlazoMaximo());
		opcionSimCuota.setEsEmergente(esEmergente);
		opcionSimCuota.setTipoPrestamista(tipoPrestamista);
		opcionSimCuota.setEdadAsegurado(parametros.getEdadActualAnios());

		try {
			if (tipoTablaAmortizacion != null && !tipoTablaAmortizacion.isEmpty()) {
		
				opcionSimCuota.setMontoMaxSac(montoMaxSimulacion);
				opcionSimCuota.setTipoProductoPq(TiposProductosPq.NOR);
				simulacion = this.simularCreditoServicio.simularCreditoSegunMontoIngresadoSac(opcionSimCuota, calculoCreditoServicio, FuncionesUtilesSac.fabricarCliente(datosCredito), operacionSac);
			
			} else {
				throw new SimuladorPqException("Debe Seleccionar un tipo de Amortizaci\u00F3n");
			}
			opcionSimCuota.setCuotaMensual(simulacion.getCuotaCredito());
		} catch (final MontosMaximosException e) {
			throw new SimuladorPqException(e);
		} catch (final ParseException e) {
			throw new SimuladorPqException(e);
		}

		simulacion.setPlazoMaximoCredito(this.obtenerPlazoMaximo(parametros.getPlazoMaximo(), esEmergente));

		return simulacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#aceptarSimulacionCuota(ec.fin.biess.pq.
	 * simulacion.dto.DatosSimulacionCuotaDto)
	 */
	@Override
	public PrestamoCalculo aceptarSimulacionCuota(final DatosSimulacionCuotaMontoDto parametros) throws SimuladorPqException, TablaAmortizacionSacException {
		final TipoPrestamista tipoPrestamista = parametros.getTipoPrestamista();

		PrestamoCalculo prestamoCalculo = new PrestamoCalculo();
		OrigenJubilado origenJubilado = null;
		if (TipoPrestamista.JUBILADO == parametros.getTipoPrestamista()) {
			origenJubilado = OrigenJubilado.HL;
		}

		int idVariedadCredito;
		try {
			idVariedadCredito = CalculoCreditoHelperSingleton.determinarIdVariedadCredito(tipoPrestamista, origenJubilado);
		} catch (final CalculoCreditoException e) {
			throw new SimuladorPqException(e);
		}


		final DatosCredito datosCredito = new DatosCredito();
		datosCredito.setTipoTabla(parametros.getTipoTablaAmortizacion());
		datosCredito.setCuotaMensualMaxima(parametros.getCuotaMensualMaxima());
		datosCredito.setCedulaAfiliado(parametros.getCedula());
		datosCredito.setMonto(parametros.getMontoCredito());
		datosCredito.setMontoMaximo(parametros.getMontoMaximoCredito());
		datosCredito.setPlazo(parametros.getPlazoCredito());
		datosCredito.setFechaSolicitud(new Date());
		datosCredito.setIdTipoCredito(parametros.getTiposProductosPq().getCodigoTipoProducto().intValue());
		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(parametros.getFechaNacimiento());
		datosCredito.setTipoPrestamista(tipoPrestamista);
		if (parametros.isEsNovacion()) {
			datosCredito.setEsNovacion(true);
			datosCredito.setPrestamoAnteriorNovacion(parametros.getPrestamoNovacion());
		}

		final DatosOrdenCompra datosOrdenCompra = new DatosOrdenCompra();
		datosOrdenCompra.setCodigoProducto(parametros.getTiposProductosPq().toString());
		datosCredito.setOrdenCompra(datosOrdenCompra);

		// Requerido para simulacion de tabla de amortizacion del SAC
		datosCredito.setNombreAsegurado(parametros.getNombreAsegurado());
		datosCredito.setTipoPeticionTablaSac(parametros.getTipoPeticionTablaSac());
		datosCredito.setTipoIdentificacionSac(parametros.getTipoIdentificacionSac());
		datosCredito.setProductoCarga("QBI");
		//Se agrega datos para simulacion en el GAF
		
		datosCredito.setNombreAsegurado(parametros.getNombreAsegurado());
		datosCredito.setTipoPeticionTablaSac("V/X");
		datosCredito.setTipoIdentificacionSac("C");
			final boolean esExtranjeroRefugiado = TipoIdentificacionSacEnum.REFUGIADO.name().equals(FuncionesUtilesSac
					.obtenerTipoIdentificacionSac(parametros.getCedula()));
			if (esExtranjeroRefugiado) {
				this.datosCredito.setTipoIdentificacionSac("N");
			}

		try {
			datosCredito.setCreditoEspecial(null);
			if (parametros.isEsEmergente()) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			prestamoCalculo = this.calculoCreditoServicio.calcularCreditoNew(datosCredito, new PrestamoCalculo());
			final BigDecimal totalInteres = this.obtieneTotalInteres(prestamoCalculo, parametros.isEsEmergente());
			prestamoCalculo.setTotalInteres(totalInteres);
		} catch (final CalculoCreditoException e) {
			throw new SimuladorPqException(e);
		} catch (final TablaAmortizacionException e) {
			throw new SimuladorPqException(e);
		}

		return prestamoCalculo;
	}

	/**
	 * Obtiene el valor total del interes recorriendose los dividendos
	 * 
	 * @param prestamoCalculo
	 * @return
	 */
	private BigDecimal obtieneTotalInteres(final PrestamoCalculo prestamoCalculo, final boolean esEmergente) {
		BigDecimal totalInteres = BigDecimal.ZERO;
		BigDecimal totalInteresGracia = BigDecimal.ZERO;
		if (prestamoCalculo.getDividendoCalculoList() != null && !prestamoCalculo.getDividendoCalculoList().isEmpty()) {
			for (final DividendoCalculo dividendoCalculo : prestamoCalculo.getDividendoCalculoList()) {
				totalInteres = totalInteres.add(dividendoCalculo.getValorInteres());
				totalInteresGracia = totalInteresGracia.add(dividendoCalculo.getValorIntPerGra());
			}
			if (esEmergente) {
				totalInteres = totalInteres.add(totalInteresGracia);
			}
		}
		totalInteres = totalInteres.add(totalInteresGracia);
		if (prestamoCalculo.getPeriodoGraciaInterZafra() != null) {
			totalInteres = totalInteres.add(prestamoCalculo.getPeriodoGraciaInterZafra().getValor() == null ? BigDecimal.ZERO
					: prestamoCalculo.getPeriodoGraciaInterZafra().getValor());
		}

		return totalInteres;
	}

	/**
	 * Devuelve la tasa de interes dado el plazo
	 * 
	 * @param plazo
	 *            Es el plazo en meses
	 * @param tipoPrestamista
	 *            Es el rol del prestamista
	 * @param edad
	 *            Edad del asegurado
	 * @param esEmergente
	 *            Indicador si es credito emergente
	 * @return
	 * @throws CondicionCalculoException
	 */
	private BigDecimal obtenerTasaInteres(final BigDecimal plazo, final TipoPrestamista tipoPrestamista, final int edad, final boolean esEmergente)
			throws CondicionCalculoException {
		String tipoProducto = TipoProductoEnum.NORMAL.getDescripcion();
		if (TipoPrestamista.JUBILADO == tipoPrestamista) {
			tipoProducto = TipoProductoEnum.JUB_PEN.getDescripcion();
		} else {
			if (esEmergente) {
				tipoProducto = TipoProductoEnum.EMERGENTE.toString();
			}
		}
		final BigDecimal resp = this.condicionCalculoServicio.obtenerTasaInteresPorPlazo(plazo, tipoProducto, edad);
		return resp;
	}

	/**
	 * Realiza el realculo de la capacidad de endeudamiento ya que ahora calcula con el plazo ingresado
	 * 
	 * @param parametros
	 * @param esEmergente
	 * @param tipoPrestamista
	 * @param plazo
	 * @return
	 * @throws SimuladorPqException
	 */
	private BigDecimal recalcularCapacidadEndeudamiento(final ParametrosCalculoCreditoResponseDto parametros, final boolean esEmergente,
			final TipoPrestamista tipoPrestamista, final int plazo) throws SimuladorPqException {
		BigDecimal capacidadEndeudamiento = BigDecimal.ZERO;
		BigDecimal capacidadPago = BigDecimal.ZERO;
		try {
			capacidadPago = this.garantiaCreditoServicio.obtenerPorcentajeComprometimientoPQ(esEmergente, tipoPrestamista,
					parametros.getEdadActualAnios(), new BigDecimal(plazo));
		} catch (final ParametroBiessException e) {
			LOG.error("Error al obtener el porcentaje de capacidad de pago", e);
			throw new SimuladorPqException(e);
		} catch (final ParametrizacionPQException e) {
			LOG.error("Error al obtener el porcentaje de capacidad de pago", e);
			throw new SimuladorPqException(e);
		}
		final BigDecimal sueldoPromedio = parametros.getSueldoPromedio();
		final BigDecimal respCapacidadPago = sueldoPromedio.multiply(capacidadPago).divide(new BigDecimal(100), RoundingMode.HALF_UP);
		BigDecimal egresos = BigDecimal.ZERO;
		if (parametros.getListaDetalleEgresos() != null && !parametros.getListaDetalleEgresos().isEmpty()) {
			for (final DetalleEgresosDto detalle : parametros.getListaDetalleEgresos()) {
				if (detalle != null) {
					egresos = egresos.add(detalle.getValor() == null ? BigDecimal.ZERO : detalle.getValor());
				}
			}
		}
		// Se suman los ingresos porque viene con valor negativo
		capacidadEndeudamiento = respCapacidadPago.add(egresos);

		return capacidadEndeudamiento;
	}

	/**
	 * Metodo que valida que el monto sea mayor o igual al 50% del SBU
	 * 
	 * @param monto
	 * @param plazo
	 * @param tipoPrestamista
	 * @param edad
	 * @return Devuelve verdadero cuando el monto es menor al 50% del SBU, y falso en caso contrario
	 * @throws SimuladorPqException
	 */
	private boolean validarValorSBU(final BigDecimal monto, final BigDecimal plazo, final TipoPrestamista tipoPrestamista, final int edad) throws SimuladorPqException {
		boolean resp = false;
		BigDecimal valorSBU = null;

		try {
			ParametrizacionPQ parametrizacionPQ = null;
			if (TipoPrestamista.JUBILADO == tipoPrestamista) {
				parametrizacionPQ = this.parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProductoEdad(plazo, "A",
						TipoProductoEnum.JUB_PEN.getDescripcion(), new BigDecimal(edad));
			} else {
				parametrizacionPQ = this.parametrizacionPQServicio.obtenerListaParametrosRangoEstadoProducto(plazo, "A",
						TipoProductoEnum.NORMAL.getDescripcion());
			}

			this.valorMinimoSBU = parametrizacionPQ.getMontoMinimoSBU();
			if (this.valorMinimoSBU == null) {
				throw new SimuladorPqException("No existe parametrizado el valor de monto m\u00EDnimo del cr\u00E9dito.");
			}
			valorSBU = this.parametroCreditoServicio.obtenerValorSBU();
			this.valorSBUMitad = valorSBU.multiply(this.valorMinimoSBU);
		} catch (final ParametrizacionPQException e) {
			LOG.error("Error al obtener el parametro para monto minimo", e);
			return true;
		} catch (final ParametroCreditoException e) {
			LOG.error("Error al obtener el valor del SBU ", e);
			return true;
		}

		if (monto.compareTo(this.valorSBUMitad) < 0) {
			resp = true;
		}

		return resp;
	}

	/**
	 * Obtiene el plazo maximo dado el plazo del credito y la validacion de emergente
	 * 
	 * @param plazoMaximoCredito
	 * @param esEmergente
	 * @return
	 */
	private int obtenerPlazoMaximo(final int plazoMaximoCredito, final boolean esEmergente) {
		int plazoMaximoResp = plazoMaximoCredito;
		if (esEmergente) {
			plazoMaximoResp = this.plazoMaximoEmergente.intValue();
		}

		return plazoMaximoResp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#calcularSimulacionMonto(ec.gov.iess.creditos.
	 * modelo.dto.OpcionCredito, boolean, ec.gov.iess.creditos.enumeracion.TipoPrestamista,
	 * ec.fin.biess.pq.simulacion.dto.ParametrosCalculoCreditoResponseDto)
	 */
	@Override
	public Simulacion calcularSimulacionMonto(final OpcionCredito opcionSimMonto, final boolean esEmergente, final TipoPrestamista tipoPrestamista,
			final ParametrosCalculoCreditoResponseDto parametros) throws SimuladorPqException {
		Simulacion simulacion = new Simulacion();
		if (opcionSimMonto.getMeses() == null) {
			throw new SimuladorPqException("Debe ingresar un n\u00FAmero de plazo");
		} else {
			final String tipoTablaAmortizacion = opcionSimMonto.getTipoTablaSeleccionado();
			// Obtiene la tasa de interes de acuerdo al plazo
			BigDecimal tasaInteres = null;
			try {
				tasaInteres = this.obtenerTasaInteres(new BigDecimal(opcionSimMonto.getMeses()), tipoPrestamista, parametros.getEdadActualAnios(),
						esEmergente);
			} catch (final CondicionCalculoException e) {
				throw new SimuladorPqException(e);
			}

			final BigDecimal capacidadEndeudamiento = this.recalcularCapacidadEndeudamiento(parametros, esEmergente, tipoPrestamista,
					opcionSimMonto.getMeses());

			opcionSimMonto.setCapacidadEndeudamiento(capacidadEndeudamiento);
			opcionSimMonto.setTasaInteres(tasaInteres);
			opcionSimMonto.setTotalGarantias(parametros.getTotalGarantia());
			opcionSimMonto.setPlazoMaximoCredito(parametros.getPlazoMaximo());
			opcionSimMonto.setEsEmergente(esEmergente);
			opcionSimMonto.setTipoPrestamista(tipoPrestamista);
			opcionSimMonto.setEdadAsegurado(parametros.getEdadActualAnios());

			try {
				if (tipoTablaAmortizacion != null && !tipoTablaAmortizacion.isEmpty()) {
					simulacion = this.simularCreditoServicio.simularCreditoSegunCuotaIngresada(opcionSimMonto);

					BigDecimal cuotaMaximaComprometer = BigDecimal.ZERO;
					cuotaMaximaComprometer = this.simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(parametros.getTotalGarantia(), tasaInteres,
							opcionSimMonto.getMeses(), tipoTablaAmortizacion);
					simulacion.setCuotaMaximaComprometer(cuotaMaximaComprometer);
					simulacion.setPlazoMaximoCredito(parametros.getPlazoMaximo());
				} else {
					throw new SimuladorPqException("Debe Seleccionar un tipo de Amortizacion");
				}
				opcionSimMonto.setValorTotalCredito(simulacion.getMontoCredito());

				if (!esEmergente && this.validarValorSBU(opcionSimMonto.getValorTotalCredito(), new BigDecimal(opcionSimMonto.getMeses()),
						tipoPrestamista, parametros.getEdadActualAnios())) {
					throw new SimuladorPqException(
							"El monto m\u00EDnimo del cr\u00E9dito debe ser al menos el " + this.valorMinimoSBU + " del SBU: $" + this.valorSBUMitad);
				}

			} catch (final MontosMaximosException e) {
				throw new SimuladorPqException(e);
			}

		}

		return simulacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#aceptarSimulacionMonto(ec.fin.biess.pq.
	 * simulacion.dto.DatosSimulacionCuotaMontoDto)
	 */
	@Override
	public PrestamoCalculo aceptarSimulacionMonto(final DatosSimulacionCuotaMontoDto parametros) throws SimuladorPqException, TablaAmortizacionSacException {
		PrestamoCalculo prestamoCalculo = new PrestamoCalculo();
		final TipoPrestamista tipoPrestamista = parametros.getTipoPrestamista();

		OrigenJubilado origenJubilado = null;
		if (TipoPrestamista.JUBILADO == parametros.getTipoPrestamista()) {
			origenJubilado = OrigenJubilado.HL;
		}

		int idVariedadCredito;
		try {
			idVariedadCredito = CalculoCreditoHelperSingleton.determinarIdVariedadCredito(tipoPrestamista, origenJubilado);
		} catch (final CalculoCreditoException e) {
			throw new SimuladorPqException(e);
		}

		// Obtiene la tasa de interes de acuerdo al plazo
		BigDecimal tasaInteres = null;
		try {
			tasaInteres = this.obtenerTasaInteres(new BigDecimal(parametros.getPlazoCredito()), tipoPrestamista, parametros.getEdadAsegurado(),
					parametros.isEsEmergente());
		} catch (final CondicionCalculoException e) {
			throw new SimuladorPqException(e);
		}

		final DatosCredito datosCredito = new DatosCredito();
		datosCredito.setTipoTabla(parametros.getTipoTablaAmortizacion());
		datosCredito.setCuotaMensualMaxima(parametros.getCuotaMensualMaxima());
		datosCredito.setCedulaAfiliado(parametros.getCedula());
		datosCredito.setTasaInteres(tasaInteres);
		datosCredito.setMonto(parametros.getMontoCredito());
		datosCredito.setPlazo(parametros.getPlazoCredito());
		datosCredito.setFechaSolicitud(new Date());
		datosCredito.setIdTipoCredito(parametros.getTiposProductosPq().getCodigoTipoProducto().intValue());
		datosCredito.setIdVariedadcredito(idVariedadCredito);
		datosCredito.setFechaNacimiento(parametros.getFechaNacimiento());
		datosCredito.setTipoPrestamista(tipoPrestamista);
		if (parametros.isEsNovacion()) {
			datosCredito.setEsNovacion(true);
			datosCredito.setPrestamoAnteriorNovacion(parametros.getPrestamoNovacion());
		}

		final DatosOrdenCompra datosOrdenCompra = new DatosOrdenCompra();
		datosOrdenCompra.setCodigoProducto(parametros.getTiposProductosPq().toString());
		datosCredito.setOrdenCompra(datosOrdenCompra);

		try {
			datosCredito.setCreditoEspecial(null);
			if (parametros.isEsEmergente()) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();
			prestamoCalculoAux = this.prestamoCalculoService.poblarPrestamoCalculoNew(datosCredito);

			prestamoCalculo = this.calculoCreditoServicio.calcularCreditoNew(datosCredito, prestamoCalculoAux);
			prestamoCalculo.setTasaInteres(tasaInteres);
			final BigDecimal totalInteres = this.obtieneTotalInteres(prestamoCalculo, parametros.isEsEmergente());
			prestamoCalculo.setTotalInteres(totalInteres);
		} catch (final CalculoCreditoException e) {
			throw new SimuladorPqException(e);
		} catch (final TablaAmortizacionException e) {
			throw new SimuladorPqException(e);
		}

		return prestamoCalculo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.pq.simulacion.service.SimulacionCreditoPqServicioLocal#obtenerCuotaMaxima(ec.gov.iess.creditos.
	 * modelo.dto.OpcionCredito, ec.gov.iess.creditos.enumeracion.TipoPrestamista, int, boolean, int,
	 * java.math.BigDecimal)
	 */
	@Override
	public Simulacion obtenerCuotaMaxima(final OpcionCredito opcionSimMonto, final TipoPrestamista tipoPrestamista, final int edadAsegurado, final boolean esEmergente,
			final int plazoMaximo, final BigDecimal totalGarantia) throws SimuladorPqException {
		final Simulacion simulacion = new Simulacion();
		simulacion.setCalculoCredito(false);
		opcionSimMonto.setValorTotalCredito(BigDecimal.ZERO);

		if (opcionSimMonto.getMeses() == null || opcionSimMonto.getMeses().equals(0)) {
			throw new SimuladorPqException("Debe ingresar un n\u00FAmero de plazo");
		} else if (opcionSimMonto.getMeses() > this.obtenerPlazoMaximo(plazoMaximo, esEmergente)) {
			final String observacionMesesMaximo = "El plazo ingresado es mayor al permitido: %d meses";
			throw new SimuladorPqException(String.format(observacionMesesMaximo, this.obtenerPlazoMaximo(plazoMaximo, esEmergente)));
		} else {
			BigDecimal tasaInteres = null;
			try {
				tasaInteres = this.obtenerTasaInteres(new BigDecimal(opcionSimMonto.getMeses()), tipoPrestamista, edadAsegurado, esEmergente);
			} catch (final CondicionCalculoException e1) {
				LOG.error("Error al obtener la tasa de interes en monto maximo en simulador: ", e1);
				final String mensajeError = e1.getMessage();
				throw new SimuladorPqException("Error al obtener la tasa de inter\u00E9s en monto m\u00E1ximo: "
						+ mensajeError.replace("ec.fin.biess.exception.ParametrizacionPQException:", ""), e1);
			}

			final int numeroMeses = opcionSimMonto.getMeses();
			final String tipoTablaAmortizacion = opcionSimMonto.getTipoTablaSeleccionado();
			if (tipoTablaAmortizacion != null && !tipoTablaAmortizacion.isEmpty()) {
				simulacion.setCuotaMaximaComprometer(
						this.simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(totalGarantia, tasaInteres, numeroMeses, tipoTablaAmortizacion));
				simulacion.setPlazoMaximoCredito(this.obtenerPlazoMaximo(plazoMaximo, esEmergente));
			}
		}

		return simulacion;
	}

	/**
	 * Llena el mapa de variedad credito
	 */
	private void setMapVariedadCredito() {
		mapVariedadCredito = new HashMap<Integer, TipoRol>();
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_AFILIADO.getIdVariedad(), TipoRol.AF);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_ZAFRERO_TEMPORAL.getIdVariedad(), TipoRol.AF);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_JUBILADO_HL.getIdVariedad(), TipoRol.JU);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_JUBILADO_HOST_GYE.getIdVariedad(), TipoRol.JU);
		mapVariedadCredito.put(VariedadCreditoEnum.CREDITO_JUBILADO_HOST_UIO.getIdVariedad(), TipoRol.JU);
	}

	/**
	 * Permite obtener las garantias desde el core SAC
	 * 
	 * @param identificacion
	 * @return
	 * @throws GarantiaException
	 */
	private InformacionGarantias obtenerGarantiasPQSAC(final String identificacion) throws GarantiaException {
		try {
			return garantiasSacServicio.obtenerGarantias(identificacion);
		} catch (final GarantiasSacException e) {
			LOG.error("Se presento un error al obtener informacion de garantias", e);
			throw new GarantiaException(e.getMessage());
		}

	} 

	@Override
	public  List<BigDecimal> validacionMontoMinimo(BigDecimal monto, BigDecimal plazo, TipoPrestamista tipoPrestamista,
			int edad) throws SimuladorPqException {
	List<BigDecimal> lista=new ArrayList<BigDecimal>();
		if(validarValorSBU(monto, plazo, tipoPrestamista, edad)) {
			lista.add(this.valorMinimoSBU);
			lista.add(this.valorSBUMitad);
			return lista;
		}else {
			  return null; 
		}
		
	
	} 

}
