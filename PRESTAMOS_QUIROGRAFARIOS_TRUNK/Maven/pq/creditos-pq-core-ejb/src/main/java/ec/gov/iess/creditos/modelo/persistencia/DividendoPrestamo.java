/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoPk;

/**
 * 
 * 
 * @author cvillarreal
 * 
 */
@Entity
@Table(name = "Kscretdividendos")
@NamedQueries( {
		@NamedQuery(name = "DividendoPrestamo.consultarTodo", query = "select o from DividendoPrestamo o"),
		@NamedQuery(name = "DividendoPrestamo.consultarPorIdPrestamo", query = "select o from DividendoPrestamo o where o.dividendoPrestamoPk.codprecla = :codprecla "
				+ "and o.dividendoPrestamoPk.codpretip = :codpretip and  o.dividendoPrestamoPk.numpreafi =  :numpreafi and  o.dividendoPrestamoPk.ordpreafi = :ordpreafi") })
@NamedNativeQueries( { @NamedNativeQuery(name = "DividendoPrestamo.mora", 
		query = "SELECT codprecla,codpretip,numdiv,numpreafi,ordpreafi,feccandiv,fecpagdiv,forcandiv, "
		+ "intpergra,intsalcap,numdoccan,obscarafi,sigcarafi,valcapamo,valcarafi,valsegsal,"
		+ "aniper,mesper,tipper,codtipdiv,codestdiv "
		+ "FROM kscretdividendos "
		+ "WHERE codestdiv in (:estadoDividendo) "
		+ "AND (numpreafi, ordpreafi, codpretip, codprecla) IN ( "
		+ "SELECT numpreafi, ordpreafi, codpretip, "
		+ "codprecla " + "FROM kscretcreditos " + " WHERE numafi = :cedula)", resultSetMapping = "mappingDividendoPrestamo") ,
		
@NamedNativeQuery(name = "DividendoPrestamo.listaCuota", 
		query = " SELECT DIV.* "+
                " FROM KSCRETCREDITOS CRE, KSCRETDIVIDENDOS DIV "+
                " WHERE CRE.NUMPREAFI = DIV.NUMPREAFI "+
                " AND CRE.CODPRECLA = DIV.CODPRECLA "+
                " AND CRE.CODPRETIP = DIV.CODPRETIP "+
                " AND CRE.ORDPREAFI = DIV.ORDPREAFI "+
                " AND DIV.ANIPER = TO_NUMBER(TO_CHAR(SYSDATE,'yyyy')) "+
                " AND DIV.MESPER = TO_NUMBER(TO_CHAR(SYSDATE,'mm')) "+
                " AND CRE.NUMAFI = :cedula "+
                " AND CRE.CODESTPRE = 'VIG'" ,resultSetMapping = "mappingDividendoPrestamo")
})
@SqlResultSetMapping(name = "mappingDividendoPrestamo", entities = @EntityResult(entityClass = DividendoPrestamo.class))
public class DividendoPrestamo implements Serializable {

	private static final long serialVersionUID = -1568173554185979031L;

	@Id
	private DividendoPrestamoPk dividendoPrestamoPk;

	
	private Date feccandiv;
	@Column(nullable = false)
	private Date fecpagdiv;
	private String forcandiv;
	@Column(nullable = false)
	private Double intpergra;
	@Column(nullable = false)
	private Double intsalcap;
	private String numdoccan;
	private String obscarafi;
	private Double tasintrea;
	private String sigcarafi;
	@Column(nullable = false)
	private Double valcapamo;
	private Double valcarafi;
	@Column(nullable = false)
	private Double valsegsal;
	/*
	 * @ManyToOne(fetch = FetchType.EAGER) @JoinColumns( { @JoinColumn(name =
	 * "TIPPER", referencedColumnName = "TIPPER"), @JoinColumn(name = "ANIPER",
	 * referencedColumnName = "ANIPER"), @JoinColumn(name = "MESPER",
	 * referencedColumnName = "MESPER") }) private Periodo periodo;
	 * 
	 */

