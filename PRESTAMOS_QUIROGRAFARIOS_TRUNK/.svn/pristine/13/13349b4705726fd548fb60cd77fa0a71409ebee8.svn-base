/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.DividendoPrestamoHistoricoDao;
import ec.gov.iess.creditos.dao.EstadoDividendoPrestamoDao;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoHistoricoPK;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.DividendoPrestamoException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnDividendoException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteDividendoException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteDividendoHistoricoException;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicioRemote;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class DividendoPrestamoServicioImpl implements
		DividendoPrestamoServicio, DividendoPrestamoServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(DividendoPrestamoServicioImpl.class);

	@EJB
	DividendoPrestamoDao dividendoPrestamoDao;

	@EJB
	EstadoDividendoPrestamoDao estadoDividendoPrestamoDao;

	@EJB
	DividendoPrestamoHistoricoDao dividendoPrestamoHistoricoDao;
	@EJB
	private ParametroBiessDao parametroBiessDao;

	/**
	 * 
	 */
	public DividendoPrestamoServicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#crearDividendosPq
	 * (java.util.List, int, int, long, java.math.BigDecimal)
	 */
	public void crearDividendosPq(DatosCredito prestamo) {
		log.debug(" crearDividendosPq");
		log.debug(" Parametros");
		log.debug(" List<DividendoCalculo> : "
				+ prestamo.getDividendoCalculoList().size());
		log.debug(" idTipocredito : " + prestamo.getIdTipoCredito());
		log.debug(" idVariedadcredito : " + prestamo.getIdVariedadcredito());
		log.debug(" numeroPrestamo : " + prestamo.getNumeroPrestamo());
		log.debug(" tasaInteres : " + prestamo.getTasaInteres());

		try {
			
			log.debug(" recorre lista de dividendos");
			for (DividendoCalculo dividendoCalculo : prestamo
					.getDividendoCalculoList()) {

				log.debug(" #Div : " + dividendoCalculo.getNumeroDividendo());
				if (dividendoCalculo.getNumeroDividendo() == 0) {
					continue;
				}

				DividendoPrestamo dividendoPrestamo = new DividendoPrestamo();
				DividendoPrestamoPk dividendoPrestamoPk = new DividendoPrestamoPk();

				dividendoPrestamoPk.setCodprecla(new Long(prestamo
						.getIdVariedadcredito()));
				dividendoPrestamoPk.setCodpretip(new Long(prestamo
						.getIdTipoCredito()));
				dividendoPrestamoPk.setNumdiv(new Long(dividendoCalculo
						.getNumeroDividendo()));
				dividendoPrestamoPk.setNumpreafi(prestamo.getNumeroPrestamo());
				dividendoPrestamoPk.setOrdpreafi(1L);

				dividendoPrestamo.setDividendoPrestamoPk(dividendoPrestamoPk);

				dividendoPrestamo.setFecpagdiv(dividendoCalculo
						.getFechapagoDividendo());

				dividendoPrestamo.setValcapamo(UtilAjusteCalculo
						.ajusteNumberBaseDatos(
								dividendoCalculo.getValorAbonoCapital())
						.doubleValue());

				dividendoPrestamo.setIntsalcap(UtilAjusteCalculo
						.ajusteNumberBaseDatos(
								dividendoCalculo.getValorInteres())
						.doubleValue());

				/*
				 * Solo en el primer dividendo se almacena el valor del periodo
				 * de gracia
				 */
				if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(prestamo.getTipoPrestamista())) 
				{
					if (dividendoCalculo.getNumeroDividendo() > ConstantesCreditos.PLAZO_INTERZAFRA)
					{
						dividendoPrestamo.setIntpergra(dividendoCalculo.getValorPeriodoGraciaInterzafra().doubleValue());
					}
					else
					{
						if (dividendoCalculo.getNumeroDividendo() == 1) {
							dividendoPrestamo.setIntpergra(UtilAjusteCalculo
									.ajusteNumberBaseDatos(
											prestamo.getPrestamoCalculo().getPeriodoGracia().getValor()     
									)
									.doubleValue());
						} else {
							dividendoPrestamo.setIntpergra(new Double(0));
						}
					}
				}
				else
				{
					// Calcula periodo de interes de gracia para creditos emergente
					if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
						if (dividendoCalculo.getNumeroDividendo() <= obtenerMesesGracia()) {
							dividendoPrestamo.setIntpergra(new Double(0));
						} else {
							dividendoPrestamo.setIntpergra(dividendoCalculo.getValorIntPerGra().doubleValue());
						}
					} else {
						// Calcula periodo de interes de gracia para creditos no emergentes
						if (dividendoCalculo.getNumeroDividendo() == 1) {
							dividendoPrestamo.setIntpergra(UtilAjusteCalculo
									.ajusteNumberBaseDatos(
											prestamo.getPrestamoCalculo().getPeriodoGracia().getValor()     
									)
									.doubleValue());
						} else {
							dividendoPrestamo.setIntpergra(new Double(0));
						}
					}
				}
				dividendoPrestamo.setValsegsal(new Double(0));
				dividendoPrestamo.setValcarafi(null);
				dividendoPrestamo.setSigcarafi(null);

				/*
				 * Periodo periodo = new Periodo(); PeriodoPk periodoPk = new
				 * PeriodoPk(); periodoPk.setTipper("M");
				 */
				Calendar cl = Calendar.getInstance();
				cl.setTime(dividendoCalculo.getFechapagoDividendo());
				log.debug(" fecha PAG div  : "
						+ (new SimpleDateFormat("MM/yyyy"))
								.format(dividendoCalculo
										.getFechapagoDividendo()));
				/*
				 * periodoPk.setAniper(new Long(cl.get(Calendar.YEAR)));
				 * periodoPk.setMesper(); periodo.setPeriodoPk(periodoPk);
				 * dividendoPrestamo.setPeriodo(periodo);
				 */
				dividendoPrestamo
						.setTipper(ConstantesCreditos.TIPO_PERIODO_DIVIDENDO);
				dividendoPrestamo.setAniper(new Long(cl.get(Calendar.YEAR)));

				dividendoPrestamo
						.setMesper(new Long(cl.get(Calendar.MONTH) + 1));
				log.debug("ANIO : " + dividendoPrestamo.getAniper() + "/ MES : "
						+ dividendoPrestamo.getMesper());

				dividendoPrestamo.setTipoDividendo(prestamo.getTipoDividendo()); // NOR
				
				if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
					EstadoDividendoPrestamo estadoDividendoPrestamo = new EstadoDividendoPrestamo();
					if (dividendoCalculo.getNumeroDividendo() <= obtenerMesesGracia()) {
						estadoDividendoPrestamo.setCodestdiv("CAN");
						dividendoPrestamo.setFeccandiv(dividendoCalculo
								.getFechapagoDividendo());
						dividendoPrestamo.setNumdoccan(obtenerNumeroMemoBiessEmergente());
						dividendoPrestamo.setForcandiv("GRACIA");
					} else {
						estadoDividendoPrestamo.setCodestdiv("REG");
						dividendoPrestamo.setNumdoccan(null);
						dividendoPrestamo.setFeccandiv(null);
					}
					dividendoPrestamo.setEstadoDividendoPrestamo(estadoDividendoPrestamo);
					
				} else {
					dividendoPrestamo.setEstadoDividendoPrestamo(prestamo
							.getEstadoDividendoPrestamo()); // REG
					dividendoPrestamo.setNumdoccan(null);
					dividendoPrestamo.setFeccandiv(null);
					dividendoPrestamo.setForcandiv(null);
				}
				
				
				dividendoPrestamo.setObscarafi(null);				
				
				dividendoPrestamo.setTasintrea(UtilAjusteCalculo
						.ajusteNumberBaseDatos(prestamo.getTasaInteres())
						.doubleValue());

				log
						.debug(" DIV : "
								+ dividendoPrestamo.getDividendoPrestamoPk()
										.getNumdiv()
								+ " cap : "
								+ dividendoPrestamo.getValcapamo()
								+ " int: "
								+ dividendoPrestamo.getIntsalcap()
								+ " = "
								+ (dividendoPrestamo.getValcapamo()
										.doubleValue() + dividendoPrestamo
										.getIntsalcap().doubleValue()));

				dividendoPrestamoDao.insert(dividendoPrestamo);
				log.debug("    Inserto");
			}
		} catch (Exception e) {
			log.error(" Error al crear los dividendos ", e);
			throw new RuntimeException(" Error al crear los dividendos ", e);
		}

	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#obtenerDividendosPorPagar()
	 */
	public List<DividendoPrestamo> obtenerDividendosPorPagar(
			PrestamoPk prestamoPk, List<String> estados) {
		return dividendoPrestamoDao.obtenerDividendosPorPrestamoYEstado(
				prestamoPk, estados); // MOR,REG
	}

	/**
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#obtenerDividendosPorPagar()
	 */
	public List<DividendoPrestamo> obtenerDividendosEnMora(
			PrestamoPk prestamoPk, List<String> estados) {
		return dividendoPrestamoDao.obtenerDividendosPorPrestamoYEstado(
				prestamoPk, estados); // MOR
	}
	

	public BigDecimal obtenerDiasMora(PrestamoPk prestamoPk)
	{
	  return dividendoPrestamoDao.diasMoraPQ(prestamoPk);	
	}

	/**
	 * @throws DividendoPrestamoException
	 * @throws NoExisteDividendoHistoricoException
	 * @throws MasDeUnDividendoException
	 * @throws NoExisteDividendoException
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#cambiarEstadoDividendo(DividendoPrestamo)
	 */
	// @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarEstadoDividendo(DividendoPrestamo dividendoPrestamo,
			String nuevoEstado, String estadoCondicion,
			String mensajeObservacion) throws DividendoPrestamoException {

		// dividendoPrestamo=dividendoPrestamoDao.load(dividendoPrestamo.getDividendoPrestamoPk());
		if (estadoCondicion != null) {
			if (String.valueOf(dividendoPrestamo.getEstadoDividendoPrestamo().getCodestdiv()).equals(estadoCondicion)) {
				EstadoDividendoPrestamo estadoDividendoPrestamo = new EstadoDividendoPrestamo();
				estadoDividendoPrestamo.setCodestdiv(nuevoEstado);
				dividendoPrestamo
						.setEstadoDividendoPrestamo(estadoDividendoPrestamo);
				dividendoPrestamoDao.update(dividendoPrestamo);
				actualizarHistoricosDividendo(dividendoPrestamo, nuevoEstado,
						mensajeObservacion);
				
			} else {
		          String detalleMensaje=String.valueOf(estadoCondicion) + "  Y SE ENCONTR\u00D3 EL ESTADO "+String.valueOf(dividendoPrestamo.getEstadoDividendoPrestamo().getCodestdiv())+
		          "DETALLE: PRESTAMO: "+dividendoPrestamo.getDividendoPrestamoPk().getNumpreafi()+" DIVIDENDO: "+dividendoPrestamo.getDividendoPrestamoPk().getNumdiv()+ " COD_PRE_CLA: "
						+ dividendoPrestamo.getDividendoPrestamoPk().getCodprecla();
				throw new DividendoPrestamoException(1,detalleMensaje);
			}
		} else {
			EstadoDividendoPrestamo estadoDividendoPrestamo = new EstadoDividendoPrestamo();
			estadoDividendoPrestamo.setCodestdiv(nuevoEstado);
			dividendoPrestamo
					.setEstadoDividendoPrestamo(estadoDividendoPrestamo);
			dividendoPrestamoDao.update(dividendoPrestamo);
			actualizarHistoricosDividendo(dividendoPrestamo, nuevoEstado,
					mensajeObservacion);
		}
	}

	private void actualizarHistoricosDividendo(
			DividendoPrestamo dividendoPrestamo, String nuevoEstado,
			String mensajeObservacion) throws DividendoPrestamoException {
		List<DividendoPrestamoHistorico> historicos = dividendoPrestamoHistoricoDao
				.obtenerUltimosHistoricosDividendo(dividendoPrestamo);
		log.debug("El numero de historicos encontrados es: "
				+ historicos.size());
		if (historicos.size() > 1) {
			StringBuilder mensajeCustomizado = new StringBuilder();
			if (dividendoPrestamo.getDividendoPrestamoPk() != null) {
				mensajeCustomizado.append(" DIVIDENDO: "
						+ dividendoPrestamo.getDividendoPrestamoPk()
								.getNumdiv());
				mensajeCustomizado.append(" PRESTAMO: "
						+ dividendoPrestamo.getDividendoPrestamoPk()
								.getNumpreafi());
				mensajeCustomizado.append(" COD_PRE_CLA: "
						+ dividendoPrestamo.getDividendoPrestamoPk()
								.getCodprecla());
			} 			
			throw new DividendoPrestamoException(2, mensajeCustomizado
					.toString());
		}
		for (DividendoPrestamoHistorico divHist : historicos) {
			DividendoPrestamoHistoricoPK dividendoPrestamoHistoricoPK = divHist
					.getDividendoPrestamoHistoricoPK();
			log.debug("**** dividendoPrestamoHistorico.getCodestdiv(): "
					+ dividendoPrestamoHistoricoPK.getCodestdiv());
			log.debug("**** dividendoPrestamoHistorico.getCodprecla(): "
					+ dividendoPrestamoHistoricoPK.getCodprecla());
			log.debug("**** dividendoPrestamoHistorico.getCodpretip(): "
					+ dividendoPrestamoHistoricoPK.getCodpretip());
			log.debug("**** dividendoPrestamoHistorico.getFecini(): "
					+ dividendoPrestamoHistoricoPK.getFecini());
			log.debug("**** dividendoPrestamoHistorico.getNumdiv(): "
					+ dividendoPrestamoHistoricoPK.getNumdiv());
			log.debug("**** dividendoPrestamoHistorico.getNumpreafi(): "
					+ dividendoPrestamoHistoricoPK.getNumpreafi());
			log.debug("**** dividendoPrestamoHistorico.getOrdpreafi(): "
					+ dividendoPrestamoHistoricoPK.getOrdpreafi());
			divHist.setFecfin(new Date());
			divHist.setObstra(mensajeObservacion);
			dividendoPrestamoHistoricoDao.update(divHist);
		}
		crearNuevoHistoricoDividendo(dividendoPrestamo, nuevoEstado);
	}

	private void crearNuevoHistoricoDividendo(
			DividendoPrestamo dividendoPrestamo, String estado) {

		if (dividendoPrestamo != null) {
			/*
			 * EstadoDividendoPrestamo estadoDividendoHistorico =new
			 * EstadoDividendoPrestamo();
			 * estadoDividendoHistorico.setCodestdiv(estadoCancelacion);
			 * dividendoPrestamo
			 * .setEstadoDividendoPrestamo(estadoDividendoHistorico);
			 */
			// dividendoPrestamoDao.update(dividendoPrestamoAux);
			DividendoPrestamoHistorico dividendoPrestamoHistorico = new DividendoPrestamoHistorico();
			DividendoPrestamoHistoricoPK dividendoPrestamoHistoricoPK = new DividendoPrestamoHistoricoPK();
			dividendoPrestamoHistoricoPK.setNumdiv(dividendoPrestamo
					.getDividendoPrestamoPk().getNumdiv());
			dividendoPrestamoHistoricoPK.setNumpreafi(dividendoPrestamo
					.getDividendoPrestamoPk().getNumpreafi());
			dividendoPrestamoHistoricoPK.setOrdpreafi(dividendoPrestamo
					.getDividendoPrestamoPk().getOrdpreafi());
			dividendoPrestamoHistoricoPK.setCodpretip(dividendoPrestamo
					.getDividendoPrestamoPk().getCodpretip());
			dividendoPrestamoHistoricoPK.setCodprecla(dividendoPrestamo
					.getDividendoPrestamoPk().getCodprecla());
			dividendoPrestamoHistoricoPK.setFecini(new java.sql.Date(System
					.currentTimeMillis()));
			dividendoPrestamoHistoricoPK.setCodestdiv(estado);
			dividendoPrestamoHistorico
					.setDividendoPrestamoHistoricoPK(dividendoPrestamoHistoricoPK);
			dividendoPrestamoHistorico.setFecfin(null);
			dividendoPrestamoHistorico.setObstra(null);
			log.debug("**** a dividendoPrestamoHistoricoDao.insert");
			dividendoPrestamoHistoricoDao.insert(dividendoPrestamoHistorico);
			log.debug("**** d dividendoPrestamoHistoricoDao.insert");
		} else {
			log.info("No se encontr\u00F3 un dividendo");
		}
	}
	
	public void eliminarDividendosEHistoricos(PrestamoPk prestamoPk){
		List<DividendoPrestamo> listaDividendos = 
		dividendoPrestamoDao.getDividendosByIdPrestamo(prestamoPk.getCodprecla(),
				prestamoPk.getCodpretip(),
				prestamoPk.getNumpreafi(),
				prestamoPk.getOrdpreafi());
		for (DividendoPrestamo dividendoPrestamo : listaDividendos) {
			List<DividendoPrestamoHistorico> listdividendoPrestamoHistorico =
			dividendoPrestamoHistoricoDao.paraBuscarTodosDividendoHist(dividendoPrestamo);
			for (DividendoPrestamoHistorico dividendoPrestamoHistorico : listdividendoPrestamoHistorico) {
				dividendoPrestamoHistoricoDao.delete(dividendoPrestamoHistorico);
			}
			dividendoPrestamoDao.delete(dividendoPrestamo);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#eliminarHistoricosDividendos(ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	public void eliminarHistoricosDividendos(PrestamoPk prestamoPk){
		List<DividendoPrestamo> listaDividendos = 
		dividendoPrestamoDao.getDividendosByIdPrestamo(prestamoPk.getCodprecla(),
				prestamoPk.getCodpretip(),
				prestamoPk.getNumpreafi(),
				prestamoPk.getOrdpreafi());
		for (DividendoPrestamo dividendoPrestamo : listaDividendos) {
			List<DividendoPrestamoHistorico> listdividendoPrestamoHistorico =
			dividendoPrestamoHistoricoDao.paraBuscarTodosDividendoHist(dividendoPrestamo);
			for (DividendoPrestamoHistorico dividendoPrestamoHistorico : listdividendoPrestamoHistorico) {
				dividendoPrestamoHistoricoDao.delete(dividendoPrestamoHistorico);
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#eliminarDividendos(ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	public void eliminarDividendos(PrestamoPk prestamoPk){
		List<DividendoPrestamo> listaDividendos = 
		dividendoPrestamoDao.getDividendosByIdPrestamo(prestamoPk.getCodprecla(),
				prestamoPk.getCodpretip(),
				prestamoPk.getNumpreafi(),
				prestamoPk.getOrdpreafi());
		for (DividendoPrestamo dividendoPrestamo : listaDividendos) {			
			dividendoPrestamoDao.delete(dividendoPrestamo);
		}
	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#eliminarDividendosHistoricoLista(java.util.List)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void eliminarDividendosHistoricoLista(List<Prestamo> pres) throws ParametroNoEncontradoException{		
		for (Prestamo prestamo : pres) {
			eliminarHistoricosDividendos(prestamo.getPrestamoPk());			
		}
		for (Prestamo prestamo : pres) {			
			eliminarDividendos(prestamo.getPrestamoPk());
		}
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#obtenerDividendosEnComprobante(ec.gov.iess.creditos
	 *      .modelo.persistencia.pk.PrestamoPk, java.util.List)
	 */
	public List<DividendoPrestamo> obtenerDividendosEnComprobante(PrestamoPk prestamoPk, List<String> estados) {
		// INC-2129 Control en Generacion de Comprobantes.
		return dividendoPrestamoDao.obtenerDividendosPorPrestamoYEstado(prestamoPk, estados); // ECO
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#
	 * contarDividendosMoraBiessPorFecha(java.lang.String)
	 */
	@Override
	public BigDecimal contarDividendosMoraBiessPorFecha(String cedula) {
		return dividendoPrestamoDao.contarDividendosMoraBiessPorFecha(cedula);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#contarDividendosPorFecha(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.util.Date, java.util.Date, java.util.List)
	 */
	public BigDecimal contarDividendosPorFecha(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fecpagdiv, Date feccandiv,
			List<String> forcandiv) {
		return dividendoPrestamoDao.contarDividendosPorFecha(numpreafi, ordpreafi, codpretip, codprecla, fecpagdiv, feccandiv, forcandiv);
	}
	
	/**
	 * Obtiene los meses de gracia
	 * 
	 * @return
	 */
	private int obtenerMesesGracia() {
		int resp = 0;
		try {
			resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
					BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico().intValue();
		} catch (ParametroBiessException e) {
			log.info("Error al obtener parametros de meses de gracia de biess emergente", e);
		}
		return resp;
	}
	
	/**
	 * Devuelve el numero de memo para cancelacion de cuotas de gracia de biess emergente
	 * 
	 * @return
	 */
	private String obtenerNumeroMemoBiessEmergente() {
		String resp = null;
		try {
			resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.NUMERO_MEMO_DIVIDENDO.getIdParametro(),
					BiessEmergenteEnum.NUMERO_MEMO_DIVIDENDO.getNombreParametro()).getValorCaracter();
		} catch (ParametroBiessException e) {
			log.info("Error al obtener parametros de meses de gracia de biess emergente", e);
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio#
	 * contarDividendosMoraBiessEmergente(java.util.Date, java.lang.String)
	 */
	public BigDecimal contarDividendosMoraBiessEmergente(Date fechaPagoDividendos, String cedula) {
		return dividendoPrestamoDao.contarDividendosMoraBiessEmergente(fechaPagoDividendos, cedula);
	}
	
}