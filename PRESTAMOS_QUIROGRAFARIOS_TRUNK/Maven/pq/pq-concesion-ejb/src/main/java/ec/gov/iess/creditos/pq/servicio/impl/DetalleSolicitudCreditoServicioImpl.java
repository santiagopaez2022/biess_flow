/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DetalleSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleSolicitud;
import ec.gov.iess.creditos.pq.servicio.DetalleSolicitudCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.DetalleSolicitudCreditoServicioRemote;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless
public class DetalleSolicitudCreditoServicioImpl implements
		DetalleSolicitudCreditoServicio, DetalleSolicitudCreditoServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(DetalleSolicitudCreditoServicioImpl.class);

	@EJB
	private DetalleSolicitudDao detalleSolicitudDao;

	/**
	 * 
	 */
	public DetalleSolicitudCreditoServicioImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DetalleSolicitudCreditoServicio#actualizarDetalleSolicitud(ec.gov.iess.creditos.pq.modelo.persistencia.DetalleSolicitud)
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void actualizarDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
		detalleSolicitudDao.update(detalleSolicitud);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DetalleSolicitudCreditoServicio#obtenerDetalleSolicitudPorCedulaNut(java.lang.String,
	 *      java.lang.String)
	 */
	public DetalleSolicitud obtenerDetalleSolicitudPorCedulaNut(String cedula,
			String nut, List<Long> tipoSolicitud, List<String> listaEstado) {
		return detalleSolicitudDao.obtenerPorCedulaNut(cedula, nut,
				tipoSolicitud, listaEstado);
	}
}