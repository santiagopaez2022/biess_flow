package ec.gov.iess.planillaspq.storeprocedure;

import java.util.Map;

import javax.ejb.Local;

import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;

@Local
public interface CambioEstadoComprobanteSpLocal {
	@SuppressWarnings("unchecked")
	public Map ejecutarCambioEstadoComprobante(ComprobantePagoPla compag, String Observacion,String cedfun,String nueest) throws EjecucionProcedimientoException;
}
