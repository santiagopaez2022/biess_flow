package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.SimulacionCancelacionSacException;

/**
 * Servicio local para obtener informacion de la liquidacion de un credito desde
 * el core SAC
 * 
 * @author roberto.guizado
 * @date 2018/10/12
 *
 */
@Local
public interface SimulacionCancelacionSacServicio {

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
