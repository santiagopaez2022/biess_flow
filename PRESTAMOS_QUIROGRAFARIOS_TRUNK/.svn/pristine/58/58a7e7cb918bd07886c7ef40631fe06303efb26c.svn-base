/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author walter.meza
 * 
 */

@Entity
@Table(name = "CRE_DETALLESOLICITUDIFI_TBL")
public class DetalleSolicitudIfi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7972167382972645396L;

	@Id
	@Column(name = "DI_CODDETSOL")
	private Long coddetsol;

	@OneToOne(mappedBy = "detalleSolicitudIfi", optional = false)
	@PrimaryKeyJoinColumn
	private DetalleSolicitud detalleSolicitudCredito;

	@Column(name = "DI_VALDESEMIFI", precision = 2, nullable = false)
	private BigDecimal valorDesembolsoIfi;

	@Column(name = "DI_NUMCTAIFI", nullable = false)
	private String numeroCuentaIfi;

	@Column(name = "DI_RUCIFI", nullable = false)
	private String rucIfi;

	@Column(name = "DI_TIPGARANTIAIFI", nullable = false)
	private String tipoGarantiaIfi;

	@Column(name = "DI_TIPCTAIFI", nullable = false)
	private String tipoCuentaIfi;

	@Column(name = "DI_NUTDEPSPL", nullable = true)
	private String nupDepSpl;

	/**
	 * 
	 */
	public DetalleSolicitudIfi() {
	}

	/**
	 * @param codIfi
	 *            the codIfi to set
	 */
	public void setCoddetsol(Long codIfi) {
		this.coddetsol = codIfi;
	}

	/**
	 * @return the codIfi
	 */
	public Long getCoddetsol() {
		return coddetsol;
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
	 * @param valorDesembolsoIfi
	 *            the valorDesembolsoIfi to set
	 */
	public void setValorDesembolsoIfi(BigDecimal valorDesembolsoIfi) {
		this.valorDesembolsoIfi = valorDesembolsoIfi;
	}

	/**
	 * @return the valorDesembolsoIfi
	 */
	public BigDecimal getValorDesembolsoIfi() {
		return valorDesembolsoIfi;
	}

	/**
	 * @param numeroCuentaIfi
	 *            the numeroCuentaIfi to set
	 */
	public void setNumeroCuentaIfi(String numeroCuentaIfi) {
		this.numeroCuentaIfi = numeroCuentaIfi;
	}

	/**
	 * @return the numeroCuentaIfi
	 */
	public String getNumeroCuentaIfi() {
		return numeroCuentaIfi;
	}

	/**
	 * @param rucIfi
	 *            the rucIfi to set
	 */
	public void setRucIfi(String rucIfi) {
		this.rucIfi = rucIfi;
	}

	/**
	 * @return the rucIfi
	 */
	public String getRucIfi() {
		return rucIfi;
	}

	/**
	 * @param tipoGarantiaIfi
	 *            the tipoGarantiaIfi to set
	 */
	public void setTipoGarantiaIfi(String tipoGarantiaIfi) {
		this.tipoGarantiaIfi = tipoGarantiaIfi;
	}

	/**
	 * @return the tipoGarantiaIfi
	 */
	public String getTipoGarantiaIfi() {
		return tipoGarantiaIfi;
	}

	/**
	 * @param tipoCuentaIfi
	 *            the tipoCuentaIfi to set
	 */
	public void setTipoCuentaIfi(String tipoCuentaIfi) {
		this.tipoCuentaIfi = tipoCuentaIfi;
	}

	/**
	 * @return the tipoCuentaIfi
	 */
	public String getTipoCuentaIfi() {
		return tipoCuentaIfi;
	}

	/**
	 * @param nupDepSpl
	 *            the nupDepSpl to set
	 */
	public void setNupDepSpl(String nupDepSpl) {
		this.nupDepSpl = nupDepSpl;
	}

	/**
	 * @return the nupDepSpl
	 */
	public String getNupDepSpl() {
		return nupDepSpl;
	}

}
