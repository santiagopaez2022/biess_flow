package ec.gov.iess.planillaspq.web.backing;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.html.HtmlDataTable;
import org.richfaces.model.selection.Selection;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.enumeracion.DocumentoHabilitacionEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoEmpleadorEnum;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiessPK;
import ec.gob.biess.planillaspq.web.exception.PagIndException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.enumeracion.RolSolicitante;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.AnularComprobanteExcepcion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.CalculoValorRealFondosExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoExcepcion;
import ec.gov.iess.creditos.excepcion.GenerarComprobantePagoIndividualExcepcion;
import ec.gov.iess.creditos.excepcion.LiquidacionAnticipadaExcepcion;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;
import ec.gov.iess.creditos.modelo.dto.DatosSituacionPrestamo;
import ec.gov.iess.creditos.modelo.dto.RequisitosCruce;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosComprobante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.EntidadNoEncontradaException;
import ec.gov.iess.creditos.pq.excepcion.GenerarComprobanteIndividualExcepcion;
import ec.gov.iess.creditos.pq.excepcion.NoTieneComprobanteVigenteException;
import ec.gov.iess.creditos.pq.excepcion.NoTieneLiquidacionVigenteException;
import ec.gov.iess.creditos.pq.servicio.ComprobantePagoServicio;
import ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.LiquidacionServicio;
import ec.gov.iess.creditos.pq.servicio.PeriodoComprobanteServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SaldoLiquidacionPrestamoServicio;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.creditos.pq.util.Utilities;
import ec.gov.iess.hl.exception.NoTieneRelacionDeDependenciaException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;
import ec.gov.iess.planillaspq.web.alertas.util.AlertUtil;
import ec.gov.iess.planillaspq.web.bean.DatosBean;
import ec.gov.iess.planillaspq.web.enumeration.FlujoParametrizacionPQEnum;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;


/**
 * Backing bean para la pagina de cosulta y adm. de creditos
 * 
 * @author roberth.obando
 * 
 */
