/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.OperacionSustitucionHipoteca;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Interface para métodos de Operaciones Hipotecas </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 17/06/2011 $]
 *          </p>
 */
@Local
public interface OperacionSustitucionHipotecaDao extends GenericDao<OperacionSustitucionHipoteca, Long> {

	/**
	 * 
	 * <b> Método para obtener operaciones hipotecadas por solicitud </b>
	 * <p>
	 * [Author: jsanchez, Date: 17/06/2011]
	 * </p>
	 * 
	 * @param numsolser
	 *            número de solicitud
	 * @param codtipsolser
	 *            tipo de solicitud
	 * @return lista de operaciones hipotecadas
	 */
	public List<OperacionSustitucionHipoteca> obtenerPorSolicitud(Long numsolser, Long codtipsolser);

	/**
	 * 
	 * <b> Método para obtener viviendas que ya pertenecen a un trámite. </b>
	 * <p>
	 * [Author: jsanchez, Date: 08/07/2011]
	 * </p>
	 * 
	 * @param estadosNoTramite
	 *            lista de estados que no significan en trámite.
	 * @param cedula
	 *            cedula del vendedor
	 * @param codigoIfi
	 *            codigo de la IFI
	 * @return lista de operaciones del vendedor
	 */
	public List<OperacionSustitucionHipoteca> obtenerViviendaEnTramite(List<String> estadosNoTramite, String cedula,
			Long codigoIfi);
}
