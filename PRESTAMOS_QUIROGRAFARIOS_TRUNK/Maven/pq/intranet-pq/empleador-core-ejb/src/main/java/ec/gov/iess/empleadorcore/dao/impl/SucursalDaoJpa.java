package ec.gov.iess.empleadorcore.dao.impl;

import ec.gov.iess.commons.dao.NamedQueryParameter;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.dao.jpa.GenericDaoJpa;
import ec.gov.iess.empleadorcore.dao.SucursalDao;
import ec.gov.iess.empleadorcore.modelo.persistencia.embedable.Sucursal;
import ec.gov.iess.empleadorcore.modelo.persistencia.pk.SucursalPK;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class SucursalDaoJpa
  extends GenericDaoJpa<Sucursal, SucursalPK>
  implements SucursalDao
{
  public SucursalDaoJpa()
  {
    super(Sucursal.class);
  }
  
  public List<Sucursal> obtenerListadoEmpresas(String codigoRuc)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<Sucursal> sucursalList = findByNamedQueryNamedParameter("Sucursal.findByRucEmpresa", new NamedQueryParameter[] { new NamedQueryParameter("rucEmpresa", codigoRuc) });
      if (sucursalList.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return sucursalList;
    }
    catch (EntidadException e)
    {
      throw new DaoException();
    }
  }
}
