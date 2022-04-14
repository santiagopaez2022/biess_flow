/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.util.Calendar;
import java.util.List;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * @author cbastidas
 * 
 */
public class DatosSituacionPrestamo {

	public DatosSituacionPrestamo() {

	}

	/**
	 * @param prestamoPk
	 * @param estadosCreVigentes
	 * @param estadosCreLiquidacion
	 * @param estadosDivPorPagar
	 * @param estadosDivMora
	 * @param estadosDivSaldoLiq
	 * @param tipoCredito
	 */
	public DatosSituacionPrestamo(PrestamoPk prestamoPk,
			List<String> estadosCreVigentes,
			List<String> estadosCreLiquidacion,
			List<String> estadosDivPorPagar, List<String> estadosDivMora,
			List<String> estadosDivSaldoLiq, List<Long> tipoCredito) {
		this.prestamoPk = prestamoPk;
		this.estadosCreVigentes = estadosCreVigentes;
		this.estadosCreLiquidacion = estadosCreLiquidacion;
		this.estadosDivPorPagar = estadosDivPorPagar;
		this.estadosDivMora = estadosDivMora;
		this.estadosDivSaldoLiq = estadosDivSaldoLiq;
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @param prestamoPk
	 * @param estadosCreVigentes
	 * @param estadosCreLiquidacion
	 * @param estadosDivPorPagar
	 * @param estadosDivMora
	 * @param estadosDivSaldoLiq
	 * @param tipoCredito
	 * @param tipoEmpleador
	 */
	public DatosSituacionPrestamo(PrestamoPk prestamoPk, List<String> estadosCreVigentes, List<String> estadosCreLiquidacion,
			List<String> estadosDivPorPagar, List<String> estadosDivMora, List<String> estadosDivSaldoLiq, List<Long> tipoCredito,
			String tipoEmpleador) {
		super();
		this.prestamoPk = prestamoPk;
		this.estadosCreVigentes = estadosCreVigentes;
		this.estadosCreLiquidacion = estadosCreLiquidacion;
		this.estadosDivPorPagar = estadosDivPorPagar;
		this.estadosDivMora = estadosDivMora;
		this.estadosDivSaldoLiq = estadosDivSaldoLiq;
		this.tipoCredito = tipoCredito;
		this.tipoEmpleador = tipoEmpleador;
	}

	private PrestamoPk prestamoPk;
	private List<String> estadosCreVigentes;
	private List<String> estadosCreLiquidacion;
	private List<String> estadosDivPorPagar;
	private List<String> estadosDivMora;
	private List<String> estadosDivSaldoLiq;
	private List<Long> tipoCredito;
	private String tipoEmpleador;
	private Calendar fechaValidezComprobante;
	private Calendar fechaValidezLiquidacion;
	private String tipoRecaudacion;
	

	public PrestamoPk getPrestamoPk() {
		return prestamoPk;
	}

	public void setPrestamoPk(PrestamoPk prestamoPk) {
		this.prestamoPk = prestamoPk;
	}

	public List<String> getEstadosCreVigentes() {
		return estadosCreVigentes;
	}

	public void setEstadosCreVigentes(List<String> estadosCreVigentes) {
		this.estadosCreVigentes = estadosCreVigentes;
	}

	public List<String> getEstadosCreLiquidacion() {
		return estadosCreLiquidacion;
	}

	public void setEstadosCreLiquidacion(List<String> estadosCreLiquidacion) {
		this.estadosCreLiquidacion = estadosCreLiquidacion;
	}

	public List<String> getEstadosDivPorPagar() {
		return estadosDivPorPagar;
	}

	public void setEstadosDivPorPagar(List<String> estadosDivPorPagar) {
		this.estadosDivPorPagar = estadosDivPorPagar;
	}

	public List<String> getEstadosDivMora() {
		return estadosDivMora;
	}

	public void setEstadosDivMora(List<String> estadosDivMora) {
		this.estadosDivMora = estadosDivMora;
	}

	public List<String> getEstadosDivSaldoLiq() {
		return estadosDivSaldoLiq;
	}

	public void setEstadosDivSaldoLiq(List<String> estadosDivSaldoLiq) {
		this.estadosDivSaldoLiq = estadosDivSaldoLiq;
	}

	public List<Long> getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(List<Long> tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getTipoEmpleador() {
		return tipoEmpleador;
	}

	public void setTipoEmpleador(String tipoEmpleador) {
		this.tipoEmpleador = tipoEmpleador;
	}

	public Calendar getFechaValidezComprobante() {
		return fechaValidezComprobante;
	}

	public void setFechaValidezComprobante(Calendar fechaValidezComprobante) {
		this.fechaValidezComprobante = fechaValidezComprobante;
	}

	public String getTipoRecaudacion() {
		return tipoRecaudacion;
	}

	public void setTipoRecaudacion(String tipoRecaudacion) {
		this.tipoRecaudacion = tipoRecaudacion;
	}

	public Calendar getFechaValidezLiquidacion() {
		return fechaValidezLiquidacion;
	}

	public void setFechaValidezLiquidacion(Calendar fechaValidezLiquidacion) {
		this.fechaValidezLiquidacion = fechaValidezLiquidacion;
	}

}
