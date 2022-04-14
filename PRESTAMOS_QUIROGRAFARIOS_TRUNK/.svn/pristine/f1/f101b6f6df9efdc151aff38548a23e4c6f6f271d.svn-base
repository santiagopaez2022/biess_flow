/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.TasaInteresDetalleDao;
import ec.gov.iess.creditos.excepcion.TasaInteresDaoException;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.TasaInteresDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.TasaInteresDetallePK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class TasaInteresDetalleDaoImpl extends
		GenericEjbDao<TasaInteresDetalle, TasaInteresDetallePK> implements
		TasaInteresDetalleDao {

	LoggerBiess log = LoggerBiess.getLogger(TasaInteresDetalleDaoImpl.class);

	public TasaInteresDetalleDaoImpl() {
		super(TasaInteresDetalle.class);
	}

	public BigDecimal consultaRangoFechasInicialTipoTasaInteres(
			String idtasaInteres, Date fechaDesde, Date fechaHasta)
			throws TasaInteresDaoException {
		log.debug(" Consulta rango fechas de registro");

		Query query = em
				.createNativeQuery("SELECT   count(1) "
						+ " FROM kscretinttasdet k "
						+ " WHERE k.codtasint = :idtasaInteres "
						+ " AND k.FECINITASINT >= to_date(:fechaDesde,'dd/mm/yyyy')"
						+ " and k.FECFINTASINT <= to_date(:fechaHasta,'dd/mm/yyyy')");

		try {

			String fechaDesdeString = (new SimpleDateFormat("dd/MM/yyyy"))
					.format(fechaDesde);
			String fechaHastaString = (new SimpleDateFormat("dd/MM/yyyy"))
					.format(fechaHasta);
			log.debug(" idtasaInteres : " + idtasaInteres);
			log.debug(" fechaDesdeString : " + fechaDesdeString);
			log.debug(" fechaHastaString : " + fechaHastaString);

			query.setParameter("idtasaInteres", idtasaInteres);
			query.setParameter("fechaHasta", fechaHastaString);
			query.setParameter("fechaDesde", fechaDesdeString);

		} catch (Exception e) {
			log.error("Error al parsear las fechas", e);
			throw new TasaInteresDaoException(" Error al parsear las fechas");
		}

		return (BigDecimal) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TasaInteresDetalle> consultaRangoFechasTipoTasaInteres(
			String idtasaInteres, Date fecha) throws TasaInteresExcepcion {

		log.debug(" Consulta rango fechas de registro");

		Query query = em
				.createNamedQuery("TasaInteresDetalle.rangoFechasTipoInteres");
		query.setParameter("idTasaInteres", idtasaInteres);

		try {
			query.setParameter("fecha", (new SimpleDateFormat("dd/MM/yyyy"))
					.format(fecha));
		} catch (Exception e) {
			log.error("Error al parsear las fechas", e);
			throw new TasaInteresExcepcion(" Error al parsear las fechas");
		}

		return query.getResultList();
	}

	public BigDecimal consultaPromedioRangoFechasInicialTipoTasaInteres(
			String idtasaInteres, Date fechaDesde, Date fechaHasta)
			throws TasaInteresExcepcion {

		Query query = em
		
				.createNativeQuery("select avg(k.portasint) from kscretinttasdet k" +
						" where k.CODTASINT = :idTasaInteres " +
						" and k.FECINITASINT >= to_date(:fechaDesde,'dd/mm/yyyy')" +
						" and k.FECFINTASINT <= to_date(:fechaHasta,'dd/mm/yyyy')" +
						" order by k.FECINITASINT");

		query.setParameter("idTasaInteres", idtasaInteres);
		try {
			query.setParameter("fechaDesde",
					(new SimpleDateFormat("dd/MM/yyyy")).format(fechaDesde));
			query.setParameter("fechaHasta",
					(new SimpleDateFormat("dd/MM/yyyy")).format(fechaHasta));
		} catch (Exception e) {
			log.error("Error al parsear las fechas", e);
			throw new TasaInteresExcepcion(" Error al parsear las fechas");
		}

		return (BigDecimal) query.getSingleResult();
	}
	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal consultaInteresMora(
			String idtasaInteres, Date fecha)
			throws TasaInteresExcepcion {
/*
		Query query = em.createNamedQuery("TasaInteresDetalle.calculoInteresMora");

		query.setParameter("idTasaInteres", idtasaInteres);
		query.setParameter("fecha",fecha);
		TasaInteresDetalle tas=new TasaInteresDetalle();
		try{
			tas=(TasaInteresDetalle)query.getSingleResult();
		}catch(Exception e){
			
			System.out.print(e.getMessage());
			
		}
		
		return tas;*/
		Query query = em
		.createNativeQuery("select portasint "
		+ "from kscretinttasdet k "
		+ "where k.CODTASINT = :idTasaInteres "
		+ "and to_date(:fecha,'dd/mm/yyyy') "
		+ "between k.FECINITASINT AND k.FECFINTASINT");		

		BigDecimal d=new BigDecimal(0);
		try {
			query.setParameter("idTasaInteres", idtasaInteres);
			query.setParameter("fecha", (new SimpleDateFormat("dd/MM/yyyy"))
			.format(fecha));
			d=(BigDecimal)query.getSingleResult();
		} catch (Exception e) {
			log.error("Error al parsear las fechas", e);
			throw new TasaInteresExcepcion(" Error al parsear las fechas");
		}
		return d;
	}
	
}
