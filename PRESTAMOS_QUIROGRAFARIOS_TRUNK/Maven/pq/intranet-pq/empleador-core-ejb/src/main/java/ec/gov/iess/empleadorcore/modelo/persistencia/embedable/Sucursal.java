package ec.gov.iess.empleadorcore.modelo.persistencia.embedable;

import ec.gov.iess.empleadorcore.modelo.persistencia.pk.SucursalPK;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({@javax.persistence.NamedQuery(name="Sucursal.findByRucEmpresa", query="select o from Sucursal o where o.sucursalPK.rucemp = :rucEmpresa order by o.sucursalPK.codsuc"), @javax.persistence.NamedQuery(name="Sucursal.findAll", query="select o from Sucursal o")})
@Table(name="KSPCOTSUCURSALES")
public class Sucursal
  implements Serializable
{
  private static final long serialVersionUID = -2297206844706452865L;
  @EmbeddedId
  protected SucursalPK sucursalPK;
  private String apematrepleg;
  private String apenomrepleg;
  private String apepatrepleg;
  private String cedesprepleg;
  private String cedfunact;
  private String cedrepleg;
  private String codactsec;
  private String codcii;
  private String codclabancen;
  private String codcuebancen;
  @Column(nullable=false)
  private String coddivpol;
  @Column(nullable=false)
  private String codestemp;
  private String codofiies;
  @Column(nullable=false)
  private Long codregadm;
  private String codtiprie;
  @Column(nullable=false)
  private Long codtipsuc;
  @Column(nullable=false)
  private Long connumacu;
  @Column(nullable=false)
  private Long connumcon;
  @Column(nullable=false)
  private Long connumglo;
  @Column(nullable=false)
  private Long connumtit;
  private String dessuc;
  private Long diasug;
  private String diresprepleg;
  private String dirrepleg;
  @Column(nullable=false)
  private String dirsuc;
  private String edisuc;
  @Column(nullable=false)
  private String estaju;
  private String estautdeb;
  @Column(nullable=false)
  private String estsuc;
  private String faxsuc;
  private Timestamp feccreregpat;
  private Timestamp fecentregpat;
  private Timestamp fecfunact;
  private Timestamp fecinirepleg;
  @Column(nullable=false)
  private Timestamp fecsolregpat;
  private Timestamp fecultpagapo;
  private Timestamp fecvigrepleg;
  private String mairepleg;
  private String maisuc;
  private String nomesprepleg;
  private String nomperrecregpat;
  private String nomrepleg;
  private Long numacitra;
  private Long numcarnue;
  private String numcueban;
  private Long numenfpro;
  private Long numenfsal;
  private Long numperimp;
  @Column(nullable=false)
  private Long numtradec;
  @Column(nullable=false)
  private Long numtrarea;
  @Column(nullable=false)
  private Long numusuocu;
  private String obssuc;
  private String ofisuc;
  private String pasrepleg;
  @Column(nullable=false)
  private String priprosuc;
  @Transient
  private String provincia;
  private String rucbandeb;
  private String secmunsuc;
  @Column(nullable=false)
  private Long secnumtra;
  private String telrepleg;
  private String telsuc;
  private String tipcueban;
  @Transient
  private String tipoSucursal;
  private String unimed;
  @ManyToOne
  @JoinColumns({@JoinColumn(name="RUCEMPPAD", referencedColumnName="RUCEMP"), @JoinColumn(name="CODSUCPAD", referencedColumnName="CODSUC")})
  private Sucursal sucursal;
  @OneToMany(mappedBy="sucursal")
  private List<Sucursal> sucursalList;
  @ManyToOne
  @JoinColumn(name="RUCEMP", insertable=false, updatable=false)
  private Empleador empleador;
  
  public String getApematrepleg()
  {
    return this.apematrepleg;
  }
  
  public void setApematrepleg(String apematrepleg)
  {
    this.apematrepleg = apematrepleg;
  }
  
  public String getApenomrepleg()
  {
    return this.apenomrepleg;
  }
  
  public void setApenomrepleg(String apenomrepleg)
  {
    this.apenomrepleg = apenomrepleg;
  }
  
  public String getApepatrepleg()
  {
    return this.apepatrepleg;
  }
  
  public void setApepatrepleg(String apepatrepleg)
  {
    this.apepatrepleg = apepatrepleg;
  }
  
  public String getCedesprepleg()
  {
    return this.cedesprepleg;
  }
  
  public void setCedesprepleg(String cedesprepleg)
  {
    this.cedesprepleg = cedesprepleg;
  }
  
  public String getCedfunact()
  {
    return this.cedfunact;
  }
  
  public void setCedfunact(String cedfunact)
  {
    this.cedfunact = cedfunact;
  }
  
  public String getCedrepleg()
  {
    return this.cedrepleg;
  }
  
  public void setCedrepleg(String cedrepleg)
  {
    this.cedrepleg = cedrepleg;
  }
  
  public String getCodactsec()
  {
    return this.codactsec;
  }
  
  public void setCodactsec(String codactsec)
  {
    this.codactsec = codactsec;
  }
  
  public String getCodcii()
  {
    return this.codcii;
  }
  
  public void setCodcii(String codcii)
  {
    this.codcii = codcii;
  }
  
  public String getCodclabancen()
  {
    return this.codclabancen;
  }
  
  public void setCodclabancen(String codclabancen)
  {
    this.codclabancen = codclabancen;
  }
  
  public String getCodcuebancen()
  {
    return this.codcuebancen;
  }
  
  public void setCodcuebancen(String codcuebancen)
  {
    this.codcuebancen = codcuebancen;
  }
  
  public String getCoddivpol()
  {
    return this.coddivpol;
  }
  
  public void setCoddivpol(String coddivpol)
  {
    this.coddivpol = coddivpol;
  }
  
  public String getCodestemp()
  {
    return this.codestemp;
  }
  
  public void setCodestemp(String codestemp)
  {
    this.codestemp = codestemp;
  }
  
  public String getCodofiies()
  {
    return this.codofiies;
  }
  
  public void setCodofiies(String codofiies)
  {
    this.codofiies = codofiies;
  }
  
  public Long getCodregadm()
  {
    return this.codregadm;
  }
  
  public void setCodregadm(Long codregadm)
  {
    this.codregadm = codregadm;
  }
  
  public String getCodtiprie()
  {
    return this.codtiprie;
  }
  
  public void setCodtiprie(String codtiprie)
  {
    this.codtiprie = codtiprie;
  }
  
  public Long getCodtipsuc()
  {
    return this.codtipsuc;
  }
  
  public void setCodtipsuc(Long codtipsuc)
  {
    this.codtipsuc = codtipsuc;
  }
  
  public Long getConnumacu()
  {
    return this.connumacu;
  }
  
  public void setConnumacu(Long connumacu)
  {
    this.connumacu = connumacu;
  }
  
  public Long getConnumcon()
  {
    return this.connumcon;
  }
  
  public void setConnumcon(Long connumcon)
  {
    this.connumcon = connumcon;
  }
  
  public Long getConnumglo()
  {
    return this.connumglo;
  }
  
  public void setConnumglo(Long connumglo)
  {
    this.connumglo = connumglo;
  }
  
  public Long getConnumtit()
  {
    return this.connumtit;
  }
  
  public void setConnumtit(Long connumtit)
  {
    this.connumtit = connumtit;
  }
  
  public String getDessuc()
  {
    return this.dessuc;
  }
  
  public void setDessuc(String dessuc)
  {
    this.dessuc = dessuc;
  }
  
  public Long getDiasug()
  {
    return this.diasug;
  }
  
  public void setDiasug(Long diasug)
  {
    this.diasug = diasug;
  }
  
  public String getDiresprepleg()
  {
    return this.diresprepleg;
  }
  
  public void setDiresprepleg(String diresprepleg)
  {
    this.diresprepleg = diresprepleg;
  }
  
  public String getDirrepleg()
  {
    return this.dirrepleg;
  }
  
  public void setDirrepleg(String dirrepleg)
  {
    this.dirrepleg = dirrepleg;
  }
  
  public String getDirsuc()
  {
    return this.dirsuc;
  }
  
  public void setDirsuc(String dirsuc)
  {
    this.dirsuc = dirsuc;
  }
  
  public String getEdisuc()
  {
    return this.edisuc;
  }
  
  public void setEdisuc(String edisuc)
  {
    this.edisuc = edisuc;
  }
  
  public String getEstaju()
  {
    return this.estaju;
  }
  
  public void setEstaju(String estaju)
  {
    this.estaju = estaju;
  }
  
  public String getEstautdeb()
  {
    return this.estautdeb;
  }
  
  public void setEstautdeb(String estautdeb)
  {
    this.estautdeb = estautdeb;
  }
  
  public String getEstsuc()
  {
    return this.estsuc;
  }
  
  public void setEstsuc(String estsuc)
  {
    this.estsuc = estsuc;
  }
  
  public String getFaxsuc()
  {
    return this.faxsuc;
  }
  
  public void setFaxsuc(String faxsuc)
  {
    this.faxsuc = faxsuc;
  }
  
  public Timestamp getFeccreregpat()
  {
    return this.feccreregpat;
  }
  
  public void setFeccreregpat(Timestamp feccreregpat)
  {
    this.feccreregpat = feccreregpat;
  }
  
  public Timestamp getFecentregpat()
  {
    return this.fecentregpat;
  }
  
  public void setFecentregpat(Timestamp fecentregpat)
  {
    this.fecentregpat = fecentregpat;
  }
  
  public Timestamp getFecfunact()
  {
    return this.fecfunact;
  }
  
  public void setFecfunact(Timestamp fecfunact)
  {
    this.fecfunact = fecfunact;
  }
  
  public Timestamp getFecinirepleg()
  {
    return this.fecinirepleg;
  }
  
  public void setFecinirepleg(Timestamp fecinirepleg)
  {
    this.fecinirepleg = fecinirepleg;
  }
  
  public Timestamp getFecsolregpat()
  {
    return this.fecsolregpat;
  }
  
  public void setFecsolregpat(Timestamp fecsolregpat)
  {
    this.fecsolregpat = fecsolregpat;
  }
  
  public Timestamp getFecultpagapo()
  {
    return this.fecultpagapo;
  }
  
  public void setFecultpagapo(Timestamp fecultpagapo)
  {
    this.fecultpagapo = fecultpagapo;
  }
  
  public Timestamp getFecvigrepleg()
  {
    return this.fecvigrepleg;
  }
  
  public void setFecvigrepleg(Timestamp fecvigrepleg)
  {
    this.fecvigrepleg = fecvigrepleg;
  }
  
  public String getMairepleg()
  {
    return this.mairepleg;
  }
  
  public void setMairepleg(String mairepleg)
  {
    this.mairepleg = mairepleg;
  }
  
  public String getMaisuc()
  {
    return this.maisuc;
  }
  
  public void setMaisuc(String maisuc)
  {
    this.maisuc = maisuc;
  }
  
  public String getNomesprepleg()
  {
    return this.nomesprepleg;
  }
  
  public void setNomesprepleg(String nomesprepleg)
  {
    this.nomesprepleg = nomesprepleg;
  }
  
  public String getNomperrecregpat()
  {
    return this.nomperrecregpat;
  }
  
  public void setNomperrecregpat(String nomperrecregpat)
  {
    this.nomperrecregpat = nomperrecregpat;
  }
  
  public String getNomrepleg()
  {
    return this.nomrepleg;
  }
  
  public void setNomrepleg(String nomrepleg)
  {
    this.nomrepleg = nomrepleg;
  }
  
  public Long getNumacitra()
  {
    return this.numacitra;
  }
  
  public void setNumacitra(Long numacitra)
  {
    this.numacitra = numacitra;
  }
  
  public Long getNumcarnue()
  {
    return this.numcarnue;
  }
  
  public void setNumcarnue(Long numcarnue)
  {
    this.numcarnue = numcarnue;
  }
  
  public String getNumcueban()
  {
    return this.numcueban;
  }
  
  public void setNumcueban(String numcueban)
  {
    this.numcueban = numcueban;
  }
  
  public Long getNumenfpro()
  {
    return this.numenfpro;
  }
  
  public void setNumenfpro(Long numenfpro)
  {
    this.numenfpro = numenfpro;
  }
  
  public Long getNumenfsal()
  {
    return this.numenfsal;
  }
  
  public void setNumenfsal(Long numenfsal)
  {
    this.numenfsal = numenfsal;
  }
  
  public Long getNumperimp()
  {
    return this.numperimp;
  }
  
  public void setNumperimp(Long numperimp)
  {
    this.numperimp = numperimp;
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
  
  public Long getNumusuocu()
  {
    return this.numusuocu;
  }
  
  public void setNumusuocu(Long numusuocu)
  {
    this.numusuocu = numusuocu;
  }
  
  public String getObssuc()
  {
    return this.obssuc;
  }
  
  public void setObssuc(String obssuc)
  {
    this.obssuc = obssuc;
  }
  
  public String getOfisuc()
  {
    return this.ofisuc;
  }
  
  public void setOfisuc(String ofisuc)
  {
    this.ofisuc = ofisuc;
  }
  
  public String getPasrepleg()
  {
    return this.pasrepleg;
  }
  
  public void setPasrepleg(String pasrepleg)
  {
    this.pasrepleg = pasrepleg;
  }
  
  public String getPriprosuc()
  {
    return this.priprosuc;
  }
  
  public void setPriprosuc(String priprosuc)
  {
    this.priprosuc = priprosuc;
  }
  
  public String getRucbandeb()
  {
    return this.rucbandeb;
  }
  
  public void setRucbandeb(String rucbandeb)
  {
    this.rucbandeb = rucbandeb;
  }
  
  public String getSecmunsuc()
  {
    return this.secmunsuc;
  }
  
  public void setSecmunsuc(String secmunsuc)
  {
    this.secmunsuc = secmunsuc;
  }
  
  public Long getSecnumtra()
  {
    return this.secnumtra;
  }
  
  public void setSecnumtra(Long secnumtra)
  {
    this.secnumtra = secnumtra;
  }
  
  public String getTelrepleg()
  {
    return this.telrepleg;
  }
  
  public void setTelrepleg(String telrepleg)
  {
    this.telrepleg = telrepleg;
  }
  
  public String getTelsuc()
  {
    return this.telsuc;
  }
  
  public void setTelsuc(String telsuc)
  {
    this.telsuc = telsuc;
  }
  
  public String getTipcueban()
  {
    return this.tipcueban;
  }
  
  public void setTipcueban(String tipcueban)
  {
    this.tipcueban = tipcueban;
  }
  
  public String getUnimed()
  {
    return this.unimed;
  }
  
  public void setUnimed(String unimed)
  {
    this.unimed = unimed;
  }
  
  public Sucursal getSucursal()
  {
    return this.sucursal;
  }
  
  public void setSucursal(Sucursal sucursal)
  {
    this.sucursal = sucursal;
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
    sucursal.setSucursal(this);
    return sucursal;
  }
  
  public Sucursal removeSucursal(Sucursal sucursal)
  {
    getSucursalList().remove(sucursal);
    sucursal.setSucursal(null);
    return sucursal;
  }
  
  public Empleador getEmpleador()
  {
    return this.empleador;
  }
  
  public void setEmpleador(Empleador empleador)
  {
    this.empleador = empleador;
  }
  
  public SucursalPK getSucursalPK()
  {
    return this.sucursalPK;
  }
  
  public void setSucursalPK(SucursalPK sucursalPK)
  {
    this.sucursalPK = sucursalPK;
  }
  
  public String getProvincia()
  {
    return this.provincia;
  }
  
  public void setProvincia(String provincia)
  {
    this.provincia = provincia;
  }
  
  public String getTipoSucursal()
  {
    return this.tipoSucursal;
  }
  
  public void setTipoSucursal(String tipoSucursal)
  {
    this.tipoSucursal = tipoSucursal;
  }
}
