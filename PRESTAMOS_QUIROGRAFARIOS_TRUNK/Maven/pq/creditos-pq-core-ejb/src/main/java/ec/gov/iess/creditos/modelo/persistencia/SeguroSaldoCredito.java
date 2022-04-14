/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * Clase de modelo de persistencia con la tasa de interes del seguro de saldo
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TCRPQT_SEGUROSALDOS")
@NamedQueries( { @NamedQuery(name = "SeguroSaldoCredito.consultarRangoEdadPlazo", 
		query = "select o from SeguroSaldoCredito o " +
				"where :fechaSolicitud between o.fecinivig and o.fecfinvig " +
				"and o.codpretip = :tipoCredito " +
				"and o.codprecla = :varidadCredito " +
				"and :edadActual between o.edades and o.edahas " +
				"and o.plames=:plazoMeses") })
public class SeguroSaldoCredito implements Serializable {

	@Id
	@Column(nullable = false)
	private Long codsegsal;

	private Long codprecla;
	private Long codpretip;
	@Column(nullable = false)
	private Long edades;
	@Column(nullable = false)
	private Long edahas;
	private Date fecfinvig;
	private Date fecinivig;
	@Column(nullable = false)	
	private Long plames;
	@Column(nullable = false)
	private Double tasint;

	public SeguroSaldoCredito() {
	}

	/**
	 * @return the codsegsal
	 */
	public Long getCodsegsal() {
		return codsegsal;
	}

	/**
	 * @return the codprecla
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	/**
	 * @return the codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * @return the edades
	 */
	public Long getEdades() {
		return edades;
	}

	/**
	 * @return the edahas
	 */
	public Long getEdahas() {
		return edahas;
	}

	/**
	 * @return the fecfinvig
	 */
	public Date getFecfinvig() {
		return fecfinvig;
	}

	/**
	 * @return the fecinivig
	 */
	public Date getFecinivig() {
		return fecinivig;
	}

	/**
	 * @return the plames
	 */
	public Long getPlames() {
		return plames;
	}

	/**
	 * @return the tasint
	 */
	public Double getTasint() {
		return tasint;
	}

	/**
	 * @param codsegsal the codsegsal to set
	 */
	public void setCodsegsal(Long codsegsal) {
		this.codsegsal = codsegsal;
	}

	/**
	 * @param codprecla the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @param codpretip the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @param edades the edades to set
	 */
	public void setEdades(Long edades) {
		this.edades = edades;
	}

	/**
	 * @param edahas the edahas to set
	 */
	public void setEdahas(Long edahas) {
		this.edahas = edahas;
	}

	/**
	 * @param fecfinvig the fecfinvig to set
	 */
	public void setFecfinvig(Date fecfinvig) {
		this.fecfinvig = fecfinvig;
	}

	/**
	 * @param fecinivig the fecinivig to set
	 */
	public void setFecinivig(Date fecinivig) {
		this.fecinivig = fecinivig;
	}

	/**
	 * @param plames the plames to set
	 */
	public void setPlames(Long plames) {
		this.plames = plames;
	}

	/**
	 * @param tasint the tasint to set
	 */
	public void setTasint(Double tasint) {
		this.tasint = tasint;
	}

}
