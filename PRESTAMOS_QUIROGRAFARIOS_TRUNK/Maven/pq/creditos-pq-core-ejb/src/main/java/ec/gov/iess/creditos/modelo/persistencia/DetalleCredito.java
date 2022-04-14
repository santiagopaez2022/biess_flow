/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
/**
 * 
 * Entida que representa Detalle del credito
 * 
 *@author rtituana
 *Cambios 30/08/2011 por Andres Cantos
 *cambios en el named query consultaPorDetalleCredito para que reconozca los estados APR y PDA
 * 
 */
@Entity
@SqlResultSetMapping(
		name="ResultadoDetalleCredito",
		entities=
			@EntityResult(
				entityClass=ec.gov.iess.creditos.modelo.persistencia.DetalleCredito.class
			)
		)		
@NamedNativeQuery(
		name="consultaPorDetalleCredito", 
		query=" SELECT CRE.NUMPREAFI numeroPrestamo, TO_CHAR(CRE.FECPREAFI,'dd-mm-yyyy') fechaPrestamoAfiliado,"+
		" BAN.NOMINSFIN banco, CRE.NUMCTABAN cuenta, TIP.DESTIPCUE tipoCuenta, "+
		" CRE.VALSALCAP - CRE.VALSEGSAL - NVL(CRE.VALLIQNOV, 0) transferencia, EST.DESESTPRE estado" +
		" FROM KSCRETCREDITOS CRE, KSCREVINSFIN BAN, KSCRETPREEST EST, KSCRETCUEBANTIP TIP"+
		" WHERE CRE.NUMAFI= :cedula"+
		" AND CRE.NUMPREAFI = :numeroPrestamo"+
		" AND CRE.CODPRETIP = :codPreTip"+
		" AND CRE.ORDPREAFI = :ordPreAfi"+
		" AND CRE.CODPRECLA = :codPreCla"+		
		" AND CRE.RUCEMPINSFIN = BAN.RUCINSFIN"+ 
		" AND CRE.CODESTPRE = EST.CODESTPRE"+ 
		" AND TIP.CODTIPCUE = CRE.CODTIPCUE"+ 
		" AND CRE.CODESTPRE IN ('GEN', 'ELT', 'VIG', 'ANU', 'REC', 'APR', 'PDA', 'PDC','PAP', 'PDV')",
		resultSetMapping="ResultadoDetalleCredito")
public class DetalleCredito implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long numeroPrestamo;
	private String fechaPrestamoAfiliado;
	private String banco;
	private String cuenta;
	private String tipoCuenta;
	/**
	 * Ricardo Tituana: INC-5780, cambio tipo de dato a bigdecimal
	 */
	private BigDecimal transferencia;
	private String estado;
	
	public DetalleCredito() {
	
	}

	public DetalleCredito(Long numeroPrestamo, String fechaPrestamoAfiliado,
			String banco, String cuenta, String tipoCuenta, BigDecimal transferencia,
			String estado) {
		super();
		this.numeroPrestamo = numeroPrestamo;
		this.fechaPrestamoAfiliado = fechaPrestamoAfiliado;
		this.banco = banco;
		this.cuenta = cuenta;
		this.tipoCuenta = tipoCuenta;
		this.transferencia = transferencia;
		this.estado = estado;
	}

	public Long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(Long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public String getFechaPrestamoAfiliado() {
		return fechaPrestamoAfiliado;
	}

	public void setFechaPrestamoAfiliado(String fechaPrestamoAfiliado) {
		this.fechaPrestamoAfiliado = fechaPrestamoAfiliado;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(BigDecimal transferencia) {
		this.transferencia = transferencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
