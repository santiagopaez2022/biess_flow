package ec.gov.iess.creditos.pq.excepcion;

public class CambiarEstadoLiquidacionHistoricoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1774480192260127556L;

	public CambiarEstadoLiquidacionHistoricoException() {
		
	}

	public CambiarEstadoLiquidacionHistoricoException(String message) {
		super(message);
		
	}

	public CambiarEstadoLiquidacionHistoricoException(Throwable cause) {
		super(cause);
		
	}

	public CambiarEstadoLiquidacionHistoricoException(String message,
			Throwable cause) {
		super(message, cause);
	}

}
