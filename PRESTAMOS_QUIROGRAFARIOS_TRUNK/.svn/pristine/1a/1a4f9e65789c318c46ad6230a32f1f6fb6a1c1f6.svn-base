package ec.fin.biess.pq.simulacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

/**
 * Objeto con parametros necesarios para obtener tabla de amortizacion y seguro de saldos de PQ
 * 
 * @author hugo.mora
 * @date 2017/05/24
 *
 */
public class DatosSimulacionCuotaMontoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoPrestamista tipoPrestamista;

	private int plazoCredito;

	private int edadAsegurado;

	private boolean esEmergente;

	private String tipoTablaAmortizacion;

	private String cedula;

	private BigDecimal cuotaMensualMaxima;

	private BigDecimal montoCredito;

	private BigDecimal montoMaximoCredito;

	private TiposProductosPq tiposProductosPq;

	private Date fechaNacimiento;
	
	private boolean esNovacion;
	
	private Prestamo prestamoNovacion;

	// Requerido para simulacion de tabla de amortizacion en SAC
	private String nombreAsegurado;
	private String tipoPeticionTablaSac;
	private String tipoIdentificacionSac;
	private String productoCarga;

	// Getters and setters
	public TipoPrestamista getTipoPrestamista() {
		return tipoPrestamista;
	}

	public void setTipoPrestamista(TipoPrestamista tipoPrestamista) {
		this.tipoPrestamista = tipoPrestamista;
	}

	public int getPlazoCredito() {
		return plazoCredito;
	}

	public void setPlazoCredito(int plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public int getEdadAsegurado() {
		return edadAsegurado;
	}

	public void setEdadAsegurado(int edadAsegurado) {
		this.edadAsegurado = edadAsegurado;
	}

	public boolean isEsEmergente() {
		return esEmergente;
	}

	public void setEsEmergente(boolean esEmergente) {
		this.esEmergente = esEmergente;
	}

	public String getTipoTablaAmortizacion() {
		return tipoTablaAmortizacion;
	}

	public void setTipoTablaAmortizacion(String tipoTablaAmortizacion) {
		this.tipoTablaAmortizacion = tipoTablaAmortizacion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public BigDecimal getCuotaMensualMaxima() {
		return cuotaMensualMaxima;
	}

	public void setCuotaMensualMaxima(BigDecimal cuotaMensualMaxima) {
		this.cuotaMensualMaxima = cuotaMensualMaxima;
	}

	public BigDecimal getMontoCredito() {
		return montoCredito;
	}

	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}

	public BigDecimal getMontoMaximoCredito() {
		return montoMaximoCredito;
	}

	public void setMontoMaximoCredito(BigDecimal montoMaximoCredito) {
		this.montoMaximoCredito = montoMaximoCredito;
	}

	public TiposProductosPq getTiposProductosPq() {
		return tiposProductosPq;
	}

	public void setTiposProductosPq(TiposProductosPq tiposProductosPq) {
		this.tiposProductosPq = tiposProductosPq;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isEsNovacion() {
		return esNovacion;
	}

	public void setEsNovacion(boolean esNovacion) {
		this.esNovacion = esNovacion;
	}

	public Prestamo getPrestamoNovacion() {
		return prestamoNovacion;
	}

	public void setPrestamoNovacion(Prestamo prestamoNovacion) {
		this.prestamoNovacion = prestamoNovacion;
	}

	public String getNombreAsegurado() {
		return nombreAsegurado;
	}

	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}

	public String getTipoPeticionTablaSac() {
		return tipoPeticionTablaSac;
	}

	public void setTipoPeticionTablaSac(String tipoPeticionTablaSac) {
		this.tipoPeticionTablaSac = tipoPeticionTablaSac;
	}

	public String getTipoIdentificacionSac() {
		return tipoIdentificacionSac;
	}

	public void setTipoIdentificacionSac(String tipoIdentificacionSac) {
		this.tipoIdentificacionSac = tipoIdentificacionSac;
	}

	public String getProductoCarga() {
		return productoCarga;
	}

	public void setProductoCarga(String productoCarga) {
		this.productoCarga = productoCarga;
	}

}
