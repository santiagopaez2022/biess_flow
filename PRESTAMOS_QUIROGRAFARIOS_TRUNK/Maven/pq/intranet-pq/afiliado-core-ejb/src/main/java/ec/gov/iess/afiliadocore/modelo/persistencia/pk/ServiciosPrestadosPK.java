package ec.gov.iess.afiliadocore.modelo.persistencia.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ServiciosPrestadosPK
  implements Serializable
{
  private static final long serialVersionUID = -5944909381703141576L;
  @Column(nullable=false)
  public String codsuc;
  @Column(nullable=false)
  public String numafi;
  @Column(nullable=false)
  public String rucemp;
  
  public ServiciosPrestadosPK() {}
  
  public ServiciosPrestadosPK(String codsuc, String numafi, String rucemp)
  {
    this.codsuc = codsuc;
    this.numafi = numafi;
    this.rucemp = rucemp;
  }
  
  public boolean equals(Object other)
  {
    if ((other instanceof ServiciosPrestadosPK))
    {
      ServiciosPrestadosPK otherServiciosPrestadosPK = (ServiciosPrestadosPK)other;
      boolean areEqual = (otherServiciosPrestadosPK.codsuc.equals(this.codsuc)) && (otherServiciosPrestadosPK.numafi.equals(this.numafi)) && (otherServiciosPrestadosPK.rucemp.equals(this.rucemp));
      


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
