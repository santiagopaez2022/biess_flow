package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoDetallePk;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * @author Daniel Cardenas
 * 
 */
@Local
public interface LiquidacionPrestamoDetalleDao extends
		GenericDao<LiquidacionPrestamoDetalle, LiquidacionPrestamoDetallePk>{	
	public List<LiquidacionPrestamoDetalle> buscarPorNumLiqNumAfiCodPreOrdPreCodTip(
			Long numLiqPre, Long numPreAfi, Long codigoPreCla,
			Long ordPreAfi, Long codPreTip);
}
