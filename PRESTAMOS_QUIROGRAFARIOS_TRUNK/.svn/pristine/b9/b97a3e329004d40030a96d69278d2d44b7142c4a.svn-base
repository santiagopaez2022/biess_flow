package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.DetalleFocalizadoDao;
import ec.gov.iess.creditos.modelo.persistencia.DetalleFocalizado;
import ec.gov.iess.creditos.pq.servicio.DetalleFocalizadoServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DetalleFocalizadoServicioRemote;

/**
 * Servicio para logica de negocio de DetalleFocalizado
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class DetalleFocalizadoServicioImpl implements DetalleFocalizadoServicioLocal, DetalleFocalizadoServicioRemote {

	@EJB
	private DetalleFocalizadoDao detalleFocalizadoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DetalleFocalizadoServicioLocal#buscarPorPrestamoYEstado(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public List<DetalleFocalizado> buscarPorPrestamoYEstado(Long codprecla, Long codpretip, Long numpreafi, Long ordpreaf, String estado) {
		return detalleFocalizadoDao.buscarPorPrestamoYEstado(codprecla, codpretip, numpreafi, ordpreaf, estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.DetalleFocalizadoServicioLocal#insertarDetalleFocalizado(ec.gov.iess.creditos.
	 * modelo.persistencia.DetalleFocalizado)
	 */
	@Override
	public void insertarDetalleFocalizado(DetalleFocalizado detalleFocalizado) {
		detalleFocalizadoDao.insert(detalleFocalizado);
	}

}
