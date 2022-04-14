package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CuentasBancariasAprobadas;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasPk;
import ec.gov.iess.dao.GenericDao;

/**
 * @author acantos
 *
 */
@Local
public interface CuentaBancariaAprobadaDao extends GenericDao<CuentasBancariasAprobadas, CuentasBancariasAprobadasPk>{
	
	/**
	 * 
	 * <b> Busca las cuentas bancarias del afiliado </b>
	 * <p>
	 * [Author: acantos, Date: 31/08/2011]
	 * </p>
	 * 
	 * @param cedula, la cedula del afiliado
	 */	
	public List<CuentasBancariasAprobadas> consultarCuentasporAfiliado(String cedula);
	
}
