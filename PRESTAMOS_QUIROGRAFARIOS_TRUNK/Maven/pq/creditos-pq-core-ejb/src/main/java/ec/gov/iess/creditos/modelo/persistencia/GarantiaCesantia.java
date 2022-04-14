package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Incluir aquí la descripción de la clase.
 *  
 * @version $Revision: 1.1 $  [Jul 26, 2007]
 * @author pmlopez
 */
@Entity
@Table(name="KSAFITCESANTIAS")
public class GarantiaCesantia implements Serializable {
	
	private static final long serialVersionUID = 1058373850158924490L;

	@Id
	@Column(name="CEDULA")
	private String cedula;
	
	@Column(name="CESMINT") 
	private BigDecimal valorHost;
	
	@Column(name="VALCOM")
	private BigDecimal valorComprometidoHost;
	
	@Column(name="CESHISLAB") 
	private BigDecimal valorHistoriaLaboral;
	
	@Column(name="VALCOMHIS") 
	private BigDecimal valorComprometidoHl;
	
	@Column(name="SALTOT")
	private BigDecimal totalCesantia;
	
	@Column(name="FECCAR")
	private Timestamp fechaCarga;
	
	@Column(name="ESTADO")
	private String estado;
	
	@Column(name="ESTACT")
	private String estact;
	
	@Column(name="BANDERA") 
	private String bandera;
	
	@Column(name="FECHASCAL") 
	private Timestamp fechascal;
	
	@Column(name="FECEJEPROACT") 
	private Timestamp fecejeproact;
	
	/**
	 * Retorna el valor de cedula.
	 * @return cedula
	 */
	public String getCedula() {
		return cedula;
	}

	
	/**
	 * Asigna el valor de cedula.
	 * @param cedula
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
	/**
	 * Retorna el valor de valorHost.
	 * @return valorHost
	 */
	public BigDecimal getValorHost() {
		return valorHost;
	}

	
	/**
	 * Asigna el valor de valorHost.
	 * @param valorHost
	 */
	public void setValorHost(BigDecimal valorHost) {
		this.valorHost = valorHost;
	}

	
	/**
	 * Retorna el valor de valorComprometidoHost.
	 * @return valorComprometidoHost
	 */
	public BigDecimal getValorComprometidoHost() {
		return valorComprometidoHost;
	}

	
	/**
	 * Asigna el valor de valorComprometidoHost.
	 * @param valorComprometidoHost
	 */
	public void setValorComprometidoHost(BigDecimal valorComprometidoHost) {
		this.valorComprometidoHost = valorComprometidoHost;
	}

	
	/**
	 * Retorna el valor de valorHistoriaLaboral.
	 * @return valorHistoriaLaboral
	 */
	public BigDecimal getValorHistoriaLaboral() {
		return valorHistoriaLaboral;
	}

	
	/**
	 * Asigna el valor de valorHistoriaLaboral.
	 * @param valorHistoriaLaboral
	 */
	public void setValorHistoriaLaboral(BigDecimal valorHistoriaLaboral) {
		this.valorHistoriaLaboral = valorHistoriaLaboral;
	}

	
	/**
	 * Retorna el valor de valorComprometidoHl.
	 * @return valorComprometidoHl
	 */
	public BigDecimal getValorComprometidoHl() {
		return valorComprometidoHl;
	}

	
	/**
	 * Asigna el valor de valorComprometidoHl.
	 * @param valorComprometidoHl
	 */
	public void setValorComprometidoHl(BigDecimal valorComprometidoHl) {
		this.valorComprometidoHl = valorComprometidoHl;
	}

	
	/**
	 * Retorna el valor de totalCesantia.
	 * @return totalCesantia
	 */
	public BigDecimal getTotalCesantia() {
		return totalCesantia;
	}

	
	/**
	 * Asigna el valor de totalCesantia.
	 * @param totalCesantia
	 */
	public void setTotalCesantia(BigDecimal totalCesantia) {
		this.totalCesantia = totalCesantia;
	}

	
	/**
	 * Retorna el valor de fechaCarga.
	 * @return fechaCarga
	 */
	public Timestamp getFechaCarga() {
		return fechaCarga;
	}

	
	/**
	 * Asigna el valor de fechaCarga.
	 * @param fechaCarga
	 */
	public void setFechaCarga(Timestamp fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	
	/**
	 * Retorna el valor de estado.
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	
	/**
	 * Asigna el valor de estado.
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	/**
	 * Retorna el valor de estact.
	 * @return estact
	 */
	public String getEstact() {
		return estact;
	}

	
	/**
	 * Asigna el valor de estact.
	 * @param estact
	 */
	public void setEstact(String estact) {
		this.estact = estact;
	}

	
	/**
	 * Retorna el valor de bandera.
	 * @return bandera
	 */
	public String getBandera() {
		return bandera;
	}

	
	/**
	 * Asigna el valor de bandera.
	 * @param bandera
	 */
	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	
	/**
	 * Retorna el valor de fechascal.
	 * @return fechascal
	 */
	public Timestamp getFechascal() {
		return fechascal;
	}

	
	/**
	 * Asigna el valor de fechascal.
	 * @param fechascal
	 */
	public void setFechascal(Timestamp fechascal) {
		this.fechascal = fechascal;
	}

	
	/**
	 * Retorna el valor de fecejeproact.
	 * @return fecejeproact
	 */
	public Timestamp getFecejeproact() {
		return fecejeproact;
	}

	
	/**
	 * Asigna el valor de fecejeproact.
	 * @param fecejeproact
	 */
	public void setFecejeproact(Timestamp fecejeproact) {
		this.fecejeproact = fecejeproact;
	}

	
	
	
}