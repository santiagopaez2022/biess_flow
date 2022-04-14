/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.GarantiaPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.GarantiaPrestamoPK;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface GarantiaPrestamoDao extends
		GenericDao<GarantiaPrestamo, GarantiaPrestamoPK> {

	/**
	 * Método que retorna el valor disponible comprometido de un PQ
	 * @param cedula
	 * @return
	 */
	public BigDecimal obtenerDisponibleComprometidoFondosReservaPQ(String cedula);
}
