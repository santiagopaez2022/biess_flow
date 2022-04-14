package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.excepcion.LiquidacionFondosReservaExcepcion;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.CancelacionOperacionSacException;

/**
 * Servicio local para cancela liquidacion de un credito desde el core SAC
 * 
 * @author roberto.guizado
 * @date 2018/10/15
 *
 */
@Remote
public interface CancelaOperacionSacServicioRemoto {

	/**
	 * Metodo que permite obtener la liquidacion de un credito
	 * 
	 * @param operacionSacRequest
	 * @return
	 * @throws LiquidacionFondosReservaExcepcion
	 */
	CreditoExigibleDto cancelarCredito(OperacionSacRequest operacionSacRequest) throws CancelacionOperacionSacException;

}
