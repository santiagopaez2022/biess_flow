package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

import org.ajax4jsf.component.html.AjaxForm;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.hl.exception.ContactosAfiliadoException;
import ec.gov.iess.hl.exception.DivisionPoliticaException;
import ec.gov.iess.hl.modelo.ContactosAfiliado;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.pks.ContactosAfiliadoPK;
import ec.gov.iess.hl.servicio.ContactosAfiliadoServicio;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.validator.EmailValidator;
import ec.gov.iess.pq.concesion.web.validator.RequeridoValidator;
import ec.gov.iess.pq.concesion.web.validator.TelefonoValidator;

/**
 * @author Daniel Cardenas
 * 
 */
public class ContactosBean extends BaseBean implements Serializable {

	// log4j
	private transient static final LoggerBiess log = LoggerBiess
			.getLogger(ContactosBean.class);

	private static final long serialVersionUID = -1262829432034168676L;

	@EJB(name = "ContactosAfiliadoServicioImpl/local")
	private ContactosAfiliadoServicio contactosAfiliadoServicio;

	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionPoliticaServicio;

	// propiedades del componente

	private AjaxForm formulario;

	// private HtmlDataTable tabla;

	private boolean mostrarFormulario = false;

	private boolean mostrarTabla = false;

	private boolean nuevo = false;

	private DatosBean datos;

	private List<ContactosAfiliado> lista;

	private ContactosAfiliado contacto;

	private int total;

	private ArrayList<SelectItem> provinciaList;
	private ArrayList<SelectItem> cantonList;
	private ArrayList<SelectItem> parroquiaList;

