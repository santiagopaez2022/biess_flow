/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.richfaces.component.html.HtmlDataTable;

import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.EstadoOrdenCompra;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.planillaspq.servicio.BusquedasServicioLocal;
import ec.gov.iess.planillaspq.servicio.OrdenCompraServicio;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * <b> Consultas sobre ordenes de compra de PQ para computadores. </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.1.2.3 $
 *          <p>
 *          [$Author: geguiguren $, $Date: 2011/05/30 16:03:30 $]
 *          </p>
 */
public class ConsultaOrdenesBacking extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = -8055665324278439817L;
	private static final LoggerBiess log = LoggerBiess.getLogger(ConsultaOrdenesBacking.class);

	@EJB(name = "OrdenCompraServicioImpl/local")
	private OrdenCompraServicio ordenServicio;
	
	@EJB(name = "BusquedasServicioImpl/local")
	private  BusquedasServicioLocal busquedaServicio;

	private static final String CATALOGO_ORDEN = "ESTORD";

	private String error = null;
	private String mensaje = null;

	private Date fechaConsultaDesde = new Date();
	private Date fechaConsultaHasta = new Date();
	private Date fechaImpresion = null;
	private String estadoOrden = null;

	private HtmlDataTable tablaOrdenesPorAprobar;
	private HtmlDataTable tablaOrdenesImprimir;
	
	private BigDecimal totalOrdenes;
	private BigDecimal totalSeguros;
	private BigDecimal totalPrestamos;

	private OrdenCompra item;

	private List<OrdenCompra> listaOrdenes = null;

	private boolean ordenesPresentes = false;


	/**
	 * @param inicializar
	 *            the inicializar to set
	 */
	public void setInicializar(String inicializar) {
		error = null;
		setearNull();
	}

	/**
	 * <b> Busca si existen ordenes entregados para una fecha de consulta </b>
	 * <p>
	 * [Author: geguiguren, Date: 23/11/2010]
	 * </p>
	 * 
	 */
	public String obtenerOrdenesFecha() {

		listaOrdenes = null;
		error = null;
		
		totalOrdenes = BigDecimal.ZERO;
		totalSeguros = BigDecimal.ZERO;
		totalPrestamos = BigDecimal.ZERO;
		
		try {
			if (fechaConsultaDesde.after(fechaConsultaHasta)) {
				error = "La fecha a consultar hasta debe ser menor a la fecha desde";
				return null;
			}

			if (estadoOrden.equals("TODOS")) {
				listaOrdenes = ordenServicio.consultarOrdenesPorFecha(CATALOGO_ORDEN, EstadoOrdenCompra.Aprobado.getDescripcion(),
						fechaSinHora(fechaConsultaDesde, 1), fechaSinHora(fechaConsultaHasta, 0));
				listaOrdenes.addAll(ordenServicio.consultarOrdenesPorFecha(CATALOGO_ORDEN, EstadoOrdenCompra.Rechazado.getDescripcion(),
						fechaSinHora(fechaConsultaDesde, 1), fechaSinHora(fechaConsultaHasta, 0)));
			} else {
				listaOrdenes = ordenServicio.consultarOrdenesPorFecha(CATALOGO_ORDEN, estadoOrden,
						fechaSinHora(fechaConsultaDesde, 1), fechaSinHora(fechaConsultaHasta, 0));
			}
			log.info("tama\u00F1o lista: " + listaOrdenes.size());
			if (listaOrdenes.isEmpty()) {
				error = "No existen ordenes para el c\u00F3digo o per\u00EDodo seleccionado";
				return null;
			}
			
			// lleno los nombres del afiliado
			Double totalOrd = new Double(0);
			Double totalPre = new Double(0);
			Double totalSeg = new Double(0);
			for (OrdenCompra orden : listaOrdenes) {
				orden.setNombres(busquedaServicio.buscarAfiliadoPorId(orden.getPrestamo().getNumafi()));
				
				totalPre += orden.getPrestamo().getMntpre();
				totalPrestamos = new BigDecimal(totalPre).setScale(2, RoundingMode.HALF_UP);
				
				totalSeg += orden.getPrestamo().getValsegsal();
				totalSeguros = new BigDecimal(totalSeg).setScale(2, RoundingMode.HALF_UP);
				
				totalOrd += orden.getOcValor();
				totalOrdenes = new BigDecimal(totalOrd).setScale(2, RoundingMode.HALF_UP);
			}
			ordenesPresentes = true;
		} catch (Exception e) {
			log.error(e);
		}
		return "";
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
		return true;
	}

	
	/**
	 * <b>
	 * Redirecciona hacia la pagina de impresion de la consulta.
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 23/05/2011]</p>
	 *
	 * @return
	 */ 
	public String imprimirConsulta(){
		
		fechaImpresion = new Date();
		tablaOrdenesImprimir = null;
		return "imprimirConsulta";
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

	/**
	 * @return the estadoOrden
	 */
	public String getEstadoOrden() {
		return estadoOrden;
	}

	/**
	 * @param estadoOrden
	 *            the estadoOrden to set
	 */
	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	/**
	 * @return the listaOrdenes
	 */
	public List<OrdenCompra> getListaOrdenes() {
		return listaOrdenes;
	}

	/**
	 * @param listaOrdenes the listaOrdenes to set
	 */
	public void setListaOrdenes(List<OrdenCompra> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

	/**
	 * @return the fechaImpresion
	 */
	public Date getFechaImpresion() {
		return fechaImpresion;
	}

	/**
	 * @param fechaImpresion the fechaImpresion to set
	 */
	public void setFechaImpresion(Date fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	/**
	 * @return the tablaOrdenesImprimir
	 */
	public HtmlDataTable getTablaOrdenesImprimir() {
		return tablaOrdenesImprimir;
	}

	/**
	 * @param tablaOrdenesImprimir the tablaOrdenesImprimir to set
	 */
	public void setTablaOrdenesImprimir(HtmlDataTable tablaOrdenesImprimir) {
		this.tablaOrdenesImprimir = tablaOrdenesImprimir;
	}

	/**
	 * @return the totalOrdenes
	 */
	public BigDecimal getTotalOrdenes() {
		return totalOrdenes;
	}

	/**
	 * @param totalOrdenes the totalOrdenes to set
	 */
	public void setTotalOrdenes(BigDecimal totalOrdenes) {
		this.totalOrdenes = totalOrdenes;
	}

	/**
	 * @return the totalSeguros
	 */
	public BigDecimal getTotalSeguros() {
		return totalSeguros;
	}

	/**
	 * @param totalSeguros the totalSeguros to set
	 */
	public void setTotalSeguros(BigDecimal totalSeguros) {
		this.totalSeguros = totalSeguros;
	}

	/**
	 * @return the totalPrestamos
	 */
	public BigDecimal getTotalPrestamos() {
		return totalPrestamos;
	}

	/**
	 * @param totalPrestamos the totalPrestamos to set
	 */
	public void setTotalPrestamos(BigDecimal totalPrestamos) {
		this.totalPrestamos = totalPrestamos;
	}
}
