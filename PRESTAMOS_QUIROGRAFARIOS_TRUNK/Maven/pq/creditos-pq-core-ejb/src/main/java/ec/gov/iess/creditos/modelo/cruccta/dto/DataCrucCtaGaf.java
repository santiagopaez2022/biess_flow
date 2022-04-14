/**
 * 
 */
package ec.gov.iess.creditos.modelo.cruccta.dto;

import java.math.BigDecimal;

import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;

/**
 * Cruce de cuenta
 * 
 * @author PAUL
 *
 */
public class DataCrucCtaGaf {

	private PrestamoPk prestamoPk;
	private String cedula;
	private String coddivpol;
	private String cumpleImposiciones;
	private String estadoAfiliado;
	private BigDecimal valorCruceCta;

	public PrestamoPk getPrestamoPk() {
		return prestamoPk;
	}

	public void setPrestamoPk(PrestamoPk prestamoPk) {
		this.prestamoPk = prestamoPk;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCoddivpol() {
		return coddivpol;
	}

	public void setCoddivpol(String coddivpol) {
		this.coddivpol = coddivpol;
	}

	public String getCumpleImposiciones() {
		return cumpleImposiciones;
	}

	public void setCumpleImposiciones(String cumpleImposiciones) {
		this.cumpleImposiciones = cumpleImposiciones;
	}

	public String getEstadoAfiliado() {
		return estadoAfiliado;
	}

	public void setEstadoAfiliado(String estadoAfiliado) {
		this.estadoAfiliado = estadoAfiliado;
	}

	public BigDecimal getValorCruceCta() {
		return valorCruceCta;
	}

	public void setValorCruceCta(BigDecimal valorCruceCta) {
		this.valorCruceCta = valorCruceCta;
	}

}
