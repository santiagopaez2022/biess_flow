package ec.fin.biess.creditos.pq.dao;

import java.math.BigDecimal;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.modelo.persistencia.RolPeriodoDetalle;
import ec.fin.biess.creditos.pq.modelo.persistencia.RolPeriodoDetallePk;
import ec.gov.iess.dao.GenericDao;

/**
 * Interfaz para consultar Detalles de Rol de Periodo
 * @author christian.gomez
 *
 */
@Local
public interface RolPeriodoDetalleDao extends GenericDao<RolPeriodoDetalle, RolPeriodoDetallePk> {

	/**
	 * Devuelve la suma de los valores de los detalles encontrados en base al filtro creado por los parámetros
	 * @param identificacion del usuario
	 * @param anio en el cual buscar
	 * @param mes en el cual buscar
	 * @param rubro tipo de rubro a buscar
	 * @return {@link BigDecimal}
	 */
	public BigDecimal sumarValorPorIdentificacionAnioMesRubro(String identificacion, Long anio, Long mes, Long rubro);
}
