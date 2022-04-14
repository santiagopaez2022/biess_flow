package ec.gov.iess.creditos.otp.dto;

import java.io.Serializable;

/**
 * DTO de respuesta a generacion y validacion del OTP
 * 
 * @author hugo.mora
 *
 */
public class OTPResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoGenerado;

	private String codigoRespuesta;

	private String mensajeRespuesta;

	// Getters and setters
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public String getCodigoGenerado() {
		return codigoGenerado;
	}

	public void setCodigoGenerado(String codigoGenerado) {
		this.codigoGenerado = codigoGenerado;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

}
