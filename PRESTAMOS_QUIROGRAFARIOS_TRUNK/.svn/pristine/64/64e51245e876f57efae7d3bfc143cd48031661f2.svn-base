package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.AportesPfr2DaoLocal;
import ec.gov.iess.creditos.dao.CruceFondosReservaAportesDao;
import ec.gov.iess.creditos.dao.GarantiaCesantiaDao;
import ec.gov.iess.creditos.dao.GarantiaPrestamoDao;
import ec.gov.iess.creditos.enumeracion.BloqueoGarantiasPq;
import ec.gov.iess.creditos.enumeracion.CodigoEstadoGarantiaFondos;
import ec.gov.iess.creditos.excepcion.CesantiaExcepcion;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.persistencia.AportesPfr2;
import ec.gov.iess.creditos.modelo.persistencia.CruceFondosReservaAportes;
import ec.gov.iess.creditos.modelo.persistencia.EstadoGarantia;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.GarantiaPrestamoPK;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.DescomprometerGarantiaException;
import ec.gov.iess.creditos.pq.servicio.ConsultaCesantiaServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaPrestamoServicioRemote;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;
import ec.gov.iess.servicio.cesantia.dao.CesantiaDao;
import ec.gov.iess.servicio.cesantia.modelo.Cesantia;
import ec.gov.iess.servicio.cesantia.servicio.CesantiaServicio;
import ec.gov.iess.servicio.fondoreserva.modelo.CuentaFondoReserva;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class GarantiaPrestamoServicioImpl implements GarantiaPrestamoServicio,
		GarantiaPrestamoServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(GarantiaPrestamoServicioImpl.class);

	@EJB
	private GarantiaPrestamoDao garantiaPrestamoDao;

	@EJB
	private GarantiaCesantiaDao garantiaCesantiaDao;

	@EJB
	ConsultaFondosReservaServicio consultaFondosReservaServicio;

	@EJB
	CesantiaServicio consultaCesantia;

	@EJB
	CesantiaDao cesantiaDao;

	@EJB
	AportesPfr2DaoLocal aportesPfr2Dao;

	@EJB
	CruceFondosReservaAportesDao cruceFondosReservaAportesDao;

	@EJB
	ConsultaCesantiaServicio cuentaCesantiaServicio;

	/**
	 * 
	 */
	public GarantiaPrestamoServicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.GarantiaPrestamoServicio#crearGarantiaPq
	 * (java.lang.String, long, int, int, java.util.Date, java.math.BigDecimal)
	 */
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void crearGarantiaPq(DatosGarantia garantia) {

		log.debug("crearGarantiaPq");
		log.debug("cedula : " + garantia.getCedula());
		log.debug("numeroPrestamo : " + garantia.getNumeroPrestamo());
		log.debug("idTipocredito : " + garantia.getIdTipocredito());
		log.debug("idVariedadCredito : " + garantia.getIdVariedadCredito());
		log.debug("fechaSolicitud : " + garantia.getFechaSolicitud());
		log.debug("montocredito : " + garantia.getMontocredito());

		CuentaFondoReserva cuentaFondoReserva = consultarCuentaFondoreserva(garantia);

		GarantiaCesantia cesantia = null;
		BigDecimal valorTotalCesantia = null;

		try {
			cesantia = cuentaCesantiaServicio.consultarCesantia(garantia
					.getValReqFondos().getCedula());
			valorTotalCesantia = cuentaCesantiaServicio
					.consultarCesantia(garantia);
		} catch (CesantiaExcepcion e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}

		GarantiaPrestamo garantiaPrestamo = new GarantiaPrestamo();
		GarantiaPrestamoPK garantiaPrestamoPK = new GarantiaPrestamoPK();

		garantiaPrestamoPK.setCodprecla(new Long(garantia
				.getIdVariedadCredito()));
		garantiaPrestamoPK.setCodpretip(new Long(garantia.getIdTipocredito()));
		garantiaPrestamoPK.setOrdpreafi(1L);
		garantiaPrestamoPK.setNumpreafi(garantia.getNumeroPrestamo());
		garantiaPrestamoPK.setNumafi(garantia.getCedula());
		garantiaPrestamo.setGarantiaPrestamoPK(garantiaPrestamoPK);

		garantiaPrestamo.setFecpreafi(garantia.getFechaSolicitud());
		garantiaPrestamo.setFeccanpre(null);

		garantiaPrestamo.setValdisceshl(cesantia.getValorHistoriaLaboral()
				.doubleValue());
		garantiaPrestamo.setValdisceshos(cesantia.getValorHost().doubleValue());
		garantiaPrestamo.setValdisfonres(cuentaFondoReserva
				.getSaldoDisponible().doubleValue());

		garantiaPrestamo.setBlogarces("0");
		garantiaPrestamo.setValfonresusadeb(null);
		garantiaPrestamo.setValcesusadeb(null);

		garantiaPrestamo.setFecobtfonres(garantia.getFechaSolicitud());
		garantiaPrestamo.setFecobtces(garantia.getFechaSolicitud());
		garantiaPrestamo.setBlogarfonres("0");

		garantiaPrestamo.setEstmig(null);
		garantiaPrestamo.setFecmig(null);

		// proceso de reparticion

		log.info(" Valores comprometidos");
		BigDecimal frComprometido = new BigDecimal(0);
		BigDecimal saldoCredito = new BigDecimal(0);

		//para novacion agrego el comprometido del credito en novacion 
		if (garantia.getValReqFondos().isNovacion()) {
			cuentaFondoReserva.setSaldoDisponible(
					cuentaFondoReserva.getSaldoDisponible().add(garantia.getComprometidoFondos()));
		}

		log.info(" Debito de la Fondo Reserva : "
				+ cuentaFondoReserva.getSaldoDisponible());

		if (garantia.getMontocredito().floatValue() < cuentaFondoReserva
				.getSaldoDisponible().floatValue()) {
			frComprometido = garantia.getMontocredito();

		} else {

			frComprometido = cuentaFondoReserva.getSaldoDisponible();

			saldoCredito = garantia.getMontocredito().add(
					frComprometido.negate());
		}

		frComprometido = frComprometido.setScale(2, BigDecimal.ROUND_HALF_UP);
		log.info(" frComprometido : " + frComprometido);
		log.info(" saldo : " + saldoCredito);

		garantiaPrestamo.setValcomfonres(UtilAjusteCalculo
				.ajusteNumberBaseDatos(frComprometido).doubleValue());

		// Cuando se graba un crédito el valor de saldo por aplicar fondos de
		// reserva es igual al valor comprometido
		garantiaPrestamo.setSalporaplfonres(garantiaPrestamo.getValcomfonres());

		BigDecimal csComprometido = new BigDecimal(0);

		log.info(" Debito de la cesantia");

		if (saldoCredito.floatValue() > 0
				&& saldoCredito.floatValue() <= valorTotalCesantia.floatValue()) {
			// proceso de cesantia
			csComprometido = saldoCredito;
			log.debug(" CES comprometido : " + csComprometido);
			saldoCredito = saldoCredito.add(saldoCredito.negate());
			log.debug(" saldo : " + saldoCredito);
			csComprometido = csComprometido.setScale(2,
					BigDecimal.ROUND_HALF_UP);
		} else if (saldoCredito.floatValue() > 0) {
			log.debug(" saldo : " + saldoCredito);
			StringBuffer msg = new StringBuffer();
			msg.append(" El saldo de credito al descontar de la cesantia y FR es mayor a 0");
			log.error(msg.toString());
			throw new RuntimeException(msg.toString());
		}
		// Se debe Comprometer lo del Pq Anterior si tubiera múltiples PQs.
		BigDecimal valorComprometidoCesAcumulada = new BigDecimal(0);
		BigDecimal valorComprometidoCesSola = new BigDecimal(0);
		if (garantia.getValReqFondos().isNovacion()) {
			valorComprometidoCesAcumulada = garantia.getCompromCesSac()
					.add(cesantia.getValorComprometidoHost())
					.add(csComprometido);
		} else {
			valorComprometidoCesAcumulada = garantia.getCompromCesSac()
					.add(cesantia.getValorComprometidoHost())
					.add(csComprometido);
		}
		valorComprometidoCesSola = csComprometido;

		log.info(" CSComprometido : " + csComprometido);
		log.info(" Saldo : " + saldoCredito);
		garantiaPrestamo.setValcomceshl(UtilAjusteCalculo
				.ajusteNumberBaseDatos(valorComprometidoCesSola).doubleValue());
		garantiaPrestamo.setValdisceshl(UtilAjusteCalculo
				.ajusteNumberBaseDatos(
						cesantia.getTotalCesantia().subtract(csComprometido))
				.doubleValue());

		// Determinado si se bloquea o no el valor de fondos de reserva. Solo se
		// marca cuando es mayor que 0
		if (garantiaPrestamo.getValcomfonres() > 0) {
			garantiaPrestamo.setBlogarfonres("1");
			EstadoGarantia estadoGarantia = new EstadoGarantia();

			estadoGarantia.setCodigoEstadoGarantia("RES");
			estadoGarantia
					.setDescripcionEstadoGarantia("La garantía de Fondos de Reserva, ha sido Reservada");
			estadoGarantia.setIndicadorHabiliatadoEstado("1");
			garantiaPrestamo.setEstadoGarantia(estadoGarantia);

		} else {
			// Si no se bloque valores de fondos de reserva
			EstadoGarantia estadoGarantia = new EstadoGarantia();
			estadoGarantia.setCodigoEstadoGarantia("NUS");
			estadoGarantia
					.setDescripcionEstadoGarantia("No se usa valor de FR para Garantia de PQ");
			estadoGarantia.setIndicadorHabiliatadoEstado("1");
			garantiaPrestamo.setEstadoGarantia(estadoGarantia);
		}

		if (garantiaPrestamo.getValcomceshl() > 0) {
			garantiaPrestamo.setBlogarces("1");
		}

		try {
			log.debug(" Creacion garantia ");
			garantiaPrestamoDao.insert(garantiaPrestamo);
			log.debug("Actualiza ksafitcesantias");
			cesantia.setValorComprometidoHl(UtilAjusteCalculo
					.ajusteNumberBaseDatos(valorComprometidoCesAcumulada));
			garantiaCesantiaDao.update(cesantia);
			// this.actualizarKsafitcesantias(cesantia,
			// BigDecimal.valueOf(garantiaPrestamo.getValcomceshl()));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private CuentaFondoReserva consultarCuentaFondoreserva(
			DatosGarantia datGarantia) {

		CuentaFondoReserva cuentaFondoReserva = null;

		cuentaFondoReserva = consultaFondosReservaServicio
				.consultarFondoReserva(datGarantia);
		return cuentaFondoReserva;
	}

	@SuppressWarnings("unused")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private Cesantia consultarCuentaCesantia(String cedula) {

		Cesantia cesantia = new Cesantia();
		cesantia = consultaCesantia.getCesantia(cedula);
		if (cesantia != null) {
			if (cesantia.getTotalCesantia().doubleValue() < 0) {
				cesantia.setTotalCesantia(new BigDecimal(0));
			}
		} else {
			cesantia = new Cesantia();
			cesantia.setValorHistoriaLaboral(new BigDecimal(0));
			cesantia.setValorHost(new BigDecimal(0));
			cesantia.setTotalCesantia(new BigDecimal(0));
		}

		log.info(" Valor cesantia HOST : " + cesantia.getValorHost()
				+ " COMPR HOSt : " + cesantia.getValorComprometidoHost()
				+ " VAL HL : " + cesantia.getValorHistoriaLaboral()
				+ " COMP HL : " + cesantia.getValorComprometidoHl()
				+ " DISP : " + cesantia.getTotalCesantia());

		return cesantia;
	}

	public void descomprometerGarantias(Prestamo pre,
			CodigoEstadoGarantiaFondos estadoActualGarantia,
			CodigoEstadoGarantiaFondos estadoNuevoGarantia)
			throws DescomprometerGarantiaException {

		boolean estado = descomprometerGarantiasFondos(pre,
				estadoActualGarantia, estadoNuevoGarantia);
		if (estado == true)
			log.info("Se descomprometieron los fondos correctamente");
		else
			log.info("Su crédito no está comprometido con fondos");

		descomprometerGarantiaCesantias(pre);

	}

	public boolean descomprometerGarantiasFondos(Prestamo pre,
			CodigoEstadoGarantiaFondos estadoActualGarantia,
			CodigoEstadoGarantiaFondos estadoNuevoGarantia)
			throws DescomprometerGarantiaException {

		PrestamoPk prestamoPk = pre.getPrestamoPk();
		GarantiaPrestamoPK garantiaPrestamoPK = new GarantiaPrestamoPK(
				prestamoPk.getCodprecla(), prestamoPk.getCodpretip(),
				pre.getNumafi(), prestamoPk.getNumpreafi(),
				prestamoPk.getOrdpreafi());

		Double ValorAplicado = new Double(0);
		Double ValorComprometidoFondos = new Double(0);
		Double SaldoPorAplicarFondos = new Double(0);
		Double ValorCapitalDisponible = new Double(0);

		Double ValorRestituir = new Double(0);
		Double ValorComprometidoPqCapital = new Double(0);
		Double ValorRealComprometido = new Double(0);

		String EstadoGarantia = "";

		List<AportesPfr2> listaAportes;
		GarantiaPrestamo garantiaPrestamo;
		garantiaPrestamo = garantiaPrestamoDao.load(garantiaPrestamoPK);

		if (garantiaPrestamo == null) {

			return false;

		}

		if (garantiaPrestamo.getSalporaplfonres() != null)
			SaldoPorAplicarFondos = garantiaPrestamo.getSalporaplfonres();

		if (garantiaPrestamo.getEstadoGarantia() != null)
			EstadoGarantia = garantiaPrestamo.getEstadoGarantia()
					.getCodigoEstadoGarantia();

		ValorComprometidoFondos = garantiaPrestamo.getValcomfonres();
		ValorRestituir = ValorComprometidoFondos - SaldoPorAplicarFondos;

		if (garantiaPrestamo.getBlogarfonres().equals(
				BloqueoGarantiasPq.BLOQUEADO.getCodigo())) {

			if (EstadoGarantia.equals(estadoActualGarantia.toString())
					|| EstadoGarantia.equals("")) {

				// cambio realizado por ricardo tituana
				// listaAportes=aportesPfr2Dao.findByCedulaAportesComprometidosPq(pre.getNumafi());
				listaAportes = aportesPfr2Dao
						.findByCedulaAndPrestamoAportesComprometidosPq(pre
								.getNumafi(), pre.getPrestamoPk()
								.getNumpreafi(), pre.getPrestamoPk()
								.getCodpretip(), pre.getPrestamoPk()
								.getOrdpreafi(), pre.getPrestamoPk()
								.getCodprecla());

				for (AportesPfr2 aportes : listaAportes) {

					if (ValorRestituir.doubleValue() > 0) {

						List<CruceFondosReservaAportes> cruceAportes;

						cruceAportes = cruceFondosReservaAportesDao
								.findByNumpreafiYCodigoAporte(pre.getNumafi(),
										pre.getPrestamoPk().getNumpreafi(),
										pre.getPrestamoPk().getCodprecla(),
										pre.getPrestamoPk().getCodpretip(),
										pre.getPrestamoPk().getOrdpreafi(),
										aportes.getCodigoAporte());

						if (cruceAportes.size() > 0) {

							ValorRealComprometido = cruceAportes.get(0)
									.getCapitalcomprometido();

							ValorCapitalDisponible = aportes
									.getValorCapitalDisponible()
									.add(BigDecimal
											.valueOf(ValorRealComprometido))
									.doubleValue();

							ValorComprometidoPqCapital = aportes.getValorComprometidoGarPqCap().doubleValue() - ValorRealComprometido;

							ValorAplicado = ValorAplicado
									+ ValorRealComprometido;
							ValorRestituir = ValorRestituir
									- ValorRealComprometido;

							aportes.setValorCapitalDisponible(BigDecimal
									.valueOf(ValorCapitalDisponible));
							aportes.setValorComprometidoGarPqCap(BigDecimal
									.valueOf(ValorComprometidoPqCapital));

							aportesPfr2Dao.update(aportes);

							CruceFondosReservaAportes cruceFondosAporte = cruceAportes
									.get(0);
							cruceFondosAporte
									.setEstproctaind(estadoNuevoGarantia
											.toString());
							cruceFondosAporte.setFecpro(new Date());
							cruceFondosReservaAportesDao
									.update(cruceFondosAporte);

						}

					}
				}

				garantiaPrestamo.setSalporaplfonres(new Double(0));
				EstadoGarantia estadoGarantia = new EstadoGarantia();
				estadoGarantia.setCodigoEstadoGarantia(estadoNuevoGarantia
						.toString());
				garantiaPrestamo.setEstadoGarantia(estadoGarantia);
				garantiaPrestamo
						.setBlogarfonres(BloqueoGarantiasPq.DESBLOQUEADO
								.getCodigo());
				garantiaPrestamoDao.update(garantiaPrestamo);
				return true;
			} else
				return false;
		} else
			return false;

	}

	public void descomprometerGarantiaCesantias(Prestamo prestamo)
			throws DescomprometerGarantiaException {

		GarantiaPrestamoPK garantiaPrestamoPK = new GarantiaPrestamoPK(prestamo
				.getPrestamoPk().getCodprecla(), prestamo.getPrestamoPk()
				.getCodpretip(), prestamo.getNumafi(), prestamo.getPrestamoPk()
				.getNumpreafi(), prestamo.getPrestamoPk().getOrdpreafi());

		Calendar fecha = new GregorianCalendar();
		fecha.set(Calendar.YEAR, 2007);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DATE, 1);
		Date fechaResolucion = fecha.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GarantiaPrestamo garantiaPrestamo = garantiaPrestamoDao
				.load(garantiaPrestamoPK);
		GarantiaCesantia garantiaCesantia = garantiaCesantiaDao.load(prestamo
				.getNumafi());

		if (garantiaCesantia == null) {

			throw new DescomprometerGarantiaException();

		}

		if (sdf.format(prestamo.getFecpreafi()).compareTo(
				sdf.format(fechaResolucion)) >= 0) {
			if (garantiaPrestamo != null) {
				if (new Long(garantiaPrestamo.getBlogarces()) > 0) {
					garantiaPrestamo.setBlogarces("0");
					garantiaPrestamoDao.update(garantiaPrestamo);

					garantiaCesantia.setValorComprometidoHl(garantiaCesantia
							.getValorComprometidoHl()
							.add(garantiaCesantia.getValorComprometidoHost())
							.subtract(
									new BigDecimal(garantiaPrestamo
											.getValcomceshl())));
					garantiaCesantia.setEstact("3");
					garantiaCesantiaDao.update(garantiaCesantia);
				}

			}
		} else {
			if (!prestamo.getPrestamoPk().getCodprecla().equals(23)) {
				garantiaCesantia.setValorComprometidoHl(garantiaCesantia
						.getValorComprometidoHl().subtract(
								new BigDecimal(garantiaPrestamo
										.getValcomceshl())));
				garantiaCesantia.setEstact("3");
				garantiaCesantiaDao.update(garantiaCesantia);
			} else {
				garantiaCesantia.setValorComprometidoHost(garantiaCesantia
						.getValorComprometidoHost().subtract(
								new BigDecimal(garantiaPrestamo
										.getValcomceshl())));
				garantiaCesantia.setEstact("3");
				garantiaCesantiaDao.update(garantiaCesantia);
			}

		}
	}

	@Override
	public GarantiaCesantia consultarCesantiaCliente(String identificacion) {
		GarantiaCesantia cesantia = null;
		try {
			cesantia = cuentaCesantiaServicio.consultarCesantia(identificacion);

		} catch (CesantiaExcepcion cesantiaException) {
			log.error("Se projudo un error al consultar la la garantia de cesantias,", cesantiaException);

		}
		return cesantia;
	}

	@Override
	public BigDecimal consultarTotalFondosReserva(String identificacion) {
		return aportesPfr2Dao.obtenerTotalFr(identificacion);
	}
	
	

}
