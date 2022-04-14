package ec.fin.biess.pq.simulacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;

/**
 * DTO con parametros para realizar la simulacion del credito
 * 
 * @author hugo.mora
 * @date 2017/05/16
 *
 */
public class ParametrosCalculoCreditoResponseDto implements Serializable {

	private static final long serialVersionUID = 290956852405588144L;

	private int edadActualAnios;

	private int edadActualMeses;

	private int plazoMaximo;

	private BigDecimal sueldoPromedio;

	private BigDecimal capacidadPago;

	private List<DetalleEgresosDto> listaDetalleEgresos;

	private BigDecimal capacidadEndeudamiento;

	private List<DetalleIngresosDto> listaDetalleIngresos;

	private InformacionPrestacionPensionado informacionPrestacionPensionado;

	private BigDecimal totalGarantia;

	private BigDecimal porcentajeCapacidadPago;

	private Date fechaNacimiento;

	private boolean tieneCapacidadEndeudamiento;

	// Getters and setters
	public int getEdadActualAnios() {
		return edadActualAnios;
	}

	public void setEdadActualAnios(int edadActualAnios) {
		this.edadActualAnios = edadActualAnios;
	}

	public int getEdadActualMeses() {
		return edadActualMeses;
	}

	public void setEdadActualMeses(int edadActualMeses) {
		this.edadActualMeses = edadActualMeses;
	}

	public int getPlazoMaximo() {
		return plazoMaximo;
	}

	public void setPlazoMaximo(int plazoMaximo) {
		this.plazoMaximo = plazoMaximo;
	}

	public BigDecimal getSueldoPromedio() {
		return sueldoPromedio;
	}

	public void setSueldoPromedio(BigDecimal sueldoPromedio) {
		this.sueldoPromedio = sueldoPromedio;
	}

	public BigDecimal getCapacidadPago() {
		return capacidadPago;
	}

	public void setCapacidadPago(BigDecimal capacidadPago) {
		this.capacidadPago = capacidadPago;
	}

	public List<DetalleEgresosDto> getListaDetalleEgresos() {
		return listaDetalleEgresos;
	}

	public void setListaDetalleEgresos(List<DetalleEgresosDto> listaDetalleEgresos) {
		this.listaDetalleEgresos = listaDetalleEgresos;
	}

	public BigDecimal getCapacidadEndeudamiento() {
		return capacidadEndeudamiento;
	}

	public void setCapacidadEndeudamiento(BigDecimal capacidadEndeudamiento) {
		this.capacidadEndeudamiento = capacidadEndeudamiento;
	}

	public List<DetalleIngresosDto> getListaDetalleIngresos() {
		return listaDetalleIngresos;
	}

	public void setListaDetalleIngresos(List<DetalleIngresosDto> listaDetalleIngresos) {
		this.listaDetalleIngresos = listaDetalleIngresos;
	}

	public InformacionPrestacionPensionado getInformacionPrestacionPensionado() {
		return informacionPrestacionPensionado;
	}

	public void setInformacionPrestacionPensionado(InformacionPrestacionPensionado informacionPrestacionPensionado) {
		this.informacionPrestacionPensionado = informacionPrestacionPensionado;
	}

	public BigDecimal getTotalGarantia() {
		return totalGarantia;
	}

	public void setTotalGarantia(BigDecimal totalGarantia) {
		this.totalGarantia = totalGarantia;
	}

	public BigDecimal getPorcentajeCapacidadPago() {
		return porcentajeCapacidadPago;
	}

	public void setPorcentajeCapacidadPago(BigDecimal porcentajeCapacidadPago) {
		this.porcentajeCapacidadPago = porcentajeCapacidadPago;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public boolean isTieneCapacidadEndeudamiento() {
		return tieneCapacidadEndeudamiento;
	}

	public void setTieneCapacidadEndeudamiento(boolean tieneCapacidadEndeudamiento) {
		this.tieneCapacidadEndeudamiento = tieneCapacidadEndeudamiento;
	}

}
