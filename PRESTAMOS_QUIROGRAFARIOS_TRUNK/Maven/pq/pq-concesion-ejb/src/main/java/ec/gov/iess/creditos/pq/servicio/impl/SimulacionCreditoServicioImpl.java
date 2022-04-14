/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.excepcion.MontosMaximosException;
import ec.fin.biess.creditos.pq.servicio.PersonalizarCreditoService;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.modelo.dto.OpcionCredito;
import ec.gov.iess.creditos.modelo.dto.PlazoCredito;
import ec.gov.iess.creditos.modelo.dto.Simulacion;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionRequestDto;
import ec.gov.iess.creditos.pq.dto.OperacionSacRequest;
import ec.gov.iess.creditos.pq.dto.PrestamoRequestDto;
import ec.gov.iess.creditos.pq.dto.TablaAmortizacionSac;
import ec.gov.iess.creditos.pq.excepcion.TablaAmortizacionSacException;
import ec.gov.iess.creditos.pq.helper.simulacion.SimulacionCreditoFactory;
import ec.gov.iess.creditos.pq.helper.simulacion.SimularCredito;
import ec.gov.iess.creditos.pq.servicio.CalculoCreditoServicio;
import ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio;
import ec.gov.iess.creditos.pq.util.CalculoCreditoHelperSingleton;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;
import ec.gov.iess.creditos.util.UtilAjusteCalculo;

/**
 * @author cvillarreal
 * 
 */
@Stateless
public class SimulacionCreditoServicioImpl implements SimularCreditoServicio {

	private static final String PLAZO_ING_SUP_PERMI = "El plazo ingresado es superior al plazo permitido.";
	private static final String MSG_MONTO_MENOR_1 = "El monto ingresado no puede ser menor a 1 USD.";
	private static final String MSG_PLAZO_MAYOR = "El plazo ingresado no puede ser menor o igual cero.";
	SimulacionCreditoFactory simulacionCreditoFactory;
	@EJB
	private PersonalizarCreditoService personalizarCreditoService;

	/**
	 * 
	 */
	public SimulacionCreditoServicioImpl() {

		this.simulacionCreditoFactory = SimulacionCreditoFactory.getInstancia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SimularCreditoServicio#simularCredito(int,
	 *      int, java.math.BigDecimal, java.math.BigDecimal, java.util.List)
	 */
	@Override
	public Simulacion simularCredito(final int tipoSimulacion, final int plazo,
			final BigDecimal monto, final BigDecimal cuota,
			final List<PlazoCredito> plazoCreditoList, final BigDecimal tasaInteres) {

		final SimularCredito simularCredito = this.simulacionCreditoFactory
				.factory(tipoSimulacion);
		return simularCredito.simularCredito(plazoCreditoList, cuota, monto,
				tasaInteres, plazo);
	}

