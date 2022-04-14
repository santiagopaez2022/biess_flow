package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.BloqueoUsuarioCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.BloqueoUsuario;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Dao para acceso a datos de la entidad BloqueoUsuario
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class BloqueoUsuarioCreditoDaoImpl extends GenericEjbDao<BloqueoUsuario, String> implements BloqueoUsuarioCreditoDao {

	public BloqueoUsuarioCreditoDaoImpl() {
		super(BloqueoUsuario.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.BloqueoUsuarioDao#guardarOActualizar(ec.gov.iess.creditos.modelo.persistencia.
	 * BloqueoUsuario)
	 */
	@Override
	public void guardarOActualizar(BloqueoUsuario bloqueoUsuario) {
		if (bloqueoUsuario == null || bloqueoUsuario.getCedula() == null) {
			super.insert(bloqueoUsuario);
		} else {
			super.update(bloqueoUsuario);
		}
	}

}
