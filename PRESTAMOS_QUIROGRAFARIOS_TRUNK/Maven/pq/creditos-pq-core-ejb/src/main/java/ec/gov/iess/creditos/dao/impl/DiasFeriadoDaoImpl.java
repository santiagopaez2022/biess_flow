package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.DiasFeriadoDao;
import ec.gov.iess.creditos.modelo.persistencia.DiasFeriado;
import ec.gov.iess.dao.ejb.GenericEjbDao;

@Stateless
public class DiasFeriadoDaoImpl  extends
GenericEjbDao<DiasFeriado, Long> implements DiasFeriadoDao{

	public DiasFeriadoDaoImpl() {
		super(DiasFeriado.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DiasFeriado obtenerPorAnioMesDiaMod(Long anio, Long mes, Long dia, String modulo){
		DiasFeriado diasFeriado = null;
		
		try{
			Query q = em.createNamedQuery("DiasFeriado.obtenerPorAnioMesDiaMod");
			q.setParameter("anio", anio);
			q.setParameter("mes", mes);
			q.setParameter("dia", dia);
			q.setParameter("modulo", modulo);
			diasFeriado = (DiasFeriado)q.getSingleResult();
		}catch (  NoResultException e ) {
			diasFeriado = null;
		}
		return diasFeriado;
	}
	
	/*
	 * 
	 * 
	 
	 public List<DetalleSolicitud> consultarPorFechaSolicitudPago(Date desde,
			Date hasta) {
		List<DetalleSolicitud> lista = new ArrayList<DetalleSolicitud>();
		if (desde == null && hasta == null) {
			lista = em.createNamedQuery(
					"DetalleSolicitud.consultarPorFechaSolicitudPagoNula")
					.getResultList();

		} else {
			if (desde == null) {
				Calendar inicio = Calendar.getInstance();
				inicio.set(Calendar.YEAR, 1900);
				desde = inicio.getTime();
			}
			if (hasta == null) {
				hasta = new Date();
			}

			Query q = em
					.createNamedQuery("DetalleSolicitud.consultarPorFechaSolicitudPagoNula");
			q.setParameter("desde", desde);
			q.setParameter("hasta", hasta);
			lista = q.getResultList();
		}

		return lista;
	}
	 
	 * 
	 */

}
