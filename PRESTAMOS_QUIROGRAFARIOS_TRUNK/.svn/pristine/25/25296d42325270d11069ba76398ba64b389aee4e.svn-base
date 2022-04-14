package ec.gov.iess.creditos.pq.util;

import java.util.ArrayList;
import java.util.List;

import ec.fin.biess.creditos.pq.modelo.dto.PrestacionPensionado;
import ec.gov.iess.creditos.enumeracion.PrestacionesValidasCredito;
import ec.gov.iess.servicio.pensiones.modelo.Prestacion;
import ec.gov.iess.servicio.pensiones.modelo.TipoPrestacionSeguro;

public class GeneradorListaPrestaciones {

	public static List<TipoPrestacionSeguro> generarLista() {
		List<TipoPrestacionSeguro> prestacionesValidas = new ArrayList<TipoPrestacionSeguro>();
		for (PrestacionesValidasCredito p : PrestacionesValidasCredito.values()) {
			TipoPrestacionSeguro tipoPrestacionSeguro = new TipoPrestacionSeguro();
			tipoPrestacionSeguro.setTipoSeguro(p.getTipoSeguro());
			tipoPrestacionSeguro.setTipoPrestacion(p.getTipoPrestacion());
			prestacionesValidas.add(tipoPrestacionSeguro);
		}
		return prestacionesValidas;
	}

	public static String getNombresPrestaciones(List<Prestacion> prestaciones) {
		StringBuffer nombres = new StringBuffer();

		for (Prestacion prestacion : prestaciones) {
			nombres.append(PrestacionesValidasCredito.obtenerNombre(prestacion
					.getTipoSeguro(), prestacion.getTipoPrestacion()));
			nombres.append(", ");
		}
		
		//REmovemos dos caracteres del final
		nombres.deleteCharAt(nombres.length() - 1);
		nombres.deleteCharAt(nombres.length()- 1);
		return nombres.toString();
	}
	
	/**
	 * Obtiene la descripcion de las prestaciones de jubilados/pensionistas.
	 * 
	 * @param prestaciones
	 *            - lista de prestaciones.
	 */
	public static String concatenarDescripcionPrestaciones(List<PrestacionPensionado> listaPrestaciones) {
		// INC-2135 Pensiones Jubilados/Pensionistas
		String respuesta = "";
		if (null != listaPrestaciones && !listaPrestaciones.isEmpty()) {
			for (PrestacionPensionado pres : listaPrestaciones) {
				respuesta = respuesta + pres.getDescripcion() + ", ";
			}
			respuesta = respuesta.substring(0, respuesta.length() - 2).toUpperCase();
		}
		return respuesta;
	}
}
