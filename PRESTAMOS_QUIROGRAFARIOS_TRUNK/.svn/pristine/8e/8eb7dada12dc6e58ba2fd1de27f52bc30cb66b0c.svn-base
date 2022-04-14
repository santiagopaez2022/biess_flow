/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * @author cvillarreal
 * 
 */
@SuppressWarnings("serial")
public class Simulacion implements Serializable {

	private BigDecimal montoMaximoCredito = new BigDecimal(0);
	private int plazoMaximoCredito;
	private int plazoCredito;
	private BigDecimal cuotaMaximaComprometer = new BigDecimal(0);

	private BigDecimal cuotaCredito = new BigDecimal(0);
	private BigDecimal montoCredito = new BigDecimal(0);

	private String observacion;
	private boolean calculoCredito;

	private String tipoSimulacion;
	
	private BigDecimal tasaInteres;

	public Simulacion() {

	}

	/**
	 * @return the montoMaximoCredito
	 */
	public BigDecimal getMontoMaximoCredito() {
		return montoMaximoCredito;
	}

	/**
	 * @return the plazoMaximoCredito
	 */
	public int getPlazoMaximoCredito() {
		return plazoMaximoCredito;
	}

	/**
	 * @return the plazoCredito
	 */
	public int getPlazoCredito() {
		return plazoCredito;
	}

	/**
	 * @return the cuotaMaximaComprometer
	 */
	public BigDecimal getCuotaMaximaComprometer() {
		return cuotaMaximaComprometer;
	}

	/**
	 * @return the cuotaCredito
	 */
	public BigDecimal getCuotaCredito() {
		return cuotaCredito;
	}

	/**
	 * @return the montoCredito
	 */
	public BigDecimal getMontoCredito() {
		return montoCredito;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @return the calculoCredito
	 */
	public boolean getCalculoCredito() {
		return calculoCredito;
	}

	/**
	 * @param montoMaximoCredito
	 *            the montoMaximoCredito to set
	 */
	public void setMontoMaximoCredito(BigDecimal montoMaximoCredito) {
		this.montoMaximoCredito = montoMaximoCredito;
		this.montoMaximoCredito = UtilAjusteCalculo
				.ajusteCalculo(this.montoMaximoCredito);
	}

	/**
	 * @param plazoMaximoCredito
	 *            the plazoMaximoCredito to set
	 */
	public void setPlazoMaximoCredito(int plazoMaximoCredito) {
		this.plazoMaximoCredito = plazoMaximoCredito;
	}

	/**
	 * @param plazoCredito
	 *            the plazoCredito to set
	 */
	public void setPlazoCredito(int plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	/**
	 * @param cuotaMaximaComprometer
	 *            the cuotaMaximaComprometer to set
	 */
	public void setCuotaMaximaComprometer(BigDecimal cuotaMaximaComprometer) {
		this.cuotaMaximaComprometer = cuotaMaximaComprometer;
		this.cuotaMaximaComprometer = UtilAjusteCalculo
				.ajusteCalculo(this.cuotaMaximaComprometer);

	}

	/**
	 * @param cuotaCredito
	 *            the cuotaCredito to set
	 */
	public void setCuotaCredito(BigDecimal cuotaCredito) {
		this.cuotaCredito = cuotaCredito;
		this.cuotaCredito = UtilAjusteCalculo.ajusteCalculo(this.cuotaCredito);
	}

	/**
	 * @param montoCredito
	 *            the montoCredito to set
	 */
	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
		this.montoCredito = UtilAjusteCalculo.ajusteCalculo(this.montoCredito);
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @param calculoCredito
	 *            the calculoCredito to set
	 */
	public void setCalculoCredito(boolean calculoCredito) {
		this.calculoCredito = calculoCredito;
	}

	/**
	 * @return
	 * @see java.math.BigDecimal#toString()
	 */
	public String toString() {

		StringBuffer cadena = new StringBuffer();

		cadena = cadena.append(" Tipo Simulacion : " + this.getTipoSimulacion()
				+ "\n");
		cadena = cadena.append(" Plazo Maximo : "
				+ this.getPlazoMaximoCredito() + "\n");
		cadena = cadena.append(" Monto Maximo : "
				+ this.getMontoMaximoCredito() + "\n");
		cadena = cadena.append(" Cuota Maxima : "
				+ this.getCuotaMaximaComprometer() + "\n");
		cadena = cadena.append(" Plazo : " + this.getPlazoCredito() + "\n");
		cadena = cadena.append(" Monto : " + this.getMontoCredito() + "\n");
		cadena = cadena.append(" Cuota : " + this.getCuotaCredito() + "\n");
		cadena = cadena.append(" Observaciones : " + this.getObservacion()
				+ "\n");
		cadena = cadena.append(" Resultado : " + this.getCalculoCredito()
				+ "\n");

		return cadena.toString();
	}

	/**
	 * @return the tipoSimulacion
	 */
	public String getTipoSimulacion() {
		return tipoSimulacion;
	}

	/**
	 * @param tipoSimulacion
	 *            the tipoSimulacion to set
	 */
	public void setTipoSimulacion(String tipoSimulacion) {
		this.tipoSimulacion = tipoSimulacion;
	}

	
	/**
	 * Returns the value of tasaInteres.
	 * @return tasaInteres
	 */
	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	
	/**
	 * Sets the value for tasaInteres.
	 * @param tasaInteres
	 */
	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

}
