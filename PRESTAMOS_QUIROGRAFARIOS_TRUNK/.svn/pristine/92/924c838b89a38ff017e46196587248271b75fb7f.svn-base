/**
 * 
 */
package ec.fin.biess.creditos.pq.dao.ejb;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.biess.creditos.pq.dao.RolPeriodoDetalleDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.RolPeriodoDetalle;
import ec.fin.biess.creditos.pq.modelo.persistencia.RolPeriodoDetallePk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion de la Interfaz para consultar Detalles de Rol de Periodo
 * @author christian.gomez
 *
 */
@Stateless
public class RolPeriodoDetalleDaoEjb extends GenericEjbDao<RolPeriodoDetalle, RolPeriodoDetallePk> implements
		RolPeriodoDetalleDao {

	public RolPeriodoDetalleDaoEjb(){
		super(RolPeriodoDetalle.class);
	}

	/**
	 * Devuelve la suma de los valores de los detalles encontrados en base al filtro creado por los parámetros
	 * @param identificacion del usuario
	 * @param anio en el cual buscar
	 * @param mes en el cual buscar
	 * @param rubro tipo de rubro a buscar
	 * @return {@link BigDecimal}
	 */
	public BigDecimal sumarValorPorIdentificacionAnioMesRubro(String identificacion, Long anio, Long mes, Long rubro) {
		Query q = em.createNamedQuery("RolPeriodoDetalle.sumValorByIdentificacionAnioMesRubro");
		q.setParameter("identificacion", identificacion );
		q.setParameter("anio", anio);
		q.setParameter("mes", mes);
		q.setParameter("rubro", rubro);
		return (BigDecimal) q.getSingleResult();
	}
	
}
