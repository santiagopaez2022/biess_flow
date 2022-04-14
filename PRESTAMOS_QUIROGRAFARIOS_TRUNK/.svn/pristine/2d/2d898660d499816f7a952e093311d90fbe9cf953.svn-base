package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.CreacionOperacionSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;

/**
 * Servicio local para consumo de creditos pq exigibles desde core negocio
 * 
 * @author hugo.mora
 * @date 2018/09/03
 *
 */
@Local
public interface CreditoPQCrearOperacionSacServicioLocal {

	/**
	 * 
	 * @return
	 * @throws PQExigibleException
	 */
	CreditoExigibleDto obtenerNumeroOperacion(OperacionSacRequest operacionSacRequest) throws CreacionOperacionSacException;

}
