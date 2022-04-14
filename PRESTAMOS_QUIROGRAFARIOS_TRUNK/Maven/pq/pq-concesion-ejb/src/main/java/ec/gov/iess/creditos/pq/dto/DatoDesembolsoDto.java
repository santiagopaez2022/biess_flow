/**
 * 
 */
package ec.gov.iess.creditos.pq.dto;

import java.math.BigDecimal;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class DatoDesembolsoDto {

	private String codigoForma;
	private String codigoBanco;
	private String tipoCuenta;
	private String numeroCuenta;
	private BigDecimal montoPrecancelacion;

	public String getCodigoForma() {
		return codigoForma;
	}

	public void setCodigoForma(String codigoForma) {
		this.codigoForma = codigoForma;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public BigDecimal getMontoPrecancelacion() {
		return montoPrecancelacion;
	}

	public void setMontoPrecancelacion(BigDecimal montoPrecancelacion) {
		this.montoPrecancelacion = montoPrecancelacion;
	}

}
