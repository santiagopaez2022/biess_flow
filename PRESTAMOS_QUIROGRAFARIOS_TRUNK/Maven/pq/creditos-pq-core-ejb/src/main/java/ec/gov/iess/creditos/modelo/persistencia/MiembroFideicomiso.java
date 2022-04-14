/* 
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.MiembroFideicomisoPk;

/**
 * Clase para la persistencia de los miembros del fideicomiso
 * 
 * @author jsanchez
 * @version 1.0
 */
@Entity
@Table(name = "CRE_CC_MIEM_PROYECTO_TBL")
@NamedQuery(name = "MiembroFideicomiso.obtenerPorCodigoProyecto", query = "Select o from MiembroFideicomiso o "
		+ "where o.pk.codigoProyecto=:codigo order by o.orden")
public class MiembroFideicomiso implements Serializable {

	private static final long serialVersionUID = -3933262524426353022L;

	@EmbeddedId
	private MiembroFideicomisoPk pk;

	@Column(name = "MP_TIPO_IDENTIFICACION", nullable = false)
	private String tipoIdentificacion;

	@Column(name = "MP_ESTADO", nullable = false)
	private String estado;

	@Column(name = "MP_SCORE", nullable = false)
	private BigDecimal score;

	@Column(name = "MP_CALBUR", nullable = false)
	private String calBuro;

	@Column(name = "MP_TIPO_MIEMBRO", nullable = false)
	private String tipoMiembro;

	@Column(name = "MP_ORDEN", nullable = false)
	private Integer orden;

	@Transient
	private String nombre;

	@Transient
	private String nomTipoIdentificacion;

	@Transient
	private boolean representanteLegal;

	@Transient
	private String tipoRucPersona;

	@Transient
	private boolean personaNaturalEmpleador;

	@Transient
	private boolean puedeSerConstructor;

	@Transient
	private boolean constructor;

	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * @param tipoIdentificacion
	 *            the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the pk
	 */
	public MiembroFideicomisoPk getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(MiembroFideicomisoPk pk) {
		this.pk = pk;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nomTipoIdentificacion
	 */
	public String getNomTipoIdentificacion() {
		return nomTipoIdentificacion;
	}

	/**
	 * @param nomTipoIdentificacion
	 *            the nomTipoIdentificacion to set
	 */
	public void setNomTipoIdentificacion(String nomTipoIdentificacion) {
		this.nomTipoIdentificacion = nomTipoIdentificacion;
	}

	/**
	 * @return the representanteLegal
	 */
	public boolean isRepresentanteLegal() {
		return representanteLegal;
	}

	/**
	 * @param representanteLegal
	 *            the representanteLegal to set
	 */
	public void setRepresentanteLegal(boolean representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	/**
	 * @return the tipoRucPersona
	 */
	public String getTipoRucPersona() {
		return tipoRucPersona;
	}

	/**
	 * @param tipoRucPersona
	 *            the tipoRucPersona to set
	 */
	public void setTipoRucPersona(String tipoRucPersona) {
		this.tipoRucPersona = tipoRucPersona;
	}

	/**
	 * @return the personaNaturalEmpleador
	 */
	public boolean isPersonaNaturalEmpleador() {
		return personaNaturalEmpleador;
	}

	/**
	 * @param personaNaturalEmpleador
	 *            the personaNaturalEmpleador to set
	 */
	public void setPersonaNaturalEmpleador(boolean personaNaturalEmpleador) {
		this.personaNaturalEmpleador = personaNaturalEmpleador;
	}

	/**
	 * @return the puedeSerConstructor
	 */
	public boolean isPuedeSerConstructor() {
		return puedeSerConstructor;
	}

	/**
	 * @param puedeSerConstructor
	 *            the puedeSerConstructor to set
	 */
	public void setPuedeSerConstructor(boolean puedeSerConstructor) {
		this.puedeSerConstructor = puedeSerConstructor;
	}

	/**
	 * @return the constructor
	 */
	public boolean isConstructor() {
		return constructor;
	}

	/**
	 * @param constructor
	 *            the constructor to set
	 */
	public void setConstructor(boolean constructor) {
		this.constructor = constructor;
	}

	/**
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 * @return the calBuro
	 */
	public String getCalBuro() {
		return calBuro;
	}

	/**
	 * @param calBuro
	 *            the calBuro to set
	 */
	public void setCalBuro(String calBuro) {
		this.calBuro = calBuro;
	}

	/**
	 * @return the tipoMiembro
	 */
	public String getTipoMiembro() {
		return tipoMiembro;
	}

	/**
	 * @param tipoMiembro
	 *            the tipoMiembro to set
	 */
	public void setTipoMiembro(String tipoMiembro) {
		this.tipoMiembro = tipoMiembro;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
