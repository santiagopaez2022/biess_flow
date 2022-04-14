/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.MasDeUnCostoPorHitoException;
import ec.gov.iess.creditos.modelo.persistencia.HitoControl;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface HitoControlDao extends GenericDao<HitoControl, Long> {

	public List<HitoControl> findByTipoSolicitud(Long idTipoSolicitud);

	public HitoControl findByIdHitoCostoActual(Long idHito, Date fecha)
			throws MasDeUnCostoPorHitoException;

	public List<HitoControl> findByTipoProducto(Long idTipoSolicitud);
}
