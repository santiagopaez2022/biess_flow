package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.EstadoGarantiaDao;
import ec.gov.iess.creditos.modelo.persistencia.EstadoGarantia;
import ec.gov.iess.dao.ejb.GenericEjbDao;

@Stateless
public class EstadoGarantiasDaoImpl extends
		GenericEjbDao<EstadoGarantia, String> implements
		EstadoGarantiaDao {
	
	public EstadoGarantiasDaoImpl() {
		super(EstadoGarantia.class);
	}

}
