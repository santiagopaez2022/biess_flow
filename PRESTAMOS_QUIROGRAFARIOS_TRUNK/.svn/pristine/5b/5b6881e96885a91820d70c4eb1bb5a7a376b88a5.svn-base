package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.DividendoPrestamoDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.servicio.ConsultaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaCreditoServicioRemote;

/**
 * @author jvaca
 * 
 */
@Stateless
public class ConsultaCreditoServicioImpl implements ConsultaCreditoServicio,
		ConsultaCreditoServicioRemote {
	@EJB
	PrestamoDao prestamoDao;

	@EJB
	DividendoPrestamoDao dividendoPrestamoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ConsultaCreditoServicio#getDividendosPrestamo(java.lang.Long,
	 *      java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public List<DividendoPrestamo> getDividendosPrestamo(Long codprecla,
			Long codpretip, Long numpreafi, Long ordpreafi) {
		return dividendoPrestamoDao.getDividendosByIdPrestamo(codprecla,
				codpretip, numpreafi, ordpreafi);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ConsultaCreditoServicio#getPrestamosVigentesPorCedula(java.lang.String)
	 */
	public List<Prestamo> getPrestamosVigentesPorCedula(String cedula,List<String> estadoCredito) {
		List<Prestamo> prestamos = prestamoDao.listaPrestamoVigentesHl(cedula,estadoCredito);
		if (prestamos != null && !prestamos.isEmpty()) {
			for (Prestamo prestamo : prestamos) {
				prestamo.setDividendosPrestamo(dividendoPrestamoDao
						.getDividendosByIdPrestamo(prestamo.getCreditoPk()
								.getCodprecla(), prestamo.getCreditoPk()
								.getCodpretip(), prestamo.getCreditoPk()
								.getNumpreafi(), prestamo.getCreditoPk()
								.getOrdpreafi()));
			}
		}
		return prestamos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.ConsultaCreditoServicio#getAllPrestamosPorCedula(java.lang.String)
	 */
	public List<Prestamo> getAllPrestamosPorCedula(String cedula) {
		List<Prestamo> prestamos = prestamoDao.getPrestamosPorCedula(cedula);
		if (prestamos != null && !prestamos.isEmpty()) {
			for (Prestamo prestamo : prestamos) {
				prestamo.setDividendosPrestamo(dividendoPrestamoDao
						.getDividendosByIdPrestamo(prestamo.getCreditoPk()
								.getCodprecla(), prestamo.getCreditoPk()
								.getCodpretip(), prestamo.getCreditoPk()
								.getNumpreafi(), prestamo.getCreditoPk()
								.getOrdpreafi()));
			}
		}
		return prestamos;

	}

}
