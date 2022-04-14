package ec.gov.iess.planillaspq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.planillaspq.exceptions.AnulaPlanillaHlException;
import ec.gov.iess.planillaspq.exceptions.AnulaPlanillaHostException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;

@Local
public interface AnulaPlanillaServicioLocal {

	public void AnularPlanillaHost(Planillas planilla, String Observacion,String cedusu)
	throws AnulaPlanillaHostException;
	public void AnularPlanillaHl(Planillas planilla,List<PlanillaPrestamosDetalle> pladetlist, String Observacion,String cedusu)
	throws AnulaPlanillaHlException;
	
		
}
		