	@Column(nullable = false)
	private Long mesper;
	@Column(nullable = false)
	private String tipper;
	@Column(nullable = false)
	private Long aniper;
	@Column
	private BigDecimal cr_interescapitalant;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODTIPDIV", referencedColumnName = "CODTIPDIV")
	private TipoDividendo tipoDividendo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODESTDIV", referencedColumnName = "CODESTDIV")
	private EstadoDividendoPrestamo estadoDividendoPrestamo;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "NUMPREAFI", referencedColumnName = "NUMPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "ORDPREAFI", referencedColumnName = "ORDPREAFI", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRETIP", referencedColumnName = "CODPRETIP", insertable = false, updatable = false),
			@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false) })
	private Prestamo prestamo;

	@Transient
	private BigDecimal saldoCapital;

	public BigDecimal getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(BigDecimal saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public DividendoPrestamo() {

	}

	/**
	 * @return the dividendoPrestamoPk
	 */
	public DividendoPrestamoPk getDividendoPrestamoPk() {
		return dividendoPrestamoPk;
	}

	/**
	 * @param dividendoPrestamoPk
	 *            the dividendoPrestamoPk to set
	 */
	public void setDividendoPrestamoPk(DividendoPrestamoPk dividendoPrestamoPk) {
		this.dividendoPrestamoPk = dividendoPrestamoPk;
	}

	/**
	 * @return the feccandiv
	 */
	public Date getFeccandiv() {
		return feccandiv;
	}

	/**
	 * @param feccandiv
	 *            the feccandiv to set
	 */
	public void setFeccandiv(Date feccandiv) {
		this.feccandiv = feccandiv;
	}

	/**
	 * @return the fecpagdiv
	 */
	public Date getFecpagdiv() {
		return fecpagdiv;
	}

	/**
	 * @param fecpagdiv
	 *            the fecpagdiv to set
	 */
	public void setFecpagdiv(Date fecpagdiv) {
		this.fecpagdiv = fecpagdiv;
	}

	/**
	 * @return the forcandiv
	 */
	public String getForcandiv() {
		return forcandiv;
	}

	/**
	 * @param forcandiv
	 *            the forcandiv to set
	 */
	public void setForcandiv(String forcandiv) {
		this.forcandiv = forcandiv;
	}

	/**
	 * @return the intpergra
	 */
	public Double getIntpergra() {
		return intpergra;
	}

	/**
	 * @param intpergra
	 *            the intpergra to set
	 */
	public void setIntpergra(Double intpergra) {
		this.intpergra = intpergra;
	}

	/**
	 * @return the intsalcap
	 */
	public Double getIntsalcap() {
		return intsalcap;
	}

	/**
	 * @param intsalcap
	 *            the intsalcap to set
	 */
	public void setIntsalcap(Double intsalcap) {
		this.intsalcap = intsalcap;
	}

	/**
	 * @return the numdoccan
	 */
	public String getNumdoccan() {
		return numdoccan;
	}

	/**
	 * @param numdoccan
	 *            the numdoccan to set
	 */
	public void setNumdoccan(String numdoccan) {
		this.numdoccan = numdoccan;
	}

	/**
	 * @return the obscarafi
	 */
	public String getObscarafi() {
		return obscarafi;
	}

	/**
	 * @param obscarafi
	 *            the obscarafi to set
	 */
	public void setObscarafi(String obscarafi) {
		this.obscarafi = obscarafi;
	}

	/**
	 * @return the sigcarafi
	 */
	public String getSigcarafi() {
		return sigcarafi;
	}

	/**
	 * @param sigcarafi
	 *            the sigcarafi to set
	 */
	public void setSigcarafi(String sigcarafi) {
		this.sigcarafi = sigcarafi;
	}

	/**
	 * @return the valcapamo
	 */
	public Double getValcapamo() {
		return valcapamo;
	}

	/**
	 * @param valcapamo
	 *            the valcapamo to set
	 */
	public void setValcapamo(Double valcapamo) {
		this.valcapamo = valcapamo;
	}

	/**
	 * @return the valcarafi
	 */
	public Double getValcarafi() {
		return valcarafi;
	}

	/**
	 * @param valcarafi
	 *            the valcarafi to set
	 */
	public void setValcarafi(Double valcarafi) {
		this.valcarafi = valcarafi;
	}

	/**
	 * @return the valsegsal
	 */
	public Double getValsegsal() {
		return valsegsal;
	}

	/**
	 * @param valsegsal
	 *            the valsegsal to set
	 */
	public void setValsegsal(Double valsegsal) {
		this.valsegsal = valsegsal;
	}

	/**
	 * @return the tipoDividendo
	 */
	public TipoDividendo getTipoDividendo() {
		return tipoDividendo;
	}

	/**
	 * @param tipoDividendo
	 *            the tipoDividendo to set
	 */
	public void setTipoDividendo(TipoDividendo tipoDividendo) {
		this.tipoDividendo = tipoDividendo;
	}

	/**
	 * @return the estadoDividendoPrestamo
	 */
	public EstadoDividendoPrestamo getEstadoDividendoPrestamo() {
		return estadoDividendoPrestamo;
	}

	/**
	 * @param estadoDividendoPrestamo
	 *            the estadoDividendoPrestamo to set
	 */
	public void setEstadoDividendoPrestamo(EstadoDividendoPrestamo estadoDividendoPrestamo) {
		this.estadoDividendoPrestamo = estadoDividendoPrestamo;
	}

	/**
	 * @return the tasintrea
	 */
	public Double getTasintrea() {
		return tasintrea;
	}

	/**
	 * @param tasintrea
	 *            the tasintrea to set
	 */
	public void setTasintrea(Double tasintrea) {
		this.tasintrea = tasintrea;
	}

	/**
	 * @return the mesper
	 */
	public Long getMesper() {
		return mesper;
	}

	/**
	 * @return the tipper
	 */
	public String getTipper() {
		return tipper;
	}

	/**
	 * @return the aniper
	 */
	public Long getAniper() {
		return aniper;
	}

	/**
	 * @param mesper
	 *            the mesper to set
	 */
	public void setMesper(Long mesper) {
		this.mesper = mesper;
	}

	/**
	 * @param tipper
	 *            the tipper to set
	 */
	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	/**
	 * @param aniper
	 *            the aniper to set
	 */
	public void setAniper(Long aniper) {
		this.aniper = aniper;
	}

	/**
	 * Returns the value of prestamo.
	 * @return prestamo
	 */
	public Prestamo getPrestamo() {
		return prestamo;
	}

	/**
	 * Sets the value for prestamo.
	 * @param prestamo
	 */
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public BigDecimal getCr_interescapitalant() {
		return cr_interescapitalant;
	}

	public void setCr_interescapitalant(BigDecimal cr_interescapitalant) {
		this.cr_interescapitalant = cr_interescapitalant;
	}
}