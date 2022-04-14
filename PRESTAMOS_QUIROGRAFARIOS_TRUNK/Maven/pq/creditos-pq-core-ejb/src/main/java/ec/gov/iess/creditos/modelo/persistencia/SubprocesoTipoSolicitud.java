/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author cvillarreal
 * 
 */
@Entity
@Table(name = "CRE_SUPROCESOTIPOSOL_TBL")
@SequenceGenerator(name = "secSubprocesoTipoSolicitud", sequenceName = "CRE_SUBPROCESOSTIPOSOL_SEQ", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name ="SubprocesoTipoSolicitud.findByTipoSolicitudSubproceso", query = "select o from SubprocesoTipoSolicitud o where o.idTipoSolicitud = :idTipoSolicitud and o.subprocesoSolicitud.id = :subprocesoSolicitud")
})
public class SubprocesoTipoSolicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2147316058077008708L;

	@Id
	@Column(name = "ID_SUBPROTIPOSOL", nullable = false)
	@Basic(optional = false)
	@GeneratedValue(generator = "secSubprocesoTipoSolicitud", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "CODTIPSOLSER", nullable = false)
	@Basic(optional = false)
	private Long idTipoSolicitud;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_SUBPROCESO", nullable = false)
	private SubprocesoSolicitud subprocesoSolicitud;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "subprocesoTipoSolicitud")
	@OrderBy("fechaVigencia.fechaDesde ASC")
	private List<SubProcesoRealizadoSolicitud> subprocesosRealizados;

	/**
	 * 
	 */
	public SubprocesoTipoSolicitud() {
	}

	/**
	 * @return the subprocesosRealizados
	 */
	public List<SubProcesoRealizadoSolicitud> getSubprocesosRealizados() {
		return subprocesosRealizados;
	}

	/**
	 * @param subprocesosRealizados
	 *            the subprocesosRealizados to set
	 */
	public void setSubprocesosRealizados(
			List<SubProcesoRealizadoSolicitud> subprocesosRealizados) {
		this.subprocesosRealizados = subprocesosRealizados;
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
	 * @return the subprocesoSolicitud
	 */
	public SubprocesoSolicitud getSubprocesoSolicitud() {
		return subprocesoSolicitud;
	}

	/**
	 * @param subprocesoSolicitud
	 *            the subprocesoSolicitud to set
	 */
	public void setSubprocesoSolicitud(SubprocesoSolicitud subprocesoSolicitud) {
		this.subprocesoSolicitud = subprocesoSolicitud;
	}

}
