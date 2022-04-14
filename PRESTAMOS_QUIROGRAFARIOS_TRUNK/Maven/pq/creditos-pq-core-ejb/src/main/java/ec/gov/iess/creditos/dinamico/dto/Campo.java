/**
 * 
 */
package ec.gov.iess.creditos.dinamico.dto;

import java.io.Serializable;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class Campo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234034342240237037L;

	/**
	 * item
	 */
	private String item;
	/**
	 * valor
	 */
	private String valor;

	public String getItem() {
		return item;
	}

	public void setItem(final String item) {
		this.item = item;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(final String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Campo [item=" + item + ", valor=" + valor + "]";
	}

}
