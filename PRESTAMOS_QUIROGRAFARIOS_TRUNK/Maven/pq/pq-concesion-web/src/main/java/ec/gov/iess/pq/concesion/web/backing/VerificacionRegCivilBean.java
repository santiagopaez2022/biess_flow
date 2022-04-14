/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionRegistroCivil;
import ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicioT;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.exception.RegistroCivilException;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * 
 * Permite enlazar datos de tipo de aprobación con la pantalla
 * 
 * @author pjarrin
 * 
 */
public class VerificacionRegCivilBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 5009032001046866185L;

	private Date confirmaFechaExpedicion;
	private String confirmaCodigoDactilar;
	private String mensajeRegCivil;
	private String mensajeErrorRegCivil;
	private String errorIngresoDatos;
	private String errorConfirmacionDatos;
	private HtmlAjaxCommandButton btnAceptar;
	private boolean verPopPup = false;
	private DatosBean datos;

	@EJB(name = "RegistroCivilBiessServicioImplT/local")
	RegistroCivilBiessServicioT registroCivilServicioT;

	private transient LoggerBiess log = LoggerBiess.getLogger(VerificacionRegCivilBean.class);

	public VerificacionRegCivilBean() {
	}

	/**
	 * Inicializacion de datos
	 */
	public void init() {
		datos.setRespuesta(false);
		datos.setCambiarBoton(false);
		datos.setRepAnio(null);
		datos.setRepDia(null);
		datos.setRepMes(null);
		datos.setAnio(null);
		datos.setMes(null);
		datos.setDia(null);
		datos.setRepCodigoDactilar(null);
		datos.setCodigoDactilar(null);
		datos.setAceptaDatos(false);
		datos.setFechaExpedicion(null);
		datos.setErrorRC(false);
	}

	/**
	 * Funcion que acepta la validacion para proceder a confirmar (normal)
	 */
	public String aceptarValidacion() {
		if (datos.isCreditoNovacion()) {
			return aceptarValidacionNovacion();
		} else {
			return aceptarValidacionCredito();
		}
	}
	
	/**
	 * Funcion que acepta la validacion para proceder a confirmar (normal)
	 * 
	 * @return String
	 */
	public String aceptarValidacionNovacion() {
		return preValidacionInfoRC(true);
	}
	
	/**
	 * Funcion que acepta la validacion para proceder a confirmar (normal)
	 */
	public String aceptarValidacionCredito() {
		return preValidacionInfoRC(false);
	}

	/**
	 * Metodo que realiza una validacion previa de la info ingresada por el usuario en la ventana de validacion RC.
	 * 
	 * @param isnovacion
	 * @return String
	 */
	private String preValidacionInfoRC(boolean isnovacion) {
		mensajeErrorRegCivil = null;
		errorIngresoDatos = null;
		errorConfirmacionDatos = null;
		datos.setRespuesta(false);
		if (null == preValidacionIngresoDatos()) {
			return null;
		}
		if (null == preValidacionConfirmacionDatos()) {
			return null;
		}
		if (datos.getAnio().trim().compareToIgnoreCase(datos.getRepAnio().trim()) == 0
				&& datos.getMes().trim().compareToIgnoreCase(datos.getRepMes().trim()) == 0
				&& datos.getDia().trim().compareToIgnoreCase(datos.getRepDia().trim()) == 0
				&& datos.getCodigoDactilar().trim().compareToIgnoreCase(datos.getRepCodigoDactilar()) == 0) {
			datos.setAceptaDatos(true);
			return verificacionRegCivil(isnovacion);
		} else {
			datos.setAceptaDatos(false);
			addGlobalErrorMessage("Los datos ingresados no coinciden con los de confirmaci\u00F3n. Asegurese de que los datos sean iguales", "");
			mensajeErrorRegCivil = "Los datos ingresados no coinciden con los de confirmaci\u00F3n. Asegurese de que los datos sean iguales";
			return null;
		}
	}

	/**
	 * Metodo que valida los campos de la seccion ingreso de datos.
	 * 
	 * @return
	 */
	private String preValidacionIngresoDatos() {
		if (null == datos.getCodigoDactilar() || datos.getCodigoDactilar().trim().isEmpty()) {
			errorIngresoDatos = "Ingrese c\u00F3digo dactilar";
			return null;
		}
		if (null == datos.getAnio() || datos.getAnio().trim().isEmpty()) {
			errorIngresoDatos = "Ingrese a\u00F1o fecha de expedici\u00F3n";
			return null;
		}
		if (null == datos.getMes() || datos.getMes().trim().isEmpty()) {
			errorIngresoDatos = "Ingrese mes fecha de expedici\u00F3n";
			return null;
		}
		if (null == datos.getDia() || datos.getDia().trim().isEmpty()) {
			errorIngresoDatos = "Ingrese d\u00EDa fecha de expedici\u00F3n";
			return null;
		}
		
		return "";
	}

	/**
	 * Metodo que valida los campos de la seccion confirmacion de datos.
	 * 
	 * @return
	 */
	private String preValidacionConfirmacionDatos() {
		if (null == datos.getRepCodigoDactilar() || datos.getRepCodigoDactilar().trim().isEmpty()) {
			errorConfirmacionDatos = "Confirme c\u00F3digo dactilar";
			return  null;
		}
		if (null == datos.getRepAnio() || datos.getRepAnio().trim().isEmpty()) {
			errorConfirmacionDatos = "Confirme a\u00F1o fecha de expedici\u00F3n";
			return null;
		}
		if (null == datos.getRepMes() || datos.getRepMes().trim().isEmpty()) {
			errorConfirmacionDatos = "Confirme mes fecha de expedici\u00F3n";
			return null;
		}
		if (null == datos.getRepDia() || datos.getRepDia().trim().isEmpty()) {
			errorConfirmacionDatos = "Confirme d\u00EDa fecha de expedici\u00F3n";
			return null;
		}
		
		return "";
	}

	/**
	 * Funcion para ejectuar validaciones en el caso que no confirme informacion
	 */
	public void noConfirma() {
		datos.setAceptaDatos(false);
	}

	/**
	 * Funcion que verifica fecha de expedicion y codigo dactilar del afiliado
	 * 
	 * @return
	 */
	private String verificacionRegCivil(boolean isnovacion) {
		mensajeErrorRegCivil = null;
		String tramarc = "";
		String cedula = this.datos.getSolicitante().getDatosPersonales().getCedula();
		try {
			/* Consultamos el ws del RC */

			InformacionRegistroCivil registroCivil = new InformacionRegistroCivil();
			registroCivil = registroCivilServicioT.consultarWS(datos.getSolicitante().getDatosPersonales().getCedula(), null, super.getIpUser(),
					AplicativoEnum.PQ_WEB);
			/* Buscamos los campos que necesitamos */
			String codigoDactilarWS = registroCivil.getCodigoDactilar();
			String fechaExpericacionWS = registroCivil.getFechaExpedicion();
			/* Verificar información obtenida del WS */
			if (null == codigoDactilarWS || null == fechaExpericacionWS || "" == codigoDactilarWS || "" == fechaExpericacionWS) {
				log.error("Error al obtener c\u00f3digo dactilar y/o fecha de expedici\u00f3n del registro civil.");
				/* Guardar log de consulta al WS del registro civil */
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaExitoObservacion(cedula, "false",
						"Error al obtener c\u00f3digo dactilar y/o fecha de expedici\u00f3n del registro civil.");
				super.guardarAuditoria(OperacionEnum.VERIFICACION_REGISTRO_CIVIL, parametroEvento, cedula);
				
				setDataVerificacionRC(true, false, isnovacion, false);
				throw new RegistroCivilException("Error al obtener c\u00f3digo dactilar y/o fecha de expedici\u00f3n del registro civil.");
			}
			/* Formatear fechas */
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaExpiracionFinal = null;
			String tmpFecha = datos.getDia() + "/" + datos.getMes() + "/" + datos.getAnio();
			try {
				datos.setFechaExpedicion(sdf.parse(tmpFecha));
				SimpleDateFormat sdfRc = new SimpleDateFormat("ddMMyyyy");
				fechaExpiracionFinal = sdfRc.parse(fechaExpericacionWS);
				log.info("formateo de fechas "+fechaExpiracionFinal+" / "+datos.getFechaExpedicion());
			} catch (ParseException e) {
				log.error("Error al formatear fecha a dd/MM/yyyy.");
				log.error("Fecha expedicion ws: " + fechaExpericacionWS);
				log.error("Fecha expedicion ingresada: " + tmpFecha);
				log.error(e.getMessage());
				/* Guardar log de consulta al WS del registro civil */
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaExitoObservacion(cedula, "false",
						"Error al formatear fecha a dd/MM/yyyy.");
				super.guardarAuditoria(OperacionEnum.VERIFICACION_REGISTRO_CIVIL, parametroEvento, cedula);
				
				setDataVerificacionRC(true, false, isnovacion, false);
				throw new RegistroCivilException("Error al formatear fecha a dd/MM/yyyy.");
			}
			/* Comparar datos ingresados con datos del WS */
			if (datos.getCodigoDactilar().compareTo(codigoDactilarWS) == 0 && datos.getFechaExpedicion().compareTo(fechaExpiracionFinal) == 0) {
				init();
				// verPopPup = true;
				setDataVerificacionRC(false, true, false, true);
				/* Guardar log de consulta al WS del registro civil */
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaExitoObservacion(cedula, "true",
						"El prestamo paso a estado PDA");
				super.guardarAuditoria(OperacionEnum.VERIFICACION_REGISTRO_CIVIL, parametroEvento, cedula);
				
				return "resultadoPrestamo";
			} else {
				init();
				// verPopPup = false;
				setDataVerificacionRC(false, false, isnovacion, true);
				/* Guardar log de consulta al WS del registro civil */
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaExitoObservacion(cedula, "true",
						"El prestamo paso a estado ERC");
				super.guardarAuditoria(OperacionEnum.VERIFICACION_REGISTRO_CIVIL, parametroEvento, cedula);
				return "errorRegistroCivil";
			}
		} catch (RegistroCivilException e) {
			mensajeErrorRegCivil = e.getMessage();
			setDataVerificacionRC(true, false, isnovacion, false);
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			log.error("Trama registro civil para CI: " + cedula);
			log.error("--- Incio trama ---");
			log.error(tramarc);
			log.error("--- Fin trama ---");
			//mensajeErrorRegCivil = "Error al verificar informaci\u00f3n del registro civil.";
			mensajeErrorRegCivil = "Lo sentimos no se puede verificar su informaci\u00f3n, por favor intente m\u00E1s tarde.";			
			setDataVerificacionRC(true, false, isnovacion, false);
			/* Guardar log de consulta al WS del registro civil */
			ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaExitoObservacion(cedula, "false",
					"Error al verificar informaci\u00F3n del registro civil. " + e.getMessage());
			super.guardarAuditoria(OperacionEnum.VERIFICACION_REGISTRO_CIVIL, parametroEvento, cedula);
			return null;
		}
	}

	/**
	 * Metodo que fija las variables de verificacion RC.
	 * 
	 * @param errorrc
	 * @param datoscorrecto
	 * @param rcnovacion
	 * @param respuesta
	 */
	private void setDataVerificacionRC(boolean errorrc, boolean datoscorrecto, boolean rcnovacion, boolean respuesta) {
		datos.setErrorRC(errorrc);
		datos.setDatosCorrecto(datoscorrecto);
		/* Aplica para novaciones que deben ser fijadas como ERC */
		datos.setRegistroCivilNovacion(rcnovacion);
		datos.setRespuesta(respuesta);
	}

	public Date getConfirmaFechaExpedicion() {
		return confirmaFechaExpedicion;
	}

	public void setConfirmaFechaExpedicion(Date confirmaFechaExpedicion) {
		this.confirmaFechaExpedicion = confirmaFechaExpedicion;
	}

	public String getConfirmaCodigoDactilar() {
		return confirmaCodigoDactilar;
	}

	public void setConfirmaCodigoDactilar(String confirmaCodigoDactilar) {
		this.confirmaCodigoDactilar = confirmaCodigoDactilar;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public String getMensajeRegCivil() {
		return mensajeRegCivil;
	}

	public void setMensajeRegCivil(String mensajeRegCivil) {
		this.mensajeRegCivil = mensajeRegCivil;
	}

	public String getMensajeErrorRegCivil() {
		return mensajeErrorRegCivil;
	}

	public void setMensajeErrorRegCivil(String mensajeErrorRegCivil) {
		this.mensajeErrorRegCivil = mensajeErrorRegCivil;
	}

	/**
	 * @return the errorIngresoDatos
	 */
	public String getErrorIngresoDatos() {
		return errorIngresoDatos;
	}

	/**
	 * @return the errorConfirmacionDatos
	 */
	public String getErrorConfirmacionDatos() {
		return errorConfirmacionDatos;
	}

	public HtmlAjaxCommandButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(HtmlAjaxCommandButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public boolean isVerPopPup() {
		return verPopPup;
	}

	public void setVerPopPup(boolean verPopPup) {
		this.verPopPup = verPopPup;
	}

}