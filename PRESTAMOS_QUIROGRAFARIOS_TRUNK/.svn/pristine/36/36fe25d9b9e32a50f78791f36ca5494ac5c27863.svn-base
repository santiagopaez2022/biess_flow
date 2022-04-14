package ec.gov.iess.afiliadocore.dao;

import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.AfiliadoCuentaBancaria;
import ec.gov.iess.commons.dao.GenericDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import javax.ejb.Local;

@Local
public abstract interface AfiliadoCuentaBancariaDao
  extends GenericDao<AfiliadoCuentaBancaria, Long>
{
  public abstract AfiliadoCuentaBancaria consultarPorCedulaAfiliado(String paramString)
    throws DaoException, EntidadNoExisteException;
}
