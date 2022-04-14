package ec.gov.iess.planillaspq.storeprocedure;

import java.util.Map;

import javax.ejb.Remote;
import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;



@Remote
public interface AnulaPlanillaSpRemote {
	@SuppressWarnings("unchecked")
	public Map ejecutarAnulacionPlanillas(Planillas pla) throws EjecucionProcedimientoException;
}
