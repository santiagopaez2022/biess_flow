package ec.fin.biess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionListaObservadosException;

/**
 * Servicio Validar si un Afiliado/Refugiado esta en alguna lista de observados nacional o internacional.
 * 
 * @author Diego Iza.
 * 
 */
@Local
public interface PrecalificacionListaObservadosServicioLocal {

	/**
	 * Validaa si un Afiliado/Refugiado esta en alguna lista de observados nacional o internaciona.
	 * 
	 * @param precalificacion
	 *            - datos de la precalificacion.
	 * 
	 * @return {@link PrecalificacionExcepcion}
	 * 
	 * @throws PrecalificacionExcepcion
	 *             - excepcion.
	 */
	public Precalificacion obtenerPrecalificacionListaObservados(Precalificacion precalificacion)
			throws PrecalificacionListaObservadosException;
}