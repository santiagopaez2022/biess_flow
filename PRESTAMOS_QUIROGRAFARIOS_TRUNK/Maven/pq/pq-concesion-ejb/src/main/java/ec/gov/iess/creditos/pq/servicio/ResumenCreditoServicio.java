package ec.gov.iess.creditos.pq.servicio;
import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

/**
 * @author jasanchez
 * 
 */

@Local
public interface ResumenCreditoServicio {

	public void registrarResumenCredito(DatosCredito credito,Prestamo prestamo);

}
