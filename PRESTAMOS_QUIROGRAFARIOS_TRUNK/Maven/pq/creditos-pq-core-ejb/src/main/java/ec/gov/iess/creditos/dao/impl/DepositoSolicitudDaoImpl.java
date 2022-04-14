/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.DepositoSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.DepositoSolicitud;
import ec.gov.iess.dao.ejb.GenericEjbDao;


/**
 * @author caldaz
 *
 */
@Stateless
public class DepositoSolicitudDaoImpl  
extends GenericEjbDao<DepositoSolicitud, Long> implements DepositoSolicitudDao {

	/**
	 * 
	 */
	public DepositoSolicitudDaoImpl() {
		super(DepositoSolicitud.class);
	}

}
