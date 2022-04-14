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
public class PlazoCreditoConDocumentoFiduciario extends PlazoCredito implements
		Serializable {

	private BigDecimal valorEstimadoDocumentoFiduciario;

	/**
	 * 
	 */
	public PlazoCreditoConDocumentoFiduciario() {

	}

	public PlazoCreditoConDocumentoFiduciario(OpcionCredito opcionCreditoNew,
			BigDecimal porcentajeComprometidoNew,
			BigDecimal valorMaximoComprometerNew,
			BigDecimal valorMaximoCreditoNew,
			BigDecimal valorEstimadoDocumentoFiduciarioNew) {

		this.opcionCredito = opcionCreditoNew;
		this.porcentajeComprometido = porcentajeComprometidoNew;
		this.valorMaximoComprometer = valorMaximoComprometerNew;
		this.valorMaximoCredito = valorMaximoCreditoNew;
		this.valorEstimadoDocumentoFiduciario = valorEstimadoDocumentoFiduciarioNew;

	}

	/**
	 * @param valorTotalCreditoNew
	 * @param mesesNew
	 * @param cuotaMensualNew
	 */
	public PlazoCreditoConDocumentoFiduciario(BigDecimal valorTotalCreditoNew,
			int mesesNew, BigDecimal cuotaMensualNew) {
		super(valorTotalCreditoNew, mesesNew, cuotaMensualNew);
	}

	public BigDecimal getValorEstimadoDocumentoFiduciario() {
		return valorEstimadoDocumentoFiduciario;
	}

	public void setValorEstimadoDocumentoFiduciario(
			BigDecimal valorEstimadoDocumentoFiduciario) {
		this.valorEstimadoDocumentoFiduciario = valorEstimadoDocumentoFiduciario;
		this.valorEstimadoDocumentoFiduciario = UtilAjusteCalculo
				.ajusteCalculo(this.valorEstimadoDocumentoFiduciario);
	}

	@Override
	public BigDecimal getValorSeguroSaldoMensual() {
		return null;
	}

	@Override
	public void setValorSeguroSaldoMensual(BigDecimal valor) {

	}

}
