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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * Tipo de solicitud del credito
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Kscretsersoltip")
@NamedQueries( { @NamedQuery(name = "TipoSolicitudCredito.consultarSerialSolicitud", 
		query = "select o from TipoSolicitudCredito o where " +
				"o.codpretip = :idTipoCredito and codprecla = :idVariedadCredito "),
				@NamedQuery(name="TipoSolicitudCredito.consultarTipoSolicitud",		
						query = "select o from TipoSolicitudCredito o where " +
						"o.codtipsolser = :idTipoSolicitud")
})
public class TipoSolicitudCredito implements Serializable {

	@Id
	@Column(nullable = false)
	private Long codtipsolser;

	@Column(nullable = false)
	private String codclasolser;
	private Long codprecla;
	private Long codpretip;
	private String codtipconafi;

	private String codtiptrahip;
	@Column(nullable = false)
	private String desgratipsol;
	@Column(nullable = false)
	private String destipsolser;
	@Column(nullable = false)
	private String indhabtipsol;
	@Column(nullable = false)
	private Long numsectipsol;

	public TipoSolicitudCredito() {

	}

	/**
	 * @return the codtipsolser
	 */
	public Long getCodtipsolser() {
		return codtipsolser;
	}

	/**
	 * @return the codclasolser
	 */
	public String getCodclasolser() {
		return codclasolser;
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
	 * @return the codtipconafi
	 */
	public String getCodtipconafi() {
		return codtipconafi;
	}

	/**
	 * @return the codtiptrahip
	 */
	public String getCodtiptrahip() {
		return codtiptrahip;
	}

	/**
	 * @return the desgratipsol
	 */
	public String getDesgratipsol() {
		return desgratipsol;
	}

	/**
	 * @return the destipsolser
	 */
	public String getDestipsolser() {
		return destipsolser;
	}

	/**
	 * @return the indhabtipsol
	 */
	public String getIndhabtipsol() {
		return indhabtipsol;
	}

	/**
	 * @return the numsectipsol
	 */
	public Long getNumsectipsol() {
		return numsectipsol;
	}

	/**
	 * @param codtipsolser
	 *            the codtipsolser to set
	 */
	public void setCodtipsolser(Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}

	/**
	 * @param codclasolser
	 *            the codclasolser to set
	 */
	public void setCodclasolser(String codclasolser) {
		this.codclasolser = codclasolser;
	}

	/**
	 * @param codprecla
	 *            the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @param codpretip
	 *            the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @param codtipconafi
	 *            the codtipconafi to set
	 */
	public void setCodtipconafi(String codtipconafi) {
		this.codtipconafi = codtipconafi;
	}

	/**
	 * @param codtiptrahip
	 *            the codtiptrahip to set
	 */
	public void setCodtiptrahip(String codtiptrahip) {
		this.codtiptrahip = codtiptrahip;
	}

	/**
	 * @param desgratipsol
	 *            the desgratipsol to set
	 */
	public void setDesgratipsol(String desgratipsol) {
		this.desgratipsol = desgratipsol;
	}

	/**
	 * @param destipsolser
	 *            the destipsolser to set
	 */
	public void setDestipsolser(String destipsolser) {
		this.destipsolser = destipsolser;
	}

	/**
	 * @param indhabtipsol
	 *            the indhabtipsol to set
	 */
	public void setIndhabtipsol(String indhabtipsol) {
		this.indhabtipsol = indhabtipsol;
	}

	/**
	 * @param numsectipsol
	 *            the numsectipsol to set
	 */
	public void setNumsectipsol(Long numsectipsol) {
		this.numsectipsol = numsectipsol;
	}

}
