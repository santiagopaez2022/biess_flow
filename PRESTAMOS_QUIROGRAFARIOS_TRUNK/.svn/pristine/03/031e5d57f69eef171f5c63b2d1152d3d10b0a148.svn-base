package ec.gov.iess.creditos.dao.impl;


import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CuentaBancariaHistAprobadaDao;
import ec.gov.iess.creditos.modelo.persistencia.CuentasBancariasHistAprobadas;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasHistPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author acantos
 *
 */
@Stateless
public class CuentaBancariaHistAprobadaImpl extends GenericEjbDao<CuentasBancariasHistAprobadas, CuentasBancariasAprobadasHistPk>
	implements CuentaBancariaHistAprobadaDao{
	
	LoggerBiess log = LoggerBiess.getLogger(PrestamoDaoImpl.class);

	public CuentaBancariaHistAprobadaImpl() {
		super(CuentasBancariasHistAprobadas.class);
	}
}
