package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.BloqueoUsuario;

/**
 * Servicio remoto para BloqueoUsuario
 * 
 * @author hugo.mora
 *
 */
@Remote
public interface BloqueoUsuarioServicioRemote {

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
