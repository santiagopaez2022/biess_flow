package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CrearCreditoQuirografarioException extends Exception {

	private static final long serialVersionUID = -1813644503830119460L;

	public CrearCreditoQuirografarioException() {
		super();
	}

	public CrearCreditoQuirografarioException(String msg) {
		super(msg);
	}

	public CrearCreditoQuirografarioException(Throwable throwable) {
		super(throwable);
	}

	public CrearCreditoQuirografarioException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
