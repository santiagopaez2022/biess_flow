/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import ec.fin.biess.creditos.pq.excepcion.ConsultaParametroException;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.fin.biess.shop.integration.webservice.BiessShopService;
import ec.fin.biess.shop.integration.webservice.Orden;
import ec.fin.biess.shop.integration.webservice.OrdenWSService;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ITransaccionRecaudacionDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.dto.DatosOrdenCompra;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.ObtenerProductosExcepcion;
import ec.gov.iess.creditos.pq.excepcion.ProveedorException;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaParametroServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitudCreditoServicio;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancaria;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.modelo.TipoCuenta;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.MensajesUtil;
import ec.gov.iess.pq.concesion.web.util.Utilities;
import ec.gov.iess.util.EnmascararUtil;

/**
 * 
 * <b> Permite enlazar datos de productos de prestamos quirografarios con la
 * pantalla </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:30:30 $]
 *          </p>
 */
public class TipoProductosBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -1491088389758874645L;

	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	private AdministracionOrdenCompraProveedorServicio administracionOrdenCompraServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;
	//INC-2286 Ferrocarriles
	@EJB(name="InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(name="ProveedorServicioImpl/local")
	private transient ProveedorServicio proveedorServicio;
	
	@EJB(name = "SolicitudCreditoServicioImpl/local")
	private SolicitudCreditoServicio solicitudCreditoServicio;
	
	@EJB(name = "SolicitanteServicioImpl/local")
	private SolicitanteServicio solicitanteServicio;
	
	@EJB(name = "TransaccionRecaudacionDaoImpl/local")
	private ITransaccionRecaudacionDao transaccionRecaudacionDao;
	
	@EJB(name="ConsultaParametroServicioImpl/local")
	private ConsultaParametroServicioLocal consultaParametroServicio;
	
	/* Servicio para consultar datos del afiliado */
	@EJB(name = "AfiliadoServicioImpl/local")
	AfiliadoServicio afiliadoServicio;
	
	private boolean habilitarNormal = false;
	private boolean habilitarCompu = false;
	private boolean habilitarTuristico = false;
	private String mensageProductos;
	private String mensageAutorizacion;
	private boolean mostrarMensageEndudamiento = false;
	private String codigoTipoProducto;
	private DatosBean datos;
	private boolean cargado = false;
	//INC-2286 Ferrocarriles
	private List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
	private String idProveedorSeleccionado;
	private Proveedor proveedorSeleccionado;
	private List<SelectItem> listaProveedorSelectItems;
	//bloquenovacion planillaje
	private boolean bloqueaNovacion;
	
	private boolean mostrarMsgInfo;

	//valor para busqueda de producto
	private String codigoEspecialProd;

	//mensje error 
	private String msgErrNovacion;

	/**
	 * Rango habilitado novaciones jubilados
	 */
	private String rangoHabiNovJub;

	/**
	 * Rango habilitado novaciones 
	 */
    private String rangoHabiNovAfi;
    /**
	 * Rango habilitado novaciones afiliados-jubilados.
	 */
    private String rangoHabiNovAfiJub;
    /**
     * Rango de habilitacion de jubilado
     */
    private String rangoNovJub;
    /**
     * Rango de habiliracion afiliados.
     */
    private String rangoNovAfi;
    /**
     * Rango de habilitacion afiliados-jubilados.
     */
    private String rangoNovAfiJub;


	/**
	 * Bandera para determinar en que pantalla mostrar
	 * 0 Pantalla Produto Normal
	 * 1 Pantalla Otros Productos Normal
	 * 2 Pantalla Producto Novacion
	 * 3 Pantalla Otros Productos Novacion
	 */
	private int tipoSolicitud;

	private String errorMsg;

	private transient static final LoggerBiess LOG = LoggerBiess.getLogger(TipoProductosBean.class);
	
	private String mensajeInformativo;
	
	private Afiliado afiliado;
	
	private String diaInicioGenerarComp;
	
	private String diaFinGenerarComp;
	
	private String periodoGeneracionComprobantes;
	
	public TipoProductosBean() {
	}
	/**
	 * Inicializar
	 */
	@PostConstruct
	public void init() {
		msgErrNovacion=null;
		try {
			this.rangoNovJub= consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABNOVJU");
			this.rangoNovAfi=consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABNOVAFI");
			this.rangoNovAfiJub=consultaParametroServicio.consultarParametroString("PQW_CON_DIAHABNOVAFJ");
			this.rangoHabiNovJub=obtenerRangos(Utilities.obtenerRangosDias(rangoNovJub).trim());
			this.rangoHabiNovAfi=obtenerRangos(Utilities.obtenerRangosDias(rangoNovAfi).trim());
			this.rangoHabiNovAfiJub=obtenerRangos(Utilities.obtenerRangosDias(rangoNovAfiJub).trim());
		
			this.datos.setBloqueoNovaPlanillaje(!this.prestamoServicio.
					validarHabilitaNovacionPlanillaje(this.datos.getRolPrestamista(),rangoNovJub,rangoNovAfi, rangoNovAfiJub));
			
			this.periodoGeneracionComprobantes= super.devolverMensaje("condiciones","novacion.item6",obtenerNumeroOrdinal(Utilities.obtenerRangosDias(rangoNovAfi).trim()));
			
		} catch (PeriodoComprobanteException e) {
			msgErrNovacion=e.getMessage();
			LOG.error(e.getMessage());
		} catch (ConsultaParametroException e) {
			msgErrNovacion="";
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Permite obtener numero ordinal de dia de inicio y fin de generacion comprobante
	 * @param rangos
	 * @return
	 */
	private static String obtenerNumeroOrdinal(String rangos) {

		String[] primerArreglo = rangos.split(":");		
		String[] valores=primerArreglo[0].split("-");
		
		int diaInicio= Integer.valueOf(valores[0]);
		int diaFin= Integer.valueOf(valores[1]);
		
		return Utilities.obtenerNumeroOrdinal(diaInicio)+" al "+ Utilities.obtenerNumeroOrdinal(diaFin);

	}
	
	/**
	 * Permite obtener los rangos para mostrar al cliente
	 * @param rangos
	 * @return
	 */
	private static String obtenerRangos(String rangos) {
		String valor="";
		String[] primerArreglo = rangos.split(":");
		for(int i=0;i<primerArreglo.length;i++) {
			 String[] valores=primerArreglo[i].split("-"); 
			  if(primerArreglo.length>2) {
            	  valor=valor+Integer.valueOf(valores[0])+" al "+Integer.valueOf(valores[1])+(i==primerArreglo.length-2?" y del ":primerArreglo.length==i+1?" de cada mes.":",");
              }else {
            	  valor=valor+Integer.valueOf(valores[0])+" al "+Integer.valueOf(valores[1])+(primerArreglo.length==i+1?" de cada mes.":" y del ");	
              }
		}
	return valor;
	}
	
	
	public void	consultarDatosAfiliado(String cedula) {
		try {
			afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);
			if (afiliado != null) {
				String emailAfiliado = afiliado.getMaiafi();
				if (emailAfiliado != null) {
					datos.setMascaraEmail(EnmascararUtil.enmascararMail(3, null, emailAfiliado)); 
				} else {
					datos.setMascaraEmail(null);
				}
				if (afiliado.getCelafi() != null) {
					datos.setMascaraCelular(EnmascararUtil.enmascararDato(0, 6, afiliado.getCelafi()));
				} else {
					datos.setMascaraCelular(null);
				}
			} 
		} catch (AfiliadoException e) {
			afiliado = null;
			LOG.error(e.getMessage());
		}
	}

	public void load() {
		habilitarNormal = true;
		habilitarCompu = true;
		habilitarTuristico = false;
	}

	/**
	 * 
	 * @return
	 */
	public String seleccionarProductoNovacion() {
		this.datos.setProductoProductivo(false);
		this.datos.setProdViajaEcu(false);
		this.mensajeInformativo = null;
		this.tipoSolicitud = 2;
		mostrarMsgInfo = false;
		return this.seleccionarProducto(true, false);//Es novacion y no selecciono emergente
	}
	
	/**
	 * 
	 * @return
	 */
	public String seleccionarTipoOtrosProducto() {
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.datos.setProdViajaEcu(false);
		this.tipoSolicitud = 1;
		return this.seleccionarProducto();
	}
	

	/**
	 * 
	 * @return
	 */
	public String seleccionarProductoDinamico() {
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.datos.setProdViajaEcu(false);
		this.tipoSolicitud = 1;
		return this.seleccionarProducto();
	}
	/**
	 * 
	 * @return
	 */
	public String seleccionarTipoOtrosProductoNovacion() {
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.tipoSolicitud = 3;
		return this.seleccionarProducto();
	}
	
	/**
	 * 
	 * @return
	 */
	public String seleccionarProducto() {
		mostrarMsgInfo = false;
		idProveedorSeleccionado = null;
		this.datos.setDiscapacitado(false);
		vaciarDatoDinamico();
		return this.seleccionarProducto(false, false);//No es novacion y no selecciono emergente
	}
	
	private void vaciarDatoDinamico() {
		this.datos.setDataProductoDinamico(null);
		this.datos.setDataRespGenericaResponseDto(null);
		this.datos.setCodigoProdEspecial(null);
		this.datos.setCodigoContratoProv(null);

	}

	/**
	 * 
	 * @return
	 */
	public String seleccionarProductoNormal() {
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.datos.setProdViajaEcu(false);
		this.tipoSolicitud = 0;
		return this.seleccionarProducto();
	}
	
	
	/**
	 * Metodo para selecciona el nuevo producto viaja Ecuador
	 * @return retorna la siguiente pagina
	 */
	public String seleccionarProductoViajaEcuador() {
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.datos.setProdViajaEcu(true);
		this.tipoSolicitud = 0;
		return this.seleccionarProducto();
	}
	
	public String seleccionarProductoProductivo() {
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(true);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.tipoSolicitud = 0;
		return this.seleccionarProducto();
	}
	
	/**
	 * Producto para Biess Emergente para los damnificados del terremoto
	 * 
	 * @return
	 */
	public String seleccionarBiessEmergente() {
		//Porque esta dentro de la pantalla de producto Normal
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.tipoSolicitud = 0;
		mostrarMsgInfo = false;
		idProveedorSeleccionado = null;
		return this.seleccionarProducto(false, true);//No es novacion y selecciono emergente
	}
	
	/**
	 * Producto para Biess Mujer
	 * 
	 * @return
	 */
	public String seleccionarBiessMujer() {
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.tipoSolicitud = 0;
		return this.seleccionarProducto();
	}
	
	/**
	 * Producto para Biess Mama
	 * 
	 * @return
	 */
	public String seleccionarBiessMama() {
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(true);
		this.datos.setProductoBiessPapaSeleccionado(false);
		this.tipoSolicitud = 0;
		return this.seleccionarProducto();
	}
	
	/**
	 * Producto para Biess Papa
	 * 
	 * @return
	 */
	public String seleccionarBiessPapa() {
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.datos.setProductoBiessMamaSeleccionado(false);
		this.datos.setProductoBiessPapaSeleccionado(true);
		this.tipoSolicitud = 0;
		return this.seleccionarProducto();
	}
	
	/**
	 * Seleccionar novaciones para Biess Emergente
	 * 
	 * @return
	 */
	public String seleccionarBiessNovacionEmergente() {
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.tipoSolicitud = 2;
		mostrarMsgInfo = false;
		idProveedorSeleccionado = null;
		return this.seleccionarProducto(true, true);//Es novacion y selecciono emergente
	}
		
	private String seleccionarProducto(boolean esNovacion, boolean esOpcionEmergente) {		
		this.errorMsg=null;
		this.datos.setDiscapacitado(false);
		if (codigoTipoProducto == null || codigoTipoProducto.isEmpty()) {
			return "productos";
		}
		if (datos.getSolicitante() == null) {
			return null;
		}
		
		// Validacion creditos en estado de bloqueo ERC y ECC.
		datos.setListaPrestamosERC(null);
		datos.setListaPrestamosECC(null);
		datos.setListaPrestamosERC(prestamoServicio.poseeCreditosERC(datos
				.getSolicitante().getDatosPersonales().getCedula()));
		datos.setListaPrestamosECC(prestamoServicio.poseeCreditosECC(datos
				.getSolicitante().getDatosPersonales().getCedula()));
		if (null != datos.getListaPrestamosERC()
				|| null != datos.getListaPrestamosECC()) {
			return "errorRegistroCivil";
		}
		
		datos.setPagoPensionesAlimenticias(false);
		mostrarMensageEndudamiento = false;
		
		TiposProductosPq tiposProductosPq = TiposProductosPq.valueOf(codigoTipoProducto);
		CategoriaTipoProductoPq categoriaTipoProductoPq = tiposProductosPq.getCategoriaTipoProducto();
		//
		datos.setDatosOrdenCompra(null);
		datos.setCreditoNovacion(esNovacion);
		datos.setPrestamoNovacionSeleccionado(null);
		datos.setTiposProductosPq(tiposProductosPq);
		datos.setCategoriaTipoProductoPq(categoriaTipoProductoPq);
		datos.setCodigoProdEspecial(codigoEspecialProd==null?null:Long.valueOf(codigoEspecialProd));
		//
		obtenerProductos(tiposProductosPq, categoriaTipoProductoPq);
		if (mostrarMensageEndudamiento) {
			return "productos";
		}
		mensageAutorizacion();
		datos.getDatosOrdenCompra().setMensageAutorizacion(mensageAutorizacion);
		
		
		 boolean perteneceZona = true;	
		this.calificaBiessEmergente(perteneceZona);
		
		// Setea informacion del empleador
		Solicitante solicitante = datos.getSolicitante();
		try {
			try {
				// Determinamos el empleador del solictante segun su rol y si es emergente
				this.solicitanteServicio.determinarEmpleador(datos.getSolicitante().getDatosPersonales().getCedula(),
						datos.getTipo(), solicitante, this.datos.isProductoBiessEmergente());
			} catch (SucursalException e) {
				throw new SolicitanteExcepcion(e);
			} catch (ServicioPrestadoException e) {
				throw new SolicitanteExcepcion("ServicioPrestadoException", e);
			} catch (NoServicioPrestadoSucursalException e) {
				LOG.info("El afiliado no tiene empleador o sucursal: " + datos.getSolicitante().getDatosPersonales().getCedula());
			}
		} catch (SolicitanteExcepcion e) {
			this.errorMsg = MensajesUtil.getErrorMessage(FacesContext.getCurrentInstance(), "credito.error.aplicativo");
			LOG.error("Error al cargar solicitante y cargas", e);
			return null;
		}

		solicitante.setTipo(datos.getTipo());
		datos.setSolicitante(solicitante);
		
		LOG.info("Pertenece a zona de damnificados >>>" + perteneceZona);
		//Solo si selecciono la opcion de emergente
		if (esOpcionEmergente) {
			return redirecionaBiessEmergente(perteneceZona, esNovacion);
		}
		
		//Como no fue emergente le seteamos la variable
		this.datos.setProductoBiessEmergente(false);
		
		if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_NORMALES)) {
			
		} else if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_BIENES)) {
			
			return "consulta_contrato";
			
		} else if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_SERVICIOS)) {
			if (tiposProductosPq.equals(TiposProductosPq.PEN)) {
				// INC-2272 Pensiones Alimenticias
				// Iniciar datos de la cuenta, de terceros.
				datos.setCuentaBancariaBeneficiarioCredito(new CuentaBancaria());
				datos.getCuentaBancariaBeneficiarioCredito().setTipoCuenta(
						new TipoCuenta());
				datos.getCuentaBancariaBeneficiarioCredito()
						.setInstitucionFinanciera(new InstitucionFinanciera());
				datos.setBeneficiarioCredito(new BeneficiarioCredito());
				//Setear Variable - Pago Pensiones Alimenticias. 
				datos.setPagoPensionesAlimenticias(true);
			} else if (TiposProductosPq.ECU_TUR.equals(tiposProductosPq)) {
				this.obtenerInfoCtaBancariaEcuadorTurismo();
				return "aceptarProductos";
			} else if(TiposProductosPq.DINAMICO.equals(tiposProductosPq)){				
				return "consulta_contrato";
			
			} else {
				try {
					//INC-2286 Ferrocarriles
					listaProveedor = administracionOrdenCompraServicio.obtenerListaProveedorPorTipoProducto(datos
							.getTiposProductosPq().getCodigoTipoProducto());
					if (listaProveedor == null || listaProveedor.isEmpty()) {
						return "productos";
					} else if (listaProveedor.size() == 1) {
						proveedorSeleccionado = listaProveedor.get(0);
						agregarProveedor();
					} else {
						return "proveedores";
					}
				} catch (ProveedorException e) {
					return "productos";
				}
			}
		} else if (categoriaTipoProductoPq.equals(CategoriaTipoProductoPq.CAT_FOCALIZADOS)) {
			return "informacion_focalizados";
		}
		return "aceptarProductos";

	}

	public void obtenerProductos(TiposProductosPq tiposProductosPq, CategoriaTipoProductoPq categoriaTipoProductoPq) {
		try {
			mensageProductos = "";
			this.errorMsg = null;
			DatosOrdenCompra datosOrden = new DatosOrdenCompra();
			datosOrden.setCodigoProducto(datos.getTiposProductosPq().toString());
			datosOrden.setDescripcionProducto(datos.getTiposProductosPq().getDescripcion());
			String numeroOrden = null;
			BigDecimal montoOrden = BigDecimal.ZERO;
			String observacionOrden = null;
			String rucProveedor = null;
			if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_NORMALES) {

			} else if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_BIENES) {
				// Se deshabilita la opcion para Zafreros
				if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datos.getTipo())) {
					throw new ObtenerProductosExcepcion("PRODUCTO NO DISPONIBLE PARA EL SOLICITANTE");
				}

				String cedula = datos.getSolicitante().getDatosPersonales().getCedula();

				// Consultar el Web Service
				try {
					BiessShopService service = new BiessShopService();
					OrdenWSService port = service.getOrdenWSServicePort();
					Orden result = port.obtenerOrden(cedula);
					// Fin Web Service
					if (result.getRespuesta() != 1) {
						// throw new ObtenerProductosExcepcion("NO TIENE ");
						setMensageProductos("Usted no tiene una orden de compra registrada.");
						mostrarMensageEndudamiento = true;
						return;
					} else {
						// Datos de la orden de compra
						numeroOrden = result.getNumeroOrden();
						montoOrden = result.getValorOrden();
						observacionOrden = result.getObservacion();
						rucProveedor = result.getRucProveedor();
					}
					/*
					 * Omar Villanueva 2013-03-05. Validar ordenes que tienen el valor de los campos value_xtratech y
					 * value_cnt en null.
					 */
					if (null == result.getValueXtratech() || result.getValueXtratech().compareTo(BigDecimal.ZERO) <= 0) {
						/* Rechazar orden de compra */
						port.confirmarSolicitud(0, 0, "", numeroOrden);
						throw new ObtenerProductosExcepcion("Orden de compra " + numeroOrden
								+ " inconsistente. Esta orden de compra fue rechazada. "
								+ "Favor genere una nueva orden de compra desde la tienda virtual.");
					}
					/* Fin cambios Omar Villanueva */
					// Valida que el monto de la orden se mayor a cero
					if (null == montoOrden || montoOrden.compareTo(BigDecimal.ZERO) <= 0) {
						throw new ObtenerProductosExcepcion(
								"El sistema reporta un valor inconsistente en la Orden de Compra, por lo cual no se generar\u00E1 el Cr\u00E9dito Quirografario");
					}
					// Consultar Proveedor
					datosOrden.setProveedor(administracionOrdenCompraServicio.obtenerProveedor(rucProveedor));
					datosOrden.setNumeroOrden(numeroOrden);
					datosOrden.setMontoOrden(montoOrden);
					datosOrden.setObservacionOrden(observacionOrden);

				} catch (ProveedorException e) {
					throw new ObtenerProductosExcepcion("ERROR AL CONSULTAR LOS DATOS DEL PROVEEDOR");
				} catch (ObtenerProductosExcepcion e) {
					setMensageProductos(e.getMessage());
					mostrarMensageEndudamiento = true;
				} catch (Exception e) {
					throw new ObtenerProductosExcepcion("Error al consultar datos desde la tienda virtual.");
				}

			} else if (categoriaTipoProductoPq == CategoriaTipoProductoPq.CAT_SERVICIOS) {
				
			}
			datos.setDatosOrdenCompra(datosOrden);
		} catch (ObtenerProductosExcepcion e) {
			setMensageProductos(e.getMessage());
			mostrarMensageEndudamiento = true;
		} 
	}
	//INC-2286 Ferrocarriles
	/**
	 * Metodo para agregar un proveedor al flujo
	 */
	private void agregarProveedor() {

		ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera institucionFinancieraProveedor = new ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera();
		InstitucionFinanciera institucionFinanciera = institucionFinancieraServicio
				.getInstitucionFinanciera(proveedorSeleccionado.getPrRucInstfinanciera());
		institucionFinancieraProveedor.setInstitucion(institucionFinanciera.getDescripcion());
		institucionFinancieraProveedor.setInstitucionId(proveedorSeleccionado.getPrRucInstfinanciera());
		institucionFinancieraProveedor.setNumeroCuenta(proveedorSeleccionado.getPrNumCuenta());
		institucionFinancieraProveedor.setTipoCuentaId(proveedorSeleccionado.getPrTipoCuenta());
		String nombreTipoCuenta = "AHO".equals(proveedorSeleccionado.getPrTipoCuenta()) ? "CUENTA DE AHORROS"
				: "CUENTA CORRIENTE";
		institucionFinancieraProveedor.setTipoCuenta(nombreTipoCuenta);
		datos.setInstitucionFinancieraProveedor(institucionFinancieraProveedor);
		datos.setProveedor(proveedorSeleccionado);
	}

	public void mensageAutorizacion() {
		mensageAutorizacion = new String(
				"Yo, afiliado {0} con CI # {1}, en mi calidad de prestatario, para todos los fines pertinentes, "
						+ "por este medio solicito e instruyo de manera expresa e irrevocable al Biess para que la totalidad "
						+ "de los recursos fruto del cr\u00E9dito quirografario aprobado a mi favor para el financiamiento en la "
						+ "adquisici\u00F3n de bien(es) mueble(s) y/o servicio(s); sean transferidos o acreditados directamente "
						+ "a la(s) cuenta(s) de los proveedor(es)/vendedor(es) de los bien(es) y/o servicio(s) a adquirir.");
		mensageAutorizacion = mensageAutorizacion.replace("{0}", datos
				.getSolicitante().getDatosPersonales().getApellidosNombres());
		mensageAutorizacion = mensageAutorizacion.replace("{1}", datos
				.getSolicitante().getDatosPersonales().getCedula());
	}
	
	/**
	 * Obtiene informacion de la cuenta bancaria a la que se realizara la transferencia cuando se seleccione un credito
	 * de tipo Ecuador tu lugar en el mundo
	 */
	private void obtenerInfoCtaBancariaEcuadorTurismo() {
		List<Proveedor> proveedoresTurismo = this.proveedorServicio
				.consultarProveedorActivoCodpretip(Long.valueOf(TiposProductosPq.ECU_TUR.getCodigoTipoProducto()));
		if (proveedoresTurismo != null && !proveedoresTurismo.isEmpty()) {
			// Solo debe existir un proveedor activo de tipo Ecuador Tu Lugar en el Mundo, sin embargo, si existen mas
			// proveedores se selecciona el primero
			Proveedor proveedorTurismo = proveedoresTurismo.get(0);
			ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera institucionFinancieraProveedor = new ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera();
			InstitucionFinanciera institucionFinanciera = this.institucionFinancieraServicio
					.getInstitucionFinanciera(proveedorTurismo.getPrRucInstfinanciera());
			institucionFinancieraProveedor.setInstitucion(institucionFinanciera.getDescripcion());
			institucionFinancieraProveedor.setInstitucionId(proveedorTurismo.getPrRucInstfinanciera());
			institucionFinancieraProveedor.setNumeroCuenta(proveedorTurismo.getPrNumCuenta());
			institucionFinancieraProveedor.setTipoCuentaId(proveedorTurismo.getPrTipoCuenta());
			String nombreTipoCuenta = "AHO".equals(proveedorTurismo.getPrTipoCuenta()) ? "CUENTA DE AHORROS" : "CUENTA CORRIENTE";
			institucionFinancieraProveedor.setTipoCuenta(nombreTipoCuenta);
			this.datos.setInstitucionFinancieraProveedor(institucionFinancieraProveedor);
			this.datos.setProveedor(proveedorTurismo);
		} else {
			LOG.info("No existe cuenta bancaria registrada para proveedor de Ecuador Tu Lugar en el Mundo");
		}
	}

	/**
	 * Funcion que verifica si existen creditos ERC
	 * 
	 * @return
	 */
	public String verificarERCConcesion() {
		// INC-2272 Pensiones Alimenticias
		String navegacion = "aceptarInfoNovacion";
		msgErrNovacion=null;
		try {
		if (!this.validarBloqueoNovacion()) {
			navegacion = "bloqueoNovacion";
		}else if(!this.prestamoServicio.validarHabilitaNovacionPlanillaje(this.datos.getRolPrestamista(),rangoNovJub,rangoNovAfi,rangoNovAfiJub)) {
			//navegacion = "bloqueoNovacion";
			return "";
		}
		}catch(PeriodoComprobanteException e) {
			msgErrNovacion=e.getMessage();
			LOG.error(e.getMessage());
			return "";
		}
		
		return navegacion;
	}
	


	public String generarCapacidadEndeudamiento() {

		return "aceptarTienda";
	}
	//INC-2286 Ferrocarriles
	/**
	 * Validacion de proveedor 
	 */
	public void validarProveedor(){
		proveedorSeleccionado = proveedorServicio.load(Long.valueOf(idProveedorSeleccionado));
		agregarProveedor();
	}

	/**
	 * 
	 * @return
	 */
	public String seleccionarOtrosProductos(){
		this.datos.setProdViajaEcu(false);
		this.datos.setProductoProductivo(false);
		this.tipoSolicitud = 1;
		this.errorMsg = null;
		datos.setProductoBiessEmergente(false);
		this.datos.setDiscapacitado(false);
		mostrarMsgInfo = false;
		
		this.mostrarMensageEndudamiento = false;
		
		return "productosVarios";
	}
	
	/**
	 * Redirecciona a resultado pre calificacion para biess emergente
	 * 
	 * @param perteneceZona
	 * @param esNovacion
	 * @return
	 */
	private String redirecionaBiessEmergente(boolean perteneceZona, boolean esNovacion) {
		String redirecciona = "";
		if (perteneceZona) {
			mostrarMsgInfo = false;
			redirecciona = "aceptarProductos";
		} else {
			mostrarMsgInfo = true;
			redirecciona = "tipoProductoEmerg";
			if (esNovacion) {
				redirecciona = "tipoProductoNovacionEmerg";
			}
		}

		return redirecciona;
	}
	
	/**
	 * Calificacmos biess emergente
	 * @param perteneceZona
	 */
	private void calificaBiessEmergente(boolean perteneceZona){
		if (perteneceZona) {
			// Indica que el afiliado es damnificado y esta realizando B. emergente
			this.datos.setProductoBiessEmergente(true);
		} else {
			this.datos.setProductoBiessEmergente(false);
		}
	}
	
	/**
	 * Validacion bloquear novacion
	 */
	private boolean validarBloqueoNovacion() {
		return this.prestamoServicio.permiteRealizarNovaciones();
	}
	
	public boolean isHabilitarNormal() {
		return habilitarNormal;
	}

	public void setHabilitarNormal(boolean habilitarNormal) {
		this.habilitarNormal = habilitarNormal;
	}

	public boolean isHabilitarCompu() {
		return habilitarCompu;
	}

	public void setHabilitarCompu(boolean habilitarCompu) {
		this.habilitarCompu = habilitarCompu;
	}

	public boolean isHabilitarTuristico() {
		return habilitarTuristico;
	}

	public void setHabilitarTuristico(boolean habilitarTuristico) {
		this.habilitarTuristico = habilitarTuristico;
	}

	public String getMensageProductos() {
		return mensageProductos;
	}

	public void setMensageProductos(String mensageProductos) {
		this.errorMsg = mensageProductos;
		this.mensageProductos = mensageProductos;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public boolean isCargado() {
		return cargado;
	}

	public void setCargado(boolean cargado) {
		this.cargado = cargado;
	}

	public String getMensageAutorizacion() {
		return mensageAutorizacion;
	}

	public void setMensageAutorizacion(String mensageAutorizacion) {
		this.mensageAutorizacion = mensageAutorizacion;
	}

	public boolean isMostrarMensageEndudamiento() {
		return mostrarMensageEndudamiento;
	}

	public void setMostrarMensageEndudamiento(boolean mostrarMensageEndudamiento) {
		this.mostrarMensageEndudamiento = mostrarMensageEndudamiento;
	}

	public String getCodigoTipoProducto() {
		return codigoTipoProducto;
	}

	public void setCodigoTipoProducto(String codigoTipoProducto) {
		this.codigoTipoProducto = codigoTipoProducto;
	}
	//INC-2286 Ferrocarriles
	/**
	 * Obtiene una lista de proveedores
	 * @return List<Proveedor> 
	 */
	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public String getIdProveedorSeleccionado() {
		return idProveedorSeleccionado;
	}

	public void setIdProveedorSeleccionado(String idProveedorSeleccionado) {
		this.idProveedorSeleccionado = idProveedorSeleccionado;
	}

	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

	public List<SelectItem> getListaProveedorSelectItems() {
		if (null == listaProveedorSelectItems) {
			listaProveedorSelectItems = new ArrayList<SelectItem>();
			for (Proveedor proveedor : listaProveedor) {
				SelectItem item = new SelectItem(proveedor.getPrIdProveedor(), proveedor.getPrDireccion());
				listaProveedorSelectItems.add(item);
			}
		}
		return listaProveedorSelectItems;
	}

	public boolean isMostrarMsgInfo() {
		return mostrarMsgInfo;
	}

	public void setMostrarMsgInfo(boolean mostrarMsgInfo) {
		this.mostrarMsgInfo = mostrarMsgInfo;
	}	
	
	/**
	 * @return
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	/**
	 * @return
	 */
	public int getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * @param tipoSolicitud
	 */
	public void setTipoSolicitud(int tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}
	
	public String getCodigoEspecialProd() {
		return codigoEspecialProd;
	}

	public void setCodigoEspecialProd(String codigoEspecialProd) {
		this.codigoEspecialProd = codigoEspecialProd;
	}
	
	public boolean isBloqueaNovacion() {
		return bloqueaNovacion;
	}

	public void setBloqueaNovacion(boolean bloqueaNovacion) {
		this.bloqueaNovacion = bloqueaNovacion;
	}
	public String getMsgErrNovacion() {
		return msgErrNovacion;
	}
	public void setMsgErrNovacion(String msgErrNovacion) {
		this.msgErrNovacion = msgErrNovacion;
	}
	public String getRangoHabiNovJub() {
		return rangoHabiNovJub;
	}
	public void setRangoHabiNovJub(String rangoHabiNovJub) {
		this.rangoHabiNovJub = rangoHabiNovJub;
	}
	public String getRangoHabiNovAfi() {
		return rangoHabiNovAfi;
	}
	public void setRangoHabiNovAfi(String rangoHabiNovAfi) {
		this.rangoHabiNovAfi = rangoHabiNovAfi;
	}	
	public String getRangoHabiNovAfiJub() {
		return rangoHabiNovAfiJub;
	}
	public void setRangoHabiNovAfiJub(String rangoHabiNovAfiJub) {
		this.rangoHabiNovAfiJub = rangoHabiNovAfiJub;
	}
	public String getRangoNovJub() {
		return rangoNovJub;
	}
	public void setRangoNovJub(String rangoNovJub) {
		this.rangoNovJub = rangoNovJub;
	}
	public String getRangoNovAfi() {
		return rangoNovAfi;
	}
	public void setRangoNovAfi(String rangoNovAfi) {
		this.rangoNovAfi = rangoNovAfi;
	}
	public String getPeriodoGeneracionComprobantes() {
		return periodoGeneracionComprobantes;
	}
	public void setPeriodoGeneracionComprobantes(String periodoGeneracionComprobantes) {
		this.periodoGeneracionComprobantes = periodoGeneracionComprobantes;
	}
		
}