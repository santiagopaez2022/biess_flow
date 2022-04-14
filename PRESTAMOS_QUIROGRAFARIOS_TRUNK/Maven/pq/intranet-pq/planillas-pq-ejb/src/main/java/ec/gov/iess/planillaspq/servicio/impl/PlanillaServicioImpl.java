package ec.gov.iess.planillaspq.servicio.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.gov.iess.planillaspq.dao.PlanillaDao;
import ec.gov.iess.planillaspq.dao.PlanillaDetalleHostDao;
import ec.gov.iess.planillaspq.exceptions.PlanillaException;
import ec.gov.iess.planillaspq.exceptions.PlanillaHostException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;
import ec.gov.iess.planillaspq.servicio.BusquedasServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaServicioRemote;

@Stateless
public class PlanillaServicioImpl implements PlanillaServicioLocal,
		PlanillaServicioRemote{
	
	@EJB
	private PlanillaDao planillaDao; 

	@EJB
	private PlanillaDetalleHostDao detplahostDao; 

	@EJB
	private BusquedasServicioLocal busqueda; 
	
	public List<Planillas> buscarPlanillaPorRuc(PlanillasPK id) 
	throws PlanillaException{
		try{
			return busqueda.asignarNombreEmpleador(planillaDao.buscarPorRuc(id));
		}catch(Exception e){
			throw new PlanillaException("No existe esa planilla para ese periodo, o empleador o sucursal o ya se encuentra cancelada",e);
		}	
	}
	public void actualizarPlanilla(Planillas planilla){
		planillaDao.update(planilla);
		
	}

	public List<PlanillaDetalleHost> buscarPorPlanilla(PlanillaDetalleHostPK id) throws PlanillaHostException{
			return busqueda.asignarNombreAfiliadoHost(detplahostDao.buscarPorPlanilla(id));		
	}

	public void eliminarPlanillaDetalle(PlanillaDetalleHost plapredet){
		detplahostDao.delete(plapredet);
	}
	
}
