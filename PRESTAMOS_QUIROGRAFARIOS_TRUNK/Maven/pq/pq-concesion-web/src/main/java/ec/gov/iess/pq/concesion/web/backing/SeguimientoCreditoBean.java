package ec.gov.iess.pq.concesion.web.backing;

import java.io.StringWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.alertas.util.AlertUtil;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.listaobservados.exception.BloqueoListaControlException;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.fin.biess.listaobservados.service.BloqueoListaControlServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dinamico.dto.DataProductoDinResponseDto;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.excepcion.PrestamoPqCoreException;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCredito;
import ec.gov.iess.creditos.modelo.persistencia.DetalleFocalizado;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoEstadoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;
import ec.gov.iess.creditos.pq.excepcion.ConecSrvPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ConsultaDataPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.OTPException;
import ec.gov.iess.creditos.pq.excepcion.PrestamosTuristicosException;
import ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoValidacionServicioLocal;
import ec.gov.iess.creditos.pq.servicio.OTPServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrestamoDinamicoLocalService;
import ec.gov.iess.creditos.pq.servicio.PrestamoEstadoHistoricoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoTuristicoLocalService;
import ec.gov.iess.hl.exception.DivisionPoliticaException;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.bean.DetallePrestamoBean;
import ec.gov.iess.pq.concesion.web.bean.DetalleSeguimientoCreditoBean;
import ec.gov.iess.pq.concesion.web.enumeration.ParametrosGeneralesEnum;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.EncriptacionUtil;
import ec.gov.iess.pq.concesion.web.util.Utilities;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/*
 * Clase para hacer el seguimiento del prestamo
 * @author rtituana
 */
public class SeguimientoCreditoBean extends BaseBean {

	private transient static final LoggerBiess log = LoggerBiess.getLogger(SeguimientoCreditoBean.class);

	private Boolean seleccionado=false;

	private Boolean algunoSeleccionado=false;

	private Prestamo prestamoSeleccionado;

	private DetallePrestamoBean detallePrestamo;

	private String msjError = null;

	private DatosBean datos;

	private List<DetalleSeguimientoCreditoBean> detalleSeguimientoCreditoBeanList;

	private String msjCreditoListaObservadosPep ;

	@EJB(name = "PrestamoDinamicoImpl/local")
	private PrestamoDinamicoLocalService prestamoDinamico;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;

	@EJB(name = "PrestamoEstadoHistoricoServicioImpl/local")
	private PrestamoEstadoHistoricoServicio prestamoEstadoHistoricoServicio;

	// Reimpresion de FAT.

	@EJB(name = "BeneficiarioCreditoServicioImpl/local")
	private BeneficiarioCreditoServicio beneficiarioCreditoServicio;

	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionPoliticaServicio;

	@EJB(name = "ProveedorServicioImpl/local")
	private ProveedorServicio proveedorServicio;

	@EJB(name = "CreditoValidacionServicioImpl/local")
	private CreditoValidacionServicioLocal creditoValidacionServicio;

	@EJB(name = "CatalogoFocalizadosServicioImpl/local")
	private CatalogoFocalizadosServicioLocal catalogoFocalizadosServicio;

	@EJB(name = "CreditoValidacionServicioImpl/local")
	private transient CreditoValidacionServicioLocal credCreditoValidacionServicio;

	@EJB(name = "OTPServicioImpl/local")
	private transient OTPServicioLocal otpServicio;

	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;

	@EJB(name = "PrestamoTuristicoServiceImpl/local")
	private PrestamoTuristicoLocalService prestamoTuristicoServicio;

	private boolean desplegarDetalle = false;

	private String textoFAT;

	private boolean desplegarFAT = false;

	private int diasCaducidad;

	private String estiloPanelInfoError = "messagePanel";

	// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
	@EJB(name = "BloqueoListaControlServicioImpl/local")
	private BloqueoListaControlServicioLocal bloqueoListaControlServicio;

	private CategoriaTipoProductoPq categoriaTipoProductoPq;


	@PostConstruct
	public void init() {
		cargarTextosParametrizados();
	}

