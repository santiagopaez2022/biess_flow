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
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoLiquidacionPrestamoPk;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.2 $  [Sep 24, 2007]
 * @author pablo
 */
@Entity
@Table(name = "KSCRETLIQCRE")
public class SaldoLiquidacionPrestamo implements Serializable {

	private static final long serialVersionUID = 8028780913991850928L;
	
	@EmbeddedId
	private SaldoLiquidacionPrestamoPk pk;

	@Column(name = "VALCAPAMO_CE")
	private BigDecimal valCapAmoCe;

	@Column(name = "INTSALCAP_CE")
	private BigDecimal intSalCapCe;

	@Column(name = "INTPERGRA_CE")
	private BigDecimal intPergraCe;

	@Column(name = "INTPERMOR_CE")
	private BigDecimal intPerMorCe;

	@Column(name = "VALCAPAMO_FR")
	private BigDecimal valCapAmoFr;

	@Column(name = "INTSALCAP_FR")
	private BigDecimal intSalCapFr;

	@Column(name = "INTPERGRA_FR")
	private BigDecimal intPerGraFr;

	@Column(name = "INTPERMOR_FR")
	private BigDecimal intPerMorFr;

	@Column(name = "SALVALCAPAMO")
	private BigDecimal salValCapAmo;

	@Column(name = "SALINTSALCAP")
	private BigDecimal salIntSalCap;

	@Column(name = "SALINTPERGRA")
	private BigDecimal salIntPerGra;

