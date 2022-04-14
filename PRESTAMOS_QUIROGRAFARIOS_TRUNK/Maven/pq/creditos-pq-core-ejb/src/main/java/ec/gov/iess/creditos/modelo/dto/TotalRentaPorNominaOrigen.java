package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;

import ec.gov.iess.creditos.enumeracion.OrigenJubilado;

/**
 * Clase que almacena el valor total de renta que percibe un jubilado en cada
 * una de las nóminas.
 * 
 * @author jvaca
 * 
 */
public class TotalRentaPorNominaOrigen implements Serializable {

	private static final long serialVersionUID = 6946236618109498824L;
	/**
	 * Representa el origen de la nómina. puede ser HL, UIO, GYE
	 */
	private OrigenJubilado origen;

	/**
	 * Representa el total de la renta que persibe, luego de sumar las rentas de
	 * las prestaciones que percibe un jubilado
	 */
	private double totalRenta;

	public OrigenJubilado getOrigen() {
		return origen;
	}

	public void setOrigen(OrigenJubilado origen) {
		this.origen = origen;
	}

	public double getTotalRenta() {
		return totalRenta;
	}

	public void setTotalRenta(double totalRenta) {
		this.totalRenta = Math.round(totalRenta);
	}
}
