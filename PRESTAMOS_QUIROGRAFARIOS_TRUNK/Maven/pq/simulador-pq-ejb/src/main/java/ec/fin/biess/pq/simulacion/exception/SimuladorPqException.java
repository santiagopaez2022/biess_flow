package ec.fin.biess.pq.simulacion.exception;

/**
 * Excepcion para el simulador de PQ
 * 
 * @author hugo.mora
 * @date 2017/05/17
 *
 */
public class SimuladorPqException extends Exception {

	private static final long serialVersionUID = 3195485309006532733L;

	public SimuladorPqException() {
		super();
	}

	public SimuladorPqException(String message) {
		super(message);
	}

	public SimuladorPqException(Throwable throwable) {
		super(throwable);
	}

	public SimuladorPqException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
