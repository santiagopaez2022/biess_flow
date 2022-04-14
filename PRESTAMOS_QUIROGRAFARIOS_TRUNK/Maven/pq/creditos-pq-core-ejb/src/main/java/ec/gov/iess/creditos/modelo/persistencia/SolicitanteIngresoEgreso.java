/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.TipoIngresoEgreso;

/**
 * 
 * Detalle los ingresos y egresos del solicitante
 * 
 * @author cvillarreal
 * @version 1.0
 * 
 */
@Entity
@Table(name = "CRE_DETALLEINGEGR_TBL")
@NamedQueries( { @NamedQuery(name = "IngresoEgresoSolicitante.findAll", query = "SELECT o FROM SolicitanteIngresoEgreso o") })
@SequenceGenerator(name = "CRE_DETALLEINGEGR_SEQ", sequenceName = "CRE_DETALLEINGEGR_SEQ", allocationSize = 1)
public class SolicitanteIngresoEgreso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5986587815986300032L;

	@Id
	@Column(name = "CODDETIENEGR", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_DETALLEINGEGR_SEQ")
	private long codIngEgr;

	@Column(name = "VALINGEGR")
	private BigDecimal valor;

	@Column(name = "DESINGEGR")
	private String descingegr;

	@Column(name = "TIPOREGINGEGR")
	@Enumerated(EnumType.STRING)
	private TipoIngresoEgreso tipoIngresoEgreso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODSOL", referencedColumnName = "CODSOL")
	private SolicitanteCredito solicitanteCredito;

	/**
	 * 
	 */
	public SolicitanteIngresoEgreso() {
	}

	public SolicitanteIngresoEgreso(TipoIngresoEgreso tipoIngresoEgreso,
			BigDecimal valor, String desc, SolicitanteCredito solicitanteCredito) {
		this.valor = valor;
		this.tipoIngresoEgreso = tipoIngresoEgreso;
		this.descingegr = desc;
		this.solicitanteCredito = solicitanteCredito;
	}

	/**
	 * @return the codIngEgr
	 */
	public Long getCodIngEgr() {
		return codIngEgr;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @return the descingegr
	 */
	public String getDescingegr() {
		return descingegr;
	}

	/**
	 * @param codIngEgr
	 *            the codIngEgr to set
	 */
	public void setCodIngEgr(Long codIngEgr) {
		this.codIngEgr = codIngEgr;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @param descingegr
	 *            the descingegr to set
	 */
	public void setDescingegr(String descingegr) {
		this.descingegr = descingegr;
	}

	/**
	 * @return the solicitanteCredito
	 */
	public SolicitanteCredito getSolicitanteCredito() {
		return solicitanteCredito;
	}

	/**
	 * @param solicitanteCredito
	 *            the solicitanteCredito to set
	 */
	public void setSolicitanteCredito(SolicitanteCredito solicitanteCredito) {
		this.solicitanteCredito = solicitanteCredito;
	}

	public TipoIngresoEgreso getTipoIngresoEgreso() {
		return tipoIngresoEgreso;
	}

	public void setTipoIngresoEgreso(TipoIngresoEgreso tipoIngresoEgreso) {
		this.tipoIngresoEgreso = tipoIngresoEgreso;
	}

	public void setCodIngEgr(long codIngEgr) {
		this.codIngEgr = codIngEgr;
	}

}
