package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.CalculoCredito;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;

@Local
public interface CalculoCreditoServicio {

	CalculoCredito obtenerCalculoCredito(DatosCredito credito,
			BigDecimal sumaSaldoVigente, TipoPrestamista tipoPrestamista)
			throws CalculoCreditoException;

	/**
	 * calcula el credito, seguro de desgravamesn, perido de gracia y dividendos
	 * 
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
	
	/**
	 * calcula el credito, seguro de desgravamesn, perido de gracia y dividendos
	 * por los nuevos cambios de la tabla de amortizacion
	 * @param prestamoCalculo 
	 * @param tasaInteres
	 *            tasa de interes para los calculos
	 * @param monto
	 *            monto del credito al que aplica
	 * @param plazo
	 *            plazo en meses
	 * @param tasaSeguroDesgravamen
	 *            tasa del seguro de desgravamen
	 * 
	 * @return un objeto de modelo {@link PrestamoCalculo} con los datos del
	 *         calculo
	 * @throws CalculoCreditoException
	 * @author dsuasnavas
	 * @throws TablaAmortizacionException 
	 */
	PrestamoCalculo calcularCreditoNew(DatosCredito datosCredito, PrestamoCalculo prestamoCalculo)
			throws CalculoCreditoException, TablaAmortizacionException, TablaAmortizacionSacException;


	// cambios 18/10/2011
	public BigDecimal recuperarMontoTienda();
	// fin cambios
	
	
	/**
	 * Calcula los datos necesario para el credito
	 * @param credito
	 * @return CalculoCredito
	 * @throws CalculoCreditoException
	 * @author diana.suasnavas
	 */
	CalculoCredito obtenerCalculoCreditoNew(DatosCredito credito)
			throws CalculoCreditoException;

	
	/**
	 * Permite obtener la tabla de amortizacion
	 * @param request
	 * @return
	 * @throws TablaAmortizacionSacException
	 */
	TablaAmortizacionSac obtenerInformacionTablaAmortizacionSAC(OperacionSacRequest request)
			throws TablaAmortizacionSacException;

}