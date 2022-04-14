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
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.LiquidacionPrestamoDao;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Sep 19, 2007]
 * @author pablo
 */
@Stateless
public class LiquidacionPrestamoDaoImpl 
extends GenericEjbDao<LiquidacionPrestamo, Long> 
implements LiquidacionPrestamoDao {

	private static final String contarPorNumLiq = 
	"SELECT count(*) FROM LiquidacionPrestamo LP "+
	" WHERE  LP.numeroLiquidacion = :ainnumliqpre";
	
	private static final String buscarPorNumLiq = 
		"SELECT LP FROM LiquidacionPrestamo LP "+
		" WHERE  LP.numeroLiquidacion = :ainnumliqpre";
	
	public LiquidacionPrestamoDaoImpl() {
		super(LiquidacionPrestamo.class);
	}

	@SuppressWarnings("unchecked")
	public List<LiquidacionPrestamo> obtenerLiquidacionPorPrestamoYEstado(PrestamoPk prestamoPk,
			EstadoLiquidacion estadoLiquidacion) {
		String sql = "select l from LiquidacionPrestamo l where l.estadoLiquidacion = :estado and l.prestamo.prestamoPk = :prestamoPk";
		Query query = em.createQuery(sql);
		query.setParameter("estado", estadoLiquidacion);
		query.setParameter("prestamoPk", prestamoPk);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<LiquidacionPrestamo> obtenerLiquidacionPorAfiliadoYEstado(String numeroAfiliado,
			EstadoLiquidacion estadoLiquidacion) {
		String sql = "select l from LiquidacionPrestamo l where l.estadoLiquidacion = :estado and l.prestamo.numafi = :numafi";
		Query query = em.createQuery(sql);
		query.setParameter("estado", estadoLiquidacion);
		query.setParameter("numafi", numeroAfiliado);
		return query.getResultList();
	}
	
	public Long contarPorNumLiq(Long numeroLiquidacion)
	{
		Query query = em.createQuery(contarPorNumLiq);
		query.setParameter("ainnumliqpre", numeroLiquidacion);
		return (Long)query.getSingleResult();
	}
	
	public LiquidacionPrestamo buscarPorNumLiq(Long numeroLiquidacion)
	{
		Query query = em.createQuery(buscarPorNumLiq);
		query.setParameter("ainnumliqpre", numeroLiquidacion);
		return (LiquidacionPrestamo)query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.LiquidacionPrestamoDao#buscarLiquidacionPorNumeroTipoEstado(java.lang.Long,
	 * java.lang.String, ec.gov.iess.creditos.enumeracion.EstadoLiquidacion)
	 */
	@Override
	public LiquidacionPrestamo buscarLiquidacionPorNumeroTipoEstado(Long numeroLiquidacion, String tipoLiquidacion,
			EstadoLiquidacion estadoLiquidacion) {
		Query query = em.createNamedQuery("LiquidacionPrestamo.buscarLiquidacionPorNumeroTipoEstado");
		query.setParameter("numeroLiquidacion", numeroLiquidacion);
		query.setParameter("tipoLiquidacion", tipoLiquidacion);
		query.setParameter("estadoLiquidacion", estadoLiquidacion);
		
		LiquidacionPrestamo liquidacionPrestamo = null;
		try {
			liquidacionPrestamo = (LiquidacionPrestamo) query.getSingleResult();
		} catch (NoResultException e) {
			liquidacionPrestamo = null;
		}
		
		return liquidacionPrestamo;
	}

}