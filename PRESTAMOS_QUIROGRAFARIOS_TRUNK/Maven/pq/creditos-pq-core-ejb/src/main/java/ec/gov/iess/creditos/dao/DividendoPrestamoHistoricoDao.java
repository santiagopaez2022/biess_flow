/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
 
package ec.gov.iess.creditos.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoHistoricoPK;
import ec.gov.iess.dao.GenericDao;

 /**
 * 
 * <b> Interface para el historico del dividendo del prestamo. </b>
 * 
 * @author Ricardo Tituaña 
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 03/10/2011 $]
 *          </p>
 */
 
@Local
public interface DividendoPrestamoHistoricoDao extends
		GenericDao<DividendoPrestamoHistorico, DividendoPrestamoHistoricoPK> {
	/**
	 * Consulta que trae el numero de elementos cuando los distintivos usados
	 * son los campos numpreafi, ordpreafi, codpretip, codprecla codestdiv,
	 * fecfin IS NULL
	 * 
	 * @param cedula
	 *            identificcaion de la persona
	 * @return 0 en el caso de que no exista coincidencia, 1 en caso de que
	 *         exista una sola coincidencia, + 2 cuando existen mas de dos
	 *         coincidencias
	 * 
	 * @author ricardo tituaña
	 */
	public Long contarPorSeleccionEspecifica(DividendoPrestamo dividendoPrestamo);

	/**
	 * Consulta que trae la tupla del historico de dividendos con los
	 * distintivos usados que son los campos numpreafi, ordpreafi, codpretip,
	 * codprecla codestdiv, fecfin IS NULL
	 * 
	 * @param cedula
	 *            identificcaion de la persona
	 * @return 0 en el caso de que no exista coincidencia, 1 en caso de que
	 *         exista una sola coincidencia, + 2 cuando existen mas de dos
	 *         coincidencias
	 * 
	 * @author ricardo tituaña
	 */
	public DividendoPrestamoHistorico paraBuscarPorPKDividendoPrestam(
			DividendoPrestamo dividendoPrestamo);
	/**
	 * Método que retorna un listado de historicos que tengan la fecfin en null
	 * Teóricamente debería retornar únicamente uno
	 * @param dividendoPrestamo
	 * @return
	 */
	public List<DividendoPrestamoHistorico> obtenerUltimosHistoricosDividendo(DividendoPrestamo dividendoPrestamo);
	
	public void cambiarFinfecYobstraPorDividendoPrestamoHistorico(Date fecfin, String obstra, DividendoPrestamoHistorico dividendoPrestamoHistorico);
	
	 /**
	 * 
	 * <b> Lista del préstamos historico </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param dividendoPrestamo
	 *            : dividendo del prestamo
	 * 
	 * @return List<DividendoPrestamoHistorico>: lista de historicos del dividendo
	 */
	public List<DividendoPrestamoHistorico> paraBuscarTodosDividendoHist(DividendoPrestamo dividendoPrestamo) ;

}
