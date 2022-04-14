/**
 * 
 */
package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.MSPBiessException;

/**
 * @author christian.gomez
 *
 */
@Local
public interface ConsultaMSPDiscapacitadosServicio {
	
	/**
	 * Consumo al servicio web de discapacitados desde el ESB
	 * 
	 * @param cedula
	 * @param direccionIP
	 * @return
	 * @throws MSPBiessException
	 */
	Boolean consultarWSDiscESB(String cedula, String direccionIP) throws MSPBiessException;

	/**
	 * Metodo que obtiene informacion de una persona discapacitada desde el MSP, dado su numero de cedula.
	 * 
	 * @param cedula
	 * @param direccionIP
	 * @return
	 * @throws MSPBiessException
	 */
	public Boolean consultarWSDisc(String cedula, String direccionIP) throws MSPBiessException;
}
