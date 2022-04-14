/*
 * Copyright 2011 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.constante;

/** 
 * <b>
 * Constantes Generales de parametros en PQ
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
public enum ParametrosPQ {
	
	FTP_ORIUSER_OPI("157","usuario ftp origen arch opi"),
	FTP_ORIPASS_OPI("158","pass usuario ftp origen arch opi"),
	FTP_ORISERVER_OPI("159","servidor ftp origen arch opi"),
	FTP_ORIPATH_OPI("160","servidor ftp path origen arch opi"),
	FTP_DESUSER_OPI("161","usuario ftp dest arch opi"),
	FTP_DESPASS_OPI("162","pass usuario ftp dest arch opi"),
	FTP_DESSERVER_OPI("163","servidor ftp dest arch opi"),
	FTP_DESPATH_OPI("164","servidor ftp path dest arch opi"),
	FTP_MAILS_OPI("165","mails usuarios arch opi"),
	CATALOGO_PH("4","catalogo ph"),
	VIVIENDA_PH("1","VIVIENDA"),
	CONSTRUCCION_PH("2","CONTRUCCION"),
	REMODELACION_PH("3","REMODELACION"),
	SUSTITUCION_PH("4","REMODELACION"),
	FTP_DESUSER_OPIPH("74","usuario ftp origen arch opi"),
	FTP_DESPASS_OPIPH("75","pass usuario ftp origen arch opi"),
	FTP_DESSERVER_OPIPH("73","pass usuario ftp origen arch opi"),
	FTP_DESPATH_VIV_OPIPH("166","directorio ftp vivienda"),
	FTP_DESPATH_CON_OPIPH("167","directorio ftp construccion"),
	FTP_DESPATH_REM_OPIPH("168","directorio ftp remodelacion"),
	FTP_DESPATH_SUS_OPIPH("169","directorio ftp sustitucion"),
	QPQGEN("179","QUERY PQ REGENERACION DIV"),
	QPQGENMAILS("180","MAILS PROCESOS PQGEN VID");
	

	/**
	 * Método Constructor
	 * @param String idParametro
	 * @param String descripcion
	 * Realiza la asignación de valores a las variables
	 */
	ParametrosPQ(String idParametro, String descripcion) {
		this.idParametro = idParametro;
		this.descripcion = descripcion;
	}
	
	private final String idParametro;
	private final String descripcion;
	
	
	
	/**
	 * Obtiene el IdParametro
	 * @return String
	 */
	public String getIdParametro() {
		return idParametro;
	}
	
	/**
	 * Obtiene la Descripción
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	
	

}
