package ec.gov.iess.pq.concesion.simulador.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.richfaces.component.html.HtmlTab;

import ec.fin.biess.pq.simulacion.dto.ParametrosCalculoCreditoResponseDto;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.dto.Precalificacion;
import ec.gov.iess.creditos.modelo.dto.Requisito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.pq.concesion.web.bean.DatosBean;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * Variable de sesion utilizadas para el simulador de prestamos quirografarios
 * 
 * @author hugo.mora
 * @date 2017/06/05
 *
 */
public class SimuladorPqSesionBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private DatosBean datos;

	private boolean calculoCredito;

	private boolean calculoCreditoNovacion;

	private ParametrosCalculoCreditoResponseDto parametrosSimulacion;

	private List<Prestamo> prestamosNovar;

	private Precalificacion precalificacionNuevo;

	private Precalificacion precalificacionNovacionNuevo;

	private Prestamo prestamoNovacionSeleccionado;

	private boolean cargaInicial;

	private boolean cargado;

	private boolean cargadoNovacion;

	private ParametrosCalculoCreditoResponseDto parametrosSimulacionNovacion;

	private boolean mostrarPanelSimulacion = false;

	private boolean mostrarPaneles = true;

	private boolean mostrarPanelesNovacion = true;

	private List<DividendoCalculo> tablaAmortizacion;

	private List<DividendoCalculo> tablaAmortizacionNovacion;

	private boolean mostrarRequisitosNoBloqueantes;

	private boolean mostrarRequisitosNoBloqueantesNovacion;

	private List<Requisito> listaRequisitosNoBloqueantes = new ArrayList<Requisito>();

	private List<Requisito> listaRequisitosNoBloqueantesNovacion = new ArrayList<Requisito>();

	private HtmlTab htmlTabAmo = new HtmlTab();// pestania amortizacion

	private boolean tieneNovacionesAnioFiscal;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;

	// Getters and setters
	public boolean isCalculoCredito() {
		return calculoCredito;
	}

	public void setCalculoCredito(boolean calculoCredito) {
		this.calculoCredito = calculoCredito;
	}

	public ParametrosCalculoCreditoResponseDto getParametrosSimulacion() {
		return parametrosSimulacion;
	}

	public void setParametrosSimulacion(ParametrosCalculoCreditoResponseDto parametrosSimulacion) {
		this.parametrosSimulacion = parametrosSimulacion;
	}

	public List<Prestamo> getPrestamosNovar() {
		if (this.prestamosNovar == null) {
			this.prestamosNovar = this.prestamoServicio
					.obtienePrestamosVigentesNovacionSimulador(this.datos.getSolicitante().getDatosPersonales().getCedula(), this.datos.getTipo());
		}
		return prestamosNovar;
	}

	public void setPrestamosNovar(List<Prestamo> prestamosNovar) {
		this.prestamosNovar = prestamosNovar;
	}

	public DatosBean getDatos() {
		return datos;
	}

	public void setDatos(DatosBean datos) {
		this.datos = datos;
	}

	public Precalificacion getPrecalificacionNuevo() {
		return precalificacionNuevo;
	}

	public void setPrecalificacionNuevo(Precalificacion precalificacionNuevo) {
		this.precalificacionNuevo = precalificacionNuevo;
	}

	public Prestamo getPrestamoNovacionSeleccionado() {
		return prestamoNovacionSeleccionado;
	}

	public void setPrestamoNovacionSeleccionado(Prestamo prestamoNovacionSeleccionado) {
		this.prestamoNovacionSeleccionado = prestamoNovacionSeleccionado;
	}

	public boolean isCargaInicial() {
		return cargaInicial;
	}

	public void setCargaInicial(boolean cargaInicial) {
		this.cargaInicial = cargaInicial;
	}

	public boolean isCargado() {
		return cargado;
	}

	public void setCargado(boolean cargado) {
		this.cargado = cargado;
	}

	public Precalificacion getPrecalificacionNovacionNuevo() {
		return precalificacionNovacionNuevo;
	}

	public void setPrecalificacionNovacionNuevo(Precalificacion precalificacionNovacionNuevo) {
		this.precalificacionNovacionNuevo = precalificacionNovacionNuevo;
	}

	public boolean isCargadoNovacion() {
		return cargadoNovacion;
	}

	public void setCargadoNovacion(boolean cargadoNovacion) {
		this.cargadoNovacion = cargadoNovacion;
	}

	public ParametrosCalculoCreditoResponseDto getParametrosSimulacionNovacion() {
		return parametrosSimulacionNovacion;
	}

	public void setParametrosSimulacionNovacion(ParametrosCalculoCreditoResponseDto parametrosSimulacionNovacion) {
		this.parametrosSimulacionNovacion = parametrosSimulacionNovacion;
	}

	public boolean isCalculoCreditoNovacion() {
		return calculoCreditoNovacion;
	}

	public void setCalculoCreditoNovacion(boolean calculoCreditoNovacion) {
		this.calculoCreditoNovacion = calculoCreditoNovacion;
	}

	public boolean isMostrarPanelSimulacion() {
		return mostrarPanelSimulacion;
	}

	public void setMostrarPanelSimulacion(boolean mostrarPanelSimulacion) {
		this.mostrarPanelSimulacion = mostrarPanelSimulacion;
	}

	public boolean isMostrarPaneles() {
		return mostrarPaneles;
	}

	public void setMostrarPaneles(boolean mostrarPaneles) {
		this.mostrarPaneles = mostrarPaneles;
	}

	public boolean isMostrarPanelesNovacion() {
		return mostrarPanelesNovacion;
	}

	public void setMostrarPanelesNovacion(boolean mostrarPanelesNovacion) {
		this.mostrarPanelesNovacion = mostrarPanelesNovacion;
	}

	public List<DividendoCalculo> getTablaAmortizacion() {
		return tablaAmortizacion;
	}

	public void setTablaAmortizacion(List<DividendoCalculo> tablaAmortizacion) {
		this.tablaAmortizacion = tablaAmortizacion;
	}

	public List<DividendoCalculo> getTablaAmortizacionNovacion() {
		return tablaAmortizacionNovacion;
	}

	public void setTablaAmortizacionNovacion(List<DividendoCalculo> tablaAmortizacionNovacion) {
		this.tablaAmortizacionNovacion = tablaAmortizacionNovacion;
	}

	public boolean isMostrarRequisitosNoBloqueantes() {
		return mostrarRequisitosNoBloqueantes;
	}

	public void setMostrarRequisitosNoBloqueantes(boolean mostrarRequisitosNoBloqueantes) {
		this.mostrarRequisitosNoBloqueantes = mostrarRequisitosNoBloqueantes;
	}

	public boolean isMostrarRequisitosNoBloqueantesNovacion() {
		return mostrarRequisitosNoBloqueantesNovacion;
	}

	public void setMostrarRequisitosNoBloqueantesNovacion(boolean mostrarRequisitosNoBloqueantesNovacion) {
		this.mostrarRequisitosNoBloqueantesNovacion = mostrarRequisitosNoBloqueantesNovacion;
	}

	public List<Requisito> getListaRequisitosNoBloqueantes() {
		return listaRequisitosNoBloqueantes;
	}

	public void setListaRequisitosNoBloqueantes(List<Requisito> listaRequisitosNoBloqueantes) {
		this.listaRequisitosNoBloqueantes = listaRequisitosNoBloqueantes;
	}

	public List<Requisito> getListaRequisitosNoBloqueantesNovacion() {
		return listaRequisitosNoBloqueantesNovacion;
	}

	public void setListaRequisitosNoBloqueantesNovacion(List<Requisito> listaRequisitosNoBloqueantesNovacion) {
		this.listaRequisitosNoBloqueantesNovacion = listaRequisitosNoBloqueantesNovacion;
	}

	public HtmlTab getHtmlTabAmo() {
		return htmlTabAmo;
	}

	public void setHtmlTabAmo(HtmlTab htmlTabAmo) {
		this.htmlTabAmo = htmlTabAmo;
	}

	public boolean isTieneNovacionesAnioFiscal() {
		return tieneNovacionesAnioFiscal;
	}

	public void setTieneNovacionesAnioFiscal(boolean tieneNovacionesAnioFiscal) {
		this.tieneNovacionesAnioFiscal = tieneNovacionesAnioFiscal;
	}

}