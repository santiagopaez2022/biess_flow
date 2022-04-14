package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Daniel Cardenas
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "KSCRETPRETIP")
public class TipoPrestamo implements Serializable {

	@Id
	@Column(name = "CODPRETIP", nullable = false)
	private Long codigo;

	@Column(name = "despretip", nullable = false)
	private String descripcion;

	@Column(name = "indhabpretip", nullable = false)
	private String indicadorHab;
	
	// INC-2272 Pensiones Alimenticias
	@Column(name = "codmodulo")
	private String codigoModulo;	

	@Column(name = "codwspretip")
	private String codigoWebService;

	@Column(name = "nompretip")
	private String nombreProducto;
	
	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	 * @return the indicadorHab
	 */
	public String getIndicadorHab() {
		return indicadorHab;
	}

	/**
	 * @param indicadorHab
	 *            the indicadorHab to set
	 */
	public void setIndicadorHab(String indicadorHab) {
		this.indicadorHab = indicadorHab;
	}

	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}

	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}	

	/**
	 * @return the codigoWebService
	 */
	public String getCodigoWebService() {
		return codigoWebService;
	}

	/**
	 * @param codigoWebService the codigoWebService to set
	 */
	public void setCodigoWebService(String codigoWebService) {
		this.codigoWebService = codigoWebService;
	}

	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

}