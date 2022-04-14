/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.Date;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.TipoSeguro;
import ec.gov.iess.creditos.modelo.persistencia.CoeficienteSeguro;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface CoeficienteSeguroDao extends GenericDao<CoeficienteSeguro, Long> {

	/**
	 * Consulta el seguro dependiendo de parametros en una fecha actual
	 * 
	 * @param codtipsolser
	 *            identificador de la solicitud
	 * @param anio
	 *            para establecer el coeficiente
	 * @param tipoSeguro
	 *            tipo de seguro de la consulta
	 * @param fecha
	 *            de vigencia del coeficiente
	 * @return un obj de modelo {@link CoeficienteSeguro} caso contrario null
	 */
	public CoeficienteSeguro consultarTipoSolicitudTipoSeguro(
			Long codtipsolser, Integer anio, TipoSeguro tipoSeguro, Date fecha);

}
