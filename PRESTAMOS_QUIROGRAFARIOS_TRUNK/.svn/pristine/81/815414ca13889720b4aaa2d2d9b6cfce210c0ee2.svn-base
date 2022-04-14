package ec.gov.iess.planillaspq.servicio.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.dao.PlanillaDao;
import ec.gov.iess.planillaspq.dao.PlanillaDetalleDao;
import ec.gov.iess.planillaspq.dao.PlanillaDetalleHostDao;
import ec.gov.iess.planillaspq.exceptions.AjustaPlanillaHlException;
import ec.gov.iess.planillaspq.exceptions.AjustaPlanillaHostException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoDividendoException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioLocal;
import ec.gov.iess.planillaspq.servicio.AjustePlanillaServicioLocal;
import ec.gov.iess.planillaspq.servicio.AjustePlanillaServicioRemote;

@Stateless
public class AjustePlanillaServicioImpl implements AjustePlanillaServicioLocal,
AjustePlanillaServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(AjustePlanillaServicioImpl.class);
	
	@EJB
	private PlanillaDao planillaDao; 
	
	@EJB
	private PlanillaDetalleDao planillaDetalleDao;
			
	@EJB
	private PlanillaDetalleHostDao planillaDetalleHostDao;
			
	@EJB
	private CambioEstadoServicioLocal cambioEstado; 
		
	public void AjustarPlanilla(Planillas planilla, List<PlanillaPrestamosDetalle> pladetlisteli,String Observacion)
	throws AjustaPlanillaHlException{
		
		int count=0;
		BigDecimal sumdetpla=new BigDecimal(0);
		String error=null;
		try{
			if (planilla.getEsttippla().equals("MOR") || planilla.getEsttippla().equals("GEN")){				
			
			log.info("La Planilla está en "+planilla.getEsttippla()+"por lo que si entró a la 1ra condición");
			
				for (PlanillaPrestamosDetalle pladet:pladetlisteli){

						count++;
						
						log.info("número de detalles: "+pladetlisteli.size());
						log.info("Cédula del detalle eliminar:  "+pladet.getNumafi());
						cambioEstado.CambioEstadoDividendo(pladet, Observacion);
							log.info("Actualizó el estado de la cabecera del dividendo");

							sumdetpla=sumdetpla.add(pladet.getValtotdiv());
							log.info("Año: "+pladet.getPk().getAniper()+" precla: "+pladet.getPk().getCodprecla()+" pretip: "+pladet.getPk().getCodpretip()+" codsuc: "+pladet.getPk().getCodsuc()+
									" tippla: "+pladet.getPk().getCodtippla()+" mes: "+pladet.getPk().getMesper()+" numdiv: "+pladet.getPk().getNumdiv()+" numpreafi: "+pladet.getPk().getNumpreafi()+
									" ordpreafi: "+pladet.getPk().getOrdpreafi()+" ruc: "+pladet.getPk().getRucemp()+" secpla: "+pladet.getPk().getSecpla()+" tipper: "+pladet.getPk().getTipper()+
									" codpagpen: "+pladet.getCodpagpen()+" numafi: "+pladet.getNumafi()+" valtotdiv: "+pladet.getValtotdiv());
							
							error="NO SE PUDO ELIMINAR AL AFILIADO "+ pladet.getNumafi()+" DE LA PLANILLA";
							planillaDetalleDao.eliminar(pladet);
							
							log.info("Eliminó el detalle de la planilla. "+pladet.getNumafi());

				}
		
				Planillas plaact=planilla;
		
				plaact.setNumdetpla(plaact.getNumdetpla()-pladetlisteli.size());
				plaact.setValnorpla(plaact.getValnorpla().subtract(sumdetpla));
				error="NO SE PUDO ACTUALIZAR LA CABECERA DE LA PLANILLA DE HL.";
		
				planillaDao.update(plaact);
				error="";
			}
		
		} catch (CambioEstadoDividendoException e) {
			// TODO Auto-generated catch block
			throw new AjustaPlanillaHlException(e.getMessage(),e);				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AjustaPlanillaHlException(error,e);			
		}
		
	}

	public void AjustarPlanillaHost(Planillas planilla, List<PlanillaDetalleHost> pladetlisteli,String Observacion)
	throws AjustaPlanillaHostException{
		
		int count=0;
		BigDecimal sumdetpla=new BigDecimal(0);
		String error="";
		try{
			if (planilla.getEsttippla().equals("MOR") || planilla.getEsttippla().equals("GEN")){				
			
			log.info("La Planilla está en "+planilla.getEsttippla()+"por lo que si entró a la 1ra condición");
			
				for (PlanillaDetalleHost pladet:pladetlisteli){

						count++;
						
						log.info("número de detalles: "+pladetlisteli.size());
						log.info("Cédula del detalle eliminar:  "+pladet.getPk().getNumafi());

							sumdetpla=sumdetpla.add(pladet.getValtotdiv());
							log.info("Año: "+pladet.getPk().getAniper()+" pretip: "+pladet.getPk().getCodsuc()+
									" tippla: "+pladet.getPk().getCodtippla()+" mes: "+pladet.getPk().getMesper()+
									" ruc: "+pladet.getPk().getRucemp()+" secpla: "+pladet.getPk().getSecpla()+" tipper: "+pladet.getPk().getTipper()+
									" numafi: "+pladet.getPk().getNumafi()+" valtotdiv: "+pladet.getValtotdiv());
							
							error="NO SE PUDO ELIMINAR AL AFILIADO "+ pladet.getPk().getNumafi()+" DE LA PLANILLA";
							planillaDetalleHostDao.eliminar(pladet);
							
							log.info("Eliminó el detalle de la planilla. "+pladet.getPk().getNumafi());


				}
		
				Planillas plaact=planilla;
		
				plaact.setNumdetpla(plaact.getNumdetpla()-pladetlisteli.size());
				plaact.setValnorpla(plaact.getValnorpla().subtract(sumdetpla));
		
				error="NO SE PUDO ACTUALIZAR LA CABECERA DE LA PLANILLA DEL HOST.";
				planillaDao.update(plaact);
				log.info("Actualizó correctamente la cabecera de la planilla");
				error="";
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AjustaPlanillaHostException(error,e);			
			
		}
		
	}

}
		

