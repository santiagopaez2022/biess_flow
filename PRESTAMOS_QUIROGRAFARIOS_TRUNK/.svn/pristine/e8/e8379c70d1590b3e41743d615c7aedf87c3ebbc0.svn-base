package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao;
import ec.gov.iess.creditos.dao.PrestamoResumenHistoricoDao;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoEstadoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoResumenHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoEstadoHistoricoPK;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.ActualizarPrestamoEstadoHistoricoException;
import ec.gov.iess.creditos.pq.servicio.PrestamoEstadoHistoricoServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoEstadoHistoricoServicioRemote;

@Stateless
public class PrestamoEstadoHistoricoServicioImpl implements
		PrestamoEstadoHistoricoServicio, PrestamoEstadoHistoricoServicioRemote {

	@EJB
	private PrestamoEstadoHistoricoDao prestamoEstadoHistoricoDao;
	
	@EJB
	private PrestamoResumenHistoricoDao prestamoResumenHistoricoDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPrestamoEstadoHistorico(Long numPreAfi,
			Long ordPreAfi, Long codPreTip, Long codPreCla, String codEstAnt,
			String codEstNue, Date FecFin, String obsTra)
			throws ActualizarPrestamoEstadoHistoricoException {

		// Actualización del registro del estado anterior

		String mensajeError = "";
		// prestamoEstadoHistoricoPK.set
		Long contarNumPrestamosEstadoHis = prestamoEstadoHistoricoDao
				.contarPorPkSinfecIni(codEstAnt, numPreAfi, ordPreAfi,
						codPreTip, codPreCla);
		if (contarNumPrestamosEstadoHis == 0l) {
			mensajeError = "No se pudo Actualizar el Registro del Estado Anterior "
					+ codEstAnt
					+ "del Préstamo "
					+ codPreTip
					+ " . "
					+ codPreCla + "." + numPreAfi + "." + ordPreAfi;
			throw new ActualizarPrestamoEstadoHistoricoException(mensajeError);
		} else if (contarNumPrestamosEstadoHis > 1l) {
			mensajeError = "Se ha ENCONTRADO dos o más Registros del Estado Anterior "
					+ codEstAnt
					+ "del Préstamo "
					+ codPreTip
					+ " . "
					+ codPreCla + "." + numPreAfi + "." + ordPreAfi;
			throw new ActualizarPrestamoEstadoHistoricoException(mensajeError);
		} else {
			PrestamoEstadoHistorico prestamoEstadoHistorico = prestamoEstadoHistoricoDao
					.obtenerPorPkSinfecIni(codEstAnt, numPreAfi, ordPreAfi,
							codPreTip, codPreCla);
			prestamoEstadoHistorico.setFecfin(new Date(System
					.currentTimeMillis()));
			prestamoEstadoHistorico.setObstra(obsTra);
			prestamoEstadoHistoricoDao.update(prestamoEstadoHistorico);
			// // falta implementar mas logica aca
		}
	}
	/**
	 * @see ec.gov.iess.creditos.dao.PrestamoEstadoHistoricoDao#buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla)
	 *
	 */
	public List<PrestamoEstadoHistorico> buscarHistoricosDePrestamo(Long numpreafi,Long ordpreafi, Long codpretip, Long codprecla){
		List<PrestamoEstadoHistorico> prestamoEstadoHistoricoList = null;
		prestamoEstadoHistoricoList = prestamoEstadoHistoricoDao.buscarHistoricosDePrestamo(numpreafi, ordpreafi, codpretip, codprecla);
		return prestamoEstadoHistoricoList;		
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarprestamoPdaRec(Long numPreAfi,
			Long ordPreAfi, Long codPreTip, Long codPreCla, String codEstAnt, String obsTra)
			throws ActualizarPrestamoEstadoHistoricoException {

		// Actualización del registro del estado anterior

		String mensajeError = "";
		// prestamoEstadoHistoricoPK.set
		BigDecimal contarNumPrestamosEstadoHis = prestamoEstadoHistoricoDao
				.verificaexistenciahist(codEstAnt, numPreAfi, ordPreAfi,
						codPreTip, codPreCla);
		int cont = contarNumPrestamosEstadoHis.intValue();
		if (cont == 0) {
			mensajeError = "No se pudo Actualizar el Registro del Estado Anterior "
					+ codEstAnt
					+ "del Préstamo "
					+ codPreTip
					+ " . "
					+ codPreCla + "." + numPreAfi + "." + ordPreAfi + ". Porque no existe";
			throw new ActualizarPrestamoEstadoHistoricoException(mensajeError);
		} else if (cont > 1) {
			mensajeError = "Se ha ENCONTRADO dos o más Registros del Estado Anterior "
					+ codEstAnt
					+ "del Préstamo "
					+ codPreTip
					+ " . "
					+ codPreCla + "." + numPreAfi + "." + ordPreAfi;
			throw new ActualizarPrestamoEstadoHistoricoException(mensajeError);
		} else {
			PrestamoEstadoHistorico prestamoEstadoHistorico = prestamoEstadoHistoricoDao
					.obtenerhistorico(codEstAnt, numPreAfi, ordPreAfi,
							codPreTip, codPreCla);
			prestamoEstadoHistorico.setFecfin(new Date(System
					.currentTimeMillis()));
			prestamoEstadoHistorico.setObstra(obsTra);
			prestamoEstadoHistoricoDao.update(prestamoEstadoHistorico);
			// Ahora creo el registro historico del estado REC
			
			PrestamoEstadoHistoricoPK pkrec = new PrestamoEstadoHistoricoPK();
			pkrec.setCodestpre("REC");
			pkrec.setCodprecla(codPreCla);
			pkrec.setCodpretip(codPreTip);
			pkrec.setNumpreafi(numPreAfi);
			pkrec.setOrdpreafi(ordPreAfi);
			pkrec.setFecini(new Date(System.currentTimeMillis()));
			
			PrestamoEstadoHistorico prec = new PrestamoEstadoHistorico();
			prec.setPrestamoEstadoHistoricoPK(pkrec);
			prestamoEstadoHistoricoDao.insert(prec);
		}
	}
	public String getmotivorechazo(PrestamoPk pk) {
		List<PrestamoResumenHistorico> lst =  prestamoResumenHistoricoDao.consultarporpq(pk);
		if(lst.isEmpty()){
			return "";
		}else{
			PrestamoResumenHistorico prh =  lst.get(0);
			return prh.getCatalogorechazo().getMo_descripcion();
		}
	}

	/** 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamoEstadoHistoricoServicio#
	 * actualizarPrestamoHistorico
	 * (ec.gov.iess.creditos.modelo.persistencia.Prestamo, java.lang.String,
	 * java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarPrestamoHistorico(Prestamo prestamo,
			String codigoNuevoEstado, String observacion)
			throws ActualizarPrestamoEstadoHistoricoException {

		// Actualización del registro del estado anterior

		String mensajeError = "";
		// prestamoEstadoHistoricoPK.set
		BigDecimal contarNumPrestamosEstadoHis = prestamoEstadoHistoricoDao
				.verificaexistenciahist(prestamo.getEstadoPrestamo()
						.getCodestpre(), prestamo.getPrestamoPk()
						.getNumpreafi(), prestamo.getPrestamoPk()
						.getOrdpreafi(), prestamo.getPrestamoPk()
						.getCodpretip(), prestamo.getPrestamoPk()
						.getCodprecla());
		int cont = contarNumPrestamosEstadoHis.intValue();
		if (cont == 0) {
			mensajeError = "No se pudo Actualizar el Registro del Estado Anterior "
					+ prestamo.getEstadoPrestamo().getCodestpre()
					+ "del Préstamo "
					+ prestamo.getPrestamoPk().getCodpretip()
					+ " . "
					+ prestamo.getPrestamoPk().getCodprecla()
					+ "."
					+ prestamo.getPrestamoPk().getNumpreafi()
					+ "."
					+ prestamo.getPrestamoPk().getOrdpreafi()
					+ ". Porque no existe";
			throw new ActualizarPrestamoEstadoHistoricoException(mensajeError);
		} else if (cont > 1) {
			mensajeError = "Se ha ENCONTRADO dos o más Registros del Estado Anterior "
					+ prestamo.getEstadoPrestamo().getCodestpre()
					+ "del Préstamo "
					+ prestamo.getPrestamoPk().getCodpretip()
					+ " . "
					+ prestamo.getPrestamoPk().getCodprecla()
					+ "."
					+ prestamo.getPrestamoPk().getNumpreafi()
					+ "."
					+ prestamo.getPrestamoPk().getOrdpreafi();
			throw new ActualizarPrestamoEstadoHistoricoException(mensajeError);
		} else {
			PrestamoEstadoHistorico prestamoEstadoHistorico = prestamoEstadoHistoricoDao
					.obtenerhistorico(prestamo.getEstadoPrestamo()
							.getCodestpre(), prestamo.getPrestamoPk()
							.getNumpreafi(), prestamo.getPrestamoPk()
							.getOrdpreafi(), prestamo.getPrestamoPk()
							.getCodpretip(), prestamo.getPrestamoPk()
							.getCodprecla());
			prestamoEstadoHistorico.setFecfin(new Date(System
					.currentTimeMillis()));
			prestamoEstadoHistorico.setObstra(observacion);
			prestamoEstadoHistoricoDao.update(prestamoEstadoHistorico);
			// Ahora creo el registro historico con estado :codigoNuevoEstado

			PrestamoEstadoHistoricoPK pkrec = new PrestamoEstadoHistoricoPK();
			pkrec.setCodestpre(codigoNuevoEstado);
			pkrec.setCodprecla(prestamo.getPrestamoPk().getCodprecla());
			pkrec.setCodpretip(prestamo.getPrestamoPk().getCodpretip());
			pkrec.setNumpreafi(prestamo.getPrestamoPk().getNumpreafi());
			pkrec.setOrdpreafi(prestamo.getPrestamoPk().getOrdpreafi());
			pkrec.setFecini(new Date(System.currentTimeMillis()));

			PrestamoEstadoHistorico prec = new PrestamoEstadoHistorico();
			prec.setPrestamoEstadoHistoricoPK(pkrec);
			prestamoEstadoHistoricoDao.insert(prec);
		}
	}
}
