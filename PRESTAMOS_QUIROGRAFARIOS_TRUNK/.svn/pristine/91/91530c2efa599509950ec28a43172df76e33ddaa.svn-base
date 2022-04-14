/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase de entidad PrestamoInformacionDetalle
 * 
 *
 */
@Entity
@Table(name = "CRE_INFOCREDITOSDET_TBL", schema = "PQ_OWNER")
@NamedQuery(name = "PrestamoInformacionDetalle.obtenerInformacionDetallePorPK", 
	query = " select o from PrestamoInformacionDetalle o WHERE o.codprecla = :codprecla and o.codpretip = :codpretip and o.numpreafi = :numpreafi and ordpreafi = :ordpreafi ")
public class PrestamoInformacionDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_SECUENCIA", nullable = false)
	private Long id;

	@Column(name = "CODPRECLA", nullable = false)
	private Long codprecla;

	@Column(name = "CODPRETIP", nullable = false)
	private Long codpretip;

	@Column(name = "NUMPREAFI", nullable = false)
	private Long numpreafi;

	@Column(name = "ORDPREAFI", nullable = false)
	private Long ordpreafi;

	@Column(name = "ID_OBSERVACION1")
	private String observacion1;

	@Column(name = "ID_OBSERVACION2")
	private String observacion2;

	@Column(name = "ID_ARCHIVO")
	private Byte[] archivo;

	@Column(name = "ID_NOMBREARCHIVO")
	private String nombreArchivo;

	public PrestamoInformacionDetalle() {

	}

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
	 * @return the codprecla
	 */
	public Long getCodprecla() {
		return codprecla;
	}

	/**
	 * @param codprecla
	 *            the codprecla to set
	 */
	public void setCodprecla(Long codprecla) {
		this.codprecla = codprecla;
	}

	/**
	 * @return the codpretip
	 */
	public Long getCodpretip() {
		return codpretip;
	}

	/**
	 * @param codpretip
	 *            the codpretip to set
	 */
	public void setCodpretip(Long codpretip) {
		this.codpretip = codpretip;
	}

	/**
	 * @return the numpreafi
	 */
	public Long getNumpreafi() {
		return numpreafi;
	}

	/**
	 * @param numpreafi
	 *            the numpreafi to set
	 */
	public void setNumpreafi(Long numpreafi) {
		this.numpreafi = numpreafi;
	}

	/**
	 * @return the ordpreafi
	 */
	public Long getOrdpreafi() {
		return ordpreafi;
	}

	/**
	 * @param ordpreafi
	 *            the ordpreafi to set
	 */
	public void setOrdpreafi(Long ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	/**
	 * @return the observacion1
	 */
	public String getObservacion1() {
		return observacion1;
	}

	/**
	 * @param observacion1
	 *            the observacion1 to set
	 */
	public void setObservacion1(String observacion1) {
		this.observacion1 = observacion1;
	}

	/**
	 * @return the observacion2
	 */
	public String getObservacion2() {
		return observacion2;
	}

	/**
	 * @param observacion2
	 *            the observacion2 to set
	 */
	public void setObservacion2(String observacion2) {
		this.observacion2 = observacion2;
	}

	/**
	 * @return the archivo
	 */
	public Byte[] getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(Byte[] archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 *            the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}
