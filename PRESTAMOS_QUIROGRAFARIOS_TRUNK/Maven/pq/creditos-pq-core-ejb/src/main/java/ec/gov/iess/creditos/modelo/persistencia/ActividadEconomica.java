package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Daniel Cardenas
 * 
 */
@Entity
@Table(name = "CRE_ACTIVIDADESECONOMICAS_TBL")
@NamedQueries( {
		@NamedQuery(name = "ActividadEconomica.getAllOrderByDescripcionSoloPadres", query = "SELECT act FROM ActividadEconomica act WHERE act.estado='A' and act.actividadEconomica IS NULL order by act.descripcion"),
		@NamedQuery(name = "ActividadEconomica.getAllOrderByDescripcionHijos", query = "SELECT act FROM ActividadEconomica act where act.actividadEconomica.codigo=:codigoPadre AND act.estado='A' order by act.descripcion") })
public class ActividadEconomica implements Serializable {

	private static final long serialVersionUID = 1975753207793702607L;

	@Id
	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "ESTADO")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "CODIGOPADRE", referencedColumnName = "CODIGO")
	private ActividadEconomica actividadEconomica;

	public ActividadEconomica() {
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the actividadEconomica
	 */
	public ActividadEconomica getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * @param actividadEconomica
	 *            the actividadEconomica to set
	 */
	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}
}