package ec.gov.iess.afiliadocore.dao;

import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.ServiciosPrestados;
import ec.gov.iess.afiliadocore.modelo.persistencia.pk.ServiciosPrestadosPK;
import ec.gov.iess.commons.dao.GenericDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface ServiciosPrestadosDao
  extends GenericDao<ServiciosPrestados, ServiciosPrestadosPK>
{
  public abstract List<ServiciosPrestados> consultarAfiliadoActivo(String paramString)
    throws DaoException, EntidadNoExisteException;
}
