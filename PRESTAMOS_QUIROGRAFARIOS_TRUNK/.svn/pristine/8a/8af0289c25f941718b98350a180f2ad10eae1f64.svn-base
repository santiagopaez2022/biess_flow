package ec.gov.iess.empleadorcore.dao.impl;

import ec.gov.iess.commons.dao.NamedQueryParameter;
import ec.gov.iess.commons.dao.QueryParameter;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.dao.jpa.GenericDaoJpa;
import ec.gov.iess.empleadorcore.dao.EmpleadorDao;
import ec.gov.iess.empleadorcore.modelo.persistencia.embedable.Empleador;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class EmpleadorDaoJpa
  extends GenericDaoJpa<Empleador, String>
  implements EmpleadorDao
{
  public EmpleadorDaoJpa()
  {
    super(Empleador.class);
  }
  
  public Empleador consultarEmpresaPorRuc(String rucEmpresa)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<Empleador> empleadorList = findByNamedQueryNamedParameter("Empleador.findByRuc", new NamedQueryParameter[] { new NamedQueryParameter("rucEmpresa", rucEmpresa) });
      if (empleadorList.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return (Empleador)empleadorList.get(0);
    }
    catch (EntidadException e)
    {
      throw new DaoException();
    }
  }
  
  public List<Empleador> consultarInstitucionFinanciera(String nombre)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<Empleador> empleadorList = findByNamedQueryPositionalParameter("Empleador.findByInstitucionFinanciera", new QueryParameter[] { new QueryParameter(nombre) });
      if (empleadorList.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return empleadorList;
    }
    catch (EntidadException e)
    {
      throw new DaoException();
    }
  }
}
