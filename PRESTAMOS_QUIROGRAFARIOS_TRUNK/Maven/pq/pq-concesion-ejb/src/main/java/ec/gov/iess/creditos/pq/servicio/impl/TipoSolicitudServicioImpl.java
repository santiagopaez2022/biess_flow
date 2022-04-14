/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.TipoSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoSolicitudCredito;
import ec.gov.iess.creditos.pq.servicio.TipoSolicitudServicio;
import ec.gov.iess.creditos.pq.servicio.TipoSolicitudServicioRemote;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class TipoSolicitudServicioImpl implements TipoSolicitudServicio,
		TipoSolicitudServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(TipoSolicitudServicioImpl.class);

	@EJB
	TipoSolicitudDao tipoSolicitudDao;

	/**
	 * 
	 */
	public TipoSolicitudServicioImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.TipoSolicitudDao#actualizarNumeroSolicitudCredito(ec.gov.iess.creditos.pq.modelo.persistencia.TipoSolicitudCredito)
	 */
	public TipoSolicitudCredito actualizarNumeroSolicitudCredito(
			TipoSolicitudCredito tipoSolicitudCredito) {

		long nuevoSecuencia = tipoSolicitudCredito.getNumsectipsol() + 1L;

		tipoSolicitudCredito.setNumsectipsol(nuevoSecuencia);
		tipoSolicitudDao.update(tipoSolicitudCredito);
		return tipoSolicitudCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.dao.TipoSolicitudDao#consultarTipoSolicitud(int,
	 *      int)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TipoSolicitudCredito consultarTipoSolicitud(int idTipoCredito,
			int idVariedadCrdeito) {
		return tipoSolicitudDao.consultarTipoSolicitud(idTipoCredito,
				idVariedadCrdeito);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.TipoSolicitudServicio#consultarTipoSolicitud(java.lang.Long)
	 */
	public TipoSolicitudCredito consultarTipoSolicitud(Long idTipoSolicitud) {

		return tipoSolicitudDao.consultarTipoSolicitud(idTipoSolicitud);
	}
}
