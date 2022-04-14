package ec.gov.iess.creditos.focalizados.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Dto para respuesta de consulta al catalogo de productos
 * 
 * @author hugo.mora
 *
 */
public class ProductoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoMEER;

	private String nombre;

	private String descripcion;

	private BigDecimal precio;

	private String marcaNombre;

	private Integer tipoProducto;

	private String estado;

	// La siguiente variable representa campo de ProductoDto de core-biess-sapp-ejb
	private Long numFila;

	private String nombreMostrar;

	private String gobierno;

	// Getters and setters
	public Integer getCodigoMEER() {
		return codigoMEER;
	}

	public void setCodigoMEER(Integer codigoMEER) {
		this.codigoMEER = codigoMEER;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getMarcaNombre() {
		return marcaNombre;
	}

	public void setMarcaNombre(String marcaNombre) {
		this.marcaNombre = marcaNombre;
	}

	public Integer getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(Integer tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getNumFila() {
		return numFila;
	}

	public void setNumFila(Long numFila) {
		this.numFila = numFila;
	}

	public String getNombreMostrar() {
		this.nombreMostrar = this.marcaNombre + " - " + this.nombre + " - " + this.descripcion;
		return this.nombreMostrar;
	}

	public void setNombreMostrar(String nombreMostrar) {
		this.nombreMostrar = nombreMostrar;
	}

	public String getGobierno() {
		return gobierno;
	}

	public void setGobierno(String gobierno) {
		this.gobierno = gobierno;
	}

}
