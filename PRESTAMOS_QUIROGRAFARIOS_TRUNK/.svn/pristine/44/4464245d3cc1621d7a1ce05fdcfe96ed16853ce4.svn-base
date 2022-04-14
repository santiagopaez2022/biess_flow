/**
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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.TipoDocumentoExtranjero;

/**
 * Clase para mapeo de la tabla KSPCOTBITREGCIV.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Entity
@Table(name = "KSPCOTBITREGCIV")
@NamedQueries({ @NamedQuery(name = "RegistroCivilExtranjero.obtenerPorCedula", query = "SELECT e from RegistroCivilExtranjero e where e.estado=1 and e.cedula=:cedula order by e.tipoDocumento desc") })
public class RegistroCivilExtranjero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic
	@Column(name = "CEDIDEUSU")
	private String cedula;

	@Column(name = "NUMDOCALT")
	private String numeroIdentificacion;

	@Column(name = "TIPDOC")
	@Enumerated(EnumType.STRING)
	private TipoDocumentoExtranjero tipoDocumento;

	@Column(name = "NUMIDEHOS")
	private String numeroHost;

	@Column(name = "FECTRA")
	private Date fechaRegistro;

	@Column(name = "ESTDOC")
	private String estado;

	@Column(name = "EMAIL")
	private String mail;

	/**
	 * 
	 */
	public RegistroCivilExtranjero() {
		super();
	}

	/**
	 * @param cedula
	 */
	public RegistroCivilExtranjero(TipoDocumentoExtranjero tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * @param numeroIdentificacion
	 *            the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * @return the numeroHost
	 */
	public String getNumeroHost() {
		return numeroHost;
	}

	/**
	 * @param numeroHost
	 *            the numeroHost to set
	 */
	public void setNumeroHost(String numeroHost) {
		this.numeroHost = numeroHost;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return tipoDocumento.toString();
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumentoExtranjero getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumentoExtranjero tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
