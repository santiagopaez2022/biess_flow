/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * <b>
 * Entidad representa a los datos del dia de feriado
 * </b>
 *  
 * @author Ricardo Tituaña
 * 
*/
@Entity
@Table(name = "pco_diasferiados_tbl")
@NamedQueries( {
		@NamedQuery(name = "DiasFeriado.obtenerPorAnioMesDiaMod", query = "SELECT d FROM DiasFeriado d WHERE d.anio=:anio and d.mes=:mes and d.diaFeriado=:dia and d.modulo=:modulo") })
@SequenceGenerator(name = "PCO_DIASFERIADOS_SEQ", sequenceName = "PCO_DIASFERIADOS_SEQ", allocationSize = 1)
public class DiasFeriado implements Serializable {

	private static final long serialVersionUID = 1975753207793702607L;
	
	@Id	
	@Column(name="DF_SEC",nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PCO_DIASFERIADOS_SEQ")
	private Long Id;

	@Column(name = "DF_ANIO")
	private Long anio;
	
	@Column(name = "DF_MES")
	private Long mes;

	@Column(name = "DF_DIA_FERIADO")
	private Long diaFeriado;

	@Column(name = "DF_ESFERIADO_DELEY")
	private String esFeriado;

	@Column(name = "MO_MODULO")
	private String modulo;
	
	public DiasFeriado() {	
	}

	public DiasFeriado(Long id, Long anio, Long mes,
			Long diaFeriado, String esFeriado, String modulo) {
		super();
		Id = id;
		this.anio = anio;
		this.mes = mes;
		this.diaFeriado = diaFeriado;
		this.esFeriado = esFeriado;
		this.modulo = modulo;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getDiaFeriado() {
		return diaFeriado;
	}

	public void setDiaFeriado(Long diaFeriado) {
		this.diaFeriado = diaFeriado;
	}

	public String getEsFeriado() {
		return esFeriado;
	}

	public void setEsFeriado(String esFeriado) {
		this.esFeriado = esFeriado;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	
	
	
}