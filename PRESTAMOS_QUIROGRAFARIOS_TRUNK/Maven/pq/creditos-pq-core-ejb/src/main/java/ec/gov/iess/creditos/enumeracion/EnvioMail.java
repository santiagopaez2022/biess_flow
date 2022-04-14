/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.enumeracion;
/** 
 * <b>
 * Clase que permite realizar el envio de mails.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.3 $ <p>[$Author: smanosalvas $, $Date: 2011/03/01 16:01:16 $]</p>
*/ 
public enum EnvioMail {

	ASUNTO("ERROR CRUCE"), 
	ASUNTOERRORNOVACION("ERROR SUSCITADO DURANTE EL PROCESO DE NOVACION"), 
	ASUNTOINCONSISTENCIANOVACION("INCONSISTENCIA EN LA BDD DURANTE LA NOVACION"), //
	CUERPOFECHAYCUENTA("Estimad@ \n\n Algunos Aportes de Fondos de Reserva no tienen fecha de inicio de rendimiento y también existen inconsistencias en la cuenta individual de Fondos de Reserva del afiliado con cédula: "), //
	CUERPOFECHAINICIO("Estimad@ \n\n Algunos Aportes de Fondos de Reserva no tienen fecha de inicio de rendimiento para el afiliado con cédula: "), //
	CUERPOERRORCUENTA("Estimad@ \n\n Existen inconsistencias en la cuenta individual de Fondos de Reserva del afiliado con cédula: "), //
	COMENTARIO("Problema a resolver en 48 horas."), //
	PIEMAIL("Saludos"), //
	 /**
	 *  Ricardo Tituaña: INC-5780 se actualizo el tipo de correo de "iess.gov.ec" a "iess.gob.ec"
	 */	
	FROM1("carlos.bastidas@biess.fin.ec"),
	FROM2("carlos.bastidas@biess.fin.ec"),
	FROM3("haydee.pilco@biess.fin.ec"),
	FROM4("carlos.bastidas@biess.fin.ec"),
	FROM5("pablo.alvarez@biess.fin.ec");
		
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
