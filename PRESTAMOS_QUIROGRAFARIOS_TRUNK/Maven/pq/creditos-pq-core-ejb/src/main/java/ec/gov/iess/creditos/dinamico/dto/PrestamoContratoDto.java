/**
 * 
 */
package ec.gov.iess.creditos.dinamico.dto;

/**
 * @author paul.sampedro
 *
 */
public class PrestamoContratoDto extends RespuestaError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6759591314866191233L;
	/**
	 * Documento contrato
	 */
	private String docContrato;

	public String getDocContrato() {
		return docContrato;
	}

	public void setDocContrato(String docContrato) {
		this.docContrato = docContrato;
	}

}
