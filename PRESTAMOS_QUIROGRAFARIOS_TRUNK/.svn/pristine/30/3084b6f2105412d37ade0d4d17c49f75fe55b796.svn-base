/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.richfaces.component.html.HtmlDataTable;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.EstadoOrdenCompra;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.planillaspq.servicio.BusquedasServicioLocal;
import ec.gov.iess.planillaspq.servicio.OrdenCompraServicio;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * <b> Procesa el listado de ordenes de compra de PQ para computadores. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.1.2.3 $
 *          <p>
 *          [$Author: geguiguren $, $Date: 2011/05/30 16:03:30 $]
 *          </p>
 */
public class AprobacionOrdenesBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = -588867541389631522L;

	private static final LoggerBiess log = LoggerBiess.getLogger(AprobacionOrdenesBacking.class);

	@EJB(name = "OrdenCompraServicioImpl/local")
	private OrdenCompraServicio ordenServicio;
	
	@EJB(name = "BusquedasServicioImpl/local")
	private  BusquedasServicioLocal busquedaServicio;
	
	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	private  AdministracionOrdenCompraProveedorServicio administracionOrdenServicio;

	private static final String CATALOGO_ORDEN = "ESTORD";

	private String error = null;
	private String mensaje = null;

	private Date fechaConsultaDesde = null;
	private Date fechaConsultaHasta = null;
	private String numeroOrden = null;

	private HtmlDataTable tablaOrdenesPorAprobar;

	private OrdenCompra item;
	private OrdenCompra detalleOrden;

	private List<OrdenCompra> listaOrdenesPorAprobar = null;

	private boolean ordenesPresentes = false;
	private boolean ordenActualizada = false;

	/**
	 * @param inicializar
	 *            the inicializar to set
	 */
	public void setInicializar(String inicializar) {
		error = null;
		obtenerOrdenes();
	}
	
	/**
	 * <b> Busca si existen ORDENES en estado ENTREGADA. </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 17/05/2011]
	 * </p>
	 * 
	 * @return
	 */
	public String obtenerOrdenes() {
		setearNull();

		try {
			listaOrdenesPorAprobar = ordenServicio.consultarOrdenes(CATALOGO_ORDEN, EstadoOrdenCompra.Entregado.getDescripcion());

			if (listaOrdenesPorAprobar.isEmpty()) {
				this.ordenesPresentes = false;
				error = "No existen solicitudes en estado ENTREGADO";
			} else {
				this.ordenesPresentes = true;
			}
			
			// lleno los nombres del afiliado
			for (OrdenCompra orden : listaOrdenesPorAprobar) {
				orden.setNombres(busquedaServicio.buscarAfiliadoPorId(orden.getPrestamo().getNumafi()));
			}
		} catch (Exception e) {
			log.error(e);
		}
		return "";
	}

	/**
	 * <b> Busca si existen ordenes entregados para una fecha de consulta </b>
	 * <p>
	 * [Author: geguiguren, Date: 23/11/2010]
	 * </p>
	 * 
	 */
	public String obtenerOrdenesFecha() {

		listaOrdenesPorAprobar = null;
		error = null;
		detalleOrden = null;

		try {

			if (numeroOrden.length() > 0) {
				listaOrdenesPorAprobar = new ArrayList<OrdenCompra>();
				OrdenCompra o = ordenServicio.consultarOrdenPorCodigo(numeroOrden);
				listaOrdenesPorAprobar.add(o);
			} else {

				if (fechaConsultaDesde.after(fechaConsultaHasta)) {
					error = "La fecha a consultar hasta debe ser menor a la fecha desde";
					return null;
				}

				listaOrdenesPorAprobar = ordenServicio.consultarOrdenesPorFecha(CATALOGO_ORDEN, EstadoOrdenCompra.Entregado.getDescripcion(),
						fechaSinHora(fechaConsultaDesde,1), fechaSinHora(fechaConsultaHasta,0));

			}
			log.info("tamaño lista: " + listaOrdenesPorAprobar.size());

			if (listaOrdenesPorAprobar.isEmpty()) {
				error = "No existen ordenes para el código o período seleccionado";
				return null;
			}
			
			// lleno los nombres del afiliado
			for (OrdenCompra orden : listaOrdenesPorAprobar) {
				orden.setNombres(busquedaServicio.buscarAfiliadoPorId(orden.getPrestamo().getNumafi()));
			}

		} catch (Exception e) {
			log.error(e);
		}
		return "";
	}

	/**
	 * <b> Trae los detalles de la Orden </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 29/11/2010]
	 * </p>
	 * 
	 * @return
	 */
	public String verDetalle() {

		detalleOrden = null;

		try {
			if (tablaOrdenesPorAprobar.isRowAvailable()) {
				detalleOrden = (OrdenCompra) tablaOrdenesPorAprobar.getRowData();
			}
		} catch (Exception e) {
			log.error("Error verDetalle(): ", e);
		}
		return null;
	}

	/**
	 * <b> Cambia el estado de una Orden de 'ENT' a 'APR' </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 19/05/2011]
	 * </p>
	 * 
	 * @return
	 */
	public String aprobarOrden() {
		try {

			if (tablaOrdenesPorAprobar.isRowAvailable()) {
				item = (OrdenCompra) tablaOrdenesPorAprobar.getRowData();

				if (EstadoOrdenCompra.Entregado.getDescripcion().equals(item.getDetalleCatalogo().getId().getDcCodigo())) {
					
					//ordenServicio.actualizarOrdenEstado(item, EstadoOrdenCompra.Aprobado.getDescripcion());
					
					//TODO Responsable CBASTIDAS: Realizar re-calculo de tabla de amortizacion y actualizacion prestamo
					//administracionOrdenServicio.recalculaTablaAmortizacionOrdenCompra(item.getOcCodOrdCompra(), item.getFecConfirmacion());
					administracionOrdenServicio.recalculaTablaAmortizacionOrdenCompra(item.getOcCodOrdCompra(), new Date());
					
				} else {
					error = "Solo puede cambiar los saldos en estado ENT";
				}
			}
		} catch (NoServicioPrestadoSucursalException e) {
			error = "Error al aprobar orden: " + e.getMessage();
			log.error("Error en aprobarOrden(): ", e);
		} catch (Exception e) {
			error = "Error al aprobar orden: " + e.getMessage();
			log.error("Error en aprobarOrden(): ", e);
		}
		
		// refresco el listado
		listaOrdenesPorAprobar = null;
		if (numeroOrden != null || fechaConsultaDesde != null || fechaConsultaHasta != null) {
			
			obtenerOrdenesFecha();
		} else {
			obtenerOrdenes();
		}
		return null;
	}

	/**
	 * <b> Cambia el estado de una Orden de 'ENT' a 'REC' </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 19/05/2011]
	 * </p>
	 * 
	 * @return
	 */
	public String rechazarSaldo() {

		try {
			if (tablaOrdenesPorAprobar.isRowAvailable()) {
				item = (OrdenCompra) tablaOrdenesPorAprobar.getRowData();

				if (EstadoOrdenCompra.Entregado.getDescripcion().equals(item.getDetalleCatalogo().getId().getDcCodigo())) {
					item.setOcObservacion(item.getOcObservacion() + " Rechazado por: " + item.getObservacionRechazo());
					ordenServicio.actualizarOrdenEstado(item, EstadoOrdenCompra.Rechazado.getDescripcion());
				} else {
					error = "Solo puede cambiar los saldos en estado ENT";
				}
			}
		} catch (Exception e) {
			log.error("Error al aprobarSaldo(): ", e);
		}
		setearNull();
		// refresco el listado
		if (numeroOrden != null || fechaConsultaDesde != null || fechaConsultaHasta != null) {
			obtenerOrdenesFecha();
		} else {
			obtenerOrdenes();
		}
		return null;
	}

	/**
	 * <b> Obtiene la fecha actual eliminando la hora </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 01/12/2010]
	 * </p>
	 * 
	 * @return
	 */
	private Date fechaSinHora(Date fecha, int inicio) {

		final Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);

		if (inicio == 1) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else {
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
		}
		return cal.getTime();
	}

	/**
	 * <b> Setea algunas variables del Bean </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 01/12/2010]
	 * </p>
	 * 
	 * @return
	 */
	public boolean setearNull() {
		error = null;
		mensaje = null;
		ordenesPresentes = false;
		ordenActualizada = false;
		detalleOrden = null;
		
		return true;
	}

	/**
	 * @return the ordenServicio
	 */
	public OrdenCompraServicio getOrdenServicio() {
		return ordenServicio;
	}

	/**
	 * @param ordenServicio
	 *            the ordenServicio to set
	 */
	public void setOrdenServicio(OrdenCompraServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
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
	 * @return the fechaConsultaDesde
	 */
	public Date getFechaConsultaDesde() {
		return fechaConsultaDesde;
	}

	/**
	 * @param fechaConsultaDesde
	 *            the fechaConsultaDesde to set
	 */
	public void setFechaConsultaDesde(Date fechaConsultaDesde) {
		this.fechaConsultaDesde = fechaConsultaDesde;
	}

	/**
	 * @return the fechaConsultaHasta
	 */
	public Date getFechaConsultaHasta() {
		return fechaConsultaHasta;
	}

	/**
	 * @param fechaConsultaHasta
	 *            the fechaConsultaHasta to set
	 */
	public void setFechaConsultaHasta(Date fechaConsultaHasta) {
		this.fechaConsultaHasta = fechaConsultaHasta;
	}

	/**
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden
	 *            the numeroOrden to set
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the tablaOrdenesPorAprobar
	 */
	public HtmlDataTable getTablaOrdenesPorAprobar() {
		return tablaOrdenesPorAprobar;
	}

	/**
	 * @param tablaOrdenesPorAprobar
	 *            the tablaOrdenesPorAprobar to set
	 */
	public void setTablaOrdenesPorAprobar(HtmlDataTable tablaOrdenesPorAprobar) {
		this.tablaOrdenesPorAprobar = tablaOrdenesPorAprobar;
	}

	/**
	 * @return the detalleOrden
	 */
	public OrdenCompra getDetalleOrden() {
		return detalleOrden;
	}

	/**
	 * @param detalleOrden
	 *            the detalleOrden to set
	 */
	public void setDetalleOrden(OrdenCompra detalleOrden) {
		this.detalleOrden = detalleOrden;
	}

	/**
	 * @return the listaOrdenesPorAprobar
	 */
	public List<OrdenCompra> getListaOrdenesPorAprobar() {
		return listaOrdenesPorAprobar;
	}

	/**
	 * @param listaOrdenesPorAprobar
	 *            the listaOrdenesPorAprobar to set
	 */
	public void setListaOrdenesPorAprobar(List<OrdenCompra> listaOrdenesPorAprobar) {
		this.listaOrdenesPorAprobar = listaOrdenesPorAprobar;
	}

	/**
	 * @return the ordenesPresentes
	 */
	public boolean isOrdenesPresentes() {
		return ordenesPresentes;
	}

	/**
	 * @param ordenesPresentes
	 *            the ordenesPresentes to set
	 */
	public void setOrdenesPresentes(boolean ordenesPresentes) {
		this.ordenesPresentes = ordenesPresentes;
	}

	/**
	 * @return the ordenActualizada
	 */
	public boolean isOrdenActualizada() {
		return ordenActualizada;
	}

	/**
	 * @param ordenActualizada
	 *            the ordenActualizada to set
	 */
	public void setOrdenActualizada(boolean ordenActualizada) {
		this.ordenActualizada = ordenActualizada;
	}

	/**
	 * @return the item
	 */
	public OrdenCompra getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(OrdenCompra item) {
		this.item = item;
	}
	
}
