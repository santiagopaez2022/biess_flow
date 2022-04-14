/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.BeneficiarioCreditoPK;
import ec.gov.iess.hl.modelo.DivisionPolitica;

/**
 * Clase para persistencia de beneficiarios de creditos quirografarios.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Entity
@Table(name = "CRE_BENEFICIARIOSCREDITOS_TBL")
@NamedQueries({
		@NamedQuery(name = "BeneficiarioCredito.buscarPorPeriodo", query = "SELECT o FROM BeneficiarioCredito o WHERE trunc(o.fechaRegistro) >= :fecha_ant"
				+ " AND  trunc(o.fechaRegistro) <= :fecha_post order by o.fechaRegistro asc "),
		@NamedQuery(name = "BeneficiarioCredito.buscarPorPK", query = "SELECT o FROM BeneficiarioCredito o WHERE o.beneficiarioCreditoPK.codprecla=:codprecla"
				+ " and o.beneficiarioCreditoPK.codpretip =:codpretip and o.beneficiarioCreditoPK.numpreafi=:numpreafi and o.beneficiarioCreditoPK.ordpreafi=:ordpreafi") })
public class BeneficiarioCredito implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeneficiarioCreditoPK beneficiarioCreditoPK;

	@Column(name = "BC_NUMERO_BENEFICIARIO")
	private String numeroBeneficiario;

	@Column(name = "BC_BENEFICIARIO")
	private String beneficiario;

	@Column(name = "BC_NOMBRE_MENOR")
	private String nombreMenor;

	@Column(name = "BC_NUMERO_CAUSA")
	private String numeroCausa;

	@Column(name = "BC_NUMERO_JUZGADO")
	private String numeroJuzgado;

	@Column(name = "BC_NUMERO_KARDEX")
	private String numeroKardex;

	@Column(name = "BC_PROVINCIA_JUICIO")
	private String provinciaJuicio;

	@Column(name = "BC_CIUDAD_JUICIO")
	private String ciudadJuicio;

	@Column(name = "BC_FECHA_REGISTRO")
	private Date fechaRegistro;

	@Transient
	private DivisionPolitica ciudad;

	@Transient
	private DivisionPolitica provincia;

	/**
	 * 
	 */
	public BeneficiarioCredito() {

	}

	/**
	 * @return the beneficiarioCreditoPK
	 */
	public BeneficiarioCreditoPK getBeneficiarioCreditoPK() {
		return beneficiarioCreditoPK;
	}

	/**
	 * @param beneficiarioCreditoPK
	 *            the beneficiarioCreditoPK to set
	 */
	public void setBeneficiarioCreditoPK(BeneficiarioCreditoPK beneficiarioCreditoPK) {
		this.beneficiarioCreditoPK = beneficiarioCreditoPK;
	}

	/**
	 * @return the numeroBeneficiario
	 */
	public String getNumeroBeneficiario() {
		return numeroBeneficiario;
	}

	/**
	 * @param numeroBeneficiario
	 *            the numeroBeneficiario to set
	 */
	public void setNumeroBeneficiario(String numeroBeneficiario) {
		this.numeroBeneficiario = numeroBeneficiario;
	}

	/**
	 * @return the numeroJuzgado
	 */
	public String getNumeroJuzgado() {
		return numeroJuzgado;
	}

	/**
	 * @param numeroJuzgado
	 *            the numeroJuzgado to set
	 */
	public void setNumeroJuzgado(String numeroJuzgado) {
		this.numeroJuzgado = numeroJuzgado;
	}

	/**
	 * @return the provinciaJuicio
	 */
	public String getProvinciaJuicio() {
		return provinciaJuicio;
	}

	/**
	 * @param provinciaJuicio
	 *            the provinciaJuicio to set
	 */
	public void setProvinciaJuicio(String provinciaJuicio) {
		this.provinciaJuicio = provinciaJuicio;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro
	 *            the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the beneficiario
	 */
	public String getBeneficiario() {
		return beneficiario;
	}

	/**
	 * @param beneficiario
	 *            the beneficiario to set
	 */
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	/**
	 * @return the ciudadJuicio
	 */
	public String getCiudadJuicio() {
		return ciudadJuicio;
	}

	/**
	 * @param ciudadJuicio
	 *            the ciudadJuicio to set
	 */
	public void setCiudadJuicio(String ciudadJuicio) {
		this.ciudadJuicio = ciudadJuicio;
	}

	/**
	 * @return the ciudad
	 */
	public DivisionPolitica getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 *            the ciudad to set
	 */
	public void setCiudad(DivisionPolitica ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the numeroCausa
	 */
	public String getNumeroCausa() {
		return numeroCausa;
	}

	/**
	 * @param numeroCausa
	 *            the numeroCausa to set
	 */
	public void setNumeroCausa(String numeroCausa) {
		this.numeroCausa = numeroCausa;
	}

	/**
	 * @return the nombreMenor
	 */
	public String getNombreMenor() {
		return nombreMenor;
	}

	/**
	 * @param nombreMenor
	 *            the nombreMenor to set
	 */
	public void setNombreMenor(String nombreMenor) {
		this.nombreMenor = nombreMenor;
	}

	/**
	 * @return the numeroKardex
	 */
	public String getNumeroKardex() {
		return numeroKardex;
	}

	/**
	 * @param numeroKardex
	 *            the numeroKardex to set
	 */
	public void setNumeroKardex(String numeroKardex) {
		this.numeroKardex = numeroKardex;
	}

	/**
	 * @return the provincia
	 */
	public DivisionPolitica getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(DivisionPolitica provincia) {
		this.provincia = provincia;
	}

}
