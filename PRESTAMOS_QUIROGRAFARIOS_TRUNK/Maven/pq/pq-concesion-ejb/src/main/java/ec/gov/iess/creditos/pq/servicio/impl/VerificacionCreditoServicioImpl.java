/**
 * 
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.MigracionPrestamoHostDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.MigracionPrestamoHost;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto;

/**
 * Implementacion de la interfaz de verificacion de creditos remota.
 * 
 * 
 * @version 1.0
 * @author cvillarreal
 */
@Stateless
public class VerificacionCreditoServicioImpl implements
		VerificacionCreditoServicio, VerificacionCreditoServicioRemoto {

	@EJB
	PrestamoDao prestamoDao;
	@EJB
	MigracionPrestamoHostDao migracionPrestamoHostDao;

	@EJB
	DividendoPrestamoDao dividendoPrestamoDao;

	public VerificacionCreditoServicioImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#consultarPrestamosConLaMismaCuenta(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List<Prestamo> consultarPrestamosConLaMismaCuenta(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta,List<String> estadoCredito) {

		return prestamoDao.consultarPrestamosConLaMismaCuenta(
				rucInstitucionFinanciera, idTipoCuenta, numeroCuenta,estadoCredito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#existePrestamoConLaMismaCuenta(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public boolean existePrestamoConLaMismaCuenta(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta,List<String> estadoCredito) {

		return prestamoDao.existePrestamoConLaMismaCuenta(
				rucInstitucionFinanciera, idTipoCuenta, numeroCuenta,estadoCredito);
	}

	public boolean existePrestamoConLaMismaCuentaYCedula(
			String rucInstitucionFinanciera, TipoCuenta idTipoCuenta,
			String numeroCuenta,List<String> estadoCredito,String cedula) {

		return prestamoDao.existePrestamoConLaMismaCuentaYCedula(
				rucInstitucionFinanciera, idTipoCuenta, numeroCuenta,estadoCredito,cedula);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#listaPrestamoMoraHl(java.lang.String)
	 */
	public List<DividendoPrestamo> listaPrestamoMoraHl(String cedula,List<String> estadoDividendo) {

		// return prestamoDao.listaPrestamoMoraHl(cedula);
		return dividendoPrestamoDao.listaPrestamoMoraHl(cedula,estadoDividendo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#listaPrestamoVigentesHl(java.lang.String)
	 */
	public List<Prestamo> listaPrestamoVigentesHl(String cedula,List<String> estadoCredito) {

		return prestamoDao.listaPrestamoVigentesHl(cedula,estadoCredito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#listaPrestamoVigentesHost(java.lang.String)
	 */
	public List<MigracionPrestamoHost> listaPrestamoVigentesHost(String cedula,List<String> estadoMigrado) {

		return migracionPrestamoHostDao.listaPrestamoVigentesHost(cedula,estadoMigrado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#tienePrestamoMoraHl(java.lang.String)
	 */
	public boolean tienePrestamoMoraHl(String cedula,List<String> estadoMigrado) {

		// return prestamoDao.tienePrestamoMoraHl(cedula);
		return dividendoPrestamoDao.tienePrestamoMoraHl(cedula,estadoMigrado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#tienePrestamoVigentesHl(java.lang.String)
	 */
	public boolean tienePrestamoVigentesHl(String cedula,List<String> estadoCredito) {

		return prestamoDao.tienePrestamoVigentesHl(cedula,estadoCredito);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.VerificacionCreditoServicioRemoto#tienePrestamoVigentesHost(java.lang.String)
	 */
	public boolean tienePrestamoVigentesHost(String cedula,List<String> estadoMigrado) {

		return migracionPrestamoHostDao.tienePrestamoVigentesHost(cedula,estadoMigrado);
	}

}
