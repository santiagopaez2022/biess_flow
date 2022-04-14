/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.excepcion.MasDeUnCostoPorHitoException;
import ec.gov.iess.creditos.modelo.persistencia.HitoControl;
import ec.gov.iess.creditos.modelo.persistencia.RubroCalificacion;
import ec.gov.iess.creditos.pq.excepcion.HitoControlException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteCostoOperativoException;

/**
 * @author cvillarreal
 * 
 */
@Remote
public interface EstadosCalificacionServicioRemote {

	/**
	 * Consulta los hitos por tipo de solicitud
	 * 
	 * @param idTipoSolicitud
	 * @return una lista de Hito de control
	 */
	public List<HitoControl> consultarHitosPorTipoSolicitud(Long idTipoSolicitud)
			throws HitoControlException;

	public BigDecimal costoOperativoHitoActual(Long idHito)
			throws NoExisteCostoOperativoException,
			MasDeUnCostoPorHitoException;
	
	public List<RubroCalificacion> consultarRubros();

}
