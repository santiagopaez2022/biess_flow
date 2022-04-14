package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DetalleFocalizado;

/**
 * Servicio local para logica de negocio de DetalleFocalizado
 * 
 * @author hugo.mora
 *
 */
@Local
public interface DetalleFocalizadoServicioLocal {

	/**
	 * Consulta un DetalleFocalizado dado los datos del prestamo (codprecla, codpretip, numpreafi, ordpreafi) y por
	 * estado
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreaf
	 * @param estado
	 * @return
	 */
	List<DetalleFocalizado> buscarPorPrestamoYEstado(Long codprecla, Long codpretip, Long numpreafi, Long ordpreaf, String estado);

	/**
	 * Guarda un DetalleFocalizado
	 * 
	 * @param detalleFocalizado
	 */
	void insertarDetalleFocalizado(DetalleFocalizado detalleFocalizado);

}
