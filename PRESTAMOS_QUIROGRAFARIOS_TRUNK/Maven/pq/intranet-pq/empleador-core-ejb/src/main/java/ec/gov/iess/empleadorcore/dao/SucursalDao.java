package ec.gov.iess.empleadorcore.dao;

import ec.gov.iess.commons.dao.GenericDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.empleadorcore.modelo.persistencia.embedable.Sucursal;
import ec.gov.iess.empleadorcore.modelo.persistencia.pk.SucursalPK;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface SucursalDao
  extends GenericDao<Sucursal, SucursalPK>
{
  public abstract List<Sucursal> obtenerListadoEmpresas(String paramString)
    throws DaoException, EntidadNoExisteException;
}
