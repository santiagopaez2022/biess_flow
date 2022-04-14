package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.otp.dto.NotificacionOTP;
import ec.gov.iess.creditos.pq.excepcion.OTPException;

/**
 * Servicio local para generacion y validacion del codigo OTP
 * 
 * @author hugo.mora
 * @date 2016/11/14
 *
 */
@Local
public interface OTPServicioLocal {

	/**
	 * Genera el codigo OTP en base al id de transaccion
	 * 
	 * @param idTransaccion
	 * @param referencia
	 * @param notificacionOTP
	 * @param idNegocio
	 * @throws OTPException
	 */
	void generaOTP(String idTransaccion, String referencia, NotificacionOTP notificacionOTP, String idNegocio) throws OTPException;

	/**
	 * @param idTransaccion
	 * @param codigoIngresado
	 * @return
	 * @throws OTPException
	 */
	boolean validaOTP(String idTransaccion, String codigoIngresado) throws OTPException;

	/**
	 * Obtiene el codigo de activacion para prestamos pq focalizados
	 * 
	 * @param idTransaccion
	 * @param referencia
	 * @return
	 * @throws OTPException
	 */
	String obtenerCodigoActivacion(String idTransaccion, String referencia) throws OTPException;

}
