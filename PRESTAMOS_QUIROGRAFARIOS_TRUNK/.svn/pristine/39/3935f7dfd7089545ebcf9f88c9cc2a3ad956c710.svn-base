package ec.gov.iess.creditos.pq.excepcion;

public class CalculoCreditoException extends Exception {

	private static final long serialVersionUID = -7891990970038378853L;

	public CalculoCreditoException() {
	}

	public CalculoCreditoException(String arg0) {
		super(arg0);
	}

	public CalculoCreditoException(Throwable arg0) {
		super(arg0);
	}

	public CalculoCreditoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public CalculoCreditoException(int codigoError, String mensajeCustomizado) throws CalculoCreditoException {
		String mensajeExcepcion = null;
		if (mensajeCustomizado == null) {
			mensajeCustomizado = "";
		}
		switch (codigoError) {
		case 1:
			mensajeExcepcion = "NO SE PUDO DETERMINAR EL VALOR DE SEGURO DE SALDOS.. " + mensajeCustomizado;
			break;
		case 2:
			mensajeExcepcion = "NO SE PUDO DETERMINAR EL VALOR CANCELADO POR NOVACION.. " + mensajeCustomizado;
			break;
		case 3:
			mensajeExcepcion = "Monto a transferir negativo (-$" + mensajeCustomizado;
			break;
		case 4:
			mensajeExcepcion = "<strong>AVISO:</strong><br />MONTO DEL CR\u00C9DITO MAYOR AL PERMITIDO: " + mensajeCustomizado;
			break;
		default:
			mensajeExcepcion = mensajeCustomizado;
		}
		throw new CalculoCreditoException(mensajeExcepcion);
	}

}