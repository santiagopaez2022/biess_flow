package ec.gov.iess.afiliadocore.dao.impl;

import ec.gov.iess.afiliadocore.dao.AfiliadoCuentaBancariaDao;
import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.AfiliadoCuentaBancaria;
import ec.gov.iess.commons.dao.QueryParameter;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.dao.jpa.GenericDaoJpa;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class AfiliadoCuentaBancariaDaoJpa
  extends GenericDaoJpa<AfiliadoCuentaBancaria, Long>
  implements AfiliadoCuentaBancariaDao
{
  public AfiliadoCuentaBancariaDaoJpa()
  {
    super(AfiliadoCuentaBancaria.class);
  }
  
  public AfiliadoCuentaBancaria consultarPorCedulaAfiliado(String cedulaAfiliado)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<AfiliadoCuentaBancaria> listaAfiliado = findByNamedQueryPositionalParameter("AfiliadoCuentaBancaria.consultarPorCedulaAfiliado", new QueryParameter[] { new QueryParameter(cedulaAfiliado) });
      if (listaAfiliado.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return (AfiliadoCuentaBancaria)listaAfiliado.iterator().next();
    }
    catch (EntidadException e)
    {
      throw new DaoException(e);
    }
  }
}
