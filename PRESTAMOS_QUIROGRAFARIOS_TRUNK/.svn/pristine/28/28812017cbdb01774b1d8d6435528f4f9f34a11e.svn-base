package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Tabla para periodos de generacion de comprobantes y liquidacion de creditos
 * 
 * @author hugo.mora
 * @date 2016/08/03
 *
 */
@Entity
@Table(name = "cre_periodocomprobante_tbl")
@NamedQueries({ @NamedQuery(name = "PeriodoComprobante.buscarPorPeriodoYEmpleador", query = "SELECT p FROM PeriodoComprobante p"
		+ " WHERE p.anioPer = :aniper AND p.mesPer = :mesper AND p.tipoEmpleador = :tipoEmpleador" + " AND p.estadoRegistro = 'ACT' "), })
public class PeriodoComprobante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PC_IDCODIGO")
	private Long codigo;

	@Column(name = "PC_ANIOPER")
	private Integer anioPer;

	@Column(name = "PC_MESPER")
	private Integer mesPer;

	@Column(name = "PC_TIPOPERIODO")
	private String tipoPeriodo;

	@Column(name = "PC_TIPOEMPLEADOR")
	private String tipoEmpleador;

	@Column(name = "PC_FECHAINICIO")
	private Date fechaInicio;

	@Column(name = "PC_FECHAFIN")
	private Date fechaFin;

	@Column(name = "PC_ESTADOREGISTRO")
	private String estadoRegistro;

	@Column(name = "PC_DESCRIPCION")
	private String descripcion;
	
	@Column(name = "PC_RANGOHABILITACOMP")
	private String rangoHabilitaComprobante;
	
	@Column(name = "PC_RANGOHABILITALIQ")
	private String rangoHabilidaLiquidacion;

	// Getters and setters

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getAnioPer() {
		return anioPer;
	}

	public void setAnioPer(Integer anioPer) {
		this.anioPer = anioPer;
	}

	public Integer getMesPer() {
		return mesPer;
	}

	public void setMesPer(Integer mesPer) {
		this.mesPer = mesPer;
	}

	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public String getTipoEmpleador() {
		return tipoEmpleador;
	}

	public void setTipoEmpleador(String tipoEmpleador) {
		this.tipoEmpleador = tipoEmpleador;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRangoHabilitaComprobante() {
		return rangoHabilitaComprobante;
	}

	public void setRangoHabilitaComprobante(String rangoHabilitaComprobante) {
		this.rangoHabilitaComprobante = rangoHabilitaComprobante;
	}

	public String getRangoHabilidaLiquidacion() {
		return rangoHabilidaLiquidacion;
	}

	public void setRangoHabilidaLiquidacion(String rangoHabilidaLiquidacion) {
		this.rangoHabilidaLiquidacion = rangoHabilidaLiquidacion;
	}

}
