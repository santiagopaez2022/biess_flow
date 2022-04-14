package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;

/**
 * Servicio local para obtener informacion de garantias comprometidas del SAC
 * 
 * @author hugo.mora
 * @date 2018/08/31
 *
 */
@Local
public interface GarantiasSacServicioLocal {

	/**
	 * Devuelve el valor de garantias desde core negocio, cuya informacion es
	 * proporcionada por el sac
	 * 
	 * @param numeroIdentificacion
	 * @return
	 * @throws GarantiasSacException
	 */
	public InformacionGarantias obtenerGarantias(String numeroIdentificacion) throws GarantiasSacException;

}
