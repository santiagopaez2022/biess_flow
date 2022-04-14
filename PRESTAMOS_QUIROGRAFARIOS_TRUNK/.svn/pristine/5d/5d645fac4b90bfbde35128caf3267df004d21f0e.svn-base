/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DetalleBienesSolicitud;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Interface para métodos de bienes de la solicitud. </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Sanchez $, $Date: 07/06/2011 $]
 *          </p>
 */
@Local
public interface DetalleBienesSolicitudDao extends GenericDao<DetalleBienesSolicitud, Long> {

	/**
	 * 
	 * <b> Método para obtener los bienes de una solicitud </b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 07/06/2011]
	 * </p>
	 * 
	 * @param numsolser
	 *            número de solicitud
	 * @param codtipsolser
	 *            código de tipo solicitud
	 * @return lista de bienes
	 */
	public List<DetalleBienesSolicitud> obtenerBienesPorSolicitud(Long numsolser, Long codtipsolser);

}
