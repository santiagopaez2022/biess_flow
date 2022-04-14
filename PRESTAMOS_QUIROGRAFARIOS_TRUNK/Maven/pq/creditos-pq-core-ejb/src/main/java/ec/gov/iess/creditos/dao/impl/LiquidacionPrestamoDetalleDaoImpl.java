package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.LiquidacionPrestamoDetalleDao;
import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamoDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.LiquidacionPrestamoDetallePk;
import ec.gov.iess.dao.ejb.GenericEjbDao;

@Stateless
public class LiquidacionPrestamoDetalleDaoImpl  
extends GenericEjbDao<LiquidacionPrestamoDetalle, LiquidacionPrestamoDetallePk> 
implements LiquidacionPrestamoDetalleDao{

	public LiquidacionPrestamoDetalleDaoImpl() {
		super(LiquidacionPrestamoDetalle.class);
	}
	
	public List<LiquidacionPrestamoDetalle> buscarPorNumLiqNumAfiCodPreOrdPreCodTip(
			Long numLiqPre, Long numPreAfi, Long codigoPreCla,
			Long ordPreAfi, Long codPreTip){
		String consultaPorNumLiqNumAfiCodPreOrdPreCodTip =
			"SELECT D"+
			" FROM LiquidacionPrestamoDetalle D"+
			" WHERE D.pk.numLiqPre = :numLiqPre"+
			" AND D.numPreAfi = :numPreAfi"+
			" AND D.codPreCla = :codPreCla"+
			" AND D.ordPreAfi = :ordPreAfi"+
			" AND D.codPreTip = :codPreTip"+
			" AND D.liquidacionPrestamo.numeroLiquidacion = :numLiqPre"+
			//" AND D.numLiqPre = C.numLiqPre"+
			" AND D.liquidacionPrestamo.tipoLiquidacion = 'PRE'";
		Query query = em.createQuery(consultaPorNumLiqNumAfiCodPreOrdPreCodTip);
		query.setParameter("numLiqPre", numLiqPre);
		query.setParameter("numPreAfi", numPreAfi);
		query.setParameter("codPreCla", codigoPreCla);
		query.setParameter("ordPreAfi", ordPreAfi);
		query.setParameter("codPreTip", codPreTip);		
		return (List<LiquidacionPrestamoDetalle>)query.getResultList();
	}
	
}
