package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;


@Entity
@Table(name="HLCRETHOSPLAPREDET")
@NamedQueries({
	@NamedQuery(name = "PlanillaDetalleHost.BuscarPorId", 
		    query = "select o from PlanillaDetalleHost o where o.pk = :Id"),
			@NamedQuery(name = "PlanillaDetalleHost.BuscarPorPlanilla", 
					query = "select o from PlanillaDetalleHost o where o.pk.rucemp= :rucemp " +
				    		"and o.pk.codsuc= :codsuc " +
				    		"and o.pk.aniper= :aniper " +
				    		"and o.pk.mesper= :mesper " +
				    		"and o.pk.codtippla= :codtippla " +
				    		"and o.pk.tipper= :tipper " +
				    		"and o.pk.secpla= :secpla " +
				    		"order by o.pk.numafi")
})
public class PlanillaDetalleHost implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlanillaDetalleHostPK pk;

	private BigDecimal valtotdiv;

	@Transient
	private String nombreAfiliado;

	public PlanillaDetalleHost() {
		super();
	}

	public PlanillaDetalleHostPK getPk() {
		return this.pk;
	}

	public void setPk(PlanillaDetalleHostPK pk) {
		this.pk = pk;
	}

	public BigDecimal getValtotdiv() {
		return this.valtotdiv;
	}

	public void setValtotdiv(BigDecimal valtotdiv) {
		this.valtotdiv = valtotdiv;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

}
