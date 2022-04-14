package ec.fin.biess.creditos.pq.modelo.persistencia;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Clase para mapear la tabla TIPO_PRESTACION
 * 
 * @author christian.gomez
 * 
 */
@Entity
@Table(name = "TIPO_PRESTACION")
public class TipoPrestacion {

	@EmbeddedId
	TipoPrestacionPk pk;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "PERMANENCIA")
	private String permanencia;

	@Column(name = "CODIGO_TABLA")
	private Long codigoTabla;

	@ManyToOne
	@JoinColumn(name = "TIPO_SEGURO", referencedColumnName = "TIPO_SEGURO", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private TipoSeguro tipoSeguro;

	/**
	 * Constructor.
	 */
	public TipoPrestacion() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param pk
	 * @param nombre
	 * @param permanencia
	 * @param codigoTabla
	 */
	public TipoPrestacion(TipoPrestacionPk pk, String nombre,
			String permanencia, Long codigoTabla) {
		this.pk = pk;
		this.nombre = nombre;
		this.permanencia = permanencia;
		this.codigoTabla = codigoTabla;
	}

	/**
	 * @return the pk
	 */
	public TipoPrestacionPk getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(TipoPrestacionPk pk) {
		this.pk = pk;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the permanencia
	 */
	public String getPermanencia() {
		return permanencia;
	}

	/**
	 * @param permanencia
	 *            the permanencia to set
	 */
	public void setPermanencia(String permanencia) {
		this.permanencia = permanencia;
	}

	/**
	 * @return the codigoTabla
	 */
	public Long getCodigoTabla() {
		return codigoTabla;
	}

	/**
	 * @param codigoTabla
	 *            the codigoTabla to set
	 */
	public void setCodigoTabla(Long codigoTabla) {
		this.codigoTabla = codigoTabla;
	}

	/**
	 * @return the tipoSeguro
	 */
	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	/**
	 * @param tipoSeguro
	 *            the tipoSeguro to set
	 */
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

}
