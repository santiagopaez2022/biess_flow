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
import javax.faces.context.FacesContext;
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
import ec.fin.biess.listaobservados.exception.BloqueoListaControlException;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.fin.biess.listaobservados.service.BloqueoListaControlServicioLocal;
import ec.fin.biess.modelo.persistencia.HistoricoAprobacionesMasivas;
import ec.fin.biess.modelo.persistencia.pk.HistoricoAprobacionesMasivasPk;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.enumeracion.TipoBeneficiarioCredito;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.CuentaBancariaAnterior;
import ec.gov.iess.creditos.modelo.dto.SolicitudPda;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoRechazoCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacionDetalle;
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

public class AprobacionPdaBacking extends BaseBean implements Serializable {

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
			.getLogger(AprobacionPdaBacking.class);

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
	
	private CategoriaTipoProductoPq categoriaTipoProductoPq;
	
	// INC-2148: Refugiados.
    private String visaPasaporte;
	
	private List<PrestamoInformacionDetalle> listaInformacionDetalle;
    
    private PrestamoInformacionDetalle adjuntoSeleccionado;
    
    // INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
    @EJB(name = "BloqueoListaControlServicioImpl/local")
	private BloqueoListaControlServicioLocal bloqueoListaControlServicio;
    
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
    
    public AprobacionPdaBacking() {
  	}
    
    @PostConstruct
	public void init() {
    	limpiarCargaMasivos();
	}
        
