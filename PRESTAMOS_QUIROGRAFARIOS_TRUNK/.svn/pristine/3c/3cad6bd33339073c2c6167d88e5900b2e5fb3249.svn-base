/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.HitoDetalle;
import ec.gov.iess.dao.GenericDao;

/**
 * HitoDetalleDao.java DAO de acceso a tabla CRE_CC_HITOSCONTROLDETALLE_TBL
 * 
 * @author geguiguren
 */
@Local
public interface HitoDetalleDao extends GenericDao<HitoDetalle, Long> {

	/**
	 * Obtiene el listado de Hitos segun un tipo de Solicitud (Producto)
	 * determinado
	 * 
	 * @param idTipoSolicitud
	 *            Long que representa el tipo de producto a obtener
	 * @return List<HitoDetalle> representa el listado de Sub-Itos o tareas
	 *         correspondientes a ese tipo de solicitud
	 * */
	public List<HitoDetalle> detalleHitosPorTipoSolicitud(Long idTipoSolicitud);
}
