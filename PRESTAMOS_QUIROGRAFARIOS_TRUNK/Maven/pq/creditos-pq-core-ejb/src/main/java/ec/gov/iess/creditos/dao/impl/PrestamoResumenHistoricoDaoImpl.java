/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PrestamoResumenHistoricoDao;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoResumenHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoResumenHistoricoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Implementación de la cuota de préstamos hipotecario. </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@Stateless
public class PrestamoResumenHistoricoDaoImpl extends GenericEjbDao<PrestamoResumenHistorico, PrestamoResumenHistoricoPk>
		implements PrestamoResumenHistoricoDao {

	LoggerBiess log = LoggerBiess.getLogger(PrestamoResumenHistoricoDaoImpl.class);

	public PrestamoResumenHistoricoDaoImpl() {
		super(PrestamoResumenHistorico.class);
	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.dao.PrestamoResumenHistoricoDao#consultarporpq(ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk)
	 */
	@SuppressWarnings("unchecked")
	public List<PrestamoResumenHistorico> consultarporpq(PrestamoPk pk) {

		Query query = em
		.createNativeQuery("select H.* from pq_owner.cre_crereshist_tbl H " +
				"JOIN  PQ_OWNER.CRE_MOTIVORECHAZO_TBL M ON H.CR_MOTIVORECHAZO = M.CRE_MOTIVORECHAZO_PK " +
				"where numpreafi = :numpre and ordpreafi = :ordpre and codpretip = :codpret and " +
				"codprecla = :codprec and CR_MOTIVORECHAZO is not null and CR_CEDULAFUNCIONARIO is not null",PrestamoResumenHistorico.class);

		query.setParameter("numpre", pk.getNumpreafi());
		query.setParameter("ordpre", pk.getOrdpreafi());
		query.setParameter("codpret", pk.getCodpretip());
		query.setParameter("codprec", pk.getCodprecla());
		
		return query.getResultList();
	}
}
