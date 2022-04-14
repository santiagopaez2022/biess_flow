/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.TipoPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.TipoPrestamo;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion TipoPrestamoProductoDao
 * 
 * @author edwin.maza
 * @version 1.0.0
 * 
 */
@Stateless
public class TipoPrestamoDaoImpl extends GenericEjbDao<TipoPrestamo, Long>
		implements TipoPrestamoDao {

	public TipoPrestamoDaoImpl() {
		super(TipoPrestamo.class);
	}

	/**
	 * @see ec.gov.iess.creditos.dao.TipoPrestamoDao#getListaTipoPrestamoProducto(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<TipoPrestamo> getListaTipoPrestamoProducto(String codigoModulo) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT tp FROM TipoPrestamo tp ");
			sql.append(" WHERE tp.codigoModulo=:codigoModulo");
			sql.append(" AND tp.indicadorHab=:indicadorHab");
			Query query = em.createQuery(sql.toString());
			query.setParameter("codigoModulo", codigoModulo);
			query.setParameter("indicadorHab", "1");
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * @see ec.gov.iess.creditos.dao.TipoPrestamoDao#obtenerTipoPrestamoPorIds(java
	 *      .util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<TipoPrestamo> obtenerTipoPrestamoPorIds(List<Long> idsTipos) {

		if (idsTipos == null || idsTipos.isEmpty()) {
			return new ArrayList<TipoPrestamo>();
		}

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT tp FROM TipoPrestamo tp ");
			sql.append(" WHERE tp.codigo in (:idsTipos)");
			Query query = em.createQuery(sql.toString());
			query.setParameter("idsTipos", idsTipos);

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
