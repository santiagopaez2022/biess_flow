package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

/**
 * Clase exception para Insertar Cabecera Liquidacion Exception
 * 24/08/2010: rtituana se agrego ApplicationException
 * @author Palvarez
 * 
 */
@ApplicationException(rollback = true)
public class InsertarDetalleLiquidacionException extends Exception {

	private static final long serialVersionUID = -7891990970038378853L;

	public InsertarDetalleLiquidacionException() {
	}

	public InsertarDetalleLiquidacionException(String arg0) {
		super(arg0);
	}

	public InsertarDetalleLiquidacionException(Throwable arg0) {
		super(arg0);
	}

	public InsertarDetalleLiquidacionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}