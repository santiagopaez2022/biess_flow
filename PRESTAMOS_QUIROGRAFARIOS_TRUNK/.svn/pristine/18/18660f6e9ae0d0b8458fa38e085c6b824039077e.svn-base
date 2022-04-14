package ec.gov.iess.afiliadocore.dao.impl;

import ec.gov.iess.afiliadocore.dao.ServiciosPrestadosDao;
import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.ServiciosPrestados;
import ec.gov.iess.afiliadocore.modelo.persistencia.pk.ServiciosPrestadosPK;
import ec.gov.iess.commons.dao.NamedQueryParameter;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import ec.gov.iess.commons.dao.jpa.GenericDaoJpa;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ServiciosPrestadosDaoJpa
  extends GenericDaoJpa<ServiciosPrestados, ServiciosPrestadosPK>
  implements ServiciosPrestadosDao
{
  public ServiciosPrestadosDaoJpa()
  {
    super(ServiciosPrestados.class);
  }
  
  public List<ServiciosPrestados> consultarAfiliadoActivo(String cedula)
    throws DaoException, EntidadNoExisteException
  {
    try
    {
      List<ServiciosPrestados> resultado = findByNamedQueryNamedParameter("ServiciosPrestados.findAfiliadoActivo", new NamedQueryParameter[] { new NamedQueryParameter("cedula", cedula) });
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
