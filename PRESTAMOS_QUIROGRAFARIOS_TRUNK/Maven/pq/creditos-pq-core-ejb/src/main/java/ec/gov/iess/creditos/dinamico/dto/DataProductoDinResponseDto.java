/**
 * 
 */
package ec.gov.iess.creditos.dinamico.dto;

import java.util.List;

/**
 * Clase pojo que contiene los datos de un producto
 * 
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class DataProductoDinResponseDto extends RespuestaError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7646034342240237037L;

	/**
	 * titulo
	 */
	private String titulo;
	/**
	 * descripcion
	 */
	private String descripcion;
	/**
	 * nombreBusqueda
	 */
	private String nombreBusqueda;
	/**
	 * bloqueoFinSemana
	 */
	private Boolean bloqueoFinSemana;
	/**
	 * codigoEspecial
	 */
	private Long codigoEspecial;
	/**
	 * numeroIntentos
	 */
	private int numeroIntentos;

	/**
	 * proveedores
	 */
	private List<DataCmbProveedor> proveedores;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreBusqueda() {
		return nombreBusqueda;
	}

	public void setNombreBusqueda(final String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	public Boolean getBloqueoFinSemana() {
		return bloqueoFinSemana;
	}

	public void setBloqueoFinSemana(final Boolean bloqueoFinSemana) {
		this.bloqueoFinSemana = bloqueoFinSemana;
	}

	public Long getCodigoEspecial() {
		return codigoEspecial;
	}

	public void setCodigoEspecial(final Long codigoEspecial) {
		this.codigoEspecial = codigoEspecial;
	}

	public List<DataCmbProveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(final List<DataCmbProveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public int getNumeroIntentos() {
		return numeroIntentos;
	}

	public void setNumeroIntentos(final int numeroIntentos) {
		this.numeroIntentos = numeroIntentos;
	}

}
