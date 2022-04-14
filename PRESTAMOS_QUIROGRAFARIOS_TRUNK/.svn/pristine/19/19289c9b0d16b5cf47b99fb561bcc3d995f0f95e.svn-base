package ec.gov.iess.empleadorcore.dao;

import ec.gov.iess.commons.dao.GenericDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.empleadorcore.modelo.persistencia.embedable.Empleador;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface EmpleadorDao
  extends GenericDao<Empleador, String>
{
  public abstract Empleador consultarEmpresaPorRuc(String paramString)
    throws DaoException, EntidadNoExisteException;
  
  public abstract List<Empleador> consultarInstitucionFinanciera(String paramString)
    throws DaoException, EntidadNoExisteException;
}
