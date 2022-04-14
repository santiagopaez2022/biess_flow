package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gov.iess.creditos.dao.EstadoProcesoDao;
import ec.gov.iess.creditos.modelo.persistencia.EstadoProceso;
import ec.gov.iess.creditos.pq.servicio.EstadoProcesoServicio;
import ec.gov.iess.creditos.pq.servicio.EstadoProcesoServicioRemote;

@Stateless
public class EstadoProcesoServicioImpl implements EstadoProcesoServicio,
		EstadoProcesoServicioRemote {

	//private Logger log = Logger.getLogger(EstadoProcesoServicioImpl.class);

	@EJB
	private EstadoProcesoDao estadoProcesoDao;

	public EstadoProcesoServicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.EstadoProcesoDao#consultarEstadosTipoSolicitudEntreOrden(java.lang.Long,
	 *      java.lang.Long, java.lang.Long)
	 */
	public List<EstadoProceso> consultarEstadosTipoSolicitudEntreOrden(
			Long tipoSolicitud, Long ordenInicial, Long ordenFinal) {

		return estadoProcesoDao.consultarEstadosTipoSolicitudEntreOrden(
				tipoSolicitud, ordenInicial, ordenFinal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.EstadoProcesoDao#consultarTipoSolicitudOrden(java.lang.Long,
	 *      java.lang.Long)
	 */
	public EstadoProceso consultarTipoSolicitudOrden(Long tipoSolicitud,
			Long orden) {

		return estadoProcesoDao.consultarTipoSolicitudOrden(tipoSolicitud,
				orden);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.EstadoProcesoServicio#consultarEstadoProcesoByPk(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EstadoProceso consultarEstadoProcesoByPk(Long id) {

		return estadoProcesoDao.consultarByPk(id);
	}

}
