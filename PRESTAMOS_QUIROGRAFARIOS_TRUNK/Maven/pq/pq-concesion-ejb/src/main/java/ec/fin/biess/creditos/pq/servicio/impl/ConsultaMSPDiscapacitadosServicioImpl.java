/**
 * 
 */
package ec.fin.biess.creditos.pq.servicio.impl;

import java.net.URL;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.namespace.QName;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.consultasnap.client.mq.modelo.ConsultarDiscapacidadMSPResp;
import ec.fin.biess.consultasnap.client.mq.service.SnapServiceLocal;
import ec.fin.biess.creditos.pq.excepcion.MSPBiessException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.servicio.ConsultaMSPDiscapacitadosServicio;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.tools.integracion.excepciones.ServicioESBExcepcion;
import ec.fin.biess.webservice.DatoAfiliadoWebService;
import ec.fin.biess.webservice.SnapRespuestaDto;
import ec.fin.biess.webservice.SnapWebService;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * @author christian.gomez
 * 
 */
@Stateless
public class ConsultaMSPDiscapacitadosServicioImpl implements ConsultaMSPDiscapacitadosServicio {

	private static final transient LoggerBiess LOG = LoggerBiess.getLogger(ConsultaMSPDiscapacitadosServicioImpl.class);

	@EJB
	private SnapServiceLocal snapServicio;
    @EJB
    private ParametroBiessDao parametroBiessDao;
    @EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.ConsultaMSPDiscapacitadosServicio#consultarWSDiscESB(java.lang.String,
	 * java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Boolean consultarWSDiscESB(String cedula, String direccionIP) throws MSPBiessException {
		boolean result = false;
		try {
			/* Consultar servicio SNAP MSP */
			ConsultarDiscapacidadMSPResp resp = snapServicio.consultarDiscapacidadMSP("CLI-PQ", cedula);
			if (!resp.getControlRes().getCodErr().equals("0")) {
				ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, "false",
						resp.getControlRes().getCodErr() + " - " + resp.getControlRes().getDesErr());
				this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
				throw new MSPBiessException(resp.getControlRes().getDesErr());
			} else {
				/* Validar discapacidad */
				if (null != resp.getDatosPersona().getGradoDiscapacidad()
						&& !resp.getDatosPersona().getGradoDiscapacidad().isEmpty()) {// Es discapacitado
					result = true;
					ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, "true",
							"Es discapacitado");
					this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
				}
			}
		} catch (ServicioESBExcepcion e) { // Si no es discapacitado
			LOG.error("Error al consumir servicio SNAP MSP.", e);
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, "false",
					e.getMessage());
			this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
			throw new MSPBiessException(
					"Estimado asegurado, al momento el servicio para validar su discapacidad no est\u00E1 disponible. Por favor intente m\u00E1s tarde.");
		} catch (Exception e) {
			LOG.error("Error no manejado al consumir servicio SNAP MSP.", e);
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, "false",
					e.getMessage());
			this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
			throw new MSPBiessException(
					"Estimado asegurado, al momento el servicio para validar su discapacidad no est\u00E1 disponible. Por favor intente m\u00E1s tarde.");
		}

		return result;
	}
        
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.ConsultaMSPDiscapacitadosServicio#consultarWSDisc(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean consultarWSDisc(String cedula, String direccionIP) throws MSPBiessException {
		boolean respuesta = false;
		try {
			String urlString = parametroBiessDao.consultarPorIdNombreParametro("SNAP_DISCAPACITADO", "URL_CONSUMO_DISCAPACITADO").getValorCaracter();
			URL url = new URL(urlString);
			QName qname = new QName("http://webservice.biess.fin.ec/", "DatoAfiliadoWebService");
			DatoAfiliadoWebService datoAfiliadoWebService = new DatoAfiliadoWebService(url, qname);
			SnapWebService snapWSService = datoAfiliadoWebService.getSnapWebServicePort();
			SnapRespuestaDto snapRespuestaDto = snapWSService.validaDiscapacitado(cedula);
			respuesta = snapRespuestaDto.isDiscapacitado();
		} catch (Exception ex) {
			throw new MSPBiessException("ERROR AL CONSUMIR EL SERVICIO DEL SNAP PARA DISCAPACITADOS", ex);
		}
		return respuesta;
	}

}
