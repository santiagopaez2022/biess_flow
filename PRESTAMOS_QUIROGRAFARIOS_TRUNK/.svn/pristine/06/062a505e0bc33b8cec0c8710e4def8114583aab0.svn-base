/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.ParametroValorPk;

/** 
 * <b>
 * Entidad Parametro Valor 
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Entity
@Table(name = "KSCRETPREVARCONFIN")
@NamedQuery(name = "ParametroValor.parametroVigente", query = "select pv from ParametroValor pv where pv.parametro.codigo = :codConFin and pv.pk.codPreTip = 2 and pv.pk.codPreCla = 30")
public class ParametroValor implements Serializable {

	private static final long serialVersionUID = -5857442527792200290L;

	@EmbeddedId
	private ParametroValorPk pk;

	@Column(name = "CODREFCONFIN")
	private String codRefConFin;

	@Column(name = "TIPVALCONFIN")
	private String tipValConFin;

	@Column(name = "VALNUMCONFIN")
	private Double valor;

	@Column(name = "VALCARCONFIN")
	private String valCarConFin;

	@Column(name = "VALFECCONFIN")
	private Timestamp valFecConFin;

	@Column(name = "VALINIRANCONFIN")
	private Integer valIniRanConFin;

	@Column(name = "VALFINRANCONFIN")
	private Integer valFinRanConFin;
	
	@Column(name = "VALCHARCONFIN")
	private String valCharConFin;

	@ManyToOne
	@JoinColumn(name = "CODCONFIN", referencedColumnName = "CODCONFIN", insertable = false, updatable = false)
	private Parametro parametro;

	/**
	 * Returns the value of pk.
	 * @return pk
	 */
	public ParametroValorPk getPk() {
		return pk;
	}

	/**
	 * Sets the value for pk.
	 * @param pk
	 */
	public void setPk(ParametroValorPk pk) {
		this.pk = pk;
	}

	/**
	 * Returns the value of codRefConFin.
	 * @return codRefConFin
	 */
	public String getCodRefConFin() {
		return codRefConFin;
	}

	/**
	 * Sets the value for codRefConFin.
	 * @param codRefConFin
	 */
	public void setCodRefConFin(String codRefConFin) {
		this.codRefConFin = codRefConFin;
	}

	/**
	 * Returns the value of tipValConFin.
	 * @return tipValConFin
	 */
	public String getTipValConFin() {
		return tipValConFin;
	}

	/**
	 * Sets the value for tipValConFin.
	 * @param tipValConFin
	 */
	public void setTipValConFin(String tipValConFin) {
		this.tipValConFin = tipValConFin;
	}

	/**
	 * Returns the value of valor.
	 * @return valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * Sets the value for valor.
	 * @param valor
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * Returns the value of valCarConFin.
	 * @return valCarConFin
	 */
	public String getValCarConFin() {
		return valCarConFin;
	}

	/**
	 * Sets the value for valCarConFin.
	 * @param valCarConFin
	 */
	public void setValCarConFin(String valCarConFin) {
		this.valCarConFin = valCarConFin;
	}

	/**
	 * Returns the value of valFecConFin.
	 * @return valFecConFin
	 */
	public Timestamp getValFecConFin() {
		return valFecConFin;
	}

	/**
	 * Sets the value for valFecConFin.
	 * @param valFecConFin
	 */
	public void setValFecConFin(Timestamp valFecConFin) {
		this.valFecConFin = valFecConFin;
	}

	/**
	 * Returns the value of valIniRanConFin.
	 * @return valIniRanConFin
	 */
	public Integer getValIniRanConFin() {
		return valIniRanConFin;
	}

	/**
	 * Sets the value for valIniRanConFin.
	 * @param valIniRanConFin
	 */
	public void setValIniRanConFin(Integer valIniRanConFin) {
		this.valIniRanConFin = valIniRanConFin;
	}

	/**
	 * Returns the value of valFinRanConFin.
	 * @return valFinRanConFin
	 */
	public Integer getValFinRanConFin() {
		return valFinRanConFin;
	}

	/**
	 * Sets the value for valFinRanConFin.
	 * @param valFinRanConFin
	 */
	public void setValFinRanConFin(Integer valFinRanConFin) {
		this.valFinRanConFin = valFinRanConFin;
	}

	/**
	 * Returns the value of parametro.
	 * @return parametro
	 */
	public Parametro getParametro() {
		return parametro;
	}

	/**
	 * Sets the value for parametro.
	 * @param parametro
	 */
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	/**
	 * @return the valCharConFin
	 */
	public String getValCharConFin() {
		return valCharConFin;
	}

	/**
	 * @param valCharConFin the valCharConFin to set
	 */
	public void setValCharConFin(String valCharConFin) {
		this.valCharConFin = valCharConFin;
	}

	
}