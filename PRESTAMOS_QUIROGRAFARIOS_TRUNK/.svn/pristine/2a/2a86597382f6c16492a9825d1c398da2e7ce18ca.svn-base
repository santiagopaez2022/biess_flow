/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ec.fin.biess.creditos.pq.modelo.dto.AprobacionDto;
import ec.fin.biess.exception.HistoricoAprobacionesMasivasExcepcion;
import ec.fin.biess.modelo.persistencia.HistoricoAprobacionesMasivas;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.OrdenCompraEntradaWS;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.OrdenCompraException;
import ec.gov.iess.creditos.pq.excepcion.ProveedorException;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;

/**
 * 
 * <b> Interface remota para la administracion de la orden de compra y
 * proveedor. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:43:08 $]
 *          </p>
 */
@Remote
public interface AdministracionOrdenCompraProveedorServicioRemote {
	public List<OrdenCompra> obtenerOrdenCompra() throws OrdenCompraException;

	public void crearOrdenCompra(DatosCredito datosCredito)
			throws OrdenCompraException;

	public void actualizarOrdenCompraEstado(DatosCredito datosCredito)
			throws OrdenCompraException;

	public Proveedor obtenerProveedor(String rucEmp) throws ProveedorException;

	public InstitucionFinanciera obtenerInstitucionFinancieraProveedor(
			String rucEmp) throws ProveedorException;

	public void confirmarOrdenEntrega(OrdenCompraEntradaWS ordenCompraEntradaWS)
			throws OrdenCompraException;

	public void recalculaTablaAmortizacionOrdenCompra(String numeroOrden, Date fechaOrden)
			throws OrdenCompraException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException;
	
	/**
	 * 
	 * <b> Recalcula la tabla de amortizacion. </b>
	 * <p>
	 * [Author: Andres Cantos, Date: 16/08/2011]
	 * </p>
	 * 
	 * @param numpreafi
	 *            : Numero de prestamo del afiliado
	 * @param ordpreafi
	 *            : Ordinal secuencial del prestamo del afiliado
	 * @param codpretip
	 *            : Codigo del tipo de prestamo
	 * @param codprecla
	 *            : Codigo de la clase de prestamo
	 * @param fechaaprobacion
	 *            : Fecha de aprobacion de la orden
	 * @throws OrdenCompraException
	 *             Excepcion para manejo de ordenes de compra
	 * @throws NoServicioPrestadoSucursalException            
	 */
	public void recalculaTablaAmortizacionparaPDA(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fechaaprobacion,
			String cedulafuncionario) throws SolicitudException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException;
	
	/**
	 * Recalculo de la Tabla de Amortización para CRON
	 * 
	 * @param numpreafi: Numero de prestamo del afiliado
	 * @param ordpreafi: Ordinal secuencial del prestamo del afiliado
	 * @param codpretip: Codigo del tipo de prestamo
	 * @param codprecla: Codigo de la clase de prestamo
	 * @param fechaaprobacion : Fecha de aprobacion de la orden
	 * @author rtituaña
	 * @throws SolicitudException
	 * @throws NoServicioPrestadoSucursalException
	 */
	public void recalculaTablaAmortizacionparaCRON(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fechaaprobacion)
			throws SolicitudException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException;
	
	/**
	 * Metodo donde se llama a la insercion batch de historico de aprobaciones masivas
	 * 
	 * @param listadoHisAprMasivas
	 * @throws HistoricoAprobacionesMasivasExcepcion
	 */
	public void insertarHistoricoAprobacionMasiva(List<HistoricoAprobacionesMasivas> listadoHisAprMasivas)
			throws HistoricoAprobacionesMasivasExcepcion;
	
	/**
	 * Metodo que devuelve el codigo del historico de la secuencia CRE_PQ_HISAPRMASIVA_SEQ
	 * 
	 * @return BigDecimal secuencial de historico
	 */
	public BigDecimal consultarCodigoHistorico();
	
	public void procesarAutorizacionPDAMasivos(List<Prestamo> listaPrestamos, 
			List<Prestamo> listadoPrestamosPDV,
			List<HistoricoAprobacionesMasivas> listadoHisAprMasivas, 
			Date fechaAprobacion, String cedulaFuncionario, String nombreFuncionario, 
			String correoFuncionario, int tamanio, BigDecimal secuencialHistorico,
			String ipUsuario, String usuario, String hostRemoto) throws SolicitudException;
	
	public void procesarTablaAmortizacionParaPDA(AprobacionDto aprobacion, BigDecimal secuencialHistorico,
			String ipUsuario, String usuario, String hostRemoto)
			throws SolicitudException;
	
	public void actualizarHistoricoMasivoParaPDA(AprobacionDto dato, BigDecimal secuencialHistorico)
			throws SolicitudException;
	
	public void validarCesanteAmortizacionparaPDA(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) 
			throws SolicitudException, NoServicioPrestadoSucursalException;
	
}
