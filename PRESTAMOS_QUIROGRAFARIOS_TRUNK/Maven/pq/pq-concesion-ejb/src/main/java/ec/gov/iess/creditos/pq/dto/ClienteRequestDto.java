/*
 * © Banco del Instituto Ecuatoriano de Seguridad Social 2020
 */
package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Datos comunes de cliente para envio al gaf.
 *
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 */
public class ClienteRequestDto implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1256443387589675L;
	
	/** Tipo de documento del afiliado. */
	private String tipoDocumento;
	
	/** Identificacion del afiliado. */
	private String numeroDocumento;
	
	/** Nombre del afiliado. */
	private String nombre;
	
	/** Fecha de nacimiento del afiliado. */
	private String fechaNacimiento;
	
	/** 1:afiliado/jubilado 2: Zafrero. */
	private String tipoCliente;
	
	/** Parroquia al que pertenece el empleador. */
	private String parroquia;
	
	/** Por confirmar. */
	private BigDecimal rentaNetaSueldo;
	
	/** Por confirmar. */
	private BigDecimal capacidadEndeudamiento;
	
	/** Por confirmar. */
	private String excepcionaGarantia;

	/**
	 * Constructor.
	 */
	public ClienteRequestDto() {
		//Constructor vacio
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento the new fecha nacimiento
	 */
	public void setFechaNacimiento(final String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the tipo cliente.
	 *
	 * @return the tipo cliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Sets the tipo cliente.
	 *
	 * @param tipoCliente the new tipo cliente
	 */
	public void setTipoCliente(final String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * Gets the parroquia.
	 *
	 * @return the parroquia
	 */
	public String getParroquia() {
		return parroquia;
	}

	/**
	 * Sets the parroquia.
	 *
	 * @param parroquia the new parroquia
	 */
	public void setParroquia(final String parroquia) {
		this.parroquia = parroquia;
	}

	/**
	 * Gets the renta neta sueldo.
	 *
	 * @return the renta neta sueldo
	 */
	public BigDecimal getRentaNetaSueldo() {
		return rentaNetaSueldo;
	}

	/**
	 * Sets the renta neta sueldo.
	 *
	 * @param rentaNetaSueldo the new renta neta sueldo
	 */
	public void setRentaNetaSueldo(final BigDecimal rentaNetaSueldo) {
		this.rentaNetaSueldo = rentaNetaSueldo;
	}

	/**
	 * Gets the capacidad endeudamiento.
	 *
	 * @return the capacidad endeudamiento
	 */
	public BigDecimal getCapacidadEndeudamiento() {
		return capacidadEndeudamiento;
	}

	/**
	 * Sets the capacidad endeudamiento.
	 *
	 * @param capacidadEndeudamiento the new capacidad endeudamiento
	 */
	public void setCapacidadEndeudamiento(final BigDecimal capacidadEndeudamiento) {
		this.capacidadEndeudamiento = capacidadEndeudamiento;
	}

	/**
	 * Gets the excepciona garantia.
	 *
	 * @return the excepciona garantia
	 */
	public String getExcepcionaGarantia() {
		return excepcionaGarantia;
	}

	/**
	 * Sets the excepciona garantia.
	 *
	 * @param excepcionaGarantia the new excepciona garantia
	 */
	public void setExcepcionaGarantia(final String excepcionaGarantia) {
		this.excepcionaGarantia = excepcionaGarantia;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento the new tipo documento
	 */
	public void setTipoDocumento(final String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento the new numero documento
	 */
	public void setNumeroDocumento(final String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
