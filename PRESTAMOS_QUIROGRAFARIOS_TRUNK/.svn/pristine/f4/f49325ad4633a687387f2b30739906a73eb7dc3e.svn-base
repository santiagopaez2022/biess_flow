package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase para mapear la tabla tipo_seguro
 * 
 * @author christian.gomez
 * 
 */
@Entity
@Table(name = "tipo_seguro")
public class TipoSeguro {

	@Id
	@Column(name = "TIPO_SEGURO")
	private String id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CODCLASEG")
	private Long codClase;

	@OneToMany(mappedBy = "tipoSeguro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TipoPrestacion> listaPrestaciones;

	/**
	 * Constructor.
	 */
	public TipoSeguro() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param nombre
	 * @param codClase
	 */
	public TipoSeguro(String id, String nombre, Long codClase) {
		this.id = id;
		this.nombre = nombre;
		this.codClase = codClase;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the codClase
	 */
	public Long getCodClase() {
		return codClase;
	}

	/**
	 * @param codClase
	 *            the codClase to set
	 */
	public void setCodClase(Long codClase) {
		this.codClase = codClase;
	}

	/**
	 * @return the listaPrestaciones
	 */
	public List<TipoPrestacion> getListaPrestaciones() {
		return listaPrestaciones;
	}

	/**
	 * @param listaPrestaciones
	 *            the listaPrestaciones to set
	 */
	public void setListaPrestaciones(List<TipoPrestacion> listaPrestaciones) {
		this.listaPrestaciones = listaPrestaciones;
	}

}
