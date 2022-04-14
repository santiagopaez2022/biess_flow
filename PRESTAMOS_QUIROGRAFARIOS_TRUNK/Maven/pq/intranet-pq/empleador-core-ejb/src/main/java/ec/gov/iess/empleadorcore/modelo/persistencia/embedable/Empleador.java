package ec.gov.iess.empleadorcore.modelo.persistencia.embedable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Empleador.findByRuc", query="select o from Empleador o where o.rucemp = :rucEmpresa")
@NamedNativeQueries({@javax.persistence.NamedNativeQuery(name="Empleador.findByInstitucionFinanciera", query="SELECT * FROM KSPCOTEMPLEADORES WHERE EMPINSFIN = 1 AND NOMEMP LIKE UPPER(?1) ORDER BY NOMEMP, RUCEMP ", resultClass=Empleador.class)})
@Table(name="KSPCOTEMPLEADORES")
public class Empleador
  implements Serializable
{
  private static final long serialVersionUID = -6780130989379311343L;
  private String claemp;
  @Column(nullable=false)
  private String codclacom;
  @Column(nullable=false)
  private String codsegsoc;
  @Column(nullable=false)
  private String codtipemp;
  @Column(nullable=false)
  private String codtipemr;
  private String codtiporg;
  private Long ctabceinsfin;
  private String empabr;
  @Column(nullable=false)
  private String empacap;
  @Column(nullable=false)
  private String empentcon;
  private String empexe;
  private String empexefonres;
  @Column(nullable=false)
  private String empinsfin;
  @Column(nullable=false)
  private String empunimed;
  private Timestamp feccreemp;
  private Timestamp fecentdoc;
  @Column(nullable=false)
  private Timestamp fecregemp;
  @Column(nullable=false)
  private String nomemp;
  private String nomresrec;
  private Long numcedafivol;
  @Column(nullable=false)
  private Long numlinnov;
  @Column(nullable=false)
  private Long numlinsuc;
  @Column(nullable=false)
  private Long numtradec;
  @Column(nullable=false)
  private Long numtrarea;
  @Id
  @Column(nullable=false)
  private String rucemp;
  @OneToMany(mappedBy="empleador")
  private List<Sucursal> sucursalList;
  @ManyToOne
  @JoinColumns({@javax.persistence.JoinColumn(name="CODTIPEMP", referencedColumnName="CODTIPEMP", insertable=false, updatable=false), @javax.persistence.JoinColumn(name="CODSEGSOC", referencedColumnName="CODSEGSOC", insertable=false, updatable=false)})
  private TipoEmpleador tipoEmpleador;
  
  public String getClaemp()
  {
    return this.claemp;
  }
  
  public void setClaemp(String claemp)
  {
    this.claemp = claemp;
  }
  
  public String getCodclacom()
  {
    return this.codclacom;
  }
  
  public void setCodclacom(String codclacom)
  {
    this.codclacom = codclacom;
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
  
  public String getCodtipemr()
  {
    return this.codtipemr;
  }
  
  public void setCodtipemr(String codtipemr)
  {
    this.codtipemr = codtipemr;
  }
  
  public String getCodtiporg()
  {
    return this.codtiporg;
  }
  
  public void setCodtiporg(String codtiporg)
  {
    this.codtiporg = codtiporg;
  }
  
  public Long getCtabceinsfin()
  {
    return this.ctabceinsfin;
  }
  
  public void setCtabceinsfin(Long ctabceinsfin)
  {
    this.ctabceinsfin = ctabceinsfin;
  }
  
  public String getEmpabr()
  {
    return this.empabr;
  }
  
  public void setEmpabr(String empabr)
  {
    this.empabr = empabr;
  }
  
  public String getEmpacap()
  {
    return this.empacap;
  }
  
  public void setEmpacap(String empacap)
  {
    this.empacap = empacap;
  }
  
  public String getEmpentcon()
  {
    return this.empentcon;
  }
  
  public void setEmpentcon(String empentcon)
  {
    this.empentcon = empentcon;
  }
  
  public String getEmpexe()
  {
    return this.empexe;
  }
  
  public void setEmpexe(String empexe)
  {
    this.empexe = empexe;
  }
  
  public String getEmpexefonres()
  {
    return this.empexefonres;
  }
  
  public void setEmpexefonres(String empexefonres)
  {
    this.empexefonres = empexefonres;
  }
  
  public String getEmpinsfin()
  {
    return this.empinsfin;
  }
  
  public void setEmpinsfin(String empinsfin)
  {
    this.empinsfin = empinsfin;
  }
  
  public String getEmpunimed()
  {
    return this.empunimed;
  }
  
  public void setEmpunimed(String empunimed)
  {
    this.empunimed = empunimed;
  }
  
  public Timestamp getFeccreemp()
  {
    return this.feccreemp;
  }
  
  public void setFeccreemp(Timestamp feccreemp)
  {
    this.feccreemp = feccreemp;
  }
  
  public Timestamp getFecentdoc()
  {
    return this.fecentdoc;
  }
  
  public void setFecentdoc(Timestamp fecentdoc)
  {
    this.fecentdoc = fecentdoc;
  }
  
  public Timestamp getFecregemp()
  {
    return this.fecregemp;
  }
  
  public void setFecregemp(Timestamp fecregemp)
  {
    this.fecregemp = fecregemp;
  }
  
  public String getNomemp()
  {
    return this.nomemp;
  }
  
  public void setNomemp(String nomemp)
  {
    this.nomemp = nomemp;
  }
  
  public String getNomresrec()
  {
    return this.nomresrec;
  }
  
  public void setNomresrec(String nomresrec)
  {
    this.nomresrec = nomresrec;
  }
  
  public Long getNumcedafivol()
  {
    return this.numcedafivol;
  }
  
  public void setNumcedafivol(Long numcedafivol)
  {
    this.numcedafivol = numcedafivol;
  }
  
  public Long getNumlinnov()
  {
    return this.numlinnov;
  }
  
  public void setNumlinnov(Long numlinnov)
  {
    this.numlinnov = numlinnov;
  }
  
  public Long getNumlinsuc()
  {
    return this.numlinsuc;
  }
  
  public void setNumlinsuc(Long numlinsuc)
  {
    this.numlinsuc = numlinsuc;
  }
  
  public Long getNumtradec()
  {
    return this.numtradec;
  }
  
  public void setNumtradec(Long numtradec)
  {
    this.numtradec = numtradec;
  }
  
  public Long getNumtrarea()
  {
    return this.numtrarea;
  }
  
  public void setNumtrarea(Long numtrarea)
  {
    this.numtrarea = numtrarea;
  }
  
  public String getRucemp()
  {
    return this.rucemp;
  }
  
  public void setRucemp(String rucemp)
  {
    this.rucemp = rucemp;
  }
  
  public List<Sucursal> getSucursalList()
  {
    return this.sucursalList;
  }
  
  public void setSucursalList(List<Sucursal> sucursalList)
  {
    this.sucursalList = sucursalList;
  }
  
  public Sucursal addSucursal(Sucursal sucursal)
  {
    getSucursalList().add(sucursal);
    sucursal.setEmpleador(this);
    return sucursal;
  }
  
  public Sucursal removeSucursal(Sucursal sucursal)
  {
    getSucursalList().remove(sucursal);
    sucursal.setEmpleador(null);
    return sucursal;
  }
  
  public TipoEmpleador getTipoEmpleador()
  {
    return this.tipoEmpleador;
  }
  
  public void setTipoEmpleador(TipoEmpleador tipoEmpleador)
  {
    this.tipoEmpleador = tipoEmpleador;
  }
}
