package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.SolicitudPago;
import ec.gov.iess.creditos.pq.servicio.SolicitudPagoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitudPagoServicioRemoto;
import ec.gov.iess.creditos.sp.SolicitudPagoJdbc;
import ec.gov.iess.creditos.sp.impl.SolicitudPagoJdbcImpl;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless
public class SolicitudPagoServicioImpl implements SolicitudPagoServicio,
		SolicitudPagoServicioRemoto {

	LoggerBiess log = LoggerBiess.getLogger(SolicitudPagoServicioImpl.class);

	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;

	/**
	 * Constructor
	 */
	public SolicitudPagoServicioImpl() {
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitudPagoServicio#obtenerPorFechaSolicitudPago(java.util.Date,
	 *      java.util.Date, java.util.List)
	 */
	public List<SolicitudPago> obtenerPorFechaSolicitudPago(Date desde,
			Date hasta, List<Long> estados, List<Long> tiposPrestamos) {

		SolicitudPagoJdbc solicitudPagoJdbc = new SolicitudPagoJdbcImpl(
				dataSource);

		return solicitudPagoJdbc.obtenerPorFechaSolicitudPago(desde, hasta,
				estados,tiposPrestamos);
	}

}