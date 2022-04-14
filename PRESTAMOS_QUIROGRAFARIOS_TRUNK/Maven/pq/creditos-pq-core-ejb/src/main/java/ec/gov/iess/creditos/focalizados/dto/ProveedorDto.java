package ec.gov.iess.creditos.focalizados.dto;

import java.io.Serializable;

import ec.gov.iess.creditos.modelo.persistencia.Proveedor;

/**
 * Dto para respuesta de consulta al catalogo de proveedores
 * 
 * @author hugo.mora
 *
 */
public class ProveedorDto implements Serializable, Comparable<Proveedor> {

	private static final long serialVersionUID = 1L;

	private Integer codigoMEER;

	private String ruc;

	private String razonSocial;

	private String nombreComercial;

	private String direccion;

	private String codigoINEC;

	private String estado;

	// Getters and setters
	public Integer getCodigoMEER() {
		return codigoMEER;
	}

	public void setCodigoMEER(Integer codigoMEER) {
		this.codigoMEER = codigoMEER;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoINEC() {
		return codigoINEC;
	}

	public void setCodigoINEC(String codigoINEC) {
		this.codigoINEC = codigoINEC;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Proveedor proveedor) {
		int respuesta = -1;
		if (this.ruc.equals(proveedor.getPrRuc())) {
			respuesta = 0;
		}
		return respuesta;
	}

}
