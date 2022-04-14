/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.EstadoGenerico;
import ec.gov.iess.creditos.modelo.dto.FechaVigencia;

/**
 * @author cvillarreal
 */
@Entity
@Table(name = "CRE_HITOSCONTROL_TBL")
@SequenceGenerator(name = "secHitoControl", sequenceName = "CRE_HITOSCONTROL_SEQ", initialValue = 1, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name="HitoControl.byTipoSolicitud",query="SELECT DISTINCT o FROM HitoControl o, IN (o.subprocesos) sp, " +
			"IN (sp.subprocesos) spts " +
			"WHERE spts.idTipoSolicitud=:idTipoSolicitud AND o.estado='ACT' ORDER BY o.id"),
			
	@NamedQuery(name="HitoControl.fyndCostoActualById",
			query="SELECT o FROM HitoControl o, IN (o.hitoCostos) cost " +
				"WHERE o.id=:idHito AND  :fecha BETWEEN cost.fechaVigencia.fechaDesde AND cost.fechaVigencia.fechaHasta"),
				
	@NamedQuery(name = "HitoControl.byTipoProducto", query = "SELECT DISTINCT o FROM HitoControl o  "
			+ "WHERE o.tipoServicio =:idTipoSolicitud and o.estado ='ACT' ORDER BY o.id")
})
public class HitoControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2472675780293085935L;

	@Id
	@Column(name = "ID_HITOSCONTROL", nullable = false)
	@Basic(optional = false)
	@GeneratedValue(generator = "secHitoControl", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "DESCRIPCION_HITO", nullable = false)
	@Basic(optional = false)
	private String descripcion;

	@Column(name = "ESTADO_HITO", nullable = false)
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private EstadoGenerico estado;
	
	@Column(name = "CODTIPSOLSER", nullable = false)
	@Basic(optional = true)
	private Long tipoServicio;
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "hitoControl")
	@OrderBy("id ASC")
	private List<HitoDetalle> hitoDetalles;
	
	

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name = "fechaDesde", 
					column = @Column(name = "FEC_DESDE", nullable = false)),
			@AttributeOverride(name = "fechaHasta", 
					column = @Column(name = "FEC_HASTA", nullable = false))})
	private FechaVigencia fechaVigencia;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "hitoControl")
	@OrderBy("fechaVigencia.fechaDesde ASC")
	private List<HitoCosto> hitoCostos;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "hitoControl")
	@OrderBy("id ASC")
	private List<SubprocesoSolicitud> subprocesos;

	/**
	 * @return the subprocesos
	 */
	public List<SubprocesoSolicitud> getSubprocesos() {
		return subprocesos;
	}

	/**
	 * @param subprocesos
	 *            the subprocesos to set
	 */
	public void setSubprocesos(List<SubprocesoSolicitud> subprocesos) {
		this.subprocesos = subprocesos;
	}

	/**
	 * 
	 */
	public HitoControl() {
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the estado
	 */
	public EstadoGenerico getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(EstadoGenerico estado) {
		this.estado = estado;
	}

	/**
	 * @return the hitoCostos
	 */
	public List<HitoCosto> getHitoCostos() {
		return hitoCostos;
	}

	/**
	 * @param hitoCostos
	 *            the hitoCostos to set
	 */
	public void setHitoCostos(List<HitoCosto> hitoCostos) {
		this.hitoCostos = hitoCostos;
	}

	/**
	 * @return the fechaVigencia
	 */
	public FechaVigencia getFechaVigencia() {
		return fechaVigencia;
	}

	/**
	 * @param fechaVigencia the fechaVigencia to set
	 */
	public void setFechaVigencia(FechaVigencia fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * @return the tipoServicio
	 */
	public Long getTipoServicio() {
		return tipoServicio;
	}

	/**
	 * @param tipoServicio the tipoServicio to set
	 */
	public void setTipoServicio(Long tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	/**
	 * @return the hitoDetalles
	 */
	public List<HitoDetalle> getHitoDetalles() {
		return hitoDetalles;
	}

	/**
	 * @param hitoDetalles the hitoDetalles to set
	 */
	public void setHitoDetalles(List<HitoDetalle> hitoDetalles) {
		this.hitoDetalles = hitoDetalles;
	}

}
