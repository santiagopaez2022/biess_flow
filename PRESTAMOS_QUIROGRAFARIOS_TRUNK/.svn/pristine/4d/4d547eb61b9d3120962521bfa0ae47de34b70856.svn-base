/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.dao.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.PrestamoBiessDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiessPK;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementación DAO para la clase PrestamoBiess.
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
public class PrestamoBiessDaoEjb extends GenericEjbDao<PrestamoBiess, PrestamoBiessPK> implements PrestamoBiessDao {

	@EJB
	private PrestamoDao prestamoDao;
	
	/**
	 * Constructor.
	 */
	public PrestamoBiessDaoEjb() {
		super(PrestamoBiess.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.PrestamoBiessDao#getItemsByRange(java.lang.String, java.util.List, java.util.List, int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PrestamoBiess> getItemsByRange(final String cedula, final List<String> estadosCredito, final List<Long> tiposCredito, final int firstRow, final int endRow) {
		final Query query = em.createNamedQuery("PrestamoBiess.prestamosByRange");
		query.setParameter("cedula", cedula);
		query.setParameter("estadosCredito", estadosCredito);
		query.setParameter("tiposCredito", tiposCredito);
		if (firstRow > 0) {
			query.setFirstResult(firstRow);
		}
		final int maxResults = endRow - firstRow;
		if (maxResults > 0) {
			query.setMaxResults(maxResults);
		}

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.PrestamoBiessDao#getRowCount(java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public int getRowCount(final String cedula, final List<String> estadosCredito, final List<Long> tiposCredito) {
		final Query query = em.createNamedQuery("Prestamo.rowCount");
		query.setParameter("cedula", cedula);
		query.setParameter("estadosCredito", estadosCredito);
		query.setParameter("tiposCredito", tiposCredito);

		return ((Long) query.getSingleResult()).intValue();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PrestamoBiess> obtenerPrestamosCedulaEstadosTipoCredito(final String cedula, final List<String> estadosCredito, final List<Long> tiposCredito) {
		final Query query = em.createNamedQuery("PrestamoBiess.prestamosByCedulaEstadosTipoCredito");
		query.setParameter("cedula", cedula);
		query.setParameter("estadosCredito", estadosCredito);
		query.setParameter("tiposCredito", tiposCredito);
		
		return query.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see ec.fin.biess.creditos.pq.dao.PrestamoBiessDao#getPrestamosEstadoBloqueo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PrestamoBiess> getPrestamosEstadoBloqueo (final String cedula, final String estadoBloqNormal, final String estadoBloqNovacion) {
		final Query query = em.createNamedQuery("PrestamoBiess.prestamosEstadoBloqueo");
		query.setParameter("cedula", cedula);
		query.setParameter("estadoNormal", estadoBloqNormal);
		query.setParameter("estadoNovacion", estadoBloqNovacion);
		query.setParameter("estadoVig", "VIG");

		return query.getResultList();		
	}
	
	/**
	 * @see ec.fin.biess.creditos.pq.dao.PrestamoBiessDao#
	 *      actualizarFechaCancelacionYEstado(ec.gov.iess.creditos.modelo.
	 *      persistencia.Prestamo, java.lang.String, java.sql.Date)
	 */
	@Override
	public boolean actualizarFechaCancelacionYEstado(final Prestamo prestamo,
			final String codigoEstadoPrestamo, final Date fechaCancelacion) {

		// INC-2272 Pensiones Alimenticias
		prestamo.getEstadoPrestamo().setCodestpre(codigoEstadoPrestamo);
		prestamo.setFeccanpre(fechaCancelacion);
		prestamoDao.update(prestamo);
		return true;
	}
	
	@Override
	public PrestamoBiess buscarPorOpIdent(final Long numOperacionSAC, final String identificacion) {
		final Query query = em
				.createNamedQuery("Prestamo.consultarPorNumOpId");
		query.setParameter("numOpSAC", numOperacionSAC);
		query.setParameter("identificacion", identificacion);
		
		final List<PrestamoBiess> prestamos=query.getResultList();
		if(!prestamos.isEmpty()) {
			return prestamos.get(0);
		}else {
			return null;
		}
	}
	

	
}
