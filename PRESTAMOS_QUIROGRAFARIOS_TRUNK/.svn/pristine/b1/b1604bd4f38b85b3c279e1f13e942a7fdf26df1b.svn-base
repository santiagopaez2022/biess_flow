/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ProcesoRealizadoDao;
import ec.gov.iess.creditos.modelo.persistencia.ProcesoRealizado;
import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class ProcesoRealizadoDaoImpl extends GenericEjbDao<ProcesoRealizado, Long>
		implements ProcesoRealizadoDao {

	private LoggerBiess log = LoggerBiess.getLogger(ProcesoRealizadoDaoImpl.class);

	public ProcesoRealizadoDaoImpl() {
		super(ProcesoRealizado.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.ProcesoRealizadoDao#insertarNuevoProcesoRealizado(ec.gov.iess.creditos.pq.modelo.persistencia.ProcesoRealizado)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void insertarNuevoProcesoRealizado(ProcesoRealizado procesoRealizado) {

		log.debug(" Nuevo proceso ");
		log.debug(procesoRealizado.toString());

		insert(procesoRealizado);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.ProcesoRealizadoDao#getProcesoRealizadoSolicitud(ec.gov.iess.creditos.pq.modelo.persistencia.SolicitudCreditoPK)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ProcesoRealizado> getProcesoRealizadoSolicitud(
			SolicitudCreditoPK solicitudCreditoPK) {

		log.debug("Lista de procesos.");
		log.debug("IdTipoSolicitud : " + solicitudCreditoPK.getCodtipsolser());
		log.debug("numero solicitud : " + solicitudCreditoPK.getNumsolser());

		Query q = em.createNamedQuery("ProcesoRealizado.findbySolicitud");
		q.setParameter("idTipoSolicitud", solicitudCreditoPK.getCodtipsolser());
		q.setParameter("numeroSolicitud", solicitudCreditoPK.getNumsolser());

		return q.getResultList();
	}

}
