/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DocumentacionRequeridaDetalleDao;
import ec.gov.iess.creditos.modelo.persistencia.DocumentacionRequeridaDetalle;
import ec.gov.iess.dao.ejb.GenericEjbDao;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * @author cvillarreal 03/10/2011
 * 
 */
@Stateless
public class DocumentacionRequeridaDetalleDaoImpl extends GenericEjbDao<DocumentacionRequeridaDetalle, Long> implements
		DocumentacionRequeridaDetalleDao {
	
	private static final LoggerBiess log = LoggerBiess.getLogger(DocumentacionRequeridaDetalleDaoImpl.class);

	
	
	public DocumentacionRequeridaDetalleDaoImpl() {
		super(DocumentacionRequeridaDetalle.class);
	}

	@SuppressWarnings("unchecked")
	public List<DocumentacionRequeridaDetalle> getDocumentacion(String idProvincia, Long idTipoSolciitud,
			String vendedorTipoPersona, String tipoBien) {
		log.debug(" Consulta los documentos de TS : " + idTipoSolciitud + " provicnia : " + idProvincia);
		Query q = em
				.createNamedQuery("DocumentacionRequeridaDetalle.findByProvinciaAndTipoSolicitudAndVendedorTipoPersonaAndTipoBien");
		q.setParameter("idTipoSolicitud", idTipoSolciitud);
		q.setParameter("idProvincia", idProvincia);
		q.setParameter("vendedorTipoPersona", vendedorTipoPersona);
		q.setParameter("tipoBien", tipoBien);
		return q.getResultList();
	}
	
	
	
	
	
	/* (non-Javadoc)
	* @see ec.gov.iess.creditos.dao.DocumentacionRequeridaDetalleDao#getDocumentacion(java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	*/ 
	@SuppressWarnings("unchecked")
	public List<DocumentacionRequeridaDetalle> getDocumentacion(String idProvincia, Long idTipoSolciitud,
			String vendedorTipoPersona, String tipoBien, String docTipo_tipoAfiliado) {
		log.debug(" Consulta los documentos de TS : " + idTipoSolciitud + " provicnia : " + idProvincia+"TipoAfiliado: "+docTipo_tipoAfiliado);

		Query q = em
				.createNamedQuery("DocumentacionRequeridaDetalle.ObtenerTipoSolVendTipoPerTipoBienTipoAfivProvin");
		q.setParameter("idTipoSolicitud", idTipoSolciitud);
		q.setParameter("idProvincia", idProvincia);
		q.setParameter("vendedorTipoPersona", vendedorTipoPersona);
		q.setParameter("tipoBien", tipoBien);
		q.setParameter("tipoAfiliado", docTipo_tipoAfiliado);

		return q.getResultList();
	}

	
	
	
	/* (non-Javadoc)
	* @see ec.gov.iess.creditos.dao.DocumentacionRequeridaDetalleDao#obtenerDocumentacionPorTipoSolicitud(java.lang.Long)
	*/ 
	@SuppressWarnings("unchecked")
	public List<DocumentacionRequeridaDetalle> obtenerDocumentacionPorTipoSolicitud(Long idTipoSolicitud) {
		log.debug(" Consulta los documentos de TS : " + idTipoSolicitud);
		Query q = em.createNamedQuery("DocumentacionRequeridaDetalle.findByTipoSolicitud");
		q.setParameter("idTipoSolicitud", idTipoSolicitud);
		return q.getResultList();
	}
}
