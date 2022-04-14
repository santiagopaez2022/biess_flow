package ec.gov.iess.empleadorcore.modelo.persistencia.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SucursalPK
  implements Serializable
{
  private static final long serialVersionUID = 710431368413111044L;
  @Column(nullable=false)
  private String codsuc;
  @Column(name="RUCEMP", nullable=false)
  private String rucemp;
  
  public SucursalPK() {}
  
  public SucursalPK(String codsuc, String rucemp)
  {
    this.codsuc = codsuc;
    this.rucemp = rucemp;
  }
  
  public boolean equals(Object other)
  {
    if ((other instanceof SucursalPK))
    {
      SucursalPK otherSucursalPK = (SucursalPK)other;
      boolean areEqual = (otherSucursalPK.codsuc.equals(this.codsuc)) && (otherSucursalPK.rucemp.equals(this.rucemp));
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
  
  public String getRucemp()
  {
    return this.rucemp;
  }
  
  public void setRucemp(String rucemp)
  {
    this.rucemp = rucemp;
  }
}
