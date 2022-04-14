package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.dao.GenericDao;
@Local
public interface EstadoDividendoPrestamoDao extends
GenericDao<EstadoDividendoPrestamo, String> {

}
