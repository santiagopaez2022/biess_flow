/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.sp;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.SolicitudPago;

/**
 * 
 * <b> Interface para métodos de desembolso de créditos </b>
 * 
 * @author Daniel Cardenas, jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 22/09/2011 $]
 *          </p>
 */
@Local
public interface SolicitudPagoJdbc {

	/**
	 * 
	 * <b> Método para obtener la lista de solicitudes para generar el
	 * desembolso </b>
	 * <p>
	 * [Author: jsanchez, Date: 22/09/2011]
	 * </p>
	 * 
	 * @param desde
	 *            fecha de consulta
	 * @param hasta
	 *            fecha l&#237;mite de consulta
	 * @param estados
	 *            estados de las etapas de la solicitud
	 * @param tiposPrestamos
	 *            tipos de créditos
	 * @param tiposDesembolsos
	 *            tipos de desembolsos
	 * @return lista de solicitudes para desembolsar
	 */
	public List<SolicitudPago> obtenerPorFechaSolicitudPago(Date desde, Date hasta,
			List<Long> estados, List<Long> tiposPrestamos);

	/**
	 * 
	 * <b> Método para obtener la lista de solicitudes para generar el
	 * desembolso de terreno y construcci&#243;n </b>
	 * <p>
	 * [Author: jsanchez, Date: 22/09/2011]
	 * </p>
	 * 
	 * @param desde
	 *            fecha de consulta
	 * @param hasta
	 *            fecha l&#237;mite de consulta
	 * @param estados
	 *            estados de las etapas de la solicitud
	 * @param tiposPrestamos
	 *            tipos de créditos
	 * @param tiposDesembolsos
	 *            tipos de desembolsos
	 * @return lista de solicitudes para desembolsar
	 */
	public List<SolicitudPago> obtenerSolicitudPagoTerrenoConstruccion(Date desde, Date hasta,
			List<Long> estados, List<Long> tiposPrestamos, List<String> tiposDesembolsos);
	
	/**
	 * 
	 * <b> Método para obtener la lista de solicitudes para generar el
	 * desembolso de terreno y construcci&#243;n Unificados </b>
	 * <p>
	 * [Author: jsanchez, Date: 22/09/2011]
	 * </p>
	 * 
	 * @param desde
	 *            fecha de consulta
	 * @param hasta
	 *            fecha l&#237;mite de consulta
	 * @param estados
	 *            estados de las etapas de la solicitud
	 * @param tiposPrestamos
	 *            tipos de créditos
	 * @param tiposDesembolsos
	 *            tipos de desembolsos
	 * @return lista de solicitudes para desembolsar
	 */
	public List<SolicitudPago> obtenerSolicitudPagoTerrenoConstruccionUnificado(Date desde, Date hasta, List<Long> estados,
			List<Long> tiposPrestamos, List<String> tiposDesembolsos);
	
	/**
	 * 
	 * @param desde
	 * @param hasta
	 * @param estados
	 * @param tiposPrestamos
	 * @return
	 */
	public List<SolicitudPago> obtenerSolicitudPagoBiessIfi(Date desde, Date hasta,
			List<Long> estados, List<Long> tiposPrestamos, boolean isReverso);
	
	/**
	 * 
	 * @param desde
	 * @param hasta
	 * @param estados
	 * @param tiposPrestamos
	 * @return
	 */
	public List<SolicitudPago> obtenerSolicitudPagoBiessBiess(Date desde, Date hasta,
			List<Long> estados, List<Long> tiposPrestamos, boolean isReverso);
	
	/**
	 * 
	 * <b> Método para obtener la lista de solicitudes para generar el
	 * desembolso de SH</b>
	 * <p>
	 * [Author: jsanchez, Date: 22/09/2011]
	 * </p>
	 * 
	 * @param desde
	 *            fecha de consulta
	 * @param hasta
	 *            fecha l&#237;mite de consulta
	 * @param estados
	 *            estados de las etapas de la solicitud
	 * @param tiposPrestamos
	 *            tipos de créditos
	 * @param tiposDesembolsos
	 *            tipos de desembolsos
	 * @return lista de solicitudes para desembolsar
	 */
	public List<SolicitudPago> obtenerPorFechaSolicitudPagoSH(Date desde, Date hasta,
			List<Long> estados, List<Long> tiposPrestamos);
	
}