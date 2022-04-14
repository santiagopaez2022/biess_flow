package ec.fin.biess.creditos.pq.ws.impl;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.OrdenCompraEntradaWS;

@Local
public interface OrdenWs {
	
	public String confirmarOrden(OrdenCompraEntradaWS ordenCompraEntradaWS );
}
