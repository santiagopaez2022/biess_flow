/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.modelo.dto.AprobacionDto;
import ec.fin.biess.exception.HistoricoAprobacionesMasivasExcepcion;
import ec.fin.biess.modelo.persistencia.HistoricoAprobacionesMasivas;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.OrdenCompraEntradaWS;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.excepcion.DesbloqueoException;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.OrdenCompraException;
import ec.gov.iess.creditos.pq.excepcion.ProveedorException;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;

/**
 * 
 * <b> Interface local para la Administracion de la orden de compra y proveedor.
 * </b>
 * 
 * @author cbastidas,pjarrin
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:43:08 $]
 *          </p>
 */
@Local
public interface AdministracionOrdenCompraProveedorServicio {
	/**
	 * 
	 * <b> Método que obtiene una lista de ordenes de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 16/04/2011]
	 * </p>
	 * 
	 * @return List<OrdenCompra>
	 * @throws OrdenCompraException
	 *             :Excepción para el manejo de ordenes de compra
	 */
	public List<OrdenCompra> obtenerOrdenCompra() throws OrdenCompraException;

	/**
	 * 
	 * <b> Metodo para crear la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 16/04/2011]
	 * </p>
	 * 
	 * @param datosCredito
	 *            :Objeto datos crédito
	 * @throws OrdenCompraException
	 *             : Excepción para el manejo de ordenes de compra
	 */
	public void crearOrdenCompra(DatosCredito datosCredito)
			throws OrdenCompraException;

	/**
	 * 
	 * <b> Actualizacion del estado de la orden de compra. </b>
	 * <p>
	 * [Author: cbastidas, Date: 25/04/2011]
	 * </p>
	 * 
	 * @param datosCredito
	 *            :Objeto datos crédito
	 * @throws OrdenCompraExceptionExcepción
	 *             para el manejo de ordenes de compra
	 */
	public void actualizarOrdenCompraEstado(DatosCredito datosCredito)
			throws OrdenCompraException;

	/**
	 * 
	 * <b> Método que permite la obtención del proveedor </b>
	 * <p>
	 * [Author: cbastidas, Date: 16/04/2011]
	 * </p>
	 * 
	 * @param rucEmp
	 *            :Ruc del Empleador
	 * @return
	 * @throws ProveedorException
	 *             : Exception para manejo del proveedor
	 */
	public Proveedor obtenerProveedor(String rucEmp) throws ProveedorException;

	/**
	 * 
	 * <b> Permite la obtención del objeto Institución Financiera </b>
	 * <p>
	 * [Author: cbastidas, Date: 16/04/2011]
	 * </p>
	 * 
	 * @param rucEmp
	 *            : Ruc del Empleador
	 * @return
	 * @throws ProveedorException
	 *             : Exception para manejo del proveedor
	 */
	public InstitucionFinanciera obtenerInstitucionFinancieraProveedor(
			String rucEmp) throws ProveedorException;

	/**
	 * 
	 * <b> Confirma la orden de compra por medio de un webServices. </b>
	 * <p>
	 * [Author: cbastidas, Date: 13/05/2011]
	 * </p>
	 * 
	 * @param ordenCompraEntradaWS
	 *            : Parametros del web Service
	 * @throws OrdenCompraException
	 *             Excepcion para manejo de ordenes de compra
	 */
	public void confirmarOrdenEntrega(OrdenCompraEntradaWS ordenCompraEntradaWS)
			throws OrdenCompraException;

	/**
	 * 
	 * <b> Recalcula la tabla de amortizacion. </b>
	 * <p>
	 * [Author: cbastidas, Date: 13/05/2011]
	 * </p>
	 * 
	 * @param numeroOrden
	 *            : Número de Orden
	 * @param fechaOrden
	 *            : Fecha de la Orden
	 * @throws OrdenCompraException
	 *             Excepcion para manejo de ordenes de compra
	 * @throws NoServicioPrestadoSucursalException
	 */

	public void recalculaTablaAmortizacionOrdenCompra(String numeroOrden,
			Date fechaOrden) throws OrdenCompraException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException;

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
	public void recalculaTablaAmortizacionparaPDA(Long numpreafi,
			Long ordpreafi, Long codpretip, Long codprecla,
			Date fechaaprobacion, String cedulafuncionario)
			throws SolicitudException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException;

	/**
	 * 
	 * <b> Recalcula la tabla de amortización por medio de Proceso CRON. </b>
	 * <p>
	 * [Author: Danny Olaya, Date: 20/10/2011]
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
	public void recalculaTablaAmortizacionparaCRON(Long numpreafi,
			Long ordpreafi, Long codpretip, Long codprecla, Date fechaaprobacion)
			throws SolicitudException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException;

	/**
	 * Funcion que permite desbloquear un prestamo en estado ERC
	 * 
	 * @param prestamo
	 * @param cedulaFuncionario
	 * @throws DesbloqueoException
	 */
	void desbloquearPrestamo(Prestamo prestamo, String cedulaFuncionario)
			throws DesbloqueoException;

	/**
	 * Metodo que obtiene el proveedor dado su codigo de tipo de producto
	 * 
	 * @param codigoTipoProducto
	 * @return
	 * @throws ProveedorException
	 */
	public Proveedor obtenerProveedorPorTipoProducto(Long codigoTipoProducto)
			throws ProveedorException;
	
	/**
	 * Metodo que obtiene una lista de proveedores dado su codigo de tipo de producto
	 * 
	 * @param codigoTipoProducto
	 * @return List<Proveedor>
	 * @throws ProveedorException
	 */
	public List<Proveedor> obtenerListaProveedorPorTipoProducto(Long codigoTipoProducto) throws ProveedorException;
	
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