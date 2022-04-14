package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ResumenCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.ResumenCreditoPQ;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */

/**
 * Session Bean implementation class ResumenCreditoDaoImpl
 */
@Stateless
public class ResumenCreditoDaoImpl extends GenericEjbDao<ResumenCreditoPQ,String> implements ResumenCreditoDao {

	LoggerBiess log = LoggerBiess.getLogger(ResumenCreditoDaoImpl.class);
	
    
    public ResumenCreditoDaoImpl() {
    	super(ResumenCreditoPQ.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void ingresar(ResumenCreditoPQ resumenCredito) {
		try {
			em.persist(resumenCredito);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


}
