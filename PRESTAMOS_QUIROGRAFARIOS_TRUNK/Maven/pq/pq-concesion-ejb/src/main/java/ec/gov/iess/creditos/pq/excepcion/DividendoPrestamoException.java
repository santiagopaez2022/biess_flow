package ec.gov.iess.creditos.pq.excepcion;

import java.io.PrintWriter;
import java.io.StringWriter;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.servicio.impl.CancelarLiquidacionServicioImp;
import ec.gov.iess.creditos.pq.util.MailSend;

public class DividendoPrestamoException extends Exception {

	private static final long serialVersionUID = -7891990970038378853L;
	private LoggerBiess log = LoggerBiess.getLogger(CancelarLiquidacionServicioImp.class);
	public DividendoPrestamoException() {
	}

	public DividendoPrestamoException(String arg0) {
		super(arg0);
	}

	public DividendoPrestamoException(Throwable arg0) {
		super(arg0);
	}

	public DividendoPrestamoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DividendoPrestamoException(int codigoError,String mensajeCustomizado) throws DividendoPrestamoException {
		String mensajeExcepcion=null;
		StringWriter sw = new StringWriter();
		if(mensajeCustomizado==null){
			mensajeCustomizado="";
		}
	        switch(codigoError){
	            case 1: mensajeExcepcion="SE SUSCITÓ UNA INCONSISTENCIA AL ACTUALIZAR EL DIVIDENDO NO PUEDE SER ACTUALIZADO YA QUE EL MISMO DEBE TENER UN ESTADO " +
	            		mensajeCustomizado;	 	            
				printStackTrace(new PrintWriter(sw));
	            MailSend.enviarMailInconsistenciaNovacionPQ(mensajeExcepcion+".\n\n"+sw.toString());
	            break;	   
	            case 2: mensajeExcepcion="SE SUSCITÓ UNA INCONSISTENCIA AL ACTUALIZAR EL HISTORICO DE UN DIVIDENDO SE HA ENCONTRADO MAS DE UN HISTORICO.   " +
        		mensajeCustomizado;
				printStackTrace(new PrintWriter(sw));
	            MailSend.enviarMailInconsistenciaNovacionPQ(mensajeExcepcion+".\n\n"+sw.toString());
	            break;	
	        }
	        log.info("Se detectô una excepcion controlada: "+ mensajeExcepcion);
	throw new DividendoPrestamoException(mensajeExcepcion);
	}
}