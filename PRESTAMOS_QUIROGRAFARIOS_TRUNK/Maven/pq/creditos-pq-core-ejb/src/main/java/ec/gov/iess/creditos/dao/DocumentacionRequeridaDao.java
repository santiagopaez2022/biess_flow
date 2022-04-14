package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DocumentacionRequerida;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * @author cvillarreal
 * 
 */
@Local
public interface DocumentacionRequeridaDao extends
		GenericDao<DocumentacionRequerida, Long> {

	

}