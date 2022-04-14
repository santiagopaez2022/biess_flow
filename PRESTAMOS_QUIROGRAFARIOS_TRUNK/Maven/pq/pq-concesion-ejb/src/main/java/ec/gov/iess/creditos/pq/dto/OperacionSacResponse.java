/**
 * 
 */
package ec.gov.iess.creditos.pq.dto;

/**
 * The Class OperacionSacResponse.
 *
 * @author roberto.guizado
 */
public class OperacionSacResponse extends MensajeSacResponseDto {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 99898001545789645L;
	
	/** Permite obtener la informacion de los creditos. */
	private CreditoExigibleDto operacionResponse;
	
	/**
	 * Instantiates a new operacion sac response.
	 */
	public OperacionSacResponse() {		
		//constructor vacio
	}
	
	/**
	 * Gets the operacion response.
	 *
	 * @return the operacion response
	 */
	public CreditoExigibleDto getOperacionResponse() {
		return operacionResponse;
	}

	/**
	 * Sets the operacion response.
	 *
	 * @param operacionResponse the new operacion response
	 */
	public void setOperacionResponse(final CreditoExigibleDto operacionResponse) {
		this.operacionResponse = operacionResponse;
	}
}
