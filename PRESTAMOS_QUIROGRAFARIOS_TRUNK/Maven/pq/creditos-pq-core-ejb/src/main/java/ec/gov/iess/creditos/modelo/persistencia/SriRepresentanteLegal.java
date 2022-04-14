package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapeo de la tabla KSPCOTSRIREPLEG
 * 
 * @author acantos
 * @author Andres Cantos
 * @version 1.0
 * 
 */

@Entity
@Table(name="KSPCOTSRIREPLEG")
public class SriRepresentanteLegal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8354231196681696268L;
	
	@Column(name="TIPIDE",nullable=false)
	private String tipide;		
	
	@Id
	@Column(name="CEDIDEREPLEG",nullable=false)
	@Basic(optional=false)
	private String cediderepleg;		
	
	@Column(name="IDERUCPAS",nullable=true)
	private String iderucpas;		
	
	@Column(name="TELREPLEG",nullable=true)
	private String telrepleg;		
	
	@Column(name="MAIREPLEG",nullable=true)
	private String mairepleg;	
	
	@Column(name="DIRREPLEG",nullable=true)
	private String dirrepleg;	

	@Column(name="NOMREPLEG",nullable=false)
	private String nomrepleg;	
	
	@Column(name="FECCAR",nullable=true)
	private Date feccar	;

	public String getTipide() {
		return tipide;
	}

	public void setTipide(String tipide) {
		this.tipide = tipide;
	}

	public String getCediderepleg() {
		return cediderepleg;
	}

	public void setCediderepleg(String cediderepleg) {
		this.cediderepleg = cediderepleg;
	}

	public String getIderucpas() {
		return iderucpas;
	}

	public void setIderucpas(String iderucpas) {
		this.iderucpas = iderucpas;
	}

	public String getTelrepleg() {
		return telrepleg;
	}

	public void setTelrepleg(String telrepleg) {
		this.telrepleg = telrepleg;
	}

	public String getMairepleg() {
		return mairepleg;
	}

	public void setMairepleg(String mairepleg) {
		this.mairepleg = mairepleg;
	}

	public String getDirrepleg() {
		return dirrepleg;
	}

	public void setDirrepleg(String dirrepleg) {
		this.dirrepleg = dirrepleg;
	}

	public String getNomrepleg() {
		return nomrepleg;
	}

	public void setNomrepleg(String nomrepleg) {
		this.nomrepleg = nomrepleg;
	}

	public Date getFeccar() {
		return feccar;
	}

	public void setFeccar(Date feccar) {
		this.feccar = feccar;
	}	
	
	

}
