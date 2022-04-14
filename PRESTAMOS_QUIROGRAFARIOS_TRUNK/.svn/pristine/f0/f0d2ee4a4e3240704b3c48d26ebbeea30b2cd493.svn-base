package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;

/**
 * Servicio local para obtener informacion de garantias comprometidas del SAC
 * 
 * @author hugo.mora
 * @date 2018/08/31
 *
 */
@Local
public interface GarantiasPorOperacionSacServicio {

	/**
	 * Permite obtener las garantias por numero de operacion
	 * 
	 * @param operacionSacRequest
	 * @return
	 * @throws GarantiasSacException
	 */
	InformacionGarantias obtenerGarantiasPorOperacion(OperacionSacRequest operacionSacRequest) throws GarantiasSacException;
	
	

}
