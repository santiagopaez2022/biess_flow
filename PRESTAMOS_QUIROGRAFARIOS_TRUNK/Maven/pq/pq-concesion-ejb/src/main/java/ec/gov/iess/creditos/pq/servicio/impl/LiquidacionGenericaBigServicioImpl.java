package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.commons.util.date.DateUtil;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dao.AniosPlazoCapitalEndeudamientoDao;
import ec.gov.iess.creditos.dao.LiquidacionPrestamoDao;
import ec.gov.iess.creditos.dao.LiquidacionPrestamoDetalleDao;
import ec.gov.iess.creditos.dao.LiquidacionPrestamoHistoricoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.dao.TasaInteresDetalleDao;
import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.enumeracion.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoCancelacion;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoDetalle;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoDetallePk;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoHistoricoPK;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.CalculoInteresMoraException;
import ec.gov.iess.creditos.pq.excepcion.DividendoPrestamoException;
import ec.gov.iess.creditos.pq.excepcion.GenerarLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarCabeceraLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarDetalleLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarHistoricoLiquidacionException;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaBigServicioLocal;
import ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaBigServicioRemote;

/**
 * Session Bean implementation class LiquidacionGenericaServicioImpl
 */
@Stateless
public class LiquidacionGenericaBigServicioImpl implements
		LiquidacionGenericaBigServicioRemote, LiquidacionGenericaBigServicioLocal {

	LoggerBiess log = LoggerBiess.getLogger(LiquidacionGenericaBigServicioImpl.class);

	/**
	 * Default constructor.
	 */
	public LiquidacionGenericaBigServicioImpl() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private AniosPlazoCapitalEndeudamientoDao aniosPazoCapitalEndeudamiento;

	@EJB
	private TasaInteresDetalleDao tasaInteresDetalleDao;

	@EJB
	private LiquidacionPrestamoDao liquidacionPrestamoDao;

	@EJB
	private LiquidacionPrestamoHistoricoDao liquidacionPrestamoHistoricoDao;

	@EJB
	private LiquidacionPrestamoDetalleDao liquidacionPrestamoDetalleDao;

	@EJB
	private DividendoPrestamoServicio dividendoPrestamoServicio;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal calculoTasaInteres(String idtasaInteres, Date fecha)
			throws TasaInteresExcepcion {

		BigDecimal tasa = tasaInteresDetalleDao.consultaInteresMora(idtasaInteres,
				fecha);
		return tasa;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal calculoInteresMora(String idtasaInteres, Date fecha,
			BigDecimal valtotdiv) throws CalculoInteresMoraException {

		try {

			BigDecimal porint = calculoTasaInteres(idtasaInteres, new Date());
			int nummes = DateUtil.diferenciaMesesEntreFechas(fecha, new Date());
			BigDecimal valintmor = ((porint.divide(new BigDecimal(12)).multiply((new BigDecimal(nummes - 1)).multiply(valtotdiv).divide(new BigDecimal(100)))));
			return valintmor;
		} catch (TasaInteresExcepcion e) {
			throw new CalculoInteresMoraException();
		}
	}

	@SuppressWarnings("static-access")
	public CalculoLiquidacion calculoLiquidacion(PrestamoPk id,
			TipoLiquidacion tipoLiquidacion)
			throws CalculoLiquidacionExcepcion {

		BigDecimal saltotal;
		BigDecimal salparcial;
		BigDecimal valsalcre;
		BigDecimal valcre;
		BigDecimal valcoesegsal;
		BigDecimal valintmor = new BigDecimal(0);
		BigDecimal totintmor = new BigDecimal(0);
		BigDecimal totintcap = new BigDecimal(0);
		BigDecimal valsegsal = new BigDecimal(0);
		BigDecimal totsegsal = new BigDecimal(0);
		BigDecimal valdivtot = new BigDecimal(0);
		BigDecimal totdiv = new BigDecimal(0);
		BigDecimal totcan = new BigDecimal(0);

		Calendar fechaperiodo = new GregorianCalendar();

		Prestamo prestamo = prestamoDao.load(id);
		prestamo.getDividendosPrestamo().size();

		saltotal = BigDecimal.valueOf(prestamo.getMntpre());
		salparcial = new BigDecimal(0);
		valsalcre = new BigDecimal(0);
		valcre = BigDecimal.valueOf(prestamo.getMntpre());

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			valsalcre = valcre.subtract(BigDecimal.valueOf(div.getValcapamo()));
			valcre = valsalcre;
			saltotal = saltotal.add(valsalcre);

			if (DateUtil.compararPeriodos(div.getAniper(), div.getMesper())) {
				salparcial = salparcial.add(valsalcre);
			}
		}

		if (prestamo.getClaprerea() == null) {

			AniosPlazoCapitalEndeudamiento coeSegSal = aniosPazoCapitalEndeudamiento
					.consultarCoeficienteSeguroSaldos(prestamo);
			valcoesegsal = BigDecimal.valueOf(coeSegSal.getPorcoesegsal());

		} else {
			valcoesegsal = new BigDecimal(0);
		}

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("MOR")
					|| div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"CNV")) {

				if (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
						"MOR")) {

					if (!(tipoLiquidacion.equals(tipoLiquidacion.LCF) || tipoLiquidacion
							.equals(tipoLiquidacion.LNV))) {

						fechaperiodo.setTime(div.getFecpagdiv());
						fechaperiodo.add(Calendar.MONTH, 1);
						fechaperiodo.add(Calendar.DATE, -1);

						try {
							valintmor = calculoInteresMora(div
									.getEstadoDividendoPrestamo()
									.getCodestdiv(), fechaperiodo.getTime(),
									BigDecimal.valueOf(div.getIntpergra()).add(BigDecimal.valueOf(div.getIntsalcap()).add(
											BigDecimal.valueOf(div.getValcapamo()))));
						} catch (CalculoInteresMoraException e) {
							// TODO Auto-generated catch block
							throw new CalculoLiquidacionExcepcion();
						}
						totintmor = totintmor.add(valintmor);
						totintcap = BigDecimal.valueOf(div.getIntsalcap()).add(totintcap);

					}

				} else {

					if (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"CNV")) {

						if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
								|| tipoLiquidacion.equals(tipoLiquidacion.LCF)
								|| tipoLiquidacion.equals(tipoLiquidacion.LNV))
								&& DateUtil.compararPeriodos(div.getAniper(),
										div.getMesper(), 0, 1, 0) >= 0) {

							valsegsal = BigDecimal.valueOf(div.getValcapamo()).multiply(valcoesegsal);
							totsegsal = totsegsal.add(valsegsal);
						} else {

							if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LCF)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LNV))
									&& DateUtil.compararPeriodos(div
											.getAniper(), div.getMesper(), 0,
											0, 0) == 0) {

								totintcap = BigDecimal.valueOf(div.getIntsalcap()).add(totintcap);
								valsegsal = new BigDecimal(0);

							}

						}

					}
				}
				valdivtot = BigDecimal.valueOf(div.getValcapamo()).add(BigDecimal.valueOf(div.getIntpergra()));
				totdiv = totdiv.add(valdivtot);
			}
		}

		if (prestamo.getClaprerea() == null) {
			totsegsal = totsegsal.subtract((totsegsal.multiply(new BigDecimal(0.03))));
		} else {
			totsegsal = (salparcial.divide(saltotal)).multiply(BigDecimal.valueOf(prestamo.getValsegsal())).multiply(
					new BigDecimal(ConstantesCreditos.COEFICIENTE_LIQUIDACION));
		}

		totdiv = totdiv.add(totintcap);
		totcan = totdiv.add(totintmor).subtract(totsegsal);
		if (totcan.doubleValue() <0){
			
			totcan=new BigDecimal(0);
			
		}

		CalculoLiquidacion calliq = new CalculoLiquidacion();

		calliq.setCapitalTotal(totdiv);
		calliq.setEstadoPrestamo(prestamo.getEstadoPrestamo().getCodestpre());
		calliq.setFechaFinal(prestamo.getFecfinpre());
		calliq.setFechaInicial(prestamo.getFecinipre());
		calliq.setInteresPorMora(totintmor.setScale(2, BigDecimal.ROUND_UP));
		calliq.setMontoPrestamo(BigDecimal.valueOf(prestamo.getMntpre())
				.setScale(2, BigDecimal.ROUND_UP));
		calliq.setSeguroSaldos(totsegsal.setScale(2, BigDecimal.ROUND_UP));
		calliq.setValorPorCancelar(totcan.setScale(2, BigDecimal.ROUND_UP));

		return calliq;
	}

	public Long generarLiquidacion(Prestamo pre,
			TipoLiquidacion tipoLiquidacion,
			EstadoLiquidacion estadoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo) throws GenerarLiquidacionException {

		Long numeroLiquidacion;

		try {

			numeroLiquidacion = insertarCabeceraLiquidacion(pre,
					tipoLiquidacion.toString(), estadoLiquidacion);
			insertarHistoricoLiquidacion(numeroLiquidacion, estadoLiquidacion
					.toString(), tipoLiquidacion.toString());
			insertarDetalleLiquidacion(pre, numeroLiquidacion, tipoLiquidacion,
					 estadoCredito,nuevoEstadoDividendo);

		} catch (InsertarCabeceraLiquidacionException e) {
			// TODO Auto-generated catch block
			throw new GenerarLiquidacionException();
		} catch (InsertarHistoricoLiquidacionException e) {
			// TODO Auto-generated catch block
			throw new GenerarLiquidacionException();
		} catch (InsertarDetalleLiquidacionException e) {
			// TODO Auto-generated catch block
			throw new GenerarLiquidacionException();
		}

		return numeroLiquidacion;

	}

	public Long insertarCabeceraLiquidacion(Prestamo pre,
			String tipoLiquidacion, EstadoLiquidacion estadoLiquidacion)
			throws InsertarCabeceraLiquidacionException {

		Calendar fechaperiodo = new GregorianCalendar();

		LiquidacionPrestamo liquidacionPrestamo = new LiquidacionPrestamo();

		liquidacionPrestamo.setFechaLiquidacion(fechaperiodo.getTime());
		liquidacionPrestamo.setNumPreAfi(pre.getPrestamoPk().getNumpreafi());
		liquidacionPrestamo.setCodPreCla(pre.getPrestamoPk().getCodprecla());
		liquidacionPrestamo.setCodPreTip(pre.getPrestamoPk().getCodpretip());
		liquidacionPrestamo.setOrdPreAfi(pre.getPrestamoPk().getOrdpreafi());
		liquidacionPrestamo.setEstadoLiquidacion(estadoLiquidacion);
		liquidacionPrestamo.setTipoLiquidacion(tipoLiquidacion);
		fechaperiodo.set(Calendar.DATE, fechaperiodo
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		liquidacionPrestamo.setFecTerPreLiq(fechaperiodo.getTime());

		liquidacionPrestamoDao.insert(liquidacionPrestamo);
		liquidacionPrestamoDao.flush();

		return liquidacionPrestamo.getNumeroLiquidacion();
	}

	public void insertarHistoricoLiquidacion(Long numeroLiquidacion,
			String estadoLiquidacion, String tipoLiquidacion)
			throws InsertarHistoricoLiquidacionException {

		Calendar fechaperiodo = new GregorianCalendar();
		LiquidacionPrestamoHistoricoPK liquidacionPrestamoHistoricoPk = new LiquidacionPrestamoHistoricoPK();
		LiquidacionPrestamoHistorico liquidacionPrestamoHistorico = new LiquidacionPrestamoHistorico();

		liquidacionPrestamoHistoricoPk.setFecini(fechaperiodo.getTime());
		liquidacionPrestamoHistoricoPk.setNumliqpre(numeroLiquidacion);
		liquidacionPrestamoHistoricoPk.setCodestliqpre(estadoLiquidacion);
		liquidacionPrestamoHistorico
				.setLiquidacionPrestamoHistoricoPk(liquidacionPrestamoHistoricoPk);
		liquidacionPrestamoHistorico.setCodtipliq(tipoLiquidacion);

		liquidacionPrestamoHistoricoDao.insert(liquidacionPrestamoHistorico);

	}

	@SuppressWarnings("static-access")
	public void insertarDetalleLiquidacion(Prestamo prestamo,
			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
			throws InsertarDetalleLiquidacionException {

		BigDecimal saltotal;
		BigDecimal salparcial;
		BigDecimal valsalcre;
		BigDecimal valcre;
		BigDecimal valcoesegsal;
		BigDecimal valintmor = new BigDecimal(0);
		BigDecimal valdivtot = new BigDecimal(0);
		BigDecimal valcoesegsal2 = new BigDecimal(0);
		BigDecimal valintmoracu = new BigDecimal(0);
		BigDecimal totcanliq = new BigDecimal(0);
		BigDecimal acucapamo = new BigDecimal(0);
		BigDecimal acuintsalcap = new BigDecimal(0);
		BigDecimal acuintpergra = new BigDecimal(0);
		BigDecimal segsalpur = new BigDecimal(0);
		BigDecimal valcapamodiv = new BigDecimal(0);
		BigDecimal valcanliqfin = new BigDecimal(0);
		BigDecimal acudivtot = new BigDecimal(0);

		Long numliq = new Long(0);

		BigDecimal intsalcapdiv = new BigDecimal(0);
		BigDecimal intpergra = new BigDecimal(0);
		BigDecimal segsalnet = new BigDecimal(0);

		String inddivtot = null;
		String indestdiv = null;
		Integer vermorfecdiv;
		Integer vermorfecliq;

		Calendar fechaperiodo = new GregorianCalendar();

		vermorfecliq = 12 * fechaperiodo.get(Calendar.DAY_OF_MONTH) + fechaperiodo.get(Calendar.YEAR);

		prestamo.getDividendosPrestamo().size();
		EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		estadoPrestamo.setCodestpre(estadoCredito.toString());
		prestamo.setEstadoPrestamo(estadoPrestamo);
//		prestamoDao.update(prestamo);
		prestamoDao.actualizarFecCancelacionYEstado(prestamo);
		prestamoDao.flush();
		log.debug("Se actualiza el pq a LNV");
		

		saltotal = BigDecimal.valueOf(prestamo.getMntpre());
		salparcial = new BigDecimal(0);
		valsalcre = new BigDecimal(0);
		valcre = BigDecimal.valueOf(prestamo.getMntpre());

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			valsalcre = valcre.subtract(BigDecimal.valueOf(div.getValcapamo()));
			valcre = valsalcre;
			saltotal = saltotal.add(valsalcre);

			if (DateUtil.compararPeriodos(div.getAniper(), div.getMesper())) {
				salparcial = salparcial.add(valsalcre);
			}
		}

		if (prestamo.getClaprerea() == null) {

			AniosPlazoCapitalEndeudamiento coeSegSal = aniosPazoCapitalEndeudamiento
					.consultarCoeficienteSeguroSaldos(prestamo);
			valcoesegsal = BigDecimal.valueOf(coeSegSal.getPorcoesegsal());

		} else {
			valcoesegsal = new BigDecimal(0);
		}

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("MOR")
					|| div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"REG")) {

				if (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
						"MOR")) {

					// Para las liquidaciones de cruce como para las de novación
					// no se tomarán en cuenta los dividendos en mora.
					if (!(tipoLiquidacion.equals(tipoLiquidacion.LCF) || tipoLiquidacion
							.equals(tipoLiquidacion.LNV))) {

						numliq = numliq + 1;

						fechaperiodo.setTime(div.getFecpagdiv());
						fechaperiodo.add(Calendar.MONTH, 1);
						fechaperiodo.add(Calendar.DATE, -1);
						valdivtot = BigDecimal.valueOf(div.getIntpergra()).add(BigDecimal.valueOf(div.getIntsalcap()).add(
								BigDecimal.valueOf(div.getValcapamo())));
						valcoesegsal2 = new BigDecimal(0);

						try {
							valintmor = calculoInteresMora(div
									.getEstadoDividendoPrestamo()
									.getCodestdiv(), fechaperiodo.getTime(),
									BigDecimal.valueOf(div.getIntpergra()).add(BigDecimal.valueOf(div.getIntsalcap()).add(
											BigDecimal.valueOf(div.getValcapamo()))));
						} catch (CalculoInteresMoraException e) {
							// TODO Auto-generated catch block
							throw new InsertarDetalleLiquidacionException();
						}
						valintmoracu = valintmoracu.add(valintmor);
						totcanliq = totcanliq.add(valdivtot);
						valcapamodiv = BigDecimal.valueOf(div.getValcapamo());
						intsalcapdiv = BigDecimal.valueOf(div.getIntsalcap());
						intpergra = BigDecimal.valueOf(div.getIntpergra());
						inddivtot = "1";
						indestdiv = "M";

					}

				} else {

					if (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"REG")) {

						numliq = numliq + 1;

						valintmor = new BigDecimal(0);
						vermorfecdiv = 12 * div.getAniper().intValue()
								+ div.getMesper().intValue();

						if (vermorfecdiv < vermorfecliq) {
							// un dividendo registrado deberia mora
							throw new InsertarDetalleLiquidacionException();
						}
						
						
						if (div.getAniper().intValue() == fechaperiodo.get(Calendar.YEAR)
								&& div.getMesper().intValue() == fechaperiodo.get(Calendar.MONTH)+1) {

							valdivtot = BigDecimal.valueOf(div.getIntpergra()).add(BigDecimal.valueOf(div.getIntsalcap()).add(
									BigDecimal.valueOf(div.getValcapamo())));
							valcoesegsal2 = new BigDecimal(0);

							totcanliq = totcanliq.add(valdivtot);
							acucapamo = acucapamo.add(BigDecimal.valueOf(div.getValcapamo()));
							acuintsalcap = acuintsalcap.add(BigDecimal.valueOf(div.getIntsalcap()));
							acuintpergra = acuintpergra.add(BigDecimal.valueOf(div.getIntpergra()));

							valcapamodiv = BigDecimal.valueOf(div.getValcapamo());
							intsalcapdiv = BigDecimal.valueOf(div.getIntsalcap());
							intpergra = BigDecimal.valueOf(div.getIntpergra());
							inddivtot = "1";
							indestdiv = "L";

						} else {

							valdivtot = BigDecimal.valueOf(div.getValcapamo()).add(BigDecimal.valueOf(div.getIntpergra()));
							// cambio las condiciones para tomar en cuenta el
							// nuevo tipo de prestamo
							if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LCF) || tipoLiquidacion
									.equals(tipoLiquidacion.LNV))
									&& prestamo.getClaprerea() == null) {
								valcoesegsal2 = BigDecimal.valueOf(div.getValcapamo()).multiply(valcoesegsal);
								segsalpur = segsalpur.add(valcoesegsal);
							} else if (tipoLiquidacion
									.equals(tipoLiquidacion.RCE)) {

								valcoesegsal2 = new BigDecimal(0);

							} else if ((tipoLiquidacion
									.equals(tipoLiquidacion.PRE)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LCF) || tipoLiquidacion
									.equals(tipoLiquidacion.LNV))
									&& prestamo.getClaprerea().equals("NUV")) {

								valcoesegsal2 = new BigDecimal(0);

							}
							totcanliq = totcanliq.add(valdivtot);
							acucapamo = acucapamo.add(BigDecimal.valueOf(div.getValcapamo()));
							acuintpergra = acuintpergra.add(BigDecimal.valueOf(div.getIntpergra()));

							valcapamodiv = BigDecimal.valueOf(div.getValcapamo());
							intsalcapdiv = new BigDecimal(0);
							intpergra = BigDecimal.valueOf(div.getIntpergra());
							inddivtot = "0";
							indestdiv = "R";

						}

					}
				}

				LiquidacionPrestamoDetalle liquidacionPrestamoDetalle = new LiquidacionPrestamoDetalle();
				LiquidacionPrestamoDetallePk liquidacionPrestamoDetallePk = new LiquidacionPrestamoDetallePk();
				liquidacionPrestamoDetallePk.setNumLin(numliq);
				liquidacionPrestamoDetallePk.setNumLiqPre(numeroLiquidacion);
				liquidacionPrestamoDetalle.setPk(liquidacionPrestamoDetallePk);
				liquidacionPrestamoDetalle.setNumDiv(div
						.getDividendoPrestamoPk().getNumdiv());
				liquidacionPrestamoDetalle.setCodPreTip(div
						.getDividendoPrestamoPk().getCodpretip());
				liquidacionPrestamoDetalle.setCodPreCla(div
						.getDividendoPrestamoPk().getCodprecla());
				liquidacionPrestamoDetalle.setNumPreAfi(div
						.getDividendoPrestamoPk().getNumpreafi());
				liquidacionPrestamoDetalle.setValIntMor(valintmor);
				liquidacionPrestamoDetalle.setOrdPreAfi(div
						.getDividendoPrestamoPk().getOrdpreafi());
				liquidacionPrestamoDetalle.setValDivTot(valdivtot.setScale(2, BigDecimal.ROUND_UP));
				liquidacionPrestamoDetalle.setValCoeSegSal(valcoesegsal2.setScale(2, BigDecimal.ROUND_UP));
				liquidacionPrestamoDetalle.setValCapAmo(valcapamodiv.setScale(2, BigDecimal.ROUND_UP));
				liquidacionPrestamoDetalle.setIntSalCap(intsalcapdiv.setScale(2, BigDecimal.ROUND_UP));
				liquidacionPrestamoDetalle.setIntPerGra(intpergra.setScale(2, BigDecimal.ROUND_UP));
				liquidacionPrestamoDetalle.setIndDivTot(inddivtot);
				liquidacionPrestamoDetalle.setIndEstDiv(indestdiv);

				liquidacionPrestamoDetalleDao
						.insert(liquidacionPrestamoDetalle);

				try {
					String mensajeGeneracionLiq= TipoCancelacion.MSGGENLIQUIDACION.getDescripcion()+numeroLiquidacion.toString();
					dividendoPrestamoServicio.actualizarEstadoDividendo(div,
							nuevoEstadoDividendo.toString(), "REG",
							mensajeGeneracionLiq);
				} catch (DividendoPrestamoException e) {
					throw new InsertarDetalleLiquidacionException(e);
				}

			}

			acudivtot = acudivtot.add(valdivtot);
		}

		if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
				|| tipoLiquidacion.equals(tipoLiquidacion.LCF) || tipoLiquidacion
				.equals(tipoLiquidacion.LNV))
				&& prestamo.getClaprerea() == null) {

			segsalnet = segsalpur.subtract(segsalpur.multiply(new BigDecimal(0.03)));

		} else if (tipoLiquidacion.equals(tipoLiquidacion.RCE)) {

			segsalnet = new BigDecimal(0);

		} else if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
				|| tipoLiquidacion.equals(tipoLiquidacion.LCF) || tipoLiquidacion
				.equals(tipoLiquidacion.LNV))
				&& prestamo.getClaprerea().equals("NUV")) {

			segsalnet = (salparcial.divide(saltotal)).multiply(BigDecimal.valueOf(prestamo.getValsegsal())).multiply
					(new BigDecimal(ConstantesCreditos.COEFICIENTE_LIQUIDACION));
			segsalpur = new BigDecimal(0);
		}

		totcanliq = totcanliq.add(valintmoracu);

		valcanliqfin = totcanliq.subtract(segsalnet);

		LiquidacionPrestamo liquidacionPrestamo = new LiquidacionPrestamo();

		liquidacionPrestamo = liquidacionPrestamoDao.load(numeroLiquidacion);

		liquidacionPrestamo.setTotCanLiq(valcanliqfin);
		liquidacionPrestamo.setSegSalPur(segsalpur.setScale(2, BigDecimal.ROUND_UP));
		liquidacionPrestamo.setSegSalNet(segsalnet.setScale(2, BigDecimal.ROUND_UP));
		liquidacionPrestamo.setSumCapAmo(acucapamo.setScale(2, BigDecimal.ROUND_UP));
		liquidacionPrestamo.setSumIntSalCap(acuintsalcap.setScale(2, BigDecimal.ROUND_UP));
		liquidacionPrestamo.setSumIntPerGra(acuintpergra.setScale(2, BigDecimal.ROUND_UP));
		liquidacionPrestamo.setSumIntMor(valintmoracu.setScale(2, BigDecimal.ROUND_UP));
		liquidacionPrestamo.setSumDivTot(acudivtot.setScale(2, BigDecimal.ROUND_UP));

		liquidacionPrestamoDao.update(liquidacionPrestamo);
		liquidacionPrestamoDao.flush();

	}
}
