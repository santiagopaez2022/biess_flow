/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author cvillarreal
 * 
 */

@Entity
@Table(name = "TCRHP_ANIPLACAPENDHIP")
@NamedQueries( { @NamedQuery(name = "HistoricoAniosPlazoCapitalEndeudamiento.consultaPlazoEndeudamiento", query = "select o from HistoricoAniosPlazoCapitalEndeudamiento o "
		+ " where o.variedadCredito = :variedadCredito "
		+ " and o.tipoCredito = :tipoCredito "
		+ " and o.aniosPlazo = :plazoEndeudamiento "
		+ " and :fechaConsulta BETWEEN o.fechaInicioVigencia AND o.fechaFinVigencia ") })
public class HistoricoAniosPlazoCapitalEndeudamiento implements Serializable {

	private static final long serialVersionUID = 587809448931371913L;

	@Id
	@Column(name = "CODCAPEND", nullable = false)
	private Long idPlazo;

	@Column(name = "CODPRETIP")
	private Integer tipoCredito;

	@Column(name = "CODPRECLA")
	private Integer variedadCredito;

	@Column(name = "NUMANIPLA")
	private Integer aniosPlazo;

	@Column(name = "PORCAPEND")
	private BigDecimal porcentajeInteres;

	@Column(name = "FECDES")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicioVigencia;

	@Column(name = "FECHAS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFinVigencia;

	public HistoricoAniosPlazoCapitalEndeudamiento() {

	}

	/**
	 * @return the idPlazo
	 */
	public Long getIdPlazo() {
		return idPlazo;
	}

	/**
	 * @return the tipoCredito
	 */
	public Integer getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @return the variedadCredito
	 */
	public Integer getVariedadCredito() {
		return variedadCredito;
	}

	/**
	 * @return the aniosPlazo
	 */
	public Integer getAniosPlazo() {
		return aniosPlazo;
	}

	/**
	 * @return the porcentajeInteres
	 */
	public BigDecimal getPorcentajeInteres() {
		return porcentajeInteres;
	}

	/**
	 * @return the fechaInicioVigencia
	 */
	public Date getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}

	/**
	 * @return the fechaFinVigencia
	 */
	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}

	/**
	 * @param idPlazo
	 *            the idPlazo to set
	 */
	public void setIdPlazo(Long idPlazo) {
		this.idPlazo = idPlazo;
	}

	/**
	 * @param tipoCredito
	 *            the tipoCredito to set
	 */
	public void setTipoCredito(Integer tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @param variedadCredito
	 *            the variedadCredito to set
	 */
	public void setVariedadCredito(Integer variedadCredito) {
		this.variedadCredito = variedadCredito;
	}

	/**
	 * @param aniosPlazo
	 *            the aniosPlazo to set
	 */
	public void setAniosPlazo(Integer aniosPlazo) {
		this.aniosPlazo = aniosPlazo;
	}

	/**
	 * @param porcentajeInteres
	 *            the porcentajeInteres to set
	 */
	public void setPorcentajeInteres(BigDecimal porcentajeInteres) {
		this.porcentajeInteres = porcentajeInteres;
	}

	/**
	 * @param fechaInicioVigencia
	 *            the fechaInicioVigencia to set
	 */
	public void setFechaInicioVigencia(Date fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}

	/**
	 * @param fechaFinVigencia
	 *            the fechaFinVigencia to set
	 */
	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer msg = new StringBuffer();

		msg.append("PLAZo ENDEU : ");
		msg.append("ID : "
				+ this.idPlazo
				+ " tipoCredito : "
				+ this.tipoCredito
				+ " variedadCredito : "
				+ this.variedadCredito
				+ " plazo : "
				+ this.aniosPlazo
				+ " fecha ini : "
				+ (new SimpleDateFormat("dd/MM/yyyy"))
						.format(this.fechaInicioVigencia)
				+ " fecha fin : "
				+ (new SimpleDateFormat("dd/MM/yyyy"))
						.format(this.fechaFinVigencia));

		return msg.toString();
	}

}
