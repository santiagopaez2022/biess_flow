/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: Jenny Sanchez $, $Date: 06/06/2011 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_BIENES_ACCESORIOS_TBL")
@NamedQuery(name = "DetalleBienesSolicitud.obtenerPorCodigoSolicitud", query = "Select o from DetalleBienesSolicitud o "
		+ "where o.solicitudCredito.solicitudCreditoPK.numsolser=:numsolser "
		+ "and o.solicitudCredito.solicitudCreditoPK.codtipsolser=:codtipsolser")
@SequenceGenerator(name = "detalleBienesSolicitudSeq", sequenceName = "CRE_BIENES_ACCESORIOS_SEQ", initialValue = 1, allocationSize = 1)
public class DetalleBienesSolicitud implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -1710098041244050761L;

	@Id
	@Column(name = "BA_ID", nullable = false, insertable = true)
	@GeneratedValue(generator = "detalleBienesSolicitudSeq", strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	private Long id;

	@Column(name = "BA_PARQUEADERO")
	private String parqueadero;

	@Column(name = "BA_BODEGAS")
	private String bodegas;

	@Column(name = "BA_LAVADO")
	private String lavadoSecado;

	@Column(name = "BA_OTROS")
	private String otros;
	
	@Column(name = "BA_TERRAZA")
	private String inmPoseeTerraza;

	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "BA_CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "BA_NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the parqueadero
	 */
	public String getParqueadero() {
		return parqueadero;
	}

	/**
	 * @param parqueadero
	 *            the parqueadero to set
	 */
	public void setParqueadero(String parqueadero) {
		this.parqueadero = parqueadero;
	}

	/**
	 * @return the bodegas
	 */
	public String getBodegas() {
		return bodegas;
	}

	/**
	 * @param bodegas
	 *            the bodegas to set
	 */
	public void setBodegas(String bodegas) {
		this.bodegas = bodegas;
	}

	/**
	 * @return the lavadoSecado
	 */
	public String getLavadoSecado() {
		return lavadoSecado;
	}

	/**
	 * @param lavadoSecado
	 *            the lavadoSecado to set
	 */
	public void setLavadoSecado(String lavadoSecado) {
		this.lavadoSecado = lavadoSecado;
	}

	/**
	 * @return the otros
	 */
	public String getOtros() {
		return otros;
	}

	/**
	 * @param otros
	 *            the otros to set
	 */
	public void setOtros(String otros) {
		this.otros = otros;
	}

	/**
	 * @param inmPoseeTerraza the inmPoseeTerraza to set
	 */
	public void setInmPoseeTerraza(String inmPoseeTerraza) {
		this.inmPoseeTerraza = inmPoseeTerraza;
	}

	/**
	 * @return the inmPoseeTerraza
	 */
	public String getInmPoseeTerraza() {
		return inmPoseeTerraza;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param solicitudCredito
	 *            the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}
}
