/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase para la persistencia de las operaciones en la IFI que el usuario hace
 * la sustitución de hipoteca
 * 
 * @author jsanchez
 * 
 */
@Entity
@Table(name = "CRE_PO_OPERACIONESSUSTHIP_TBL")
@SequenceGenerator(name = "seqOperacionSustitucionHipoteca", sequenceName = "CRE_PO_OPERACIONESSUSTHIP_SEQ", allocationSize = 1, initialValue = 1)
@NamedQueries({
		@NamedQuery(name = "OperacionSustitucionHipoteca.obtenerPorSolicitud", query = "Select o from OperacionSustitucionHipoteca o "
				+ "where o.solicitudCredito.solicitudCreditoPK.numsolser=:numsolser "
				+ "and o.solicitudCredito.solicitudCreditoPK.codtipsolser=:codtipsolser order by o.codigo"),
		@NamedQuery(name = "OperacionSustitucionHipoteca.obtenerViviendaEnTramite", query = "Select o From OperacionSustitucionHipoteca o "
				+ " Where o.solicitudCredito.codestsolser not in (:estados) "
				+ "		and o.solicitudCredito.solicitudCreditoPK.codtipsolser in (57,58) "
				+ " 	and o.cedula=:cedula "
				+ "  	and o.codigoIfi=:codigoIfi") })
public class OperacionSustitucionHipoteca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 690495034227703754L;

	@Id
	@Column(name = "PS_CODIGO", nullable = false)
	@GeneratedValue(generator = "seqOperacionSustitucionHipoteca", strategy = GenerationType.SEQUENCE)
	private Long codigo;

	@Column(name = "PS_NUMERO_OPERACION", nullable = false)
	private String numeroOperacion;

	@Column(name = "PS_CODIGO_IFI", nullable = false)
	private Long codigoIfi;

	@Column(name = "PS_CEDULA", nullable = false)
	private String cedula;

	@Column(name = "PS_NOMBRE_IFI", nullable = false)
	private String nombreIFI;

	@Column(name = "PS_SALDO_HIPOTECARIO", nullable = false)
	private BigDecimal saldoHipotecario;

	@Column(name = "PS_CUOTA_ESTIMADA", nullable = false)
	private BigDecimal cuotaEstimada;

	@Column(name = "PS_TIPO_DEUDOR", nullable = false)
	private String tipoDeudor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "PS_CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "PS_NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	@Transient
	private String rucIfiHipoteca;

	@Transient
	private String cuentaIfiHipoteca;

	public OperacionSustitucionHipoteca() {

	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the numeroOperacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * @param numeroOperacion
	 *            the numeroOperacion to set
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * @return the codigoIfi
	 */
	public Long getCodigoIfi() {
		return codigoIfi;
	}

	/**
	 * @param codigoIfi
	 *            the codigoIfi to set
	 */
	public void setCodigoIfi(Long codigoIfi) {
		this.codigoIfi = codigoIfi;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the saldoHipotecario
	 */
	public BigDecimal getSaldoHipotecario() {
		return saldoHipotecario;
	}

	/**
	 * @param saldoHipotecario
	 *            the saldoHipotecario to set
	 */
	public void setSaldoHipotecario(BigDecimal saldoHipotecario) {
		this.saldoHipotecario = saldoHipotecario;
	}

	/**
	 * @return the cuotaEstimada
	 */
	public BigDecimal getCuotaEstimada() {
		return cuotaEstimada;
	}

	/**
	 * @param cuotaEstimada
	 *            the cuotaEstimada to set
	 */
	public void setCuotaEstimada(BigDecimal cuotaEstimada) {
		this.cuotaEstimada = cuotaEstimada;
	}

	/**
	 * @return the tipoDeudor
	 */
	public String getTipoDeudor() {
		return tipoDeudor;
	}

	/**
	 * @param tipoDeudor
	 *            the tipoDeudor to set
	 */
	public void setTipoDeudor(String tipoDeudor) {
		this.tipoDeudor = tipoDeudor;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param solicitudCredito
	 *            the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	/**
	 * @return the nombreIFI
	 */
	public String getNombreIFI() {
		return nombreIFI;
	}

	/**
	 * @param nombreIFI
	 *            the nombreIFI to set
	 */
	public void setNombreIFI(String nombreIFI) {
		this.nombreIFI = nombreIFI;
	}

	/**
	 * @return the rucIfiHipoteca
	 */
	public String getRucIfiHipoteca() {
		return rucIfiHipoteca;
	}

	/**
	 * @param rucIfiHipoteca
	 *            the rucIfiHipoteca to set
	 */
	public void setRucIfiHipoteca(String rucIfiHipoteca) {
		this.rucIfiHipoteca = rucIfiHipoteca;
	}

	/**
	 * @return the cuentaIfiHipoteca
	 */
	public String getCuentaIfiHipoteca() {
		return cuentaIfiHipoteca;
	}

	/**
	 * @param cuentaIfiHipoteca
	 *            the cuentaIfiHipoteca to set
	 */
	public void setCuentaIfiHipoteca(String cuentaIfiHipoteca) {
		this.cuentaIfiHipoteca = cuentaIfiHipoteca;
	}
}
