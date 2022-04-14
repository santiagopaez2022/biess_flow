package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.consultacesantia.client.mq.service.CesantiaWSServiceLocal;
import ec.fin.biess.creditos.pq.dao.CesantiasPQDao;
import ec.fin.biess.creditos.pq.enumeracion.ParametrosBiessEnum;
import ec.fin.biess.creditos.pq.excepcion.ConsultaCesantiaException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionCesantia;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.tools.integracion.excepciones.IntegracionMQExcepcion;
import ec.fin.biess.tools.integracion.excepciones.JAXBExcepcion;
import ec.fin.biess.tools.integracion.excepciones.ServicioESBExcepcion;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.GarantiaCesantiaDao;
import ec.gov.iess.creditos.dao.GarantiaPrestamoDao;
import ec.gov.iess.creditos.excepcion.CesantiaExcepcion;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.excepcion.GarantiasSacException;
import ec.gov.iess.creditos.pq.servicio.ConsultaCesantiaServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaCesantiaServicioRemote;
import ec.gov.iess.creditos.pq.servicio.GarantiasPorOperacionSacServicio;
import ec.gov.iess.creditos.pq.servicio.GarantiasSacServicioLocal;
import ec.gov.iess.servicio.cesantia.excepcion.ActualizacionCesantiaExcetion;
import ec.gov.iess.servicio.cesantia.servicio.CuentaCesantiaServicio;
import ec.gov.iess.servicio.fondoreserva.servicio.BloqueoServicio;

/*
 * Jaime Molina
 */
