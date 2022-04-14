package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.modelo.dto.ValidarRequisitosPrecalificacionBiess;
import ec.fin.biess.creditos.pq.servicio.InformacionIessServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosFondos;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.servicio.ConsultaBuroServicio;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.PrecalificacionUtil;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.DireccionIPUtil;
import ec.gov.iess.pq.concesion.web.util.MensajesUtil;

public class ResultadoPrecalificacionNovacionBean extends BaseBean implements
		Serializable {

	private static final long serialVersionUID = 8996184511719272885L;

	// log4j
	private static final LoggerBiess log = LoggerBiess
			.getLogger(ResultadoPrecalificacionNovacionBean.class);

	@EJB(name = "PrecalificacionServicioUsaResumenImpl/local")
	private PrecalificacionServicio precalificacionServicio;

	@EJB(name = "ConsultaBuroServicioImpl/local")
	private ConsultaBuroServicio consultaBuroServicio;
	
	@EJB(name = "CreditoPQOpEmplSacServicioImpl/local")
	private CreditoPQEmpSacServicioLocal creditoPqClientesEmpl;
	
	@EJB(name = "GarantiasSacServicioImpl/local")
	private GarantiasSacServicioLocal garantiasSAC;
	
	// propiedades del componente

	private Precalificacion precalificacion;

	private DatosBean datos;

	private boolean cargado = false;// indica si ya se ha cargado los requisitos

	private boolean cargadoGarantias = false;

	private boolean cargadoBuro = false;

	private Garantia garantia;
	
	@EJB(name = "InformacionIessServicioImpl/local")
	private InformacionIessServicio informacionIessServicio;

	private String codigoNotificacion;

	public void setPrecalificacion(Precalificacion precalificacion) {
		this.precalificacion = precalificacion;
	}

	public Precalificacion getPrecalificacion() {
		return precalificacion;
	}

	public boolean isCargado() {
		return cargado;
	}

	public void setCargado(boolean cargado) {
		this.cargado = cargado;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	private List<String> obtenerTiposCargos() {
		List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_CARGO_PRO);
		tipos.add(TiposCredito.TIPO_CARGO_REG);
		return tipos;
	}

	private List<String> obtenerEstadosSolicitud() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_FONDOS_AFP);
		estados.add(EstadosCredito.ESTADO_FONDOS_AFT);
		estados.add(EstadosCredito.ESTADO_FONDOS_ANE);
		estados.add(EstadosCredito.ESTADO_FONDOS_GUI);
		estados.add(EstadosCredito.ESTADO_FONDOS_REG);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA);
		return estados;
	}

	// acciones de la aplicacion

	public String cargarPrecalificacion() throws ParametroCreditoException {
		// Inicializo objetos
		datos.setDatosGarantia(null);
		datos.setGarantia(null);
		datos.setValReqPrecalificacion(null);
		precalificacion = null;

		if (datos.getSolicitante() == null) {
			log.warn("se ha perdido valores de sesion en precalificacion");
			return "errorSesionVacia";
		}

		CreditoAccionBean creditoAccionBean = (CreditoAccionBean) getSession()
				.getAttribute("creditoAccion");
		Prestamo prestamoNovacion = creditoAccionBean
				.getPrestamoSeleccionadoNovacion();

		if (precalificacion == null) {
			TipoPrestamista tipoPrestamista = datos.getTipo();
			String cedula = datos.getSolicitante().getDatosPersonales()
					.getCedula();

			ValidarRequisitosFondos validarFondos = new ValidarRequisitosFondos();
			validarFondos.setCedula(cedula);
			validarFondos.setTiposCargos(obtenerTiposCargos());
			validarFondos
					.setEstadoBloqueado(EstadosCredito.ESTADO_BLOQUEO_CUENTA);
			validarFondos.setEstadosSolicitud(obtenerEstadosSolicitud());
			validarFondos.setNovacion(true);

			DatosGarantia datosGarantia = new DatosGarantia();
			datosGarantia.setCedula(cedula);
			datosGarantia.setFechaSolicitud(new Date());
			datosGarantia.setTipoPrestamista(tipoPrestamista);
			datosGarantia.setValReqFondos(validarFondos);

			// if (null != datos.getSolicitante()) {
			// obtenerCuotaMensualBuroDeCredito();
			// }
			// GE: paso el parametro para el calculo de la garantia
			// CB: eliminación buro de credito
			// datosGarantia.setCuotaMensualBuro(datos.getCuotaEstimadaMensualBuro());
			datosGarantia.setCuotaMensualBuro(BigDecimal.ZERO);
			datos.setCuotaEstimadaMensualBuro(BigDecimal.ZERO);
			cargadoBuro = true;
			// Paso el credito de novacion a las garantias
			datosGarantia.setPrestamoVigNovacion(prestamoNovacion);
			datos.setDatosGarantia(datosGarantia);

			ValidarRequisitosPrecalificacionBiess reqPrecalificacion = new ValidarRequisitosPrecalificacionBiess();
			reqPrecalificacion.setCedula(cedula);
			reqPrecalificacion.setTipoPrestamista(tipoPrestamista);
			reqPrecalificacion
					.setEstadoCreditoCuentaBancaria(PrecalificacionUtil.obtenerEstadosCuentaBancaria());
			reqPrecalificacion.setEstadoCreditoHl(PrecalificacionUtil.obtenerEstadosHl());
			reqPrecalificacion.setEstadoSolicitudHlPH(PrecalificacionUtil.obtenerEstadosHlPH());
			reqPrecalificacion.setCodEstSolSerList(PrecalificacionUtil.obtenerEstadosSolicitudPH());
			reqPrecalificacion.setCodTipSolSerList(PrecalificacionUtil.obtenerTiposSolicitudPH());
			reqPrecalificacion.setSolicitante(datos.getSolicitante());
			reqPrecalificacion.setNovacion(true);
			reqPrecalificacion.setPrestamoVigenteNovacion(prestamoNovacion);
			/* Fijo informacion de discapacitado */
			reqPrecalificacion.setDiscapacitado(datos.getDiscapacitado());
			
			if (datos.isProductoBiessEmergente()) {
				datosGarantia.setIndicaCreditoEmergente(true);
			} else {
				datosGarantia.setIndicaCreditoEmergente(false);
			}
			
			datosGarantia.setFechaNacimiento(this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento());
			datosGarantia.setPlazo(new BigDecimal(this.datos.getPlazo()));
			datosGarantia.setFechaNacimiento(this.datos.getSolicitante().getDatosPersonales().getFechaNacimiento());
			reqPrecalificacion.setGarantia(datosGarantia);
			try {
				BigDecimal saldoPrestamoNovar = BigDecimal.ZERO;
				//Tomo el saldo del prestamo a novar del cargado del SAC el monto de precancelacion
				if (datos.isNovacionCredito()
						&& datos.getPrestamoNovacionSeleccionado() != null) {
					saldoPrestamoNovar =BigDecimal.valueOf(datos.getPrestamoNovacionSeleccionado().getValliqnov());
				}
				// INC-2135 Pensiones Jubilados/Pensionistas
				if (TipoPrestamista.JUBILADO.equals(reqPrecalificacion.getTipoPrestamista())) {
					// Si es jubilado solo necesitoa prestaciones
					this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
							TipoInformacionServicioIessEnum.PRESTACIONES));
				} else {
					this.datos.setInformacionIessServicio(this.informacionIessServicio.obtenerInformacion(this.datos.getInformacionIessServicio(),
							TipoInformacionServicioIessEnum.APORTES_MORA_CESANTIAS));
					
					
						 this.datos.setInformacionGarantia(this.datos.getInformacionGarantia()==null?garantiasSAC.obtenerGarantias(getHttpServletRequest().getRemoteUser()):this.datos.getInformacionGarantia());
						
				}
				if(datos.getInfoPqExigile()==null) {
					datos.setInfoPqExigile(creditoPqClientesEmpl.obtenerInfoPqOperEmp(getHttpServletRequest().getRemoteUser()));
				}
				
				precalificacion = precalificacionServicio.obtenerPrecalificacion(reqPrecalificacion, saldoPrestamoNovar, datos.getRolPrestamista(),
						this.datos.getInformacionIessServicio(), this.datos.isPagoPensionesAlimenticias(),
						datos.isProductoBiessEmergente(),datos.getInfoPqExigile(),this.datos.getInformacionGarantia());
				precalificacion.setIdUsuarioLogueado(getHttpServletRequest().getRemoteUser());
				precalificacion.setIpUsuarioLogueado(DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()));
				log.debug("termina de instanciar");
				// la lista de requisitos ya se ha cargado
				cargado = true;
				log.debug("total requisitos:"
						+ precalificacion.getRequisitos().size());
				log.debug("aprueba?" + precalificacion.isCalificado());
				
				// Inicio - Informacion auditoria (1)
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosRequisitosPrecalificacion(
						precalificacion.getIdUsuarioLogueado(), precalificacion.isCalificado() ? "true" : "false");
				// Fin - Informacion auditoria (1)
				
				for (Requisito requisitoDato : precalificacion.getRequisitos()) {
					// Inicio - Informacion auditoria (2)
					ParametroEvento requisito = new ParametroEvento();
					requisito.setNombre("requisito");
					requisito.addVP("regla", requisitoDato.getDescripcion().replaceAll("\"", new String()))
							.addVP("resultado", requisitoDato.getObservacion()).addVP("aprueba", requisitoDato.isAprobado() ? "true" : "false");
					parametroEvento.getDetalle().add(requisito);
					// Fin - Informacion auditoria (2)
				}
				
				// Inicio - Informacion auditoria (3)
				super.guardarAuditoria(OperacionEnum.REQUISITOS_PRECALIFICACION_NOVACION, parametroEvento, precalificacion.getIdUsuarioLogueado());
				// Fin - Informacion auditoria (3)

				// GE: guarda en sesion estado aprobado y verifica que haya
				// conexion al buró
				if (precalificacion.isCalificado() && cargadoBuro) {
					datos.getSolicitante().setAprobado(true);
				} else {
					datos.getSolicitante().setAprobado(false);
				}

				log.debug("cargando garantias.");
				garantia = precalificacion.getGarantia();
				datos.setGarantia(garantia);

				cargadoGarantias = true;

                //almaceno la precalificacion en datos MIGRACION CARTERA
				
				this.datos.setPrecalificacion(precalificacion);

			} catch (PrecalificacionExcepcion e) {
				String mensaje = MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(), "credito.error.aplicativo");
				log.error(e);
				FacesMessage message = new FacesMessage();
				message.setDetail(mensaje);
				message.setSummary(mensaje);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				context().addMessage(null, message);
			} catch (PQExigibleException e) {
				String mensaje=e.getCodigo() + ", " + e.getMensaje();
				FacesMessage message = new FacesMessage();
				message.setDetail(mensaje);
				message.setSummary(mensaje);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				context().addMessage(null, message);
			} catch (GarantiasSacException e) {
				String mensaje = e.getCodigo() + ", " + e.getMensaje();
				FacesMessage message = new FacesMessage();
				message.setDetail(mensaje);
				message.setSummary(mensaje);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				context().addMessage(null, message);
			}
		}
		return null;// se queda en la mismaf
	}


	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public boolean isCargadoGarantias() {
		return cargadoGarantias;
	}

	public void setCargadoGarantias(boolean cargadoGarantias) {
		this.cargadoGarantias = cargadoGarantias;
	}

	/**
	 * <b> Obtiene el valor de endeudamiento de un Jubilado. </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 12/04/2011]
	 * </p>
	 * 
	 */
	private void obtenerCuotaMensualBuroDeCredito() {
		BigDecimal cuotaMensual = BigDecimal.ZERO;

		try {
			if (datos.getSolicitante().getTipo() == TipoPrestamista.JUBILADO) {
				cuotaMensual = consultaBuroServicio.getCuotaMensual(datos
						.getSolicitante().getDatosPersonales().getCedula());
			}
			cargadoBuro = true;
		} catch (Exception e) {
			log.error(e);
			FacesMessage message = new FacesMessage();
			message.setDetail("Problemas con la conexi\u00F3n al obtener los datos de bur\u00F3 de cr\u00E9dito. Por favor intente m\u00E1s tarde");
			message.setSummary("Problemas con la conexi\u00F3n al obtener los datos de bur\u00F3 de cr\u00E9dito. Por favor intente m\u00E1s tarde");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context().addMessage(null, message);
			cargadoBuro = false;
			cargadoGarantias = false;
		}
		datos.setCuotaEstimadaMensualBuro(cuotaMensual);
		log.info("valor cuota del Buro de VT: " + cuotaMensual);
	}

	/**
	 * @return the cargadoBuro
	 */
	public boolean isCargadoBuro() {
		return cargadoBuro;
	}

	/**
	 * @param cargadoBuro
	 *            the cargadoBuro to set
	 */
	public void setCargadoBuro(boolean cargadoBuro) {
		this.cargadoBuro = cargadoBuro;
	}

	/**
	 * @return the codigoNotificacion
	 */
	public String getCodigoNotificacion() {
		return codigoNotificacion;
	}

	/**
	 * @param codigoNotificacion the codigoNotificacion to set
	 */
	public void setCodigoNotificacion(String codigoNotificacion) {
		this.codigoNotificacion = codigoNotificacion;
	}

}