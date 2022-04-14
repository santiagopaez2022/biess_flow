/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.OperacionSacResponse;
import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
@Local
public interface DatoOperacionSacLocal {
	/**
	 * Permite consultar todos lo datos de una operacion
	 * 
	 * @param operacion
	 * @return
	 */
	OperacionSacResponse obtenerDatosOperacion(OperacionSacRequest operacion) throws PeticionSacException;

	/**
	 * Permite consultar el estado de una operacion
	 * 
	 * @param operacion
	 * @return
	 */
	String obtenerEstadoOperacion(OperacionSacRequest operacion) throws PeticionSacException;

}
