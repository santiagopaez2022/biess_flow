/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.fin.biess.creditos.pq.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.modelo.dto.ParamsReglasPrecalificacion;
import ec.fin.biess.creditos.pq.servicio.PrecalificacionListaObservadosServicioLocal;
import ec.fin.biess.listaobservados.client.mq.modelo.BusquedaIDVerificacionNewResp;
import ec.fin.biess.listaobservados.client.mq.modelo.ResultadosOFAC;
import ec.fin.biess.listaobservados.client.mq.service.ListaObservadosLocalService;
import ec.fin.biess.listaobservados.constant.ConstantesListaControl;
import ec.fin.biess.listaobservados.constant.ConstantesListaObservadosWS;
import ec.fin.biess.listaobservados.enumeration.EstadoBloqueoEnum;
import ec.fin.biess.listaobservados.enumeration.TipoListaEnum;
import ec.fin.biess.listaobservados.exception.BloqueoListaControlException;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.fin.biess.listaobservados.modelo.persistence.DetalleBloqueoListaControl;
import ec.fin.biess.listaobservados.modelo.persistence.ListaControl;
import ec.fin.biess.listaobservados.service.BloqueoListaControlServicioLocal;
import ec.fin.biess.listaobservados.service.ListaControlServicioLocal;
import ec.fin.biess.tools.integracion.excepciones.IntegracionMQExcepcion;
import ec.fin.biess.tools.integracion.excepciones.JAXBExcepcion;
import ec.fin.biess.tools.integracion.excepciones.ServicioESBExcepcion;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionListaObservadosException;
import ec.gov.iess.creditos.pq.util.Utilities;
import ec.gov.iess.creditos.pq.util.ValidadorPrecalificacion;

/**
 * Servicio Validar si un Afiliado/Refugiado esta en alguna lista de observados nacional o internacional.
 * 
 * @author Diego Iza.
 * 
 */
@Stateless
public class PrecalificacionListaObservadosServicioImpl implements PrecalificacionListaObservadosServicioLocal {

	private LoggerBiess log = LoggerBiess.getLogger(PrecalificacionListaObservadosServicioImpl.class);

	@EJB
	private BloqueoListaControlServicioLocal bloqueoListaControlServicio;

	@EJB
	private ListaControlServicioLocal listaControlServicio;

	@EJB(name = "ListaObservadosServiceImpl/local")
	private ListaObservadosLocalService listaObservadosService;

