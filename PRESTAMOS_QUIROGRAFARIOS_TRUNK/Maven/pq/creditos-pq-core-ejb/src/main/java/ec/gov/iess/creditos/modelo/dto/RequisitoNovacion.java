/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase que almacena si un requisito de novacion es posible o no novar.
 *
 * @author paul.sampedro
 */
public class RequisitoNovacion implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5661554344440865940L;
	
	/** The nombre. */
	private String nombre;
	
	/** The valor. */
	private BigDecimal valor;
	
	/** The observacion. */
	private String observacion;
	
	/** The nut. */
	private Long nut;
	

	
	/**
	 * Constructor con las observaciones.
	 *
	 * @param nombre the nombre
	 * @param valor the valor
	 * @param observacion the observacion
	 * @param nut the nut
	 */
	public RequisitoNovacion(final String nombre, final BigDecimal valor, final String observacion,final Long nut) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.observacion = observacion;
		this.nut=nut;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Sets the observacion.
	 *
	 * @param observacion the new observacion
	 */
	public void setObservacion(final String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Gets the nut.
	 *
	 * @return the nut
	 */
	public Long getNut() {
		return nut;
	}

	/**
	 * Sets the nut.
	 *
	 * @param nut the new nut
	 */
	public void setNut(final Long nut) {
		this.nut = nut;
	}
}
