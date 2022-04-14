package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.CreacionOperacionSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;

/**
 * Servicio remoto para consumo de creditos pq exigibles desde core negocio
 * 
 * @author hugo.mora
 * @date 2018/09/03
 *
 */
@Remote
public interface CreditoPQCrearOperacionSacServicioRemoto {

	/**
	 * 
	 * @return
	 * @throws PQExigibleException
	 */
	CreditoExigibleDto obtenerNumeroOperacion(OperacionSacRequest operacionSacRequest) throws CreacionOperacionSacException;

}
