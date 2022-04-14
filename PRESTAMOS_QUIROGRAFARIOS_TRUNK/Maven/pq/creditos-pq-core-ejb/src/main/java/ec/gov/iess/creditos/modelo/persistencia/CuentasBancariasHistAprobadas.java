package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasHistPk;

@Entity
@Table(name="CRE_CUENTASBANCARIASHIST_TBL", schema="PQ_OWNER")
public class CuentasBancariasHistAprobadas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CuentasBancariasAprobadasHistPk cuentabancariahistpk;
	
	@Column(name = "CB_RUC_INST_FINANCIERA")
	private String rucinstfinanciera;
	
	@Column(name = "CB_ENTIDAD_FINANCIERA")
	private String entidadfinanciera;
		
	@Column(name = "CB_TIPO_CUENTA")
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipocta;
	
	@Column(name = "CB_NUMERO_CUENTA")
	private String numerocuenta;
	
	@Column(name = "CB_ESTADO")
	private String estado;
	
	@Column(name = "CB_FUNCIONARIO")
	private String funcionario;
	
	@Column(name = "CB_FECHAREGANT")
	private Date fechaanterior;

	public CuentasBancariasAprobadasHistPk getCuentabancariahistpk() {
		return cuentabancariahistpk;
	}

	public void setCuentabancariahistpk(
			CuentasBancariasAprobadasHistPk cuentabancariahistpk) {
		this.cuentabancariahistpk = cuentabancariahistpk;
	}

	public String getRucinstfinanciera() {
		return rucinstfinanciera;
	}

	public void setRucinstfinanciera(String rucinstfinanciera) {
		this.rucinstfinanciera = rucinstfinanciera;
	}

	public String getEntidadfinanciera() {
		return entidadfinanciera;
	}

	public void setEntidadfinanciera(String entidadfinanciera) {
		this.entidadfinanciera = entidadfinanciera;
	}

	public TipoCuenta getTipocta() {
		return tipocta;
	}

	public void setTipocta(TipoCuenta tipocta) {
		this.tipocta = tipocta;
	}

	public String getNumerocuenta() {
		return numerocuenta;
	}

	public void setNumerocuenta(String numerocuenta) {
		this.numerocuenta = numerocuenta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public Date getFechaanterior() {
		return fechaanterior;
	}

	public void setFechaanterior(Date fechaanterior) {
		this.fechaanterior = fechaanterior;
	}
	
}
