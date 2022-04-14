package ec.gov.iess.pq.concesion.simulador.backing;

import java.io.Serializable;

import ec.gov.iess.pq.concesion.web.util.BaseBean;

/**
 * Bean de request para inicializar paneles del simulador
 * 
 * @author hugo.mora
 *
 */
public class SimuladorPqRequestBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String seleccionTramiteComoDiscapacitado = "NO";

	private SimuladorPqSesionBacking simuladorPqSesion;

	public String getSeleccionTramiteComoDiscapacitado() {
		this.simuladorPqSesion.setMostrarPanelSimulacion(false);
		if (this.simuladorPqSesion.getTablaAmortizacion() != null) {
			this.simuladorPqSesion.getTablaAmortizacion().clear();
		}
		if (this.simuladorPqSesion.getTablaAmortizacionNovacion() != null) {
			this.simuladorPqSesion.getTablaAmortizacionNovacion().clear();
		}
		this.simuladorPqSesion.setCalculoCredito(false);
		this.simuladorPqSesion.setCalculoCreditoNovacion(false);
		return seleccionTramiteComoDiscapacitado;
	}

	public void setSeleccionTramiteComoDiscapacitado(String seleccionTramiteComoDiscapacitado) {
		this.seleccionTramiteComoDiscapacitado = seleccionTramiteComoDiscapacitado;
	}

	public SimuladorPqSesionBacking getSimuladorPqSesion() {
		return simuladorPqSesion;
	}

	public void setSimuladorPqSesion(SimuladorPqSesionBacking simuladorPqSesion) {
		this.simuladorPqSesion = simuladorPqSesion;
	}

}
