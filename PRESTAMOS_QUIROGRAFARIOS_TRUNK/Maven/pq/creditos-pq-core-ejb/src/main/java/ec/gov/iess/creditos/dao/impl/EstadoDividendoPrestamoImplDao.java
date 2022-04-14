package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.EstadoDividendoPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.dao.ejb.GenericEjbDao;


@Stateless
public class EstadoDividendoPrestamoImplDao  extends
GenericEjbDao<EstadoDividendoPrestamo, String> 
implements EstadoDividendoPrestamoDao{
	
	public EstadoDividendoPrestamoImplDao() {
		super(EstadoDividendoPrestamo.class);
	}
}
