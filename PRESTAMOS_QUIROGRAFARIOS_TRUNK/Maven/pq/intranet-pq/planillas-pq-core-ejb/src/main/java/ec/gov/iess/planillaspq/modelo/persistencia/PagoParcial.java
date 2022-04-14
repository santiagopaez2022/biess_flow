package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.PagoParcialPK;

@Entity
@Table(name="REC_PAGOPARCIAL_TBL")
@NamedQueries({
	@NamedQuery(name = "PagoParcial.BuscarPorComprobante", 
		    query = "select o from PagoParcial o where o.pk.codtipcompag = :codtipcompag " +
		    		"and o.pk.codcompag = :codcompag")
})
public class PagoParcial implements Serializable {
	@EmbeddedId
	private PagoParcialPK pk;

	@Column(name="PP_FECHA")
	private Date ppFecha;

	@Column(name="PP_VALOR_PAGO_PARCIAL")
	private BigDecimal ppValorPagoParcial;

	@Column(name="PP_VALOR_INTERES")
	private BigDecimal ppValorInteres;

	@Column(name="PP_VALOR_DIFERENCIA_VENCIDA")
	private BigDecimal ppValorDiferenciaVencida;

	@Column(name="PP_ANIO")
	private BigDecimal ppAnio;

	@Column(name="PP_MES")
	private BigDecimal ppMes;

	@Column(name="PP_RUC")
	private String ppRuc;

	@Column(name="PP_IDREF")
	private BigDecimal ppIdref;

	private static final long serialVersionUID = 1L;

	public PagoParcial() {
		super();
	}

	public PagoParcialPK getPk() {
		return this.pk;
	}

	public void setPk(PagoParcialPK pk) {
		this.pk = pk;
	}

	public Date getPpFecha() {
		return this.ppFecha;
	}

	public void setPpFecha(Date ppFecha) {
		this.ppFecha = ppFecha;
	}

	public BigDecimal getPpValorPagoParcial() {
		return this.ppValorPagoParcial;
	}

	public void setPpValorPagoParcial(BigDecimal ppValorPagoParcial) {
		this.ppValorPagoParcial = ppValorPagoParcial;
	}

	public BigDecimal getPpValorInteres() {
		return this.ppValorInteres;
	}

	public void setPpValorInteres(BigDecimal ppValorInteres) {
		this.ppValorInteres = ppValorInteres;
	}

	public BigDecimal getPpValorDiferenciaVencida() {
		return this.ppValorDiferenciaVencida;
	}

	public void setPpValorDiferenciaVencida(BigDecimal ppValorDiferenciaVencida) {
		this.ppValorDiferenciaVencida = ppValorDiferenciaVencida;
	}

	public BigDecimal getPpAnio() {
		return this.ppAnio;
	}

	public void setPpAnio(BigDecimal ppAnio) {
		this.ppAnio = ppAnio;
	}

	public BigDecimal getPpMes() {
		return this.ppMes;
	}

	public void setPpMes(BigDecimal ppMes) {
		this.ppMes = ppMes;
	}

	public String getPpRuc() {
		return this.ppRuc;
	}

	public void setPpRuc(String ppRuc) {
		this.ppRuc = ppRuc;
	}

	public BigDecimal getPpIdref() {
		return this.ppIdref;
	}

	public void setPpIdref(BigDecimal ppIdref) {
		this.ppIdref = ppIdref;
	}

}
