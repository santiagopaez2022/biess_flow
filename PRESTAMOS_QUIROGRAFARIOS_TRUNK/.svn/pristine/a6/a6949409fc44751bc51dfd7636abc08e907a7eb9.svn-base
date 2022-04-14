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

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.dao.PrestacionesBiessDao;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Ejb para consultar residuos de cesantias de tipo RESIVM y RENIVM
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PrestacionesBiessDaoEjb extends GenericEjbDao<Object, Long> implements PrestacionesBiessDao {

	public PrestacionesBiessDaoEjb() {
		super(Object.class);
	}

	/* (non-Javadoc)
	 * @see ec.fin.biess.creditos.pq.dao.PrestacionesBiessDao#consultarPrestacionesHL(java.lang.String)
	 */
//	@SuppressWarnings("unchecked")
//	@Deprecated
//	public List<Prestacion> consultarPrestacionesHL(String cedula) {
//		try {
//			final String SQL_QUERY = " select rownum id, p.SECUENCIAL_PRESTACION secuencialPrestacion, 'HL' tipoSistema, " +
//				" p.asegurado asegurado, B.identificacion_beneficiario beneficiario, tipo_prestacion tipoPrestacion, " +
//				" tipo_seguro tipoSeguro, DECODE(b.FECHA_FINAL_PENSION,NULL,100,0) porcentajeIncapacidad, " +
//				" b.FECHA_FINAL_PENSION fechaTopePension, " +
//				" TIPO_BENEFICIARIO tipoParentezco, "+
//				" (nvl(b.VALOR_PENSION,0) + nvl(b.VALOR_AUMENTOS,0) + nvl(b.VALOR_ULTIMO_AUMENTO,0) +  nvl(b.VALOR_AUMENTO_GOBIERNO,0) " + 
//				" + nvl(b.VALOR_AUMENTO_CD143,0) + nvl(b.VALOR_AJUSTE,0) + nvl(b.VALOR_REVALORIZACION,0) + nvl(b.valor_nivelacion_renta,0) " +
//				" + nvl((select sum(valor) " +
//				"		 from  rol_periodo_detalle r " +
//				"		 where b.SECUENCIAL_PRESTACION = R.SECUENCIAL_PRESTACION " +
//				"		 and b.IDENTIFICACION_BENEFICIARIO = R.IDENTIFICACION_BENEFICIARIO " +
//				"		 and r.rubro in (295,296) " +
//				"		 and r.ANO = to_char(sysdate,'yyyy') " +
//				"		 and r.MES = to_char(sysdate,'mm')),0) ) ingresos, " +
//				" nvl((select sum(valor) " +
//				"        from  rol_periodo_detalle r " +
//				"        where b.SECUENCIAL_PRESTACION = R.SECUENCIAL_PRESTACION " +
//				"        and b.IDENTIFICACION_BENEFICIARIO = R.IDENTIFICACION_BENEFICIARIO " +
//				"        and r.rubro = 11 " +
//				"        and r.ANO = to_char(sysdate,'yyyy') " +
//				"        and r.MES = to_char(sysdate,'mm')),0) valorRetencionesJudiciales " +
//				" from prestaciones p, beneficiarios b " +
//				" where b.SECUENCIAL_PRESTACION = p.SECUENCIAL_PRESTACION  " +
//				" and B.ESTADO_VIGENCIA ='A' " +
//				" and p.ESTADO_PRESTACION='A' " +
//				" and  B.identificacion_beneficiario= :cedula " +
//				" and b.FECHA_INGRESO_ROL <= sysdate ";			
//			Query query = em.createNativeQuery(SQL_QUERY);
//			query.setParameter("cedula", cedula);
//
//			return castToPrestacion(query.getResultList());
//		} catch (Exception e) {
//			return null;
//		}
//	}

	/**
	 * Metodo que convierte la lista de objectos a tipo Prestacion.
	 * 
	 * @param tmpList
	 * @return List<Prestacion>
	 */
	// private List<Prestacion> castToPrestacion(List<Object> tmpList) {
	// List<Prestacion> returnList = new ArrayList<Prestacion>();
	// Iterator<Object> iterator = tmpList.iterator();
	// while (iterator.hasNext()) {
	// Prestacion prestacion = new Prestacion();
	// Object[] obj = (Object[]) iterator.next();
	// prestacion.setId(((BigDecimal) obj[0]).longValue());
	//
	// prestacion.setSecuencialPrestacion(((BigDecimal) obj[1]).longValue());
	//
	// prestacion.setTipoSistema(String.valueOf(obj[2]));
	// prestacion.setAsegurado((String) obj[3]);
	// prestacion.setBeneficiario((String) obj[4]);
	// prestacion.setTipoPrestacion((String) obj[5]);
	// prestacion.setTipoSeguro((String) obj[6]);
	// prestacion.setPorcentajeIncapacidad(((BigDecimal) obj[7]).longValue());
	// prestacion.setFechaTopePension((Date) obj[8]);
	// prestacion.setTipoParentezco((String) obj[9]);
	// prestacion.setIngresos((BigDecimal) obj[10]);
	// prestacion.setValorRetencionesJudiciales((BigDecimal) obj[11]);
	// returnList.add(prestacion);
	// }
	//
	// return returnList;
	// }

}
