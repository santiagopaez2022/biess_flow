/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.TipoSolicitanteDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitante;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class TipoSolicitanteDaoImpl extends GenericEjbDao<TipoSolicitante, Long>
		implements TipoSolicitanteDao {

	/**
	 * @param type
	 */
	public TipoSolicitanteDaoImpl() {
		super(TipoSolicitante.class);
	}

}
