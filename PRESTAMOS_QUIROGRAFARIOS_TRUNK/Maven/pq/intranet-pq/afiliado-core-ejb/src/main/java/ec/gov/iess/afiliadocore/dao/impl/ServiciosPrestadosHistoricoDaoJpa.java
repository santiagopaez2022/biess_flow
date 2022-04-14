package ec.gov.iess.afiliadocore.dao.impl;

import ec.gov.iess.afiliadocore.dao.ServiciosPrestadosHistoricoDao;
import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.ServiciosPrestadosHistorico;
import ec.gov.iess.afiliadocore.modelo.persistencia.pk.ServiciosPrestadosHistoricoPK;
import ec.gov.iess.commons.dao.QueryParameter;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.dao.jpa.GenericDaoJpa;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ServiciosPrestadosHistoricoDaoJpa
  extends GenericDaoJpa<ServiciosPrestadosHistorico, ServiciosPrestadosHistoricoPK>
  implements ServiciosPrestadosHistoricoDao
{
  public ServiciosPrestadosHistoricoDaoJpa()
  {
    super(ServiciosPrestadosHistorico.class);
  }
  
  public List<ServiciosPrestadosHistorico> consultarPeriodoActivo(String cedulaAfiliado, Date fechaReposo)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<ServiciosPrestadosHistorico> resultado = findByNamedQueryPositionalParameter("ServiciosPrestadosHistorico.findByFechaAfiliadoActivo", new QueryParameter[] { new QueryParameter(cedulaAfiliado), new QueryParameter(fechaReposo) });
      if (resultado.isEmpty()) {
        throw new EntidadNoExisteException();
      }
      return resultado;
    }
    catch (EntidadException e)
    {
      throw new DaoException(e);
    }
  }
}
