package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;
/**
 * Clase exception para Generar Liquidacion 
 * @author Palvarez
 * 
 */
@ApplicationException(rollback = true)
public class GenerarLiquidacionException extends Exception {

	private static final long serialVersionUID = -7891990970038378853L;

	public GenerarLiquidacionException() {
	}

	public GenerarLiquidacionException(String arg0) {
		super(arg0);
	}

	public GenerarLiquidacionException(Throwable arg0) {
		super(arg0);
	}

	public GenerarLiquidacionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}