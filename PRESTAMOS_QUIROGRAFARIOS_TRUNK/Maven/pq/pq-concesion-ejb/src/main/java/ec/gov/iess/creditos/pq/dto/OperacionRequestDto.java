/**
 * 
 */
package ec.gov.iess.creditos.pq.dto;

/**
 * Pojo con caracteristica de entrada de una operacion
 * 
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class OperacionRequestDto {

	private Long numero;
	private String fechaSimulacion;
	private String origenFondos;	
	private String destinoFondos;
	private String tipoTicket;
	private String fechaPago;
	private Double valorTotalPago;
	private Long nut;
	private String tipoProducto;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getFechaSimulacion() {
		return fechaSimulacion;
	}

	public void setFechaSimulacion(String fechaSimulacion) {
		this.fechaSimulacion = fechaSimulacion;
	}

	public String getOrigenFondos() {
		return origenFondos;
	}

	public void setOrigenFondos(String origenFondos) {
		this.origenFondos = origenFondos;
	}

	/**
	 * @return the tipoTicket
	 */
	public String getTipoTicket() {
		return tipoTicket;
	}

	/**
	 * @param tipoTicket the tipoTicket to set
	 */
	public void setTipoTicket(String tipoTicket) {
		this.tipoTicket = tipoTicket;
	}

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the valorTotalPago
	 */
	public Double getValorTotalPago() {
		return valorTotalPago;
	}

	/**
	 * @param valorTotalPago the valorTotalPago to set
	 */
	public void setValorTotalPago(Double valorTotalPago) {
		this.valorTotalPago = valorTotalPago;
	}

	/**
	 * @return the nut
	 */
	public Long getNut() {
		return nut;
	}

	/**
	 * @param nut the nut to set
	 */
	public void setNut(Long nut) {
		this.nut = nut;
	}

	public String getDestinoFondos() {
		return destinoFondos;
	}

	public void setDestinoFondos(String destinoFondos) {
		this.destinoFondos = destinoFondos;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	
}
