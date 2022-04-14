package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.modelo.persistencia.pk.CuentasBancariasAprobadasPk;


@Entity
@Table(name="CRE_CUENTASBANCARIAS_TBL", schema="PQ_OWNER")
@NamedQueries( {
	@NamedQuery(name = "CuentasBancariasAprobadas.busquedaporafiliado", 
	query = "select o from CuentasBancariasAprobadas o where o.cuentabancariapk.cedula = :cedula")
})
public class CuentasBancariasAprobadas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CuentasBancariasAprobadasPk cuentabancariapk;
	
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
	
	public CuentasBancariasAprobadasPk getCuentabancariapk() {
		return cuentabancariapk;
	}

	public void setCuentabancariapk(CuentasBancariasAprobadasPk cuentabancariapk) {
		this.cuentabancariapk = cuentabancariapk;
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
	
}
