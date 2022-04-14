package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.pq.excepcion.DeterminarRolException;
import ec.gov.iess.hl.exception.NoTieneRelacionDeDependenciaException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.pensiones.exception.PensionException;
import ec.gov.iess.servicio.pensiones.modelo.TipoPrestacionSeguro;

/**
 * Servicio para obtener informacion de roles
 * 
 * @author hugo.mora
 * 
 */
@Local
public interface DefinirRolServicioLocal {

	/**
	 * Determina el tipo de rol dada la cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws DeterminarRolException
	 * @throws PensionException
	 * @throws ParametroNoEncontradoException
	 */
	TipoPrestamista determinarTipoRol(String cedula) throws DeterminarRolException, PensionException, ParametroNoEncontradoException;
	
	/**
	 * Determina las prestaciones validas para la precalificacion
	 * 
	 * @return
	 */
	public List<TipoPrestacionSeguro> getPrestacionesValidas();
	
	/**
	 * Determina si un afiliado es voluntario o cesante
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @return
	 * @throws ServicioPrestadoException
	 * @throws NoTieneRelacionDeDependenciaException
	 */
	TipoPrestamista determinaVoluntarioCesante(String cedula, TipoPrestamista tipoPrestamista)
			throws ServicioPrestadoException, NoTieneRelacionDeDependenciaException;
	
	/**
	 * Determina si un afiliado es cesante, voluntario o doble rol
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @return TipoPrestamista
	 * @throws ServicioPrestadoException
	 * @throws NoTieneRelacionDeDependenciaException
	 */
	TipoPrestamista determinarTipoPrestamistaCesanteVoluntarioDobleRol(String cedula, TipoPrestamista tipoPrestamista)
			throws ServicioPrestadoException, NoTieneRelacionDeDependenciaException;
	
}
