package ec.gov.iess.creditos.otp.dto;

import java.io.Serializable;

/**
 * Dto para peticion de validacion de OTP
 * 
 * @author hugo.mora
 * @date 2016/11/17
 *
 */
public class OTPValidarRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idTransaccion;

	private String codigoIngresado;

	// Getters and setters
	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getCodigoIngresado() {
		return codigoIngresado;
	}

	public void setCodigoIngresado(String codigoIngresado) {
		this.codigoIngresado = codigoIngresado;
	}

}
