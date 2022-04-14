package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

public class OpcionCredito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4941874485530279772L;

	protected BigDecimal valorTotalCredito;

	protected Integer meses;

	protected BigDecimal cuotaMensual;
	
	//Esto se aniadio para los nuevos requerimeintos de las Tablas de Amortizacion DS
	protected String tipoTablaSeleccionado;	
	protected BigDecimal tasaInteres;	
	protected BigDecimal capacidadEndeudamiento;	
	protected BigDecimal totalGarantias;
	protected int plazoMaximoCredito;
	// Bandera para indicar si es credito emergente
	private boolean esEmergente;
	
	private TipoPrestamista tipoPrestamista;
	private int edadAsegurado;
	private TiposProductosPq tipoProductoPq;

	//MIGRACION SAC
	private BigDecimal cuotaMaxSac;
	private BigDecimal montoMaxSac;
	/**
	 * Es el codigo del producto cuando es un credito dinamico se debe llenar este campo
	 */
	private Long codProdEspecial;

	public OpcionCredito() {

	}

	public OpcionCredito(BigDecimal valorTotalCreditoNew, Integer mesesNew,
			BigDecimal cuotaMensualNew) {

		this.valorTotalCredito = valorTotalCreditoNew;
		this.meses = mesesNew;
		this.cuotaMensual = cuotaMensualNew;
	}

	public BigDecimal getCuotaMensual() {
		return cuotaMensual;
	}

	public void setCuotaMensual(BigDecimal cuotaMensual) {
		this.cuotaMensual = cuotaMensual;
		this.cuotaMensual = UtilAjusteCalculo.ajusteCalculo(this.cuotaMensual);

	}

	public Integer getMeses() {
		return meses;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
		this.valorTotalCredito = UtilAjusteCalculo
				.ajusteCalculo(this.valorTotalCredito);
	}

	public String getTipoTablaSeleccionado() {
		return tipoTablaSeleccionado;
	}

	public void setTipoTablaSeleccionado(String tipoTablaSeleccionado) {
		this.tipoTablaSeleccionado = tipoTablaSeleccionado;
	}

	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public BigDecimal getCapacidadEndeudamiento() {
		return capacidadEndeudamiento;
	}

	public void setCapacidadEndeudamiento(BigDecimal capacidadEndeudamiento) {
		this.capacidadEndeudamiento = capacidadEndeudamiento;
	}

	public BigDecimal getTotalGarantias() {
		return totalGarantias;
	}

	public void setTotalGarantias(BigDecimal totalGarantias) {
		this.totalGarantias = totalGarantias;
	}

	public int getPlazoMaximoCredito() {
		return plazoMaximoCredito;
	}

	public void setPlazoMaximoCredito(int plazoMaximoCredito) {
		this.plazoMaximoCredito = plazoMaximoCredito;
	}
	
	public boolean isEsEmergente() {
		return esEmergente;
	}

	public void setEsEmergente(boolean esEmergente) {
		this.esEmergente = esEmergente;
	}

	public TipoPrestamista getTipoPrestamista() {
		return tipoPrestamista;
	}

	public void setTipoPrestamista(TipoPrestamista tipoPrestamista) {
		this.tipoPrestamista = tipoPrestamista;
	}

	public int getEdadAsegurado() {
		return edadAsegurado;
	}

	public void setEdadAsegurado(int edadAsegurado) {
		this.edadAsegurado = edadAsegurado;
	}

	public TiposProductosPq getTipoProductoPq() {
		return tipoProductoPq;
	}

	public void setTipoProductoPq(TiposProductosPq tipoProductoPq) {
		this.tipoProductoPq = tipoProductoPq;
	}

	public BigDecimal getCuotaMaxSac() {
		return cuotaMaxSac;
	}

	public void setCuotaMaxSac(BigDecimal cuotaMaxSac) {
		this.cuotaMaxSac = cuotaMaxSac;
	}

	public BigDecimal getMontoMaxSac() {
		return montoMaxSac;
	}

	public void setMontoMaxSac(BigDecimal montoMaxSac) {
		this.montoMaxSac = montoMaxSac;
	}

	public Long getCodProdEspecial() {
		return codProdEspecial;
	}

	public void setCodProdEspecial(Long codProdEspecial) {
		this.codProdEspecial = codProdEspecial;
	}


}