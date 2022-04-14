/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean para la tabla CRE_DETCATALOGOPQ_TBL
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "CRE_DETCATALOGOPQ_TBL")
public class ParametroPQ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DP_ID_DETCATALOGO", nullable = false, unique = true)
	private Integer id;

	@Column(name = "DP_CODDETCATALOGO", nullable = false)
	private String codigo;

	@Column(name = "DP_DESDETCATALOGO", nullable = false)
	private String descripcion;

	@Column(name = "DP_TIPVALDETCATALOGO", nullable = false)
	private String tipoValor;

	@Column(name = "DP_VALNUMDETCATALOGO")
	private BigDecimal valorNumerico;

	@Column(name = "DP_UNIVALNUMDETCATALOGO")
	private String unidadValorNumerico;

	@Column(name = "DP_VALCARDETCATALOGO")
	private String valorCaracter;

	@Column(name = "DP_VALFECDETCATALOGO")
	private Date valorFecha;

	@Column(name = "DP_ESTADODETCATALOGO", nullable = false)
	private String estado;

	@Column(name = "DP_VALFECACTUALIZACION")
	private Date fechaActualizacion;

	@Column(name = "DP_OBSDETCATALOGO")
	private String observacion;

	@Column(name = "CP_ID_CATALOGO", nullable = false)
	private Integer idCatalogo;

	@Column(name = "DP_INICIORANGODETCATALOGO")
	private BigDecimal inicioRango;

	@Column(name = "DP_FINALRANGODETCATALOGO")
	private BigDecimal finalRango;

	public ParametroPQ() {

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the tipoValor
	 */
	public String getTipoValor() {
		return tipoValor;
	}

	/**
	 * @param tipoValor
	 *            the tipoValor to set
	 */
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	/**
	 * @return the valorNumerico
	 */
	public BigDecimal getValorNumerico() {
		return valorNumerico;
	}

	/**
	 * @param valorNumerico
	 *            the valorNumerico to set
	 */
	public void setValorNumerico(BigDecimal valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	/**
	 * @return the unidadValorNumerico
	 */
	public String getUnidadValorNumerico() {
		return unidadValorNumerico;
	}

	/**
	 * @param unidadValorNumerico
	 *            the unidadValorNumerico to set
	 */
	public void setUnidadValorNumerico(String unidadValorNumerico) {
		this.unidadValorNumerico = unidadValorNumerico;
	}

	/**
	 * @return the valorCaracter
	 */
	public String getValorCaracter() {
		return valorCaracter;
	}

	/**
	 * @param valorCaracter
	 *            the valorCaracter to set
	 */
	public void setValorCaracter(String valorCaracter) {
		this.valorCaracter = valorCaracter;
	}

	/**
	 * @return the valorFecha
	 */
	public Date getValorFecha() {
		return valorFecha;
	}

	/**
	 * @param valorFecha
	 *            the valorFecha to set
	 */
	public void setValorFecha(Date valorFecha) {
		this.valorFecha = valorFecha;
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
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion
	 *            the fechaActualizacion to set
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the idCatalogo
	 */
	public Integer getIdCatalogo() {
		return idCatalogo;
	}

	/**
	 * @param idCatalogo
	 *            the idCatalogo to set
	 */
	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	/**
	 * @return the inicioRango
	 */
	public BigDecimal getInicioRango() {
		return inicioRango;
	}

	/**
	 * @param inicioRango
	 *            the inicioRango to set
	 */
	public void setInicioRango(BigDecimal inicioRango) {
		this.inicioRango = inicioRango;
	}

	/**
	 * @return the finalRango
	 */
	public BigDecimal getFinalRango() {
		return finalRango;
	}

	/**
	 * @param finalRango
	 *            the finalRango to set
	 */
	public void setFinalRango(BigDecimal finalRango) {
		this.finalRango = finalRango;
	}

}
