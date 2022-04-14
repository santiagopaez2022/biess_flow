/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.DividendoPrestamoException;

/**
 * @author cvillarreal
 * 
 */
@Remote
public interface DividendoPrestamoServicioRemote {

	/**
	 * Crea los dividendos de los creditos quirografarios
	 * 
	 * @param dividendoCalculoList
	 * @param idTipocredito
	 * @param idVariedadcredito
	 * @param numeroPrestamo
	 * @param tasaInteres
	 */
	public void crearDividendosPq(DatosCredito prestamo);

	/**
	 * Devuelve los dividendos de un prestamo que se encuentran en estado MOR o REG
	 * @return
	 */
	public List<DividendoPrestamo> obtenerDividendosPorPagar(PrestamoPk prestamoPk,List<String> estados);

	/**
	 * Obtiene la lista de dividendos en mora estado MOR
	 * @param prestamoPk
	 * @return
	 */
	public List<DividendoPrestamo> obtenerDividendosEnMora(PrestamoPk prestamoPk,List<String> estados);
	
	/**
	 * Obtiene dias de mora del credito
	 * @param prestamoPk
	 * @return
	 */
	public BigDecimal obtenerDiasMora(PrestamoPk prestamoPk);
	
	/**
	 *  Cambia el estado del dividendo al nuevo estado enviado como parámetro y se actualizan los respectivos históricos
	 *   
	 */
	public void actualizarEstadoDividendo(DividendoPrestamo dividendoPrestamo, String nuevoEstado,String estadoCondicion, String mensajeObservacion) throws DividendoPrestamoException;
	
	/**
	 * Elimina los Dividendos e Históricos de un Préstamo
	 * @param prestamoPk
	 * @return
	 */
	
	public void eliminarDividendosEHistoricos(PrestamoPk prestamoPk);
	
	/**
	 * Elimina los Dividendos e Históricos de un Préstamo
	 * @param prestamoPk
	 * @return
	 */
	
	public void eliminarHistoricosDividendos(PrestamoPk prestamoPk);
	
	/**
	 * Elimina los Dividendos de un Préstamo
	 * @param prestamoPk
	 * @return
	 */
	
	public void eliminarDividendos(PrestamoPk prestamoPk);
	
	/**
	 * Elimina la lista de los Dividendos e Históricos de un Préstamo
	 * @param List<Prestamo>
	 * @return
	 */
	public void eliminarDividendosHistoricoLista(List<Prestamo> pres) throws ParametroNoEncontradoException;
	
	/**
	 * Cuenta los dividendos en mora desde una fecha hacia atras
	 * 
	 * @param fechaPagoDividendos
	 * @param cedula
	 * @return
	 */
	BigDecimal contarDividendosMoraBiessPorFecha(String cedula);
	
		/**
	 * Cuenta los dividendos dada la informacion del prestamo y fechas de cancelación
	 * 
	 * @param numpreafi
	 * @param ordpreafi
	 * @param codpretip
	 * @param codprecla
	 * @param fecpagdiv
	 * @param feccandiv
	 * @param forcandiv
	 * @return
	 */
	BigDecimal contarDividendosPorFecha(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fecpagdiv, Date feccandiv,
			List<String> forcandiv);
	
	/**
	 * Cuenta los dividendos en mora para prestamos Emergentes
	 * 
	 * @param fechaPagoDividendos
	 * @param cedula
	 * @return
	 */
	BigDecimal contarDividendosMoraBiessEmergente(Date fechaPagoDividendos, String cedula);
	
}