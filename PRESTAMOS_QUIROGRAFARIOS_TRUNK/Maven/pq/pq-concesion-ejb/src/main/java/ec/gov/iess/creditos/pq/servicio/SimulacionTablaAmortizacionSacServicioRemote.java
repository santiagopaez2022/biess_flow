package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;


@Remote
public interface SimulacionTablaAmortizacionSacServicioRemote {

	/**
	 * Permite obtener la informacion de la tabla de amortizacion por medio de la
	 * cuota-plazo
	 * 
	 * @param request
	 * @return
	 * @throws TablaAmortizacionSacException
	 */
	TablaAmortizacionSac simularTablaAmortizacionSAC(OperacionSacRequest request) throws TablaAmortizacionSacException;

}
