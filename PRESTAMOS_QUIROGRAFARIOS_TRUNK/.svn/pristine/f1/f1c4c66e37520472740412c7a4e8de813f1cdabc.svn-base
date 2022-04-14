package ec.fin.biess.creditos.pq.servicio.impl;

import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.fin.biess.creditos.pq.servicio.PrestamoCalculoService;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.pq.util.CalculoPeriodoGracia;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * Implementacion de la interfaz PrestamoCalculoService
 * 
 * @author diana.suasnavas
 *
 */
@Stateless
public class PrestamoCalculoServiceImpl implements PrestamoCalculoService {

	private LoggerBiess log = LoggerBiess.getLogger(PrestamoCalculoServiceImpl.class);

	/**
	 * Llena todos los datos del prestamo y los dividendos de la tabla de amortizacion
	 * 
	 * @param datosCredito
	 * @return PrestamosCalculo
	 * @throws TablaAmortizacionException
	 */
	public PrestamoCalculo poblarPrestamoCalculoNew(DatosCredito datosCredito) throws TablaAmortizacionException {
		log.debug("CARGA PRESTAMO");
		PrestamoCalculo prestamoCalculo = new PrestamoCalculo();
		CalculoPeriodoGracia calculoPeriodoGracia = new CalculoPeriodoGracia();
		// determina periodo de gracia
		prestamoCalculo.setPeriodoGracia(calculoPeriodoGracia.determinaPeriodoGracia(datosCredito.getFechaSolicitud(),
				UtilAjusteCalculo.ajusteNumber(datosCredito.getTasaInteres(), 2),
				UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2)));
		prestamoCalculo.setPlazoMeses(datosCredito.getPlazo());
		prestamoCalculo.setValorCredito(UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2));
		prestamoCalculo.setValorTotalDividendoMensual(datosCredito.getCuotaMensualMaxima());
		prestamoCalculo.setCedula(datosCredito.getCedulaAfiliado());
		prestamoCalculo.setMontoPrestamo(UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2));
		datosCredito.setPrestamoCalculo(prestamoCalculo);
		

		return prestamoCalculo;
	}
}
