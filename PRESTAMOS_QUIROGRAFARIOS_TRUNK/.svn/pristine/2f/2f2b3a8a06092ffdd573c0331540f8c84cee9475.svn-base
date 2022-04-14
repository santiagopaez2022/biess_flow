package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author cvillarreal
 * 
 */

@Entity
@Table(name = "CRE_PROCESOSREALIZADOS_TBL")
@NamedQuery(name = "ProcesoRealizado.findbySolicitud", query = "SELECT o FROM ProcesoRealizado o WHERE o.solicitudCredito.solicitudCreditoPK.codtipsolser = :idTipoSolicitud "
		+ "AND o.solicitudCredito.solicitudCreditoPK.numsolser = :numeroSolicitud ORDER BY o.id")
@SequenceGenerator(name = "sqProcesoRealizado", sequenceName = "CRE_PROCESOSREALIZADOS_SEQ", initialValue = 1, allocationSize = 1)
public class ProcesoRealizado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqProcesoRealizado")
	@Column(name = "SECPRO")
	private Long id;

	@Column(name = "FECPRO")
	@Temporal(TemporalType.DATE)
	private Date fechaProceso;

	@Column(name = "USRRES")
	private String usuario;

	@Column(name = "OBSPRO")
	private String observacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODESTPRO")
	private EstadoProceso estadoProceso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "procesoRealizado", fetch = FetchType.EAGER)
	@OrderBy("id,fechaVigencia.fechaDesde ASC")
	private List<SubProcesoRealizadoSolicitud> subprocesos;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ProcesoRealizado() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public EstadoProceso getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(EstadoProceso estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	@Override
	public String toString() {
		StringBuffer msg = new StringBuffer();

		msg.append("tipo solicitud : "
				+ this.solicitudCredito.getSolicitudCreditoPK()
						.getCodtipsolser() + "\n");

		msg.append("Numero solicitud : "
				+ this.solicitudCredito.getSolicitudCreditoPK().getNumsolser()
				+ "\n");

		msg.append("fecha Proceso : " + this.fechaProceso + "\n");

		msg.append("Usuario : " + this.usuario + "\n");

		msg.append("Observaciones : " + this.observacion + "\n");

		msg.append("Estado : " + this.estadoProceso.getDescripcion() + "\n");

		// lista de subprocesos
		msg.append(" LISTA SUBPROCESO:\n");
		if (this.subprocesos == null){
			this.subprocesos = new ArrayList<SubProcesoRealizadoSolicitud>();
		}
		for (SubProcesoRealizadoSolicitud item : this.subprocesos) {
			msg.append("  "
					+ item.getSubprocesoTipoSolicitud()
							.getSubprocesoSolicitud().getDescripcion()
					+ "  OBS : " + item.getObservaciones() + " \n");
		}

		return msg.toString();
	}

	/**
	 * determina el ultimo subproceso realizado en este proceso
	 * siempre y cuando la realcion este anotada con OrderBy
	 * @return
	 */
	@Transient
	public SubProcesoRealizadoSolicitud getUltimoSubproceso() {

		if (null == getSubprocesos() && getSubprocesos().size() == 0) {
			return null;
		}
		return getSubprocesos().get(getSubprocesos().size()-1);
	}

	/**
	 * @return the subprocesos
	 */
	public List<SubProcesoRealizadoSolicitud> getSubprocesos() {
		return subprocesos;
	}

	/**
	 * @param subprocesos
	 *            the subprocesos to set
	 */
	public void setSubprocesos(List<SubProcesoRealizadoSolicitud> subprocesos) {
		this.subprocesos = subprocesos;
	}

}
