package ec.gov.iess.planillaspq.storeprocedure;

import java.util.Map;

import javax.ejb.Local;

import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;

@Local
public interface AnulaPlanillaSpLocal {
	@SuppressWarnings("unchecked")
	public Map ejecutarAnulacionPlanillas(Planillas pla) throws EjecucionProcedimientoException;
}
