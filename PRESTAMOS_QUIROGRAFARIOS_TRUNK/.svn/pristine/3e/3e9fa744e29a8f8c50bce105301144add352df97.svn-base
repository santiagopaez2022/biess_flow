package ec.gov.iess.planillaspq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.planillaspq.dao.PlanillaDetalleDao;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;
import ec.gov.iess.planillaspq.servicio.PlanillaPrestamoDetalleServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaPrestamoDetalleServicioRemote;


@Stateless
public class PlanillaPrestamoDetalleServicioImpl implements PlanillaPrestamoDetalleServicioLocal,
PlanillaPrestamoDetalleServicioRemote {
	
	@EJB
	private PlanillaDetalleDao plapredetDao; 
	
//	@EJB
//	private BusquedasServicioLocal busqueda; 
	
	public List<PlanillaPrestamosDetalle> buscarPorPlanilla(PlanillaPrestamosDetallePK id){
		
		//return busqueda.asignarNombreAfiliado(plapredetDao.buscarPorPlanilla(id));
		return plapredetDao.buscarPorPlanilla(id);
		
	}

	public PlanillaPrestamosDetalle buscarPorId(PlanillaPrestamosDetallePK id){
		
		return plapredetDao.buscarPorId(id);
	}
	public PlanillaPrestamosDetalle buscarPorPk(String ruc,String codsuc,long secpla,long aniper,long mesper,long numpreafi,String codtippla,String tipper){
		return plapredetDao.buscarPorPk(ruc,codsuc,secpla,aniper,mesper,numpreafi,codtippla,tipper);
		
	}
	
	public void actualizarPlanillaDetalle(PlanillaPrestamosDetalle plapredet){
		plapredetDao.update(plapredet);
		
	}	
	public void eliminarPlanillaDetalle(PlanillaPrestamosDetalle plapredet){
		plapredetDao.delete(plapredet);
		
	}
		
}
