/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import org.richfaces.component.html.HtmlDataTable;

import ec.fin.biess.conozcasucliente.exception.ClienteExcepcion;
import ec.fin.biess.conozcasucliente.modelo.persistence.Cliente;
import ec.fin.biess.conozcasucliente.service.ClienteServicioLocal;
import ec.fin.biess.creditos.pq.dao.PrestamoBiessDao;
import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicio;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.hl.servicio.EmpleadorServicio;
import ec.gov.iess.planillaspq.web.bean.DatosBean;
import ec.gov.iess.planillaspq.web.bean.PrestamoBean;
import ec.gov.iess.planillaspq.web.enumeration.FlujoParametrizacionPQEnum;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * 
 * <b> Consulta el crédito quirografario. </b>
 * 
 * @author pmlopez,cbastidas
 * @version $Revision: 1.15 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:30:30 $]
 *          </p>
 */
public class ConsultaCreditoBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 2425621223704034431L;

	private static final LoggerBiess LOG = LoggerBiess.getLogger(ConsultaCreditoBean.class);

	@EJB(name = "EmpleadorServicioImpl/local")
	private EmpleadorServicio empleadorServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;
	
	@EJB(name = "PrestamoBiessDaoEjb/local")
	private PrestamoBiessDao prestamoBiessDao;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	@EJB(name = "SolicitanteServicioImpl/local")
	private SolicitanteServicio solicitanteServicio;
	
	@EJB(name = "ClienteServicioImpl/local")
	private ClienteServicioLocal clienteServicio;
	
	private DatosBean datos;
	private CreditoAccionBean creditoAccion;
	private PrestamoBean prestamoBean;	
	private String mensaje;
	private boolean tieneNovaciones;
	private boolean muestraInteresGracia;
	private BigDecimal intPrimerDividendo;
	private HtmlDataTable tablaPrestamos;
	private List<PrestamoBiess> listaPrestamos;
	private String cedulaAfiliado;
	private String mensajeError;
	private String tipoComprobante;
	private boolean tieneComprobantes;
	
	public ConsultaCreditoBean() {
	}
	
	/**
	 * Poscontructor.
	 */
	@PostConstruct
	public void init() {
		limpiarPantalla();
		if (isPostback()) {
			cedulaAfiliado = null;
		}
	}
	
	public String inicializarProcesoCuentaIndividual() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.CCI.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			obtenerPrestamos();
			if (listaPrestamos.size() >0 ) {
				flujo = "consultarCreditos";
			} else {
				mensajeError = "El asegurado no posee informaci\u00F3n de  cuenta individual de pr\u00E9stamos quirografarios";
			}
		} catch (SolicitanteExcepcion se) {
			mensajeError = "No se encontro informaci\u00F3n para el n\u00FAmero de c\u00E9dula " + cedulaAfiliado;
		}
		creditoAccion.setMsjError(mensajeError);
		return flujo;
	}
	
	public String inicializarProcesoGenerarComprobante() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.GCPI.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			obtenerPrestamos();
			if (listaPrestamos.size() >0 ) {
				flujo = "consultarCreditos";
			} else {
				mensajeError = "El asegurado no posee cr\u00E9ditos vigentes";
			}
		} catch (SolicitanteExcepcion se) {
			mensajeError = "No se encontro informaci\u00F3n para el n\u00FAmero de c\u00E9dula " + cedulaAfiliado;
		}
		creditoAccion.setMsjError(mensajeError);
		return flujo;
	}
	
	public String inicializarProcesoGenerarLiquidacion() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.GLA.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			obtenerPrestamos();
			if (listaPrestamos.size() >0 ) {
				flujo = "consultarCreditos";
			} else {
				mensajeError = "No se encontro informaci\u00F3n para el n\u00FAmero de c\u00E9dula " + cedulaAfiliado;
			}
		} catch (SolicitanteExcepcion se) {
			mensajeError = "No se encontro información para el número de cedula " + cedulaAfiliado;
		}
		return flujo;
	}
	
	public String inicializarConsultaComprobante() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.CCP.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			flujo = creditoAccion.consultaComprobantes(null);
			if (flujo == null && creditoAccion.getMsjError() != null) {
				mensajeError = creditoAccion.getMsjError();
			}
		} catch (SolicitanteExcepcion se) {
			mensajeError = "No se encontro información para el número de cedula " + cedulaAfiliado;
		}
		return flujo;
	}
	
	public String inicializarConsultaComprobanteAnulacion() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.ACPI.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			flujo = creditoAccion.consultaComprobantes(tipoComprobante);
			if (flujo == null && creditoAccion.getMsjError() != null) {
				//mensajeError = creditoAccion.getMsjError();
				tieneComprobantes = false;
				creditoAccion.setHabilitarVerComprobantes(false);
			} else {
				tieneComprobantes = true;
				creditoAccion.setHabilitarVerComprobantes(true);
			}
			
		} catch (SolicitanteExcepcion se) {
			mensajeError = "No se encontro información para el número de cedula " + cedulaAfiliado;
		}
		return flujo;
	}
	
	public String inicializarConsultaComprobanteAnulacionLiquidacion() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.ALA.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			flujo = creditoAccion.consultaComprobantes(tipoComprobante);
			/*if (flujo == null && creditoAccion.getMsjError() != null) {
				//mensajeError = creditoAccion.getMsjError();
			}*/
		} catch (SolicitanteExcepcion se) {
			mensajeError = "El asegurado no tiene liquidaciones generadas ni en comprobante de pago que necesiten ser Canceladas";
		}
		return flujo;
	}
	
	public String inicializarConsultaLiquidacion() {
		String flujo = null;
		limpiarPantalla();
		try {
			datos.setFlujoProceso(FlujoParametrizacionPQEnum.CLA.name());
			cargarSolicitante();
			creditoAccion.setDatos(datos);
			flujo = creditoAccion.consultaComprobantes(null);
			if (flujo == null && creditoAccion.getMsjError() != null) {
				mensajeError = creditoAccion.getMsjError();
			}
		} catch (SolicitanteExcepcion se) {
			mensajeError = "No se encontro información para el número de cedula " + cedulaAfiliado;
		}
		return flujo;
	}
	
	/**
	 * <b> Busca si existen prestamos para los flujos de comprobante y liquidacion. </b>
	 * 
	 */
	public void obtenerPrestamos() {
		if (FlujoParametrizacionPQEnum.GCPI.name().equals(datos.getFlujoProceso())) {
			listaPrestamos = prestamoBiessDao.obtenerPrestamosCedulaEstadosTipoCredito(cedulaAfiliado, obtenerEstadosCreditoComprobante(), obtenerTiposCredito());
		} else if (FlujoParametrizacionPQEnum.GLA.name().equals(datos.getFlujoProceso())) {
			listaPrestamos = prestamoBiessDao.obtenerPrestamosCedulaEstadosTipoCredito(cedulaAfiliado, obtenerEstadosCreditoLiquidacion(), obtenerTiposCredito());
		} else if (FlujoParametrizacionPQEnum.CCI.name().equals(datos.getFlujoProceso())) {
			listaPrestamos = prestamoBiessDao.obtenerPrestamosCedulaEstadosTipoCredito(cedulaAfiliado, obtenerEstadosCredito(), obtenerTiposCredito());
		}
	}
	
	/**
	 * Metodo que limpia los campos de ingreso de datos antes de retornar a la p�gina principal
	 */
	public String salir() {
		limpiarPantalla();
		cedulaAfiliado = null;
		return "principalSalir";
	}
	
	/**
	 * Metodo que limpia los campos de ingreso de datos antes de retornar a la p�gina principal
	 */
	public void limpiarPantalla() {
		datos = new DatosBean();
		listaPrestamos = new ArrayList<PrestamoBiess>();
		mensajeError = null;
		tieneComprobantes = false;
		creditoAccion.resetear();
	}

	private List<Long> obtenerTiposCredito() {
		List<Long> tiposCredito = new ArrayList<Long>();
		tiposCredito.add(TiposCredito.TIPO_CREDITO_AFILIADO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_ZAFRERO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		return tiposCredito;
	}

	private List<String> obtenerEstadosCreditoComprobante() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadoCredito.VIG.name());
		estados.add(EstadoCredito.ELI.name());
		estados.add(EstadoCredito.ELC.name());
		estados.add(EstadoCredito.ELF.name());
		
		return estados;
	}
	
	private List<String> obtenerEstadosCreditoLiquidacion() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadoCredito.VIG.name());
		
		return estados;
	}
	
	private List<String> obtenerEstadosCredito() {
		List<String> estados = new ArrayList<String>();
		estados.add(EstadoCredito.VIG.name());
		estados.add(EstadoCredito.CAN.name());
		return estados;
	}
	
	// metodos privados del componenteO
		private void cargarSolicitante() throws SolicitanteExcepcion {
				try {
					Solicitante solicitante = solicitanteServicio.obtenerSolicitanteMasCargas(
							cedulaAfiliado, null);
					
					List<Cliente> clientes = null;
					try {
						clientes = clienteServicio.obtenerPorCedula(cedulaAfiliado);
					} catch (ClienteExcepcion e) {
						LOG.error("fecha ult. act:" + e);
					}
					if (clientes == null || clientes.isEmpty() || clientes.get(0).getFechaActualizacion() == null) {
						LOG.debug("no ha actualizado nunca.");
						solicitante.getDatosPersonales().setDireccionDomicilio("");
						datos.setActualizado(false);
						datos.setFechaUltimaActualizacion(null);
					} else if (clientes.get(0).getFechaActualizacion() != null) { 
						// si ha actualizado alguna vez permite avanzar
						datos.setActualizado(true);
						datos.setFechaUltimaActualizacion(clientes.get(0).getFechaActualizacion());
					}
						
					datos.setSolicitante(solicitante);
					datos.setEmailDb(solicitante.getDatosPersonales().getEmail());
						
					InformacionIessServicioDto informacionIessServicioDto = new InformacionIessServicioDto();
					informacionIessServicioDto.setCedula(cedulaAfiliado);
					this.datos.setInformacionIessServicio(informacionIessServicioDto);
				} catch (SolicitanteExcepcion e) {
					LOG.error("Exception", e);
					throw e;
				}
		}
		
		/**
		 * Cuando selecciona un prestamo
		 * 
		 * @return
		 */
		public String seleccionar() {
			String pagina = null;
			if (tablaPrestamos.isRowAvailable()) {
				PrestamoBiess prestamoBiessTmp = (PrestamoBiess) tablaPrestamos.getRowData();
				creditoAccion.setPrestamoSeleccionadoBiess(prestamoBiessTmp);
				creditoAccion.setDatos(datos);
				pagina = creditoAccion.seleccionar();
			}
			
			return pagina;
		}
	
		
		/**
		 * Método que se encarga de totalizar el valor de interés de un
		 * prestamo.
		 * 
		 * @return total de interés
		 */
		public double getTotalInteres() {
			double totalInteres = 0d;
			if (this.prestamoBean != null
					&& this.prestamoBean.getPrestamo() != null
					&& this.prestamoBean.getPrestamo().getDividendosPrestamo() != null) {
				for (DividendoPrestamo dividendoPrestamo : this.prestamoBean
						.getPrestamo().getDividendosPrestamo()) {
					totalInteres = totalInteres + dividendoPrestamo.getIntsalcap();
				}
			}

			return totalInteres;
		}

		public double getTotalCapital() {
			double totalCapital = 0d;
			if (this.prestamoBean != null
					&& this.prestamoBean.getPrestamo() != null
					&& this.prestamoBean.getPrestamo().getDividendosPrestamo() != null) {
				for (DividendoPrestamo dividendoPrestamo : this.prestamoBean
						.getPrestamo().getDividendosPrestamo()) {
					totalCapital = totalCapital + dividendoPrestamo.getValcapamo();
				}
			}
			return totalCapital;
		}
		
		/**
		 * Metodo para totalizar el interes de gracia de una persona
		 * 
		 * @return
		 */
		public double getTotalInteresGracia() {
			double totalInteresGracia = 0d;
			if (this.prestamoBean != null
					&& this.prestamoBean.getPrestamo() != null
					&& this.prestamoBean.getPrestamo().getDividendosPrestamo() != null) {
				for (DividendoPrestamo dividendoPrestamo : this.prestamoBean
						.getPrestamo().getDividendosPrestamo()) {
					totalInteresGracia += dividendoPrestamo.getIntpergra();
				}
			}
			return totalInteresGracia;
		}

	/**
	 * Returns the value of datos.
	 * 
	 * @return datos
	 */
	public DatosBean getDatos() {
		return datos;
	}

	/**
	 * Sets the value for datos.
	 * 
	 * @param datos
	 */
	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	/**
	 * @return the creditoAccion
	 */
	public CreditoAccionBean getCreditoAccion() {
		return creditoAccion;
	}

	/**
	 * @param creditoAccion
	 *            the creditoAccion to set
	 */
	public void setCreditoAccion(CreditoAccionBean creditoAccion) {
		this.creditoAccion = creditoAccion;
	}
	
	public PrestamoBean getPrestamoBean() {
			LOG.debug("obtiene detalle prestamo.");
			if (creditoAccion.getPrestamoSeleccionadoBiess() != null) {
				// en el caso que venga de la lista de creditos
				PrestamoBean prestamoBeanLista = new PrestamoBean();
				Prestamo prestamo = prestamoServicio
						.getPrestamoByPk(creditoAccion
								.getPrestamoSeleccionado().getCreditoPk());
				muestraInteresGracia = CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial());
				prestamoBeanLista.setPrestamo(prestamo);
				prestamoBeanLista.setInstitucionFinanciera(empleadorServicio
						.getEmpleador(creditoAccion.getPrestamoSeleccionado()
								.getRucempinsfin()));
				setIntPrimerDividendo(new BigDecimal(calculaValorInteresPrimeraCuota(prestamo.getDividendosPrestamo(),
						CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial()))));
				this.setPrestamoBean(prestamoBeanLista);
			}
		return prestamoBean;
	}
	
	/**
	 * Obtiene el valor de la primera cuota para creditos emergentes
	 * 
	 * @param listaDividendos
	 * @return
	 */
	private Double calculaValorInteresPrimeraCuota(List<DividendoPrestamo> listaDividendos, boolean esEmergente) {
		BigDecimal interesFinMesCuota = BigDecimal.ZERO;
		BigDecimal plazoGracia = obtenerMesesGracia(esEmergente);
		// Se obtiene la 4ta cuota para el calculo del interes desde la concesion del credito hasta la primera cuota
		if (listaDividendos != null && !listaDividendos.isEmpty()) {
			DividendoPrestamo dividendo = listaDividendos.get(plazoGracia.intValue());
			BigDecimal dividendoPrestamo = new BigDecimal(dividendo.getIntsalcap());
			LOG.debug("Dividendo prestamo primera cuota: " + dividendo.getPrestamo().getPrestamoPk().getNumpreafi());
			BigDecimal interesProporcionalCuota = dividendoPrestamo.multiply(plazoGracia)
					.divide(new BigDecimal(listaDividendos.size()).subtract(plazoGracia), 2, RoundingMode.HALF_UP);
			interesFinMesCuota = new BigDecimal(dividendo.getIntpergra()).subtract(interesProporcionalCuota);
			interesFinMesCuota = interesFinMesCuota.setScale(2, RoundingMode.HALF_UP);
		}

		return interesFinMesCuota.doubleValue();
	}
	
	/**
	 * Obtiene los meses de gracia
	 * 
	 * @return
	 */
	private BigDecimal obtenerMesesGracia(boolean esEmergente) {
		BigDecimal resp = BigDecimal.ZERO;
		if (esEmergente) {
			try {
				resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
						BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico();
			} catch (ParametroBiessException e) {
				LOG.error("Error al leer parametro de meses de gracia >>", e);
			}
		}
		return resp;
	}
	
	public void setPrestamoBean(PrestamoBean prestamoBean) {
		this.prestamoBean = prestamoBean;
	}

	public String getMensaje() {
		return mensaje;
	}
	
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Metodo que indica si el request es un postback.
	 * 
	 * @return
	 */
	private boolean isPostback() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    
	    return context.getRenderKit().getResponseStateManager().isPostback(context);
	}
	
	public boolean isTieneNovaciones() {
		return tieneNovaciones;
	}

	public void setTieneNovaciones(boolean tieneNovaciones) {
		this.tieneNovaciones = tieneNovaciones;
	}

	public boolean isMuestraInteresGracia() {
		return muestraInteresGracia;
	}

	public void setMuestraInteresGracia(boolean muestraInteresGracia) {
		this.muestraInteresGracia = muestraInteresGracia;
	}

	public BigDecimal getIntPrimerDividendo() {
		return intPrimerDividendo;
	}

	public void setIntPrimerDividendo(BigDecimal intPrimerDividendo) {
		this.intPrimerDividendo = intPrimerDividendo;
	}

	public String getCedulaAfiliado() {
		return cedulaAfiliado;
	}

	public void setCedulaAfiliado(String cedulaAfiliado) {
		this.cedulaAfiliado = cedulaAfiliado;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public HtmlDataTable getTablaPrestamos() {
		return tablaPrestamos;
	}

	public void setTablaPrestamos(HtmlDataTable tablaPrestamos) {
		this.tablaPrestamos = tablaPrestamos;
	}

	public List<PrestamoBiess> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(List<PrestamoBiess> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public boolean isTieneComprobantes() {
		return tieneComprobantes;
	}

	public void setTieneComprobantes(boolean tieneComprobantes) {
		this.tieneComprobantes = tieneComprobantes;
	}
 	
}