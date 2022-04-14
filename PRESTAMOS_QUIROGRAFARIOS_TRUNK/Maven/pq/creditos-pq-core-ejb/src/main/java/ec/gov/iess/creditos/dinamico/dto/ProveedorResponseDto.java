/**
 * 
 */
package ec.gov.iess.creditos.dinamico.dto;

import java.io.Serializable;

/**
 * Data respuesta de un proveedor
 * 
 * @author paul.sampedro
 *
 */
public class ProveedorResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5489270970838264044L;
	/**
	 * nombre
	 */
	private String nombre;
	/**
	 * ruc
	 */
	private String ruc;

	/**
	 * Indica si se bloquea el monto obtenido de la consulta
	 */
	private Boolean montoBloqueado;

	/**
	 * categoria
	 */
	private CategoriaProveedorDto categoriaProveedor;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Boolean getMontoBloqueado() {
		return montoBloqueado;
	}

	public void setMontoBloqueado(Boolean montoBloqueado) {
		this.montoBloqueado = montoBloqueado;
	}

	public CategoriaProveedorDto getCategoriaProveedor() {
		return categoriaProveedor;
	}

	public void setCategoriaProveedor(CategoriaProveedorDto categoriaProveedor) {
		this.categoriaProveedor = categoriaProveedor;
	}

}
