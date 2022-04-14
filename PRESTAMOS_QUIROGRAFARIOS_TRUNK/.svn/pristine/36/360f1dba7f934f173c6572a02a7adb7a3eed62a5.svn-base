package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicio;
import ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicioRemote;
import ec.gov.iess.servicio.fondoreserva.excepcion.CuentaFondoReservaExcepcion;
import ec.gov.iess.servicio.fondoreserva.modelo.CuentaFondoReserva;
import ec.gov.iess.servicio.fondoreserva.modelo.persistencia.Bloqueo;
import ec.gov.iess.servicio.fondoreserva.servicio.BloqueoServicio;
import ec.gov.iess.servicio.fondoreserva.servicio.FondoReservaServicio;

@Stateless
public class ConsultaFondosReservaServicioImpl implements ConsultaFondosReservaServicio,
		ConsultaFondosReservaServicioRemote {
	@EJB(beanName = "FondoReserva2ServicioImpl")
	FondoReservaServicio fondoReservaServicio;
	
	@EJB
	BloqueoServicio bloqueoServicio;
	
	

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CuentaFondoReserva consultarFondoReserva(final DatosGarantia datGarantia) {
		
		// Si tiene cargos el valor de fondos de reserva es cero
		if (tieneCargos(datGarantia.getValReqFondos().getCedula(),datGarantia.getValReqFondos().getTiposCargos())) {
			return armarCuentaFondos(datGarantia.getValReqFondos().getCedula(),
					"EL AFILIADO TIENE CARGOS POR APLICAR, PARA MAS INFORMACION ACERCARSE A FONDOS DE TERCEROS");
		}
		
		//Si tiene bloqueos
		
		if(tieneBloqueosEnCuenta(datGarantia.getValReqFondos().getCedula(),datGarantia.getValReqFondos().getEstadoBloqueado())){
			return armarCuentaFondos(datGarantia.getValReqFondos().getCedula(), "TIENE UN BLOQUEO TOTAL A LA CUENTA DE FONDOS DE RESERVA");
		}
		
		if (tieneSolicitudFondosEnTramite(datGarantia.getValReqFondos().getCedula(),datGarantia.getValReqFondos().getEstadosSolicitud())) {
			return armarCuentaFondos(datGarantia.getValReqFondos().getCedula(), "EL AFILIADO TIENE UNA SOLICITUD DE FONDOS DE RESERVA EN TRAMITE. "
					+ "PARA MAS INFORMACION FAVOR ACERCARSE A FONDOS DE TERCEROS");
		}
		
		final LoggerBiess log = LoggerBiess.getLogger(ConsultaFondosReservaServicioImpl.class);
		CuentaFondoReserva cuentaFondoReserva;
		try {
			// Se consulta los fondos de reserva
			cuentaFondoReserva = fondoReservaServicio.getCuentaFondoReserva(datGarantia.getValReqFondos().getCedula());
		
			//Cuando se realice una novacion se suma al valor obtenido los valores comprometidos del pq
			if(datGarantia.getValReqFondos().isNovacion()){
				final BigDecimal nuevoValorFr = cuentaFondoReserva.getSaldoDisponible().setScale(2, BigDecimal.ROUND_HALF_UP);
				cuentaFondoReserva.setSaldoDisponible(nuevoValorFr);				
			}
			// Controlando que el valor disponible de fondos no se a cero
			if (cuentaFondoReserva.getSaldoDisponible().doubleValue() < 0) {
				cuentaFondoReserva.setSaldoDisponible(BigDecimal.ZERO);
				cuentaFondoReserva.setObservacion("EL VALOR DE FONDOS DE RESERVA ENCONTRADO ES NEGATIVO"
						+ cuentaFondoReserva.getSaldoDisponible()
						+ " POR LO QUE SE HA AJUSTADO SU VALOR DE FONDOS DE RESERVA A CERO");
				return cuentaFondoReserva;
			}
			
		} catch (final CuentaFondoReservaExcepcion e) {
			log.warn("No se ha podido consultar el valor de fondos de reserva para la cédula: " + datGarantia.getValReqFondos().getCedula());
			log.debug("Esto es lo que pasó: ", e);
			
			return armarCuentaFondos(datGarantia.getValReqFondos().getCedula(), "NO SE PUDO CONSULTAR SU VALOR DE FONDOS DE RESERVA. :" + e.getMessage());
		} catch (final Exception e) {
			log.warn("No se ha podido consultar el valor de fondos de reserva para la cédula: " + datGarantia.getValReqFondos().getCedula());
			log.debug("Esto es lo que pasó: ", e);
			
			return armarCuentaFondos(datGarantia.getValReqFondos().getCedula(), "NO SE PUDO CONSULTAR SU VALOR DE FONDOS DE RESERVA. :" + e.getMessage());
		}
		
		if (cuentaFondoReserva.getSaldoDisponible().doubleValue() == 0) {
			if (fondoReservaServicio.tienenAportes(datGarantia.getValReqFondos().getCedula())) {
				if (fondoReservaServicio.tieneAportesDisponibles(datGarantia.getValReqFondos().getCedula())) {
					cuentaFondoReserva.setObservacion("TODOS SUS APORTES DISPONIBLES TIENEN UN VALOR DE CERO");
				} else {
					cuentaFondoReserva
							.setObservacion("USTED YA HA RETIRADO SUS FONDOS DE RESERVA O SU CUENTA DE FONDOS DE RESERVA ESTA BLOQUEADA, PARA MAS INFORMACION ACERCARSE A FONDOS DE TERCEROS ");
				}
			} else {
				cuentaFondoReserva.setObservacion("NO TIENE CUENTA DE FONDOS DE RESERVA");
			}
		}
		
		return cuentaFondoReserva;
	}
	
	/**
	 * Se encarga de armar un objeto {@link CuentaFondoReserva} con saldo disponible de cero
	 * 
	 * @param cedula
	 * @return
	 */
	private CuentaFondoReserva armarCuentaFondos(final String cedula, final String observacion) {
		
		final CuentaFondoReserva cuentaFondoReserva = new CuentaFondoReserva();
		
		cuentaFondoReserva.setCedula(cedula);
		cuentaFondoReserva.setSaldoDisponible(new BigDecimal(0));
		cuentaFondoReserva.setObservacion(observacion);
		
		return cuentaFondoReserva;
		
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicio#tieneAportesFondos(java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tieneAportesFondos(final String cedula) {
		return fondoReservaServicio.tienenAportes(cedula);
	}
	
	/**
	 * @see ec.gov.iess.creditos.pq.servicio.ConsultaFondosReservaServicio#tieneCargos(java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tieneCargos(final String cedula,final List<String> tipos) {
		final Long cuentaCargos = fondoReservaServicio.cuentaCargos(cedula, tipos);
		// Si tiene cargos el valor de fondos de reserva es cero
		return cuentaCargos.longValue() > 0;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tieneSolicitudFondosEnTramite(final String cedula,final List<String> estados) {	
		final Long contadorSolcitudes = fondoReservaServicio.cuentaSolicitudFondosReservaEstados(cedula, estados);
		return contadorSolcitudes.longValue() > 0;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean tieneBloqueosEnCuenta(final String cedula,final String estado) {
		
		final List<Bloqueo> listaBloqueos = bloqueoServicio.consultarBloqueosPorCedulaEstado(cedula, estado);
		
		return !listaBloqueos.isEmpty();
	}
	

	
}
