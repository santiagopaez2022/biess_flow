package ec.fin.biess.creditos.pq.ws.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.jboss.wsf.spi.annotation.WebContext;

import ec.gov.iess.creditos.modelo.dto.OrdenCompraEntradaWS;
import ec.gov.iess.creditos.pq.excepcion.OrdenCompraException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;


@WebService(serviceName = "OrdenComputador")
@SOAPBinding(style = Style.DOCUMENT)
@WebContext(contextRoot = "/pqws", urlPattern = "/orden-computador")
@Stateless
//@HandlerChain(file = "META-INF/handlers.xml")
public class OrdenWsImpl implements OrdenWs{

	
	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	AdministracionOrdenCompraProveedorServicio administracionOrdenCompraProveedorServicio;
	
	@WebMethod(operationName="confirmarOrden")
	public String confirmarOrden(OrdenCompraEntradaWS ordenCompraEntradaWS ) {
		String respuesta = null;
		try {
			
			administracionOrdenCompraProveedorServicio.confirmarOrdenEntrega(ordenCompraEntradaWS);
			respuesta = 
				generarRespuesta(
						ordenCompraEntradaWS.getNumeroOrden(),
						ordenCompraEntradaWS.getFecConfOrden(),
						"1",
						"ok");
		} catch (OrdenCompraException e) {
			respuesta = 
			generarRespuesta(
					ordenCompraEntradaWS.getNumeroOrden(),
					ordenCompraEntradaWS.getFecConfOrden(),
					"0",
					e.getMessage());
		}		
		return respuesta;
	}
	
	private String generarRespuesta(String numeroOrden, Date fechaConfirmacion, String codErr, String mensErr){
		StringBuffer respuesta = new StringBuffer();
		respuesta.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		respuesta.append("<numeroOrden>");
		respuesta.append(numeroOrden);
		respuesta.append("</numeroOrden>");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if (fechaConfirmacion == null)
			fechaConfirmacion = new Date();
        String dateS = sdf.format(fechaConfirmacion	);
		
		respuesta.append("<fechaConfirmacion>");
		respuesta.append(dateS);
		respuesta.append("</fechaConfirmacion>");
		
		respuesta.append("<codErr>");
		respuesta.append(codErr);
		respuesta.append("</codErr>");
		
		respuesta.append("<mensErr>");
		respuesta.append(mensErr);
		respuesta.append("</mensErr>");
		
		return respuesta.toString();
	}
	
	
}
