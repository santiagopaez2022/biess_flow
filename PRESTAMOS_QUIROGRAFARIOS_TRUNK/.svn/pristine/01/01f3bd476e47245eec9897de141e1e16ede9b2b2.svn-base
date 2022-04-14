package ec.gov.iess.empleadorcore.modelo.persistencia.embedable;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Seccion.findAll", query="select o from Seccion o")
@Table(name="KSPCOTSECCIONES")
public class Seccion
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @Column(nullable=false)
  private String codsec;
  @Column(nullable=false)
  private String dessec;
  @OneToMany(mappedBy="seccion")
  private List<TipoEmpleador> tipoEmpleadorList;
  
  public String getCodsec()
  {
    return this.codsec;
  }
  
  public void setCodsec(String codsec)
  {
    this.codsec = codsec;
  }
  
  public String getDessec()
  {
    return this.dessec;
  }
  
  public void setDessec(String dessec)
  {
    this.dessec = dessec;
  }
  
  public TipoEmpleador addTipoEmpleador(TipoEmpleador tipoEmpleador)
  {
    getTipoEmpleadorList().add(tipoEmpleador);
    tipoEmpleador.setSeccion(this);
    return tipoEmpleador;
  }
  
  public TipoEmpleador removeTipoEmpleador(TipoEmpleador tipoEmpleador)
  {
    getTipoEmpleadorList().remove(tipoEmpleador);
    tipoEmpleador.setSeccion(null);
    return tipoEmpleador;
  }
  
  public List<TipoEmpleador> getTipoEmpleadorList()
  {
    return this.tipoEmpleadorList;
  }
  
  public void setTipoEmpleadorList(List<TipoEmpleador> tipoEmpleadorList)
  {
    this.tipoEmpleadorList = tipoEmpleadorList;
  }
}
