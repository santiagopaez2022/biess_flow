package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.SolicitanteCredito;
import ec.gov.iess.dao.GenericDao;

@Local
public interface SolicitanteCreditoDao extends GenericDao<SolicitanteCredito, Long> {

	/**
	 * Consulta un solicitante en base a la cedula tipo de solicitud, y una
	 * lista de estados que no son vigentes del tipo de solicitud
	 * 
	 * @param cedula
	 * @param idTipoSolcitud
	 *            tipo de solicitud
	 * @param estadosNoVigentesSolicitud
	 *            lista de estados no vigentes de la solicitud
	 * @return un objeto persistente {@link SolicitanteCredito} caso contrario
	 *         null
	 * @author cvillarreal
	 */
	public SolicitanteCredito getSolicitanteByCedulaTipoSolicitudestadoSolicitud(String cedula, Long idTipoSolcitud,
			List<String> estadosNoVigentesSolicitud);

	public Long tieneRol(String rol, String cedula);

	/**
	 * Método para obtener el solicitande de un crédito por tipo de crédito y de
	 * acuerdo a los estados enviados
	 * 
	 * @author jsanchez
	 * @param cedula
	 * @param estadosSolicitud
	 * @param tiposSolicitud
	 * @return
	 */
	public SolicitanteCredito obtenerPorCedulaTipoEstadoSolicitud(String cedula, List<String> estadosSolicitud,
			List<Long> tiposSolicitud);

	/**
	 * 
	 * <b> Método para obtener lista de solicitudes vigentes </b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 03/06/2011]
	 * </p>
	 * 
	 * @param cedula
	 *            : cedula
	 * @param estadosSolicitud
	 *            : estado de la solicitud
	 * @param tiposSolicitud
	 *            : tipo de la solicitud
	 * @return lista de solicitantes de la solicitud
	 */
	public List<SolicitanteCredito> obtenerSolicitudesVigentes(String cedula, List<String> estadosSolicitud,
			List<Long> tiposSolicitud);
}
