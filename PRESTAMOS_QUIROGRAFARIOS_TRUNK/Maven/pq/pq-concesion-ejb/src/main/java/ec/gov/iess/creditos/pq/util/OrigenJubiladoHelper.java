package ec.gov.iess.creditos.pq.util;

import java.util.ArrayList;
import java.util.List;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.modelo.dto.TotalRentaPorNominaOrigen;
import ec.gov.iess.pensiones.constantes.TipoSistemaPrestacionesConstante;
import ec.gov.iess.servicio.pensiones.modelo.Prestacion;

public class OrigenJubiladoHelper {
	
	/**
	 * Método que se encarga de analizar las prestaciones que tiene un jubilado
	 * para determinar su origen.<pre/>
	 * 
	 * Un jubilado puede tener varias prestaciones y cada una de esas prestaciones 
	 * pudo ser concedida en tres origenes: Historia Laboral, Host Quito y Host Guayaquil.
	 * 
	 * El orden de prioridad de los orígenes es
	 * HL
	 * HOUIO
	 * HOGYE
	 * Para determinar estas prioridades se utiliza una enumeración que define un valor entero 
	 * para cada origen.  
	 * 3 para HL
	 * 2 para HOUIO
	 * 1 para HOGYE
	 * Si un jubilado tiene mas de una prestación se retornará el origen de acuerdo al
	 * orden de prioridad
	 * 
	 * @param prestacionesJubilado
	 * @return OrigenJubilado 
	 */
	public static OrigenJubilado getOrigenJubilado(List<Prestacion> prestacionesJubilado){
		LoggerBiess log = LoggerBiess.getLogger(OrigenJubilado.class);
		//Se asume por defecto que es del Host de Guayaquil
		OrigenJubilado origenJubilado = null;
		
		OrigenJubilado origenARetornar = OrigenJubilado.HOGYE;;
		
		for (Prestacion prestacion : prestacionesJubilado) {
			String origen = prestacion.getTipoSistema();
			
			if(TipoSistemaPrestacionesConstante.SISTEMA_HISTORIA_LABORAL.getValor().equals(origen)){
				origenJubilado = OrigenJubilado.HL;
				log.debug("Origen: " + origenJubilado + " Valor: " + origenJubilado.getValorEntero());
			}
			
			if(TipoSistemaPrestacionesConstante.SISTEMA_HOST_UIO.getValor().equals(origen)){
				origenJubilado =  OrigenJubilado.HOUIO;
				log.debug("Origen: " + origenJubilado + " Valor: " + origenJubilado.getValorEntero());
			}
			
			if(TipoSistemaPrestacionesConstante.SISTEMA_HOST_GYE.getValor().equals(origen)){
				origenJubilado =  OrigenJubilado.HOGYE;
				log.debug("Origen: " + origenJubilado + " Valor: " + origenJubilado.getValorEntero());
			}
			
			try {
				if (origenJubilado.getValorEntero() > origenARetornar
						.getValorEntero()) {
					origenARetornar = origenJubilado;
				}
			} catch (NullPointerException e) {
				throw e;
			}
			
		}
		return origenARetornar;
	}
	
	/**
	 * Determina el origen del jubilado tomando en cuenta en qué nomina recibe el mayor valor 
	 * de renta sumando las rentas de todas las prestaciones
	 * @param listaRentaPorNomina
	 * @return Origen del jubilado
	 */
	public static OrigenJubilado determinarOrigenJubilado(List<TotalRentaPorNominaOrigen> listaRentaPorNomina){
		//Asegurandose de que la coleccion tenga datos
		if(listaRentaPorNomina == null)
			return null;
		
		if(listaRentaPorNomina.isEmpty())
			return null;
		
		TotalRentaPorNominaOrigen mayor = listaRentaPorNomina.get(0);
		
		for (TotalRentaPorNominaOrigen totalRentaPorNominaOrigen : listaRentaPorNomina) {
			if(totalRentaPorNominaOrigen.getTotalRenta() > mayor.getTotalRenta()){
				mayor = totalRentaPorNominaOrigen;
			}
		}
		
		return mayor.getTotalRenta() > 0 ? mayor.getOrigen() : null;
		
	}
	
	/**
	 * Determina el origen del jubilado tomando en cuenta en qué nomina recibe el mayor valor 
	 * de renta sumando las rentas de todas las prestaciones 
	 * @param listaRentaPorNomina
	 * @return TotalRentaPorNominaOrigen
	 */
	public static TotalRentaPorNominaOrigen determinarMayorTotalRenta(List<TotalRentaPorNominaOrigen> listaRentaPorNomina){
		//Asegurandose de que la coleccion tenga datos
		if(listaRentaPorNomina == null)
			return null;
		
		if(listaRentaPorNomina.isEmpty())
			return null;
		
		
		TotalRentaPorNominaOrigen mayor = listaRentaPorNomina.get(0);
		
		//Se eliminan los origenes cuya renta sea cero con el fin de devolver origenes con valores negativos y posibles retenciones
		List<TotalRentaPorNominaOrigen> listaTmp=new ArrayList<TotalRentaPorNominaOrigen>();
		for(TotalRentaPorNominaOrigen origen:listaRentaPorNomina){
			if(origen.getTotalRenta()!=0){
				listaTmp.add(origen);
			}	
		}
		if(!listaTmp.isEmpty()){
			mayor=listaTmp.get(0);
			for (TotalRentaPorNominaOrigen totalRentaPorNominaOrigen : listaTmp) {
				if(totalRentaPorNominaOrigen.getTotalRenta() > mayor.getTotalRenta()){
					mayor = totalRentaPorNominaOrigen;
				}
			}
		}
		return mayor;
		
	}
	
}
