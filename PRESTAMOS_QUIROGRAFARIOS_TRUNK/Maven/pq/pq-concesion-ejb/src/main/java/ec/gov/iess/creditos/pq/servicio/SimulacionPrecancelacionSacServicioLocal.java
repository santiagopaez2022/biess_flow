package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;

/**
 * Servicio local para consumo de creditos pq exigibles desde core negocio
 * 
 * @author roberto.guizado
 * @date 2018/11/19
 *
 */
@Local
public interface SimulacionPrecancelacionSacServicioLocal {

	/**
	 * Devuelve un listado de creditos exigibles desde core negocio del SAC
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return Devuelve un objeto List<CreditoExigibleDto>
	 * @throws PQExigibleException
	 */
	List<CreditoExigibleDto> obtenerListaExigiblePrecancelacion(String numeroIdentificacion) throws PQExigibleException;

}
