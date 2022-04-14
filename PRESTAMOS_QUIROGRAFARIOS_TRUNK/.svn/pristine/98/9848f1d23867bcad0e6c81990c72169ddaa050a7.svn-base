/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dao.DetalleCatalogosDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoTablaReferencia;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.CalculoCredito;
import ec.gov.iess.creditos.modelo.dto.CondicionCalculo;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.SeguroSaldo;
import ec.gov.iess.creditos.modelo.dto.TablaReferenciaCredito;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;
import ec.gov.iess.creditos.modelo.persistencia.pk.DetalleCatalogosPK;
import ec.gov.iess.creditos.pq.dto.DividendoDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.PrestamoRequestDto;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.pq.excepcion.CondicionCalculoException;
import ec.gov.iess.creditos.pq.excepcion.SeguroSaldoException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.excepcion.TablaReferenciaException;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicioRemote;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio;
import ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionTablaAmortizacionSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.TablaReferenciaCreditoServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.CalculoPeriodoGracia;
import ec.gov.iess.creditos.pq.util.CalculoValoresCredito;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * <b> Permite realizar los cálculos para la concesión y simulación del crédito.
 * </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.11.2.1 $
 *          <p>
 *          [$Author: cbastidas $, $Date: 2011/05/24 13:54:36 $]
 *          </p>
 */
