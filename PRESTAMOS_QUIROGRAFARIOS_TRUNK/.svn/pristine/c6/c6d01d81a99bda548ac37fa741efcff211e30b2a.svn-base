/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b> Clase para persistencia de catálogo de prestaciones de jubilados en PH.
 * </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_CATALOGOPRESTACION_TBL")
@SequenceGenerator(name = "seqCatalogoPrestacion", sequenceName = "CRE_CATALOGOPRESTACION_SEQ", allocationSize = 1, initialValue = 1)
@NamedQuery(name = "CatalogoTipoPrestacion.obtenerActivos", query = "Select o From CatalogoTipoPrestacion o Where o.estado=1")
public class CatalogoTipoPrestacion implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -4395726873511448156L;

	@Id
	@Column(name = "CP_ID", nullable = false)
	@GeneratedValue(generator = "seqCatalogoPrestacion", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "CP_TIPO_PRESTACION")
	private String tipoPrestacion;

	@Column(name = "CP_DESCRIPCION")
	private String descripcion;

	@Column(name = "CP_ESTADO")
	private Long estado;

	@OneToMany(mappedBy = "tipoPrestacion", cascade = CascadeType.ALL)
	private List<CatalogoPrestacionSeguro> listaCatalogoPrestacionSeguro;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the tipoPrestacion
	 */
	public String getTipoPrestacion() {
		return tipoPrestacion;
	}

	/**
	 * @param tipoPrestacion
	 *            the tipoPrestacion to set
	 */
	public void setTipoPrestacion(String tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
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
	public Long getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Long estado) {
		this.estado = estado;
	}

	/**
	 * @return the listaCatalogoPrestacionSeguro
	 */
	public List<CatalogoPrestacionSeguro> getListaCatalogoPrestacionSeguro() {
		return listaCatalogoPrestacionSeguro;
	}

	/**
	 * @param listaCatalogoPrestacionSeguro
	 *            the listaCatalogoPrestacionSeguro to set
	 */
	public void setListaCatalogoPrestacionSeguro(List<CatalogoPrestacionSeguro> listaCatalogoPrestacionSeguro) {
		this.listaCatalogoPrestacionSeguro = listaCatalogoPrestacionSeguro;
	}
}
