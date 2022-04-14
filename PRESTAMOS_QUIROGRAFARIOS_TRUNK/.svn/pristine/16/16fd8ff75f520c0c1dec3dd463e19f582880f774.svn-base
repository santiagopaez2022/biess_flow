/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author cvillarreal
 *
 */
@Entity
@Table(name="CRE_NOVEDADANULACION_TBL")
@SequenceGenerator(name="secNovedadAnulacion",sequenceName="CRE_NOVEDADANULACION_SEQ",initialValue=1,allocationSize=1)
public class NovedadAnulacion implements Serializable {

	
	private static final long serialVersionUID = -6267712010831147782L;
	
	@Id
	@Column(name="SECANU",nullable=false)
	@Basic(optional=false)
	@GeneratedValue(generator="secNovedadAnulacion",strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="NUMSOLSER",referencedColumnName="NUMSOLSER"),
		@JoinColumn(name="CODTIPSOLSER",referencedColumnName="CODTIPSOLSER")
	})
	private SolicitudCredito solicitudCredito;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_HITOSCONTROL")
	private HitoControl hitoControl;
	
	@Column(name="OBSANU",nullable=true)
	@Basic(optional=true)
	private String observacion;
	
	@Column(name="COSOPE",nullable=false)
	@Basic(optional=false)
	private BigDecimal costoOperativo = BigDecimal.valueOf(0);
	
	@Column(name="COSVAR",nullable=false)
	@Basic(optional=false)
	private BigDecimal costoVarios = BigDecimal.valueOf(0);
	
	@Column(name="COSTOT",nullable=false)
	@Basic(optional=false)
	private BigDecimal costoTotal = BigDecimal.valueOf(0);
	
	@Column(name="FECANU",nullable=false)
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAnulacion ;
	
	@Column(name="USRANU",nullable=false)
	@Basic(optional=false)
	private String usuario;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_SUBPROTIPOSOL")
	private SubprocesoTipoSolicitud subprocesoTipoSolicitud;
	
	@OneToMany(mappedBy="novedadAnulacion",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private List<DetalleAnulacion> detalleAnulacion;



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
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}



	/**
	 * @param solicitudCredito the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
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



	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}



	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	/**
	 * @return the costoOperativo
	 */
	public BigDecimal getCostoOperativo() {
		return costoOperativo;
	}



	/**
	 * @param costoOperativo the costoOperativo to set
	 */
	public void setCostoOperativo(BigDecimal costoOperativo) {
		this.costoOperativo = costoOperativo;
	}



	/**
	 * @return the costoVarios
	 */
	public BigDecimal getCostoVarios() {
		return costoVarios;
	}



	/**
	 * @param costoVarios the costoVarios to set
	 */
	public void setCostoVarios(BigDecimal costoVarios) {
		this.costoVarios = costoVarios;
	}



	/**
	 * @return the costoTotal
	 */
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}



	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}



	/**
	 * @return the fechaAnulacion
	 */
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}



	/**
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}



	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}



	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public NovedadAnulacion(){
		
	}



	/**
	 * @return the subprocesoTipoSolicitud
	 */
	public SubprocesoTipoSolicitud getSubprocesoTipoSolicitud() {
		return subprocesoTipoSolicitud;
	}



	/**
	 * @param subprocesoTipoSolicitud the subprocesoTipoSolicitud to set
	 */
	public void setSubprocesoTipoSolicitud(
			SubprocesoTipoSolicitud subprocesoTipoSolicitud) {
		this.subprocesoTipoSolicitud = subprocesoTipoSolicitud;
	}



	/**
	 * @return the detalleAnulacion
	 */
	public List<DetalleAnulacion> getDetalleAnulacion() {
		return detalleAnulacion;
	}



	/**
	 * @param detalleAnulacion the detalleAnulacion to set
	 */
	public void setDetalleAnulacion(List<DetalleAnulacion> detalleAnulacion) {
		this.detalleAnulacion = detalleAnulacion;
	}

}
