/**
 * 
 */
package ec.gov.iess.pq.concesion.web.bean;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class DataConsultaContrato {

	/**
	 * Ruc que se selecciona en el combo del formulario
	 */
	private String rucSeleccionado;
	/**
	 * Numero de contrato ingresado en el formulario
	 */
	private String numContrtIng;

	public String getRucSeleccionado() {
		return rucSeleccionado;
	}

	public void setRucSeleccionado(final String rucSeleccionado) {
		this.rucSeleccionado = rucSeleccionado;
	}

	public String getNumContrtIng() {
		return numContrtIng;
	}

	public void setNumContrtIng(final String numContrtIng) {
		this.numContrtIng = numContrtIng;
	}

}
