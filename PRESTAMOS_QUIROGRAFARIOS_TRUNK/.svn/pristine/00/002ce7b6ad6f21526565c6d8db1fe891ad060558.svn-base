package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the REC_VALOR_TRANSACCION_ENV_TBL database table.
 * 
 */
@Entity
@Table(name="REC_VALOR_TRANSACCION_ENV_TBL")
public class DetalleTransaccionRecaudacion implements Serializable {

	private static final long serialVersionUID = 3881457467097169690L;

	@Column(name="VT_ANIODIVIDENDO")
	private Long vtAniodividendo;

	@Column(name="VT_CONCEPTO")
	private String vtConcepto;

	@Temporal(TemporalType.DATE)
	@Column(name="VT_FEC_VENC_DIVI")
	private Date vtFecVencDivi;

	@Temporal(TemporalType.DATE)
	@Column(name="VT_FECHAPROCESO")
	private Date vtFechaproceso;

	@Column(name="VT_IDGAF")
	private BigDecimal vtIdgaf;
	
	@Column(name="VT_IDTIPOTRANSACCION")
	private BigDecimal vtIdtipotransaccion;

	@Id
	@Column(name="VT_IDTRANSACCION")
	private BigDecimal vtIdtransaccion;

	@Column(name="VT_MESDIVIDENDO")
	private Long vtMesdividendo;

	@Column(name="VT_NRO_DIVIDENDO")
	private BigDecimal vtNroDividendo;

	@Column(name="VT_VALOR")
	private BigDecimal vtValor;

	public Long getVtAniodividendo() {
		return this.vtAniodividendo;
	}

	public void setVtAniodividendo(Long vtAniodividendo) {
		this.vtAniodividendo = vtAniodividendo;
	}

	public String getVtConcepto() {
		return this.vtConcepto;
	}

	public void setVtConcepto(String vtConcepto) {
		this.vtConcepto = vtConcepto;
	}

	public Date getVtFecVencDivi() {
		return this.vtFecVencDivi;
	}

	public void setVtFecVencDivi(Date vtFecVencDivi) {
		this.vtFecVencDivi = vtFecVencDivi;
	}

	public Date getVtFechaproceso() {
		return this.vtFechaproceso;
	}

	public void setVtFechaproceso(Date vtFechaproceso) {
		this.vtFechaproceso = vtFechaproceso;
	}

	public BigDecimal getVtIdgaf() {
		return this.vtIdgaf;
	}

	public void setVtIdgaf(BigDecimal vtIdgaf) {
		this.vtIdgaf = vtIdgaf;
	}

	public BigDecimal getVtIdtipotransaccion() {
		return this.vtIdtipotransaccion;
	}

	public void setVtIdtipotransaccion(BigDecimal vtIdtipotransaccion) {
		this.vtIdtipotransaccion = vtIdtipotransaccion;
	}

	public BigDecimal getVtIdtransaccion() {
		return this.vtIdtransaccion;
	}

	public void setVtIdtransaccion(BigDecimal vtIdtransaccion) {
		this.vtIdtransaccion = vtIdtransaccion;
	}

	public Long getVtMesdividendo() {
		return this.vtMesdividendo;
	}

	public void setVtMesdividendo(Long vtMesdividendo) {
		this.vtMesdividendo = vtMesdividendo;
	}

	public BigDecimal getVtNroDividendo() {
		return this.vtNroDividendo;
	}

	public void setVtNroDividendo(BigDecimal vtNroDividendo) {
		this.vtNroDividendo = vtNroDividendo;
	}

	public BigDecimal getVtValor() {
		return this.vtValor;
	}

	public void setVtValor(BigDecimal vtValor) {
		this.vtValor = vtValor;
	}

}