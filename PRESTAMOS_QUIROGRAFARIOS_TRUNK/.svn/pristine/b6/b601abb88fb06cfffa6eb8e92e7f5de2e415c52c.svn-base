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
public class PlazoCreditoSinDocumentoFiduciario extends PlazoCredito implements
		Serializable {

	private BigDecimal valorSeguroSaldoMensual;

	/**
	 * 
	 */
	public PlazoCreditoSinDocumentoFiduciario() {

	}

	public PlazoCreditoSinDocumentoFiduciario(OpcionCredito opcionCreditoNew,
			BigDecimal porcentajeComprometidoNew,
			BigDecimal valorMaximoComprometerNew,
			BigDecimal valorMaximoCreditoNew,
			BigDecimal valorSeguroSaldoMensualNew) {

		this.opcionCredito = opcionCreditoNew;
		this.porcentajeComprometido = porcentajeComprometidoNew;
		this.valorMaximoComprometer = valorMaximoComprometerNew;
		this.valorMaximoCredito = valorMaximoCreditoNew;
		this.valorSeguroSaldoMensual = valorSeguroSaldoMensualNew;

	}

	/**
	 * @param valorTotalCreditoNew
	 * @param mesesNew
	 * @param cuotaMensualNew
	 */
	public PlazoCreditoSinDocumentoFiduciario(BigDecimal valorTotalCreditoNew,
			int mesesNew, BigDecimal cuotaMensualNew) {
		super(valorTotalCreditoNew, mesesNew, cuotaMensualNew);
	}

	public BigDecimal getValorSeguroSaldoMensual() {
		return valorSeguroSaldoMensual;
	}

	public void setValorSeguroSaldoMensual(BigDecimal valorSeguroSaldoMensual) {
		this.valorSeguroSaldoMensual = valorSeguroSaldoMensual;
		this.valorSeguroSaldoMensual = UtilAjusteCalculo
				.ajusteCalculo(this.valorSeguroSaldoMensual);
	}

}
