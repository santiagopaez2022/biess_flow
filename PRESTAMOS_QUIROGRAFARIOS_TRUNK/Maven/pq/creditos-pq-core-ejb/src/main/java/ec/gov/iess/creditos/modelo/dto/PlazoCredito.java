/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * <b> Permite crear la tabla de referencia del crédito </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:12 $]
 *          </p>
 */
public abstract class PlazoCredito implements Serializable {

	private static final long serialVersionUID = 4728767307135241759L;

	protected OpcionCredito opcionCredito;

	protected BigDecimal porcentajeComprometido;

	protected BigDecimal valorMaximoComprometer;

	protected BigDecimal valorMaximoCredito;

	protected BigDecimal valorLiquidoPagar;

	protected BigDecimal valorTotalSeguroSaldo;

	protected BigDecimal valorTotalPeriodoGraciaInterzafra;

	protected BigDecimal valorTotalPeriodoGracia;

	protected BigDecimal valorLiquidacionNovacion;

	// Permite validar si puede generar PQ con los nuevos productos
	protected boolean cumpleMonto;
	protected BigDecimal montoMaximoOrden;
	protected int plazoMaximoOrden;

	public PlazoCredito() {

	}

	public PlazoCredito(OpcionCredito opcionCreditoNew,
			BigDecimal porcentajeComprometidoNew,
			BigDecimal valorMaximoComprometerNew,
			BigDecimal valorMaximoCreditoNew) {

		this.opcionCredito = opcionCreditoNew;
		this.porcentajeComprometido = porcentajeComprometidoNew;
		this.valorMaximoComprometer = valorMaximoComprometerNew;
		this.valorMaximoCredito = valorMaximoCreditoNew;

	}

	/**
	 * @param valorTotalCreditoNew
	 * @param mesesNew
	 * @param cuotaMensualNew
	 */
	public PlazoCredito(BigDecimal valorTotalCreditoNew, int mesesNew,
			BigDecimal cuotaMensualNew) {

	}

	/**
	 * @return the opcionCredito
	 */
	public OpcionCredito getOpcionCredito() {
		return opcionCredito;
	}

	/**
	 * @param opcionCredito
	 *            the opcionCredito to set
	 */
	public void setOpcionCredito(OpcionCredito opcionCredito) {
		this.opcionCredito = opcionCredito;
	}

	/**
	 * @return the porcentajeComprometido
	 */
	public BigDecimal getPorcentajeComprometido() {
		return porcentajeComprometido;
	}

	/**
	 * @param porcentajeComprometido
	 *            the porcentajeComprometido to set
	 */
	public void setPorcentajeComprometido(BigDecimal porcentajeComprometido) {
		this.porcentajeComprometido = porcentajeComprometido;
		this.porcentajeComprometido = UtilAjusteCalculo
				.ajusteCalculo(this.porcentajeComprometido);
	}

	/**
	 * @return the valorMaximoComprometer
	 */
	public BigDecimal getValorMaximoComprometer() {
		return valorMaximoComprometer;
	}

	/**
	 * @param valorMaximoComprometer
	 *            the valorMaximoComprometer to set
	 */
	public void setValorMaximoComprometer(BigDecimal valorMaximoComprometer) {
		this.valorMaximoComprometer = valorMaximoComprometer;
		this.valorMaximoComprometer = UtilAjusteCalculo
				.ajusteCalculo(this.valorMaximoComprometer);
	}

	/**
	 * @return the valorMaximoCredito
	 */
	public BigDecimal getValorMaximoCredito() {
		return valorMaximoCredito;
	}

	/**
	 * @param valorMaximoCredito
	 *            the valorMaximoCredito to set
	 */
	public void setValorMaximoCredito(BigDecimal valorMaximoCredito) {
		this.valorMaximoCredito = valorMaximoCredito;
		this.valorMaximoCredito = UtilAjusteCalculo
				.ajusteCalculo(this.valorMaximoCredito);
	}
/**
 * 
 * <b>
 * Setea el valor del seguro de saldos mensual.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @param valor
 */
	public abstract void setValorSeguroSaldoMensual(BigDecimal valor);
/**
 * 
 * <b>
 * Obtiene el valor de seguro de saldos.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @return  BigDecimal Valor de seguro de saldos
 */
	public abstract BigDecimal getValorSeguroSaldoMensual();

	/**
	 * @return the valorLiquidoPagar
	 */
	public BigDecimal getValorLiquidoPagar() {
		return valorLiquidoPagar;
	}

	/**
	 * @return the valorTotalSeguroSaldo
	 */
	public BigDecimal getValorTotalSeguroSaldo() {
		return valorTotalSeguroSaldo;
	}

