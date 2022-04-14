/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.PermisoAccion;
import ec.gov.iess.creditos.enumeracion.RolSolicitante;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_PROCESOSPERMISOS_TBL")
@NamedQueries({
	@NamedQuery(name="PermisoProceso.findByPersmisoAccion",
			query="SELECT o FROM PermisoProceso o WHERE " +
					"o.tipoAccion.id = :idAccion " +
					"AND o.estadoProceso.id = :idEstado " +
					"AND o.rolSolicitante = :rol " +
					"AND o.estadoProceso.codigoTipoSolicitud = :tipoSolicitud " +
					"AND o.idTipoSolciitud = :tipoSolicitud2")
})
public class PermisoProceso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CODPROROL",nullable=false)
	private Long id;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="ROL",nullable=false)
	private RolSolicitante rolSolicitante;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="ACCION",nullable=false)
	private PermisoAccion permisoAccion;
	
	@Column(name="CODTIPSOLSER")
	private Long idTipoSolciitud;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CODTIPACC")
	private TipoAccion tipoAccion;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="CODESTPRO",referencedColumnName="CODESTPRO")
	private EstadoProceso estadoProceso;
	
	

	/**
	 * 
	 */
	public PermisoProceso() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public RolSolicitante getRolSolicitante() {
		return rolSolicitante;
	}



	public void setRolSolicitante(RolSolicitante rolSolicitante) {
		this.rolSolicitante = rolSolicitante;
	}



	public PermisoAccion getPermisoAccion() {
		return permisoAccion;
	}



	public void setPermisoAccion(PermisoAccion permisoAccion) {
		this.permisoAccion = permisoAccion;
	}



	public TipoAccion getTipoAccion() {
		return tipoAccion;
	}



	public void setTipoAccion(TipoAccion tipoAccion) {
		this.tipoAccion = tipoAccion;
	}



	public EstadoProceso getEstadoProceso() {
		return estadoProceso;
	}



	public void setEstadoProceso(EstadoProceso estadoProceso) {
		this.estadoProceso = estadoProceso;
	}



	/**
	 * @return the idTipoSolciitud
	 */
	public Long getIdTipoSolciitud() {
		return idTipoSolciitud;
	}



	/**
	 * @param idTipoSolciitud the idTipoSolciitud to set
	 */
	public void setIdTipoSolciitud(Long idTipoSolciitud) {
		this.idTipoSolciitud = idTipoSolciitud;
	}


}
