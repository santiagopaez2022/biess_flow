package ec.gov.iess.empleadorcore.modelo.persistencia.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TipoEmpleadorPK
  implements Serializable
{
  private static final long serialVersionUID = 7859514013005262799L;
  @Column(nullable=false)
  public String codsegsoc;
  @Column(nullable=false)
  public String codtipemp;
  
  public TipoEmpleadorPK() {}
  
  public TipoEmpleadorPK(String codsegsoc, String codtipemp)
  {
    this.codsegsoc = codsegsoc;
    this.codtipemp = codtipemp;
  }
  
  public boolean equals(Object other)
  {
    if ((other instanceof TipoEmpleadorPK))
    {
      TipoEmpleadorPK otherKspcotemptipPK = (TipoEmpleadorPK)other;
      boolean areEqual = (otherKspcotemptipPK.codsegsoc.equals(this.codsegsoc)) && (otherKspcotemptipPK.codtipemp.equals(this.codtipemp));
      
      return areEqual;
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode();
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
}
