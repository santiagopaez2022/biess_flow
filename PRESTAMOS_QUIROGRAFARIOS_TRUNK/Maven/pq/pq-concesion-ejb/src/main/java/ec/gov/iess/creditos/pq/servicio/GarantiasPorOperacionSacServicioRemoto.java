package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;

/**
 * Servicio remoto para obtener informacion de garantias comprometidas del SAC
 * 
 * @author roberto.guizado
 * @date 2018/10/10
 *
 */
@Remote
public interface GarantiasPorOperacionSacServicioRemoto {

	/**
	 * Permite obtener las garantias por numero de operacion
	 * 
	 * @param operacionSacRequest
	 * @return
	 * @throws GarantiasSacException
	 */
	InformacionGarantias obtenerGarantiasPorOperacion(OperacionSacRequest operacionSacRequest) throws GarantiasSacException;

}
