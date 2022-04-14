package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.BloqueoUsuario;

/**
 * Servicio local para BloqueoUsuario
 * 
 * @author hugo.mora
 *
 */
@Local
public interface BloqueoUsuarioServicioLocal {

	/**
	 * Guarda o actualiza un BloqueoUsuario
	 * 
	 * @param bloqueoUsuario
	 */
	void guardarOActualizar(BloqueoUsuario bloqueoUsuario);

	/**
	 * Busca un BloqueoUsuario por cedula
	 * 
	 * @param cedula
	 * @return
	 */
	BloqueoUsuario buscarPorCedula(String cedula);

}
