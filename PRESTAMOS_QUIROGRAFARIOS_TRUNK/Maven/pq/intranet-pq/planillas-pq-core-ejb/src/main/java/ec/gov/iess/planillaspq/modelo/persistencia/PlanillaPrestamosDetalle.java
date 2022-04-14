package ec.gov.iess.planillaspq.modelo.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;

@Entity
@Table(name="kscretplapredet")
@NamedQueries({
	@NamedQuery(name = "PlanillaDetalle.BuscarPorId", 
		    query = "select o from PlanillaPrestamosDetalle o where o.pk = :Id"),
//	@NamedQuery(name = "PlanillaDetalle.BuscarPorPlanilla", 
//			query = "select o from PlanillaPrestamosDetalle o where o.pk.rucemp= :rucemp " +
//		    		"and o.pk.codsuc= :codsuc " +
//		    		"and o.pk.aniper= :aniper " +
//		    		"and o.pk.mesper= :mesper " +
//		    		"and o.pk.codtippla= :codtippla " +
//		    		"and o.pk.tipper= :tipper " +
//		    		"order by o.numafi"),
	@NamedQuery(name = "PlanillaDetalle.BuscarPorPlanilla", 
					query = "select new PlanillaPrestamosDetalle(o,(select a.apenomafi from Afiliado a where a.numafi = o.numafi)) " +
							" from PlanillaPrestamosDetalle o where o.pk.rucemp= :rucemp " +
				    		" and o.pk.codsuc= :codsuc " +
				    		" and o.pk.aniper= :aniper " +
				    		" and o.pk.mesper= :mesper " +
				    		" and o.pk.codtippla= :codtippla " +
				    		" and o.pk.tipper= :tipper " +
				    		" order by o.numafi "),		    
	@NamedQuery(name = "PlanillaDetalle.BuscarPorPk", 
			query = "select o from PlanillaPrestamosDetalle o where o.pk.rucemp= :rucemp " +
		    		"and o.pk.codsuc= :codsuc " +
		    		"and o.pk.aniper= :aniper " +
		    		"and o.pk.mesper= :mesper " +
		    		"and o.pk.codtippla= :codtippla " +
		    		"and o.pk.tipper= :tipper "+
		    		"and o.pk.secpla= :secpla " +
		    		"and o.pk.numpreafi= :numpreafi")
})
//@NamedNativeQueries({
//		@NamedNativeQuery(name = "PlanillaDetalle.BuscarPorPlanilla",
//				query = " select d.rucemp, d.codsuc, d.aniper, d.mesper, d.codtippla, " +					
//					" d.tipper, d.secpla, d.numpreafi, d.ordpreafi, d.numdiv, d.codpretip, d.codprecla, " +
//					" d.numafi, d.valtotdiv, d.codpagpen, a.apenomafi nombreAfiliado " +
//					" from KSCRETPLAPREDET d inner join KSPCOTAFILIADOS a on d.numafi = a.numafi " +
//					" where d.rucemp= :rucemp " +
//					" and d.codsuc= :codsuc " +
//					" and d.aniper= :aniper " +
//					" and d.mesper= :mesper " +
//					" and d.codtippla= :codtippla " + 
//					" and d.tipper= :tipper " +
//    				" order by d.numafi ",
//    				resultSetMapping = "PlanillaDetalleMap")
//})		
//@SqlResultSetMapping(name = "PlanillaDetalleMap",
//		entities = @EntityResult(entityClass = PlanillaPrestamosDetalleAfi.class)
//)
public class PlanillaPrestamosDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlanillaPrestamosDetallePK pk;

	private String numafi;

	private BigDecimal valtotdiv;

	private String codpagpen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="NUMDIV", referencedColumnName="NUMDIV", insertable = false, updatable = false),
		@JoinColumn(name="CODPRETIP", referencedColumnName="CODPRETIP", insertable = false, updatable = false),
		@JoinColumn(name="ORDPREAFI", referencedColumnName="ORDPREAFI", insertable = false, updatable = false),
		@JoinColumn(name="NUMPREAFI", referencedColumnName="NUMPREAFI", insertable = false, updatable = false),
		@JoinColumn(name="CODPRECLA", referencedColumnName="CODPRECLA", insertable = false, updatable = false)
	})
	private Dividendos dividendos;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="RUCEMP", referencedColumnName="RUCEMP", insertable = false, updatable = false),
		@JoinColumn(name="CODSUC", referencedColumnName="CODSUC", insertable = false, updatable = false),
		@JoinColumn(name="CODTIPPLA", referencedColumnName="CODTIPPLA", insertable = false, updatable = false),
		@JoinColumn(name="TIPPER", referencedColumnName="TIPPER", insertable = false, updatable = false),
		@JoinColumn(name="ANIPER", referencedColumnName="ANIPER", insertable = false, updatable = false),
		@JoinColumn(name="MESPER", referencedColumnName="MESPER", insertable = false, updatable = false),
		@JoinColumn(name="SECPLA", referencedColumnName="SECPLA", insertable = false, updatable = false)
	})
	private Planillas planillas;

	@Transient
	private String nombreAfiliado;
	

	public PlanillaPrestamosDetalle() {
		super();
	}

	public PlanillaPrestamosDetalle(PlanillaPrestamosDetalle detalle, String apenomafi) {
		this.pk = detalle.getPk();		
		this.codpagpen = detalle.getCodpagpen();
		this.numafi = detalle.getNumafi();
		this.valtotdiv = detalle.getValtotdiv();
		this.planillas = detalle.getplanillas();
		this.dividendos = detalle.getdividendos();		
		this.nombreAfiliado = apenomafi;
	}
	
	public PlanillaPrestamosDetallePK getPk() {
		return this.pk;
	}

	public void setPk(PlanillaPrestamosDetallePK pk) {
		this.pk = pk;
	}

	public String getNumafi() {
		return this.numafi;
	}

	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	public BigDecimal getValtotdiv() {
		return this.valtotdiv;
	}

	public void setValtotdiv(BigDecimal valtotdiv) {
		this.valtotdiv = valtotdiv;
	}

	public String getCodpagpen() {
		return this.codpagpen;
	}

	public void setCodpagpen(String codpagpen) {
		this.codpagpen = codpagpen;
	}

	public Dividendos getdividendos() {
		return this.dividendos;
	}

	public void setdividendos(Dividendos dividendos) {
		this.dividendos = dividendos;
	}

	public Planillas getplanillas() {
		return this.planillas;
	}

	public void setplanillas(Planillas planillas) {
		this.planillas = planillas;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

}
