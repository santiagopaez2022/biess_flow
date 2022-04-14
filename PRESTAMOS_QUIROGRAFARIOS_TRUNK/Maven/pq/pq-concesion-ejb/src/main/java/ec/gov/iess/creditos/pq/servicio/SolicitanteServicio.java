
package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.ObtenerRolesExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;

@Local
public interface SolicitanteServicio {

	List<TipoPrestamista> obtenerRoles(String cedula) throws ObtenerRolesExcepcion;

	Solicitante obtenerSolicitante(String cedula, TipoPrestamista tipoPrestamista) throws SolicitanteExcepcion;
	
	Solicitante obtenerSolicitanteMasCargas(String cedula, TipoPrestamista tipoPrestamista) throws SolicitanteExcepcion;
	
	/**
	 * @param cedula
	 * @param tipoPrestamista
	 * @param esProductoEmergente
	 * @return
	 * @throws SolicitanteExcepcion
	 */
	Solicitante obtenerSolicitanteMasCargas(String cedula, TipoPrestamista tipoPrestamista, boolean esProductoEmergente) throws SolicitanteExcepcion;
	
	/**
	 * Obtiene solicitantes dado tambien si se escogio producto emergente
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @param esProductoEmergente
	 * @return
	 * @throws SolicitanteExcepcion
	 */
	Solicitante obtenerSolicitante(String cedula, TipoPrestamista tipoPrestamista, boolean esProductoEmergente) throws SolicitanteExcepcion;
	
	/**
	 * Valida si una cedula es de refugiado/extrangero.
	 * 
	 * @param cedulaRefugiado
	 *            - cedula del refugiado.
	 * 
	 * @return boolean.
	 */
	boolean validarCedulaRefugiado(String cedulaRefugiado);
	
	/**
	 * Determina el empleador de afiliados o jubilados de zonas emergentes
	 * 
	 * @param cedula
	 * @param tipo
	 * @param solicitante
	 * @throws SucursalException
	 * @throws ServicioPrestadoException
	 * @throws NoServicioPrestadoSucursalException
	 */
	void determinarEmpleador(String cedula, TipoPrestamista tipo, Solicitante solicitante, boolean esEmergente)
			throws SucursalException, ServicioPrestadoException, NoServicioPrestadoSucursalException;

}