	public int getTotal() {
		if (lista != null) {
			total = lista.size();
		} else {
			total = 0;
		}
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public List<ContactosAfiliado> getLista() {
		if (lista == null) {
			log.debug("obteniendo lista de contactos");
			lista = contactosAfiliadoServicio.consultaContactosAfiliado(datos
					.getSolicitante().getDatosPersonales().getCedula());
			log.debug("tiene " + lista.size() + "contactos");
		}
		return lista;
	}

	public void setLista(List<ContactosAfiliado> lista) {
		this.lista = lista;
	}

	public AjaxForm getFormulario() {
		return formulario;
	}

	public void setFormulario(AjaxForm formulario) {
		this.formulario = formulario;
	}

	public ContactosAfiliado getContacto() {
		return contacto;
	}

	public void setContacto(ContactosAfiliado contacto) {
		this.contacto = contacto;
	}

	public boolean isMostrarFormulario() {
		return mostrarFormulario;
	}

	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	// acciones de la aplicacion

	public String nuevo() {
		contacto = null;
		contacto = new ContactosAfiliado();

		/*
		 * ContactosAfiliadoPK pk= new ContactosAfiliadoPK();
		 * pk.setNumafi(datos.
		 * getSolicitante().getDatosPersonales().getCedula());
		 * pk.setCedidecon(""); contacto.setContactosAfiliadoPK(pk);
		 */
		contacto.setContactosAfiliadoPK(new ContactosAfiliadoPK());
		try {
			contacto.setDivisionPolitica(divisionPoliticaServicio
					.consultaDivisionPoliticaPorId(datos.getSolicitante()
							.getDatosPersonales().getParroquiaId()));
		} catch (DivisionPoliticaException e) {
			log.error(e);
		}

		log.debug("nuevo");
		nuevo = true;
		mostrarTabla = true;
		mostrarFormulario = true;
		return null;
	}

	public String administrar() {
		log.debug("administrar");
		mostrarTabla = true;
		mostrarFormulario = false;
		return null;
	}

	public String editar() {

		log.debug("obteniendo contacto...");
		// contacto = contactoSeleccionado();
		this.setContacto(contactoSeleccionado());
		log.debug(contacto.getApenomcon());
		log.debug("prov:"
				+ contacto.getDivisionPolitica().getDivisionPolitica()
						.getDivisionPolitica().getCoddivpol());
		log.debug("can:"
				+ contacto.getDivisionPolitica().getDivisionPolitica()
						.getCoddivpol());
		log.debug("parr:" + contacto.getDivisionPolitica().getCoddivpol());
		// formulario.
		cantonList = null;
		parroquiaList = null;
		nuevo = false;
		mostrarTabla = true;
		mostrarFormulario = true;
		return null;
	}

	public String grabar() {

		String cedulaAfiliado = datos.getSolicitante().getDatosPersonales()
				.getCedula();
		String cedulaContacto = contacto.getContactosAfiliadoPK()
				.getCedidecon();
		String nombreContacto = contacto.getApenomcon();
		String divisionPoliticaContacto = contacto.getDivisionPolitica()
				.getCoddivpol();
		String direccionContacto = contacto.getDircon();
		String telefonoContacto = contacto.getTelcon();
		String emailContacto = contacto.getMaicont();
		String celularContacto = contacto.getCelcon();

		log.debug("empieza a validar");
		RequeridoValidator.validate(cedulaAfiliado, context(),
				"cedula.contacto.requerida");
		RequeridoValidator.validate(nombreContacto, context(),
				"nombres.apellidos.requerido");
		RequeridoValidator.validate(direccionContacto, context(),
				"direccion.requerido");
		EmailValidator.validate(emailContacto, context(), "email.invalido");
		TelefonoValidator.validate(telefonoContacto, context(),
				"telefono.invalido",false);
		TelefonoValidator.validate(celularContacto, context(),
				"telefono.invalido",true);

		if (context().getMessages().hasNext()) {
			log.debug("existen errores en el form de contacto.");
			mostrarFormulario = true;
			return null;
		}
		log.debug("termina de validar");

		try {

			if (nuevo) {
				Timestamp fechaCreacionContacto = new Timestamp(
						new Date().getTime());
				contactosAfiliadoServicio.creaContacto(cedulaAfiliado,
						cedulaContacto, nombreContacto,
						divisionPoliticaContacto, direccionContacto,
						telefonoContacto, emailContacto, celularContacto,
						fechaCreacionContacto);

			} else {
				contactosAfiliadoServicio.actualizaDatosContacto(
						cedulaAfiliado, cedulaContacto, nombreContacto,
						divisionPoliticaContacto, direccionContacto,
						telefonoContacto, emailContacto, celularContacto);
			}
			log.debug("ha grabado");
			// mostrarTabla = true;
			mostrarFormulario = false;
			lista = null;
			lista = getLista();// borra para que vuelva a consultar
			mostrarTabla = true;
		} catch (ContactosAfiliadoException e) {
			log.error(e);
			FacesMessage message = new FacesMessage();
			message.setDetail(e.getMessage());
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context().addMessage(null, message);
			// mostrarTabla = true;
			mostrarFormulario = true;
			// lista=null;
		}
		return null;
	}

	// boton cancelar
	public String cancelar() {
		log.debug("cancela edicion o nuevo");
		mostrarFormulario = false;
		return null;
	}

	private ContactosAfiliado contactoSeleccionado() {
		ContactosAfiliado contacto = (ContactosAfiliado) context()
				.getExternalContext().getRequestMap().get("item");
		return (contacto);
	}

	public ArrayList<SelectItem> getProvinciaList() {
		if (provinciaList == null) {
			log.debug("obteniendo provincias C");
			List<DivisionPolitica> provincias = divisionPoliticaServicio
					.consultarProvincias();
			provinciaList = new ArrayList<SelectItem>();
			for (Iterator<DivisionPolitica> iterator = provincias.iterator(); iterator
					.hasNext();) {
				DivisionPolitica provincia = iterator.next();
				provinciaList.add(new SelectItem(provincia.getCoddivpol(),
						provincia.getNomdivpol()));
			}
		}
		this.cantonList = null;
		return provinciaList;
	}

	public void setProvinciaList(ArrayList<SelectItem> provinciaList) {
		this.provinciaList = provinciaList;
	}

	public ArrayList<SelectItem> getCantonList() {
		if (cantonList == null) {
			log.debug("obteniendo cantones C");
			List<DivisionPolitica> cantones = new ArrayList<DivisionPolitica>();
			cantones = divisionPoliticaServicio
					.consultarDivisionPoliticaHijos(contacto
							.getDivisionPolitica().getDivisionPolitica()
							.getDivisionPolitica().getCoddivpol());
			cantonList = new ArrayList<SelectItem>();
			for (Iterator<DivisionPolitica> iterator = cantones.iterator(); iterator
					.hasNext();) {
				DivisionPolitica canton = iterator.next();
				cantonList.add(new SelectItem(canton.getCoddivpol(), canton
						.getNomdivpol()));
			}
		}
		this.parroquiaList = null;

		return cantonList;
	}

	public void setCantonList(ArrayList<SelectItem> cantonList) {
		this.cantonList = cantonList;
	}

	public ArrayList<SelectItem> getParroquiaList() {
		if (parroquiaList == null) {
			log.debug("obteniendo parroquias C");
			List<DivisionPolitica> parroquias = new ArrayList<DivisionPolitica>();

			parroquias = divisionPoliticaServicio
					.consultarDivisionPoliticaHijos(contacto
							.getDivisionPolitica().getDivisionPolitica()
							.getCoddivpol());
			parroquiaList = new ArrayList<SelectItem>();
			for (Iterator<DivisionPolitica> iterator = parroquias.iterator(); iterator
					.hasNext();) {
				DivisionPolitica parroquia = iterator.next();
				parroquiaList.add(new SelectItem(parroquia.getCoddivpol(),
						parroquia.getNomdivpol()));
			}
		}
		return parroquiaList;
	}

	public void setParroquiaList(ArrayList<SelectItem> parroquiaList) {
		this.parroquiaList = parroquiaList;
	}
}