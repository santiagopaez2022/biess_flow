package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CuentaIndividualDao;
import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.EstadoPrestamoDao;
import ec.gov.iess.creditos.dao.LiquidacionPrestamoDao;
import ec.gov.iess.creditos.dao.LiquidacionPrestamoHistoricoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoCancelacion;
import ec.gov.iess.creditos.modelo.persistencia.CuentaIndividual;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoDetalle;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoHistoricoPK;
import ec.gov.iess.creditos.pq.excepcion.CancelaLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.DividendoPrestamoException;
import ec.gov.iess.creditos.pq.servicio.CancelarLiquidacionServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CancelarLiquidacionServicioRemote;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;

/**
 * Session Bean implementation class CancelarLiquidacionServicioImp
 */
@Stateless
public class CancelarLiquidacionServicioImp implements CancelarLiquidacionServicioLocal,CancelarLiquidacionServicioRemote {

    /**
     * Default constructor. 
     */
    public CancelarLiquidacionServicioImp() {
        // TODO Auto-generated constructor stub
    }

private LoggerBiess log = LoggerBiess.getLogger(CancelarLiquidacionServicioImp.class);
	
	@EJB
	private PrestamoDao prestamoDao;
	
	@EJB
	private DividendoPrestamoDao dividendoPrestamoDao;
	
	@EJB
	private LiquidacionPrestamoDao liquidacionPrestamoDao;
	
	@EJB
	private CuentaIndividualDao cuentaIndividualDao;
	
	@EJB
	private EstadoPrestamoDao estadoPrestamoDao;
	
	@EJB
	private DividendoPrestamoServicio dividendoPrestamoServicio;	
	
	@EJB
	private LiquidacionPrestamoHistoricoDao liquidacionPrestamoHistoricoDao;
	
	public void cancelarCredito(Prestamo prestamo){
	
		Prestamo prestamoAux = prestamoDao.buscarPorPk(prestamo.getPrestamoPk());
		EstadoPrestamo estadoPrestamo = estadoPrestamoDao.load("CAN");
		prestamoAux.setEstadoPrestamo(estadoPrestamo);
		//prestamoAux.setPrisegsal(new BigDecimal(0));

		prestamoDao.update(prestamoAux);
		
	}

