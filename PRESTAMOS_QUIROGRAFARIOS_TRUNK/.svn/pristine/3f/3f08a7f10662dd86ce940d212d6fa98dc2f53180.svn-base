package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.SubProcesoRealizadoSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.SubProcesoRealizadoSolicitud;
import ec.gov.iess.dao.ejb.GenericEjbDao;
@Stateless
public class SubProcesoRealizadoSolicitudDaoImpl extends GenericEjbDao<SubProcesoRealizadoSolicitud, Long> implements
		SubProcesoRealizadoSolicitudDao {

	SubProcesoRealizadoSolicitudDaoImpl(){
		super(SubProcesoRealizadoSolicitud.class);
	}

	public SubProcesoRealizadoSolicitud findByCodtipsolserNumsolserSubprocesotiposol(
			Long idTipoSolicitud, Long numeroSolicitud, Long subprocesotiposol) {
		Query query = null;
		query = em.createNamedQuery("SubProcesoRealizadoSolicitud.findByCodtipsolserNumsolserSubprocesotiposol");
		query.setParameter("idTipoSolicitud", idTipoSolicitud);
		query.setParameter("numeroSolicitud", numeroSolicitud);
		query.setParameter("subprocesotiposol", subprocesotiposol);
		
		return (SubProcesoRealizadoSolicitud)query.getSingleResult();
	}
}
