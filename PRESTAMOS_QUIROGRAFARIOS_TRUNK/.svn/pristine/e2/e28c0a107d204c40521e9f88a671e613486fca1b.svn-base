package ec.gov.iess.pq.concesion.focalizados.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.matriz.exception.BiessDivisionPoliticaException;
import ec.fin.biess.matriz.modelo.persistence.BiessDivisionPolitica;
import ec.fin.biess.matriz.service.BiessDivisionPoliticaServicioLocal;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.focalizados.dto.ProveedorDto;
import ec.gov.iess.creditos.focalizados.dto.PuntoVentaDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.otp.dto.NotificacionOTP;
import ec.gov.iess.creditos.pq.excepcion.OTPException;
import ec.gov.iess.creditos.pq.excepcion.PrestamosFocalizadosException;
import ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal;
import ec.gov.iess.creditos.pq.servicio.OTPServicioLocal;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.pq.concesion.focalizados.enumeration.TipoProductoFocalizadoEnum;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.Utilities;

/**
 * Backing para PQ Focalizado
 * 
 * @author hugo.mora
 * @date 2016/10/14
 *
 */
public class PqFocalizadosBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient static final Logger LOG = Logger.getLogger(PqFocalizadosBacking.class);

	@EJB(name = "BiessDivisionPoliticaServicioImpl/local")
	private BiessDivisionPoliticaServicioLocal divisionPoliticaService;

	@EJB(name = "CatalogoFocalizadosServicioImpl/local")
	private CatalogoFocalizadosServicioLocal catalogoFocalizadosServicio;

	@EJB(name = "ProveedorServicioImpl/local")
	private transient ProveedorServicio proveedorServicio;

	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB(name = "OTPServicioImpl/local")
	private transient OTPServicioLocal otpServicio;

	@EJB(name = "PrestamoServicioImpl/local")
	private transient PrestamoServicio prestamoServicio;

	@EJB(name = "ParametroBiessDaoImpl/local")
	private transient ParametroBiessDao parametroBiessDao;

	private DatosBean datos;

	private boolean agregarOllas;

	private List<SelectItem> listaProvinciaItems;
	private List<SelectItem> listaCantonItems = new ArrayList<SelectItem>();
	private List<SelectItem> listaEstablecimientoItems = new ArrayList<SelectItem>();
	private List<SelectItem> listaPuntoVentaItems = new ArrayList<SelectItem>();
	private List<SelectItem> listaCocinasItems = new ArrayList<SelectItem>();

	private String provinciaSeleccionada;
	private String cantonSeleccionado;
	private Integer establecimientoSeleccionado;
	private Integer puntoVentaSeleccionado;
	private Integer cocinaSeleccionada;

	private String observacionEstablecimiento;
	private String observacionCocinas;

	private String codigoOtpIngresado;
	private String idTransaccion;

	private ProductoDto cocinaInformacion;
	private Map<Integer, ProveedorDto> mapaProveedor;

	// Indica la informacion del proveedor al cual se realizara la transferencia
	private Proveedor proveedorTransferencia;

	private boolean tienePrestamosVigentes;
	private String mensajePrestamosVigentes;
	private String linkCatalogoMeer;
	private String codigoCocina;

	private String codigoProductoMeer;
	private String codigoProveedorMeer;
	private boolean verCatalogo;
	private boolean buscarPorCodigo = false;

	/**
	 * Inicializa variables
	 */
	@PostConstruct
	private void init() {
		this.observacionEstablecimiento = null;
		cargarProveedoresConvenioActivos();
		try {
			// Carga el listado de provincias
			this.listaProvinciaItems = this.buildDivisionPoliticaSelect(this.divisionPoliticaService.obtenerProvincias());
			this.provinciaSeleccionada = null;
		} catch (BiessDivisionPoliticaException e) {
			LOG.error("Error al obtener la division politica", e);
			this.observacionEstablecimiento = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
		}

		// Verifica si tiene el numero de prestamos vigentes focalizado maximo
		try {
			this.mensajePrestamosVigentes = null;
			int prestamosVigentesMaximo = this.parametroBiessDao
					.consultarPorIdNombreParametro(ParametrosBiessEnum.MAXIMO_CREDITOS_VIGENTES.getIdParametro(),
							ParametrosBiessEnum.MAXIMO_CREDITOS_VIGENTES.getNombreParametro())
					.getValorNumerico().intValue();
			int prestamosVigentes = this.prestamoServicio.contarPorEstadoTipoPrestamo(this.datos.getSolicitante().getDatosPersonales().getCedula(),
					"VIG", TiposProductosPq.FOC.getCodigoTipoProducto()).intValue();

			if (prestamosVigentes >= prestamosVigentesMaximo) {
				this.tienePrestamosVigentes = true;
				this.mensajePrestamosVigentes = String.format(getResource("messages", "mensaje.pq.focalizado.maximo.vigentes"),
						prestamosVigentesMaximo);
			}

			this.linkCatalogoMeer = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.URL_CATALOGO_MEER.getIdParametro(),
					ParametrosBiessEnum.URL_CATALOGO_MEER.getNombreParametro()).getValorCaracter();
			
			this.verCatalogo = "S".equals(this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.MOSTRAR_CATALOGO.getIdParametro(),
					ParametrosBiessEnum.MOSTRAR_CATALOGO.getNombreParametro()).getValorCaracter()) ? true : false;
		} catch (ParametroBiessException e) {
			LOG.error("Error al obtener parametro de base de datos", e);
		}
	}

	/**
	 * Construye la division politica seleccionada
	 * 
	 * @param lista
	 * @return
	 */
	private List<SelectItem> buildDivisionPoliticaSelect(List<BiessDivisionPolitica> lista) {
		List<SelectItem> listaCatalogo = new ArrayList<SelectItem>();
		SelectItem itemSeleccione = new SelectItem(null, "Seleccione");
		listaCatalogo.add(itemSeleccione);
		for (BiessDivisionPolitica div : lista) {
			SelectItem item = new SelectItem();
			item.setValue(div.getCodigo());
			item.setLabel(div.getNombreDivisionPolitica());
			listaCatalogo.add(item);
		}
		return listaCatalogo;
	}

	/**
	 * Carga el combo de cantones en base a la provincia seleccionada
	 */
	public void cargarCanton() {
		this.observacionEstablecimiento = null;

		try {
			this.listaCantonItems = this.buildDivisionPoliticaSelect(this.divisionPoliticaService.obtenerCantones(this.provinciaSeleccionada));
		} catch (BiessDivisionPoliticaException e) {
			LOG.info("Excepcion al cargar el canton con la provincia seleccionada: " + this.provinciaSeleccionada + " ", e);
		}

		this.listaPuntoVentaItems.clear();
		this.cantonSeleccionado = null;
	}

	/**
	 * Genera el codigo OTP para verificacion
	 * 
	 * @return
	 * @throws OTPException
	 */
	public void generarCodigoOTP(String idTransaccion, String referencia, NotificacionOTP notificacionOTP, String idNegocio) throws OTPException {
		this.otpServicio.generaOTP(idTransaccion, referencia, notificacionOTP, idNegocio);
	}

	/**
	 * Valida que el codigo OTP sea valido
	 * 
	 * @param idTransaccion
	 * @return
	 * @throws OTPException
	 */
	public boolean validaCodigoOtp(String idTransaccion) throws OTPException {
		boolean codigoValido = this.otpServicio.validaOTP(idTransaccion, this.codigoOtpIngresado);

		return codigoValido;
	}

	private ProductoDto consultarProductoPorEstablecimientoTipoCodigoProducto(Integer codigoProveedorMeer, Integer tipoProducto,
			Integer codigoProductoMeer) throws PrestamosFocalizadosException {
		ProductoDto producto = null;
		producto = this.catalogoFocalizadosServicio.consultarProductoPorEstablecimientoTipoCodigoProducto(codigoProveedorMeer, tipoProducto,
				codigoProductoMeer);

		return producto;
	}

	/**
	 * Busca la cocina por codigo
	 * 
	 * @return
	 */
	public String buscarCocinaPorCodigo() {
		int contador = 0;
		for (int x = 0; x < this.codigoCocina.length(); x++) {
			if (this.codigoCocina.charAt(x) == '-') {
				contador++;
			}
		}

		if (!this.codigoCocina.contains("-")) {
			this.observacionEstablecimiento = super.getResource("errores", "pq.focalizado.codigo.cocina.mal.ingreso");
			return null;
		} else if (contador > 1) {
			this.observacionEstablecimiento = super.getResource("errores", "pq.focalizado.codigo.cocina.mal.ingreso");
			return null;
		} else {
			try {
				String[] codigoIngresado = this.codigoCocina.split("-");
				this.codigoProveedorMeer = codigoIngresado[0];
				this.codigoProductoMeer = codigoIngresado[1];

				this.cocinaInformacion = this.consultarProductoPorEstablecimientoTipoCodigoProducto(Integer.valueOf(this.codigoProveedorMeer),
						TipoProductoFocalizadoEnum.COCINAS.getCodigo(), Integer.valueOf(this.codigoProductoMeer));
				ProveedorDto proveedor = this.catalogoFocalizadosServicio.consultarProveedorPorCodigoMeer(Integer.valueOf(this.codigoProveedorMeer));
				this.setearInformacionFinancieraProveedor(proveedor);
				
				this.agregarOllas = false;
				if ("SI".equals(this.cocinaInformacion.getGobierno())) {
					this.agregarOllas = true;
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				this.observacionEstablecimiento = super.getResource("errores", "pq.focalizado.codigo.cocina.mal.ingreso");
				return null;
			} catch (PrestamosFocalizadosException e) {
				LOG.info("1. Se presento una excepcion al buscar cocina por establecimiento " + this.codigoProveedorMeer + " y codigo de producto "
						+ this.codigoProductoMeer + ". ", e);
				this.observacionEstablecimiento = super.getResource("errores", "pq.focalizado.codigo.cocina.mal.ingreso");
				return "";
			} catch (Exception e) {
				LOG.error("2. -Se presento una excepcion consultar cocinas por establecimiento y punto de venta " + this.codigoProveedorMeer + ". ",
						e);
				this.observacionEstablecimiento = getResource("messages", "mensaje.pq.focalizado.error.cocinas.establecimiento");
				return "";
			}
		}

		return "catalogoCocinas";
	}
	
	/**
	 * Busca un producto de pq focalizado por tipo de producto, establecimiento, y punto de venta
	 * 
	 * @param codigoProducto
	 * @param establecimiento
	 * @param puntoVenta
	 * @return
	 */
	public List<ProductoDto> buscarProductoPorEstablecimientoYPV(Integer codigoProducto, Integer establecimiento, Integer puntoVenta)
			throws PrestamosFocalizadosException {
		List<ProductoDto> listaProductos = null;
		listaProductos = this.catalogoFocalizadosServicio.consultarProductoDtoPorEstablecimientoYPV(codigoProducto, establecimiento, puntoVenta);
		return listaProductos;
	}

	/**
	 * Busca cocinas dado el establecimiento y el punto de venta
	 * 
	 * @return
	 */
	public String buscarCocinasPorEstablecimientoYPV() {
		this.agregarOllas = false;
		this.cocinaSeleccionada = null;
		this.cocinaInformacion = null;
		this.listaCocinasItems.clear();

		try {
			SelectItem item = null;
			this.listaCocinasItems.clear();
			item = new SelectItem(null, "Seleccione");
			this.listaCocinasItems.add(item);

			List<ProductoDto> listaProductos = buscarProductoPorEstablecimientoYPV(TipoProductoFocalizadoEnum.COCINAS.getCodigo(),
					this.establecimientoSeleccionado, this.puntoVentaSeleccionado);

			Collections.sort(listaProductos, new Comparator<ProductoDto>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
				 */
				@Override
				public int compare(ProductoDto o1, ProductoDto o2) {
					return o1.getNombreMostrar().compareTo(o2.getNombreMostrar());
				}

			});

			for (ProductoDto producto : listaProductos) {
				String nombreCocina = producto.getNombreMostrar();
				item = new SelectItem(producto.getCodigoMEER(), nombreCocina.length() >= 50 ? nombreCocina.substring(0, 50) : nombreCocina);
				this.listaCocinasItems.add(item);
			}

			// Si tiene un registro a lista es porque no tiene ollas, ya que el primer elemento es la opcion Seleccuione
			if (this.listaCocinasItems.size() == 1) {
				this.observacionEstablecimiento = "No existen registros para el establecimiento y punto de venta seleccionado.";
			}

			ProveedorDto proveedorDto = this.mapaProveedor.get(this.establecimientoSeleccionado);
			this.setearInformacionFinancieraProveedor(proveedorDto);
		} catch (PrestamosFocalizadosException e) {
			LOG.info("1. Se presento una excepcion al buscar cocina por establecimiento " + this.establecimientoSeleccionado + " y punto de venta "
					+ this.puntoVentaSeleccionado + ". ", e);
			this.observacionEstablecimiento = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
			return "";
		} catch (Exception e) {
			LOG.error(
					"2. -Se presento una excepcion consultar cocinas por establecimiento y punto de venta " + this.establecimientoSeleccionado + ". ",
					e);
			this.observacionEstablecimiento = getResource("messages", "mensaje.pq.focalizado.error.cocinas.establecimiento");
			return "";
		}

		return "catalogoCocinas";
	}

	/**
	 * A partir del proveedor (BIE_PROVEEDOR_TBL) obtiene la informacion de la cuenta bancaria del proveedor de la tabla
	 * CRE_PROVEEDORES_TBL
	 * 
	 * @param codigoMeer
	 * @throws PrestamosFocalizadosException
	 */
	private void setearInformacionFinancieraProveedor(ProveedorDto proveedorDto) throws PrestamosFocalizadosException {
		try {
			// Se obtiene el proveedor de la tabla CRE_PROVEEDORES_TBL que contiene informacion para realizar la
			// trnasferencia
			this.proveedorTransferencia = this.proveedorServicio.consultarProveedorRucCodpretip(proveedorDto.getRuc(),
					TiposProductosPq.FOC.getCodigoTipoProducto());

			// Se setea informacion de la institucion financiera donde se realizara la transferencia
			ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera institucionFinancieraProveedor = new ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera();
			InstitucionFinanciera institucionFinanciera = this.institucionFinancieraServicio
					.getInstitucionFinanciera(this.proveedorTransferencia.getPrRucInstfinanciera());
			institucionFinancieraProveedor.setInstitucion(institucionFinanciera.getDescripcion());
			institucionFinancieraProveedor.setInstitucionId(this.proveedorTransferencia.getPrRucInstfinanciera());
			institucionFinancieraProveedor.setNumeroCuenta(this.proveedorTransferencia.getPrNumCuenta());
			institucionFinancieraProveedor.setTipoCuentaId(this.proveedorTransferencia.getPrTipoCuenta());
			String nombreTipoCuenta = "AHO".equals(this.proveedorTransferencia.getPrTipoCuenta()) ? "CUENTA DE AHORROS" : "CUENTA CORRIENTE";
			institucionFinancieraProveedor.setTipoCuenta(nombreTipoCuenta);
			this.datos.setInstitucionFinancieraProveedor(institucionFinancieraProveedor);
			if (this.buscarPorCodigo) {
				this.datos.setIdProveedorMeer(Long.valueOf(this.codigoProveedorMeer));
			} else {
				this.datos.setIdProveedorMeer(this.establecimientoSeleccionado.longValue());
			}
			this.datos.setProveedor(this.proveedorTransferencia);
		} catch (Exception e) {
			LOG.error("Se presento un error al setear informacion financiera del proveedor ", e);
			throw new PrestamosFocalizadosException(getResource("messages", "mensaje.pq.focalizado.error.financiero.establecimiento"));
		}
	}

	/**
	 * Carga el listado de provincias para llenar el combo
	 * 
	 * @return
	 */
	public String resetearProvinciaSeleccionada() {
		this.observacionEstablecimiento = null;
		this.provinciaSeleccionada = null;

		this.listaCantonItems.clear();
		this.listaPuntoVentaItems.clear();
		return "";
	}

	/**
	 * Carga un listado de proveedores con convenio en estado activo
	 */
	private void cargarProveedoresConvenioActivos() {
		try {
			List<ProveedorDto> proveedores = this.catalogoFocalizadosServicio.consultarProveedoresConvenioPorEstado("ACT");
			SelectItem item = null;
			this.listaEstablecimientoItems.clear();
			item = new SelectItem(null, "Seleccione");
			this.listaEstablecimientoItems.add(item);
			this.mapaProveedor = new HashMap<Integer, ProveedorDto>();

			Collections.sort(proveedores, new Comparator<ProveedorDto>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
				 */
				@Override
				public int compare(ProveedorDto o1, ProveedorDto o2) {
					return o1.getNombreComercial().compareTo(o2.getNombreComercial());
				}

			});
			for (ProveedorDto proveedor : proveedores) {
				item = new SelectItem(proveedor.getCodigoMEER(), proveedor.getNombreComercial());
				this.mapaProveedor.put(proveedor.getCodigoMEER(), proveedor);
				this.listaEstablecimientoItems.add(item);
			}
		} catch (PrestamosFocalizadosException e) {
			LOG.info("Se presento una excepcion al buscar proveedor en estado activo", e);
			this.observacionEstablecimiento = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
		} catch (Exception e) {
			LOG.error("Se presento una excepcion al buscar proveedores con convenio activos", e);
			this.observacionEstablecimiento = getResource("messages", "mensaje.pq.focalizado.error.proveedores.activos");
		}
	}

	/**
	 * Busca un punto de venta por ubicacion INEC y por codigo de proveedor
	 * 
	 * @return
	 */
	public String buscarPuntoVentaPorUbicacionInecYProveedor() {
		this.puntoVentaSeleccionado = null;
		this.observacionEstablecimiento = null;
		try {
			if (this.establecimientoSeleccionado == null) {
				this.observacionEstablecimiento = getResource("messages", "mensaje.pq.focalizados.establecimiento.escoger");
			} else {
				List<PuntoVentaDto> puntoVentas = this.catalogoFocalizadosServicio
						.consultarPuntoVentaPorCodigoInecYProveedorActivos(this.cantonSeleccionado, this.establecimientoSeleccionado);
				SelectItem item = null;
				this.listaPuntoVentaItems.clear();
				item = new SelectItem(null, "Seleccione");
				this.listaPuntoVentaItems.add(item);

				Collections.sort(puntoVentas, new Comparator<PuntoVentaDto>() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
					 */
					@Override
					public int compare(PuntoVentaDto o1, PuntoVentaDto o2) {
						return o1.getNombreComercial().compareTo(o2.getNombreComercial());
					}

				});
				for (PuntoVentaDto puntoVenta : puntoVentas) {
					item = new SelectItem(puntoVenta.getCodigoMEER(), puntoVenta.getNombreComercial());
					this.listaPuntoVentaItems.add(item);
				}
			}
		} catch (PrestamosFocalizadosException e) {
			LOG.info("1. Se presento una excepcion al buscar punto de venta por codigo de ubicacion INEC y por codigo de proveedor", e);
			this.observacionEstablecimiento = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
		} catch (Exception e) {
			LOG.error("2. Se presento una excepcion al buscar punto de venta por codigo de ubicacion INEC y por codigo de proveedor", e);
			this.observacionEstablecimiento = getResource("messages", "mensaje.pq.focalizado.error.punto.venta");
		}
		return "";
	}

	/**
	 * Consulta informacion de cocina de induccion dado el codigo meer
	 * 
	 * @return
	 */
	public String consultarCocinaPorCodigoMeer() {
		this.observacionCocinas = null;
		try {
			this.cocinaInformacion = this.catalogoFocalizadosServicio.consultarProductoPorCodigoMeer(this.cocinaSeleccionada);
			this.agregarOllas = false;
			if ("SI".equals(this.cocinaInformacion.getGobierno())) {
				this.agregarOllas = true;
			}
		} catch (PrestamosFocalizadosException e) {
			LOG.info("1. Se presento una excepcion al consultar un producto por codigo meer : " + this.cocinaSeleccionada + " ", e);
			this.observacionCocinas = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
		} catch (Exception e) {
			LOG.error("2. Se presento una excepcion al consultar un producto por codigo meer : " + this.cocinaSeleccionada + " ", e);
			this.observacionCocinas = getResource("messages", "mensaje.pq.focalizado.cocina.codigo");
		}
		return "";
	}

	/**
	 * Bloquea la cuenta del asegurado por intentos fallidos
	 * 
	 * @return
	 */
	public String bloquearCuentaIntentosFallidos() {
		// Se cierra la sesion para que no pueda seguir navegando en el aplicativo
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) ectx.getSession(false);
		session.invalidate();
		return "";
	}

	/**
	 * Avanza a la pantalla para seleccionar cocina por codigo y vacia variables
	 * 
	 * @return
	 */
	public String seleccionaCocinaPorCodigo() {
		limpiarCampos();
		this.buscarPorCodigo = true;

		return "seleccionar_establecimiento";
	}
	
	/**
	 * Avanza a la pantalla para seleccionar establecimiento y vacia variables
	 * 
	 * @return
	 */
	public String seleccionaCocinaPorEstablecimiento() {
		limpiarCampos();
		this.buscarPorCodigo = false;

		return "seleccionar_establecimiento";
	}
	
	/**
	 * Limpia ciertos campos de busqueda
	 */
	private void limpiarCampos() {
		this.codigoCocina = null;
		this.observacionEstablecimiento = null;
		this.vaciarCombosEstablecimiento();
	}

	/**
	 * Encera los combos de establecimientos y ubicacion
	 */
	private void vaciarCombosEstablecimiento() {
		this.observacionCocinas = null;
		this.establecimientoSeleccionado = null;
		this.provinciaSeleccionada = null;
		this.listaCantonItems.clear();
		this.listaPuntoVentaItems.clear();
	}

	/**
	 * Cuando se da clic en regresar desde el catalogo de cocinas
	 * 
	 * @return
	 */
	public String regresarCocinas() {
		vaciarCombosEstablecimiento();

		return "regresar";
	}

	/**
	 * Redirecciona a la pagina de catalogo de productos del MEER
	 * 
	 * @throws IOException
	 */
	public void redirectCatalogoMeer() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(this.linkCatalogoMeer);
	}

	// Getters and setters

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public boolean isAgregarOllas() {
		return agregarOllas;
	}

	public void setAgregarOllas(boolean agregarOllas) {
		this.agregarOllas = agregarOllas;
	}

	public List<SelectItem> getListaProvinciaItems() {
		return listaProvinciaItems;
	}

	public void setListaProvinciaItems(List<SelectItem> listaProvinciaItems) {
		this.listaProvinciaItems = listaProvinciaItems;
	}

	public List<SelectItem> getListaCantonItems() {
		return listaCantonItems;
	}

	public void setListaCantonItems(List<SelectItem> listaCantonItems) {
		this.listaCantonItems = listaCantonItems;
	}

	public String getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}

	public void setProvinciaSeleccionada(String provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}

	public String getCantonSeleccionado() {
		return cantonSeleccionado;
	}

	public void setCantonSeleccionado(String cantonSeleccionado) {
		this.cantonSeleccionado = cantonSeleccionado;
	}

	public String getObservacionEstablecimiento() {
		return observacionEstablecimiento;
	}

	public void setObservacionEstablecimiento(String observacionEstablecimiento) {
		this.observacionEstablecimiento = observacionEstablecimiento;
	}

	public Integer getEstablecimientoSeleccionado() {
		return establecimientoSeleccionado;
	}

	public void setEstablecimientoSeleccionado(Integer establecimientoSeleccionado) {
		this.establecimientoSeleccionado = establecimientoSeleccionado;
	}

	public List<SelectItem> getListaEstablecimientoItems() {
		return listaEstablecimientoItems;
	}

	public void setListaEstablecimientoItems(List<SelectItem> listaEstablecimientoItems) {
		this.listaEstablecimientoItems = listaEstablecimientoItems;
	}

	public Integer getPuntoVentaSeleccionado() {
		return puntoVentaSeleccionado;
	}

	public void setPuntoVentaSeleccionado(Integer puntoVentaSeleccionado) {
		this.puntoVentaSeleccionado = puntoVentaSeleccionado;
	}

	public List<SelectItem> getListaPuntoVentaItems() {
		return listaPuntoVentaItems;
	}

	public void setListaPuntoVentaItems(List<SelectItem> listaPuntoVentaItems) {
		this.listaPuntoVentaItems = listaPuntoVentaItems;
	}

	public List<SelectItem> getListaCocinasItems() {
		return listaCocinasItems;
	}

	public void setListaCocinasItems(List<SelectItem> listaCocinasItems) {
		this.listaCocinasItems = listaCocinasItems;
	}

	public Integer getCocinaSeleccionada() {
		return cocinaSeleccionada;
	}

	public void setCocinaSeleccionada(Integer cocinaSeleccionada) {
		this.cocinaSeleccionada = cocinaSeleccionada;
	}

	public String getObservacionCocinas() {
		return observacionCocinas;
	}

	public void setObservacionCocinas(String observacionCocinas) {
		this.observacionCocinas = observacionCocinas;
	}

	public ProductoDto getCocinaInformacion() {
		return cocinaInformacion;
	}

	public void setCocinaInformacion(ProductoDto cocinaInformacion) {
		this.cocinaInformacion = cocinaInformacion;
	}

	public Proveedor getProveedorTransferencia() {
		return proveedorTransferencia;
	}

	public void setProveedorTransferencia(Proveedor proveedorTransferencia) {
		this.proveedorTransferencia = proveedorTransferencia;
	}

	public String getCodigoOtpIngresado() {
		return codigoOtpIngresado;
	}

	public void setCodigoOtpIngresado(String codigoOtpIngresado) {
		this.codigoOtpIngresado = codigoOtpIngresado;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public boolean isTienePrestamosVigentes() {
		return tienePrestamosVigentes;
	}

	public void setTienePrestamosVigentes(boolean tienePrestamosVigentes) {
		this.tienePrestamosVigentes = tienePrestamosVigentes;
	}

	public String getMensajePrestamosVigentes() {
		return mensajePrestamosVigentes;
	}

	public void setMensajePrestamosVigentes(String mensajePrestamosVigentes) {
		this.mensajePrestamosVigentes = mensajePrestamosVigentes;
	}

	public String getLinkCatalogoMeer() {
		return linkCatalogoMeer;
	}

	public void setLinkCatalogoMeer(String linkCatalogoMeer) {
		this.linkCatalogoMeer = linkCatalogoMeer;
	}

	public String getCodigoCocina() {
		return codigoCocina;
	}

	public void setCodigoCocina(String codigoCocina) {
		this.codigoCocina = codigoCocina;
	}

	public String getCodigoProductoMeer() {
		return codigoProductoMeer;
	}

	public void setCodigoProductoMeer(String codigoProductoMeer) {
		this.codigoProductoMeer = codigoProductoMeer;
	}

	public String getCodigoProveedorMeer() {
		return codigoProveedorMeer;
	}

	public void setCodigoProveedorMeer(String codigoProveedorMeer) {
		this.codigoProveedorMeer = codigoProveedorMeer;
	}

	public boolean isVerCatalogo() {
		return verCatalogo;
	}

	public void setVerCatalogo(boolean verCatalogo) {
		this.verCatalogo = verCatalogo;
	}

	public boolean isBuscarPorCodigo() {
		return buscarPorCodigo;
	}

	public void setBuscarPorCodigo(boolean buscarPorCodigo) {
		this.buscarPorCodigo = buscarPorCodigo;
	}

}