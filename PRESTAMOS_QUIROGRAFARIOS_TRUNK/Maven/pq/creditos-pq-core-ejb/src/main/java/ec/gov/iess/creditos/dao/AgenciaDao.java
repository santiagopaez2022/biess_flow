/**
 * 
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.Agencia;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface AgenciaDao extends GenericDao<Agencia, String> {

	/**
	 * Consulta la agencia dependiendo de la provincia
	 * 
	 * @param idProvincia identificador de la provincia
	 * @return {@link Agencia} caso contrario null
	 */
	public Agencia consultarAgenciaPorProvincia(String idProvincia);

}