@Stateless
public class CalculoCreditoServicioImpl implements CalculoCreditoServicio,
		CalculoCreditoServicioRemote {

	private final LoggerBiess log = LoggerBiess.getLogger(CalculoCreditoServicioImpl.class);

	@EJB
	CondicionCalculoServicio condicionCalculoServicio;

	@EJB
	private TablaReferenciaCreditoServicio tablaReferenciaCreditoServicio;
	
	@EJB
	private SeguroSaldoCreditoServicio seguroSaldoCreditoServicio;


	// cambios 18/10/2011
	@EJB
	private DetalleCatalogosDao detalleCatalogosDao;
	

	@EJB
	private ParametroBiessDao parametroBiessDao;

	@EJB
	private SimulacionTablaAmortizacionSacServicioLocal tablaAmortizacionCuotaPlazoSacServicio;
	

	// fin cambios

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CalculoCredito obtenerCalculoCredito(final DatosCredito credito,
			final BigDecimal sumaSaldosVigente, final TipoPrestamista tipoPrestamista) throws CalculoCreditoException {
		final CalculoCredito calculoCredito = new CalculoCredito();
		try {
			// Cálculos de la cabecera del crédito
			// Nuevas tasas de cálculo de interés de concesión del BIESS
			final CondicionCalculo condicionCalculo = condicionCalculoServicio
					.poblarCondicionCalculo(credito.getGenero(),
							credito.getFechaNacimiento(),
							credito.getFechaSolicitud(),
							credito.getParNumeroSemanas(),
							tipoPrestamista);
			calculoCredito.setCondicionCalculo(condicionCalculo);

			// garantia
			/*
			 * Garantia garantia = garantiaCreditoServicio.getGarantia(credito
			 * .getGarantia());
			 */
			final Garantia garantia = credito.getGarantiaReal();
			calculoCredito.setGarantia(garantia);

			// tablaReferencial
			final TablaReferenciaCredito tablaReferenciaCredito = new TablaReferenciaCredito();
			// mejor opcion
			final OpcionCredito mejorOpcion = tablaReferenciaCreditoServicio
					.determinarMejorOpcioncredito();
			tablaReferenciaCredito.setMejorOpcionCredito(mejorOpcion);
			// plazos sin doc. fid.
			int tipoTablaReferencia = TipoTablaReferencia.SIN_DOCUMENTO_FIDUCIARIO
					.getValor();
			credito.setParTipoTablaReferencia(tipoTablaReferencia);
			credito.setTasaInteres(condicionCalculo.getTasaInteres());
			credito.setPorcentajeSeguroDesgravamen(condicionCalculo
					.getPorcentajeSeguroDesgravamen());

			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(credito.getGarantia()
					.getTipoPrestamista())) {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_ZAFREROS);
				credito.setPlazoMaximoMeses(ConstantesCreditos.PLAZO_MAXIMO_ZAFREROS);
			} else {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_PRODUCTO_NORMAL);
				credito.setPlazoMaximoMeses(condicionCalculo.getPlazoMaximo());
			}
			// Plazo máximo es de acuerdo al producto seleccionado
			
			// INC-2272 Pensiones Alimenticias
			/*if (TiposProductosPq.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
					credito.getOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()) == CategoriaTipoProductoPq.CAT_BIENES) {
				/*
				 * credito .setPlazoMaximoPrestamo(ConstantesCreditos. PLAZO_MAXIMO_PRODUCTO_COMPUTADORAS); credito
				 * .setPlazoMaximoMeses (ConstantesCreditos.PLAZO_MAXIMO_PRODUCTO_COMPUTADORAS);
				 */

				/*
				 * SE QUEMA EL PLAZO MAXIMO DE COMPUTADORAS POR REQUERIMIENTO URGENTE***
				 */

				//credito.setPlazoMaximoPrestamo(60);
				//credito.setPlazoMaximoMeses(60);

			//}*/
			/*
			if (credito.getOrdenCompra().getCodigoProducto() == TiposProductosPq.TUR
					.toString()) {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_PRODUCTO_TURISTICO);
				credito.setPlazoMaximoMeses(ConstantesCreditos.PLAZO_MAXIMO_PRODUCTO_TURISTICO);
			}*/

			// Cambiar el plazo para enfermo terminal
			if (credito.isEsEnfermoTerminal()) {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_ENFERMO_TERMINAL);
				credito.setPlazoMaximoMeses(ConstantesCreditos.PLAZO_MAXIMO_ENFERMO_TERMINAL);
			}
			// fin cambio

			final int idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(credito.getGarantia()
							.getTipoPrestamista(), credito.getOrigenJubilado());
			credito.setIdVariedadcredito(idVariedadCredito);
			credito.setCalculoCredito(calculoCredito);

			// TABLA DE REFERENCIA
			List<PlazoCredito> listaSD = null;

			listaSD = tablaReferenciaCreditoServicio.determinarTablaReferencia(
					credito, sumaSaldosVigente);

			credito.setListaPlazoCredito(listaSD);
			// Calculo del plazo maximo y monto maximo para los nuevos productos
			// INC-2272 Pensiones Alimenticias
			if (TiposProductosPq.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
					credito.getOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()) == CategoriaTipoProductoPq.CAT_BIENES) {
				calculoPlazoMontoMaximo(credito);
				tablaReferenciaCredito.setPlazoCreditoSinDocumentoFiduciario(credito.getListaPlazoCredito());
			} else {
				tablaReferenciaCredito.setPlazoCreditoSinDocumentoFiduciario(listaSD);
			}

			// plazos con doc. fid.
			tipoTablaReferencia = TipoTablaReferencia.CON_DOCUMENTO_FIDUCIARIO
					.getValor();
			tablaReferenciaCredito.setPlazoCreditoConDocumentoFiduciario(null);

			calculoCredito.setTablaReferenciaCredito(tablaReferenciaCredito);

			// CB 11/12/2010 Aumento la cuota de Hipotecario
			/*
			 * BigDecimal cuotaSolicitudHipotecario = prestamoDao
			 * .cuotaPrestamoSolicitudHipotecario(credito .getCedulaAfiliado());
			 */
			/* BigDecimal sueldoPromedio = new BigDecimal(0); */
			BigDecimal porcentajeComprometido = BigDecimal.ZERO;
			// BigDecimal valorDisponible = new BigDecimal(0);
			BigDecimal valorMaximoComprometer = BigDecimal.ZERO;
			// BigDecimal cuotaHipotecario = new BigDecimal(0);
			// CB 27/12/2010 Se aumenta el valor de la liquidacion por Novación
			// cuando tiene solicitud de Hipotecarios
			BigDecimal valorLiquidacionNovacion = BigDecimal.ZERO;
			if (credito.isEsNovacion()) {
				valorLiquidacionNovacion = calculoCredito
						.getTablaReferenciaCredito()
						.getPlazoCreditoSinDocumentoFiduciario().get(0)
						.getValorLiquidacionNovacion();
			}
			calculoCredito.setLiquidacionNovacion(valorLiquidacionNovacion);

			// sueldoPromedio = calculoCredito.getGarantia().getSueloPromedio();
			porcentajeComprometido = new BigDecimal(
					ConstantesCreditos.PORCENTAJE_COMPROMETIMIENTO_PH);
			calculoCredito.setPorcentajeHipotecario(porcentajeComprometido);

			// GE: resto el valor de Buro, solo en caso de Jubilado el valor de
			// la cuota sera > 0
			// BigDecimal cuotaBuroCredito = credito.getCuotaMensualBuro();
			// BigDecimal valorNeto = sueldoPromedio.subtract(cuotaBuroCredito);

			// valorDisponible =
			// valorNeto.multiply(porcentajeComprometido.divide(new
			// BigDecimal(100)));
			// calculoCredito.setDisponibleHipotecario(valorDisponible);

			/*
			 * if (cuotaSolicitudHipotecario == null) {
			 * cuotaSolicitudHipotecario = new BigDecimal(0); }
			 */

			/*
			 * if (cuotaSolicitudHipotecario.floatValue() > 0) {
			 * valorMaximoComprometer = valorDisponible
			 * .subtract(cuotaSolicitudHipotecario); valorMaximoComprometer =
			 * valorMaximoComprometer.setScale(2, BigDecimal.ROUND_HALF_UP); }
			 * else { cuotaHipotecario =
			 * prestamoDao.cuotaPrestamoHipotecario(credito
			 * .getCedulaAfiliado()); if (cuotaHipotecario == null) {
			 * cuotaHipotecario = new BigDecimal(0); } if
			 * (cuotaHipotecario.floatValue() > 0) { valorMaximoComprometer =
			 * valorDisponible .subtract(cuotaHipotecario);
			 * valorMaximoComprometer = valorMaximoComprometer.setScale(2,
			 * BigDecimal.ROUND_HALF_UP); } }
			 */
			/*
			 * calculoCredito
			 * .setCuotaSolicitudHipotecario(cuotaSolicitudHipotecario);
			 */
			calculoCredito.setDisponibleHipotecario(calculoCredito
					.getGarantia().getCapacidadPago());
			calculoCredito.setCuotaHipotecario(calculoCredito.getGarantia()
					.getCuotaHipotecarios());
			valorMaximoComprometer = calculoCredito.getGarantia()
					.getSueloPromedio();
			valorMaximoComprometer = valorMaximoComprometer.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			calculoCredito
					.setMaximoComprometerHipotecario(valorMaximoComprometer);

			if (calculoCredito.getCuotaHipotecario().floatValue() > 0) {
				calculoCredito.setTienePH(true);
			} else {
				calculoCredito.setTienePH(false);
			}

			// Se agregan los datos de la Orden de compra
			// INC-2272 Pensiones Alimenticias
			if (TiposProductosPq.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
					credito.getOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()) == CategoriaTipoProductoPq.CAT_BIENES) {
				calculoCredito.setOrdenCompra(credito.getOrdenCompra());
			}

			// Se valida si le alcanza la capacidad de endudamiento
			if (UtilAjusteCalculo.ajusteNumberBaseDatos(
					calculoCredito.getGarantia().getSueloPromedio())
					.doubleValue() <= 0) {
				calculoCredito.setTieneCapacidadEndeudamiento(false);
			} else {
				calculoCredito.setTieneCapacidadEndeudamiento(true);
			}

			credito.setCalculoCredito(calculoCredito);
			return calculoCredito;

		} catch (final CondicionCalculoException e) {
			log.error("error al obtener condiciones de calculo: " + e);
			throw new CalculoCreditoException(e.getMessage());
		} catch (final TablaReferenciaException e) {
			log.error("error al obtener tabla de referencia sin doc fid: " + e);
			throw new CalculoCreditoException(e);
		}
	}

	/**
	 * 
	 * <b> Calculo del plazo y monto maximo para nuevos productos. </b>
	 * <p>
	 * [Author: cbastidas, Date: 13/04/2011]
	 * </p>
	 * 
	 * @param credito
	 *            : Objeto crédito
	 */
	public void calculoPlazoMontoMaximo(final DatosCredito credito) {
		BigDecimal montoMaximoProducto = BigDecimal.ZERO;
		BigDecimal montoMinimoProducto = BigDecimal.ZERO;
		int plazoMaximoProducto = 0;
		int plazoMinimoProducto = 0;

		// Excluyo plazos que no cumplen validacion

		final List<PlazoCredito> listaNuevaSD = new ArrayList<PlazoCredito>();

		for (final PlazoCredito plazoCredito : credito.getListaPlazoCredito()) {
			if (plazoCredito.isCumpleMonto()) {
				listaNuevaSD.add(plazoCredito);
			}
		}
		credito.setListaPlazoCredito(listaNuevaSD);
		// Calcula monto maximo del producto
		for (final PlazoCredito plazoCredito : credito.getListaPlazoCredito()) {
			if (plazoCredito.isCumpleMonto()) {
				if (montoMaximoProducto.floatValue() < plazoCredito
						.getValorMaximoCredito().floatValue()) {
					montoMaximoProducto = plazoCredito.getValorMaximoCredito()
							.setScale(2);
					plazoMaximoProducto = plazoCredito.getPlazoMaximoOrden();
				}
			}
		}
		// Calcula monto y plazo minimo
		for (final PlazoCredito plazoCredito : credito.getListaPlazoCredito()) {
			if (plazoCredito.isCumpleMonto()) {
				if (montoMaximoProducto.floatValue() > plazoCredito
						.getValorMaximoCredito().floatValue()) {
					montoMinimoProducto = plazoCredito.getValorMaximoCredito()
							.setScale(2);
					plazoMinimoProducto = plazoCredito.getPlazoMaximoOrden();
				}
			}
		}

		credito.getCalculoCredito().setMontoMaximoProducto(montoMaximoProducto);
		credito.getCalculoCredito().setPlazoMaximoProducto(plazoMaximoProducto);
		credito.getCalculoCredito().setMontoMinimoProducto(montoMinimoProducto);
		credito.getCalculoCredito().setPlazoMinimomoProducto(
				plazoMinimoProducto);
	}

	@Override
	public PrestamoCalculo calculoarCredito(final DatosCredito datosCredito)
			throws CalculoCreditoException {
		
		int plazo = datosCredito.getPlazo();
		if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(datosCredito.getCreditoEspecial())) {
			try {
				plazo += obtenerMesesGracia().intValue();
			} catch (final SeguroSaldoException e) {
				throw new CalculoCreditoException("Error al calcular meses de gracia para prestamos emergentes", e);
			}
		}

		final CalculoValoresCredito calculoValoresCredito = new CalculoValoresCredito();
		final PrestamoCalculo prestamoCalculo = calculoValoresCredito
				.poblarPrestamoCalculo(datosCredito);

		int plazoNuevoZafrero = 0;
		try {
			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datosCredito
					.getTipoPrestamista())) {
				if (datosCredito.getPlazo() > 6) {
					plazoNuevoZafrero = datosCredito.getPlazo() + 6;
				} else {
					plazoNuevoZafrero = datosCredito.getPlazo();
				}
				// INC-2272 Pensiones Alimenticias
				if (TiposProductosPq.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
						datosCredito.getOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()) == CategoriaTipoProductoPq.CAT_BIENES) {
					prestamoCalculo
							.setSeguroSaldo(this.seguroSaldoCreditoServicio.calculoSeguroSaldo(
									datosCredito.getIdTipoCredito(),
									datosCredito.getIdVariedadcredito(),
									datosCredito.getFechaNacimiento(),
									datosCredito.getFechaSolicitud(),
									plazoNuevoZafrero,
									datosCredito
											.getMonto()
											.subtract(
													datosCredito
															.getValorSeguroSaldosOrden())));
				} else {
					prestamoCalculo
							.setSeguroSaldo(this.seguroSaldoCreditoServicio.calculoSeguroSaldo(
									datosCredito.getIdTipoCredito(),
									datosCredito.getIdVariedadcredito(),
									datosCredito.getFechaNacimiento(),
									datosCredito.getFechaSolicitud(),
									plazoNuevoZafrero, datosCredito.getMonto()));
				}

			} else if (TiposProductosPq.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
					datosCredito.getOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()) == CategoriaTipoProductoPq.CAT_BIENES) {
				prestamoCalculo.setSeguroSaldo(this.seguroSaldoCreditoServicio
						.calculoSeguroSaldo(
								datosCredito.getIdTipoCredito(),
								datosCredito.getIdVariedadcredito(),
								datosCredito.getFechaNacimiento(),
								datosCredito.getFechaSolicitud(),
								plazo,
								datosCredito.getMonto().subtract(
										datosCredito
												.getValorSeguroSaldosOrden())));
			} else {
				prestamoCalculo.setSeguroSaldo(this.seguroSaldoCreditoServicio
						.calculoSeguroSaldo(datosCredito.getIdTipoCredito(),
								datosCredito.getIdVariedadcredito(),
								datosCredito.getFechaNacimiento(),
								datosCredito.getFechaSolicitud(),
								plazo,
								datosCredito.getMonto()));
			}

		} catch (final SeguroSaldoException e) {
			this.log.error(" Determinar seguro de saldo :", e);
			throw new CalculoCreditoException(1, null);
		}
		if (datosCredito.isEsNovacion()) {
		
				prestamoCalculo
	.setMontoCanceladoNovacion(BigDecimal.valueOf(datosCredito.getPrestamoAnteriorNovacion().getValliqnov()));
				

			prestamoCalculo.setLiquidoPagar(datosCredito.getMonto().add(
					prestamoCalculo.getSeguroSaldo().getValor()
							.add(prestamoCalculo.getMontoCanceladoNovacion())
							.negate()));

			if (prestamoCalculo.getLiquidoPagar().doubleValue() <= 0.0D) {
				this.log.error(" Valor liquido a pagar negativo:"
						+ prestamoCalculo.getLiquidoPagar().toString());

				throw new CalculoCreditoException(
						3,
						prestamoCalculo.getLiquidoPagar().setScale(2, 0)
								+ " (Monto - liquidación PQ Anterior - Seguro de Saldos). SU VALOR DE GARANTIAS O SU CAPACIDAD DE ENDEUDAMIENTO NO CUBRE EL SALDO PENDIENTE DEL PQ ANTERIOR");
			}

		} else {
			prestamoCalculo.setLiquidoPagar(datosCredito.getMonto().add(
					prestamoCalculo.getSeguroSaldo().getValor().negate()));
		}

		this.log.debug(" Liquido pagar : " + prestamoCalculo.getLiquidoPagar());
		return prestamoCalculo;
	}
	

	
	@Override
	public PrestamoCalculo calcularCreditoNew(final DatosCredito datosCredito, final PrestamoCalculo prestamoCalculo)
			throws CalculoCreditoException, TablaAmortizacionException, TablaAmortizacionSacException {
		
		prestamoCalculo.setPlazoMeses(datosCredito.getPlazo());
		prestamoCalculo.setValorCredito(UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2));
		prestamoCalculo.setValorTotalDividendoMensual(datosCredito.getCuotaMensualMaxima());
		prestamoCalculo.setCedula(datosCredito.getCedulaAfiliado());
		prestamoCalculo.setMontoPrestamo(UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2));
		datosCredito.setPrestamoCalculo(prestamoCalculo);
		
			
			BigDecimal montoCreditoInteresGracia = datosCredito.getMonto();
		        final TablaAmortizacionSac informacionSAC = obtenerInformacionTablaAmortizacionSAC(datosCredito);			
			prestamoCalculo.setDividendoCalculoList(obtenerListaDividendos(informacionSAC.getListaDividendos(),datosCredito));
			prestamoCalculo.setValorCredito(informacionSAC.getMontoPrestamo());			
			final SeguroSaldo segurosaldos = new SeguroSaldo();
			segurosaldos.setValor(informacionSAC.getListaDividendos().get(0).getSeguroDesgravamen());
			segurosaldos.setTasaInteres(BigDecimal.ZERO);
			prestamoCalculo.setSeguroSaldo(segurosaldos);
			validarMontoMaximo(datosCredito, prestamoCalculo, informacionSAC.getMontoPrestamo());
			prestamoCalculo.setTasaInteres(informacionSAC.getInteres());
			prestamoCalculo.setPlazoMeses(informacionSAC.getPlazo());		
			if ((datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.TUR.toString())
					|| datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.FOC.toString())
					|| datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.ECU_TUR.toString())
							|| datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.DINAMICO.toString())
							)
					&& datosCredito.getEstadoDividendoPrestamo() == null) {
				final BigDecimal montoAux = datosCredito.getMonto().add(prestamoCalculo.getSeguroSaldo().getValor());
				
				//Solo cuando es TUR, ECU_TUR o FOC el monto para el calculo de periodo de gracia esta incluido seguro de saldos
				montoCreditoInteresGracia = montoCreditoInteresGracia.add(prestamoCalculo.getSeguroSaldo().getValor());

				// Valida que el monto mas el seguro de saldos no sea mayor al monto maximo de credito de la persona
				if (montoAux.compareTo(datosCredito.getMontoMaximo()) > 0) {
					String mensajeError = "El monto del cr\u00E9dito m\u00E1s el seguro de saldos es mayor al monto m\u00E1ximo. Monto Cr\u00E9dito + Seguro de Saldos = %.2f%n  --  Monto M\u00E1ximo = %.2f%n";
					if (datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.ECU_TUR.toString())) {
						mensajeError = "El monto del cr\u00E9dito establecido por la Agencia de Viajes <strong>$ %.2f%n </strong>m\u00E1s el seguro de saldos <strong>$ %.2f%n</strong> es mayor al monto m\u00E1ximo concedido <strong>$ %.2f%n</strong>.";
						throw new CalculoCreditoException(4,
								String.format(mensajeError, datosCredito.getMonto().setScale(2, RoundingMode.HALF_UP),
										prestamoCalculo.getSeguroSaldo().getValor().setScale(2, RoundingMode.HALF_UP),
										datosCredito.getMontoMaximo().setScale(2, RoundingMode.HALF_UP)));
						
					}else if (datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.DINAMICO.toString())) {
						mensajeError = "El monto del cr\u00E9dito pactado con el proveedor <strong>$ %.2f%n </strong>m\u00E1s el seguro de saldos <strong>$ %.2f%n</strong> es mayor al monto m\u00E1ximo concedido <strong>$ %.2f%n</strong>.";
						throw new CalculoCreditoException(4,
								String.format(mensajeError, datosCredito.getMonto().setScale(2, RoundingMode.HALF_UP),
										prestamoCalculo.getSeguroSaldo().getValor().setScale(2, RoundingMode.HALF_UP),
										datosCredito.getMontoMaximo().setScale(2, RoundingMode.HALF_UP)));
					} 
					
					else {
						throw new CalculoCreditoException(4, String.format(mensajeError, montoAux.setScale(2, RoundingMode.HALF_UP),
								datosCredito.getMontoMaximo().setScale(2, RoundingMode.HALF_UP)));
					}
				}

				datosCredito.setMonto(montoAux);
			}
			
			final CalculoPeriodoGracia calculoPeriodoGracia = new CalculoPeriodoGracia();
			
			// determina periodo de gracia
			prestamoCalculo.setPeriodoGracia(calculoPeriodoGracia.determinaPeriodoGracia(datosCredito.getFechaSolicitud(),
					UtilAjusteCalculo.ajusteNumber(datosCredito.getTasaInteres(), 2),
					UtilAjusteCalculo.ajusteNumber(montoCreditoInteresGracia, 2)));
			
			List<DividendoCalculo> dividendoCalculolist = new ArrayList<DividendoCalculo>();
			if (prestamoCalculo.getDividendoCalculoList().size() > 1) {
				try {
					prestamoCalculo.setFechaInicioPrestamo(FuncionesUtilesSac.obtenerFechaSac(informacionSAC.getListaDividendos().get(0).getFechaCancelacion()));
				} catch (final ParseException e) {
					log.error("Error al consultar informacion de tabla de amortizacion de SAC", e);
					throw new TablaAmortizacionException("Se present\u00F3 un error al tranformar fecha GAF");
				}
			} else {
				prestamoCalculo.setFechaInicioPrestamo(datosCredito.getFechaSolicitud());
			}
			dividendoCalculolist = prestamoCalculo.getDividendoCalculoList();
			// Aqui tienes el ultimo dividendo calculo como un objeto
			final DividendoCalculo dividendoCalculoUltimo = dividendoCalculolist.get(dividendoCalculolist.size() - 1);
			final Date fechaFinPrestamo = dividendoCalculoUltimo.getFechapagoDividendo();
			prestamoCalculo.setFechaFinPrestamo(fechaFinPrestamo);
			log.debug("fechaFinPrestamo: " + fechaFinPrestamo);


		if (datosCredito.isEsNovacion()) {

				prestamoCalculo
				.setMontoCanceladoNovacion(BigDecimal.valueOf(datosCredito.getPrestamoAnteriorNovacion().getValliqnov()));
		

			prestamoCalculo.setLiquidoPagar(datosCredito.getMonto().add(
					prestamoCalculo.getSeguroSaldo().getValor()
							.add(prestamoCalculo.getMontoCanceladoNovacion())
							.negate()));

			if (prestamoCalculo.getLiquidoPagar().doubleValue() <= 0.0D) {
				this.log.error(" Valor liquido a pagar negativo:"
						+ prestamoCalculo.getLiquidoPagar().toString());

				throw new CalculoCreditoException(
						3,
						prestamoCalculo.getLiquidoPagar().setScale(2, 0).abs()
								+ "):El Monto del cr\u00E9dito nuevo (-) el valor de la liquidaci\u00F3n PQ Anterior (-) el valor del seguro de desgravamen es negativo.");
			}

		} else {

			prestamoCalculo.setLiquidoPagar(datosCredito.getMonto().add(prestamoCalculo.getSeguroSaldo().getValor().negate()));

		}

		this.log.debug(" Liquido pagar : " + prestamoCalculo.getLiquidoPagar());
		return prestamoCalculo;
	}

	/**
	 * Permite validar el monto de prestamo que no sobrepase el monto maximo
	 * 
	 * @param datosCredito
	 * @param montoPrestamo
	 * @throws CalculoCreditoException
	 */
	private void validarMontoMaximo(final DatosCredito datosCredito, final PrestamoCalculo prestamoCalculo,
			final BigDecimal montoPrestamo) throws CalculoCreditoException {

		if ((datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.TUR.toString())
				|| datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.FOC.toString())
				|| datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.ECU_TUR.toString()))
				&& datosCredito.getEstadoDividendoPrestamo() == null) {
			datosCredito.setMonto(montoPrestamo.add(prestamoCalculo.getSeguroSaldo().getValor()));//OJO
			if (montoPrestamo.compareTo(datosCredito.getMontoMaximo()) > 0) {
				String mensajeError = "El monto del cr\u00E9dito m\u00E1s el seguro de saldos es mayor al monto m\u00E1ximo. Monto Cr\u00E9dito + Seguro de Saldos = %.2f%n  --  Monto M\u00E1ximo = %.2f%n";
				if (datosCredito.getOrdenCompra().getCodigoProducto().equals(TiposProductosPq.ECU_TUR.toString())) {
					mensajeError = "El monto del cr\u00E9dito establecido por la Agencia de Viajes <strong>$ %.2f%n </strong>m\u00E1s el seguro de saldos <strong>$ %.2f%n</strong> es mayor al monto m\u00E1ximo concedido <strong>$ %.2f%n</strong>.";
					throw new CalculoCreditoException(4,
							String.format(mensajeError, datosCredito.getMonto().setScale(2, RoundingMode.HALF_UP),
									prestamoCalculo.getSeguroSaldo().getValor().setScale(2, RoundingMode.HALF_UP),
									datosCredito.getMontoMaximo().setScale(2, RoundingMode.HALF_UP)));
				} else {
					throw new CalculoCreditoException(4,
							String.format(mensajeError, montoPrestamo.setScale(2, RoundingMode.HALF_UP),
									datosCredito.getMontoMaximo().setScale(2, RoundingMode.HALF_UP)));
				}
			}
		}
	}

	@Override
	public BigDecimal recuperarMontoTienda() {
		final DetalleCatalogosPK detCatalogoPK = new DetalleCatalogosPK();
		detCatalogoPK.setCaCatalogo("MONTTIENDA");
		detCatalogoPK.setDcCodigo("MONTO");
		final DetalleCatalogos detCatalogo = detalleCatalogosDao.load(detCatalogoPK);
		if (detCatalogo == null)
			return BigDecimal.ZERO;
		final String valor = detCatalogo.getDcValor();
		try {
			final BigDecimal montotienda = new BigDecimal(valor);
			return montotienda;
		} catch (final Exception e) {
			return BigDecimal.ZERO;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CalculoCredito obtenerCalculoCreditoNew(final DatosCredito credito) throws CalculoCreditoException {
		final CalculoCredito calculoCredito = new CalculoCredito();
		try {			
			// C?lculos de la cabecera del credito
			// Nuevas tasas de calculo de interes de concesion del BIESS
			final CondicionCalculo condicionCalculo = condicionCalculoServicio
					.poblarCondicionCalculo(credito.getGenero(),
							credito.getFechaNacimiento(),
							credito.getFechaSolicitud(),
							credito.getParNumeroSemanas(),
							credito.getTipoPrestamista());
			calculoCredito.setCondicionCalculo(condicionCalculo);			

			final Garantia garantia = credito.getGarantiaReal();
			calculoCredito.setGarantia(garantia);
			
			
			credito.setTasaInteres(condicionCalculo.getTasaInteres());
			credito.setPorcentajeSeguroDesgravamen(condicionCalculo.getPorcentajeSeguroDesgravamen());

			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(credito.getGarantia()
					.getTipoPrestamista())) {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_ZAFREROS);
				credito.setPlazoMaximoMeses(ConstantesCreditos.PLAZO_MAXIMO_ZAFREROS);
			} else {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_PRODUCTO_NORMAL);
				credito.setPlazoMaximoMeses(condicionCalculo.getPlazoMaximo());
			}
		
			// Cambiar el plazo para enfermo terminal
			if (credito.isEsEnfermoTerminal()) {
				credito.setPlazoMaximoPrestamo(ConstantesCreditos.PLAZO_MAXIMO_ENFERMO_TERMINAL);
				credito.setPlazoMaximoMeses(ConstantesCreditos.PLAZO_MAXIMO_ENFERMO_TERMINAL);
			}
			// fin cambio

			final int idVariedadCredito = CalculoCreditoHelperSingleton
					.determinarIdVariedadCredito(credito.getGarantia()
							.getTipoPrestamista(), credito.getOrigenJubilado());
			credito.setIdVariedadcredito(idVariedadCredito);
			credito.setCalculoCredito(calculoCredito);


			BigDecimal porcentajeComprometido = BigDecimal.ZERO;
			BigDecimal valorMaximoComprometer = BigDecimal.ZERO;

			porcentajeComprometido = new BigDecimal(ConstantesCreditos.PORCENTAJE_COMPROMETIMIENTO_PH);
			calculoCredito.setPorcentajeHipotecario(porcentajeComprometido);

		
			/*
			 * calculoCredito
			 * .setCuotaSolicitudHipotecario(cuotaSolicitudHipotecario);
			 */
			calculoCredito.setDisponibleHipotecario(calculoCredito.getGarantia().getCapacidadPago());
			calculoCredito.setCuotaHipotecario(calculoCredito.getGarantia().getCuotaHipotecarios());
			valorMaximoComprometer = calculoCredito.getGarantia().getSueloPromedio();
			valorMaximoComprometer = valorMaximoComprometer.setScale(2,BigDecimal.ROUND_HALF_UP);
			calculoCredito.setMaximoComprometerHipotecario(valorMaximoComprometer);

			if (calculoCredito.getCuotaHipotecario().floatValue() > 0) {
				calculoCredito.setTienePH(true);
			} else {
				calculoCredito.setTienePH(false);
			}

			// Se agregan los datos de la Orden de compra
			// INC-2272 Pe<nsiones Alimenticias
			if (TiposProductosPq.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
					credito.getOrdenCompra().getCodigoProducto()).getCodigoTipoProducto()) == CategoriaTipoProductoPq.CAT_BIENES) {
				calculoCredito.setOrdenCompra(credito.getOrdenCompra());
			}

			// Se valida si le alcanza la capacidad de endudamiento
			if (UtilAjusteCalculo.ajusteNumberBaseDatos(
					calculoCredito.getGarantia().getSueloPromedio())
					.doubleValue() <= 0) {
				calculoCredito.setTieneCapacidadEndeudamiento(false);
			} else {
				calculoCredito.setTieneCapacidadEndeudamiento(true);
			}

			credito.setCalculoCredito(calculoCredito);			
			return calculoCredito;

		} catch (final CondicionCalculoException e) {
			log.error("error al obtener condiciones de calculo: " + e);
			throw new CalculoCreditoException(e.getMessage());
		} 
	}
	
	/**
	 * Obtiene los meses de gracia
	 * 
	 * @param esEmergente
	 * @return
	 * @throws MontosMaximosException
	 */
	private BigDecimal obtenerMesesGracia() throws SeguroSaldoException {
		BigDecimal resp = BigDecimal.ZERO;
		try {
			resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
					BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico();
		} catch (final ParametroBiessException e) {
			throw new SeguroSaldoException("Error al obtener parametros de meses de gracia de biess emergente", e);
		}
		return resp;
	}
	
	/**
	 * Permite obtener la informacion de la tabla de amortizacion desde el core SAC
	 * 
	 * @param datosCredito
	 * @return
	 * @throws TablaAmortizacionException
	 */
	private TablaAmortizacionSac obtenerInformacionTablaAmortizacionSAC(final DatosCredito datosCredito)
			throws TablaAmortizacionSacException {

		try {
			final OperacionSacRequest request = new OperacionSacRequest();
			request.setCliente(FuncionesUtilesSac.fabricarCliente(datosCredito));
			final PrestamoRequestDto prestamo = new PrestamoRequestDto();
			final String tipoPeticion = datosCredito.getTipoPeticionTablaSac();
			// V/X es por monto, V/K es por cuota
			if ("V/X".equals(tipoPeticion)) {
				prestamo.setMontoCuota(BigDecimal.ZERO);
				prestamo.setMonto(datosCredito.getMonto());
			} else {
				prestamo.setMontoCuota(datosCredito.getCuotaMensualMaxima());
				prestamo.setMonto(BigDecimal.ZERO);
			}
			prestamo.setPlazo(datosCredito.getPlazo());
			prestamo.setTipoTablaAmortizacion(FuncionesUtilesSac.obtenerTipoTablaSac(datosCredito.getTipoTabla()));			
			prestamo.setCargaDividendos(Boolean.TRUE);
			final OperacionRequestDto operacion = new OperacionRequestDto();
			operacion.setTipoProducto(obtenerTipoProducto(datosCredito));
			if(datosCredito.getPrestamoAnteriorNovacion()!=null && datosCredito.getPrestamoAnteriorNovacion().getNumOperacionSAC()!=null) {
				operacion.setNumero(datosCredito.getPrestamoAnteriorNovacion().getNumOperacionSAC());
			}
			request.setOperacion(operacion);
			request.setPrestamo(prestamo);
			return tablaAmortizacionCuotaPlazoSacServicio.simularTablaAmortizacionSAC(request);
		} catch (final ParseException e) {
			log.error("Error al parsear la fecha envio core SAC", e);
			throw new TablaAmortizacionSacException(ConstantesSAC.MENSAJE_VALOR_NULO, ConstantesSAC.COD_VALOR_NULO);
		}
	}
	
	private String obtenerTipoProducto(final DatosCredito datosCredito) {
		if (datosCredito.getCreditoEspecial() != null) {
			switch (datosCredito.getCreditoEspecial().intValue()) {
			case 1:
				return "EMERG";
			case 2:
				return "VEC";
			default:
				return "EBI";
			}
		} else {
			return datosCredito.getOrdenCompra().getCodigoProducto();
		}

	}

	/**
	 * Permite parsear los dividendos obtenidos del SAC
	 * 
	 * @param dividendoDtos
	 * @return
	 * @throws TablaAmortizacionException
	 */
	private List<DividendoCalculo> obtenerListaDividendos(final List<DividendoDto> dividendoDtos,final DatosCredito datosCredito)
			throws TablaAmortizacionException {
		final List<DividendoCalculo> dividendosCalculo = new ArrayList<DividendoCalculo>();
		
		try {
			if (dividendoDtos != null && !dividendoDtos.isEmpty()) {
				for (final DividendoDto dividendo : dividendoDtos) {
					if(dividendo.getNumeroDividendo()==0) {
						continue;
					}
					final DividendoCalculo dividendoCalculo = new DividendoCalculo();
					dividendoCalculo.setNumeroDividendo(dividendo.getNumeroDividendo());
					dividendoCalculo.setValorAbonoCapital(dividendo.getCapital());
					dividendoCalculo.setValorInteres(dividendo.getInteres());
					dividendoCalculo.setSeguroDesgravamen(dividendo.getSeguroDesgravamen());
					dividendoCalculo.setValorDividendo(dividendo.getTotal());
					dividendoCalculo.setvalorSaldoCapital(dividendo.getCapitalReducido());
					dividendoCalculo
							.setFechapagoDividendo(FuncionesUtilesSac.obtenerFechaSac(dividendo.getFechaCancelacion()));
					dividendoCalculo.setEstado(dividendo.getEstado());
					// Los siguientes datos se setean para evitar
					// NullPointerException
					dividendoCalculo.setValorIntPerGra(dividendo.getPeriodoGracia());
					dividendoCalculo.setValorPeriodoGraciaInterzafra(BigDecimal.ZERO);
					dividendosCalculo.add(dividendoCalculo);
				}
			}
		} catch (final ParseException e) {
			log.error("Error al consultar informacion de tabla de amortizacion de SAC", e);
			throw new TablaAmortizacionException("Se present\u00F3 un error al obtener tabla de amortizaci\u00F3n");
		}
		return dividendosCalculo;
	}
	

	@Override
	public TablaAmortizacionSac obtenerInformacionTablaAmortizacionSAC(final OperacionSacRequest request)
			throws TablaAmortizacionSacException {
		return tablaAmortizacionCuotaPlazoSacServicio.simularTablaAmortizacionSAC(request);
	}
}