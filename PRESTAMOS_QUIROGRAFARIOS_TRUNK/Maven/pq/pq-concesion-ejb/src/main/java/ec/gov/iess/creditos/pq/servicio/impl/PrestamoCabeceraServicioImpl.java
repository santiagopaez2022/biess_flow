/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.enumeracion.BiessEmergenteEnum;
import ec.fin.biess.creditos.pq.enumeracion.CreditoEspecialEnum;
import ec.fin.biess.dao.ParametroBiessDao;
import ec.fin.biess.exception.ParametroBiessException;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.PrestamoDao;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.excepcion.CabeceraCreditoQuirografarioException;
import ec.gov.iess.creditos.pq.helper.reglas.Messages;
import ec.gov.iess.creditos.pq.servicio.PrestamocabeceraServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamocabeceraServicioRemote;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * 
 * <b> Permite realizar el guardado de la cabecera del cr�dito </b>
 * 
 * @author cvillarreal,cbastidas
 * @version $Revision: 1.8 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:29:23 $]
 *          </p>
 */
@Stateless
public class PrestamoCabeceraServicioImpl implements PrestamocabeceraServicio,
		PrestamocabeceraServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(PrestamoCabeceraServicioImpl.class);

	@EJB
	PrestamoDao prestamoDao;
	
	@EJB
	private ParametroBiessDao parametroBiessDao;

	/**
	 * 
	 */
	public PrestamoCabeceraServicioImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.PrestamocabeceraServicio#crearPrestamoPq(ec.gov.iess.creditos.modelo.dto.
	 * DatosCredito)
	 */
	@Override
	public Prestamo crearPrestamoPq(DatosCredito prestamo) throws CabeceraCreditoQuirografarioException {

		log.debug(" crearPrestamoPq ");
		try {

			Prestamo prestamoNew = new Prestamo();
			PrestamoPk prestamoPkNew = new PrestamoPk();

			log.debug(" setCodprecla "
					+ prestamo.getVariablePrestamo().getVariablePrestamoPK()
							.getCodprecla());
			prestamoPkNew.setCodprecla(prestamo.getVariablePrestamo()
					.getVariablePrestamoPK().getCodprecla());

			log.debug(" setCodpretip "
					+ prestamo.getVariablePrestamo().getVariablePrestamoPK()
							.getCodpretip());
			prestamoPkNew.setCodpretip(prestamo.getVariablePrestamo()
					.getVariablePrestamoPK().getCodpretip());

			log.debug(" setNumpreafi "
					+ prestamo.getVariablePrestamo().getSecvarpre());
			prestamoPkNew.setNumpreafi(prestamo.getVariablePrestamo()
					.getSecvarpre());

			log.debug(" setOrdpreafi " + prestamo.getOrdenPrestamo());
			prestamoPkNew.setOrdpreafi(prestamo.getOrdenPrestamo());
			prestamoNew.setPrestamoPk(prestamoPkNew);

			log.debug(" setFecpreafi " + prestamo.getFechaSolicitud());
			prestamoNew.setFecpreafi(prestamo.getFechaSolicitud());
			
			prestamoNew.setValsegsal(UtilAjusteCalculo.ajusteNumberBaseDatos(
					prestamo.getPrestamoCalculo().getSeguroSaldo().getValor())
					.doubleValue());
			log.debug(" setValsegsal "
					+ prestamo.getPrestamoCalculo().getSeguroSaldo().getValor()
							.doubleValue());

			log.debug(" setValsalcap "
					+ prestamo.getPrestamoCalculo().getMontoPrestamo()
							.doubleValue());
			// Si es turismo suma el seguro de saldos
			if (TiposProductosPq.TUR.getCodigoTipoProducto().equals(prestamo.getVariablePrestamo().getVariablePrestamoPK().getCodpretip())
					|| TiposProductosPq.FOC.getCodigoTipoProducto().equals(prestamo.getVariablePrestamo().getVariablePrestamoPK().getCodpretip())
					|| TiposProductosPq.ECU_TUR.getCodigoTipoProducto().equals(prestamo.getVariablePrestamo().getVariablePrestamoPK().getCodpretip())) {
				prestamoNew.setValsalcap(UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getPrestamoCalculo().getMontoPrestamo()).doubleValue()
						+ UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getPrestamoCalculo().getSeguroSaldo().getValor()).doubleValue());
			} else {
				prestamoNew.setValsalcap(UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getPrestamoCalculo().getMontoPrestamo()).doubleValue());
			}
			
			log.debug(" setMntpre "
					+ prestamo.getPrestamoCalculo().getMontoPrestamo()
							.doubleValue());
			// Si es turismo o focalizado suma el seguro de saldos
			if (TiposProductosPq.TUR.getCodigoTipoProducto().equals(prestamo.getVariablePrestamo().getVariablePrestamoPK().getCodpretip())
					|| TiposProductosPq.FOC.getCodigoTipoProducto().equals(prestamo.getVariablePrestamo().getVariablePrestamoPK().getCodpretip())
					|| TiposProductosPq.ECU_TUR.getCodigoTipoProducto().equals(prestamo.getVariablePrestamo().getVariablePrestamoPK().getCodpretip())) {
				prestamoNew.setMntpre(UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getPrestamoCalculo().getMontoPrestamo()).doubleValue()
						+ UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getPrestamoCalculo().getSeguroSaldo().getValor()).doubleValue());
			} else {
				prestamoNew.setMntpre(UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getPrestamoCalculo().getMontoPrestamo()).doubleValue());
			}

			log.debug(" setObsanupre " + null);
			prestamoNew.setObsanupre(null);

			log.debug(" setTipper " + prestamo.getTipoPeriodo());
			prestamoNew.setTipper(prestamo.getTipoPeriodo());

			log.debug(" setCodparpre " + prestamo.getParametroPrestamo());
			prestamoNew.setCodparpre(prestamo.getParametroPrestamo());

			log.debug(" setAniper " + prestamo.getAnio());
			prestamoNew.setAniper(prestamo.getAnio());

			log.debug(" setMesper " + prestamo.getMes());
			prestamoNew.setMesper(prestamo.getMes());

			if (prestamo.getOficinaIess() == null) {
				log.info("No existe codigo de division politica para la cedula: " + prestamo.getPrestamoCalculo().getCedula());
				throw new CabeceraCreditoQuirografarioException(Messages.getString("mensaje.codigo.divpol"));
			}
			log.debug(" setCodregadm "
					+ prestamo.getOficinaIess().getCodregadm());
			prestamoNew.setCodregadm(prestamo.getOficinaIess().getCodregadm());

			log.debug(" setNumsolser "
					+ prestamo.getSolicitudCredito().getSolicitudCreditoPK()
							.getNumsolser());

			prestamoNew.setNumsolser(prestamo.getSolicitudCredito()
					.getSolicitudCreditoPK().getNumsolser());

			log.debug(" setCodtipsolser "
					+ prestamo.getSolicitudCredito().getSolicitudCreditoPK()
							.getCodtipsolser());
			prestamoNew.setCodtipsolser(prestamo.getSolicitudCredito()
					.getSolicitudCreditoPK().getCodtipsolser());

			log.debug(" setCodestpre " + prestamo.getEstadoPrestamo());
			prestamoNew.setEstadoPrestamo(prestamo.getEstadoPrestamo());

			log.debug(" setFecinipre "
					+ prestamo.getPrestamoCalculo().getFechaInicioPrestamo());
			prestamoNew.setFecinipre(prestamo.getPrestamoCalculo()
					.getFechaInicioPrestamo());

			log.debug(" setFecfinpre "
					+ prestamo.getPrestamoCalculo().getFechaFinPrestamo());
			prestamoNew.setFecfinpre(prestamo.getPrestamoCalculo()
					.getFechaFinPrestamo());

			log.debug(" setCodfun " + null);
			prestamoNew.setCodfun(null);

			log.debug(" setNumafi " + prestamo.getPrestamoCalculo().getCedula());
			prestamoNew.setNumafi(prestamo.getPrestamoCalculo().getCedula());

			log.debug(" setPenoafi " + prestamo.getTipoSolicitante());
			prestamoNew.setPenoafi(prestamo.getTipoSolicitante());

			// Institucion financiera por tipo de cr�dito
			// CB 11/04/2011
			// INC-2272 Pensiones Alimenticias
			TiposProductosPq tiposProductosPq = TiposProductosPq
					.valueOf(prestamo.getOrdenCompra().getCodigoProducto());
			CategoriaTipoProductoPq categoriaTipoProductoPq = TiposProductosPq.getCategoriaTipoProductoPq(tiposProductosPq.getCodigoTipoProducto());
			//INC-2286 Ferrocarriles
			if (CategoriaTipoProductoPq.CAT_BIENES==categoriaTipoProductoPq) {
				log.debug(" setTipoCuenta "
						+ prestamo.getOrdenCompra().getProveedor()
								.getPrTipoCuenta());
				if (prestamo.getOrdenCompra().getProveedor().getPrTipoCuenta()
						.equals(TipoCuenta.AHO.toString())) {
					prestamoNew.setTipoCuenta(TipoCuenta.AHO);
				}

				if (prestamo.getOrdenCompra().getProveedor().getPrTipoCuenta()
						.equals(TipoCuenta.COR.toString())) {
					prestamoNew.setTipoCuenta(TipoCuenta.COR);
				}
				log.debug(" setNumctaban "
						+ prestamo.getOrdenCompra().getProveedor()
								.getPrNumCuenta());
				prestamoNew.setNumctaban(prestamo.getOrdenCompra()
						.getProveedor().getPrNumCuenta());
				log.debug(" setRucempinsfin "
						+ prestamo.getOrdenCompra().getProveedor().getPrRuc());
				prestamoNew.setRucempinsfin(prestamo.getOrdenCompra()
						.getProveedor().getPrRuc());

			} else if (CategoriaTipoProductoPq.CAT_NORMALES==categoriaTipoProductoPq
					|| ( CategoriaTipoProductoPq.CAT_SERVICIOS==categoriaTipoProductoPq && TiposProductosPq.PEN==tiposProductosPq)) {
				log.debug(" setTipoCuenta "
						+ prestamo.getInstitucionFinanciera().getTipoCuentaId());

				if (prestamo.getInstitucionFinanciera().getTipoCuentaId()
						.equals(TipoCuenta.AHO.toString())) {
					prestamoNew.setTipoCuenta(TipoCuenta.AHO);
				}

				if (prestamo.getInstitucionFinanciera().getTipoCuentaId()
						.equals(TipoCuenta.COR.toString())) {
					prestamoNew.setTipoCuenta(TipoCuenta.COR);
				}
				log.debug(" setNumctaban "
						+ prestamo.getInstitucionFinanciera().getNumeroCuenta());
				prestamoNew.setNumctaban(prestamo.getInstitucionFinanciera()
						.getNumeroCuenta());

				log.debug(" setRucempinsfin "
						+ prestamo.getInstitucionFinanciera()
								.getInstitucionId());
				prestamoNew.setRucempinsfin(prestamo.getInstitucionFinanciera()
						.getInstitucionId());
			} else if (CategoriaTipoProductoPq.CAT_SERVICIOS == categoriaTipoProductoPq
					|| CategoriaTipoProductoPq.CAT_FOCALIZADOS == categoriaTipoProductoPq) {
				if (prestamo.getInstitucionFinancieraProveedor().getTipoCuentaId().equals(TipoCuenta.AHO.toString())) {
					prestamoNew.setTipoCuenta(TipoCuenta.AHO);
				}

				if (prestamo.getInstitucionFinancieraProveedor().getTipoCuentaId().equals(TipoCuenta.COR.toString())) {
					prestamoNew.setTipoCuenta(TipoCuenta.COR);
				}
				prestamoNew.setNumctaban(prestamo.getInstitucionFinancieraProveedor().getNumeroCuenta());
				prestamoNew.setRucempinsfin(prestamo.getInstitucionFinancieraProveedor().getInstitucionId());
				/* Fijar id proveedor */
				if (prestamo.getProveedor() != null) {
					prestamoNew.setIdProveedor(prestamo.getProveedor().getPrIdProveedor());
				}
			}
			// Fin cambio Institucion

			log.debug(" setRucemp " + prestamo.getEmpleador().getRucEmpleador());
			prestamoNew.setRucemp(prestamo.getEmpleador().getRucEmpleador());

			log.debug(" setCodsuc "
					+ prestamo.getEmpleador().getCodigoSucursal());
			prestamoNew.setCodsuc(prestamo.getEmpleador().getCodigoSucursal());

			log.debug(" setValtotdiv "
					+ prestamo.getPrestamoCalculo()
							.getValorTotalDividendoMensual().doubleValue());
			prestamoNew.setValtotdiv(UtilAjusteCalculo.ajusteNumberBaseDatos(
					prestamo.getPrestamoCalculo()
							.getValorTotalDividendoMensual()).doubleValue());

			log.debug(" setPlzmes "
					+ new Long(prestamo.getPrestamoCalculo().getPlazoMeses()));
			prestamoNew.setPlzmes(new Long(prestamo.getPrestamoCalculo()
					.getPlazoMeses()));
			if (CreditoEspecialEnum.EMERGENTE.getCodigoCredito().equals(prestamo.getCreditoEspecial())) {
				prestamoNew.setPlzmes(prestamoNew.getPlzmes() + obtenerMesesGracia());
			}

			log.debug(" setTasint " + prestamo.getTasaInteres().doubleValue());
			prestamoNew.setTasint(UtilAjusteCalculo.ajusteNumberBaseDatos(prestamo.getTasaInteres()).doubleValue());

			//se setea cero, debido a que SAC calcula estos valores
			prestamoNew.setIntdiagrc(0.0);


			log.debug(" setCoddivpol " + prestamo.getIdDivisionPolitica());
			prestamoNew.setCoddivpol(prestamo.getIdDivisionPolitica());

			log.debug(" setFormaPago " + 1L);
			prestamoNew.setFormaPago(1L);

			log.debug(" setFectracre "
					+ prestamo.getPrestamoCalculo().getFechaTrasferencia());
			prestamoNew.setFectracre(prestamo.getPrestamoCalculo()
					.getFechaTrasferencia());

			log.debug(" setClaprerea " + prestamo.getClasePrestamoReal());
			prestamoNew.setClaprerea(prestamo.getClasePrestamoReal());

			log.debug(" setFeccanpre " + null);
			prestamoNew.setFeccanpre(null);

			log.debug(" setFeccalintpergra "
					+ prestamo.getPrestamoCalculo().getPeriodoGracia()
							.getFechaInicio());
			prestamoNew.setFeccalintpergra(prestamo.getPrestamoCalculo()
					.getPeriodoGracia().getFechaInicio());

			prestamoNew.setPrisegsal(prestamo.getPrestamoCalculo()
					.getSeguroSaldo().getTasaInteres());
			log.debug(" setPrisegsal " + prestamoNew.getPrisegsal());

			// Para La Novacion
			if (prestamo.isEsNovacion())
				if ((prestamo.getNumeroCanceladoNovacion() != null && prestamo
						.getNumeroCanceladoNovacion() > 0)
						&& (prestamo.getValorCanceladoNovacion() != null && prestamo
								.getValorCanceladoNovacion() > 0)) {
					prestamoNew.setValliqnov(prestamo
							.getValorCanceladoNovacion());
					prestamoNew.setNumprecannov(prestamo
							.getNumeroCanceladoNovacion());
//					/**
//					 * Carlos Bastidas: INC-6047 se agrega el numero de
//					 * liquidacion por novacion en la cabecera del credito"
//					 */
//					prestamoNew.setNumliqprenov(prestamo
//							.getNumeroLiquidacionNovacion());
				} else {
					throw new Exception(
							"Existe un problema con el Numero Cancelado Novacion o el Valor Cancelado Novacion");
				}

			//INC-2148 Refugiados.
			log.debug(" setTipoBeneficiario "
					+ prestamo.getTipoBeneficiario());
			prestamoNew.setTipoBeneficiario(prestamo.getTipoBeneficiario());
			
			log.debug(" setNumeroVisaPasaporteAfiliado "
					+ prestamo.getVisaPasaporteAfiliado());
			prestamoNew.setNumeroVisaPasaporteAfiliado(prestamo.getVisaPasaporteAfiliado());
			
			//se agrega lo del tipo de tabla de amortizacion creada DS			
			prestamoNew.setTipoTablaAmortizacion(prestamo.getTipoTabla());
			
			prestamoNew.setParametrizacion(prestamo.getParametrizacion());
			
			// Setea bandera de prestamo especial
			prestamoNew.setCreditoEspecial(prestamo.getCreditoEspecial());
			
			// Setea informacion de creditos Ecuador tu lugar en el mundo
			prestamoNew.setNumeroReserva(prestamo.getNumeroReserva());
			
			prestamoDao.insert(prestamoNew);
			return prestamoNew;
		} catch (CabeceraCreditoQuirografarioException e) {
			throw new CabeceraCreditoQuirografarioException(e);
		} catch (Exception e) {
			log.error("Error al crear la cabecera del credto :", e);
			throw new RuntimeException("Error creacion cabecera credito: " + e.getMessage(), e);
		}
	}
	
	/**
	 * Obtiene el numero de meses de gracia para creditos emergentes
	 * 
	 * @return
	 */
	private Long obtenerMesesGracia() {
		long mesesGracia = 0;
		try {
			mesesGracia = parametroBiessDao
					.consultarPorIdNombreParametro(BiessEmergenteEnum.MESES_GRACIA.getIdParametro(),
							BiessEmergenteEnum.MESES_GRACIA.getNombreParametro())
					.getValorNumerico().longValue();
		} catch (ParametroBiessException e) {
			log.info("Error al obtener el parametro de meses de gracia", e);
		}
		return mesesGracia;
	}

}