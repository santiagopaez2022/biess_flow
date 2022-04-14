/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.pq.concesion.enumeraciones;
/** 
 * <b>
 * Permite realizar el envio de mails para cruce de cuentas
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.4 $ <p>[$Author: smanosalvas $, $Date: 2011/03/01 16:01:36 $]</p>
*/ 
public enum EnvioMail {

	ASUNTO("ERROR CRUCE"), //
	CUERPOFECHAYCUENTA("Estimad@ \n\n Algunos Aportes de Fondos de Reserva no tienen fecha de inicio de rendimiento y también existen inconsistencias en la cuenta individual de Fondos de Reserva del afiliado con cédula: "), //
	CUERPOFECHAINICIO("Estimad@ \n\n Algunos Aportes de Fondos de Reserva no tienen fecha de inicio de rendimiento para el afiliado con cédula: "), //
	CUERPOERRORCUENTA("Estimad@ \n\n Existen inconsistencias en la cuenta individual de Fondos de Reserva del afiliado con cédula: "), //
	COMENTARIO("Problema a resolver en 48 horas."), //
	PIEMAIL("Saludos"), //
	/**
	 *  Ricardo Tituaña: INC-5780 se actualizo el tipo de correo de "iess.gov.ec" a "iess.gob.ec"
	 */
	FROM1("felipe.calero@biess.fin.ec"),
	FROM2("avelasco@iess.gob.ec"),
	FROM3("sespinoza@iess.gob.ec"),
	FROM4("pablo.alvarez@biess.fin.ec");
	
		
	private String valor;
	
	private EnvioMail(String valor) {
		this.valor = valor;
		
	}
	
	/**
	 * Incluir aqui­ la descripcion del metodo.
	 * 
	 * @return
	 */
	
	public String getValor() {
		return valor;
	}

}
