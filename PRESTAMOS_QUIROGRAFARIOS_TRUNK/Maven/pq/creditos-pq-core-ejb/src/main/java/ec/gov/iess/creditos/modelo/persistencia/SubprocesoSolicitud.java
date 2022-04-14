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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.EstadoGenerico;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_SUBPROCESOSIFI_TBL")
@SequenceGenerator(name="secSubprocesoSolicitud",sequenceName="CRE_SUBPROCESOSIFI_SEQ",initialValue=1,allocationSize=1)
public class SubprocesoSolicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6038786513037456228L;

	
	@Id
	@Column(name="ID_SUBPROCESOS",nullable=false)
	@Basic(optional=false)
	@GeneratedValue(generator="secSubprocesoSolicitud",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="DESCRIPCION_SUBPROCESOS",nullable=false)
	@Basic(optional=false)
	private String descripcion;
	
	@Column(name="ESTADO",nullable=false)
	@Basic(optional=false)
	@Enumerated(EnumType.STRING)
	private EstadoGenerico estado;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_HITOSCONTROL")
	private HitoControl hitoControl;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="subprocesoSolicitud",fetch=FetchType.EAGER)
	@OrderBy("id ASC")
	private List<SubprocesoTipoSolicitud> subprocesos;
	
	
	/**
	 * @return the subprocesos
	 */
	public List<SubprocesoTipoSolicitud> getSubprocesos() {
		return subprocesos;
	}



	/**
	 * @param subprocesos the subprocesos to set
	 */
	public void setSubprocesos(List<SubprocesoTipoSolicitud> subprocesos) {
		this.subprocesos = subprocesos;
	}



	/**
	 * 
	 */
	public SubprocesoSolicitud() {
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
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
	 * @param descripcion the descripcion to set
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
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoGenerico estado) {
		this.estado = estado;
	}



	/**
	 * @return the hitoControl
	 */
	public HitoControl getHitoControl() {
		return hitoControl;
	}



	/**
	 * @param hitoControl the hitoControl to set
	 */
	public void setHitoControl(HitoControl hitoControl) {
		this.hitoControl = hitoControl;
	}

	
	
}