    public void limpiarCargaMasivos() {
    	//deshabilitarBotones = false;
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
    
    /**
	 * Busca el detalle de planillas de los empleados registrados en un archivo 
	 * 
	 * @return
	 */
	public String validarArchivoCedulas() {
		limpiarCargaMasivos();
		visualizarTemporal = true;
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
    
	public String cargarDatosMasivos() {
    	List<Prestamo> lista = new ArrayList<Prestamo>();
    	List<String> estados = new ArrayList<String>();
    	this.estadoPrestamo = null;
    	this.fechadesde = new Date();
    	this.fechahasta = new Date();
    	Date fechaDesdeMasivo = null;
    	Date fechaHastaMasivo = null;
    	this.idTipoProducto = null;
    	this.visualizarTemporal = true;
    	
		if (this.estadoPrestamo == null
				|| this.estadoPrestamo.trim().length() <= 0) {
			estados.add(EstadoCredito.PDA.toString());
			estados.add(EstadoCredito.PDC.toString());
		} else {
			estados.add(estadoPrestamo);
		}
		
    	if (cedulasPrestamos.size() > 0) {
    		String cedulasString = StringUtils.join(this.cedulasPrestamos, ',');
    		// Inicio Auditoria
    		ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultaPrestamosMasivos(cedulasString);
    		super.guardarAuditoria(OperacionEnum.CONSULTAR_SOLICITUDES_PDAPDC_MASIVO, parametroEvento, null);
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
	    		return "aprobarMasivo";
    		}
    	} else {
    		mensajeErrorCarga = super.getResource(ARCHIVO_ERRORES, "aprobar.pda.masivo.error.resultado.carga");
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
			List<Prestamo> listadoPrestamosPDV = new ArrayList<Prestamo>();
			List<HistoricoAprobacionesMasivas> listadoHisAprMasivas = new ArrayList<HistoricoAprobacionesMasivas>();
			int total = solicitudPdaAuxiliar.size();
			BigDecimal secuencialHistorico = administracionordencompraservicio.consultarCodigoHistorico();
			for (SolicitudPda solicitudPda : solicitudPdaAuxiliar) {
				Prestamo pqpda = solicitudPda.getPrestamo();
				this.enListaObservados = this.verificarListaControl(solicitudPda);
				if (this.enListaObservados
						&& pqpda.getEstadoPrestamo().getCodestpre().equalsIgnoreCase(EstadoCredito.PDA.toString())) {
					listadoPrestamosPDV.add(pqpda);
				} else {
					listadoPrestamosAprobar.add(pqpda);
				}
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
							listadoPrestamosAprobar,listadoPrestamosPDV,listadoHisAprMasivas,
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

	public void consultarprestamospda() {
		this.okmensaje = null;
		String estado = null;
		if (this.fechadesde.after(this.fechahasta)) {
			this.mensaje = super.getResource(ARCHIVO_MENSAJES, "aprobar.pda.masivo.fecha.mensaje");
			if (this.listaPrestamosPDA != null)
				this.listaPrestamosPDA.clear();
		} else {
			this.mensaje = null;
		}
		
		// INC-2272 Pensiones Alimenticias ==> obtiene prestamos en estado: PDA
		// o PDC. // INC-2148: Refugiados.
		List<String> estados = new ArrayList<String>();
		if (this.estadoPrestamo == null
				|| this.estadoPrestamo.trim().length() <= 0) {
			estados.add(EstadoCredito.PDA.toString());
			estados.add(EstadoCredito.PDC.toString());
			estado = "Todos";
		} else {
			estados.add(estadoPrestamo);
			estado = estadoPrestamo;
		}
		
		// Inicio Auditoria
		ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultaPrestamoPDA(this.cedula, estado,
				UtilDate.dateToString(this.fechadesde, "dd/MM/yyyy"), UtilDate.dateToString(this.fechahasta, "dd/MM/yyyy"),
				this.obtenerNombreLabelLista(this.listaTipoPrestamoAprobacion, this.idTipoProducto), this.visaPasaporte);
		super.guardarAuditoria(OperacionEnum.CONSULTAR_SOLICITUDES_PENDIENTES, parametroEvento, this.cedula);
		// Fin Auditoria
		
		// INC-2148: Refugiados - nuevo parametro visaPasaporte.
		List<Prestamo> lista = this.prestamopdaservicio
				.consultarPrestamos(estados, this.fechadesde,
						this.fechahasta, this.cedula, this.idTipoProducto, this.visaPasaporte);
			
		this.listaPrestamosPDA = new ArrayList<SolicitudPda>();
		
		this.listaPrestamosPDA = cargarListaSolicitudPDA(lista);
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
	 * Setea el vaor de la cedula a nulo
	 */
	public void valorCedulaNulo() {
		this.cedula = null;
	}

	public void removerprestamodelista(SolicitudPda p) {
		this.listaPrestamosPDA.remove(p);
	}

	public void aprobarsolicitudpda() {
		this.okmensaje = null;
		this.errorMessage = null;
		this.mensaje = null;
		Object objfun = context().getELContext().getELResolver()
				.getValue(context().getELContext(), null, "funcionario");
		this.cedulafuncionario = ((FuncionarioHandler) objfun).getCedula();

		SolicitudPda solicitudPda = (SolicitudPda) getHttpServletRequest()
				.getAttribute("item");

		Prestamo pqpda = solicitudPda.getPrestamo();
		try {
			
			this.enListaObservados = this.verificarListaControl(solicitudPda);
			
			log.info("INICIO APROBACION DE CREDITO PARA AFILIADO: "
					+ pqpda.getNumafi()
					+ " credito numpreafi-ordpreafi-codpretip-codprecla : "
					+ pqpda.getPrestamoPk().getNumpreafi() + "-"
					+ pqpda.getPrestamoPk().getOrdpreafi() + "-"
					+ pqpda.getPrestamoPk().getCodpretip() + "-"
					+ pqpda.getPrestamoPk().getCodprecla());

			log.info("En listaObservados >>>" + this.enListaObservados);
			log.info("Estado es >>>" + pqpda.getEstadoPrestamo().getCodestpre());
			if (this.enListaObservados
					&& pqpda.getEstadoPrestamo().getCodestpre().equalsIgnoreCase(EstadoCredito.PDA.toString())) {
				log.info("Entra en PDV");
				this.prestamopdaservicio.actualizarcabeceraprestamoPDV(pqpda.getPrestamoPk());
				removerprestamodelista(solicitudPda);
			} else {
				log.info("Entra en RecalcularPDA");
				this.administracionordencompraservicio.recalculaTablaAmortizacionparaPDA(pqpda.getPrestamoPk()
						.getNumpreafi(), pqpda.getPrestamoPk().getOrdpreafi(), pqpda.getPrestamoPk().getCodpretip(),
						pqpda.getPrestamoPk().getCodprecla(), new Date(), this.cedulafuncionario);

				removerprestamodelista(solicitudPda);
			}
			
			this.okmensaje = ("Se ha Aceptado el pr\u00E9stamo del afiliado : "
					+ pqpda.getAfiliado().getApenomafi() + " con la c\u00E9dula : " + pqpda
					.getNumafi());
					
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAprobarRechazarDetallePrestamoPDA(pqpda.getNumafi(),
					pqpda.getEstadoPrestamo().getCodestpre(), UtilDate.dateToString(this.fechadesde, "dd/MM/yyyy"),
					UtilDate.dateToString(this.fechahasta, "dd/MM/yyyy"),
					this.obtenerNombreLabelLista(this.listaTipoPrestamoAprobacion, this.idTipoProducto), this.visaPasaporte,
					String.valueOf(pqpda.getPrestamoPk().getCodprecla()), String.valueOf(pqpda.getPrestamoPk().getCodpretip()),
					String.valueOf(pqpda.getPrestamoPk().getNumpreafi()), String.valueOf(pqpda.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.APROBAR_SOLICITUDES_PENDIENTES, parametroEvento, pqpda.getNumafi());
			// Fin Auditoria
			
		} catch (NoServicioPrestadoSucursalException e) {
			this.okmensaje = null;
			this.errorMessage = e.getMessage();
			this.mensaje = e.getMessage();
			log.error("NoServicioPrestadoSucursalException: Error recalculando la tabla de amortizacion al aprobar PDA", e);
		} catch (Exception e) {
			this.okmensaje = null;
			this.errorMessage = "Error Recalculando la tabla de amortizaci\u00F3n";
			this.mensaje = "Error Recalculando la tabla de amortizaci\u00F3n";
			log.error("Error recalculando la tabla de amortizacion :", e);
		}
		
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

		Prestamo pqpda = this.prestamorechazo.getPrestamo();
		SolicitudPda pqremover = this.prestamorechazo;
		try {
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAprobarRechazarDetallePrestamoPDA(pqpda.getNumafi(),
					pqpda.getEstadoPrestamo().getCodestpre(), UtilDate.dateToString(this.fechadesde, "dd/MM/yyyy"),
					UtilDate.dateToString(this.fechahasta, "dd/MM/yyyy"),
					this.obtenerNombreLabelLista(this.listaTipoPrestamoAprobacion, this.idTipoProducto), this.visaPasaporte,
					String.valueOf(pqpda.getPrestamoPk().getCodprecla()), String.valueOf(pqpda.getPrestamoPk().getCodpretip()),
					String.valueOf(pqpda.getPrestamoPk().getNumpreafi()), String.valueOf(pqpda.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.RECHAZAR_SOLICITUDES_PENDIENTES, parametroEvento, pqpda.getNumafi());
			// Fin Auditoria
			
			log.info("INICIO RECHAZO DE CREDITO PARA AFILIADO: "
					+ pqpda.getNumafi()
					+ " credito numpreafi-ordpreafi-codpretip-codprecla : "
					+ pqpda.getPrestamoPk().getNumpreafi() + "-"
					+ pqpda.getPrestamoPk().getOrdpreafi() + "-"
					+ pqpda.getPrestamoPk().getCodpretip() + "-"
					+ pqpda.getPrestamoPk().getCodprecla());

			// INC-2272 Pensiones Alimenticias ==> Se envia el estado del prestamo.
			this.prestamopdaservicio.rechazarPrestamoPda(pqpda,
					this.observacionrechazo, this.rechazoseleccionado,
					this.cedulafuncionario, pqpda.getEstadoPrestamo()
							.getCodestpre());

			removerprestamodelista(pqremover);
			this.okmensaje = ("Se ha rechazado el pr\u00E9stamo del afiliado : "
					+ pqpda.getAfiliado().getApenomafi() + " con la c\u00E9dula : " + pqpda
					.getNumafi());
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
			this.mensaje = ("Error realizando el re - calculo de la tabla de amortizaci\u00F3n: " + e
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
		
		Prestamo pqpda = this.solicitudPdaSeleccionada.getPrestamo();

		try {
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAprobarRechazarDetallePrestamoPDA(pqpda.getNumafi(),
					pqpda.getEstadoPrestamo().getCodestpre(), UtilDate.dateToString(this.fechadesde, "dd/MM/yyyy"),
					UtilDate.dateToString(this.fechahasta, "dd/MM/yyyy"),
					this.obtenerNombreLabelLista(this.listaTipoPrestamoAprobacion, this.idTipoProducto), this.visaPasaporte,
					String.valueOf(pqpda.getPrestamoPk().getCodprecla()), String.valueOf(pqpda.getPrestamoPk().getCodpretip()),
					String.valueOf(pqpda.getPrestamoPk().getNumpreafi()), String.valueOf(pqpda.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.VER_DETALLE_SOLICITUD_PENDIENTE, parametroEvento, pqpda.getNumafi());
			// Fin Auditoria
			
			/* Obtener la categoria del credito */
			TiposProductosPq tiposProductosPq = TiposProductosPq.getTiposProductosPq(solicitudPdaSeleccionada.getPrestamo().getPrestamoPk().getCodpretip());
			categoriaTipoProductoPq = tiposProductosPq.getCategoriaTipoProducto();
			
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
			
			this.enListaObservados = this.verificarListaControl(this.solicitudPdaSeleccionada);
		} catch (Exception e) {
			log.error("Error al recuperar los detalles del beneficiario :", e);
		}
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

	public void obtenerAdjuntos() {
		solicitudPdaSeleccionada = (SolicitudPda) getHttpServletRequest().getAttribute("item");
		listaInformacionDetalle = prestamopdaservicio.obtenerPrestamoInformacionDetalle(solicitudPdaSeleccionada
				.getPrestamo().getPrestamoPk());
	}
	
	public String descargarArchivo() {
		adjuntoSeleccionado = (PrestamoInformacionDetalle) getHttpServletRequest().getAttribute("_detalle");		
		try {
			if (adjuntoSeleccionado.getArchivo() != null) {
				redirect(getContextName() + "/descargarArchivo");
			}
		} catch (Exception e) {
			log.error("Error al descargar archivo");
		}
		
		return "";
	}
	
	public void limpiarDatosPantallaGeneral() {
		this.visualizarCarga = false;
		this.listadoCedulas = new ArrayList<CedulasMasivasBean>();
		this.mensajeErrorCarga = null;
		this.mensajeErrorCedulasArchivo = null;
	}

	/**
	 * Se encarga de recuperar el nombre del contexto de la aplicacion web.
	 * 
	 * @return Una cadena con el nombre del contexto
	 */
	public final String getContextName() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	/**
	 * Se encarga de ejecutar una redireccion.
	 * 
	 * @param url
	 *            url de destino
	 * @throws IOException
	 *             en caso de no poder hacer la redireccion
	 */
	public void redirect(final String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
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
	 * @return the listaTipoPrestamoAprobacion
	 */
	public ArrayList<SelectItem> getListaTipoPrestamoAprobacion() {
		// INC-2272 Pensiones Alimenticias
		if (listaTipoPrestamoAprobacion == null) {
			List<Long> idsTipos = new ArrayList<Long>();
			idsTipos.add(1L);
			//idsTipos.add(5L);
			//INC-2286 Ferrocarriles
			idsTipos.add(6L);
			idsTipos.add(7L);
			idsTipos.add(8L);
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
	 * Verifica si un credito esta en PEP.
	 * 
	 * @param solicitudPda - datos del la solicitud.
	 * 
	 * @return true esta en lista, caso contrario false.
	 */
	private boolean verificarListaControl(SolicitudPda solicitudPda) {
		
		// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
		boolean resp = false;
		
		try {
			bloqueoListaControl = bloqueoListaControlServicio.obtenerPorCredito(solicitudPda.getPrestamo()
					.getPrestamoPk().getCodprecla(), solicitudPda.getPrestamo().getPrestamoPk().getCodpretip(),
					solicitudPda.getPrestamo().getPrestamoPk().getNumpreafi(), solicitudPda.getPrestamo()
							.getPrestamoPk().getOrdpreafi(), "PQ");
			if (bloqueoListaControl == null || bloqueoListaControl.getIdBloqueo() == null) {
				resp = false;
			} else {
				resp = true;
			}
		} catch (BloqueoListaControlException e) {
			log.error("No se pudo verificar la informaciÃ³n en la lista de observados.", e);
		}
		
		return resp;
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

	/**
	 * @return the listaInformacionDetalle
	 */
	public List<PrestamoInformacionDetalle> getListaInformacionDetalle() {
		return listaInformacionDetalle;
	}

	/**
	 * @param listaInformacionDetalle the listaInformacionDetalle to set
	 */
	public void setListaInformacionDetalle(List<PrestamoInformacionDetalle> listaInformacionDetalle) {
		this.listaInformacionDetalle = listaInformacionDetalle;
	}

	/**
	 * @return the adjuntoSeleccionado
	 */
	public PrestamoInformacionDetalle getAdjuntoSeleccionado() {
		return adjuntoSeleccionado;
	}

	/**
	 * @param adjuntoSeleccionado the adjuntoSeleccionado to set
	 */
	public void setAdjuntoSeleccionado(PrestamoInformacionDetalle adjuntoSeleccionado) {
		this.adjuntoSeleccionado = adjuntoSeleccionado;
	}

	public List<CedulasMasivasBean> getListadoCedulas() {
		return listadoCedulas;
	}

	public void setListadoCedulas(List<CedulasMasivasBean> listadoCedulas) {
		this.listadoCedulas = listadoCedulas;
	}

	public boolean isVisualizarCarga() {
		return visualizarCarga;
	}

	public void setVisualizarCarga(boolean visualizarCarga) {
		this.visualizarCarga = visualizarCarga;
	}

	public boolean isVisualizarOrdenes() {
		return visualizarOrdenes;
	}

	public void setVisualizarOrdenes(boolean visualizarOrdenes) {
		this.visualizarOrdenes = visualizarOrdenes;
	}

	public List<String> getCedulasPrestamos() {
		return cedulasPrestamos;
	}

	public void setCedulasPrestamos(List<String> cedulasPrestamos) {
		this.cedulasPrestamos = cedulasPrestamos;
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

	public List<SolicitudPda> getListaPrestamosPDAMasivos() {
		return listaPrestamosPDAMasivos;
	}

	public void setListaPrestamosPDAMasivos(
			List<SolicitudPda> listaPrestamosPDAMasivos) {
		this.listaPrestamosPDAMasivos = listaPrestamosPDAMasivos;
	}

	public String getMensajeMasivos() {
		return mensajeMasivos;
	}

	public void setMensajeMasivos(String mensajeMasivos) {
		this.mensajeMasivos = mensajeMasivos;
	}

	public UploadedFile getArchivoCedulas() {
		return archivoCedulas;
	}

	public void setArchivoCedulas(UploadedFile archivoCedulas) {
		this.archivoCedulas = archivoCedulas;
	}

	public boolean isProcesoCarga() {
		return procesoCarga;
	}

	public void setProcesoCarga(boolean procesoCarga) {
		this.procesoCarga = procesoCarga;
	}

	public String getMensajeErrorCedulasArchivo() {
		return mensajeErrorCedulasArchivo;
	}

	public void setMensajeErrorCedulasArchivo(String mensajeErrorCedulasArchivo) {
		this.mensajeErrorCedulasArchivo = mensajeErrorCedulasArchivo;
	}

	public boolean isVisualizarAceptar() {
		return visualizarAceptar;
	}

	public void setVisualizarAceptar(boolean visualizarAceptar) {
		this.visualizarAceptar = visualizarAceptar;
	}

	public String getMensajeErrorCarga() {
		return mensajeErrorCarga;
	}

	public void setMensajeErrorCarga(String mensajeErrorCarga) {
		this.mensajeErrorCarga = mensajeErrorCarga;
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