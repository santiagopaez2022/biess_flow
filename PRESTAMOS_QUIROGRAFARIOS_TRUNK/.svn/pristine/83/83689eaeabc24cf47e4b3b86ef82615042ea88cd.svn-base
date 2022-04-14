package ec.gov.iess.creditos.dao.impl;

import javax.persistence.Query;

import ec.gov.iess.creditos.dao.PrestamoVistaDao;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoVista;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoVistaPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

public class PrestamoVistaDaoImpl
extends GenericEjbDao<PrestamoVista, PrestamoVistaPK>
implements PrestamoVistaDao{
	public PrestamoVistaDaoImpl() {
		super(PrestamoVista.class);
	}
	
	public Long contarPorPK(PrestamoVistaPK prestamoVistaPK){
		String consultaContarPorPK = 
			" SELECT COUNT(*) FROM PrestamoVista PV where" +
			" PV.PrestamoVistaPK = :PrestamoVistaPK";
		Query query = em.createQuery(consultaContarPorPK);
		query.setParameter("PrestamoVistaPK", prestamoVistaPK);
		return (Long)query.getSingleResult();
	}
	
	public PrestamoVista obtenerPorPK(PrestamoVistaPK prestamoVistaPK){
		String consultaContarPorPK = 
			" SELECT PV FROM PrestamoVista PV where" +
			" PV.PrestamoVistaPK = :PrestamoVistaPK";
		Query query = em.createQuery(consultaContarPorPK);
		query.setParameter("PrestamoVistaPK", prestamoVistaPK);
		return (PrestamoVista)query.getSingleResult();
	}
}
