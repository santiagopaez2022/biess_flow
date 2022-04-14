package ec.gov.iess.creditos.pq.excepcion;

import javax.ejb.ApplicationException;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.servicio.impl.DividendoPrestamoServicioImpl;
/**
 * Clase exception para Cancela Liquidacion
 * @author PAlvarez
 * 
 */
@ApplicationException(rollback = true)
public class CancelaLiquidacionException extends Exception {

	private static final long serialVersionUID = -7891990970038378853L;
	LoggerBiess log = LoggerBiess.getLogger(DividendoPrestamoServicioImpl.class);

	public CancelaLiquidacionException() {
	}

	public CancelaLiquidacionException(String arg0) {
		super(arg0);
	}

	public CancelaLiquidacionException(Throwable arg0) {
		super(arg0);
	}

	public CancelaLiquidacionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CancelaLiquidacionException(int codigoError,String mensajeCustomizado) throws CancelaLiquidacionException {
		String mensajeExcepcion=null;
		if(mensajeCustomizado==null){
			mensajeCustomizado="";
		}
        switch(codigoError){
            case 1: mensajeExcepcion="NO SE HA PODIDO CANCELAR EL PRÉSTAMO ACTUAL, FAVOR REINTENTE EN 48 HORAS.. " +
            		mensajeCustomizado;
            break;	           
        }
        log.info("Se detectô una excepcion controlada: "+ mensajeExcepcion);
	throw new CancelaLiquidacionException(mensajeExcepcion);
	}
}