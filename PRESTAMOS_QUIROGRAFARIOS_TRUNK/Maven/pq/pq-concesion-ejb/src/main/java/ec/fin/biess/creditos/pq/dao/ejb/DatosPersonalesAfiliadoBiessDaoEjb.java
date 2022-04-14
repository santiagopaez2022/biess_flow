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

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.DatosPersonalesAfiliadoBiessDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.DatosPersonalesAfiliadoBiess;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion DAO DatosPersonalesAfiliadoDao
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
public class DatosPersonalesAfiliadoBiessDaoEjb extends GenericEjbDao<DatosPersonalesAfiliadoBiess, String> implements DatosPersonalesAfiliadoBiessDao {

	public DatosPersonalesAfiliadoBiessDaoEjb() {
		super(DatosPersonalesAfiliadoBiess.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.DatosPersonalesAfiliadoDao#consultarPorCedula(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<DatosPersonalesAfiliadoBiess> consultarPorCedula(String cedula) {
		final String NAMED_QUERY = "DatosPersonalesAfiliadoBiess.consultarPorCedula";
		Query query = em.createNamedQuery(NAMED_QUERY);
		query.setParameter("cedula", cedula);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.dao.DatosPersonalesAfiliadoBiessDao#actualizarDiscapacitado(java.lang.String, boolean)
	 */
	public int actualizarDiscapacitado(String cedula, boolean discapacitado) {
		final String SQL_QUERY = " UPDATE CRE_DATOSPERSONALES_TBL SET DP_DISCAPACITADO = :discapacitado WHERE DP_CEDULA = :cedula ";
		Query query = em.createNativeQuery(SQL_QUERY);
		String isDiscapacitado = true == discapacitado ? "SI" : "NO";
		query.setParameter("discapacitado", isDiscapacitado);
		query.setParameter("cedula", cedula);

		return query.executeUpdate();
	}

}
