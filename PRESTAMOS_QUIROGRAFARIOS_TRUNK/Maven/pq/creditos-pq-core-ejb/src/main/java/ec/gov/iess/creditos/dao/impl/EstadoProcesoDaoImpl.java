package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.EstadoProcesoDao;
import ec.gov.iess.creditos.modelo.persistencia.EstadoProceso;
import ec.gov.iess.dao.ejb.GenericEjbDao;

@Stateless
public class EstadoProcesoDaoImpl extends GenericEjbDao<EstadoProceso, Long>
		implements EstadoProcesoDao {

	private LoggerBiess log = LoggerBiess.getLogger(EstadoProcesoDaoImpl.class);

	public EstadoProcesoDaoImpl() {
		super(EstadoProceso.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.EstadoProcesoDao#consultarEstadosTipoSolicitudEntreOrden(java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<EstadoProceso> consultarEstadosTipoSolicitudEntreOrden(
			Long tipoSolicitud, Long ordenInicial, Long ordenFinal) {

		log.debug("Consulta estados");

		Query q = em
				.createNamedQuery("EstadoProceso.fyndEntreEstadosByTipoSolicitud");
		q.setParameter("tipoSolicitud", tipoSolicitud);
		q.setParameter("orden1", ordenInicial);
		q.setParameter("orden2", ordenFinal);

		return q.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.EstadoProcesoDao#consultarTipoSolicitudOrden(java.lang.Long,
	 *      java.lang.Long)
	 */
	public EstadoProceso consultarTipoSolicitudOrden(Long tipoSolicitud,
			Long orden) {

		Query q = em
				.createNamedQuery("EstadoProceso.fyndEstadoByTipoSolicitud");
		q.setParameter("tipoSolicitud", tipoSolicitud);
		q.setParameter("orden", orden);

		return (EstadoProceso) q.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.EstadoProcesoDao#consultarByPk(java.lang.Long)
	 */
	public EstadoProceso consultarByPk(Long id) {
		log.debug("Consulta ID : " + id);
		return load(id);
	}

}
