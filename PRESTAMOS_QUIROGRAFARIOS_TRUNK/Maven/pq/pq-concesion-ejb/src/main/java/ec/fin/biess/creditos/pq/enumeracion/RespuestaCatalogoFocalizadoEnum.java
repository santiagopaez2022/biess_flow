package ec.fin.biess.creditos.pq.enumeracion;

/**
 * Enumeracion para el manejo de codigos de respuesta para catalogos de prestamos focalizados
 * 
 * @author hugo.mora
 *
 */
public enum RespuestaCatalogoFocalizadoEnum {

	EXITO("0", "Exito"),
	NO_EXISTEN_REGISTROS("1", "No existen registros"),
	ERROR_CONSULTA("2", "Error al realizar la consulta");
	
	private String codigo;
	private String descripcion;
	
	private RespuestaCatalogoFocalizadoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	// Getters and setters
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
}