public class CreditoAccionBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private transient static final LoggerBiess LOG = LoggerBiess
			.getLogger(CreditoAccionBean.class);

	@EJB(name = "LiquidacionServicioImpl/local")
	private LiquidacionServicio liquidacionServicio;

	@EJB(name = "ComprobantePagoServicioImpl/local")
	private ComprobantePagoServicio comprobantePagoServicio;

	@EJB(name = "DividendoPrestamoServicioImpl/local")
	private DividendoPrestamoServicio dividendoPrestamoServicio;

	@EJB(name = "SaldoLiquidacionPrestamoServicioImpl/local")
	private SaldoLiquidacionPrestamoServicio saldoLiquidacionPrestamoServicio;
	
	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;
	
	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;
	
	@EJB(name = "DefinirRolServicioImpl/local")
	private DefinirRolServicioLocal definirRolServicio;
	
	@EJB(name = "PeriodoComprobanteServicioImpl/local")
	private PeriodoComprobanteServicio periodoComprobanteServicio;
	
	@EJB(name = "ServicioPrestadoServicioImpl/local")
	ServicioPrestadoServicio servicioPrestado;
	
	private boolean habilitarLiquidarFondos = false;
	private boolean habilitarRequisitosCruce = true;
	private boolean habilitarCalcularLiq = false;
	private String msjCalcularLiq = null;
	private boolean habilitarCalcularLiqFondos = false;
	private String msjCalcularLiqFondos = null;
	private boolean habilitarGenerarComp = false;
	private String msjGenerarComp = null;
	private boolean habilitarVerDetalle = false;

	private boolean habilitarConsultaLiq = false;
	private boolean habilitarConsultaGC = false;

	private Prestamo prestamoSeleccionado;
	private PrestamoBiess prestamoSeleccionadoBiess;
	private Selection selectedItem;

	private String msjError = null;

	private BigDecimal valorRealFondos;

	// para liquidaciones
	private CalculoLiquidacion calculoLiquidacion;
	private Long numeroLiquidacion;
	private LiquidacionPrestamo liquidacionPrestamo;
	private ComprobantePago comprobantePago;
	private List<RequisitosCruce> requisitosCruceCuentas;

	// para pagind
	private List<DividendoPrestamo> dividendosPorPagar;
	private List<SaldoLiquidacionPrestamo> saldosLiq;
	private List<ComprobantePago> comprobantesDePago;
	private HtmlDataTable tablaComprobantesPago;
	private HtmlDataTable tablaComprobantesLiquidacion;
	private String msjErrorPagInd = null;
	private boolean pagindGenerado = false;

	private ValidarRequisitosRecaudacion validarRecaudacion = new ValidarRequisitosRecaudacion();
	private List<EstadoComprobantePago> estadosLiquidar = new ArrayList<EstadoComprobantePago>();
	private ValidarRequisitosComprobante validarComprobante = new ValidarRequisitosComprobante();
	private DatosSituacionPrestamo situacionPrestamo = new DatosSituacionPrestamo();

	private DatosBean datos;
	
	// INC-2129 Control en Generacion de Comprobantes.
	private boolean desplegarMensajeComprobanteImpago = false;
	
	private boolean todos;
		
	private SaldoLiquidacionPrestamo saldoLiquidacionPrestamoSeleccionado;
	
	private TipoPrestamista tipoPrestamista = null;
	private String tipoCredito;
	private List<LiquidacionPrestamo> listaLiquidaciones;
	
	private HtmlDataTable tablaComprobantes;
	
	private boolean flujoPagInd = true;
	private boolean habilitarVerComprobantes = false;

	private List<String> obtenerDividendosMora() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_MOR);
		return estados;
	}

	private List<String> obtenerDividendosEco() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_ECO);
		return estados;
	}

	private List<String> obtenerDividendosEpl() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_EPL);
		return estados;
	}

	private List<String> obtenerDividendosReg() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_REG);
		return estados;
	}

	private List<String> obtenerDivComprobante() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_DIV_ECP);
		estados.add(EstadosCredito.ESTADO_DIV_GEN);
		estados.add(EstadosCredito.ESTADO_DIV_MOR);
		estados.add(EstadosCredito.ESTADO_DIV_TGL);
		return estados;
	}

	private List<String> obtenerTiposSolicitudAfi() {
		List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_SOLICITUD_AFI_AFL);
		tipos.add(TiposCredito.TIPO_SOLICITUD_AFI_FAL);
		return tipos;
	}

	private List<String> obtenerEstadosSolicitudAfi() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_FONDOS_AFT);
		estados.add(EstadosCredito.ESTADO_FONDOS_REG);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA);
		return estados;
	}

	/**
	 * Carlos Bastidas: INC-6047 se agrega estados de solicitud en tramite por
	 * fondos de reserva"
	 */
	private List<String> obtenerEstadosSolicitudTramite() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA_ANU);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA_PAG);
		estados.add(EstadosCredito.ESTADO_FONDOS_TRA_NPA);
		return estados;
	}

	private List<String> obtenerEstadoCargoReg() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_CARGO_REG);
		return estados;
	}

	private List<String> obtenerEstadoCargoPro() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_CARGO_PRO);
		return estados;
	}

	private List<String> obtenerEstadoBloqueado() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_BLOQUEADO);
		return estados;
	}

	private List<String> obtenerTipoAportes() {
		List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_APORTE_BAC);
		tipos.add(TiposCredito.TIPO_APORTE_PAG);
		tipos.add(TiposCredito.TIPO_APORTE_EXC);
		tipos.add(TiposCredito.TIPO_APORTE_CAR);
		tipos.add(TiposCredito.TIPO_APORTE_AJS);
		tipos.add(TiposCredito.TIPO_APORTE_REL);
		return tipos;
	}
	
	/**
	 * Cuando selecciona un prestamo
	 * 
	 * @return
	 */
	public String seleccionar() {
		String pagina = null;
		/* Validar si existe un credito seleccionado */
		if (null == prestamoSeleccionadoBiess) {
			return "";
		}
		
		resetear();
		

		/* Obtener prestamo seleccionado */		
		PrestamoBiessPK pbpk = prestamoSeleccionadoBiess.getPrestamoPk();
		PrestamoPk pk = new PrestamoPk(pbpk.getCodprecla(), pbpk.getCodpretip(), pbpk.getNumpreafi(), pbpk.getOrdpreafi());
		prestamoSeleccionado = prestamoServicio.bucarPrestamoPk(pk);

		// verifica si puede liquidar
		LOG.debug("getNumpreafi:"
				+ prestamoSeleccionado.getCreditoPk().getNumpreafi());
		LOG.debug("getCodprecla:"
				+ prestamoSeleccionado.getCreditoPk().getCodprecla());
		LOG.debug("getCodpretip:"
				+ prestamoSeleccionado.getCreditoPk().getCodpretip());
		LOG.debug("getOrdpreafi:"
				+ prestamoSeleccionado.getCreditoPk().getOrdpreafi());

		// Carga datos en objeto ValidarRequisitosRecaudacion
		validarRecaudacion.setFlujo(RolSolicitante.FUN.name());
		validarRecaudacion.setPrestamo(prestamoSeleccionado);
		validarRecaudacion.setEstadoCredito(EstadosCredito.ESTADO_VIGENTE);
		validarRecaudacion.setDividendosMora(obtenerDividendosMora());
		validarRecaudacion.setDividendosEco(obtenerDividendosEco());
		validarRecaudacion.setDividendosEpl(obtenerDividendosEpl());
		validarRecaudacion.setEstadoComprobante(obtenerDivComprobante());
		validarRecaudacion.setTipoCredito(obtenerTiposCreditoValidacion());
		validarRecaudacion.setDividendosReg(obtenerDividendosReg());
		validarRecaudacion
				.setEstadoSolicitudFondos(obtenerEstadosSolicitudAfi());
		validarRecaudacion.setTipoSolicitudFondos(obtenerTiposSolicitudAfi());
		validarRecaudacion.setEstadoCargoReg(obtenerEstadoCargoReg());
		validarRecaudacion.setEstadoCargoPro(obtenerEstadoCargoPro());
		validarRecaudacion.setEstadoBloqueado(obtenerEstadoBloqueado());
		validarRecaudacion.setTipoAporte(obtenerTipoAportes());
		/**
		 * Carlos Bastidas: INC-6047 se agrega estados de solicitud en tramite
		 * por fondos de reserva"
		 */
		validarRecaudacion
				.setEstadoSolicitudFondosTramite(obtenerEstadosSolicitudTramite());
		situacionPrestamo.setPrestamoPk(prestamoSeleccionado.getPrestamoPk());
		situacionPrestamo.setEstadosCreVigentes(obtenerEstadosCreditoVig());
		situacionPrestamo.setEstadosCreLiquidacion(obtenerEstadosCreditoLiq());
		situacionPrestamo.setEstadosDivPorPagar(obtenerEstadosDivPorPagar());
		situacionPrestamo.setEstadosDivMora(obtenerEstadosDivMora());
		situacionPrestamo.setEstadosDivSaldoLiq(obtenerEstadosSaldoLiq());
		situacionPrestamo.setTipoCredito(obtenerTiposSituacionCredito());
		situacionPrestamo.setTipoEmpleador(null);
		
		validarComprobante.setEstadosComprobante(obtenerEstadosComprobante());
		validarComprobante.setEstadosPrestamo(obtenerEstadosCreComprobante());
		validarComprobante.setPrestamoPk(prestamoSeleccionado.getPrestamoPk());
		validarComprobante.setTiposComprobante(obtenerTiposComprobante());
		
		asignarRol(prestamoSeleccionado);
		
		if (FlujoParametrizacionPQEnum.CCI.name().equals(datos.getFlujoProceso())) {
			pagina = "detallePrestamo";
			// Inicio Auditoria
			FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			PrestamoPk prestamoPk = situacionPrestamo.getPrestamoPk();
			//ST
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaComprobante(funcionario.getCedula(), 
					datos.getSolicitante().getDatosPersonales().getCedula(), prestamoPk.getCodprecla().toString(),
					prestamoPk.getCodpretip().toString(), prestamoPk.getNumpreafi().toString(), prestamoPk.getOrdpreafi().toString());
			
			super.guardarAuditoria(OperacionEnum.CONSULTAR_COMPROBANTE_INDIVIDUAL, parametroEvento, prestamoPk.getNumpreafi().toString());
			// Fin Auditoria
		}
		
		
		if (this.creditoConcedidoComoJubilado(prestamoSeleccionado)) {
			tipoPrestamista = TipoPrestamista.AFILIADO_JUBILADO;
		} else {
			// Se determina el rol si es voluntario o cesante
			try {
				tipoPrestamista = definirRolServicio.determinaVoluntarioCesante(datos.getSolicitante().getDatosPersonales().getCedula() , datos.getRolPrestamista());
			} catch (ServicioPrestadoException e) {
				msjError = e.getMessage();
			} catch (NoTieneRelacionDeDependenciaException e) {
				msjError = e.getMessage();
			}
		}
		
		Calendar fechaGeneracion = Calendar.getInstance();
		Calendar fechaValidez = determinaEmpleadorFechaGeneracion(fechaGeneracion.getTime(), 
				fechaGeneracion.getTime(), fechaGeneracion);
		
		situacionPrestamo.setFechaValidezLiquidacion(fechaValidez);
		situacionPrestamo.setFechaValidezComprobante(fechaValidez);
		
		if (!TipoPrestamista.AFILIADO.equals(tipoPrestamista)
				&& !TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)) {
			if (FlujoParametrizacionPQEnum.GLA.name().equals(datos.getFlujoProceso())) {
					pagina = calcularLiq();
			} else if (FlujoParametrizacionPQEnum.GCPI.name().equals(datos.getFlujoProceso())) {
				// verifica si puede generar comprobante
				pagina = seleccionarDividendos();
			}
		}
		
		if (FlujoParametrizacionPQEnum.GLA.name().equals(datos.getFlujoProceso()) || 
				FlujoParametrizacionPQEnum.GCPI.name().equals(datos.getFlujoProceso())) {
			try {
				// Se restringe la generacion de comprobantes a AFILIADOS y AFILIADOS-JUBILADOS
				if (TipoPrestamista.AFILIADO.equals(tipoPrestamista)
						|| TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)) {
	
					// Se determina que tipo de empleador es
					String tipoEmpleador = null;
					if (this.creditoConcedidoComoJubilado(prestamoSeleccionado)) {
						tipoEmpleador = TipoEmpleadorEnum.JUB.getCodigo();
					} else {
						tipoEmpleador = this.comprobantePagoServicio.determinaEmpleador(prestamoSeleccionado.getPrestamoPk());
						if (tipoEmpleador == null) {
							msjError = "Error al determinar el tipo de empleador asociado al pr\u00E9stamo seleccionado";
							return "consultaCreditos";
						}
					}
					
					PeriodoComprobante periodoComprobante = this.periodoComprobanteServicio.buscarPorPeriodoYEmpleador(fechaGeneracion.get(Calendar.YEAR),
							fechaGeneracion.get(Calendar.MONTH) + 1, tipoEmpleador);
					
					situacionPrestamo.setTipoEmpleador(tipoEmpleador);
					
					Calendar fechaValidezCalculada = determinaEmpleadorFechaGeneracion(periodoComprobante.getFechaInicio(), 
							periodoComprobante.getFechaFin(), fechaGeneracion);
					
					situacionPrestamo.setFechaValidezLiquidacion(fechaValidezCalculada);
					situacionPrestamo.setFechaValidezComprobante(fechaValidezCalculada);
					if (FlujoParametrizacionPQEnum.GLA.name().equals(datos.getFlujoProceso())) {
							// verifica si puede generar liquidacion
							if (liquidacionServicio.esPosibleLiquidacion(validarRecaudacion)) {
								habilitarCalcularLiq = this.comprobantePagoServicio.habilitaComprobanteLiquidacion(fechaGeneracion, tipoEmpleador,
										DocumentoHabilitacionEnum.LIQUIDACION_ANTICIPADA, periodoComprobante);
								if (habilitarCalcularLiq) {
									pagina = calcularLiq();
								} else {
									msjError = "Para este día no se encuentra habilitada la generación de liquidación anticipada";
								}
							} else {
								msjError = "El asegurado no tiene dividendos pendientes de pago a la fecha";
							}
					}
					
					if (FlujoParametrizacionPQEnum.GCPI.name().equals(datos.getFlujoProceso())) {
						// verifica si puede generar comprobante
						if (liquidacionServicio.esPosibleGenCompIndividual(situacionPrestamo)) {
							habilitarGenerarComp = this.comprobantePagoServicio.habilitaComprobanteLiquidacion(fechaGeneracion, tipoEmpleador, 
									DocumentoHabilitacionEnum.COMPROBANTE_PAGO, periodoComprobante);	
							if (habilitarGenerarComp) {
								pagina = seleccionarDividendos();
							} else {
								msjError = "Para este día no se encuentra habilitada la generación de comprobante individual";
							}
						} else {
							msjError = "El asegurado no tiene dividendos pendientes de pago a la fecha";
						}
						
					}
				}
			} catch (PeriodoComprobanteException e) {
				msjError = e.getMessage();
				habilitarCalcularLiq = false;
				LOG.error("Error al habilitar generacion de comprobante o liquidacion anticipada.", e);
			} catch (LiquidacionAnticipadaExcepcion e) {
				msjError = e.getMessage();
			} catch (GenerarComprobanteIndividualExcepcion e) {
				msjError = e.getMessage();
			}
		}
		return pagina;
	}
	
	
	public String consultaComprobantes(String tipoComprobante) {
		String pagina = null;
		if (FlujoParametrizacionPQEnum.CLA.name().equals(datos.getFlujoProceso())) {
			try {
				listaLiquidaciones = liquidacionServicio
						.obtenerLiquidacionesVigente(datos.getSolicitante().getDatosPersonales().getCedula());
				pagina = "liquidacionesPago";
			} catch (NoTieneLiquidacionVigenteException e) {
				msjError = "No existen liquidaciones vigentes";
			}
		} else if (FlujoParametrizacionPQEnum.CCP.name().equals(datos.getFlujoProceso())) {
			validarComprobante.setEstadosComprobante(obtenerEstadosComprobante());
			validarComprobante.setNumeroAfiliado(datos.getSolicitante().getDatosPersonales().getCedula());
			validarComprobante.setEstadosPrestamo(obtenerEstadosCreComprobante());
			validarComprobante.setTiposComprobante(obtenerTiposComprobante());
			if (comprobantePagoServicio.existeComprobantesIndividualesVigentes(validarComprobante)) {
				pagina = consultarPagInd();
			} else {
				msjError = "El asegurado no tiene ningun comprobantes de pago individual que pueda cancelar";
			}
		} else if (FlujoParametrizacionPQEnum.ACPI.name().equals(datos.getFlujoProceso())) {
			validarComprobante.setEstadosComprobante(obtenerEstadosComprobante());
			validarComprobante.setNumeroAfiliado(datos.getSolicitante().getDatosPersonales().getCedula());
			validarComprobante.setEstadosPrestamo(obtenerEstadosCreComprobanteConsulta());
			validarComprobante.setTiposComprobante(obtenerTiposComprobantePagoIndividual(tipoComprobante));
			comprobantesDePago = comprobantePagoServicio.obtenerComprobantesIndividualesVigentes(validarComprobante);
			if (comprobantesDePago.size() == 0) {
				if (TiposCredito.TIPO_COMPROBANTE_PAG.equals(tipoComprobante)) {
					msjError = "El asegurado no tiene ningun comprobantes de pago individual que pueda anular";
				} else {
					msjError = "El asegurado no tiene ningun comprobantes de saldo de debito que pueda anular";
				}
			}
			
		} else if (FlujoParametrizacionPQEnum.ALA.name().equals(datos.getFlujoProceso())) { 
			try {
				listaLiquidaciones = liquidacionServicio
						.obtenerLiquidacionesVigente(datos.getSolicitante().getDatosPersonales().getCedula());
			} catch (NoTieneLiquidacionVigenteException e) {
				msjError = "No existen liquidaciones vigentes";
			}
		}
		
		
		return pagina;
	}
	
	private void asignarRol (Prestamo prestamo) {

		if (creditoConcedidoComoAfiliado(prestamo)) {
			datos.setRolPrestamista(TipoPrestamista.AFILIADO);
		} else if (creditoConcedidoComoJubilado(prestamo)) {
			datos.setRolPrestamista(TipoPrestamista.JUBILADO);
		} else if (creditoConcedidoComoZafrero(prestamo)) {
			datos.setRolPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
		}
		
		datos.setRolesCargados(true);
	}
	
	/**
	 * Verifica si el credito fue concedido como jubilado
	 * 
	 * @param prestamo
	 * @return
	 */
	private boolean creditoConcedidoComoJubilado(Prestamo prestamo) {
		boolean valida = false;
		// Si el codprecla es 21, 24 o 25 significa que el credito fue concedido como jubilado
		if (prestamo.getCreditoPk().getCodprecla().equals(21L) || prestamo.getCreditoPk().getCodprecla().equals(24L)
				|| prestamo.getCreditoPk().getCodprecla().equals(25L)) {
			valida = true;
		}
		return valida;
	}
	
	/**
	 * Verifica si el credito fue concedido como afiliado
	 * 
	 * @param prestamo
	 * @return
	 */
	private boolean creditoConcedidoComoAfiliado(Prestamo prestamo) {
		boolean valida = false;
		// Si el codprecla es 20 significa que el credito fue concedido como afiliado
		if (prestamo.getCreditoPk().getCodprecla().equals(20L)) {
			valida = true;
		}
		return valida;
	}
	
	/**
	 * Verifica si el credito fue concedido como zafrero
	 * 
	 * @param prestamo
	 * @return
	 */
	private boolean creditoConcedidoComoZafrero(Prestamo prestamo) {
		boolean valida = false;
		// Si el codprecla es 22 significa que el credito fue concedido como zafrero
		if (prestamo.getCreditoPk().getCodprecla().equals(22L)) {
			valida = true;
		}
		return valida;
	}
	
	/**
	 * Indica si se debe calificar al empleador (MIN, PRI, PUB, JUB) de acuerdo a la fecha de generacion de comprobante
	 * (COMP, LIQ)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param fechaGeneracion
	 * @return Calendar
	 */
	private Calendar determinaEmpleadorFechaGeneracion(Date fechaInicio, Date fechaFin, Calendar fechaGeneracion) {
		Calendar fechaValidezComprobante = null;
		int diasValidez = Utilities.calcurarNuemroDias(fechaInicio, fechaFin);
		fechaValidezComprobante = Utilities.agregarDiasFecha(fechaGeneracion, diasValidez);
		fechaValidezComprobante.set(Calendar.HOUR_OF_DAY, 23);
		fechaValidezComprobante.set(Calendar.MINUTE, 59);
		fechaValidezComprobante.set(Calendar.SECOND, 59);
		return fechaValidezComprobante;
	}
	
	/**
	 * Calcula como quedaria la Liquidacion del credito
	 * 
	 * @return
	 */

	public BigDecimal calcularValorFondos() {
		try {
			return liquidacionServicio.calcularValorFondos(
					prestamoSeleccionado.getPrestamoPk(),
					prestamoSeleccionado.getNumafi(),
					validarRecaudacion.getCumpleImposiciones()).setScale(2,
					BigDecimal.ROUND_DOWN);
		} catch (CalculoValorRealFondosExcepcion e) {
			msjError = e.getMessage();
			return null;
		}
	}
	
	/**
	 * Cuando selecciona un comprobante pago
	 * 
	 * @return
	 */
	public String seleccionarComprobantePagoAnular() {
		String pagina = null;
		if (tablaComprobantesPago.isRowAvailable()) {
			comprobantePago = (ComprobantePago) tablaComprobantesPago.getRowData();
		}
		
		return pagina;
	}
	
	/**
	 * Cuando selecciona un prestamo
	 * 
	 * @return
	 */
	public String procesarAnularComprobantePago() {
		String pagina = null;
		seleccionarComprobantePagoAnular();
		anularComprobantePago();
		return pagina;
	}
	
	/**
	 * Cuando selecciona un prestamo
	 * 
	 * @return
	 */
	public String anularComprobantePago() {
		String pagina = null;
			try {
				comprobantesDePago.clear();
				comprobantePagoServicio.anularComprobantePago(comprobantePago);
				msjError = "El comprobante de pago N°: " + comprobantePago.getPk().getCodComPagAfi() + " ha sido anulado";
				habilitarVerComprobantes = false;
				FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
				
				// Inicio Auditoria
				PrestamoPk prestamoPk = comprobantePago.getPrestamo().getPrestamoPk();
				ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaComprobante(funcionario.getCedula(), 
						datos.getSolicitante().getDatosPersonales().getCedula(), prestamoPk.getCodprecla().toString(),
						prestamoPk.getCodpretip().toString(), prestamoPk.getNumpreafi().toString(), prestamoPk.getOrdpreafi().toString(),
						comprobantePago.getTipoComprobante().getTipoComprobante(), comprobantePago.getNumeroDocumentoPago());
				super.guardarAuditoria(OperacionEnum.ANULAR_COMPROBANTE_PAGO, parametroEvento, prestamoPk.getNumpreafi().toString());
				// Fin Auditoria
				
			} catch (AnularComprobanteExcepcion ae) {
				msjError = "Problemas al anular el comprobante";
			}
		return pagina;
	}
	
	/**
	 * Cuando selecciona un prestamo
	 * 
	 * @return
	 */
	public String anularComprobanteLiquidacion() {
		String pagina = null;
		if (tablaComprobantesLiquidacion.isRowAvailable()) {
			
			LiquidacionPrestamo liquidacion = (LiquidacionPrestamo) tablaComprobantesLiquidacion.getRowData();
			try {
				listaLiquidaciones.clear();
				estadosLiquidar.clear();
				estadosLiquidar.add(EstadoComprobantePago.GEN);
				comprobantePago = comprobantePagoServicio
						.obtenerComprobantePagoVigente(liquidacion.getNumeroLiquidacion(),
								estadosLiquidar);
				comprobantePagoServicio.anularComprobantePago(comprobantePago);
				msjError = "El comprobante de pago N: " + comprobantePago.getPk().getCodComPagAfi() + " ha sido anulado";
				
				FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
				// Inicio Auditoria
				PrestamoPk prestamoPk = comprobantePago.getPrestamo().getPrestamoPk();
				ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaComprobante(funcionario.getCedula(), 
						datos.getSolicitante().getDatosPersonales().getCedula(), prestamoPk.getCodprecla().toString(),
						prestamoPk.getCodpretip().toString(), prestamoPk.getNumpreafi().toString(), prestamoPk.getOrdpreafi().toString(),
						comprobantePago.getTipoComprobante().getTipoComprobante(), comprobantePago.getNumeroDocumentoPago());
				super.guardarAuditoria(OperacionEnum.ANULAR_LIQUIDACION_PRESTAMO, parametroEvento, prestamoPk.getNumpreafi().toString());
				// Fin Auditoria
			} catch (NoTieneComprobanteVigenteException e) {
				msjError = "Problemas al anular el comprobante Vigencia";

			} catch (AnularComprobanteExcepcion ae) {
				msjError = ae.getMessage();
			}
		}
		
		return pagina;
	}

	/**
	 * Calcula como quedaria la Liquidacion del credito
	 * 
	 * @return
	 */
	public String calcularLiq() {
		try {
			validarRecaudacion.setTipoLiquidacion(TipoLiquidacion.PRE);
			calculoLiquidacion = liquidacionServicio.calcularLiquidacion(
					validarRecaudacion, TipoLiquidacion.PRE);
		} catch (CalculoLiquidacionExcepcion e) {
			msjError = e.getMessage();
			return "consultaCreditos";
		}
		return "calculoLiquidacion";
	}

	/**
	 * Realiza la liquidacion del credito
	 * 
	 * @return
	 */
	public String liquidar() {
		try {
			estadosLiquidar.clear();
			estadosLiquidar.add(EstadoComprobantePago.DEP);
			estadosLiquidar.add(EstadoComprobantePago.GEN);
			
			validarRecaudacion.setTipoEmpleador(null);
			
			Calendar fechaGeneracion = Calendar.getInstance();
			Calendar fechaValidez = determinaEmpleadorFechaGeneracion(fechaGeneracion.getTime(), 
					fechaGeneracion.getTime(), fechaGeneracion);
			
			validarRecaudacion.setFechaValidezLiquidacion(fechaValidez);
			
			// determinamos que tipo de empleador es
			if (TipoPrestamista.AFILIADO.equals(tipoPrestamista)
					|| TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)) {
				// Se determina que tipo de empleador es
				String tipoEmpleador = null;
				if (this.creditoConcedidoComoJubilado(prestamoSeleccionado)) {
					tipoEmpleador = TipoEmpleadorEnum.JUB.getCodigo();
				} else {
					tipoEmpleador = this.comprobantePagoServicio.determinaEmpleador(prestamoSeleccionado.getPrestamoPk());
					if (tipoEmpleador == null) {
						msjError = "Error al determinar el tipo de empleador asociado al pr\u00E9stamo seleccionado";
						return "consultarCreditos";
					}
				}
				
				PeriodoComprobante periodoComprobante = this.periodoComprobanteServicio.buscarPorPeriodoYEmpleador(fechaGeneracion.get(Calendar.YEAR),
						fechaGeneracion.get(Calendar.MONTH) + 1, tipoEmpleador);
				
				validarRecaudacion.setTipoEmpleador(tipoEmpleador);
				Calendar fechaValidezLiquidacion = determinaEmpleadorFechaGeneracion(periodoComprobante.getFechaInicio(), 
						periodoComprobante.getFechaFin(), fechaGeneracion);
				
				validarRecaudacion.setFechaValidezLiquidacion(fechaValidezLiquidacion);
			}
			numeroLiquidacion = liquidacionServicio
					.generarLiquidacionAnticipada(validarRecaudacion,
							TipoLiquidacion.PRE).longValue();
			habilitarConsultaLiq = true;
			// cargo la liquidacion
			liquidacionPrestamo = liquidacionServicio
					.consultar(numeroLiquidacion);
			// cargo el comprobante de pago
			comprobantePago = comprobantePagoServicio
					.obtenerComprobantePagoVigente(numeroLiquidacion,
							estadosLiquidar);
			
			FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaLiquidacion(funcionario.getCedula(), 
					datos.getSolicitante().getDatosPersonales().getCedula(), liquidacionPrestamo.getCodPreCla().toString(),
					liquidacionPrestamo.getCodPreTip().toString(), liquidacionPrestamo.getNumPreAfi().toString(), liquidacionPrestamo.getOrdPreAfi().toString(),
					liquidacionPrestamo.getTipoLiquidacion(), liquidacionPrestamo.getNumeroLiquidacion().toString(),
					liquidacionPrestamo.getTotCanLiq().toString(), comprobantePago.getTipoComprobante().getTipoComprobante(),
					comprobantePago.getNumeroDocumentoPago(), comprobantePago.getValorTotalComprobante().toString());
			super.guardarAuditoria(OperacionEnum.GENERAR_LIQUIDACION_PRESTAMO, parametroEvento, liquidacionPrestamo.getNumPreAfi().toString());
			// Fin Auditoria
		} catch (NoTieneComprobanteVigenteException e) {
			msjError = e.getMessage();
			return "consultarCreditos";

		} catch (LiquidacionAnticipadaExcepcion e) {
			msjError = e.getMessage();
			return "consultarCreditos";

		} catch (GenerarComprobantePagoExcepcion e) {
			msjError = e.getMessage();
			return "consultarCreditos";
		} catch (PeriodoComprobanteException e) {
			msjError = e.getMessage();
			LOG.error("Error al habilitar generacion de comprobante o liquidacion anticipada.", e);
			return "consultarCreditos";
		}
		
		
		/* INC-1817 Notificaciones asegurados */
		if (null != liquidacionPrestamo && null != comprobantePago) {
			enviarAlertaLiqCredito();
		}

		return "resultadoLiquidacion";
	}

	/**
	 * Consulta la liquidacion vigente
	 * 
	 * @return
	 */
	public String consultarLiquidacion() {
		LOG.info("Consultando liquidacion #:" + numeroLiquidacion);
		liquidacionPrestamo = liquidacionServicio.consultar(numeroLiquidacion);
		estadosLiquidar.clear();
		estadosLiquidar.add(EstadoComprobantePago.DEP);
		estadosLiquidar.add(EstadoComprobantePago.GEN);
		// cargo el comprobante de pago
		try {
			comprobantePago = comprobantePagoServicio
					.obtenerComprobantePagoVigente(numeroLiquidacion,
							estadosLiquidar);
			
			FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaLiquidacion(funcionario.getCedula(), 
					datos.getSolicitante().getDatosPersonales().getCedula(), liquidacionPrestamo.getCodPreCla().toString(),
					liquidacionPrestamo.getCodPreTip().toString(), liquidacionPrestamo.getNumPreAfi().toString(), liquidacionPrestamo.getOrdPreAfi().toString(),
					liquidacionPrestamo.getTipoLiquidacion(), liquidacionPrestamo.getNumeroLiquidacion().toString());
			super.guardarAuditoria(OperacionEnum.CONSULTAR_LIQUIDACION_PRESTAMO, parametroEvento, liquidacionPrestamo.getNumPreAfi().toString());
			// Fin Auditoria
		} catch (NoTieneComprobanteVigenteException e) {
			msjError = e.getMessage();
			comprobantePago = null;
			return "liquidacionesPago";
		}
		return "resultadoLiquidacion";
	}

	/**
	 * Envia a la pagina de seleccion de dividendos
	 * 
	 * @return
	 */
	public String seleccionarDividendos() {
		msjErrorPagInd = null;
		List<DividendoPrestamo> divActuales = null;
		Date fechaDivMor = null;
		Date fechaDivSaldeb = null;
		Calendar cl = Calendar.getInstance();
		Date fechaActual = cl.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		String estado = prestamoSeleccionado.getEstadoPrestamo().getCodestpre();
		LOG.debug("estado del prestamo:" + estado);

		// INC-2129 Control en Generacion de Comprobantes. inicio
		this.desplegarMensajeComprobanteImpago = false;
		List<DividendoPrestamo> dividendosECO = dividendoPrestamoServicio.obtenerDividendosEnComprobante(
				prestamoSeleccionado.getPrestamoPk(), obtenerDividendosEco());

		if (dividendosECO != null && !dividendosECO.isEmpty()) {
			this.desplegarMensajeComprobanteImpago = true;
			return "pagIndSeleccion";
		}
		// INC-2129 Control en Generacion de Comprobantes. fin
				
		if (estado.equals("ELC") || estado.equals("ELF")) {
			// Validacion para generar comprobante si el credito esta en ELC o
			// ELF y tiene dividendos en mora CB
			dividendosPorPagar = dividendoPrestamoServicio
					.obtenerDividendosEnMora(
							prestamoSeleccionado.getPrestamoPk(),
							obtenerEstadosDivMora());
			saldosLiq = saldoLiquidacionPrestamoServicio
					.obtenerSaldosPorLiquidacion(
							prestamoSeleccionado.getPrestamoPk(),
							obtenerEstadosSaldoLiq());

			if (dividendosPorPagar.size() > 0 && saldosLiq.size() > 0) {
				// Obtengo el primer dividendo seria la menor fecha de pago;
				fechaDivMor = ((DividendoPrestamo) dividendosPorPagar.get(0))
						.getFecpagdiv();
				fechaDivSaldeb = ((SaldoLiquidacionPrestamo) saldosLiq.get(0))
						.getDividendoPrestamo().getFecpagdiv();

				if (sdf.format(fechaDivSaldeb).compareTo(
						sdf.format(fechaDivMor)) > 0) {
					return "pagIndSeleccion";
				} else {
					return "pagIndSeleccionSaldos";
				}
			} else {
				if (dividendosPorPagar.size() > 0) {
					return "pagIndSeleccion";
				} else {
					return "pagIndSeleccionSaldos";
				}
			}
		} else {
			dividendosPorPagar = dividendoPrestamoServicio
					.obtenerDividendosPorPagar(
							prestamoSeleccionado.getPrestamoPk(),
							obtenerEstadosDivPorPagar());
			divActuales = new ArrayList<DividendoPrestamo>();
			for (DividendoPrestamo div : dividendosPorPagar) {
				if (sdf.format(div.getFecpagdiv()).compareTo(
						sdf.format(fechaActual)) <= 0) {
					divActuales.add(div);
				}
			}

			if (divActuales.size() > 0) {
				dividendosPorPagar = null;
				dividendosPorPagar = divActuales;
			} else {
				dividendosPorPagar = null;
			}

			return "pagIndSeleccion";

		}
	}

	/**
	 * Genera pagind de acuerdo a los divindendos seleccionados
	 * 
	 * @return
	 */
	public String generarPagInd() {
		try {
			generarComprobante("dividendosSeleccionados", "DIVIDENDOS");
			flujoPagInd = false;
		} catch (PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
			return null;
		}
		pagindGenerado = true;
		
		
		if (null != comprobantesDePago) {
			enviarAlertaComPagInd();
		}
		
		return "comprobantesPago";
	}

	/**
	 * Genera pagind de acuerdo a los saldos seleccionados
	 * 
	 * @return
	 */
	public String generarPagIndSaldos() {
		try {
			generarComprobante("saldosSeleccionados", "SALDOS");
			flujoPagInd = false;
		} catch (PagIndException e) {
			msjErrorPagInd = e.getMessage();
			pagindGenerado = false;
			return null;
		}
		pagindGenerado = true;
		return "comprobantesPago";
	}

	/**
	 * Consulta comprobantes PagInd
	 * 
	 * @return
	 */
	public String consultarPagInd() {
		comprobantesDePago = comprobantePagoServicio
				.obtenerComprobantesIndividualesVigentes(validarComprobante);
		LOG.debug("total comprobantes:" + comprobantesDePago);
		return "comprobantesPago";
	}

	public String seleccionarComprobante() {
		if (tablaComprobantes.isRowAvailable()) {
			comprobantePago = (ComprobantePago) getHttpServletRequest()
					.getAttribute("comprobante");
			
			LOG.debug("getCodComPagAfi()"
					+ comprobantePago.getPk().getCodComPagAfi());
			LOG.debug("getCodTipComPag()"
					+ comprobantePago.getPk().getCodTipComPag());

			LOG.debug("selecciono comprobante num:"
					+ comprobantePago.getNumeroDocumentoPago());

			try {
				comprobantePago = comprobantePagoServicio
						.obtenerPorPk(comprobantePago.getPk());
			} catch (EntidadNoEncontradaException e) {
				LOG.warn("No se encontro el comprobante:"
						+ comprobantePago.getNumeroDocumentoPago());
				comprobantePago = null;
			}
			
		}
		return null;
	}
	
	public String seleccionarComprobanteP() {
		String pagina = null;
			if (tablaComprobantes.isRowAvailable()) {
				comprobantePago = (ComprobantePago) tablaComprobantes.getRowData();
				
				LOG.debug("getCodComPagAfi()"
						+ comprobantePago.getPk().getCodComPagAfi());
				LOG.debug("getCodTipComPag()"
						+ comprobantePago.getPk().getCodTipComPag());

				LOG.debug("selecciono comprobante num:"
						+ comprobantePago.getNumeroDocumentoPago());

				try {
					comprobantePago = comprobantePagoServicio
							.obtenerPorPk(comprobantePago.getPk());
					
					if (FlujoParametrizacionPQEnum.CCP.name().equals(datos.getFlujoProceso())) {
						FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
						// Inicio Auditoria
						PrestamoPk prestamoPk = comprobantePago.getPrestamo().getPrestamoPk();
						ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaComprobante(funcionario.getCedula(), 
								datos.getSolicitante().getDatosPersonales().getCedula(), prestamoPk.getCodprecla().toString(),
								prestamoPk.getCodpretip().toString(), prestamoPk.getNumpreafi().toString(), prestamoPk.getOrdpreafi().toString(),
								comprobantePago.getTipoComprobante().getTipoComprobante(), comprobantePago.getNumeroDocumentoPago());
						super.guardarAuditoria(OperacionEnum.CONSULTAR_COMPROBANTE_PAGO, parametroEvento, prestamoPk.getNumpreafi().toString());
						// Fin Auditoria
					}
					
					pagina = "comprobanteDividendos";
				} catch (EntidadNoEncontradaException e) {
					LOG.warn("No se encontro el comprobante:"
							+ comprobantePago.getNumeroDocumentoPago());
					comprobantePago = null;
				}
				
			}
			return pagina;
		}
	
	
	public String seleccionarLiquidacion() {

		liquidacionPrestamo = (LiquidacionPrestamo) getHttpServletRequest()
				.getAttribute("liquidacion");

		LOG.info("getNumeroLiquidacion()"
				+ liquidacionPrestamo.getNumeroLiquidacion());
		LOG.info("getNumpreafi()"
				+ liquidacionPrestamo.getPrestamo().getPrestamoPk().getNumpreafi());

		LOG.info("selecciono liquidacion num:"
				+ liquidacionPrestamo.getNumeroLiquidacion());
		prestamoSeleccionado = liquidacionPrestamo.getPrestamo();

		numeroLiquidacion = liquidacionPrestamo.getNumeroLiquidacion();
		return consultarLiquidacion();
	}
	

	/**
	 * Genera PagInd para dividendos y saldos
	 * 
	 * @param checkBoxName
	 * @throws PagIndException
	 */
	private void generarComprobante(String checkBoxName, String tipo)
			throws PagIndException {

		String[] dividendosSeleccionados = getHttpServletRequest()
				.getParameterValues(checkBoxName);
		List<Long> dividendosAPagar = new ArrayList<Long>();
		if (tipo.equals("DIVIDENDOS")) {
			dividendosAPagar = PagIndHelper.validarDividendos(
					dividendosSeleccionados, dividendosPorPagar);
		} else {// SALDOS
			// INC-2129 Control en Generacion de Comprobantes.
			List<SaldoLiquidacionPrestamo> saldosPorPagar = new ArrayList<SaldoLiquidacionPrestamo>();
			List<Long> valoresIds = new ArrayList<Long>();
			
			for(SaldoLiquidacionPrestamo saldoLiquidacionPrestamo :  saldosLiq) {
				if(saldoLiquidacionPrestamo.isSeleccionado()){
					valoresIds.add(saldoLiquidacionPrestamo.getDividendoPrestamo().getDividendoPrestamoPk().getNumdiv());
					saldosPorPagar.add(saldoLiquidacionPrestamo);
				}
			}
			
			if(valoresIds.isEmpty()){
				LOG.info("no ha seleccionado nada");
				throw new PagIndException("Seleccione al menos un dividendo.");
			} else {
				String[] valores = new String[valoresIds.size()];
				int i =0;
				for(Long id : valoresIds){
					valores[i] = String.valueOf(id);
					i++;
				}
				dividendosAPagar = PagIndHelper.validarDividendosSaldos(valores, saldosLiq);
			}
			//dividendosAPagar = PagIndHelper.validarDividendosSaldos(dividendosSeleccionados, saldosLiq);
		}

		try {
			FuncionarioHandler funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			comprobantePagoServicio.generarComprobantePagoIndividual(
					dividendosAPagar, situacionPrestamo);
			comprobantesDePago = comprobantePagoServicio
					.obtenerComprobanteIndividualVigente(validarComprobante);
			
			// Inicio Auditoria
			PrestamoPk prestamoPk = situacionPrestamo.getPrestamoPk();
			ComprobantePago comprobantePago = comprobantesDePago.get(0);
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAuditoriaComprobante(funcionario.getCedula(), 
					datos.getSolicitante().getDatosPersonales().getCedula(), prestamoPk.getCodprecla().toString(),
					prestamoPk.getCodpretip().toString(), prestamoPk.getNumpreafi().toString(), prestamoPk.getOrdpreafi().toString(),
					comprobantePago.getTipoComprobante().getTipoComprobante(), comprobantePago.getNumeroDocumentoPago(),
					comprobantePago.getValorTotalComprobante().toString());
			super.guardarAuditoria(OperacionEnum.GENERAR_COMPROBANTE_INDIVIDUAL, parametroEvento, prestamoPk.getNumpreafi().toString());
			// Fin Auditoria
			
			LOG.debug("total comprobantes:" + comprobantesDePago);
		} catch (GenerarComprobantePagoIndividualExcepcion e) {
			throw new PagIndException(e.getMessage());
		}
		LOG.debug("pagind pagindGenerado!");
		msjErrorPagInd = null;
		saldosLiq = null;
		dividendosPorPagar = null;
		// TODO deshabilitar habilitar?
		// habilitarGenerarComp = false;
	}

	/**
	 * Metodo para deshabilitar todos los botones
	 */
	public void resetear() {
		cleanOptions();
		msjError = null;
	}

	/* getters and setters */

	/**
	 * @return the habilitarCalcularLiq
	 */
	public boolean isHabilitarCalcularLiq() {
		return habilitarCalcularLiq;
	}

	/**
	 * @param habilitarCalcularLiq
	 *            the habilitarCalcularLiq to set
	 */
	public void setHabilitarCalcularLiq(boolean liquidar) {
		this.habilitarCalcularLiq = liquidar;
	}

	/**
	 * @return the habilitarCalcularLiqFr
	 */
	public boolean isHabilitarCalcularLiqFondos() {
		return habilitarCalcularLiqFondos;
	}

	/**
	 * @param habilitarCalcularLiqFr
	 *            the habilitarCalcularLiqFr to set
	 */
	public void setHabilitarCalcularLiqFondos(boolean habilitarCalcularLiqFondos) {
		this.habilitarCalcularLiqFondos = habilitarCalcularLiqFondos;
	}

	/**
	 * @return the habilitarVerDetalle
	 */
	public boolean isHabilitarVerDetalle() {
		return habilitarVerDetalle;
	}

	/**
	 * @param habilitarVerDetalle
	 *            the habilitarVerDetalle to set
	 */
	public void setHabilitarVerDetalle(boolean verDetalle) {
		this.habilitarVerDetalle = verDetalle;
	}

	/**
	 * @return the msjCalcularLiq
	 */
	public String getMsjCalcularLiq() {
		return msjCalcularLiq;
	}

	/**
	 * @param msjCalcularLiq
	 *            the msjCalcularLiq to set
	 */
	public void setMsjCalcularLiq(String msjLiquidar) {
		this.msjCalcularLiq = msjLiquidar;
	}

	/**
	 * @return the msjCalcularLiqFondos
	 */
	public String getMsjCalcularLiqFondos() {
		return msjCalcularLiqFondos;
	}

	/**
	 * @param msjCalcularLiqFR
	 *            the msjCalcularLiqFr to set
	 */
	public void setMsjCalcularLiqFondos(String msjCalcularLiqFondos) {
		this.msjCalcularLiqFondos = msjCalcularLiqFondos;
	}

	/**
	 * @return the msjError
	 */
	public String getMsjError() {
		return msjError;
	}

	/**
	 * @param msjError
	 *            the msjError to set
	 */
	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}

	/**
	 * @return the habilitarGenerarComp
	 */
	public boolean isHabilitarGenerarComp() {
		return habilitarGenerarComp;
	}

	/**
	 * @param habilitarGenerarComp
	 *            the habilitarGenerarComp to set
	 */
	public void setHabilitarGenerarComp(boolean habilitarGenerarComp) {
		this.habilitarGenerarComp = habilitarGenerarComp;
	}

	/**
	 * @return the msjGenerarComp
	 */
	public String getMsjGenerarComp() {
		return msjGenerarComp;
	}

	/**
	 * @param msjGenerarComp
	 *            the msjGenerarComp to set
	 */
	public void setMsjGenerarComp(String msjGenerarComp) {
		this.msjGenerarComp = msjGenerarComp;
	}

	/**
	 * @return the calculoLiquidacion
	 */
	public CalculoLiquidacion getCalculoLiquidacion() {
		return calculoLiquidacion;
	}

	/**
	 * @param calculoLiquidacion
	 *            the calculoLiquidacion to set
	 */
	public void setCalculoLiquidacion(CalculoLiquidacion calculoLiquidacion) {
		this.calculoLiquidacion = calculoLiquidacion;
	}

	/**
	 * @return the prestamoSeleccionado
	 */
	public Prestamo getPrestamoSeleccionado() {
		return prestamoSeleccionado;
	}

	/**
	 * @param prestamoSeleccionado
	 *            the prestamoSeleccionado to set
	 */
	public void setPrestamoSeleccionado(Prestamo prestamoSeleccionado) {
		this.prestamoSeleccionado = prestamoSeleccionado;
	}

	/**
	 * @return the habilitarConsultaLiq
	 */
	public boolean isHabilitarConsultaLiq() {
		return habilitarConsultaLiq;
	}

	/**
	 * @param habilitarConsultaLiq
	 *            the habilitarConsultaLiq to set
	 */
	public void setHabilitarConsultaLiq(boolean habilitarConsultaLiq) {
		this.habilitarConsultaLiq = habilitarConsultaLiq;
	}

	/**
	 * @return the habilitarConsultaGC
	 */
	public boolean isHabilitarConsultaGC() {
		return habilitarConsultaGC;
	}

	/**
	 * @param habilitarConsultaGC
	 *            the habilitarConsultaGC to set
	 */
	public void setHabilitarConsultaGC(boolean habilitarConsultaGC) {
		this.habilitarConsultaGC = habilitarConsultaGC;
	}

	/**
	 * @return the liquidacionPrestamo
	 */
	public LiquidacionPrestamo getLiquidacionPrestamo() {
		return liquidacionPrestamo;
	}

	/**
	 * @param liquidacionPrestamo
	 *            the liquidacionPrestamo to set
	 */
	public void setLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo) {
		this.liquidacionPrestamo = liquidacionPrestamo;
	}

	/**
	 * @return the comprobantePago
	 */
	public ComprobantePago getComprobantePago() {
		return comprobantePago;
	}

	/**
	 * @param comprobantePago
	 *            the comprobantePago to set
	 */
	public void setComprobantePago(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	/**
	 * @return the dividendosPorPagar
	 */
	public List<DividendoPrestamo> getDividendosPorPagar() {
		return dividendosPorPagar;
	}

	/**
	 * @param dividendosPorPagar
	 *            the dividendosPorPagar to set
	 */
	public void setDividendosPorPagar(List<DividendoPrestamo> dividendosPorPagar) {
		this.dividendosPorPagar = dividendosPorPagar;
	}

	/**
	 * @return the msjErrorPagInd
	 */
	public String getMsjErrorPagInd() {
		return msjErrorPagInd;
	}

	/**
	 * @param msjErrorPagInd
	 *            the msjErrorPagInd to set
	 */
	public void setMsjErrorPagInd(String msjErrorPagInd) {
		this.msjErrorPagInd = msjErrorPagInd;
	}

	/**
	 * @return the saldosLiq
	 */
	public List<SaldoLiquidacionPrestamo> getSaldosLiq() {
		return saldosLiq;
	}

	/**
	 * @param saldosLiq
	 *            the saldosLiq to set
	 */
	public void setSaldosLiq(List<SaldoLiquidacionPrestamo> saldosLiq) {
		this.saldosLiq = saldosLiq;
	}

	/**
	 * @return the comprobantesDePago
	 */
	public List<ComprobantePago> getComprobantesDePago() {
		return comprobantesDePago;
	}

	/**
	 * @param comprobantesDePago
	 *            the comprobantesDePago to set
	 */
	public void setComprobantesDePago(List<ComprobantePago> comprobantesDePago) {
		this.comprobantesDePago = comprobantesDePago;
	}

	/**
	 * @return the pagindGenerado
	 */
	public boolean isPagindGenerado() {
		return pagindGenerado;
	}

	/**
	 * @param pagindGenerado
	 *            the pagindGenerado to set
	 */
	public void setPagindGenerado(boolean pagindGenerado) {
		this.pagindGenerado = pagindGenerado;
	}

	public BigDecimal getValorRealFondos() {
		valorRealFondos = calcularValorFondos();
		return valorRealFondos;

	}

	public void setValorRealFondos(BigDecimal valorRealFondos) {
		this.valorRealFondos = valorRealFondos;
	}

	public boolean isHabilitarLiquidarFondos() {
		habilitarLiquidarFondos = valorRealFondos.longValue() >= calculoLiquidacion
				.getValorPorCancelar().longValue();
		return habilitarLiquidarFondos;

	}

	public void setHabilitarLiquidarFondos(boolean habilitarLiquidarFondos) {
		this.habilitarLiquidarFondos = habilitarLiquidarFondos;
	}

	public List<RequisitosCruce> getRequisitosCruceCuentas() {
		return requisitosCruceCuentas;
	}

	public void setRequisitosCruceCuentas(
			List<RequisitosCruce> requisitosCruceCuentas) {
		this.requisitosCruceCuentas = requisitosCruceCuentas;
	}

	public boolean isHabilitarRequisitosCruce() {
		return habilitarRequisitosCruce;
	}

	public void setHabilitarRequisitosCruce(boolean habilitarRequisitosCruce) {
		this.habilitarRequisitosCruce = habilitarRequisitosCruce;
	}
	

	private List<Long> obtenerTiposCreditoValidacion() {
		List<Long> tiposCredito = new ArrayList<Long>();
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_MIGRADOS_HOST);
		return tiposCredito;
	}

	private List<String> obtenerEstadosCreditoVig() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTES_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_CRE);
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_ANTICIPADA);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CRUSE_FONDOS);
		return estados;
	}

	private List<String> obtenerEstadosCreditoLiq() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_ANTICIPADA);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CRUSE_FONDOS);
		return estados;
	}

	private List<String> obtenerEstadosDivPorPagar() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_MORA);
		estados.add(EstadosCredito.ESTADO_REGISTRADO);
		return estados;
	}

	private List<String> obtenerEstadosDivMora() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_MORA);
		return estados;
	}

	private List<String> obtenerEstadosSaldoLiq() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTIAS_DIV);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_DIV);
		return estados;
	}

	private List<Long> obtenerTiposSituacionCredito() {
		List<Long> tipos = new ArrayList<Long>();
		tipos.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
		tipos.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
		tipos.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		return tipos;
	}

	private List<String> obtenerEstadosCreComprobante() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTES_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_ANTICIPADA);
		return estados;
	}
	
	private List<String> obtenerEstadosCreComprobanteConsulta() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_VIGENTE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_FONDOS_CRE);
		estados.add(EstadosCredito.ESTADO_LIQUIDACION_CESANTES_CRE);
		return estados;
	}

	private List<EstadoComprobantePago> obtenerEstadosComprobante() {
		List<EstadoComprobantePago> estadosComprobante = new ArrayList<EstadoComprobantePago>();
		estadosComprobante.add(EstadoComprobantePago.GEN);
		return estadosComprobante;
	}

	private List<String> obtenerTiposComprobante() {
		List<String> tipos = new ArrayList<String>();
		tipos.add(TiposCredito.TIPO_COMPROBANTE_SAL);
		tipos.add(TiposCredito.TIPO_COMPROBANTE_SAL_BIESS);
		tipos.add(TiposCredito.TIPO_COMPROBANTE_PAG);
		tipos.add(TiposCredito.TIPO_COMPROBANTE_PAG_BIESS);
		return tipos;
	}
	
	private List<String> obtenerTiposComprobantePagoIndividual(String tipoCredito) {
		List<String> tipos = new ArrayList<String>();
		if (TiposCredito.TIPO_COMPROBANTE_SAL.equals(tipoCredito)) {
			tipos.add(TiposCredito.TIPO_COMPROBANTE_SAL);
			tipos.add(TiposCredito.TIPO_COMPROBANTE_SAL_BIESS);
		}
		
		if (TiposCredito.TIPO_COMPROBANTE_PAG.equals(tipoCredito)) {
			tipos.add(TiposCredito.TIPO_COMPROBANTE_PAG);
			tipos.add(TiposCredito.TIPO_COMPROBANTE_PAG_BIESS);
		}
		return tipos;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(Selection selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return the selectedItem
	 */
	public Selection getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @return the datos
	 */
	public DatosBean getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	/**
	 * Metodo que limpia las opciones de recaudacion para un nuevo request.
	 * 
	 */
	public void cleanOptions() {
		msjCalcularLiq = null;		
		msjCalcularLiqFondos = null;
		msjGenerarComp = null;
		msjErrorPagInd = null;
		calculoLiquidacion = null;
		numeroLiquidacion = null;
		liquidacionPrestamo = null;
		comprobantePago = null;
		dividendosPorPagar = null;
		saldosLiq = null;
		comprobantesDePago = null;
		pagindGenerado = false;
		listaLiquidaciones = null;
	}
	
	/**
	 * Metodo que resetea los botones con opciones de recaudacion y regresa a la pagina de seleccion.
	 * 
	 * @return String
	 */
	public String cancelar() {
		resetear();
		
		return "consultaCreditos";
	}
	

	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado un comprobante de pago individual.
	 * 
	 */
	private void enviarAlertaComPagInd() {
		try {
			String templatePath = "ec/gov/iess/planillaspq/web/alertas/templates/email/compagind.ftl";                    
	        /* Parametros del mensaje */
	        Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", datos.getSolicitante().getDatosPersonales().getApellidosNombres());
	        alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
	        DatosPersonales informacionAfiliado = new DatosPersonales();
			informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
			informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());
	        AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
		} catch (Exception e) {
			LOG.error("Error al enviar alerta de generacion de comprobante de pago individual al afiliado: " 
					+ datos.getSolicitante().getDatosPersonales().getCedula(), e);
		}
	}

	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado una liquidacion de credito.
	 * 
	 */
	private void enviarAlertaLiqCredito() {
		try {
			String templatePath = "ec/gov/iess/planillaspq/web/alertas/templates/email/liqcredito.ftl";                    
	        /* Parametros del mensaje */
	        Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", datos.getSolicitante().getDatosPersonales().getApellidosNombres());
	        alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
	        DatosPersonales informacionAfiliado = new DatosPersonales();
			informacionAfiliado.setApellidosNombres(datos.getSolicitante().getDatosPersonales().getApellidosNombres());
			informacionAfiliado.setCedula(datos.getSolicitante().getDatosPersonales().getCedula());
			informacionAfiliado.setCelular(datos.getSolicitante().getDatosPersonales().getCelular());
			informacionAfiliado.setEmail(datos.getSolicitante().getDatosPersonales().getEmail());
	        AlertUtil.enviarAlerta(alertUserHelper, informacionAfiliado, templatePath, alertData, null, AlertType.EMAIL);
		} catch (Exception e) {
			LOG.error("Error al enviar alerta de generacion de liquidacion de credito al afiliado: " 
					+ datos.getSolicitante().getDatosPersonales().getCedula(), e);
		}
	}

	/**
	 * @return the desplegarMensajeComprobanteImpago
	 */
	public boolean isDesplegarMensajeComprobanteImpago() {
		return desplegarMensajeComprobanteImpago;
	}

	/**
	 * @param desplegarMensajeComprobanteImpago the desplegarMensajeComprobanteImpago to set
	 */
	public void setDesplegarMensajeComprobanteImpago(
			boolean desplegarMensajeComprobanteImpago) {
		this.desplegarMensajeComprobanteImpago = desplegarMensajeComprobanteImpago;
	}

	/**
	 * Selecciona todos los dividendos.
	 * 
	 * @param event
	 */
	public void seleccionarTodos(ValueChangeEvent event) {
		// INC-2129 Control en Generacion de Comprobantes.
		if(saldosLiq!=null && !saldosLiq.isEmpty()){
			for(SaldoLiquidacionPrestamo saldoLiquidacionPrestamo: saldosLiq){
				saldoLiquidacionPrestamo.setSeleccionado((Boolean)event.getNewValue());
			}
		}
	}
	
	/**
	 * Selecciona un dividendo.
	 */
	public void seleccionarSaldo() {
		// INC-2129 Control en Generacion de Comprobantes.
		saldoLiquidacionPrestamoSeleccionado.setSeleccionado(true);
	}
	
	/**
	 * Deselecciona un dividendo.
	 */
	public void deseleccionarSaldo() {
		// INC-2129 Control en Generacion de Comprobantes.
		saldoLiquidacionPrestamoSeleccionado.setSeleccionado(false);
		this.setTodos(false);
	}

	/**
	 * @return the todos
	 */
	public boolean isTodos() {
		return todos;
	}

	/**
	 * @param todos the todos to set
	 */
	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	/**
	 * @return the saldoLiquidacionPrestamoSeleccionado
	 */
	public SaldoLiquidacionPrestamo getSaldoLiquidacionPrestamoSeleccionado() {
		return saldoLiquidacionPrestamoSeleccionado;
	}

	/**
	 * @param saldoLiquidacionPrestamoSeleccionado the saldoLiquidacionPrestamoSeleccionado to set
	 */
	public void setSaldoLiquidacionPrestamoSeleccionado(SaldoLiquidacionPrestamo saldoLiquidacionPrestamoSeleccionado) {
		this.saldoLiquidacionPrestamoSeleccionado = saldoLiquidacionPrestamoSeleccionado;
	}

	public PrestamoBiess getPrestamoSeleccionadoBiess() {
		return prestamoSeleccionadoBiess;
	}

	public void setPrestamoSeleccionadoBiess(PrestamoBiess prestamoSeleccionadoBiess) {
		this.prestamoSeleccionadoBiess = prestamoSeleccionadoBiess;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public List<LiquidacionPrestamo> getListaLiquidaciones() {
		return listaLiquidaciones;
	}

	public void setListaLiquidaciones(List<LiquidacionPrestamo> listaLiquidaciones) {
		this.listaLiquidaciones = listaLiquidaciones;
	}

	public HtmlDataTable getTablaComprobantesPago() {
		return tablaComprobantesPago;
	}

	public void setTablaComprobantesPago(HtmlDataTable tablaComprobantesPago) {
		this.tablaComprobantesPago = tablaComprobantesPago;
	}

	public HtmlDataTable getTablaComprobantesLiquidacion() {
		return tablaComprobantesLiquidacion;
	}

	public void setTablaComprobantesLiquidacion(
			HtmlDataTable tablaComprobantesLiquidacion) {
		this.tablaComprobantesLiquidacion = tablaComprobantesLiquidacion;
	}

	public HtmlDataTable getTablaComprobantes() {
		return tablaComprobantes;
	}

	public void setTablaComprobantes(HtmlDataTable tablaComprobantes) {
		this.tablaComprobantes = tablaComprobantes;
	}

	public boolean isFlujoPagInd() {
		return flujoPagInd;
	}

	public void setFlujoPagInd(boolean flujoPagInd) {
		this.flujoPagInd = flujoPagInd;
	}

	public boolean isHabilitarVerComprobantes() {
		return habilitarVerComprobantes;
	}

	public void setHabilitarVerComprobantes(boolean habilitarVerComprobantes) {
		this.habilitarVerComprobantes = habilitarVerComprobantes;
	}

}