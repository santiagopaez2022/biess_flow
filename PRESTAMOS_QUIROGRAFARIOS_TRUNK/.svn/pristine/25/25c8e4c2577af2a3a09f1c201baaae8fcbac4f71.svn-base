/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.SaldoLiquidacionPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoLiquidacionPrestamoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.2 $ [Sep 24, 2007]
 * @author pablo
 */
@Stateless
public class SaldoLiquidacionPrestamoDaoImpl extends
		GenericEjbDao<SaldoLiquidacionPrestamo, SaldoLiquidacionPrestamoPk>
		implements SaldoLiquidacionPrestamoDao {

	public SaldoLiquidacionPrestamoDaoImpl() {
		super(SaldoLiquidacionPrestamo.class);
	}

	@SuppressWarnings("unchecked")
	public List<SaldoLiquidacionPrestamo> obtenerPorPrestamoYEstados(
			PrestamoPk prestamoPk, List<String> estados) {
		String sql = "select s from SaldoLiquidacionPrestamo s where "
				+ " s.dividendoPrestamo.prestamo.prestamoPk = :prestamoPk "
				+ " and s.dividendoPrestamo.estadoDividendoPrestamo.codestdiv in (:estados)";
		Query query = em.createQuery(sql);
		query.setParameter("prestamoPk", prestamoPk);
		query.setParameter("estados", estados);
		return query.getResultList();
	}

}