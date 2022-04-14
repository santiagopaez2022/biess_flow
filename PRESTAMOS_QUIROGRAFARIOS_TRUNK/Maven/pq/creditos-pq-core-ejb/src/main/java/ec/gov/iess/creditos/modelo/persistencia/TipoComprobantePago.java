/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa a los tipos de comprobantes de pago
 *  
 * @version $Revision: 1.1 $  [Sep 24, 2007]
 * @author pablo
 */
@Entity
@Table(name = "KSRECTCOMPAGTIP")
public class TipoComprobantePago implements Serializable {

	private static final long serialVersionUID = -5183942693003382770L;

	@Column(name = "CODTIPCOMPAG", nullable = false)
	@Id
	private String tipoComprobante;

	@Column(name = "DESTIPCOMPAG", nullable = false)
	private String descripcion;

	@Column(name = "SECTIPCOMPAG", nullable = false)
	private Long secTipComPag;

	@Column(name = "NUMDIAVAL")
	private Integer numDiaVal;

	@Column(name = "CALCOMPAG")
	private String calComPag;

	@Column(name = "CTABANCARIA")
	private String cuentaBancaria;

	@Column(name = "CTACONTABLE")
	private String cuentaContable;

	@Column(name = "CODTRA")
	private Long codTra;

	/**
	 * Returns the value of tipoComprobante.
	 * @return tipoComprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}

	/**
	 * Sets the value for tipoComprobante.
	 * @param tipoComprobante
	 */
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
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
	 * Returns the value of secTipComPag.
	 * @return secTipComPag
	 */
	public Long getSecTipComPag() {
		return secTipComPag;
	}

	/**
	 * Sets the value for secTipComPag.
	 * @param secTipComPag
	 */
	public void setSecTipComPag(Long secTipComPag) {
		this.secTipComPag = secTipComPag;
	}

	/**
	 * Returns the value of numDiaVal.
	 * @return numDiaVal
	 */
	public Integer getNumDiaVal() {
		return numDiaVal;
	}

	/**
	 * Sets the value for numDiaVal.
	 * @param numDiaVal
	 */
	public void setNumDiaVal(Integer numDiaVal) {
		this.numDiaVal = numDiaVal;
	}

	/**
	 * Returns the value of calComPag.
	 * @return calComPag
	 */
	public String getCalComPag() {
		return calComPag;
	}

	/**
	 * Sets the value for calComPag.
	 * @param calComPag
	 */
	public void setCalComPag(String calComPag) {
		this.calComPag = calComPag;
	}

	/**
	 * Returns the value of cuentaBancaria.
	 * @return cuentaBancaria
	 */
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	/**
	 * Sets the value for cuentaBancaria.
	 * @param cuentaBancaria
	 */
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	/**
	 * Returns the value of cuentaContable.
	 * @return cuentaContable
	 */
	public String getCuentaContable() {
		return cuentaContable;
	}

	/**
	 * Sets the value for cuentaContable.
	 * @param cuentaContable
	 */
	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	/**
	 * Returns the value of codTra.
	 * @return codTra
	 */
	public Long getCodTra() {
		return codTra;
	}

	/**
	 * Sets the value for codTra.
	 * @param codTra
	 */
	public void setCodTra(Long codTra) {
		this.codTra = codTra;
	}

}
