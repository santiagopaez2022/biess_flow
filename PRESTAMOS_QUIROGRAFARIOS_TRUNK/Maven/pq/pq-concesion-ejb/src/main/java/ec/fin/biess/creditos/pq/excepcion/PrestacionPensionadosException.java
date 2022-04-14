/**
 * 
 */
package ec.fin.biess.creditos.pq.excepcion;

/**
 * Manejo de Exceptions para {@link PrestacionPensionado}
 * @author christian.gomez
 *
 */
public class PrestacionPensionadosException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 564568784289481L;

	public PrestacionPensionadosException(){
		
	}
	public PrestacionPensionadosException(String arg0){
		super(arg0);
	}
	
	public PrestacionPensionadosException(Throwable arg0){
		super(arg0);
	}
	
	public PrestacionPensionadosException(String arg0, Throwable arg1){
		super(arg0, arg1);
	}
}
