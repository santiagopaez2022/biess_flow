package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.InformacionAportacionResponseDto;
import ec.gov.iess.creditos.pq.excepcion.AportacionesException;

/**
 * Servicio local para consumo del servicio de aportaciones
 * 
 * @author hugo.mora
 * @date 2016/07/06
 *
 */
@Local
public interface AportacionesServicioLocal {

	/**
	 * Devuelve un objeto InformacionAportacionResponseDto para informacion de aportaciones consecutivas, acumuladas y
	 * sueldo promedio
	 * 
	 * @param cedula
	 * @param tipoCalculo
	 * @param numeroUltimasAportaciones
	 * @return
	 * @throws AportacionesException
	 */
	InformacionAportacionResponseDto obtenerInformacionAportaciones(String cedula, String tipoCalculo, int numeroUltimasAportaciones)
			throws AportacionesException;

}