	@Override
	public Simulacion simularCreditoSegunMontoIngresado(final OpcionCredito opcionCredito) throws MontosMaximosException {
		
		final Simulacion simulacion = new Simulacion();
		simulacion.setMontoCredito(opcionCredito.getValorTotalCredito()); //monto del credito
		simulacion.setTipoSimulacion(opcionCredito.getTipoTablaSeleccionado());
		simulacion.setPlazoCredito(opcionCredito.getMeses()); //plazo para el credito		
		simulacion.setPlazoMaximoCredito(opcionCredito.getPlazoMaximoCredito()); 
		
			
		// 1. Controla que el plazo sea mayor a cero

		if (opcionCredito.getMeses() <= 0) {
			simulacion.setObservacion(MSG_PLAZO_MAYOR);
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		
		// 2. Controla que el monto sea mayor a 1

		if (opcionCredito.getValorTotalCredito().floatValue() < 1) {
			simulacion.setObservacion(MSG_MONTO_MENOR_1);
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		
		// 3. controla plazo maximo
		if (opcionCredito.getMeses() > opcionCredito.getPlazoMaximoCredito()){
			simulacion.setObservacion(PLAZO_ING_SUP_PERMI);
			simulacion.setCalculoCredito(false);
			return simulacion;
		}
		
		final BigDecimal montoMaximoCredito = personalizarCreditoService.obtenerMontoMaximoPorTipoAmortizacion(opcionCredito.getCapacidadEndeudamiento(),
				opcionCredito.getTasaInteres(), new BigDecimal(opcionCredito.getMeses()), opcionCredito.getTotalGarantias(),
				opcionCredito.getTipoTablaSeleccionado(), opcionCredito.isEsEmergente(), opcionCredito.getTipoPrestamista(),
				opcionCredito.getEdadAsegurado());		
		// 4. si el monto ingresado es mayor al monto maximo
		simulacion.setMontoMaximoCredito(montoMaximoCredito);
		if (opcionCredito.getValorTotalCredito().floatValue() > montoMaximoCredito.floatValue()) {
			simulacion.setObservacion("El monto solicitado es mayor al monto m\u00E1ximo permitido en ese plazo.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		
		simulacion.setCuotaCredito(obtenerCuotaMaximaPorTipoTabla(opcionCredito.getValorTotalCredito(), opcionCredito.getTasaInteres(), opcionCredito.getMeses(), opcionCredito.getTipoTablaSeleccionado()));
		simulacion.setCalculoCredito(true);
		simulacion.setObservacion("Simulaci\u00F3n Exitosa");
		return simulacion;
	}
	
	

	@Override
	public BigDecimal obtenerCuotaMaximaPorTipoTabla(final BigDecimal montoCredito, final BigDecimal tasaInteres, final int numeroMeses, final String tipoTabla){		
		BigDecimal cuota = BigDecimal.ZERO;
		final TipoTablaEnum tipo = TipoTablaEnum.valueOf(tipoTabla);	
		  switch(tipo) {		  
		            case ALEMANA:		  
		                 System.out.println("Alemana");
		                 cuota= CalculoCreditoHelperSingleton.getInstancia().determinarCuotaMaximaAlemana(montoCredito, tasaInteres, numeroMeses);
		                 break;		  
		            case FRANCESA:		  
		                 System.out.println("Fracesa");
		                 cuota= CalculoCreditoHelperSingleton.getInstancia().determinarCuotaMaxima(montoCredito, tasaInteres, numeroMeses);
		                 break;		  
		           default:		  
		                 System.out.println("default");
		                 throw new IllegalArgumentException("EL tipo de Tabla deber ser ALEMANA o FRANCESA");		               	  
		           }
		return cuota;
		
	}

	@Override
	public Simulacion simularCreditoSegunCuotaIngresada(
			final OpcionCredito opcionCredito) throws MontosMaximosException {		
		
		final Simulacion simulacion = new Simulacion();		
		simulacion.setPlazoCredito(opcionCredito.getMeses()); //plazo para el credito
		simulacion.setTipoSimulacion(opcionCredito.getTipoTablaSeleccionado());		
		simulacion.setPlazoMaximoCredito(opcionCredito.getPlazoMaximoCredito());
		
				// 1. Controla que el plazo sea mayor a cero

				if (opcionCredito.getMeses() <= 0) {
					simulacion.setObservacion(MSG_PLAZO_MAYOR);
					simulacion.setCalculoCredito(false);
					return simulacion;

				}
			
				// 2. Controla que la cuota sea mayor a 0
				if (opcionCredito.getCuotaMensual().floatValue() <= 0) {
					simulacion.setObservacion("La cuota ingresado no puede ser menor o igual 0 USD.");
					simulacion.setCalculoCredito(false);
					return simulacion;

				}
				
				// 3. controla plazo maximo
				if (opcionCredito.getMeses() > opcionCredito.getPlazoMaximoCredito()){
					simulacion.setObservacion(PLAZO_ING_SUP_PERMI);
					simulacion.setCalculoCredito(false);
					return simulacion;
				}
				
				
				final BigDecimal cuotaMaximaCalculada = opcionCredito.getCapacidadEndeudamiento();
				simulacion.setCuotaMaximaComprometer(cuotaMaximaCalculada);
				
				if (opcionCredito.getCuotaMensual().floatValue() > cuotaMaximaCalculada.floatValue()) {
					simulacion.setObservacion("La cuota ingresada es mayor a la cuota estimada a comprometer en ese plazo.");
					simulacion.setCalculoCredito(false);
					return simulacion;		
				}
				
				//4 validar monto y couta				
				BigDecimal montoCalculado = CalculoCreditoHelperSingleton.getInstancia().obtenerMontoMaximoPorTipoAmortizacionSinAjuste(opcionCredito.getCuotaMensual(), opcionCredito.getTasaInteres(), new BigDecimal(opcionCredito.getMeses()), opcionCredito.getTipoTablaSeleccionado()); 
				montoCalculado = montoCalculado.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				if (montoCalculado.floatValue() >   UtilAjusteCalculo.ajusteNumberBaseDatos(opcionCredito.getTotalGarantias()).floatValue()) {
					simulacion
							.setObservacion("Con la cuota ingresada al plazo ingresado, el monto calculado ("
									+ montoCalculado
									+ ") sobrepasa el limite maximo de credito ("
									+ opcionCredito.getTotalGarantias() + ")");
					simulacion.setCalculoCredito(false);
					return simulacion;

				}
						
				
				// 5. Calculo monto				
		simulacion.setMontoCredito(
				personalizarCreditoService.obtenerMontoMaximoPorTipoAmortizacion(opcionCredito.getCuotaMensual(), opcionCredito.getTasaInteres(),
						new BigDecimal(opcionCredito.getMeses()), opcionCredito.getTotalGarantias(), opcionCredito.getTipoTablaSeleccionado(),
						opcionCredito.isEsEmergente(), opcionCredito.getTipoPrestamista(), opcionCredito.getEdadAsegurado()));				
				simulacion.setCalculoCredito(true);
				simulacion.setObservacion("Simulacion Exitosa");
			

		
		return simulacion;
	}

	@Override
	public Simulacion simularCreditoSegunMontoIngresadoSac(final OpcionCredito opcionCredito,
			final CalculoCreditoServicio calculoCreditoServicio,final ClienteRequestDto cliente, Long operacionSac) throws MontosMaximosException {
		
		
		final Simulacion simulacion = new Simulacion();
		simulacion.setMontoCredito(opcionCredito.getValorTotalCredito()); //monto del credito
		simulacion.setTipoSimulacion(opcionCredito.getTipoTablaSeleccionado());
		simulacion.setPlazoCredito(opcionCredito.getMeses()); //plazo para el credito		
		simulacion.setPlazoMaximoCredito(opcionCredito.getPlazoMaximoCredito()); 
		
			
		// 1. Controla que el plazo sea mayor a cero

		if (opcionCredito.getMeses() <= 0) {
			simulacion.setObservacion(MSG_PLAZO_MAYOR);
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		
		// 2. Controla que el monto sea mayor a 1

		if (opcionCredito.getValorTotalCredito().floatValue() < 1) {
			simulacion.setObservacion(MSG_MONTO_MENOR_1);
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		
		// 3. controla plazo maximo
		if (opcionCredito.getMeses() > opcionCredito.getPlazoMaximoCredito()){
			simulacion.setObservacion(PLAZO_ING_SUP_PERMI);
			simulacion.setCalculoCredito(false);
			return simulacion;
		}
		
		final BigDecimal montoMaximoCredito = opcionCredito.getMontoMaxSac();

		
		// 4. si el monto ingresado es mayor al monto maximo
		simulacion.setMontoMaximoCredito(montoMaximoCredito);
		if (opcionCredito.getValorTotalCredito().floatValue() > montoMaximoCredito.floatValue()) {
			simulacion.setObservacion("El monto solicitado es mayor al monto m\u00E1ximo permitido en ese plazo.");
			simulacion.setCalculoCredito(false);
			return simulacion;

		}
		
			
				TablaAmortizacionSac tblAmor=null;
				try {
					tblAmor=	calculoCreditoServicio.obtenerInformacionTablaAmortizacionSAC(obtenerSimulacionSac(cliente, opcionCredito, Boolean.FALSE, operacionSac));
				} catch (final TablaAmortizacionSacException e) {
					simulacion.setObservacion(e.getCodigo()+":Se ha producido un error al obtener la información, por favor notificar este mensaje al siguiente correo  ayudapq@biess.fin.ec.");
					simulacion.setCalculoCredito(false);
					return simulacion;

				}
				
				simulacion.setCuotaCredito(tblAmor.getValorCuotaPagar());
				simulacion.setTasaInteres(tblAmor.getInteres());
				simulacion.setCalculoCredito(true);
				simulacion.setObservacion("Simulaci\u00F3n Exitosa");
				return simulacion;
						
	}
	
	private OperacionSacRequest obtenerSimulacionSac(final ClienteRequestDto cliente,final OpcionCredito opcionCredito,final boolean esPorCuota, Long operacionSac) {
		final OperacionSacRequest request = new OperacionSacRequest();
		request.setCliente(cliente);
		final PrestamoRequestDto prestamo = new PrestamoRequestDto();
		if(esPorCuota) {
			prestamo.setMontoCuota(opcionCredito.getCapacidadEndeudamiento());
			prestamo.setMonto(BigDecimal.ZERO);
		}else {
			prestamo.setMontoCuota(BigDecimal.ZERO);
			prestamo.setMonto(opcionCredito.getValorTotalCredito());
		}
		
		
		prestamo.setPlazo(opcionCredito.getMeses());
		prestamo.setTipoTablaAmortizacion(FuncionesUtilesSac.obtenerTipoTablaSac(opcionCredito.getTipoTablaSeleccionado()));
		final OperacionRequestDto operacion = new OperacionRequestDto();
		operacion.setTipoProducto(obtenerTipoProducto(opcionCredito));
		if(operacionSac != null) {
			operacion.setNumero(operacionSac);
		}
		
		request.setOperacion(operacion);
		request.setPrestamo(prestamo);
		return request;
	}
	
	private String obtenerTipoProducto(final OpcionCredito opcionCredito) {
		if( "DINAMICO".equals(opcionCredito.getTipoProductoPq().name())){
			return opcionCredito.getCodProdEspecial()==2?"VEC":"EBI";
		}else {
			return opcionCredito.isEsEmergente()?"EMERG":opcionCredito.getTipoProductoPq().name();
		}
	}
	
	
}
