/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.exception.EmailAlertUserException;
import ec.fin.biess.alerts.exception.SmsAlertUserException;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.excepcion.AprobacionCreditoSenderException;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.modelo.dto.AprobacionDto;
import ec.fin.biess.creditos.pq.modelo.dto.AprobacionMasivaDto;
import ec.fin.biess.creditos.pq.servicio.PrestamoCalculoService;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio;
import ec.fin.biess.dao.HistoricoAprobacionesMasivasDao;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.HistoricoAprobacionesMasivasExcepcion;
import ec.fin.biess.exception.ParametrizacionPQException;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.modelo.persistencia.HistoricoAprobacionesMasivas;
import ec.fin.biess.modelo.persistencia.pk.HistoricoAprobacionesMasivasPk;
import ec.fin.biess.shop.integration.webservice.BiessShopService;
import ec.fin.biess.shop.integration.webservice.Orden;
import ec.fin.biess.shop.integration.webservice.OrdenWSService;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DetalleCatalogosDao;
import ec.gov.iess.creditos.dao.OrdenCompraDao;
import ec.gov.iess.creditos.dao.OrdenCompraDetDao;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.dao.PrestamoResumenHistoricoDao;
import ec.gov.iess.creditos.dao.ProveedorDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.EstadoOrdenCompra;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.DatosOrdenCompra;
import ec.gov.iess.creditos.modelo.dto.DetalleOrdenEntradaWs;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.modelo.dto.OrdenCompraEntradaWS;
import ec.gov.iess.creditos.modelo.dto.PrestamoCalculo;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosFondos;
import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompra;
import ec.gov.iess.creditos.modelo.persistencia.OrdenCompraDetalle;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.PrestamoResumenHistorico;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.modelo.persistencia.TipoDividendo;
import ec.gov.iess.creditos.modelo.persistencia.pk.DetalleCatalogosPK;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.CalculoCreditoException;
import ec.gov.iess.creditos.pq.excepcion.CondicionCalculoException;
import ec.gov.iess.creditos.pq.excepcion.DesbloqueoException;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.OrdenCompraException;
import ec.gov.iess.creditos.pq.excepcion.ProveedorException;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitudException;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicioRemote;
import ec.gov.iess.creditos.pq.servicio.AprobacionCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio;
import ec.gov.iess.creditos.pq.servicio.CuentaBancariaAprobadaServicioLocal;
import ec.gov.iess.creditos.pq.servicio.DividendoPrestamoServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiaCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.CreditosEspecialesUtil;
import ec.gov.iess.creditos.pq.util.Utilities;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;
import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;
import ec.gov.iess.cuentabancaria.servicio.InstitucionFinancieraServicio;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author pjarrin
 * 
 */
