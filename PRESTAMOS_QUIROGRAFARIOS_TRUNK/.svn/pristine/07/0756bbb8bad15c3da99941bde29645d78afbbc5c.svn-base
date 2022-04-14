package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dinamico.dto.DataCmbProveedor;
import ec.gov.iess.creditos.dinamico.dto.DataProveedorRequestDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.excepcion.ConecSrvPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ConsultaDataPqDinamicoException;
import ec.gov.iess.creditos.pq.excepcion.ExisteCodigoContratoException;
import ec.gov.iess.creditos.pq.excepcion.ParamCategoriaException;
import ec.gov.iess.creditos.pq.servicio.PrestamoDinamicoLocalService;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.pq.concesion.pqdinamico.util.DinamicoPqUtil;
import ec.gov.iess.pq.concesion.web.bean.DataConsultaContrato;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.helper.AuditoriaPqWebHelper;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * Managed Bean que controla la consulta de contrato de un proveedor
 * 
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class ConsultaContratoProvBean extends BaseBean implements Serializable {

	/**
	 * Minimo empresas cargar combo
	 */
	private static final int NUM_MINIMO_PROV = 1;

	/**
	 * Servicio para obtener datos de un producto y proveedor
	 */
	@EJB(name = "PrestamoDinamicoImpl/local")
	private transient PrestamoDinamicoLocalService prestamoDinamico;

	/**
	 * Servicio para obtener datos del proveedor
	 */
	@EJB(name = "ProveedorServicioImpl/local")
	private transient ProveedorServicio proveedorServicio;

	/**
	 * Servicio que obtiene los datos de una intitucion financiera
	 */
	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio instFinServ;

	/**
	 * Data
	 */
	private DataConsultaContrato inputFormContrt;

	/**
	 * Datos que se cargan en sesion y esta en todo el proceso de concesion
	 */
	private DatosBean datos;

	/**
	 * Bandera que permite mostrar el formulario solo si se obtiene los datos del
	 * producto
	 */
	private boolean habilitaForm;

	/**
	 * bandera que permite habilitar el boton para consultar
	 */
	private boolean buscaHabilitada = true;

	/**
	 * bandera que permite continuar con el proceso habilitar el boton Aceptar si es
	 * false
	 */
	private boolean habilitAcept = true;

	/**
	 * Variable que cuenta el numero de intentos ingresados para comparar con el
	 * parametrizado
	 */
	private transient int intentosReserva;

	/**
	 * Lista de proveedores que se va a cargar en el jsf
	 */
	private List<SelectItem> proveedores = new ArrayList<SelectItem>();

	/**
	 * Serailizacion de datos
	 */
	private static final long serialVersionUID = -6485077931600232567L;

	/**
	 * LOG de la clase
	 */
	private static final LoggerBiess LOG = LoggerBiess.getLogger(ConsultaContratoProvBean.class);

	/**
	 * Metodo que carga los datos del producto a partir del credito especial cuando
	 * se carga la pagina
	 */
	public void cargarDataProducto() {
		try {

			if (datos.getDataProductoDinamico() == null
					|| !datos.getCodigoProdEspecial().equals(datos.getDataProductoDinamico().getCodigoEspecial())) {
			inicializaVariables();
			datos.setDataProductoDinamico(prestamoDinamico.consultarDataProducto(datos.getCodigoProdEspecial()));
				inicializaFormulario();
			}

		} catch (ConsultaDataPqDinamicoException e) {
			LOG.error("Error al consultar el producto en el serivicio", e);
			addGlobalErrorMessage(e.getMessage(), "");
		} catch (ConecSrvPqDinamicoException e) {
			LOG.error("Error al consultar el producto en el serivicio", e);
			addGlobalErrorMessage(getResource("messages", "pq.dinamico.error.generico"), "");
		}

	}

	private void inicializaFormulario() {
		cargaComboProveedores();
		habilitaBusqueda();
		setHabilitaForm(Boolean.TRUE);
		setHabilitAcept(Boolean.TRUE);
	}

	private void inicializaVariables() {
		inputFormContrt = new DataConsultaContrato();
		this.datos.setDataRespGenericaResponseDto(null);
	}

	private void habilitaBusqueda() {
		if (this.datos.getDataProductoDinamico().getProveedores().size() == NUM_MINIMO_PROV) {
			this.buscaHabilitada = false;
		} else {
			this.buscaHabilitada = true;
		}

	}

	private void cargaComboProveedores() {
		final List<SelectItem> listaItems = new ArrayList<SelectItem>();
		for (final DataCmbProveedor selectItem : datos.getDataProductoDinamico().getProveedores()) {
			listaItems.add(nuevoItem(selectItem));
		}
		proveedores = listaItems;
	}

	/**
	 * Crea un nuevo item para combo de proveedores
	 * 
	 * @param selectItem
	 * @return
	 */
	private SelectItem nuevoItem(final DataCmbProveedor selectItem) {
		return new SelectItem(selectItem.getRuc(), selectItem.getNombre());
	}

	/**
	 * Realiza la consulta del numero de contrato desde el jsf
	 */
	public void consultarNumContrato() {
		if(inputFormContrt.getNumContrtIng()==null || inputFormContrt.getNumContrtIng().isEmpty()) {
			addGlobalErrorMessage(super.getResource("errores", "pq.dinamico.error.requerido"), "");
			return;
		}
		try {

			datos.setDataRespGenericaResponseDto(prestamoDinamico.consultarDataProveedor(obtenerDataProv()));
			setHabilitAcept(false);

		} catch (ConsultaDataPqDinamicoException e) {
			LOG.warn("Error en respuesta servicio", e);
			this.intentosReserva++;
			final String mensajeValida = String.format(super.getResource("messages", "pq.dinamico.mensaje.intentos"),
					this.intentosReserva, this.datos.getDataProductoDinamico().getNumeroIntentos());
			guardarAuditoria();
			cerrarSesionPorIntentos();
			addGlobalErrorMessage(mensajeValida, "");
			inicializaVariables();
		} catch (ConecSrvPqDinamicoException e) {
			LOG.error("Error no especificado al consultar", e);
			addGlobalErrorMessage(super.getResource("messages", "pq.dinamico.error.generico"), "");
			inicializaVariables();
		} catch (ExisteCodigoContratoException e) {
			LOG.warn("Existe el codigo de contrato", e);
			addGlobalErrorMessage(getResource("messages", "pq.dinamico.mensaje.existe.prod"), "");
			inicializaVariables();
		} catch (ParamCategoriaException e) {
			LOG.warn("No esta parametrizado la categoria", e);
			addGlobalErrorMessage(getResource("messages", "pq.dinamico.mensaje.categ.noparam"), "");
			inicializaVariables();
		}

		this.datos.setCodigoContratoProv(inputFormContrt.getNumContrtIng());

	}

	private DataProveedorRequestDto obtenerDataProv() {
		final DataProveedorRequestDto dataProveedor = new DataProveedorRequestDto();
		dataProveedor.setCodigoContrato(inputFormContrt.getNumContrtIng());
		dataProveedor.setCodigoEspecial(this.datos.getDataProductoDinamico().getCodigoEspecial());
		return dataProveedor;
	}

	private void guardarAuditoria() {
		// Inicio guarda auditoria
		super.guardarAuditoria(OperacionEnum.VERIFICACION_CODIGO_CONTRATO_PROVEEDOR,
				AuditoriaPqWebHelper.obtenerParametrosReservaEcuadorTurismo(
						datos.getSolicitante().getDatosPersonales().getCedula(),
						this.getInputFormContrt().getNumContrtIng(), intentosReserva, null, null),
				this.datos.getSolicitante().getDatosPersonales().getCedula());
		// Fin guarda auditoria
	}

	private void cerrarSesionPorIntentos() {

		if (this.intentosReserva == this.datos.getDataProductoDinamico().getNumeroIntentos()
				&& this.datos.getDataProductoDinamico().getNumeroIntentos() != 0) {
			final ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
			final HttpSession session = (HttpSession) ectx.getSession(false);
			session.invalidate();
			final FacesContext ctx = FacesContext.getCurrentInstance();
			final Application app = ctx.getApplication();
			app.getNavigationHandler().handleNavigation(ctx, "/pages/concesion/roles.jsf", "roles");
		}
	}

	/**
	 * Metodo que permite habilitar la busqueda si se ecogio un ruc
	 */
	public void habilitarBusqueda() {
		if (inputFormContrt.getRucSeleccionado() == null || inputFormContrt.getRucSeleccionado().isEmpty()) {
			this.buscaHabilitada = true;
		} else {
			this.buscaHabilitada = false;
		}

	}

	/**
	 * Metodo que permite continuar el flujo y obtener los datos bancarios del
	 * afiliado
	 * 
	 * @return
	 */
	public String aceptar() {
		String ruc = obtenerRuc();
		if (ruc == null) {
			addGlobalErrorMessage(super.getResource("messages", "pq.dinamico.error.generico"), "");
			return "";

		} else {
			obtenerInfoCtaBancariaEcuadorTurismo(ruc);
		return "aceptarProductos";
	}

	}

	private String obtenerRuc() {

		if (datos == null || datos.getDataRespGenericaResponseDto() == null
				|| datos.getDataRespGenericaResponseDto().getProveedor() == null
				|| datos.getDataRespGenericaResponseDto().getProveedor().getRuc() == null) {
			return null;
		} else {
			return datos.getDataRespGenericaResponseDto().getProveedor().getRuc();
		}
	}

	/**
	 * Permite realizar limpieza de los inputs del formulario
	 */
	public void cancelar() {
		this.inputFormContrt.setNumContrtIng("");
		this.datos.setDataRespGenericaResponseDto(null);
		this.setHabilitAcept(true);
	}

	private void obtenerInfoCtaBancariaEcuadorTurismo(final String ruc) {

		final Proveedor provTurismo = this.proveedorServicio.consultarProveedorRucCodpretip(ruc,
				this.datos.getTiposProductosPq().getCodigoTipoProducto());

		if (provTurismo == null) {
			LOG.info("No existe cuenta bancaria registrada para proveedor");
		} else {
			final InstitucionFinanciera instiFina = instFinServ
					.getInstitucionFinanciera(provTurismo.getPrRucInstfinanciera());
			this.datos.setInstitucionFinancieraProveedor(
					DinamicoPqUtil.llenarInstitucionFinanciera(instiFina, provTurismo));
			this.datos.setProveedor(provTurismo);
		}
	}

	/**
	 * Metodo que devuelve la cantidad de proveedores que fuero cargados desde el
	 * proveedor
	 * 
	 * @return
	 */
	public int getCantidadProveedores() {
		return this.datos.getDataProductoDinamico() == null ? 0
				: this.datos.getDataProductoDinamico().getProveedores() == null ? 0
						: this.datos.getDataProductoDinamico().getProveedores().size();

	}

	/**
	 * Obtiene la cantidad de campos que tiene le header
	 * 
	 * @return
	 */
	public int getCantidadCamposHeader() {
		return this.datos.getDataRespGenericaResponseDto() == null ? 0
				: this.datos.getDataRespGenericaResponseDto().getHeader() == null ? 0
						: this.datos.getDataRespGenericaResponseDto().getHeader().size();

	}

	/**
	 * Obtiene la cantidad de campos que tiene el body
	 * 
	 * @return
	 */
	public int getCantidadCamposBody() {
		return this.datos.getDataRespGenericaResponseDto() == null ? 0
				: this.datos.getDataRespGenericaResponseDto().getHeader() == null ? 0
						: this.datos.getDataRespGenericaResponseDto().getBody().size();

	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(final DatosBean datos) {
		this.datos = datos;
	}

	public List<SelectItem> getProveedores() {
		return proveedores;
	}

	public void setProveedores(final List<SelectItem> proveedores) {
		this.proveedores = proveedores;
	}

	public boolean isBuscaHabilitada() {
		return buscaHabilitada;
	}

	public void setBuscaHabilitada(final boolean buscaHabilitada) {
		this.buscaHabilitada = buscaHabilitada;
	}

	public boolean isHabilitAcept() {
		return habilitAcept;
	}

	public void setHabilitAcept(final boolean habilitAcept) {
		this.habilitAcept = habilitAcept;
	}

	public boolean isHabilitaForm() {
		return habilitaForm;
	}

	public void setHabilitaForm(final boolean habilitaForm) {
		this.habilitaForm = habilitaForm;
	}

	public DataConsultaContrato getInputFormContrt() {
		return inputFormContrt;
	}

	public void setInputFormContrt(final DataConsultaContrato inputFormContrt) {
		this.inputFormContrt = inputFormContrt;
	}

}