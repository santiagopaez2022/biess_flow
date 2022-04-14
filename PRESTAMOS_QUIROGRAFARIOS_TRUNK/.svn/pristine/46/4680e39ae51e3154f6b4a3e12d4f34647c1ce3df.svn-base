package ec.gov.iess.afiliadocore.dao;

import ec.gov.iess.afiliadocore.modelo.persistencia.embedable.ServiciosPrestadosHistorico;
import ec.gov.iess.afiliadocore.modelo.persistencia.pk.ServiciosPrestadosHistoricoPK;
import ec.gov.iess.commons.dao.GenericDao;
import ec.gov.iess.commons.dao.excepciones.DaoException;
import ec.gov.iess.commons.dao.excepciones.EntidadNoExisteException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public abstract interface ServiciosPrestadosHistoricoDao
  extends GenericDao<ServiciosPrestadosHistorico, ServiciosPrestadosHistoricoPK>
{
  public abstract List<ServiciosPrestadosHistorico> consultarPeriodoActivo(String paramString, Date paramDate)
    throws DaoException, EntidadNoExisteException;
}
