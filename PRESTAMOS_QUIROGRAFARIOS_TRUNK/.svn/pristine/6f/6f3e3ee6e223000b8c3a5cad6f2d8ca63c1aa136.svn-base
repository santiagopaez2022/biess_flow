/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

/**
 * 
 * <b> Clase que permite realizar el paso de los datos del credito y garantías
 * en préstamos quirografarios. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/11 14:03:38 $]
 *          </p>
 */
public class DatosGarantia implements Serializable {
	private static final long serialVersionUID = -3495592654631505541L;

	public DatosGarantia() {
	}

	private String cedula;
	private long numeroPrestamo;
	private int idTipocredito;
	private int idVariedadCredito;
	private Date fechaSolicitud;
	private BigDecimal montocredito;
	private String tipoSolicitante;
	ValidarRequisitosFondos valReqFondos;
	TipoPrestamista tipoPrestamista;
	// Parámetro del monto máximo del crédito
	private BigDecimal parametroMontoMaximo;
	// Comprometidos del credito
	private BigDecimal comprometidoCesantias;
	private BigDecimal comprometidoFondos;
	//Comprometidos SAC
	private BigDecimal compromCesSac;
	private BigDecimal compromFonrSac;

	// valor para cuota de Buro de Credito
	private BigDecimal cuotaMensualBuro;

	// Credito para novacion
	private Prestamo prestamoVigNovacion;

	private boolean indicaCreditoEmergente;
	
	private Date fechaNacimiento;
	private BigDecimal plazo;
	
	// Se usa para tener el dato en el simulador
	private BigDecimal valorDisponibleCesantia;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public int getIdTipocredito() {
		return idTipocredito;
	}

	public void setIdTipocredito(int idTipocredito) {
		this.idTipocredito = idTipocredito;
	}

	public int getIdVariedadCredito() {
		return idVariedadCredito;
	}

	public void setIdVariedadCredito(int idVariedadCredito) {
		this.idVariedadCredito = idVariedadCredito;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public BigDecimal getMontocredito() {
		return montocredito;
	}

	public void setMontocredito(BigDecimal montocredito) {
		this.montocredito = montocredito;
	}

	public String getTipoSolicitante() {
		return tipoSolicitante;
	}

	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	public ValidarRequisitosFondos getValReqFondos() {
		return valReqFondos;
	}

	public void setValReqFondos(ValidarRequisitosFondos valReqFondos) {
		this.valReqFondos = valReqFondos;
	}

	public TipoPrestamista getTipoPrestamista() {
		return tipoPrestamista;
	}

	public void setTipoPrestamista(TipoPrestamista tipoPrestamista) {
		this.tipoPrestamista = tipoPrestamista;
	}

	public BigDecimal getParametroMontoMaximo() {
		return parametroMontoMaximo;
	}

	public void setParametroMontoMaximo(BigDecimal parametroMontoMaximo) {
		this.parametroMontoMaximo = parametroMontoMaximo;
	}

	/**
	 * @return the cuotaMensualBuro
	 */
	public BigDecimal getCuotaMensualBuro() {
		return cuotaMensualBuro;
	}

	/**
	 * @param cuotaMensualBuro
	 *            the cuotaMensualBuro to set
	 */
	public void setCuotaMensualBuro(BigDecimal cuotaMensualBuro) {
		this.cuotaMensualBuro = cuotaMensualBuro;
	}

	public BigDecimal getComprometidoCesantias() {
		return comprometidoCesantias;
	}

	public void setComprometidoCesantias(BigDecimal comprometidoCesantias) {
		this.comprometidoCesantias = comprometidoCesantias;
	}

	public BigDecimal getComprometidoFondos() {
		return comprometidoFondos;
	}

	public void setComprometidoFondos(BigDecimal comprometidoFondos) {
		this.comprometidoFondos = comprometidoFondos;
	}

	public Prestamo getPrestamoVigNovacion() {
		return prestamoVigNovacion;
	}

	public void setPrestamoVigNovacion(Prestamo prestamoVigNovacion) {
		this.prestamoVigNovacion = prestamoVigNovacion;
	}

	public boolean isIndicaCreditoEmergente() {
		return indicaCreditoEmergente;
	}

	public void setIndicaCreditoEmergente(boolean indicaCreditoEmergente) {
		this.indicaCreditoEmergente = indicaCreditoEmergente;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public BigDecimal getPlazo() {
		return plazo;
	}

	public void setPlazo(BigDecimal plazo) {
		this.plazo = plazo;
	}

	public BigDecimal getValorDisponibleCesantia() {
		return valorDisponibleCesantia;
	}

	public void setValorDisponibleCesantia(BigDecimal valorDisponibleCesantia) {
		this.valorDisponibleCesantia = valorDisponibleCesantia;
	}

	public BigDecimal getCompromCesSac() {
		return compromCesSac;
	}

	public void setCompromCesSac(BigDecimal compromCesSac) {
		this.compromCesSac = compromCesSac;
	}

	public BigDecimal getCompromFonrSac() {
		return compromFonrSac;
	}

	public void setCompromFonrSac(BigDecimal compromFonrSac) {
		this.compromFonrSac = compromFonrSac;
	}

}
