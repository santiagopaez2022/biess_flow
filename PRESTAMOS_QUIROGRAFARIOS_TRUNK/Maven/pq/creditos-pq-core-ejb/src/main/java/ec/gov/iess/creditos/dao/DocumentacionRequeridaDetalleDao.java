package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DocumentacionRequeridaDetalle;
import ec.gov.iess.dao.GenericDao;

/**
 * @author Daniel Cardenas 03/10/2011
 */
@Local
public interface DocumentacionRequeridaDetalleDao extends GenericDao<DocumentacionRequeridaDetalle, Long> {

	/**
	 * Consulta la documentacion de una solciitud por provicia y tipo de
	 * solicitud, ademas el parametro docTipo_tipoAfiliado
	 * 
	 * @param idProvincia
	 * @param idTipoSolciitud
	 * @param docTipo_tipoAfiliado tipo de afiliado (AJ , VO)
	 * @return
	 */
	public List<DocumentacionRequeridaDetalle> getDocumentacion(String idProvincia, Long idTipoSolciitud,
			String vendedorTipoPersona, String tipoBien, String docTipo_tipoAfiliado);

	
	/**
	 * Consulta la documentacion de una solciitud por provicia y tipo de
	 * solicitud 
	 * 
	 * @param idProvincia
	 * @param idTipoSolciitud
	 * @return
	 */
	public List<DocumentacionRequeridaDetalle> getDocumentacion(String idProvincia, Long idTipoSolciitud,
			String vendedorTipoPersona, String tipoBien);

	/**
	 * Obtiene un Listado de documentacion requerida por un tipo de Solicitud
	 * definido el parametro.
	 * 
	 * @author geguiguren
	 * @param idTipoSolciitud
	 *            Indica el tipo de Solicitud de los documentos que queremos
	 *            recuperar
	 * 
	 * @return List<DocumentacionRequerida> el listado de documentacion
	 *         requerido
	 * 
	 */
	public List<DocumentacionRequeridaDetalle> obtenerDocumentacionPorTipoSolicitud(Long idTipoSolicitud);

}
