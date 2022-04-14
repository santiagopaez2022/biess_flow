package ec.gov.iess.pq.concesion.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import ec.fin.biess.conozcasucliente.sbs.enumeration.SbsCatalogoEnum;
import ec.fin.biess.conozcasucliente.sbs.exception.SbsCatalogoExcepcion;
import ec.fin.biess.conozcasucliente.sbs.modelo.persistence.SbsCatalogo;
import ec.fin.biess.conozcasucliente.sbs.service.SbsCatalogoServicioLocal;
import ec.fin.biess.creditos.pq.servicio.TipoCuentaBiessServicio;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.persistencia.TipoCuentaBiess;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;

public class CombosDireccionBean implements Serializable {

	private static final long serialVersionUID = -3541498982181246156L;

	// LIMITE DE CARGAS FAMILIARES, ANGEL SARMIENTO
	private static final int maxCargas=30;
	
	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionPoliticaServicio;

	// propiedades del componente

	private ArrayList<SelectItem> provinciaList;
	private ArrayList<SelectItem> cantonList;
	private ArrayList<SelectItem> parroquiaList;
	private ArrayList<SelectItem> cargasList; 
	private Solicitante solicitante;
	
	// INC-2272 Pensiones Alimenticias
	@EJB(name = "InstitucionFinancieraServicioImpl/local")
	private transient InstitucionFinancieraServicio institucionFinancieraServicio;
	
	@EJB(name = "TipoCuentaBiessServicioImpl/local")
	private TipoCuentaBiessServicio tipoCuentaBiessServicioLocal;

	private ArrayList<SelectItem> institucionFinancieraList;

	private ArrayList<SelectItem> ciudadList;
	
	private ArrayList<SelectItem> listaTipoCuenta;
		
	private String idProvinciaPA;
	
	// INC-2300 Requerimiento R24
	@EJB(name = "SbsCatalogoServicioImpl/local")
	private transient SbsCatalogoServicioLocal sbsCatalogoServicioLocal;
	
	private ArrayList<SelectItem> nivelEstudioList;
			
	private ArrayList<SelectItem> profesionList;
	
	private ArrayList<SelectItem> tipoViviendaList;
			
	private ArrayList<SelectItem> origenIngresosList;
	
	public ArrayList<SelectItem> getCargasList() {
		if (cargasList == null) {
			cargarCargas();
		}
		
		return cargasList;
	}

	public void setCargasList(ArrayList<SelectItem> cargasList) {
		this.cargasList = cargasList;
	}

	// METODO REALIZADO ANGEL SARMIENTO
	private void cargarCargas() {
		if (cargasList == null) {
			cargasList = new ArrayList<SelectItem>();
			for (int i=0;i<maxCargas;i++) {
				
				cargasList.add(new SelectItem(i));
			}
		}
		
	}

	public void cambiarProvincia() {
		cargarProvincias();
		cargarCantones();
		if (!cantonList.isEmpty()) {
			solicitante.getDatosPersonales().setCantonId(
					cantonList.get(0).getValue().toString());
		}
	}

	public void cambiarCanton() {
		cargarCantones();
		cargarParroquias();

	}

