package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionPQExigible;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;

/**
 * Servicio remoto para consumo de creditos pq exigibles desde core negocio
 * 
 * @author hugo.mora
 * @date 2018/09/03
 *
 */
@Remote
public interface CreditoPQExigibleSacServicioRemoto {

	/**
	 * Indica si el asegurado se encuentra en mora en base a la informacion obtenida
	 * desde core negocio del SAC
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return
	 * @throws PQExigibleException
	 */
	boolean tieneMora(String numeroIdentificacion) throws PQExigibleException;

	/**
	 * Devuelve el valor de saldo de creditos del asegurado en base a la informacion
	 * obtenida desde core negocio del SAC
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return
	 * @throws PQExigibleException
	 */
	BigDecimal obtenerSaldoCreditos(String numeroIdentificacion) throws PQExigibleException;

	/**
	 * Devuelve un listado de creditos exigibles desde core negocio del SAC
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return Devuelve un objeto List<CreditoExigibleDto>
	 * @throws PQExigibleException
	 */
	List<CreditoExigibleDto> obtenerListaPQExigibles(String numeroIdentificacion) throws PQExigibleException;

	/**
	 * Obtener informacion del credito exigible
	 * 
	 * @param numeroIdentificacion
	 * @return
	 * @throws PQExigibleException
	 */
	InformacionPQExigible obtenerInfoPqExigible(String numeroIdentificacion) throws PQExigibleException;

}
