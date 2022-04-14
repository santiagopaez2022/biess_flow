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
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoDetallePk;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.2 $ [Sep 19, 2007]
 * @author pablo
 */
@Entity
@Table(name = "KSCRETPRELIQDET")			   
public class LiquidacionPrestamoDetalle implements Serializable {

	private static final long serialVersionUID = 3513461188424753150L;

	@EmbeddedId
	private LiquidacionPrestamoDetallePk pk;

	@Column(name = "CODTIPCONAFI")
	private Long codTipConAfi;

	@Column(name = "NUMCUOAFI")
	private Long numCuoAfi;

	@Column(name = "CODCONAFI")
	private String codConAfi;

	@Column(name = "NUMDIV")
	private Long numDiv;

	@Column(name = "CODPRETIP")
	private Long codPreTip;

	@Column(name = "CODPRECLA")
	private Long codPreCla;

	@Column(name = "NUMPREAFI")
	private Long numPreAfi;

	@Column(name = "ORDPREAFI")
	private Long ordPreAfi;

	@Column(name = "VALINTMOR", nullable = false)
	private BigDecimal valIntMor;

	@Column(name = "VALDIVTOT", nullable = false)
	private BigDecimal valDivTot;

	@Column(name = "VALCOESEGSAL")
	private BigDecimal valCoeSegSal;

	@Column(name = "VALCAPAMO")
	private BigDecimal valCapAmo;

	@Column(name = "INTSALCAP")
	private BigDecimal intSalCap;

	@Column(name = "INTPERGRA")
	private BigDecimal intPerGra;

	@Column(name = "INDDIVTOT")
	private String indDivTot;

	@Column(name = "INDESTDIV")
	private String indEstDiv;

