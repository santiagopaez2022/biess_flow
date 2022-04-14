package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;

import javax.ejb.Local;

@Local
public interface ResumenFinPrestamoServicio {

	/**
	 * Crear el resumen del credito quirografario
	 * 
	 * @param idTipoCredito 
	 * @param idVeriedadCredito
	 * @param tipoSolicitante
	 * @param numeroPrestamo
	 * @param valorPeriodoGracia
	 * @param plazoMeses
	 * @param montoPrestamo
	 * @param tasaInteres
	 * @param valorSegurosaldos
	 * @param tasaSegurosaldos
	 */
	public void crearResumenFinPrestamoPq(int idTipoCredito,
			int idVeriedadCredito, long numeroPrestamo,
			BigDecimal valorPeriodoGracia, int plazoMeses,
			BigDecimal montoPrestamo, BigDecimal tasaInteres,
			BigDecimal valorSegurosaldos, BigDecimal tasaSegurosaldos);

}
