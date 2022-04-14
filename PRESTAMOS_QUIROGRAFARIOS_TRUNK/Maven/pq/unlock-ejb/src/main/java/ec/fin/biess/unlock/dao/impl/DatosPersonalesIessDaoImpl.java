package ec.fin.biess.unlock.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.fin.biess.unlock.dao.DatosPersonalesIessDao;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;

@Stateless
public class DatosPersonalesIessDaoImpl implements DatosPersonalesIessDao {

	@PersistenceContext(unitName = "unlock-PU")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see ec.fin.biess.unlock.dao.DatosPersonalesIessDao#getDatosPersonales(java.lang.String)
	 */
	@Override
	public DatosPersonales getDatosPersonales(String cedula) {
		final String SQL_QUERY = "select numafi, maiafi from kspcotafiliados where numafi = :numafi";
		Query query = em.createNativeQuery(SQL_QUERY);
		try {
			query.setParameter("numafi", cedula);
			return castToDatosPersonales(query.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Metodo que convierte un objecto a tipo DatosPersonales.
	 * 
	 * @param objresult
	 * @return DatosPersonales
	 */
	private DatosPersonales castToDatosPersonales(Object objresult) {
		DatosPersonales dp = new DatosPersonales();
		Object[] obj = (Object[]) objresult;
		dp.setCedula(String.valueOf(obj[0]));
		dp.setEmail(String.valueOf(obj[1]));

		return dp;
	}

}
