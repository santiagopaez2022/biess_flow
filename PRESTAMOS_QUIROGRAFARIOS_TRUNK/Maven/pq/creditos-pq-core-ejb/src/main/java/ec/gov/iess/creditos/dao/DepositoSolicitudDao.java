/**
 * 
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DepositoSolicitud;
import ec.gov.iess.dao.GenericDao;

/**
 * @author caldaz
 *
 */
@Local
public interface DepositoSolicitudDao extends
		GenericDao<DepositoSolicitud, Long> {

}
