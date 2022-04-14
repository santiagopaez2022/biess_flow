/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CreditoQuirografarioHostDao;
import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.modelo.persistencia.CreditoQuirografarioHost;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class CreditoQuirografarioHostDaoImpl extends
		GenericEjbDao<CreditoQuirografarioHost, Serializable> implements
		CreditoQuirografarioHostDao {

	private LoggerBiess log = LoggerBiess.getLogger(CreditoQuirografarioHostDaoImpl.class);

	public CreditoQuirografarioHostDaoImpl() {
		super(CreditoQuirografarioHost.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.CreditoQuirografarioHostDao#getCreditosQuirografariosHost(java.lang.String,
	 *      java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<CreditoQuirografarioHost> getCreditosQuirografariosHost(
			String cedula, List<String> listaCreditos)
			throws ListaVaciaException {

		if (listaCreditos == null || listaCreditos.size() == 0) {
			StringBuffer msg = new StringBuffer();
			msg.append("No existe codigo de creditos quirografarios HOST");
			log.error(msg.toString());
			throw new ListaVaciaException(msg.toString());
		}

		Query q = em
				.createNamedQuery("CreditoQuirografarioHost.findCreditosCedulaTiposCredito");
		q.setParameter("cedula", cedula);
		q.setParameter("listaCredistos", listaCreditos);

		return q.getResultList();
	}

}
