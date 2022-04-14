/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.excepcion.PeticionSacException;

/**
 * @author roberto.guizado
 *
 */
@Local
public interface PeticionRestSacServicio {

	/**
	 * Permite obtener la respuesta contra los servicios rest del sac
	 * 
	 * @param request
	 * @param urlServicioRest
	 * @return
	 * @throws PeticionSacException
	 */
	String obtenerRespuestaSac(Object request, String urlServicioRest) throws PeticionSacException;

}
