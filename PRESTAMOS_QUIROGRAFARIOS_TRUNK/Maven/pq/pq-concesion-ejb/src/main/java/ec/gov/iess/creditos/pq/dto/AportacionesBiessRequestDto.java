package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;

/**
 * DTO para peticion para el consumo del servicio de aportaciones consecutivas y
 * acumuladas
 * 
 * @author hugo.mora
 * @date 2016/07/14
 *
 */
public class AportacionesBiessRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cedula;

	private String tipoCalculo;

	private int numeroUltimasAportaciones;

	// Getters and setters
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public int getNumeroUltimasAportaciones() {
		return numeroUltimasAportaciones;
	}

	public void setNumeroUltimasAportaciones(int numeroUltimasAportaciones) {
		this.numeroUltimasAportaciones = numeroUltimasAportaciones;
	}

}
