/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase para mapear la tabla cre_documentacionavaluos_tbl
 * 
 * @author jsanchez 03/10/2011
 * 
 */
@Entity
@Table(name = "CRE_DOCUMENTACIONAVALUOS_TBL")
@NamedQueries({
@NamedQuery(name = "DocumentacionAvaluo.obtenerPorTipoSolSer", query = "Select d from DocumentacionAvaluo d where d.codTipoSolSer=:codTipoSolSer order by d.id"),
@NamedQuery(name="DocumentacionAvaluo.obtenerPorTipoSolserCodTipPro", 
		query="select d from DocumentacionAvaluo d where d.codTipoSolSer=:codTipoSolSer and d.codTipPro=:codTipoProducto order by d.id")})
public class DocumentacionAvaluo implements Serializable {

	private static final long serialVersionUID = 185175765829812355L;

	@Id
	@Column(name = "CODDOC", nullable = false)
	private Long id;

	@Column(name = "CODTIPSOLSER")
	private Long codTipoSolSer;

	@Column(name = "DESCDOC")
	private String descripcion;

	@Column(name = "OBSERVA")
	private String observacion;

	private String codTipPro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodTipoSolSer() {
		return codTipoSolSer;
	}

	public void setCodTipoSolSer(Long codTipoSolSer) {
		this.codTipoSolSer = codTipoSolSer;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCodTipPro() {
		return codTipPro;
	}

	public void setCodTipPro(String codTipPro) {
		this.codTipPro = codTipPro;
	}
}
