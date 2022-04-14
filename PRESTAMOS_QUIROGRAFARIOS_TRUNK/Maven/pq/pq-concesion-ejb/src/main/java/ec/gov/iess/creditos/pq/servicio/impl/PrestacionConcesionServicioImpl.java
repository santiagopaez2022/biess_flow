/**
 * 
 */

package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.PrestacionConcesionDao;
import ec.gov.iess.creditos.modelo.persistencia.PrestacionConcesion;
import ec.gov.iess.creditos.pq.servicio.PrestacionConcesionServicio;
import ec.gov.iess.creditos.pq.servicio.PrestacionConcesionServicioRemote;

/**
 * 
 * <b> Prestación. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: cbastidas $, $Date: 16/06/2011 $]
 *          </p>
 */
@Stateless
public class PrestacionConcesionServicioImpl implements
		PrestacionConcesionServicio, PrestacionConcesionServicioRemote {

	@EJB
	private PrestacionConcesionDao prestacionConcesionDao;

	public void registrar(PrestacionConcesion prestamoConcesion) {
		prestacionConcesionDao.insert(prestamoConcesion);
	}
}