package ec.gov.iess.planillaspq.web.backing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.richfaces.component.html.HtmlDataTable;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio;
import ec.fin.biess.creditos.pq.servicio.EstadoPrestamoServicio;
import ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.fin.biess.modelo.persistencia.HistoricoAprobacionesMasivas;
import ec.fin.biess.modelo.persistencia.pk.HistoricoAprobacionesMasivasPk;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.enumeracion.TipoBeneficiarioCredito;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.dto.SolicitudPda;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoRechazoCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.TipoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.CatalogoRechazoServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrestamoQuirografarioServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancariaAfiliado;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.Sucursal;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.planillaspq.web.bean.CedulasMasivasBean;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;
import ec.gov.iess.planillaspq.web.util.CedulaUtil;
import ec.gov.iess.planillaspq.web.util.Util;
import ec.gov.iess.planillaspq.web.util.UtilDate;

public class AprobacionPdvBacking extends BaseBean implements Serializable {

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamopdaservicio;

	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	private AdministracionOrdenCompraProveedorServicio administracionordencompraservicio;

	@EJB(name = "CatalogoRechazoServicioImpl/local")
	private CatalogoRechazoServicioLocal catalogorechazoservicio;

	@EJB(name = "PrestamoQuirografarioServicioImpl/local")
	private PrestamoQuirografarioServicio prestamoQuirografarioServicio;
	
	private static final long serialVersionUID = -8055665324278439817L;
	private static final LoggerBiess log = LoggerBiess
			.getLogger(AprobacionPdvBacking.class);

	private String mensaje = null;
	private String okmensaje = null;
	private String cedulafuncionario = null;

	private Date fechadesde = new Date();
	private Date fechahasta = new Date();
	private String cedula = null;
	private List<SolicitudPda> listaPrestamosPDA;
	private HtmlDataTable tblsolicitudxaprobar;
	private List<SelectItem> listamotivorechazo;
	private String observacionrechazo;
	private Long rechazoseleccionado;
	private SolicitudPda prestamorechazo = null;

	// INC-2272 Pensiones Alimenticias
	@EJB(name = "TipoPrestamoServicioImpl/local")
	private TipoPrestamoServicio tipoPrestamoServicio;
	
	@EJB(name = "BeneficiarioCreditoServicioImpl/local")
	private BeneficiarioCreditoServicio beneficiarioCreditoServicio;
	
	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionPoliticaServicio;
	
	@EJB(name = "EstadoPrestamoServicioImpl/local")
	private EstadoPrestamoServicio estadoPrestamoServicio;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private ParametroBiessDao parametroBiessDao;
	
	private String estadoPrestamo;
	private Long idTipoProducto;
	private SolicitudPda solicitudPdaSeleccionada;
		
	private ArrayList<SelectItem> listaTipoPrestamoAprobacion;
	
	private boolean pagoPensionesAlimenticias;
	
	private BeneficiarioCredito beneficiarioCredito;
	
	// INC-2148: Refugiados.
    private String visaPasaporte;
    
    private BloqueoListaControl bloqueoListaControl;
    
    private boolean enListaObservados;
    
  //Aprobacion Masiva
    private List<CedulasMasivasBean> listadoCedulas;
    private boolean visualizarCarga;
    private boolean visualizarTemporal;
    private boolean visualizarOrdenes;
    private boolean visualizarAceptar;
    private boolean procesoCarga;
    private boolean seleccionarTodos;
    //private boolean deshabilitarBotones;
    private List<String> cedulasPrestamos;
    private String mensajeCarga;
    private int contadorSelecionados;
    private List<SolicitudPda> solicitudPdaAuxiliar;
    private List<SolicitudPda> listaPrestamosPDAMasivos;
    private String mensajeMasivos;
    private UploadedFile archivoCedulas;
    
    private String mensajeErrorCedulasArchivo;
    private String mensajeErrorCarga;
    
    private static final String EXTENSION_ARCHIVO_CEDULAS = ".txt";
    
    private static final String ARCHIVO_MENSAJES = "mensajes";
    private static final String ARCHIVO_ERRORES = "errores";
    
    public AprobacionPdvBacking() {
  	}
	
    @PostConstruct
	public void init() {
    	limpiarCargaMasivos();
	}
    
	public List<SelectItem> getListamotivorechazo() {
		if (this.listamotivorechazo == null) {
			List<CatalogoRechazoCredito> lista = this.catalogorechazoservicio
					.devolvertodos();
			this.listamotivorechazo = new ArrayList<SelectItem>();
			for (CatalogoRechazoCredito catr : lista) {
				SelectItem l = new SelectItem();
				l.setLabel(catr.getMo_descripcion());
				l.setValue(catr.getCre_motivorechazo_pk());
				this.listamotivorechazo.add(l);
			}
		}
		return this.listamotivorechazo;
	}
	
	public void limpiarCargaMasivos() {
		mensajeErrorCedulasArchivo = null;
    	mensajeMasivos = null;
    	contadorSelecionados = 0;
    	mensajeCarga = null;
    	mensajeErrorCarga = null;
    	procesoCarga = false;
    	visualizarCarga = false;
    	visualizarOrdenes = false;
    	visualizarAceptar = false;
    	listadoCedulas = new ArrayList<CedulasMasivasBean>();
    	seleccionarTodos = false;
    	cedulasPrestamos = new ArrayList<String>();
    	solicitudPdaAuxiliar = new ArrayList<SolicitudPda>();
    	listaPrestamosPDAMasivos = new ArrayList<SolicitudPda>();
    	mensajeErrorCedulasArchivo = null;  
    	visualizarTemporal = false;
    }
	
