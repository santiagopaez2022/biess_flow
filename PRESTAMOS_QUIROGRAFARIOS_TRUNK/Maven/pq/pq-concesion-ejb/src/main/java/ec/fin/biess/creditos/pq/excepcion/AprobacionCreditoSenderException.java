package ec.fin.biess.creditos.pq.excepcion;

public class AprobacionCreditoSenderException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AprobacionCreditoSenderException(){
		
	}
	
	public AprobacionCreditoSenderException(String mensaje){
		super(mensaje);
	}
	
	public AprobacionCreditoSenderException(Throwable cause){
		super(cause);
	}
	
	public AprobacionCreditoSenderException(String mensaje, Throwable cause){
		super(mensaje, cause);
	}

}
