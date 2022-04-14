package ec.gov.iess.pq.concesion.web.bean;

import java.math.BigDecimal;

import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;

/**
 * Clase que se usa para presentar el detalle del prestamo
 * 
 * @author rtituana
 */

public class DetallePrestamoBean {
	private String cedula;
	private String nombre;
	private String numeroPrestamo;
	private String fechaRegistroPrestamo;
	private String nombreInstitucionFinanciera;
	private String numeroCuentaBancaria;
	private String tipoCuentaBancaria;
	private BigDecimal valorTransferido;
	private String estadoActualPrestamo;
	//INC-2286 Ferrocarriles
	private String ciudad;

	// Reimpresion de FAT.
	private BeneficiarioCredito beneficiarioCredito;

	public DetallePrestamoBean() {

	}

	public DetallePrestamoBean(String cedula, String nombre, String numeroPrestamo, String fechaRegistroPrestamo,
			String nombreInstitucionFinanciera, String numeroCuentaBancaria, String tipoCuentaBancaria,
			BigDecimal valorTransferido, String estadoActualPrestamo) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.numeroPrestamo = numeroPrestamo;
		this.fechaRegistroPrestamo = fechaRegistroPrestamo;
		this.nombreInstitucionFinanciera = nombreInstitucionFinanciera;
		this.numeroCuentaBancaria = numeroCuentaBancaria;
		this.tipoCuentaBancaria = tipoCuentaBancaria;
		this.valorTransferido = valorTransferido;
		this.estadoActualPrestamo = estadoActualPrestamo;

		// Reimpresion de FAT.
		this.beneficiarioCredito = null;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public String getFechaRegistroPrestamo() {
		return fechaRegistroPrestamo;
	}

	public void setFechaRegistroPrestamo(String fechaRegistroPrestamo) {
		this.fechaRegistroPrestamo = fechaRegistroPrestamo;
	}

	public String getNombreInstitucionFinanciera() {
		return nombreInstitucionFinanciera;
	}

	public void setNombreInstitucionFinanciera(String nombreInstitucionFinanciera) {
		this.nombreInstitucionFinanciera = nombreInstitucionFinanciera;
	}

	public String getNumeroCuentaBancaria() {
		return numeroCuentaBancaria;
	}

	public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
		this.numeroCuentaBancaria = numeroCuentaBancaria;
	}

	public String getTipoCuentaBancaria() {
		return tipoCuentaBancaria;
	}

	public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
		this.tipoCuentaBancaria = tipoCuentaBancaria;
	}

	public BigDecimal getValorTransferido() {
		return valorTransferido;
	}

	public void setValorTransferido(BigDecimal valorTransferido) {
		this.valorTransferido = valorTransferido;
	}

	public String getEstadoActualPrestamo() {
		return estadoActualPrestamo;
	}

	public void setEstadoActualPrestamo(String estadoActualPrestamo) {
		this.estadoActualPrestamo = estadoActualPrestamo;
	}

	/**
	 * @return the beneficiarioCredito
	 */
	public BeneficiarioCredito getBeneficiarioCredito() {
		return beneficiarioCredito;
	}

	/**
	 * @param beneficiarioCredito
	 *            the beneficiarioCredito to set
	 */
	public void setBeneficiarioCredito(BeneficiarioCredito beneficiarioCredito) {
		this.beneficiarioCredito = beneficiarioCredito;
	}

	/**
	 * @return cuidad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	

}