	@Column(name = "SALINTPERMOR")
	private BigDecimal salIntPerMor;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUMDIV", referencedColumnName="NUMDIV", insertable=false, updatable=false),
		@JoinColumn(name="CODPRETIP", referencedColumnName="CODPRETIP", insertable=false, updatable=false),
		@JoinColumn(name="ORDPREAFI", referencedColumnName="ORDPREAFI", insertable=false, updatable=false),
		@JoinColumn(name="NUMPREAFI", referencedColumnName="NUMPREAFI", insertable=false, updatable=false),
		@JoinColumn(name="CODPRECLA", referencedColumnName="CODPRECLA", insertable=false, updatable=false)
	})
	private DividendoPrestamo dividendoPrestamo;

	@Transient
	private boolean seleccionado;
	
	/**
	 * Returns the value of pk.
	 * @return pk
	 */
	public SaldoLiquidacionPrestamoPk getPk() {
		return pk;
	}

	
	/**
	 * Sets the value for pk.
	 * @param pk
	 */
	public void setPk(SaldoLiquidacionPrestamoPk pk) {
		this.pk = pk;
	}

	
	/**
	 * Returns the value of valCapAmoCe.
	 * @return valCapAmoCe
	 */
	public BigDecimal getValCapAmoCe() {
		return valCapAmoCe;
	}

	
	/**
	 * Sets the value for valCapAmoCe.
	 * @param valCapAmoCe
	 */
	public void setValCapAmoCe(BigDecimal valCapAmoCe) {
		this.valCapAmoCe = valCapAmoCe;
	}

	
	/**
	 * Returns the value of intSalCapCe.
	 * @return intSalCapCe
	 */
	public BigDecimal getIntSalCapCe() {
		return intSalCapCe;
	}

	
	/**
	 * Sets the value for intSalCapCe.
	 * @param intSalCapCe
	 */
	public void setIntSalCapCe(BigDecimal intSalCapCe) {
		this.intSalCapCe = intSalCapCe;
	}

	
	/**
	 * Returns the value of intPergraCe.
	 * @return intPergraCe
	 */
	public BigDecimal getIntPergraCe() {
		return intPergraCe;
	}

	
	/**
	 * Sets the value for intPergraCe.
	 * @param intPergraCe
	 */
	public void setIntPergraCe(BigDecimal intPergraCe) {
		this.intPergraCe = intPergraCe;
	}

	
	/**
	 * Returns the value of intPerMorCe.
	 * @return intPerMorCe
	 */
	public BigDecimal getIntPerMorCe() {
		return intPerMorCe;
	}

	
	/**
	 * Sets the value for intPerMorCe.
	 * @param intPerMorCe
	 */
	public void setIntPerMorCe(BigDecimal intPerMorCe) {
		this.intPerMorCe = intPerMorCe;
	}

	
	/**
	 * Returns the value of valCapAmoFr.
	 * @return valCapAmoFr
	 */
	public BigDecimal getValCapAmoFr() {
		return valCapAmoFr;
	}

	
	/**
	 * Sets the value for valCapAmoFr.
	 * @param valCapAmoFr
	 */
	public void setValCapAmoFr(BigDecimal valCapAmoFr) {
		this.valCapAmoFr = valCapAmoFr;
	}

	
	/**
	 * Returns the value of intSalCapFr.
	 * @return intSalCapFr
	 */
	public BigDecimal getIntSalCapFr() {
		return intSalCapFr;
	}

	
	/**
	 * Sets the value for intSalCapFr.
	 * @param intSalCapFr
	 */
	public void setIntSalCapFr(BigDecimal intSalCapFr) {
		this.intSalCapFr = intSalCapFr;
	}

	
	/**
	 * Returns the value of intPerGraFr.
	 * @return intPerGraFr
	 */
	public BigDecimal getIntPerGraFr() {
		return intPerGraFr;
	}

	
	/**
	 * Sets the value for intPerGraFr.
	 * @param intPerGraFr
	 */
	public void setIntPerGraFr(BigDecimal intPerGraFr) {
		this.intPerGraFr = intPerGraFr;
	}

	
	/**
	 * Returns the value of intPerMorFr.
	 * @return intPerMorFr
	 */
	public BigDecimal getIntPerMorFr() {
		return intPerMorFr;
	}

	
	/**
	 * Sets the value for intPerMorFr.
	 * @param intPerMorFr
	 */
	public void setIntPerMorFr(BigDecimal intPerMorFr) {
		this.intPerMorFr = intPerMorFr;
	}

	
	/**
	 * Returns the value of salValCapAmo.
	 * @return salValCapAmo
	 */
	public BigDecimal getSalValCapAmo() {
		return salValCapAmo;
	}

	
	/**
	 * Sets the value for salValCapAmo.
	 * @param salValCapAmo
	 */
	public void setSalValCapAmo(BigDecimal salValCapAmo) {
		this.salValCapAmo = salValCapAmo;
	}

	
	/**
	 * Returns the value of salIntSalCap.
	 * @return salIntSalCap
	 */
	public BigDecimal getSalIntSalCap() {
		return salIntSalCap;
	}

	
	/**
	 * Sets the value for salIntSalCap.
	 * @param salIntSalCap
	 */
	public void setSalIntSalCap(BigDecimal salIntSalCap) {
		this.salIntSalCap = salIntSalCap;
	}

	
	/**
	 * Returns the value of salIntPerGra.
	 * @return salIntPerGra
	 */
	public BigDecimal getSalIntPerGra() {
		return salIntPerGra;
	}

	
	/**
	 * Sets the value for salIntPerGra.
	 * @param salIntPerGra
	 */
	public void setSalIntPerGra(BigDecimal salIntPerGra) {
		this.salIntPerGra = salIntPerGra;
	}

	
	/**
	 * Returns the value of salIntPerMo.
	 * @return salIntPerMo
	 */
	public BigDecimal getSalIntPerMor() {
		return salIntPerMor;
	}

	
	/**
	 * Sets the value for salIntPerMo.
	 * @param salIntPerMo
	 */
	public void setSalIntPerMor(BigDecimal salIntPerMo) {
		this.salIntPerMor = salIntPerMo;
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
	
	/**
	 * Obtiene el valor total del saldo
	 * SALVALCAPAMO + SALINTSALCAP + SALINTPERGRA + SALINTPERMOR
	 * @return
	 */
	public BigDecimal getValorSaldo(){
		return salValCapAmo.add(salIntSalCap.add(salIntPerGra.add(salIntPerMor)));
	}


	/**
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}


	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

}