package ec.fin.biess.creditos.pq.excepcion;

public class MontosMaximosException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MontosMaximosException(){
		
	}
	
	public MontosMaximosException(String mensaje){
		super(mensaje);
	}
	
	public MontosMaximosException(Throwable cause){
		super(cause);
	}
	
	public MontosMaximosException(String mensaje, Throwable cause){
		super(mensaje, cause);
	}

}
