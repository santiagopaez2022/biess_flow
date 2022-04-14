package ec.gov.iess.pq.concesion.focalizados.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.pq.excepcion.PrestamosFocalizadosException;
import ec.gov.iess.creditos.pq.servicio.CatalogoFocalizadosServicioLocal;
import ec.gov.iess.pq.concesion.focalizados.enumeration.TipoProductoFocalizadoEnum;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;
import ec.gov.iess.pq.concesion.web.util.Utilities;

/**
 * Backing para seleccion de ollas de inducción para pq focalizados
 * 
 * @author hugo.mora
 * @date 2016/11/08
 *
 */
public class OllasFocalizadosBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient static final LoggerBiess LOG = LoggerBiess.getLogger(OllasFocalizadosBacking.class);

	@EJB(name = "CatalogoFocalizadosServicioImpl/local")
	private CatalogoFocalizadosServicioLocal catalogoFocalizadosServicio;

	private PqFocalizadosBacking pqFocalizados;

	private List<SelectItem> listaOllasItems = new ArrayList<SelectItem>();

	private Integer ollaSeleccionada;

	private String observacionOllas;

	private ProductoDto ollaInformacion;

	private DatosBean datos;
	
	private String codigoEstablecimiento = "";

	/**
	 * Busca cocinas dado el establecimiento y el punto de venta
	 * 
	 * @return
	 */
	public void buscarOllasPorEstablecimientoYPV() {
		this.ollaSeleccionada = null;
		this.observacionOllas = null;
		this.ollaInformacion = null;
		this.listaOllasItems.clear();

		try {
			SelectItem item = null;
			this.listaOllasItems.clear();
			item = new SelectItem(null, "Seleccione");
			this.listaOllasItems.add(item);

			List<ProductoDto> listaProductos = null;
			
			if (this.pqFocalizados.isBuscarPorCodigo()) {
				listaProductos = this.catalogoFocalizadosServicio.consultarProductoPorEstablecimientoTipo(
						Integer.valueOf(this.pqFocalizados.getCodigoProveedorMeer()), TipoProductoFocalizadoEnum.OLLAS.getCodigo());
				codigoEstablecimiento = this.pqFocalizados.getCodigoProveedorMeer();
			} else {
				listaProductos = this.pqFocalizados.buscarProductoPorEstablecimientoYPV(TipoProductoFocalizadoEnum.OLLAS.getCodigo(),
						this.pqFocalizados.getEstablecimientoSeleccionado(), this.pqFocalizados.getPuntoVentaSeleccionado());
				codigoEstablecimiento = String.valueOf(this.pqFocalizados.getEstablecimientoSeleccionado());
			}			
			
			if (listaProductos != null && !listaProductos.isEmpty()) {
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

				String esCocinaGobierno = this.pqFocalizados.getCocinaInformacion().getGobierno();
				for (ProductoDto olla : listaProductos) {
					if ("NO".equals(esCocinaGobierno) && "SI".equals(olla.getGobierno())) {
						continue;
					} else if ("SI".equals(esCocinaGobierno) && "NO".equals(olla.getGobierno())) {
						continue;
					}
					String nombreCocina = olla.getNombreMostrar();
					
					String codigoOlla = "(" + this.codigoEstablecimiento + "-" + olla.getCodigoMEER() + ") ";
					item = new SelectItem(olla.getCodigoMEER(), nombreCocina.length() >= 50 ? codigoOlla + nombreCocina.substring(0, 50) : codigoOlla + nombreCocina);
					this.listaOllasItems.add(item);
				}
			}

			// Si tiene un registro a lista es porque no tiene ollas, ya que el primer elemento es la opcion Seleccuione
			if (this.listaOllasItems.size() == 1) {
				this.observacionOllas = getResource("messages", "mensaje.pq.focalizado.no.registros");
			}

		} catch (PrestamosFocalizadosException e) {
			LOG.info("1. Se presento una excepcion al setear informacion financiera del proveedor ", e);
			this.observacionOllas = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
		} catch (Exception e) {
			LOG.error("2. -Se presento una excepcion consultar cocinas por establecimiento y punto de venta ", e);
			this.observacionOllas = getResource("messages", "mensaje.pq.focalizado.error.ollas.establecimiento");
		}
	}

	/**
	 * Consulta informacion de olla de induccion dado el codigo meer
	 * 
	 * @return
	 */
	public String consultarOllaPorCodigoMeer() {
		this.observacionOllas = null;
		if (this.ollaSeleccionada == null || this.ollaSeleccionada == 0L) {
			this.ollaInformacion = null;
		} else {

			try {
				this.ollaInformacion = this.catalogoFocalizadosServicio.consultarProductoPorCodigoMeer(this.ollaSeleccionada);
				String codigoOlla = "(" + this.codigoEstablecimiento + "-" + ollaInformacion.getCodigoMEER() + ") ";
				String descripcionOlla = codigoOlla +  this.ollaInformacion.getDescripcion();
				this.ollaInformacion.setDescripcion(descripcionOlla);
			} catch (PrestamosFocalizadosException e) {
				LOG.info("1. Se presento una excepcion al consultar un producto por codigo meer : " + this.ollaSeleccionada + " ", e);
				this.observacionOllas = Utilities.reemplazarStringHastaCaracter(e.getMessage(), ":", "");
			} catch (Exception e) {
				LOG.error("2. Se presento una excepcion al consultar un producto por codigo meer : " + this.ollaSeleccionada + " ", e);
				this.observacionOllas = getResource("messages", "mensaje.pq.focalizado.olla.codigo");
			}
		}
		return "";
	}
	
	/**
	 * Regresa desde catalogo de ollas
	 * 
	 * @return
	 */
	public String regresarDesdeOllas() {
		this.pqFocalizados.setCocinaSeleccionada(null);
		
		if (!this.pqFocalizados.isBuscarPorCodigo()) {
			this.pqFocalizados.setCocinaInformacion(null);
		}
		
		this.ollaSeleccionada = null;
		this.ollaInformacion = null;

		this.pqFocalizados.setAgregarOllas(false);
		if (this.pqFocalizados.isBuscarPorCodigo() && this.pqFocalizados.getCocinaInformacion() != null
				&& "SI".equals(this.pqFocalizados.getCocinaInformacion().getGobierno())) {
			this.pqFocalizados.setAgregarOllas(true);
		}

		return "regresar";
	}

	// Getters and setters
	public PqFocalizadosBacking getPqFocalizados() {
		return pqFocalizados;
	}

	public void setPqFocalizados(PqFocalizadosBacking pqFocalizados) {
		this.pqFocalizados = pqFocalizados;
	}

	public List<SelectItem> getListaOllasItems() {
		return listaOllasItems;
	}

	public void setListaOllasItems(List<SelectItem> listaOllasItems) {
		this.listaOllasItems = listaOllasItems;
	}

	public Integer getOllaSeleccionada() {
		return ollaSeleccionada;
	}

	public void setOllaSeleccionada(Integer ollaSeleccionada) {
		this.ollaSeleccionada = ollaSeleccionada;
	}

	public String getObservacionOllas() {
		return observacionOllas;
	}

	public void setObservacionOllas(String observacionOllas) {
		this.observacionOllas = observacionOllas;
	}

	public ProductoDto getOllaInformacion() {
		return ollaInformacion;
	}

	public void setOllaInformacion(ProductoDto ollaInformacion) {
		this.ollaInformacion = ollaInformacion;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

}