	public String validarArchivoCedulas() {
		limpiarCargaMasivos();
		this.visualizarTemporal = true;
		listadoCedulas = new ArrayList<CedulasMasivasBean>();
		visualizarCarga = false;
		this.okmensaje = null;
		this.mensajeErrorCedulasArchivo = null;
		if (this.mensajeErrorCedulasArchivo == null) {
			if (archivoCedulas == null) {
				mensajeErrorCedulasArchivo = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.txt.carga");		
			} else {
				String extensionArchivo = archivoCedulas.getName().substring(archivoCedulas.getName().lastIndexOf("."), archivoCedulas.getName().length());
				if (!extensionArchivo.equalsIgnoreCase(EXTENSION_ARCHIVO_CEDULAS)) {// Si no cumple con la extension del archivo
					mensajeErrorCedulasArchivo = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.txt.formato");
				} else {// Procesa si el archivo es correcto
		            String cedula;
		            InputStream in = null;
		            try {
		            	in = archivoCedulas.getInputStream();
				        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				        int contador = 1;
			            while ((cedula = reader.readLine()) != null) {
			            	CedulasMasivasBean cedulasMasivasBean = new CedulasMasivasBean();
			            	cedulasMasivasBean.setId(contador);
			            	cedulasMasivasBean.setCedula(cedula);
			            	listadoCedulas.add(cedulasMasivasBean);
			            	contador += 1;
			            }
			            this.validarCedulas(); 
			            procesoCarga = true;
			            visualizarCarga = true;
			            visualizarOrdenes = false;
		            } catch(IOException ioe) {
		            	mensajeErrorCedulasArchivo = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.carga.archivo");		
		            } finally {
		            	if (in != null) {
		            		try {
			            		 in.close();
			            	 } catch (IOException e) {
			            		 log.error("Error al cerrar el archivo de cedulas cargado validarArchivoCedulas." + e);
			                 }
		            	}
		            }
				}
			}
		}
		return null;
	}
	
	private void validarCedulas() {
    	String cedula = "";
    	cedulasPrestamos = new ArrayList<String>();
    	String mensajeErrorNumero = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.cedula.novalida");
    	String mensajeErrorTamanio = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.cedula.novalida");
    	String mensajeErrorDigito = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.cedula.novalida");
    	
    	for(CedulasMasivasBean cedulaMasiva: listadoCedulas) {
    		cedula = cedulaMasiva.getCedula();
    		int respuesta = CedulaUtil.esCedulaValida(cedula, 24);
    		if  (respuesta == 0) {
    			cedulasPrestamos.add(cedula);
				cedulaMasiva.setMensaje("OK");
    		} else if (respuesta == 1) {
    			cedulaMasiva.setMensaje(mensajeErrorTamanio);
    		} else if (respuesta == 2) {
    			cedulaMasiva.setMensaje(mensajeErrorNumero);
    		} else if (respuesta == 3 || respuesta == 4) {
    			cedulaMasiva.setMensaje(mensajeErrorDigito);
    		}
    		
    	}
    }
	
	/**
	 *  Realiza el marcado de los registros de detalle de planilla de liquidacion de los empleados
	 */
	public void marcarTodosRegistros() {
		if (seleccionarTodos) {
			for (SolicitudPda solicitudPda : listaPrestamosPDAMasivos) {
				solicitudPda.setDatoSeleccionado(true);
			}
			contadorSelecionados = listaPrestamosPDAMasivos.size();
		} else {
			for (SolicitudPda solicitudPda : listaPrestamosPDAMasivos) {
				solicitudPda.setDatoSeleccionado(false);
			}
			contadorSelecionados = 0;
		}
	}
	
	public void seleccionarSolicitud() {
		SolicitudPda solicitudPda = (SolicitudPda) getHttpServletRequest()
				.getAttribute("item");
		if(solicitudPda.isDatoSeleccionado()) {
			contadorSelecionados += 1;
		} else {
			contadorSelecionados -= 1;
		}
	}
	
	public void aprobarAuxiliarMasivo() {
		solicitudPdaAuxiliar = new ArrayList<SolicitudPda>();
		for (SolicitudPda solicitudPda : listaPrestamosPDAMasivos) {
			if (solicitudPda.isDatoSeleccionado()) {
				solicitudPdaAuxiliar.add(solicitudPda);
			}
		}
		visualizarAceptar = true;
		visualizarCarga = false;
		visualizarOrdenes = false;
	}
	
