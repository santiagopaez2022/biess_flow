package ec.gov.iess.creditos.otp.dto;

import java.io.Serializable;

/**
 * DTO de request para obtener el codigo de activaion
 * 
 * @author hugo.mora
 * @date 2016/11/21
 *
 */
public class ObtenerCodigoRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idTransaccion;

	private String referencia;

	// Getters and setters
	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
