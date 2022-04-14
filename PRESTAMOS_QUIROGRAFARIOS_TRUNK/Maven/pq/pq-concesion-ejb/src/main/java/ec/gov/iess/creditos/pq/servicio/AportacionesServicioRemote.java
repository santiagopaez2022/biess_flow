package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.InformacionAportacionResponseDto;
import ec.gov.iess.creditos.pq.excepcion.AportacionesException;

/**
 * Servicio remoto para verificacion de mora patronal del empleador
 * 
 * @author hugo.mora
 * @date 29 de mar. de 2016
 *
 */
@Remote
public interface AportacionesServicioRemote {

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
