package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.ComprobantePagoHistoricoPK;

@Entity
@Table(name="ksrectcompagesthis")
@NamedQueries({
	@NamedQuery(name = "ComprobanteHistorico.BuscarPorId", 
		    query = "select o from ComprobantePagoHistorico o where o.pk = :Id"),
	@NamedQuery(name = "ComprobanteHistorico.BuscarComprobateActual", 
				    query = "select o from ComprobantePagoHistorico o where o.pk.codtipcompag = :codtipcompag " +
		    		"and o.pk.codcompag = :codcompag " +
		    		"and o.pk.codestcompag = :codestcompag " +
		    		"and o.fecfin is null")
})
public class ComprobantePagoHistorico implements Serializable {
	@EmbeddedId
	private ComprobantePagoHistoricoPK pk;

	private String codusu;

	private Date fecfin;

	private String obstra;

	private Date fecsys;

	private static final long serialVersionUID = 1L;
	
	public ComprobantePagoHistorico() {
		super();
	}

	public ComprobantePagoHistoricoPK getPk() {
		return this.pk;
	}

	public void setPk(ComprobantePagoHistoricoPK pk) {
		this.pk = pk;
	}

	public String getCodusu() {
		return this.codusu;
	}

	public void setCodusu(String codusu) {
		this.codusu = codusu;
	}

	public Date getFecfin() {
		return this.fecfin;
	}

	public void setFecfin(Date fecfin) {
		this.fecfin = fecfin;
	}

	public String getObstra() {
		return this.obstra;
	}

	public void setObstra(String obstra) {
		this.obstra = obstra;
	}

	public Date getFecsys() {
		return this.fecsys;
	}

	public void setFecsys(Date fecsys) {
		this.fecsys = fecsys;
	}

}
