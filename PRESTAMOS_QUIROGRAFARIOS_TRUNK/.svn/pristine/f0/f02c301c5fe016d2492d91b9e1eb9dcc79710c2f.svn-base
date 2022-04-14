package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="KSCREVPARCRE")
public class ParametrosCredito implements Serializable {

	private static final long serialVersionUID = 5299184145030481153L;
	/*
	 IDPARCRE                         Yes    CHAR(1)                  
	TASINTSEM                        Yes    NUMBER                   
	TASSEGSAL                        Yes    NUMBER                   
	MINNUMIMP                        Yes    NUMBER                   
	NUMIMPCONS                       Yes    NUMBER                   
	TOPSALMIN                        Yes    NUMBER                   
	FACCAPEND                        Yes    NUMBER                   
	MNTMAXCRE                        Yes    NUMBER
	 */
	@Id
	@Column(name ="IDPARCRE",nullable = false, updatable = false, insertable = false)
	private String idParametro;
	
	@Column(name ="TASINTSEM",nullable = false, updatable = false, insertable = false)
	private BigDecimal tasaInteresSemestral;
	
	@Column(name ="TASSEGSAL",nullable = false, updatable = false, insertable = false)
	private BigDecimal tasaSeguroSaldos;
	
	@Column(name ="MINNUMIMP",nullable = false, updatable = false, insertable = false)
	private BigDecimal numeroMinimoImposiciones;
	
	@Column(name ="NUMIMPCONS",nullable = false, updatable = false, insertable = false)
	private BigDecimal numeroImposicionesConsecutivas;
	
	@Column(name ="TOPSALMIN",nullable = false, updatable = false, insertable = false)
	private BigDecimal topsalmin;
	
	@Column(name ="FACCAPEND",nullable = false, updatable = false, insertable = false)
	private BigDecimal factorCapacidadEdeudamiento;
	
	@Column(name ="MNTMAXCRE",nullable = false, updatable = false, insertable = false)
	private BigDecimal montoMaximoCredito;
	
	/**
	 * @return the idParametro
	 */
	public String getIdParametro() {
		return idParametro;
	}
	/**
	 * @return the tasaInteresSemestral
	 */
	public BigDecimal getTasaInteresSemestral() {
		return tasaInteresSemestral;
	}
	/**
	 * @return the tasaSeguroSaldos
	 */
	public BigDecimal getTasaSeguroSaldos() {
		return tasaSeguroSaldos;
	}
	/**
	 * @return the numeroMinimoImposiciones
	 */
	public BigDecimal getNumeroMinimoImposiciones() {
		return numeroMinimoImposiciones;
	}
	/**
	 * @return the numeroImposicionesConsecutivas
	 */
	public BigDecimal getNumeroImposicionesConsecutivas() {
		return numeroImposicionesConsecutivas;
	}
	/**
	 * @return the topsalmin
	 */
	public BigDecimal getTopsalmin() {
		return topsalmin;
	}
	/**
	 * @return the factorCapacidadEdeudamiento
	 */
	public BigDecimal getFactorCapacidadEdeudamiento() {
		return factorCapacidadEdeudamiento;
	}
	/**
	 * @return the montoMaximoCredito
	 */
	public BigDecimal getMontoMaximoCredito() {
		return montoMaximoCredito;
	}
	/**
	 * @param idParametro the idParametro to set
	 */
	public void setIdParametro(String idParametro) {
		this.idParametro = idParametro;
	}
	/**
	 * @param tasaInteresSemestral the tasaInteresSemestral to set
	 */
	public void setTasaInteresSemestral(BigDecimal tasaInteresSemestral) {
		this.tasaInteresSemestral = tasaInteresSemestral;
	}
	/**
	 * @param tasaSeguroSaldos the tasaSeguroSaldos to set
	 */
	public void setTasaSeguroSaldos(BigDecimal tasaSeguroSaldos) {
		this.tasaSeguroSaldos = tasaSeguroSaldos;
	}
	/**
	 * @param numeroMinimoImposiciones the numeroMinimoImposiciones to set
	 */
	public void setNumeroMinimoImposiciones(BigDecimal numeroMinimoImposiciones) {
		this.numeroMinimoImposiciones = numeroMinimoImposiciones;
	}
	/**
	 * @param numeroImposicionesConsecutivas the numeroImposicionesConsecutivas to set
	 */
	public void setNumeroImposicionesConsecutivas(BigDecimal numeroImposicionesConsecutivas) {
		this.numeroImposicionesConsecutivas = numeroImposicionesConsecutivas;
	}
	/**
	 * @param topsalmin the topsalmin to set
	 */
	public void setTopsalmin(BigDecimal topsalmin) {
		this.topsalmin = topsalmin;
	}
	/**
	 * @param factorCapacidadEdeudamiento the factorCapacidadEdeudamiento to set
	 */
	public void setFactorCapacidadEdeudamiento(BigDecimal factorCapacidadEdeudamiento) {
		this.factorCapacidadEdeudamiento = factorCapacidadEdeudamiento;
	}
	/**
	 * @param montoMaximoCredito the montoMaximoCredito to set
	 */
	public void setMontoMaximoCredito(BigDecimal montoMaximoCredito) {
		this.montoMaximoCredito = montoMaximoCredito;
	}
	

}
