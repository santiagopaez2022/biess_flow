package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Dto de respuesta con informacion de la tabla de amortizacion desde el SAC
 * 
 * @author hugo.mora
 * @date 2018/08/31
 *
 */
public class TablaAmortizacionSac implements Serializable {

	private static final long serialVersionUID = -4545064979790021665L;

	private BigDecimal montoPrestamo;

	private BigDecimal valorCuotaPagar;

	private String tipoTabla;
	
	private int plazo;

	private BigDecimal interes;
	
	private List<DividendoDto> listaDividendos;

	public List<DividendoDto> getListaDividendos() {
		return listaDividendos;
	}

	public void setListaDividendos(List<DividendoDto> listaDividendos) {
		this.listaDividendos = listaDividendos;
	}
	public BigDecimal getMontoPrestamo() {
		return montoPrestamo;
	}

	public void setMontoPrestamo(BigDecimal montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	public BigDecimal getValorCuotaPagar() {
		return valorCuotaPagar;
	}

	public void setValorCuotaPagar(BigDecimal valorCuotaPagar) {
		this.valorCuotaPagar = valorCuotaPagar;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public String getTipoTabla() {
		return tipoTabla;
	}

	public void setTipoTabla(String tipoTabla) {
		this.tipoTabla = tipoTabla;
	}

}
