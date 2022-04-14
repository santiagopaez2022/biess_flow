/**
 * 
 */
package ec.fin.biess.creditos.pq.dao.ejb;

import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.dao.TipoPrestacionDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.TipoPrestacion;
import ec.fin.biess.creditos.pq.modelo.persistencia.TipoPrestacionPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion de la interfaz para consultar Tipos de Prestaciones de Pensiones
 * 
 * @author christian.gomez
 *
 */
@Stateless
public class TipoPrestacionDaoEjb extends GenericEjbDao<TipoPrestacion, TipoPrestacionPk> implements TipoPrestacionDao {

	
	public TipoPrestacionDaoEjb(){
		super(TipoPrestacion.class);
	}

}
