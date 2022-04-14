package ec.gov.iess.afiliadocore.modelo.persistencia.embedable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="AfiliadoCuentaBancaria.findAll", query="select o from AfiliadoCuentaBancaria o")
@NamedNativeQueries({@javax.persistence.NamedNativeQuery(name="AfiliadoCuentaBancaria.consultarPorCedulaAfiliado", query=" SELECT * FROM AFI_CUENTABANCARIA_VW WHERE CB_CEDULA = ?1 ", resultClass=AfiliadoCuentaBancaria.class)})
@Table(name="AFI_CUENTABANCARIA_VW")
public class AfiliadoCuentaBancaria
  implements Serializable
{
  private static final long serialVersionUID = 8236400947056434026L;
  @Id
  @Column(name="CB_CODIGOCUENTA", nullable=false)
  private Long codigoCuenta;
  @Column(name="CB_CEDULA", nullable=false)
  private String cedulaAfiliado;
  @Column(name="CB_NUMERO", nullable=false)
  private String numeroCuenta;
  @Column(name="CB_TIPO", nullable=true)
  private String tipoCuenta;
  @Column(name="CB_RUC", nullable=true)
  private String rucFinanciera;
  @Column(name="CB_BANCO", nullable=true)
  private String nombreFinanciera;
  
  public Long getCodigoCuenta()
  {
    return this.codigoCuenta;
  }
  
  public void setCodigoCuenta(Long codigoCuenta)
  {
    this.codigoCuenta = codigoCuenta;
  }
  
  public String getCedulaAfiliado()
  {
    return this.cedulaAfiliado;
  }
  
  public void setCedulaAfiliado(String cedulaAfiliado)
  {
    this.cedulaAfiliado = cedulaAfiliado;
  }
  
  public String getNumeroCuenta()
  {
    return this.numeroCuenta;
  }
  
  public void setNumeroCuenta(String numeroCuenta)
  {
    this.numeroCuenta = numeroCuenta;
  }
  
  public String getTipoCuenta()
  {
    return this.tipoCuenta;
  }
  
  public void setTipoCuenta(String tipoCuenta)
  {
    this.tipoCuenta = tipoCuenta;
  }
  
  public String getRucFinanciera()
  {
    return this.rucFinanciera;
  }
  
  public void setRucFinanciera(String rucFinanciera)
  {
    this.rucFinanciera = rucFinanciera;
  }
  
  public String getNombreFinanciera()
  {
    return this.nombreFinanciera;
  }
  
  public void setNombreFinanciera(String nombreFinanciera)
  {
    this.nombreFinanciera = nombreFinanciera;
  }
}