	/**
	 * @see ec.fin.biess.creditos.pq.servicio.PrecalificacionListaObservadosServicioLocal#obtenerPrecalificacionListaObservados
	 *      (ec.gov.iess.creditos.modelo.dto.Precalificacion)
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Precalificacion obtenerPrecalificacionListaObservados(Precalificacion precalificacion)
			throws PrecalificacionListaObservadosException {

		if (precalificacion == null) {
			precalificacion = new Precalificacion();
			precalificacion.setCalificado(true);
			precalificacion.setRequisitos(new ArrayList<Requisito>());
		}

		// Fijar parametros reglas precalificacion.
		ParamsReglasPrecalificacion parametros = new ParamsReglasPrecalificacion();

		// Verificar si esta en lista de observados.
		this.validarListaObservados(precalificacion);

		// Agrega para evaluar la lista de requisitos
		parametros.setTipoListaControl(precalificacion.getTipoListaControl());

		// Ejecucion de las reglas del negocio.
		try {
			ValidadorPrecalificacion.ejecutarReglasJavaListaObservados(precalificacion, parametros);
		} catch (PrecalificacionExcepcion e) {
			throw new PrecalificacionListaObservadosException(
					"ERROR AL DETERMINAR LAS REGLAS DE CALIFICACION EN LISTA DE OBSERVADOS", e);
		}

		return precalificacion;

	}

	/**
	 * Valida si un Afiliado/Refugiado consta en alguna lista de observados nacionales o internacionales.
	 * 
	 * @param precalificacion
	 *            - datos de la precalificacion.
	 * 
	 * @throws PrecalificacionListaObservadosException
	 *             - excepcion.
	 */
	private void validarListaObservados(Precalificacion precalificacion) throws PrecalificacionListaObservadosException {
		// Verificar si esta bloqueado.
		BloqueoListaControl bloqueoListaControl = this.verificarListaObservados(precalificacion);

		// GENERAR UNA RESPUESTA ADECUADA SEGUN LA LISTA DE OBSERVADOS EN LA QUE SE ENCUENTRA.
		if (bloqueoListaControl != null) {
			if (bloqueoListaControl.getListaDetalles() != null && !bloqueoListaControl.getListaDetalles().isEmpty()) {

				HashMap<String, String> tiposListaControl = new HashMap<String, String>();
				precalificacion.setEnListaObservados(false);
				precalificacion.setEnListaObservadosCONSEP(false);
				precalificacion.setEnListaObservadosOTROS(false);
				precalificacion.setEnListaObservadosPEP(false);
				precalificacion.setFecharegistroListaObservados(bloqueoListaControl.getFechaRegistro());

				for (DetalleBloqueoListaControl detalle : bloqueoListaControl.getListaDetalles()) {
					if (!tiposListaControl.containsKey(detalle.getListaControl().getTipoLista())) {
						tiposListaControl.put(detalle.getListaControl().getTipoLista(), detalle.getListaControl()
								.getTipoLista());
					}
				}
				if (!tiposListaControl.isEmpty()) {
					if (tiposListaControl.containsKey(TipoListaEnum.OTRO.toString())) {
						precalificacion.setTipoListaControl(tiposListaControl.get(TipoListaEnum.OTRO.toString()));
						precalificacion.setEnListaObservadosOTROS(true);
						precalificacion.setEnListaObservados(true);
						return;
					}
					if (tiposListaControl.containsKey(TipoListaEnum.CONSEP.toString())) {
						precalificacion.setTipoListaControl(tiposListaControl.get(TipoListaEnum.CONSEP.toString()));
						precalificacion.setEnListaObservadosCONSEP(true);
						precalificacion.setEnListaObservados(true);
						return;
					}
					if (tiposListaControl.containsKey(TipoListaEnum.PEP.toString())) {
						precalificacion.setTipoListaControl(tiposListaControl.get(TipoListaEnum.PEP.toString()));
						precalificacion.setEnListaObservadosPEP(true);
						precalificacion.setBloqueoListaControl(bloqueoListaControl);
						return;
					}
				}
			}
		}
	}

	/**
	 * Verifica si un Afiliado/Refugiado consta en alguna lista de observados nacionales o internacionales.
	 * 
	 * @param precalificacion
	 *            - datos de la precalificacion.
	 * 
	 * @return BloqueoListaControl
	 * @throws PrecalificacionListaObservadosException
	 *             - excepcion.
	 */
	private BloqueoListaControl verificarListaObservados(Precalificacion precalificacion)
			throws PrecalificacionListaObservadosException {

		BloqueoListaControl bloqueoListaControl = null;

		// Verificar si esta bloqueado en la BDD Local.
		try {
			bloqueoListaControl = bloqueoListaControlServicio.obtenerBloqueo(precalificacion
					.getValidarRequisitosPrecalificacion().getCedula(), EstadoBloqueoEnum.S, "PQ");
		} catch (BloqueoListaControlException e) {
			throw new PrecalificacionListaObservadosException(
					"No se pudo verificar la información en la lista de observados.", e);
		}

		if (bloqueoListaControl == null || bloqueoListaControl.getIdBloqueo() == null) {
			// VERIFICAR CONTRA EL SERVICIO WEB
			bloqueoListaControl = this.verificarListaObservadosWS(precalificacion);
			// Registra el historico de bloqueos.
			this.registrarBloqueoListaObservados(bloqueoListaControl);
		} else {
			Date fechaActual = new Date();
			boolean bloqueoCaducado = false;

			int numeroDias = Utilities.calcurarNuemroDias(fechaActual, bloqueoListaControl.getFechaRegistro());
			if (numeroDias > 7) {
				bloqueoCaducado = true;
			}

			if (bloqueoCaducado) {
				// Cancela el registro existente
				bloqueoListaControl.setUsuarioActualizacion(precalificacion.getIdUsuarioLogueado());
				bloqueoListaControl.setIpActualizacion(precalificacion.getIpUsuarioLogueado());
				bloqueoListaControl.setFechaDesbloqueo(new Date());
				bloqueoListaControl.setFechaActualizacion(new Date());
				bloqueoListaControl.setObservacionDesbloqueo(ConstantesListaControl.MENSAJE_DESBLOQUEO_AUTOMATICO);
				this.anularBloqueoListaObservados(bloqueoListaControl);

				// VERIFICAR CONTRA EL SERVICIO WEB
				bloqueoListaControl = this.verificarListaObservadosWS(precalificacion);
				// Registra el historico de bloqueos.
				this.registrarBloqueoListaObservados(bloqueoListaControl);
			}
		}

		return bloqueoListaControl;
	}

