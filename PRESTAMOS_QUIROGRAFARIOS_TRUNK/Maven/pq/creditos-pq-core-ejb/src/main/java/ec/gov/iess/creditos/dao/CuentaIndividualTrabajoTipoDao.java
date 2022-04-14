package ec.gov.iess.creditos.dao;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.CuentaIndividualTrabajoTipo;
import ec.gov.iess.dao.GenericDao;

@Remote
public interface CuentaIndividualTrabajoTipoDao extends
GenericDao<CuentaIndividualTrabajoTipo, String>{

}
