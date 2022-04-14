package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NovarCreditoQuirografarioException extends Exception {

	private static final long serialVersionUID = -1813644503830119460L;

	public NovarCreditoQuirografarioException() {
		super();
	}

	public NovarCreditoQuirografarioException(String msg) {
		super(msg);
	}

	public NovarCreditoQuirografarioException(Throwable throwable) {
		super(throwable);
	}

	public NovarCreditoQuirografarioException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
