package ec.gov.iess.creditos.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CatalogoRechazoCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoRechazoCredito;
import ec.gov.iess.dao.ejb.GenericEjbDao;


/**
 * @author acantos
 *
 */
@Stateless
public class CatalogoRechazoCreditoDaoImpl  extends GenericEjbDao<CatalogoRechazoCredito, BigInteger>
 implements CatalogoRechazoCreditoDao{

	CatalogoRechazoCreditoDaoImpl() {
		super(CatalogoRechazoCredito.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<CatalogoRechazoCredito> devolvertodos() {
		Query query = em.createNamedQuery("catalogorechazo.todos");
		return (List<CatalogoRechazoCredito>) query.getResultList();
	}

		
}
