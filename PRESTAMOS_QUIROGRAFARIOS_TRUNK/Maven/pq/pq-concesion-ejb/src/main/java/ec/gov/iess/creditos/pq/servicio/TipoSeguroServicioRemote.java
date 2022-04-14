/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.security.InvalidParameterException;

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.TipoSeguro;
import ec.gov.iess.creditos.modelo.persistencia.CoeficienteSeguro;
import ec.gov.iess.creditos.pq.excepcion.ConsultaCoeficienteException;

/**
 * @author cvillarreal
 * 
 */
@Remote
public interface TipoSeguroServicioRemote {

	/**
	 * 
	 * Consulta l,os coeficientes para los tipo de seguro
	 * 
	 * @param codtipsolser
	 *            tipo de solicitud
	 * @param anio
	 *            anio para la consulta del seguro de desgravamen
	 * @param tipoSeguro
	 *            seguro para el coeficnete
	 * @return {@link CoeficienteSeguro} caso contrario null
	 * @throws ConsultaCoeficienteException
	 *             en caso de erroe en la consulta
	 * @throws InvalidParameterException
	 *             en caso de que los parametros sean null o vacios
	 */
	public CoeficienteSeguro consultarCoeficienteTipoSolicitud(
			Long codtipsolser, Integer anio, TipoSeguro tipoSeguro)
			throws ConsultaCoeficienteException, InvalidParameterException;

}
