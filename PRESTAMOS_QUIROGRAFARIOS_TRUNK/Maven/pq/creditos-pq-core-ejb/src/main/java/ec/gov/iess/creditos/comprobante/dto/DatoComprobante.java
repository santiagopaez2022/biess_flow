/**
 * 
 */
package ec.gov.iess.creditos.comprobante.dto;

import java.util.Date;

/**
 * Datos de entrada para generacion de un comprobante
 * 
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class DatoComprobante {
	private Integer tipoTransaccion;
	private String cedula;
	private Long nut;
	private Long idGaf;
	private Double valorCobro;
	private Double intMora;
	private Date fechaVencimiento;
	private String modulo;

	public Integer getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(Integer tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Long getNut() {
		return nut;
	}

	public void setNut(Long nut) {
		this.nut = nut;
	}

	public Long getIdGaf() {
		return idGaf;
	}

	public void setIdGaf(Long idGaf) {
		this.idGaf = idGaf;
	}

	public Double getValorCobro() {
		return valorCobro;
	}

	public void setValorCobro(Double valorCobro) {
		this.valorCobro = valorCobro;
	}

	public Double getIntMora() {
		return intMora;
	}

	public void setIntMora(Double intMora) {
		this.intMora = intMora;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return "DatoComprobante [tipoTransaccion=" + tipoTransaccion + ", cedula=" + cedula + ", nut=" + nut + ", idGaf=" + idGaf + ", valorCobro="
				+ valorCobro + ", intMora=" + intMora + ", fechaVencimiento=" + fechaVencimiento + ", modulo=" + modulo + "]";
	}

}
