/**
 * 
 */
package ec.gov.iess.creditos.pq.excepcion;

/**
 * Clase para manejar las excepciones que se producen al realizar los procesos de verificacion de listas de observados
 * 
 * @author diego.iza
 * 
 */
@SuppressWarnings("serial")
public class PrecalificacionListaObservadosException extends Exception {

	public PrecalificacionListaObservadosException() {
		super();
	}

	public PrecalificacionListaObservadosException(String message) {
		super(message);
	}

	public PrecalificacionListaObservadosException(Throwable throwable) {
		super(throwable);
	}

	public PrecalificacionListaObservadosException(String message, Throwable throwable) {
		super(message, throwable);
	}
}