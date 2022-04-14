package ec.gov.iess.creditos.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoRechazoCredito;
import ec.gov.iess.dao.GenericDao;

/**
 * Interface para declarar metodos para el catalogo de motivos de rechazo de un P
 * 
 * @author acantos
 * @version $Revision: 1.0 $
 */
@Local
public interface CatalogoRechazoCreditoDao extends GenericDao<CatalogoRechazoCredito, BigInteger>{
	
	public List<CatalogoRechazoCredito> devolvertodos();
}
