package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author cvillarreal
 *
 */
@Embeddable
public class FechaVigencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8660566419079092687L;
	
	@Temporal(TemporalType.DATE)
	private Date fechaDesde;
	
	@Temporal(TemporalType.DATE)
	private Date fechaHasta;
	
	
	public FechaVigencia(){
		
	}


	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}


	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}


	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
