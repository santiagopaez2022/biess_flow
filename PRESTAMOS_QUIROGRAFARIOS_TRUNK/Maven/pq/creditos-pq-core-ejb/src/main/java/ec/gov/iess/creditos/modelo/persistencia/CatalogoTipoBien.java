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
 * Clase para mapear la tabla cre_catalogotipobien_tbl
 * 
 * @author jsanchez
 * 
 */
@Entity
@NamedQueries( {
		@NamedQuery(name = "CatalogoTipoBien.obtenerPorTipSolSer", query = "SELECT o FROM CatalogoTipoBien o "
				+ " WHERE o.codTipSolSer=:codigoTipoSol and o.estadoBien='A' order by o.codCatalogo"),
		@NamedQuery(name = "CatalogoTipoBien.obtenerPorTipSolSerCodigoBien", query = "SELECT o FROM CatalogoTipoBien o "
				+ " WHERE o.codTipSolSer=:codigoTipoSol and o.codBie=:codigoBien and o.estadoBien='A' order by o.tipoBien") })
@Table(name = "CRE_CATALOGOTIPOBIEN_TBL")
public class CatalogoTipoBien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2042953155723928920L;

	@Id
	@Column(name = "CODCAT", nullable = false)
	private Long codCatalogo;

	private Long codTipSolSer;

	@Column(name = "TIPBIEN")
	private String tipoBien;

	@Column(name = "ESTBIEN")
	private String estadoBien;

	private String codBie;

	/**
	 * @return the codCatalogo
	 */
	public Long getCodCatalogo() {
		return codCatalogo;
	}

	/**
	 * @param codCatalogo
	 *            the codCatalogo to set
	 */
	public void setCodCatalogo(Long codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	/**
	 * @return the codTipSolSer
	 */
	public Long getCodTipSolSer() {
		return codTipSolSer;
	}

	/**
	 * @param codTipSolSer
	 *            the codTipSolSer to set
	 */
	public void setCodTipSolSer(Long codTipSolSer) {
		this.codTipSolSer = codTipSolSer;
	}

	/**
	 * @return the tipoBien
	 */
	public String getTipoBien() {
		return tipoBien;
	}

	/**
	 * @param tipoBien
	 *            the tipoBien to set
	 */
	public void setTipoBien(String tipoBien) {
		this.tipoBien = tipoBien;
	}

	/**
	 * @return the estadoBien
	 */
	public String getEstadoBien() {
		return estadoBien;
	}

	/**
	 * @param estadoBien
	 *            the estadoBien to set
	 */
	public void setEstadoBien(String estadoBien) {
		this.estadoBien = estadoBien;
	}

	/**
	 * @return the codBie
	 */
	public String getCodBie() {
		return codBie;
	}

	/**
	 * @param codBie
	 *            the codBie to set
	 */
	public void setCodBie(String codBie) {
		this.codBie = codBie;
	}
}
