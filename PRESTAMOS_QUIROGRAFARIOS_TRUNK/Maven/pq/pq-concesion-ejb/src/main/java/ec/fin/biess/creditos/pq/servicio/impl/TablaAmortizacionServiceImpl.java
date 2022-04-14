package ec.fin.biess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.ConfiguracionPQEnum;
import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.excepcion.TablaAmortizacionException;
import ec.fin.biess.creditos.pq.servicio.TablaAmortizacionService;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.fin.biess.tablaAmortizacion.client.mq.modelo.TipoTabla;
import ec.fin.biess.webservice.TablasAmortizacionGraciaDto;
import ec.fin.biess.webservice.TablasAmortizacionGraciaWebService;
import ec.fin.biess.webservice.TablasAmortizacionGraciaWebService_Service;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.enumeracion.TipoPeriodoGracia;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.dto.DividendoCalculo;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.CalculoPeriodoGracia;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * Implementacion de la interfaz TablaAmortizacionService
 * 
 * @author diana.suasnavas
 *
 */
@Stateless
public class TablaAmortizacionServiceImpl implements TablaAmortizacionService {

	@EJB
	private ParametroBiessDao parametroBiessDao;

	public List<DividendoCalculo> obtenerTablaAmortizacionPorTipo(DatosCredito datosCredito, boolean esEmergente)
			throws TablaAmortizacionException {
		BigDecimal interes = UtilAjusteCalculo.ajusteNumber(datosCredito.getTasaInteres(), 2);
		BigDecimal monto = UtilAjusteCalculo.ajusteNumber(datosCredito.getMonto(), 2);
		int plazo = datosCredito.getPlazo();
		BigDecimal periodoGracia = datosCredito.getPrestamoCalculo().getPeriodoGracia().getValor();
		List<DividendoCalculo> dividendoCalculoList = new ArrayList<DividendoCalculo>();
		Calendar clfechaPagoDividendo = new GregorianCalendar();
		Date fechaPagoDividendo = new Date();
		CalculoPeriodoGracia calculoPeriodoGraciaZafra = new CalculoPeriodoGracia();
		BigDecimal periodoGraciaInterZafra = BigDecimal.ZERO;
		BigDecimal periodoGraciaPorrateado = BigDecimal.ZERO;
		int nuevoPlazo = 0; // se usa para los zafreros
		datosCredito.getPrestamoCalculo().setPeriodoGraciaPorrateadoInterZafra(BigDecimal.ZERO);
		datosCredito.getPrestamoCalculo().setPeriodoGraciaInterZafra(null);
			TipoTabla tipoTablaEnum = TipoTabla.valueOf(datosCredito.getTipoTabla());
			TablasAmortizacionGraciaWebService tablasAmortizacionGraciaWebService = getTablaAmortizacionWebService();
			List<TablasAmortizacionGraciaDto> tabla;
	        try {
	        	int mesesGracia = obtenerMesesGracia(esEmergente).intValue();
	            tabla = tablasAmortizacionGraciaWebService.obtenerTablaAmortizacion(monto, interes, plazo, tipoTablaEnum.name(), mesesGracia);
	            for (TablasAmortizacionGraciaDto tablaAmortizacionDTO : tabla) {
					DividendoCalculo dividendo = new DividendoCalculo();
					dividendo.setNumeroDividendo(tablaAmortizacionDTO.getNumeroDividendo());
					if (esEmergente) {
						if (tablaAmortizacionDTO.getNumeroDividendo() == (obtenerMesesGracia(true).intValue() + 1)) {
							dividendo.setValorDividendo(tablaAmortizacionDTO.getValorDividendoGracia().add(periodoGracia));
							dividendo.setValorIntPerGra(tablaAmortizacionDTO.getValorGracia().add(periodoGracia));
						} else {
							dividendo.setValorDividendo(tablaAmortizacionDTO.getValorDividendoGracia());
							dividendo.setValorIntPerGra(tablaAmortizacionDTO.getValorGracia());
						}
						
					} else {
						if (tablaAmortizacionDTO.getNumeroDividendo() == 1) {
							dividendo.setValorDividendo(tablaAmortizacionDTO.getValorDividendo().add(periodoGracia));
							dividendo.setValorIntPerGra(periodoGracia);
						} else {
							dividendo.setValorDividendo(tablaAmortizacionDTO.getValorDividendo());
							dividendo.setValorIntPerGra(BigDecimal.ZERO);
						}
					}
					dividendo.setValorInteres(tablaAmortizacionDTO.getValorInteres());
					dividendo.setValorAbonoCapital(tablaAmortizacionDTO.getCapitalAmortizado());
					dividendo.setvalorSaldoCapital(tablaAmortizacionDTO.getValorSaldoCapital());
					
					// Aqui es para los zafreros
					if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(datosCredito.getTipoPrestamista())
							&& plazo > ConstantesCreditos.PLAZO_INTERZAFRA) {
						if (tablaAmortizacionDTO.getNumeroDividendo() == 7) {
							// Aqui se setea la fecha cuando es zafrero y mayor de los 6 meses
							fechaPagoDividendo = CalculoCreditoHelperSingleton.getInstancia().determinaFechaFin(
									fechaPagoDividendo, TipoPeriodoGracia.PERIODO_INTERZAFRA);
						} else {
							fechaPagoDividendo = CalculoCreditoHelperSingleton.getInstancia().determinaFechaFin(
									fechaPagoDividendo, TipoPeriodoGracia.MES_SIGUIENTE);
						}
						if (tablaAmortizacionDTO.getNumeroDividendo() == (ConstantesCreditos.PLAZO_INTERZAFRA)) {
							datosCredito.getPrestamoCalculo().setPeriodoGraciaInterZafra(
									calculoPeriodoGraciaZafra.determinaPeriodoGraciaInterZafra(datosCredito
											.getFechaSolicitud(), interes, UtilAjusteCalculo.ajusteNumber(
											tablaAmortizacionDTO.getValorSaldoCapital(), 2), plazo));
							periodoGraciaInterZafra = UtilAjusteCalculo.ajusteNumber(datosCredito.getPrestamoCalculo()
									.getPeriodoGraciaInterZafra().getValor(), 2);
							nuevoPlazo = plazo - ConstantesCreditos.PLAZO_INTERZAFRA;
							periodoGraciaPorrateado = UtilAjusteCalculo.ajusteNumber(
									BigDecimal.valueOf(periodoGraciaInterZafra.doubleValue() / nuevoPlazo), 2);
							datosCredito.getPrestamoCalculo().setPeriodoGraciaPorrateadoInterZafra(
									periodoGraciaPorrateado);
						}
						if (tablaAmortizacionDTO.getNumeroDividendo() > ConstantesCreditos.PLAZO_INTERZAFRA) {
							dividendo.setValorDividendo(tablaAmortizacionDTO.getValorDividendo().add(
									datosCredito.getPrestamoCalculo().getPeriodoGraciaPorrateadoInterZafra()));
						}
					} else if (tablaAmortizacionDTO.getNumeroDividendo() >= 1) {
						fechaPagoDividendo = CalculoCreditoHelperSingleton.getInstancia().determinaFechaFin(
								fechaPagoDividendo, TipoPeriodoGracia.MES_SIGUIENTE);
					}
					clfechaPagoDividendo.setTime(fechaPagoDividendo);
					dividendo.setFechapagoDividendo(clfechaPagoDividendo.getTime());
					dividendo.setValorPeriodoGraciaInterzafra(periodoGraciaPorrateado);
					dividendoCalculoList.add(dividendo);
				}
	        } catch (Exception ex) {
	            throw new TablaAmortizacionException("El servicio web de tabla de amortizacion no esta disponible", ex);
	        }
		

