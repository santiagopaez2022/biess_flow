/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.ResumenFinPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.ResumenFinPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.ResumenFinPrestamoPK;
import ec.gov.iess.creditos.pq.servicio.ResumenFinPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.ResumenFinPrestamoServicioRemote;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class ResumenFinPrestamoservicioImpl implements
		ResumenFinPrestamoServicio, ResumenFinPrestamoServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(ResumenFinPrestamoservicioImpl.class);

	@EJB
	ResumenFinPrestamoDao resumenFinPrestamoDao;

	/**
	 * 
	 */
	public ResumenFinPrestamoservicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ResumenFinPrestamoServicio#crearResumenFinPrestamoPq(int,
	 *      int, java.lang.String, long, java.math.BigDecimal, int,
	 *      java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal,
	 *      java.math.BigDecimal)
	 */
	public void crearResumenFinPrestamoPq(int idTipoCredito,
			int idVeriedadCredito, long numeroPrestamo,
			BigDecimal valorPeriodoGracia, int plazoMeses,
			BigDecimal montoPrestamo, BigDecimal tasaInteres,
			BigDecimal valorSegurosaldos, BigDecimal tasaSegurosaldos) {

		ResumenFinPrestamo resumenFinPrestamo = new ResumenFinPrestamo();
		ResumenFinPrestamoPK resumenFinPrestamoPK = new ResumenFinPrestamoPK();

		log.debug(" crearResumenFinPrestamoPq");
		log.debug(" Parametros");
		log.debug(" idTipoCredito : " + idTipoCredito);
		log.debug(" idVeriedadCredito : " + idVeriedadCredito);
		log.debug(" numeroPrestamo : " + numeroPrestamo);
		log.debug(" valorPeriodoGracia : " + valorPeriodoGracia);
		log.debug(" plazoMeses : " + plazoMeses);
		log.debug(" montoPrestamo : " + montoPrestamo);
		log.debug(" tasaInteres : " + tasaInteres);
		log.debug(" valorSegurosaldos : " + valorSegurosaldos);
		log.debug(" tasaSegurosaldos : " + tasaSegurosaldos);

		try {
			// 1. valor periodo de gracia

			log.debug(" Insert 1. valor periodo de gracia");
			resumenFinPrestamoPK.setCodconfin("18");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("DOL");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(new Long(1));

			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(UtilAjusteCalculo
					.ajusteNumberBaseDatos(valorPeriodoGracia).doubleValue());

			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);

			resumenFinPrestamoDao.insert(resumenFinPrestamo);

		} catch (Exception e) {
			log.error("Error valor periodo de gracia", e);
			throw new RuntimeException("Error valor periodo de gracia", e);
		}

		try {

			// 2. plazo meses

			log.debug(" Insert 2. plazo meses");
			resumenFinPrestamo = new ResumenFinPrestamo();
			resumenFinPrestamoPK = new ResumenFinPrestamoPK();
			resumenFinPrestamoPK.setCodconfin("2");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("MES");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(1L);
			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(new Double(plazoMeses));
			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);
			resumenFinPrestamoDao.insert(resumenFinPrestamo);
		} catch (Exception e) {
			log.error("Error plazo meses ", e);
			throw new RuntimeException("Error plazo meses", e);
		}

		try {

			// 3. monto prestamo

			log.debug(" Insert 3. monto prestamo");
			resumenFinPrestamo = new ResumenFinPrestamo();
			resumenFinPrestamoPK = new ResumenFinPrestamoPK();
			resumenFinPrestamoPK.setCodconfin("1");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("DOL");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(1L);
			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(UtilAjusteCalculo
					.ajusteNumberBaseDatos(montoPrestamo).doubleValue());
			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);
			resumenFinPrestamoDao.insert(resumenFinPrestamo);
		} catch (Exception e) {
			log.error("Error monto prestamo ", e);
			throw new RuntimeException("Error monto prestamo", e);
		}

		try {

			// 4. tasa de interes

			log.debug(" Insert 4. tasa de interes");
			resumenFinPrestamo = new ResumenFinPrestamo();
			resumenFinPrestamoPK = new ResumenFinPrestamoPK();
			resumenFinPrestamoPK.setCodconfin("4");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("POR");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(1L);
			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(UtilAjusteCalculo
					.ajusteNumberBaseDatos(tasaInteres).doubleValue());
			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);
			resumenFinPrestamoDao.insert(resumenFinPrestamo);

		} catch (Exception e) {
			log.error("Error tasa de interes ", e);
			throw new RuntimeException("Error tasa de interes", e);
		}

		try {

			// 5. valor seguro de saldos

			log.debug(" Insert 5. valor seguro de saldos");
			resumenFinPrestamo = new ResumenFinPrestamo();
			resumenFinPrestamoPK = new ResumenFinPrestamoPK();
			resumenFinPrestamoPK.setCodconfin("19");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("DOL");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(1L);
			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(UtilAjusteCalculo
					.ajusteNumberBaseDatos(valorSegurosaldos).doubleValue());
			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);
			resumenFinPrestamoDao.insert(resumenFinPrestamo);
		} catch (Exception e) {
			log.error("Error valor seguro de saldos ", e);
			throw new RuntimeException("Error valor seguro de saldos", e);
		}

		try {

			// 6. numero de cuotas

			log.debug(" Insert 7. numero de cuotas");
			resumenFinPrestamo = new ResumenFinPrestamo();
			resumenFinPrestamoPK = new ResumenFinPrestamoPK();
			resumenFinPrestamoPK.setCodconfin("12");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("CUO");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(1L);
			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(new Double(12));
			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);
			resumenFinPrestamoDao.insert(resumenFinPrestamo);

		} catch (Exception e) {
			log.error("Error numero de cuotas ", e);
			throw new RuntimeException("Error numero de cuotas", e);
		}

		try {

			// 7. interes seguro de saldo

			resumenFinPrestamo = new ResumenFinPrestamo();
			resumenFinPrestamoPK = new ResumenFinPrestamoPK();
			resumenFinPrestamoPK.setCodconfin("8");
			resumenFinPrestamoPK.setCodprecla(new Long(idVeriedadCredito));
			resumenFinPrestamoPK.setCodpretip(new Long(idTipoCredito));
			resumenFinPrestamoPK.setCodunimedconfin("POR");
			resumenFinPrestamoPK.setNumpreafi(numeroPrestamo);
			resumenFinPrestamoPK.setOrdpreafi(1L);
			resumenFinPrestamo.setTipvalconfinpre("N");
			resumenFinPrestamo.setValcarconfinpre(null);
			resumenFinPrestamo.setValfecconfinpre(null);
			resumenFinPrestamo.setValnumconfinpre(tasaSegurosaldos
					.doubleValue());
			resumenFinPrestamo.setResumenFinPrestamoPK(resumenFinPrestamoPK);
			resumenFinPrestamoDao.insert(resumenFinPrestamo);

			log.debug(" Insert 8. interes seguro de saldo");

		} catch (Exception e) {
			log.error("Error interes seguro de saldo ", e);
			throw new RuntimeException("Error interes seguro de saldo", e);
		}

	}

}
