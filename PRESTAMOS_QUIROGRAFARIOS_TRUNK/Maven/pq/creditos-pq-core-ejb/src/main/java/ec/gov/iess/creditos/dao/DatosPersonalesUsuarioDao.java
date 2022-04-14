/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DatosPersonalesUsuario;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Interface para métodos de datos adicionales de solicitud </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 03/10/2011 $]
 *          </p>
 */
@Local
public interface DatosPersonalesUsuarioDao extends GenericDao<DatosPersonalesUsuario, Long> {

	/**
	 * 
	 * <b> Método para obtener la lista de detalles adicionales por detalle
	 * solicitud </b>
	 * <p>
	 * [Author: jsanchez, Date: 03/08/2011]
	 * </p>
	 * 
	 * @param codigoDetalle
	 *            del detalle de solicitud
	 * @return lista de adicionales
	 */
	public List<DatosPersonalesUsuario> obtenerPorDetalleSolicitud(Long codigoDetalle);
	
	/**
	 * <b>
	 * Devuelve la lista de Garantías Activas por Solicitantes, Es decir todo solicitante puede <br /> 
	 * ser FIADOR o GARANTE de un préstamo.
	 * </b>
	 * <p>[Author: Ron@ld Barrer@ rbar.ec@gmail.com, Date: 08/08/2011]</p>
	 *
	 * @param cedula
	 * @param notEstados : estados en los que no puede estar una solicitud para q se considere ACTIVO el préstamo.
	 * @return
	 */ 
	public List<DatosPersonalesUsuario> listaGarantiasActivasdeSolicitante(String cedula, List<String> notEstados)
	 throws Exception;
}
