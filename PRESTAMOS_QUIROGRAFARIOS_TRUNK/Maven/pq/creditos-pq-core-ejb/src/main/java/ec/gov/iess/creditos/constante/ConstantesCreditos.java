/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.constante;

/** 
 * <b>
 * Constantes Generales como tipo de tasas de interés referentes a créditos quirografarios.
 * </b>
 *  
 * @author cbastidas
 * @version $Revision: 1.7 $ <p>[$Author: smanosalvas $, $Date: 2011/10/03 $]</p>
*/ 
public interface ConstantesCreditos {
	public static final String RUC_IESS = "1760004650001";
	public static final int NUMERO_SEMANAS = 26;
	public static final String ID_TASA_BCO_CENTRAL = "ACT";
	public static final String ID_TASA_ACTORIAL = "TAC";
	public static final String TIPO_PERIODO_DIVIDENDO = "M";
	public static final Double COEFICIENTE_LIQUIDACION=0.9;
	public static final int PLAZO_INTERZAFRA=6;
	public static final Double PORCENTAJE_COMPROMETIMIENTO_PH = 40.00;
	public static final Double PORCENTAJE_COMPROMETIMIENTO_PQ = 30.00;
	public static final Double PORCENTAJE_TASA_ACTIVA = 0.5;
	public static final int PLAZO_MAXIMO_ZAFREROS = 12;
	public static final int PLAZO_MAXIMO_PRODUCTO_NORMAL = 48;
	public static final int PLAZO_MAXIMO_PRODUCTO_COMPUTADORAS = 24;
	public static final int PLAZO_MAXIMO_PRODUCTO_TURISTICO = 12;
	public static final int PLAZO_MAXIMO_ENFERMO_TERMINAL = 12;
}