package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;

/**
 * DTO para peticion de consumo del servicio de mora patronal
 * 
 * @author hugo.mora
 * @date 29 de mar. de 2016
 *
 */
public class MoraPatronalBiessRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoSucursal;

	private String rucEmpleador;

	// Getters and setters

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getRucEmpleador() {
		return rucEmpleador;
	}

	public void setRucEmpleador(String rucEmpleador) {
		this.rucEmpleador = rucEmpleador;
	}

}
