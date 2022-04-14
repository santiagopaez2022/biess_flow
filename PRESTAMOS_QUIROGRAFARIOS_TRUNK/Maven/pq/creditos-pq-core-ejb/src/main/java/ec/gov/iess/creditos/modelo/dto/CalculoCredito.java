/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <b> Clase que tiene los calculos del credito y la cuota de hipotecario . </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.6 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:11 $]
 *          </p>
 */
public class CalculoCredito implements Serializable {

	private static final long serialVersionUID = 7640228446645223120L;

	private CondicionCalculo condicionCalculo;

	private Garantia garantia;

	private TablaReferenciaCredito tablaReferenciaCredito;

	/* CB: INC-7900 Se aumenta la cuota de préstamo hipotecario 11/12/2010 */
	private BigDecimal cuotaHipotecario;
	private BigDecimal cuotaSolicitudHipotecario;
	private BigDecimal porcentajeHipotecario;
	private BigDecimal disponibleHipotecario;
	private BigDecimal maximoComprometerHipotecario;
	private BigDecimal liquidacionNovacion;
	private boolean tienePH;

	/* CB: INC-9893 Datos de la Orden de Compra */
	private DatosOrdenCompra ordenCompra;
	private BigDecimal montoMaximoProducto;
	private int plazoMaximoProducto;
	private BigDecimal montoMinimoProducto;
	private int plazoMinimoProducto;

	/* CB Validacion del la capacidad de endeudamiento multiples PQs */
	private boolean tieneCapacidadEndeudamiento;

	public CalculoCredito() {

	}

	public CalculoCredito(CondicionCalculo condicionCalculoNew,
			Garantia garantiaNew,
			TablaReferenciaCredito tablaReferenciaCreditoNew) {

		this.condicionCalculo = condicionCalculoNew;
		this.garantia = garantiaNew;
		this.tablaReferenciaCredito = tablaReferenciaCreditoNew;
	}

	public CondicionCalculo getCondicionCalculo() {
		return condicionCalculo;
	}

	public void setCondicionCalculo(CondicionCalculo condicionCalculo) {
		this.condicionCalculo = condicionCalculo;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public TablaReferenciaCredito getTablaReferenciaCredito() {
		return tablaReferenciaCredito;
	}

	public void setTablaReferenciaCredito(
			TablaReferenciaCredito tablaReferenciaCredito) {
		this.tablaReferenciaCredito = tablaReferenciaCredito;
	}

	public BigDecimal getCuotaHipotecario() {
		return cuotaHipotecario;
	}

	public void setCuotaHipotecario(BigDecimal cuotaHipotecario) {
		this.cuotaHipotecario = cuotaHipotecario;
	}

	public BigDecimal getCuotaSolicitudHipotecario() {
		return cuotaSolicitudHipotecario;
	}

	public void setCuotaSolicitudHipotecario(
			BigDecimal cuotaSolicitudHipotecario) {
		this.cuotaSolicitudHipotecario = cuotaSolicitudHipotecario;
	}

	public BigDecimal getPorcentajeHipotecario() {
		return porcentajeHipotecario;
	}

	public void setPorcentajeHipotecario(BigDecimal porcentajeHipotecario) {
		this.porcentajeHipotecario = porcentajeHipotecario;
	}

	public BigDecimal getDisponibleHipotecario() {
		return disponibleHipotecario;
	}

	public void setDisponibleHipotecario(BigDecimal disponibleHipotecario) {
		this.disponibleHipotecario = disponibleHipotecario;
	}

	public BigDecimal getMaximoComprometerHipotecario() {
		return maximoComprometerHipotecario;
	}

	public void setMaximoComprometerHipotecario(
			BigDecimal maximoComprometerHipotecario) {
		this.maximoComprometerHipotecario = maximoComprometerHipotecario;
	}

	public boolean isTienePH() {
		return tienePH;
	}

	public void setTienePH(boolean tienePH) {
		this.tienePH = tienePH;
	}

	public BigDecimal getLiquidacionNovacion() {
		return liquidacionNovacion;
	}

	public void setLiquidacionNovacion(BigDecimal liquidacionNovacion) {
		this.liquidacionNovacion = liquidacionNovacion;
	}

	public DatosOrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(DatosOrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public BigDecimal getMontoMaximoProducto() {
		return montoMaximoProducto;
	}

	public void setMontoMaximoProducto(BigDecimal montoMaximoProducto) {
		this.montoMaximoProducto = montoMaximoProducto;
	}

	public int getPlazoMaximoProducto() {
		return plazoMaximoProducto;
	}

	public void setPlazoMaximoProducto(int plazoMaximoProducto) {
		this.plazoMaximoProducto = plazoMaximoProducto;
	}

	public BigDecimal getMontoMinimoProducto() {
		return montoMinimoProducto;
	}

	public void setMontoMinimoProducto(BigDecimal montoMinimoProducto) {
		this.montoMinimoProducto = montoMinimoProducto;
	}

	public int getPlazoMinimomoProducto() {
		return plazoMinimoProducto;
	}

	public void setPlazoMinimomoProducto(int plazoMinimomoProducto) {
		this.plazoMinimoProducto = plazoMinimomoProducto;
	}

	public boolean isTieneCapacidadEndeudamiento() {
		return tieneCapacidadEndeudamiento;
	}

	public void setTieneCapacidadEndeudamiento(
			boolean tieneCapacidadEndeudamiento) {
		this.tieneCapacidadEndeudamiento = tieneCapacidadEndeudamiento;
	}

}