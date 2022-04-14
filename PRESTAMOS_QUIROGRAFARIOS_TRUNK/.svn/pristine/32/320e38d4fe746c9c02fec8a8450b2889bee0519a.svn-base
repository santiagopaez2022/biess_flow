/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.creditos.pq.excepcion.SecuenciaCreditoException;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface VariablePrestamoServicio {

	/**
	 * Obtiene e incrementa el secuencial de una varidad de credito
	 * 
	 * @param idTipocredito
	 *            identificador del tipo de credito
	 * @param idVariedadCredito
	 *            identificador de la variedad de cerdito
	 * @return un objeto de modelo {@link VariablePrestamo} caso contrario null
	 * @author cvillarreal
	 * @throws SecuenciaCreditoException
	 */
	public VariablePrestamo obtenerSecuencial(int idTipocredito, int idVariedadCredito)
			throws SecuenciaCreditoException;
}
