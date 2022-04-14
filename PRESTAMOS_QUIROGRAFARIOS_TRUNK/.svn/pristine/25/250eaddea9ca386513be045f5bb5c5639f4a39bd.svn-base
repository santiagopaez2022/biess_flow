package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.DividendosHistoricoPK;

@Entity
@Table(name="kscretdivpreesthis")
@NamedQueries({
	@NamedQuery(name = "DividendosHistorico.BuscarPorId", 
		    query = "select o from DividendosHistorico o where o.pk = :Id"),
			@NamedQuery(name = "DividendosHistorico.BuscarDividendoActual", 
				    query = "select o from DividendosHistorico o where o.pk.ordpreafi = :ordpreafi " +
		    		"and o.pk.numdiv = :numdiv " +
		    		"and o.pk.codprecla = :codprecla " +
		    		"and o.pk.codpretip = :codpretip " +
		    		"and o.pk.numpreafi = :numpreafi " +
		    		"and o.pk.codestdiv = :codestdiv " +
		    		"and o.fecfin IS NULL")
})
public class DividendosHistorico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private DividendosHistoricoPK pk;

	private Date fecfin;

	private String obstra;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUMDIV", referencedColumnName="NUMDIV", insertable = false, updatable = false),
		@JoinColumn(name="CODPRETIP", referencedColumnName="CODPRETIP", insertable = false, updatable = false),
		@JoinColumn(name="ORDPREAFI", referencedColumnName="ORDPREAFI", insertable = false, updatable = false),
		@JoinColumn(name="NUMPREAFI", referencedColumnName="NUMPREAFI", insertable = false, updatable = false),
		@JoinColumn(name="CODPRECLA", referencedColumnName="CODPRECLA", insertable = false, updatable = false)
	})
	private Dividendos dividendos;

	public DividendosHistorico() {
		super();
	}
	public DividendosHistorico(DividendosHistoricoPK pk) {
		super();
		this.pk = pk;
	}

	public DividendosHistoricoPK getPk() {
		return this.pk;
	}

	public void setPk(DividendosHistoricoPK pk) {
		this.pk = pk;
	}

	public Date getFecfin() {
		return this.fecfin;
	}

	public void setFecfin(Date fechahoy) {
		this.fecfin = fechahoy;
	}

	public String getObstra() {
		return this.obstra;
	}

	public void setObstra(String obstra) {
		this.obstra = obstra;
	}

	public Dividendos getDividendos() {
		return this.dividendos;
	}

	public void setDividendos(Dividendos dividendos) {
		this.dividendos = dividendos;
	}

}
