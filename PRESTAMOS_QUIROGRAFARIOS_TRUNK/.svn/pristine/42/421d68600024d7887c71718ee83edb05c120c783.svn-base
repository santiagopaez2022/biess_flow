/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.AgenciaDao;
import ec.gov.iess.creditos.modelo.persistencia.Agencia;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class AgenciaDaoImpl extends GenericEjbDao<Agencia, String> implements
		AgenciaDao {

	private static final LoggerBiess log = LoggerBiess.getLogger(AgenciaDaoImpl.class);

	public AgenciaDaoImpl() {
		super(Agencia.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.AgenciaDao#consultarAgenciaPorProvincia(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Agencia consultarAgenciaPorProvincia(String idProvincia) {
		
		log.debug(" DAO :  consulta agencia id provincia:" + idProvincia);
		
		Query q = em.createNamedQuery("Agencia.buscarPorProvincia");
		q.setParameter("idProvincia", idProvincia);
		
		try {
			return (Agencia)q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}

}
