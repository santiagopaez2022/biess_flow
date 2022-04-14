package ec.gov.iess.creditos.pq.servicio;


import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.LiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.CambiarEstadoLiquidacionHistoricoException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnDividendoException;
import ec.gov.iess.creditos.pq.excepcion.MasDeUnDividendoHistoricoException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteDividendoException;
import ec.gov.iess.creditos.pq.excepcion.NoExisteDividendoHistoricoException;

@Local
public interface CancelaCreditoServicio {
	public void cancelarCredito(Prestamo prestamo);
	public void cancelaLiquidacionPrestamo(LiquidacionPrestamo liquidacionPrestamo) throws CambiarEstadoLiquidacionHistoricoException, NoExisteDividendoHistoricoException, MasDeUnDividendoHistoricoException, NoExisteDividendoException, MasDeUnDividendoException;

}
