package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.CatalogoRechazoCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoRechazoCredito;
import ec.gov.iess.creditos.pq.servicio.CatalogoRechazoServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CatalogoRechazoServicioRemote;

/**
 * Implementacion metodos del catalogo de motivos de rechazo de un pq
 * creacion 24/08/2011 
 * @author acantos
 * @version 1.0
 *
 */
 
@Stateless
public class CatalogoRechazoServicioImpl implements CatalogoRechazoServicioLocal, 
CatalogoRechazoServicioRemote{
	
	LoggerBiess log = LoggerBiess.getLogger(CatalogoRechazoServicioImpl.class);
	
	@EJB
	private CatalogoRechazoCreditoDao catalogorechazodao;

	public List<CatalogoRechazoCredito> devolvertodos() {
		return catalogorechazodao.devolvertodos();
	}


}
