/**
 * 
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.TipoAccion;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface TipoAccionDao extends GenericDao<TipoAccion, Long> {
	
}
