package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Llave primaria de la clase TipoPrestacion
 * 
 * @author christian.gomez
 * 
 */
@Embeddable
public class TipoPrestacionPk implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5456789264L;

	@Column(name = "TIPO_PRESTACION")
	private String idPrestacion;

	@Column(name = "TIPO_SEGURO")
	private String idSeguro;

	/**
	 * Constructor.
	 */
	public TipoPrestacionPk() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param idPrestacion
	 * @param idSeguro
	 */
	public TipoPrestacionPk(String idPrestacion, String idSeguro) {
		this.idPrestacion = idPrestacion;
		this.idSeguro = idSeguro;
	}

	/**
	 * @return the idPrestacion
	 */
	public String getIdPrestacion() {
		return idPrestacion;
	}

	/**
	 * @param idPrestacion
	 *            the idPrestacion to set
	 */
	public void setIdPrestacion(String idPrestacion) {
		this.idPrestacion = idPrestacion;
	}

	/**
	 * @return the idSeguro
	 */
	public String getIdSeguro() {
		return idSeguro;
	}

	/**
	 * @param idSeguro
	 *            the idSeguro to set
	 */
	public void setIdSeguro(String idSeguro) {
		this.idSeguro = idSeguro;
	}

}