		return dividendoCalculoList;
	}
	
	/**
	 * Conexion al servicio web de tablas de amortizacion
	 * 
	 * @return
	 * @throws MontosMaximosException
	 */
	private TablasAmortizacionGraciaWebService getTablaAmortizacionWebService() throws TablaAmortizacionException {
		String url;
		try {
			url = parametroBiessDao.consultarPorIdNombreParametro(ConfiguracionPQEnum.URL_TABLAS_AMORTIZACION.getIdParametro(),
					ConfiguracionPQEnum.URL_TABLAS_AMORTIZACION.getNombreParametro()).getValorCaracter();
		} catch (ParametroBiessException e) {
			throw new TablaAmortizacionException("Error al obtener parametros de URL de tabla de amortizacion de biess emergente", e);
		}
		try {
			QName qname = new QName("http://webservice.biess.fin.ec/", "TablasAmortizacionGraciaWebService");
			final TablasAmortizacionGraciaWebService_Service tablasAmortizacionGraciaWebService_Service = new TablasAmortizacionGraciaWebService_Service(
					new URL(url), qname);
			return tablasAmortizacionGraciaWebService_Service.getTablasAmortizacionGraciaWebServicePort();
		} catch (Exception ex) {
			throw new TablaAmortizacionException("La URL de tabla de amortizacion no esta disponible", ex);
		}
	}
	
	/**
	 * Obtiene los meses de gracia
	 * 
	 * @param esEmergente
	 * @return
	 * @throws MontosMaximosException
	 */
	private BigDecimal obtenerMesesGracia(boolean esEmergente) throws MontosMaximosException {
		BigDecimal resp = BigDecimal.ZERO;
		if (esEmergente) {
			try {
				resp = parametroBiessDao.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
						BiessEmergenteEnum.MESES_GRACIA.getNombreParametro()).getValorNumerico();
			} catch (ParametroBiessException e) {
				throw new MontosMaximosException("Error al obtener parametros de meses de gracia de biess emergente", e);
			}
		} 
		return resp;
	}

}
