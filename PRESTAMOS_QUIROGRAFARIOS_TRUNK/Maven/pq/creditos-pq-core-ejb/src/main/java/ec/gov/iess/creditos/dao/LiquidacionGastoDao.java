/**
 * 
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.LiquidacionGasto;
import ec.gov.iess.dao.GenericDao;

/**
 * @author rsambrano
 * 
 */
@Local
public interface LiquidacionGastoDao extends GenericDao<LiquidacionGasto, Long> {

	List<LiquidacionGasto> consultarSolicitudLiquidacion();

	LiquidacionGasto consultarSolicitudLiquidacionExistente(Long nut,
			String cedula);

	List<LiquidacionGasto> consultarSolicitudLiquidacionCC();

	List<LiquidacionGasto> consultarSolicitudLiquidacionCM();

	List<LiquidacionGasto> consultarSolicitudLiquidacionGenerada(
			List<Long> listaTiposSolicitud);

	BigDecimal obtenerCuotasRemanentes(Long nut);

	BigDecimal obtenerValorOriginal(Long nut);

}