@Stateless
public class AdministracionOrdenCompraProveedorServicioImpl implements
		AdministracionOrdenCompraProveedorServicio,
		AdministracionOrdenCompraProveedorServicioRemote {

	LoggerBiess log = LoggerBiess
			.getLogger(AdministracionOrdenCompraProveedorServicioImpl.class);

	@EJB
	private OrdenCompraDao ordenCompraDao;

	@EJB
	private ProveedorDao proveedorDao;

	@EJB
	private DetalleCatalogosDao detalleCatalogosDao;

	@EJB
	private InstitucionFinancieraServicio institucionFinancieraServicio;

	@EJB
	private OrdenCompraDetDao ordenCompraDetDao;

	@EJB
	private PrestamoDao prestamoDao;

	@EJB
	private SolicitanteServicio solicitanteServicio;

	@EJB
	private CalculoCreditoServicio calculoCreditoServicio;

	@EJB
	private DividendoPrestamoServicio dividendoPrestamoServicio;

	@EJB
	private PrestamoResumenHistoricoDao prestamoResumenHistoricoDao;

	@EJB
	private CondicionCalculoServicio condicionCalculoServicio;
	
	// cambio para control de fraudes 31/08/2010 - Lista blnca
	@EJB(name = "CuentaBancariaAprobadaServicioImpl/local")
	private CuentaBancariaAprobadaServicioLocal ctabancariaaprservicio;
	
	@EJB
	private PrestamoCalculoService prestamoCalculoService;
	
	@EJB
	private SimularCreditoServicio simularCreditoServicio;
	
	@EJB
	private ParametroBiessDao parametroBiessDao;
	
	@EJB
	private GarantiaCreditoServicio garantiaCreditoServicio;
	
	//Aprobacion Masivos
	@EJB
	private HistoricoAprobacionesMasivasDao historicoAprobacionesMasivasDao;
	
	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;
	
	@EJB
	private AprobacionCreditoServicio aprobacionCreditoServicio;
	
	@EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;
	
	private CalculoCreditoHelperSingleton calculoCreditoHelper;
	
	@EJB(name = "TipoPrestamoServicioImpl/local")
	private TipoPrestamoServicio tipoPrestamoServicio;
	
	//Aprobacion Masivos
	private static final String PATH_TEMPLATE = "ec/gov/iess/creditos/pq/alertas/templates/email/aprobacionMasiva.ftl";
	
	
	private AprobacionDto aprobacionDto;
	
	BigDecimal mesesGracia = null;

	public AdministracionOrdenCompraProveedorServicioImpl() {
	}
	
	/**
	 * Inicializacion de variables
	 */
	@PostConstruct
	private void init() {
		this.calculoCreditoHelper = CalculoCreditoHelperSingleton.getInstancia();
		this.mesesGracia = obtenerMesesGracia();
	}

	public List<OrdenCompra> obtenerOrdenCompra() {
		List<OrdenCompra> listaOrdenesCompra = ordenCompraDao
				.consultarOrdenCompra();
		return listaOrdenesCompra;
	}

	public void crearOrdenCompra(DatosCredito datosCredito)throws OrdenCompraException {
		try {
			/* Cabecera */
			OrdenCompra orden = new OrdenCompra();
			orden.setOcCodOrdCompra(datosCredito.getOrdenCompra().getNumeroOrden());
			orden.setOcFecGeneracion(new Date());
			orden.setOcValor(datosCredito.getOrdenCompra().getMontoOrden().doubleValue());
			orden.setOcObservacion(datosCredito.getOrdenCompra().getObservacionOrden());
			DetalleCatalogosPK detCatalogoPK = new DetalleCatalogosPK();
			detCatalogoPK.setCaCatalogo(EstadoOrdenCompra.EstadoOrdenCompra.getDescripcion());
			detCatalogoPK.setDcCodigo(EstadoOrdenCompra.Generado.getDescripcion());
			DetalleCatalogos detCatalogo = detalleCatalogosDao.load(detCatalogoPK);
			orden.setDetalleCatalogo(detCatalogo);
			orden.setPrestamo(datosCredito.getPrestamoOrdenCompra());			
			ordenCompraDao.insert(orden);
			/* Detalle */			
			BiessShopService service = new BiessShopService();
			OrdenWSService port = service.getOrdenWSServicePort(); 
			Orden order = port.obtenerOrden(datosCredito.getCedulaAfiliado());
			if (null == order.getValueXtratech() || order.getValueXtratech().compareTo(BigDecimal.ZERO) <= 0) {				
				log.error("Valor de la orden de compra inconsistente: " + order.getNumeroOrden());
				throw new OrdenCompraException("Orden de compra inconsistente");
			}
			if (null != order.getValueXtratech() && order.getValueXtratech().compareTo(BigDecimal.ZERO) > 0) {
				OrdenCompraDetalle ordenCompraDetalle = new OrdenCompraDetalle();
				ordenCompraDetalle.setProveedor(datosCredito.getOrdenCompra().getProveedor());
				ordenCompraDetalle.setOrdenCompra(orden);
				ordenCompraDetalle.setValorParcial(order.getValueXtratech());
				ordenCompraDetDao.insert(ordenCompraDetalle);
			}
			if (null != order.getValueCnt() && order.getValueCnt().compareTo(BigDecimal.ZERO) > 0) {
				String rucCNT = "1768152560001";				
				OrdenCompraDetalle ordenCompraDetalle = new OrdenCompraDetalle();
				ordenCompraDetalle.setProveedor(proveedorDao.consultarProveedorRUC(rucCNT));
				ordenCompraDetalle.setOrdenCompra(orden);
				ordenCompraDetalle.setValorParcial(order.getValueCnt());
				ordenCompraDetDao.insert(ordenCompraDetalle);
			}						
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new OrdenCompraException(e.getMessage(), e);
		}
	}

	public void actualizarOrdenCompraEstado(DatosCredito datosCredito) {
		OrdenCompra ordenCompra = ordenCompraDao.load(datosCredito
				.getOrdenCompra().getNumeroOrden());
		DetalleCatalogosPK detCatalogoPK = new DetalleCatalogosPK();
		detCatalogoPK.setCaCatalogo(EstadoOrdenCompra.EstadoOrdenCompra
				.getDescripcion());
		detCatalogoPK.setDcCodigo(EstadoOrdenCompra.Informado.getDescripcion());
		DetalleCatalogos detCatalogo = detalleCatalogosDao.load(detCatalogoPK);
		ordenCompra.setDetalleCatalogo(detCatalogo);
		ordenCompraDao.update(ordenCompra);
	}

	public void regenerarOrden(DatosCredito datosCredito) {
		OrdenCompra ordenCompra = ordenCompraDao.load(datosCredito
				.getOrdenCompra().getNumeroOrden());
		DetalleCatalogosPK detCatalogoPK = new DetalleCatalogosPK();
		detCatalogoPK.setCaCatalogo(EstadoOrdenCompra.EstadoOrdenCompra
				.getDescripcion());
		detCatalogoPK.setDcCodigo(EstadoOrdenCompra.Aprobado.getDescripcion());
		DetalleCatalogos detCatalogo = detalleCatalogosDao.load(detCatalogoPK);
		ordenCompra.setDetalleCatalogo(detCatalogo);
		ordenCompraDao.update(ordenCompra);
	}

	public void confirmarOrdenEntrega(OrdenCompraEntradaWS ordenCompraEntradaWS) throws OrdenCompraException {		
		// obtengo orden compra
		OrdenCompra ordenCompra = ordenCompraDao.load(ordenCompraEntradaWS.getNumeroOrden());

		validarOrdenCompraEntradaWS(ordenCompraEntradaWS, ordenCompra);

		ordenCompra.setNumFactura(ordenCompraEntradaWS.getNumeroFactura());
		ordenCompra.setFecConfirmacion(ordenCompraEntradaWS.getFecConfOrden());
		//ordenCompra.setOcValor(ordenCompraEntradaWS.getValorOrden().doubleValue());

		//INC-2272 Pensiones Alimenticias
		/*DatosOrdenCompra datosOrdenCompra = new DatosOrdenCompra();
		datosOrdenCompra.setNumeroOrden(ordenCompra.getOcCodOrdCompra());
		datosOrdenCompra.setCodigoProducto(TiposProductosPq.COM.toString());
		datosOrdenCompra.setMontoOrden(new BigDecimal(ordenCompra.getOcValor()));*/

		DetalleCatalogosPK detCatalogoPK = new DetalleCatalogosPK();
		detCatalogoPK.setCaCatalogo(EstadoOrdenCompra.EstadoOrdenCompra.getDescripcion());
		detCatalogoPK.setDcCodigo(EstadoOrdenCompra.Entregado.getDescripcion());
		DetalleCatalogos detCatalogo = detalleCatalogosDao.load(detCatalogoPK);
		ordenCompra.setDetalleCatalogo(detCatalogo);	
		ordenCompraDao.update(ordenCompra);		
	}

	private void validarOrdenCompraEntradaWS(
			OrdenCompraEntradaWS ordenCompraEntradaWS, OrdenCompra ordenCompra)
			throws OrdenCompraException {
		if (ordenCompra == null) {
			throw new OrdenCompraException("No existe la orden con el numero: "
					+ ordenCompraEntradaWS.getNumeroOrden());
		}
		if ("".equals(ordenCompraEntradaWS.getEstadoOrden())
				|| !"Entregada".equals(ordenCompraEntradaWS.getEstadoOrden())) {
			throw new OrdenCompraException("Estado enviado no valido");
		}
		if (!ordenCompra.getDetalleCatalogo().getId().getDcCodigo()
				.equals(EstadoOrdenCompra.Informado.getDescripcion())) {
			throw new OrdenCompraException(
					"Estado de la orden es distinto de informado: "
							+ ordenCompra.getDetalleCatalogo().getId()
									.getDcCodigo());
		}
		if (ordenCompraEntradaWS.getNumeroFactura() == null
				|| "".equals(ordenCompraEntradaWS.getNumeroFactura())) {
			try {
				new Double(ordenCompraEntradaWS.getNumeroFactura().trim());
			} catch (Exception e) {
				throw new OrdenCompraException("Valor de la factura no valido");
			}
			throw new OrdenCompraException("Valor de la factura no valido");
		}
		if ("Entregada".equals(ordenCompraEntradaWS.getEstadoOrden())) {
			if (ordenCompraEntradaWS.getValorOrden().compareTo(BigDecimal.ZERO) <= 0) {
				throw new OrdenCompraException("Valor de la orden no valido: " + 
						ordenCompraEntradaWS.getValorOrden());
			}
			if (ordenCompraEntradaWS.getValorOrden().setScale(2, BigDecimal.ROUND_HALF_UP)
					.compareTo(new BigDecimal(ordenCompra.getOcValor()).setScale(2, BigDecimal.ROUND_HALF_UP)) != 0) {			
				throw new OrdenCompraException(
						"El valor total de la orden enviado no coincide con el que se encuentra previamente registrado: " +
						ordenCompra.getOcValor().doubleValue());
			}
			BigDecimal valoresParciales = BigDecimal.ZERO;
			for (DetalleOrdenEntradaWs detalleOrdenEntradaWs : ordenCompraEntradaWS
					.getListaDetalleOrdenEntradaWs()) {
				String ruc = detalleOrdenEntradaWs.getRuc();
				if (ruc == null || "".equals(ruc) || !(ruc.length() == 13)) {
					throw new OrdenCompraException(
							"El ruc en uno de los detalles no es valido");
				}
				valoresParciales = valoresParciales.add(detalleOrdenEntradaWs.getValorParcial()); 
			}
			if (valoresParciales.setScale(2, BigDecimal.ROUND_HALF_UP)
					.compareTo(new BigDecimal(ordenCompra.getOcValor()).setScale(2,BigDecimal.ROUND_HALF_UP)) != 0) {
				throw new OrdenCompraException(
						"La suma de los valores parciales del detalle no coincide con el valor total de la orden: " +
						ordenCompra.getOcValor().doubleValue());
			}
		}
		if ("Multa".equals(ordenCompraEntradaWS.getEstadoOrden())) {
			if (ordenCompraEntradaWS.getValorMulta().doubleValue() < new Double(
					0)) {
				throw new OrdenCompraException(
						"Valor de la multa de la orden no valido");
			}
		}
		if ("Multa".equals(ordenCompraEntradaWS.getEstadoOrden())) {
			if (ordenCompraEntradaWS.getFecPlazoPago() == null) {
				throw new OrdenCompraException("Fecha de pago no valido");
			}
		}

	}

	// metodo que recalculo los dividendos

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio#recalculaTablaAmortizacionOrdenCompra
	 * (java.lang.String, java.util.Date)
	 */
	public void recalculaTablaAmortizacionOrdenCompra(String numeroOrden, Date fechaOrden)
			throws OrdenCompraException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException {
		Solicitante solicitante = null;
		PrestamoCalculo prestamoCalculo = null;

		log.info("EMPIEZA EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");

		DatosCredito datosCredito = new DatosCredito();
		// fecha de la confirmacion de la orden
		datosCredito.setFechaSolicitud(fechaOrden);

		log.info("OBTENCION ORDEN COMPRA");
		// obtengo orden compra
		OrdenCompra ordenCompra = ordenCompraDao.load(numeroOrden);
		if (ordenCompra == null) {
			throw new OrdenCompraException("No existe la orden con el #: "
					+ numeroOrden);
		}
		log.info("FIN ORDEN COMPRA");
		// obtengo prestamo
		Prestamo prestamo = ordenCompra.getPrestamo();

		// determino tipo de prestamo
		if (prestamo.getPrestamoPk().getCodprecla() == 22) {
			datosCredito.setTipoPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
		} else if (prestamo.getPrestamoPk().getCodprecla() == 20) {
			datosCredito.setTipoPrestamista(TipoPrestamista.AFILIADO);
		} else if (prestamo.getPrestamoPk().getCodprecla() == 21
				|| prestamo.getPrestamoPk().getCodprecla() == 24
				|| prestamo.getPrestamoPk().getCodprecla() == 25) {
			datosCredito.setTipoPrestamista(TipoPrestamista.JUBILADO);
		}
		DatosOrdenCompra datosOrdenCompra = new DatosOrdenCompra();
		datosOrdenCompra.setNumeroOrden(ordenCompra.getOcCodOrdCompra());
		//INC-2272 Pensiones Alimenticias
		datosOrdenCompra.setCodigoProducto(TiposProductosPq
				.getTiposProductosPq(prestamo.getCreditoPk().getCodpretip())
				.toString());
		datosOrdenCompra
				.setMontoOrden(new BigDecimal(ordenCompra.getOcValor()));
		datosCredito.setValorSeguroSaldosOrden(new BigDecimal(prestamo
				.getValsegsal()));

		datosCredito.setOrdenCompra(datosOrdenCompra);
		// determino solicitante
		try {
			solicitante = obtenerSolicitante(prestamo.getNumafi(),
					datosCredito.getTipoPrestamista());
		} catch (SolicitanteExcepcion e) {
			throw new OrdenCompraException(e.getMessage(), e);
		}
		
		int edad = calculoCreditoHelper.determinarEdad(solicitante.getDatosPersonales().getFechaNacimiento(), new Date()).intValue();
		
		// determino los valores de los datos del credito
		log.info("INICIO CONFIGURAR CREDITO");
		try {
			configurarDatosCredito(solicitante, datosCredito, prestamo, edad);
		} catch (CalculoCreditoException e) {
			throw new OrdenCompraException(e.getMessage(), e);
		}
		log.info("FIN CONFIGURAR CREDITO");

		// determino tasa de interes
		log.info("INICIO TASA DE INTERES");
		try {
			
			datosCredito.setTasaInteres(
					this.obtenerTasaInteres(new BigDecimal(datosCredito.getPlazo()), "NORMAL", edad));
			// calcular cuota mensual
			datosCredito.setCuotaMensualMaxima(simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(datosCredito.getMonto(),
					datosCredito.getTasaInteres(), datosCredito.getPlazo(), datosCredito.getTipoTabla()));
		} catch (CondicionCalculoException e) {
			throw new OrdenCompraException(e.getMessage(), e);
		}
		log.info("FIN TASA DE INTERES");

		log.info("INICIO CALCULO DEL CREDITO");
		try {
			//aumentar para corregir lo de tablas de amortizacion
			PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();
			prestamoCalculoAux = prestamoCalculoService.poblarPrestamoCalculoNew(datosCredito);
			
			prestamoCalculo = this.calculoCreditoServicio
					.calcularCreditoNew(datosCredito,prestamoCalculoAux);			
			
			datosCredito.setPrestamoCalculo(prestamoCalculo);
		} catch (CalculoCreditoException e) {
			throw new OrdenCompraException(e.getMessage(), e);
		}
		catch (TablaAmortizacionException e) {			
			throw new OrdenCompraException(e.getMessage(), e);
		}
		log.info("FIN CALCULO DEL CREDITO");

		try {
			datosCredito.setPrestamoCalculo(prestamoCalculo);
			Date fecPreafi = datosCredito.getFechaSolicitud();
			Date fecPreIniAfi = prestamoCalculo.getFechaInicioPrestamo();
			Date fecPrefinAfi = prestamoCalculo.getFechaFinPrestamo();
			datosCredito.setDividendoCalculoList(prestamoCalculo
					.getDividendoCalculoList());
			datosCredito.setPrestamoCalculo(prestamoCalculo);

			log.info("INICIO ELIMINAR DIVIDENDOS");
			// eliminar antiguos
			dividendoPrestamoServicio.eliminarDividendosEHistoricos(prestamo
					.getPrestamoPk());
			log.info("FIN ELIMINAR DIVIDENDOS");
			// crear nuevos dividendos
			log.info("INICIO CREAR DIVIDENDOS");
			dividendoPrestamoServicio.crearDividendosPq(datosCredito);
			log.info("FIN CREAR DIVIDENDOS");

			log.info("INICIO HISTORICO DIVIDENDOS");
			PrestamoResumenHistorico prestamoResumenHistorico = new PrestamoResumenHistorico();
			prestamoResumenHistorico.setPrestamo(prestamo);
			prestamoResumenHistorico.setChFecCreacion(prestamo.getFecpreafi());
			prestamoResumenHistorico.setChFecInicio(prestamo.getFecinipre());
			prestamoResumenHistorico.setChFecFin(prestamo.getFecfinpre());
			prestamoResumenHistorico.setChTipoCuenta(prestamo.getTipoCuenta());
			prestamoResumenHistorico.setChNumCtabancaria(prestamo
					.getNumctaban());
			prestamoResumenHistorico.setChRucIntsfinanciera(prestamo
					.getRucempinsfin());
			prestamoResumenHistorico.setChValorDividendo(prestamo
					.getValtotdiv());

			prestamoResumenHistorico.setChMonto(prestamo.getMntpre());
			prestamoResumenHistorico.setChPlazo(prestamo.getPlzmes());
			prestamoResumenHistorico.setChTasa(prestamo.getTasint());

			prestamoResumenHistorico
					.setChIntDiasgracia(prestamo.getIntdiagrc());
			prestamoResumenHistorico.setChValorSegurosaldos(prestamo
					.getValsegsal());
			prestamoResumenHistorico
					.setChObservacion("Tabla de Amortizacion regenerada");
			prestamoResumenHistorico.setChFecTransaccion(new Date());

			prestamoResumenHistoricoDao.insert(prestamoResumenHistorico);

			log.info("FIN HISTORICO DIVIDENDOS");

			log.info("INICIO ACTUALIZAR CREDITO");
			Calendar cl = Calendar.getInstance();
			cl.setTime(datosCredito.getFechaSolicitud());

			datosCredito.setAnio(new Long(cl.get(Calendar.YEAR)));
			datosCredito.setMes(new Long(cl.get(Calendar.MONTH) + 1));

			prestamo.setFecpreafi(fecPreafi);
			prestamo.setAniper(datosCredito.getAnio());
			prestamo.setMesper(datosCredito.getMes());
			EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
			estadoPrestamo.setCodestpre("VIG");
			prestamo.setEstadoPrestamo(estadoPrestamo);
			prestamo.setFecinipre(fecPreIniAfi);
			prestamo.setFecfinpre(fecPrefinAfi);
			prestamo.setValtotdiv(UtilAjusteCalculo.ajusteNumberBaseDatos(
					prestamoCalculo.getValorTotalDividendoMensual())
					.doubleValue());
			prestamo.setTasint(UtilAjusteCalculo.ajusteNumber(
					datosCredito.getTasaInteres(), 2).doubleValue());
			prestamo.setIntdiagrc(UtilAjusteCalculo.ajusteNumberBaseDatos(
					prestamoCalculo.getPeriodoGracia().getValor())
					.doubleValue());
			prestamoDao.update(prestamo);
			log.info("FIN ACTUALIZAR CREDITO");

			regenerarOrden(datosCredito);
		} catch (Exception e) {
			log.error("ERROR AL REGENERAR LA TABLA DE AMORTIZACION: "
					+ prestamo.getPrestamoPk().getNumpreafi()
					+ prestamo.getPrestamoPk().getCodprecla()
					+ prestamo.getPrestamoPk().getCodpretip()
					+ prestamo.getPrestamoPk().getOrdpreafi() + "ORDEN :"
					+ ordenCompra.getOcCodOrdCompra());
			throw new OrdenCompraException(e.getMessage(), e);
		}

		log.info("EXITO : CONCLUYE EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");

	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio#procesarAutizacionPDAMasivos(java.util.List, java.util.List, java.util.List, java.util.Date, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void procesarAutorizacionPDAMasivos(List<Prestamo> listaPrestamos, 
			List<Prestamo> listadoPrestamosPDV,
			List<HistoricoAprobacionesMasivas> listadoHisAprMasivas, 
			Date fechaAprobacion, String cedulaFuncionario, String nombreFuncionario, 
			String correoFuncionario, int tamanio, BigDecimal secuencialHistorico,
			String ipUsuario, String usuario, String hostRemoto) throws SolicitudException {
		try {		
			
			if (listadoPrestamosPDV != null) {
				int tamanioPDV = listadoPrestamosPDV.size();
				if (!listadoPrestamosPDV.isEmpty()) {
					ArrayList<String> solicitudesAprobadas = procesarAutorizacionPDVMasivos(listadoPrestamosPDV, 
							fechaAprobacion, cedulaFuncionario, secuencialHistorico);
					
					if (tamanio == tamanioPDV) {
						//Enviar Alerta
						enviarEmailAprobacionMasivos(solicitudesAprobadas,secuencialHistorico,correoFuncionario,nombreFuncionario);
					}
				}
			}
			
			if (!listaPrestamos.isEmpty()) {
				HashMap<String,Object> datos = recalculaTablaAmortizacionPDAMasivos(listaPrestamos,fechaAprobacion,cedulaFuncionario);
				AprobacionMasivaDto aprobacionMasivaDto = new AprobacionMasivaDto();
				aprobacionMasivaDto.setCedulaFuncionario(cedulaFuncionario);
				aprobacionMasivaDto.setNombreFuncionario(nombreFuncionario);
				aprobacionMasivaDto.setEmailFuncionario(correoFuncionario);
				aprobacionMasivaDto.setSecuencialHistorico(secuencialHistorico);
				aprobacionMasivaDto.setFechaAprobacion(fechaAprobacion);
				aprobacionMasivaDto.setTamanio(tamanio);
				aprobacionMasivaDto.setIpUsuario(ipUsuario);
				aprobacionMasivaDto.setUsuario(usuario);
				aprobacionMasivaDto.setHostRemoto(hostRemoto);
				
				try {
					//Insertar
					List<Prestamo> listaPrestamosError = (List<Prestamo>) datos.get("ERROR");
					if (listaPrestamosError.size() > 0) {
						listadoHisAprMasivas = modificarPdaErrores(listaPrestamosError,listadoHisAprMasivas);
					}
					if (listadoHisAprMasivas.size() > 0) {
						log.info("Inicio Proceso de Insertar Historico de Aprbacion Masiva");
						insertarHistoricoAprobacionMasiva(listadoHisAprMasivas);
						log.info("Fin Proceso de Insertar Historico de Aprbacion Masiva");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							log.error("Error al interrumpir la insercion de historicos", e);
						}
						//Enviar Proceso Cola
						List<AprobacionDto> aprobacionTotal = (List<AprobacionDto>) datos.get("APROBADOS");
						int tamanioAprobados = aprobacionTotal.size();
						if (tamanioAprobados > 10) {
							HashMap<String,Integer> tamanioCola = tamanioColas(tamanioAprobados);
							int tamanioPrimeraColaFinal = ((Integer) tamanioCola.get("cola1")).intValue();
							int tamanioSegundaColaFinal = tamanioPrimeraColaFinal + ((Integer) tamanioCola.get("cola2")).intValue();
							int tamanioTerceraColaFinal = tamanioSegundaColaFinal + ((Integer) tamanioCola.get("cola3")).intValue();
							List<AprobacionDto> primeraCola = new ArrayList<AprobacionDto>();
							List<AprobacionDto> segundaCola = new ArrayList<AprobacionDto>();
							List<AprobacionDto> terceraCola = new ArrayList<AprobacionDto>();
							int contador = 1;
							for (AprobacionDto aprobacionDto: aprobacionTotal) {
								if (contador <= tamanioPrimeraColaFinal) {
									primeraCola.add(aprobacionDto);
								} else if (contador > tamanioPrimeraColaFinal && 
										contador <= tamanioSegundaColaFinal) {
									segundaCola.add(aprobacionDto);
								} else if (contador > tamanioSegundaColaFinal && 
										contador <= tamanioTerceraColaFinal) {
									terceraCola.add(aprobacionDto);
								}
								contador = contador + 1;
							}
							
							aprobacionMasivaDto.setListaAprobacion(segundaCola);
							aprobacionCreditoServicio.encolarTramiteAprobacionMasiva(aprobacionMasivaDto);
							
							aprobacionMasivaDto.setListaAprobacion(terceraCola);
							aprobacionCreditoServicio.encolarTramiteAprobacionMasiva(aprobacionMasivaDto);
							
							aprobacionMasivaDto.setListaAprobacion(primeraCola);
							aprobacionCreditoServicio.encolarTramiteAprobacionMasiva(aprobacionMasivaDto);
							
						} else {
							aprobacionMasivaDto.setListaAprobacion(aprobacionTotal);
							aprobacionCreditoServicio.encolarTramiteAprobacionMasiva(aprobacionMasivaDto);
						}
					} else {
						throw new SolicitudException("No existen solicitudes a Aprobar");
					}
					
				} catch (HistoricoAprobacionesMasivasExcepcion e) {
					throw new SolicitudException(e.getMessage(), e);
				} 
			}
		} catch (AprobacionCreditoSenderException ae) {
			throw new SolicitudException(ae.getMessage(), ae);
		}	
	}
	
	private HashMap<String,Integer> tamanioColas(int tamanio) {
		HashMap<String,Integer> tamanioCola = new HashMap<String,Integer>();
		double tamanioPrimeraAux = (tamanio*40.00)/100.00;
		
		int parteEnteraPrimera = (int) tamanioPrimeraAux;
		double parteDecimalPrimera = tamanioPrimeraAux - parteEnteraPrimera;
		
		int tamanioPrimera = 0;
		if (parteDecimalPrimera > 0) {
			tamanioPrimera = parteEnteraPrimera + 1;
		} else {
			tamanioPrimera = parteEnteraPrimera;
		}
		double tamanioSegundaAux = ((tamanio - tamanioPrimera) * 50.00)/100.00;		
		int tamanioSegunda = (int) tamanioSegundaAux;
		int tamanioTercera = tamanio - tamanioPrimera - tamanioSegunda;
		tamanioCola.put("cola1", Integer.valueOf(tamanioPrimera));
		tamanioCola.put("cola2", Integer.valueOf(tamanioSegunda));
		tamanioCola.put("cola3", Integer.valueOf(tamanioTercera));
		return tamanioCola;
	}
	
	private List<HistoricoAprobacionesMasivas> modificarPdaErrores(List<Prestamo> listadoPrestamos, 
			List<HistoricoAprobacionesMasivas> listadoHisAprMasivas) {
		List<HistoricoAprobacionesMasivas> listadoFinalAprMAsivas = 
				new ArrayList<HistoricoAprobacionesMasivas>();
		for (HistoricoAprobacionesMasivas historico: listadoHisAprMasivas) {
			HistoricoAprobacionesMasivasPk historicoPk = historico.getPk();
			String hisNumAfiliado = historico.getIdentificacionAfiliado();
			BigDecimal hisNumpreafi = historicoPk.getNumpreafi();
			Long hisCodprecla = historicoPk.getCodprecla();
			Long hisCodpretip = historicoPk.getCodpretip();
			Long hisOrdpreafi = historicoPk.getOrdpreafi();
			for (Prestamo prestamo: listadoPrestamos) {
					PrestamoPk prestamoPk = prestamo.getPrestamoPk();
					String numAfiliado = prestamo.getNumafi();
					BigDecimal numpreafi = new BigDecimal(prestamoPk.getNumpreafi());
					Long codprecla = prestamoPk.getCodprecla();
					Long codpretip = prestamoPk.getCodpretip();
					Long ordpreafi = prestamoPk.getOrdpreafi();
					
				if (hisNumAfiliado.equals(numAfiliado) 
					&& hisNumpreafi.compareTo(numpreafi) == 0 
					&& hisCodprecla.compareTo(codprecla) == 0
					&& hisCodpretip.compareTo(codpretip) == 0
					&& hisOrdpreafi.compareTo(ordpreafi) == 0) {
					historico.setProcesada("NO");
					break;
				}
			}
			listadoFinalAprMAsivas.add(historico);
		}
		
		return listadoFinalAprMAsivas;
	}
	
	private ArrayList<String> procesarAutorizacionPDVMasivos(List<Prestamo> listadoPrestamosPDV,
			Date fechaAprobacion, String cedulaFuncionario, BigDecimal secuencialHistorico) {
		ArrayList<String> solicitudesAprobadas = new ArrayList<String>();
		String numAfi = "";
		String nombreAfiliado = "";
		for (Prestamo prestamo: listadoPrestamosPDV) {
			numAfi = prestamo.getNumafi();
			nombreAfiliado = prestamo.getAfiliado().getApenomafi();
			//Historico Masivos Actualizar
			PrestamoPk prestamoPk = prestamo.getPrestamoPk();
			HistoricoAprobacionesMasivasPk historicoAprobacionesMasivasPk = new HistoricoAprobacionesMasivasPk();
			historicoAprobacionesMasivasPk.setIdHistorico(secuencialHistorico);
			historicoAprobacionesMasivasPk.setNumpreafi(new BigDecimal(prestamoPk.getNumpreafi()));
			historicoAprobacionesMasivasPk.setOrdpreafi(prestamoPk.getOrdpreafi());
			historicoAprobacionesMasivasPk.setCodpretip(prestamoPk.getCodpretip());
			historicoAprobacionesMasivasPk.setCodprecla(prestamoPk.getCodprecla());
			HistoricoAprobacionesMasivas historicoAprobacionesMasivas = new HistoricoAprobacionesMasivas();
			historicoAprobacionesMasivas.setPk(historicoAprobacionesMasivasPk);
			historicoAprobacionesMasivas.setIdentificacionAfiliado(numAfi);
			historicoAprobacionesMasivas.setNombreAfiliado(nombreAfiliado);
			historicoAprobacionesMasivas.setIdentificacionUsuario(cedulaFuncionario);
			historicoAprobacionesMasivas.setFechaProceso(fechaAprobacion);
			historicoAprobacionesMasivas.setProcesada("SI");
			historicoAprobacionesMasivas.setEstadoProceso(prestamo.getEstadoPrestamo().getCodestpre());
			historicoAprobacionesMasivasDao.update(historicoAprobacionesMasivas);
			//Fin Historico Masivos Actualizar
			this.actualizarcabeceraprestamoPDV(prestamoPk);
			solicitudesAprobadas.add(nombreAfiliado + ", " + numAfi + "\n");
		}
		return solicitudesAprobadas;
	}
		
	private void actualizarcabeceraprestamoPDV(PrestamoPk pk) {

        // INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
        Prestamo pr = prestamoDao.load(pk);

        EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
        estadoPrestamo.setCodestpre("PDV");
	        
        pr.setEstadoPrestamo(estadoPrestamo);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.error("Error al actualizar estado PDV", e);
		}

        prestamoDao.update(pr);
    }
    
	/**
	 * @param fechaaprobacion
	 * @param cedulafuncionario
	 * @throws SolicitudException
	 */
	private HashMap<String,Object> recalculaTablaAmortizacionPDAMasivos(List<Prestamo> listaPrestamos, Date fechaAprobacion, String cedulaFuncionario)
			{
		HashMap<String,Object> datos = new HashMap<String,Object>();
		List<AprobacionDto> listaAprobacionDto = new ArrayList<AprobacionDto>();
		List<Prestamo> listaPrestamosError = new ArrayList<Prestamo>();
		boolean agregar = false;
		for(Prestamo prestamoLocal: listaPrestamos) {
			this.log.info("INICIO DE CREACION DE OBJETO DE APROBACION DE CREDITO");
			agregar = false;
			try {
				recalculaTablaAmortizacionparaPDA(prestamoLocal,fechaAprobacion,cedulaFuncionario);
				agregar = true;
			} catch (SolicitudException e) {
				this.log.info("ERROR RECALCULAR TABLA AMORTIZACION");
				e.printStackTrace();
			} catch (TablaAmortizacionSacException e) {
				this.log.info("Error al obtener tabla de amortizacion SAC", e);
			}
			if (agregar) {
				listaAprobacionDto.add(aprobacionDto);
			} else {
				listaPrestamosError.add(prestamoLocal);
			}
			this.log.info("FIN DE CREACION DE OBJETO DE APROBACION DE CREDITO");
		} 
		datos.put("APROBADOS", listaAprobacionDto);
		datos.put("ERROR", listaPrestamosError);
		this.log.info("EXITO : CONCLUYE EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION MASIVO------->>>>");
		return datos;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio
	 * #recalculaTablaAmortizacionparaPDA(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date)
	 */
	public void recalculaTablaAmortizacionparaPDA(Prestamo prestamo, Date fechaAprobacion, String cedulaFuncionario)
			throws SolicitudException, TablaAmortizacionSacException {
		aprobacionDto = new AprobacionDto();
		PrestamoPk prestamoPk = prestamo.getPrestamoPk();
		String numAfiliado = prestamo.getNumafi();
		Long codpretip = prestamoPk.getCodpretip();
		long codprecla = prestamoPk.getCodprecla().longValue();
		Long creditoEspecial = prestamo.getCreditoEspecial();
		TipoCuenta tipoCuenta = prestamo.getTipoCuenta();
		String numeroCuentaBanco = prestamo.getNumctaban();
		String rucInstitucionFinanciera = prestamo.getRucempinsfin();
		Double valorSeguroSaldos = prestamo.getValsegsal();
		String codigoEstadoPrestamo = prestamo.getEstadoPrestamo().getCodestpre();
		String tipoBeneficiario = prestamo.getTipoBeneficiario();
		
		DatosOrdenCompra oc = new DatosOrdenCompra();
		// INC-2148 refugiados.
		oc.setCodigoProducto(TiposProductosPq.getTiposProductosPq(codpretip).toString());
	
		Solicitante solicitante = null;
		PrestamoCalculo prestamoCalculo = null;

		this.log.info("EMPIEZA EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");
		DatosCredito datosCredito = new DatosCredito();
		datosCredito.setFechaSolicitud(fechaAprobacion);
		datosCredito.setOrdenCompra(oc);

		if (codprecla == 22L)
			datosCredito.setTipoPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
		else if (codprecla == 20L)
			datosCredito.setTipoPrestamista(TipoPrestamista.AFILIADO);
		else if ((codprecla == 21L)
				|| (codprecla == 24L)
				|| (codprecla == 25L)) {
			datosCredito.setTipoPrestamista(TipoPrestamista.JUBILADO);
		}

		datosCredito.setValorSeguroSaldosOrden(new BigDecimal(valorSeguroSaldos.doubleValue()));
		try {
			solicitante = obtenerSolicitante(numAfiliado,
					datosCredito.getTipoPrestamista());
		} catch (SolicitanteExcepcion e) {
			throw new SolicitudException(e.getMessage(), e);
		} catch (NoServicioPrestadoSucursalException en) {
			throw new SolicitudException(en.getMessage(), en);
		}
		
		int edad = calculoCreditoHelper.determinarEdad(solicitante.getDatosPersonales().getFechaNacimiento(), new Date()).intValue();

		this.log.info("INICIO CONFIGURAR CREDITO");
		try {
			configurarDatosCredito(solicitante, datosCredito, prestamo, edad);
		} catch (CalculoCreditoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		this.log.info("FIN CONFIGURAR CREDITO");

		this.log.info("INICIO TASA DE INTERES");
		try {
			BigDecimal plazoCredito = new BigDecimal(datosCredito.getPlazo());
			String tipoProducto = TipoProductoEnum.NORMAL.getDescripcion();
			if (datosCredito.getTipoPrestamista() == TipoPrestamista.JUBILADO) {
				tipoProducto = TipoProductoEnum.JUB_PEN.getDescripcion();
			}
			if (prestamo.getCreditoEspecial() != null) {
				if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
						plazoCredito = plazoCredito.subtract(obtenerMesesGracia());
					}
				}
			datosCredito.setPlazo(plazoCredito.intValue());
			
			datosCredito.setTasaInteres(this.obtenerTasaInteres(plazoCredito, tipoProducto, edad));
			//calcular cuota mensual
			datosCredito.setCuotaMensualMaxima(simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(
					datosCredito.getMonto(), datosCredito.getTasaInteres(), datosCredito.getPlazo(), datosCredito.getTipoTabla()));
		} catch (CondicionCalculoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		this.log.info("FIN TASA DE INTERES");

		this.log.info("INICIO CALCULO DEL CREDITO");
		try {
			//aumentar para corregir lo de tablas de amortizacion
			PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();
			prestamoCalculoAux = prestamoCalculoService.poblarPrestamoCalculoNew(datosCredito);
			
			if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(creditoEspecial)) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			prestamoCalculo = this.calculoCreditoServicio
					.calcularCreditoNew(datosCredito,prestamoCalculoAux);

			datosCredito.setPrestamoCalculo(prestamoCalculo);
		} catch (CalculoCreditoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		catch (TablaAmortizacionException e) {			
			throw new SolicitudException(e.getMessage(), e);
		}
		this.log.info("FIN CALCULO DEL CREDITO");
		try {
			datosCredito.setPrestamoCalculo(prestamoCalculo);
			Date fecPreafi = datosCredito.getFechaSolicitud();
			Date fecPreIniAfi = prestamoCalculo.getFechaInicioPrestamo();
			Date fecPrefinAfi = prestamoCalculo.getFechaFinPrestamo();
			datosCredito.setDividendoCalculoList(prestamoCalculo
					.getDividendoCalculoList());

			datosCredito.setPrestamoCalculo(prestamoCalculo);
			this.log.info("INICIO AGREGAR OBJETO MODIFICACION DIVIDENDOS");
			aprobacionDto.setDatosCredito(datosCredito);
			this.log.info("FIN AGREGAR OBJETO MODIFICACION DIVIDENDOS");

			this.log.info("INICIO HISTORICO DIVIDENDOS");
			PrestamoResumenHistorico prestamoResumenHistorico = new PrestamoResumenHistorico();
			prestamoResumenHistorico.setPrestamo(prestamo);
			prestamoResumenHistorico.setChFecCreacion(prestamo.getFecpreafi());
			prestamoResumenHistorico.setChFecInicio(prestamo.getFecinipre());
			prestamoResumenHistorico.setChFecFin(prestamo.getFecfinpre());
			prestamoResumenHistorico.setChTipoCuenta(tipoCuenta);
			prestamoResumenHistorico.setChNumCtabancaria(numeroCuentaBanco);
			prestamoResumenHistorico.setChRucIntsfinanciera(rucInstitucionFinanciera);

			prestamoResumenHistorico.setChValorDividendo(prestamo
					.getValtotdiv());

			prestamoResumenHistorico.setChMonto(prestamo.getMntpre());
			prestamoResumenHistorico.setChPlazo(prestamo.getPlzmes());
			prestamoResumenHistorico.setChTasa(prestamo.getTasint());

			prestamoResumenHistorico
					.setChIntDiasgracia(prestamo.getIntdiagrc());

			prestamoResumenHistorico.setChValorSegurosaldos(valorSeguroSaldos);

			prestamoResumenHistorico
					.setChObservacion("Tabla de Amortizacion regenerada");

			prestamoResumenHistorico.setChFecTransaccion(new Date());
			prestamoResumenHistorico.setCr_cedulafuncionario(cedulaFuncionario);
			
			this.log.info("INICIO AGREGAR OBJETO MODIFICACION HISTORICO PRESTAMO");
			aprobacionDto.setPrestamoResumenHistorico(prestamoResumenHistorico);
			this.log.info("FIN AGREGAR OBJETO MODIFICACION HISTORICO PRESTAMO");
			
			this.log.info("FIN HISTORICO DIVIDENDOS");

			this.log.info("INICIO ACTUALIZAR CREDITO");
			Calendar cl = Calendar.getInstance();
			cl.setTime(datosCredito.getFechaSolicitud());

			datosCredito.setAnio(new Long(cl.get(1)));
			datosCredito.setMes(new Long(cl.get(2) + 1));

			prestamo.setFecpreafi(fecPreafi);
			prestamo.setAniper(datosCredito.getAnio());
			prestamo.setMesper(datosCredito.getMes());
			EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
			//INC-2272 Pensiones Alimenticias
			// Caso aprobacion externos
			TiposProductosPq tiposProductosPq = TiposProductosPq
					.getTiposProductosPq(codpretip);
			estadoPrestamo.setCodestpre("APR");
			//INC-2286 Ferrocarriles
			//Triple validacion PDA-PAP-PDC-->APR
			// Primero se obtiene la categoria de tipo de producto
			final CategoriaTipoProductoPq catTipoProdPq = TiposProductosPq.getCategoriaTipoProductoPq(tiposProductosPq.getCodigoTipoProducto());
			if (catTipoProdPq == CategoriaTipoProductoPq.CAT_SERVICIOS && tiposProductosPq != TiposProductosPq.PEN
					&& (codigoEstadoPrestamo.equals("PDA") || codigoEstadoPrestamo.equals("PDV"))) {
				// Si es categoria servicios (ferrocarriles, turismo) y diferente de pensiones alimenticias
				// y el estado del prestamo esta en PDA o en PDV actualiza el estado a PAP
				estadoPrestamo.setCodestpre("PAP");
			} else if (catTipoProdPq == CategoriaTipoProductoPq.CAT_SERVICIOS
					&& (tiposProductosPq == TiposProductosPq.PEN || tipoBeneficiario.equals("REF"))
					&& (codigoEstadoPrestamo.equals("PDA") || codigoEstadoPrestamo.equals("PDV"))) {
				// Si es categoria servicios y es pensiones alimenticias o es refugiado/extranjero (REF)
				// y el estado del prestamo esta en PDA o PDV actualiza el estado a PDC
				estadoPrestamo.setCodestpre("PDC");
			} else if (tipoBeneficiario.equals("REF")
					&& (codigoEstadoPrestamo.equals("PDA") || codigoEstadoPrestamo.equals("PDV"))) {
				// Si es refugiado/extranjero (REF) y el estado del prestamo es PDA
				// o es PDV cambia el estado del prestamo a PDC 
				estadoPrestamo.setCodestpre("PDC");
			}
			
			prestamo.setEstadoPrestamo(estadoPrestamo);
			prestamo.setFecinipre(fecPreIniAfi);
			prestamo.setFecfinpre(fecPrefinAfi);
			prestamo.setValtotdiv(Double.valueOf(UtilAjusteCalculo
					.ajusteNumberBaseDatos(
							prestamoCalculo.getValorTotalDividendoMensual())
					.doubleValue()));

			prestamo.setTasint(Double.valueOf(UtilAjusteCalculo.ajusteNumber(
					datosCredito.getTasaInteres(), 2).doubleValue()));

			if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(creditoEspecial)) {
				prestamo.setIntdiagrc(Double.valueOf(UtilAjusteCalculo
						.ajusteNumberBaseDatos(
								prestamoCalculo.getPeriodoGracia().getValor())
						.doubleValue()) + obtenerSumaInteresGracia(datosCredito.getDividendoCalculoList()).doubleValue());
			} else {
				prestamo.setIntdiagrc(Double.valueOf(UtilAjusteCalculo
						.ajusteNumberBaseDatos(
								prestamoCalculo.getPeriodoGracia().getValor())
						.doubleValue()));
			}

			this.log.info("INICIO AGREGAR OBJETO MODIFICACION PRESTAMO");
			aprobacionDto.setPrestamoLocal(prestamo);
			this.log.info("FIN AGREGAR OBJETO MODIFICACION PRESTAMO");
			
			this.log.info("FIN ACTUALIZAR CREDITO");

		} catch (Exception e) {
			this.log.error("ERROR AL REGENERAR LA TABL DE AMORTIZACION: "
					+ prestamoPk.getNumpreafi()
					+ codprecla
					+ codpretip
					+ prestamoPk.getOrdpreafi());

			throw new SolicitudException(e.getMessage(), e);
		}

		this.log.info("EXITO : CONCLUYE EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio
	 * #recalculaTablaAmortizacionparaPDA(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.util.Date)
	 */
	public void recalculaTablaAmortizacionparaPDA(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fechaaprobacion,
			String cedulafuncionario) throws SolicitudException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException {
		PrestamoPk prestamopk = new PrestamoPk(codprecla, codpretip, numpreafi,
				ordpreafi);

		DatosOrdenCompra oc = new DatosOrdenCompra();
		// INC-2148 refugiados.
		oc.setCodigoProducto(TiposProductosPq.getTiposProductosPq(codpretip).toString());
	
		Solicitante solicitante = null;
		PrestamoCalculo prestamoCalculo = null;

		this.log.info("EMPIEZA EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");

		DatosCredito datosCredito = new DatosCredito();

		datosCredito.setFechaSolicitud(fechaaprobacion);

		Prestamo prestamo = (Prestamo) this.prestamoDao.load(prestamopk);

		datosCredito.setOrdenCompra(oc);

		if (prestamo.getPrestamoPk().getCodprecla().longValue() == 22L)
			datosCredito.setTipoPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
		else if (prestamo.getPrestamoPk().getCodprecla().longValue() == 20L)
			datosCredito.setTipoPrestamista(TipoPrestamista.AFILIADO);
		else if ((prestamo.getPrestamoPk().getCodprecla().longValue() == 21L)
				|| (prestamo.getPrestamoPk().getCodprecla().longValue() == 24L)
				|| (prestamo.getPrestamoPk().getCodprecla().longValue() == 25L)) {
			datosCredito.setTipoPrestamista(TipoPrestamista.JUBILADO);
		}

		datosCredito.setValorSeguroSaldosOrden(new BigDecimal(prestamo
				.getValsegsal().doubleValue()));
		try {
			solicitante = obtenerSolicitante(prestamo.getNumafi(),
					datosCredito.getTipoPrestamista());
		} catch (SolicitanteExcepcion e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		
		int edad = calculoCreditoHelper.determinarEdad(solicitante.getDatosPersonales().getFechaNacimiento(), new Date()).intValue();

		this.log.info("INICIO CONFIGURAR CREDITO");
		try {
			configurarDatosCredito(solicitante, datosCredito, prestamo, edad);
		} catch (CalculoCreditoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		this.log.info("FIN CONFIGURAR CREDITO");

		this.log.info("INICIO TASA DE INTERES");
		try {
			BigDecimal plazoCredito = new BigDecimal(datosCredito.getPlazo());
			String tipoProducto = TipoProductoEnum.NORMAL.getDescripcion();
			if (datosCredito.getTipoPrestamista() == TipoPrestamista.JUBILADO) {
				tipoProducto = TipoProductoEnum.JUB_PEN.getDescripcion();
			}
				if (prestamo.getCreditoEspecial() != null) {
					if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
						plazoCredito = plazoCredito.subtract(obtenerMesesGracia());
					}
				}
			
			datosCredito.setPlazo(plazoCredito.intValue());
			
			datosCredito.setTasaInteres(this.obtenerTasaInteres(plazoCredito, tipoProducto, edad));
			//calcular cuota mensual
			datosCredito.setCuotaMensualMaxima(simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(datosCredito.getMonto(), datosCredito.getTasaInteres(), datosCredito.getPlazo(), datosCredito.getTipoTabla()));
		} catch (CondicionCalculoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		this.log.info("FIN TASA DE INTERES");

		this.log.info("INICIO CALCULO DEL CREDITO");
		try {
			//aumentar para corregir lo de tablas de amortizacion
			PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();
			prestamoCalculoAux = prestamoCalculoService.poblarPrestamoCalculoNew(datosCredito);
			
			if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			prestamoCalculo = this.calculoCreditoServicio
					.calcularCreditoNew(datosCredito,prestamoCalculoAux);

			datosCredito.setPrestamoCalculo(prestamoCalculo);
		} catch (CalculoCreditoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		catch (TablaAmortizacionException e) {			
			throw new SolicitudException(e.getMessage(), e);
		}
		this.log.info("FIN CALCULO DEL CREDITO");
		try {
			datosCredito.setPrestamoCalculo(prestamoCalculo);
			Date fecPreafi = datosCredito.getFechaSolicitud();
			Date fecPreIniAfi = prestamoCalculo.getFechaInicioPrestamo();
			Date fecPrefinAfi = prestamoCalculo.getFechaFinPrestamo();
			datosCredito.setDividendoCalculoList(prestamoCalculo
					.getDividendoCalculoList());

			datosCredito.setPrestamoCalculo(prestamoCalculo);
			this.log.info("INICIO ELIMINAR DIVIDENDOS");

			this.dividendoPrestamoServicio
					.eliminarHistoricosDividendos(prestamo.getPrestamoPk());
			this.dividendoPrestamoServicio.eliminarDividendos(prestamo
					.getPrestamoPk());
			this.log.info("FIN ELIMINAR DIVIDENDOS");

			this.log.info("INICIO CREAR DIVIDENDOS");
			this.dividendoPrestamoServicio.crearDividendosPq(datosCredito);
			this.log.info("FIN CREAR DIVIDENDOS");

			this.log.info("INICIO HISTORICO DIVIDENDOS");
			PrestamoResumenHistorico prestamoResumenHistorico = new PrestamoResumenHistorico();
			prestamoResumenHistorico.setPrestamo(prestamo);
			prestamoResumenHistorico.setChFecCreacion(prestamo.getFecpreafi());
			prestamoResumenHistorico.setChFecInicio(prestamo.getFecinipre());
			prestamoResumenHistorico.setChFecFin(prestamo.getFecfinpre());
			prestamoResumenHistorico.setChTipoCuenta(prestamo.getTipoCuenta());
			prestamoResumenHistorico.setChNumCtabancaria(prestamo
					.getNumctaban());

			prestamoResumenHistorico.setChRucIntsfinanciera(prestamo
					.getRucempinsfin());

			prestamoResumenHistorico.setChValorDividendo(prestamo
					.getValtotdiv());

			prestamoResumenHistorico.setChMonto(prestamo.getMntpre());
			prestamoResumenHistorico.setChPlazo(prestamo.getPlzmes());
			prestamoResumenHistorico.setChTasa(prestamo.getTasint());

			prestamoResumenHistorico
					.setChIntDiasgracia(prestamo.getIntdiagrc());

			prestamoResumenHistorico.setChValorSegurosaldos(prestamo
					.getValsegsal());

			prestamoResumenHistorico
					.setChObservacion("Tabla de Amortizacion regenerada");

			prestamoResumenHistorico.setChFecTransaccion(new Date());
			prestamoResumenHistorico.setCr_cedulafuncionario(cedulafuncionario);
			this.prestamoResumenHistoricoDao.insert(prestamoResumenHistorico);

			this.log.info("FIN HISTORICO DIVIDENDOS");

			this.log.info("INICIO ACTUALIZAR CREDITO");
			Calendar cl = Calendar.getInstance();
			cl.setTime(datosCredito.getFechaSolicitud());

			datosCredito.setAnio(new Long(cl.get(1)));
			datosCredito.setMes(new Long(cl.get(2) + 1));

			prestamo.setFecpreafi(fecPreafi);
			prestamo.setAniper(datosCredito.getAnio());
			prestamo.setMesper(datosCredito.getMes());
			EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
			//INC-2272 Pensiones Alimenticias
			// Caso aprobacion externos
			TiposProductosPq tiposProductosPq = TiposProductosPq
					.getTiposProductosPq(codpretip); 
			estadoPrestamo.setCodestpre("APR");
			//INC-2286 Ferrocarriles
			//Triple validacion PDA-PAP-PDC-->APR
			// Primero se obtiene la categoria de tipo de producto
			final CategoriaTipoProductoPq catTipoProdPq = TiposProductosPq.getCategoriaTipoProductoPq(tiposProductosPq.getCodigoTipoProducto());
			if (catTipoProdPq == CategoriaTipoProductoPq.CAT_SERVICIOS && tiposProductosPq != TiposProductosPq.PEN
					&& (prestamo.getEstadoPrestamo().getCodestpre().equals("PDA") || prestamo.getEstadoPrestamo().getCodestpre().equals("PDV"))) {
				// Si es categoria servicios (ferrocarriles, turismo) y diferente de pensiones alimenticias
				// y el estado del prestamo esta en PDA o en PDV actualiza el estado a PAP
				estadoPrestamo.setCodestpre("PAP");
			} else if (catTipoProdPq == CategoriaTipoProductoPq.CAT_SERVICIOS
					&& (tiposProductosPq == TiposProductosPq.PEN || prestamo.getTipoBeneficiario().equals("REF"))
					&& (prestamo.getEstadoPrestamo().getCodestpre().equals("PDA") || prestamo.getEstadoPrestamo().getCodestpre().equals("PDV"))) {
				// Si es categoria servicios y es pensiones alimenticias o es refugiado/extranjero (REF)
				// y el estado del prestamo esta en PDA o PDV actualiza el estado a PDC
				estadoPrestamo.setCodestpre("PDC");
			} else if (prestamo.getTipoBeneficiario().equals("REF")
					&& (prestamo.getEstadoPrestamo().getCodestpre().equals("PDA") || prestamo.getEstadoPrestamo().getCodestpre().equals("PDV"))) {
				// Si es refugiado/extranjero (REF) y el estado del prestamo es PDA
				// o es PDV cambia el estado del prestamo a PDC 
				estadoPrestamo.setCodestpre("PDC");
			}
			
			prestamo.setEstadoPrestamo(estadoPrestamo);
			prestamo.setFecinipre(fecPreIniAfi);
			prestamo.setFecfinpre(fecPrefinAfi);
			prestamo.setValtotdiv(Double.valueOf(UtilAjusteCalculo
					.ajusteNumberBaseDatos(
							prestamoCalculo.getValorTotalDividendoMensual())
					.doubleValue()));

			prestamo.setTasint(Double.valueOf(UtilAjusteCalculo.ajusteNumber(
					datosCredito.getTasaInteres(), 2).doubleValue()));

			if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
				prestamo.setIntdiagrc(Double.valueOf(UtilAjusteCalculo
						.ajusteNumberBaseDatos(
								prestamoCalculo.getPeriodoGracia().getValor())
						.doubleValue()) + obtenerSumaInteresGracia(datosCredito.getDividendoCalculoList()).doubleValue());
			} else {
				prestamo.setIntdiagrc(Double.valueOf(UtilAjusteCalculo
						.ajusteNumberBaseDatos(
								prestamoCalculo.getPeriodoGracia().getValor())
						.doubleValue()));
			}

			this.prestamoDao.update(prestamo);
			this.log.info("FIN ACTUALIZAR CREDITO");

			this.ctabancariaaprservicio.actulizarctalistablanca(prestamo
					.getNumafi(), prestamo.getInstitucionfinanciera()
					.getNomemp(), prestamo.getNumctaban(), prestamo
					.getRucempinsfin(), prestamo.getTipoCuenta(),
					cedulafuncionario, prestamo.getEstadoPrestamo().getCodestpre());
		} catch (Exception e) {
			this.log.error("ERROR AL REGENERAR LA TABL DE AMORTIZACION: "
					+ prestamo.getPrestamoPk().getNumpreafi()
					+ prestamo.getPrestamoPk().getCodprecla()
					+ prestamo.getPrestamoPk().getCodpretip()
					+ prestamo.getPrestamoPk().getOrdpreafi());

			throw new SolicitudException(e.getMessage(), e);
		}

		this.log.info("EXITO : CONCLUYE EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void procesarTablaAmortizacionParaPDA(AprobacionDto aprobacion, BigDecimal secuencialHistorico,
			String ipUsuario, String usuario, String hostRemoto)
			throws SolicitudException {
		Prestamo prestamo = aprobacion.getPrestamoLocal();
		PrestamoPk prestamoPk = prestamo.getPrestamoPk();
		DatosCredito datosCredito = aprobacion.getDatosCredito();
		PrestamoResumenHistorico prestamoResumenHistorico = aprobacion.getPrestamoResumenHistorico();
		String cedula = prestamo.getNumafi();
		String estadoPrestamo = prestamo.getEstadoPrestamo().getCodestpre();
		String numPreAfi = prestamoPk.getNumpreafi().toString();
		String ordPreafi = prestamoPk.getOrdpreafi().toString();
		String codPreTip = prestamoPk.getCodpretip().toString();
		String codPreCla = prestamoPk.getCodprecla().toString();
		String cedulaFuncionario = prestamoResumenHistorico.getCr_cedulafuncionario();
		this.log.debug("prestamo.getNumafi(): " + cedula);
		this.log.debug("prestamo.getAfiliado().getApenomafi(): " + prestamo.getAfiliado().getApenomafi());
		try {
			this.log.info("INICIO ELIMINAR DIVIDENDOS");
			this.dividendoPrestamoServicio
					.eliminarHistoricosDividendos(prestamo.getPrestamoPk());
			this.dividendoPrestamoServicio.eliminarDividendos(prestamo
					.getPrestamoPk());
			this.log.debug("FIN ELIMINAR DIVIDENDOS");
			this.log.debug("INICIO CREAR DIVIDENDOS");
			this.dividendoPrestamoServicio.crearDividendosPq(datosCredito);
			this.log.debug("FIN CREAR DIVIDENDOS");
			this.log.debug("INICIO CREAR HISTORICO DIVIDENDOS");
			this.prestamoResumenHistoricoDao.insert(prestamoResumenHistorico);
			this.log.debug("FIN CREAR HISTORICO DIVIDENDOS");
			this.log.debug("INICIO MODIFICACION PRESTAMO");
			this.prestamoDao.update(prestamo);
			this.log.debug("FIN MODIFICACION PRESTAMO");
			this.log.debug("INICIO MODIFICAR LISTA BLANCA");
			this.ctabancariaaprservicio.actulizarctalistablanca(cedula, 
					prestamo.getInstitucionfinanciera()
					.getNomemp(), prestamo.getNumctaban(), prestamo
					.getRucempinsfin(), prestamo.getTipoCuenta(), 
					cedulaFuncionario, estadoPrestamo);
			this.log.debug("FIN MODIFICAR LISTA BLANCA");
			if (secuencialHistorico != null) {
				this.log.debug("INICIO ACTUALIZAR HISTORICO APROBACION MASIVA");
				HistoricoAprobacionesMasivasPk historicoAprobacionesMasivasPk = new HistoricoAprobacionesMasivasPk();
				historicoAprobacionesMasivasPk.setIdHistorico(secuencialHistorico);
				historicoAprobacionesMasivasPk.setNumpreafi(new BigDecimal(prestamoPk.getNumpreafi()));
				historicoAprobacionesMasivasPk.setOrdpreafi(prestamoPk.getOrdpreafi());
				historicoAprobacionesMasivasPk.setCodpretip(prestamoPk.getCodpretip());
				historicoAprobacionesMasivasPk.setCodprecla(prestamoPk.getCodprecla());
				HistoricoAprobacionesMasivas historicoAprobacionesMasivas = new HistoricoAprobacionesMasivas();
				historicoAprobacionesMasivas.setPk(historicoAprobacionesMasivasPk);
				historicoAprobacionesMasivas.setIdentificacionAfiliado(cedula);
				historicoAprobacionesMasivas.setNombreAfiliado(prestamo.getAfiliado().getApenomafi());
				historicoAprobacionesMasivas.setIdentificacionUsuario(cedulaFuncionario);
				historicoAprobacionesMasivas.setFechaProceso(datosCredito.getFechaSolicitud());
				historicoAprobacionesMasivas.setProcesada("SI");
				historicoAprobacionesMasivas.setEstadoProceso(estadoPrestamo);
				historicoAprobacionesMasivasDao.update(historicoAprobacionesMasivas);
				this.log.debug("FIN ACTUALIZAR HISTORICO APROBACION MASIVA");
			}
			
			prestamo.setTipoPrestamo(tipoPrestamoServicio.load(Long.valueOf(codPreTip)));
			
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosAprobarPrestamoMasivo(cedula, 
					estadoPrestamo, prestamo.getTipoPrestamo().getDescripcion(), 
					numPreAfi, codPreCla, codPreTip, ordPreafi, 
					Utilities.getFormatedDate(datosCredito.getFechaSolicitud(), "dd/MM/yyyy"));
			this.servicioAuditoria.guardarAuditoriaProcesosMasivos(OperacionEnum.APROBAR_SOLICITUDES_MASIVO, parametroEvento, 
					usuario, ipUsuario, hostRemoto);
			// Fin Auditoria
			
		} catch (Exception e) {
			throw new SolicitudException(e.getMessage(), e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarHistoricoMasivoParaPDA(AprobacionDto dato, BigDecimal secuencialHistorico)
			throws SolicitudException {
		try {
			PrestamoPk prestamoPk = dato.getPrestamoLocal().getPrestamoPk();
			HistoricoAprobacionesMasivasPk historicoAprobacionesMasivasPk = new HistoricoAprobacionesMasivasPk();
			historicoAprobacionesMasivasPk.setIdHistorico(secuencialHistorico);
			historicoAprobacionesMasivasPk.setNumpreafi(new BigDecimal(prestamoPk.getNumpreafi()));
			historicoAprobacionesMasivasPk.setOrdpreafi(prestamoPk.getOrdpreafi());
			historicoAprobacionesMasivasPk.setCodpretip(prestamoPk.getCodpretip());
			historicoAprobacionesMasivasPk.setCodprecla(prestamoPk.getCodprecla());
			HistoricoAprobacionesMasivas historicoAprobacionesMasivas = new HistoricoAprobacionesMasivas();
			historicoAprobacionesMasivas.setPk(historicoAprobacionesMasivasPk);
			historicoAprobacionesMasivas.setIdentificacionAfiliado(dato.getPrestamoLocal().getNumafi());
			historicoAprobacionesMasivas.setNombreAfiliado(dato.getPrestamoLocal().getAfiliado().getApenomafi());
			historicoAprobacionesMasivas.setIdentificacionUsuario(dato.getPrestamoResumenHistorico().getCr_cedulafuncionario());
			historicoAprobacionesMasivas.setFechaProceso(dato.getDatosCredito().getFechaSolicitud());
			historicoAprobacionesMasivas.setProcesada("NO");
			historicoAprobacionesMasivas.setEstadoProceso(dato.getPrestamoLocal().getEstadoPrestamo().getCodestpre());
			historicoAprobacionesMasivasDao.update(historicoAprobacionesMasivas);
		} catch (Exception e) {
			throw new SolicitudException(e.getMessage(), e);
		}
	}
	
	/**
	 * Obtiene la sumatoria del interes dle periodo de gracia
	 * 
	 * @param dividendoCalculoList
	 * @return
	 */
	private BigDecimal obtenerSumaInteresGracia(List<DividendoCalculo> dividendoCalculoList) {
		BigDecimal resp = BigDecimal.ZERO;
		for (DividendoCalculo dividendoCalculo : dividendoCalculoList) {
			resp = resp.add(dividendoCalculo.getValorIntPerGra());
		}
		return resp;
	}
	
	/**
	 * Obtiene los meses de gracia
	 * 
	 * @return
	 */
	private BigDecimal obtenerMesesGracia() {
		BigDecimal resp = BigDecimal.ZERO;
		try {
			resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
					BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico();
		} catch (ParametroBiessException e) {
			log.error("Error al leer parametro de meses de gracia >>", e);
		}
		return resp;
	}
	
	/**
	 * Devuelve la tasa de interes dado el plazo
	 * 
	 * @param plazo
	 *            Es el plazo en meses
	 * @param tipoProducto
	 * @param tipoPrestamista
	 * @param edad
	 * @return
	 * @throws CondicionCalculoException
	 */
	private BigDecimal obtenerTasaInteres(BigDecimal plazo, String tipoProducto, int edad)
			throws CondicionCalculoException {
		BigDecimal resp = condicionCalculoServicio.obtenerTasaInteresPorPlazo(plazo, tipoProducto, edad);
		return resp;
	}

	// / Fecha de Aprobacion del Crédito - Fecha en la que se aprobó el
	// crédito(en la que cambio de estado de PDA a APR) o la fecha actual en la
	// que se ejecutará el CRON
	// Trabaja directamente con la tabla KSCRETDIVIDENDOS

	/**
	 * Recalculo de la Tabla de Amortización para CRON
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
	 * @author rtituaña
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio#recalculaTablaAmortizacionparaCRON(
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.util.Date)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void recalculaTablaAmortizacionparaCRON(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla, Date fechaaprobacion)
			throws SolicitudException, TablaAmortizacionSacException, NoServicioPrestadoSucursalException {
		// Creo la Clave Primaria del Préstamo PQ
		PrestamoPk prestamopk = new PrestamoPk(codprecla, codpretip, numpreafi,
				ordpreafi);
		// Creo el Orden de Compra para que pase la Validación
		DatosOrdenCompra oc = new DatosOrdenCompra();
		// INC-2148 refugiados.
		oc.setCodigoProducto(TiposProductosPq.getTiposProductosPq(codpretip).toString());

		Solicitante solicitante = null;
		PrestamoCalculo prestamoCalculo = null;

		log.info("EMPIEZA EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");

		// Creo una instancia de DatosCredito
		DatosCredito datosCredito = new DatosCredito();

		// Fecha de Aprobación de la Solicitud
		datosCredito.setFechaSolicitud(fechaaprobacion);

		// Obtengo el Préstamo
		Prestamo prestamo = prestamoDao.load(prestamopk);

		// Seteamos la Orden de compra para que pase la validacion al Calcular
		// el Crédito
		datosCredito.setOrdenCompra(oc);

		// Determino el Tipo de Préstamo
		if (prestamo.getPrestamoPk().getCodprecla() == 22) {
			datosCredito.setTipoPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
		} else if (prestamo.getPrestamoPk().getCodprecla() == 20) {
			datosCredito.setTipoPrestamista(TipoPrestamista.AFILIADO);
		} else if (prestamo.getPrestamoPk().getCodprecla() == 21
				|| prestamo.getPrestamoPk().getCodprecla() == 24
				|| prestamo.getPrestamoPk().getCodprecla() == 25) {
			datosCredito.setTipoPrestamista(TipoPrestamista.JUBILADO);
		}

		datosCredito.setValorSeguroSaldosOrden(new BigDecimal(prestamo
				.getValsegsal()));

		// Determino el Solicitante
		try {
			solicitante = obtenerSolicitante(prestamo.getNumafi(),
					datosCredito.getTipoPrestamista());
		} catch (SolicitanteExcepcion e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		
		int edad = calculoCreditoHelper.determinarEdad(solicitante.getDatosPersonales().getFechaNacimiento(), new Date()).intValue();

		// Determino los Valores de los Datos del Credito
		log.info("INICIO CONFIGURAR CREDITO");
		try {
			configurarDatosCredito(solicitante, datosCredito, prestamo, edad);
		} catch (CalculoCreditoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		log.info("FIN CONFIGURAR CREDITO");

		// Determino la Tasa de Interés
		log.info("INICIO TASA DE INTERES");
		try {
			BigDecimal plazoCredito = new BigDecimal(datosCredito.getPlazo());
			String tipoProducto = "NORMAL";
			if (datosCredito.getTipoPrestamista() == TipoPrestamista.JUBILADO) {
				tipoProducto = TipoProductoEnum.JUB_PEN.getDescripcion();
			} 
			if (prestamo.getCreditoEspecial() != null) {
				if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
					plazoCredito = plazoCredito.subtract(obtenerMesesGracia());
				}
			}
			datosCredito.setTasaInteres(this.obtenerTasaInteres(plazoCredito, tipoProducto, edad));
			datosCredito.setPlazo(plazoCredito.intValue());
			//calcular cuota mensual
			datosCredito.setCuotaMensualMaxima(simularCreditoServicio.obtenerCuotaMaximaPorTipoTabla(datosCredito.getMonto(), datosCredito.getTasaInteres(), datosCredito.getPlazo(), datosCredito.getTipoTabla()));
		} catch (CondicionCalculoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		log.info("FIN DE TASA DE INTERES");
		log.info("INICIO DE CALCULO DEL CREDITO");

		// Determinamos el Caculo del Credito
		try {
			//aumentar para corregir lo de tablas de amortizacion
			PrestamoCalculo prestamoCalculoAux = new PrestamoCalculo();
			prestamoCalculoAux = prestamoCalculoService.poblarPrestamoCalculoNew(datosCredito);
			if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
				datosCredito.setCreditoEspecial(CreditoEspecialEnum.EMERGENTE.getCodigoCredito());
			}
			
			prestamoCalculo = this.calculoCreditoServicio
					.calcularCreditoNew(datosCredito,prestamoCalculoAux);			
			
			datosCredito.setPrestamoCalculo(prestamoCalculo);
		} catch (CalculoCreditoException e) {
			throw new SolicitudException(e.getMessage(), e);
		}
		catch (TablaAmortizacionException e) {			
			throw new SolicitudException(e.getMessage(), e);
		}
		log.info("FIN DE CALCULO DEL CREDITO");

		try {
			datosCredito.setPrestamoCalculo(prestamoCalculo);

			datosCredito.setDividendoCalculoList(prestamoCalculo
					.getDividendoCalculoList());
			datosCredito.setPrestamoCalculo(prestamoCalculo);
			// Creamos los Nuevos Dividendos
			log.info("INICIO CREAR DIVIDENDOS");
			dividendoPrestamoServicio.crearDividendosPq(datosCredito);
			log.info("FIN CREAR DIVIDENDOS");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("ERROR AL REGENERAR LA TABLA DE AMORTIZACION: "
					+ prestamo.getPrestamoPk().getNumpreafi()
					+ prestamo.getPrestamoPk().getCodprecla()
					+ prestamo.getPrestamoPk().getCodpretip()
					+ prestamo.getPrestamoPk().getOrdpreafi());
			throw new SolicitudException(e.getMessage(), e);
		}
		log.info("EXITO : CONCLUYE EL PROCESO DE RECALCULO DE LA TABLA DE AMORTIZACION------->>>>");
	}

	private void configurarDatosCredito(Solicitante solicitante,
			DatosCredito datosCredito, Prestamo prestamo, int edad)
			throws CalculoCreditoException {

		String cedula = solicitante.getDatosPersonales().getCedula();
		String genero = solicitante.getDatosPersonales().getGenero()
				.getCodigo();
		Date fechaNacimiento = solicitante.getDatosPersonales()
				.getFechaNacimiento();
		OrigenJubilado origenJubilado = solicitante.getOrigenJubilado();

		DatosGarantia datosGarantia = obtenerDatosGarantia(cedula,
				datosCredito.getTipoPrestamista());
		datosGarantia.setIdTipocredito(prestamo.getPrestamoPk().getCodpretip()
				.intValue());
		datosGarantia.setIdVariedadCredito(prestamo.getPrestamoPk()
				.getCodprecla().intValue());
		datosGarantia.setMontocredito(new BigDecimal(prestamo.getMntpre()));
		datosCredito.setPlazo(prestamo.getPlzmes().intValue());
		int plazoCredito=datosCredito.getPlazo();
		if (prestamo.getCreditoEspecial()!=null && CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
			BigDecimal plazoCreditoAux = new BigDecimal(datosCredito.getPlazo());
			BigDecimal nuevoPlazo=plazoCreditoAux.subtract(obtenerMesesGracia());
			plazoCredito=nuevoPlazo.intValue();
		
		}
		BigDecimal montoMaximo = BigDecimal.ZERO;
		try {
			montoMaximo = garantiaCreditoServicio.obtenerMontoMaximo(new BigDecimal(plazoCredito),
					datosCredito.getTipoPrestamista(), edad);
		} catch (ParametrizacionPQException e) {
			throw new CalculoCreditoException(e);
		} catch (ParametroCreditoException e) {
			throw new CalculoCreditoException(e);
		}
		datosGarantia.setParametroMontoMaximo(montoMaximo);

		TipoDividendo tipoDividendo = new TipoDividendo();
		tipoDividendo.setCodtipdiv("NOR");
		datosCredito.setTipoDividendo(tipoDividendo);

		EstadoDividendoPrestamo estadoDividendoPrestamo = new EstadoDividendoPrestamo();
		estadoDividendoPrestamo.setCodestdiv("REG");
		datosCredito.setEstadoDividendoPrestamo(estadoDividendoPrestamo);

		datosCredito.setCedulaAfiliado(cedula);
		datosCredito.setGenero(genero);
		datosCredito.setFechaNacimiento(fechaNacimiento);
		datosCredito.setOrigenJubilado(origenJubilado);
		datosCredito.setGarantia(datosGarantia);
		// tipo credito
		datosCredito.setIdTipoCredito(prestamo.getPrestamoPk().getCodpretip()
				.intValue());
		// tipo id variedad credito
		datosCredito.setIdVariedadcredito(prestamo.getPrestamoPk()
				.getCodprecla().intValue());
		// numero credito
		datosCredito.setNumeroPrestamo(prestamo.getPrestamoPk().getNumpreafi());

		datosCredito.setMonto(new BigDecimal(prestamo.getMntpre()));
		//setear el tipo de tabla de amortizacion
		if(prestamo.getTipoTablaAmortizacion()!=null){
			datosCredito.setTipoTabla(prestamo.getTipoTablaAmortizacion());			
		} else{
			datosCredito.setTipoTabla(TipoTablaEnum.FRANCESA.value());
		}
		
	}
	
	private DatosGarantia obtenerDatosGarantia(String cedula,
			TipoPrestamista tipoPrestamista) {
		ValidarRequisitosFondos validarFondos = new ValidarRequisitosFondos();
		validarFondos.setCedula(cedula);
		validarFondos.setTiposCargos(obtenerTiposCargos());
		validarFondos.setEstadoBloqueado("ACT");
		validarFondos.setEstadosSolicitud(obtenerEstadosSolicitud());

		DatosGarantia datosGarantia = new DatosGarantia();
		datosGarantia.setCedula(cedula);
		datosGarantia.setFechaSolicitud(new Date());
		datosGarantia.setTipoPrestamista(tipoPrestamista);
		datosGarantia.setValReqFondos(validarFondos);
		return datosGarantia;
	}

	private List<String> obtenerTiposCargos() {
		List<String> tipos = new ArrayList<String>();
		tipos.add("PRO");
		tipos.add("REG");
		return tipos;
	}

	private List<String> obtenerEstadosSolicitud() {
		List<String> estados = new ArrayList<String>();
		estados.add("AFP");
		estados.add("AFT");
		estados.add("ANE");
		estados.add("GUI");
		estados.add("REG");
		estados.add("TRA");
		return estados;
	}

	/**
	 * Obtiene el empleador y setea informacion del solicitante
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @return
	 * @throws SolicitanteExcepcion
	 */
	private Solicitante obtenerSolicitante(String cedula, TipoPrestamista tipoPrestamista)
			throws SolicitanteExcepcion, NoServicioPrestadoSucursalException {
		Solicitante solicitante = solicitanteServicio.obtenerSolicitante(cedula, tipoPrestamista);
		try {
			// Determinamos el empleador del solictante segun su rol
			this.solicitanteServicio.determinarEmpleador(cedula, tipoPrestamista, solicitante, false);
		} catch (SucursalException e) {
			throw new SolicitanteExcepcion(e);
		} catch (ServicioPrestadoException e) {
			throw new SolicitanteExcepcion(e);
		}
		return solicitante;
	}

	public Proveedor obtenerProveedor(String rucEmp) throws ProveedorException {
		try {
			Proveedor proveedor = proveedorDao.consultarProveedorRUC(rucEmp);
			return proveedor;
		} catch (Exception e) {
			throw new ProveedorException(e.getMessage(), e);
		}
	}

	public InstitucionFinanciera obtenerInstitucionFinancieraProveedor(
			String rucEmp) throws ProveedorException {
		try {
			InstitucionFinanciera intitucionFin = institucionFinancieraServicio
					.getInstitucionFinanciera(rucEmp);
			return intitucionFin;
		} catch (Exception e) {
			throw new ProveedorException(e.getMessage(), e);
		}
	}

	public void desbloquearPrestamo(Prestamo prestamo, String cedulaFuncionario)
			throws DesbloqueoException {
		try {
			log.error("INICIO HISTORICO DEL CREDITO");
			PrestamoResumenHistorico prestamoResumenHistorico = new PrestamoResumenHistorico();
			prestamoResumenHistorico.setPrestamo(prestamo);
			prestamoResumenHistorico.setChFecCreacion(prestamo.getFecpreafi());
			prestamoResumenHistorico.setChFecInicio(prestamo.getFecinipre());
			prestamoResumenHistorico.setChFecFin(prestamo.getFecfinpre());
			prestamoResumenHistorico.setChTipoCuenta(prestamo.getTipoCuenta());
			prestamoResumenHistorico.setChNumCtabancaria(prestamo
					.getNumctaban());
			prestamoResumenHistorico.setChRucIntsfinanciera(prestamo
					.getRucempinsfin());
			prestamoResumenHistorico.setChValorDividendo(prestamo
					.getValtotdiv());

			prestamoResumenHistorico.setChMonto(prestamo.getMntpre());
			prestamoResumenHistorico.setChPlazo(prestamo.getPlzmes());
			prestamoResumenHistorico.setChTasa(prestamo.getTasint());

			prestamoResumenHistorico
					.setChIntDiasgracia(prestamo.getIntdiagrc());
			prestamoResumenHistorico.setChValorSegurosaldos(prestamo
					.getValsegsal());
			/* Obs segun tipo de bloqueo */
			String obs = "RECHAZO CREDITO ERROR REGISTRO CIVIL (ERC)";
			if (prestamo.getEstadoPrestamo().getCodestpre().equals("ECC")) {
				obs = "RECHAZO CREDITO EN CUENTA BANCARIA (ECC)";
			}			
			prestamoResumenHistorico
					.setChObservacion(obs);
			prestamoResumenHistorico.setChFecTransaccion(new Date());
			prestamoResumenHistorico.setCr_cedulafuncionario(cedulaFuncionario);
			prestamoResumenHistoricoDao.insert(prestamoResumenHistorico);
			log.error("FIN HISTORICO DEL CREDITO");

			log.error("INICIO ACTUALIZAR CREDITO");
			/* Estado actual del prestamo */
			String estadoPrestamoStr = new String(prestamo.getEstadoPrestamo().getCodestpre()); 
			EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
			estadoPrestamo.setCodestpre("REC"); // Cambiar el estado a REC
			prestamo.setEstadoPrestamo(estadoPrestamo);
			prestamo.setObsanupre(obs);
			prestamoDao.update(prestamo);
			/* Se agrega a lista blanca los desbloqueos de tipo ECC */
			if (estadoPrestamoStr.equals("ECC")) {
				ctabancariaaprservicio.actulizarctalistablanca(prestamo
						.getNumafi(), null, null, null, null,
						cedulaFuncionario, "ECC");
			}
			log.error("FIN ACTUALIZAR CREDITO");
		} catch (Exception e) {
			throw new DesbloqueoException(e.getMessage(), e);
		}
	}
	
	/** 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio
	 * #obtenerProveedorPorTipoProducto(java.lang.Long)
	 */
	public Proveedor obtenerProveedorPorTipoProducto(Long codigoTipoProducto)
			throws ProveedorException {
		//INC-2272 Pensiones Alimenticias
		return proveedorDao.obtenerProveedorPorTipoProducto(codigoTipoProducto);
	}

	/**
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio#getListaProveedorPorTipoProducto(
	 * java.lang.Long)
	 */
	public List<Proveedor> obtenerListaProveedorPorTipoProducto(Long codigoTipoProducto) throws ProveedorException {
		return proveedorDao.obtenerListaProveedorPorTipoProducto(codigoTipoProducto);
	}
	
	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio#insertarHistoricoAprobacionMasiva(java.util.List)
	 */
	@Override
	public void insertarHistoricoAprobacionMasiva(List<HistoricoAprobacionesMasivas> listadoHisAprMasivas)
			throws HistoricoAprobacionesMasivasExcepcion {
		historicoAprobacionesMasivasDao.insertarHistoricoAprobacionMasiva(listadoHisAprMasivas);
	}
	
	@Override
	public BigDecimal consultarCodigoHistorico() {
		return historicoAprobacionesMasivasDao.consultarCodigoHistorico();
	}
	
	/**
	 * Envia el correo electronico cuando se realiza el ajuste de planillas sin usar colas de mensajeria
	 * 
	 * @param reversoPlanilla
	 */
	private void enviarEmailAprobacionMasivos(ArrayList<String> cuerpoMensaje, BigDecimal numeroProceso, String destinatario, String nombreFuncionario) {
		try {
			String mensajeAprobados = "Para el proceso " + numeroProceso + " se aprobo las solicitudes de las siguientes personas:\n\n";
			InformacionAfiliado dp = new InformacionAfiliado();			
			//dp.setCedula(userBean.getCedulaUsuario());
			dp.setApellidosNombres(nombreFuncionario);
			dp.setEmail(destinatario);
			/* Parametros del mensaje */
	        Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", dp.getApellidosNombres());	        
	        alertData.put("msg_fecha", Utilities.getCurrentDate("dd/MM/yyyy HH:mm"));
	        alertData.put("msg_aprobacion", mensajeAprobados);
	        alertData.put("aprobadosList", cuerpoMensaje);
	        
	        freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
	        
	        Configuration cfg = new Configuration();
			/* Cargar el template */
			cfg.setClassForTemplateLoading(AdministracionOrdenCompraProveedorServicioImpl.class, "/");
			Template template = cfg.getTemplate(PATH_TEMPLATE);
	        
	        alertUserHelper.sendAlert(dp, "Notificaci\u00F3n Banco del IESS", template, alertData, null, AlertType.EMAIL);
	        log.info("El correo electronico fue enviado correctamente");
		} catch (SmsAlertUserException e) {
			log.error("Error en el envio de notificaciones SMS", e);
		} catch (ClassNotFoundException e) {
			log.error("Error en el envio de notificaciones", e);
		} catch (IOException e) {
			log.error("Error desconocido en el envio de notificaciones", e);
		} catch (EmailAlertUserException e) {
			log.error("Error en el envio de notificaciones Email", e);
		}
	}
	
	public void validarCesanteAmortizacionparaPDA(Long numpreafi, Long ordpreafi, Long codpretip, Long codprecla) 
			throws SolicitudException, NoServicioPrestadoSucursalException {
		PrestamoPk prestamopk = new PrestamoPk(codprecla, codpretip, numpreafi,
				ordpreafi);

		DatosOrdenCompra oc = new DatosOrdenCompra();
		oc.setCodigoProducto(TiposProductosPq.getTiposProductosPq(codpretip).toString());
		DatosCredito datosCredito = new DatosCredito();
		Prestamo prestamo = (Prestamo) this.prestamoDao.load(prestamopk);
		datosCredito.setOrdenCompra(oc);
		if (prestamo.getPrestamoPk().getCodprecla().longValue() == 22L)
			datosCredito.setTipoPrestamista(TipoPrestamista.ZAFRERO_TEMPORAL);
		else if (prestamo.getPrestamoPk().getCodprecla().longValue() == 20L)
			datosCredito.setTipoPrestamista(TipoPrestamista.AFILIADO);
		else if ((prestamo.getPrestamoPk().getCodprecla().longValue() == 21L)
				|| (prestamo.getPrestamoPk().getCodprecla().longValue() == 24L)
				|| (prestamo.getPrestamoPk().getCodprecla().longValue() == 25L)) {
			datosCredito.setTipoPrestamista(TipoPrestamista.JUBILADO);
		}
		datosCredito.setValorSeguroSaldosOrden(new BigDecimal(prestamo
				.getValsegsal().doubleValue()));
		try {
			obtenerSolicitante(prestamo.getNumafi(),
					datosCredito.getTipoPrestamista());
		} catch (SolicitanteExcepcion e) {
			throw new SolicitudException(e.getMessage(), e);
		}
	}

	public AprobacionDto getAprobacionDto() {
		return aprobacionDto;
	}

	public void setAprobacionDto(AprobacionDto aprobacionDto) {
		this.aprobacionDto = aprobacionDto;
	}

	public BigDecimal getMesesGracia() {
		return mesesGracia;
	}

	public void setMesesGracia(BigDecimal mesesGracia) {
		this.mesesGracia = mesesGracia;
	}
}
