/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/** 
 * <b>
 * Entidad de parametros
 * </b>
 *  
 * @author Ricardo Tituaña
 *
*/
@Entity
@Table(name="KSCRETCONFINLIS")
public class Parametro implements Serializable {

	private static final long serialVersionUID = 5111807950727006903L;

	@Column(name="CODCONFIN")
	@Id
	private String codigo;
	
	@Column(name="DESCONFIN")
	private String descripcion;
	
	@Column(name="CTACONFIN")
	private String ctaConFin;
	
	@Column(name="INDHABCONFIN")
	private String indHabConFin;
	
	@Column(name="TEXEXP")
	private String texto;
	
	@OneToMany(mappedBy="parametro")
	private List<ParametroValor> valores;

	/**
	 * Returns the value of codigo.
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	
	/**
	 * Sets the value for codigo.
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	/**
	 * Returns the value of descripcion.
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	
	/**
	 * Sets the value for descripcion.
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * Returns the value of ctaConFin.
	 * @return ctaConFin
	 */
	public String getCtaConFin() {
		return ctaConFin;
	}

	
	/**
	 * Sets the value for ctaConFin.
	 * @param ctaConFin
	 */
	public void setCtaConFin(String ctaConFin) {
		this.ctaConFin = ctaConFin;
	}

	
	/**
	 * Returns the value of indHabConFin.
	 * @return indHabConFin
	 */
	public String getIndHabConFin() {
		return indHabConFin;
	}

	
	/**
	 * Sets the value for indHabConFin.
	 * @param indHabConFin
	 */
	public void setIndHabConFin(String indHabConFin) {
		this.indHabConFin = indHabConFin;
	}

	
	/**
	 * Returns the value of texto.
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

	
	/**
	 * Sets the value for texto.
	 * @param texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}


	
	/**
	 * Returns the value of valores.
	 * @return valores
	 */
	public List<ParametroValor> getValores() {
		return valores;
	}


	
	/**
	 * Sets the value for valores.
	 * @param valores
	 */
	public void setValores(List<ParametroValor> valores) {
		this.valores = valores;
	}
	
}