	@ManyToOne
	@JoinColumn(name = "NUMLIQPRE", referencedColumnName = "NUMLIQPRE", insertable = false, updatable = false)
	private LiquidacionPrestamo liquidacionPrestamo;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false),
			@JoinColumn(name = "NUMDIV", referencedColumnName = "NUMDIV", insertable = false, updatable = false) })
	private DividendoPrestamo dividendoPrestamo;

	/**
	 * Returns the value of pk.
	 * 
	 * @return pk
	 */
	public LiquidacionPrestamoDetallePk getPk() {
		return pk;
	}

	/**
	 * Sets the value for pk.
	 * 
	 * @param pk
	 */
	public void setPk(LiquidacionPrestamoDetallePk pk) {
		this.pk = pk;
	}

	/**
	 * Returns the value of codTipConAfi.
	 * 
	 * @return codTipConAfi
	 */
	public Long getCodTipConAfi() {
		return codTipConAfi;
	}

	/**
	 * Sets the value for codTipConAfi.
	 * 
	 * @param codTipConAfi
	 */
	public void setCodTipConAfi(Long codTipConAfi) {
		this.codTipConAfi = codTipConAfi;
	}

	/**
	 * Returns the value of numCuoAfi.
	 * 
	 * @return numCuoAfi
	 */
	public Long getNumCuoAfi() {
		return numCuoAfi;
	}

	/**
	 * Sets the value for numCuoAfi.
	 * 
	 * @param numCuoAfi
	 */
	public void setNumCuoAfi(Long numCuoAfi) {
		this.numCuoAfi = numCuoAfi;
	}

	/**
	 * Returns the value of codConAfi.
	 * 
	 * @return codConAfi
	 */
	public String getCodConAfi() {
		return codConAfi;
	}

	/**
	 * Sets the value for codConAfi.
	 * 
	 * @param codConAfi
	 */
	public void setCodConAfi(String codConAfi) {
		this.codConAfi = codConAfi;
	}

	/**
	 * Returns the value of numDiv.
	 * 
	 * @return numDiv
	 */
	public Long getNumDiv() {
		return numDiv;
	}

	/**
	 * Sets the value for numDiv.
	 * 
	 * @param numDiv
	 */
	public void setNumDiv(Long numDiv) {
		this.numDiv = numDiv;
	}

	/**
	 * Returns the value of codPreTip.
	 * 
	 * @return codPreTip
	 */
	public Long getCodPreTip() {
		return codPreTip;
	}

	/**
	 * Sets the value for codPreTip.
	 * 
	 * @param codPreTip
	 */
	public void setCodPreTip(Long codPreTip) {
		this.codPreTip = codPreTip;
	}

	/**
	 * Returns the value of codPreCla.
	 * 
	 * @return codPreCla
	 */
	public Long getCodPreCla() {
		return codPreCla;
	}

	/**
	 * Sets the value for codPreCla.
	 * 
	 * @param codPreCla
	 */
	public void setCodPreCla(Long codPreCla) {
		this.codPreCla = codPreCla;
	}

	/**
	 * Returns the value of numPreAfi.
	 * 
	 * @return numPreAfi
	 */
	public Long getNumPreAfi() {
		return numPreAfi;
	}

	/**
	 * Sets the value for numPreAfi.
	 * 
	 * @param numPreAfi
	 */
	public void setNumPreAfi(Long numPreAfi) {
		this.numPreAfi = numPreAfi;
	}

	/**
	 * Returns the value of ordPreAfi.
	 * 
	 * @return ordPreAfi
	 */
	public Long getOrdPreAfi() {
		return ordPreAfi;
	}

	/**
	 * Sets the value for ordPreAfi.
	 * 
	 * @param ordPreAfi
	 */
	public void setOrdPreAfi(Long ordPreAfi) {
		this.ordPreAfi = ordPreAfi;
	}

	/**
	 * @return the valIntMor
	 */
	public BigDecimal getValIntMor() {
		return valIntMor;
	}

	/**
	 * @param valIntMor
	 *            the valIntMor to set
	 */
	public void setValIntMor(BigDecimal valIntMor) {
		this.valIntMor = valIntMor;
	}

	/**
	 * @return the valDivTot
	 */
	public BigDecimal getValDivTot() {
		return valDivTot;
	}

	/**
	 * @param valDivTot
	 *            the valDivTot to set
	 */
	public void setValDivTot(BigDecimal valDivTot) {
		this.valDivTot = valDivTot;
	}

	/**
	 * @return the valCoeSegSal
	 */
	public BigDecimal getValCoeSegSal() {
		return valCoeSegSal;
	}

	/**
	 * @param valCoeSegSal
	 *            the valCoeSegSal to set
	 */
	public void setValCoeSegSal(BigDecimal valCoeSegSal) {
		this.valCoeSegSal = valCoeSegSal;
	}

	/**
	 * @return the valCapAmo
	 */
	public BigDecimal getValCapAmo() {
		return valCapAmo;
	}

	/**
	 * @param valCapAmo
	 *            the valCapAmo to set
	 */
	public void setValCapAmo(BigDecimal valCapAmo) {
		this.valCapAmo = valCapAmo;
	}

	/**
	 * @return the intSalCap
	 */
	public BigDecimal getIntSalCap() {
		return intSalCap;
	}

	/**
	 * @param intSalCap
	 *            the intSalCap to set
	 */
	public void setIntSalCap(BigDecimal intSalCap) {
		this.intSalCap = intSalCap;
	}

	/**
	 * @return the intPerGra
	 */
	public BigDecimal getIntPerGra() {
		return intPerGra;
	}

	/**
	 * @param intPerGra
	 *            the intPerGra to set
	 */
	public void setIntPerGra(BigDecimal intPerGra) {
		this.intPerGra = intPerGra;
	}

	/**
	 * Returns the value of indDivTot.
	 * 
	 * @return indDivTot
	 */
	public String getIndDivTot() {
		return indDivTot;
	}

	/**
	 * Sets the value for indDivTot.
	 * 
	 * @param indDivTot
	 */
	public void setIndDivTot(String indDivTot) {
		this.indDivTot = indDivTot;
	}

	/**
	 * Returns the value of indEstDiv.
	 * 
	 * @return indEstDiv
	 */
	public String getIndEstDiv() {
		return indEstDiv;
	}

	/**
	 * Sets the value for indEstDiv.
	 * 
	 * @param indEstDiv
	 */
	public void setIndEstDiv(String indEstDiv) {
		this.indEstDiv = indEstDiv;
	}

	/**
	 * Returns the value of liquidacionPrestamo.
	 * 
	 * @return liquidacionPrestamo
	 */
	public LiquidacionPrestamo getLiquidacionPrestamo() {
		return liquidacionPrestamo;
	}

	/**
	 * Sets the value for liquidacionPrestamo.
	 * 
	 * @param liquidacionPrestamo
	 */
	public void setLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo) {
		this.liquidacionPrestamo = liquidacionPrestamo;
	}

	/**
	 * Returns the value of dividendoPrestamo.
	 * 
	 * @return dividendoPrestamo
	 */
	public DividendoPrestamo getDividendoPrestamo() {
		return dividendoPrestamo;
	}

	/**
	 * Sets the value for dividendoPrestamo.
	 * 
	 * @param dividendoPrestamo
	 */
	public void setDividendoPrestamo(DividendoPrestamo dividendoPrestamo) {
		this.dividendoPrestamo = dividendoPrestamo;
	}

}