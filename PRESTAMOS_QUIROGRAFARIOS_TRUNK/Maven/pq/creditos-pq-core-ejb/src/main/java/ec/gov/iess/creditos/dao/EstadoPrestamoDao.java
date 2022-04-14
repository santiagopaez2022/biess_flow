package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.dao.GenericDao;

@Local
public interface EstadoPrestamoDao extends GenericDao<EstadoPrestamo, String>{

}
