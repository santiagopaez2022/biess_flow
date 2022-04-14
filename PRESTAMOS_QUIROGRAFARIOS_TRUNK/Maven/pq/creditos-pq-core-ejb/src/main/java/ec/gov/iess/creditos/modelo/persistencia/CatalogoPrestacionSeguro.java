/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b> Clase para persistencia de catálogo de prestaciones por seguro de
 * jubilados para ph. </b>
 * 
 * @author Jenny Sánchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 14/07/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_PRESTACIONXSEGURO_TBL")
@SequenceGenerator(name = "seqPrestacionSeguro", sequenceName = "CRE_PRESTACIONXSEGURO_SEQ", allocationSize = 1, initialValue = 1)
@NamedQuery(name = "CatalogoPrestacionSeguro.obtenerPorPrestacionSeguros", query = "Select o From CatalogoPrestacionSeguro o "
		+ " Where 	o.tipoPrestacion.tipoPrestacion=:tipoPrestacion "
		+ " 		and o.tipoSeguro.tipoSeguro=:tipoSeguro"
		+ "			and o.tipoSeguroAlternativo.tipoSeguro=:tipoSeguroAlt ")
public class CatalogoPrestacionSeguro implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 176521443821218350L;

	@Id
	@Column(name = "PS_ID")
	@GeneratedValue(generator = "seqPrestacionSeguro", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PS_TIPO_PRESTACION", referencedColumnName = "CP_ID")
	private CatalogoTipoPrestacion tipoPrestacion;

	@ManyToOne
	@JoinColumn(name = "PS_TIPO_SEGURO", referencedColumnName = "CS_ID")
	private CatalogoTipoSeguro tipoSeguro;

	@ManyToOne
	@JoinColumn(name = "PS_TIPO_SEGURO_ALT", referencedColumnName = "CS_ID")
	private CatalogoTipoSeguro tipoSeguroAlternativo;

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
	public CatalogoTipoPrestacion getTipoPrestacion() {
		return tipoPrestacion;
	}

	/**
	 * @param tipoPrestacion
	 *            the tipoPrestacion to set
	 */
	public void setTipoPrestacion(CatalogoTipoPrestacion tipoPrestacion) {
		this.tipoPrestacion = tipoPrestacion;
	}

	/**
	 * @return the tipoSeguro
	 */
	public CatalogoTipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	/**
	 * @param tipoSeguro
	 *            the tipoSeguro to set
	 */
	public void setTipoSeguro(CatalogoTipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	/**
	 * @return the tipoSeguroAlternativo
	 */
	public CatalogoTipoSeguro getTipoSeguroAlternativo() {
		return tipoSeguroAlternativo;
	}

	/**
	 * @param tipoSeguroAlternativo
	 *            the tipoSeguroAlternativo to set
	 */
	public void setTipoSeguroAlternativo(CatalogoTipoSeguro tipoSeguroAlternativo) {
		this.tipoSeguroAlternativo = tipoSeguroAlternativo;
	}
}
