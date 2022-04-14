/**
 * 
 */
package ec.gov.iess.planillaspq.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;


import ec.fin.biess.listaobservados.enumeration.EstadoBloqueoEnum;
import ec.fin.biess.listaobservados.exception.BloqueoListaControlException;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.fin.biess.listaobservados.service.BloqueoListaControlServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.hl.exception.RegistroCivilException;
import ec.gov.iess.hl.modelo.RegistroCivil;
import ec.gov.iess.hl.servicio.RegistroCivilServicio;
import ec.gov.iess.planillaspq.web.util.BaseBean;
import ec.gov.iess.planillaspq.web.util.DireccionIPUtil;

/**
 * Clase controlador para la pantalla de administracion de asegurados en lista de observados.
 * 
 * @author diego.iza
 * 
 */
public class ListaObservadosBean extends BaseBean implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 3416054199075488000L;

	private static final LoggerBiess log = LoggerBiess.getLogger(ListaObservadosBean.class);

	@EJB(name = "BloqueoListaControlServicioImpl/local")
	private BloqueoListaControlServicioLocal bloqueoListaControlServicio;

	@EJB(name = "RegistroCivilServicioImpl/local")
	private RegistroCivilServicio registroCivilServicio;

	private BloqueoListaControl bloqueoListaControlCriterio;

	private BloqueoListaControl bloqueoListaControlSeleccionado;

	private String mensaje;

	private String mensajeExito;

	private String mensajeRequerido;

	private List<BloqueoListaControl> listaBloqueos;

	private ArrayList<SelectItem> aplicativosListItems;

	private List<String> aplicativos;

	private ArrayList<SelectItem> estadosListItems;

	private EstadoBloqueoEnum estadoBloqueado = EstadoBloqueoEnum.S;

	private String motivo;

	private Map<String, String> listaCedulas;

	/**
	 * Constructor.
	 */
	public ListaObservadosBean() {
		this.bloqueoListaControlCriterio = new BloqueoListaControl();
		bloqueoListaControlCriterio.setFechaInicio(new Date());
		bloqueoListaControlCriterio.setFechaFin(new Date());
	}

	/**
	 * @return the aplicativosListItems
	 */
	public ArrayList<SelectItem> getAplicativosListItems() {

		if (aplicativosListItems == null) {
			aplicativosListItems = new ArrayList<SelectItem>();
			aplicativosListItems.add(new SelectItem(null, "Todos..."));
			aplicativosListItems.add(new SelectItem("PQ", "PQ"));
			aplicativosListItems.add(new SelectItem("PH", "PH"));
			aplicativosListItems.add(new SelectItem("PP", "PP"));
			aplicativos = new ArrayList<String>();
			aplicativos.add("PQ");
			aplicativos.add("PH");
			aplicativos.add("PP");
		}

		return aplicativosListItems;
	}

	/**
	 * @return the estadosListItems
	 */
	public ArrayList<SelectItem> getEstadosListItems() {

		if (estadosListItems == null) {
			estadosListItems = new ArrayList<SelectItem>();
			estadosListItems.add(new SelectItem(null, "Todos..."));
			estadosListItems.add(new SelectItem(EstadoBloqueoEnum.S, EstadoBloqueoEnum.S.toString()));
			estadosListItems.add(new SelectItem(EstadoBloqueoEnum.N, EstadoBloqueoEnum.N.toString()));
		}

		return estadosListItems;
	}

	/**
	 * Obtiene los datos del beneficiario, consumiendo el ws de registro civil.
	 * 
	 * @param cedula
	 * 
	 */
	public String obtenerDatosBeneficiarioRegistroCivil(String cedula) {

		if (this.listaCedulas == null) {
			this.listaCedulas = new HashMap<String, String>();
		}

		if (cedula == null || cedula.trim().length() <= 0) {
			return "";
		}

		if (this.listaCedulas.containsKey(cedula)) {
			return this.listaCedulas.get(cedula);
		}

		RegistroCivil registroCivil = null;
		try {
			registroCivil = registroCivilServicio.consultarRegistroCivil(cedula);

			// Verificar informacion obtenida del WS
			if (null == registroCivil || null == registroCivil.getNomper() || "" == registroCivil.getNomper().trim()) {
				log.error("Error al obtener el nombre del beneficiario del registro civil.");
				return cedula;
			} else {
				this.listaCedulas.put(cedula, registroCivil.getNomper());
				return registroCivil.getNomper();
			}

		} catch (RegistroCivilException e) {
			log.error("Error al obtener el nombre del beneficiario del registro civil.");
		} catch (NullPointerException e) {
			log.error("Error al obtener el nombre del beneficiario del registro civil.");
		} catch (Exception e) {
			log.error("Error al obtener el nombre del beneficiario del registro civil.");
		}

		return cedula;
	}

	/**
	 * Obtiene el listado de los asegurados que se encuentran en una lista de observados, bloqueado y no bloqueados.
	 */
	public void obtenerUsuariosEnListaObservados() {

		this.mensaje = null;
		this.mensajeExito = null;
		this.motivo = null;

		try {

			if (this.listaBloqueos != null) {
				this.listaBloqueos.clear();
			}

			// Valida Fechas
			if (this.bloqueoListaControlCriterio.getFechaInicio().after(this.bloqueoListaControlCriterio.getFechaFin())) {
				this.mensaje = "La Fecha a consultar desde, debe ser menor igual que la fecha a consultar hasta";
			} else {
				this.mensaje = null;
			}

			// Valida Aplicativos
			if (this.bloqueoListaControlCriterio.getAplicativo() == null
					|| this.bloqueoListaControlCriterio.getAplicativo().trim().length() <= 0) {
				this.bloqueoListaControlCriterio.setAplicativos(this.aplicativos);
			} else {
				this.bloqueoListaControlCriterio.setAplicativos(new ArrayList<String>());
				this.bloqueoListaControlCriterio.getAplicativos().add(this.bloqueoListaControlCriterio.getAplicativo());
			}

			// Valida Estados
			if (this.bloqueoListaControlCriterio.getBloqueado() == null
					|| this.bloqueoListaControlCriterio.getBloqueado().toString().trim().length() <= 0) {
				this.bloqueoListaControlCriterio.setEstados(Arrays.asList(EstadoBloqueoEnum.values()));
			} else {
				this.bloqueoListaControlCriterio.setEstados(new ArrayList<EstadoBloqueoEnum>());
				this.bloqueoListaControlCriterio.getEstados().add(this.bloqueoListaControlCriterio.getBloqueado());
			}

			// Consultar Informacion
			this.listaBloqueos = this.bloqueoListaControlServicio.obtenerBloqueos(this.bloqueoListaControlCriterio);

			if (this.listaBloqueos != null && !this.listaBloqueos.isEmpty()) {
				for (BloqueoListaControl bloqueoListaControl : this.listaBloqueos) {
					bloqueoListaControl.setFuncionarioIngresa(this
							.obtenerDatosBeneficiarioRegistroCivil(bloqueoListaControl.getUsuarioRegistro()));
					bloqueoListaControl.setFuncionarioActualiza(this
							.obtenerDatosBeneficiarioRegistroCivil(bloqueoListaControl.getUsuarioActualizacion()));
				}
			}

		} catch (BloqueoListaControlException e) {
			this.mensaje = "No se pudo obtener la informaci\u00F3n de los registros de bloqueos en lista de observados.";
			log.error("No se pudo obtener la informaci\u00F3n de los registros de bloqueos en lista de observados.", e);
		} catch (Exception e) {
			this.mensaje = "No se pudo obtener la informaci\u00F3n de los registros de bloqueos en lista de observados.";
			log.error("No se pudo obtener la informaci\u00F3n de los registros de bloqueos en lista de observados.", e);
		}
	}

	/**
	 * Desbloquea un asegurado.
	 */
	public void desbloquear() {
		this.mensaje = null;
		this.mensajeExito = null;
		this.mensajeRequerido = null;

		if (this.bloqueoListaControlSeleccionado != null) {

			if (this.motivo == null || this.motivo.trim().length() <= 0) {
				this.mensajeRequerido = "El motivo para realizar el desbloqueo es un valor requerido.";
				return;
			}

			this.bloqueoListaControlSeleccionado.setBloqueado(EstadoBloqueoEnum.N);
			this.bloqueoListaControlSeleccionado.setFechaActualizacion(new Date());
			this.bloqueoListaControlSeleccionado.setFechaDesbloqueo(new Date());
			this.bloqueoListaControlSeleccionado.setUsuarioActualizacion(this.getRemoteCI());
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			this.bloqueoListaControlSeleccionado.setIpActualizacion(DireccionIPUtil.obtenerDireccionIPCliente(request));
			this.bloqueoListaControlSeleccionado.setObservacionDesbloqueo(this.motivo.trim());

			try {
				this.bloqueoListaControlSeleccionado.setMacAddressMod(this.bloqueoListaControlSeleccionado
						.getIpActualizacion());

				this.bloqueoListaControlServicio.actualizar(this.bloqueoListaControlSeleccionado);

				this.mensajeExito = "Se realiz\u00F3 el desbloqueo correcto del asegurado: "
						+ this.bloqueoListaControlSeleccionado.getNombresApellidos() + " con c\u00E9dula: "
						+ this.bloqueoListaControlSeleccionado.getCedula();

				this.motivo = null;
				
				this.obtenerUsuariosEnListaObservados();
			} catch (BloqueoListaControlException e) {
				this.mensajeExito = "No se pudo realizar el desbloqueo del asegurado: "
						+ this.bloqueoListaControlSeleccionado.getNombresApellidos() + " con c\u00E9dula: "
						+ this.bloqueoListaControlSeleccionado.getCedula();
				log.error("No se pudo realizar el desbloqueo del asegurado: ", e);
			} catch (Exception e) {
				this.mensajeExito = "No se pudo realizar el desbloqueo del asegurado: "
						+ this.bloqueoListaControlSeleccionado.getNombresApellidos() + " con c\u00E9dula: "
						+ this.bloqueoListaControlSeleccionado.getCedula();
				log.error("No se pudo realizar el desbloqueo del asegurado: ", e);
			}
		}
	}

	/**
	 * Cierra la pantalla.
	 */
	public void cancelar() {
		this.mensaje = null;
		this.mensajeExito = null;
		this.mensajeRequerido = null;
		this.bloqueoListaControlSeleccionado = null;
		this.motivo = null;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the listaBloqueos
	 */
	public List<BloqueoListaControl> getListaBloqueos() {
		return listaBloqueos;
	}

	/**
	 * @param listaBloqueos
	 *            the listaBloqueos to set
	 */
	public void setListaBloqueos(List<BloqueoListaControl> listaBloqueos) {
		this.listaBloqueos = listaBloqueos;
	}

	/**
	 * @param aplicativosListItems
	 *            the aplicativosListItems to set
	 */
	public void setAplicativosListItems(ArrayList<SelectItem> aplicativosListItems) {
		this.aplicativosListItems = aplicativosListItems;
	}

	/**
	 * @param estadosListItems
	 *            the estadosListItems to set
	 */
	public void setEstadosListItems(ArrayList<SelectItem> estadosListItems) {
		this.estadosListItems = estadosListItems;
	}

	/**
	 * @return the bloqueoListaControlCriterio
	 */
	public BloqueoListaControl getBloqueoListaControlCriterio() {
		return bloqueoListaControlCriterio;
	}

	/**
	 * @param bloqueoListaControlCriterio
	 *            the bloqueoListaControlCriterio to set
	 */
	public void setBloqueoListaControlCriterio(BloqueoListaControl bloqueoListaControlCriterio) {
		this.bloqueoListaControlCriterio = bloqueoListaControlCriterio;
	}

	/**
	 * @return the bloqueoListaControlSeleccionado
	 */
	public BloqueoListaControl getBloqueoListaControlSeleccionado() {
		return bloqueoListaControlSeleccionado;
	}

	/**
	 * @param bloqueoListaControlSeleccionado
	 *            the bloqueoListaControlSeleccionado to set
	 */
	public void setBloqueoListaControlSeleccionado(BloqueoListaControl bloqueoListaControlSeleccionado) {
		this.bloqueoListaControlSeleccionado = bloqueoListaControlSeleccionado;
	}

	/**
	 * @return the aplicativos
	 */
	public List<String> getAplicativos() {
		return aplicativos;
	}

	/**
	 * @param aplicativos
	 *            the aplicativos to set
	 */
	public void setAplicativos(List<String> aplicativos) {
		this.aplicativos = aplicativos;
	}

	/**
	 * @return the mensajeExito
	 */
	public String getMensajeExito() {
		return mensajeExito;
	}

	/**
	 * @param mensajeExito
	 *            the mensajeExito to set
	 */
	public void setMensajeExito(String mensajeExito) {
		this.mensajeExito = mensajeExito;
	}

	/**
	 * @return the estadoBloqueado
	 */
	public EstadoBloqueoEnum getEstadoBloqueado() {
		return estadoBloqueado;
	}

	/**
	 * @param estadoBloqueado
	 *            the estadoBloqueado to set
	 */
	public void setEstadoBloqueado(EstadoBloqueoEnum estadoBloqueado) {
		this.estadoBloqueado = estadoBloqueado;
	}

	/**
	 * @return the mensajeRequerido
	 */
	public String getMensajeRequerido() {
		return mensajeRequerido;
	}

	/**
	 * @param mensajeRequerido
	 *            the mensajeRequerido to set
	 */
	public void setMensajeRequerido(String mensajeRequerido) {
		this.mensajeRequerido = mensajeRequerido;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the listaCedulas
	 */
	public Map<String, String> getListaCedulas() {
		return listaCedulas;
	}

	/**
	 * @param listaCedulas
	 *            the listaCedulas to set
	 */
	public void setListaCedulas(Map<String, String> listaCedulas) {
		this.listaCedulas = listaCedulas;
	}

}
