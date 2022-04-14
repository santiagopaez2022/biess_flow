/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.EstadoLiquidacion;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.1 $ [Sep 19, 2007]
 * @author pablo
 */
@Entity
@Table(name = "KSCRETPRELIQ")
@NamedQueries( {
	@NamedQuery(name = "LiquidacionPrestamo.buscarLiquidacionPorNumeroTipoEstado", query = "select liq from LiquidacionPrestamo liq"
			+ " where liq.numeroLiquidacion = :numeroLiquidacion "
			+ " and liq.tipoLiquidacion = :tipoLiquidacion "
			+ " and liq.estadoLiquidacion = :estadoLiquidacion ")
})
@SequenceGenerator(name = "secLididacionPrestamo", sequenceName = "HLSLIQPRE", allocationSize = 1)
public class LiquidacionPrestamo implements Serializable {

	private static final long serialVersionUID = 1378392021385989105L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="secLididacionPrestamo")
	@Column(name = "NUMLIQPRE", nullable = false)
	private Long numeroLiquidacion;

	@Column(name = "FECLIQPRE", nullable = false)
	private Date fechaLiquidacion;

	@Column(name = "ORDPREAFI")
	private Long ordPreAfi;

	@Column(name = "NUMPREAFI")
	private Long numPreAfi;

	@Column(name = "CODPRETIP")
	private Long codPreTip;

	@Column(name = "CODPRECLA")
	private Long codPreCla;

	@Column(name = "CODESTLIQPRE", nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoLiquidacion estadoLiquidacion;

	@Column(name = "CODTIPLIQ", nullable = false)
	private String tipoLiquidacion;

	@Column(name = "TIPDOCCAN")
	private String tipDocCan;

	@Column(name = "NUMDOCCAN")
	private String numDocCan;

	@Column(name = "FECDOCCAN")
	private Date fecDocCan;

	@Column(name = "CODTIPCONAFI")
	private String codTipConAfi;

	@Column(name = "CODCONAFI")
	private String codConAfi;

	@Column(name = "VALSALCAP")
	private BigDecimal valSalCap;

	@Column(name = "VALRESMAT")
	private BigDecimal valResMat;

	@Column(name = "VALINTNOCOB")
	private BigDecimal valIntNoCob;

	@Column(name = "FECTERPRELIQ", nullable = false)
	private Date fecTerPreLiq;

	@Column(name = "SEGSALPUR")
	private BigDecimal segSalPur;

	@Column(name = "SEGSALNET")
	private BigDecimal segSalNet;

	@Column(name = "TOTCANLIQ")
	private BigDecimal totCanLiq;

	@Column(name = "SUMCAPAMO")
	private BigDecimal sumCapAmo;

	@Column(name = "SUMINTSALCAP")
	private BigDecimal sumIntSalCap;

	@Column(name = "SUMINTPERGRA")
	private BigDecimal sumIntPerGra;

	@Column(name = "SUMINTMOR")
	private BigDecimal sumIntMor;

	@Column(name = "SUMDIVTOT")
	private BigDecimal sumDivTot;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false) })
	private Prestamo prestamo;

	@OneToMany(mappedBy = "liquidacionPrestamo")
	private List<LiquidacionPrestamoDetalle> detalle;

	/**
	 * Returns the value of numeroLiquidacion.
	 * 
	 * @return numeroLiquidacion
	 */
	public Long getNumeroLiquidacion() {
		return numeroLiquidacion;
	}

	/**
	 * Sets the value for numeroLiquidacion.
	 * 
	 * @param numeroLiquidacion
	 */
	public void setNumeroLiquidacion(Long numeroLiquidacion) {
		this.numeroLiquidacion = numeroLiquidacion;
	}

	/**
	 * Returns the value of fechaLiquidacion.
	 * 
	 * @return fechaLiquidacion
	 */
	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the value for fechaLiquidacion.
	 * 
	 * @param fechaLiquidacion
	 */
	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Returns the value of ordPreAfi.
	 * 
	 * @return ordPreAfi
	 */
	public Long getOrdPreAfi() {
		return ordPreAfi;
	}

	/**
	 * Sets the value for ordPreAfi.
	 * 
	 * @param ordPreAfi
	 */
	public void setOrdPreAfi(Long ordPreAfi) {
		this.ordPreAfi = ordPreAfi;
	}

	/**
	 * Returns the value of numPreAfi.
	 * 
	 * @return numPreAfi
	 */
	public Long getNumPreAfi() {
		return numPreAfi;
	}

	/**
	 * Sets the value for numPreAfi.
	 * 
	 * @param numPreAfi
	 */
	public void setNumPreAfi(Long numPreAfi) {
		this.numPreAfi = numPreAfi;
	}

	/**
	 * Returns the value of codPreTip.
	 * 
	 * @return codPreTip
	 */
	public Long getCodPreTip() {
		return codPreTip;
	}

	/**
	 * Sets the value for codPreTip.
	 * 
	 * @param codPreTip
	 */
	public void setCodPreTip(Long codPreTip) {
		this.codPreTip = codPreTip;
	}

	/**
	 * Returns the value of codPreCla.
	 * 
	 * @return codPreCla
	 */
	public Long getCodPreCla() {
		return codPreCla;
	}

	/**
	 * Sets the value for codPreCla.
	 * 
	 * @param codPreCla
	 */
	public void setCodPreCla(Long codPreCla) {
		this.codPreCla = codPreCla;
	}

	/**
	 * Returns the value of estadoLiquidacion.
	 * 
	 * @return estadoLiquidacion
	 */
	public EstadoLiquidacion getEstadoLiquidacion() {
		return estadoLiquidacion;
	}

	/**
	 * Sets the value for estadoLiquidacion.
	 * 
	 * @param estadoLiquidacion
	 */
	public void setEstadoLiquidacion(EstadoLiquidacion estadoLiquidacion) {
		this.estadoLiquidacion = estadoLiquidacion;
	}

	/**
	 * Returns the value of tipoLiquidacion.
	 * 
	 * @return tipoLiquidacion
	 */
	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	/**
	 * Sets the value for tipoLiquidacion.
	 * 
	 * @param tipoLiquidacion
	 */
	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	/**
	 * Returns the value of tipDocCan.
	 * 
	 * @return tipDocCan
	 */
	public String getTipDocCan() {
		return tipDocCan;
	}

	/**
	 * Sets the value for tipDocCan.
	 * 
	 * @param tipDocCan
	 */
	public void setTipDocCan(String tipDocCan) {
		this.tipDocCan = tipDocCan;
	}

	/**
	 * Returns the value of numDocCan.
	 * 
	 * @return numDocCan
	 */
	public String getNumDocCan() {
		return numDocCan;
	}

	/**
	 * Sets the value for numDocCan.
	 * 
	 * @param numDocCan
	 */
	public void setNumDocCan(String numDocCan) {
		this.numDocCan = numDocCan;
	}

	/**
	 * Returns the value of fecDocCan.
	 * 
	 * @return fecDocCan
	 */
	public Date getFecDocCan() {
		return fecDocCan;
	}

	/**
	 * Sets the value for fecDocCan.
	 * 
	 * @param fecDocCan
	 */
	public void setFecDocCan(Date fecDocCan) {
		this.fecDocCan = fecDocCan;
	}

	/**
	 * Returns the value of codTipConAfi.
	 * 
	 * @return codTipConAfi
	 */
	public String getCodTipConAfi() {
		return codTipConAfi;
	}

	/**
	 * Sets the value for codTipConAfi.
	 * 
	 * @param codTipConAfi
	 */
	public void setCodTipConAfi(String codTipConAfi) {
		this.codTipConAfi = codTipConAfi;
	}

	/**
	 * Returns the value of codConAfi.
	 * 
	 * @return codConAfi
	 */
	public String getCodConAfi() {
		return codConAfi;
	}

	/**
	 * Sets the value for codConAfi.
	 * 
	 * @param codConAfi
	 */
	public void setCodConAfi(String codConAfi) {
		this.codConAfi = codConAfi;
	}

	/**
	 * @return the valSalCap
	 */
	public BigDecimal getValSalCap() {
		return valSalCap;
	}

	/**
	 * @param valSalCap
	 *            the valSalCap to set
	 */
	public void setValSalCap(BigDecimal valSalCap) {
		this.valSalCap = valSalCap;
	}

	/**
	 * @return the valResMat
	 */
	public BigDecimal getValResMat() {
		return valResMat;
	}

	/**
	 * @param valResMat
	 *            the valResMat to set
	 */
	public void setValResMat(BigDecimal valResMat) {
		this.valResMat = valResMat;
	}

	/**
	 * @return the valIntNoCob
	 */
	public BigDecimal getValIntNoCob() {
		return valIntNoCob;
	}

	/**
	 * @param valIntNoCob
	 *            the valIntNoCob to set
	 */
	public void setValIntNoCob(BigDecimal valIntNoCob) {
		this.valIntNoCob = valIntNoCob;
	}

	/**
	 * @return the fecTerPreLiq
	 */
	public Date getFecTerPreLiq() {
		return fecTerPreLiq;
	}

	/**
	 * @param fecTerPreLiq
	 *            the fecTerPreLiq to set
	 */
	public void setFecTerPreLiq(Date fecTerPreLiq) {
		this.fecTerPreLiq = fecTerPreLiq;
	}

	/**
	 * @return the segSalPur
	 */
	public BigDecimal getSegSalPur() {
		return segSalPur;
	}

	/**
	 * @param segSalPur
	 *            the segSalPur to set
	 */
	public void setSegSalPur(BigDecimal segSalPur) {
		this.segSalPur = segSalPur;
	}

	/**
	 * @return the segSalNet
	 */
	public BigDecimal getSegSalNet() {
		return segSalNet;
	}

	/**
	 * @param segSalNet
	 *            the segSalNet to set
	 */
	public void setSegSalNet(BigDecimal segSalNet) {
		this.segSalNet = segSalNet;
	}

	/**
	 * @return the totCanLiq
	 */
	public BigDecimal getTotCanLiq() {
		return totCanLiq;
	}

	/**
	 * @param totCanLiq
	 *            the totCanLiq to set
	 */
	public void setTotCanLiq(BigDecimal totCanLiq) {
		this.totCanLiq = totCanLiq;
	}

	/**
	 * @return the sumCapAmo
	 */
	public BigDecimal getSumCapAmo() {
		return sumCapAmo;
	}

	/**
	 * @param sumCapAmo
	 *            the sumCapAmo to set
	 */
	public void setSumCapAmo(BigDecimal sumCapAmo) {
		this.sumCapAmo = sumCapAmo;
	}

	/**
	 * @return the sumIntSalCap
	 */
	public BigDecimal getSumIntSalCap() {
		return sumIntSalCap;
	}

	/**
	 * @param sumIntSalCap
	 *            the sumIntSalCap to set
	 */
	public void setSumIntSalCap(BigDecimal sumIntSalCap) {
		this.sumIntSalCap = sumIntSalCap;
	}

	/**
	 * @return the sumIntPerGra
	 */
	public BigDecimal getSumIntPerGra() {
		return sumIntPerGra;
	}

	/**
	 * @param sumIntPerGra
	 *            the sumIntPerGra to set
	 */
	public void setSumIntPerGra(BigDecimal sumIntPerGra) {
		this.sumIntPerGra = sumIntPerGra;
	}

	/**
	 * @return the sumIntMor
	 */
	public BigDecimal getSumIntMor() {
		return sumIntMor;
	}

	/**
	 * @param sumIntMor
	 *            the sumIntMor to set
	 */
	public void setSumIntMor(BigDecimal sumIntMor) {
		this.sumIntMor = sumIntMor;
	}

	/**
	 * @return the sumDivTot
	 */
	public BigDecimal getSumDivTot() {
		return sumDivTot;
	}

	/**
	 * @param sumDivTot
	 *            the sumDivTot to set
	 */
	public void setSumDivTot(BigDecimal sumDivTot) {
		this.sumDivTot = sumDivTot;
	}

	/**
	 * @return the prestamo
	 */
	public Prestamo getPrestamo() {
		return prestamo;
	}

	/**
	 * @param prestamo
	 *            the prestamo to set
	 */
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	/**
	 * @return the detalle
	 */
	public List<LiquidacionPrestamoDetalle> getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(List<LiquidacionPrestamoDetalle> detalle) {
		this.detalle = detalle;
	}
}
