/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import ec.gov.iess.creditos.modelo.persistencia.pk.TasaInteresDetallePK;

/**
 * @author cvillarreal
 * 
 */

@Entity
@Table(name = "Kscretinttasdet")
@NamedQueries( {
	
		@NamedQuery(name = "TasaInteresDetalle.consultaPorTasaInteres", query = "select o from TasaInteresDetalle o "
				+ "where o.tasaInteresDetallePK.codtasint=:idtasaInteres "
				+ "order by o.tasaInteresDetallePK.fecinitasint "),
				
		@NamedQuery(name = "TasaInteresDetalle.rangoFechasInicioTipoInteres", query = "select o from TasaInteresDetalle o "
				+ " where o.tasaInteresDetallePK.codtasint = :idTasInteres "
				+ " and o.tasaInteresDetallePK.fecinitasint  between  :fechaDesde and :fechahasta order by o.tasaInteresDetallePK.fecinitasint desc"),
				
		@NamedQuery(name = "TasaInteresDetalle.calculoInteresMora", query = "select o from TasaInteresDetalle o " +
					"where o.tasaInteresDetallePK.codtasint = :idTasaInteres "
					+ "and :fecha between o.tasaInteresDetallePK.fecinitasint and o.fecfintasint")
})
@NamedNativeQueries( {

@NamedNativeQuery(name = "TasaInteresDetalle.rangoFechasTipoInteres", query = "select * "
		+ "from kscretinttasdet k "
		+ "where k.CODTASINT = :idTasaInteres "
		+ "and to_date(:fecha,'dd/mm/yyyy')"
		+ "between k.FECINITASINT AND k.FECFINTASINT ", resultSetMapping = "mappingTasaInteresDetalle")
})
@SqlResultSetMapping(name = "mappingTasaInteresDetalle", entities = @EntityResult(entityClass = TasaInteresDetalle.class))
public class TasaInteresDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TasaInteresDetallePK tasaInteresDetallePK;

	@Column(nullable = false)
	private Double portasint;

	@Column(nullable = false)
	private Date fecfintasint;


	@ManyToOne
	@JoinColumn(name = "CODTASINT", referencedColumnName = "CODTASINT")
	private TasaInteres tasaInteres;

	/**
	 * 
	 */
	public TasaInteresDetalle() {
	}

	/**
	 * @return the tasaInteresDetallePK
	 */
	public TasaInteresDetallePK getTasaInteresDetallePK() {
		return tasaInteresDetallePK;
	}

	/**
	 * @param tasaInteresDetallePK
	 *            the tasaInteresDetallePK to set
	 */
	public void setTasaInteresDetallePK(
			TasaInteresDetallePK tasaInteresDetallePK) {
		this.tasaInteresDetallePK = tasaInteresDetallePK;
	}

	/**
	 * @return the portasint
	 */
	public Double getPortasint() {
		return portasint;
	}

	/**
	 * @param portasint
	 *            the portasint to set
	 */
	public void setPortasint(Double portasint) {
		this.portasint = portasint;
	}

	/**
	 * @return the tasaInteres
	 */
	public TasaInteres getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres
	 *            the tasaInteres to set
	 */
	public void setTasaInteres(TasaInteres tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public Date getFecfintasint() {
		return fecfintasint;
	}

	public void setFecfintasint(Date fecfintasint) {
		this.fecfintasint = fecfintasint;
	}

}
