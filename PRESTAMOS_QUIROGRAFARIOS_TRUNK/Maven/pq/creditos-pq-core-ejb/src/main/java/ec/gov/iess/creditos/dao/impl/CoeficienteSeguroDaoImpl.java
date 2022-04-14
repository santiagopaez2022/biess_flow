/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CoeficienteSeguroDao;
import ec.gov.iess.creditos.enumeracion.TipoSeguro;
import ec.gov.iess.creditos.modelo.persistencia.CoeficienteSeguro;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class CoeficienteSeguroDaoImpl extends
		GenericEjbDao<CoeficienteSeguro, Long> implements CoeficienteSeguroDao {

	private static LoggerBiess log = LoggerBiess.getLogger(CoeficienteSeguroDaoImpl.class);

	/**
	 * 
	 */
	public CoeficienteSeguroDaoImpl() {
		super(CoeficienteSeguro.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.CoeficienteSeguroDao#consultarTipoSolicitudTipoSeguro(java.lang.Long,
	 *      java.lang.Integer, ec.gov.iess.creditos.pq.constantes.TipoSeguro,
	 *      java.util.Date)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public CoeficienteSeguro consultarTipoSolicitudTipoSeguro(
			Long codtipsolser, Integer anio, TipoSeguro tipoSeguro, Date fecha) {
		Query q = em
				.createNamedQuery("CoeficienteSeguro.findTipoSeguroTipoPrestacion");
		q.setParameter("codtipsolser", codtipsolser);
		q.setParameter("fecha", fecha);
		q.setParameter("tipoSeguro", tipoSeguro.getId());
		q.setParameter("anio", anio);

		try {
			return (CoeficienteSeguro) q.getSingleResult();
		} catch (NoResultException e) {
			log.error("DAO : No existe coeficiente : " + tipoSeguro);
			return null;
		}
	}

}
