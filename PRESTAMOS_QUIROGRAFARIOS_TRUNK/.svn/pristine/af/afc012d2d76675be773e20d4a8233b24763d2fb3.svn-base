package ec.fin.biess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.servicio.InformacionIessServicio;
import ec.gov.iess.creditos.excepcion.CesantiaExcepcion;
import ec.gov.iess.creditos.pq.excepcion.PrecalificacionExcepcion;
import ec.gov.iess.creditos.pq.servicio.ConsultaCesantiaServicio;
import ec.gov.iess.creditos.pq.servicio.PrecalificacionServicio;

/**
 * Servicio para obtener informacion de los consumos de servicios web de prestaciones, aportes y mora
 * 
 * @author hugo.mora
 *
 */
@Stateless
public class InformacionIessServicioImpl implements InformacionIessServicio {

	@EJB
	private ConsultaCesantiaServicio consultaCesantiaServicio;

	@EJB
	private PrecalificacionServicio precalificacionServicio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.InformacionIessServicio#obtenerInformacion(ec.fin.biess.creditos.pq.modelo.dto.
	 * InformacionIessServicioDto, ec.fin.biess.creditos.pq.enumeracion.TipoInformacionServicioIessEnum)
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public InformacionIessServicioDto obtenerInformacion(InformacionIessServicioDto informacionIessServicio,
			TipoInformacionServicioIessEnum tipoInfoServicioIessEnum) throws PrecalificacionExcepcion {
		try {
			switch (tipoInfoServicioIessEnum) {
			case APORTES_MORA:
				if (informacionIessServicio.getInformacionAportacionResponse() == null) {
					informacionIessServicio.setInformacionAportacionResponse(this.precalificacionServicio
							.obtenerInformacionAportaciones(informacionIessServicio.getCedula(), informacionIessServicio.isProductoBiessEmergente()));
				}
				break;
			case CESANTIAS:
				if (informacionIessServicio.getInformacionCesantia() == null) {
					informacionIessServicio
							.setInformacionCesantia(this.consultaCesantiaServicio.consultarValorDisponible(informacionIessServicio.getCedula()));
				}
				break;
			case PRESTACIONES:
				if (informacionIessServicio.getInformacionPrestacionPensionado() == null) {
					informacionIessServicio.setInformacionPrestacionPensionado(
							this.precalificacionServicio.obtieneInformacionPensiones(informacionIessServicio.getCedula()));
				}
				break;
			case TODOS:
				if (informacionIessServicio.getInformacionAportacionResponse() == null) {
					informacionIessServicio.setInformacionAportacionResponse(this.precalificacionServicio
							.obtenerInformacionAportaciones(informacionIessServicio.getCedula(), informacionIessServicio.isProductoBiessEmergente()));
				}
				if (informacionIessServicio.getInformacionCesantia() == null) {
					informacionIessServicio
							.setInformacionCesantia(this.consultaCesantiaServicio.consultarValorDisponible(informacionIessServicio.getCedula()));
				}
				if (informacionIessServicio.getInformacionPrestacionPensionado() == null) {
					informacionIessServicio.setInformacionPrestacionPensionado(
							this.precalificacionServicio.obtieneInformacionPensiones(informacionIessServicio.getCedula()));
				}
				break;
			case APORTES_MORA_CESANTIAS:
				if (informacionIessServicio.getInformacionAportacionResponse() == null) {
					informacionIessServicio.setInformacionAportacionResponse(this.precalificacionServicio
							.obtenerInformacionAportaciones(informacionIessServicio.getCedula(), informacionIessServicio.isProductoBiessEmergente()));
				}
				if (informacionIessServicio.getInformacionCesantia() == null) {
					informacionIessServicio
							.setInformacionCesantia(this.consultaCesantiaServicio.consultarValorDisponible(informacionIessServicio.getCedula()));
				}
				break;
			default:
				break;
			}
		} catch (CesantiaExcepcion e) {
			throw new PrecalificacionExcepcion(e);
		} catch (PrecalificacionExcepcion e) {
			throw new PrecalificacionExcepcion(e);
		}

		return informacionIessServicio;
	}
}
