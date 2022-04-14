package ec.fin.biess.creditos.pq.enumeracion;

/**
 * Codigos de respuesta para consumo de servicios de prestamos turisticos
 * 
 * @author hugo.mora
 *
 */
public enum CodigosRespuestaPaqueteTuristicoEnum {
	
	//Consulta de reservas
    EXISTE_RESERVA("0", "Existe Reserva."),
    NO_EXISTE_RESERVA("1", "No existe Reserva."),
    CODIGO_INVALIDO("2", "Codigo de reserva invalido."),
    ERROR_CONSULTAR("4", "Error en el proceso de consulta de reserva."),
    //Anulacion reserva
    EXITO_PROCESO("0", "Anulacion exitosa."),
    ERROR_ANULAR("1", "Error al anular."),
    //General
    DATO_INGRESO_NULO("3", "Error los datos ingresados son nulos o vac\u00EDos.");
	
	private String codigo;
	
	private String descripcion;
	
	private CodigosRespuestaPaqueteTuristicoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	// Getters
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
