/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.PrestamoInformacionDetalleDao;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacionDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Implementación del DAO PrestamoInformacionDetalleDao. </b>
 * 
 */
@Stateless
public class PrestamoInformacionDetalleDaoImpl extends GenericEjbDao<PrestamoInformacionDetalle, Long> implements
		PrestamoInformacionDetalleDao {

	public PrestamoInformacionDetalleDaoImpl() {
		super(PrestamoInformacionDetalle.class);
	}

	/*
	 * @see
	 * ec.gov.iess.creditos.dao.PrestamoInformacionDetalleDao#obtenerInformacionDetallePorPK(ec.gov.iess.creditos.modelo
	 * .persistencia.pk.PrestamoPk)
	 */
	@SuppressWarnings("unchecked")
	public List<PrestamoInformacionDetalle> obtenerInformacionDetallePorPK(PrestamoPk prestamoPk) {
		Query query = em.createNamedQuery("PrestamoInformacionDetalle.obtenerInformacionDetallePorPK");
		query.setParameter("codprecla", prestamoPk.getCodprecla());
		query.setParameter("codpretip", prestamoPk.getCodpretip());
		query.setParameter("numpreafi", prestamoPk.getNumpreafi());
		query.setParameter("ordpreafi", prestamoPk.getOrdpreafi());

		return query.getResultList();
	}

}
