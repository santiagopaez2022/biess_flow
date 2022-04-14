/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author walter.meza
 * 
 */

@Entity
@Table(name = "CRE_OPERACIONESCAN_TBL")
@NamedQueries({ @NamedQuery(name = "OperacionesCanceladas.buscarPorNutCancelacion", query = "SELECT o FROM OperacionesCanceladas o WHERE o.nutOperacionCanc = :nutOperacionCanc")

})
public class OperacionesCanceladas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5538992217427030440L;

	@Id
	@Column(name = "OC_CODDETSOL")
	private Long coddetsol;

	@OneToOne(mappedBy = "operacionCancelada", optional = false)
	@PrimaryKeyJoinColumn
	private DetalleSolicitud detalleSolicitudCredito;

	@Column(name = "OC_NUTOPERACION", nullable = false)
	private Long nutOperacionCanc;

	@Column(name = "OC_VALOROPERACION", precision = 2, nullable = false)
	private BigDecimal valorOperacionCanc;

	@Column(name = "OC_VALORCAPITAL", precision = 2, nullable = false)
	private BigDecimal valorCapitalCanc;

	@Column(name = "OC_VALORINTERES", precision = 2, nullable = false)
	private BigDecimal valorInteresCanc;

	@Column(name = "OC_VALORSEGINC", nullable = false)
	private BigDecimal valorSeguroIncCanc;

	@Column(name = "OC_VALORSEGDES", nullable = false)
	private BigDecimal valorSeguroDesCanc;

	@Column(name = "OC_VALORINTMORA", nullable = true)
	private BigDecimal valorInteresMora;

	@Column(name = "OC_FECHACAN", nullable = false)
	private Date fechaCancelacion;

	@Column(name = "OC_NUTDEPSPL", nullable = true)
	private String nupDepSpl;
	
	@Column(name = "OC_VALORIMPUESTOS", nullable = true)
	private BigDecimal valorImpuestos;
	
	@Column(name = "OC_VALORSEGVIDA", nullable = true)
	private BigDecimal valorSeguroVida;

	/**
	 * 
	 */
	public OperacionesCanceladas() {
		// TODO:
	}

	/**
	 * @param detalleSolicitudCredito
	 *            the detalleSolicitudCredito to set
	 */
	public void setDetalleSolicitudCredito(
			DetalleSolicitud detalleSolicitudCredito) {
		this.detalleSolicitudCredito = detalleSolicitudCredito;
	}

	/**
	 * @return the detalleSolicitudCredito
	 */
	public DetalleSolicitud getDetalleSolicitudCredito() {
		return detalleSolicitudCredito;
	}

	/**
	 * @param nutOperacionCanc
	 *            the nutOperacionCanc to set
	 */
	public void setNutOperacionCanc(Long nutOperacionCanc) {
		this.nutOperacionCanc = nutOperacionCanc;
	}

	/**
	 * @return the nutOperacionCanc
	 */
	public Long getNutOperacionCanc() {
		return nutOperacionCanc;
	}

	/**
	 * @return the valorOperacionCanc
	 */
	public BigDecimal getValorOperacionCanc() {
		return valorOperacionCanc;
	}

	/**
	 * @param valorOperacionCanc
	 *            the valorOperacionCanc to set
	 */
	public void setValorOperacionCanc(BigDecimal valorOperacionCanc) {
		this.valorOperacionCanc = valorOperacionCanc;
	}

	/**
	 * @param valorCapitalCanc
	 *            the valorCapitalCanc to set
	 */
	public void setValorCapitalCanc(BigDecimal valorCapitalCanc) {
		this.valorCapitalCanc = valorCapitalCanc;
	}

	/**
	 * @return the valorCapitalCanc
	 */
	public BigDecimal getValorCapitalCanc() {
		return valorCapitalCanc;
	}

	/**
	 * @param valorInteresCanc
	 *            the valorInteresCanc to set
	 */
	public void setValorInteresCanc(BigDecimal valorInteresCanc) {
		this.valorInteresCanc = valorInteresCanc;
	}

	/**
	 * @return the valorInteresCanc
	 */
	public BigDecimal getValorInteresCanc() {
		return valorInteresCanc;
	}

	/**
	 * @param valorSeguroIncCanc
	 *            the valorSeguroIncCanc to set
	 */
	public void setValorSeguroIncCanc(BigDecimal valorSeguroIncCanc) {
		this.valorSeguroIncCanc = valorSeguroIncCanc;
	}

	/**
	 * @return the valorSeguroIncCanc
	 */
	public BigDecimal getValorSeguroIncCanc() {
		return valorSeguroIncCanc;
	}

	/**
	 * @param valorSeguroDesCanc
	 *            the valorSeguroDesCanc to set
	 */
	public void setValorSeguroDesCanc(BigDecimal valorSeguroDesCanc) {
		this.valorSeguroDesCanc = valorSeguroDesCanc;
	}

	/**
	 * @return the valorSeguroDesCanc
	 */
	public BigDecimal getValorSeguroDesCanc() {
		return valorSeguroDesCanc;
	}

	/**
	 * @param valorInteresMora
	 *            the valorInteresMora to set
	 */
	public void setValorInteresMora(BigDecimal valorInteresMora) {
		this.valorInteresMora = valorInteresMora;
	}

	/**
	 * @return the valorInteresMora
	 */
	public BigDecimal getValorInteresMora() {
		return valorInteresMora;
	}

	/**
	 * @param fechaCancelacion
	 *            the fechaCancelacion to set
	 */
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	/**
	 * @return the fechaCancelacion
	 */
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	/**
	 * @return the nupDepSpl
	 */
	public String getNupDepSpl() {
		return nupDepSpl;
	}

	/**
	 * @param nupDepSpl
	 *            the nupDepSpl to set
	 */
	public void setNupDepSpl(String nupDepSpl) {
		this.nupDepSpl = nupDepSpl;
	}

	/**
	 * @return the coddetsol
	 */
	public Long getCoddetsol() {
		return coddetsol;
	}

	/**
	 * @param coddetsol
	 *            the coddetsol to set
	 */
	public void setCoddetsol(Long coddetsol) {
		this.coddetsol = coddetsol;
	}

	/**
	 * @return the valorImpuestos
	 */
	public BigDecimal getValorImpuestos() {
		return valorImpuestos;
	}

	/**
	 * @param valorImpuestos the valorImpuestos to set
	 */
	public void setValorImpuestos(BigDecimal valorImpuestos) {
		this.valorImpuestos = valorImpuestos;
	}

	/**
	 * @return the valorGastos
	 */
	public BigDecimal getValorSeguroVida() {
		return valorSeguroVida;
	}

	/**
	 * @param valorGastos the valorGastos to set
	 */
	public void setValorSeguroVida(BigDecimal valorGastos) {
		this.valorSeguroVida = valorGastos;
	}

}
