/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.NovedadAnulacionDao;
import ec.gov.iess.creditos.modelo.persistencia.NovedadAnulacion;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class NovedadAnulacionDaoImpl extends
		GenericEjbDao<NovedadAnulacion, Long> implements NovedadAnulacionDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.dao.GenericDao#load(java.io.Serializable)
	 */
	public NovedadAnulacionDaoImpl() {
		super(NovedadAnulacion.class);
	}

}
