/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.handler;

import javax.ejb.EJB;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.hl.servicio.RegistroCivilServicio;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * EstadoMenuBean.java
 * 
 * Bean para verificar los roles de un funcionario
 * 
 * @version 1.0
 * @creacion 04/03/2010
 * @modificacion 04/03/2010
 * @author Daniel Cardenas
 * @author William Valencia
 */
public class FuncionarioHandler extends BaseBean {

	@EJB(name = "RegistroCivilServicioImpl/local")
	private RegistroCivilServicio registroCivil;

	private static final LoggerBiess log = LoggerBiess.getLogger(FuncionarioHandler.class);

	private String cedula;
	private String nombreCompleto;

	public FuncionarioHandler() {
	}

	public String getCedula() {
		cedula = super.getHttpServletRequest().getRemoteUser();
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreCompleto() {
		if (nombreCompleto == null) {
			try {
				nombreCompleto = registroCivil.consultarRegistroCivil(this.getCedula()).getNomper();
			} catch (Exception e) {

				log.error("Error al consultar el nombfre usuario : " + e.getMessage());
				setNombreCompleto("ERROR AL CONSULTAR NOMBRE DE USUARIO");

			}
		}
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreFuncionario) {
		this.nombreCompleto = nombreFuncionario;
	}

}