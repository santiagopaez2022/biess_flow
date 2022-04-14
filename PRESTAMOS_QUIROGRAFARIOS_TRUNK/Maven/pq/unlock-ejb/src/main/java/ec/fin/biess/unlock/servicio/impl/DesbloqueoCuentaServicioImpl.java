package ec.fin.biess.unlock.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.unlock.dao.BloqueoUsuarioDao;
import ec.fin.biess.unlock.dao.BloqueoUsuarioHistoricoDao;
import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;
import ec.fin.biess.unlock.modelo.BloqueoUsuarioHistorico;
import ec.fin.biess.unlock.servicio.DesbloqueoCuentaServicio;

@Stateless
public class DesbloqueoCuentaServicioImpl implements DesbloqueoCuentaServicio {

	@EJB
	private transient BloqueoUsuarioDao bloqueoUsuarioDao;
	@EJB
	private transient BloqueoUsuarioHistoricoDao bloqueoUsuarioHistoricoDao;

	@Override
	public BloqueoUsuario getBloqueoUsuario(final String cedula) {
		return bloqueoUsuarioDao.getBloqueoUsuario(cedula);
	}

	@Override
	public void desbloquearCuenta(BloqueoUsuario bloqueoUsuario) throws UnlockException {

		final String OBS = "Se desbloque\u00F3 cuenta via funcionario. ";
		bloqueoUsuario.setIndicadorCuentaBloqueda("N");
		bloqueoUsuario.setNumeroBloqueosParciales(new Integer(0));
		bloqueoUsuario.setNumeroBloqueosTotales(new Integer(0));
		bloqueoUsuario.setToken(null);
		bloqueoUsuario.setFechaBloqueosParciales(null);
		bloqueoUsuario.setFechaDesbloqueos(new Date());
		bloqueoUsuario.setObservacion(OBS + bloqueoUsuario.getObservacion());		
		bloqueoUsuarioDao.actualizar(bloqueoUsuario);	

	}

	@Override
	public List<BloqueoUsuarioHistorico> getListaBloqueoUsuarioHistorico(final String cedula) {
		return bloqueoUsuarioHistoricoDao.getListaBloqueoUsuarioHistorico(cedula);
	}
	
}
