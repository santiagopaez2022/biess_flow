package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PK para la entidad RolPeriodoDetalle
 * 
 * @author christian.gomez
 * 
 */
@Embeddable
public class RolPeriodoDetallePk implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 899715448926456L;

	@Column(name = "ID_DETALLE_ROL")
	private Long idDetalleRol;

	@Column(name = "RUBRO")
	private Long rubro;

	@Column(name = "IDENTIFICACION_BENEFICIARIO")
	private String identiBeneficiario;

	@Column(name = "SECUENCIAL_PRESTACION")
	private Long secuencialPrestacion;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "ANO")
	private Long anio;

	@Column(name = "MES")
	private Long mes;

	/**
	 * Constructor.
	 */
	public RolPeriodoDetallePk() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param idDetalleRol
	 * @param rubro
	 * @param identiBeneficario
	 * @param secuencialPrestacion
	 * @param tipo
	 * @param anio
	 * @param mes
	 */
	public RolPeriodoDetallePk(Long idDetalleRol, Long rubro,
			String identiBeneficario, Long secuencialPrestacion, String tipo,
			Long anio, Long mes) {
		this.idDetalleRol = idDetalleRol;
		this.rubro = rubro;
		this.identiBeneficiario = identiBeneficario;
		this.secuencialPrestacion = secuencialPrestacion;
		this.tipo = tipo;
		this.anio = anio;
		this.mes = mes;
	}

	/**
	 * @return the idDetalleRol
	 */
	public Long getIdDetalleRol() {
		return idDetalleRol;
	}

	/**
	 * @param idDetalleRol
	 *            the idDetalleRol to set
	 */
	public void setIdDetalleRol(Long idDetalleRol) {
		this.idDetalleRol = idDetalleRol;
	}

	/**
	 * @return the rubro
	 */
	public Long getRubro() {
		return rubro;
	}

	/**
	 * @param rubro
	 *            the rubro to set
	 */
	public void setRubro(Long rubro) {
		this.rubro = rubro;
	}

	/**
	 * @return the identiBeneficiario
	 */
	public String getIdentiBeneficiario() {
		return identiBeneficiario;
	}

	/**
	 * @param identiBeneficiario
	 *            the identiBeneficiario to set
	 */
	public void setIdentiBeneficiario(String identiBeneficiario) {
		this.identiBeneficiario = identiBeneficiario;
	}

	/**
	 * @return the secuencialPrestacion
	 */
	public Long getSecuencialPrestacion() {
		return secuencialPrestacion;
	}

	/**
	 * @param secuencialPrestacion
	 *            the secuencialPrestacion to set
	 */
	public void setSecuencialPrestacion(Long secuencialPrestacion) {
		this.secuencialPrestacion = secuencialPrestacion;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the anio
	 */
	public Long getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(Long anio) {
		this.anio = anio;
	}

	/**
	 * @return the mes
	 */
	public Long getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(Long mes) {
		this.mes = mes;
	}

}
