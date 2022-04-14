/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;

/**
 * 
 * <b> Representacion ws entrada del Objeto orden detalle de compra. </b>
 * 
 * @author Ricardo Tituana
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/10/03 $]
 *          </p>
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CRE_CRERESHIST_TBL")
public class PrestamoResumenHistorico implements Serializable {

	@Id
	@Column(nullable = false, name = "CH_ID_CRERESHISTORICO")
	private long chIdCrereshistorico;

	// Asociación a Préstamos
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI"),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI"),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP"),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA") })
	private Prestamo prestamo;

	@Column(nullable = false, name = "CH_FEC_CREACION")
	private Date chFecCreacion;

	@Column(nullable = false, name = "CH_FEC_INICIO")
	private Date chFecInicio;

	@Column(nullable = false, name = "CH_FEC_FIN")
	private Date chFecFin;

	@Column(nullable = false, name = "CH_TIPO_CUENTA")
	@Enumerated(EnumType.STRING)
	private TipoCuenta chTipoCuenta;

	@Column(nullable = false, name = "CH_NUM_CTABANCARIA")
	private String chNumCtabancaria;

	@Column(nullable = false, name = "CH_RUC_INTSFINANCIERA")
	private String chRucIntsfinanciera;

	@Column(nullable = false, name = "CH_VALOR_DIVIDENDO")
	private Double chValorDividendo;

	@Column(nullable = false, name = "CH_MONTO")
	private Double chMonto;

	@Column(nullable = false, name = "CH_PLAZO")
	private Long chPlazo;

	@Column(nullable = false, name = "CH_TASA")
	private Double chTasa;

	@Column(nullable = false, name = "CH_INT_DIASGRACIA")
	private Double chIntDiasgracia;

	@Column(nullable = false, name = "CH_VALOR_SEGUROSALDOS")
	private Double chValorSegurosaldos;

	@Column(nullable = false, name = "CH_OBSERVACION")
	private String chObservacion;

	@Column(nullable = false, name = "CH_FEC_TRANSACCION")
	private Date chFecTransaccion;
	
	//cambios Inc - 13346 pq-fraudes	
	//Cambios andres 16/08/2011
	@Column(nullable = true, name = "CR_CEDULAFUNCIONARIO")
	private String cr_cedulafuncionario;
	
	@Column(nullable = true, name = "CR_MOTIVORECHAZO")
	private Long cr_motivorechazo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CR_MOTIVORECHAZO", referencedColumnName = "CRE_MOTIVORECHAZO_PK", insertable = false, updatable = false)
	private CatalogoRechazoCredito catalogorechazo;
	
	public CatalogoRechazoCredito getCatalogorechazo() {
		return catalogorechazo;
	}

	public void setCatalogorechazo(CatalogoRechazoCredito catalogorechazo) {
		this.catalogorechazo = catalogorechazo;
	}

	public String getCr_cedulafuncionario() {
		return cr_cedulafuncionario;
	}

	public void setCr_cedulafuncionario(String cr_cedulafuncionario) {
		this.cr_cedulafuncionario = cr_cedulafuncionario;
	}

	public Long getCr_motivorechazo() {
		return cr_motivorechazo;
	}

	public void setCr_motivorechazo(Long cr_motivorechazo) {
		this.cr_motivorechazo = cr_motivorechazo;
	}

	//fin cambios
	public PrestamoResumenHistorico() {
	}

	/**
	 * 
	 * <b> Identificador unico del histórico. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return long Identificador del histórico
	 */
	public long getChIdCrereshistorico() {
		return chIdCrereshistorico;
	}

	/**
	 * 
	 * <b> Identificador unico del histórico. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chIdCrereshistorico
	 *            Identificador del histórico
	 */
	public void setChIdCrereshistorico(long chIdCrereshistorico) {
		this.chIdCrereshistorico = chIdCrereshistorico;
	}

	/**
	 * 
	 * <b> Prestamo. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Prestamo Prestamo
	 */

	public Prestamo getPrestamo() {
		return prestamo;
	}

	/**
	 * 
	 * <b> Prestamo. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param prestamo
	 *            Prestamo
	 */
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	/**
	 * 
	 * <b> Fecha de creción. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Date Fecha de creción
	 */
	public Date getChFecCreacion() {
		return chFecCreacion;
	}

	/**
	 * 
	 * <b> Fecha de creción. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chFecCreacion
	 *            Fecha de creción.
	 */
	public void setChFecCreacion(Date chFecCreacion) {
		this.chFecCreacion = chFecCreacion;
	}

	/**
	 * 
	 * <b> Fecha de Inicio. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Date Fecha de Inicio
	 */
	public Date getChFecInicio() {
		return chFecInicio;
	}

	/**
	 * 
	 * <b> Fecha de Inicio. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chFecInicio
	 *            Fecha de Inicio.
	 */
	public void setChFecInicio(Date chFecInicio) {
		this.chFecInicio = chFecInicio;
	}

	/**
	 * 
	 * <b> Fecha fin </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Fecha fin
	 */
	public Date getChFecFin() {
		return chFecFin;
	}

	/**
	 * 
	 * <b> Fecha fin </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chFecFin
	 *            Fecha fin
	 */
	public void setChFecFin(Date chFecFin) {
		this.chFecFin = chFecFin;
	}

	/**
	 * 
	 * <b> Tipo de Cuenta </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return TipoCuenta Tipo de Cuenta
	 */
	public TipoCuenta getChTipoCuenta() {
		return chTipoCuenta;
	}

	/**
	 * 
	 * <b> Tipo de Cuenta </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chTipoCuenta
	 *            Tipo de Cuenta
	 */
	public void setChTipoCuenta(TipoCuenta chTipoCuenta) {
		this.chTipoCuenta = chTipoCuenta;
	}

	/**
	 * 
	 * <b> Número de cuenta Bancaria. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return String Número de cuenta Bancaria
	 */
	public String getChNumCtabancaria() {
		return chNumCtabancaria;
	}

	/**
	 * 
	 * <b> Número de cuenta Bancaria. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chNumCtabancaria
	 */
	public void setChNumCtabancaria(String chNumCtabancaria) {
		this.chNumCtabancaria = chNumCtabancaria;
	}

	/**
	 * 
	 * <b> Ruc Institucion Financiera. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return String Institucion Financiera
	 */
	public String getChRucIntsfinanciera() {
		return chRucIntsfinanciera;
	}

	/**
	 * 
	 * <b> Ruc Institucion Financiera. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chRucIntsfinanciera
	 *            Ruc Institucion Financiera
	 */
	public void setChRucIntsfinanciera(String chRucIntsfinanciera) {
		this.chRucIntsfinanciera = chRucIntsfinanciera;
	}

	/**
	 * 
	 * <b> Valor del dividendo. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Double Valor del dividendo
	 */
	public Double getChValorDividendo() {
		return chValorDividendo;
	}

	/**
	 * 
	 * <b> Valor del dividendo. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chValorDividendo
	 *            Valor del dividendo.
	 */
	public void setChValorDividendo(Double chValorDividendo) {
		this.chValorDividendo = chValorDividendo;
	}

	/**
	 * 
	 * <b> Monto del crédito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Double Monto del crédito
	 */
	public Double getChMonto() {
		return chMonto;
	}

	/**
	 * 
	 * <b> Monto del crédito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chMonto
	 *            Monto del crédito
	 */
	public void setChMonto(Double chMonto) {
		this.chMonto = chMonto;
	}

	/**
	 * 
	 * <b> Plazo del crédito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Long Plazo del crédito
	 */
	public Long getChPlazo() {
		return chPlazo;
	}

	/**
	 * 
	 * <b> Plazo del crédito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chPlazo
	 *            Plazo del crédito
	 */
	public void setChPlazo(Long chPlazo) {
		this.chPlazo = chPlazo;
	}

	/**
	 * 
	 * <b> Tasa del crédito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Double Tasa del crédito
	 */
	public Double getChTasa() {
		return chTasa;
	}

	/**
	 * 
	 * <b> Tasa del crédito. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chTasa
	 *            Tasa del crédito.
	 */
	public void setChTasa(Double chTasa) {
		this.chTasa = chTasa;
	}

	/**
	 * 
	 * <b> Interés días de gracia. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Double Interés días de gracia
	 */
	public Double getChIntDiasgracia() {
		return chIntDiasgracia;
	}

	/**
	 * 
	 * <b> Interés días de gracia. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chIntDiasgracia
	 *            Interés días de gracia.
	 */
	public void setChIntDiasgracia(Double chIntDiasgracia) {
		this.chIntDiasgracia = chIntDiasgracia;
	}

	/**
	 * 
	 * <b> Valor de seguro de saldos. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Double Valor de seguro de saldos
	 */
	public Double getChValorSegurosaldos() {
		return chValorSegurosaldos;
	}

	/**
	 * 
	 * <b> Valor de seguro de saldos. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chValorSegurosaldos
	 *            Valor de seguro de saldos
	 */
	public void setChValorSegurosaldos(Double chValorSegurosaldos) {
		this.chValorSegurosaldos = chValorSegurosaldos;
	}

	/**
	 * 
	 * <b> Observación. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return String Observación
	 */
	public String getChObservacion() {
		return chObservacion;
	}

	/**
	 * 
	 * <b> Observación. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chObservacion
	 *            Observación
	 */
	public void setChObservacion(String chObservacion) {
		this.chObservacion = chObservacion;
	}

	/**
	 * 
	 * <b> Fecha de transacción. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @return Date Fecha de transacción
	 */
	public Date getChFecTransaccion() {
		return chFecTransaccion;
	}

	/**
	 * 
	 * <b> Fecha de transacción. </b>
	 * <p>
	 * [Author: cbastidas, Date: 23/05/2011]
	 * </p>
	 * 
	 * @param chFecTransaccion
	 *            Fecha de transacción
	 */
	public void setChFecTransaccion(Date chFecTransaccion) {
		this.chFecTransaccion = chFecTransaccion;
	}

}
