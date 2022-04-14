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
 * <b> Clase para la persistencia de catalogo de tipos de seguros de jubilados
 * para ph </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_CATALOGOSEGURO_TBL")
@SequenceGenerator(name = "seqCatalogoSeguro", sequenceName = "CRE_CATALOGOSEGURO_SEQ", allocationSize = 1, initialValue = 1)
@NamedQuery(name = "CatalogoTipoSeguro.obtenerActivos", query = "Select o From CatalogoTipoSeguro o Where o.estado=1")
public class CatalogoTipoSeguro implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 6859808979333150743L;

	@Id
	@Column(name = "CS_ID")
	@GeneratedValue(generator = "seqCatalogoSeguro", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "CS_TIPO_SEGURO")
	private String tipoSeguro;

	@Column(name = "CS_DESCRIPCION")
	private String descripcion;

	@Column(name = "CS_ESTADO")
	private Long estado;

	@OneToMany(mappedBy = "tipoSeguro", cascade = CascadeType.ALL)
	private List<CatalogoPrestacionSeguro> listaCatalogoTipoSeguro;

	@OneToMany(mappedBy = "tipoSeguroAlternativo", cascade = CascadeType.ALL)
	private List<CatalogoPrestacionSeguro> listaCatalogoTipoSeguroAlternativo;

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
	 * @return the tipoSeguro
	 */
	public String getTipoSeguro() {
		return tipoSeguro;
	}

	/**
	 * @param tipoSeguro
	 *            the tipoSeguro to set
	 */
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
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
	 * @return the listaCatalogoTipoSeguro
	 */
	public List<CatalogoPrestacionSeguro> getListaCatalogoTipoSeguro() {
		return listaCatalogoTipoSeguro;
	}

	/**
	 * @param listaCatalogoTipoSeguro
	 *            the listaCatalogoTipoSeguro to set
	 */
	public void setListaCatalogoTipoSeguro(List<CatalogoPrestacionSeguro> listaCatalogoTipoSeguro) {
		this.listaCatalogoTipoSeguro = listaCatalogoTipoSeguro;
	}

	/**
	 * @return the listaCatalogoTipoSeguroAlternativo
	 */
	public List<CatalogoPrestacionSeguro> getListaCatalogoTipoSeguroAlternativo() {
		return listaCatalogoTipoSeguroAlternativo;
	}

	/**
	 * @param listaCatalogoTipoSeguroAlternativo
	 *            the listaCatalogoTipoSeguroAlternativo to set
	 */
	public void setListaCatalogoTipoSeguroAlternativo(List<CatalogoPrestacionSeguro> listaCatalogoTipoSeguroAlternativo) {
		this.listaCatalogoTipoSeguroAlternativo = listaCatalogoTipoSeguroAlternativo;
	}
}