	/**
	 * selecciona para los detalles del credito
	 * @retunr
	 * @author rituana
	 */
	public void seleccionar() {
		algunoSeleccionado = true;
		detallePrestamo = null;
		detalleSeguimientoCreditoBeanList = null;
		prestamoSeleccionado = (Prestamo) getHttpServletRequest().getAttribute("item");
		datos = (DatosBean) getHttpServletRequest().getSession().getAttribute("datos");
		DetalleCredito detalleCredito = prestamoServicio.consultarDetalleCredito(datos.getSolicitante()
						.getDatosPersonales().getCedula(), prestamoSeleccionado.getCreditoPk().getNumpreafi(),
				prestamoSeleccionado.getCreditoPk().getCodpretip(), prestamoSeleccionado.getCreditoPk().getOrdpreafi(),
				prestamoSeleccionado.getCreditoPk().getCodprecla());
		List<PrestamoEstadoHistorico>prestamoEstadoHistoricoList =
				prestamoEstadoHistoricoServicio.buscarHistoricosDePrestamo(
						prestamoSeleccionado.getPrestamoPk().getNumpreafi(),
						prestamoSeleccionado.getPrestamoPk().getOrdpreafi(),
						prestamoSeleccionado.getPrestamoPk().getCodpretip(),
						prestamoSeleccionado.getPrestamoPk().getCodprecla());
		if (detalleCredito!=null && prestamoEstadoHistoricoList!=null) {

			detallePrestamo = new DetallePrestamoBean();
			detallePrestamo.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			detallePrestamo.setNombre(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			detallePrestamo.setNumeroPrestamo(detalleCredito.getNumeroPrestamo().toString());
			detallePrestamo.setFechaRegistroPrestamo(detalleCredito.getFechaPrestamoAfiliado());
			detallePrestamo.setNombreInstitucionFinanciera(detalleCredito.getBanco());
			detallePrestamo.setNumeroCuentaBancaria(detalleCredito.getCuenta());
			detallePrestamo.setTipoCuentaBancaria(detalleCredito.getTipoCuenta());
			detallePrestamo.setValorTransferido(detalleCredito.getTransferencia());
			detallePrestamo.setEstadoActualPrestamo(detalleCredito.getEstado());
			//INC-2286 Ferrocarriles
			try {
				if(prestamoSeleccionado.getIdProveedor() !=null){
					datos.setProveedor(proveedorServicio.load(prestamoSeleccionado.getIdProveedor()));
				}

				detallePrestamo.setCiudad(divisionPoliticaServicio.consultaDivisionPoliticaPorId(
						prestamoSeleccionado.getCoddivpol().substring(0, 4)).getNomdivpol());
			} catch (DivisionPoliticaException e) {
				detallePrestamo.setCiudad("QUITO");
			}
			detalleSeguimientoCreditoBeanList = new ArrayList<DetalleSeguimientoCreditoBean>();


			for (PrestamoEstadoHistorico prestamoEstadoHistorico : prestamoEstadoHistoricoList) {
				detalleSeguimientoCreditoBeanList.add(
						new DetalleSeguimientoCreditoBean(
								prestamoEstadoHistorico.getPrestamoEstadoHistoricoPK().getFecini(),
								prestamoEstadoHistorico.getEstadoPrestamo().getDesestpre().toUpperCase(),
								prestamoEstadoHistorico.getObstra()
						)
				);
			}
			for (int i = 0; i < detalleSeguimientoCreditoBeanList.size(); i++) {
				if (1+i<detalleSeguimientoCreditoBeanList.size()){
					DetalleSeguimientoCreditoBean detalleSeguimiento1 = detalleSeguimientoCreditoBeanList.get(i+1);
					DetalleSeguimientoCreditoBean detalleSeguimiento2 = detalleSeguimientoCreditoBeanList.get(i);
					String motivorec = "";
					if(detalleSeguimiento2.getEstado().toUpperCase().equals("RECHAZADO")){
						motivorec = prestamoEstadoHistoricoServicio.getmotivorechazo(prestamoSeleccionado.getPrestamoPk());
						if(!motivorec.equals("")) motivorec = ", "+motivorec;
					}
					detalleSeguimiento2.setObservacion(detalleSeguimiento1.getObservacion()+motivorec);

					detalleSeguimientoCreditoBeanList.set(i, detalleSeguimiento2);
				}
			}
			DetalleSeguimientoCreditoBean detalleSeguimiento1 =
					detalleSeguimientoCreditoBeanList.get(detalleSeguimientoCreditoBeanList.size()-1);
			detalleSeguimiento1.setObservacion("Generaci\u00F3n del cr\u00E9dito");
			detalleSeguimientoCreditoBeanList.set(detalleSeguimientoCreditoBeanList.size()-1, detalleSeguimiento1);
			Collections.reverse(detalleSeguimientoCreditoBeanList);
			msjError = null;
		} else {
			msjError = "No existe datos";
		}

		log.info("getApellidosNombres: "+datos.getSolicitante().getDatosPersonales().getApellidosNombres());
		seleccionado=true;
		log.info("seleccionado: "+seleccionado);

		// Reimpresion de FAT.
		this.desplegarDetalle = true;
	}

	/**
	 * Proceso de anulacion.
	 */
	public void anular() {
		try {
			if (TiposProductosPq.FOC.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {
				Map<String, Long> parametrosCredito = new HashMap<String, Long>();
				parametrosCredito.put("codprecla", this.prestamoSeleccionado.getPrestamoPk().getCodprecla());
				parametrosCredito.put("codpretip", this.prestamoSeleccionado.getPrestamoPk().getCodpretip());
				parametrosCredito.put("numpreafi", this.prestamoSeleccionado.getPrestamoPk().getNumpreafi());
				parametrosCredito.put("ordpreafi", this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi());
				parametrosCredito.put("codtipsolser", this.prestamoSeleccionado.getCodtipsolser());
				parametrosCredito.put("numsolser", this.prestamoSeleccionado.getNumsolser());
				this.prestamoServicio.anularCreditoPQConProcedimiento(parametrosCredito,
						this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre());

				// Inicio auditoria
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaCredito(this.prestamoSeleccionado.getNumafi(),
						this.prestamoSeleccionado.getPrestamoPk().getCodprecla().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getCodpretip().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getNumpreafi().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi().toString());
				super.guardarAuditoria(OperacionEnum.ANULAR_PQ_FOCALIZADO, parametroEvento, this.prestamoSeleccionado.getNumafi());
				// Fin auditoria

			} else if (TiposProductosPq.ECU_TUR.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {
				Map<String, Long> parametrosCredito = new HashMap<String, Long>();
				parametrosCredito.put("codprecla", this.prestamoSeleccionado.getPrestamoPk().getCodprecla());
				parametrosCredito.put("codpretip", this.prestamoSeleccionado.getPrestamoPk().getCodpretip());
				parametrosCredito.put("numpreafi", this.prestamoSeleccionado.getPrestamoPk().getNumpreafi());
				parametrosCredito.put("ordpreafi", this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi());

				this.prestamoTuristicoServicio.rechazarCreditoTuristicoConProcedimiento(parametrosCredito,
						this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre(), this.prestamoSeleccionado.getNumafi(),
						this.prestamoSeleccionado.getNumeroReserva());

				// Inicio auditoria
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosRechazaReimprimePQEcuadorTurismo(this.prestamoSeleccionado.getNumafi(),
						this.prestamoSeleccionado.getPrestamoPk().getCodprecla().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getCodpretip().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getNumpreafi().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi().toString(),
						this.prestamoSeleccionado.getNumeroReserva());
				super.guardarAuditoria(OperacionEnum.ANULAR_PQ_ECUADOR_TURISMO, parametroEvento, this.prestamoSeleccionado.getNumafi());
				// Fin auditoria
			} else {
				this.prestamoServicio.anularPrestamo(this.prestamoSeleccionado, "PQ anulado por el Afiliado/Jubilado ");

				// Inicio auditoria
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaCredito(this.prestamoSeleccionado.getNumafi(),
						this.prestamoSeleccionado.getPrestamoPk().getCodprecla().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getCodpretip().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getNumpreafi().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi().toString());
				super.guardarAuditoria(OperacionEnum.ANULAR_PQ, parametroEvento, this.prestamoSeleccionado.getNumafi());
				// Fin auditoria
			}

			this.estiloPanelInfoError = "okayPanel";
			super.addGlobalInfoMessage("El cr\u00E9dito ha sido rechazado correctamente", "");
		} catch (ActualizarPrestamoEstadoHistoricoException e) {
			this.estiloPanelInfoError = "errorPanel";
			super.addGlobalErrorMessage(Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", ""), "");
			msjError = e.getMessage();
			log.error("1. Error al anular credito", e);
		} catch (PrestamosTuristicosException e) {
			this.estiloPanelInfoError = "errorPanel";
			super.addGlobalErrorMessage(Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", ""), "");
			msjError = e.getMessage();
			log.error("2. Error al anular credito", e);
		} catch (PrestamoPqCoreException e) {
			this.estiloPanelInfoError = "errorPanel";
			super.addGlobalErrorMessage(Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", ""), "");
			msjError = e.getMessage();
			log.error("3. Error al anular credito", e);
		} catch (Exception e) {
			this.estiloPanelInfoError = "errorPanel";
			super.addGlobalErrorMessage("Se present\u00F3 un error al anular el cr\u00E9dito. Por favor intente m\u00E1s tarde", "");
			msjError = "Error al procesar la anulaci\u00F3n";
			log.error("4. Error al anular credito", e);
		}
	}

	/**
	 * setea a las variables
	 *
	 * @author rituana
	 */
	public void regresar(){
		prestamoSeleccionado = null;
		detallePrestamo = null;
		detalleSeguimientoCreditoBeanList = null;
		seleccionado = false;
		algunoSeleccionado=false;
		msjError = null;
	}

	/**
	 * Verifica si es Pago de Pensiones Alimenticias.
	 * @return
	 */
	public boolean isReimpresionFAT() {
		// Reimpresion de FAT.
		if (this.prestamoSeleccionado == null || this.prestamoSeleccionado.getPrestamoPk() == null) {
			return false;
		}

		categoriaTipoProductoPq = TiposProductosPq
				.getCategoriaTipoProductoPq(this.prestamoSeleccionado.getPrestamoPk().getCodpretip());

		CreditoValidacion creditoValidacion = this.credCreditoValidacionServicio.consultarPorPrestamo(
				this.prestamoSeleccionado.getPrestamoPk().getCodprecla(), this.prestamoSeleccionado.getPrestamoPk().getCodpretip(),
				this.prestamoSeleccionado.getPrestamoPk().getNumpreafi(), this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi());
		// Se muestra el boton de impresion de formulario de autorizacion de transferencias de fondos para pq
		// focalizados unicamente cuando este en estado GEN
		if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_FOCALIZADOS)
				&& "GEN".equals(this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre()) && "SOL".equals(creditoValidacion.getEstado())) {
			return true;
		}

		// Se permite la reimpresion del formulario de autorizacion de transferencia de fondos cuando es producto
		// Ecuador Tu Lugar en el Mundo y se encuentre en estado GEN
		//para tipo de servicios 4 se mantiene siempre el reimpresion.
		if (TiposProductosPq.DINAMICO.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())
				&& this.prestamoSeleccionado.getCreditoEspecial()!=null) {
			return true;
		}

		if (!this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre().equals("PDA")
				&& !this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre().equals("PAP")) {
			return false;
		}

		if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_SERVICIOS)) {
			return true;
		}

		return false;
	}
	/**
	 * Setea las propiedades para imprimir el formulario de autorizacion de transferencias.
	 */
	public void imprimirFAT() {
		try {
			CategoriaTipoProductoPq categoriaTipoProductoPq = TiposProductosPq
					.getCategoriaTipoProductoPq(this.prestamoSeleccionado.getPrestamoPk().getCodpretip());
			// Reimpresion de FAT.
			this.desplegarFAT = false;
			if (TiposProductosPq.PEN.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {
				if (this.cargarDatosBeneficiarioCredito()) {
					this.desplegarFAT = true;
					this.obtenerTextoFAT(null);
				}
				// INC-2286 Ferrocarriles
			} else if ((categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_SERVICIOS
					|| categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_FOCALIZADOS)
					&& !TiposProductosPq.PEN.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {

				//toma el seguimiento del guardado si existe
				if(TiposProductosPq.DINAMICO.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {
					try {
						this.textoFAT= prestamoDinamico.obtenerDocumentoContrato(prestamoSeleccionado.getNumeroReserva());
						this.desplegarFAT = true;
						//termino el proceso
						return;
					}catch(ConsultaDataPqDinamicoException e) {
						//continuar con el proceso que existia
						log.info("Error negocio",e);
					}catch(ConecSrvPqDinamicoException e) {
						//continuar con el proceso que existia
						log.error("Error conexion al servicio",e);
					}
				}

				CreditoValidacion creditoValidacion = this.credCreditoValidacionServicio.consultarPorPrestamo(
						this.prestamoSeleccionado.getPrestamoPk().getCodprecla(), this.prestamoSeleccionado.getPrestamoPk().getCodpretip(),
						this.prestamoSeleccionado.getPrestamoPk().getNumpreafi(), this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi());

				// Crea el id de transaccion
				Map<String, Object> parametrosId = new HashMap<String, Object>();
				parametrosId.put("APLICATIVO", "PQ");
				parametrosId.put("CODPRECLA", this.prestamoSeleccionado.getPrestamoPk().getCodprecla());
				parametrosId.put("ORDPREAFI", this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi());
				parametrosId.put("CODPRETIP", this.prestamoSeleccionado.getPrestamoPk().getCodpretip());
				parametrosId.put("CEDULA", datos.getSolicitante().getDatosPersonales().getCedula());
				Calendar calendar = Calendar.getInstance();
				parametrosId.put("DIA", calendar.get(Calendar.DATE));
				parametrosId.put("MES", calendar.get(Calendar.MONTH) + 1);
				parametrosId.put("ANIO", calendar.get(Calendar.YEAR));
				parametrosId.put("HORA", calendar.get(Calendar.HOUR_OF_DAY));
				parametrosId.put("MINUTO", calendar.get(Calendar.MINUTE));
				parametrosId.put("SEGUNDO", calendar.get(Calendar.SECOND));

				String codigoActivacion = "";
				if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_FOCALIZADOS) {
					codigoActivacion = this.otpServicio.obtenerCodigoActivacion(obtenerIdTransaccion(parametrosId),
							this.datos.getSolicitante().getDatosPersonales().getCedula());

					creditoValidacion.setCodigoActivacion(EncriptacionUtil.generarMD5(codigoActivacion));
					creditoValidacionServicio.actualizarCreditoValidacion(creditoValidacion);
				}

				this.obtenerTextoFAT(codigoActivacion);
				this.desplegarFAT = true;
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("Error al encripar codigo de activacion", e);
		}
		catch (OTPException e) {
			log.error("Error al generar el codigo de activacion", e);
		}
	}

	/**
	 * Obtiene un id de transaccion para generacion de codigo OTP para prestamos focalizados a partir de un mapa de
	 * parametros
	 *
	 * @param parametrosId
	 * @return
	 */
	private String obtenerIdTransaccion(Map<String, Object> parametrosId) {
		StringBuilder idTransaccion = new StringBuilder();
		idTransaccion.append(parametrosId.get("APLICATIVO"));
		idTransaccion.append(parametrosId.get("CODPRECLA"));
		idTransaccion.append(parametrosId.get("ORDPREAFI"));
		idTransaccion.append(parametrosId.get("CODPRETIP"));
		idTransaccion.append(parametrosId.get("CEDULA"));
		idTransaccion.append(parametrosId.get("DIA"));
		idTransaccion.append(parametrosId.get("MES"));
		idTransaccion.append(parametrosId.get("ANIO"));
		idTransaccion.append(parametrosId.get("HORA"));
		idTransaccion.append(parametrosId.get("MINUTO"));
		idTransaccion.append(parametrosId.get("SEGUNDO"));

		return idTransaccion.toString();
	}

	/**
	 * Carga los detalles del beneficiario.
	 */
	private boolean cargarDatosBeneficiarioCredito() {
		// Reimpresion de FAT.
		try {
			this.detallePrestamo.setBeneficiarioCredito(beneficiarioCreditoServicio.obtenerPorPK(
					this.prestamoSeleccionado.getPrestamoPk().getCodprecla(), this.prestamoSeleccionado.getPrestamoPk()
							.getCodpretip(), this.prestamoSeleccionado.getPrestamoPk().getNumpreafi(),
					this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi()));
			this.detallePrestamo.getBeneficiarioCredito().setProvincia(
					divisionPoliticaServicio.consultarDivisionPolitica(this.detallePrestamo.getBeneficiarioCredito()
							.getProvinciaJuicio()));
			this.detallePrestamo.getBeneficiarioCredito().setCiudad(
					divisionPoliticaServicio.consultarDivisionPolitica(this.detallePrestamo.getBeneficiarioCredito()
							.getCiudadJuicio()));
			return true;
		} catch (Exception e) {
			this.msjError = "No se pudo obtener informaci\u00F3n del Beneficiario con Patria Potestad.";
			log.error("Problemas al cargar informaci\u00F3n del Beneficiario con Patria Potestad.", e);
		}

		return false;
	}

	/**
	 * Obtiene el texto para el formulario de autorizacion de transferencias.
	 *
	 * @return texto formulario.
	 */
	public String obtenerTextoFAT(String codigoActivacion) {
		// INC-2129 Reimpresion de FAT.
		try {
			categoriaTipoProductoPq = TiposProductosPq
					.getCategoriaTipoProductoPq(this.prestamoSeleccionado.getPrestamoPk().getCodpretip());

			TiposProductosPq tipoProductoPqSeleccionado = TiposProductosPq
					.getTiposProductosPq(this.prestamoSeleccionado.getPrestamoPk().getCodpretip());
			/* Obtener template */
			String templatePath ="";
			/* Parametros del mensaje */
			Map<String, Object> parametros = new HashMap<String, Object>();
			//Comunes
			SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
			parametros.put("param_fecha", sdf.format(this.prestamoSeleccionado.getFecpreafi()));
			parametros.put("param_solicitante", this.datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			parametros.put("param_solicitante_cedula", this.detallePrestamo.getCedula());
			parametros.put("param_acercarse_islas", "");

			//Pensiones Alimenticias
			if (TiposProductosPq.PEN.getCodigoTipoProducto().equals(
					this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {
				templatePath= "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferencia.ftl";
				parametros.put("param_ciudad", this.detallePrestamo.getBeneficiarioCredito().getCiudad().getNomdivpol());
				parametros.put("param_tipo_cuenta", this.detallePrestamo.getTipoCuentaBancaria());
				parametros.put("param_numero_cuenta", this.detallePrestamo.getNumeroCuentaBancaria());
				parametros.put("param_banco", this.detallePrestamo.getNombreInstitucionFinanciera());
				parametros.put("param_beneficiario", this.detallePrestamo.getBeneficiarioCredito().getBeneficiario());
				parametros.put("param_nombre_menores", this.detallePrestamo.getBeneficiarioCredito().getNombreMenor());
				parametros.put("param_beneficiario_cedula", this.detallePrestamo.getBeneficiarioCredito()
						.getNumeroBeneficiario());
				parametros.put("param_numero_juzgado", this.detallePrestamo.getBeneficiarioCredito().getNumeroJuzgado());

				// INC-2344 Pension Alimenticia.
				parametros.put("param_monto", this.getPrestamoSeleccionado().getMntpre()
						- (this.getPrestamoSeleccionado().getValsegsal() + ((this.getPrestamoSeleccionado()
						.getValliqnov() == null) ? 0 : this.getPrestamoSeleccionado().getValliqnov())));

				//INC-2286 Ferrocarriles
			} else if (tipoProductoPqSeleccionado == TiposProductosPq.TREN || tipoProductoPqSeleccionado == TiposProductosPq.TUR) {
				templatePath= "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaFEEP.ftl";
				String operadora = "EMPRESA";
				if (TiposProductosPq.TUR.getCodigoTipoProducto().equals(this.prestamoSeleccionado.getPrestamoPk().getCodpretip())) {
					operadora = "OPERADORA TURISTICA";
					parametros.put("param_acercarse_islas", getResource("messages", "creditur.acercarse.islas"));
				}
				parametros.put("param_operadora", operadora);
				Proveedor proveedor = proveedorServicio.load(prestamoSeleccionado.getIdProveedor());
				parametros.put("param_proveedor_nombre", proveedor.getPrNombre());
				parametros.put("param_ciudad", this.detallePrestamo.getCiudad());
				parametros.put("param_operadora", getOperadora());
				parametros.put("param_proveedor_nombre", datos.getProveedor().getPrNombre());
				parametros.put("param_monto", this.detallePrestamo.getValorTransferido());
				parametros.put("param_tipo_cuenta", this.detallePrestamo.getTipoCuentaBancaria());
				parametros.put("param_numero_cuenta", this.detallePrestamo.getNumeroCuentaBancaria());
				parametros.put("param_banco", this.detallePrestamo.getNombreInstitucionFinanciera());
			} else if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_FOCALIZADOS)) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioAutorizacionTransferenciaFocalizados.ftl";
				Long codprecla = this.prestamoSeleccionado.getPrestamoPk().getCodprecla();
				Long codpretip = this.prestamoSeleccionado.getPrestamoPk().getCodpretip();
				Long numpreafi = this.prestamoSeleccionado.getPrestamoPk().getNumpreafi();
				Long ordpreafi = this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi();

				StringBuilder codigoCredito = new StringBuilder();
				codigoCredito.append(Utilities.agregarCerosInicio(codprecla.toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(ordpreafi.toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(codpretip.toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(numpreafi.toString(), 2));

				CreditoValidacion creditoValidacion = this.creditoValidacionServicio.consultarPorPrestamoConDetalle(codprecla, codpretip, numpreafi, ordpreafi);

				Proveedor proveedor = proveedorServicio.load(this.prestamoSeleccionado.getIdProveedor());

				parametros.put("param_codigo_credito", codigoCredito.toString());
				parametros.put("param_num_referencia", codigoActivacion);
				parametros.put("param_fecha_caducidad", sdf.format(creditoValidacion.getFechaCaducidad()));
				parametros.put("param_monto", this.prestamoSeleccionado.getMntpre() - this.prestamoSeleccionado.getValsegsal());
				parametros.put("param_numero_cuenta", this.detallePrestamo.getNumeroCuentaBancaria());
				parametros.put("param_banco", this.detallePrestamo.getNombreInstitucionFinanciera());
				parametros.put("param_proveedor_nombre", proveedor.getPrNombre() + " - " + this.datos.getProveedor().getPrRuc());
				parametros.put("param_adquision_producto",
						creditoValidacion.getListaDetalleFocalizado().size() == 1 ? "la adquisici&#243;n de la cocina de inducci&#243;n"
								: "la adquisici&#243;n de cocina de inducci&#243;n y juego de ollas");
				parametros.put("nombre_beneficiario", this.datos.getProveedor().getPrNombre() + " - " + this.datos.getProveedor().getPrRuc());
				try {
					this.diasCaducidad = parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.DIAS_CADUCIDAD.getIdParametro(),
							ParametrosBiessEnum.DIAS_CADUCIDAD.getNombreParametro()).getValorNumerico().intValue();
				} catch (ParametroBiessException e) {
					log.error("Error al leer parametro", e);
					this.msjError = "No se pudo obtener informaci\u00F3n.";
				}
				parametros.put("param_dias_caducidad", this.diasCaducidad);

				parametros.put("modelo_marca_cocina", "");
				parametros.put("modelo_marca_ollas", "");
				for (DetalleFocalizado detalleFocalizado : creditoValidacion.getListaDetalleFocalizado()) {
					ProductoDto producto = this.catalogoFocalizadosServicio.consultarProductoPorCodigoMeer(detalleFocalizado.getCodigoProductoMeer());
					if (producto.getTipoProducto().equals(2)) {
						StringBuilder marcaModeloCocina = new StringBuilder();
						marcaModeloCocina.append("Marca cocina ");
						marcaModeloCocina.append(producto.getMarcaNombre());
						marcaModeloCocina.append(", Modelo ");
						marcaModeloCocina.append(producto.getNombre());

						parametros.put("modelo_marca_cocina", marcaModeloCocina.toString());
					} else {
						StringBuilder marcaModeloOllas = new StringBuilder();
						marcaModeloOllas.append(" y Marca Juego de Ollas ");
						marcaModeloOllas.append(producto.getMarcaNombre());
						marcaModeloOllas.append(", Modelo ");
						marcaModeloOllas.append(producto.getNombre());
						parametros.put("modelo_marca_ollas", marcaModeloOllas.toString());
					}
				}

				// Inicio auditoria
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosCedulaCredito(this.prestamoSeleccionado.getNumafi(),
						this.prestamoSeleccionado.getPrestamoPk().getCodprecla().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getCodpretip().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getNumpreafi().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi().toString());
				super.guardarAuditoria(OperacionEnum.REIMPRIMIR_FORMULARIO_FOCALIZADO, parametroEvento, this.prestamoSeleccionado.getNumafi());
				// Fin auditoria
			} else if (tipoProductoPqSeleccionado == TiposProductosPq.ECU_TUR) {
				templatePath = "ec/fin/biess/creditos/pq/formularios/templates/formularioReimpAutorizacionTransferenciaDinamico.ftl";
				Long codprecla = this.prestamoSeleccionado.getPrestamoPk().getCodprecla();
				Long codpretip = this.prestamoSeleccionado.getPrestamoPk().getCodpretip();
				Long numpreafi = this.prestamoSeleccionado.getPrestamoPk().getNumpreafi();
				Long ordpreafi = this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi();

				DataProductoDinResponseDto dataProducto=prestamoDinamico.consultarDataProducto(this.prestamoSeleccionado.getCreditoEspecial());
				StringBuilder codigoCredito = new StringBuilder();
				codigoCredito.append(
						Utilities.agregarCerosInicio(codprecla.toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(ordpreafi.toString(), 2));
				codigoCredito.append(Utilities.agregarCerosInicio(codpretip.toString(), 2));
				codigoCredito.append(numpreafi.toString());

				parametros.put("param_codigo_credito", codigoCredito.toString());
				parametros.put("param_num_reserva", this.prestamoSeleccionado.getNumeroReserva());
				parametros.put("param_monto", this.prestamoSeleccionado.getMntpre() - this.prestamoSeleccionado.getValsegsal());
				parametros.put("param_numero_cuenta", this.detallePrestamo.getNumeroCuentaBancaria());
				parametros.put("param_banco", this.detallePrestamo.getNombreInstitucionFinanciera());
				parametros.put("nom_proveedor", this.datos.getProveedor().getPrNombre());
				parametros.put("nom_producto", dataProducto.getTitulo());


				// Inicio auditoria
				ParametroEvento parametroEvento = AuditoriaPqWebHelper.obtenerParametrosRechazaReimprimePQEcuadorTurismo(this.prestamoSeleccionado.getNumafi(),
						this.prestamoSeleccionado.getPrestamoPk().getCodprecla().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getCodpretip().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getNumpreafi().toString(),
						this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi().toString(),
						this.prestamoSeleccionado.getNumeroReserva());
				super.guardarAuditoria(OperacionEnum.REIMPRIMIR_FORMULARIO_ECUADOR_TURISMO, parametroEvento, this.prestamoSeleccionado.getNumafi());
				// Fin auditoria
			}

			/* Cargar el template */
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(AlertUtil.class, "/");
			Template template = cfg.getTemplate(templatePath);

			if (null == parametros || null == template) {
				throw new Exception(
						"Error al imprimir el Formulario de Autorizaci\u00f3n de Transferencias. El template y/o el map de datos son nulos.");
			}

			try {
				Writer out = new StringWriter();
				template.process(parametros, out);
				out.flush();
				this.textoFAT = out.toString();
			} catch (TemplateException e) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.msjError = "No se pudo obtener informaci\u00F3n.";
			log.error(e.getMessage());
		}

		return textoFAT;
	}

	/**
	 * Verifica si un credito esta en PEP.
	 *
	 * @return true esta en lista, caso contrario false.
	 */
	public boolean isEnListaControl() {

		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		if (this.prestamoSeleccionado == null || this.prestamoSeleccionado.getPrestamoPk() == null) {
			return false;
		}

		boolean resp = false;

		try {
			BloqueoListaControl bloqueoListaControl = bloqueoListaControlServicio.obtenerPorCredito(
					this.prestamoSeleccionado.getPrestamoPk().getCodprecla(), this.prestamoSeleccionado.getPrestamoPk()
							.getCodpretip(), this.prestamoSeleccionado.getPrestamoPk().getNumpreafi(),
					this.prestamoSeleccionado.getPrestamoPk().getOrdpreafi(), "PQ");
			if (bloqueoListaControl == null || bloqueoListaControl.getIdBloqueo() == null) {
				resp = false;
			} else {
				resp = true;
			}
		} catch (BloqueoListaControlException e) {
			log.error("No se pudo verificar la informaci\u00F3n en la lista de observados.", e);
		}

		return resp;
	}

	/**
	 * Verifica si un credito esta en PEP.
	 *
	 * @return true esta en lista, caso contrario false.
	 */
	public boolean isPresentaMensajes() {

		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		if (this.prestamoSeleccionado == null || this.prestamoSeleccionado.getPrestamoPk() == null) {
			return false;
		}

		if (this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre().equalsIgnoreCase(EstadoCredito.PDA.toString())
				|| this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre()
				.equalsIgnoreCase(EstadoCredito.PDC.toString())
				|| this.prestamoSeleccionado.getEstadoPrestamo().getCodestpre()
				.equalsIgnoreCase(EstadoCredito.PDV.toString())) {
			return true;
		} else {
			return false;
		}
	}

	private void cargarTextosParametrizados() {
		msjCreditoListaObservadosPep = obtenerTextoParametrizado(super.getResource("messages", "credito.datos.lista.observados.pep.mensaje"),
				ParametrosGeneralesEnum.PQW_CORREO_CONCESION);
	}

	public String obtenerTextoParametrizado(String texto, ParametrosGeneralesEnum parametro) {
		return Utilities.reemplazarParametroEnTexto(texto, parametro, datos.getMapaParametrosGenerales());
	}
	/**
	 * selecciona un prestamo
	 * @retunr
	 * @author rituana
	 */
	public Boolean getSeleccionado() {
		return seleccionado;
	}
	/**
	 * setea prestamo
	 *
	 * @author rituana
	 */
	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	/**
	 * obtiene Prestamo Seleccionado
	 * @retunr
	 * @author rituana
	 */
	public Prestamo getPrestamoSeleccionado() {
		return prestamoSeleccionado;
	}
	/**
	 * obtiene Prestamo Seleccionado
	 *
	 * @author rituana
	 */
	public void setPrestamoSeleccionado(Prestamo prestamoSeleccionado) {
		this.prestamoSeleccionado = prestamoSeleccionado;
	}
	/**
	 * obtiene Detalle Prestamo
	 * @retunr
	 * @author rituana
	 */
	public DetallePrestamoBean getDetallePrestamo() {
		return detallePrestamo;
	}

	/**
	 * configura Detalle Prestamo
	 *
	 * @author rituana
	 */
	public void setDetallePrestamo(DetallePrestamoBean detallePrestamo) {
		this.detallePrestamo = detallePrestamo;
	}

	/**
	 * obtiene lista  Detalle Prestamo bean
	 * @retunr
	 * @author rituana
	 */
	public List<DetalleSeguimientoCreditoBean> getDetalleSeguimientoCreditoBeanList() {
		return detalleSeguimientoCreditoBeanList;
	}


	/**
	 * configura lista  Detalle Prestamo bean
	 *
	 * @author rituana
	 */
	public void setDetalleSeguimientoCreditoBeanList(
			List<DetalleSeguimientoCreditoBean> detalleSeguimientoCreditoBeanList) {
		this.detalleSeguimientoCreditoBeanList = detalleSeguimientoCreditoBeanList;
	}

	/**
	 * consigue mensaje error
	 * @retunr
	 * @author rituana
	 */
	public String getMsjError() {
		return msjError;
	}
	/**
	 * configura mensaje error
	 *
	 * @author rituana
	 */
	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	/**
	 * verifica si hay uno seleccionado
	 * @retunr
	 * @author rituana
	 */
	public Boolean getAlgunoSeleccionado() {
		return algunoSeleccionado;
	}
	/**
	 * configura si hay uno seleccionado
	 * @retunr
	 * @author rituana
	 */
	public void setAlgunoSeleccionado(Boolean algunoSeleccionado) {
		this.algunoSeleccionado = algunoSeleccionado;
	}

	/**
	 * @return the desplegarDetalle
	 */
	public boolean isDesplegarDetalle() {
		return desplegarDetalle;
	}

	/**
	 * @param desplegarDetalle the desplegarDetalle to set
	 */
	public void setDesplegarDetalle(boolean desplegarDetalle) {
		this.desplegarDetalle = desplegarDetalle;
	}

	/**
	 * @return the textoFAT
	 */
	public String getTextoFAT() {
		return textoFAT;
	}

	/**
	 * @param textoFAT the textoFAT to set
	 */
	public void setTextoFAT(String textoFAT) {
		this.textoFAT = textoFAT;
	}

	/**
	 * @return the desplegarFAT
	 */
	public boolean isDesplegarFAT() {
		return desplegarFAT;
	}

	/**
	 * @param desplegarFAT the desplegarFAT to set
	 */
	public void setDesplegarFAT(boolean desplegarFAT) {
		this.desplegarFAT = desplegarFAT;
	}

	private String getOperadora() {
		String operadora = "EMPRESA";
		TiposProductosPq tiposProductosPq = TiposProductosPq.getTiposProductosPq(datos.getProveedor().getTipoPrestamoProducto().getCodigo());
		if (tiposProductosPq != null && tiposProductosPq.equals(TiposProductosPq.TUR)) {
			operadora = "OPERADORA TURISTICA";
		}

		return operadora;
	}

	/**
	 * @return the categoriaTipoProductoPq
	 */
	public CategoriaTipoProductoPq getCategoriaTipoProductoPq() {
		return categoriaTipoProductoPq;
	}

	/**
	 * @param categoriaTipoProductoPq the categoriaTipoProductoPq to set
	 */
	public void setCategoriaTipoProductoPq(CategoriaTipoProductoPq categoriaTipoProductoPq) {
		this.categoriaTipoProductoPq = categoriaTipoProductoPq;
	}

	public String getEstiloPanelInfoError() {
		return estiloPanelInfoError;
	}

	public void setEstiloPanelInfoError(String estiloPanelInfoError) {
		this.estiloPanelInfoError = estiloPanelInfoError;
	}

	public String getMsjCreditoListaObservadosPep() {
		return msjCreditoListaObservadosPep;
	}

	public void setMsjCreditoListaObservadosPep(String msjCreditoListaObservadosPep) {
		this.msjCreditoListaObservadosPep = msjCreditoListaObservadosPep;
	}
}
