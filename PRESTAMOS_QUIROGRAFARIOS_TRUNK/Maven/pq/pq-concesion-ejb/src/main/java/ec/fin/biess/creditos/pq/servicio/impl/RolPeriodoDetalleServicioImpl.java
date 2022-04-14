package ec.fin.biess.creditos.pq.servicio.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.dao.RolPeriodoDetalleDao;
import ec.fin.biess.creditos.pq.servicio.RolPeriodoDetalleServicioLocal;

/**
 * Implementacion de la Interfaz local para el servicio de RolPeriodoDetalle
 * 
 * @author christian.gomez
 * 
 */
@Stateless
public class RolPeriodoDetalleServicioImpl implements
		RolPeriodoDetalleServicioLocal {

	@EJB
	private RolPeriodoDetalleDao rolPeriodoDetalleDao;

	/**
	 * Devuelve la suma de los valores de los detalles encontrados en base al
	 * filtro creado por los parámetros
	 * 
	 * @param identificacion
	 *            Identificacion del jubilado/pensionista
	 * @param anio
	 *            Anio a buscar en los registros
	 * @param mes
	 *            Mes a buscar en los registros
	 * @param rubro
	 *            Tipo de rubro a buscar en los registros
	 * @return {@link BigDecimal}
	 */
	public BigDecimal sumarValorPorIdentificacionAnioMesRubro(
			String identificacion, Long anio, Long mes, Long rubro) {
		return rolPeriodoDetalleDao.sumarValorPorIdentificacionAnioMesRubro(
				identificacion, anio, mes, rubro);
	}

}
