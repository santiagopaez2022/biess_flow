package ec.gov.iess.planillaspq.storeprocedure;

import java.util.Map;

import javax.ejb.Remote;

import ec.gov.iess.commons.dao.excepciones.EjecucionProcedimientoException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;

@Remote
public interface CambioEstadoComprobanteSpRemote {
	@SuppressWarnings("unchecked")
	public Map ejecutarCambioEstadoComprobante(ComprobantePagoPla compag, String Observacion,String cedfun,String nueest) throws EjecucionProcedimientoException;
}
