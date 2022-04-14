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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoDetallePk;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.2 $  [Sep 24, 2007]
 * @author pablo
 */
@Entity
@Table(name = "kscretcompagafidet")
public class ComprobantePagoDetalle implements Serializable {

	private static final long serialVersionUID = -4573070416210539042L;

	@EmbeddedId
	private ComprobantePagoDetallePk pk;

	@Column(name = "NUMLIQPRE")
	private Long numLiqPre;

	@Column(name = "VALCANDET", nullable = false)
	private Float valCanDet;

	@Column(name = "OBSDET")
	private String obsDet;

	@Column(name = "VALINTDET", nullable = false)
	private Float valIntDet;

	@Column(name = "TIPTRA", nullable = false)
	private String tipTra;

	@Column(name = "CODTIPRUBCOMPAG")
	private String codTipRubComPag;

	@Column(name = "NUMCUOAFI")
	private Long numCuoAfi;

	@Column(name = "CODCONAFI")
	private String codConAfi;

	@Column(name = "CODTIPCONAFI")
	private String codTipConAfi;

	@Column(name = "INTSALCAP")
	private Float intSalCap;

	@Column(name = "INTPERGRA")
	private Float intPerGra;

	@Column(name = "VALSALCAP")
	private Float valSalCap;

	@Column(name = "SALINTMOR")
	private Float salIntMor;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "CODCOMPAGAFI", referencedColumnName = "CODCOMPAGAFI", insertable = false, updatable = false),
			@JoinColumn(name = "CODTIPCOMPAG", referencedColumnName = "CODTIPCOMPAG", insertable = false, updatable = false) })
	private ComprobantePago comprobantePago;

	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "NUMDIV", referencedColumnName = "NUMDIV"),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP"),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI"),
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI"),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA") })
	private DividendoPrestamo dividendoPrestamo;

	/**
	 * Returns the value of numLiqPre.
	 * @return numLiqPre
	 */
	public Long getNumLiqPre() {
		return numLiqPre;
	}

	/**
	 * Sets the value for numLiqPre.
	 * @param numLiqPre
	 */
	public void setNumLiqPre(Long numLiqPre) {
		this.numLiqPre = numLiqPre;
	}

	/**
	 * Returns the value of valCanDet.
	 * @return valCanDet
	 */
	public Float getValCanDet() {
		return valCanDet;
	}

	/**
	 * Sets the value for valCanDet.
	 * @param valCanDet
	 */
	public void setValCanDet(Float valCanDet) {
		this.valCanDet = valCanDet;
	}

	/**
	 * Returns the value of obsDet.
	 * @return obsDet
	 */
	public String getObsDet() {
		return obsDet;
	}

	/**
	 * Sets the value for obsDet.
	 * @param obsDet
	 */
	public void setObsDet(String obsDet) {
		this.obsDet = obsDet;
	}

	/**
	 * Returns the value of valIntDet.
	 * @return valIntDet
	 */
	public Float getValIntDet() {
		return valIntDet;
	}

	/**
	 * Sets the value for valIntDet.
	 * @param valIntDet
	 */
	public void setValIntDet(Float valIntDet) {
		this.valIntDet = valIntDet;
	}

	/**
	 * Returns the value of tipTra.
	 * @return tipTra
	 */
	public String getTipTra() {
		return tipTra;
	}

	/**
	 * Sets the value for tipTra.
	 * @param tipTra
	 */
	public void setTipTra(String tipTra) {
		this.tipTra = tipTra;
	}

	/**
	 * Returns the value of codTipRubComPag.
	 * @return codTipRubComPag
	 */
	public String getCodTipRubComPag() {
		return codTipRubComPag;
	}

	/**
	 * Sets the value for codTipRubComPag.
	 * @param codTipRubComPag
	 */
	public void setCodTipRubComPag(String codTipRubComPag) {
		this.codTipRubComPag = codTipRubComPag;
	}

	/**
	 * Returns the value of numCuoAfi.
	 * @return numCuoAfi
	 */
	public Long getNumCuoAfi() {
		return numCuoAfi;
	}

	/**
	 * Sets the value for numCuoAfi.
	 * @param numCuoAfi
	 */
	public void setNumCuoAfi(Long numCuoAfi) {
		this.numCuoAfi = numCuoAfi;
	}

	/**
	 * Returns the value of codConAfi.
	 * @return codConAfi
	 */
	public String getCodConAfi() {
		return codConAfi;
	}

	/**
	 * Sets the value for codConAfi.
	 * @param codConAfi
	 */
	public void setCodConAfi(String codConAfi) {
		this.codConAfi = codConAfi;
	}

	/**
	 * Returns the value of codTipConAfi.
	 * @return codTipConAfi
	 */
	public String getCodTipConAfi() {
		return codTipConAfi;
	}

	/**
	 * Sets the value for codTipConAfi.
	 * @param codTipConAfi
	 */
	public void setCodTipConAfi(String codTipConAfi) {
		this.codTipConAfi = codTipConAfi;
	}

	/**
	 * Returns the value of intSalCap.
	 * @return intSalCap
	 */
	public Float getIntSalCap() {
		return intSalCap;
	}

	/**
	 * Sets the value for intSalCap.
	 * @param intSalCap
	 */
	public void setIntSalCap(Float intSalCap) {
		this.intSalCap = intSalCap;
	}

	/**
	 * Returns the value of intPerGra.
	 * @return intPerGra
	 */
	public Float getIntPerGra() {
		return intPerGra;
	}

	/**
	 * Sets the value for intPerGra.
	 * @param intPerGra
	 */
	public void setIntPerGra(Float intPerGra) {
		this.intPerGra = intPerGra;
	}

	/**
	 * Returns the value of valSalCap.
	 * @return valSalCap
	 */
	public Float getValSalCap() {
		return valSalCap;
	}

	/**
	 * Sets the value for valSalCap.
	 * @param valSalCap
	 */
	public void setValSalCap(Float valSalCap) {
		this.valSalCap = valSalCap;
	}

	/**
	 * Returns the value of salIntMor.
	 * @return salIntMor
	 */
	public Float getSalIntMor() {
		return salIntMor;
	}

	/**
	 * Sets the value for salIntMor.
	 * @param salIntMor
	 */
	public void setSalIntMor(Float salIntMor) {
		this.salIntMor = salIntMor;
	}

	/**
	 * Returns the value of pk.
	 * @return pk
	 */
	public ComprobantePagoDetallePk getPk() {
		return pk;
	}

	/**
	 * Sets the value for pk.
	 * @param pk
	 */
	public void setPk(ComprobantePagoDetallePk pk) {
		this.pk = pk;
	}

	/**
	 * Returns the value of comprobantePago.
	 * @return comprobantePago
	 */
	public ComprobantePago getComprobantePago() {
		return comprobantePago;
	}

	/**
	 * Sets the value for comprobantePago.
	 * @param comprobantePago
	 */
	public void setComprobantePago(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	/**
	 * Returns the value of dividendoPrestamo.
	 * @return dividendoPrestamo
	 */
	public DividendoPrestamo getDividendoPrestamo() {
		return dividendoPrestamo;
	}

	/**
	 * Sets the value for dividendoPrestamo.
	 * @param dividendoPrestamo
	 */
	public void setDividendoPrestamo(DividendoPrestamo dividendoPrestamo) {
		this.dividendoPrestamo = dividendoPrestamo;
	}

}