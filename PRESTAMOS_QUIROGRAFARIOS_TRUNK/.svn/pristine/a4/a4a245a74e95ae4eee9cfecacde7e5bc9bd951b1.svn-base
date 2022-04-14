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

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.dao.DatosPersonalesAfiliadoBiessDao;
import ec.fin.biess.creditos.pq.enumeracion.DiscapacitadosEnum;
import ec.fin.biess.creditos.pq.excepcion.AfiliadoDiscapacitadoException;
import ec.fin.biess.creditos.pq.excepcion.MSPBiessException;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.modelo.persistencia.DatosPersonalesAfiliadoBiess;
import ec.fin.biess.creditos.pq.servicio.ConsultaMSPDiscapacitadosServicio;
import ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio;
import ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicio;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * Clase que implementa los servicios de DatosPersonalesAfiliado.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Stateless
public class DatosPersonalesAfiliadoBiessServicioImpl implements DatosPersonalesAfiliadoBiessServicio {

	private static final transient LoggerBiess LOG = LoggerBiess.getLogger(DatosPersonalesAfiliadoBiessServicioImpl.class);

	@EJB
	DatosPersonalesAfiliadoBiessDao datosPersonalesAfiliadoBiessDao;

	@EJB
	RegistroCivilBiessServicio registroCivilServicio;

	@EJB
	ConsultaMSPDiscapacitadosServicio consultaMSPDiscapacitadosServicio;
	
	@EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;
	
	private String error = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoServicio#consultarPorCedula(java.lang.String)
	 */
        @Override
	public DatosPersonalesAfiliadoBiess consultarPorCedula(String cedula) {
		return datosPersonalesAfiliadoBiessDao.consultarPorCedula(cedula).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoServicio#consultarNumeroCargas(java.lang.String)
	 */
        @Override
	public Integer consultarNumeroCargas(String cedula) {
		try {
			Integer cargas = consultarPorCedula(cedula).getCargaFamiliar();
			return null == cargas ? new Integer(0) : cargas;
		} catch (Exception e) {
			LOG.error("Error al consultar cargas familiares. CI: " + cedula);
			return new Integer(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio#actualizarDiscapacitado(java.lang.String,
	 * java.lang.Boolean, java.lang.Boolean, java.lang.String, ec.fin.biess.enumeraciones.AplicativoEnum)
	 */
    @Override
	public void actualizarDiscapacitado(String cedula, Boolean discapacitadoOp, Boolean discapacitadoDb, String direccionIP,
			AplicativoEnum aplicativo) throws AfiliadoDiscapacitadoException, RegistroCivilBiessException {
		if (null != discapacitadoDb && discapacitadoDb == discapacitadoOp) {
			return;
		}
		/* Validar si el afiliado es realmente discapacitado */
		if (discapacitadoOp) {
			if (!validarDiscapacitado(cedula, direccionIP, aplicativo)) {
				throw new AfiliadoDiscapacitadoException("El afiliado no es considerado como discapacitado. CI: "
						+ cedula);
			}
		}
		/* Actualizar campo discapacitado en los datos personales de un afiliado. */
		try {
			if (1 > datosPersonalesAfiliadoBiessDao.actualizarDiscapacitado(cedula, discapacitadoOp)) {
				LOG.error("Error al actualizar informacion de discapacitado para el afiliado CI: " + cedula);
				throw new AfiliadoDiscapacitadoException(
						"Error al actualizar informacion de discapacitado para el afiliado CI: " + cedula);
			}
		} catch (Exception e) {
			LOG.error("Error al actualizar informacion de discapacitado para el afiliado CI: " + cedula, e);
			throw new AfiliadoDiscapacitadoException(
					"Error al actualizar informacion de discapacitado para el afiliado CI: " + cedula, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio#validarDiscapacitado(java.lang.String,
	 * java.lang.String, ec.fin.biess.enumeraciones.AplicativoEnum)
	 */
    @Override
	public boolean validarDiscapacitado(String cedula, String direccionIP, AplicativoEnum aplicativo) throws RegistroCivilBiessException {
		boolean result = false;
		// CGH 2014/05/22 Se valida si el usuario es descapacitado contra el servicio ESB del Ministerio de Salud
		// Publica MSP
		String exito = "";
		String observacion = "";
		try {
			result = consultaMSPDiscapacitadosServicio.consultarWSDisc(cedula, direccionIP);
			if (result){
				exito = "true";
				observacion = "El afiliado es discapacitado segun el MSP.";
			} else {
				exito = "true";
				observacion = "El afiliado no es discapacitado segun el MSP.";
			}
		} catch (MSPBiessException e) {
			LOG.error("No se pudo consultar en la SNAP el discapacitado", e);
			exito = "false";
			observacion = "1 - El afiliado no es considerado como discapacitado segun el MSP";
			
			result = validarDiscapacitadoRC(cedula, direccionIP, aplicativo);
		} finally {
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, exito, observacion);
			this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
		}
		// Si el MSP no devuelve info de discapacitado se verifica en el RC

		return result;
	}

	/**
	 * Valida condicion de discapacidad a traves del WS del RC.
	 * 
	 * @param cedula
	 * @param direccionIP
	 * @return boolean
	 * @throws RegistroCivilBiessException
	 */
	private boolean validarDiscapacitadoRC(String cedula, String direccionIP, AplicativoEnum aplicativo) throws RegistroCivilBiessException {
		final String campo = "CONDICION_CEDULADO";
		String condicion = registroCivilServicio.obtenerCampo(registroCivilServicio.consultarWS(cedula, null, direccionIP, aplicativo), campo);
		/* Comparar resultado WS con lista de discapacidades aceptadas como validas en PQ */
		for (DiscapacitadosEnum valor : DiscapacitadosEnum.values()) {
			if (valor.getId().compareToIgnoreCase(condicion.trim()) == 0) {
				ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, "true",
						"El afiliado es discapacitado segun el MSP.");
				this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
				return true;
			}
		}
		
		// Inicio auditoria
		ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(cedula, "false",
				"1 - El afiliado no es considerado como discapacitado segun el RC. CONDICION_CEDULADO: " + condicion);
		this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_DISCAPACITADOS, parametroEvento, direccionIP, cedula);
		// Fin auditoria

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio#consultarDiscapacitado(java.lang.String)
	 */
        @Override
	public Boolean consultarDiscapacitado(String cedula) {
		try {
			String discapacitado = consultarPorCedula(cedula).getDiscapacitado();
			if (null == discapacitado) {
				return null;
			}
			return discapacitado.trim().compareToIgnoreCase("SI") == 0 ? true : false;
		} catch (Exception e) {
			LOG.error("Error al consultar informacion discapacitado. CI: " + cedula);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio#actualizar(ec.fin.biess.creditos.pq.modelo
	 * .persistencia.DatosPersonalesAfiliadoBiess)
	 */
        @Override
	public void actualizar(DatosPersonalesAfiliadoBiess dpab) {
		datosPersonalesAfiliadoBiessDao.update(dpab);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio#nuevo(ec.fin.biess.creditos.pq.modelo.
	 * persistencia.DatosPersonalesAfiliadoBiess)
	 */
        @Override
	public void nuevo(DatosPersonalesAfiliadoBiess dpab) {
		datosPersonalesAfiliadoBiessDao.insert(dpab);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
	/**
	 * @see ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio#mantenerSesionActiva()
	 */
        @Override
	public void mantenerSesionActiva() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Mantiene activa la sesion entre cliente/servidor.");
		}
	}

}
