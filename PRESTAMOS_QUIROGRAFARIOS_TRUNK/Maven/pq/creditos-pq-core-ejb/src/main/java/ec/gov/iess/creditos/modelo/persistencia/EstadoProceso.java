/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author cvillarreal
 * 
 */
@Entity
@Table(name = "CRE_ESTADOPROCESOS_TBL")
@NamedQueries( { @NamedQuery(name = "EstadoProceso.fyndEstadoByTipoSolicitud", 
			query = "SELECT o FROM EstadoProceso o WHERE "
			+ "o.codigoTipoSolicitud = :tipoSolicitud AND o.orden = :orden"),
			
		@NamedQuery(name = "EstadoProceso.fyndEntreEstadosByTipoSolicitud", 
			query = "SELECT o FROM EstadoProceso o WHERE "
			+ "o.codigoTipoSolicitud = :tipoSolicitud AND o.orden BETWEEN :orden1 AND :orden2 order by o.orden")
})
public class EstadoProceso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODESTPRO")
	private Long id;
	
	@Column(name="CODTIPSOLSER")
	private Long codigoTipoSolicitud;

	@Column(name = "DESESTPRO")
	private String descripcion;

	@Column(name = "ORDEN", nullable = false)
	private Integer orden;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CODPRE",referencedColumnName="CODPRE")
	private EstadosPrestamoProceso estadosPrestamoProceso;


	/**
	 * 
	 */
	public EstadoProceso() {
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
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}


	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}


	/**
	 * @return the estadosPrestamoProceso
	 */
	public EstadosPrestamoProceso getEstadosPrestamoProceso() {
		return estadosPrestamoProceso;
	}


	/**
	 * @param estadosPrestamoProceso the estadosPrestamoProceso to set
	 */
	public void setEstadosPrestamoProceso(
			EstadosPrestamoProceso estadosPrestamoProceso) {
		this.estadosPrestamoProceso = estadosPrestamoProceso;
	}


	/**
	 * @return the codigoTipoSolicitud
	 */
	public Long getCodigoTipoSolicitud() {
		return codigoTipoSolicitud;
	}


	/**
	 * @param codigoTipoSolicitud the codigoTipoSolicitud to set
	 */
	public void setCodigoTipoSolicitud(Long codigoTipoSolicitud) {
		this.codigoTipoSolicitud = codigoTipoSolicitud;
	}

}