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
 * Tipo de rol para la solicitud de credito
 * 
 * @author cvillarreal 03/10/2011
 * @version
 * 
 */
@Entity
@Table(name = "CRE_TIPOROLSOLICITANTE_TBL")
@NamedQueries({
		@NamedQuery(name = "TipoRolSolicitante.findAll", query = "SELECT o FROM TipoRolSolicitante o"),
		@NamedQuery(name = "TipoRolSolicitante.obtenerPorTipoProducto", query = "SELECT o FROM TipoRolSolicitante o " +
				"WHERE o.codtipsolser in (:listaProductos) ORDER BY o.codtiprolsol") })
public class TipoRolSolicitante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 640846903024026712L;

	@Id
	@Column(nullable = false)
	private Long codtiprolsol;

	private String destiprolsol;

	private Long codtipsolser;

	/**
	 * 
	 */
	public TipoRolSolicitante() {
	}

	/**
	 * @return the codtiprolsol
	 */
	public Long getCodtiprolsol() {
		return codtiprolsol;
	}

	/**
	 * @return the destiprolsol
	 */
	public String getDestiprolsol() {
		return destiprolsol;
	}

	/**
	 * @param codtiprolsol
	 *            the codtiprolsol to set
	 */
	public void setCodtiprolsol(Long codtiprolsol) {
		this.codtiprolsol = codtiprolsol;
	}

	/**
	 * @param destiprolsol
	 *            the destiprolsol to set
	 */
	public void setDestiprolsol(String destiprolsol) {
		this.destiprolsol = destiprolsol;
	}

	/**
	 * @return the codtipsolser
	 */
	public Long getCodtipsolser() {
		return codtipsolser;
	}

	/**
	 * @param codtipsolser
	 *            the codtipsolser to set
	 */
	public void setCodtipsolser(Long codtipsolser) {
		this.codtipsolser = codtipsolser;
	}
}
