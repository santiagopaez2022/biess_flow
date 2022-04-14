package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.fin.biess.creditos.pq.servicio.PrestacionesBiessServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.pq.servicio.DetalleCatalogoServicio;
import ec.gov.iess.creditos.pq.servicio.OrigenJubiladoServicio;
import ec.gov.iess.creditos.pq.servicio.OrigenJubiladoServicioRemote;

/**
 * @author pjarrin
 * 
 */
@Stateless
public class OrigenJubiladoServicioImpl implements OrigenJubiladoServicio, OrigenJubiladoServicioRemote {
	LoggerBiess log = LoggerBiess.getLogger(OrigenJubiladoServicioImpl.class);

	@EJB
	private PrestacionesBiessServicio prestacionesBiessServicio;

	@EJB
	private DetalleCatalogoServicio detalleCatalogoServicio;

	/*
	 * @see ec.gov.iess.creditos.pq.servicio.OrigenJubiladoServicio# obtenerTotalRentasPorOrigen(java.lang.String)
	 */
	// public List<TotalRentaPorNominaOrigen> obtenerTotalRentasPorOrigen(
	// String cedula) {
	//
	// List<TotalRentaPorNominaOrigen> listaARetornar = new ArrayList<TotalRentaPorNominaOrigen>();
	//
	// // Primero se llenan las prestaciones válidas para el crédito
	// List<TipoPrestacionSeguro> prestacionesValidasCredito = GeneradorListaPrestaciones
	// .generarLista();
	// // Luego se llenan las prestaciones para cada nómina
	// /* INC-1731 2013-11-05 Se incluye el aumento 296 en los rubros para la concesión */
	// List<Prestacion> prestacionesHL = prestacionesBiessServicio.getListaPrestacionPorOrigen(cedula,
	// TipoSistemaPrestacionesConstante.SISTEMA_HISTORIA_LABORAL, prestacionesValidasCredito);
	// List<Prestacion> prestacionesHOUIO = prestacionesBiessServicio.getListaPrestacionPorOrigen(cedula,
	// TipoSistemaPrestacionesConstante.SISTEMA_HOST_UIO, prestacionesValidasCredito);
	// List<Prestacion> prestacionesHOGYE = prestacionesBiessServicio.getListaPrestacionPorOrigen(cedula,
	// TipoSistemaPrestacionesConstante.SISTEMA_HOST_GYE, prestacionesValidasCredito);
	// /* Fin cambios INC-1731 */
	//
	// // Se arma la colección con los totales de renta por cada origen
	// TotalRentaPorNominaOrigen total = new TotalRentaPorNominaOrigen();
	// total.setOrigen(OrigenJubilado.HL);
	// total.setTotalRenta(consolidarRentas(prestacionesHL));
	//
	// listaARetornar.add(total);
	//
	// total = new TotalRentaPorNominaOrigen();
	// total.setOrigen(OrigenJubilado.HOUIO);
	// total.setTotalRenta(consolidarRentas(prestacionesHOUIO));
	//
	// listaARetornar.add(total);
	//
	// total = new TotalRentaPorNominaOrigen();
	// total.setOrigen(OrigenJubilado.HOGYE);
	// total.setTotalRenta(consolidarRentas(prestacionesHOGYE));
	//
	// listaARetornar.add(total);
	//
	// return listaARetornar;
	// }

	/**
	 * Se encarga de sumar el valor de renta de cada una de las prestaciones
	 * 
	 * @param prestaciones
	 * @return
	 */
	// private double consolidarRentas(List<Prestacion> prestaciones) {
	// if (prestaciones == null)
	// return 0;
	// if (prestaciones.isEmpty())
	// return (0);
	// double total = 0;
	// double totalRetenciones = 0;
	//
	// for (Prestacion prestacion : prestaciones) {
	// if (prestacion.getTipoPrestacion().equals("VO")) {
	// if (detalleCatalogoServicio
	// .compruebaParentezcoBeneficiario(prestacion
	// .getTipoParentezco())) {
	//
	// total += prestacion.getIngresos().doubleValue();
	// totalRetenciones += prestacion
	// .getValorRetencionesJudiciales().doubleValue();
	// }
	// } else {
	// total += prestacion.getIngresos().doubleValue();
	// totalRetenciones += prestacion.getValorRetencionesJudiciales()
	// .doubleValue();
	// }
	// }
	//
	// return total - totalRetenciones;
	// }

	/*
	 * @see ec.gov.iess.creditos.pq.servicio.OrigenJubiladoServicio#obtenerOrigenJubilado (java.lang.String)
	 */
	// public TotalRentaPorNominaOrigen obtenerOrigenJubilado(String cedula) {
	// List<TotalRentaPorNominaOrigen> lista = this
	// .obtenerTotalRentasPorOrigen(cedula);
	// return OrigenJubiladoHelper.determinarMayorTotalRenta(lista);
	// }

}
