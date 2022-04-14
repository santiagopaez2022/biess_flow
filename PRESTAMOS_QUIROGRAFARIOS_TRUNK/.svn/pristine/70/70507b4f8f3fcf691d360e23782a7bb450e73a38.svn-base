/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.PrestamoResumenHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoResumenHistoricoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b> Contiene métodos que permite validar requisitos para la pre-calificación
 * del crédito </b>
 *
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@Local
public interface PrestamoResumenHistoricoDao extends GenericDao<PrestamoResumenHistorico, PrestamoResumenHistoricoPk> {

	/**
	 * @author acantos
	 * recupera el historico de un pq, historico de la tabla cre_crereshist_tbl
	 * @param pk
	 * @return
	 */
	public List<PrestamoResumenHistorico> consultarporpq(PrestamoPk pk);
}
