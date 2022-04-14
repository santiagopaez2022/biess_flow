package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Angel Sarmiento 03/10/2011
 * 
 */
@Entity
@Table(name = "CRE_CATALOGODESTINOPQ_TBL")
@NamedQueries( {
		@NamedQuery(name = "Catalogo.getAll", query = "SELECT cat from Catalogo cat ORDER BY cat.codigo")})
public class Catalogo implements Serializable{
	
	private static final long serialVersionUID = 1975753207793702607L;
	
	
	@Id
	@Basic
	@Column(name = "COPQ_ID")
	private Long codigo;

	@Column(name = "CDPQ_DESCRIPCION")
	private String descripcion;
	
	// INC-2272 Pensiones Alimenticias
	@Column(name = "CDPQ_CODPRETIP")
	private Long codigoTipoProducto;
	
	@Column(name = "CDPQ_ACTIVO")
	private String indicadorActivo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigoTipoProducto
	 */
	public Long getCodigoTipoProducto() {
		return codigoTipoProducto;
	}

	/**
	 * @param codigoTipoProducto the codigoTipoProducto to set
	 */
	public void setCodigoTipoProducto(Long codigoTipoProducto) {
		this.codigoTipoProducto = codigoTipoProducto;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

}
