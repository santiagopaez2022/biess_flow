package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.BloqueoUsuarioCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.BloqueoUsuario;
import ec.gov.iess.creditos.pq.servicio.BloqueoUsuarioServicioLocal;
import ec.gov.iess.creditos.pq.servicio.BloqueoUsuarioServicioRemote;

/**
 * Servicio para BloqueoUsuario
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class BloqueoUsuarioServicioImpl implements BloqueoUsuarioServicioLocal, BloqueoUsuarioServicioRemote {

	@EJB
	private BloqueoUsuarioCreditoDao bloqueoUsuarioDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.BloqueoUsuarioServicioLocal#guardarOActualizar(ec.gov.iess.creditos.modelo.
	 * persistencia.BloqueoUsuario)
	 */
	@Override
	public void guardarOActualizar(BloqueoUsuario bloqueoUsuario) {
		bloqueoUsuarioDao.guardarOActualizar(bloqueoUsuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.BloqueoUsuarioServicioLocal#buscarPorCedula(java.lang.String)
	 */
	@Override
	public BloqueoUsuario buscarPorCedula(String cedula) {
		return bloqueoUsuarioDao.load(cedula);
	}

}
