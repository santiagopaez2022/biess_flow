package ec.gov.iess.afiliadocore.modelo.persistencia.embedable;

import ec.gov.iess.afiliadocore.modelo.persistencia.pk.ServiciosPrestadosPK;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="ServiciosPrestados.findAfiliadoActivo", query="select o from ServiciosPrestados o  where o.esthislab = '1' and o.fecsalafi is null and o.afiliado.numafi = :cedula")})
@Table(name="KSAFITSERPRE")
public class ServiciosPrestados
  implements Serializable
{
  private static final long serialVersionUID = -1490394650478645260L;
  @EmbeddedId
  protected ServiciosPrestadosPK serviciosPrestadosPK;
  private String avimat;
  private String catjeffam;
  private String codactsec;
  @Column(nullable=false)
  private Long codgrpcot;
  @Column(nullable=false)
  private Long codjor;
  @Column(nullable=false)
  private String codreltra;
  @Column(nullable=false)
  private String codsegsoc;
  @Column(nullable=false)
  private String codtipemp;
  @Column(nullable=false)
  private String estaju;
  private String estfonres;
  @Column(nullable=false)
  private String esthislab;
  private String estmig;
  @Column(nullable=false)
  private Timestamp fecingafi;
  private Timestamp fecinifonres;
  private Timestamp fecsalafi;
  @Column(nullable=false)
  private Long numactafi;
  @Column(nullable=false)
  private String ocuafi;
  private String oripag;
  @Column(nullable=false)
  private Double porapoadi;
  @Column(nullable=false)
  private Double poraponor;
  @Column(nullable=false)
  private Double salafi;
  private Timestamp tieproden;
  private String tiptra;
  @ManyToOne
  @JoinColumn(name="NUMAFI", insertable=false, updatable=false)
  private Afiliado afiliado;
  
  public String getAvimat()
  {
    return this.avimat;
  }
  
  public void setAvimat(String avimat)
  {
    this.avimat = avimat;
  }
  
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
  
  public Long getCodjor()
  {
    return this.codjor;
  }
  
  public void setCodjor(Long codjor)
  {
    this.codjor = codjor;
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
  
  public String getEstaju()
  {
    return this.estaju;
  }
  
  public void setEstaju(String estaju)
  {
    this.estaju = estaju;
  }
  
  public String getEstfonres()
  {
    return this.estfonres;
  }
  
  public void setEstfonres(String estfonres)
  {
    this.estfonres = estfonres;
  }
  
  public String getEsthislab()
  {
    return this.esthislab;
  }
  
  public void setEsthislab(String esthislab)
  {
    this.esthislab = esthislab;
  }
  
  public String getEstmig()
  {
    return this.estmig;
  }
  
  public void setEstmig(String estmig)
  {
    this.estmig = estmig;
  }
  
  public Timestamp getFecingafi()
  {
    return this.fecingafi;
  }
  
  public void setFecingafi(Timestamp fecingafi)
  {
    this.fecingafi = fecingafi;
  }
  
  public Timestamp getFecinifonres()
  {
    return this.fecinifonres;
  }
  
  public void setFecinifonres(Timestamp fecinifonres)
  {
    this.fecinifonres = fecinifonres;
  }
  
  public Timestamp getFecsalafi()
  {
    return this.fecsalafi;
  }
  
  public void setFecsalafi(Timestamp fecsalafi)
  {
    this.fecsalafi = fecsalafi;
  }
  
  public Long getNumactafi()
  {
    return this.numactafi;
  }
  
  public void setNumactafi(Long numactafi)
  {
    this.numactafi = numactafi;
  }
  
  public String getOcuafi()
  {
    return this.ocuafi;
  }
  
  public void setOcuafi(String ocuafi)
  {
    this.ocuafi = ocuafi;
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
  
  public Timestamp getTieproden()
  {
    return this.tieproden;
  }
  
  public void setTieproden(Timestamp tieproden)
  {
    this.tieproden = tieproden;
  }
  
  public String getTiptra()
  {
    return this.tiptra;
  }
  
  public void setTiptra(String tiptra)
  {
    this.tiptra = tiptra;
  }
  
  public Afiliado getAfiliado()
  {
    return this.afiliado;
  }
  
  public void setAfiliado(Afiliado afiliado)
  {
    this.afiliado = afiliado;
  }
  
  public ServiciosPrestadosPK getServiciosPrestadosPK()
  {
    return this.serviciosPrestadosPK;
  }
  
  public void setServiciosPrestadosPK(ServiciosPrestadosPK serviciosPrestadosPK)
  {
    this.serviciosPrestadosPK = serviciosPrestadosPK;
  }
}
