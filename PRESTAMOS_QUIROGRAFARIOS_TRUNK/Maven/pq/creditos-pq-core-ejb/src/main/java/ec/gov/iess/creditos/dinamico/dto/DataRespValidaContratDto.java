/**
 * 
 */
package ec.gov.iess.creditos.dinamico.dto;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class DataRespValidaContratDto extends RespuestaError {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1234034342240161637L;
	/**
	 * Valida existencia de codigo
	 */
	private Boolean existeCodContrt;

	public Boolean getExisteCodContrt() {
		return existeCodContrt;
	}

	public void setExisteCodContrt(final Boolean existeCodContrt) {
		this.existeCodContrt = existeCodContrt;
	}

}
