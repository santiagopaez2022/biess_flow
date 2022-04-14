package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Contiene informacion de garantias que se obtiene desde el SAC
 * 
 * @author hugo.mora
 * @date 2018/09/01
 *
 */
public class InformacionGarantias implements Serializable {

	private static final long serialVersionUID = -8878014696400455L;

	private List<GarantiasComprometidasDto> listaGarantiasFC;

	private List<GarantiasComprometidasDto> listaGarantiasFR;

	private BigDecimal totalGarantiasFondoReserva;

	private BigDecimal totalGarantiasFondosCesantia;

	
	public BigDecimal getTotalGarantiasFondoReserva() {
		return totalGarantiasFondoReserva;
	}

	public void setTotalGarantiasFondoReserva(BigDecimal totalGarantiasFondoReserva) {
		this.totalGarantiasFondoReserva = totalGarantiasFondoReserva;
	}

	public BigDecimal getTotalGarantiasFondosCesantia() {
		return totalGarantiasFondosCesantia;
	}

	public void setTotalGarantiasFondosCesantia(BigDecimal totalGarantiasFondosCesantia) {
		this.totalGarantiasFondosCesantia = totalGarantiasFondosCesantia;
	}

	public List<GarantiasComprometidasDto> getListaGarantiasFC() {
		return listaGarantiasFC;
	}

	public void setListaGarantiasFC(List<GarantiasComprometidasDto> listaGarantiasFC) {
		this.listaGarantiasFC = listaGarantiasFC;
	}

	public List<GarantiasComprometidasDto> getListaGarantiasFR() {
		return listaGarantiasFR;
	}

	public void setListaGarantiasFR(List<GarantiasComprometidasDto> listaGarantiasFR) {
		this.listaGarantiasFR = listaGarantiasFR;
	}

}
