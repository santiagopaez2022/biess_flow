/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;

/**
 * Clase que ayuda a pasar parametros para ejecutar las reglas de precalificacion.
 *  
 * @author Omar Villanueva
 * @version 1.0
 *
 */
public class ParamsReglasPrecalificacion implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal sumaSaldos;
	
	private BigDecimal sbu;
	
	private BigDecimal numsbu;
	
	private boolean tieneCFA;
	
	private boolean discapacitado;

	// INC-2135 Pensiones Jubilados/Pensionistas
	private InformacionPrestacionPensionado informacionPrestacionPensionado;
	
	private BigDecimal valorRetencionesJudiciales;
	
	// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
	private String tipoListaControl;
	
	// Verificacion si la persona es damnificada por terremoto
	private boolean validaBiessEmergente;
	private BigDecimal numeroImposicionesEmergente;
	private BigDecimal imposicionesConsecutivasEmergente;
	private boolean paramMoraPlanillaEmergente;
	private boolean paramMoraComprobanteEmergente;
	
	// Verificacion si valida mora para sector publico y si pertenece a beneficiarios sector publico
	private boolean paramMoraComprobantePublicos;
	private boolean validaPublicos;
	
	//Determina si alguno de sus empleadores esta en mora
	private boolean tieneMoraPatronal;
	
	// Numero de imposiciones acumuladas y consecutivas
	private int numeroAportacionesAcumuladasRequeridas;
	private int numeroAportacionesConsecutivasRequeridas;

	
	//Validaciones migracion cartera
	private GarantiaCesantia garantiaCesantia;

	/**
	 * @return the sumaSaldos
	 */
	public BigDecimal getSumaSaldos() {
		return sumaSaldos;
	}

	/**
	 * @param sumaSaldos the sumaSaldos to set
	 */
	public void setSumaSaldos(BigDecimal sumaSaldos) {
		this.sumaSaldos = sumaSaldos;
	}

	/**
	 * @return the sbu
	 */
	public BigDecimal getSbu() {
		return sbu;
	}

	/**
	 * @param sbu the sbu to set
	 */
	public void setSbu(BigDecimal sbu) {
		this.sbu = sbu;
	}

	/**
	 * @return the numsbu
	 */
	public BigDecimal getNumsbu() {
		return numsbu;
	}

	/**
	 * @param numsbu the numsbu to set
	 */
	public void setNumsbu(BigDecimal numsbu) {
		this.numsbu = numsbu;
	}

	/**
	 * @return the tieneCFA
	 */
	public boolean isTieneCFA() {
		return tieneCFA;
	}

	/**
	 * @param tieneCFA the tieneCFA to set
	 */
	public void setTieneCFA(boolean tieneCFA) {
		this.tieneCFA = tieneCFA;
	}

	/**
	 * @return the discapacitado
	 */
	public boolean isDiscapacitado() {
		return discapacitado;
	}

	/**
	 * @param discapacitado the discapacitado to set
	 */
	public void setDiscapacitado(boolean discapacitado) {
		this.discapacitado = discapacitado;
	}

	/**
	 * @return the valorRetencionesJudiciales
	 */
	public BigDecimal getValorRetencionesJudiciales() {
		return valorRetencionesJudiciales;
	}

	/**
	 * @param valorRetencionesJudiciales the valorRetencionesJudiciales to set
	 */
	public void setValorRetencionesJudiciales(BigDecimal valorRetencionesJudiciales) {
		this.valorRetencionesJudiciales = valorRetencionesJudiciales;
	}

	/**
	 * @return the informacionPrestacionPensionado
	 */
	public InformacionPrestacionPensionado getInformacionPrestacionPensionado() {
		return informacionPrestacionPensionado;
	}

	/**
	 * @param informacionPrestacionPensionado the informacionPrestacionPensionado to set
	 */
	public void setInformacionPrestacionPensionado(InformacionPrestacionPensionado informacionPrestacionPensionado) {
		this.informacionPrestacionPensionado = informacionPrestacionPensionado;
	}

	/**
	 * @return the tipoListaControl
	 */
	public String getTipoListaControl() {
		return tipoListaControl;
	}

	/**
	 * @param tipoListaControl the tipoListaControl to set
	 */
	public void setTipoListaControl(String tipoListaControl) {
		this.tipoListaControl = tipoListaControl;
	}

	public boolean isParamMoraComprobantePublicos() {
		return paramMoraComprobantePublicos;
	}

	public void setParamMoraComprobantePublicos(boolean paramMoraComprobantePublicos) {
		this.paramMoraComprobantePublicos = paramMoraComprobantePublicos;
	}

	public boolean isValidaPublicos() {
		return validaPublicos;
	}

	public void setValidaPublicos(boolean validaPublicos) {
		this.validaPublicos = validaPublicos;
	}
	
	public boolean isValidaBiessEmergente() {
		return validaBiessEmergente;
	}

	public void setValidaBiessEmergente(boolean validaBiessEmergente) {
		this.validaBiessEmergente = validaBiessEmergente;
	}

	public BigDecimal getNumeroImposicionesEmergente() {
		return numeroImposicionesEmergente;
	}

	public void setNumeroImposicionesEmergente(BigDecimal numeroImposicionesEmergente) {
		this.numeroImposicionesEmergente = numeroImposicionesEmergente;
	}

	public BigDecimal getImposicionesConsecutivasEmergente() {
		return imposicionesConsecutivasEmergente;
	}

	public void setImposicionesConsecutivasEmergente(BigDecimal imposicionesConsecutivasEmergente) {
		this.imposicionesConsecutivasEmergente = imposicionesConsecutivasEmergente;
	}

	public boolean isParamMoraPlanillaEmergente() {
		return paramMoraPlanillaEmergente;
	}

	public void setParamMoraPlanillaEmergente(boolean paramMoraPlanillaEmergente) {
		this.paramMoraPlanillaEmergente = paramMoraPlanillaEmergente;
	}

	public boolean isParamMoraComprobanteEmergente() {
		return paramMoraComprobanteEmergente;
	}

	public void setParamMoraComprobanteEmergente(boolean paramMoraComprobanteEmergente) {
		this.paramMoraComprobanteEmergente = paramMoraComprobanteEmergente;
	}
	
	/**
	 * @return
	 */
	public boolean isTieneMoraPatronal() {
		return tieneMoraPatronal;
	}

	/**
	 * @param tieneMoraPatronal
	 */
	public void setTieneMoraPatronal(boolean tieneMoraPatronal) {
		this.tieneMoraPatronal = tieneMoraPatronal;
	}

	public int getNumeroAportacionesAcumuladasRequeridas() {
		return numeroAportacionesAcumuladasRequeridas;
	}

	public void setNumeroAportacionesAcumuladasRequeridas(int numeroAportacionesAcumuladasRequeridas) {
		this.numeroAportacionesAcumuladasRequeridas = numeroAportacionesAcumuladasRequeridas;
	}

	public int getNumeroAportacionesConsecutivasRequeridas() {
		return numeroAportacionesConsecutivasRequeridas;
	}

	public void setNumeroAportacionesConsecutivasRequeridas(int numeroAportacionesConsecutivasRequeridas) {
		this.numeroAportacionesConsecutivasRequeridas = numeroAportacionesConsecutivasRequeridas;
	}
	
	public GarantiaCesantia getGarantiaCesantia() {
		return garantiaCesantia;
	}

	public void setGarantiaCesantia(GarantiaCesantia garantiaCesantia) {
		this.garantiaCesantia = garantiaCesantia;
	}
	
}
