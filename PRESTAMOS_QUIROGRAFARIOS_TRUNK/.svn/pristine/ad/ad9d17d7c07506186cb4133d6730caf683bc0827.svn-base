/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.servicio.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.security.SecurityDomain;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.dao.AfiliadoDaoBiess;
import ec.fin.biess.creditos.pq.excepcion.AfiliadoExceptionBiess;
import ec.fin.biess.creditos.pq.excepcion.EmailException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.servicio.AfiliadoServicioBiess;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.gov.iess.hl.dao.AfiliadoDao;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.exception.DivisionPoliticaException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;

/**
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Stateless
@SecurityDomain(value = "iessSecurity")
public class AfiliadoServicioBiessImpl implements AfiliadoServicioBiess {
	
	@EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;

	@EJB
	AfiliadoDaoBiess afiliadoDaoBiess;
	
	@EJB
	AfiliadoServicio afiliadoServicio;

	@EJB
	DivisionPoliticaServicio divisionPoliticaServicio;
	
	@EJB
	AfiliadoDao afiliadoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.AfiliadoServicioBiess#actualizarDatosAfiliado(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean,
	 * java.util.Date, java.lang.String)
	 */
	public void actualizarDatosAfiliado(String cedula, String divisionPolitica, String direccion, String telefonoDomicilio, String numeroCelular, String email,
			Boolean poseeVivienda, Date fechaActualizacionDatosDomicilio, String ipUser) throws AfiliadoExceptionBiess {
		/* Verificar si el afiliado existe */
		Afiliado afiliado;
		String exito = "";
		String observacion = "";
		
		try {
			afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);
		} catch (AfiliadoException e) {
			exito = "false";
			observacion = "Error en la actualizacion: " + e.getMessage();
			throw new AfiliadoExceptionBiess("Error al obtener datos del afiliado de la tabla kspcotafiliados. CI: " + cedula, e);
		}
		if (null == afiliado) {
			throw new AfiliadoExceptionBiess("Afiliado no existe. CI: " + cedula);
		}
		/* Verificar si la division politica existe */
		DivisionPolitica divPolitica;
		try {
			divPolitica = divisionPoliticaServicio.consultaDivisionPoliticaPorId(divisionPolitica);
		} catch (DivisionPoliticaException e) {
			exito = "false";
			observacion = "Error en la actualizacion: " + e.getMessage();
			throw new AfiliadoExceptionBiess("Error al obtener division politica del afiliado. CI: " + cedula + ". CODDIVPOL: " + divisionPolitica, e);
		}
		if (null == divPolitica) {
			exito = "false";
			observacion = "Error en la actualizacion: " + "Divisi\u00F3n pol\u00EDtica no existe. ID: " + divisionPolitica;
			throw new AfiliadoExceptionBiess("Divisi\u00F3n pol\u00EDtica no existe. ID: " + divisionPolitica);
		}
		/*
		 * Fijar nuevo codigo de division politica. Fijar Codigo de extranjero (dpcodigo) a null debido a que solo se puede actualizar coddivpol si este es null (TRIGGER:
		 * KSPCOGBUXXAFILIADOS)
		 */
		String coddivpol = divPolitica.getCoddivpol();
		String dpcodigo = null;
		/* Fijar bandera de posee vivienda */
		String posviv = null;
		if (poseeVivienda != null) {
			posviv = poseeVivienda.booleanValue() ? "1" : "0";
		}
		/* Actualizar los datos del afiliado */
		try {
			afiliadoDaoBiess.actualizarDatosAfiliado(cedula, coddivpol, direccion, telefonoDomicilio, numeroCelular, email, posviv, fechaActualizacionDatosDomicilio, dpcodigo);
			exito = "true";
			observacion = "Actualizacion correcta";
		} catch (Exception e) {
			exito = "false";
			observacion = "Error en la actualizacion: " + e.getMessage();
			throw new AfiliadoExceptionBiess("Error al actualizar datos del afiliado. CI: " + cedula);
		} finally {
			// Inicio auditoria
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosActualizaDatos(cedula, exito, observacion);
			this.servicioAuditoria.guardarAuditoria(OperacionEnum.ACTUALIZA_DATOS_AFILIADO, parametroEvento, ipUser, cedula);
			// Fin auditoria
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.AfiliadoServicioBiess#reActualizarDatosAfiliado(ec.gov.iess.hl.modelo.Afiliado,
	 * java.lang.String)
	 */
	public void reActualizarDatosAfiliado(Afiliado afiliado, String ipUser) throws EmailException {
		String exito = "";
		String observacion = "";
		try {
			afiliadoDao.update(afiliado);
			exito = "true";
			observacion = "Actualizacion correcta";
		} catch (Exception e) {
			exito = "false";
			observacion = "Error en la actualizacion: " + e.getMessage();
			throw new EmailException(e);
		} finally {
			// Inicio auditoria
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosActualizaDatos(afiliado.getCedulaAfiliado(), exito, observacion);
			this.servicioAuditoria.guardarAuditoria(OperacionEnum.ACTUALIZA_DATOS_AFILIADO, parametroEvento, ipUser, afiliado.getCedulaAfiliado());
			// Fin auditoria
		}
	}
	
}
