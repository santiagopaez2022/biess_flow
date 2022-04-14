/**
 * 
 */
package ec.gov.iess.planillaspq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;

/**
 * @author palvarez
 *
 */
@Remote
public interface PlanillaPrestamoDetalleServicioRemote {
	public List<PlanillaPrestamosDetalle> buscarPorPlanilla(PlanillaPrestamosDetallePK id);
	public PlanillaPrestamosDetalle buscarPorId(PlanillaPrestamosDetallePK id);
	public PlanillaPrestamosDetalle buscarPorPk(String ruc,String codsuc,long secpla,long aniper,long mesper,long numpreafi,String codtippla,String tipper);
	public void actualizarPlanillaDetalle(PlanillaPrestamosDetalle plapredet);
	public void eliminarPlanillaDetalle(PlanillaPrestamosDetalle plapredet);
}
