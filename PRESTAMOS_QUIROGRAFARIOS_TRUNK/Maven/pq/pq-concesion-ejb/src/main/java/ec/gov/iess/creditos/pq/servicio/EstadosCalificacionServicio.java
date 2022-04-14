/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.MasDeUnCostoPorHitoException;
import ec.gov.iess.creditos.modelo.persistencia.HitoControl;
import ec.gov.iess.creditos.modelo.persistencia.RubroCalificacion;
import ec.gov.iess.creditos.pq.excepcion.HitoControlException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteCostoOperativoException;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface EstadosCalificacionServicio {

	/**
	 * Consulta los hitos por tipo de solicitud
	 * 
	 * @param idTipoSolicitud
	 * @return una lista de Hito de control
	 */
	public List<HitoControl> consultarHitosPorTipoSolicitud(Long idTipoSolicitud)
			throws HitoControlException;

	/**
	 * 
	 * @param idHito
	 * @return
	 * @throws NoExisteCostoOperativoException
	 * @throws MasDeUnCostoPorHitoException
	 */
	public BigDecimal costoOperativoHitoActual(Long idHito)
			throws NoExisteCostoOperativoException,
			MasDeUnCostoPorHitoException;
	/**
	 * 
	 * @return
	 */
	public List<RubroCalificacion> consultarRubros();

}
