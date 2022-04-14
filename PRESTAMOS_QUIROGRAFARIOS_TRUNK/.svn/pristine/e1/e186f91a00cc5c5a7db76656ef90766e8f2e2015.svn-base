package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.pq.excepcion.ResumenConsolidadoException;

/**
 * Servicio local para verificacion de mora patronal del empleador
 * 
 * @author hugo.mora
 * @date 29 de mar. de 2016
 *
 */
@Local
public interface MoraPatronalServicioLocal {

	/**
	 * Verifica si el empleador esta en mora dado el ruc y la sucursal
	 * 
	 * @param rucEmpleador
	 * @param codigoSucursal
	 * @throws ResumenConsolidadoException
	 */
	boolean tieneMoraPatronal(String rucEmpleador, String codigoSucursal) throws ResumenConsolidadoException;

}
