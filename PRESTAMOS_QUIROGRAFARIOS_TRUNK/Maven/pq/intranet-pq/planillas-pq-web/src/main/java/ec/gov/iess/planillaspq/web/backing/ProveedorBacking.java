package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;
import ec.gov.iess.creditos.modelo.dto.SucursalDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.modelo.persistencia.TipoPrestamo;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.planillaspq.web.alertas.util.AlertUtil;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * Clase para administrar los proveedores
 * 
 * @author edwin.maza
 *
 */
public class ProveedorBacking extends BaseBean implements Serializable {
	
	private static final LoggerBiess log = LoggerBiess
			.getLogger(ProveedorBacking.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "ProveedorServicioImpl/local")
	private transient ProveedorServicio proveedorServicio;
	@EJB(name = "TipoPrestamoServicioImpl/local")
	private transient TipoPrestamoServicio tipoPrestamoProductoServicio;
	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio institucionFinancieraServicio;
	@EJB(name = "AfiliadoServicioImpl/local")
	private transient AfiliadoServicio afiliadoServicio;
	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;

	private Long idProveedor;
	private Proveedor proveedor;
	private Long idTipoProducto;
	private FuncionarioHandler funcionario;
	private boolean newone = false;
	private String name;
	
	@PostConstruct
	void init() {
		funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
	}
	
	public void seleccionarProveedor(ActionEvent event) {
		proveedor = proveedorServicio.load(idProveedor);
		idTipoProducto = proveedor.getTipoPrestamoProducto().getCodigo();
		newone = true;
	}

	/**
	 * Agregar un proveedor
	 * 
	 * @return {@link String}
	 */
	public String agregar() {
		proveedor = new Proveedor();
		proveedor.setTipoPrestamoProducto(new TipoPrestamo());
		newone = true;
		return "";
	}

	/**
	 * Guardar un proveedor
	 * 
	 * @return {@link String}
	 */
	public String guardar() {
		try {
			/* Verificar si el nombre de usuario existe */
			if (proveedorServicio.usuarioExiste(proveedor)) {
				addGlobalErrorMessage("El nombre de usuario ya existe. Favor eliga otro nombre de usuario para el contacto.", "");
				return "";
			}			
			/* Verificar si el codigo de acceso existe */
			if (proveedorServicio.codigoAccesoExiste(proveedor)) {
				addGlobalErrorMessage("El c\u00F3digo de acceso ya existe. Favor eliga otro c\u00F3digo de acceso WS.", "");
				return "";
			}			
			/* Guardar/actualizar datos del proveedor */
			proveedor.setFechaActualizacion(new Date());
			proveedor.setCedulaFuncionario(funcionario.getCedula());
			proveedor.getTipoPrestamoProducto().setCodigo(idTipoProducto);
			proveedor.setPrCedrepCtacorriente(proveedor.getPrCedulaReplegal());
			if (proveedor.getPrIdProveedor() > 0) {
				proveedorServicio.actualizar(proveedor);
			} else {
				proveedor.setEstadoCatalogo("ESTPROV");
				proveedor = proveedorServicio.guardar(proveedor);
			}
			addGlobalInfoMessage("Guardado con exito.", "");
		} catch (Exception e) {
			addGlobalErrorMessage("Error al guardar datos del proveedor.", "");
			return "";
		}
		proveedor = new Proveedor();
		proveedor.setTipoPrestamoProducto(new TipoPrestamo());
		
		return "";
	}

	/**
	 * Cancelar la edicion de un proveedor
	 * @return {@link String} 
	 */
	public String cancelar() {
		newone = false;
		proveedor = null;
		
		return "";
	}
	
	/**
	 * Obtiene la lista de proveedores
	 * 
	 * @return List<SelectItem>
	 */
	public List<Proveedor> getListaProveedor() {
		List<Proveedor> lista = proveedorServicio.getListaProveedor();
		if (null == lista) {
			lista = new ArrayList<Proveedor>();
		}
		return lista;
	}

