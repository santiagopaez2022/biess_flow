package ec.gov.iess.empleadorcore.modelo.persistencia.embedable;

import ec.gov.iess.empleadorcore.modelo.persistencia.pk.TipoEmpleadorPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name="TipoEmpleador.findAll", query="select o from TipoEmpleador o")
@Table(name="KSPCOTEMPTIP")
public class TipoEmpleador
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected TipoEmpleadorPK tipoEmpleadorPK;
  private String basleg;
  @Column(nullable=false)
  private Long codseggrp;
  @Column(nullable=false)
  private String destipemp;
  private String esttipemp;
  @Column(nullable=false)
  private String fueval;
  @OneToMany(mappedBy="tipoEmpleador")
  private List<Empleador> empleadorList;
  @ManyToOne
  @JoinColumn(name="CODSEC", insertable=false, updatable=false)
  private Seccion seccion;
  
  public String getBasleg()
  {
    return this.basleg;
  }
  
  public void setBasleg(String basleg)
  {
    this.basleg = basleg;
  }
  
  public Long getCodseggrp()
  {
    return this.codseggrp;
  }
  
  public void setCodseggrp(Long codseggrp)
  {
    this.codseggrp = codseggrp;
  }
  
  public String getDestipemp()
  {
    return this.destipemp;
  }
  
  public void setDestipemp(String destipemp)
  {
    this.destipemp = destipemp;
  }
  
  public String getEsttipemp()
  {
    return this.esttipemp;
  }
  
  public void setEsttipemp(String esttipemp)
  {
    this.esttipemp = esttipemp;
  }
  
  public String getFueval()
  {
    return this.fueval;
  }
  
  public void setFueval(String fueval)
  {
    this.fueval = fueval;
  }
  
  public Seccion getSeccion()
  {
    return this.seccion;
  }
  
  public void setSeccion(Seccion seccion)
  {
    this.seccion = seccion;
  }
  
  public TipoEmpleadorPK getTipoEmpleadorPK()
  {
    return this.tipoEmpleadorPK;
  }
  
  public void setTipoEmpleadorPK(TipoEmpleadorPK tipoEmpleadorPK)
  {
    this.tipoEmpleadorPK = tipoEmpleadorPK;
  }
  
  public List<Empleador> getEmpleadorList()
  {
    return this.empleadorList;
  }
  
  public void setEmpleadorList(List<Empleador> empleadorList)
  {
    this.empleadorList = empleadorList;
  }
}
