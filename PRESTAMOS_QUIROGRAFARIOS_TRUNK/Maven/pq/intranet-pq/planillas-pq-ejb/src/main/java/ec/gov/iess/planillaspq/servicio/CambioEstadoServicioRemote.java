package ec.gov.iess.planillaspq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteGenericoException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoDividendoException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;


@Remote
public interface CambioEstadoServicioRemote {

public void CambioEstadoComprobante(Planillas planilla, String Observacion,String TipoComp,String cedfun) throws CambioEstadoComprobanteException;
public void CambioEstadoDividendo(PlanillaPrestamosDetalle pladet, String Observacion) throws CambioEstadoDividendoException;
public void CambioEstadoComprobanteGenerico(ComprobantePagoPla compag, String Observacion,String TipoComp,String cedfun,String nueest)
throws CambioEstadoComprobanteGenericoException;

}
		

