package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.DestinoCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;


@Remote
public interface DestinoCreditoServicioRemote {
	
	public void insertarDestinoCredito(PrestamoPk pk,DatosCredito credito);
	//public void insertarDestinoCredito(DestinoCredito destinoCredito);
	

}
