/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * <b> Representacion ws entrada del Objeto orden detalle de compra. </b>
 * 
 * @author Ricardo Tituaña
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/06/14 19:36:43 $]
 *          </p>
 */
@SuppressWarnings("serial")
@Embeddable
public class PrestamoResumenHistoricoPk implements Serializable {

	@Column(name = "FECINI", nullable = false)
	private Date fecini;

	@Column(name = "CODPRECLA", nullable = false)
	private Long codprecla;

	@Column(name = "CODPRETIP", nullable = false)
	private Long codpretip;

	@Column(name = "NUMPREAFI", nullable = false)
	private Long numpreafi;

	@Column(name = "ORDPREAFI", nullable = false)
	private Long ordpreafi;

	public PrestamoResumenHistoricoPk() {
	}

	public PrestamoResumenHistoricoPk(Date fecini, Long codprecla,
			Long codpretip, Long numpreafi, Long ordpreafi) {
		super();
		this.fecini = fecini;
		this.codprecla = codprecla;
		this.codpretip = codpretip;
		this.numpreafi = numpreafi;
		this.ordpreafi = ordpreafi;
	}

	/**
	 * Comparador de objetos
	 */
	public boolean equals(Object other) {
		if (other instanceof PrestamoResumenHistoricoPk) {
			final PrestamoResumenHistoricoPk otherKscretcreditosPK = (PrestamoResumenHistoricoPk) other;
			final boolean areEqual = (otherKscretcreditosPK.fecini
					.equals(fecini)
					&& otherKscretcreditosPK.codprecla.equals(codprecla)
					&& otherKscretcreditosPK.codpretip.equals(codpretip)
					&& otherKscretcreditosPK.numpreafi.equals(numpreafi) && otherKscretcreditosPK.ordpreafi
					.equals(ordpreafi));
			return areEqual;
		}
		return false;
	}

	/**
	 * hascode
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * <b> obtiene codprecla </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @return codprecla : codprecla del prestamo
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	/**
	 * <b> obtiene codprecla </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @param codprecla
	 *            : codprecla del prestamo
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * <b> obtiene codpretip </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @return codpretip : codigo tipo del prestamo
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * <b> establece codigo tipo del prestamo </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @param codpretip
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * <b> obtiene el numero del prestamo afiliado </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @return numpreafi : el numero del prestamo afiliado
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	/**
	 * <b> establece el numero del prestamo afiliado </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @param numpreafi
	 *            : el numero del prestamo afiliado
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	/**
	 * <b> obtiene ordpreafi </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @return ordpreafi : ordpreafi del prestamo
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	/**
	 * <b> establece ordpreafi </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @param ordpreafi
	 *            : ordpreafi del prestamo
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	/**
	 * <b> obtiene fecha inicio </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @return fecini : fecha inicio
	 */
	public Date getFecini() {
		return fecini;
	}

	/**
	 * <b> establece fecha inicio </b>
	 * <p>
	 * [Author: cbastidas, Date: 17/05/2011]
	 * </p>
	 * 
	 * @param fecini
	 *            : fecha inicio
	 */
	public void setFecini(Date fecini) {
		this.fecini = fecini;
	}

}
