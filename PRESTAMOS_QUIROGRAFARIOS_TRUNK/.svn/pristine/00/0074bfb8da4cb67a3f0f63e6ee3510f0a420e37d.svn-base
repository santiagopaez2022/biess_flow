package ec.fin.biess.unlock.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;
import ec.fin.biess.unlock.modelo.BloqueoUsuarioHistorico;

@Local
public interface DesbloqueoCuentaServicio {

	/**
	 * Retorna un objeto BloqueoUsuario dado su cedula
	 * 
	 * @param cedula
	 * @return
	 */
	BloqueoUsuario getBloqueoUsuario(final String cedula);

	/**
	 * Funcion que procesa el desbloqueo del usuario
	 * 
	 * @param cedula
	 * @throws UnlockException 
	 */
	void desbloquearCuenta(BloqueoUsuario bloqueoUsuario) throws UnlockException;

	/**
	 * Funcion que devuelve todo el hitorico de bloqueos de usuario
	 * 
	 * @param cedula
	 * @return
	 */
	List<BloqueoUsuarioHistorico> getListaBloqueoUsuarioHistorico(final String cedula);

}
