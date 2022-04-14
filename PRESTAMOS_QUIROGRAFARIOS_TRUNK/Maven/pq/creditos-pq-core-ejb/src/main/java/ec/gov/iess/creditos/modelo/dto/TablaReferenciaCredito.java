/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author cvillarreal
 * 
 */
public class TablaReferenciaCredito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128179731918135394L;

	private OpcionCredito mejorOpcionCredito;

	private List<PlazoCredito> plazoCreditoSinDocumentoFiduciario;

	private List<PlazoCredito> plazoCreditoConDocumentoFiduciario;

	/**
	 * 
	 */
	public TablaReferenciaCredito() {
	}

	public TablaReferenciaCredito(
			List<PlazoCredito> plazoCreditoSinDocumentoFiduciarioNew,
			List<PlazoCredito> plazoCreditoConDocumentoFiduciarioNew,
			OpcionCredito mejorOpcionCreditoNew) {

		this.plazoCreditoConDocumentoFiduciario = plazoCreditoConDocumentoFiduciarioNew;
		this.plazoCreditoSinDocumentoFiduciario = plazoCreditoSinDocumentoFiduciarioNew;
		this.mejorOpcionCredito = mejorOpcionCreditoNew;
	}

	public List<PlazoCredito> getPlazoCreditoSinDocumentoFiduciario() {
		return plazoCreditoSinDocumentoFiduciario;
	}

	public void setPlazoCreditoSinDocumentoFiduciario(
			List<PlazoCredito> plazoCreditoSinDocumentoFiduciario) {
		this.plazoCreditoSinDocumentoFiduciario = plazoCreditoSinDocumentoFiduciario;
	}

	public List<PlazoCredito> getPlazoCreditoConDocumentoFiduciario() {
		return plazoCreditoConDocumentoFiduciario;
	}

	public void setPlazoCreditoConDocumentoFiduciario(
			List<PlazoCredito> plazoCreditoConDocumentoFiduciario) {
		this.plazoCreditoConDocumentoFiduciario = plazoCreditoConDocumentoFiduciario;
	}

	public OpcionCredito getMejorOpcionCredito() {
		return mejorOpcionCredito;
	}

	public void setMejorOpcionCredito(OpcionCredito mejorOpcionCredito) {
		this.mejorOpcionCredito = mejorOpcionCredito;
	}

}