	public void cargarProvincias() {
		if (provinciaList == null) {
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
		// INC-2272 Pensiones Alimenticias.
		this.ciudadList = null;
		// INC-2148 Refugiados.
		this.cargarCiudadesPensionesAlimenticias();
	}

	private void cargarCantones() {
		if (cantonList == null) {
			cantonList = new ArrayList<SelectItem>();
			if (solicitante != null) {
				List<DivisionPolitica> cantones = divisionPoliticaServicio
						.consultarDivisionPoliticaHijos(solicitante
								.getDatosPersonales().getProvinciaId());
				for (Iterator<DivisionPolitica> iterator = cantones.iterator(); iterator
						.hasNext();) {
					DivisionPolitica canton = iterator.next();
					cantonList.add(new SelectItem(canton.getCoddivpol(), canton
							.getNomdivpol()));
				}

			}

		}
		this.parroquiaList = null;

	}

	private void cargarParroquias() {
		if (parroquiaList == null) {
			parroquiaList = new ArrayList<SelectItem>();
			if (solicitante != null) {
				List<DivisionPolitica> parroquias = divisionPoliticaServicio
						.consultarDivisionPoliticaHijos(solicitante
								.getDatosPersonales().getCantonId());
				for (Iterator<DivisionPolitica> iterator = parroquias
						.iterator(); iterator.hasNext();) {
					DivisionPolitica parroquia = iterator.next();
					parroquiaList.add(new SelectItem(parroquia.getCoddivpol(),
							parroquia.getNomdivpol()));
				}
			}
		}
	}

	public ArrayList<SelectItem> getProvinciaList() {
		if (provinciaList == null) {
			cargarProvincias();
		}
		return provinciaList;
	}

	public void setProvinciaList(ArrayList<SelectItem> provinciaList) {
		this.provinciaList = provinciaList;
	}

	public ArrayList<SelectItem> getCantonList() {
		if (cantonList == null) {
			cargarCantones();
		}
		return cantonList;
	}

	public void setCantonList(ArrayList<SelectItem> cantonList) {
		this.cantonList = cantonList;
	}

	public ArrayList<SelectItem> getParroquiaList() {
		if (parroquiaList == null) {
			cargarParroquias();
		}
		return parroquiaList;
	}

	public void setParroquiaList(ArrayList<SelectItem> parroquiaList) {
		this.parroquiaList = parroquiaList;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
	
	// INC-2272 Pension Alimenticia.
	/**
	 * Obtiene el listado de instituciones financieras.
	 * 
	 */
	public void cargarInstitucionesFinancieras() {

		// INC-2272 Pension Alimenticia.
		if (this.institucionFinancieraList == null) {

			List<InstitucionFinanciera> lista = institucionFinancieraServicio.getIfisOrderByDescripcion();

			institucionFinancieraList = new ArrayList<SelectItem>();

			for (InstitucionFinanciera institucionFinanciera : lista) {
				SelectItem item = new SelectItem(
						institucionFinanciera.getRuc(),
						institucionFinanciera.getDescripcion());
				institucionFinancieraList.add(item);
			}
		}
	}

	

	/**
	 * Obtiene las ciudades de una provincia.
	 */
	public void cargarCiudadesPensionesAlimenticias() {

		// INC-2272 Pension Alimenticia.
		if (ciudadList == null) {
			ciudadList = new ArrayList<SelectItem>();
			if (this.getIdProvinciaPA() != null && this.getIdProvinciaPA().trim().length() > 0) {
				List<DivisionPolitica> cantones = divisionPoliticaServicio
						.consultarDivisionPoliticaHijos(this.getIdProvinciaPA());
				for (Iterator<DivisionPolitica> iterator = cantones.iterator(); iterator
						.hasNext();) {
					DivisionPolitica canton = iterator.next();
					ciudadList.add(new SelectItem(canton.getCoddivpol(), canton
							.getNomdivpol()));
				}

			}

		}
	}
	
	/**
	 * @return the institucionFinancieraList
	 */
	public ArrayList<SelectItem> getInstitucionFinancieraList() {

		// INC-2272 Pension Alimenticia.
		if (institucionFinancieraList == null) {
			this.cargarInstitucionesFinancieras();
		}

		return institucionFinancieraList;
	}

	/**
	 * @param institucionFinancieraList
	 *            the institucionFinancieraList to set
	 */
	public void setInstitucionFinancieraList(
			ArrayList<SelectItem> institucionFinancieraList) {
		this.institucionFinancieraList = institucionFinancieraList;
	}


	/**
	 * @return the ciudadList
	 */
	public ArrayList<SelectItem> getCiudadList() {
		// INC-2272 Pension Alimenticia.
		if (ciudadList == null) {
			this.cargarCiudadesPensionesAlimenticias();
		}
		return ciudadList;
	}

	/**
	 * @param ciudadList
	 *            the ciudadList to set
	 */
	public void setCiudadList(ArrayList<SelectItem> ciudadList) {
		this.ciudadList = ciudadList;
	}

	/**
	 * @return the idProvinciaPA
	 */
	public String getIdProvinciaPA() {
		return idProvinciaPA;
	}

	/**
	 * @param idProvinciaPA the idProvinciaPA to set
	 */
	public void setIdProvinciaPA(String idProvinciaPA) {
		this.idProvinciaPA = idProvinciaPA;
	}

	/**
	 * @return the listaTipoCuenta
	 */
	public ArrayList<SelectItem> getListaTipoCuenta() {
		// if(listaTipoCuenta ==null){
		// this.cargarTiposCuentas();
		// }
		listaTipoCuenta = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("AHO", "Ahorro");
		listaTipoCuenta.add(item);
		item = new SelectItem("COR", "Corriente");
		listaTipoCuenta.add(item);

		return listaTipoCuenta;
	}

	/**
	 * @param listaTipoCuenta the listaTipoCuenta to set
	 */
	public void setListaTipoCuenta(ArrayList<SelectItem> listaTipoCuenta) {
		this.listaTipoCuenta = listaTipoCuenta;
	}
	
	/**
	 * Obtienen los tipos de cuentas.
	 */
	public void cargarTiposCuentas() {
		// INC-2272 Pensiones Alimenticias
		if (listaTipoCuenta == null) {
			List<TipoCuentaBiess> lista = tipoCuentaBiessServicioLocal
					.obtenerTodos();
			listaTipoCuenta = new ArrayList<SelectItem>();
			for (Iterator<TipoCuentaBiess> iterator = lista.iterator(); iterator
					.hasNext();) {
				TipoCuentaBiess tipoCuentaBiess = iterator.next();
				listaTipoCuenta.add(new SelectItem(tipoCuentaBiess.getCodigo(),
						tipoCuentaBiess.getDescripcion()));
			}
		}
		this.cantonList = null;
		// INC-2272 Pensiones Alimenticias.
		this.ciudadList = null;
	}
	
	/**
	 * Obtiene niveles de estudios.
	 */
	private void cargarNivelEstudios() {
		// INC-2300 Requerimiento R24
		if (nivelEstudioList == null) {
			nivelEstudioList = new ArrayList<SelectItem>();
			List<SbsCatalogo> lista;
			try {
				lista = sbsCatalogoServicioLocal.buscarPorCodigoPadre(SbsCatalogoEnum.NIVEL_ESTUDIO.getId());
				if (lista != null && !lista.isEmpty()) {
					for (Iterator<SbsCatalogo> iterator = lista.iterator(); iterator.hasNext();) {
						SbsCatalogo item = iterator.next();
						nivelEstudioList.add(new SelectItem(item.getCodigo(), item.getDescripcion()));
					}
				}
			} catch (SbsCatalogoExcepcion e) {
				nivelEstudioList.clear();
			}
		}
	}

	/**
	 * Obtiene profesiones.
	 */
	private void cargarProfesiones() {
		// INC-2300 Requerimiento R24
		if (profesionList == null) {
			profesionList = new ArrayList<SelectItem>();
			List<SbsCatalogo> lista;
			try {
				lista = sbsCatalogoServicioLocal.buscarPorCodigoPadre(SbsCatalogoEnum.PROFESION.getId());
				if (lista != null && !lista.isEmpty()) {
					for (Iterator<SbsCatalogo> iterator = lista.iterator(); iterator.hasNext();) {
						SbsCatalogo item = iterator.next();
						profesionList.add(new SelectItem(item.getCodigo(), item.getDescripcion()));
					}
				}
			} catch (SbsCatalogoExcepcion e) {
				profesionList.clear();
			}
		}
	}

	/**
	 * Obtiene tipos de viviendas.
	 */
	private void cargarTiposViviendas() {
		// INC-2300 Requerimiento R24
		if (tipoViviendaList == null) {
			tipoViviendaList = new ArrayList<SelectItem>();
			List<SbsCatalogo> lista;
			try {
				lista = sbsCatalogoServicioLocal.buscarPorCodigoPadre(SbsCatalogoEnum.TIPO_VIVIENDA.getId());
				if (lista != null && !lista.isEmpty()) {
					for (Iterator<SbsCatalogo> iterator = lista.iterator(); iterator.hasNext();) {
						SbsCatalogo item = iterator.next();
						tipoViviendaList.add(new SelectItem(item.getCodigo(), item.getDescripcion()));
					}
				}
			} catch (SbsCatalogoExcepcion e) {
				tipoViviendaList.clear();
			}
		}
	}

	/**
	 * Obtiene origenes de ingresos.
	 */
	private void cargarOrigenesIngresos() {
		// INC-2300 Requerimiento R24
		if (origenIngresosList == null) {
			origenIngresosList = new ArrayList<SelectItem>();
			List<SbsCatalogo> lista;
			try {
				lista = sbsCatalogoServicioLocal.buscarPorCodigoPadre(SbsCatalogoEnum.ORIGEN_INGRESOS.getId());
				if (lista != null && !lista.isEmpty()) {
					for (Iterator<SbsCatalogo> iterator = lista.iterator(); iterator.hasNext();) {
						SbsCatalogo item = iterator.next();
						origenIngresosList.add(new SelectItem(item.getCodigo(), item.getDescripcion()));
					}
				}
			} catch (SbsCatalogoExcepcion e) {
				origenIngresosList.clear();
			}
		}
	}

	/**
	 * @return the nivelEstudioList
	 */
	public ArrayList<SelectItem> getNivelEstudioList() {
		// INC-2300 Requerimiento R24
		if (nivelEstudioList == null) {
			this.cargarNivelEstudios();
		}
		return nivelEstudioList;
	}

	/**
	 * @param nivelEstudioList
	 *            the nivelEstudioList to set
	 */
	public void setNivelEstudioList(ArrayList<SelectItem> nivelEstudioList) {
		this.nivelEstudioList = nivelEstudioList;
	}

	/**
	 * @return the profesionList
	 */
	public ArrayList<SelectItem> getProfesionList() {
		// INC-2300 Requerimiento R24
		if (profesionList == null) {
			this.cargarProfesiones();
		}
		return profesionList;
	}

	/**
	 * @param profesionList
	 *            the profesionList to set
	 */
	public void setProfesionList(ArrayList<SelectItem> profesionList) {
		this.profesionList = profesionList;
	}

	/**
	 * @return the tipoViviendaList
	 */
	public ArrayList<SelectItem> getTipoViviendaList() {
		// INC-2300 Requerimiento R24
		if (tipoViviendaList == null) {
			this.cargarTiposViviendas();
		}
		return tipoViviendaList;
	}

	/**
	 * @param tipoViviendaList
	 *            the tipoViviendaList to set
	 */
	public void setTipoViviendaList(ArrayList<SelectItem> tipoViviendaList) {
		this.tipoViviendaList = tipoViviendaList;
	}

	/**
	 * @return the origenIngresosList
	 */
	public ArrayList<SelectItem> getOrigenIngresosList() {
		// INC-2300 Requerimiento R24
		if (origenIngresosList == null) {
			this.cargarOrigenesIngresos();
		}
		return origenIngresosList;
	}

	/**
	 * @param origenIngresosList
	 *            the origenIngresosList to set
	 */
	public void setOrigenIngresosList(ArrayList<SelectItem> origenIngresosList) {
		this.origenIngresosList = origenIngresosList;
	}
}
