package ec.gov.iess.afiliadocore.modelo.persistencia.pk;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ServiciosPrestadosHistoricoPK
  implements Serializable
{
  private static final long serialVersionUID = 803208589035090298L;
  @Column(nullable=false, insertable=false, updatable=false)
  public String codsuc;
  @Column(nullable=false)
  public Timestamp fecini;
  @Column(nullable=false, insertable=false, updatable=false)
  public String numafi;
  @Column(nullable=false, insertable=false, updatable=false)
  public String rucemp;
  
  public ServiciosPrestadosHistoricoPK() {}
  
  public ServiciosPrestadosHistoricoPK(String codsuc, Timestamp fecini, String numafi, String rucemp)
  {
    this.codsuc = codsuc;
    this.fecini = fecini;
    this.numafi = numafi;
    this.rucemp = rucemp;
  }
  
  public boolean equals(Object other)
  {
    if ((other instanceof ServiciosPrestadosHistoricoPK))
    {
      ServiciosPrestadosHistoricoPK otherServiciosPrestadosHistoricoPK = (ServiciosPrestadosHistoricoPK)other;
      boolean areEqual = (otherServiciosPrestadosHistoricoPK.codsuc.equals(this.codsuc)) && (otherServiciosPrestadosHistoricoPK.fecini.equals(this.fecini)) && (otherServiciosPrestadosHistoricoPK.numafi.equals(this.numafi)) && (otherServiciosPrestadosHistoricoPK.rucemp.equals(this.rucemp));
      
      return areEqual;
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode();
  }
  
  public String getCodsuc()
  {
    return this.codsuc;
  }
  
  public void setCodsuc(String codsuc)
  {
    this.codsuc = codsuc;
  }
  
  public Timestamp getFecini()
  {
    return this.fecini;
  }
  
  public void setFecini(Timestamp fecini)
  {
    this.fecini = fecini;
  }
  
  public String getNumafi()
  {
    return this.numafi;
  }
  
  public void setNumafi(String numafi)
  {
    this.numafi = numafi;
  }
  
  public String getRucemp()
  {
    return this.rucemp;
  }
  
  public void setRucemp(String rucemp)
  {
    this.rucemp = rucemp;
  }
}
