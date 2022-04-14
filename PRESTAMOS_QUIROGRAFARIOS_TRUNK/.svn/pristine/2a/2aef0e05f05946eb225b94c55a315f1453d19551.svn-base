/**
 * 
 */
package ec.gov.iess.planillaspq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;

/**
 * @author palvarez
 *
 */
@Local
public interface PlanillaPrestamoDetalleServicioLocal {
	public List<PlanillaPrestamosDetalle> buscarPorPlanilla(PlanillaPrestamosDetallePK id);
	public PlanillaPrestamosDetalle buscarPorId(PlanillaPrestamosDetallePK id);
	public PlanillaPrestamosDetalle buscarPorPk(String ruc,String codsuc,long secpla,long aniper,long mesper,long numpreafi,String codtippla,String tipper);
	public void actualizarPlanillaDetalle(PlanillaPrestamosDetalle plapredet);
	public void eliminarPlanillaDetalle(PlanillaPrestamosDetalle plapredet);
}
