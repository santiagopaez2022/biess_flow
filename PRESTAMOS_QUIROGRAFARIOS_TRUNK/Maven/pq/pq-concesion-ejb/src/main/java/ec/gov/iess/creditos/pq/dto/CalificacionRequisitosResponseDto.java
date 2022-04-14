/*
 * © Banco del Instituto Ecuatoriano de Seguridad Social 2020
 */
package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;

import ec.gov.iess.creditos.pq.dto.migracion.cartera.MensajeRespuestaDto;

/**
 * The Class CalificacionRequisitosResponseDto.
 */
public class CalificacionRequisitosResponseDto extends MensajeRespuestaDto implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1256443387878675L;

	/** esta en tramite. */
	private boolean estaEnTramite;

	/**
	 * Checks if is esta en tramite.
	 *
	 * @return true, if is esta en tramite
	 */
	public boolean isEstaEnTramite() {
		return estaEnTramite;
	}

	/**
	 * Sets the esta en tramite.
	 *
	 * @param estaEnTramite the new esta en tramite
	 */
	public void setEstaEnTramite(final boolean estaEnTramite) {
		this.estaEnTramite = estaEnTramite;
	}

}
