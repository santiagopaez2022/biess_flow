package ec.fin.biess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.client.ws.registrocivillocal.ClientRegistroCivilLocal;
import ec.fin.biess.client.ws.registrocivillocal.dto.CiudadanoRegistroCivilDto;
import ec.fin.biess.client.ws.registrocivillocal.exception.ClientRegistroCivilLocalException;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionRegistroCivil;
import ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicioT;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * Servicio para consultar el servicio web del registro civil
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class RegistroCivilBiessServicioImplT implements RegistroCivilBiessServicioT {

	private static final transient LoggerBiess LOG = LoggerBiess.getLogger(RegistroCivilBiessServicioImplT.class);
	
	@EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicioT#consultarWS(java.lang.String,
	 * java.lang.String, java.lang.String, ec.fin.biess.enumeraciones.AplicativoEnum)
	 */
	public InformacionRegistroCivil consultarWS(String cedula, String cedulaFuncionario, String direccionIP, AplicativoEnum aplicativo)
			throws RegistroCivilBiessException {
		String respuesta = "OK";
		String estado = "0";
		String observacion = "";
		InformacionRegistroCivil informacionRegistro = new InformacionRegistroCivil();
		try {
			ClientRegistroCivilLocal clienteRegistoCivil = new ClientRegistroCivilLocal();
			CiudadanoRegistroCivilDto ciudadano = clienteRegistoCivil.consultarDatosAll(cedula);
			if (ciudadano != null) {
				informacionRegistro.setCedula(cedula);
				informacionRegistro.setCodigoDactilar(ciudadano.getCodigoDactilar());
				informacionRegistro.setFechaExpedicion(ciudadano.getFechaExpedicionCedula());
				informacionRegistro.setCedula(cedula);
				informacionRegistro.setConyugue(ciudadano.getConyuge());
				informacionRegistro.setDireccionDomiciliaria(ciudadano.getDireccionDomiciliaria());
				informacionRegistro.setEstadoCivil(ciudadano.getEstadoCivil());
				informacionRegistro.setFechaDefuncion(ciudadano.getFechaDefuncion());
				informacionRegistro.setFechaNacimiento(ciudadano.getFechaNacimiento());
				informacionRegistro.setGenero(ciudadano.getGenero());
				informacionRegistro.setInstruccion(ciudadano.getInstruccion());
				informacionRegistro.setLugarNacimiento(ciudadano.getLugarNacimiento());
				informacionRegistro.setNacionalidad(ciudadano.getNacionalidad());
				informacionRegistro.setNombre(ciudadano.getNombre());
				informacionRegistro.setProfesion(ciudadano.getProfesion());
				informacionRegistro.setSexo(ciudadano.getSexo());
			}
		} catch (ClientRegistroCivilLocalException e) {
			LOG.error("Se presento un error al consultar informacion del registro civil", e);
			observacion = "Error no manejado al consultar informacion del registro civil: " + e.getMessage();
			respuesta = "ERR";
			estado = "1";
			throw new RegistroCivilBiessException(observacion, e);
		} finally {
			/* Guardar log de consulta al WS del registro civil */
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, respuesta,
					estado + " " + observacion);
			if (AplicativoEnum.PQ_WEB == aplicativo) {
				this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_REGISTRO_CIVIL_PQ_WEB, parametroEvento, direccionIP, cedula);
			} else {
				this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_REGISTRO_CIVIL_PQ_INTRANET, parametroEvento, direccionIP, cedula);
			}
		}

		return informacionRegistro;
	}

}