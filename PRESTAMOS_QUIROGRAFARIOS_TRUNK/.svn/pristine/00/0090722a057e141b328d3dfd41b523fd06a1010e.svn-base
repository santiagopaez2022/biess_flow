package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CuentaBancariaAprobadaDao;
import ec.gov.iess.creditos.modelo.persistencia.CuentasBancariasAprobadas;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author acantos
 *
 */
@Stateless
public class CuentaBancariaAprobadaImpl extends GenericEjbDao<CuentasBancariasAprobadas, CuentasBancariasAprobadasPk>
	implements CuentaBancariaAprobadaDao{
	
	LoggerBiess log = LoggerBiess.getLogger(PrestamoDaoImpl.class);

	public CuentaBancariaAprobadaImpl() {
		super(CuentasBancariasAprobadas.class);
	}

	@SuppressWarnings("unchecked")
	public List<CuentasBancariasAprobadas> consultarCuentasporAfiliado(String cedula){
		Query query = em.createNamedQuery("CuentasBancariasAprobadas.busquedaporafiliado");
			query.setParameter("cedula", cedula);
		return query.getResultList();
	}
}
