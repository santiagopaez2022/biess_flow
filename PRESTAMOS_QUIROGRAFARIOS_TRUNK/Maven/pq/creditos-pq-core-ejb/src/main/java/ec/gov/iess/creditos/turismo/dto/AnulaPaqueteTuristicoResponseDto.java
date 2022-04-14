package ec.gov.iess.creditos.turismo.dto;

import java.io.Serializable;

/**
 * Pojo de respuesta del servicio web anulacion de Informacion Paquete Turistico
 * 
 * @author hugo.mora
 *
 */
public class AnulaPaqueteTuristicoResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoRespuesta;

	private String descripcionRespuesta;

	// Getters and setters
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getDescripcionRespuesta() {
		return descripcionRespuesta;
	}

	public void setDescripcionRespuesta(String descripcionRespuesta) {
		this.descripcionRespuesta = descripcionRespuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaqueteTuristicoResponseDto [codigoRespuesta=" + codigoRespuesta + ", descripcionRespuesta=" + descripcionRespuesta + "]";
	}

}