	public Proveedor getProveedor() {
		if (proveedor == null) {
			proveedor = new Proveedor();
		}
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	/**
	 * Obtiene la lista de productos
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getlistaTipoProducto() {
		List<TipoPrestamo> lista = tipoPrestamoProductoServicio.getListaTipoPrestamo("PQ");
		List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
		for (TipoPrestamo tipoPrestamoProducto : lista) {
			SelectItem item = new SelectItem(tipoPrestamoProducto.getCodigo(), tipoPrestamoProducto.getDescripcion());
			listaSelectItem.add(item);
		}
		return listaSelectItem;
	}

	/**
	 * Obtiene la lista de Tipo de cuentas
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getlistaTipoCuenta() {
		List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("AHO", "Ahorro");
		listaSelectItem.add(item);
		item = new SelectItem("COR", "Corriente");
		listaSelectItem.add(item);
		return listaSelectItem;
	}

	/**
	 * Obtiene la lista de Estado
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getlistaEstado() {
		List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("ACT", "Activo");
		listaSelectItem.add(item);
		item = new SelectItem("INA", "Inactivo");
		listaSelectItem.add(item);
		return listaSelectItem;
	}

	/**
	 * Obtiene la lista de Bancos
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getlistaInstitucionFinanciera() {
		List<InstitucionFinanciera> lista = institucionFinancieraServicio.getIfisOrderByDescripcion();
		List<SelectItem> listaSelectItem = new ArrayList<SelectItem>();
		for (InstitucionFinanciera institucionFinanciera : lista) {
			SelectItem item = new SelectItem(institucionFinanciera.getRuc(), institucionFinanciera.getDescripcion());
			listaSelectItem.add(item);
		}
		return listaSelectItem;
	}
	
	public void obtenerDatosSucursal() {
		if (null == proveedor.getPrRuc() || proveedor.getPrRuc().isEmpty()) {
			addGlobalErrorMessage("Ingrese el RUC del proveedor.", "");
			return;
		}
		if (null == proveedor.getCodigoSucursal() || proveedor.getCodigoSucursal().isEmpty()) {
			addGlobalErrorMessage("Ingrese el c\u00F3digo de la sucursal.", "");
			return;
		}
		SucursalDto datosSucursal = proveedorServicio.obtenerDatosSucursal(proveedor.getPrRuc(),
				proveedor.getCodigoSucursal());
		proveedor.setPrNombre(datosSucursal.getNombre());
		proveedor.setPrDireccion(datosSucursal.getDireccion());
		proveedor.setPrTelefonoProveedor(datosSucursal.getTelefono());
		proveedor.setPrRucInstfinanciera(datosSucursal.getRucInstFinanciera());
		String tipcta = "";
		if (datosSucursal.getTipoCuenta() != null) {
			if (datosSucursal.getTipoCuenta().equals("01")) {
				tipcta = "COR";	
			} else if (datosSucursal.getTipoCuenta().equals("02")) {
				tipcta = "AHO";	
			}			
		}
		proveedor.setPrTipoCuenta(tipcta);
		proveedor.setPrNumCuenta(datosSucursal.getNumeroCuenta());
		proveedor.setPrCedulaReplegal(datosSucursal.getCedulaRepLegal());
		proveedor.setPrNombreReplgal(datosSucursal.getNombreRepLegal());
		proveedor.setPrTelefonoReplegal(datosSucursal.getTelefonoRepLegal());
	}

	public void obtenerDatosContacto() {
		if (null == proveedor.getCedulaContacto() || proveedor.getCedulaContacto().isEmpty()) {
			addGlobalErrorMessage("Ingrese la c\u00E9dula del contacto.", "");
			return;
		}
		Afiliado datosAfiliado;
		try {
			datosAfiliado = afiliadoServicio.consultarDatosAfiliado(proveedor.getCedulaContacto());
			proveedor.setNombreContacto(datosAfiliado.getApenomafi());
			proveedor.setDireccionContacto(datosAfiliado.getDirafi() != null ? datosAfiliado.getDirafi() : "");
			proveedor.setTelefonoContacto(datosAfiliado.getTelafi() != null ? datosAfiliado.getTelafi() : "");
		} catch (AfiliadoException e) {
		}
		proveedor.setUsuarioContacto(proveedor.getCedulaContacto());
	}

	public void enviarEmailClave(ActionEvent event) {
		proveedor = proveedorServicio.load(idProveedor);
		if (null == proveedor.getPrCorreoContacto() || proveedor.getPrCorreoContacto().isEmpty()) {
			addGlobalErrorMessage("Ingrese el correo electr\u00F3nico del contacto.", "");
			return;
		}		
		if (null == proveedor.getUsuarioContacto() || proveedor.getUsuarioContacto().length() < 8) {
			addGlobalErrorMessage("El nombre de usuario debe tener al menos 8 caracteres.", "");
			return;
		}		
		/* Actualizar clave en proveedor */
		final String clave = proveedorServicio.getRamdomPassword(8);
		final String encryptedClave = proveedorServicio.getEncriptedPassword(clave);
		proveedor.setClaveContacto(encryptedClave);
		proveedorServicio.actualizar(proveedor);
		/* Enviar e-mail con clave */
		try {
			DatosPersonales dp = new DatosPersonales();
			dp.setCedula(proveedor.getCedulaContacto());
			dp.setEmail(proveedor.getPrCorreoContacto());
			String templatePath = "ec/gov/iess/planillaspq/web/alertas/templates/email/claveprov.ftl";                    
	        Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_nombre", proveedor.getNombreContacto());
	        alertData.put("msg_usuario", proveedor.getUsuarioContacto());	        
	        alertData.put("msg_clave", clave);
	        AlertUtil.enviarAlerta(alertUserHelper, dp, templatePath, alertData, null, AlertType.EMAIL);
	        addGlobalInfoMessage("Clave generada y enviada con exito.", "");
		} catch (Exception e) {
			log.error("Error al enviar clave proveedor: " + proveedor.getPrCorreoContacto(), e);
			addGlobalInfoMessage("Error al generar y enviar clave.", "");
		}
	}

	/**
	 * @return the newone
	 */
	public boolean isNewone() {
		return newone;
	}

	/**
	 * @param newone the newone to set
	 */
	public void setNewone(boolean newone) {
		this.newone = newone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}