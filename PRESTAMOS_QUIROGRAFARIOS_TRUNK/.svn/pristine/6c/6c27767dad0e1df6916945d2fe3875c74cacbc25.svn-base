package ec.gov.iess.afiliadocore.modelo.persistencia.embedable;

import ec.gov.iess.afiliadocore.modelo.persistencia.pk.ServiciosPrestadosHistoricoPK;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedNativeQuery(name="ServiciosPrestadosHistorico.findByFechaAfiliadoActivo", query="SELECT H.* FROM KSAFITSERPREHIS H WHERE H.NUMAFI =?1 AND ?2 BETWEEN H.FECINI AND NVL(H.FECFIN, SYSDATE)", resultClass=ServiciosPrestadosHistorico.class)
@NamedQuery(name="ServiciosPrestadosHistorico.findAll", query="select o from ServiciosPrestadosHistorico o")
@Table(name="KSAFITSERPREHIS")
public class ServiciosPrestadosHistorico
  implements Serializable
{
  private static final long serialVersionUID = 2435109296666516189L;
  @EmbeddedId
  protected ServiciosPrestadosHistoricoPK serviciosPrestadosHistoricoPK;
  private String catjeffam;
  private String codactsec;
  @Column(nullable=false)
  private Long codgrpcot;
  private String codnovafi;
  @Column(nullable=false)
  private String codreltra;
  @Column(nullable=false)
  private String codsegsoc;
  @Column(nullable=false)
  private String codtipemp;
  private String codtipnovafi;
  private Timestamp fecfin;
  private String oripag;
  @Column(nullable=false)
  private Double porapoadi;
  @Column(nullable=false)
  private Double poraponor;
  private Double salafi;
  
  public String getCatjeffam()
  {
    return this.catjeffam;
  }
  
  public void setCatjeffam(String catjeffam)
  {
    this.catjeffam = catjeffam;
  }
  
  public String getCodactsec()
  {
    return this.codactsec;
  }
  
  public void setCodactsec(String codactsec)
  {
    this.codactsec = codactsec;
  }
  
  public Long getCodgrpcot()
  {
    return this.codgrpcot;
  }
  
  public void setCodgrpcot(Long codgrpcot)
  {
    this.codgrpcot = codgrpcot;
  }
  
  public String getCodnovafi()
  {
    return this.codnovafi;
  }
  
  public void setCodnovafi(String codnovafi)
  {
    this.codnovafi = codnovafi;
  }
  
  public String getCodreltra()
  {
    return this.codreltra;
  }
  
  public void setCodreltra(String codreltra)
  {
    this.codreltra = codreltra;
  }
  
  public String getCodsegsoc()
  {
    return this.codsegsoc;
  }
  
  public void setCodsegsoc(String codsegsoc)
  {
    this.codsegsoc = codsegsoc;
  }
  
  public String getCodtipemp()
  {
    return this.codtipemp;
  }
  
  public void setCodtipemp(String codtipemp)
  {
    this.codtipemp = codtipemp;
  }
  
  public String getCodtipnovafi()
  {
    return this.codtipnovafi;
  }
  
  public void setCodtipnovafi(String codtipnovafi)
  {
    this.codtipnovafi = codtipnovafi;
  }
  
  public Timestamp getFecfin()
  {
    return this.fecfin;
  }
  
  public void setFecfin(Timestamp fecfin)
  {
    this.fecfin = fecfin;
  }
  
  public String getOripag()
  {
    return this.oripag;
  }
  
  public void setOripag(String oripag)
  {
    this.oripag = oripag;
  }
  
  public Double getPorapoadi()
  {
    return this.porapoadi;
  }
  
  public void setPorapoadi(Double porapoadi)
  {
    this.porapoadi = porapoadi;
  }
  
  public Double getPoraponor()
  {
    return this.poraponor;
  }
  
  public void setPoraponor(Double poraponor)
  {
    this.poraponor = poraponor;
  }
  
  public Double getSalafi()
  {
    return this.salafi;
  }
  
  public void setSalafi(Double salafi)
  {
    this.salafi = salafi;
  }
  
  public ServiciosPrestadosHistoricoPK getServiciosPrestadosHistoricoPK()
  {
    return this.serviciosPrestadosHistoricoPK;
  }
  
  public void setServiciosPrestadosHistoricoPK(ServiciosPrestadosHistoricoPK serviciosPrestadosHistoricoPK)
  {
    this.serviciosPrestadosHistoricoPK = serviciosPrestadosHistoricoPK;
  }
}
