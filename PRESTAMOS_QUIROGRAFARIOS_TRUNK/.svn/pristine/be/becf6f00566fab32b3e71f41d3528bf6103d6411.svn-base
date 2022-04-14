package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

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
@Local
public interface CancelaOperacionSacServicio {

	/**
	 * Metodo que permite obtener la liquidacion de un credito
	 * 
	 * @param operacionSacRequest
	 * @return
	 * @throws LiquidacionFondosReservaExcepcion
	 * @throws CancelacionOperacionSacException 
	 */
	CreditoExigibleDto cancelarCredito(OperacionSacRequest operacionSacRequest) throws CancelacionOperacionSacException;

}
