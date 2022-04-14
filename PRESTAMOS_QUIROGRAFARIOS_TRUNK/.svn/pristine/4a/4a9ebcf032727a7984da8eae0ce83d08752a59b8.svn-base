/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dao.AniosPlazoCapitalEndeudamientoDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCreditoSinDocumentoFiduciario;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.pq.excepcion.NoExistePlazoEndeudamiento;
import ec.gov.iess.creditos.pq.excepcion.SeguroSaldoException;
import ec.gov.iess.creditos.pq.excepcion.TablaReferenciaException;
import ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal;
import ec.gov.iess.creditos.pq.servicio.SeguroSaldoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.TablaReferenciaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.TablaReferenciaCreditoServicioRemoto;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.CalculoPeriodoGracia;
import ec.gov.iess.creditos.pq.util.CalculoTablaReferencia;
import ec.gov.iess.creditos.pq.util.CalculoTablaReferenciaFactory;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * <b> Servicio que realiza el cáculo referencial del crédito. </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.6 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/11 14:04:00 $]
 *          </p>
 */
@Stateless
public class TablaReferenciaCreditoServicioImpl implements
		TablaReferenciaCreditoServicio, TablaReferenciaCreditoServicioRemoto {

	LoggerBiess log = LoggerBiess.getLogger(TablaReferenciaCreditoServicioImpl.class);

	protected CalculoCreditoHelperSingleton calculoCreditoHelper;
	protected CalculoPeriodoGracia calculoPeriodoGracia;

	@EJB
	SeguroSaldoCreditoServicio seguroSaldoCreditoServicio;

	private CalculoTablaReferenciaFactory calculoTablaReferenciaFactory;

	@EJB
	AniosPlazoCapitalEndeudamientoDao aniosPlazoCapitalEndeudamientoDao;

	@EJB
	private LiquidacionGenericaServicioLocal liquidacionGenericaServicio;

	public TablaReferenciaCreditoServicioImpl() {

	}

	@PostConstruct
	public void init() {
		this.calculoTablaReferenciaFactory = CalculoTablaReferenciaFactory
				.getInstancia();
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton
				.getInstancia();
		this.calculoPeriodoGracia = new CalculoPeriodoGracia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeec.gov.iess.creditos.pq.servicio.TablaReferenciaCreditoServicio#
	 * consultarPlazoEndeudamiento(int)
	 */
	public List<AniosPlazoCapitalEndeudamiento> consultarPlazoEndeudamiento(
			final int plazoMaximoPrestamo, final int plazoMaximoMeses,
			int idVariedadCredito, int idTipoCredito) {

		log.debug(" Determina plazo maximo endeudamiento");
		log.debug(" plazoMaximoMeses : " + plazoMaximoMeses);
		log.debug(" idVariedadCredito : " + idVariedadCredito);
		log.debug(" idTipoCredito : " + idTipoCredito);

		BigDecimal plazo = new BigDecimal(plazoMaximoMeses);
		plazo = plazo.divide(new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP);
		plazo = plazo.setScale(0, BigDecimal.ROUND_UP);

		/*
		 * Se determina la diferencia de meses para que la consulta incluya un
		 * registro adicional en caso de no ser multiplo de doce
		 */
		int plazoMaximoMesesNew = plazo.intValue() * 12;
		log.debug(" plazoMaximoMeses con meses faltantes: "
				+ plazoMaximoMesesNew);

		List<AniosPlazoCapitalEndeudamiento> plazoList = aniosPlazoCapitalEndeudamientoDao
				.consultarEndeudamientoPlazoMaximo(idVariedadCredito,
						idTipoCredito, plazoMaximoMesesNew);

		if (plazoList.size() > 0 && plazoMaximoMesesNew <= plazoMaximoPrestamo) {
			/*
			 * Al ultimo registro se le actualiza el plazo maximo
			 */
			AniosPlazoCapitalEndeudamiento ultimo = plazoList.get(plazoList
					.size() - 1);
			ultimo.getPk().setNumanipla(new Long(plazoMaximoMeses));
		}

		return plazoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeec.gov.iess.creditos.pq.servicio.TablaReferenciaCreditoServicio#
	 * determinarMejorOpcioncredito()
	 */
	public OpcionCredito determinarMejorOpcioncredito() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeec.gov.iess.creditos.pq.servicio.TablaReferenciaCreditoServicio#
	 * determinarTablaReferencia(int,
	 * ec.gov.iess.creditos.pq.modelo.CondicionCalculo,
	 * ec.gov.iess.creditos.pq.modelo.Garantia)
	 */
	public List<PlazoCredito> determinarTablaReferencia(DatosCredito credito,
			BigDecimal saldoDisponibleVigente) throws TablaReferenciaException {

		log.debug(" Tabla de Referencia");
		log.debug(" tipoTablaReferencia :"
				+ credito.getParTipoTablaReferencia());
		log.debug(" tasaInteres :" + credito.getTasaInteres());
		log.debug(" porcentajeSeguroDesgravamen :"
				+ credito.getParSeguroDesgravamen());
		// log.debug(" sueldoPromedio :"
		// + credito.getCalculoCredito().getGarantia().getSueloPromedio());
		log.debug(" totalGarantia :"
				+ credito.getCalculoCredito().getGarantia()
						.getTotalGarantiaAjustada());
		log.debug(" cupoMaximoCredito :"
				+ credito.getCalculoCredito().getGarantia()
						.getCupoMaximoCredito());
		log.debug(" plazoMaximoPrestamo :" + credito.getPlazoMaximoPrestamo());
		log.debug(" plazoMaximoMeses :" + credito.getPlazoMaximoMeses());
		log.debug(" idVariedadCredito :" + credito.getIdVariedadcredito());
		log.debug(" idTipoCredito :" + credito.getIdTipoCredito());
		log.debug(" fechaSolicitud :" + credito.getFechaSolicitud());
		log.debug(" fechaNacimiento :" + credito.getFechaNacimiento());

		List<AniosPlazoCapitalEndeudamiento> plazoEndeudamientoList = consultarPlazoEndeudamiento(
				credito.getPlazoMaximoPrestamo(),
				credito.getPlazoMaximoMeses(), credito.getIdVariedadcredito(),
				credito.getIdTipoCredito());
		credito.setPlazoEndeudamientoList(plazoEndeudamientoList);

		if (plazoEndeudamientoList.size() == 0) {
			throw new NoExistePlazoEndeudamiento(
					"No existe plazo endeudamiento");
		}

		CalculoTablaReferencia calculoTablaReferencia = this.calculoTablaReferenciaFactory
				.getTipoTablaReferencia(credito.getParTipoTablaReferencia());

		log.debug(" Instancia tabla referencia : "
				+ calculoTablaReferencia.getClass().getName());

		log.debug(" Calcula la tabla");
		/*
		 * List<PlazoCredito> plazoCreditoList = calculoTablaReferencia
		 * .determinarTablaReferencia(plazoEndeudamientoList, sueldoPromedio,
		 * tasaInteres, porcentajeSeguroDesgravamen, totalGarantia,
		 * cupoMaximoCredito, fechaSolicitud, fechaNacimiento, idTipoCredito,
		 * idVariedadCredito);
		 */
		try {
			List<PlazoCredito> plazoCreditoList = this

			.determinarTablaReferenciaFinal(credito, saldoDisponibleVigente);

			return plazoCreditoList;
		} catch (RuntimeException e) {
			throw new TablaReferenciaException(e);
		}

	}

	public List<PlazoCredito> determinarTablaReferenciaFinal(
			DatosCredito credito, BigDecimal saldoDisponibleVigente) {
		log.debug(" Determina tabla de referencia sin docuemnto fiduciario");
		List<PlazoCredito> plazoCreditoList = new ArrayList<PlazoCredito>();

		BigDecimal valorTopecredito = null;

		log.debug(" determina el valor maximo de credito");

		if (new Integer(credito.getCalculoCredito().getGarantia()
				.getTotalGarantiaAjustada().compareTo(saldoDisponibleVigente))
				.equals(-1)) {
			valorTopecredito = credito.getCalculoCredito().getGarantia()
					.getTotalGarantiaAjustada();
		} else {
			valorTopecredito = saldoDisponibleVigente;
		}
		log.debug(" monto maximo: " + valorTopecredito);
		// if (credito.getCalculoCredito().getGarantia()
		// .getTotalGarantiaAjustada().floatValue() > credito
		// .getCalculoCredito().getGarantia().getCupoMaximoCredito()
		// .floatValue()) {
		// valorTopecredito = credito.getCalculoCredito().getGarantia()
		// .getSueloPromedio();
		// log.debug(" garantia mayor al tope : " + valorTopecredito);
		// } else {
		// valorTopecredito = credito.getCalculoCredito().getGarantia()
		// .getTotalGarantiaAjustada();
		// log.debug(" garantia menor al tope : " + valorTopecredito);
		// }

		log.debug(" Itera los anios de endeudamiento determinados");
		for (AniosPlazoCapitalEndeudamiento aniosPlazoCapitalEndeudamiento : credito
				.getPlazoEndeudamientoList()) {

			log.debug(" iteracion nueva--------");
			PlazoCredito plazoNew = new PlazoCreditoSinDocumentoFiduciario();

			OpcionCredito opcionCreditoNew = new OpcionCredito();

			opcionCreditoNew.setMeses(aniosPlazoCapitalEndeudamiento.getPk()
					.getNumanipla().intValue());
			log.debug(" Meses : " + opcionCreditoNew.getMeses());

			plazoNew.setPorcentajeComprometido(new BigDecimal(
					aniosPlazoCapitalEndeudamiento.getPorcapend()));
			log.debug(" porcentaje maximo comprometer : "
					+ plazoNew.getPorcentajeComprometido());

			/*
			 * BigDecimal porcentajeComprometido = plazoNew
			 * .getPorcentajeComprometido().divide(new BigDecimal(100), 4,
			 * BigDecimal.ROUND_HALF_UP);
			 */
			// CB: 15/06/2011
			// Se elimina el porcentaje de comprometimiento porque ya se lo
			// realiza en el cáculo de las
			// garantías
			// plazoNew.setValorMaximoComprometer(porcentajeComprometido.multiply(totalIncluidoBuro));
			plazoNew.setValorMaximoComprometer(credito.getCalculoCredito()
					.getGarantia().getSueloPromedio());

			log.debug(" valor maximo comprometido : "
					+ plazoNew.getValorMaximoComprometer());

			plazoNew.setValorMaximoCredito(this.calculoCreditoHelper
					.determinarMontoMaximo(
							plazoNew.getValorMaximoComprometer(), credito
									.getTasaInteres(), new BigDecimal(
									opcionCreditoNew.getMeses()),
							valorTopecredito));
			log.debug(" valor maximo del credito estimado : "
					+ plazoNew.getValorMaximoCredito());

			// Validacion del monto máximo para nuevos productos de PQs
			/*
			 * if (credito.getOrdenCompra().getCodigoProducto() !=
			 * TiposProductosPq.NOR.toString()) {
			 * plazoNew.setValorMaximoCredito(
			 * credito.getOrdenCompra().getMontoOrden()); }
			 */

			try {
				if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(credito
						.getTipoPrestamista())) {
					int plazoNuevoZafrero = opcionCreditoNew.getMeses()
							+ ConstantesCreditos.PLAZO_INTERZAFRA;

					plazoNew.setValorTotalSeguroSaldo(calculoValorSeguroSaldo(
							credito.getIdTipoCredito(),
							credito.getIdVariedadcredito(),
							credito.getFechaNacimiento(),
							credito.getFechaSolicitud(),
							plazoNew.getValorMaximoCredito(), plazoNuevoZafrero));
				} else {
					plazoNew.setValorTotalSeguroSaldo(calculoValorSeguroSaldo(
							credito.getIdTipoCredito(),
							credito.getIdVariedadcredito(),
							credito.getFechaNacimiento(),
							credito.getFechaSolicitud(),
							plazoNew.getValorMaximoCredito(),
							opcionCreditoNew.getMeses()));

					log.debug(" valor Seguro saldos Total : "
							+ plazoNew.getValorTotalSeguroSaldo());
				}
			} catch (SeguroSaldoException e) {
				throw new RuntimeException(e);
			}
			// Validacion del monto máximo para nuevos productos de PQs
			CategoriaTipoProductoPq categoriaTipoProductoPq = TiposProductosPq
					.getCategoriaTipoProductoPq(TiposProductosPq.valueOf(
							credito.getOrdenCompra().getCodigoProducto())
							.getCodigoTipoProducto());
			if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_BIENES) {
				BigDecimal montoCreditoValidacion = new BigDecimal(0);
				BigDecimal montoCredito = new BigDecimal(0);
				montoCreditoValidacion = UtilAjusteCalculo.ajusteNumber(
						credito.getOrdenCompra().getMontoOrden()
								.add(plazoNew.getValorTotalSeguroSaldo()), 2);
				plazoNew.setMontoMaximoOrden(credito.getOrdenCompra()
						.getMontoOrden());

				plazoNew.setPlazoMaximoOrden(opcionCreditoNew.getMeses());
				if (plazoNew.getValorMaximoCredito().doubleValue() < montoCreditoValidacion
						.doubleValue()) {
					plazoNew.setCumpleMonto(false);
				} else {
					plazoNew.setCumpleMonto(true);

					try {
						plazoNew.setValorTotalSeguroSaldo(calculoValorSeguroSaldo(
								credito.getIdTipoCredito(), credito
										.getIdVariedadcredito(), credito
										.getFechaNacimiento(), credito
										.getFechaSolicitud(), credito
										.getOrdenCompra().getMontoOrden(),
								opcionCreditoNew.getMeses()));
					} catch (SeguroSaldoException e) {
						throw new RuntimeException(e);
					}
					montoCredito = UtilAjusteCalculo.ajusteNumber(
							credito.getOrdenCompra().getMontoOrden()
									.add(plazoNew.getValorTotalSeguroSaldo()),
							2);
					plazoNew.setValorMaximoCredito(montoCredito);
				}
			}

			opcionCreditoNew.setCuotaMensual(this.calculoCreditoHelper
					.determinarCuotaMaxima(plazoNew.getValorMaximoCredito(),
							credito.getTasaInteres(),
							opcionCreditoNew.getMeses()));
			log.debug(" valaor de la cuota estimada : "
					+ opcionCreditoNew.getCuotaMensual());
			// Fin cálculo monto máximo

			plazoNew.setValorTotalPeriodoGracia(this.calculoPeriodoGracia
					.determinaPeriodoGracia(credito.getFechaSolicitud(),
							credito.getTasaInteres(),
							plazoNew.getValorMaximoCredito()).getValor());
			log.debug(" valor Periodo de gracia Total : "
					+ plazoNew.getValorTotalPeriodoGracia());
			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(credito
					.getTipoPrestamista())) {
				if (opcionCreditoNew.getMeses() > ConstantesCreditos.PLAZO_INTERZAFRA) {
					plazoNew.setValorTotalPeriodoGraciaInterzafra(this.calculoPeriodoGracia
							.determinaPeriodoGraciaInterZafra(
									credito.getFechaSolicitud(),
									credito.getTasaInteres(),
									plazoNew.getValorMaximoCredito(),
									opcionCreditoNew.getMeses()).getValor());
					log.debug(" valor Periodo de gracia Total : "
							+ plazoNew.getValorTotalPeriodoGraciaInterzafra());
				}
			}
			// Para la Novacion
			if (credito.isEsNovacion()) {

				try {
					plazoNew.setValorLiquidacionNovacion(liquidacionGenericaServicio
							.calculoLiquidacion(
									credito.getPrestamoAnteriorNovacion(),
									TipoLiquidacion.LNV).getValorPorCancelar());
				} catch (CalculoLiquidacionExcepcion e) {
					throw new RuntimeException(e);
				}

				log.debug(" valor Liquidacion Novacion : "
						+ plazoNew.getValorLiquidacionNovacion());

				plazoNew.setValorLiquidoPagar(plazoNew.getValorMaximoCredito()
						.add(plazoNew.getValorTotalSeguroSaldo()
								.add(plazoNew.getValorLiquidacionNovacion())
								.negate()));
			} else {

				plazoNew.setValorLiquidoPagar(plazoNew.getValorMaximoCredito()
						.add(plazoNew.getValorTotalSeguroSaldo().negate()));
			}

			log.debug(" valor Liquido pagar Total : "
					+ plazoNew.getValorLiquidoPagar());

			plazoNew.setOpcionCredito(opcionCreditoNew);

			plazoCreditoList.add(plazoNew);

		}

		return plazoCreditoList;

	}

	protected BigDecimal calculoValorSeguroSaldo(int idTipoCredito,
			int idVariedadCredito, Date fechaNacimiento, Date fechaSolicitud,
			BigDecimal montoTotalCredito, int plazoMeses)
			throws SeguroSaldoException {

		log.debug(" calcula valor seguro de saldo");
		log.debug(" idTipoCredito " + idTipoCredito);
		log.debug(" idVariedadCredito " + idVariedadCredito);
		log.debug(" fechaNacimiento " + fechaNacimiento);
		log.debug(" fechaSolicitud " + fechaSolicitud);
		log.debug(" montoTotalCredito " + montoTotalCredito);
		log.debug(" plazoMeses " + plazoMeses);

		try {

			/*
			 * this.seguroSaldoCreditoServicio =
			 * (SeguroSaldoCreditoServicioRemoto) Util
			 * .getServiceBean("SeguroSaldoCreditoServicioImpl/remote");
			 */
			BigDecimal valorSegurosaldo = seguroSaldoCreditoServicio
					.calculoSeguroSaldoEnBaseMontoCredito(idTipoCredito,
							idVariedadCredito, fechaNacimiento, fechaSolicitud,
							plazoMeses, montoTotalCredito);

			log.debug(" valorSegurosaldo Calculado: " + valorSegurosaldo);

			return valorSegurosaldo;

		} catch (SeguroSaldoException e) {
			throw new SeguroSaldoException(e);
		} catch (Exception e) {
			log.error(" Error llamada servicio seguroSaldoCreditoServicio : ",
					e);
			throw new RuntimeException(e);
		}

	}

}
