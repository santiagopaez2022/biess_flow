/**
 * 
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.GarantiaPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.GarantiaPrestamoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class GarantiaPrestamoDaoImpl extends
		GenericEjbDao<GarantiaPrestamo, GarantiaPrestamoPK> implements
		GarantiaPrestamoDao {
	
	/**
	 * @param type
	 */
	public GarantiaPrestamoDaoImpl() {
		super(GarantiaPrestamo.class);
	}
	
	/**
 	* @author jmolina
 	* 
 	*/
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal obtenerDisponibleComprometidoFondosReservaPQ(String cedula) {
		String sql = "SELECT SUM(NVL(VALORCOMPROMETIDOGARPQCAP,0) ) "
				+ "FROM APORTES_PFR2 " + "WHERE CEDULA = :cedula "
				+ "AND ESTADOBLOQUEO  = :estadoBloqueo "
				+ "AND TIPOREGISTRO  IN (:tiposPermitidos) "
				+ "AND MARCADISPONIBLE = :marcaDisponible";

		List<String> tiposPermitidos = new ArrayList<String>();
		tiposPermitidos.add("APO");
		tiposPermitidos.add("REL");
		tiposPermitidos.add("CAP");

		Query query = em.createNativeQuery(sql);
		query.setParameter("cedula", cedula);
		query.setParameter("estadoBloqueo", "N");
		query.setParameter("tiposPermitidos", tiposPermitidos);
		query.setParameter("marcaDisponible", "NP");

		return (BigDecimal) query.getSingleResult();
	}


}
