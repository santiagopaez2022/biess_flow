package ec.gov.iess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.CatalogoRechazoCredito;

/**
 * Interfaz para metodos del catalogo de motivos de rechazo de un pq
 * @author acantos
 * @version 1.0
 */
@Remote
public interface CatalogoRechazoServicioRemote {
	public List<CatalogoRechazoCredito> devolvertodos();
}