@Stateless
public class ConsultaCesantiaServicioImpl implements ConsultaCesantiaServicio,
		ConsultaCesantiaServicioRemote {
	
	
	
	@EJB(beanName = "CuentaCesantiaServicioResumenImpl")
	CuentaCesantiaServicio cuentaCesantiaServicio;

	@EJB
	GarantiaCesantiaDao cesantiaDao;

	@EJB
	BloqueoServicio bloqueoServicio;
	
	@EJB
	GarantiaPrestamoDao garantiaPrestamoDao;
	
	@EJB(beanName = "CesantiaWSServiceImpl")
	private CesantiaWSServiceLocal cesantiaWSService;

	@EJB
	private GarantiasSacServicioLocal garantiasSacServicio;
	
	@EJB
	private GarantiasPorOperacionSacServicio garantiaSacPorOp;
	
	@EJB
	private CesantiasPQDao cesantiasPQDao;
	
	@EJB(name = "ParametroBiessDaoImpl/local")
	private transient ParametroBiessDao parametroBiessDao;
	

	private final LoggerBiess log = LoggerBiess
			.getLogger(PrecalificacionServicioUsaResumenImpl.class);

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal consultarCesantia(final DatosGarantia datGarantia)
			throws CesantiaExcepcion {
		BigDecimal valorCesantia = new BigDecimal(0);
		final GarantiaCesantia cuentaCesantia = cesantiaDao.load(datGarantia.getCedula());
		if (cuentaCesantia == null) {
			throw new CesantiaExcepcion(
					"NO EXISTE CUENTA DE CESANTIA PARA LA CEDULA: "
							+ datGarantia.getCedula());
		}
		log.info("Total Cesantia: " + cuentaCesantia.getTotalCesantia());
		log.info("Cesantia comprometido host: "
				+ cuentaCesantia.getValorComprometidoHost());
		log.info("Cesantia comprometido hl"
				+ cuentaCesantia.getValorComprometidoHl());

		if (datGarantia.getValReqFondos().isNovacion()) {
			final OperacionSacRequest operacionSacRequest=new OperacionSacRequest();
			final ClienteRequestDto cliente=new ClienteRequestDto();
			cliente.setTipoCliente(datGarantia.getPrestamoVigNovacion().getTipoafiliadoSac());
			operacionSacRequest.setCliente(cliente);
			final OperacionRequestDto operacion=new OperacionRequestDto();
			operacion.setNumero(datGarantia.getPrestamoVigNovacion().getNumOperacionSAC());
			operacion.setNut(datGarantia.getPrestamoVigNovacion().getNut());
			operacion.setTipoProducto(datGarantia.getPrestamoVigNovacion().getDestinoComercial().getCodProdPq());
			operacionSacRequest.setOperacion(operacion);
			BigDecimal totalCesantiaCredNovar=BigDecimal.ZERO;
			BigDecimal totalFondoCredNovar=BigDecimal.ZERO;
			
			try {
				final InformacionGarantias infoGaran=garantiaSacPorOp.obtenerGarantiasPorOperacion(operacionSacRequest);
				totalCesantiaCredNovar=infoGaran.getTotalGarantiasFondosCesantia();
				totalFondoCredNovar=infoGaran.getTotalGarantiasFondoReserva();
			} catch (final GarantiasSacException e1) {
				log.error("Error al obtener las cesantias del SAC",e1);
				throw new CesantiaExcepcion(
						"Error obtener cesantia comprometidas sac: "
								+" "+e1.getCodigo()+":");
			}
		    
			datGarantia.setComprometidoCesantias(totalCesantiaCredNovar);
		    datGarantia.setComprometidoFondos(totalFondoCredNovar);
			valorCesantia = (cuentaCesantia.getValorHost().add(cuentaCesantia.getValorHistoriaLaboral())).subtract(datGarantia.getCompromCesSac().subtract(totalCesantiaCredNovar));
		} else {
			BigDecimal valorCesantiaHostHl = null;
			BigDecimal valoresComprometidos = null;
			valorCesantiaHostHl = cuentaCesantia.getValorHost().add(cuentaCesantia.getValorHistoriaLaboral());
			// Se reemplaza cuentaCesantia.getValorComprometidoHl() por garantias comprometidas desde SAC
			 final BigDecimal garantiaComprometidaSacCE = datGarantia.getCompromCesSac();
			valoresComprometidos = garantiaComprometidaSacCE.add(cuentaCesantia.getValorComprometidoHost());
			valorCesantiaHostHl = valorCesantiaHostHl.subtract(valoresComprometidos);
			if (valorCesantiaHostHl.doubleValue() < 0) {
				valorCesantiaHostHl = BigDecimal.ZERO;
			}
			valorCesantia = valorCesantiaHostHl;
		}
		return valorCesantia;
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public GarantiaCesantia consultarCesantia(final String cedula)
			throws CesantiaExcepcion {
		final GarantiaCesantia cuentaCesantia = cesantiaDao.load(cedula);
		if (cuentaCesantia == null) {
			throw new CesantiaExcepcion(
					"NO EXISTE CUENTA DE CESANTIA PARA LA CEDULA: "
							+ cedula);
		}else
		{
			return cuentaCesantia;
		}
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal consultarValordisponibleconExtemporaneos(final String cedula){
		return cesantiaDao.consultarValordisponibleconExtemporaneos(cedula);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date consultarFechadecuentaindividual(final String cedula){
		return cesantiaDao.consultarFechadecuentaindividual(cedula);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarvalorcesantia(final String cedula, final BigDecimal valor) throws ActualizacionCesantiaExcetion {
		
		GarantiaCesantia cuentaCesantia = null; 
		
		cuentaCesantia =  cesantiaDao.load(cedula);
		if(cuentaCesantia == null){
			log.info("No existe cuenta de cesantia para la cedula: " + cedula);
			Date fechactaindividual = this.consultarFechadecuentaindividual(cedula);
			if(fechactaindividual == null)
				fechactaindividual = new Date();
			
			cuentaCesantia = new GarantiaCesantia();
			//insertar una nueva cesantia
			cuentaCesantia.setCedula(cedula);
			cuentaCesantia.setValorHost(new BigDecimal(0));
			cuentaCesantia.setValorComprometidoHost(new BigDecimal(0));
			cuentaCesantia.setValorHistoriaLaboral(valor);
			cuentaCesantia.setValorComprometidoHl(new BigDecimal(0));
			cuentaCesantia.setFechaCarga( new Timestamp(new Date().getTime()));
			cuentaCesantia.setTotalCesantia(valor);
			cuentaCesantia.setEstado("0");
			cuentaCesantia.setEstact("3");
			cuentaCesantia.setBandera("1");
			cuentaCesantia.setFechascal(new Timestamp(fechactaindividual.getTime()));
			cuentaCesantia.setFecejeproact(new Timestamp(new Date().getTime()));
			cesantiaDao.insert(cuentaCesantia);
		}else{
			cuentaCesantia.setValorHistoriaLaboral(valor);
			cuentaCesantia.setValorHost(new BigDecimal(0));
			cesantiaDao.update(cuentaCesantia);	
		}
	}
	

	/* (non-Javadoc)
	 * @see ec.gov.iess.creditos.pq.servicio.ConsultaCesantiaServicio#consultarValorDisponible(String)
	 */
	/**
	 * REQ-560 LUIS AVILA
	 * MEDIANTE PARAMETO EJECUTA =1 RELIZA EL LLAMADO AL PROCEDURE
	 * SI EJECUTA = 0 MUESTRA MENSAJE DE ERROR.
	 */
	@Override
	public InformacionCesantia consultarValorDisponible(final String identificacion) throws CesantiaExcepcion {
		final InformacionCesantia infoCesantia = new InformacionCesantia();
		int ejecutar = 0;
		String valorCesantia= "";
		try {
			//REQ 560 verifica bandera para realizar el flujo	
			ejecutar = this.parametroBiessDao.consultarPorIdNombreParametro(ParametrosBiessEnum.VALIDA_CESANTIA.getIdParametro(), 
					ParametrosBiessEnum.VALIDA_CESANTIA.getNombreParametro())
					.getValorNumerico().intValue();
			if(ejecutar == 0) {
			valorCesantia = this.cesantiaWSService.consultarCesantiaDisponible("CLI-PQ", identificacion);
			}else {	
				//REQ 560 METODO QUE LLAMA AL PROCEDIMIENTO
				valorCesantia = cesantiasPQDao.actualizarCesantias(identificacion);
			}
			if (valorCesantia != null && !"".equals(valorCesantia.trim())) {
				infoCesantia.setIdentificacion(identificacion);
				infoCesantia.setValorCesantia(new BigDecimal(valorCesantia));
			}
		} catch (final ServicioESBExcepcion e) {
			log.error("Error de integracion ServicioESBExcepcion", e);
			throw new CesantiaExcepcion(e);
		} catch (final JAXBExcepcion e) {
			log.error("Error de integracion JAXBExcepcion", e);
			throw new CesantiaExcepcion(e);
		} catch (final IntegracionMQExcepcion e) {
			log.error("Error de integracion IntegracionMQExcepcion", e);
			throw new CesantiaExcepcion(e);
		}catch (final ConsultaCesantiaException	 e) {
			log.error("No devolvio cesantia el procedimiento", e);
			throw new CesantiaExcepcion(e);
		}catch (final ParametroBiessException e) {
			log.error("Error de integracion IntegracionMQExcepcion", e);
			throw new CesantiaExcepcion(e);
		}
		return infoCesantia;
	}
}