package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;

/**
 * Interfaz local para consumo de servicio de creditos empleador
 * 
 * @author paul.sampedro
 *
 */
@Local
public interface CreditoPQEmpSacServicioLocal {

	/**
	 * Devuelve un listado de creditos de clientes con saldo empleador
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return Devuelve un objeto List<CreditoExigibleDto>
	 * @throws PQExigibleException
	 */
	List<CreditoExigibleDto> obtenerListaPQOperEmp(String numeroIdentificacion) throws PQExigibleException;

	/**
	 * Obtener informacion del credito clientes con saldo empleador
	 * 
	 * @param numeroIdentificacion
	 * @return
	 * @throws PQExigibleException
	 */
	InformacionPQExigible obtenerInfoPqOperEmp(String numeroIdentificacion) throws PQExigibleException;

}
