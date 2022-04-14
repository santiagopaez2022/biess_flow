package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.SimulacionCancelacionSacException;

/**
 * Servicio remoto para consumo de la liquidacion de un credito desde core
 * negocio
 * 
 * @author roberto.guizado
 * @date 2018/10/12
 *
 */
@Remote
public interface SimulacionCancelacionSacServicioRemoto {

	/**
	 * Metodo que permite obtener la liquidacion de un credito
	 * 
	 * @param operacionSacRequest
	 * @return
	 * @throws CalculoLiquidacionExcepcion
	 */
	CreditoExigibleDto simularCancelacion(OperacionSacRequest operacionSacRequest)
			throws SimulacionCancelacionSacException;

}
