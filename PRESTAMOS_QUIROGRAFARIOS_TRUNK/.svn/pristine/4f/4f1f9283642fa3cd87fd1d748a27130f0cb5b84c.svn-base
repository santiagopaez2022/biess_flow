package ec.gov.iess.planillaspq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.afiliadocore.dao.AfiliadoDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.empleadorcore.dao.EmpleadorDao;
import ec.gov.iess.planillaspq.exceptions.AfiliadoNoExisteException;
import ec.gov.iess.planillaspq.exceptions.EmpleadorNoExisteException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.servicio.BusquedasServicioLocal;
import ec.gov.iess.planillaspq.servicio.BusquedasServicioRemote;


@Stateless
public class BusquedasServicioImpl implements BusquedasServicioLocal,
	BusquedasServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(AjustePlanillaServicioImpl.class);
	
	@EJB
	private AfiliadoDao afiliadoDao;

	@EJB
	private EmpleadorDao empleadorDao; 
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public String buscarAfiliadoPorId(String id) throws AfiliadoNoExisteException{
		
		try{
			return afiliadoDao.consultarPorCedula(id).getApenomafi();
		}catch(EntidadNoExisteException e){
			e.printStackTrace();				
			throw new AfiliadoNoExisteException("NO EXISTE AFILIADO");
		}catch(DaoException e){
			e.printStackTrace();			
			throw new AfiliadoNoExisteException();
		}
	}
	public String buscarEmpleadorPorId(String id) throws EmpleadorNoExisteException{
		
		try{
		return empleadorDao.consultarEmpresaPorRuc(id).getNomemp();
		}catch(EntidadNoExisteException e){
			e.printStackTrace();				
			throw new EmpleadorNoExisteException("NO EXISTE EMPLEADOR");
		}catch(DaoException e){
			e.printStackTrace();			
			throw new EmpleadorNoExisteException();
		}
	}

	public List<PlanillaPrestamosDetalle> asignarNombreAfiliado(List<PlanillaPrestamosDetalle> lista){
		
		for(PlanillaPrestamosDetalle pladet:lista){
			String nombre="";
			try {
				nombre = buscarAfiliadoPorId(pladet.getNumafi());
			} catch (AfiliadoNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pladet.setNombreAfiliado(nombre);
		}
		return lista;
		
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PlanillaDetalleHost> asignarNombreAfiliadoHost(List<PlanillaDetalleHost> lista){
		
		for(PlanillaDetalleHost pladet:lista){
			try{
				pladet.setNombreAfiliado(buscarAfiliadoPorId(pladet.getPk().getNumafi()));
			}catch(Exception e){
				pladet.setNombreAfiliado("NO REGISTRADO EN HL");
				//e.printStackTrace();
			}	
		}
		
		return lista;
		
	}

	public List<Planillas> asignarNombreEmpleador(List<Planillas> lista){
		
		for(Planillas pla:lista){
			try {
				pla.setNombreEmpleador(buscarEmpleadorPorId(pla.getPk().getRucemp()));
			} catch (EmpleadorNoExisteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		return lista;
		
	}
		
}