	public void aprobarSolicitudMasiva() {
		Date fechaAprobacion = new Date();
		Object objfun = context().getELContext().getELResolver()
				.getValue(context().getELContext(), null, "funcionario");
		this.cedulafuncionario = ((FuncionarioHandler) objfun).getCedula();
		String nombreFuncionario = ((FuncionarioHandler) objfun).getNombreCompleto();
		String ipUsuario = this.getIpUser();
		String usuario = this.getRemoteCI();
		String hostRemoto = this.obtenerNombrePCRemoto();
		
		try {
			String stringCorreoPersona = parametroBiessDao.consultarPorIdNombreParametro("PARAMETRIZACION_PQI", "EMAIL_APR_MASIVA").getValorCaracter();
			String emailFuncionario = Util.devolverCorreo(stringCorreoPersona, cedulafuncionario);
			List<Prestamo> listadoPrestamosAprobar = new ArrayList<Prestamo>();
			List<HistoricoAprobacionesMasivas> listadoHisAprMasivas = new ArrayList<HistoricoAprobacionesMasivas>();
			int total = solicitudPdaAuxiliar.size();
			BigDecimal secuencialHistorico = administracionordencompraservicio.consultarCodigoHistorico();
			for (SolicitudPda solicitudPda : solicitudPdaAuxiliar) {
				Prestamo pqpda = solicitudPda.getPrestamo();
				listadoPrestamosAprobar.add(pqpda);
				//Historico Masivos
				PrestamoPk prestamoPk = pqpda.getPrestamoPk();
				HistoricoAprobacionesMasivasPk historicoAprobacionesMasivasPk = new HistoricoAprobacionesMasivasPk();
				historicoAprobacionesMasivasPk.setIdHistorico(secuencialHistorico);
				historicoAprobacionesMasivasPk.setNumpreafi(new BigDecimal(prestamoPk.getNumpreafi()));
				historicoAprobacionesMasivasPk.setOrdpreafi(prestamoPk.getOrdpreafi());
				historicoAprobacionesMasivasPk.setCodpretip(prestamoPk.getCodpretip());
				historicoAprobacionesMasivasPk.setCodprecla(prestamoPk.getCodprecla());
				HistoricoAprobacionesMasivas historicoAprobacionesMasivas = new HistoricoAprobacionesMasivas();
				historicoAprobacionesMasivas.setPk(historicoAprobacionesMasivasPk);
				historicoAprobacionesMasivas.setIdentificacionAfiliado(pqpda.getNumafi());
				historicoAprobacionesMasivas.setNombreAfiliado(pqpda.getAfiliado().getApenomafi());
				historicoAprobacionesMasivas.setIdentificacionUsuario(cedulafuncionario);
				historicoAprobacionesMasivas.setFechaProceso(fechaAprobacion);
				historicoAprobacionesMasivas.setEstadoProceso(pqpda.getEstadoPrestamo().getCodestpre());
				listadoHisAprMasivas.add(historicoAprobacionesMasivas);
				//Fin Historico Masivos
			}
		
			if (!listadoHisAprMasivas.isEmpty()) {
				
				try {
					this.administracionordencompraservicio.procesarAutorizacionPDAMasivos(
							listadoPrestamosAprobar,null,listadoHisAprMasivas,
							fechaAprobacion,cedulafuncionario,nombreFuncionario,emailFuncionario,total,secuencialHistorico,
							ipUsuario,usuario,hostRemoto);
					limpiarCargaMasivos();
					this.mensajeMasivos = super.getResource(ARCHIVO_MENSAJES, "aprobar.pda.masivo.aprobar.mensaje1") +" " + secuencialHistorico 
							+ " " +super.getResource(ARCHIVO_MENSAJES, "aprobar.pda.masivo.aprobar.mensaje2");
					visualizarAceptar = false;
				} catch (SolicitudException e) {
					limpiarCargaMasivos();
					this.mensajeMasivos = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.aprobacion");
					visualizarAceptar = false;
					log.error("Error proceso masivo: " + e.getMessage());
				}
			}
		
		} catch (ParametroBiessException e1) {
			limpiarCargaMasivos();
			this.mensajeMasivos = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.aprobacion");
			visualizarAceptar = false;
			log.error("Error al obtenet parametros de correo electronico: " + e1.getMessage());
		}
	}
	
	public void cancelarSolicitudMasiva() {
		visualizarAceptar = false;
		visualizarOrdenes = true;
	}
	
	public String cargarDatosMasivos() {
    	List<Prestamo> lista = new ArrayList<Prestamo>();
    	this.estadoPrestamo = null;
    	this.fechadesde = new Date();
    	this.fechahasta = new Date();
    	Date fechaDesdeMasivo = null;
    	Date fechaHastaMasivo = null;
    	this.idTipoProducto = null;
    	this.visualizarTemporal = true;
    	
		List<String> estados = new ArrayList<String>();
		if (this.estadoPrestamo == null
				|| this.estadoPrestamo.trim().length() <= 0) {
			estados.add(EstadoCredito.PDV.toString());
		} else {
			estados.add(estadoPrestamo);
		}
    	
    	if (cedulasPrestamos.size() > 0) {
    		String cedulasString = StringUtils.join(this.cedulasPrestamos, ',');
    		// Inicio Auditoria
    		ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultaPrestamosMasivos(cedulasString);
    		super.guardarAuditoria(OperacionEnum.CONSULTAR_SOLICITUDES_PDV_MASIVO, parametroEvento, null);
    		// Fin Auditoria
    		lista = this.prestamopdaservicio
    				.consultarPrestamosPorEstadosCedulas(estados, fechaDesdeMasivo,
    						fechaHastaMasivo, this.idTipoProducto, this.cedulasPrestamos);
    		this.listaPrestamosPDAMasivos = new ArrayList<SolicitudPda>();
    		listaPrestamosPDAMasivos = cargarListaSolicitudPDA(lista);
    		if (listaPrestamosPDAMasivos.isEmpty()) {
    			mensajeErrorCarga = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.resultado.carga");
    		} else {
	    		mensajeCarga = super.getResource(ARCHIVO_MENSAJES, "aprobar.pda.masivo.numero.creditos") + " " + listaPrestamosPDAMasivos.size();
	    		visualizarCarga = false;
	    		visualizarOrdenes = true;
	    		return "aprobarMasivoPdv";
    		}
    	} else {
    		mensajeErrorCarga = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.resultado.carga");
    	}
    	return null;
    }

	public void setListamotivorechazo(List<SelectItem> listamotivorechazo) {
		this.listamotivorechazo = listamotivorechazo;
	}

	public String getObservacionrechazo() {
		return this.observacionrechazo;
	}

	public void setObservacionrechazo(String observacionrechazo) {
		this.observacionrechazo = observacionrechazo;
	}

	public Long getRechazoseleccionado() {
		return this.rechazoseleccionado;
	}

	public void setRechazoseleccionado(Long rechazoseleccionado) {
		this.rechazoseleccionado = rechazoseleccionado;
	}

	public String getCedulafuncionario() {
		return this.cedulafuncionario;
	}

	public void setCedulafuncionario(String cedulafuncionario) {
		this.cedulafuncionario = cedulafuncionario;
	}

	public String getOkmensaje() {
		return this.okmensaje;
	}

	public void setOkmensaje(String okmensaje) {
		this.okmensaje = okmensaje;
	}

	public HtmlDataTable getTblsolicitudxaprobar() {
		return this.tblsolicitudxaprobar;
	}