	/**
	 * @return the valorTotalPeriodoGracia
	 */
	public BigDecimal getValorTotalPeriodoGracia() {
		return valorTotalPeriodoGracia;
	}

	/**
	 * @param valorLiquidoPagar
	 *            the valorLiquidoPagar to set
	 */
	public void setValorLiquidoPagar(BigDecimal valorLiquidoPagar) {
		this.valorLiquidoPagar = valorLiquidoPagar;
		this.valorLiquidoPagar = UtilAjusteCalculo
				.ajusteCalculo(this.valorLiquidoPagar);
	}

	/**
	 * @param valorTotalSeguroSaldo
	 *            the valorTotalSeguroSaldo to set
	 */
	public void setValorTotalSeguroSaldo(BigDecimal valorTotalSeguroSaldo) {
		this.valorTotalSeguroSaldo = valorTotalSeguroSaldo;
		this.valorTotalSeguroSaldo = UtilAjusteCalculo
				.ajusteCalculo(this.valorTotalSeguroSaldo);
	}

	/**
	 * @param valorTotalPeriodoGracia
	 *            the valorTotalPeriodoGracia to set
	 */
	public void setValorTotalPeriodoGracia(BigDecimal valorTotalPeriodoGracia) {
		this.valorTotalPeriodoGracia = valorTotalPeriodoGracia;
		this.valorTotalPeriodoGracia = UtilAjusteCalculo
				.ajusteCalculo(this.valorTotalPeriodoGracia);
	}
/**
 * 
 * <b>
 * Obtiene el valor de liquidación por novación.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @return BigDecimal Valor de liquidación por novación
 */
	public BigDecimal getValorLiquidacionNovacion() {
		return valorLiquidacionNovacion;
	}
/**
 * 
 * <b>
 * Setea el valor de liquidación por novación.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @param valorLiquidacionNovacion : Valor de liquidación por novación
 */
	public void setValorLiquidacionNovacion(BigDecimal valorLiquidacionNovacion) {
		this.valorLiquidacionNovacion = valorLiquidacionNovacion;
		this.valorTotalPeriodoGracia = UtilAjusteCalculo
				.ajusteCalculo(this.valorTotalPeriodoGracia);
	}
/**
 * 
 * <b>
 * Retorma el valor de periodo de gracia zafreros.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @return BigDecimal Valor total del periodo de gracia
 */
	public BigDecimal getValorTotalPeriodoGraciaInterzafra() {
		return valorTotalPeriodoGraciaInterzafra;
	}
/**
 * 
 * <b>
 * Periodo de gracia de interzafra.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @param valorTotalPeriodoGraciaInterzafra
 */
	public void setValorTotalPeriodoGraciaInterzafra(
			BigDecimal valorTotalPeriodoGraciaInterzafra) {
		this.valorTotalPeriodoGraciaInterzafra = valorTotalPeriodoGraciaInterzafra;
		this.valorTotalPeriodoGraciaInterzafra = UtilAjusteCalculo
				.ajusteCalculo(this.valorTotalPeriodoGraciaInterzafra);
	}
/**
 * 
 * <b>
 * Cumple monto del Pq.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @return boolean : Cumple el monto del PQ
 */
	public boolean isCumpleMonto() {
		return cumpleMonto;
	}
/**
 * 
 * <b>
 * Cumple monto del PQ.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @param cumpleMonto : Cumple el monto del PQ
 */
	public void setCumpleMonto(boolean cumpleMonto) {
		this.cumpleMonto = cumpleMonto;
	}
/**
 * 
 * <b>
 * Monto máximo de la orden de compra.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @return BigDecimal Monto máximo de la orden de compra
 */
	public BigDecimal getMontoMaximoOrden() {
		return montoMaximoOrden;
	}
/**
 * 
 * <b>
 * Monto máximo de la Orden.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @param montoMaximoOrden : Monto máximo de la orden
 */
	public void setMontoMaximoOrden(BigDecimal montoMaximoOrden) {
		this.montoMaximoOrden = montoMaximoOrden;
	}
/**
 * 
 * <b>
 * Plao máximo de la orden.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @return int Plazo máximo de la orden
 */
	public int getPlazoMaximoOrden() {
		return plazoMaximoOrden;
	}
/**
 * 
 * <b>
 * Plazo máximo de la orden.
 * </b>
 * <p>[Author: cbastidas, Date: 02/05/2011]</p>
 *
 * @param plazoMaximoOrden : Plazo máximo de la orden
 */
	public void setPlazoMaximoOrden(int plazoMaximoOrden) {
		this.plazoMaximoOrden = plazoMaximoOrden;
	}

}
