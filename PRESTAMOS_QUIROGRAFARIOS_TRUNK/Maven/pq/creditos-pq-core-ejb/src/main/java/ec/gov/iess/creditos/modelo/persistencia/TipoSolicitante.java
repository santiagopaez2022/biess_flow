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
 * Tipo de solicitante que reaiza la solicitud de prestamo
 * 
 * @author cvillarreal
 * @version
 * 
 */
@Entity
@Table(name = "CRE_TIPOSOLICITANTE_TBL")
@NamedQueries( { 
	@NamedQuery(name = "TipoSolicitante.findAll", 
			query = "SELECT o FROM TipoSolicitante o") })
public class TipoSolicitante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private Long codtipsol;
	private String destipsolser;

	public TipoSolicitante() {

	}

	/**
	 * @return the codtipsol
	 */
	public Long getCodtipsol() {
		return codtipsol;
	}

	/**
	 * @return the destipsolser
	 */
	public String getDestipsolser() {
		return destipsolser;
	}

	/**
	 * @param codtipsol
	 *            the codtipsol to set
	 */
	public void setCodtipsol(Long codtipsol) {
		this.codtipsol = codtipsol;
	}

	/**
	 * @param destipsolser
	 *            the destipsolser to set
	 */
	public void setDestipsolser(String destipsolser) {
		this.destipsolser = destipsolser;
	}

}