	/**
	 * Verifica si un Afiliado/Refugiado consta en alguna lista de observados nacionales o internacionales, contra el
	 * servicio web.
	 * 
	 * JULIO-2021 Se modifica . Se llama al nuevo metodo de WS Lista Observados
	 * @param precalificacion
	 *            - datos de la precalificacion.
	 * 
	 * @return BloqueoListaControl
	 * @throws PrecalificacionListaObservadosException
	 *             - excepcion.
	 */
	private BloqueoListaControl verificarListaObservadosWS(Precalificacion precalificacion)
			throws PrecalificacionListaObservadosException {

		BloqueoListaControl bloqueoListaControl = null;
		String cedula = precalificacion.getValidarRequisitosPrecalificacion().getSolicitante().getDatosPersonales()
				.getCedula();
		String nombresApellidos = precalificacion.getValidarRequisitosPrecalificacion().getSolicitante()
				.getDatosPersonales().getApellidosNombres();

		if (log.isDebugEnabled()) {
			log.info(" verificarListaObservadosWS:");
			log.info(" Cedula           :: " + cedula);
			log.info(" NombresApellidos :: " + nombresApellidos);
		}

		try {

			if (log.isDebugEnabled()) {
				log.info("verificarListaObservadosWS ************************ ingresando");
			}
			//REQ 407 Usar Nuevo Método verificarListasObservadosNew RMJ
			BusquedaIDVerificacionNewResp resp = listaObservadosService.verificarListasObservadosNew(
					ConstantesListaObservadosWS.WS_CLIENTEF, nombresApellidos, cedula,
					ConstantesListaObservadosWS.WS_ID_POLITICA, ConstantesListaObservadosWS.WS_LICENCIA_EMPRESA,
					nombresApellidos, null, ConstantesListaObservadosWS.WS_TIPO_IDENTIFICACION_CEDULA,
					ConstantesListaObservadosWS.WS_TIPO_ORGANIZACION);
			
			if (log.isDebugEnabled()) {
				log.info("verificarListaObservadosWS ************************ finalizando");
			}

			if (resp == null) {
				throw new PrecalificacionListaObservadosException("PROBLEMAS AL CONSUMIR EL SERVICIO WEB DE LISTA DE OBSERVADOS -verificarListasObservados-");
			}

			if (resp.getControlRes() != null && resp.getControlRes().getCodErr() != null) {
				if (resp.getControlRes().getCodErr().equalsIgnoreCase("0")) {
					if (resp.getResultadosRevisionOFACSwift() == null
							|| resp.getResultadosRevisionOFACSwift().getResultadosOFAC() == null
							|| resp.getResultadosRevisionOFACSwift().getResultadosOFAC().isEmpty()) {
						return bloqueoListaControl;
					}
				} else {
					throw new PrecalificacionListaObservadosException("PROBLEMAS AL CONSUMIR EL SERVICIO WEB DE LISTA DE OBSERVADOS -verificarListasObservados-:: "
							+ resp.getControlRes().getCodErr() + ",  " + resp.getControlRes().getDesErr());
				}
			}

			// Generar el Bloqueo
			bloqueoListaControl = new BloqueoListaControl();
			bloqueoListaControl.setAplicativo("PQ");
			bloqueoListaControl.setCedula(cedula);
			bloqueoListaControl.setNombresApellidos(nombresApellidos);
			bloqueoListaControl.setFechaRegistro(new Date());
			bloqueoListaControl.setUsuarioRegistro(precalificacion.getIdUsuarioLogueado());
			bloqueoListaControl.setIpIngreso(precalificacion.getIpUsuarioLogueado());

			List<DetalleBloqueoListaControl> detalles = new ArrayList<DetalleBloqueoListaControl>();
			boolean isPeps = true;
			for (ResultadosOFAC resultado : resp.getResultadosRevisionOFACSwift().getResultadosOFAC()) {
				// Cargar los detalles
				ListaControl listaControl = this.listaControlServicio.obtenerPorNombre(resultado.getFile());
				String observacion = "";
				if (listaControl == null) {
					listaControl = this.listaControlServicio.obtenerPorNombre(ConstantesListaControl.NO_DEFINIDO);
					observacion = resultado.getFile();
				}
				// Generar Detalle
				DetalleBloqueoListaControl detalle = new DetalleBloqueoListaControl();
				detalle.setBloqueoListaControl(bloqueoListaControl);
				detalle.setListaControl(listaControl);
				detalle.setFechaRegistro(new Date());
				detalle.setObservacion(observacion);

				detalles.add(detalle);

				if (listaControl.getTipoLista().equalsIgnoreCase(TipoListaEnum.CONSEP.toString())
						|| listaControl.getTipoLista().equalsIgnoreCase(TipoListaEnum.OTRO.toString())) {
					isPeps = false;
				}
			}

			// Segun el tipo de lista de observados permite o no generar un credito.
			if (isPeps) {
				bloqueoListaControl.setObservacionDesbloqueo(ConstantesListaControl.MENSAJE_REGISTRO_AUTOMATICO);
				bloqueoListaControl.setBloqueado(EstadoBloqueoEnum.N);
			} else {
				bloqueoListaControl.setObservacionDesbloqueo(ConstantesListaControl.MENSAJE_BLOQUEO_AUTOMATICO);
				bloqueoListaControl.setBloqueado(EstadoBloqueoEnum.S);
			}
			// Cargar los detalles
			bloqueoListaControl.setListaDetalles(detalles);
		} catch (IntegracionMQExcepcion e) {
			throw new PrecalificacionListaObservadosException(e);
		} catch (JAXBExcepcion e) {
			throw new PrecalificacionListaObservadosException(e);
		} catch (ServicioESBExcepcion e) {
			throw new PrecalificacionListaObservadosException(e);
		} catch (Exception e) {
			throw new PrecalificacionListaObservadosException(e);
		}

		return bloqueoListaControl;
	}

