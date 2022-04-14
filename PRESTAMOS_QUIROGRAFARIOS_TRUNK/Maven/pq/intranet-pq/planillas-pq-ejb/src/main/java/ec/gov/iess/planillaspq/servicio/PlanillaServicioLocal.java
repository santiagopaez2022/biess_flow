/**
 * 
 */
package ec.gov.iess.planillaspq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.planillaspq.exceptions.PlanillaException;
import ec.gov.iess.planillaspq.exceptions.PlanillaHostException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;

/**
 * @author palvarez
 *
 */
@Local
public interface PlanillaServicioLocal {
	public List<Planillas> buscarPlanillaPorRuc(PlanillasPK id) 
	throws PlanillaException;
	public void actualizarPlanilla(Planillas planilla);
	public List<PlanillaDetalleHost> buscarPorPlanilla(PlanillaDetalleHostPK id) throws PlanillaHostException;
	public void eliminarPlanillaDetalle(PlanillaDetalleHost plapredet);
}
