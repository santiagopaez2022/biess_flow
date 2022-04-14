/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.security.InvalidParameterException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CoeficienteSeguroDao;
import ec.gov.iess.creditos.enumeracion.TipoSeguro;
import ec.gov.iess.creditos.modelo.persistencia.CoeficienteSeguro;
import ec.gov.iess.creditos.pq.excepcion.ConsultaCoeficienteException;
import ec.gov.iess.creditos.pq.servicio.TipoSeguroServicio;
import ec.gov.iess.creditos.pq.servicio.TipoSeguroServicioRemote;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class TipoSeguroServicioImpl implements TipoSeguroServicio,
		TipoSeguroServicioRemote {

	private static LoggerBiess log = LoggerBiess.getLogger(TipoSeguroServicioImpl.class);

	@EJB
	private CoeficienteSeguroDao coeficienteSeguroDao;

	/**
	 * 
	 */
	public TipoSeguroServicioImpl() {
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CoeficienteSeguro consultarCoeficienteTipoSolicitud(
			Long codtipsolser, Integer anio, TipoSeguro tipoSeguro)
			throws ConsultaCoeficienteException, InvalidParameterException {

		log.debug("SER: Consulta del coeficiente");
		log.debug("SER: codtipsolser : " + codtipsolser);
		log.debug("SER: anio : " + anio);
		log.debug("SER: tipoSeguro : " + tipoSeguro);

		if (codtipsolser == null) {
			throw new InvalidParameterException(
					"Parametro incorrecto codtipsolser no es null");
		}

		if (anio == null) {
			throw new InvalidParameterException(
					"Parametro incorrecto anio no es null");
		}

		if (codtipsolser == null) {
			throw new InvalidParameterException(
					"Parametro incorrecto tipoSeguro no es null");
		}

		try {

			return coeficienteSeguroDao.consultarTipoSolicitudTipoSeguro(
					codtipsolser, anio, tipoSeguro, new Date());
		} catch (Exception e) {
			log.error(e);
			throw new ConsultaCoeficienteException(e);
		}

	}

}
