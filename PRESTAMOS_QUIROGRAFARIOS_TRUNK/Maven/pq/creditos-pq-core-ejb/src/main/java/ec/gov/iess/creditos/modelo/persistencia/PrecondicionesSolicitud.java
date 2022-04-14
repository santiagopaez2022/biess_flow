/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * Lista de precondicines de la solicitud
 * 
 * @author cvillarreal
 * @version 1.0
 * 
 */
@Entity
@Table(name = "CRE_LISTACONDICPRESTAMO_TBL")
@NamedQueries( { @NamedQuery(name = "PrecondicionesSolicitud.findAll", query = "SELECT o FROM PrecondicionesSolicitud o") })
@SequenceGenerator(name = "CRE_LISTACONDICPRESTAMO_SEQ", sequenceName = "CRE_LISTACONDICPRESTAMO_SEQ", allocationSize = 1)
public class PrecondicionesSolicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1598278484476879283L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRE_LISTACONDICPRESTAMO_SEQ")
	private Long codconpre;

	private String calcondi;
	private String descondi;
	private String rescondi;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	/**
	 * 
	 */
	public PrecondicionesSolicitud() {
	}

	/**
	 * @return the codconpre
	 */
	public Long getCodconpre() {
		return codconpre;
	}

	/**
	 * @return the calcondi
	 */
	public String getCalcondi() {
		return calcondi;
	}

	/**
	 * @return the descondi
	 */
	public String getDescondi() {
		return descondi;
	}

	/**
	 * @return the rescondi
	 */
	public String getRescondi() {
		return rescondi;
	}

	/**
	 * @param codconpre
	 *            the codconpre to set
	 */
	public void setCodconpre(Long codconpre) {
		this.codconpre = codconpre;
	}

	/**
	 * @param calcondi
	 *            the calcondi to set
	 */
	public void setCalcondi(String calcondi) {
		this.calcondi = calcondi;
	}

	/**
	 * @param descondi
	 *            the descondi to set
	 */
	public void setDescondi(String descondi) {
		this.descondi = descondi;
	}

	/**
	 * @param rescondi
	 *            the rescondi to set
	 */
	public void setRescondi(String rescondi) {
		this.rescondi = rescondi;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param solicitudCredito
	 *            the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

}
