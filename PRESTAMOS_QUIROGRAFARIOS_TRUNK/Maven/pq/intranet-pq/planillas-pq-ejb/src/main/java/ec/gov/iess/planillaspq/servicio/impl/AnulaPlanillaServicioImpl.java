package ec.gov.iess.planillaspq.servicio.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.exception.GenericJDBCException;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.dao.PlanillaDao;
import ec.gov.iess.planillaspq.exceptions.AnulaPlanillaHlException;
import ec.gov.iess.planillaspq.exceptions.AnulaPlanillaHostException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoDividendoException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.servicio.AnulaPlanillaServicioLocal;
import ec.gov.iess.planillaspq.servicio.AnulaPlanillaServicioRemote;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioLocal;
import ec.gov.iess.planillaspq.storeprocedure.AnulaPlanillaSpLocal;

@Stateless
public class AnulaPlanillaServicioImpl implements AnulaPlanillaServicioLocal,
AnulaPlanillaServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(AnulaPlanillaServicioImpl.class);
	
	@EJB
	private CambioEstadoServicioLocal cambioEstado; 		

	@EJB
	private AnulaPlanillaSpLocal anupla; 		

	@EJB
	private PlanillaDao planillaDao; 

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	@SuppressWarnings("unchecked")
	public void AnularPlanillaHost(Planillas planilla, String Observacion,String cedusu)
	throws AnulaPlanillaHostException{
		
		String error="NO SE PUDO ANULAR LA PLANILLA";
		try{
/*
			if (planilla.getEsttippla().equals("MOR") || planilla.getEsttippla().equals("GEN")){				
			
				log.info("Ingreso a la condición de que la planilla está en GEN,MOR o ECP");
				if (planilla.getEsttippla().equals("ECP")){
					log.info("Ingreso a la condición de que la planilla está en ECP");
					error="EXISTE INCONSISTENCIAS ENTRE LA CABECERA Y EL HISTORICO DE LA PLANILLA";	
					cambioEstado.CambioEstadoComprobante(planilla, Observacion,"DIVPREHO",cedusu,"ANU");
				}					
			}
*/
			log.info("La Planilla está en "+planilla.getEsttippla()+"por lo que si entró a la 1ra condición");
				Planillas pla=planillaDao.buscarPorId(planilla.getPk());
				Map resultado=anupla.ejecutarAnulacionPlanillas(pla);
				if (resultado.get("AOCRESPRO").toString().trim().equals("0")){
					throw new AnulaPlanillaHostException("EXISTE INCONSISTENCIA ENTRE LOS ESTADOS DE LA CABECERA Y EL HISTORICO DE LA PLANILLA. MENSAJE DE LA BASE: "+resultado.get("AOCMENERR"));	
				}
				
		} catch (EjecucionProcedimientoException e) {
			// TODO Auto-generated catch block
			throw new AnulaPlanillaHostException(e.getMessage(),e);			
		} catch (GenericJDBCException e) {
			// TODO Auto-generated catch block
			throw new AnulaPlanillaHostException(error,e);						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AnulaPlanillaHostException(error,e);			
			
		}
		
	}
	//@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	@SuppressWarnings("unchecked")
	public void AnularPlanillaHl(Planillas planilla,List<PlanillaPrestamosDetalle> pladetlist, String Observacion,String cedusu)
	throws AnulaPlanillaHlException{
			

		try{

				Planillas pla=planillaDao.buscarPorId(planilla.getPk());
				Map resultado=anupla.ejecutarAnulacionPlanillas(pla);
				if (resultado.get("AOCRESPRO").toString().trim().equals("0")){
					throw new AnulaPlanillaHostException("EXISTE INCONSISTENCIA ENTRE LOS ESTADOS DE LA CABECERA Y EL HISTORICO DE LA PLANILLA. MENSAJE DE LA BASE: "+resultado.get("AOCMENERR"));	
				}
				log.info("numero de detalles: "+pladetlist.size());
/*
				int prev=100;
				int indice=0;
				if (pladetlist.size()>100){	
				for (int i = 0; i < pladetlist.size(); i=i+prev) {				
					if (i<=pladetlist.size()){	
						indice=i+prev;					
					}else{
						indice=pladetlist.size();
					}
							for (PlanillaPrestamosDetalle pladet:pladetlist.subList(i, indice)){
							//for (PlanillaPrestamosDetalle pladet:pladetlist){
						
								cambioEstado.CambioEstadoDividendo(pladet, Observacion+"Funcionarios: "+cedusu);
								
								log.info("Actualizó el estado de la cabecera del dividendo");
		
								log.info("Año: "+pladet.getPk().getAniper()+" precla: "+pladet.getPk().getCodprecla()+" pretip: "+pladet.getPk().getCodpretip()+" codsuc: "+pladet.getPk().getCodsuc()+
										" tippla: "+pladet.getPk().getCodtippla()+" mes: "+pladet.getPk().getMesper()+" numdiv: "+pladet.getPk().getNumdiv()+" numpreafi: "+pladet.getPk().getNumpreafi()+
										" ordpreafi: "+pladet.getPk().getOrdpreafi()+" ruc: "+pladet.getPk().getRucemp()+" secpla: "+pladet.getPk().getSecpla()+" tipper: "+pladet.getPk().getTipper()+
										" codpagpen: "+pladet.getCodpagpen()+" numafi: "+pladet.getNumafi()+" valtotdiv: "+pladet.getValtotdiv());
								
								log.info("Cambio el estado de la planilla. "+pladet.getNumafi());
						     }
					}		
			}else{
	*/			
					for (PlanillaPrestamosDetalle pladet:pladetlist){
				
						cambioEstado.CambioEstadoDividendo(pladet, Observacion+"Funcionarios: "+cedusu);
						
						log.info("Actualizó el estado de la cabecera del dividendo");

						log.info("Año: "+pladet.getPk().getAniper()+" precla: "+pladet.getPk().getCodprecla()+" pretip: "+pladet.getPk().getCodpretip()+" codsuc: "+pladet.getPk().getCodsuc()+
								" tippla: "+pladet.getPk().getCodtippla()+" mes: "+pladet.getPk().getMesper()+" numdiv: "+pladet.getPk().getNumdiv()+" numpreafi: "+pladet.getPk().getNumpreafi()+
								" ordpreafi: "+pladet.getPk().getOrdpreafi()+" ruc: "+pladet.getPk().getRucemp()+" secpla: "+pladet.getPk().getSecpla()+" tipper: "+pladet.getPk().getTipper()+
								" codpagpen: "+pladet.getCodpagpen()+" numafi: "+pladet.getNumafi()+" valtotdiv: "+pladet.getValtotdiv());
						
						log.info("Cambio el estado de la planilla. "+pladet.getNumafi());
				     }
				
//			}
				
		} catch (EjecucionProcedimientoException e) {
			// TODO Auto-generated catch block
			log.info("Error de EjecucionProcedimientoException");
			throw new AnulaPlanillaHlException(e.getMessage(),e);			
		} catch (CambioEstadoDividendoException e) {
			// TODO Auto-generated catch block
			log.info("Error de CambioEstadoDividendoException");
			throw new AnulaPlanillaHlException(e.getMessage(),e);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Error de Exception");
			throw new AnulaPlanillaHlException(e.getMessage(),e);			
			//e.printStackTrace();
		}
		
	}

}
		

