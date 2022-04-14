package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.CalculoCredito;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;

@Remote
public interface CalculoCreditoServicioRemote {

	CalculoCredito obtenerCalculoCredito(DatosCredito credito,
			BigDecimal sumaSaldoVigente, TipoPrestamista tipoPrestamista)
			throws CalculoCreditoException;

	/**
	 * calcula el credito, seguro de desgravamesn, perido de gracia y dividendos
	 * 
	 * @param cedula
	 * @param tasaInteres
	 *            tasa de interes para los calculos
	 * @param monto
	 *            monto del credito al que aplica
	 * @param plazo
	 *            plazo en meses
	 * @param tasaSeguroDesgravamen
	 *            tasa del seguro de desgravamen
	 * @return un objeto de modelo {@link PrestamoCalculo} con los datos del
	 *         calculo
	 * @throws CalculoCreditoException
	 * @author cvillarreal
	 */
	PrestamoCalculo calculoarCredito(DatosCredito datosCredito)
			throws CalculoCreditoException;

	// cambios 18/10/2011
	public BigDecimal recuperarMontoTienda();
	// fin cambios
	
	/**
	 * Permite obtener la tabla de amortizacion
	 * @param request
	 * @return
	 * @throws TablaAmortizacionSacException
	 */
	TablaAmortizacionSac obtenerInformacionTablaAmortizacionSAC(OperacionSacRequest request)
			throws TablaAmortizacionSacException;
}