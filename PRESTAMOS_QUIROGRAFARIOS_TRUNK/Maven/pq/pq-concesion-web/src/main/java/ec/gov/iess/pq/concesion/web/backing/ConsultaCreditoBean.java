/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.pq.concesion.web.backing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.model.ExtendedTableDataModel;

import ec.fin.biess.creditos.pq.dao.PrestamoBiessDao;
import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ITransaccionRecaudacionDao;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.DatosOrdenCompra;
import ec.gov.iess.creditos.modelo.persistencia.ClasePrestamo;
import ec.gov.iess.creditos.modelo.persistencia.CreditoConsolidado;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.TransaccionRecaudacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.DividendoDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQEmpSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SimulacionCancelacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.TablaAmortizacionOperacionSacServicioLocal;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.ConstantesSAC;
import ec.gov.iess.creditos.pq.util.EstadosCredito;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.pq.util.TiposCredito;
import ec.gov.iess.hl.servicio.EmpleadorServicio;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.bean.PrestamoBean;
import ec.gov.iess.pq.concesion.web.helper.PrestamoDataProvider;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

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

	/**
	 * Parametro que contiene el porcentaje de novacion 
	 */
	private static final String PORCENTAJE_NOVACION_CANCELADA = "PQW_CON_PORNOVAPAG";

	private static final long serialVersionUID = 2425621223704034431L;

	private transient LoggerBiess log = LoggerBiess.getLogger(ConsultaCreditoBean.class);

	@EJB(name = "EmpleadorServicioImpl/local")
	private EmpleadorServicio empleadorServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;
	
	@EJB(name = "PrestamoBiessDaoEjb/local")
	private PrestamoBiessDao prestamoBiessDao;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;

	@EJB(name = "ConsultaParametroServicioImpl/local")
	private ConsultaParametroServicioLocal consultaParametroServicio;
	
	@EJB(name = "CreditoPQExigibleSacServicioImpl/local")
	private CreditoPQExigibleSacServicioLocal creditoPQExigibleServicio;

	@EJB(name = "TablaAmortizacionOperacionSacServicioImpl/local")
	private TablaAmortizacionOperacionSacServicioLocal tablaAmortizacionOperacionSacServicio;


	@EJB(name = "SimulacionCancelacionSacServicioImpl/local")
	private SimulacionCancelacionSacServicio simularCancelacionSrv;
	
	@EJB(name="CreditoPQOpEmplSacServicioImpl/local")
	private CreditoPQEmpSacServicioLocal creditoPqClientesEmpl;
	
	@EJB(name = "TransaccionRecaudacionDaoImpl/local")
	private ITransaccionRecaudacionDao transaccionRecaudacionDao;

	private DatosBean datos;
	private CreditoAccionBean creditoAccion;
	private PrestamoBean prestamoBean;	
	private CreditoConsolidado creditoConsolidadoTotal;
	private CreditoConsolidado creditoConsolidadoDiario;
	private List<Prestamo> creditosVigentes;
	private List<Prestamo> creVigenteAnulados;
	private List<Prestamo> creditosNovacion;
	private String mensaje;
	private boolean tieneNovaciones;
	private boolean muestraInteresGracia;
	// Valor del interes desde concesión hasta 1er dividendo
	private BigDecimal intPrimerDividendo;
	
	private ExtendedTableDataModel<PrestamoBiess> creditosVigModel;
	private String mensajeInformativoNovaciones;
	
	private CalculoCreditoHelperSingleton calculoCreditoHelper;
	private int edadAsegurado;
	private List<Prestamo> creditosCancelados;
	private List<Prestamo> creditosNoCalificadosNovacion;
	private List<Prestamo> prestamos;
	private static final String RESOURCE_MESSAGES ="messages";
	private static final String NUMERO_NOVACIONES_PERMITIDAS_AFI ="PQW_CON_NUMNOVAPER";
	private static final String NUMERO_NOVACIONES_PERMITIDAS_JUB ="PQW_CON_NUMNOVAJUB";
	String paramsAfiliado=null;
	String paramsJubilado=null;
	private boolean tieneCreditos;
	//REQ-617
	int novacionesPermitidasAfi = 0;
	int novacionesPermitidasJub = 0;
	int edadPermiteNovaciones = 0;

	
	public ConsultaCreditoBean() {
	}

	/**
	 * Poscontructor.
	 */
	@PostConstruct
	public void init () {
	    if (!isPostback()) {
	    	creditoAccion.resetear();	    	
	    }
	    
	    this.calculoCreditoHelper = CalculoCreditoHelperSingleton.getInstancia();
		this.edadAsegurado = calculoCreditoHelper.determinarEdad(datos.getSolicitante().getDatosPersonales().getFechaNacimiento(), new Date())
				.intValue();
	    
	    contarCreditosNovacion();
	}
	public String regresarPagina() {

		String pagina = datos.getPaginaOrigen();

		if (pagina == null) {

			pagina = "consultaCreditos";
		} else if (pagina.equals("calculoLiquidacion")) {
			creditoAccion.calcularLiq();
		} else if (pagina.equals("calculoLiquidacionFondos")) {
			creditoAccion.calcularLiquidacionFondos();
		}

		return pagina;
	}
	public PrestamoBean getPrestamoBean() {
		if (this.prestamoBean == null) {

			log.debug("obtiene detalle prestamo.");

			if (creditoAccion.getPrestamoSeleccionado() == null) {
				// en caso de que venga por datos personales, obtiene el vigente
				this.setPrestamoBean(this.obtenerCreditoVigente());
			} else {
				// en el caso que venga de la lista de creditos
				final PrestamoBean prestamoBeanLista = new PrestamoBean();
				final Prestamo prestamo = prestamoServicio
						.getPrestamoByPk(creditoAccion.getPrestamoSeleccionado().getCreditoPk());
				prestamoBeanLista.setInstitucionFinanciera(
						empleadorServicio.getEmpleador(creditoAccion.getPrestamoSeleccionado().getRucempinsfin()));
				final Long nut = prestamoServicio.traerNut(prestamo.getCodtipsolser(), prestamo.getNumsolser());
				// no tiene NUT
				if (nut == null) {
					muestraInteresGracia = CreditoEspecialEnum.EMERGENTE.getCodigoCredito()
							.equals(prestamo.getCreditoEspecial());
				prestamoBeanLista.setPrestamo(prestamo);
					setIntPrimerDividendo(BigDecimal.valueOf(calculaValorInteresPrimeraCuota(
							prestamo.getDividendosPrestamo(),
						CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial()))));
				this.setPrestamoBean(prestamoBeanLista);

				} else {
					prestamo.setNut(nut);
					llenarTablaAmortizacionDesdeSac(prestamo);					
					prestamoBeanLista.setPrestamo(prestamo);
					this.setPrestamoBean(prestamoBeanLista);
				}

			}
		}
		return prestamoBean;
	}

	// REQUERIMIENTO 6.1 NOVACION. SAC
	public void llenarTablaAmortizacionDesdeSac(final Prestamo prestamo) {
		try {

			final OperacionSacRequest operacionSacRequest = new OperacionSacRequest();
			final OperacionRequestDto operacion = new OperacionRequestDto();
			operacion.setNut(prestamo.getNut());
			operacion.setNumero(prestamo.getNumOperacionSAC());
			operacion.setTipoProducto(prestamo.getDestinoComercial().getCodProdPq());
			final ClienteRequestDto cliente = new ClienteRequestDto();
			cliente.setTipoCliente(FuncionesUtilesSac.obtenerTipoPrestamista(datos.getTipo()));
			operacionSacRequest.setCliente(cliente);
			operacionSacRequest.setOperacion(operacion);
			// traer tabla de amortización
			final List<DividendoDto> listaDividendos = tablaAmortizacionOperacionSacServicio
					.obtenerTablaAmortizacionOperacionSac(operacionSacRequest);
			int numeroDividendo = 0;
			final int ultimoDividendo = listaDividendos.size();
			final List<DividendoPrestamo> dividendos = new ArrayList<DividendoPrestamo>();
			Calendar calendar = Calendar.getInstance();
			for (final DividendoDto dividendoDto : listaDividendos) {
				final DividendoPrestamo dividendo = new DividendoPrestamo();
				// Seguro de Saldos
				if (numeroDividendo == 0) {
					// Seguro de Saldos
					prestamo.setValsegsal(dividendoDto.getSeguroDesgravamen().doubleValue());
					try {
						// Fecha primer dividendo
						prestamo.setFecinipre(FuncionesUtilesSac.obtenerFechaSac(dividendoDto.getFechaCancelacion()));
					} catch (final ParseException e) {
						log.error("Error al realizar la transaformacion de fecha de SAC a formato BIESS",e);
					
					}
				}

				// Fecha ultimo dividendo
				if (numeroDividendo == ultimoDividendo - 1) {
					try {
						prestamo.setFecfinpre(FuncionesUtilesSac.obtenerFechaSac(dividendoDto.getFechaCancelacion()));
					} catch (final ParseException e) {
						log.error("Error al realizar la transaformacion de fecha final del prestamo de SAC a formato BIESS",e);
					}

				}

				// Valor del dividendo
				if (numeroDividendo == 1) {
					prestamo.setValtotdiv(dividendoDto.getTotal().doubleValue());

				}

				final DividendoPrestamoPk dividendoPrestamoPK = new DividendoPrestamoPk();
				dividendoPrestamoPK.setNumdiv(Long.valueOf(dividendoDto.getNumeroDividendo()));
				dividendo.setDividendoPrestamoPk(dividendoPrestamoPK);

				// capital amortizado
				dividendo.setValcapamo(dividendoDto.getCapital().doubleValue());

				// interes intsalcap
				dividendo.setIntsalcap(dividendoDto.getInteres().doubleValue());

				// interes gracias
				dividendo.setIntpergra(dividendoDto.getPeriodoGracia().doubleValue());

				// Saldo capital
				dividendo.setSaldoCapital(dividendoDto.getCapitalReducido());

				// Fecha pago
				try {
					dividendo.setFecpagdiv(FuncionesUtilesSac.obtenerFechaSac(dividendoDto.getFechaCancelacion()));
				} catch (final ParseException e) {
					log.error("Error al parsear la fecha de dividendo de SAC a a formato BIES",e);
				}
				// Estado dividendo
				if (numeroDividendo > 0) {
					final EstadoDividendoPrestamo estadoDividendo = new EstadoDividendoPrestamo();
					  estadoDividendo.setDesestdiv(FuncionesUtilesSac.obtenerEstadoPrestamo(dividendoDto.getEstado()));
				      if("CAN".equals(dividendoDto.getEstado())) {
				    	  calendar.setTime(dividendo.getFecpagdiv());
				    	TransaccionRecaudacion transaccion=transaccionRecaudacionDao.obtenerTransaccion(BigDecimal.valueOf(prestamo.getNut()), prestamo.getNumafi(), BigDecimal.valueOf(calendar.get(Calendar.YEAR)), BigDecimal.valueOf(calendar.get(Calendar.MONTH)+1l));
				    	  estadoDividendo.setDesestdiv(transaccion==null?FuncionesUtilesSac.obtenerEstadoPrestamo(dividendoDto.getEstado()):"EPL".equals(transaccion.getEstado())?"En Planilla":FuncionesUtilesSac.obtenerEstadoPrestamo(dividendoDto.getEstado()));
				      }
				      dividendo.setEstadoDividendoPrestamo(estadoDividendo);
				}
				dividendos.add(dividendo);
				numeroDividendo = numeroDividendo + 1;
			}
			prestamo.setDividendosPrestamo(dividendos);
		} catch (final TablaAmortizacionSacException e) {
			log.error(e);
		}
	}
	


	public List<Prestamo> getCreditosVigentes() {
		if (null == creditosVigentes) {
			creditosVigentes = prestamoServicio.consultarQuirografariosVigentes(getRemoteUser(), 
					obtenerEstadosCredito(), obtenerTiposCredito());			
		}
		
		return creditosVigentes;
	}

	/**
	 * consigue CreVigente Anulados
	 * 
	 * @retunr
	 * @author palvarez, cbastidas
	 */
	public List<Prestamo> getCreVigenteAnulados() {
		if (null == creVigenteAnulados) {
			creVigenteAnulados = prestamoServicio.consultarQuirografariosVigentes(getRemoteUser(),
						obtenerEstadosCreditoVigAnu(), obtenerTiposCredito());
		}
		
		return creVigenteAnulados;
	}

	/**
	 * obtener Estados Credito Vig Anu
	 * 
	 * @retunr
	 * @author palvarez, cbastidas
	 */
	private List<String> obtenerEstadosCreditoVigAnu() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_PQ_VIGENTE);
		estados.add(EstadosCredito.ESTADO_PQ_ANULADO_RECHAZADO);
		return estados;
	}

	private List<Long> obtenerTiposCredito() {
		final List<Long> tiposCredito = new ArrayList<Long>();
		tiposCredito.add(TiposCredito.TIPO_CREDITO_AFILIADO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_ZAFRERO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HL);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_UIO);
		tiposCredito.add(TiposCredito.TIPO_CREDITO_JUBILADO_HOST_GYE);
		return tiposCredito;
	}


	private List<String> obtenerEstadosCredito() {
		final List<String> estados = new ArrayList<String>();
		estados.add(EstadosCredito.ESTADO_PQ_VIGENTE);
		estados.add(EstadosCredito.ESTADO_PQ_CANCELADO);
		return estados;
	}


	public PrestamoBean obtenerCreditoVigente() {
		final PrestamoBean prestamoBeanTemp = new PrestamoBean();
		final List<String> estadosVig = new ArrayList<String>();
		estadosVig.add(EstadosCredito.ESTADO_PQ_VIGENTE);

		final List<Prestamo> prestamosVigentes = prestamoServicio
				.getPrestamosVigentesPorCedula(datos.getSolicitante()
						.getDatosPersonales().getCedula(), estadosVig);

		if (prestamosVigentes != null && prestamosVigentes.size() > 0) {
			final Prestamo p = prestamosVigentes.get(0);
			final PrestamoPk pk = new PrestamoPk();
			pk.setCodprecla(p.getPrestamoPk().getCodprecla());
			pk.setCodpretip(p.getPrestamoPk().getCodpretip());
			pk.setNumpreafi(p.getPrestamoPk().getNumpreafi());
			pk.setOrdpreafi(p.getPrestamoPk().getOrdpreafi());

			final Prestamo prestamo = prestamoServicio.getPrestamoByPk(pk);
			prestamoBeanTemp.setPrestamo(prestamo);
			muestraInteresGracia = CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial());
			setIntPrimerDividendo(new BigDecimal(calculaValorInteresPrimeraCuota(prestamo.getDividendosPrestamo(),
					CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial()))));

			prestamoBeanTemp
					.setInstitucionFinanciera(empleadorServicio
							.getEmpleador(prestamoBeanTemp.getPrestamo()
									.getRucempinsfin()));
			return prestamoBeanTemp;
		} else {
			log.debug("No existen prestamos vigentes para la cédula");
			return null;
		}

	}

	/**
	 * Permite descargar la licitud 
	 * @return
	 */
	public String descargarLicitud() {
		 FileInputStream fileToDownload=null;
		try {
			final String nombreArchivo = "licitudfondos.pdf";
			final FacesContext context = FacesContext.getCurrentInstance();
			final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			final StringBuilder archivo = new StringBuilder(servletContext.getRealPath("/files/formularios")).append("/")
					.append(nombreArchivo);
			final HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.reset();
			response.setContentType("application/pdf");

			response.setHeader("Content-disposition", "attachment; filename=\"" + nombreArchivo + "\"");
			final PrintWriter out = response.getWriter();
			fileToDownload = new FileInputStream(archivo.toString());
			response.setContentLength(fileToDownload.available());
			int c;
			while ((c = fileToDownload.read()) != -1) {
				out.write(c);
			}
			out.flush();
			out.close();
			fileToDownload.close();
		} catch (final IOException e) {
			log.error("No se puede leer el archivo");
			
		}finally {
		    try {
		    	if(fileToDownload!=null) {
		    	fileToDownload.close();
		    	}
		    } catch (final IOException e) {
		        //No se produce pues ya se cierra
		    }
		}
		FacesContext.getCurrentInstance().responseComplete();
		return "licitudfondos";

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
			for (final DividendoPrestamo dividendoPrestamo : this.prestamoBean
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
			for (final DividendoPrestamo dividendoPrestamo : this.prestamoBean
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
			for (final DividendoPrestamo dividendoPrestamo : this.prestamoBean
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
	public void setDatos(final DatosBean datos) {
		this.datos = datos;
	}

	/**
	 * Método que se encarga de determinar qué página de reporte se va a
	 * presentar.
	 */
	public String determinarReporte() {
		final Calendar cal = new GregorianCalendar(2007, Calendar.AUGUST, 1);
		final PrestamoBean prestamoBean = getPrestamoBean();
		if (prestamoBean == null) {
			return "errorSesionVacia";
		}

		if (prestamoBean.getPrestamo().getFecpreafi().after(cal.getTime())
				|| prestamoBean.getPrestamo().getFecpreafi()
						.equals(cal.getTime())) {
			log.debug("PRESTAMO CONCEDIDO DESPUES DEL 31 DE JULIO DE 2007");
			return "detallePrestamo";
		} else {
			log.debug("PRESTAMO CONCEDIDO ANTES DEL 1 DE AGOSTO DE 2007");
			return "detallePrestamoAnterior";
		}
	}

	/**
	 * Returns the value of creditoConsolidado.
	 * 
	 * @return creditoConsolidado
	 */
	public CreditoConsolidado getCreditoConsolidadoTotal() {
		if (this.creditoConsolidadoTotal == null) {
			setCreditoConsolidadoTotal(cargarCreditoConsolidadoTotal());
		}
		return creditoConsolidadoTotal;
	}

	/**
	 * Sets the value for creditoConsolidado.
	 * 
	 * @param creditoConsolidado
	 */
	public void setCreditoConsolidadoTotal(final CreditoConsolidado creditoConsolidado) {
		this.creditoConsolidadoTotal = creditoConsolidado;
	}

	/**
	 * Returns the value of creditoConsolidadoDiario.
	 * 
	 * @return creditoConsolidadoDiario
	 */
	public CreditoConsolidado getCreditoConsolidadoDiario() {
		if (this.creditoConsolidadoDiario == null) {
			setCreditoConsolidadoDiario(cargarCreditoConsolidadoDiario());
		}
		return creditoConsolidadoDiario;
	}

	/**
	 * Sets the value for creditoConsolidadoDiario.
	 * 
	 * @param creditoConsolidadoDiario
	 */
	public void setCreditoConsolidadoDiario(
			final CreditoConsolidado creditoConsolidadoDiario) {
		this.creditoConsolidadoDiario = creditoConsolidadoDiario;
	}

	private CreditoConsolidado cargarCreditoConsolidadoTotal() {
		return prestamoServicio.getResumenConsolidadoTotal();
	}

	private CreditoConsolidado cargarCreditoConsolidadoDiario() {
		return prestamoServicio.getResumenConsolidadoDiario();
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
	public void setCreditoAccion(final CreditoAccionBean creditoAccion) {
		this.creditoAccion = creditoAccion;
	}

	public void setPrestamoBean(final PrestamoBean prestamoBean) {
		this.prestamoBean = prestamoBean;
	}

	/**
	 * @return the creditosVigNovacion
	 */
	public List<Prestamo> getCreditosVigNovacion() {
		if (datos.getPrestamosNovacion() == null) {
			// Si no existe orden de compra significa que el producto es normal
			datos.setDatosOrdenCompra(null);
			if (datos.getDatosOrdenCompra() == null) {
				final DatosOrdenCompra datosOrdenes = new DatosOrdenCompra();
				datosOrdenes.setCodigoProducto(TiposProductosPq.NOR.toString());
				datos.setDatosOrdenCompra(datosOrdenes);
			}
			creditosNovacion = obtenerListaPrestamoSAC();
			creditoAccion.setMuestraDetalleNov(Boolean.FALSE);
			datos.setCreditoNovacion(true);
			datos.setPrestamosNovacion(creditosNovacion);
			datos.setCreditosNoCalificadosNovacion(creditosNoCalificadosNovacion);
		}else {
			creditosNovacion=datos.getPrestamosNovacion();
			creditosNoCalificadosNovacion=datos.getCreditosNoCalificadosNovacion();
		}
		tieneCreditos=!creditosNovacion.isEmpty();
		return creditosNovacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(final String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Prestamo> getCreditosNovacion() {
		return creditosNovacion;
	}

	public void setCreditosNovacion(final List<Prestamo> creditosNovacion) {
		this.creditosNovacion = creditosNovacion;
	}

	/**
	 * @param creditosVigModel the creditosVigModel to set
	 */
	public void setCreditosVigModel(final ExtendedTableDataModel<PrestamoBiess> creditosVigModel) {
		this.creditosVigModel = creditosVigModel;
	}

	/**
	 * @return the creditosVigModel
	 */
	public ExtendedTableDataModel<PrestamoBiess> getCreditosVigModel() {
		
			PrestamoDataProvider provider;
			try {
				if(datos.getPrestamoDataProvider()==null) {
				provider = new PrestamoDataProvider(creditoPQExigibleServicio, prestamoBiessDao, getRemoteUser(),
						obtenerEstadosCredito(), obtenerTiposCredito());
				datos.setPrestamoDataProvider(provider);
				}else {
					provider=datos.getPrestamoDataProvider();
				}
				
			creditosVigModel = new ExtendedTableDataModel<PrestamoBiess>(provider);			
			} catch (final PQExigibleException e) {
				log.error("Error al consumir el servicio creditos exigibles SAC", e);
				this.mensaje = e.getCodigo() + ", " + e.getMensaje();
			}catch( final NoResultException nre) {
				log.error(nre);
				this.mensaje = "-1" + ", " +"Error";
			}


		return creditosVigModel;
	}
		
	/**
	 * Metodo que indica si el request es un postback.
	 * 
	 * @return
	 */
	private boolean isPostback() {
	    final FacesContext context = FacesContext.getCurrentInstance();
	    
	    return context.getRenderKit().getResponseStateManager().isPostback(context);
	}
	
	/**
	 * Metodo para contar cuantas novaciones tiene un afiliado, si el numero de novaciones en el anio actual es mayor o
	 * igual a 2 pone una bandera en true para evitar que se realicen mas novaciones
	 */
	private void contarCreditosNovacion() {
		try {
			tieneNovaciones = false;
			final Calendar cal = Calendar.getInstance();
			final Long anio = Long.valueOf(cal.get(Calendar.YEAR));
			Long numeroNovaciones = 0L;
			int novacionesPermitidasAfi = 0;
			int novacionesPermitidasJub = 0;
			int edadPermiteNovaciones = 0;
			
			//numeroNovaciones = this.prestamoServicio.contarPorEstadoAnio(getRemoteUser(), "CNV", anio);
			
			obtenerParametrosNovacion();
			
			if (paramsAfiliado != null) {
				final String[] parametrosNovacionesPermitidasAfi = paramsAfiliado.split(";");
				novacionesPermitidasAfi = Integer.parseInt(parametrosNovacionesPermitidasAfi[0]);
			}
			
			if (paramsJubilado != null) {
				final String[] parametrosNovacionesPermitidasJub = paramsJubilado.split(";");
				novacionesPermitidasJub = Integer.parseInt(parametrosNovacionesPermitidasJub[0]);
				edadPermiteNovaciones =  Integer.parseInt(parametrosNovacionesPermitidasJub[2]);
			}
	


			if (TipoPrestamista.JUBILADO == this.datos.getTipo()) {
				if (this.edadAsegurado > edadPermiteNovaciones) {
					this.tieneNovaciones = true;
					this.mensajeInformativoNovaciones = super.getResource(RESOURCE_MESSAGES, "mensaje.novacion.edad.permitida");
				} else if(numeroNovaciones >= novacionesPermitidasJub) {
					this.tieneNovaciones = true;
					this.mensajeInformativoNovaciones = super.getResource(RESOURCE_MESSAGES, "mensaje.novacion.maxima.jubilados");
				}
			} else if ((TipoPrestamista.AFILIADO == this.datos.getTipo() || TipoPrestamista.ZAFRERO_TEMPORAL == this.datos.getTipo())
					&& numeroNovaciones >= novacionesPermitidasAfi) {
				this.tieneNovaciones = true;
				this.mensajeInformativoNovaciones = String.format(super.getResource("messages", "mensaje.novacion.maxima"), novacionesPermitidasAfi, novacionesPermitidasAfi);

			}

		} catch (final Exception e) {
			log.error("Error al contar el numero de novaciones", e);
		}
	}
	
	private void obtenerParametrosNovacion() {
		try{
			paramsAfiliado= (String)this.consultaParametroServicio.consultarParametro(NUMERO_NOVACIONES_PERMITIDAS_AFI,"string");
			log.info("PARAMETRO AFILIADO OBTENIDO: "+paramsAfiliado);
			paramsJubilado= (String)this.consultaParametroServicio.consultarParametro(NUMERO_NOVACIONES_PERMITIDAS_JUB,"string");
			log.info("PARAMETRO JUBILADO OBTENIDO: "+paramsJubilado);
		}catch(final ConsultaParametroException e){
			log.error("Erro al consultar el parametro numero novaciones permitidas afiliado/jubilado");
		}
	}	
	/**
	 * Obtiene el valor de la primera cuota para creditos emergentes
	 * 
	 * @param listaDividendos
	 * @return
	 */
	private Double calculaValorInteresPrimeraCuota(final List<DividendoPrestamo> listaDividendos, final boolean esEmergente) {
		BigDecimal interesFinMesCuota = BigDecimal.ZERO;
		final BigDecimal plazoGracia = obtenerMesesGracia(esEmergente);
		// Se obtiene la 4ta cuota para el calculo del interes desde la concesion del credito hasta la primera cuota
		if (listaDividendos != null && !listaDividendos.isEmpty()) {
			final DividendoPrestamo dividendo = listaDividendos.get(plazoGracia.intValue());
			final BigDecimal dividendoPrestamo = new BigDecimal(dividendo.getIntsalcap());
			log.debug("Dividendo prestamo primera cuota: " + dividendo.getPrestamo().getPrestamoPk().getNumpreafi());
			final BigDecimal interesProporcionalCuota = (dividendoPrestamo.multiply(plazoGracia))
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
	private BigDecimal obtenerMesesGracia(final boolean esEmergente) {
		BigDecimal resp = BigDecimal.ZERO;
		if (esEmergente) {
			try {
				resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
						BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico();
			} catch (final ParametroBiessException e) {
				log.error("Error al leer parametro de meses de gracia >>", e);
			}
		}
		return resp;
	}
	


	public String seleccionarCreditoCancelado() {

		this.datos.setPaginaOrigen("creditosCancelados");

		final Prestamo tmpprestamo = (Prestamo) getHttpServletRequest().getAttribute("item");
		creditoAccion.setPrestamoSeleccionado(prestamoServicio.getPrestamoByPk(tmpprestamo.getPrestamoPk()));
		creditoAccion.seleccionarCancelado();

		try {
			getHttpServletResponse()
					.sendRedirect(getRequest().getContextPath() + "/pages/concesion/detallePrestamo.jsf");
		} catch (final IOException e){
			log.error("No se logro redireccionar hacia el detalle de detallePrestamo.jsf", e);
		}
		return null;

	}

	public List<Prestamo> getCreditosCancelados() {
		if (null == creditosCancelados) {
			creditosCancelados = construirPrestamosCancelados();
		}
		return creditosCancelados;
	}
	
	/**
	 * Permite construir los prestamos cancelados
	 * @return
	 */
	private List<Prestamo> construirPrestamosCancelados() {
		final List<Prestamo> prestamos = prestamoServicio.listarPrestamosCancelados(getRemoteUser());
		final List<Prestamo> prestamosCancelados = new ArrayList<Prestamo>();
		for (final Prestamo prestamo : prestamos) {
			final Long nut = prestamoServicio.traerNut(prestamo.getCodtipsolser(), prestamo.getNumsolser());
			if( nut != null ) {
				prestamo.setNut(nut);
			}
			prestamosCancelados.add(prestamo);
		}
		return prestamosCancelados;
		
	}

	public boolean isTieneNovaciones() {
		return tieneNovaciones;
	}

	public void setTieneNovaciones(final boolean tieneNovaciones) {
		this.tieneNovaciones = tieneNovaciones;
	}

	public boolean isMuestraInteresGracia() {
		return muestraInteresGracia;
	}

	public void setMuestraInteresGracia(final boolean muestraInteresGracia) {
		this.muestraInteresGracia = muestraInteresGracia;
	}

	public BigDecimal getIntPrimerDividendo() {
		return intPrimerDividendo;
	}

	public void setIntPrimerDividendo(final BigDecimal intPrimerDividendo) {
		this.intPrimerDividendo = intPrimerDividendo;
	}

	public String getMensajeInformativoNovaciones() {
		return mensajeInformativoNovaciones;
	}

	public void setMensajeInformativoNovaciones(final String mensajeInformativoNovaciones) {
		this.mensajeInformativoNovaciones = mensajeInformativoNovaciones;
	}
 	
	public void setCreditosCancelados(final List<Prestamo> creditosCancelados) {
		this.creditosCancelados = creditosCancelados;
	}

	/**
	 * Permite obtener la lista de prestamos desde el core SAC
	 * 
	 * @return
	 */
	private List<Prestamo> obtenerListaPrestamoSAC() {
		 
		try {
			String parametroPorcentaje;
			
			parametroPorcentaje = (String) consultaParametroServicio.consultarParametro(PORCENTAJE_NOVACION_CANCELADA, "float");
	
		    final double porcentajeValidar = Double.parseDouble(parametroPorcentaje);
			
			
			if(datos.getInfoPqExigile()==null) {
				datos.setInfoPqExigile(creditoPqClientesEmpl.obtenerInfoPqOperEmp(getHttpServletRequest().getRemoteUser()));
			}
			final List<CreditoExigibleDto> prestamosSAC = datos.getInfoPqExigile().getListaCreditosExigible();
			
			prestamos = new ArrayList<Prestamo>();
			if(prestamosSAC != null && !prestamosSAC.isEmpty()) {
				creditosNoCalificadosNovacion = new ArrayList<Prestamo>();
			}
			for (final CreditoExigibleDto creditoExigibleDto : prestamosSAC) {
				final BigDecimal cuotasPagadas = creditoExigibleDto.getTotalCuotas()
						.subtract(creditoExigibleDto.getCuotasFaltantes());
				final Double porcentajeCuotasPagadas = cuotasPagadas
						.divide(creditoExigibleDto.getTotalCuotas(), 4, RoundingMode.CEILING).doubleValue() * 100;
				//re617
				PrestamoBiess prestamoBD=prestamoBiessDao.buscarPorOpIdent(creditoExigibleDto.getOperacionSAC(), getRemoteUser());
				PrestamoPk prestamoPk = null;
				prestamoPk = new PrestamoPk();
				prestamoPk.setCodprecla(prestamoBD.getCreditoPk().getCodprecla());
				prestamoPk.setCodpretip(prestamoBD.getCreditoPk().getCodpretip());
				prestamoPk.setNumpreafi(prestamoBD.getCreditoPk().getNumpreafi());
				prestamoPk.setOrdpreafi(prestamoBD.getCreditoPk().getOrdpreafi());
				
				Prestamo prestamo=this.prestamoServicio.bucarPrestamoPk(prestamoPk);
				
				Boolean validacionOperacion = validarNovacionOperacion(prestamo);
				if (porcentajeCuotasPagadas >= porcentajeValidar  && porcentajeCuotasPagadas<100 && Boolean.TRUE.equals(!validacionOperacion)) {
					
					prestamos.add(fabricarPrestamo(creditoExigibleDto, Boolean.TRUE,parametroPorcentaje, Boolean.FALSE));
					
					
				}else {
					
					creditosNoCalificadosNovacion.add(fabricarPrestamo(creditoExigibleDto, porcentajeCuotasPagadas<porcentajeValidar?Boolean.FALSE:Boolean.TRUE,parametroPorcentaje,validacionOperacion));
				}
				
			}
		} catch (final PQExigibleException e) {
			this.mensaje = e.getCodigo() + ", " + e.getMensaje();
			log.error("Error al obtener la lista de prestamos desde el core SAC", e);
		} catch (final ConsultaParametroException e) {
			this.mensaje = "Error al consular parametros de novacion";
			log.error("Error al consultar el parametro de "+PORCENTAJE_NOVACION_CANCELADA, e);
		}
		return prestamos;
	}

	/**
	 * Permite construir la entidad de prestamo que se utiliza en el seteo de la
	 * pagina para novacion
	 * 
	 * @param creditoExigibleDto
	 * @return
	 */
	private Prestamo fabricarPrestamo(final CreditoExigibleDto creditoExigibleDto, final boolean cumpleCuotasPagadas,final String parametroPorcentaje,final boolean cumpleNumeroNovacion) {
		final Prestamo prestamo =  new Prestamo();

		try {
		final PrestamoPk prestamoPk = new PrestamoPk();
		prestamoPk.setNumpreafi(creditoExigibleDto.getNut());
		PrestamoBiess prestamoBD=prestamoBiessDao.buscarPorOpIdent(creditoExigibleDto.getOperacionSAC(), getRemoteUser());
		final ClasePrestamo clasePrestamo = new ClasePrestamo();
		clasePrestamo.setDescripcion(prestamoBD.getDestinoComercial().getDescripcion());
		prestamo.setPlzmes(creditoExigibleDto.getTotalCuotas().longValue());
		prestamo.setPrestamoPk(prestamoPk);
		ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo estado=new ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo();
		estado.setCodestpre(prestamoBD.getEstadoPrestamo().getCodestpre());
		estado.setDesestpre(prestamoBD.getEstadoPrestamo().getDesestpre());
		estado.setIndhabestpre(prestamoBD.getEstadoPrestamo().getIndhabestpre());
		prestamo.setEstadoPrestamo(estado);
		prestamo.setClasePrestamo(clasePrestamo);
		prestamo.setNut(creditoExigibleDto.getNut());
		prestamo.setNumOperacionSAC(creditoExigibleDto.getOperacionSAC());
		prestamo.setValsalcap(creditoExigibleDto.getMontoConcedido().doubleValue());
		prestamo.setValliqnov(null);
		prestamo.setDiasMora(creditoExigibleDto.getDiasMora());
		prestamo.setCumpleSaldoEmpleador(creditoExigibleDto.getSaldoOperacionEmp().signum()>0);
		prestamo.setCumpleCuotasPagadas(cumpleCuotasPagadas);
		prestamo.setPorcentajeComprometer(parametroPorcentaje);
        prestamo.setFecpreafi(prestamoBD.getFecpreafi());
		prestamo.setCumpleNumeroNovacion(cumpleNumeroNovacion);
			prestamo.setFecpreafi(FuncionesUtilesSac.obtenerFechaSac(creditoExigibleDto.getFechaConcesion()));
		} catch (final ParseException e) {
			this.mensaje = ConstantesSAC.MENSAJE_VALOR_NULO;			
		}
		return prestamo;
	}
	


	public List<Prestamo> getCreditosNoCalificadosNovacion() {
		return creditosNoCalificadosNovacion;
	}

	public void setCreditosNoCalificadosNovacion(final List<Prestamo> creditosNoCalificadosNovacion) {
		this.creditosNoCalificadosNovacion = creditosNoCalificadosNovacion;
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	public boolean isTieneCreditos() {
		return tieneCreditos;
	}

	public void setTieneCreditos(boolean tieneCreditos) {
		this.tieneCreditos = tieneCreditos;
	}
	//REQ 617 
	public Boolean validarNovacionOperacion(Prestamo actual) {
		int limiteNovacion=0;
		obtenerParametrosNovacion();
		 
		if (paramsAfiliado != null) {
			final String[] parametrosNovacionesPermitidasAfi = paramsAfiliado.split(";");
			novacionesPermitidasAfi = Integer.parseInt(parametrosNovacionesPermitidasAfi[0]);
		}
		
		if (paramsJubilado != null) {
			final String[] parametrosNovacionesPermitidasJub = paramsJubilado.split(";");
			novacionesPermitidasJub = Integer.parseInt(parametrosNovacionesPermitidasJub[0]);
			edadPermiteNovaciones =  Integer.parseInt(parametrosNovacionesPermitidasJub[2]);
		}
		
		    int numeroNovaciones = 0;
		    if (TipoPrestamista.JUBILADO == this.datos.getTipo()) {
		    	limiteNovacion = novacionesPermitidasJub;
				if (this.edadAsegurado > edadPermiteNovaciones) {
				 	return Boolean.TRUE;
					
				}
			} else if ((TipoPrestamista.AFILIADO == this.datos.getTipo() || TipoPrestamista.ZAFRERO_TEMPORAL == this.datos.getTipo())
					) {
				limiteNovacion = novacionesPermitidasAfi;
			}
		    
			for (int i = 0; i <= limiteNovacion; i++) {
				Prestamo novacion = this.prestamoServicio.buscarCreditoAnterior(actual);
				if (novacion != null) {
					actual = novacion;
					numeroNovaciones = numeroNovaciones + 1;
				} else {
					break;
				}
			}
			if(numeroNovaciones>=limiteNovacion) {
				return Boolean.TRUE;
			}else {
				
				return Boolean.FALSE;
			}
				
		
	}
	//

	public int getEdadAsegurado() {
		return edadAsegurado;
	}

	public void setEdadAsegurado(int edadAsegurado) {
		this.edadAsegurado = edadAsegurado;
	}

	public int getEdadPermiteNovaciones() {
		return edadPermiteNovaciones;
	}

	public void setEdadPermiteNovaciones(int edadPermiteNovaciones) {
		this.edadPermiteNovaciones = edadPermiteNovaciones;
	}

}