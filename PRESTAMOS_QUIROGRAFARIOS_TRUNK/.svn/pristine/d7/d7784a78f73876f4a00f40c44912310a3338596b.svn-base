package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Remote;

import ec.gov.iess.creditos.modelo.persistencia.CreditoValidacion;

/**
 * Servicio remoto para logica de negocio para CreditoValidacion
 * 
 * @author hugo.mora
 *
 */
@Remote
public interface CreditoValidacionServicioRemote {

	/**
	 * Consulta un CreditoValidacion dado los datos del prestamo (codprecla, codpretip, numpreafi, ordpreafi)
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 * @return
	 */
	CreditoValidacion consultarPorPrestamo(Long codprecla, Long codpretip, Long numpreafi, Long ordpreafi);
	
	/**
	 * Consulta un CreditoValidacion y su detalle (DetalleFocalizado) dado los datos del prestamo (codprecla, codpretip,
	 * numpreafi, ordpreafi)
	 * 
	 * @param codprecla
	 * @param codpretip
	 * @param numpreafi
	 * @param ordpreafi
	 * @return
	 */
	CreditoValidacion consultarPorPrestamoConDetalle(Long codprecla, Long codpretip, Long numpreafi, Long ordpreafi);

	/**
	 * Inserta un CreditoValidacion
	 * 
	 * @param creditoValidacion
	 */
	void insertarCreditoValidacion(CreditoValidacion creditoValidacion);
	
	/**
	 * Actualiza un CreditoValidacion
	 * 
	 * @param creditoValidacion
	 */
	void actualizarCreditoValidacion(CreditoValidacion creditoValidacion);

}
