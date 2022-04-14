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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import ec.gov.iess.creditos.dao.ComprobantePagoDao;
import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.excepcion.DebitoAutomaticoExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.ComprobantePago;
import ec.gov.iess.creditos.modelo.persistencia.pk.ComprobantePagoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.1 $ [Sep 19, 2007]
 * @author pablo
 */
@Stateless
public class ComprobantePagoDaoImpl extends GenericEjbDao<ComprobantePago, ComprobantePagoPk>
		implements ComprobantePagoDao {

	private static final String TAG_CEDULA = "cedula";

	public ComprobantePagoDaoImpl() {
		super(ComprobantePago.class);
	}

	@Override
	public Long contarPorPrestamoTipoYEstado(final PrestamoPk prestamoPk, final String tipo,
			final List<EstadoComprobantePago> listaEstados) {
		final String sql = "select count(*) from ComprobantePago c where " + "c.prestamo.prestamoPk = :prestamoPk and "
				+ "c.pk.codTipComPag = :tipo and c.estadoComprobante in (:estados)";
		final Query query = em.createQuery(sql);
		query.setParameter("prestamoPk", prestamoPk);
		query.setParameter("tipo", tipo);
		query.setParameter("estados", listaEstados);
		return (Long) query.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ComprobantePago> obtenerPorLiquidacionYEstados(final Long numeroLiquidacion,
			final List<EstadoComprobantePago> estadosComprobante) {
		final String sql = "select c from ComprobantePago c where c.liquidacion.numeroLiquidacion = :numeroLiquidacion "
				+ "and c.estadoComprobante in (:estados)";
		final Query query = em.createQuery(sql);
		query.setParameter("numeroLiquidacion", numeroLiquidacion);
		query.setParameter("estados", estadosComprobante);
		final List<ComprobantePago> lista = query.getResultList();
		return lista;
	}

	@Override
	public Long contarPorAfiliadoEstadoPTipoEstado(final String numeroAfiliado, final List<String> estadosPrestamo,
			final List<String> tiposComprobante, final List<EstadoComprobantePago> estadosComprobante) {
		final String sql = "select count(*) from ComprobantePago c where " + "c.numeroAfiliado = :numeroAfiliado "
				+ " and c.prestamo.estadoPrestamo.codestpre in (:estadosPrestamo)"
				+ " and c.pk.codTipComPag in (:tiposComprobante) "
				+ " and c.estadoComprobante in (:estadosComprobante)";
		final Query query = em.createQuery(sql);
		query.setParameter("numeroAfiliado", numeroAfiliado);
		query.setParameter("estadosPrestamo", estadosPrestamo);
		query.setParameter("tiposComprobante", tiposComprobante);
		query.setParameter("estadosComprobante", estadosComprobante);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long contarPorPrestamoEstadoPTipoEstado(final PrestamoPk prestamoPk, final List<String> estadosPrestamo,
			final List<String> tiposComprobante, final List<EstadoComprobantePago> estadosComprobante) {
		final String sql = "select count(*) from ComprobantePago c where " + "c.prestamo.prestamoPk = :prestamoPk "
				+ " and c.prestamo.estadoPrestamo.codestpre in (:estadosPrestamo)"
				+ " and c.pk.codTipComPag in (:tiposComprobante) "
				+ " and c.estadoComprobante in (:estadosComprobante)";
		final Query query = em.createQuery(sql);
		query.setParameter("prestamoPk", prestamoPk);
		query.setParameter("estadosPrestamo", estadosPrestamo);
		query.setParameter("tiposComprobante", tiposComprobante);
		query.setParameter("estadosComprobante", estadosComprobante);
		return (Long) query.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ComprobantePago> obtenerPorPrestamoEstadoPTipoEstado(final PrestamoPk prestamoPk,
			final List<String> estadosPrestamo, final List<String> tiposComprobante,
			final List<EstadoComprobantePago> estadosComprobante) {
		final String sql = "select c from ComprobantePago c where " + "c.prestamo.prestamoPk = :prestamoPk "
				+ " and c.prestamo.estadoPrestamo.codestpre in (:estadosPrestamo)"
				+ " and c.pk.codTipComPag in (:tiposComprobante) "
				+ " and c.estadoComprobante in (:estadosComprobante)";
		final Query query = em.createQuery(sql);
		query.setParameter("prestamoPk", prestamoPk);
		query.setParameter("estadosPrestamo", estadosPrestamo);
		query.setParameter("tiposComprobante", tiposComprobante);
		query.setParameter("estadosComprobante", estadosComprobante);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ComprobantePago> obtenerPorAfiliadoEstadoPTipoEstado(final String numeroAfiliado,
			final List<String> estadosPrestamo, final List<String> tiposComprobante,
			final List<EstadoComprobantePago> estadosComprobante) {
		final String sql = "select c from ComprobantePago c where " + "c.numeroAfiliado = :numeroAfiliado "
				+ " and c.prestamo.estadoPrestamo.codestpre in (:estadosPrestamo)"
				+ " and c.pk.codTipComPag in (:tiposComprobante) "
				+ " and c.estadoComprobante in (:estadosComprobante)";
		final Query query = em.createQuery(sql);
		query.setParameter("numeroAfiliado", numeroAfiliado);
		query.setParameter("estadosPrestamo", estadosPrestamo);
		query.setParameter("tiposComprobante", tiposComprobante);
		query.setParameter("estadosComprobante", estadosComprobante);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean validarComprobantePorEstadoEMI(final Long nut, final String cedula) {
		boolean existeTransaccion = false;
		final Query query = em.createNativeQuery(
				"SELECT tr_estado FROM rec_transaccion_tbl  WHERE  tr_nut = :nut AND tr_cedula = :cedula AND tr_estado='EMI'");
		query.setParameter("nut", nut);
		query.setParameter(TAG_CEDULA, cedula);
		final List<String> transaccion = query.getResultList();
		if (!transaccion.isEmpty()) {
			existeTransaccion = true;
		}
		return existeTransaccion;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean validarComprobantePorEstados(final Long nut, final String cedula,
			final List<String> estadosComprobante) {
		boolean existeTransaccion = false;
		final Query query = em.createNativeQuery(
				"SELECT tr_estado FROM rec_transaccion_tbl  WHERE  tr_nut = :nut AND tr_cedula = :cedula AND tr_estado in (:estadosComprobante) and TR_IDTIPOTRANSACCION in ('16','17','21','26','34','29')");
		query.setParameter("nut", nut);
		query.setParameter(TAG_CEDULA, cedula);
		query.setParameter("estadosComprobante", estadosComprobante);
		final List<String> transaccion = query.getResultList();
		if (!transaccion.isEmpty()) {
			existeTransaccion = true;
		}
		return existeTransaccion;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ComprobantePago> obtenerComprobantePendPago(final String identificacion,
			final List<EstadoComprobantePago> estadosComprobante) {
		final String sql = "select c from ComprobantePago c where  c.numeroAfiliado=:identificacion "
				+ "and c.estadoComprobante in (:estados)";
		final Query query = em.createQuery(sql);
		query.setParameter("identificacion", identificacion);
		query.setParameter("estados", estadosComprobante);
		return query.getResultList();
	}

	/**
	 * REQ-444 N1.-
	 * 
	 * @param identificacion
	 * @param operacion
	 * @param nut
	 * @return
	 */
	public Boolean consultarDebitoAutomaticoPeriodo(String identificacion, Long operacion, Long nut) {

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		String periodo = formatter.format(date);
		StringBuilder query = new StringBuilder();
		query.append("SELECT count(1) FROM IESS_OWNER.cre_creditosdebitosac_t where CD_NUMAFI =:identificacion ");
		query.append(" AND CD_OPERACIONSAC = :operacion AND CD_NUT = :nut ");
		query.append(
				" AND SUBSTR(TO_CHAR(CD_FECHACARGA,'DD/MM/YYYY'),4)=:periodo AND CD_ESTADOAFECOPE in ('ENV','ADA','CDA')");
		final Query q = em.createNativeQuery(query.toString());
		q.setParameter("identificacion", identificacion);
		q.setParameter("operacion", operacion);
		q.setParameter("nut", nut);
		q.setParameter("periodo", periodo);
		BigDecimal resultado = (BigDecimal) q.getSingleResult();
		if (resultado.signum() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Boolean confirmarAfectacionTransaccion(final Long idTransaccion, final Long nut, final String cedula,
			final Long operacionSac) {
		boolean existeTransaccion = true;
		final Query query = em.createNativeQuery(
				"SELECT tr_estado FROM rec_transaccion_tbl  WHERE tr_nut = :nut AND tr_cedula = :cedula AND tr_estado ='AFE' and TR_IDGAF =:operacionSac  and TR_IDTIPOTRANSACCION in ('16','17','21','26','34','29','30')");
		query.setParameter("nut", nut);
		query.setParameter(TAG_CEDULA, cedula);
		query.setParameter("operacionSac", operacionSac);
		final List<String> transaccion = query.getResultList();
		if (transaccion.isEmpty()) {
			existeTransaccion = false;
		}
		return existeTransaccion;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String consultarDebitoAutomatico(String identificacion, Long operacion, Long nut)
			throws DebitoAutomaticoExcepcion {

		try {
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");

			String periodo = formatter.format(date);
			StringBuilder query = new StringBuilder();
			query.append("SELECT cd_estadoafecope FROM PQ_OWNER.cre_creditosdebitosac_t where CD_NUMAFI =:identificacion ");
			query.append(" AND CD_OPERACIONSAC = :operacion AND CD_NUT = :nut ");
			query.append(" AND  extract(month from CD_FECHACARGA) = extract(month from TO_DATE(:periodo, 'MM-YYYY'))");
			query.append(" AND  extract(year from CD_FECHACARGA) = extract(year from TO_DATE(:periodo, 'MM-YYYY'))");

			Query q = em.createNativeQuery(query.toString());
			q.setParameter("identificacion", identificacion);
			q.setParameter("operacion", operacion);
			q.setParameter("nut", nut);
			q.setParameter("periodo", periodo);

			List<String> result = q.getResultList();
			if(result.size()>0) {
				String estado = result.get(result.size() -1);				
				return estado;
			}else {
				return "";
			}

		} catch (NoResultException e) {
			
			throw new DebitoAutomaticoExcepcion("ERROR al consultar el débito automático : " + identificacion);
		}

	}

	public Boolean estadoBanderaDebito() throws DebitoAutomaticoExcepcion {
		try {
			StringBuilder query = new StringBuilder();

			query.append(" select count(1)  from PQ_OWNER.cre_detcatalogopq_tbl b  ");
			query.append(
					" where  b.dp_coddetcatalogo = 'FLAGPLA' AND    b.dp_valcardetcatalogo = 'MENSUAL' and  b.dp_valnumdetcatalogo = 1");

			final Query q = em.createNativeQuery(query.toString());

			BigDecimal respuesta = (BigDecimal) q.getSingleResult();

			if (respuesta.intValue() > 0) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} catch (NoResultException e) {
			throw new DebitoAutomaticoExcepcion("ERROR EN LA EJECUCION");
		}

	}
	
	
	public Boolean consultaDebitoENV(String identificacion, Long operacion, Long nut)
			throws DebitoAutomaticoExcepcion {

		try {
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");

			String periodo = formatter.format(date);
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(1) FROM PQ_OWNER.cre_creditosdebitosac_t where CD_NUMAFI =:identificacion ");
			query.append(" AND CD_OPERACIONSAC = :operacion AND CD_NUT = :nut ");
			query.append(" AND  extract(month from CD_FECHACARGA) = extract(month from TO_DATE(:periodo, 'MM-YYYY'))");
			query.append(" AND  extract(year from CD_FECHACARGA) = extract(year from TO_DATE(:periodo, 'MM-YYYY'))");
			query.append(" AND nvl(cd_estadoproceso,'SDE')='SDE'");
			

			final Query q = em.createNativeQuery(query.toString());
			q.setParameter("identificacion", identificacion);
			q.setParameter("operacion", operacion);
			q.setParameter("nut", nut);
			q.setParameter("periodo", periodo);

			BigDecimal respuesta = (BigDecimal) q.getSingleResult();

			if (respuesta.intValue() > 0) {
				return Boolean.TRUE; //true no permite generar
			} else {
				return Boolean.FALSE; //false  permite generar
			}

		} catch (NoResultException e) {
			
			throw new DebitoAutomaticoExcepcion("ERROR al consultar el débito automático : " + identificacion);
		}

	}
	
}
