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
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * Clase modelo con los datos del dividendo calculado
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
public class DividendoCalculo implements Serializable {

	private int numeroDividendo;
	private int varidadCredito;
	private int tipoCredito;
	private Date fechapagoDividendo;
	private BigDecimal valorAbonoCapital;
	private BigDecimal valorInteres;
	private BigDecimal valorCuota;
	private BigDecimal valorSegurSaldo;
	private BigDecimal valorPeriodoGraciaInterzafra;
	private BigDecimal valorDividendo;
	private String tipoPeriodo = "M";
	private int anioDividendo;
	private int mesDividendo;
	private BigDecimal valorSaldoCapital;
	// Valor del interes de periodo de gracia
	private BigDecimal valorIntPerGra;
	// Valores nuevos requeridos por SAC
	private BigDecimal seguroDesgravamen;
	private String estado;

	public DividendoCalculo() {
	}

	/**
	 * @return the numeroDividendo
	 */
	public int getNumeroDividendo() {
		return numeroDividendo;
	}

	/**
	 * @return the varidadCredito
	 */
	public int getVaridadCredito() {
		return varidadCredito;
	}

	/**
	 * @return the tipoCredito
	 */
	public int getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @return the fechapagoDividendo
	 */
	public Date getFechapagoDividendo() {
		return fechapagoDividendo;
	}

	/**
	 * @return the valorAbonoCapital
	 */
	public BigDecimal getValorAbonoCapital() {
		return valorAbonoCapital;
	}

	/**
	 * @return the valorInteres
	 */
	public BigDecimal getValorInteres() {
		return valorInteres;
	}

	/**
	 * @return the valorCuota
	 */
	public BigDecimal getValorCuota() {
		return valorCuota;
	}

	/**
	 * @return the valorSegurSaldo
	 */
	public BigDecimal getValorSegurSaldo() {
		return valorSegurSaldo;
	}

	/**
	 * @return the valorDividendo
	 */
	public BigDecimal getValorDividendo() {
		return valorDividendo;
	}

	/**
	 * @return the tipoPeriodo
	 */
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	/**
	 * @return the anioDividendo
	 */
	public int getAnioDividendo() {
		return anioDividendo;
	}

	/**
	 * @return the valorSaldoCapital
	 */
	public BigDecimal getvalorSaldoCapital() {
		return valorSaldoCapital;
	}

	
	/**
	 * @return the mesDividendo
	 */
	public int getMesDividendo() {
		return mesDividendo;
	}

	/**
	 * @param numeroDividendo
	 *            the numeroDividendo to set
	 */
	public void setNumeroDividendo(int numeroDividendo) {
		this.numeroDividendo = numeroDividendo;
	}

	/**
	 * @param varidadCredito
	 *            the varidadCredito to set
	 */
	public void setVaridadCredito(int varidadCredito) {
		this.varidadCredito = varidadCredito;
	}

	/**
	 * @param tipoCredito
	 *            the tipoCredito to set
	 */
	public void setTipoCredito(int tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @param fechapagoDividendo
	 *            the fechapagoDividendo to set
	 */
	public void setFechapagoDividendo(Date fechapagoDividendo) {
		this.fechapagoDividendo = fechapagoDividendo;
	}

	/**
	 * @param valorAbonoCapital
	 *            the valorAbonoCapital to set
	 */
	public void setValorAbonoCapital(BigDecimal valorAbonoCapital) {
		this.valorAbonoCapital = valorAbonoCapital;
	}

	/**
	 * @param valorInteres
	 *            the valorInteres to set
	 */
	public void setValorInteres(BigDecimal valorInteres) {
		this.valorInteres = valorInteres;

	}

	/**
	 * @param valorCuota
	 *            the valorCuota to set
	 */
	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	/**
	 * @param valorSegurSaldo
	 *            the valorSegurSaldo to set
	 */
	public void setValorSegurSaldo(BigDecimal valorSegurSaldo) {
		this.valorSegurSaldo = valorSegurSaldo;
	}

	/**
	 * @param valorDividendo
	 *            the valorDividendo to set
	 */
	public void setValorDividendo(BigDecimal valorDividendo) {
		this.valorDividendo = valorDividendo;
	}

	/**
	 * @param tipoPeriodo
	 *            the tipoPeriodo to set
	 */
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	/**
	 * @param anioDividendo
	 *            the anioDividendo to set
	 */
	public void setAnioDividendo(int anioDividendo) {
		this.anioDividendo = anioDividendo;
	}

	/**
	 * @param mesDividendo
	 *            the mesDividendo to set
	 */
	public void setMesDividendo(int mesDividendo) {
		this.mesDividendo = mesDividendo;
	}

	/**
	 * @param valorSaldoCapital
	 *            the valorSaldoCapital to set
	 */
	public void setvalorSaldoCapital(BigDecimal valorSaldoCapital) {
		this.valorSaldoCapital = valorSaldoCapital;
	}

	public BigDecimal getValorPeriodoGraciaInterzafra() {
		return valorPeriodoGraciaInterzafra;
	}

	public void setValorPeriodoGraciaInterzafra(
			BigDecimal valorPeriodoGraciaInterzafra) {
		this.valorPeriodoGraciaInterzafra = valorPeriodoGraciaInterzafra;
	}
	
	public BigDecimal getValorIntPerGra() {
		return valorIntPerGra;
	}

	public void setValorIntPerGra(BigDecimal valorIntPerGra) {
		this.valorIntPerGra = valorIntPerGra;
	}

	public BigDecimal getSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(BigDecimal seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}