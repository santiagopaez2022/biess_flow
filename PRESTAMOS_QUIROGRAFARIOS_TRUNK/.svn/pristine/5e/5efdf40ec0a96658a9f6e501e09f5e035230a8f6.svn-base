package ec.gov.iess.creditos.pq.servicio;
import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Remote;

import ec.gov.iess.creditos.enumeracion.EstadoCredito;
import ec.gov.iess.creditos.enumeracion.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.excepcion.CalculoLiquidacionExcepcion;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.dto.CalculoLiquidacion;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.CalculoInteresMoraException;
import ec.gov.iess.creditos.pq.excepcion.GenerarLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarCabeceraLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarDetalleLiquidacionException;
import ec.gov.iess.creditos.pq.excepcion.InsertarHistoricoLiquidacionException;

@Remote
public interface LiquidacionGenericaBigServicioRemote {

    public BigDecimal calculoTasaInteres(String idtasaInteres, Date fecha) 
    throws TasaInteresExcepcion;

    public CalculoLiquidacion calculoLiquidacion(PrestamoPk id, TipoLiquidacion tipoLiquidacion)
	 throws CalculoLiquidacionExcepcion;

	public BigDecimal calculoInteresMora(String idtasaInteres, Date fecha,BigDecimal valtotdiv) 
    throws CalculoInteresMoraException;

	public Long generarLiquidacion(Prestamo pre,
			TipoLiquidacion tipoLiquidacion,
			EstadoLiquidacion estadoLiquidacion,
			EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo) throws GenerarLiquidacionException ;
	
	public Long insertarCabeceraLiquidacion(Prestamo pre, String tipoLiquidacion,EstadoLiquidacion estadoLiquidacion)
	throws InsertarCabeceraLiquidacionException;
	
	public void insertarHistoricoLiquidacion(Long numeroLiquidacion, String estadoLiquidacion,String tipoLiquidacion)
	throws InsertarHistoricoLiquidacionException;	
	
	public void insertarDetalleLiquidacion(Prestamo prestamo,
			Long numeroLiquidacion, TipoLiquidacion tipoLiquidacion
		, EstadoCredito estadoCredito,EstadoDividendoPrestamo nuevoEstadoDividendo)
			throws InsertarDetalleLiquidacionException;

}
