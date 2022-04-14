package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

/**
 * Excepcion para errores al crear la cabecera del credito
 * 
 * @author hugo.mora
 *
 */
@ApplicationException(rollback = true)
public class CabeceraCreditoQuirografarioException extends Exception {

	private static final long serialVersionUID = 1L;

	public CabeceraCreditoQuirografarioException() {
		super();
	}

	public CabeceraCreditoQuirografarioException(String msg) {
		super(msg);
	}

	public CabeceraCreditoQuirografarioException(Throwable throwable) {
		super(throwable);
	}

	public CabeceraCreditoQuirografarioException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
