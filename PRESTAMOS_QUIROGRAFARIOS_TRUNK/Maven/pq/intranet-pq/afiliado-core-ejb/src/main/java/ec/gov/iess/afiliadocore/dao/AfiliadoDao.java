package ec.gov.iess.afiliadocore.dao;

import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.Afiliado;
import ec.gov.iess.commons.dao.GenericDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface AfiliadoDao
  extends GenericDao<Afiliado, String>
{
  public abstract List<Afiliado> consultarAfiliadoActivo(String paramString)
    throws DaoException, EntidadNoExisteException;
  
  public abstract Afiliado consultarPorCedula(String paramString)
    throws DaoException, EntidadNoExisteException;
}