	public void cancelaLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo,String cedula)throws CancelaLiquidacionException {
		String mensajeCancelacion=TipoCancelacion.MSGCANLIQUIDACION.getDescripcion()+liquidacionPrestamo.getNumeroLiquidacion();
		log.info("**** Estado Liquidacion: "+liquidacionPrestamo.getEstadoLiquidacion());		
		List<LiquidacionPrestamoDetalle> liquidacionPrestamoDetalleList =liquidacionPrestamo.getDetalle();
		log.info("**** Numero de liquidacion: "+liquidacionPrestamo.getNumeroLiquidacion());		
			
		int i=0;
		for(LiquidacionPrestamoDetalle detalleLiq:liquidacionPrestamoDetalleList){
			
				DividendoPrestamoPk dividendoPrestamoPk = new DividendoPrestamoPk();
				dividendoPrestamoPk.setCodprecla(detalleLiq.getCodPreCla());
				dividendoPrestamoPk.setCodpretip(detalleLiq.getCodPreTip());			
				dividendoPrestamoPk.setNumdiv(detalleLiq.getNumDiv());
				dividendoPrestamoPk.setNumpreafi(detalleLiq.getNumPreAfi());
				dividendoPrestamoPk.setOrdpreafi(detalleLiq.getOrdPreAfi());
				//Actualización del estado del dividendo
				DividendoPrestamo dividendoTmp=dividendoPrestamoDao.load(dividendoPrestamoPk);
				try {
					dividendoPrestamoServicio.actualizarEstadoDividendo(dividendoTmp, "CNV","LNV", mensajeCancelacion);
				} catch (DividendoPrestamoException e) {
					throw new CancelaLiquidacionException(1,null);
				}
				i++;
				generarCuentaIndividual(dividendoTmp,mensajeCancelacion,cedula,i);				
			}
		cambiarEstadoLiquidacion(liquidacionPrestamo,EstadoLiquidacion.CAN,mensajeCancelacion);		
		//Cambio de estado al préstamo actual
/*		
		EstadoPrestamo estadoPrestamo=new EstadoPrestamo();
		estadoPrestamo.setCodestpre(EstadoCredito.CNV.toString());
		Calendar cal=new GregorianCalendar();
		liquidacionPrestamo.getPrestamo().setEstadoPrestamo(estadoPrestamo);
		liquidacionPrestamo.getPrestamo().setFeccanpre(cal.getTime());
		prestamoDao.actualizarFecCancelacionYEstado(liquidacionPrestamo.getPrestamo());

*/
	}
	
	public void generarCuentaIndividual(DividendoPrestamo dividendo,String mensajeObservacion,String cedula,int i){		
		//Generación de una cuenta individual por dividendo
		CuentaIndividual cuentaIndividual = new CuentaIndividual ();
		cuentaIndividual.setCodpretip(new BigDecimal(dividendo.getDividendoPrestamoPk().getCodpretip()));
		cuentaIndividual.setCodprecla(new BigDecimal(dividendo.getDividendoPrestamoPk().getCodprecla()));
		cuentaIndividual.setNumpreafi(new BigDecimal(dividendo.getDividendoPrestamoPk().getNumpreafi()));
		cuentaIndividual.setOrdpreafi(new BigDecimal(dividendo.getDividendoPrestamoPk().getOrdpreafi()));
		cuentaIndividual.setNumdiv(new BigDecimal(dividendo.getDividendoPrestamoPk().getNumdiv()));
		cuentaIndividual.setNumafi(cedula);
		cuentaIndividual.setCodpen(null);	
		cuentaIndividual.setNumseccueind(i);
		cuentaIndividual.setFecregtra(new Date(System.currentTimeMillis()));
		cuentaIndividual.setObstra(mensajeObservacion);
		cuentaIndividual.setValtra(new BigDecimal(dividendo.getValcapamo()));
		cuentaIndividual.setCodtiptra("CANPRE");
		cuentaIndividualDao.insert(cuentaIndividual);	
	}
	
	private void cambiarEstadoLiquidacion(LiquidacionPrestamo liquidacion,EstadoLiquidacion nuevoEstado,String observacion){
		liquidacion.setEstadoLiquidacion(nuevoEstado);	
		liquidacionPrestamoDao.update(liquidacion);
		actualizarHistoricosLiquidacion(liquidacion, observacion);		
	}
	
		
	private void actualizarHistoricosLiquidacion(LiquidacionPrestamo liquidacion,String observacion){
		List<LiquidacionPrestamoHistorico> liquidacionPrestamoHistoricoAux = liquidacionPrestamoHistoricoDao.buscarPorNumYCodEst(liquidacion.getNumeroLiquidacion());
		for(LiquidacionPrestamoHistorico liq:liquidacionPrestamoHistoricoAux){
			liq.setFecfin(new Date(System.currentTimeMillis()));
			liq.setObstra(observacion);
			liquidacionPrestamoHistoricoDao.update(liq);
		}		
		ingresarNuevoRegistroLiquidacion(liquidacion,EstadoLiquidacion.CAN);
	}
	private void ingresarNuevoRegistroLiquidacion(LiquidacionPrestamo liquidacion,EstadoLiquidacion estadoLiquidacion){
		LiquidacionPrestamoHistorico liquidacionPrestamoHistorico = new LiquidacionPrestamoHistorico();
		LiquidacionPrestamoHistoricoPK liquidacionPrestamoHistoricoPK = new LiquidacionPrestamoHistoricoPK();
		liquidacionPrestamoHistoricoPK.setNumliqpre(liquidacion.getNumeroLiquidacion());
		liquidacionPrestamoHistoricoPK.setCodestliqpre(estadoLiquidacion.toString());
		liquidacionPrestamoHistoricoPK.setFecini(new Date(System.currentTimeMillis()));
		liquidacionPrestamoHistorico.setLiquidacionPrestamoHistoricoPk(liquidacionPrestamoHistoricoPK);
		liquidacionPrestamoHistorico.setFecfin(null);
		liquidacionPrestamoHistorico.setObstra(null);
		liquidacionPrestamoHistoricoDao.insert(liquidacionPrestamoHistorico);
	}
	
	public void actualizarLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo,String tipo,String numeroDocumento)throws CancelaLiquidacionException {
		List<LiquidacionPrestamoDetalle> liquidacionPrestamoDetalleList =liquidacionPrestamo.getDetalle();
		for(LiquidacionPrestamoDetalle detalleLiq:liquidacionPrestamoDetalleList){
			
				DividendoPrestamoPk dividendoPrestamoPk = new DividendoPrestamoPk();
				dividendoPrestamoPk.setCodprecla(detalleLiq.getCodPreCla());
				dividendoPrestamoPk.setCodpretip(detalleLiq.getCodPreTip());			
				dividendoPrestamoPk.setNumdiv(detalleLiq.getNumDiv());
				dividendoPrestamoPk.setNumpreafi(detalleLiq.getNumPreAfi());
				dividendoPrestamoPk.setOrdpreafi(detalleLiq.getOrdPreAfi());
				//Actualización del estado del dividendo
				DividendoPrestamo dividendoTmp=dividendoPrestamoDao.load(dividendoPrestamoPk);
				dividendoTmp.setForcandiv(tipo);
				dividendoTmp.setNumdoccan(numeroDocumento);
				dividendoTmp.setFeccandiv(new Date());
				dividendoPrestamoDao.update(dividendoTmp);
			}
	}

}
