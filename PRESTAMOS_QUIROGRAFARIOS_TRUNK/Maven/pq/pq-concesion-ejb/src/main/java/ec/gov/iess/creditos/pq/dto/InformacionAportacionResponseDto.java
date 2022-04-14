package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO para respuesta del consumo del servicio de aportaciones consecutivas y
 * acumuladas y sueldo promedio
 * 
 * @author hugo.mora
 * @date 2016/07/14
 *
 */
public class InformacionAportacionResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int numeroConsecutivas;

	private int numeroAcumuladas;

	private BigDecimal sueldoPromedio;
	
	private boolean tieneMora;

	private String respuestaConsumo;

	private String mensajeConsumo;

	// Getters and setters
	public int getNumeroConsecutivas() {
		return numeroConsecutivas;
	}

	public void setNumeroConsecutivas(int numeroConsecutivas) {
		this.numeroConsecutivas = numeroConsecutivas;
	}

	public int getNumeroAcumuladas() {
		return numeroAcumuladas;
	}

	public void setNumeroAcumuladas(int numeroAcumuladas) {
		this.numeroAcumuladas = numeroAcumuladas;
	}

	public BigDecimal getSueldoPromedio() {
		return sueldoPromedio;
	}

	public void setSueldoPromedio(BigDecimal sueldoPromedio) {
		this.sueldoPromedio = sueldoPromedio;
	}

	public String getRespuestaConsumo() {
		return respuestaConsumo;
	}

	public void setRespuestaConsumo(String respuestaConsumo) {
		this.respuestaConsumo = respuestaConsumo;
	}

	public String getMensajeConsumo() {
		return mensajeConsumo;
	}

	public void setMensajeConsumo(String mensajeConsumo) {
		this.mensajeConsumo = mensajeConsumo;
	}
	
	public boolean isTieneMora() {
		return tieneMora;
	}

	public void setTieneMora(boolean tieneMora) {
		this.tieneMora = tieneMora;
	}

}
