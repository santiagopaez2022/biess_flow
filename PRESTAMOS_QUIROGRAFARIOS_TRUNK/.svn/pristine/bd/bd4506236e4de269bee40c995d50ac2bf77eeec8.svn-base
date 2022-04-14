package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import ec.gov.iess.creditos.pq.excepcion.CalculoInteresMoraException;
import ec.gov.iess.creditos.pq.excepcion.DividendoPrestamoException;
import ec.gov.iess.creditos.pq.excepcion.GenerarLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarCabeceraLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarDetalleLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarHistoricoLiquidacionException;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal;
import ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioRemote;
import ec.gov.iess.creditos.pq.util.Utilities;

/**
 * Session Bean implementation class LiquidacionGenericaServicioImpl
 */
@Stateless
public class LiquidacionGenericaServicioImpl implements
		LiquidacionGenericaServicioRemote, LiquidacionGenericaServicioLocal {

	LoggerBiess log = LoggerBiess.getLogger(LiquidacionGenericaServicioImpl.class);

	/**
	 * Default constructor.
	 */
	public LiquidacionGenericaServicioImpl() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#calculoTasaInteres(String idtasaInteres, Date fecha)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Double calculoTasaInteres(String idtasaInteres, Date fecha)
			throws TasaInteresExcepcion {

		Double tasa = tasaInteresDetalleDao.consultaInteresMora(idtasaInteres,
				fecha).doubleValue();
		return tasa;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#calculoInteresMora(String idtasaInteres, Date fecha,Double valtotdiv)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Double calculoInteresMora(String idtasaInteres, Date fecha,
			Double valtotdiv) throws CalculoInteresMoraException {

		try {

			Double porint = calculoTasaInteres(idtasaInteres, new Date());
			int nummes = DateUtil.diferenciaMesesEntreFechas(fecha, new Date());
			Double valintmor = ((porint / 12) * (nummes - 1) * valtotdiv) / 100;
			return valintmor;
		} catch (TasaInteresExcepcion e) {
			throw new CalculoInteresMoraException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#CalculoLiquidacion calculoLiquidacion(Prestamo prestamo,TipoLiquidacion tipoLiquidacion)
	 */
	@SuppressWarnings("static-access")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public CalculoLiquidacion calculoLiquidacion(Prestamo prestamo,
			TipoLiquidacion tipoLiquidacion)
			throws CalculoLiquidacionExcepcion {

		Double saltotal;
		Double salparcial;
		Double valsalcre;
		Double valcre;
		Double valcoesegsal;
		Double valintmor = new Double(0);
		Double totintmor = new Double(0);
		Double totintcap = new Double(0);
		Double valsegsal = new Double(0);
		Double totsegsal = new Double(0);
		Double valdivtot = new Double(0);
		Double totdiv = new Double(0);
		Double totcan = new Double(0);

		Calendar fechaperiodo = new GregorianCalendar();

		//Prestamo prestamo = prestamoDao.load(id);
		//prestamo.getDividendosPrestamo().size();

		saltotal = prestamo.getMntpre();
		salparcial = new Double(0);
		valsalcre = new Double(0);
		valcre = prestamo.getMntpre();

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			valsalcre = valcre - div.getValcapamo();
			valcre = valsalcre;
			saltotal = saltotal + valsalcre;

			if (DateUtil.compararPeriodos(div.getAniper(), div.getMesper())) {
				salparcial = salparcial + valsalcre;
			}
			
 		}

		if (prestamo.getClaprerea() == null) {

			AniosPlazoCapitalEndeudamiento coeSegSal = aniosPazoCapitalEndeudamiento
					.consultarCoeficienteSeguroSaldos(prestamo);
			valcoesegsal = coeSegSal.getPorcoesegsal();

		} else {
			valcoesegsal = new Double(0);
		}

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("MOR")
					|| div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"REG")) {

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
											 div.getValcapamo());
						} catch (CalculoInteresMoraException e) {
							throw new CalculoLiquidacionExcepcion();
						}
						totintmor = totintmor + valintmor;
						totintcap = div.getIntsalcap() + totintcap;

					}

				} else {

					if (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"REG")) {

						if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
								|| tipoLiquidacion.equals(tipoLiquidacion.LCF)
								|| tipoLiquidacion.equals(tipoLiquidacion.LNV))
								&& DateUtil.compararPeriodos(div.getAniper(),
										div.getMesper(), 0, 1, 0) >= 0) {
							
							valsegsal = div.getValcapamo() * valcoesegsal;
							totsegsal = totsegsal + valsegsal;
						} else {

							if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LCF)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LNV))
									&& DateUtil.compararPeriodos(div
											.getAniper(), div.getMesper(), 0,
											0, 0) == 0) {
								
								Calendar fechaActual = Calendar.getInstance();
						        int diasCalculo = Utilities.obtenerDiasCalculo(fechaActual);
						        Double intCapitalAmortActual = Utilities.obtenerInteresNormal(div.getValcapamo(), div.getTasintrea(), diasCalculo);
								totintcap = intCapitalAmortActual + totintcap;
								valsegsal = new Double(0);
							}

						}

					}
				}
				valdivtot = div.getValcapamo() + div.getIntpergra();
				totdiv = totdiv + valdivtot;
			}
		}

		if (prestamo.getClaprerea() == null) {
			totsegsal = totsegsal - (totsegsal * 0.03);
		} else {
			totsegsal = (salparcial / saltotal) * prestamo.getValsegsal()
					* ConstantesCreditos.COEFICIENTE_LIQUIDACION;
		}

		totdiv = totdiv + totintcap;
		totcan = totdiv + totintmor - totsegsal;
		if (totcan.doubleValue() <0){
			
			totcan=new Double(0);
			
		}

		CalculoLiquidacion calliq = new CalculoLiquidacion();

		calliq.setCapitalTotal(BigDecimal.valueOf(totdiv).setScale(2,
				BigDecimal.ROUND_HALF_UP));
		calliq.setEstadoPrestamo(prestamo.getEstadoPrestamo().getCodestpre());
		calliq.setFechaFinal(prestamo.getFecfinpre());
		calliq.setFechaInicial(prestamo.getFecinipre());
		calliq.setInteresPorMora(BigDecimal.valueOf(totintmor).setScale(2,
				BigDecimal.ROUND_HALF_UP));
		calliq.setMontoPrestamo(BigDecimal.valueOf(prestamo.getMntpre())
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		calliq.setSeguroSaldos(BigDecimal.valueOf(totsegsal).setScale(2,
				BigDecimal.ROUND_HALF_UP));
		calliq.setValorPorCancelar(BigDecimal.valueOf(totcan).setScale(2,
				BigDecimal.ROUND_HALF_UP));

		return calliq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#generarLiquidacion(Prestamo pre,
	 *			TipoLiquidacion tipoLiquidacion,
	 *			EstadoLiquidacion estadoLiquidacion,
	 *			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
	 */
	public LiquidacionPrestamo generarLiquidacion(Prestamo pre,
			TipoLiquidacion tipoLiquidacion,
			EstadoLiquidacion estadoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo) throws GenerarLiquidacionException {

		Long numeroLiquidacion;

		try {

			numeroLiquidacion = insertarCabeceraLiquidacion(pre,
					tipoLiquidacion.toString(), estadoLiquidacion);
			insertarHistoricoLiquidacion(numeroLiquidacion, estadoLiquidacion
					.toString(), tipoLiquidacion.toString());
			return insertarDetalleLiquidacion(pre, numeroLiquidacion, tipoLiquidacion,
					 estadoCredito,nuevoEstadoDividendo);

		} catch (InsertarCabeceraLiquidacionException e) {
			throw new GenerarLiquidacionException();
		} catch (InsertarHistoricoLiquidacionException e) {
			throw new GenerarLiquidacionException();
		} catch (InsertarDetalleLiquidacionException e) {
			throw new GenerarLiquidacionException();
		}


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#generarLiquidacion(Prestamo pre,
	 *			TipoLiquidacion tipoLiquidacion,
	 *			EstadoLiquidacion estadoLiquidacion,
	 *			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
	 */
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
		//liquidacionPrestamoDao.flush();

		return liquidacionPrestamo.getNumeroLiquidacion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#insertarHistoricoLiquidacion(Long numeroLiquidacion,
	 *			String estadoLiquidacion, String tipoLiquidacion)
	 */
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

	/*	
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.LiquidacionGenericaServicioLocal#insertarDetalleLiquidacion(Prestamo prestamo,
	 *			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion,
	 *			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
	 */
	@SuppressWarnings("static-access")
	public LiquidacionPrestamo insertarDetalleLiquidacion(Prestamo prestamo,
			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
			throws InsertarDetalleLiquidacionException {

		Double saltotal;
		Double salparcial;
		Double valsalcre;
		Double valcre;
		Double valcoesegsal;
		Double valintmor = new Double(0);
		Double valdivtot = new Double(0);
		Double valcoesegsal2 = new Double(0);
		Double valintmoracu = new Double(0);
		Double totcanliq = new Double(0);
		Double acucapamo = new Double(0);
		Double acuintsalcap = new Double(0);
		Double acuintpergra = new Double(0);
		Double segsalpur = new Double(0);
		Double valcapamodiv = new Double(0);
		Double valcanliqfin = new Double(0);
		Double acudivtot = new Double(0);

		Long numliq = new Long(0);

		Double intsalcapdiv = new Double(0);
		Double intpergra = new Double(0);
		Double segsalnet = new Double(0);
		Double salCredito = new Double(0);

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
		prestamoDao.actualizarFecCancelacionYEstado(prestamo);
		//prestamoDao.flush();
		log.debug("Se actualiza el pq a LNV");

		saltotal = prestamo.getMntpre();
		salparcial = new Double(0);
		valsalcre = new Double(0);
		valcre = prestamo.getMntpre();
		salCredito = prestamo.getMntpre();

		for (DividendoPrestamo div : prestamo.getDividendosPrestamo()) {

			valsalcre = valcre - div.getValcapamo();
			valcre = valsalcre;
			saltotal = saltotal + valsalcre;

			if (DateUtil.compararPeriodos(div.getAniper(), div.getMesper())) {
				salparcial = salparcial + valsalcre;
			}
			
			
			if (div.getEstadoDividendoPrestamo().getCodestdiv().equals("CAN")) {
				salCredito = salCredito - div.getValcapamo();
			}
		}

		if (prestamo.getClaprerea() == null) {

			AniosPlazoCapitalEndeudamiento coeSegSal = aniosPazoCapitalEndeudamiento
					.consultarCoeficienteSeguroSaldos(prestamo);
			valcoesegsal = coeSegSal.getPorcoesegsal();

		} else {
			valcoesegsal = new Double(0);
		}

		List<LiquidacionPrestamoDetalle> liqPreDetlista = new ArrayList<LiquidacionPrestamoDetalle>();
		
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
						valdivtot = div.getValcapamo() + div.getIntpergra()
								+ div.getIntsalcap();
						valcoesegsal2 = new Double(0);

						try {
							valintmor = calculoInteresMora(div
									.getEstadoDividendoPrestamo()
									.getCodestdiv(), fechaperiodo.getTime(),
											 div.getValcapamo());
						} catch (CalculoInteresMoraException e) {
							throw new InsertarDetalleLiquidacionException();
						}
						valintmoracu = valintmoracu + valintmor;
						totcanliq = totcanliq + valdivtot;
						valcapamodiv = div.getValcapamo();
						intsalcapdiv = div.getIntsalcap();
						intpergra = div.getIntpergra();
						inddivtot = "1";
						indestdiv = "M";

					}

				} else {

					if (div.getEstadoDividendoPrestamo().getCodestdiv().equals(
							"REG")) {

						numliq = numliq + 1;

						valintmor = new Double(0);
						vermorfecdiv = 12 * div.getAniper().intValue()
								+ div.getMesper().intValue();

						if (vermorfecdiv < vermorfecliq) {
							// un dividendo registrado deberia mora
							throw new InsertarDetalleLiquidacionException();
						}
						
						
						if (div.getAniper().intValue() == fechaperiodo.get(Calendar.YEAR)
								&& div.getMesper().intValue() == fechaperiodo.get(Calendar.MONTH)+1) {
							
							Calendar fechaActual = Calendar.getInstance();
					        int diasCalculo = Utilities.obtenerDiasCalculo(fechaActual);
					        Double totIntCapAmo = Utilities.obtenerInteresNormal(salCredito, div.getTasintrea(), diasCalculo);
							
							valdivtot = div.getValcapamo() + div.getIntpergra()
									+ totIntCapAmo;
							valcoesegsal2 = new Double(0);

							totcanliq = totcanliq + valdivtot;
							acucapamo = acucapamo + div.getValcapamo();
							acuintsalcap = acuintsalcap + totIntCapAmo;
							acuintpergra = acuintpergra + div.getIntpergra();

							valcapamodiv = div.getValcapamo();
							intsalcapdiv = totIntCapAmo;
							intpergra = div.getIntpergra();
							
							inddivtot = "1";
							indestdiv = "L";
							div.setCr_interescapitalant(new BigDecimal(div.getIntsalcap().doubleValue()));
							div.setIntsalcap(totIntCapAmo);
						} else {

							valdivtot = div.getValcapamo() + div.getIntpergra();
							// cambio las condiciones para tomar en cuenta el
							// nuevo tipo de prestamo
							if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LCF) || tipoLiquidacion
									.equals(tipoLiquidacion.LNV))
									&& prestamo.getClaprerea() == null) {
								
								valcoesegsal2 = div.getValcapamo()
										* valcoesegsal;
								/**
								 * RT: Cambio aprobado por Carlos Bastidas detalles en INC-5654
								 */
								segsalpur = segsalpur + valcoesegsal2;
							} else if (tipoLiquidacion
									.equals(tipoLiquidacion.RCE)) {

								valcoesegsal2 = new Double(0);

							} else if ((tipoLiquidacion
									.equals(tipoLiquidacion.PRE)
									|| tipoLiquidacion
											.equals(tipoLiquidacion.LCF) || tipoLiquidacion
									.equals(tipoLiquidacion.LNV))
									&& prestamo.getClaprerea().equals("NUV")) {

								valcoesegsal2 = new Double(0);

							}
							totcanliq = totcanliq + valdivtot;
							acucapamo = acucapamo + div.getValcapamo();
							acuintpergra = acuintpergra + div.getIntpergra();

							valcapamodiv = div.getValcapamo();
							intsalcapdiv = new Double(0);
							intpergra = div.getIntpergra();
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
				liquidacionPrestamoDetalle.setValIntMor(BigDecimal.valueOf(
						valintmor).setScale(2, BigDecimal.ROUND_HALF_UP));
				liquidacionPrestamoDetalle.setOrdPreAfi(div
						.getDividendoPrestamoPk().getOrdpreafi());
				liquidacionPrestamoDetalle.setValDivTot(BigDecimal.valueOf(
						valdivtot).setScale(2, BigDecimal.ROUND_HALF_UP));
				liquidacionPrestamoDetalle.setValCoeSegSal(BigDecimal.valueOf(
						valcoesegsal2).setScale(2, BigDecimal.ROUND_HALF_UP));
				liquidacionPrestamoDetalle.setValCapAmo(BigDecimal.valueOf(
						valcapamodiv).setScale(2, BigDecimal.ROUND_HALF_UP));
				liquidacionPrestamoDetalle.setIntSalCap(BigDecimal.valueOf(
						intsalcapdiv).setScale(2, BigDecimal.ROUND_HALF_UP));
				liquidacionPrestamoDetalle.setIntPerGra(BigDecimal.valueOf(
						intpergra).setScale(2, BigDecimal.ROUND_HALF_UP));
				liquidacionPrestamoDetalle.setIndDivTot(inddivtot);
				liquidacionPrestamoDetalle.setIndEstDiv(indestdiv);

				liqPreDetlista.add(liquidacionPrestamoDetalle);
				liquidacionPrestamoDetalleDao.insert(liquidacionPrestamoDetalle);
				

				try {
					String mensajeGeneracionLiq= TipoCancelacion.MSGGENLIQUIDACION.getDescripcion()+numeroLiquidacion.toString();
					dividendoPrestamoServicio.actualizarEstadoDividendo(div,
							nuevoEstadoDividendo.toString(), "REG",
							mensajeGeneracionLiq);
				} catch (DividendoPrestamoException e) {
					throw new InsertarDetalleLiquidacionException(e);
				}

			}

			acudivtot = acudivtot + valdivtot;
		}

		if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
				|| tipoLiquidacion.equals(tipoLiquidacion.LCF) || tipoLiquidacion
				.equals(tipoLiquidacion.LNV))
				&& prestamo.getClaprerea() == null) {

			segsalnet = segsalpur - (segsalpur * 0.03);

		} else if (tipoLiquidacion.equals(tipoLiquidacion.RCE)) {

			segsalnet = new Double(0);

		} else if ((tipoLiquidacion.equals(tipoLiquidacion.PRE)
				|| tipoLiquidacion.equals(tipoLiquidacion.LCF) || tipoLiquidacion
				.equals(tipoLiquidacion.LNV))
				&& prestamo.getClaprerea().equals("NUV")) {

			segsalnet = (salparcial / saltotal) * prestamo.getValsegsal()
					* ConstantesCreditos.COEFICIENTE_LIQUIDACION;
			segsalpur = new Double(0);
		}

		totcanliq = totcanliq + valintmoracu;

		valcanliqfin = totcanliq - segsalnet;

		LiquidacionPrestamo liquidacionPrestamo = new LiquidacionPrestamo();

		liquidacionPrestamo = liquidacionPrestamoDao.load(numeroLiquidacion);

		liquidacionPrestamo.setTotCanLiq(BigDecimal.valueOf(valcanliqfin)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSegSalPur(BigDecimal.valueOf(segsalpur)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSegSalNet(BigDecimal.valueOf(segsalnet)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSumCapAmo(BigDecimal.valueOf(acucapamo)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSumIntSalCap(BigDecimal.valueOf(acuintsalcap)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSumIntPerGra(BigDecimal.valueOf(acuintpergra)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSumIntMor(BigDecimal.valueOf(valintmoracu)
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		liquidacionPrestamo.setSumDivTot(BigDecimal.valueOf(acudivtot)
				.setScale(2, BigDecimal.ROUND_HALF_UP));

		liquidacionPrestamo.setDetalle(liqPreDetlista);
		
		liquidacionPrestamoDao.update(liquidacionPrestamo);
//		liquidacionPrestamoDao.flush();
		return liquidacionPrestamo;
	}
}
