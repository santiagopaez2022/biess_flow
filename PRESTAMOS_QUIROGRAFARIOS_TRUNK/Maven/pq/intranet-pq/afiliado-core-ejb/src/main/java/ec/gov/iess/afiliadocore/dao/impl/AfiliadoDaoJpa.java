package ec.gov.iess.afiliadocore.dao.impl;

import ec.gov.iess.afiliadocore.dao.AfiliadoDao;
import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.Afiliado;
import ec.gov.iess.commons.dao.NamedQueryParameter;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.dao.jpa.GenericDaoJpa;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class AfiliadoDaoJpa
  extends GenericDaoJpa<Afiliado, String>
  implements AfiliadoDao
{
  public AfiliadoDaoJpa()
  {
    super(Afiliado.class);
  }
  
  public List<Afiliado> consultarAfiliadoActivo(String cedula)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<Afiliado> afiliadoList = findByNamedQueryNamedParameter("Afiliado.findAByCedula", new NamedQueryParameter[] { new NamedQueryParameter("cedula", cedula) });
      if (afiliadoList.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return afiliadoList;
    }
    catch (EntidadException e)
    {
      throw new DaoException(e);
    }
  }
  
  public Afiliado consultarPorCedula(String cedula)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<Afiliado> afiliadoList = findByNamedQueryNamedParameter("Afiliado.findAByCedula", new NamedQueryParameter[] { new NamedQueryParameter("cedula", cedula) });
      if (afiliadoList.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return (Afiliado)afiliadoList.iterator().next();
    }
    catch (EntidadException e)
    {
      throw new DaoException(e);
    }
  }
}
