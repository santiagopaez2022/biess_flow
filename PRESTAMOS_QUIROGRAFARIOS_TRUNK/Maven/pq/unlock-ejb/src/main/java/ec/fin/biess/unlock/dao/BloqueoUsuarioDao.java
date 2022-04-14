package ec.fin.biess.unlock.dao;

import javax.ejb.Local;

import ec.fin.biess.unlock.modelo.BloqueoUsuario;

@Local
public interface BloqueoUsuarioDao extends GenericDao<BloqueoUsuario> {

	/**
	 * @param cedula
	 * @return BloqueoUsuario dado la cedula
	 */
	BloqueoUsuario getBloqueoUsuario(final String cedula);

	/**
	 * Metodo que retorna la entidad dado su id.
	 * 
	 * @param cedula
	 * @return BloqueoUsuario
	 */
	BloqueoUsuario getBloqueoUsuarioPorId(final String cedula);

}
