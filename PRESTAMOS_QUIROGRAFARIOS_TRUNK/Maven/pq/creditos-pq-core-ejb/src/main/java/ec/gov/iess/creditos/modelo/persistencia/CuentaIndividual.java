/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author rtituana
 */
@Entity
@Table(name = "KSCRETCUEIND")
public class CuentaIndividual implements Serializable {
	
	@Id
	@Column(nullable=false)
	private long numseccueind;

	private String codtiptra;

	private BigDecimal codpretip;

	private BigDecimal codprecla;

	private BigDecimal numpreafi;

	private BigDecimal ordpreafi;

	private String numafi;

	private String codpen;

	private Date fecregtra;

	private BigDecimal valtra;

	private BigDecimal numdiv;

	private String obstra;

	private String codconafi;

	private String codtipconafi;

	private BigDecimal numcuoafi;

	private static final long serialVersionUID = 1L;

	public CuentaIndividual() {
		super();
	}

	public long getNumseccueind() {
		return this.numseccueind;
	}

	public void setNumseccueind(long numseccueind) {
		this.numseccueind = numseccueind;
	}

	public String getCodtiptra() {
		return this.codtiptra;
	}

	public void setCodtiptra(String codtiptra) {
		this.codtiptra = codtiptra;
	}

	public BigDecimal getCodpretip() {
		return this.codpretip;
	}

	public void setCodpretip(BigDecimal codpretip) {
		this.codpretip = codpretip;
	}

	public BigDecimal getCodprecla() {
		return this.codprecla;
	}

	public void setCodprecla(BigDecimal codprecla) {
		this.codprecla = codprecla;
	}

	public BigDecimal getNumpreafi() {
		return this.numpreafi;
	}

	public void setNumpreafi(BigDecimal numpreafi) {
		this.numpreafi = numpreafi;
	}

	public BigDecimal getOrdpreafi() {
		return this.ordpreafi;
	}

	public void setOrdpreafi(BigDecimal ordpreafi) {
		this.ordpreafi = ordpreafi;
	}

	public String getNumafi() {
		return this.numafi;
	}

	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	public String getCodpen() {
		return this.codpen;
	}

	public void setCodpen(String codpen) {
		this.codpen = codpen;
	}

	public Date getFecregtra() {
		return this.fecregtra;
	}

	public void setFecregtra(Date fecregtra) {
		this.fecregtra = fecregtra;
	}

	public BigDecimal getValtra() {
		return this.valtra;
	}

	public void setValtra(BigDecimal valtra) {
		this.valtra = valtra;
	}

	public BigDecimal getNumdiv() {
		return this.numdiv;
	}

	public void setNumdiv(BigDecimal numdiv) {
		this.numdiv = numdiv;
	}

	public String getObstra() {
		return this.obstra;
	}

	public void setObstra(String obstra) {
		this.obstra = obstra;
	}

	public String getCodconafi() {
		return this.codconafi;
	}

	public void setCodconafi(String codconafi) {
		this.codconafi = codconafi;
	}

	public String getCodtipconafi() {
		return this.codtipconafi;
	}

	public void setCodtipconafi(String codtipconafi) {
		this.codtipconafi = codtipconafi;
	}

	public BigDecimal getNumcuoafi() {
		return this.numcuoafi;
	}

	public void setNumcuoafi(BigDecimal numcuoafi) {
		this.numcuoafi = numcuoafi;
	}

}
