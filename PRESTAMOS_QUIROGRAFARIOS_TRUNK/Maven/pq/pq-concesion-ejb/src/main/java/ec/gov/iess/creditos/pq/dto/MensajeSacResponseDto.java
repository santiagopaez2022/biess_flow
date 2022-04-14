/**
 * 
 */
package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;

/**
 * The Class MensajeSacResponseDto.
 *
 * @author roberto.guizado
 */
public class MensajeSacResponseDto implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1878008996666727L;

	/** codigo respuesta. */
	private String codigoRespuesta;

	/** mensaje respuesta. */
	private String mensajeRespuesta;

	/**
	 * Gets the codigo respuesta.
	 *
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Sets the codigo respuesta.
	 *
	 * @param codigoRespuesta the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(final String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * Gets the mensaje respuesta.
	 *
	 * @return the mensajeRespuesta
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	/**
	 * Sets the mensaje respuesta.
	 *
	 * @param mensajeRespuesta the mensajeRespuesta to set
	 */
	public void setMensajeRespuesta(final String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

}
