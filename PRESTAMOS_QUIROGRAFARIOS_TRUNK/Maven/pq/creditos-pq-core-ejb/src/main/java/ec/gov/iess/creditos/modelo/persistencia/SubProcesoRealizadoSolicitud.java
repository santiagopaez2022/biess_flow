/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.dto.FechaVigencia;

/**
 * @author cvillarreal
 * 
 */
@Entity
@Table(name = "CRE_SUBPROCESOREALIZADO_TBL")
@SequenceGenerator(name = "secSubProcesoRealizadoSolicitud", sequenceName = "CRE_SUBPROCESOSREALIZADOS_SEQ", initialValue = 1, allocationSize = 1)
@NamedQueries( { @NamedQuery(name = "SubProcesoRealizadoSolicitud.findByCodtipsolserNumsolserSubprocesotiposol", query = "select o from SubProcesoRealizadoSolicitud o where o.idTipoSolicitud = :idTipoSolicitud and o.numeroSolicitud = :numeroSolicitud and o.subprocesoTipoSolicitud.id = :subprocesotiposol") })
public class SubProcesoRealizadoSolicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6643825939540890368L;

	@Id
	@Column(name = "ID_SUBPROCESOREALIZADO", nullable = false)
	@Basic(optional = false)
	@GeneratedValue(generator = "secSubProcesoRealizadoSolicitud", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "CODTIPSOLSER", nullable = false)
	@Basic(optional = false)
	private Long idTipoSolicitud;

	@Column(name = "NUMSOLSER", nullable = false)
	@Basic(optional = false)
	private Long numeroSolicitud;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_SUBPROTIPOSOL")
	private SubprocesoTipoSolicitud subprocesoTipoSolicitud;

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name = "fechaDesde", column = @Column(name = "FECHA_INI", nullable = false)),
			@AttributeOverride(name = "fechaHasta", column = @Column(name = "FECHA_FIN", nullable = false)) })
	private FechaVigencia fechaVigencia;

	@Column(name = "OBSERVACIONES", nullable = true)
	@Basic(optional = true)
	private String observaciones;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SECPRO")
	private ProcesoRealizado procesoRealizado;

	@Column(name = "FECHA_CAR")
	private Date fechaCarga;

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idTipoSolicitud
	 */
	public Long getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	/**
	 * @param idTipoSolicitud
	 *            the idTipoSolicitud to set
	 */
	public void setIdTipoSolicitud(Long idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	/**
	 * @return the numeroSolicitud
	 */
	public Long getNumeroSolicitud() {
		return numeroSolicitud;
	}

	/**
	 * @param numeroSolicitud
	 *            the numeroSolicitud to set
	 */
	public void setNumeroSolicitud(Long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	/**
	 * @return the subprocesoTipoSolicitud
	 */
	public SubprocesoTipoSolicitud getSubprocesoTipoSolicitud() {
		return subprocesoTipoSolicitud;
	}

	/**
	 * @param subprocesoTipoSolicitud
	 *            the subprocesoTipoSolicitud to set
	 */
	public void setSubprocesoTipoSolicitud(
			SubprocesoTipoSolicitud subprocesoTipoSolicitud) {
		this.subprocesoTipoSolicitud = subprocesoTipoSolicitud;
	}

	/**
	 * @return the fechaVigencia
	 */
	public FechaVigencia getFechaVigencia() {
		return fechaVigencia;
	}

	/**
	 * @param fechaVigencia
	 *            the fechaVigencia to set
	 */
	public void setFechaVigencia(FechaVigencia fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * 
	 */
	public SubProcesoRealizadoSolicitud() {
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the procesoRealizado
	 */
	public ProcesoRealizado getProcesoRealizado() {
		return procesoRealizado;
	}

	/**
	 * @param procesoRealizado
	 *            the procesoRealizado to set
	 */
	public void setProcesoRealizado(ProcesoRealizado procesoRealizado) {
		this.procesoRealizado = procesoRealizado;
	}

}