	/**
	 * Registra el nuevo bloqueo.
	 * 
	 * @param bloqueoListaControl
	 *            - datos del bloqueo.
	 * 
	 * @throws PrecalificacionListaObservadosException
	 *             - excepcion.
	 */
	private void registrarBloqueoListaObservados(BloqueoListaControl bloqueoListaControl) throws PrecalificacionListaObservadosException {
		// Valida que no este nulo.
		if (bloqueoListaControl == null || bloqueoListaControl.getListaDetalles() == null
				|| bloqueoListaControl.getListaDetalles().isEmpty()) {
			return;
		}

		try {
			bloqueoListaControl = bloqueoListaControlServicio.registrar(bloqueoListaControl);
		} catch (BloqueoListaControlException e) {
			throw new PrecalificacionListaObservadosException(e);
		}
	}

	/**
	 * Anula un bloqueo.
	 * 
	 * @param bloqueoListaControl
	 *            - datos del bloqueo.
	 * 
	 * @throws PrecalificacionListaObservadosException
	 *             - excepcion.
	 */
	private void anularBloqueoListaObservados(BloqueoListaControl bloqueoListaControl)
			throws PrecalificacionListaObservadosException {
		try {
			bloqueoListaControl.setBloqueado(EstadoBloqueoEnum.N);
			bloqueoListaControl.setFechaActualizacion(new Date());
			bloqueoListaControl = bloqueoListaControlServicio.actualizar(bloqueoListaControl);
		} catch (BloqueoListaControlException e) {
			throw new PrecalificacionListaObservadosException(e);
		}
	}
	}