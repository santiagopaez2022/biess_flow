package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gov.iess.creditos.dao.DestinoCreditoDao;
import ec.gov.iess.creditos.modelo.dto.DatosCatalogo;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.Catalogo;
import ec.gov.iess.creditos.modelo.persistencia.DestinoCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.DestinoCreditoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.servicio.DestinoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.DestinoCreditoServicioRemote;

@Stateless
public class DestinoCreditoServicioImpl implements DestinoCreditoServicio,DestinoCreditoServicioRemote{
	@EJB
	private DestinoCreditoDao destinoCreditoDao;
	

	
	//@TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
	public void insertarDestinoCredito(PrestamoPk pk,DatosCredito credito)
	{
		
		List<DatosCatalogo> lst = credito.getDtocatalogo();

		for (DatosCatalogo dto : lst) {
			if(dto.getSeleccion())
			{
			DestinoCredito destinoCredito=new DestinoCredito();
			DestinoCreditoPk destinoCreditoPk=new DestinoCreditoPk();
			destinoCreditoPk.setCodPreAfi(pk.getNumpreafi());
			destinoCreditoPk.setCodPreCla(pk.getCodprecla());
			destinoCreditoPk.setCodPreTip(pk.getCodpretip());
			destinoCreditoPk.setOrdPreAfi(pk.getOrdpreafi());
			destinoCreditoPk.setCopqid(dto.getIdcatalogo());
			destinoCredito.setPk(destinoCreditoPk);
			destinoCredito.setObservacion(dto.getObservacion());
			destinoCredito.setFechaRegistro(new Date());
			destinoCreditoDao.insert(destinoCredito);
			}
			
		}
		
	}
	

}
