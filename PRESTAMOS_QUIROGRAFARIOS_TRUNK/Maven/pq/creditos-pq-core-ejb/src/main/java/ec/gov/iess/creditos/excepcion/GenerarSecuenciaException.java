package ec.gov.iess.creditos.excepcion;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class GenerarSecuenciaException extends RuntimeException {

	private static final long serialVersionUID = -206963896738800818L;

	public GenerarSecuenciaException() {
		super();
	}

	public GenerarSecuenciaException(String msg) {
		super(msg);
	}

	public GenerarSecuenciaException(Throwable throwable) {
		super(throwable);
	}

	public GenerarSecuenciaException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