	public void setTblsolicitudxaprobar(HtmlDataTable tblsolicitudxaprobar) {
		this.tblsolicitudxaprobar = tblsolicitudxaprobar;
	}

	public Date getFechadesde() {
		return this.fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechahasta() {
		return this.fechahasta;
	}

	public void setFechahasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Método que elimina caracteres especiales del nombre del afiliado en la lista de prestamos PDA
	 * 
	 */
	private void cleanNameListaPtmosPDA() {
		for (SolicitudPda solicitud : this.listaPrestamosPDA) {
			solicitud.getPrestamo().getAfiliado().setApenomafi(
					solicitud.getPrestamo().getAfiliado().getApenomafi().replaceAll("[^a-zA-Z0-9\\s]+",""));
		}
	}

	/**
	 * Setea el vaor de la cedula a nulo
	 */
	public void valorCedulaNulo() {
		this.cedula = null;
	}

	public void removerprestamodelista(SolicitudPda p) {
		this.listaPrestamosPDA.remove(p);
	}

	public void copiarfilarechazo() {
		SolicitudPda solicitudPda = (SolicitudPda) getHttpServletRequest()
				.getAttribute("item");

		this.prestamorechazo = solicitudPda;
	}

	public void borrarfilarechazo(ActionEvent e) {
		this.prestamorechazo = null;
	}

	public void rechazarsolicitudpda(ActionEvent e) {
		this.okmensaje = null;
		this.errorMessage = null;
		this.mensaje = null;
		Object objfun = context().getELContext().getELResolver()
				.getValue(context().getELContext(), null, "funcionario");
		this.cedulafuncionario = ((FuncionarioHandler) objfun).getCedula();

		Prestamo pqpdv = this.prestamorechazo.getPrestamo();
		SolicitudPda pqremover = this.prestamorechazo;
		try {
			log.info("INICIO RECHAZO DE CREDITO PARA AFILIADO: "
					+ pqpdv.getNumafi()
					+ " credito numpreafi-ordpreafi-codpretip-codprecla : "
					+ pqpdv.getPrestamoPk().getNumpreafi() + "-"
					+ pqpdv.getPrestamoPk().getOrdpreafi() + "-"
					+ pqpdv.getPrestamoPk().getCodpretip() + "-"
					+ pqpdv.getPrestamoPk().getCodprecla());

			// INC-2272 Pensiones Alimenticias ==> Se envia el estado del prestamo.
			this.prestamopdaservicio.rechazarPrestamoPda(pqpdv,
					this.observacionrechazo, this.rechazoseleccionado,
					this.cedulafuncionario, pqpdv.getEstadoPrestamo()
							.getCodestpre());

			removerprestamodelista(pqremover);
			this.okmensaje = ("Se ha rechazado el pr\u00E9stamo del afiliado : "
					+ pqpdv.getAfiliado().getApenomafi() + " con la c\u00E9dula : " + pqpdv
					.getNumafi());
			
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAprobarRechazarDetallePrestamoPDV(pqpdv.getNumafi(),
					String.valueOf(pqpdv.getPrestamoPk().getCodprecla()), String.valueOf(pqpdv.getPrestamoPk().getCodpretip()),
					String.valueOf(pqpdv.getPrestamoPk().getNumpreafi()), String.valueOf(pqpdv.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.RECHAZAR_SOLICITUDES_PDV, parametroEvento, pqpdv.getNumafi());
			// Fin Auditoria
			
		} catch (Exception ex) {
			log.error(ex.getMessage());
			this.okmensaje = null;
			this.mensaje = "Error Realizando el rechazo de la solicitud";
			log.error("Error Realizando el rechazo de la solicitud :", ex);
		}

		this.observacionrechazo = null;
		this.rechazoseleccionado = null;
	}

	public void regenerarPqAmortizacion() {
		try {
			this.prestamoQuirografarioServicio.reajustarListadoPrestamos();
			this.okmensaje = "El proceso se ejecuto sin novedades";
			this.mensaje = null;
		} catch (Exception e) {
			this.okmensaje = null;
			this.mensaje = ("Error realizando el re - calculo de la tabla de amortización: " + e
					.getMessage());
			log.error("Error Realizando el rechazo de la solicitud :", e);
		}
	}

	public List<SolicitudPda> getListaPrestamosPDA() {
		return this.listaPrestamosPDA;
	}

	public void setListaPrestamosPDA(List<SolicitudPda> listaPrestamosPDA) {
		this.listaPrestamosPDA = listaPrestamosPDA;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedula() {
		return cedula;
	}

	/**
	 * @return the estadoPrestamo
	 */
	public String getEstadoPrestamo() {
		return estadoPrestamo;
	}

	/**
	 * @param estadoPrestamo
	 *            the estadoPrestamo to set
	 */
	public void setEstadoPrestamo(String estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	/**
	 * @return the idTipoProducto
	 */
	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	/**
	 * @param idTipoProducto the idTipoProducto to set
	 */
	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	
	/**
	 * Obtiene el detalle de un prestamo.
	 */
	public void verDetallePrestamo() {
		// INC-2272 Pensiones Alimenticias
		Object objfun = context().getELContext().getELResolver()
				.getValue(context().getELContext(), null, "funcionario");
		this.cedulafuncionario = ((FuncionarioHandler) objfun).getCedula();

		this.solicitudPdaSeleccionada = (SolicitudPda) getHttpServletRequest()
				.getAttribute("item");
		
		Prestamo pqpdv = this.solicitudPdaSeleccionada.getPrestamo();

		try {
			// Datos del Beneficiario del Credito
			if (TiposProductosPq.PEN.getCodigoTipoProducto().equals(
					this.solicitudPdaSeleccionada.getPrestamo().getPrestamoPk()
							.getCodpretip())) {
				beneficiarioCredito = beneficiarioCreditoServicio.obtenerPorPK(
						this.solicitudPdaSeleccionada.getPrestamo()
								.getPrestamoPk().getCodprecla(),
						this.solicitudPdaSeleccionada.getPrestamo()
								.getPrestamoPk().getCodpretip(),
						this.solicitudPdaSeleccionada.getPrestamo()
								.getPrestamoPk().getNumpreafi(),
						this.solicitudPdaSeleccionada.getPrestamo()
								.getPrestamoPk().getOrdpreafi());
				this.setPagoPensionesAlimenticias(true);
				
				beneficiarioCredito.setProvincia(divisionPoliticaServicio
						.consultarDivisionPolitica(beneficiarioCredito
								.getProvinciaJuicio()));

				beneficiarioCredito.setCiudad(divisionPoliticaServicio
						.consultarDivisionPolitica(beneficiarioCredito
								.getCiudadJuicio()));
			} else {
				this.setPagoPensionesAlimenticias(false);
			}
			
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAprobarRechazarDetallePrestamoPDV(pqpdv.getNumafi(),
					String.valueOf(pqpdv.getPrestamoPk().getCodprecla()), String.valueOf(pqpdv.getPrestamoPk().getCodpretip()),
					String.valueOf(pqpdv.getPrestamoPk().getNumpreafi()), String.valueOf(pqpdv.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.VER_DETALLE_SOLICITUD_PDV, parametroEvento, pqpdv.getNumafi());
			// Fin Auditoria
			
		} catch (Exception e) {
			log.error("Error al recuperar los detalles del beneficiario :", e);
		}
						
	}
	
	/**
	 * 
	 */
	public void resetearDatosSolicitudPdaSeleccionada() {
		// INC-2272 Pensiones Alimenticias
		this.solicitudPdaSeleccionada = null;
	}

	/**
	 * @return the solicitudPdaSeleccionada
	 */
	public SolicitudPda getSolicitudPdaSeleccionada() {
		return solicitudPdaSeleccionada;
	}

	/**
	 * @param solicitudPdaSeleccionada the solicitudPdaSeleccionada to set
	 */
	public void setSolicitudPdaSeleccionada(SolicitudPda solicitudPdaSeleccionada) {
		this.solicitudPdaSeleccionada = solicitudPdaSeleccionada;
	}

	/**
	 * @param listaTipoPrestamoAprobacion the listaTipoPrestamoAprobacion to set
	 */
	public void setListaTipoPrestamoAprobacion(
			ArrayList<SelectItem> listaTipoPrestamoAprobacion) {
		this.listaTipoPrestamoAprobacion = listaTipoPrestamoAprobacion;
	}

	/**
	 * @return the pagoPensionesAlimenticias
	 */
	public boolean isPagoPensionesAlimenticias() {
		return pagoPensionesAlimenticias;
	}

	/**
	 * @param pagoPensionesAlimenticias the pagoPensionesAlimenticias to set
	 */
	public void setPagoPensionesAlimenticias(boolean pagoPensionesAlimenticias) {
		this.pagoPensionesAlimenticias = pagoPensionesAlimenticias;
	}

	/**
	 * @return the beneficiarioCredito
	 */
	public BeneficiarioCredito getBeneficiarioCredito() {
		return beneficiarioCredito;
	}

	/**
	 * @param beneficiarioCredito the beneficiarioCredito to set
	 */
	public void setBeneficiarioCredito(BeneficiarioCredito beneficiarioCredito) {
		this.beneficiarioCredito = beneficiarioCredito;
	}

	/**
	 * @return the visaPasaporte
	 */
	public String getVisaPasaporte() {
		return visaPasaporte;
	}

	/**
	 * @param visaPasaporte the visaPasaporte to set
	 */
	public void setVisaPasaporte(String visaPasaporte) {
		this.visaPasaporte = visaPasaporte;
	}

	/**
	 * @return the bloqueoListaControl
	 */
	public BloqueoListaControl getBloqueoListaControl() {
		return bloqueoListaControl;
	}

	/**
	 * @param bloqueoListaControl the bloqueoListaControl to set
	 */
	public void setBloqueoListaControl(BloqueoListaControl bloqueoListaControl) {
		this.bloqueoListaControl = bloqueoListaControl;
	}

	/**
	 * @return the enListaObservados
	 */
	public boolean isEnListaObservados() {
		return enListaObservados;
	}

	/**
	 * @param enListaObservados the enListaObservados to set
	 */
	public void setEnListaObservados(boolean enListaObservados) {
		this.enListaObservados = enListaObservados;
	}
	
	/**
	 * @return the listaTipoPrestamoAprobacion
	 */
	public ArrayList<SelectItem> getListaTipoPrestamoAprobacion() {
		// INC-2272 Pensiones Alimenticias
		if (listaTipoPrestamoAprobacion == null) {
			List<Long> idsTipos = new ArrayList<Long>();
			idsTipos.add(1l);
			idsTipos.add(5L);
			//INC-2286 Ferrocarriles
			idsTipos.add(6L);
			idsTipos.add(7L);
			List<TipoPrestamo> lista = tipoPrestamoServicio
					.obtenerTipoPrestamoPorIds(idsTipos);
			listaTipoPrestamoAprobacion = new ArrayList<SelectItem>();
			for (Iterator<TipoPrestamo> iterator = lista.iterator(); iterator
					.hasNext();) {
				TipoPrestamo tipoPrestamo = iterator.next();
				listaTipoPrestamoAprobacion.add(new SelectItem(tipoPrestamo
						.getCodigo(), tipoPrestamo.getDescripcion()));
			}
		}

		return listaTipoPrestamoAprobacion;
	}

	
	/**
	 * Consulta los creditos que estan en estado PDV = Pendiente de Verificacion.
	 */
	public void consultarprestamospdv() {
		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		this.okmensaje = null;
		if (this.fechadesde.after(this.fechahasta)) {
			this.mensaje = "La Fecha a consultar desde, debe ser menor igual que la fecha a consultar hasta";
			if (this.listaPrestamosPDA != null)
				this.listaPrestamosPDA.clear();
		} else {
			this.mensaje = null;
		}
		
		List<String> estados = new ArrayList<String>();
		if (this.estadoPrestamo == null
				|| this.estadoPrestamo.trim().length() <= 0) {
			estados.add(EstadoCredito.PDV.toString());
		} else {
			estados.add(estadoPrestamo);
		}
		
		// Inicio Auditoria
		ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultarPrestamoPDV(this.cedula,
				UtilDate.dateToString(this.fechadesde, "dd/MM/yyyy"), UtilDate.dateToString(this.fechahasta, "dd/MM/yyyy"),
				this.obtenerNombreLabelLista(this.listaTipoPrestamoAprobacion, this.idTipoProducto), this.visaPasaporte);
		super.guardarAuditoria(OperacionEnum.CONSULTAR_SOLICITUDES_PDV, parametroEvento, this.cedula);
		// Fin Auditoria
		
		List<Prestamo> lista = this.prestamopdaservicio
				.consultarPrestamos(estados, this.fechadesde,
						this.fechahasta, this.cedula, this.idTipoProducto, this.visaPasaporte);
			
		this.listaPrestamosPDA = new ArrayList<SolicitudPda>();
			
		if (lista != null) {
			for (Prestamo prestamo : lista) {

				SolicitudPda solicitudPDA = new SolicitudPda();
				Sucursal sucursalfila = null;
				DivisionPolitica divipoliticapatrono = null;
				String cedula = prestamo.getNumafi();
				String rucemp = prestamo.getRucemp();
				String codsucu = prestamo.getCodsuc();

				prestamo.setTipoPrestamo(this.tipoPrestamoServicio.load(prestamo.getPrestamoPk().getCodpretip()));

				prestamo.setEstadoPrestamo(this.estadoPrestamoServicio.obtenerPorCodigo(prestamo.getEstadoPrestamo()
						.getCodestpre()));

				CuentaBancariaAnterior cba = this.prestamopdaservicio.obtenerCuentabancariaAnterior(cedula);

				// INC-2148: Refugiados.
				if (prestamo.getTipoBeneficiario() == null || prestamo.getTipoBeneficiario().trim().length() <= 0) {
					solicitudPDA.setTipoBeneficiario(TipoBeneficiarioCredito.NORMAL.getDescripcion());
				} else {
					solicitudPDA.setTipoBeneficiario(TipoBeneficiarioCredito.obtenerDescripcion(prestamo
							.getTipoBeneficiario()));
				}

				if (cba != null) {
					solicitudPDA.setTipoCuentaAnterior(cba.getTipoCuentaAnterior());
					solicitudPDA.setNumeroCuentaAnterior(cba.getNumeroCuentaAnterior());
					solicitudPDA.setEntidadFinancieraAnterior(cba.getEntidadFinancieraAnterior());
					solicitudPDA.setFechaRegistroAnterior(cba.getFechaRegistroAnterior());
				}

				CuentaBancariaAfiliado ctaafidatos = this.prestamopdaservicio.obtenerCuentaValidaAfiliado(cedula);
				if (ctaafidatos == null) {
					solicitudPDA.setFechactabancaria(null);
					solicitudPDA.setPrimeravez("");
				} else {
					solicitudPDA.setFechactabancaria(ctaafidatos.getFechaRegistro());
					if (ctaafidatos.getFechaActualizacion() == null)
						solicitudPDA.setPrimeravez("PRIMERA VEZ");
					else {
						solicitudPDA.setPrimeravez("ACTUALIZACION");
					}
				}

				solicitudPDA.setPrestamo(prestamo);

				try {
					sucursalfila = this.prestamopdaservicio.obtenerSucursal(rucemp, codsucu);
					solicitudPDA.setTelsuc(sucursalfila.getTelsuc());
					solicitudPDA.setApenomrepleg(sucursalfila.getApenomrepleg());
					solicitudPDA.setDirsuc(sucursalfila.getDirsuc());
				} catch (Exception e) {
					solicitudPDA.setTelsuc("");
					solicitudPDA.setApenomrepleg("");
					solicitudPDA.setDirsuc("");
					log.error(e.getMessage());
				}
				try {
					divipoliticapatrono = this.prestamopdaservicio.consultaDivisionPoliticaPorId(sucursalfila);
					solicitudPDA.setNomdivpol(divipoliticapatrono.getDivisionPolitica().getDivisionPolitica()
							.getNomdivpol());
					solicitudPDA.setNomdivpolDP(divipoliticapatrono.getDivisionPolitica().getNomdivpol());
				} catch (Exception e) {
					solicitudPDA.setNomdivpol(null);
					solicitudPDA.setNomdivpolDP(null);
					log.error(e.getMessage());
				}
				
				// INC-2286 Ferrocarriles
				solicitudPDA.setPrestamoInformacion(prestamopdaservicio.obtenerPrestamoInformacion(prestamo
						.getCreditoPk()));
				this.listaPrestamosPDA.add(solicitudPDA);
			}
		}
		/* Limpiar nombre asegurado de carateres especiales */
		if (null != this.listaPrestamosPDA) {
			cleanNameListaPtmosPDA();
		}
	}
	
	public List<SolicitudPda> cargarListaSolicitudPDA(List<Prestamo> listaPrestamos) {
		List<SolicitudPda> listaSolicitudes = new ArrayList<SolicitudPda>();
		if (listaPrestamos != null) {
			for (Prestamo prestamo : listaPrestamos) {
				SolicitudPda solicitudPDA = new SolicitudPda();
				Sucursal sucursalfila = null;
				DivisionPolitica divipoliticapatrono = null;
				String cedula = prestamo.getNumafi();
				String rucemp = prestamo.getRucemp();
				String codsucu = prestamo.getCodsuc();
				prestamo.setTipoPrestamo(this.tipoPrestamoServicio.load(prestamo.getPrestamoPk().getCodpretip()));

				prestamo.setEstadoPrestamo(this.estadoPrestamoServicio.obtenerPorCodigo(prestamo.getEstadoPrestamo()
						.getCodestpre()));

				CuentaBancariaAnterior cba = this.prestamopdaservicio.obtenerCuentabancariaAnterior(cedula);

				// INC-2148: Refugiados.
				if (prestamo.getTipoBeneficiario() == null || prestamo.getTipoBeneficiario().trim().length() <= 0) {
					solicitudPDA.setTipoBeneficiario(TipoBeneficiarioCredito.NORMAL.getDescripcion());
				} else {
					solicitudPDA.setTipoBeneficiario(TipoBeneficiarioCredito.obtenerDescripcion(prestamo
							.getTipoBeneficiario()));
				}

				if (cba != null) {
					solicitudPDA.setTipoCuentaAnterior(cba.getTipoCuentaAnterior());
					solicitudPDA.setNumeroCuentaAnterior(cba.getNumeroCuentaAnterior());
					solicitudPDA.setEntidadFinancieraAnterior(cba.getEntidadFinancieraAnterior());
					solicitudPDA.setFechaRegistroAnterior(cba.getFechaRegistroAnterior());
				}

				CuentaBancariaAfiliado ctaafidatos = this.prestamopdaservicio.obtenerCuentaValidaAfiliado(cedula);
				if (ctaafidatos == null) {
					solicitudPDA.setFechactabancaria(null);
					solicitudPDA.setPrimeravez("");
				} else {
					solicitudPDA.setFechactabancaria(ctaafidatos.getFechaRegistro());
					if (ctaafidatos.getFechaActualizacion() == null)
						solicitudPDA.setPrimeravez("PRIMERA VEZ");
					else {
						solicitudPDA.setPrimeravez("ACTUALIZACION");
					}
				}

				solicitudPDA.setPrestamo(prestamo);

				try {
					sucursalfila = this.prestamopdaservicio.obtenerSucursal(rucemp, codsucu);
					solicitudPDA.setTelsuc(sucursalfila.getTelsuc());
					solicitudPDA.setApenomrepleg(sucursalfila.getApenomrepleg());
					solicitudPDA.setDirsuc(sucursalfila.getDirsuc());
				} catch (Exception e) {
					solicitudPDA.setTelsuc("");
					solicitudPDA.setApenomrepleg("");
					solicitudPDA.setDirsuc("");
					log.error(e.getMessage());
				}
				try {
					divipoliticapatrono = this.prestamopdaservicio.consultaDivisionPoliticaPorId(sucursalfila);
					solicitudPDA.setNomdivpol(divipoliticapatrono.getDivisionPolitica().getDivisionPolitica()
							.getNomdivpol());
					solicitudPDA.setNomdivpolDP(divipoliticapatrono.getDivisionPolitica().getNomdivpol());
				} catch (Exception e) {
					solicitudPDA.setNomdivpol(null);
					solicitudPDA.setNomdivpolDP(null);
					log.error(e.getMessage());
				}
				
				// INC-2286 Ferrocarriles
				solicitudPDA.setPrestamoInformacion(prestamopdaservicio.obtenerPrestamoInformacion(prestamo
						.getCreditoPk()));
				listaSolicitudes.add(solicitudPDA);
			}
		}
		/* Limpiar nombre asegurado de carateres especiales */
		if (null != listaSolicitudes) {
			cleanNameListaPtmosPDA(listaSolicitudes);
		}
		
		return listaSolicitudes;
	}
	
	/**
	 * Método que elimina caracteres especiales del nombre del afiliado en la lista de prestamos PDA
	 * 
	 */
	private void cleanNameListaPtmosPDA(List<SolicitudPda> listaSolicitudesPda) {
		for (SolicitudPda solicitud : listaSolicitudesPda) {
			solicitud.getPrestamo().getAfiliado().setApenomafi(
					solicitud.getPrestamo().getAfiliado().getApenomafi().replaceAll("[^a-zA-Z0-9\\s]+",""));
		}
	}
	
	
	/**
	 * Actualiza los creditos PDV a PDA.
	 */
	public void aprobarsolicitudpdv() {
		this.okmensaje = null;
		this.errorMessage = null;
		this.mensaje = null;
		Object objfun = context().getELContext().getELResolver()
				.getValue(context().getELContext(), null, "funcionario");
		this.cedulafuncionario = ((FuncionarioHandler) objfun).getCedula();

		SolicitudPda solicitudPda = (SolicitudPda) getHttpServletRequest().getAttribute("item");

		Prestamo pqpdv = solicitudPda.getPrestamo();
		try {
			log.info("INICIO APROBACION DE CREDITO PARA AFILIADO: " + pqpdv.getNumafi()
					+ " credito numpreafi-ordpreafi-codpretip-codprecla : " + pqpdv.getPrestamoPk().getNumpreafi()
					+ "-" + pqpdv.getPrestamoPk().getOrdpreafi() + "-" + pqpdv.getPrestamoPk().getCodpretip() + "-"
					+ pqpdv.getPrestamoPk().getCodprecla());

			this.administracionordencompraservicio.recalculaTablaAmortizacionparaPDA(pqpdv.getPrestamoPk()
					.getNumpreafi(), pqpdv.getPrestamoPk().getOrdpreafi(), pqpdv.getPrestamoPk().getCodpretip(), pqpdv
					.getPrestamoPk().getCodprecla(), new Date(), this.cedulafuncionario);

			removerprestamodelista(solicitudPda);

			this.okmensaje = ("Se ha Aceptado el pr\u00E9stamo del afiliado : " + pqpdv.getAfiliado().getApenomafi()
					+ " con la c\u00E9dula : " + pqpdv.getNumafi());
					
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAprobarRechazarDetallePrestamoPDV(pqpdv.getNumafi(),
					String.valueOf(pqpdv.getPrestamoPk().getCodprecla()), String.valueOf(pqpdv.getPrestamoPk().getCodpretip()),
					String.valueOf(pqpdv.getPrestamoPk().getNumpreafi()), String.valueOf(pqpdv.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.APROBAR_SOLICITUDES_PDV, parametroEvento, pqpdv.getNumafi());
			// Fin Auditoria
		} catch (NoServicioPrestadoSucursalException e) {
			this.okmensaje = null;
			this.errorMessage = e.getMessage();
			this.mensaje = e.getMessage();
			log.error("NoServicioPrestadoSucursalException: Error recalculando la tabla de amortizacion al aprobar PDV", e);
		} catch (Exception e) {
			this.okmensaje = null;
			this.errorMessage = "Error Recalculando la tabla de amortizaci\u00F3n";
			this.mensaje = "Error Recalculando la tabla de amortizaci\u00F3n";
			log.error("Error recalculando la tabla de amortizacion :", e);
		}
		
	}
	
	public void limpiarDatosPantallaGeneral() {
		this.visualizarCarga = false;
		this.listadoCedulas = new ArrayList<CedulasMasivasBean>();
		this.mensajeErrorCarga = null;
		this.mensajeErrorCedulasArchivo = null;
	}

	public List<CedulasMasivasBean> getListadoCedulas() {
		return listadoCedulas;
	}

	public void setListadoCedulas(List<CedulasMasivasBean> listadoCedulas) {
		this.listadoCedulas = listadoCedulas;
	}

	public boolean isProcesoCarga() {
		return procesoCarga;
	}

	public void setProcesoCarga(boolean procesoCarga) {
		this.procesoCarga = procesoCarga;
	}

	public UploadedFile getArchivoCedulas() {
		return archivoCedulas;
	}

	public void setArchivoCedulas(UploadedFile archivoCedulas) {
		this.archivoCedulas = archivoCedulas;
	}

	public String getMensajeErrorCedulasArchivo() {
		return mensajeErrorCedulasArchivo;
	}

	public void setMensajeErrorCedulasArchivo(String mensajeErrorCedulasArchivo) {
		this.mensajeErrorCedulasArchivo = mensajeErrorCedulasArchivo;
	}

	public boolean isVisualizarCarga() {
		return visualizarCarga;
	}

	public void setVisualizarCarga(boolean visualizarCarga) {
		this.visualizarCarga = visualizarCarga;
	}

	public List<String> getCedulasPrestamos() {
		return cedulasPrestamos;
	}

	public void setCedulasPrestamos(List<String> cedulasPrestamos) {
		this.cedulasPrestamos = cedulasPrestamos;
	}
	
	/**
	 * Devuelve la descripcion de la lista dada la lista y el valor seleccionado
	 * 
	 * @param lista
	 * @param idValue
	 * @return
	 */
	private String obtenerNombreLabelLista(List<SelectItem> lista, Long idValue) {
		String label = "";
		if (idValue == null || idValue == 0L) {
			label = "Todos";
		} else {
			for (SelectItem item : lista) {
				if (item.getValue().equals(idValue)) {
					label = item.getLabel();
					break;
				}
			}
		}

		return label;
	}

		public boolean isVisualizarOrdenes() {
			return visualizarOrdenes;
		}

		public void setVisualizarOrdenes(boolean visualizarOrdenes) {
			this.visualizarOrdenes = visualizarOrdenes;
		}

		public boolean isVisualizarAceptar() {
			return visualizarAceptar;
		}

		public void setVisualizarAceptar(boolean visualizarAceptar) {
			this.visualizarAceptar = visualizarAceptar;
		}

		public boolean isSeleccionarTodos() {
			return seleccionarTodos;
		}

		public void setSeleccionarTodos(boolean seleccionarTodos) {
			this.seleccionarTodos = seleccionarTodos;
		}

		public String getMensajeCarga() {
			return mensajeCarga;
		}

		public void setMensajeCarga(String mensajeCarga) {
			this.mensajeCarga = mensajeCarga;
		}

		public int getContadorSelecionados() {
			return contadorSelecionados;
		}

		public void setContadorSelecionados(int contadorSelecionados) {
			this.contadorSelecionados = contadorSelecionados;
		}

		public List<SolicitudPda> getSolicitudPdaAuxiliar() {
			return solicitudPdaAuxiliar;
		}

		public void setSolicitudPdaAuxiliar(List<SolicitudPda> solicitudPdaAuxiliar) {
			this.solicitudPdaAuxiliar = solicitudPdaAuxiliar;
		}

		public String getMensajeMasivos() {
			return mensajeMasivos;
		}

		public void setMensajeMasivos(String mensajeMasivos) {
			this.mensajeMasivos = mensajeMasivos;
		}

		public String getMensajeErrorCarga() {
			return mensajeErrorCarga;
		}

		public void setMensajeErrorCarga(String mensajeErrorCarga) {
			this.mensajeErrorCarga = mensajeErrorCarga;
		}

		public List<SolicitudPda> getListaPrestamosPDAMasivos() {
			return listaPrestamosPDAMasivos;
		}

		public void setListaPrestamosPDAMasivos(List<SolicitudPda> listaPrestamosPDAMasivos) {
			this.listaPrestamosPDAMasivos = listaPrestamosPDAMasivos;
		}

		public boolean isVisualizarTemporal() {
			if (!visualizarTemporal) {
				limpiarDatosPantallaGeneral();
			}
			visualizarTemporal = false;
			return visualizarTemporal;
		}

		public void setVisualizarTemporal(boolean visualizarTemporal) {
			this.visualizarTemporal = visualizarTemporal;
		}
}